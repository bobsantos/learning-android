package com.learningandroid.fragment;

import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ContactListFragment extends ListFragment {

	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.v("ITEM CLICKED", "position: " + position + " row: " + id);
	}
}
