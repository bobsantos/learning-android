package com.learningandroid.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.learningandroid.R;
import com.learningandroid.model.Contact;

public class ContactsAdapter extends ArrayAdapter<Contact> {
	private Activity activity;
	private List<Contact> contacts;
	private int resource;
	
	public ContactsAdapter(Activity activity, int resource, List<Contact> contacts) {
		super(activity, resource, contacts);
		
		this.activity = activity;
		this.contacts = contacts;
		this.resource = resource;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, null);
			
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		Contact contact = contacts.get(position);
		TextView txtName = (TextView) convertView.findViewById(R.id.contacts_list_name); 
		
		viewHolder.setTxtName(txtName);
		viewHolder.getTxtName().setText(contact.getName());
		viewHolder.getTxtName().setTag(contact.getId());
		
		return convertView;
	}
	
	public class ViewHolder {
		private TextView txtName;
		
		public TextView getTxtName() {
			return txtName;
		}
		public void setTxtName(TextView txtName) {
			this.txtName = txtName;
		}
	}
}
