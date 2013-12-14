package azzam.imad.demo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

// http://developer.android.com/training/basics/data-storage/shared-preferences.html
public class SharedPreferencesActivity extends Activity {

	private static final String SAVED_STRING = "saved_string";
	private static final String SAVED_BOOLEAN = "saved_boolean";
	private static final String SAVED_FLOAT = "saved_float";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences);
	}
	
	// http://developer.android.com/reference/android/content/SharedPreferences.html
	public void loadPreferences(View v) {
		Context context = this; // whichever activity you are in now
		String key = context.getString(R.string.preferences_key);
		SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
		
		String s = sp.getString(SAVED_STRING, "No saved string!");
		TextView tv = (TextView)findViewById(R.id.saved_textview);
		tv.setText(s);
		
		boolean on = sp.getBoolean(SAVED_BOOLEAN, false);
		ToggleButton tb = (ToggleButton)findViewById(R.id.saved_togglebutton);
		tb.setChecked(on);
		
		float f = sp.getFloat(SAVED_FLOAT, 2.5f);
		RatingBar rb = (RatingBar)findViewById(R.id.saved_ratingbar);
		rb.setRating(f);
	}
	
	// http://developer.android.com/reference/android/content/SharedPreferences.Editor.html
	public void savePreferences(View v) {
		Context context = this; // whichever activity you are in now
		String key = context.getString(R.string.preferences_key);
		SharedPreferences sp = context.getSharedPreferences(key, Context.MODE_PRIVATE);
		
		SharedPreferences.Editor editor = sp.edit();
		
		EditText et = (EditText)findViewById(R.id.edittext_to_save);
		editor.putString(SAVED_STRING, et.getText().toString());
		
		ToggleButton tb = (ToggleButton)findViewById(R.id.saved_togglebutton);
		editor.putBoolean(SAVED_BOOLEAN, tb.isChecked());
		
		RatingBar rb = (RatingBar)findViewById(R.id.saved_ratingbar);
		editor.putFloat(SAVED_FLOAT, rb.getRating());
		
		editor.apply(); // Don't forget this!!!
	}
}
