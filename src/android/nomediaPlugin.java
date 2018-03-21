package nomediaPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import java.io.File;
import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Build;
import java.lang.Exception;
import java.io.FileOutputStream;

/**
 * This class echoes a string called from JavaScript.
 */
public class nomediaPlugin extends CordovaPlugin {
    String[] permissions = { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("nomedia")) {
            String message = args.getString(0);
            this.nomedia(message, callbackContext);
            return true;
        }
        return false;
    }

    private void nomedia(String filePath, CallbackContext callbackContext) {
        getPermission();

        //File nomedia = new File(filePath + "/.nomedia");
        File nomedia = new File( filePath , ".nomedia");
        if (!nomedia.exists())
        {
            try {
                nomedia.createNewFile();
                FileOutputStream nomediaFos = new FileOutputStream(nomedia);
                nomediaFos.flush();
                nomediaFos.close();
                callbackContext.success("nomedia");
            } catch (Exception e) {
                e.printStackTrace();
                callbackContext.error("create nomedia error");
            }
        }else{
            callbackContext.success("hadnomedia");
        }

    }

    public void getPermission() {
        if (hasPermisssion()) {
        } else {
            PermissionHelper.requestPermissions(this, 0, permissions);
        }
    }

    public boolean hasPermisssion() {
        for (String p : permissions) {
            if (!PermissionHelper.hasPermission(this, p)) {
                return false;
            }
        }
        return true;
    }

}
