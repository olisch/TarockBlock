package app.adapter;

import org.blackboxx.tarockblock.persistance.Tariffset;

import android.content.Context;
import android.widget.ArrayAdapter;

public class TariffsetListAdapter extends ArrayAdapter<Tariffset> {

	public TariffsetListAdapter(Context context, int resource,
			int textViewResourceId, Tariffset[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

}
