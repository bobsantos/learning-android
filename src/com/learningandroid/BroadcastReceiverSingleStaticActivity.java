package com.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BroadcastReceiverSingleStaticActivity extends Activity {
	private static final String CUSTOM_INTENT_ACTION = "com.learningandroid.action.CUSTOM_INTENT_TOAST";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broadcast_receiver_single_static);
		
		Button btnBroadcast = (Button) findViewById(R.id.bc_single_static_btn_broadcast);
		btnBroadcast.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(CUSTOM_INTENT_ACTION);
				sendBroadcast(intent);
			}
		});
	}
}
