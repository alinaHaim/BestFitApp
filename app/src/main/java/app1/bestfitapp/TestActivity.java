package app1.bestfitapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import app1.bestfitapp.managers.BitmapManager;
import app1.bestfitapp.managers.MMCQ;
import app1.bestfitapp.managers.MMCQorginal;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_test_1;
    ImageView iv_image_1;
    View v_1_1;
    View v_1_2;
    View v_1_3;
    View v_1_4;
    View v_1_5;

    Button btn_test_2;
    ImageView iv_image_2;
    View v_2;

    Button btn_test_3;
    ImageView iv_image_3;
    View v_3_1;
    View v_3_2;

    Uri uri1;
    Uri uri2;
    Uri uri3;

    static final int CAM_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btn_test_1 = findViewById(R.id.btn_test_1);
        iv_image_1 = findViewById(R.id.iv_image_1);
        v_1_1 = findViewById(R.id.v_1_1);
        v_1_2 = findViewById(R.id.v_1_2);
        v_1_3 = findViewById(R.id.v_1_3);
        v_1_4 = findViewById(R.id.v_1_4);
        v_1_5= findViewById(R.id.v_1_5);

        btn_test_2 = findViewById(R.id.btn_test_2);
        iv_image_2 = findViewById(R.id.iv_image_2);
        v_2 = findViewById(R.id.v_2);
        btn_test_3=findViewById(R.id.btn_test_3);
        iv_image_3=findViewById(R.id.iv_image_3);
        v_3_1=findViewById(R.id.v_3_1);
        v_3_2=findViewById(R.id.v_3_2);

        btn_test_1.setOnClickListener(this);
        iv_image_1.setOnClickListener(this);
        btn_test_2.setOnClickListener(this);
        iv_image_2.setOnClickListener(this);
        btn_test_3.setOnClickListener(this);
        iv_image_3.setOnClickListener(this);

    }


    public void getNewImage( int test) {
        ImageActivity.show(this, 1, new ImageActivity.ImageUriActivityHandler() {
            @Override
            public void receivedImage(Uri uri) {
                switch (test){
                    case 1: {
                        uri1=uri;
                        break;
                    }
                    case 2: {
                        uri2=uri;
                        break;
                    }
                    case 3:{
                        uri3=uri;
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
        iv_image_1.setImageURI(uri1);
        iv_image_2.setImageURI(uri2);
        iv_image_3.setImageURI(uri3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_1: {
                Bitmap bitmap = getBitmapByUri(uri1);
                if (bitmap == null) {
                    return;
                }

               // BitmapManager.setViewsBackgroundColor(new View[]{v_1_1,v_1_2,v_1_3,v_1_4},bitmap);

                try {
                    List<MMCQ.ColorValue> list = MMCQ.compute(bitmap, 5,20);
                    View[] views = new View[]{v_1_1,v_1_2,v_1_3,v_1_4,v_1_5};
                    for(int i=0;i<views.length;i++){
                         views[i].setBackgroundColor(list.get(i).getRGB());
                        ((TextView)views[i]).setText(list.get(i).getPercentToShow());
                    }



                }catch (Exception ex){
                    Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.iv_image_1: {
                getNewImage(1);
                break;
            }
            case R.id.btn_test_2: {
                Bitmap bitmap = getBitmapByUri(uri2);
                if (bitmap == null) {
                    return;
                }

                int color = BitmapManager.getAverageColor(bitmap);
                v_2.setBackgroundColor(color);
                break;
            }
            case R.id.iv_image_2: {
                getNewImage(2);
                break;
            }
            case R.id.btn_test_3: {
                Bitmap bitmap = getBitmapByUri(uri3);
                if (bitmap == null) {
                    return;
                }

                int color_avg = BitmapManager.getAverageColor(bitmap);
                v_3_1.setBackgroundColor(color_avg);
                int color_print = BitmapManager.getPrintColor(bitmap);
                v_3_2.setBackgroundColor(color_print);
                break;
            }
            case R.id.iv_image_3: {
                getNewImage(3);
                break;
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