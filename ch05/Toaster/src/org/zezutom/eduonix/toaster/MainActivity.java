package org.zezutom.eduonix.toaster;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	// delay in seconds
	public static final long DELAY = 5;
	
	public static final int MAX_COUNT = 5;
	
	private boolean enabled = true;
	
	private int loopCount = 0;
	
	private Intent toasterIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IntentFilter filter = new IntentFilter(Toaster.OK_RESPONSE);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				if (isEnabled()) orderToast();
				else Log.i(this.getClass().getName(), "Time's up!");		
			}
		}, filter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private boolean isEnabled() {
		return enabled && loopCount < MAX_COUNT;
	}
	
	private void reset() {
		enabled = true;
		loopCount = 0;
	}
	
	public void startService(View view) {
		reset();
		orderToast();
	}
	
	private void orderToast() {		
		toasterIntent = new Intent(MainActivity.this, Toaster.class);
		toasterIntent.putExtra("seconds", DELAY);
		toasterIntent.setAction(Toaster.OK_RESPONSE);
		startService(toasterIntent);	
		loopCount++;
	}
		
	public void stopService(View view) {
		enabled = false;
		stopService(toasterIntent);
	} 

}
