package com.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learningandroid.callback.AsyncTaskPostExecute;
import com.learningandroid.networking.HttpURLGet;

public class HttpURLConnectionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_httpurlconnection);
		
		Button btnSend = (Button) findViewById(R.id.httpurlconn_httpget_btn_send);
		final TextView txtResponse = (TextView) findViewById(R.id.httpurlconn_httpget_text);
		
		btnSend.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String url = "http://www.iheartquotes.com/api/v1/random?source=humorix_misc";
				new HttpURLGet(new AsyncTaskPostExecute<String>() {
					public void onPostExecute(String t) {
						txtResponse.setText(t);
					}
				}).execute(url);
			}
		});
	}
}
