package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tariffset")
public class Tariffset {

	@DatabaseField(columnName = "ts_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ts_name", unique = true, canBeNull = false)
	private String name;

	@DatabaseField(columnName = "ts_bei", canBeNull = false, defaultValue = "2")
	private Integer bei;

	@DatabaseField(columnName = "ts_kontra", canBeNull = false, defaultValue = "8")
	private Integer kontra;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocTariffTariffset> assocTariffTariffset;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocPremiumTariffset> assocPremiumTariffsets;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocTrischakenTariffset> assocTrischakenTariffsets;

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

	public Integer getBei() {
		return bei;
	}

	public void setBei(Integer bei) {
		this.bei = bei;
	}

	public Integer getKontra() {
		return kontra;
	}

	public void setKontra(Integer kontra) {
		this.kontra = kontra;
	}

	public ForeignCollection<AssocTariffTariffset> getAssocTariffTariffset() {
		return assocTariffTariffset;
	}

	public void setAssocTariffTariffset(
			ForeignCollection<AssocTariffTariffset> assocTariffTariffset) {
		this.assocTariffTariffset = assocTariffTariffset;
	}

	public ForeignCollection<AssocPremiumTariffset> getAssocPremiumTariffsets() {
		return assocPremiumTariffsets;
	}

	public void setAssocPremiumTariffsets(
			ForeignCollection<AssocPremiumTariffset> assocPremiumTariffsets) {
		this.assocPremiumTariffsets = assocPremiumTariffsets;
	}

	public ForeignCollection<AssocTrischakenTariffset> getAssocTrischakenTariffsets() {
		return assocTrischakenTariffsets;
	}

	public void setAssocTrischakenTariffsets(
			ForeignCollection<AssocTrischakenTariffset> assocTrischakenTariffsets) {
		this.assocTrischakenTariffsets = assocTrischakenTariffsets;
	}

}
