package org.blackboxx.tarockblock;

import java.sql.SQLException;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.enums.ArrayConverter;
import org.blackboxx.tarockblock.persistance.TableTariff;
import org.blackboxx.tarockblock.persistance.TableTariffset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import app.adapter.TariffListAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class TariffsetNew extends OrmLiteBaseActivity<DatabaseHelper> implements OnClickListener {

	private TableTariffset actualTariffset;
	private ImageButton SettingsTariffsetNewTariff;
	private ImageButton SettingsTariffsetNewPremium;
	private Button SettingsTariffsetNewTrischaken;
	private Button SettingsTariffsetNewBei;
	private Button SettingsTariffsetNewKontra;
	private TextView SettingsTariffsetNewTrischakenText1;
	private TextView SettingsTariffsetNewTrischakenText2;
	private TextView SettingsTariffsetNewTrischakenText3;
	private TextView SettingsTariffsetNewBeiText;
	private TextView SettingsTariffsetNewKontraText;
	private EditText tariffsetNameEditText;
	private int ActivityId = 4;

	private ToggleButton toggleButtonTrischaken1;
	private ToggleButton toggleButtonTrischaken2;
	private ToggleButton toggleButtonTrischaken3;
	private TariffListAdapter tariffListAdapter;
	private ListView tariffListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		actualTariffset = new TableTariffset();
		Intent intent = getIntent();
		if (null != intent) {
			int editId = intent.getIntExtra("editTariffsetId", -1);
			if (editId != -1) {
				try {
					actualTariffset = getHelper().getTariffsetDao().queryForId(editId);
				} catch (SQLException e) {
					// TODO errorhandling
					e.printStackTrace();
				}
			}
		}

		// Get the global Theme-ID
		int ThemeId = 0;
		Globals g = Globals.getInstance();
		ThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, ThemeId, ActivityId);

		setContentView(R.layout.tariffset_new);

		SettingsTariffsetNewTariff = (ImageButton) findViewById(R.id.button_tariffset_new_tariff_entry);
		SettingsTariffsetNewTariff.setOnClickListener(this);
		SettingsTariffsetNewPremium = (ImageButton) findViewById(R.id.button_tariffset_new_premium_entry);
		SettingsTariffsetNewPremium.setOnClickListener(this);
		SettingsTariffsetNewTrischakenText1 = (TextView) findViewById(R.id.settings_tariff_new_trischaken_text1);
		SettingsTariffsetNewTrischakenText2 = (TextView) findViewById(R.id.settings_tariff_new_trischaken_text2);
		SettingsTariffsetNewTrischakenText3 = (TextView) findViewById(R.id.settings_tariff_new_trischaken_text3);
		SettingsTariffsetNewTrischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[actualTariffset.getTri1() - 1]);
		SettingsTariffsetNewTrischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[actualTariffset.getTri2() - 1]);
		SettingsTariffsetNewTrischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[actualTariffset.getTri3() - 1]);
		SettingsTariffsetNewTrischaken = (Button) findViewById(R.id.settings_button_tariff_new_trischaken);
		SettingsTariffsetNewTrischaken.setOnClickListener(this);
		SettingsTariffsetNewBei = (Button) findViewById(R.id.settings_button_tariff_new_bei);
		SettingsTariffsetNewBei.setOnClickListener(this);
		SettingsTariffsetNewBeiText = (TextView) findViewById(R.id.settings_tariff_new_bei_text);
		SettingsTariffsetNewBeiText.setText(ArrayConverter.getBeiText(actualTariffset.getBei(), getResources()));
		SettingsTariffsetNewKontra = (Button) findViewById(R.id.settings_button_tariff_new_kontra);
		SettingsTariffsetNewKontra.setOnClickListener(this);
		SettingsTariffsetNewKontraText = (TextView) findViewById(R.id.settings_tariff_new_kontra_text);
		SettingsTariffsetNewKontraText.setText(ArrayConverter.getKontraText(actualTariffset.getKontra(), getResources()));
		tariffsetNameEditText = (EditText) findViewById(R.id.tariffset_new_name);
		tariffsetNameEditText.setText(actualTariffset.getName());
		Button SettingsTariffsetNewSaveButton = (Button) findViewById(R.id.settings_tariffs_new_button_save);
		SettingsTariffsetNewSaveButton.setOnClickListener(this);

		tariffListView = (ListView) findViewById(R.id.settings_tariffset_new_tariffslist);
		if (actualTariffset.getId() != null) {
			tariffListAdapter = new TariffListAdapter(this, R.layout.item_tariff, R.id.settings_tariffset_tariff, actualTariffset.getTariffs().toArray(
					new TableTariff[actualTariffset.getTariffs().size()]));
			tariffListView.setAdapter(tariffListAdapter);
		}

		// TODO text aus enums bilden und nicht aus array
		// SettingsTariffsetNewTrischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[TrischakenId1]);
		// SettingsTariffsetNewTrischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[TrischakenId2]);
		// SettingsTariffsetNewTrischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[TrischakenId3]);
		// SettingsTariffsetNewBeiText.setText(getResources().getStringArray(R.array.list_bei)[BeiId]);
		// SettingsTariffsetNewKontraText.setText(getResources().getStringArray(R.array.list_kontra)[KontraId]);

		// Show the Up button in the action bar.
		setupActionBar();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_tariffset_new_tariff_entry:
			openDialogTariffsetNewTariffEntry();
			break;
		case R.id.button_tariffset_new_premium_entry:
			openDialogTariffsetNewPremiumEntry();
			break;
		case R.id.settings_button_tariff_new_trischaken:
			openDialogTariffsetNewTrischaken();
			break;
		case R.id.settings_button_tariff_new_bei:
			openDialogTariffsetNewBei();
			break;
		case R.id.settings_button_tariff_new_kontra:
			openDialogTariffsetNewKontra();
			break;
		case R.id.settings_tariffs_new_button_save:
			save();
			break;
		}
	}

	private void save() {
		// TODO add Validation
		actualTariffset.setName(tariffsetNameEditText.getText().toString());
		try {
			getHelper().getTariffsetDao().createOrUpdate(actualTariffset);
		} catch (SQLException e) {
			// TODO errorhandling
			e.printStackTrace();
		}
		Intent intent = new Intent(this, Tariffsets.class);
		startActivity(intent);

	}

	private void openDialogTariffsetNewTariffEntry() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariffset_new_tariff, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
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

	private void openDialogTariffsetNewPremiumEntry() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariffset_new_premium, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setView(promptView);

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
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

	private void openDialogTariffsetNewTrischaken() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariffset_trischaken, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		// alertDialogBuilder.setView(promptView);
		TextView trischaken1 = (TextView) promptView.findViewById(R.id.tariffset_new_trischaken1);
		TextView trischaken2 = (TextView) promptView.findViewById(R.id.tariffset_new_trischaken2);
		TextView trischaken3 = (TextView) promptView.findViewById(R.id.tariffset_new_trischaken3);
		String[] label = getResources().getStringArray(R.array.list_trischaken);
		trischaken1.setText(label[0]);
		trischaken2.setText(label[1]);
		trischaken3.setText(label[2]);
		toggleButtonTrischaken1 = (ToggleButton) promptView.findViewById(R.id.toggleButton_tri1);
		toggleButtonTrischaken1.setChecked(2 == actualTariffset.getTri1());
		toggleButtonTrischaken2 = (ToggleButton) promptView.findViewById(R.id.toggleButton_tri2);
		toggleButtonTrischaken2.setChecked(2 == actualTariffset.getTri2());
		toggleButtonTrischaken3 = (ToggleButton) promptView.findViewById(R.id.toggleButton_tri3);
		toggleButtonTrischaken3.setChecked(2 == actualTariffset.getTri3());
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_trischaken);
		alertDialogBuilder.setView(promptView);

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				actualTariffset.setTri1((toggleButtonTrischaken1.isChecked() ? 2 : 1));
				actualTariffset.setTri2((toggleButtonTrischaken2.isChecked() ? 2 : 1));
				actualTariffset.setTri3((toggleButtonTrischaken3.isChecked() ? 2 : 1));

				SettingsTariffsetNewTrischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[actualTariffset.getTri1() - 1]);
				SettingsTariffsetNewTrischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[actualTariffset.getTri2() - 1]);
				SettingsTariffsetNewTrischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[actualTariffset.getTri3() - 1]);
				dialog.dismiss();
			}
		}).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				actualTariffset.setTri1((toggleButtonTrischaken1.isChecked() ? 2 : 1));
				actualTariffset.setTri2((toggleButtonTrischaken2.isChecked() ? 2 : 1));
				actualTariffset.setTri3((toggleButtonTrischaken3.isChecked() ? 2 : 1));

				SettingsTariffsetNewTrischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[actualTariffset.getTri1() - 1]);
				SettingsTariffsetNewTrischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[actualTariffset.getTri2() - 1]);
				SettingsTariffsetNewTrischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[actualTariffset.getTri3() - 1]);
				dialog.cancel();
			}
		});
		// create an alert dialog
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();

	}

	private void openDialogTariffsetNewBei() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_bei).setSingleChoiceItems(R.array.list_bei,
				ArrayConverter.getBeiId(actualTariffset.getBei(), getResources()), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						actualTariffset.setBei(Integer.valueOf(getResources().getStringArray(R.array.list_bei_values)[id]));
						SettingsTariffsetNewBeiText.setText(getResources().getStringArray(R.array.list_bei)[id]);
						dialog.dismiss();
					}
				});
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();
	}

	private void openDialogTariffsetNewKontra() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_kontra).setSingleChoiceItems(R.array.list_kontra,
				ArrayConverter.getKontraId(actualTariffset.getKontra(), getResources()), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						actualTariffset.setKontra(Integer.valueOf(getResources().getStringArray(R.array.list_kontra_values)[id]));
						SettingsTariffsetNewKontraText.setText(getResources().getStringArray(R.array.list_kontra)[id]);
						dialog.dismiss();
					}
				});
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

	public void goto_settings_tariffs(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, Tariffsets.class);
		startActivity(intent);
	}

}
