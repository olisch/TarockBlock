package app.adapter;

import org.blackboxx.tarockblock.R;
import org.blackboxx.tarockblock.persistance.TableTariffset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TariffsetNewBasedonListAdapter extends ArrayAdapter<TableTariffset> {

	public TariffsetNewBasedonListAdapter(Context context, int resource, int textViewResourceId, TableTariffset[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TableTariffset tariffset = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tariffset, null);
		}
		// Lookup view for data population
		TextView tvName = (TextView) convertView.findViewById(R.id.item_tariffset);
		// Populate the data into the template view using the data object
		tvName.setText(tariffset.getName());
		// Return the completed view to render on screen
		return convertView;
	}

}
