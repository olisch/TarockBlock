package org.blackboxx.tarockblock.enums;

public enum TrischakenQid1 {

	einfach(1, "1 Punktesieger zahlt"), doppelt(2, "2 Punktesieger zahlen");

	private Integer id;

	private String label;

	private TrischakenQid1(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static TrischakenQid1 getValueById(Integer id) {
		for (int i = 0; i < TrischakenQid1.values().length; i++) {
			TrischakenQid1 array_element = TrischakenQid1.values()[i];
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
