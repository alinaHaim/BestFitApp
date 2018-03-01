package app1.bestfitapp;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class AddActivity extends AppCompatActivity {
    Button plus_button;
    ImageView image_view_camera;
    static final int CAM_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        plus_button=(Button) findViewById(R.id.plus_button);
        image_view_camera=(ImageView)findViewById(R.id.image_view_camera);
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TakePhoto_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getNewImage();

            }
        });
    }



    public void getNewImage(){
        ImageActivity.show(this, 1, new ImageActivity.ImageUriActivityHandler() {
            @Override
            public void receivedImage(Uri uri) {
                image_view_camera.setImageURI(uri);

            }

            @Override
            public void cancel() {

            }
        });
    }
}

