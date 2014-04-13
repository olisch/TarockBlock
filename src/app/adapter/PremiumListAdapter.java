package app.adapter;

import org.blackboxx.tarockblock.R;
import org.blackboxx.tarockblock.persistance.TablePremium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PremiumListAdapter extends ArrayAdapter<TablePremium> {

	public PremiumListAdapter(Context context, int resource, int textViewResourceId, TablePremium[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TablePremium premium = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_premium, null);
		}
		TextView name = (TextView) convertView.findViewById(R.id.tariffset_premium);
		name.setText(premium.getName());
		TextView value_silent = (TextView) convertView.findViewById(R.id.tariffset_premium_value_silent);
		value_silent.setText(premium.getValueSilent().toString());
		TextView value_called = (TextView) convertView.findViewById(R.id.tariffset_premium_value_called);
		value_called.setText(premium.getValueCalled().toString());
		// TODO background color for every second row

		return convertView;
	}

}
