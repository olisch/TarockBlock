package org.blackboxx.tarockblock.enums;

public enum PremiumType2 {

	Nothing(0, "-"), Mondfang(1, "Mondfang");

	private Integer id;

	private String label;

	private PremiumType2(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static PremiumType2 getValueById(Integer id) {
		for (int i = 0; i < PremiumType2.values().length; i++) {
			PremiumType2 array_element = PremiumType2.values()[i];
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
