package app1.bestfitapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by user on 12/03/2018.
 */

public class BuildManager {

    public static final int PERMISSIONS_REQUEST_STORAGE = 113;
    public static final int PERMISSIONS_REQUEST_CAMERA = 114;

    public static boolean isAndroid6() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }




    public static boolean hasStoragePermission(Context context) {
        if (!BuildManager.isAndroid6()) {
            return true;
        }

        return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    public static boolean hasCameraPermission(Context context) {
        if (!BuildManager.isAndroid6()) {
            return true;
        }

        return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }


    private static int cameraCounter = 0;
    public static ePermissionType askCameraPermission(Activity activity) {
        if (!hasCameraPermission(activity)) {
            if (cameraCounter < 2 ||!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                cameraCounter++;
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISSIONS_REQUEST_CAMERA);

                return ePermissionType.asking;
            }

            return ePermissionType.noPermission;
        }

        return ePermissionType.hasPermission;
    }

    public static boolean askCameraPermissionAgain(Activity activity) {
        if (!hasCameraPermission(activity)) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_CAMERA);

            return true;
        }

        return false;
    }

    public static boolean askStoragePermissionAgain(Activity activity) {

        if (!hasStoragePermission(activity)) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_STORAGE);

            return true;
        }

        return false;
    }

    public enum ePermissionType {noPermission, asking, hasPermission}

    private static int storageCounter = 0;
    public static ePermissionType askStoragePermission(Activity activity) {
        if (!hasStoragePermission(activity)) {
            if (storageCounter < 2 || !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                storageCounter++;
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_STORAGE);

                return ePermissionType.asking;
            }

            return ePermissionType.noPermission;
        }

        return ePermissionType.hasPermission;
    }



    public interface PermissionRequestInterface {
        void allow();

        void showMessage(String msg);
    }

    public static void onRequestPermissionsResult(Context context, int requestCode, String[] permissions, int[] grantResults, PermissionRequestInterface permissionRequestInterface) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionRequestInterface.allow();
            return;
        }

        switch (requestCode) {
            case PERMISSIONS_REQUEST_STORAGE: {
                permissionRequestInterface.showMessage(context.getString(R.string.permissions_request_storage));
                permissionRequestInterface.allow();
                break;
            }
            case PERMISSIONS_REQUEST_CAMERA: {
                permissionRequestInterface.showMessage(context.getString(R.string.permissions_request_camera));
                permissionRequestInterface.allow();
                break;
            }
        }
    }
}