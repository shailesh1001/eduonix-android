package com.example.listviewdemo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.detail_layout);
		
		TextView chosenCountry = (TextView) findViewById(R.id.chosenCountry);
		Intent intent = this.getIntent();
		String countryName = intent.getStringExtra("country");
		chosenCountry.setText(String.format("%s was chosen", countryName));		
	}
}
