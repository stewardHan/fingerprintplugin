package fingerprint.jimhan.com.fingerprintplugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import fingerprinttest.jimhan.com.library.fingerprintplugin.FingerFacadeJava;
import fingerprinttest.jimhan.com.library.fingerprintplugin.fingerprintlib.FingerprintUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        invokeKotlin();
       invokeJava();
    }

    private void invokeJava() {
        final FingerFacadeJava fingerFacadeJava = new FingerFacadeJava(this, new FingerFacadeJava.FingerCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(JavaActivity.this, "指纹验证成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed() {
                Toast.makeText(JavaActivity.this, "指纹验证失败", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JavaActivity.this, "开始指纹验证", Toast.LENGTH_SHORT).show();
                if (fingerFacadeJava.isSupportFingerprint() == FingerprintUtil.SUPPORT) {
                    fingerFacadeJava.startFingerprintValidate();
                } else {
                    Toast.makeText(JavaActivity.this, "您的设备不支持指纹", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fingerFacadeJava.stopFingerprintValidate();
            }
        });

    }

    private void invokeKotlin() {
        final FingerFacade fingerFacade = new FingerFacade(this, new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Toast.makeText(JavaActivity.this, "指纹验证成功", Toast.LENGTH_SHORT).show();
                return null;
            }
        }, new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Toast.makeText(JavaActivity.this, "指纹验证失败", Toast.LENGTH_SHORT).show();
                return null;
            }
        });
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(JavaActivity.this, "开始指纹验证in java", Toast.LENGTH_SHORT).show();
                if (fingerFacade.isSupportFingerprint() == FingerprintUtil.SUPPORT) {
                    fingerFacade.startFingerprintValidate();
                } else {
                    Toast.makeText(JavaActivity.this, "您的设备不支持指纹", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fingerFacade.stopFingerprintValidate();
            }
        });
    }
}
