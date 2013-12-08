package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class DetailActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_detail);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			tv.setText(String.format("Color: %s", extras.getString("color")));
		
		final Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Intent intentMain = new Intent(DetailActivity.this, MainActivity.class);
				startActivity(intentMain);
				finish();
			}
		});
	}

}
