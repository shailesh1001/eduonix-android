package com.example.service2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
	
	public void startService(View view) {
		EditText sleepTime = (EditText) findViewById(R.id.editSeconds);
		final long seconds = Long.parseLong(sleepTime.getText().toString());
		Intent intent = new Intent(MainActivity.this, Sleeper.class);
		intent.putExtra("seconds", seconds);
		startService(intent);
		
	}

}
