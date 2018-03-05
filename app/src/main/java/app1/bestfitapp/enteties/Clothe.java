package app1.bestfitapp.enteties;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by user on 04/03/2018.
 */

public class Clothe {
    public enum e_type{pants,shirt};

    public Uri uri;
    public ArrayList<ColorLevel> mainColor;
    public ArrayList<ColorLevel> printColor;
    public e_type type;
}
