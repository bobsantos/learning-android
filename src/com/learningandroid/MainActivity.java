package com.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnUIButton = (Button) findViewById(R.id.main_btn_ui_button);
        Button btnUIText = (Button) findViewById(R.id.main_btn_ui_text);
        Button btnUICheckbox = (Button) findViewById(R.id.main_btn_ui_checkbox);
        Button btnUIRadioButton = (Button) findViewById(R.id.main_btn_ui_radio_button);
        Button btnUIToggleButton = (Button) findViewById(R.id.main_btn_ui_toggle_button);
        Button btnUISpinner = (Button) findViewById(R.id.main_btn_ui_spinner);
        Button btnUIPicker = (Button) findViewById(R.id.main_btn_ui_picker);
        Button btnUNCustomToast = (Button) findViewById(R.id.main_btn_user_notification_custom_toast);
        
        btnUIButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UIButtonActivity.class));
			}
		});
        
        btnUIText.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UITextActivity.class));
			}
		});
        
        btnUICheckbox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UICheckboxActivity.class));
			}
		});
        
        btnUIRadioButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UIRadioButtonActivity.class));
			}
		});
        
        btnUIToggleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UIToggleButtonActivity.class));
			}
		});
        
        btnUISpinner.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UISpinnerActivity.class));
			}
		});
        
        btnUIPicker.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UIPickerActivity.class));
			}
		});
        
        btnUNCustomToast.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), UserNotificationCustomToastActivity.class));
			}
		});
    }
}
