package com.learningandroid.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.learningandroid.callback.AsyncTaskPostExecute;

public class ExternalFileLoader extends AsyncTask<String, Void, String> {
	private AsyncTaskPostExecute<String> callback;
	private Context ctx;
	
	public ExternalFileLoader(AsyncTaskPostExecute<String> callback, Context ctx) {
		this.callback = callback;
		this.ctx = ctx;
	}

	protected String doInBackground(String... params) {
		String fileName = params[0];
		String content = "";
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		
		if(isExternalStorageReadyForRead()){
			try {
				File docs = ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
				File file = new File(docs, fileName);
				
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				
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
	
	private boolean isExternalStorageReadyForRead(){
		boolean hasReadWriteAccess = Environment.MEDIA_MOUNTED.equalsIgnoreCase(
				Environment.getExternalStorageState());
		
		boolean hasReadAccess = Environment.MEDIA_MOUNTED_READ_ONLY.equalsIgnoreCase(
				Environment.getExternalStorageState());
		
		return  hasReadWriteAccess || hasReadAccess;
	}
}
