package com.learningandroid.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RequestUtils {
	// get your own keys at developer.marvel.com
	public static final String getMarvelRequiredQueryParams(){
		final String timestamp = new SimpleDateFormat("mm-dd-yyyy", Locale.US).format(Calendar.getInstance().getTime());
		final String pubKey = "your_public_key_here";
		final String prvKey = "your_private_key_here";
		final String hash = EncryptionUtils.md5(timestamp + prvKey + pubKey);
		
		return  "ts=" + timestamp +"&apikey=" + pubKey + "&hash=" + hash;
	} 
}
