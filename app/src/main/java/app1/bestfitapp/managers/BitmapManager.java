package app1.bestfitapp.managers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by user on 01/03/2018.
 */

public class BitmapManager {

    public static int getAverageColor(Bitmap bitmap) {
        int R = 0, G = 0, B = 0, W, H;
        W = bitmap.getWidth();
        H = bitmap.getHeight();
        int[] pixels = new int[W * H];
        bitmap.getPixels(pixels, 0, W, 0, 0, W, H);

        for (int i = 0; i < pixels.length; i++) {
            R += Color.red(pixels[i]);
            G += Color.green(pixels[i]);
            B += Color.blue(pixels[i]);
        }

        return Color.rgb(R / pixels.length, G / pixels.length, B / pixels.length);
    }
    public static int getPrintColor(Bitmap bitmap) {
        int R = 0, G = 0, B = 0, W, H;
        W = bitmap.getWidth();
        H = bitmap.getHeight();
        int[] pixels = new int[W*H];
        //int[] pixels = new int[1872*2496];
        bitmap.getPixels(pixels, 0, W, 0, 0, W, H);

        for (int i = 0; i < pixels.length; i++) {
            R += Color.red(pixels[i]);
            G += Color.green(pixels[i]);
            B += Color.blue(pixels[i]);
        }

        return Color.rgb(R / pixels.length, G / pixels.length, B / pixels.length);
    }

    public static int[] getAverageColorList(Bitmap bitmap, int levels) {
        int[] R = new int[levels];
        int[] G = new int[levels];
        int[] B = new int[levels];
        int[] counter = new int[levels];
        int levelPart = (256*3) / levels;

        int W = bitmap.getWidth();
        int H = bitmap.getHeight();
        int[] pixels = new int[W * H];
        bitmap.getPixels(pixels, 0, W, 0, 0, W, H);

        for (int i = 0; i < pixels.length; i++) {
            int r = Color.red(pixels[i]);
            int g = Color.green(pixels[i]);
            int  b = Color.blue(pixels[i]);
            int a = r+g+b;
            int levelIndex = a / levelPart;
            if(levelIndex >= levels){
                levelIndex = levels-1;
            }

            R[levelIndex] += Color.red(pixels[i]);
            G[levelIndex] += Color.green(pixels[i]);
            B[levelIndex] += Color.blue(pixels[i]);
            counter[levelIndex]++;
        }

        int[] res = new int[levels];
        for(int i=0;i<levels;i++){
            res[i] = Color.rgb(R[i] / counter[i], G[i] / counter[i], B[i] / counter[i]);
        }

        return res;
    }

    public static void setViewBackgroundColor(View view, int color){
        view.setBackgroundColor(color);
    }

    public static void setViewsBackgroundColor(View[] views,Bitmap bitmap){
        int[] colors = getAverageColorList(bitmap,views.length);
        for(int i=0;i<views.length;i++){
            setViewBackgroundColor(views[i],colors[i]);
        }
    }
}
