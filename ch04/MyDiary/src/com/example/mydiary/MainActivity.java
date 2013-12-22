package com.example.mydiary;

import android.os.Bundle;
import android.provider.BaseColumns;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int REQUEST_INSERT = 1;
	
	private DiaryDB diaryDB;
	
	private ListView lvEntries;
	
	private String[] columnNames;	
	
	private Cursor entries;
	
	private CursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create the db helper
		final CursorFactory cursorFactory = null;
		diaryDB = new DiaryDB(this, "diaryDB", cursorFactory);
				
		lvEntries = (ListView) findViewById(R.id.lvEntries);
		
		int[] targetLayoutIDs = {R.id.tvEntry, R.id.tvReadableDate, R.id.tvEntryId};
		columnNames = new String[] {"subject", "dateHuman", BaseColumns._ID};
		
		SQLiteDatabase db = diaryDB.getReadableDatabase();

		entries =  db.query("diary", columnNames, null, null, null, null, "dateMsecs desc");
		adapter = 
				new SimpleCursorAdapter(
						this, R.layout.diary_item, entries, 
						columnNames, targetLayoutIDs, 0);
		lvEntries.setAdapter(adapter);
		
		db.close();	
		
		final Context context = this;
		lvEntries.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				final TextView tvEntryId = (TextView) view.findViewById(R.id.tvEntryId);
				AlertDialog.Builder adb = new AlertDialog.Builder(context);
				adb.setTitle("Delete Entry?");
				adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						final int entryId = Integer.parseInt(tvEntryId.getText().toString());
						SQLiteDatabase db = diaryDB.getWritableDatabase();
						
						final int rowCount = db.delete("diary", "_id = " + entryId, null);
						entries = db.query("diary", columnNames, null, null, null, null, null);
						adapter.changeCursor(entries);	
						db.close();
						
						if (rowCount == 0) {
							// we should never end up here, since the entry id is NOT provided by the end user
							Toast.makeText(context, "No such entry was found: " + entryId, Toast.LENGTH_SHORT).show();
						}						
												
					}
				});
				adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing					
					}
				});	
				adb.show();
			}
			
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_INSERT) {
			if (resultCode == RESULT_OK) {
				String[] results = data.getExtras().getStringArray("results");
				SQLiteDatabase db = diaryDB.getWritableDatabase();
				
				final long now = DateUtils.nowMillis();
				ContentValues cv = new ContentValues();
				cv.put("subject", results[0]);
				cv.put("dateMsecs", now);
				cv.put("dateHuman", DateUtils.millis2String(now));				
				db.insert("diary", null, cv);
				entries = db.query("diary", columnNames, null, null, null, null, "dateMsecs desc");
				adapter.changeCursor(entries);	
				db.close();
			}			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result = true;
		switch(item.getItemId()) {
			case R.id.itemInsert:
				Intent insertIntent = new Intent(MainActivity.this, InsertActivity.class);
				startActivityForResult(insertIntent, REQUEST_INSERT);
				break;
			default:
				result = super.onOptionsItemSelected(item);
		}
		return result;
	}	

}
