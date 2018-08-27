package fingerprinttest.jimhan.com.library.fingerprintplugin;

import android.app.Activity;

import fingerprinttest.jimhan.com.library.fingerprintplugin.fingerprintlib.FingerprintUtil;
import fingerprinttest.jimhan.com.library.fingerprintplugin.fingerprintlib.core.FingerprintCore;

public class FingerFacadeJava {
    private FingerprintUtil fingerprintUtil;
    private Activity activity;
    public interface FingerCallback {
        void onSuccess();
        void onFailed();
    }
    public FingerFacadeJava(Activity activity, final FingerCallback fingerCallback) {
        FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
            @Override
            public void onAuthenticateSuccess() {
                fingerCallback.onSuccess();
            }

            @Override
            public void onAuthenticateFailed(int helpId) {

            }

            @Override
            public void onAuthenticateError(int errMsgId) {
                fingerCallback.onFailed();
            }

            @Override
            public void onStartAuthenticateResult(boolean isSuccess) {

            }
        };
        this.fingerprintUtil = new FingerprintUtil(activity, mResultListener);
        this.activity = activity;
    }

    /**
     * 是否支持指纹
     * @return
     */
    public String isSupportFingerprint() {
        return fingerprintUtil.supportFingerprint();
    }

    /**
     * 关闭指纹识别
     */
    public void stopFingerprintValidate() {
        //支持指纹的情况下使用指纹
        if (FingerprintUtil.SUPPORT == fingerprintUtil.supportFingerprint()) {
            fingerprintUtil.stopFingerprintRecognition();
        }
    }

    /**
     * 开启指纹识别
     */
    public void startFingerprintValidate() {
        //支持指纹的情况下使用指纹
        String result = fingerprintUtil.supportFingerprint();
        if (FingerprintUtil.SUPPORT.equals(result)) {
            fingerprintUtil.startFingerprintRecognition(activity);
        }
    }
}
