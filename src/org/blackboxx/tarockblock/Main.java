package org.blackboxx.tarockblock;

import org.blackboxx.tarockblock.dao.DatabaseHelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class Main extends OrmLiteBaseActivity<DatabaseHelper> {

	boolean schduledRestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get Theme-ID from Preference-File
		int ThemeId;
		SharedPreferences sharedPref = getSharedPreferences(
				getString(R.string.preference_file), Context.MODE_PRIVATE);
		ThemeId = sharedPref.getInt("pref_theme", 0);
		// ThemeId = UtilsActivity.getTheme(this);
		// Save Theme-ID in global Variable
		Globals g = Globals.getInstance();
		g.setData(ThemeId);
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, ThemeId);

		setContentView(R.layout.main);
		if (getIntent().getBooleanExtra("EXIT", false)) {
			finish();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_overflow, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
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
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void goto_info(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, Info.class);
		startActivity(intent);
	}

	public void goto_settings(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, Settings.class);
		startActivity(intent);
	}

	/** Called when user clicks a button */
	public void goto_game_call(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, GameCall.class);
		startActivity(intent);
	}

	public void goto_session_select(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, SessionSelect.class);
		startActivity(intent);
	}

	public void goto_session_new(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, SessionNew.class);
		startActivity(intent);
	}

}
