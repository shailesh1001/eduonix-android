package com.example.service3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service {

	private final IBinder binder = new LocalBinder();
	
	class LocalBinder extends Binder {
		BoundService getService() {
			return BoundService.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	public int add(int ...values) {
		if (values == null) return 0;
		
		int sum = 0;		
		for (int x : values)
			sum += x;
		
		return sum;
	}
}
