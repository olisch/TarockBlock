package app.adapter;

import org.blackboxx.tarockblock.persistance.Tariffset;

import android.content.Context;
import android.widget.ArrayAdapter;

public class TariffsetListAdapter extends ArrayAdapter<Tariffset> {

	public TariffsetListAdapter(Context context, int resource, int textViewResourceId, Tariffset[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	// @Override
	// public View getView(int position, View convertView, ViewGroup parent) {
	// // Get the data item for this position
	// Tariffset tariffset = getItem(position);
	// // Check if an existing view is being reused, otherwise inflate the view
	// if (convertView == null) {
	// convertView =
	// LayoutInflater.from(getContext()).inflate(R.layout.item_tariffset, null);
	// }
	// // Lookup view for data population
	// TextView tvName = (TextView)
	// convertView.findViewById(R.id.item_tariffset);
	// // Populate the data into the template view using the data object
	// tvName.setText(tariffset.getName());
	// // Return the completed view to render on screen
	// return convertView;
	// }
}
