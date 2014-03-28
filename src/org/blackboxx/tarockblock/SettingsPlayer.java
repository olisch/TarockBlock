package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.Player;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class SettingsPlayer extends OrmLiteBaseActivity<DatabaseHelper>
		implements OnClickListener {

	private Button SettingsPlayerNew;
	private EditText editTextMainScreen;
	private List<Player> players;
	private ListView playersList;
	private ArrayAdapter<Player> playersAdapter;

	private Player editPlayer;
	private Player deletePlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int user_theme = 0;
		Globals g = Globals.getInstance();
		user_theme = g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, user_theme);

		setContentView(R.layout.settings_player);
		SettingsPlayerNew = (Button) findViewById(R.id.settings_button_player_new);
		SettingsPlayerNew.setOnClickListener(this);
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

		playersList = (ListView) findViewById(R.id.list_players);
		playersAdapter = new ArrayAdapter<Player>(this,
				R.layout.list_item_player, R.id.list_players_item, players);
		playersList.setAdapter(playersAdapter);
		registerForContextMenu(playersList);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(Menu.NONE, 1, 1, "Edit");
		menu.add(Menu.NONE, 2, 2, "Delete");
		super.onCreateContextMenu(menu, v, menuInfo);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case 1:
			editPlayer(info.id);
			return true;
		case 2:
			deletePlayer(info.id);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void deletePlayer(long id) {
		deletePlayer = playersAdapter.getItem((int) id);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Sind sie sicher?")
				.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						try {
							getHelper().getPlayerDao().delete(deletePlayer);

						} catch (SQLException e) {
							// TODO errorhandling
							e.printStackTrace();
						}
						showPlayersList();
					}
				})
				.setNegativeButton("Nein",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});
		builder.show();
	}

	private void editPlayer(long id) {
		editPlayer = playersAdapter.getItem((int) id);
		openDialogPlayerName();
	}

	@Override
	public void onClick(View v) {

		// Context context = getApplicationContext();
		// int duration = Toast.LENGTH_LONG;
		// CharSequence text = String.valueOf(v.getId());
		// Toast.makeText(context, text, duration).show();

		openDialogPlayerName();

		//
		// AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle(R.string.title_settings_player_new)
		// .setItems(R.array.themes_list, new DialogInterface.OnClickListener()
		// {
		// public void onClick(DialogInterface dialog, int PrefThemeId) {
		// // Do something with the selection
		//
		//
		// }
		// });
		// AlertDialog alert = builder.create();
		// alert.show();
	}

	private void openDialogPlayerName() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.settings_player_new,
				null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		final EditText input = (EditText) promptView
				.findViewById(R.id.settings_player_new);
		if (editPlayer != null) {
			alertDialogBuilder.setTitle("Name �ndern");
			input.setText(editPlayer.getName());
		}
		// setup a dialog window
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// editTextMainScreen.setText(input.getText());
						Player savePlayer;
						if (editPlayer == null) {
							savePlayer = new Player();
						} else {
							savePlayer = editPlayer;
						}
						savePlayer.setName(input.getEditableText().toString());
						try {
							getHelper().getPlayerDao().createOrUpdate(
									savePlayer);
							editPlayer = null;
						} catch (SQLException e) {
							// TODO errorhandling
							e.printStackTrace();
						}
						showPlayersList();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		// create an alert dialog
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();
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

	public List<Player> getPlayers() {
		return players;
	}

}