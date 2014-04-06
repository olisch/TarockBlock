package org.blackboxx.tarockblock.enums;

public enum TrischakenQid3 {

	einfach(1, "Vorhand zahlt einfach"), doppelt(2, "Vorhand zahlt doppelt");

	private Integer id;

	private String label;

	private TrischakenQid3(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static TrischakenQid3 getValueById(Integer id) {
		for (int i = 0; i < TrischakenQid3.values().length; i++) {
			TrischakenQid3 array_element = TrischakenQid3.values()[i];
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
