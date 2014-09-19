package com.learningandroid.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

public class ExternalFileEditor extends AsyncTask<String, Void, Void> {
	private Context ctx;
	
	public ExternalFileEditor(Context ctx) {
		this.ctx = ctx;
	}

	protected Void doInBackground(String... params) {
		String fileName = params[0];
		String text = params[1];
		
		PrintWriter pw = null;
		
		if(isExternalStorageReadyForWrite()){
			try {
				File docs = ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
				File file = new File(docs, fileName);
				pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
				pw.append(text);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(pw != null){
					pw.close();
				}
			}
		}
		
		return null;
	}
	
	private boolean isExternalStorageReadyForWrite(){
		boolean hasReadWriteAccess = Environment.MEDIA_MOUNTED.equalsIgnoreCase(
				Environment.getExternalStorageState());
		
		return  hasReadWriteAccess;
	}
}
