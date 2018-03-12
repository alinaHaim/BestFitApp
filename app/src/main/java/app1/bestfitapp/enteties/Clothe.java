package app1.bestfitapp.enteties;

import android.net.Uri;

import java.util.ArrayList;

import app1.bestfitapp.managers.MMCQorginal;

/**
 * Created by user on 04/03/2018.
 */

public class Clothe {
    public enum e_type{pants,shirt};

    public Clothe(){
        colors = new ArrayList<>() ;
    }

    public String path;
    public  ArrayList<ClotheColor> colors;
    public e_type type;
}
