package org.blackboxx.tarockblock.enums;

public enum GameTrischaken {

	Nothing(0, "-"), Jungfrau(1, "Jungfrau"), Bürgermeister(2, "Bürgermeister"), Valat(3, "Valat"), Punktesieger(4, "Punktesieger");

	private Integer id;

	private String label;

	private GameTrischaken(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static GameTrischaken getValueById(Integer id) {
		for (int i = 0; i < GameTrischaken.values().length; i++) {
			GameTrischaken array_element = GameTrischaken.values()[i];
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
