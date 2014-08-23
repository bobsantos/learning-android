package com.learningandroid;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class UserNotificationCustomNotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_notification_custom_notification);
		
		final RemoteViews vwNotification = new RemoteViews(getPackageName(), 
				R.layout.user_notificattion_custom_toast_image_text);
		
		Button btnNotify = (Button) findViewById(R.id.un_custom_notification_btn_notify);
		btnNotify.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(), 
						UNCustomNotificationPendingActivity.class);
				NotificationCompat.Builder builder = 
						new NotificationCompat.Builder(getApplicationContext())
					.setSmallIcon(R.drawable.calendar)
					.setTicker("A new event has been added")
					.setContentTitle("Event Added")
					.setContent(vwNotification)
					.setContentIntent(PendingIntent.getActivity(getApplicationContext(), 
							0, intent, Intent.FLAG_ACTIVITY_NEW_TASK));
				
				NotificationManager notificationManager = (NotificationManager) getSystemService(
						Context.NOTIFICATION_SERVICE);
				notificationManager.notify(1, builder.build());
			}
		});
	}
}
