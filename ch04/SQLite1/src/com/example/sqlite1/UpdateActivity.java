package com.example.sqlite1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UpdateActivity extends Activity {

	private TextView tvName;
	
	private TextView tvPopulation;
	
	private TextView tvArea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_view);
		
		tvName = (TextView) findViewById(R.id.nameEdit);
		tvPopulation = (TextView) findViewById(R.id.populationEdit);
		tvArea = (TextView) findViewById(R.id.areaEdit);
		
		Intent updateIntent = this.getIntent();
		final String[] defaultTexts = updateIntent.getExtras().getStringArray("itemContents");
		
		tvName.setText(defaultTexts[0]);
		tvPopulation.setText(defaultTexts[1]);
		tvArea.setText(defaultTexts[2]);
	}
	
	public void updateDB(View view) {
		Intent returnIntent = new Intent();
		String[] updateResults = {
				tvName.getText().toString(),
				tvPopulation.getText().toString(),
				tvArea.getText().toString()
		}; 
		returnIntent.putExtra("results", updateResults);
		setResult(RESULT_OK, returnIntent);
		finish();
	}
}
