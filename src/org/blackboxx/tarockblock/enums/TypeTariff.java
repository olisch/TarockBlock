package org.blackboxx.tarockblock.enums;

public enum TypeTariff {

	Nothing(0, "-"), Vorhand(1, "Vorhand"), PiccoloZwiccolo(2, "Piccolo/Zwiccolo"), Bettler(3, "Bettler"), Solo(4, "Solo"), Besserrufer(5, "Besserrufer"), Pagatrufer(
			6, "Pagatrufer"), Uhurufer(7, "Uhurufer"), Kakadurufer(8, "Kakadurufer"), Quapilrufer(9, "Quapilrufer");

	private Integer id;

	private String label;

	private TypeTariff(Integer id, String label) {
		this.setId(id);
		this.setLabel(label);
	}

	public static TypeTariff getValueById(Integer id) {
		for (int i = 0; i < TypeTariff.values().length; i++) {
			TypeTariff array_element = TypeTariff.values()[i];
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
