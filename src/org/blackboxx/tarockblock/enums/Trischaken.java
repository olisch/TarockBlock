package org.blackboxx.tarockblock.enums;

public enum Trischaken {

	Punktesieger(1, "Wieviele Punktesieger zahlen?"), Bürgermeister(2, "Bürgermeister doppelt?"), Vorhand(3, "Vorhand doppelt?");

	private Integer id;

	private String label;

	private Trischaken(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static Trischaken getValueById(Integer id) {
		for (int i = 0; i < Trischaken.values().length; i++) {
			Trischaken array_element = Trischaken.values()[i];
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
