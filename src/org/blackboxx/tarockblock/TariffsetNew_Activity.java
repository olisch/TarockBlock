package org.blackboxx.tarockblock;

import java.sql.SQLException;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.enums.ArrayConverter;
import org.blackboxx.tarockblock.persistance.TablePremium;
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
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import app.adapter.PremiumListAdapter;
import app.adapter.TariffListAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class TariffsetNew_Activity extends OrmLiteBaseActivity<DatabaseHelper> implements OnClickListener {

	private TableTariffset actualTariffset;
	private ImageButton tariffNew;
	private ImageButton premiumNew;
	private Button trischaken;
	private Button bei;
	private Button kontra;
	private TextView trischakenText1;
	private TextView trischakenText2;
	private TextView trischakenText3;
	private TextView beiText;
	private TextView kontraText;
	private EditText tariffsetNameEditText;
	private int activityId = 4;

	private Switch switchTrischaken1;
	private Switch switchTrischaken2;
	private Switch switchTrischaken3;
	private TariffListAdapter tariffListAdapter;
	private PremiumListAdapter premiumListAdapter;
	private ListView tariffListView;
	private ListView premiumListView;

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
		int defaultThemeId = 0;
		Globals g = Globals.getInstance();
		defaultThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		Helper.onActivitySetPrefTheme(this, defaultThemeId, activityId);

		setContentView(R.layout.tariffset_new);

		tariffNew = (ImageButton) findViewById(R.id.button_tariffset_new_tariff_entry);
		tariffNew.setOnClickListener(this);
		premiumNew = (ImageButton) findViewById(R.id.button_tariffset_new_premium_entry);
		premiumNew.setOnClickListener(this);
		trischakenText1 = (TextView) findViewById(R.id.tariff_new_trischaken_text1);
		trischakenText2 = (TextView) findViewById(R.id.tariff_new_trischaken_text2);
		trischakenText3 = (TextView) findViewById(R.id.tariff_new_trischaken_text3);
		trischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[actualTariffset.getTri1() - 1]);
		trischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[actualTariffset.getTri2() - 1]);
		trischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[actualTariffset.getTri3() - 1]);
		trischaken = (Button) findViewById(R.id.button_tariff_new_trischaken);
		trischaken.setOnClickListener(this);
		bei = (Button) findViewById(R.id.button_tariff_new_bei);
		bei.setOnClickListener(this);
		beiText = (TextView) findViewById(R.id.tariff_new_bei_text);
		beiText.setText(ArrayConverter.getBeiText(actualTariffset.getBei(), getResources()));
		kontra = (Button) findViewById(R.id.button_tariff_new_kontra);
		kontra.setOnClickListener(this);
		kontraText = (TextView) findViewById(R.id.tariff_new_kontra_text);
		kontraText.setText(ArrayConverter.getKontraText(actualTariffset.getKontra(), getResources()));
		tariffsetNameEditText = (EditText) findViewById(R.id.tariffset_new_name);
		tariffsetNameEditText.setText(actualTariffset.getName());
		Button SettingsTariffsetNewSaveButton = (Button) findViewById(R.id.button_save);
		SettingsTariffsetNewSaveButton.setOnClickListener(this);

		ScrollView scrollViewScenes = (ScrollView) findViewById(R.id.scroll);

		tariffListView = (ListView) findViewById(R.id.tariffset_new_tariffslist);
		if (actualTariffset.getId() != null) {
			tariffListAdapter = new TariffListAdapter(this, R.layout.item_tariff, R.id.tariffset_tariff, actualTariffset.getTariffs().toArray(
					new TableTariff[actualTariffset.getTariffs().size()]));
			tariffListView.setAdapter(tariffListAdapter);
		}
		if (scrollViewScenes != null) {
			Helper.setListViewSize(tariffListView);
			// MyUtilities.setListViewHeightBasedOnChildren(tariffListView);
		}

		premiumListView = (ListView) findViewById(R.id.tariffset_new_premiumslist);
		if (actualTariffset.getId() != null) {
			premiumListAdapter = new PremiumListAdapter(this, R.layout.item_premium, R.id.tariffset_premium, actualTariffset.getPremiums().toArray(
					new TablePremium[actualTariffset.getPremiums().size()]));
			premiumListView.setAdapter(premiumListAdapter);
		}
		if (scrollViewScenes != null) {
			Helper.setListViewSize(premiumListView);
			// MyUtilities.setListViewHeightBasedOnChildren(premiumListView);
		}

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
		case R.id.button_tariff_new_trischaken:
			openDialogTariffsetNewTrischaken();
			break;
		case R.id.button_tariff_new_bei:
			openDialogTariffsetNewBei();
			break;
		case R.id.button_tariff_new_kontra:
			openDialogTariffsetNewKontra();
			break;
		case R.id.button_save:
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
		Intent intent = new Intent(this, Tariffsets_Activity.class);
		startActivity(intent);

	}

	private void openDialogTariffsetNewTariffEntry() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariffset_new_tariff, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_tariff);
		alertDialogBuilder.setView(promptView);

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
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

	private void openDialogTariffsetNewPremiumEntry() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariffset_new_premium, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		// set prompts.xml to be the layout file of the alertdialog builder
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_premium);
		alertDialogBuilder.setView(promptView);

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// get user input and set it to result
				// editTextMainScreen.setText(input.getText());
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

	private void openDialogTariffsetNewTrischaken() {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View promptView = layoutInflater.inflate(R.layout.tariffset_trischaken, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		String[] label = getResources().getStringArray(R.array.list_trischaken);
		switchTrischaken1 = (Switch) promptView.findViewById(R.id.switch_tri1);
		switchTrischaken1.setChecked(2 == actualTariffset.getTri1());
		switchTrischaken2 = (Switch) promptView.findViewById(R.id.switch_tri2);
		switchTrischaken2.setChecked(2 == actualTariffset.getTri2());
		switchTrischaken3 = (Switch) promptView.findViewById(R.id.switch_tri3);
		switchTrischaken3.setChecked(2 == actualTariffset.getTri3());
		switchTrischaken1.setText(label[0]);
		switchTrischaken2.setText(label[1]);
		switchTrischaken3.setText(label[2]);
		alertDialogBuilder.setTitle(R.string.title_settings_tariff_new_trischaken);
		alertDialogBuilder.setView(promptView);

		// setup a dialog window
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				actualTariffset.setTri1((switchTrischaken1.isChecked() ? 2 : 1));
				actualTariffset.setTri2((switchTrischaken2.isChecked() ? 2 : 1));
				actualTariffset.setTri3((switchTrischaken3.isChecked() ? 2 : 1));

				trischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[actualTariffset.getTri1() - 1]);
				trischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[actualTariffset.getTri2() - 1]);
				trischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[actualTariffset.getTri3() - 1]);
				dialog.dismiss();
			}
		}).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// actualTariffset.setTri1((switchTrischaken1.isChecked() ? 2 :
				// 1));
				// actualTariffset.setTri2((switchTrischaken2.isChecked() ? 2 :
				// 1));
				// actualTariffset.setTri3((switchTrischaken3.isChecked() ? 2 :
				// 1));
				//
				// trischakenText1.setText(getResources().getStringArray(R.array.list_trischaken1)[actualTariffset.getTri1()
				// - 1]);
				// trischakenText2.setText(getResources().getStringArray(R.array.list_trischaken2)[actualTariffset.getTri2()
				// - 1]);
				// trischakenText3.setText(getResources().getStringArray(R.array.list_trischaken3)[actualTariffset.getTri3()
				// - 1]);
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
						beiText.setText(getResources().getStringArray(R.array.list_bei)[id]);
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
						kontraText.setText(getResources().getStringArray(R.array.list_kontra)[id]);
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
		Intent intent = new Intent(this, Tariffsets_Activity.class);
		startActivity(intent);
	}

}
