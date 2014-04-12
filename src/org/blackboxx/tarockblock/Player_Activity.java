package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.TablePlayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class Player_Activity extends OrmLiteBaseActivity<DatabaseHelper> implements OnClickListener {

	private Button playerNew;
	private List<TablePlayer> players;
	private ListView playersList;
	private ArrayAdapter<TablePlayer> playersAdapter;

	private TablePlayer editPlayer;
	private TablePlayer deletePlayer;
	private int activityId = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int defaultThemeId = 0;
		Globals g = Globals.getInstance();
		defaultThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		Helper.onActivitySetPrefTheme(this, defaultThemeId, activityId);

		setContentView(R.layout.player);
		playerNew = (Button) findViewById(R.id.player_new);
		playerNew.setOnClickListener(this);
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

		playersList = (ListView) findViewById(R.id.list_player);
		playersAdapter = new ArrayAdapter<TablePlayer>(this, R.layout.item_player, R.id.item_player, players);
		playersList.setAdapter(playersAdapter);
		registerForContextMenu(playersList);
		ScrollView scrollViewScenes = (ScrollView) findViewById(R.id.scroll);
		if (scrollViewScenes != null) {
			Helper.setListViewSize(playersList);
		}

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.add(Menu.NONE, 1, 1, R.string.menu_edit);
		menu.add(Menu.NONE, 2, 2, R.string.menu_delete);
		super.onCreateContextMenu(menu, v, menuInfo);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
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
		builder.setTitle(R.string.menu_delete_dialog);
		builder.setMessage(R.string.menu_delete_dialog_player).setPositiveButton("Ja", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				try {
					getHelper().getPlayerDao().delete(deletePlayer);

				} catch (SQLException e) {
					// TODO errorhandling
					e.printStackTrace();
				}
				showPlayersList();
			}
		}).setNegativeButton("Nein", new DialogInterface.OnClickListener() {
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
		openDialogPlayerName();
	}

	private void openDialogPlayerName() {
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER_HORIZONTAL);
		final EditText input = new EditText(this);
		layout.setPadding(32, 32, 32, 32);
		input.setHint("Spielername");
		layout.addView(input);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(layout);

		int settings_player_new = 0;
		input.setId(settings_player_new);

		if (editPlayer != null) {
			alertDialogBuilder.setTitle(R.string.menu_edit_dialog);
			input.setText(editPlayer.getName());
		} else {
			alertDialogBuilder.setTitle(R.string.player_new_dialog);
		}
		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
				TablePlayer savePlayer;
				if (editPlayer == null) {
					savePlayer = new TablePlayer();
				} else {
					savePlayer = editPlayer;
				}
				savePlayer.setName(input.getEditableText().toString());
				try {
					getHelper().getPlayerDao().createOrUpdate(savePlayer);
				} catch (SQLException e) {
					// TODO errorhandling
					e.printStackTrace();
				}
				editPlayer = null;
				showPlayersList();
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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

	public List<TablePlayer> getPlayers() {
		return players;
	}

}
