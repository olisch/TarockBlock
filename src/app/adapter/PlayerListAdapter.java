package app.adapter;

import org.blackboxx.tarockblock.persistance.Player;

import android.content.Context;
import android.widget.ArrayAdapter;

public class PlayerListAdapter extends ArrayAdapter<Player> {

	public PlayerListAdapter(Context context, int resource,
			int textViewResourceId, Player[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

}
