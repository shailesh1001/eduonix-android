package com.example.sqlite1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final int REQUEST_UPDATE = 1;
	
	private CountryDB countryDB;
	
	private Cursor countries;
	
	private CursorAdapter adapter;
	
	private ListView lvCountries;
	
	private String[] columnNames;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create the db helper
		final CursorFactory cursorFactory = null;
		countryDB = new CountryDB(this, "countryDB", cursorFactory);
				
		lvCountries = (ListView) findViewById(R.id.lvCountries);
		
		int[] targetLayoutIDs = {R.id.tvName, R.id.tvPopulation, R.id.tvArea};
		columnNames = new String[] {"name", "population", "area", BaseColumns._ID};
		
		SQLiteDatabase db = countryDB.getReadableDatabase();
		countries =  db.query("countries", columnNames, null, null, null, null, null);
		adapter = 
				new SimpleCursorAdapter(
						this, R.layout.country_item, countries, 
						columnNames, targetLayoutIDs, 0);
		lvCountries.setAdapter(adapter);
		
		db.close();		
		lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				TextView tvName = (TextView) view.findViewById(R.id.tvName);
				TextView tvPopulation = (TextView) view.findViewById(R.id.tvPopulation);
				TextView tvArea = (TextView) view.findViewById(R.id.tvArea);
				
				Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
				String[] itemContents = {
						tvName.getText().toString(),
						tvPopulation.getText().toString(),
						tvArea.getText().toString()
				};
				updateIntent.putExtra("itemContents", itemContents);
				startActivityForResult(updateIntent, REQUEST_UPDATE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_UPDATE) {
			if (resultCode == RESULT_OK) {
				String[] results = data.getExtras().getStringArray("results");
				SQLiteDatabase db = countryDB.getWritableDatabase();
				
				ContentValues cv = new ContentValues();
				cv.put("population", results[1]);
				cv.put("area", results[2]);
				db.update("countries", cv, "Name = '" + results[0] + "';", null);
				countries = db.query("countries", columnNames, null, null, null, null, null);
				adapter.changeCursor(countries);	
				db.close();
			}
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		countryDB.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
