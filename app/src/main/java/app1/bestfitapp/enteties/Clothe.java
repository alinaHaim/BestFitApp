package app1.bestfitapp.enteties;

import android.net.Uri;

import java.util.ArrayList;

import app1.bestfitapp.managers.MMCQorginal;

/**
 * Created by user on 04/03/2018.
 */

public class Clothe {
    public enum e_type{pants,shirt};

    public Uri uri;
    public MMCQorginal.ColorMap color;
    public e_type type;
}
