package azzam.imad.demo;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ActionBarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_bar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_bar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
				
			case R.id.action_search:
				LinearLayout ll = (LinearLayout)findViewById(R.id.search_layout);
				if(ll.getVisibility() == View.INVISIBLE || ll.getVisibility() == View.GONE) {
					ll.setVisibility(View.VISIBLE);
				} else {
					ll.setVisibility(View.INVISIBLE);
				}
				return true;
				
			case R.id.action_settings:
				Intent intent = new Intent(this, SharedPreferencesActivity.class);
		    	startActivity(intent);
		    	return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showTheActionBar(View v) {
		getActionBar().show();
	}
	
	public void hideTheActionBar(View v) {
		getActionBar().hide();
	}
	
	public void performSearch(View v) {
		EditText et = (EditText)findViewById(R.id.search_edittext);
		String s = et.getText().toString();
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_WEB_SEARCH);
		intent.putExtra(SearchManager.QUERY, s);
		startActivity(intent);
	}
}
