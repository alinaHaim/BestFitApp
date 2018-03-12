package app1.bestfitapp;


import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app1.bestfitapp.enteties.Clothe;
import app1.bestfitapp.enteties.ClotheColor;
import app1.bestfitapp.managers.DataManager;
import app1.bestfitapp.managers.MMCQ;


public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    View plus_button;
    Button ok_button;
    ImageView image_view_camera;
    static final int CAM_REQUEST = 1;

    RadioButton shirtRbtn, pantsRbtn;
    Clothe clothe;
    ArrayList<SelectedClotheColor> colors;

    Button submit;
    View v_color_selected_1;
    View v_color_1;
    View v_color_selected_2;
    View v_color_2;
    View v_color_selected_3;
    View v_color_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        plus_button = findViewById(R.id.plus_button);
        image_view_camera = findViewById(R.id.image_view_camera);
        v_color_selected_1 = findViewById(R.id.v_color_selected_1);
        v_color_1 = findViewById(R.id.v_color_1);
        v_color_selected_2 = findViewById(R.id.v_color_selected_2);
        v_color_2 = findViewById(R.id.v_color_2);
        v_color_selected_3 = findViewById(R.id.v_color_selected_3);
        v_color_3 = findViewById(R.id.v_color_3);

        v_color_1.setOnClickListener(this);
        v_color_2.setOnClickListener(this);
        v_color_3.setOnClickListener(this);

        shirtRbtn = findViewById(R.id.shirt_rbtn);
        pantsRbtn = findViewById(R.id.pants_rbtn);

        ok_button = findViewById(R.id.ok_button);

        ok_button.setOnClickListener(this);
        plus_button.setOnClickListener(this);
        clothe = new Clothe();
        updateUi();

    }

    public void getNewImage(int test) {
        ImageActivity.show(this, 1, new ImageActivity.ImageUriActivityHandler() {
            @Override
            public void receivedImage(Uri uri) {
                switch (test) {
                    case 1: {
                        clothe.path = uri.getPath();
                        try {
                            Bitmap bitmap = getBitmapByUri(uri);
                            if (bitmap == null) {
                                return;
                            }
                            colors = new ArrayList<>();
                            List<MMCQ.ColorValue> list = MMCQ.compute(bitmap, 3, 20);
                            for (int i = 0; i < list.size() && i < 3; i++) {
                                SelectedClotheColor cc = new SelectedClotheColor();
                                cc.value = list.get(i);
                                colors.add(cc);
                            }


                        } catch (IOException ex) {
                            Toast.makeText(AddActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                        } catch (Exception ex) {
                            Toast.makeText(AddActivity.this, getString(R.string.get_color_failed), Toast.LENGTH_LONG).show();
                        }

                        break;
                    }

                }
                updateUi();
            }


            @Override
            public void cancel() {

            }
        });
    }

    private void updateUi() {
        if (clothe.path == null) {
            image_view_camera.setImageURI(null);
            image_view_camera.setVisibility(View.GONE);
            plus_button.setVisibility(View.VISIBLE);
            ok_button.setVisibility(View.GONE);
        } else {
            image_view_camera.setImageURI(Uri.parse(clothe.path));
            image_view_camera.setVisibility(View.VISIBLE);
            plus_button.setVisibility(View.GONE);
            ok_button.setVisibility(View.VISIBLE);
        }



        if (colors == null || colors.isEmpty()) {
            findViewById(R.id.ll_colors).setVisibility(View.INVISIBLE);
            findViewById(R.id.ll_type).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.ll_colors).setVisibility(View.VISIBLE);
            findViewById(R.id.ll_type).setVisibility(View.VISIBLE);
            v_color_1.setBackgroundColor(colors.get(0).value.getRGB());
            v_color_selected_1.setVisibility(colors.get(0).selected ? View.VISIBLE : View.GONE);
            ClotheColor cc = new ClotheColor(colors.get(0).value.r,colors.get(0).value.g,colors.get(0).value.b) ;
            ((TextView) findViewById(R.id.tv_level_1) ).setText(cc.level+"");

            if (colors.size() > 1) {
                v_color_2.setBackgroundColor(colors.get(1).value.getRGB());
                v_color_selected_2.setVisibility(colors.get(1).selected ? View.VISIBLE : View.GONE);
                ClotheColor cc2 = new ClotheColor(colors.get(1).value.r,colors.get(1).value.g,colors.get(1).value.b) ;
                ((TextView) findViewById(R.id.tv_level_2) ).setText(cc2.level+"");
            } else {
                v_color_2.setBackground(null);
                v_color_selected_2.setVisibility(View.GONE);
                ((TextView) findViewById(R.id.tv_level_2) ).setText("");
            }

            if (colors.size() > 2) {
                v_color_3.setBackgroundColor(colors.get(2).value.getRGB());
                v_color_selected_3.setVisibility(colors.get(2).selected ? View.VISIBLE : View.GONE);
                ClotheColor cc3 = new ClotheColor(colors.get(2).value.r,colors.get(2).value.g,colors.get(2).value.b) ;
                ((TextView) findViewById(R.id.tv_level_3) ).setText(cc3.level+"");
            } else {
                v_color_3.setBackground(null);
                v_color_selected_3.setVisibility(View.GONE);
                ((TextView) findViewById(R.id.tv_level_3) ).setText("");
            }
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.plus_button: {
                getNewImage(1);
                break;
            }
            case R.id.ok_button: {
                if (clothe.path == null) {
                    Toast.makeText(this, getResources().getString(R.string.load_image), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (shirtRbtn.isChecked()) {
                    clothe.type = Clothe.e_type.shirt;
                } else if (pantsRbtn.isChecked()) {
                    clothe.type = Clothe.e_type.pants;
                }

                clothe.colors.clear();
                for (SelectedClotheColor scc : colors) {
                    if (scc.selected) {
                        ClotheColor cc = new ClotheColor(scc.value.r, scc.value.g, scc.value.b);
                        clothe.colors.add(cc);
                    }
                }

                if (clothe.colors.size() == 0) {
                    Toast.makeText(this, getResources().getString(R.string.no_color_selected), Toast.LENGTH_SHORT).show();
                    return;
                }

                DataManager.getInstance(getApplicationContext()).addClothe(clothe);
                clothe = new Clothe();
                colors = null;
                Toast.makeText(this, getResources().getString(R.string.add_clothe_successful), Toast.LENGTH_SHORT).show();

                updateUi();
                break;
            }
            case R.id.v_color_1: {
                colors.get(0).selected = !colors.get(0).selected;
                updateUi();
                break;
            }
            case R.id.v_color_2: {
                colors.get(1).selected = !colors.get(1).selected;
                updateUi();
                break;
            }
            case R.id.v_color_3: {
                colors.get(2).selected = !colors.get(2).selected;
                updateUi();
                break;
            }
        }
    }

    class SelectedClotheColor {
        MMCQ.ColorValue value;
        boolean selected;
    }

    private Bitmap getBitmapByUri(Uri uri) {
        Bitmap res = null;

        if (uri == null) {
            Toast.makeText(this, getResources().getString(R.string.load_image), Toast.LENGTH_SHORT).show();
            return null;
        }

        try {
            res = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }

        if (res == null) {
            Toast.makeText(this, getResources().getString(R.string.load_image_error), Toast.LENGTH_LONG).show();
        }

        return res;
    }
}


