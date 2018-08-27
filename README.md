###指纹解锁
######Android从6.0版本开始，提供了指纹解锁的api。本文使用api中的FingerprintManager类，封装了一个可供直接调用的类库。
###使用方式
- ######添加依赖
Step 1. Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```

	dependencies {
	        implementation 'com.github.stewardHan:fingerprintplugin:3.0.0'
	}
```
- ######具体使用
kotlin
```
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
```
java
```
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
```
###总结
######本文涉及到的代码托管地址：https://github.com/stewardHan/fingerprintplugin.git
本文参考了[FingerprintRecognition](https://github.com/PopFisher/FingerprintRecognition)，感谢。



