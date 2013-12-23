package com.example.service1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.webkit.WebView.FindListener;
import android.widget.Toast;

public class AveragingService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int[] numbers = intent.getExtras().getIntArray("numbers");
		float avg = findAverage(numbers);
		Toast.makeText(getApplicationContext(), String.format("Average: %f", avg), Toast.LENGTH_SHORT).show();
		return startId;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "Service stopped!", Toast.LENGTH_SHORT).show();
	}
	
	private float findAverage(int[] numbers) {
		int sum = 0;
		for (int x : numbers) 
			sum += x;
		return sum / numbers.length;
	}
}
