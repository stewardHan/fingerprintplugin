package fingerprint.jimhan.com.fingerprintplugin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fingerprint.jimhan.com.fingerprintplugin.fingerprintlib.FingerprintUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fingerFacade = FingerFacade(this, {
            Toast.makeText(this, "指纹验证成功", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(this, "指纹验证失败", Toast.LENGTH_SHORT).show()
        })

        btn_start.setOnClickListener {
            Toast.makeText(this, "开始指纹验证", Toast.LENGTH_SHORT).show()
            if (fingerFacade.isSupportFingerprint() == FingerprintUtil.SUPPORT) {
                fingerFacade.startFingerprintValidate()
            } else {
                Toast.makeText(this, "您的设备不支持指纹", Toast.LENGTH_SHORT).show()
            }
        }

        btn_end.setOnClickListener {
            fingerFacade.stopFingerprintValidate()
        }

    }
}
