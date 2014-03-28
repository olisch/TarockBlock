package org.blackboxx.tarockblock;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class Info extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		Globals g = Globals.getInstance();
		int user_theme=g.getData();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this,user_theme);

		setContentView(R.layout.info);
		// Show the Up button in the action bar.
		setupActionBar();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_overflow, menu);
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
/*	        case R.id.action_sync:
        	goto_sync(null);
            return true;
        case R.id.action_rules:
        	goto_rules(null);
            return true;*/
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

}
