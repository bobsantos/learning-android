package com.learningandroid.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;

import com.learningandroid.callback.AsyncTaskPostExecute;

public class SocketHttpGet extends AsyncTask<URL, Void, String> {
	private AsyncTaskPostExecute<String> callback;
	public SocketHttpGet(AsyncTaskPostExecute<String> callback) {
		this.callback = callback;
	}
	
	protected String doInBackground(URL... urls) {
		String response = "";
		Socket socket = null;
		URL url = urls[0];
		String command = buildCommand(url);
		Log.i("COMMAND", command);
		
		try {
			socket = new Socket(url.getHost(), url.getPort() < 0 ? 80 : url.getPort());
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()), true);
			pw.println(command);
			
			response = readStream(socket.getInputStream()); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return response;
	}
	
	@Override
	protected void onPostExecute(String result) {
		callback.onPostExecute(result);
	}
	
	private String readStream(InputStream inputStream) {
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream));
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

	private String buildCommand(URL url){
		int port = url.getPort();
		String host = (port < 0) ? url.getHost() : url.getHost() + ":" + port;
		String file = url.getFile();
		
		return "GET " + file +
				" HTTP/1.1\n" + "Host: " + host + "\n" +
				"Connection: close\n\n";
	}
}