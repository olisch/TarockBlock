package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.Tariffset;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class SettingsTariffset extends OrmLiteBaseActivity<DatabaseHelper> implements OnClickListener {

	private Button SettingsTariffsetNew;
	private List<Tariffset> tariffsets;
	private ListView tariffsetList;
	private ArrayAdapter<Tariffset> tariffsetAdapter;

	private Tariffset defaultTariffset;
	private Tariffset editTariffset;
	private Tariffset deleteTariffset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int ThemeId = 0;
		Globals g = Globals.getInstance();
		ThemeId = g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, ThemeId);

		setContentView(R.layout.settings_tariffset);

		SettingsTariffsetNew = (Button) findViewById(R.id.settings_button_tariffset_new);
		SettingsTariffsetNew.setOnClickListener(this);

		// Show the Up button in the action bar.
		setupActionBar();

		showTariffsetList();
	}

	private void showTariffsetList() {
		int TariffsetId = 0;
		Globals ts = Globals.getInstance();
		TariffsetId = ts.getData();

		Context context = getApplicationContext();
		int duration = Toast.LENGTH_LONG;
		CharSequence text = String.valueOf(ts);
		Toast.makeText(context, text, duration).show();

		try {
			tariffsets = getHelper().getTariffsetDao().queryForAll();
		} catch (SQLException e) {
			// TODO errorhandling
			e.printStackTrace();
		}

		// TODO if tariffsetId = TariffsetId then make this listrow bold

		tariffsetList = (ListView) findViewById(R.id.list_tariffset);
		tariffsetAdapter = new ArrayAdapter<Tariffset>(this, R.layout.item_tariffset, R.id.item_tariffset, tariffsets);
		tariffsetList.setAdapter(tariffsetAdapter);
		registerForContextMenu(tariffsetList);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.add(Menu.NONE, 1, 1, R.string.menu_default);
		menu.add(Menu.NONE, 2, 2, R.string.menu_edit);
		menu.add(Menu.NONE, 3, 3, R.string.menu_delete);
		super.onCreateContextMenu(menu, v, menuInfo);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case 1:
			defaultTariffset(info.id);
			return true;
		case 2:
			editTariffset(info.id);
			return true;
		case 3:
			deleteTariffset(info.id);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void defaultTariffset(long id) {
		// TODO save selected tariffsetId into defaultTariffset
		defaultTariffset = tariffsetAdapter.getItem((int) id);
		int PrefTariffsetId = defaultTariffset.getId();
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("pref_tariffset", PrefTariffsetId);
		editor.commit();
		Globals ts = Globals.getInstance();
		ts.setData(PrefTariffsetId);
		showTariffsetList();
	}

	private void deleteTariffset(long id) {
		deleteTariffset = tariffsetAdapter.getItem((int) id);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.menu_delete_dialog_tariffset).setPositiveButton("Ja", new DialogInterface.OnClickListener() {
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
		switch (v.getId()) {
		case R.id.settings_button_tariffset_new:
			openDialogTariffsetNewBase();
			break;
		case 2:
			openDialogTariffsetName();
			break;
		}
	}

	private void openDialogTariffsetNewBase() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.settings_tariffset_new_base, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		alertDialogBuilder.setTitle(R.string.menu_tariffset_new_dialog);

		// TODO ersetze zweites radiobutton item mit spinner aus tariffset
		// namen, vielleicht ähnlich wie unten?

		// String[] items = new String[] { "Karnataka", "Orissa",
		// "Andhrapradesh" };
		// Spinner spinner;
		//
		// spinner = (Spinner)
		// this.findViewById(R.id.settings_tariffset_new_basedon);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_spinner_item, items);
		// adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spinner.setAdapter(adapter);

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// TODO beim anzeigen des neuen tarifsets sollte auch mitgegeben
				// werden, obs leer ist, oder welches tarifset kopiert und
				// angezeigt werden soll zum editieren
				goto_settings_tariffset_new(null);
			}
		}).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		// create an alert dialog
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();
	}

	private void openDialogTariffsetName() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.settings_tariffset_rename, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		final EditText input = (EditText) promptView.findViewById(R.id.settings_tariffset_rename);
		if (editTariffset != null) {
			alertDialogBuilder.setTitle(R.string.menu_edit_dialog);
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
