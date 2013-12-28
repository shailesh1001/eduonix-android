package org.zezutom.eduonix.bgchanger;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

public class BgChanger extends IntentService {
	
	public static final String OK_RESPONSE = "OK";
	
	public BgChanger() {
		super("BgChanger");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		final int seconds = intent.getExtras().getInt("seconds");
		SystemClock.sleep(seconds * 1000);
		Intent broadcastIntent = new Intent();
		broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		broadcastIntent.setAction(OK_RESPONSE);
		broadcastIntent.putExtra("color", intent.getExtras().getInt("color"));
		sendBroadcast(broadcastIntent);						
	}
	

}
