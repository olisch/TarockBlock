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

	@DatabaseField(columnName = "ts_tri1", canBeNull = false, defaultValue = "1")
	private Integer tri1;

	@DatabaseField(columnName = "ts_tri2", canBeNull = false, defaultValue = "2")
	private Integer tri2;

	@DatabaseField(columnName = "ts_tri3", canBeNull = false, defaultValue = "2")
	private Integer tri3;

	@ForeignCollectionField(eager = true, columnName = "tariffs")
	ForeignCollection<TableTariff> tariffs;

	@ForeignCollectionField(eager = true)
	ForeignCollection<TablePremium> premiums;

	public TableTariffset() {
		this.tri1 = 1;
		this.tri2 = 2;
		this.tri3 = 2;
		this.bei = 2;
		this.kontra = 8;
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
		this();
		this.name = name;
		this.bei = bei.getId();
		this.kontra = kontra.getId();
	}

	public TableTariffset(String name, Bei bei, KontraMax kontra, Integer tri1, Integer tri2, Integer tri3) {
		this(name, bei, kontra);
		this.tri1 = tri1;
		this.tri2 = tri2;
		this.tri3 = tri3;
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

	public Integer getTri1() {
		return tri1;
	}

	public void setTri1(Integer tri1) {
		this.tri1 = tri1;
	}

	public Integer getTri2() {
		return tri2;
	}

	public void setTri2(Integer tri2) {
		this.tri2 = tri2;
	}

	public Integer getTri3() {
		return tri3;
	}

	public void setTri3(Integer tri3) {
		this.tri3 = tri3;
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

}