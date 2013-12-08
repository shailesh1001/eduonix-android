package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ColorCodeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_color_code);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			tv.setText(String.format("Color Code: %s", extras.getString("colorCode")));
			LinearLayout layout = (LinearLayout) findViewById(R.id.colorCodeContainer);
			layout.setBackgroundColor(extras.getInt("color"));
		}
			
		
		final Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Intent intentMain = new Intent(ColorCodeActivity.this, MainActivity.class);
				startActivity(intentMain);
				finish();
			}
		});		
	}
}
