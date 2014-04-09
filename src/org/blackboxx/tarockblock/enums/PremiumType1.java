package org.blackboxx.tarockblock.enums;

public enum PremiumType1 {

	Tarock(1, "Tarock"), Farbe(2, "Farbe"), PunkteStiche(3, "Punkte/Stiche");

	private Integer id;

	private String label;

	private PremiumType1(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static PremiumType1 getValueById(Integer id) {
		for (int i = 0; i < PremiumType1.values().length; i++) {
			PremiumType1 array_element = PremiumType1.values()[i];
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
