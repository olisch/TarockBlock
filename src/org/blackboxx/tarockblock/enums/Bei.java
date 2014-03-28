package org.blackboxx.tarockblock.enums;

public enum Bei {

	Keine(1, "Keine Negativspiele Bei erlaubt"), NurPiccolo(2,
			"Nur Piccolo/Zwiccolo Bei erlaubt"), NurBettler(3,
			"Nur Bettler Bei erlaubt"), NurGleichwertige(4,
			"Nur gleichwertige Spiele Bei erlaubt"), Alle(5,
			"Alle Negativspiele Bei möglich");

	private Integer id;

	private String label;

	private Bei(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static Bei getValueById(Integer id) {
		for (int i = 0; i < Bei.values().length; i++) {
			Bei array_element = Bei.values()[i];
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
