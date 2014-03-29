package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.Player;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class GamePlayer extends OrmLiteBaseActivity<DatabaseHelper> {

	private List<Player> players;
	private ListView playersList;
	private ArrayAdapter<Player> playersAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int user_theme=0;
		Globals g = Globals.getInstance();
		user_theme=g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this,user_theme);

		setContentView(R.layout.game_player);
		// Show the Up button in the action bar.
		setupActionBar();
		
		showPlayersList();
	}

	private void showPlayersList() {
		try {
			players = getHelper().getPlayerDao().queryForAll();
		} catch (SQLException e) {
			// TODO errorhandling
			e.printStackTrace();
		}

		playersList = (ListView) findViewById(R.id.list_gameplayers);
		playersAdapter = new ArrayAdapter<Player>(this,
				R.layout.list_item_player, R.id.list_players_item, players);
		playersList.setAdapter(playersAdapter);
		registerForContextMenu(playersList);

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
