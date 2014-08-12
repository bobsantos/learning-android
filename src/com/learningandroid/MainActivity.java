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
    }
}
