package org.blackboxx.tarockblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

public class GameCall_Activity extends Activity {
	private int activityId = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int defaultThemeId = 0;
		Globals g = Globals.getInstance();
		defaultThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		Helper.onActivitySetPrefTheme(this, defaultThemeId, activityId);

		setContentView(R.layout.game_call);
		// Show the Up button in the action bar.
		setupActionBar();
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

	public void goto_game_trischaken(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, GameTrischaken_Activity.class);
		startActivity(intent);
	}

	public void goto_game_negative(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, GameNegative_Activity.class);
		startActivity(intent);
	}

	public void goto_game_regular(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, GameRegular_Activity.class);
		startActivity(intent);
	}

	public void goto_game_player(View view) {
		// Do something in response to button
		Intent intent = new Intent(this, GamePlayer_Activity.class);
		startActivity(intent);
	}

}