package com.tomes.signature;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

/**简单的java层签名校验，破解只需要在onCreate判断里进行判断修改就行
 * @author Tomes
 *
 */
public class MainActivity extends Activity {
	//用的是lol签名
	private static final String app_sign = "3082022b30820194a00302010202040b9e44dc300d06092a864886f70d01010505003059310b300906035504030c024a4a310b3009060355040b0c024a4a310f300d060355040a0c066a616368756e310b300906035504070c02535a3112301006035504080c094775616e67446f6e67310b300906035504061302434e3020170d3135313031363130303333305a180f32303635313030333130303333305a3059310b300906035504030c024a4a310b3009060355040b0c024a4a310f300d060355040a0c066a616368756e310b300906035504070c02535a3112301006035504080c094775616e67446f6e67310b300906035504061302434e30819f300d06092a864886f70d010101050003818d0030818902818100ce077c27045f0e805f7f567fdda196ca2a101aac72f1679c2096d09849012ce7a1048b0d00b4b030a4dd2818aabd051ae16bc263b946c9678980ef2de36ec97bc68813e0c458e5bffa711b1cd101384f960efa3a5c7243111dc69aaaf7057d3b9bf34716b0de400505d40803b8c413243c04a968af834478be8d6b6ac00ea6310203010001300d06092a864886f70d0101050500038181002b63dc95bfdf1546ae31683d8b67fd4e78fd5e77601caf521eb711a3e32d8b310161bbab657a0c18509cb9244f3359e67be8b7fd8150186621696808f4c60b3ba625729fd58d81cfdbef378493bcaaee2d99322e589a9708bad757ec3f722a155ccedb739259ecdc281caae8699ea1e87b7adc897b150909c481335e4c243037";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(!app_sign.equals(getSignMD5())){
			finish();
			android.os.Process.killProcess(Process.myPid());
		}
	}

	private String getSignMD5() {

		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(
					getPackageName(), PackageManager.GET_SIGNATURES);
			Signature[] signatures = packageInfo.signatures;
			StringBuilder sb = new StringBuilder();
			for (Signature signature : signatures) {
				sb.append(signature.toCharsString());
			}
			Log.i("--->Tomes", sb.toString());
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
