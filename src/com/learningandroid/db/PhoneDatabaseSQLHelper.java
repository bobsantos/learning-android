package com.learningandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PhoneDatabaseSQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "contacts.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_PHONE = "phones";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CONTACT_ID = "contact_id";
	public static final String COLUMN_NUMBER = "number";
	public static final String COLUMN_TYPE = "type";
	
	private static final String CREATE_TABLE = "create table "
			+ TABLE_PHONE + "(" 
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_NUMBER + " text not null, "
			+ COLUMN_TYPE + " text not null, "
			+ COLUMN_CONTACT_ID + " integer, "
			+ "foreign key (" + COLUMN_CONTACT_ID + ") references " 
			+ ContactDatabaseSQLHelper.TABLE_CONTACT + " (" + ContactDatabaseSQLHelper.COLUMN_ID + "));";
	
	public PhoneDatabaseSQLHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(PhoneDatabaseSQLHelper.class.getName(), 
				"Upgrading database from version " + oldVersion + "to"
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONE);
		onCreate(db);
	}
}
