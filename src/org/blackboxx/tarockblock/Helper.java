package org.blackboxx.tarockblock;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Helper {

	/** Set the theme of the activity, according to the configuration. */
	public static void onActivitySetPrefTheme(Activity activity, int usertheme, int activityId) {
		switch (usertheme) {
		default:
		case 0:
			if (activityId == 1)
				activity.setTheme(R.style.SessionTheme);
			else if (activityId == 2)
				activity.setTheme(R.style.GameTheme);
			else if (activityId == 3)
				activity.setTheme(R.style.PlayerTheme);
			else if (activityId == 4)
				activity.setTheme(R.style.TariffsetTheme);
			else if (activityId == 5)
				activity.setTheme(R.style.InfoTheme);
			else if (activityId == 6)
				activity.setTheme(R.style.ImExportTheme);
			else
				activity.setTheme(R.style.DefaultTheme);
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

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST);
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	public static void setListViewSize(ListView myListView) {
		ListAdapter myListAdapter = myListView.getAdapter();
		if (myListAdapter == null) {
			// do nothing return null
			return;
		}
		// set listAdapter in loop for getting final size
		int totalHeight = 0;
		for (int size = 0; size < myListAdapter.getCount(); size++) {
			View listItem = myListAdapter.getView(size, null, myListView);
			if (listItem != null) {
				listItem.measure(0, 0);
				totalHeight += listItem.getMeasuredHeight();
			}
		}
		// setting listview item in adapter
		ViewGroup.LayoutParams params = myListView.getLayoutParams();
		if (params != null) {
			params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
			myListView.setLayoutParams(params);
			// print height of adapter on log
		}
	}
}