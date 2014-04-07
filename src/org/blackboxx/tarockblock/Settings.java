package org.blackboxx.tarockblock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

//import android.widget.Toast;

public class Settings extends Activity implements OnClickListener {

	// private Button SettingsPlayer;
	// private Button SettingsTariffs;
	// private Button SettingsImExport;
	// private Button SettingsSynchronize;
	private Button SettingsTheme;
	private TextView ThemeText;
	private int ThemeId;
	private int ActivityId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		Globals g = Globals.getInstance();
		ThemeId = g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, ThemeId, ActivityId);

		setContentView(R.layout.settings);

		// SettingsPlayer = (Button) findViewById(R.id.settings_button_player);
		// SettingsTariffs = (Button)
		// findViewById(R.id.settings_button_tariffs);
		// SettingsImExport = (Button)
		// findViewById(R.id.settings_button_imexport);
		// SettingsSynchronize = (Button)
		// findViewById(R.id.settings_button_sync);
		SettingsTheme = (Button) findViewById(R.id.settings_button_theme);
		ThemeText = (TextView) findViewById(R.id.settings_text_theme);
		ThemeText.setText(getResources().getStringArray(R.array.themes_list)[ThemeId]);

		// SettingsPlayer.setOnClickListener(this);
		// SettingsTariffs.setOnClickListener(this);
		// SettingsImExport.setOnClickListener(this);
		// SettingsSynchronize.setOnClickListener(this);
		SettingsTheme.setOnClickListener(this);

		// Show the Up button in the action bar.
		setupActionBar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_overflow, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		// Context context = getApplicationContext();
		// int duration = Toast.LENGTH_LONG;
		// CharSequence text = String.valueOf(v.getId());
		// Toast.makeText(context, text, duration).show();

		if (v.getId() == R.id.settings_button_theme) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			// Set the dialog title
			builder.setTitle(R.string.title_settings_theme)
			// Specify the list array, the items to be selected by default (null
			// for none),
			// and the listener through which to receive callbacks when items
			// are selected
					.setSingleChoiceItems(R.array.themes_list, ThemeId, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int PrefThemeId) {
							// Do something with the selection

							// Save the new Theme-ID into Preferences
							// SharedPreferences sharedPref =
							// getPreferences(Context.MODE_PRIVATE);
							SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE);
							SharedPreferences.Editor editor = sharedPref.edit();
							editor.putInt("pref_theme", PrefThemeId);
							editor.commit();
							// Save the new global Theme-ID
							Globals g = Globals.getInstance();
							g.setData(PrefThemeId);
							// TextButton.setText(getResources().getStringArray(R.array.themes_list)[PrefThemeId]);

							// Intent intent = getIntent();
							// finish();
							// startActivity(intent);
							if (Build.VERSION.SDK_INT >= 11) {
								recreate();
							} else {
								Intent intent = getIntent();
								intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
								finish();
								overridePendingTransition(0, 0);

								startActivity(intent);
								overridePendingTransition(0, 0);
							}
							dialog.dismiss();
						}
					});

			builder.create().show();

			// AlertDialog.Builder builder = new AlertDialog.Builder(this);
			// builder.setTitle(R.string.title_settings_theme)
			// .setItems(R.array.themes_list, new
			// DialogInterface.OnClickListener() {
			// public void onClick(DialogInterface dialog, int PrefThemeId) {
			// // Do something with the selection
			//
			// // Save the new Theme-ID into Preferences
			// // SharedPreferences sharedPref =
			// getPreferences(Context.MODE_PRIVATE);
			// SharedPreferences sharedPref =
			// getSharedPreferences(getString(R.string.preference_file),
			// Context.MODE_PRIVATE);
			// SharedPreferences.Editor editor = sharedPref.edit();
			// editor.putInt("pref_theme", PrefThemeId);
			// editor.commit();
			// // Save the new global Theme-ID
			// Globals g = Globals.getInstance();
			// g.setData(PrefThemeId);
			// //
			// TextButton.setText(getResources().getStringArray(R.array.themes_list)[PrefThemeId]);
			//
			// Intent intent = getIntent();
			// finish();
			// startActivity(intent);
			//
			// }
			// });
			// AlertDialog alert = builder.create();
			// alert.show();
		}
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
			/*
			 * case R.id.action_sync: goto_sync(null); return true; case
			 * R.id.action_rules: goto_rules(null); return true;
			 */
		case R.id.action_settings:
			goto_settings(null);
			return true;
		case R.id.action_info:
			goto_info(null);
			return true;
		case R.id.action_exit:
			Intent intent = new Intent(getApplicationContext(), Main.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void goto_settings(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, Settings.class);
		startActivity(intent);
	}

	public void goto_info(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, Info.class);
		startActivity(intent);
	}

	public void goto_settings_player(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, Player.class);
		startActivity(intent);
	}

	public void goto_settings_tariffs(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, Tariffsets.class);
		startActivity(intent);
	}

	public void goto_settings_imexport(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, SettingsImExport.class);
		startActivity(intent);
	}

	public void goto_settings_sync(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, SettingsSync.class);
		startActivity(intent);
	}

}