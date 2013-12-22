package com.example.sqlite1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InsertActivity extends Activity {

	private TextView tvName;
	
	private TextView tvPopulation;
	
	private TextView tvArea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_view);

		tvName = (TextView) findViewById(R.id.nameEdit);
		tvPopulation = (TextView) findViewById(R.id.populationEdit);
		tvArea = (TextView) findViewById(R.id.areaEdit);		
	}
	
	private boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
	public void insertDB(View view) {
		final String name = tvName.getText().toString();
		final String population = tvPopulation.getText().toString();
		final String area = tvArea.getText().toString();
		
		if (isEmpty(name) || isEmpty(population) || isEmpty(area)) {
			Toast.makeText(this, "Please provide all values!", Toast.LENGTH_SHORT).show();
		}
		else {
			Intent returnIntent = new Intent();
			String[] insertResults = {name, population, area}; 
			returnIntent.putExtra("results", insertResults);
			setResult(RESULT_OK, returnIntent);
			finish();					
		}		
	}
}
