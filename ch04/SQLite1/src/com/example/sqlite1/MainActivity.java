package com.example.sqlite1;

import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.CursorAdapter;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

	private CountryDB countryDB;
	
	private Cursor countries;
	
	private ListView lvCountries;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create the db helper
		final CursorFactory cursorFactory = null;
		countryDB = new CountryDB(this, "countryDB", cursorFactory);
		
		String[] columnNames = {"name", "population", "area", BaseColumns._ID};
		lvCountries = (ListView) findViewById(R.id.lvCountries);
		
		int[] targetLayoutIDs = {R.id.tvName, R.id.tvPopulation, R.id.tvArea};
		
		SQLiteDatabase db = countryDB.getReadableDatabase();
		countries =  db.query("countries", columnNames, null, null, null, null, null);
		CursorAdapter adapter = 
				new SimpleCursorAdapter(
						this, R.layout.country_item, countries, 
						columnNames, targetLayoutIDs, 0);
		lvCountries.setAdapter(adapter);
		
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
