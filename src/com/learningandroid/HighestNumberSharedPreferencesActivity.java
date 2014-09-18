package com.learningandroid;

import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HighestNumberSharedPreferencesActivity extends Activity {
	private static final String HIGHEST_NUMBER_LABEL = "Highest Number: %d";
	private static final String HIGHEST_NUMBER_KEY = "HIGHEST_NUMBER";
	private static final String SHARED_PREFERENCES_NAME = "com.learningandroid";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highest_number_shared_preferences);
		
		final SharedPreferences pref = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
		
		Button btnGenerate = (Button) findViewById(R.id.highest_number_btn_generate);
		final TextView txtGeneratedNumber = (TextView) findViewById(R.id.highest_number_txt_number);
		final TextView txtHighestNumber = (TextView) findViewById(R.id.highest_number_txt_highest);
		
		txtHighestNumber.setText(String.format(HIGHEST_NUMBER_LABEL, pref.getInt(HIGHEST_NUMBER_KEY, 0)));
		
		btnGenerate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				int highest = pref.getInt(HIGHEST_NUMBER_KEY, 0);
				int generated = new Random().nextInt(5000);
				txtGeneratedNumber.setText(String.valueOf(generated));
				
				if(generated > highest){
					txtHighestNumber.setText(String.format(HIGHEST_NUMBER_LABEL, generated));
					pref.edit().putInt(HIGHEST_NUMBER_KEY, generated).apply();
				}
			}
		});
		
		Button btnReset = (Button) findViewById(R.id.highest_number_btn_reset);
		btnReset.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				pref.edit().remove(HIGHEST_NUMBER_KEY).apply();
				txtHighestNumber.setText(String.format(HIGHEST_NUMBER_LABEL, pref.getInt(HIGHEST_NUMBER_KEY, 0)));
				Toast.makeText(getApplicationContext(), 
						"Highest Number Reset", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
