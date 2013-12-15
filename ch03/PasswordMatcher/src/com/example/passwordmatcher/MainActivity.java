package com.example.passwordmatcher;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void submitPassword(View view) {
		TextView correctPwdView = (TextView) findViewById(R.id.correct_pwd_value);
		final String correctPwd = correctPwdView.getText().toString();
		
		TextView pwdView = (TextView) findViewById(R.id.pwd_value);
		final String pwd = pwdView.getText().toString();
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout);
		layout.setBackgroundColor(correctPwd.equals(pwd) ? Color.GREEN : Color.RED);
	}
}
