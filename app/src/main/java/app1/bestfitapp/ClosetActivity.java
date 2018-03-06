package app1.bestfitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import app1.bestfitapp.adapters.ClothesAdapter;
import app1.bestfitapp.enteties.Clothe;
import app1.bestfitapp.managers.DataManager;

public class ClosetActivity extends AppCompatActivity implements View.OnClickListener {
ListView     lv_pants;
    ListView lv_shirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);
        lv_pants = findViewById(R.id.lv_pants);
        lv_shirt = findViewById(R.id.lv_shirt);

        findViewById(R.id.add_closet_button).setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ClothesAdapter adapter = new ClothesAdapter(this, DataManager.getInstance(getApplicationContext()).clothes.getByType(Clothe.e_type.pants));
        lv_pants.setAdapter(adapter);

        adapter = new ClothesAdapter(this, DataManager.getInstance(getApplicationContext()).clothes.getByType(Clothe.e_type.shirt));
        lv_shirt.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent_add= new Intent(ClosetActivity.this,AddActivity.class);
        startActivity(intent_add);
    }
}
