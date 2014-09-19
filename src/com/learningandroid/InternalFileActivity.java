package com.learningandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learningandroid.callback.AsyncTaskPostExecute;
import com.learningandroid.file.FileEditor;
import com.learningandroid.file.FileLoader;

public class InternalFileActivity extends Activity {

	private static final String INTERNAL_FILE_ACTIVITY_FILENAME = "internal-file-activity.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_internal_file);
		
		Button btnView = (Button) findViewById(R.id.internal_file_btn_view);
		final TextView txtView = (TextView) findViewById(R.id.internal_file_txt_view);
		
		btnView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if(!getFileStreamPath(INTERNAL_FILE_ACTIVITY_FILENAME).exists()){
					
					new FileEditor(getApplicationContext()).execute(INTERNAL_FILE_ACTIVITY_FILENAME, 
							"Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
							"Curabitur massa purus, faucibus ac varius ac, pellentesque tincidunt orci. " +
							"Aliquam ac aliquet lorem. In convallis nisi elit, " +
							"quis dignissim velit interdum ac. Sed id libero auctor, faucibus sem et, " +
							"feugiat nisl. Suspendisse a commodo tortor. Suspendisse potenti. " +
							"Suspendisse suscipit lacus nulla, vel gravida lacus varius sed. Mauris " +
							"ut elit ac mi posuere ultricies." + "\n" +
							"Sed nec nunc semper libero imperdiet porta sit amet ut turpis. " +
							"Aliquam iaculis augue a fermentum imperdiet. " +
							"Etiam venenatis eget augue a ultricies. " +
							"Cras gravida convallis lorem, ut ultrices est ultricies et. " +
							"Fusce id est ut tellus pulvinar vulputate. " +
							"Curabitur non purus et ante semper laoreet in sit amet massa. " +
							"Etiam a sollicitudin tellus. Curabitur venenatis dapibus consequat.");
				}
				
				new FileLoader(new AsyncTaskPostExecute<String>() {
					public void onPostExecute(String t) {
						txtView.setText(t);
					}
				}, getApplicationContext()).execute(INTERNAL_FILE_ACTIVITY_FILENAME);
			}
		});
	}
}
