package com.learningandroid;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learningandroid.callback.AsyncTaskPostExecute;
import com.learningandroid.networking.SocketHttpGet;

public class SocketHttpGetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_socket_http_get);
		
		Button btnSend = (Button) findViewById(R.id.socket_httpget_btn_send);
		final TextView txtResponse = (TextView) findViewById(R.id.socket_httpget_text);
		
		btnSend.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				try {
					URL url = new URL("http", "www.iheartquotes.com", "/api/v1/random?source=humorix_misc");
					new SocketHttpGet(new AsyncTaskPostExecute<String>() {
						public void onPostExecute(String t) {
							txtResponse.setText(t);
						}
					}).execute(url);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
