package app1.bestfitapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import app1.bestfitapp.managers.BitmapManager;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_test_1;
    ImageView iv_image_1;
    View v_1_1;
    View v_1_2;
    View v_1_3;
    View v_1_4;

    Button btn_test_2;
    ImageView iv_image_2;
    View v_2;

    Uri uri1;
    Uri uri2;

    static final int CAM_REQUEST = 1;
    Button test_btn;
    ImageView image_view_1;


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
        btn_test_2 = findViewById(R.id.btn_test_2);
        iv_image_2 = findViewById(R.id.iv_image_2);
        v_2 = findViewById(R.id.v_2);

        btn_test_1.setOnClickListener(this);
        iv_image_1.setOnClickListener(this);
        btn_test_2.setOnClickListener(this);
        iv_image_2.setOnClickListener(this);

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test_1: {
                Bitmap bitmap = getBitmapByUri(uri1);
                if (bitmap == null) {
                    return;
                }

                BitmapManager.setViewsBackgroundColor(new View[]{v_1_1,v_1_2,v_1_3,v_1_4},bitmap);
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