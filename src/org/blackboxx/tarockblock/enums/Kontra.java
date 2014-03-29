package org.blackboxx.tarockblock.enums;

public enum Kontra {

	Kontra2(2, "Kontra (2x)"), Rekontra4(4, "Rekontra (4x)"), Subkontra8(8, "Subkontra (8x)"), Mortkontra16(16, "Mortkontra (16x)");

	private Integer id;

	private String label;

	private Kontra(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static Kontra getValueById(Integer id) {
		for (int i = 0; i < Kontra.values().length; i++) {
			Kontra array_element = Kontra.values()[i];
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
