package com.learningandroid;

import com.learningandroid.datasource.ContactsDataSource;
import com.learningandroid.model.Contact;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends Activity {
	private ContactsDataSource ds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		
		ds = new ContactsDataSource(this);
		
		Fragment fragment = getFragmentManager().findFragmentById(R.id.contacts_add_contact_fragment);
		final EditText txtName = (EditText) fragment.getView().findViewById(R.id.contacts_add_form_txt_name);
		
		Button btnCancel = (Button) findViewById(R.id.contacts_add_contact_btn_cancel);
		btnCancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i = new Intent();
				setResult(RESULT_CANCELED, i);
				finish();
			}
		});
		
		Button btnSave = (Button) findViewById(R.id.contacts_add_contact_btn_save);
		btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String name = txtName.getText().toString(); 
				if(!name.isEmpty()){
					Contact contact = new Contact();
					contact.setName(name);
					
					new CreateContactTask().execute(contact);
				} else {
					Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	private class CreateContactTask extends AsyncTask<Contact, Void, Contact>{
		protected Contact doInBackground(Contact... params) {
			Contact contact = params[0];

			ds.open();
			ds.create(contact);
			ds.close();
			
			return contact;
		}
		
		protected void onPostExecute(Contact result) {
			Intent i = new Intent();
			i.putExtra("contact", result);
			setResult(RESULT_OK, i);
			finish();
		}
	}
}
