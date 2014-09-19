package com.learningandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDatabaseSQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "contacts.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_CONTACT = "contacts";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	
	private static final String CREATE_TABLE = "create table "
			+ TABLE_CONTACT + "(" 
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_NAME + " text not null);";
	
	public ContactDatabaseSQLHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ContactDatabaseSQLHelper.class.getName(), 
				"Upgrading database from version " + oldVersion + "to"
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
		onCreate(db);
	}
}
