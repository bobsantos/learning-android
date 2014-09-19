package com.learningandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.learningandroid.R;

public class RandomQuotesPreferences extends PreferenceFragment {
	private static final String RANDOM_QUOTES_TEXTSIZE_PREFERENCE_KEY = "random_quotes_textsize_preference";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager.setDefaultValues(getActivity(), RANDOM_QUOTES_TEXTSIZE_PREFERENCE_KEY, 
				Context.MODE_PRIVATE, R.xml.user_preference, false);
		
		addPreferencesFromResource(R.xml.user_preference);
	}
}
