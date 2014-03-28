package org.blackboxx.tarockblock.enums;

public enum TariffType1 {

	Rufer(1, "Rufer"), Dreier(2, "Dreier"), Farben(3, "Farben"), Negativ(4, "Negativ");

	private Integer id;

	private String label;

	private TariffType1(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static TariffType1 getValueById(Integer id) {
		for (int i = 0; i < TariffType1.values().length; i++) {
			TariffType1 array_element = TariffType1.values()[i];
			if (array_element.getId().equals(id)) {
				return array_element;
			}
		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
