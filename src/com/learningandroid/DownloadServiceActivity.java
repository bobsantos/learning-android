package com.learningandroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.learningandroid.service.DownloadService;

public class DownloadServiceActivity extends Activity {
	private TextView txtStatus;
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context ctx, Intent intent) {
			Bundle bundle = intent.getExtras();
			if(bundle != null){
				String path = bundle.getString(DownloadService.DOWNLOAD_SERVICE_FILEPATH_KEY);
				int resultCode = bundle.getInt(DownloadService.DOWNLOAD_SERVICE_RESULT_KEY);
				if(resultCode == RESULT_OK){
					Toast.makeText(DownloadServiceActivity.this, 
							"Download COMPLETE: " + path, Toast.LENGTH_LONG).show();
					txtStatus.setText("Download COMPLETE");
				} else {
					Toast.makeText(DownloadServiceActivity.this, 
							"Download FAILED", Toast.LENGTH_LONG).show();
				}
			}
		}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_service);
		
		final Activity me = this;
		
		txtStatus = (TextView) findViewById(R.id.download_service_txt_status);
		
		Button btnDownload = (Button) findViewById(R.id.download_service_btn_download);
		btnDownload.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(me, DownloadService.class);
				intent.putExtra(DownloadService.DOWNLOAD_SERVICE_FILENAME_KEY, "index.html");
				intent.putExtra(DownloadService.DOWNLOAD_SERVICE_URL_KEY, "http://developer.android.com/index.html");
				
				startService(intent);
				txtStatus.setText("Download STARTED");
			}
		});
	}
	
	  protected void onResume() {
	    super.onResume();
	    registerReceiver(receiver, new IntentFilter(DownloadService.DOWNLOAD_SERVICE_ACTION));
	  }

	  protected void onPause() {
	    super.onPause();
	    unregisterReceiver(receiver);
	  }
}
