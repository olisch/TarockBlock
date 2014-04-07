package app.adapter;

import org.blackboxx.tarockblock.persistance.TableTariff;

import android.content.Context;
import android.widget.ArrayAdapter;

public class TariffListAdapter extends ArrayAdapter<TableTariff> {

	public TariffListAdapter(Context context, int resource, int textViewResourceId, TableTariff[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

}
