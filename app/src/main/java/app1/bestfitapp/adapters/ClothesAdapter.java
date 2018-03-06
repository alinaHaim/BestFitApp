package app1.bestfitapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import app1.bestfitapp.R;
import app1.bestfitapp.enteties.Clothe;
import app1.bestfitapp.enteties.Clothes;

/**
 * Created by user on 06/03/2018.
 */

public class ClothesAdapter extends ArrayAdapter<Clothe> {
    Activity context;
    public ClothesAdapter(Activity context,Clothes clothes) {
        super(context, R.layout.clothe_item,clothes);
       this. context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         View v = context.getLayoutInflater().inflate(R.layout.clothe_item, parent, false);
        Clothe c = getItem(position);
        ((ImageView)v.findViewById(R.id.iv_clothe)).setImageURI(c.uri);
         return  v;
    }
}
