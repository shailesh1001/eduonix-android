package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDB extends SQLiteOpenHelper {

	public static final int DB_VERSION = 1;
	
	public static final String DIARY_TABLE_NAME = "diary";

	public DiaryDB(Context context, String name, CursorFactory factory) {
		super(context, name, factory, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase dbDiary) {
		createTable(dbDiary);
		
	}

	private void createTable(SQLiteDatabase db) {
		final String createSQL = "create table " + DIARY_TABLE_NAME +
									" (" +
									"_id integer primary key autoincrement" +
									", dateMsecs integer not null" +
									", dateHuman text not null" +
									", subject text not null);";		
		db.execSQL(createSQL);
		
		final long now = DateUtils.nowMillis();
		final String insertSQL = "insert into " + DIARY_TABLE_NAME + "(subject, dateMsecs, dateHuman) " +
									"select 'Cool stuff' as subject, " + now + " as dateMsecs, '" + DateUtils.millis2String(now) + "' as dateHuman;";		
		db.execSQL(insertSQL);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase dbDiary, int oldVersion, int newVersion) {
		final String dropSQL = "";
		dbDiary.execSQL(dropSQL);
		createTable(dbDiary);		
	}

}
