package com.tomes.defineddialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void dialog(View v) {
		Builder builder = new AlertDialog.Builder(this);
		final AlertDialog alertDialog = builder.create();
		//通过布局填充，自定义dialog
		View layout = View.inflate(this, R.layout.dialog_passwordsetting, null);
		alertDialog.setView(layout);
		final EditText et_password = (EditText) layout.findViewById(R.id.et_password);
		final EditText et_confirmPassword = (EditText) layout
				.findViewById(R.id.et_confirmPassword);
		Button bt_cancel = (Button) layout.findViewById(R.id.bt_cancel);
		Button bt_confirm = (Button) layout.findViewById(R.id.bt_confirm);
		bt_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});
		bt_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String password = et_password.getText().toString();
				String confirmPassword = et_confirmPassword.getText().toString();
				if (!TextUtils.isEmpty(password)
						&& !TextUtils.isEmpty(confirmPassword)) {

					if (password.equals(confirmPassword)) {
						Toast.makeText(MainActivity.this, "login success.",
								Toast.LENGTH_SHORT).show();
						alertDialog.dismiss();
					} else {
						Toast.makeText(
								MainActivity.this,
								"the defference between password and confirmPassword.",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(MainActivity.this, "please input password.",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		alertDialog.show();

	}
}
