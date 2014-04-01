package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.Tariffset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class SettingsTariffset extends OrmLiteBaseActivity<DatabaseHelper> implements OnClickListener {

	private Button SettingsTariffsetNew;
	private List<Tariffset> tariffsets;
	private ListView tariffsetList;
	private ArrayAdapter<Tariffset> tariffsetAdapter;

	private Tariffset editTariffset;
	private Tariffset deleteTariffset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int user_theme = 0;
		Globals g = Globals.getInstance();
		user_theme = g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, user_theme);

		setContentView(R.layout.settings_tariffset);
		SettingsTariffsetNew = (Button) findViewById(R.id.settings_button_tariffset_new);
		SettingsTariffsetNew.setOnClickListener(this);
		// Show the Up button in the action bar.
		setupActionBar();

		showTariffsetList();
	}

	private void showTariffsetList() {
		try {
			tariffsets = getHelper().getTariffsetDao().queryForAll();
		} catch (SQLException e) {
			// TODO errorhandling
			e.printStackTrace();
		}

		tariffsetList = (ListView) findViewById(R.id.list_tariffset);
		tariffsetAdapter = new ArrayAdapter<Tariffset>(this, R.layout.list_item_tariffset, R.id.list_tariffset_item, tariffsets);
		tariffsetList.setAdapter(tariffsetAdapter);
		registerForContextMenu(tariffsetList);

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
			editTariffset(info.id);
			return true;
		case 2:
			deleteTariffset(info.id);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void deleteTariffset(long id) {
		deleteTariffset = tariffsetAdapter.getItem((int) id);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.tariffset_menu_delete_dialog).setPositiveButton("Ja", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				try {
					getHelper().getTariffsetDao().delete(deleteTariffset);

				} catch (SQLException e) {
					// TODO errorhandling
					e.printStackTrace();
				}
				showTariffsetList();
			}
		}).setNegativeButton("Nein", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		builder.show();
	}

	private void editTariffset(long id) {
		editTariffset = tariffsetAdapter.getItem((int) id);
		openDialogTariffsetName();
	}

	@Override
	public void onClick(View v) {
		openDialogTariffsetName();
	}

	private void openDialogTariffsetName() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.settings_tariffset_new, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		final EditText input = (EditText) promptView.findViewById(R.id.settings_tariffset_rename);
		if (editTariffset != null) {
			alertDialogBuilder.setTitle(R.string.tariffset_menu_edit_dialog);
			input.setText(editTariffset.getName());
		}
		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
				Tariffset saveTariffset;
				if (editTariffset == null) {
					saveTariffset = new Tariffset();
				} else {
					saveTariffset = editTariffset;
				}
				saveTariffset.setName(input.getEditableText().toString());
				try {
					getHelper().getTariffsetDao().createOrUpdate(saveTariffset);
					editTariffset = null;
				} catch (SQLException e) {
					// TODO errorhandling
					e.printStackTrace();
				}
				showTariffsetList();
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

	public void goto_settings_tariff(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, SettingsTariff.class);
		startActivity(intent);
	}

	public void goto_settings_tariffset_new(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, SettingsTariffsetNew.class);
		startActivity(intent);
	}

	public List<Tariffset> getTariffset() {
		return tariffsets;
	}

}
