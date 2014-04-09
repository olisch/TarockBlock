package org.blackboxx.tarockblock.enums;

public enum KontraMax {

	Kontra(2, "Kontra (x2)"), Rekontra(4, "Rekontra (x4)"), Subkontra(8, "Subkontra (x8)"), Mortkontra(16, "Mortkontra (x16)");

	private Integer id;

	private String label;

	private KontraMax(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static KontraMax getValueById(Integer id) {
		for (int i = 0; i < KontraMax.values().length; i++) {
			KontraMax array_element = KontraMax.values()[i];
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
