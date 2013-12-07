package com.example.hellolifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class LifecycleActivity extends Activity {

	private static final String logTag = "LifecycleActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event("create");
        setContentView(R.layout.activity_lifecycle);
    }

    @Override
    protected void onStart() {
    	super.onStart();
    	event("start");
    }

    @Override
    protected void onRestart() {
    	super.onRestart();
    	event("restart");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	event("resume");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	event("pause");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	event("stop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	event("destroy");
    }
    private void event(String state) {
    	Log.i(logTag, "on" + (Character.toUpperCase(state.charAt(0)) + state.substring(1)) + " called");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lifecycle, menu);
        return true;
    }
    
}
