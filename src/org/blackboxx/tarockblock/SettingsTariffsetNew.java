package org.blackboxx.tarockblock;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsTariffsetNew extends Activity implements OnClickListener {

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
	private int TrischakenId1 = 0;
	private int TrischakenId2 = 1;
	private int TrischakenId3 = 1;
	private int BeiId = 3;
	private int KontraId = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int user_theme = 0;
		Globals g = Globals.getInstance();
		user_theme = g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, user_theme);

		setContentView(R.layout.settings_tariffset_new);

		SettingsTariffsetNewTariff = (ImageButton) findViewById(R.id.button_tariffset_new_tariff_entry);
		SettingsTariffsetNewTariff.setOnClickListener(this);
		SettingsTariffsetNewPremium = (ImageButton) findViewById(R.id.button_tariffset_new_premium_entry);
		SettingsTariffsetNewPremium.setOnClickListener(this);
		SettingsTariffsetNewTrischakenText1 = (TextView) findViewById(R.id.settings_tariff_new_trischaken_text1);
		SettingsTariffsetNewTrischakenText2 = (TextView) findViewById(R.id.settings_tariff_new_trischaken_text2);
		SettingsTariffsetNewTrischakenText3 = (TextView) findViewById(R.id.settings_tariff_new_trischaken_text3);
		SettingsTariffsetNewTrischaken = (Button) findViewById(R.id.settings_button_tariff_new_trischaken);
		SettingsTariffsetNewTrischaken.setOnClickListener(this);
		SettingsTariffsetNewBei = (Button) findViewById(R.id.settings_button_tariff_new_bei);
		SettingsTariffsetNewBei.setOnClickListener(this);
		SettingsTariffsetNewBeiText = (TextView) findViewById(R.id.settings_tariff_new_bei_text);
		SettingsTariffsetNewKontra = (Button) findViewById(R.id.settings_button_tariff_new_kontra);
		SettingsTariffsetNewKontra.setOnClickListener(this);
		SettingsTariffsetNewKontraText = (TextView) findViewById(R.id.settings_tariff_new_kontra_text);

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
		}
	}

	private void openDialogTariffsetNewTariffEntry() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.item_tariff, null);
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
		View promptView = layoutInflater.inflate(R.layout.item_premium, null);
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
		// LayoutInflater layoutInflater = LayoutInflater.from(this);
		// View promptView =
		// layoutInflater.inflate(R.layout.settings_tariffset_new_trischaken,
		// null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		// alertDialogBuilder.setView(promptView);

		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_trischaken);
		// TODO liste und antwortmöglichkeiten aus enums bilden und nicht aus
		// array

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				SettingsTariffsetNewTrischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[TrischakenId1]);
				SettingsTariffsetNewTrischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[TrischakenId2]);
				SettingsTariffsetNewTrischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[TrischakenId3]);
				dialog.dismiss();
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

	private void openDialogTariffsetNewBei() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// TODO liste aus enums bilden und nicht aus array
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_bei).setSingleChoiceItems(R.array.list_bei, BeiId,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						SettingsTariffsetNewBeiText.setText(getResources().getStringArray(R.array.list_bei)[id]);
						dialog.dismiss();
					}
				});
		AlertDialog alertD = alertDialogBuilder.create();
		alertD.show();
	}

	private void openDialogTariffsetNewKontra() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// TODO liste aus enums bilden und nicht aus array
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_kontra).setSingleChoiceItems(R.array.list_kontra, KontraId,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
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
		Intent intent = new Intent(this, SettingsTariffset.class);
		startActivity(intent);
	}

}
