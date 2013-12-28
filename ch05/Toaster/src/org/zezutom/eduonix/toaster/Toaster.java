package org.zezutom.eduonix.toaster;

import java.text.DateFormat;
import java.util.Date;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class Toaster extends IntentService {

	public static final String OK_RESPONSE = "OK";

	private long startTime;
	
	public Toaster() {
		super("Toaster");
	}

	@Override
	protected void onHandleIntent(Intent intent) {		
		final long seconds = intent.getExtras().getLong("seconds");
		startTime = System.currentTimeMillis();
		SystemClock.sleep(seconds * 1000);
		Intent broadcastIntent = new Intent();
		broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		broadcastIntent.setAction(OK_RESPONSE);
		sendBroadcast(broadcastIntent);				
	}
	
	private String getTime() {
		return DateFormat.getTimeInstance().format(new Date());
	}
	
	private double getDuration() {
		return (System.currentTimeMillis() - startTime) / 1000d;
	}
		
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(Toaster.class.getName(), String.format("Let's toast at %s", getTime()));		
		Toast.makeText(this,
				String.format("Time for a toast after %.2f seconds!", getDuration()),
				Toast.LENGTH_SHORT).show();
		
	}
}
