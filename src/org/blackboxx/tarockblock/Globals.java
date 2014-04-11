package org.blackboxx.tarockblock;

public class Globals {
	private static Globals instance;

	// Global variable
	private int themeId;

	private int defaultTariffsetId = 1;

	// Restrict the constructor from being instantiated
	private Globals() {
	}

	public void setThemeId(int d) {
		this.themeId = d;
	}

	public int getThemeId() {
		return this.themeId;
	}

	public static synchronized Globals getInstance() {
		if (instance == null) {
			instance = new Globals();
		}
		return instance;
	}

	public int getDefaultTariffsetId() {
		return defaultTariffsetId;
	}

	public void setDefaultTariffsetId(int defaultTariffsetId) {
		this.defaultTariffsetId = defaultTariffsetId;
	}
}