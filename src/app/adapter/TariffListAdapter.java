package app.adapter;

import org.blackboxx.tarockblock.R;
import org.blackboxx.tarockblock.persistance.TableTariff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TariffListAdapter extends ArrayAdapter<TableTariff> {

	public TariffListAdapter(Context context, int resource, int textViewResourceId, TableTariff[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TableTariff tariff = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tariff, null);
		}
		TextView name = (TextView) convertView.findViewById(R.id.tariffset_tariff);
		name.setText(tariff.getName());
		TextView value = (TextView) convertView.findViewById(R.id.tariffset_value);
		value.setText(tariff.getValue().toString());
		// TODO background color for every second row

		return convertView;
	}

}
