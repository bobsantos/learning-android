package com.learningandroid.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SingleStaticBroadcastReceiver extends BroadcastReceiver{
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Intent received from broadcast", Toast.LENGTH_SHORT).show();
	}
}
