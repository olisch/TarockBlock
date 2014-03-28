package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "trischaken")
public class Trischaken {

	@DatabaseField(columnName = "tr_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "tr_value", canBeNull = false)
	private String value;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocTrischakenTariffset> assocTrischakenTariffsets;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ForeignCollection<AssocTrischakenTariffset> getAssocTrischakenTariffsets() {
		return assocTrischakenTariffsets;
	}

	public void setAssocTrischakenTariffsets(
			ForeignCollection<AssocTrischakenTariffset> assocTrischakenTariffsets) {
		this.assocTrischakenTariffsets = assocTrischakenTariffsets;
	}

}
