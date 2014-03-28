package org.blackboxx.tarockblock;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class SettingsTariffNew extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int user_theme=0;
		Globals g = Globals.getInstance();
		user_theme=g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this,user_theme);

		setContentView(R.layout.settings_tariff_new);
		// Show the Up button in the action bar.
		setupActionBar();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings_tariff_new, menu);
        return true;
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
        case R.id.action_trischaken:
        	goto_trischaken(null);
            return true;
        case R.id.action_bei:
        	goto_bei(null);
            return true;
        case R.id.action_kontra:
        	goto_kontra(null);
		}
		return super.onOptionsItemSelected(item);
	}

	public void goto_trischaken(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNewTrischaken.class);
	    startActivity(intent);
	}

	public void goto_bei(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNewBei.class);
	    startActivity(intent);
	}

	public void goto_kontra(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNewKontra.class);
	    startActivity(intent);
	}

	public void goto_settings_tariffs(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffs.class);
	    startActivity(intent);
	}    
	
	public void goto_settings_tariff_new_entry(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNewEntry.class);
	    startActivity(intent);
	}    

	public void goto_settings_tariff_new_trischaken(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNewTrischaken.class);
	    startActivity(intent);
	}    

	public void goto_settings_tariff_new_bei(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNewBei.class);
	    startActivity(intent);
	}    

	public void goto_settings_tariff_new_kontra(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, SettingsTariffNewKontra.class);
	    startActivity(intent);
	}    

}
