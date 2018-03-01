package app1.bestfitapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ImageActivity extends AppCompatActivity {
    public  interface ImageUriActivityHandler{
        void receivedImage(Uri uri);
        void cancel();
    }

    static ImageUriActivityHandler imageUriActivityHandler;
    static  int resultCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageManager.showChoserFileListOptions(ImageActivity.this, resultCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (imageUriActivityHandler != null){
                Uri uri = ImageManager.getChoserFileUtiOnActivityResult(getApplicationContext(),data);
                if (uri != null){
                    imageUriActivityHandler.receivedImage(uri);
                }
            }


        }else if (imageUriActivityHandler != null){
            imageUriActivityHandler.cancel();
        }

        finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public  static void show(Activity activity, int resultCode, ImageUriActivityHandler handler){
        ///TODO check camera permission , check storage perrmission

        imageUriActivityHandler = handler;
        ImageActivity.resultCode=resultCode;
        Intent intent = new Intent(activity,ImageActivity.class);
        activity.startActivity(intent);
    }

}
