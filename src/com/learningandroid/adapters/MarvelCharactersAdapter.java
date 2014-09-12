package com.learningandroid.adapters;

import java.util.List;

import com.learningandroid.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.learningandroid.callback.AsyncTaskPostExecute;
import com.learningandroid.model.MarvelCharacter;
import com.learningandroid.networking.ImageLoader;

public class MarvelCharactersAdapter extends ArrayAdapter<MarvelCharacter> {
	private Activity activity;
	private List<MarvelCharacter> characters;
	private int resource;
	
	public MarvelCharactersAdapter(Activity activity, int resource, List<MarvelCharacter> characters) {
		super(activity, resource, characters);
		
		this.activity = activity;
		this.characters = characters;
		this.resource = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, null);
			
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		MarvelCharacter character = characters.get(position);
		ImageView image = (ImageView) convertView.findViewById(R.id.marvel_character_image);
		TextView txtName = (TextView) convertView.findViewById(R.id.marvel_character_txt_name); 
		TextView txtDescription = (TextView) convertView.findViewById(R.id.marvel_character_txt_description);
		
		viewHolder.setImage(image);
		viewHolder.setTxtName(txtName);
		viewHolder.setTxtDescription(txtDescription);
		viewHolder.getTxtName().setText(character.getName());
		viewHolder.getTxtDescription().setText(character.getDescription());
		
		new ImageLoader(new BitmapCallback(viewHolder.getImage())).execute(character.getImageUrl());
		
		return convertView;
	}
	
	public class ViewHolder {
		private ImageView image;
		private TextView txtName;
		private TextView txtDescription;
		
		public ImageView getImage() {
			return image;
		}
		public void setImage(ImageView image) {
			this.image = image;
		}
		public TextView getTxtName() {
			return txtName;
		}
		public void setTxtName(TextView txtName) {
			this.txtName = txtName;
		}
		public TextView getTxtDescription() {
			return txtDescription;
		}
		public void setTxtDescription(TextView txtDescription) {
			this.txtDescription = txtDescription;
		}
	}
	
	public class BitmapCallback implements AsyncTaskPostExecute<Bitmap>{
		private ImageView image;
		
		public BitmapCallback(ImageView image) {
			this.image = image;
		}
		
		public void onPostExecute(Bitmap t) {
			image.setImageBitmap(t);
		}
	}
}
