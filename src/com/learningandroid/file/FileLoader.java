package com.learningandroid.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.AsyncTask;

import com.learningandroid.callback.AsyncTaskPostExecute;

public class FileLoader extends AsyncTask<String, Void, String> {
	private AsyncTaskPostExecute<String> callback;
	private Context ctx;
	
	public FileLoader(AsyncTaskPostExecute<String> callback, Context ctx) {
		this.callback = callback;
		this.ctx = ctx;
	}

	protected String doInBackground(String... params) {
		String path = params[0];
		String content = "";
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		
		if(ctx.getFileStreamPath(path).exists()){
			try {
				reader = new BufferedReader(new InputStreamReader(ctx.openFileInput(path)));
				
				while((content = reader.readLine()) != null){
					sb.append(content);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
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
		}
		
		return sb.toString();
	}
	
	protected void onPostExecute(String result) {
		callback.onPostExecute(result);
	}
}
