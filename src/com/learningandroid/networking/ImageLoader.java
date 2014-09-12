package com.learningandroid.networking;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.learningandroid.callback.AsyncTaskPostExecute;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
	private final AndroidHttpClient client = AndroidHttpClient.newInstance("");
	private AsyncTaskPostExecute<Bitmap> callback;
	
	public ImageLoader(AsyncTaskPostExecute<Bitmap> callback) {
		this.callback = callback;
	}
	
	protected Bitmap doInBackground(String... params) {
		final String url = params[0];
		Log.i("IMAGE URL", url);
		HttpGet httpGet = new HttpGet(url);
		
		try {
			HttpResponse response = client.execute(httpGet);
			final HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream in = null;
				in = entity.getContent();
				Log.i("IMAGE CONTENT", String.valueOf(entity.getContentLength()));
				return BitmapFactory.decodeStream(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(client != null){
				client.close();
			}
		}
		
		return null;
	}

	protected void onPostExecute(Bitmap result) {
		callback.onPostExecute(result);
	}
}
