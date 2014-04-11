package org.blackboxx.tarockblock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameNegative extends Activity implements OnClickListener {

	private Button Button_Bei;
	private Button Button_Kontra;
	private int ActivityId = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the global Theme-ID
		int ThemeId = 0;
		Globals g = Globals.getInstance();
		ThemeId = g.getThemeId();
		// Apply the Theme saved global Variable
		UtilsActivity.onActivitySetPrefTheme(this, ThemeId, ActivityId);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.title_settings_player);
		builder.setItems(R.array.list_user, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				// Do something with the selection

			}

			@SuppressWarnings("unused")
			public void onBackPressed() {
				finish();
			}
		});
		builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
				finish();
			}
		});
		AlertDialog alert = builder.create();
		alert.setCancelable(false);
		alert.setCanceledOnTouchOutside(false);
		alert.show();

		setContentView(R.layout.game_negative);

		Button_Bei = (Button) findViewById(R.id.game_negative_bei);
		Button_Bei.setOnClickListener(this);
		Button_Kontra = (Button) findViewById(R.id.game_kontra);
		Button_Kontra.setOnClickListener(this);

		// Show the Up button in the action bar.
		setupActionBar();
	}

	// @Override
	// public void onBackPressed() {
	// AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	//
	// alertDialogBuilder.setTitle("Achtung!");
	// alertDialogBuilder.setMessage("Spielauswahl wirklich beenden?");
	// alertDialogBuilder.setCancelable(false);
	// alertDialogBuilder.setPositiveButton("Ja",new
	// DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog,int id) {
	// dialog.dismiss();
	// finish();
	// }
	// })
	// .setNegativeButton("Nein",new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog,int id) {
	// dialog.cancel();
	// }
	// });
	// AlertDialog alertDialog = alertDialogBuilder.create();
	// alertDialog.show();
	// }

	// @Override
	// public void onBackPressed() {
	// super.onBackPressed();
	// this.finish();
	// }

	@Override
	public void onClick(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.game_kontra);
		builder.setItems(R.array.list_kontra, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				// Do something with the selection

			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	// @Override
	// public void onClick(View v) {
	// AlertDialog.Builder builder = new AlertDialog.Builder(this);
	// builder.setTitle(R.string.title_game_negative_bei);
	// builder.setItems(R.array.list_bei, new DialogInterface.OnClickListener()
	// {
	// public void onClick(DialogInterface dialog, int item) {
	// // Do something with the selection
	//
	// }
	// });
	// AlertDialog alert = builder.create();
	// alert.show();
	// }

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

}
