package fingerprinttest.jimhan.com.library.fingerprintplugin.fingerprintlib.log;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import fingerprinttest.jimhan.com.library.BuildConfig;


/**
 * Created by 77423 on 2016/11/7.
 */

public class FPLog {

    public static void log(String message) {
         if (BuildConfig.DEBUG) {
            Log.i("FPLog", message);
         }
    }

    public static void showTips(Context context, String tips) {
        Toast.makeText(context, tips, Toast.LENGTH_SHORT).show();
    }

}
