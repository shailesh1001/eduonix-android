package com.example.sqlite1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CountryDB extends SQLiteOpenHelper {

	public static final int DB_VERSION = 1;
	
	public static final String COUNTRY_TABLE_NAME = "countries";

	public CountryDB(Context context, String name, CursorFactory factory) {
		super(context, name, factory, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase dbCountries) {
		createTable(dbCountries);
		
	}

	private void createTable(SQLiteDatabase db) {
		final String createSQL = "create table " + COUNTRY_TABLE_NAME +
									" (" +
									"_id integer primary key autoincrement" +
									", name text" +
									", population text" +
									", area text);";		
		db.execSQL(createSQL);
		
		final String insertSQL = "insert into " + COUNTRY_TABLE_NAME + "(name, population, area) " +
									"select 'Argentina' as name, '41,769,726' as population, '1,068,296' as area " +
									"union select 'Australia', '21,766,711', '2,967,893' " +
									"union select 'India', '1,189,172,906', '1,269,338' " +
									"union select 'United States', '313,232,044', '3,718,691';";		
		db.execSQL(insertSQL);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase dbCountries, int oldVersion, int newVersion) {
		final String dropSQL = "";
		dbCountries.execSQL(dropSQL);
		createTable(dbCountries);		
	}

}
