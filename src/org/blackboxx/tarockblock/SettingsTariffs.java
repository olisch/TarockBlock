package org.blackboxx.tarockblock;

import java.sql.SQLException;
import java.util.List;

import org.blackboxx.tarockblock.dao.DatabaseHelper;
import org.blackboxx.tarockblock.persistance.Tariffset;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class SettingsTariffs extends OrmLiteBaseActivity<DatabaseHelper> {

	private List<Tariffset> tariffsets;
	private ListView tariffsetList;
	private ArrayAdapter<Tariffset> tariffsetAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int user_theme=0;
		Globals g = Globals.getInstance();
		user_theme=g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this,user_theme);

		setContentView(R.layout.settings_tariffs);
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

		tariffsetList = (ListView) findViewById(R.id.list_tariffs);
		tariffsetAdapter = new ArrayAdapter<Tariffset>(this,
				R.layout.list_item_player, R.id.list_players_item, tariffsets);
		tariffsetList.setAdapter(tariffsetAdapter);
		registerForContextMenu(tariffsetList);

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

	public void goto_settings_tariff_new(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNew.class);
	    startActivity(intent);
	}    

	public List<Tariffset> getTariffset() {
		return tariffsets;
	}

}
