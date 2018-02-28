package app1.bestfitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClosetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        findViewById(R.id.add_closet_button).setOnClickListener((v) -> {

            Intent intent_add= new Intent(ClosetActivity.this,AddActivity.class);
            startActivity(intent_add);
        });
    }
}
