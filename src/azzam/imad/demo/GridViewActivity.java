package azzam.imad.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		
		// Get the grid view
		GridView gv = (GridView) findViewById(R.id.the_gridview);
		
		// Set the adapter which will tell it how to populate each item
		gv.setAdapter(new GridViewAdapter(this));
		
		// Set the click listener to tell it what to do when an item is clicked
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				// View v is the item based on the item_gridview XML file.
				TextView tv = (TextView)(v.findViewById(R.id.item_textview));
				tv.setText("Clicked!");
			}
		});
	}
	
}
