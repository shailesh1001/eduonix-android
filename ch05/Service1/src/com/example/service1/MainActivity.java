package com.example.service1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText numbersText;
	
	private Intent averagingIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		numbersText = (EditText) findViewById(R.id.editNumbers);
	}

	public void startService(View view) {
		String[] strNumbers = numbersText.getText().toString().split(" ");
		int[] numbers = new int[strNumbers.length];
		
		for (int i=0; i < strNumbers.length; i++) {
			numbers[i] = Integer.parseInt(strNumbers[i]);
		}
		averagingIntent = new Intent(MainActivity.this, AveragingService.class);
		averagingIntent.putExtra("numbers", numbers);
		
		startService(averagingIntent);
	}
	
	public void stopService(View view) {
		stopService(averagingIntent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
