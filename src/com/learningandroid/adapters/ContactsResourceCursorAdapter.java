package com.learningandroid.adapters;

import java.io.FileNotFoundException;
import java.io.InputStream;

import com.learningandroid.R;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class ContactsResourceCursorAdapter extends ResourceCursorAdapter {
	private final ContentResolver contentResolver;
	private final Context appCtx;
	
	private BitmapDrawable defaultPic; 
	private final int picSize;
	
	public ContactsResourceCursorAdapter(Context ctx, int layout, Cursor c, int flags) {
		super(ctx, layout, c, flags);
		
		contentResolver = ctx.getContentResolver();
		appCtx = ctx.getApplicationContext();
		
		defaultPic = (BitmapDrawable) ctx.getResources().getDrawable(R.drawable.ic_action_user);
		picSize = (int) ctx.getResources().getDimension(R.dimen.generic_text_height);
		defaultPic.setBounds(0, 0, picSize, picSize);
	}
	
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		return inflater.inflate(R.layout.textview_listview, parent, false);
	}
	
	public void bindView(View view, Context ctx, Cursor cursor) {
		TextView txtGeneric = (TextView) view.findViewById(R.id.generic_text);
		txtGeneric.setText(cursor.getString(cursor
				.getColumnIndex(Contacts.DISPLAY_NAME)));

		BitmapDrawable bitmap = defaultPic;

		String photoUri = cursor.getString(cursor
				.getColumnIndex(Contacts.PHOTO_THUMBNAIL_URI));

		if (photoUri != null) {
			InputStream is = null;
			try {
				is = contentResolver.openInputStream(Uri.parse(photoUri));
				if (is != null) {
					bitmap = new BitmapDrawable(appCtx.getResources(), is);
					bitmap.setBounds(0, 0, picSize, picSize);

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		txtGeneric.setCompoundDrawables(bitmap, null, null, null);
	}
}
