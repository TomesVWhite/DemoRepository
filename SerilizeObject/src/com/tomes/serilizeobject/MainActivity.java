package com.tomes.serilizeobject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**跨进程简单传递数据，可以将数据封装到一个对象中，序列化该对象，然后搭载在intent上，传递出去
 * @author Tomes
 *
 */
public class MainActivity extends Activity {

	private Person person;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		person = new Person(1,"wang");
		person.student.put("name", "小明");
	}

	public void to2(View v){
		Intent intent=new Intent(this,SecondActivity.class);
		intent.putExtra("person", person);
		startActivity(intent);
	}
}
