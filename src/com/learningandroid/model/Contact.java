package com.learningandroid.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
	private Long id;
	private String name;
	private List<Phone> phoneNumbers;
	private List<Email> emailAddresses;
	
	public Contact() {}
	public Contact(Parcel parcel) {
		this.name = parcel.readString();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Phone> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<Phone> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public List<Email> getEmailAddresses() {
		return emailAddresses;
	}
	public void setEmailAddresses(List<Email> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
	}
	
	public static final Contact.Creator<Contact> CREATOR = new Contact.Creator<Contact>(){
		public Contact createFromParcel(Parcel source) {
			return new Contact(source);
		}

		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};
}
