package com.learningandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learningandroid.callback.AsyncTaskPostExecute;
import com.learningandroid.model.TextSize;
import com.learningandroid.networking.HttpURLGet;

public class HttpURLConnectionActivity extends Activity {

	private static final String RANDOM_QUOTES_TEXTSIZE_PREFERENCE_KEY = "random_quotes_textsize_preference";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_httpurlconnection);
		
		Button btnSend = (Button) findViewById(R.id.httpurlconn_httpget_btn_send);
		final TextView txtResponse = (TextView) findViewById(R.id.httpurlconn_httpget_text);
		String textSize = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext())
				.getString(RANDOM_QUOTES_TEXTSIZE_PREFERENCE_KEY, TextSize.MEDIUM.name());
		for(TextSize ts : TextSize.values()){
			if(ts.name().equalsIgnoreCase(textSize)){
				txtResponse.setTextAppearance(getApplicationContext(), ts.size());
				break;
			}
		}
		
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
		
		Button btnPreferences = (Button) findViewById(R.id.httpurlconn_httpget_btn_preferences);
		btnPreferences.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), RandomQuotesPreferencesActivity.class));
			}
		});
	}
}
