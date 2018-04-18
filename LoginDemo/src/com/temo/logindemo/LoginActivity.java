package com.temo.logindemo;

import com.android.debug.hv.ViewServer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**登陆测试demo,作为xposed练手，和Hierarchy Viewer使用的demo
 * @author Administrator
 *
 */
public class LoginActivity extends Activity {


	 private final String ACCOUNT="wang";  
	    private final String PASSWORD="123456";  
	    private EditText etAccount, etPassword;  
	    private Button btnLogin;  
	  
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_login);  
	  
	        etAccount=(EditText)findViewById(R.id.et_account);  
	  
	        etPassword=(EditText)findViewById(R.id.et_password);  
	  
	        btnLogin=(Button)findViewById(R.id.btn_login);  
	  
	        btnLogin.setOnClickListener(new View.OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                if (isOK(etAccount.getText().toString(), etPassword.getText().toString())) {  
	                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();  
	                } else {  
	                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();  
	                }  
	  
	            }  
	        });  
	        //启动ViewServer
	        ViewServer.get(this).addWindow(this);  
	    }  
	  
	    private boolean isOK(String account, String password){  
	        return account.equals(ACCOUNT) && password.equals(PASSWORD);  
	    }  

	    @Override
	    protected void onDestroy() {
	    	super.onDestroy();
	    	//移除ViewServer
	    	ViewServer.get(this).removeWindow(this);  
	    }
	    
	    @Override
	    protected void onResume() {
	    	super.onResume();
	    	ViewServer.get(this).setFocusedWindow(this);  
	    }
}
