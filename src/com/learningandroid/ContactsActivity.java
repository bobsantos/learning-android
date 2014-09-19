package com.learningandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.learningandroid.adapters.ContactsAdapter;
import com.learningandroid.datasource.ContactsDataSource;
import com.learningandroid.model.Contact;

public class ContactsActivity extends Activity {
	private ContactsDataSource contactsDs;
	private ListFragment contactsListFragment;
	private ContactsAdapter contactsListAdapater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		final Activity me = this;
		
		FragmentManager fragmentManager = getFragmentManager();
		contactsListFragment = (ListFragment) fragmentManager.findFragmentById(R.id.contacts_contact_list_fragment);
		contactsListAdapater = new ContactsAdapter(this, R.layout.contact_list, new ArrayList<Contact>());
		contactsListFragment.setListAdapter(contactsListAdapater);
		
		contactsDs = new ContactsDataSource(this);
		new FetchAllContacts().execute();
		
		ImageButton btnAdd = (ImageButton) findViewById(R.id.contacts_btn_add);
		btnAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivityForResult(new Intent(me, AddContactActivity.class), 1);
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 1){
			if(resultCode == RESULT_OK){
				Contact contact = data.getExtras().getParcelable("contact");
				contactsListAdapater.add(contact);
				contactsListAdapater.notifyDataSetChanged();
			} else {
				Toast.makeText(this, "Add Contact Cancelled", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	private class FetchAllContacts extends AsyncTask<Void, Void, List<Contact>>{
		protected List<Contact> doInBackground(Void... arg0) {
			contactsDs.open();
			List<Contact> contacts = contactsDs.fetchAll();
			contactsDs.close();
			
			return contacts;
		}
		
		protected void onPostExecute(List<Contact> result) {
			contactsListAdapater.clear();
			contactsListAdapater.addAll(result);
			contactsListAdapater.notifyDataSetChanged();
			contactsListFragment.setListShown(true);
		}
	}
}
