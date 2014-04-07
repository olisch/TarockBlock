package org.blackboxx.tarockblock.persistance;

import org.blackboxx.tarockblock.enums.Bei;
import org.blackboxx.tarockblock.enums.KontraMax;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tariffset")
public class TableTariffset {

	@DatabaseField(columnName = "ts_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ts_name", unique = true, canBeNull = false)
	private String name;

	@DatabaseField(columnName = "ts_bei", canBeNull = false, defaultValue = "2")
	private Integer bei;

	@DatabaseField(columnName = "ts_kontra", canBeNull = false, defaultValue = "8")
	private Integer kontra;

	@ForeignCollectionField(eager = true, columnName = "tariffs")
	ForeignCollection<TableTariff> tariffs;

	@ForeignCollectionField(eager = true)
	ForeignCollection<TablePremium> premiums;

	@ForeignCollectionField(eager = true)
	ForeignCollection<TableTrischaken> trischakens;

	public TableTariffset() {
	}

	/**
	 * just for init the id for foreng key assignments
	 * 
	 * @param id
	 */
	public TableTariffset(Integer id) {
		this.id = id;
	}

	public TableTariffset(String name, Bei bei, KontraMax kontra) {
		this.name = name;
		this.bei = bei.getId();
		this.kontra = kontra.getId();
	}

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

	public ForeignCollection<TableTariff> getTariffs() {
		return tariffs;
	}

	public void setTariffs(ForeignCollection<TableTariff> tariffs) {
		this.tariffs = tariffs;
	}

	public ForeignCollection<TablePremium> getPremiums() {
		return premiums;
	}

	public void setPremiums(ForeignCollection<TablePremium> premiums) {
		this.premiums = premiums;
	}

	public ForeignCollection<TableTrischaken> getTrischakens() {
		return trischakens;
	}

	public void setTrischakens(ForeignCollection<TableTrischaken> trischakens) {
		this.trischakens = trischakens;
	}

}