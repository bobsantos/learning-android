package com.learningandroid;

import com.learningandroid.adapters.ContactsResourceCursorAdapter;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;

public class ContactsContentProviderActivity extends ListActivity implements 
	LoaderCallbacks<Cursor>{
	private static final int LOADER_ID = 12345;
	private ContactsResourceCursorAdapter adapter;
	private static final String[] CONTACTS_ROW = new String[] {
		Contacts._ID, Contacts.DISPLAY_NAME, Contacts.PHOTO_THUMBNAIL_URI};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		adapter = new ContactsResourceCursorAdapter(this, 
				R.layout.textview_listview, null, 0);
		setListAdapter(adapter);
		
		getLoaderManager().initLoader(LOADER_ID, null, this);
	}

	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return new CursorLoader(this, Contacts.CONTENT_URI, CONTACTS_ROW, 
				null, null, Contacts._ID);
	}

	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
	}

	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);
	}
}
