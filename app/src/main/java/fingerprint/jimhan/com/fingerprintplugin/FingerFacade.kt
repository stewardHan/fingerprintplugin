package fingerprint.jimhan.com.fingerprintplugin

import android.app.Activity
import fingerprint.jimhan.com.fingerprintplugin.fingerprintlib.FingerprintUtil
import fingerprint.jimhan.com.fingerprintplugin.fingerprintlib.core.FingerprintCore

class FingerFacade(val activity: Activity, val successCallback: () -> Unit, val errorCallback: () -> Unit) {
    private val mResultListener = object : FingerprintCore.IFingerprintResultListener {
        override fun onAuthenticateSuccess() {
            activity.runOnUiThread {
                successCallback()
            }
        }

        override fun onAuthenticateFailed(helpId: Int) {
            activity.runOnUiThread {
            }
        }

        override fun onAuthenticateError(errMsgId: Int) {
            activity.runOnUiThread {
                errorCallback()
            }
        }

        override fun onStartAuthenticateResult(isSuccess: Boolean) {}
    }

    var fingerprintUtil = FingerprintUtil(activity, mResultListener)

    /**
     * 是否支持指纹
     * @return
     */
    fun isSupportFingerprint() : String {
        return fingerprintUtil.supportFingerprint()
    }

    /**
     * 关闭指纹识别
     */
    fun stopFingerprintValidate() {
        //支持指纹的情况下使用指纹
        if (FingerprintUtil.SUPPORT == fingerprintUtil.supportFingerprint()) {
            fingerprintUtil.stopFingerprintRecognition()
        }
    }

    /**
     * 开启指纹识别
     */
    fun startFingerprintValidate() {
        //支持指纹的情况下使用指纹
        val result = fingerprintUtil.supportFingerprint()
        if (FingerprintUtil.SUPPORT == result) {
            fingerprintUtil.startFingerprintRecognition(activity)
        }
    }

}