

package app1.bestfitapp.managers;

import com.google.gson.Gson;

/**
 * Created by user on 06/03/2018.
 */

public class JsonManager {
    public static String getString(Object o){
        return new Gson().toJson(o);
    }

    public static  <T> T getObject(String json, Class<T> classOfT){
        return new Gson().fromJson(json,classOfT);
    }
}
