package app1.bestfitapp.enteties;

import java.util.ArrayList;

/**
 * Created by user on 06/03/2018.
 */

public class Clothes extends ArrayList<Clothe>{

    public Clothes getByType(Clothe.e_type type) {
        Clothes res = new Clothes() ;

        for(Clothe c:this){
            if(c.type == type){
                res.add(c);
            }
        }
        return  res;
    }
}
