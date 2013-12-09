package azzam.imad.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BasicControlsActivity extends Activity implements OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_controls);
		
		Spinner s = (Spinner)findViewById(R.id.the_spinner);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		s.setAdapter(adapter);
		
		s.setOnItemSelectedListener(this);
		
		s.setSelection(3); // The 4th choice is selected to start with
	}
	
	public void togglePressed(View v) {
    	ToggleButton t = (ToggleButton)v;
    	TextView tv = (TextView)findViewById(R.id.label_toggle);
    	
    	if(t.isChecked()) {
    		tv.setText("Toggle is now on :)");
    	} else {
    		tv.setText("Toggle is now off :(");
    	}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		String theString = (String)parent.getItemAtPosition(position);
		
		TextView tv = (TextView)findViewById(R.id.label_spinner);
		tv.setText(theString + " is my favorite Simpsons character.");
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// Nothing to do here
	}
}

