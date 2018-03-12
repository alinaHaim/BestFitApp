package app1.bestfitapp.managers;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by user on 08/03/2018.
 */

public class Utils {
    public static void showDialogdResult_YesNo(Context context, int title, int message, int yesText, int NoText, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showDialodResult_YesNo(context,context.getString(title), context.getString( message), context.getString( yesText),context.getString(  NoText),yesListener,noListener);
    }

    public static void showDialodResult_YesNo(Context context, String title, String message, String yesText, String NoText, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title) ;
        alertDialog.setMessage("\n" + message + "\n");
        alertDialog.setPositiveButton(yesText , yesListener);
        alertDialog.setNegativeButton(NoText , noListener);
        alertDialog.create().show();
    }
}
