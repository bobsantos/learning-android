package com.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UIRadioButtonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ui_radio_button);
		
		final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.ui_radio_button_radio_group);
		Button btnView = (Button) findViewById(R.id.ui_radio_button_btn_view);
		
		btnView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String message = "You did not select anything...";
				int checkedId = radioGroup.getCheckedRadioButtonId();
				if(checkedId >= 0){
					RadioButton radio = (RadioButton) radioGroup.findViewById(checkedId);
					message = String.valueOf(radio.getText());
				}
				
				Toast.makeText(getApplicationContext(), 
						message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}
