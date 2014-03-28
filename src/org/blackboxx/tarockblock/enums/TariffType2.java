package org.blackboxx.tarockblock.enums;

public enum TariffType2 {

	Kontra(1, ""), NurPiccolo(2, ""), NurBettler(3, ""), NurGleichwertige(4, ""), Alle(5, "");

	private Integer id;

	private String label;

	private TariffType2(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static TariffType2 getValueById(Integer id) {
		for (int i = 0; i < TariffType2.values().length; i++) {
			TariffType2 array_element = TariffType2.values()[i];
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
