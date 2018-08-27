package fingerprinttest.jimhan.com.library.fingerprintplugin.fingerprintlib;

import android.content.Context;
import android.content.Intent;

import fingerprinttest.jimhan.com.library.fingerprintplugin.fingerprintlib.core.FingerprintCore;
import fingerprinttest.jimhan.com.library.fingerprintplugin.fingerprintlib.log.FPLog;

/**
 * Created by popfisher on 2016/11/7.
 */

public class FingerprintUtil {

    private static final String ACTION_SETTING = "android.settings.SETTINGS";

    public static String SUPPORT = "0";
    public static String NOT_SUPPORT = "1";
    public static String NO_FINGERPRINT = "2";

    public static void openFingerPrintSettingPage(Context context) {
        Intent intent = new Intent(ACTION_SETTING);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

//    public FingerPrintDialog fingerPrintDialog;
    private FingerprintCore mFingerprintCore;
    private Context context;
    public FingerprintUtil(Context context, FingerprintCore.IFingerprintResultListener listener) {
//        fingerPrintDialog = new FingerPrintDialog();
        initFingerprintCore(context, listener);
        this.context = context;
    }

    public void initFingerprintCore(Context context, FingerprintCore.IFingerprintResultListener listener) {
        mFingerprintCore = new FingerprintCore(context);
        if(null != listener){
            mFingerprintCore.setFingerprintManager(listener);
            FPLog.log("listener not null");
        } else {
            FPLog.log("listener null");
        }
    }

    /**
     * 开始指纹识别
     */
    public void startFingerprintRecognition(Context context) {
        if (mFingerprintCore.isAuthenticating()) {
            FPLog.showTips(context, "指纹识别已经开启，长按指纹解锁键");
        } else {
            mFingerprintCore.startAuthenticate();
        }
    }

    public void startFingerprintRecognitionNoDialog(Context context) {
        if (mFingerprintCore.isAuthenticating()) {
            FPLog.showTips(context, "指纹识别已经开启，长按指纹解锁键");
        } else {
            mFingerprintCore.startAuthenticate();
        }
    }

    public void stopFingerprintRecognition() {
        mFingerprintCore.cancelAuthenticate();
    }

    public void destroy() {
        if(null != mFingerprintCore) {
            mFingerprintCore.onDestroy();
        }
    }

    public String supportFingerprint() {
        if(mFingerprintCore.isSupport()) {
            if(mFingerprintCore.isHasEnrolledFingerprints()) {
                return SUPPORT;
            } else {
                return NO_FINGERPRINT;
            }
        }
        return NOT_SUPPORT;
    }

}
