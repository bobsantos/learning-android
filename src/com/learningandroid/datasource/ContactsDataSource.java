package com.learningandroid.datasource;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.learningandroid.db.ContactDatabaseSQLHelper;
import com.learningandroid.model.Contact;

public class ContactsDataSource {
	private SQLiteDatabase db;
	private ContactDatabaseSQLHelper helper;
	private String[] columns = {ContactDatabaseSQLHelper.COLUMN_ID, ContactDatabaseSQLHelper.COLUMN_NAME};
	
	public ContactsDataSource(Context ctx){
		helper = new ContactDatabaseSQLHelper(ctx);
	}
	
	public void open() throws SQLException{
		db = helper.getWritableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public Contact create(Contact contact){
		ContentValues values =  new ContentValues();
		values.put(ContactDatabaseSQLHelper.COLUMN_NAME, contact.getName());
		
		long id = db.insert(ContactDatabaseSQLHelper.TABLE_CONTACT, null, values);
		contact.setId(id);
		
		return contact;
	}
	
	public boolean update(Contact contact){
		ContentValues values =  new ContentValues();
		values.put(ContactDatabaseSQLHelper.COLUMN_NAME, contact.getName());
		
		int rows = db.update(ContactDatabaseSQLHelper.TABLE_CONTACT, values, 
				ContactDatabaseSQLHelper.COLUMN_ID + " = ?", 
				new String[] {String.valueOf(contact.getId())});
		
		return rows > 0;
	}
	
	public boolean delete(long id){
		int rows = db.delete(ContactDatabaseSQLHelper.TABLE_CONTACT, ContactDatabaseSQLHelper.COLUMN_ID + " = " + id, null);
		return rows > 0;
	}
	
	public Contact fetch(long id){
		Contact contact = null;

		Cursor cursor = db.query(ContactDatabaseSQLHelper.TABLE_CONTACT, columns, 
				ContactDatabaseSQLHelper.COLUMN_ID + " = ?", new String[] {String.valueOf(id)}, null, null, null);
		
		if(cursor != null){
			cursor.moveToFirst();
			contact = toContact(cursor);
		}
		
		return contact;
	}
	
	public List<Contact> fetchAll(){
		Log.v("FETCH-ALL", "Fetching all contacts");
		List<Contact> contacts = new ArrayList<Contact>();
		
		Cursor cursor = db.query(ContactDatabaseSQLHelper.TABLE_CONTACT, columns, null, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			
			while(!cursor.isAfterLast()){
				Contact contact = new Contact();
				contact.setId(cursor.getLong(0));
				contact.setName(cursor.getString(1));
				
				contacts.add(contact);
				cursor.moveToNext();
			}
		}
		
		Log.v("FETCH-ALL", "Done fetching all contacts");
		return contacts;
	}
	
	private Contact toContact(Cursor cursor){
		Contact contact = new Contact();
		contact.setId(cursor.getLong(0));
		contact.setName(cursor.getString(1));
		
		return contact;
	}
}
