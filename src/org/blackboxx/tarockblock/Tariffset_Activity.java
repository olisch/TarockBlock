package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.TableTariff;

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

public class Tariffset_Activity extends OrmLiteBaseActivity<DatabaseHelper> implements OnClickListener {
	private int activityId = 4;

	private Button tariffNew;
	private List<TableTariff> tariffs;
	private ListView tariffList;
	private ArrayAdapter<TableTariff> tariffAdapter;

	private TableTariff editTariff;
	private TableTariff deleteTariff;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int defaultThemeId = 0;
		Globals g = Globals.getInstance();
		defaultThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		Helper.onActivitySetPrefTheme(this, defaultThemeId, activityId);

		setContentView(R.layout.tariffset);
		tariffNew = (Button) findViewById(R.id.tariff_new);
		tariffNew.setOnClickListener(this);
		// Show the Up button in the action bar.
		setupActionBar();

		showTariffList();
	}

	private void showTariffList() {
		try {
			tariffs = getHelper().getTariffDao().queryForAll();
		} catch (SQLException e) {
			// TODO errorhandling
			e.printStackTrace();
		}

		tariffList = (ListView) findViewById(R.id.tariff);
		tariffAdapter = new ArrayAdapter<TableTariff>(this, R.layout.item_tariff, R.id.tariffset_tariff, tariffs);
		tariffList.setAdapter(tariffAdapter);
		registerForContextMenu(tariffList);

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
			editTariff(info.id);
			return true;
		case 2:
			deleteTariff(info.id);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void deleteTariff(long id) {
		deleteTariff = tariffAdapter.getItem((int) id);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.menu_delete_dialog_tariff).setPositiveButton("Ja", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				try {
					getHelper().getTariffDao().delete(deleteTariff);

				} catch (SQLException e) {
					// TODO errorhandling
					e.printStackTrace();
				}
				showTariffList();
			}
		}).setNegativeButton("Nein", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		builder.show();
	}

	private void editTariff(long id) {
		editTariff = tariffAdapter.getItem((int) id);
		openDialogTariffName();
	}

	@Override
	public void onClick(View v) {
		openDialogTariffName();
	}

	private void openDialogTariffName() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariff_rename, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		final EditText input = (EditText) promptView.findViewById(R.id.tariff_rename);
		if (editTariff != null) {
			alertDialogBuilder.setTitle(R.string.menu_edit_dialog);
			input.setText(editTariff.getName());
		}
		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
				TableTariff saveTariff;
				if (editTariff == null) {
					saveTariff = new TableTariff();
				} else {
					saveTariff = editTariff;
				}
				saveTariff.setName(input.getEditableText().toString());
				try {
					getHelper().getTariffDao().createOrUpdate(saveTariff);
					editTariff = null;
				} catch (SQLException e) {
					// TODO errorhandling
					e.printStackTrace();
				}
				showTariffList();
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

	public List<TableTariff> getTariff() {
		return tariffs;
	}

}
