package com.tomes.javaproxyhook.Activity;

import com.tomes.javaproxyhook.R;
import com.tomes.javaproxyhook.R.layout;

import android.app.Activity;
import android.os.Bundle;

public class SecondActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}
}
