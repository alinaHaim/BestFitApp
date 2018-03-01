package app1.bestfitapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TestActivity extends AppCompatActivity {

    Button plus2_button;
    ImageView image_view_cameraTest;
    //image_view_cameraTest.buildDrawingCache(true);
    //Bitmap image_bmap = image_view_cameraTest.getDrawingCache();
    //ImageView image_view_cameraTest;
    static final int CAM_REQUEST=1;
    Button test_btn;
    ImageView image_view_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        plus2_button=(Button) findViewById(R.id.plus2_button);
        image_view_cameraTest=(ImageView)findViewById(R.id.image_view_cameraTest);
        plus2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TakePhoto_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                getNewImage();

            }
        });
        //test_btn=(Button)findViewById(R.id.test_button);
        //image_view_1=(ImageView)findViewById(R.id.image_view_1);
        //test_btn.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //Intent CheckPhoto_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //setNewImageView();


            }
        //});

    //}



    public void getNewImage(){
        ImageActivity.show(this, 1, new ImageActivity.ImageUriActivityHandler() {
            @Override
            public void receivedImage(Uri uri) {
                image_view_cameraTest.setImageURI(uri);


            }

            @Override
            public void cancel() {

            }
        });
    }
    //public void setNewImageView(){

      //  BitmapManager.setViewBackgroundColor(image_view_1,BitmapManager.getAverageColor(image_bmap));

    //}
}