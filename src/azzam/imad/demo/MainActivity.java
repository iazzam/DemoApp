package azzam.imad.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import azzam.imad.demo.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainActivity extends Activity {
	
	// Full life cycle of activity discussed here:
	// http://developer.android.com/training/basics/activity-lifecycle/starting.html
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	}
	
	public void showBasicControls(View v) {
    	Intent intent = new Intent(this, BasicControlsActivity.class);
    	startActivity(intent);
	}
}
