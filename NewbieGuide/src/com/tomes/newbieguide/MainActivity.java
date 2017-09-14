package com.tomes.newbieguide;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.app.hubert.library.HighLight;
import com.app.hubert.library.NewbieGuide;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = (TextView) findViewById(R.id.tv);
        NewbieGuide.with(this)//传入activity
                .setLabel("guide1")//设置引导层标示，必传！否则报错
                .addHighLight(textView, HighLight.Type.RECTANGLE)//添加需要高亮的view
                .setLayoutRes(R.layout.view_guide)//自定义的提示layout，不要添加背景色，引导层背景色通过setBackgroundColor()设置
                .setBackgroundColor(Color.GRAY)
                .alwaysShow(true)//设置这个这是总是显示，不设置则是只有在初次启动的时候显示蒙板层，第
                .show();//直接显示引导层
	}

}
