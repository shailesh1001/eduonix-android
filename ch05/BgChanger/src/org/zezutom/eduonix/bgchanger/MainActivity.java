package org.zezutom.eduonix.bgchanger;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		IntentFilter filter = new IntentFilter(BgChanger.OK_RESPONSE);
		filter.addCategory(Intent.CATEGORY_DEFAULT);
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				changeColor(intent.getExtras().getInt("color"));
			}
		}, filter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void changeColor(View view) {
		final String secondStr = ((EditText) findViewById(R.id.secondsText)).getText().toString();
		final int seconds = Integer.parseInt(secondStr);
		
		final String colorCode = ((EditText) findViewById(R.id.colorCodeText)).getText().toString();
		
		if (colorCode == null || !colorCode.matches("^\\d{1,3},\\d{1,3},\\d{1,3}$")) {
			Toast.makeText(this, "Please provide a valid RGB code!", Toast.LENGTH_SHORT).show();
			return;
		}
					
		String codes[] = colorCode.split(",");
		int rgb[] = new int[codes.length];
		
		for (int i = 0; i < codes.length; i++) 
			rgb[i] = Integer.parseInt(codes[i]);
		
		
		Intent intent = new Intent(MainActivity.this, BgChanger.class);
		intent.putExtra("seconds", seconds);
		intent.putExtra("color", Color.rgb(rgb[0], rgb[1], rgb[2]));
		startService(intent);
	}
	
	public void resetDefault(View view) {
		changeColor(Color.TRANSPARENT);
	}
	
	public void changeColor(int color) {
		LinearLayout layout = (LinearLayout) findViewById(R.id.main_container);
		layout.setBackgroundColor(color);
	}

}
