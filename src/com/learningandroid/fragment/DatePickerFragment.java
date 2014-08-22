package com.learningandroid.fragment;

import java.util.Calendar;

import com.learningandroid.callback.DatePickerCallback;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment 
	implements DatePickerDialog.OnDateSetListener {

	private DatePickerCallback callback;
	
	public DatePickerFragment(DatePickerCallback callback) {
		this.callback = callback;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		callback.onSetDate(view, year, monthOfYear, dayOfMonth);
	}

}
