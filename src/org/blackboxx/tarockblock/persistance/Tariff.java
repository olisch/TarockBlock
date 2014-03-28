package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tariff")
public class Tariff {

	@DatabaseField(columnName = "ta_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ta_name", unique = true, canBeNull = false)
	private String name;

	@DatabaseField(columnName = "ta_type1", canBeNull = false)
	private Integer type1;

	@DatabaseField(columnName = "ta_type2", canBeNull = false)
	private Integer type2;

	@DatabaseField(columnName = "ts_value", canBeNull = false)
	private Integer value;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocTariffTariffset> assocTariffTariffset;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType1() {
		return type1;
	}

	public void setType1(Integer type1) {
		this.type1 = type1;
	}

	public Integer getType2() {
		return type2;
	}

	public void setType2(Integer type2) {
		this.type2 = type2;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public ForeignCollection<AssocTariffTariffset> getAssocTariffTariffset() {
		return assocTariffTariffset;
	}

	public void setAssocTariffTariffset(
			ForeignCollection<AssocTariffTariffset> assocTariffTariffset) {
		this.assocTariffTariffset = assocTariffTariffset;
	}

}