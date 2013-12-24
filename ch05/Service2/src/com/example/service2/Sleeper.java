package com.example.service2;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class Sleeper extends IntentService {

	private long seconds;
	
	public Sleeper() {
		super("Sleeper");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		seconds = intent.getExtras().getLong("seconds");
		
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, String.format("Slept %d seconds", seconds), Toast.LENGTH_SHORT).show();
	}

}
