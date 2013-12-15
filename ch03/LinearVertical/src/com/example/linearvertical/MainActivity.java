package com.example.linearvertical;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
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
	
	public void sayHi(View view) {
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
		mainLayout.setBackgroundColor(Color.CYAN);
		
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText("Hi there!");
	}

}
