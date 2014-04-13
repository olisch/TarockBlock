package app.adapter;

import org.blackboxx.tarockblock.persistance.TablePlayer;

import android.content.Context;
import android.widget.ArrayAdapter;

public class PlayerListAdapter extends ArrayAdapter<TablePlayer> {

	public PlayerListAdapter(Context context, int resource, int textViewResourceId, TablePlayer[] objects) {
		super(context, resource, textViewResourceId, objects);
	}
	// TODO background color for every second row

}
