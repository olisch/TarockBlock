package org.blackboxx.tarockblock.enums;

public enum TrischakenQid2 {

	einfach(1, "Bürgermeister zahlt einfach"), doppelt(2, "Bürgermeister zahlt doppelt");

	private Integer id;

	private String label;

	private TrischakenQid2(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static TrischakenQid2 getValueById(Integer id) {
		for (int i = 0; i < TrischakenQid2.values().length; i++) {
			TrischakenQid2 array_element = TrischakenQid2.values()[i];
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
