package com.learningandroid.networking;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.learningandroid.callback.AsyncTaskPostExecute;

import android.os.AsyncTask;

public class HttpURLGet extends AsyncTask<String, Void, String> {
	private AsyncTaskPostExecute<String> callback;
	
	public HttpURLGet(AsyncTaskPostExecute<String> callback) {
		this.callback = callback;
	}

	protected String doInBackground(String... params) {
		String data = "";
		HttpURLConnection connection = null;
		
		try {
			connection = (HttpURLConnection) new URL(params[0]).openConnection();
			InputStream in = new BufferedInputStream(connection.getInputStream());
			data = read(in);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	private String read(InputStream in) {
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}

	protected void onPostExecute(String result) {
		callback.onPostExecute(result);
	}
}
