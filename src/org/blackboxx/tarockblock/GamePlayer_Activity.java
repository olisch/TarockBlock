package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.TablePlayer;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class GamePlayer_Activity extends OrmLiteBaseActivity<DatabaseHelper> {

	private List<TablePlayer> players;
	private ListView playersList;
	private ArrayAdapter<TablePlayer> playersAdapter;
	private int activityId = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int defaultThemeId = 0;
		Globals g = Globals.getInstance();
		defaultThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		Helper.onActivitySetPrefTheme(this, defaultThemeId, activityId);

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
		playersAdapter = new ArrayAdapter<TablePlayer>(this, R.layout.item_player, R.id.item_player, players);
		playersList.setAdapter(playersAdapter);
		registerForContextMenu(playersList);

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		Helper.setActionBar(this);
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
