package app.adapter;

import org.blackboxx.tarockblock.R;
import org.blackboxx.tarockblock.persistance.TableTariffset;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TariffsetListAdapter extends ArrayAdapter<TableTariffset> {

	private final int defaultTariffsetId;

	public TariffsetListAdapter(Context context, int resource, int textViewResourceId, TableTariffset[] objects, int defaultTariffsetId) {
		super(context, resource, textViewResourceId, objects);
		this.defaultTariffsetId = defaultTariffsetId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		TableTariffset tariffset = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tariffset, null);
		}
		// Lookup view for data population
		TextView tvName = (TextView) convertView.findViewById(R.id.item_tariffset);
		// Populate the data into the template view using the data object
		tvName.setText(tariffset.getName());
		if (defaultTariffsetId == tariffset.getId()) {
			tvName.setTypeface(null, Typeface.BOLD);
		} else {
			tvName.setTypeface(null, Typeface.NORMAL);
		}
		// Return the completed view to render on screen
		// TODO background color for every second row
		return convertView;
	}
}
