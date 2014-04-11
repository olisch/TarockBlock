package org.blackboxx.tarockblock.enums;

import org.blackboxx.tarockblock.R;

import android.content.res.Resources;

public class ArrayConverter {

	static public Integer getBeiId(Integer tableTariffsetBei, Resources resources) {
		for (int i = 0; i < resources.getStringArray(R.array.list_bei_values).length; i++) {
			String checkElement = resources.getStringArray(R.array.list_bei_values)[i];

			if (checkElement.equals(tableTariffsetBei.toString())) {
				return i;
			}
		}
		return null;
	}

	static public String getBeiText(Integer tableTariffsetBei, Resources resources) {
		return resources.getStringArray(R.array.list_bei)[getBeiId(tableTariffsetBei, resources)];
	}

	static public Integer getKontraId(Integer tableTariffsetKontra, Resources resources) {
		for (int i = 0; i < resources.getStringArray(R.array.list_kontra_values).length; i++) {
			String checkElement = resources.getStringArray(R.array.list_kontra_values)[i];

			if (checkElement.equals(tableTariffsetKontra.toString())) {
				return i;
			}
		}
		return null;
	}

	static public String getKontraText(Integer tableTariffsetKontra, Resources resources) {
		return resources.getStringArray(R.array.list_kontra)[getKontraId(tableTariffsetKontra, resources)];
	}

}
