package com.learningandroid.networking;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import com.learningandroid.callback.AsyncTaskPostExecute;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

public class AndroidHttpClientGet extends AsyncTask<String, Void, String> {
	private AsyncTaskPostExecute<String> callback;
	private AndroidHttpClient client = AndroidHttpClient.newInstance("");
	
	public AndroidHttpClientGet(AsyncTaskPostExecute<String> callback) {
		this.callback = callback;
	}

	protected String doInBackground(String... params) {
		HttpGet get = new HttpGet(params[0]);
		ResponseHandler<String> handler = new BasicResponseHandler();
		
		try {
			return client.execute(get, handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	protected void onPostExecute(String result) {
		callback.onPostExecute(result);
	}
}
