package com.learningandroid;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.learningandroid.adapters.MarvelCharactersAdapter;
import com.learningandroid.callback.AsyncTaskPostExecute;
import com.learningandroid.model.MarvelCharacter;
import com.learningandroid.networking.AndroidHttpClientGet;
import com.learningandroid.utils.RequestUtils;

public class AndroidHttpClientActivity extends Activity {
	private MarvelCharactersAdapter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_androidhttpclient);
		
		Button btnSend = (Button) findViewById(R.id.androidhttpclient_httpget_btn_send);
		final ListView list = (ListView) findViewById(R.id.androidhttpclient_httpget_list);
		adapter = new MarvelCharactersAdapter(this, R.layout.layout_marvel_character, 
				new ArrayList<MarvelCharacter>());
		list.setAdapter(adapter);
		
		btnSend.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				String url = "http://gateway.marvel.com/v1/public/characters?" + RequestUtils.getMarvelRequiredQueryParams() + "&limit=5&orderBy=name";

				new AndroidHttpClientGet(new AsyncTaskPostExecute<String>() {
					public void onPostExecute(String t) {
						try {
							adapter.addAll(getCharacters(new JSONObject(t)));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}).execute(url);
			}
		});
	}
	
	private List<MarvelCharacter> getCharacters(JSONObject json) throws JSONException{
		List<MarvelCharacter> marvelCharacters = new ArrayList<MarvelCharacter>();
		
		JSONObject data = json.getJSONObject("data");
		JSONArray characters = data.getJSONArray("results");
		for(int i = 0; i < characters.length(); i++){
			JSONObject character = characters.getJSONObject(i);
			String name = character.getString("name");
			String description = character.getString("description");
			JSONObject imgUrl = character.getJSONObject("thumbnail");
			String imageUrl = imgUrl.getString("path") + 
					"/portrait_medium." + imgUrl.getString("extension"); 
			MarvelCharacter mc = new MarvelCharacter();
			mc.setName(name);
			mc.setDescription((description.isEmpty()) ? "No Description" : description);
			mc.setImageUrl(imageUrl);
			marvelCharacters.add(mc);
		}
		
		return marvelCharacters;
	}
}
