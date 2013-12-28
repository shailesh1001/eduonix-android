package org.zezutom.equinox.boundtranslator;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.SparseArray;

public class DigitTranslator extends Service {
	
	private IBinder binder = new LocalBinder();
	
	private SparseArray<String> translations;
	
	class LocalBinder extends Binder {
		DigitTranslator getService() {
			return DigitTranslator.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		int[] keys = getResources().getIntArray(R.array.digit_array);
		String[] values = getResources().getStringArray(R.array.digit_text_array);
		
		if (keys.length != values.length)
			throw new IllegalStateException("The digits and their translations do not match!");
		
		translations = new SparseArray<String>(keys.length);
		for (int i = 0; i < keys.length; i++)
			translations.put(keys[i], values[i]);
	}
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	public String translate(int digit) {
		if (digit < 0 || digit >= translations.size())
			return String.format(getResources().getString(R.string.no_translation_found), digit);
		else
			return translations.get(digit);
	}

}
