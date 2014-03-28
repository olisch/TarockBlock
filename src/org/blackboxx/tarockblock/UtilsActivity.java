package org.blackboxx.tarockblock;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;

public class UtilsActivity {
	
     /** Set the theme of the activity, according to the configuration. */
     public static void onActivitySetPrefTheme(Activity activity, int usertheme) {
	   	 switch (usertheme) {
	     default:
	     case 0:
	         activity.setTheme(android.R.style.Theme_Holo_Light);
	         break;
	     case 1:
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