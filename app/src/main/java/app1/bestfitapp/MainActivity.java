package app1.bestfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_about).setOnClickListener((v) -> {

            Intent intent_add= new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent_add);
        });
        findViewById(R.id.button_closet).setOnClickListener((v) -> {

            Intent intent_add= new Intent(MainActivity.this,ClosetActivity.class);
            startActivity(intent_add);
        });

        findViewById(R.id.test_button).setOnClickListener((v) -> {

            Intent intent_add= new Intent(MainActivity.this,TestActivity.class);
            startActivity(intent_add);
        });



    }


}
