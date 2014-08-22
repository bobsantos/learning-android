package com.learningandroid;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.learningandroid.callback.DatePickerCallback;
import com.learningandroid.fragment.DatePickerFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

public class UIPickerActivity extends Activity {
	private TextView txtDisplayDate;
	private ImageButton btnSetDate;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ui_picker);
		
		txtDisplayDate = (TextView) findViewById(R.id.ui_picker_txt_date_picker);
		btnSetDate = (ImageButton) findViewById(R.id.ui_picker_btn_date_picker);
		
		Calendar cal = Calendar.getInstance();
		txtDisplayDate.setText(sdf.format(cal.getTime()));
		
		btnSetDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				DatePickerFragment datePickerFragment = new DatePickerFragment(new Callback());
				datePickerFragment.show(getFragmentManager(), "datePicker");
			}
		});
	}
	
	private class Callback implements DatePickerCallback{
		public void onSetDate(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, monthOfYear);
			calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			txtDisplayDate.setText(sdf.format(calendar.getTime()));
		}
	}
}
