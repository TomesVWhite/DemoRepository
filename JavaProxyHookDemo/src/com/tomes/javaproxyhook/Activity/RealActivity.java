package com.tomes.javaproxyhook.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.tomes.javaproxyhook.R;

/**这个activity未在配置清单里申明，是为了实现替换activity
 * @author Tomes
 *
 */
public class RealActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_real);
	}
}
