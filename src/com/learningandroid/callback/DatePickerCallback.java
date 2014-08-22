package com.learningandroid.callback;

import android.widget.DatePicker;

public interface DatePickerCallback {
	void onSetDate(DatePicker view, int year, int monthOfYear,
			int dayOfMonth);
}
