package com.learningandroid.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

public class DownloadService extends IntentService {
	
	public static final String DOWNLOAD_SERVICE_ACTION = "com.learningandroid.action.DOWNLOAD";
	public static final String DOWNLOAD_SERVICE_FILEPATH_KEY = "filepath";
	public static final String DOWNLOAD_SERVICE_RESULT_KEY = "result";
	public static final String DOWNLOAD_SERVICE_URL_KEY = "url";
	public static final String DOWNLOAD_SERVICE_FILENAME_KEY = "filename";
	
	private int result = Activity.RESULT_CANCELED;

	public DownloadService() {
		super("DownloadService");
	}

	protected void onHandleIntent(Intent intent) {
		String urlPath = intent.getStringExtra(DOWNLOAD_SERVICE_URL_KEY);
		String filename = intent.getStringExtra(DOWNLOAD_SERVICE_FILENAME_KEY);

		File file = getApplicationContext().getFileStreamPath(filename);
		
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(urlPath);
			is = url.openConnection().getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			
			if(!file.exists()){
				file.createNewFile();
			}
			fos = getApplicationContext().openFileOutput(filename, MODE_PRIVATE);;
			
			int next = -1;
			while((next = reader.read()) != -1){
				fos.write(next);
			}
			
			result = Activity.RESULT_OK;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Intent resultIntent = new Intent(DOWNLOAD_SERVICE_ACTION);
		resultIntent.putExtra(DOWNLOAD_SERVICE_FILEPATH_KEY, file.getAbsolutePath());
		resultIntent.putExtra(DOWNLOAD_SERVICE_RESULT_KEY, result);
		sendBroadcast(resultIntent);
	}
}