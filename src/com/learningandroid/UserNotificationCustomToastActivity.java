package com.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class UserNotificationCustomToastActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_notification_custom_toast);
		
		Button btnToast = (Button) findViewById(R.id.user_notification_custom_toast_btn_toast);
		btnToast.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				View view = getLayoutInflater().inflate(
						R.layout.user_notificattion_custom_toast_image_text, 
						(ViewGroup)findViewById(R.id.user_notification_custom_toast_layout));
								
				Toast toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				toast.setView(view);
				toast.show();
			}
		});
	}
}
