package org.blackboxx.tarockblock;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;

public class UtilsActivity {

	/** Set the theme of the activity, according to the configuration. */
	public static void onActivitySetPrefTheme(Activity activity, int usertheme, int activityId) {
		switch (usertheme) {
		default:
		case 0:
			if (activityId == 1)
				activity.setTheme(R.style.SessionActionBarTheme);
			else if (activityId == 2)
				activity.setTheme(R.style.GameActionBarTheme);
			else if (activityId == 3)
				activity.setTheme(R.style.PlayerActionBarTheme);
			else if (activityId == 4)
				activity.setTheme(R.style.TariffsetActionBarTheme);
			else if (activityId == 5)
				activity.setTheme(R.style.InfoActionBarTheme);
			else
				activity.setTheme(R.style.DefaultActionBarTheme);
			break;
		case 1:
			activity.setTheme(android.R.style.Theme_Holo_Light);
			break;
		case 2:
			activity.setTheme(android.R.style.Theme_Holo);
			break;
		}
	}

	public static int getTheme(Context context) {
		return getIntPreference(context, "pref_theme", 0);
	}

	private static int getIntPreference(Context context, String key, int defaultValue) {
		return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue);
	}

}