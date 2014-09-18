package com.learningandroid.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.learningandroid.R;

public class RandomQuotesPreferences extends PreferenceFragment {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.user_preference);
	}
}
