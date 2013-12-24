package com.example.service3;

import com.example.service3.BoundService.LocalBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private BoundService service;
	
	private boolean bound = false;
	
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			bound = false;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder bService) {
			LocalBinder binder = (LocalBinder) bService;
			service = binder.getService();
			bound = true;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(MainActivity.this, BoundService.class);
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		if (bound) unbindService(conn);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void add(View view) {
		EditText editNumbers = (EditText) findViewById(R.id.editNumbers);
		String[] input = editNumbers.getText().toString().split(" ");
		int[] numbers = new int[input.length];
		
		for (int i = 0; i < numbers.length; i++)
			numbers[i] = Integer.valueOf(input[i]);
			
		Toast.makeText(this, String.format("sum: %d", service.add(numbers)), Toast.LENGTH_SHORT).show();
	}

}
