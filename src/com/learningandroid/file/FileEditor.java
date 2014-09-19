package com.learningandroid.file;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.content.Context;
import android.os.AsyncTask;

public class FileEditor extends AsyncTask<String, Void, Void> {
	private Context ctx;
	
	public FileEditor(Context ctx) {
		this.ctx = ctx;
	}

	protected Void doInBackground(String... params) {
		String path = params[0];
		String text = params[1];
		
		PrintWriter pw = null;
		
		if(!ctx.getFileStreamPath(path).exists()){
			try {
				pw = new PrintWriter(new OutputStreamWriter(ctx.openFileOutput(path, Context.MODE_PRIVATE)));
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
}
