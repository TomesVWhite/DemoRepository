package com.tomes.javaproxyhook.Activity;

import com.tomes.javaproxyhook.R;
import com.tomes.javaproxyhook.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**这个activity是替身activity假的，内部甚至不需要实现，但必须有这个类，否则会出现找不到这个类报错的情况。
 * @author Tomes
 *
 */
public class SubstituteActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView=new TextView(this);
		textView.setText("SubstituteActivity");
		setContentView(textView);
	}
}
