package app1.bestfitapp.managers;

import java.util.ArrayList;

import app1.bestfitapp.enteties.Clothe;

/**
 * Created by user on 04/03/2018.
 */

public class DataManager {
    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public ArrayList<Clothe> clothes;

    private DataManager() {
        clothes = new ArrayList<>();
    }

    
}
