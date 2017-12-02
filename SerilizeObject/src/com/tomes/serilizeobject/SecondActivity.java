package com.tomes.serilizeobject;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView textView=new TextView(this);
		textView.setText("--->SecondActivity");
		setContentView(textView);
		
		Intent intent = getIntent();
		Person p=(Person) intent.getSerializableExtra("person");
		System.out.println("p.getAge():"+p.getAge());
		System.out.println("p.getName():"+p.getName());
		Map student = p.student;
		System.out.println("p.student:"+student.get("name"));
	}
}
