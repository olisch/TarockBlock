package app.adapter;

import org.blackboxx.tarockblock.persistance.Tariff;

import android.content.Context;
import android.widget.ArrayAdapter;

public class TariffListAdapter extends ArrayAdapter<Tariff> {

	public TariffListAdapter(Context context, int resource, int textViewResourceId, Tariff[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

}
