package org.blackboxx.tarockblock.persistance;

import org.blackboxx.tarockblock.enums.Bei;
import org.blackboxx.tarockblock.enums.KontraMax;

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

	@ForeignCollectionField(eager = true, columnName = "tariffs")
	ForeignCollection<Tariff> tariffs;

	@ForeignCollectionField(eager = true)
	ForeignCollection<Premium> premiums;

	@ForeignCollectionField(eager = true)
	ForeignCollection<Trischaken> trischakens;

	public Tariffset() {
	}

	/**
	 * just for init the id for foreng key assignments
	 * 
	 * @param id
	 */
	public Tariffset(Integer id) {
		this.id = id;
	}

	public Tariffset(String name, Bei bei, KontraMax kontra) {
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

	public ForeignCollection<Tariff> getTariffs() {
		return tariffs;
	}

	public void setTariffs(ForeignCollection<Tariff> tariffs) {
		this.tariffs = tariffs;
	}

	public ForeignCollection<Premium> getPremiums() {
		return premiums;
	}

	public void setPremiums(ForeignCollection<Premium> premiums) {
		this.premiums = premiums;
	}

	public ForeignCollection<Trischaken> getTrischakens() {
		return trischakens;
	}

	public void setTrischakens(ForeignCollection<Trischaken> trischakens) {
		this.trischakens = trischakens;
	}

}