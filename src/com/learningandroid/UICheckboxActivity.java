package com.learningandroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.learningandroid.model.ProgrammingLanguage;

public class UICheckboxActivity extends Activity {
	// better use a fragment for the listView to take advantage of retained fragment
	// to save state of checkbox
	private ListView listView; 
	private List<ProgrammingLanguage> languages;
	private LanguageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ui_checkbox);
		
		languages = new ArrayList<ProgrammingLanguage>();
		languages.add(new ProgrammingLanguage(1L, "Java", "Java Description"));
		languages.add(new ProgrammingLanguage(2L, "Python", "Python Description"));
		languages.add(new ProgrammingLanguage(3L, "Scala", "Scala Description"));
		languages.add(new ProgrammingLanguage(4L, "JavaScript", "JavaScript Description"));
		languages.add(new ProgrammingLanguage(3L, "C#", "C# Description"));
		
		listView = (ListView) findViewById(R.id.ui_checkbox_list_view);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				ProgrammingLanguage language = adapter.getItem(position);
				language.toggleChecked();
				LanguageViewHolder holder = (LanguageViewHolder) view.getTag();
				holder.getCheckBox().setChecked(language.isChecked());
			}
		});
		
		adapter = new LanguageAdapter(this, languages);
		listView.setAdapter(adapter);
		
		Button btnView = (Button) findViewById(R.id.ui_checkbox_btn_view);
		btnView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String message = "You selected: ";
				for(int i = 0; i < languages.size(); i++){
					ProgrammingLanguage programmingLanguage = languages.get(i);
					if(programmingLanguage.isChecked()){
						if(i > 0){
							message += ", ";
						}
						message += programmingLanguage.getName();
					}
				}
				
				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/**
	 * Holds one row of checkbox and textview for reuse
	 */
	private class LanguageViewHolder {
		private CheckBox checkBox;
		private TextView textView;
		
		public LanguageViewHolder() {}
		public LanguageViewHolder(CheckBox checkBox, TextView textView) {
			this.checkBox = checkBox;
			this.textView = textView;
		}

		public CheckBox getCheckBox() {
			return checkBox;
		}
		public void setCheckBox(CheckBox checkBox) {
			this.checkBox = checkBox;
		}
		public TextView getTextView() {
			return textView;
		}
		public void setTextView(TextView textView) {
			this.textView = textView;
		}
	}
	
	private class LanguageAdapter extends ArrayAdapter<ProgrammingLanguage>{

		private LayoutInflater inflater;
		
		public LanguageAdapter(Context context, List<ProgrammingLanguage> languages) {
			super(context, R.layout.acitvity_ui_checkbox_row, 
					R.id.ui_checkbox_row_text_view, languages);
			inflater = LayoutInflater.from(context);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// language to display
			ProgrammingLanguage language = this.getItem(position); 
			
			// views in row of converterView
			CheckBox checkBox = null;
			TextView textView = null;
			
			// create the row if converterView is null
			if(convertView == null){
				convertView = inflater.inflate(R.layout.acitvity_ui_checkbox_row, null);
				
				checkBox = (CheckBox) convertView.findViewById(R.id.ui_checkbox_row_checkbox);
				textView = (TextView) convertView.findViewById(R.id.ui_checkbox_row_text_view);
				
				convertView.setTag(new LanguageViewHolder(checkBox, textView));
				
				checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						ProgrammingLanguage language = (ProgrammingLanguage) buttonView.getTag();
						language.setChecked(isChecked);
					}
				});
			} else { // if converterView is reused
				LanguageViewHolder holder = (LanguageViewHolder) convertView.getTag();
				checkBox = holder.getCheckBox();
				textView = holder.getTextView();
			}
			
			checkBox.setTag(language);
			checkBox.setChecked(language.isChecked());
			textView.setText(language.getName());
			
			return convertView;
		}
	}
}