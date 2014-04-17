package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.TableTariffset;

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
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import app.adapter.TariffsetListAdapter;
import app.adapter.TariffsetNewBasedonListAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class Tariffsets_Activity extends OrmLiteBaseActivity<DatabaseHelper> implements OnClickListener {
	private int activityId = 4;

	private Button tariffsetNew;
	private List<TableTariffset> tariffsets;
	private ListView tariffsetList;
	private TariffsetListAdapter tariffsetAdapter;
	private TariffsetNewBasedonListAdapter selectForNewTariffsetAdapter;
	private TableTariffset defaultTariffset;
	private TableTariffset editTariffset;
	private TableTariffset deleteTariffset;

	// private RadioButton radioButtonNewEmptyTariffset;
	private RadioButton radioButtonNewBasedonTariffset;
	private Spinner spinnerNewTariffsetBasedon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int defaultThemeId = 0;
		Globals g = Globals.getInstance();
		defaultThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		Helper.onActivitySetPrefTheme(this, defaultThemeId, activityId);

		setContentView(R.layout.tariffsets);

		tariffsetNew = (Button) findViewById(R.id.tariffset_new);
		tariffsetNew.setOnClickListener(this);

		// Show the Up button in the action bar.
		setupActionBar();

		showTariffsetList();
	}

	private void showTariffsetList() {
		Globals ts = Globals.getInstance();
		int tariffsetId = ts.getDefaultTariffsetId();

		try {
			tariffsets = getHelper().getTariffsetDao().queryForAll();
		} catch (SQLException e) {
			// TODO errorhandling
			e.printStackTrace();
		}

		tariffsetList = (ListView) findViewById(R.id.list_tariffset);
		tariffsetAdapter = new TariffsetListAdapter(this, R.layout.item_tariffset, R.id.item_tariffset,
				tariffsets.toArray(new TableTariffset[tariffsets.size()]), tariffsetId);
		tariffsetList.setAdapter(tariffsetAdapter);
		registerForContextMenu(tariffsetList);
		ScrollView scrollViewScenes = (ScrollView) findViewById(R.id.scroll);
		if (scrollViewScenes != null) {
			Helper.setListViewSize(tariffsetList);
		}
		tariffsetList.setOnItemClickListener(listItemClickedHandler);
	}

	// TODO code funkt, check obs auch richtig ist!
	private OnItemClickListener listItemClickedHandler = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			int tariffsetId = position + 1;
			goto_tariffset_new(tariffsetId);
		}
	};

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
		defaultTariffset = tariffsetAdapter.getItem((int) id);
		int prefTariffsetId = defaultTariffset.getId();
		SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("pref_tariffset", prefTariffsetId);
		editor.commit();
		Globals ts = Globals.getInstance();
		ts.setDefaultTariffsetId(prefTariffsetId);
		showTariffsetList();
	}

	private void deleteTariffset(long id) {
		deleteTariffset = tariffsetAdapter.getItem((int) id);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.menu_delete);
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
		case R.id.tariffset_new:
			openDialogTariffsetNewBase();
			break;
		}
	}

	private void openDialogTariffsetNewBase() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariffset_new_base, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		// TODO howto style spinner? styling mit dem angegebenen layout klappt
		// nicht!
		alertDialogBuilder.setView(promptView);

		// radioButtonNewEmptyTariffset = (RadioButton)
		// promptView.findViewById(R.id.tariffset_new_empty);
		radioButtonNewBasedonTariffset = (RadioButton) promptView.findViewById(R.id.tariffset_new_basedon);
		spinnerNewTariffsetBasedon = (Spinner) promptView.findViewById(R.id.tariffset_new_basedon_spinner);
		selectForNewTariffsetAdapter = new TariffsetNewBasedonListAdapter(this, R.layout.item_tariffset_basedon, R.id.item_tariffset_basedon,
				tariffsets.toArray(new TableTariffset[tariffsets.size()]));
		spinnerNewTariffsetBasedon.setAdapter(selectForNewTariffsetAdapter);

		alertDialogBuilder.setTitle(R.string.menu_tariffset_new_dialog);
		// TODO if spinner is touched/selected then check radio button
		// spinnerNewTariffsetBasedon.setOnClickListener(new OnClickListener() {
		// @Override
		// public void OnClick(View v) {
		// radioButtonNewBasedonTariffset.setChecked(true);
		// }
		// });

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Integer tariffsetId = null;
				if (radioButtonNewBasedonTariffset.isChecked()) {
					tariffsetId = selectForNewTariffsetAdapter.getItem(spinnerNewTariffsetBasedon.getSelectedItemPosition()).getId();
				}
				goto_tariffset_new(tariffsetId);
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
		View promptView = layoutInflater.inflate(R.layout.tariffset_rename, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		final EditText input = (EditText) promptView.findViewById(R.id.tariffset_rename);
		if (editTariffset != null) {
			alertDialogBuilder.setTitle(R.string.menu_edit_dialog);
			input.setText(editTariffset.getName());
		}
		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
				TableTariffset saveTariffset;
				if (editTariffset == null) {
					saveTariffset = new TableTariffset();
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

	// public void goto_tariff(View view) {
	// Intent intent = new Intent(this, TableTariffset.class);
	// startActivity(intent);
	// }

	public void goto_tariffset_new(Integer tariffsetId) {
		Intent intent = new Intent(this, TariffsetNew_Activity.class);
		if (tariffsetId != null) {
			intent.putExtra("editTariffsetId", tariffsetId);
		}
		startActivity(intent);
	}

	public List<TableTariffset> getTariffset() {
		return tariffsets;
	}

}
