package app1.bestfitapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_about).setOnClickListener(this);
        findViewById(R.id.button_closet).setOnClickListener(this);
        findViewById(R.id.test_button).setOnClickListener(this);

        //if(Build.VERSION >= )
       checkAppPermission();
    }

    private void checkAppPermission() {
        if (!BuildManager.hasCameraPermission(this)) {
            BuildManager.askCameraPermission(this);
        }
        if (!BuildManager.hasStoragePermission(this)) {
            BuildManager.askStoragePermission(this);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BuildManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults, new BuildManager.PermissionRequestInterface() {
            @Override
            public void allow() {
                checkAppPermission();
            }

            @Override
            public void showMessage(String msg) {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_about: {

                Intent intent_add = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent_add);
                break;
            }
            case R.id.button_closet: {

                Intent intent_add = new Intent(MainActivity.this, ClosetActivity.class);
                startActivity(intent_add);
                break;
            }

            case R.id.test_button: {

                Intent intent_add = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent_add);
                break;
            }
        }
    }
}
