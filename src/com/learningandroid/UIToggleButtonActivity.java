package com.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class UIToggleButtonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ui_toggle_button);
		
		final ToggleButton toggle = (ToggleButton) findViewById(R.id.ui_toggle_toggle);
		final Switch btnSwitch = (Switch) findViewById(R.id.ui_toggle_switch);
		
		toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String message = "";
				if(isChecked){
					message = "Don't forget to turn it OFF...";
				} else {
					message = "It's DARK in here...";
				}
				Toast.makeText(getApplicationContext(), 
						message, Toast.LENGTH_SHORT).show();
			}
		});
		
		btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton button, boolean isChecked) {
				String message = "";
				if(isChecked){
					message = "Looks shiny!";
				} else {
					message = "The floor needs some waxing!";
				}
				Toast.makeText(getApplicationContext(), 
						message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
