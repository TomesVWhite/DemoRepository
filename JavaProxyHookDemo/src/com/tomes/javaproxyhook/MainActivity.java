package com.tomes.javaproxyhook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void to2(View v){
		toSecond();
	}
	private void toSecond() {
		Intent intent=new Intent(this,SecondActivity.class);
		/*intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getApplicationContext().startActivity(intent);*/
		startActivity(intent);
	}

}
