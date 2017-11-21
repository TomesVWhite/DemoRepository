package com.tomes.wheather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void WheatherService(View v){
		Intent intent=new Intent(this,WheatherService.class);
		startService(intent);
	}
}
