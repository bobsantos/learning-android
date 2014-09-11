package com.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learningandroid.callback.AsyncTaskPostExecute;
import com.learningandroid.networking.AndroidHttpClientGet;

public class AndroidHttpClientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_androidhttpclient);
		
		Button btnSend = (Button) findViewById(R.id.androidhttpclient_httpget_btn_send);
		final TextView txtResponse = (TextView) findViewById(R.id.androidhttpclient_httpget_text);
		
		btnSend.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String url = "http://www.iheartquotes.com/api/v1/random?source=humorix_misc";
				new AndroidHttpClientGet(new AsyncTaskPostExecute<String>() {
					public void onPostExecute(String t) {
						txtResponse.setText(t);
					}
				}).execute(url);
			}
		});
	}
}
