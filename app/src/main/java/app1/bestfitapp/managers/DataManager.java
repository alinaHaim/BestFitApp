package app1.bestfitapp.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import app1.bestfitapp.enteties.Clothe;
import app1.bestfitapp.enteties.Clothes;

/**
 * Created by user on 04/03/2018.
 */

public class DataManager {
    private static DataManager instance;
    private final String KEY_CLOTHES="KEY_CLOTHES";

    public static DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }

        return instance;
    }

    public Clothes clothes;
    SharedPreferences mPreferenceManager;

    private DataManager(Context context) {
        mPreferenceManager = context.getSharedPreferences("aaa",0);
       loadClothes();
    }

    private void loadClothes() {
        String sc = mPreferenceManager.getString(KEY_CLOTHES,"");
        if(true || sc.length()==0){
            clothes = new Clothes() ;
        }else{
            clothes = JsonManager.getObject(sc,Clothes.class);
        }
    }

    public void addClothe(Clothe clothe) {
        clothes.add(clothe);
        saveClothes();
    }
    public void deleteClothe(Clothe clothe) {
        if(!clothes.contains(clothe)){
            return;
        }

        clothes.remove(clothe);
        saveClothes();
    }

    public void saveClothes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor ed=  mPreferenceManager.edit();
                ed.putString(KEY_CLOTHES,JsonManager.getString(clothes));
                ed.commit();
            }
        }).start();
    }
}
