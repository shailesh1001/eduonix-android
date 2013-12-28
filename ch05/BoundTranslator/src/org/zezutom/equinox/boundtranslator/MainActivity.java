package org.zezutom.equinox.boundtranslator;

import org.zezutom.equinox.boundtranslator.DigitTranslator.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private DigitTranslator translator;
	
	private boolean bound = false;
	
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			bound = false;			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			LocalBinder localBinder = (LocalBinder) binder;
			translator = localBinder.getService();
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
		Intent intent = new Intent(MainActivity.this, DigitTranslator.class);
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
	
	public void translate(View view) {
		EditText firstDigit = (EditText) findViewById(R.id.firstDigit);
		EditText secondDigit = (EditText) findViewById(R.id.secondDigit);
		EditText thirdDigit = (EditText) findViewById(R.id.thirdDigit);
		
		firstDigit.setText(translator.translate(getDigit(firstDigit)));
		secondDigit.setText(translator.translate(getDigit(secondDigit)));
		thirdDigit.setText(translator.translate(getDigit(thirdDigit)));				
	}
	
	private int getDigit(EditText field) {
		String value = field.getText().toString();		
		return (value == null || !value.matches("\\d+")) ? -1 : Integer.parseInt(value);  
	}

}
