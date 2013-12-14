package azzam.imad.demo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	Activity _activity;
	
	public GridViewAdapter(Activity a) {
		_activity = a;
	}
	
	// This should return the total number of items in the grid view
	@Override
	public int getCount() {
		return 50;
	}

	// Not used
	@Override
	public Object getItem(int position) {
		return null;
	}

	// Not used
	@Override
	public long getItemId(int position) {
		return 0;
	}

	// This returns the view to be displayed at a given position in the grid view.
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// A layout inflater converts the XML view description to actual controls
		LayoutInflater inflater = _activity.getLayoutInflater();
		
		View oneItem = inflater.inflate(R.layout.item_gridview, parent, false);
		
		TextView tv = (TextView)oneItem.findViewById(R.id.item_textview);
		tv.setText("This is item " + position + ".");
		
		return oneItem;
	}

}
