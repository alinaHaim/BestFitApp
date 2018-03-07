package app1.bestfitapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


import java.io.File;

import app1.bestfitapp.enteties.Clothe;
import app1.bestfitapp.managers.BitmapManager;
import app1.bestfitapp.managers.DataManager;


public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    Button plus_button;
    Button ok_button;
    ImageView image_view_camera;
    static final int CAM_REQUEST=1;

    RadioButton shirtRbtn, pantsRbtn;
    Clothe clothe;
    Button submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        plus_button=findViewById(R.id.plus_button);
        image_view_camera=findViewById(R.id.image_view_camera);

        shirtRbtn = findViewById(R.id.shirt_rbtn);
        pantsRbtn = findViewById(R.id.pants_rbtn);

        ok_button = findViewById(R.id.ok_button);

        ok_button.setOnClickListener(this);
        plus_button.setOnClickListener(this);
        clothe =new Clothe();
        updateUi();

    }

    public void getNewImage(int test){
        ImageActivity.show(this, 1, new ImageActivity.ImageUriActivityHandler() {
            @Override
            public void receivedImage(Uri uri) {
                switch (test) {
                    case 1: {
                        clothe.uri = uri;
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
        image_view_camera.setImageURI(clothe.uri);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.plus_button:{
                getNewImage(1);
                break;
            }
            case R.id.ok_button: {
                if(clothe.uri==null){
                    Toast.makeText(this,getResources().getString(R.string.load_image),Toast.LENGTH_SHORT).show();
                    return;
                }

                if (shirtRbtn.isChecked()) {
                   clothe.type = Clothe.e_type.shirt;
                } else if (pantsRbtn.isChecked()) {
                    clothe.type = Clothe.e_type.pants;
                }

                DataManager.getInstance(getApplicationContext()).addClothe(clothe);
                clothe=new Clothe();
                updateUi();
            }
        }
    }

    private Bitmap getBitmapByUri(Uri uri) {
        Bitmap res = null;

        if(uri==null){
            Toast.makeText(this,getResources().getString(R.string.load_image),Toast.LENGTH_SHORT).show();
            return null;
        }

        try {
            res = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        }

        if(res == null){
            Toast.makeText(this,getResources().getString(R.string.load_image_error),Toast.LENGTH_LONG).show();
        }

        return  res;
    }
}


