package org.blackboxx.tarockblock.persistance;

import org.blackboxx.tarockblock.enums.TariffType1;
import org.blackboxx.tarockblock.enums.TariffType2;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tariff")
public class TableTariff {

	@DatabaseField(columnName = "ta_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "ta_order", canBeNull = false)
	private Integer order;

	@DatabaseField(columnName = "ta_name", canBeNull = false)
	private String name;

	@DatabaseField(columnName = "ta_type1", canBeNull = false)
	private Integer type1;

	@DatabaseField(columnName = "ta_type2", canBeNull = false)
	private Integer type2;

	@DatabaseField(columnName = "ts_value", canBeNull = false)
	private Integer value;

	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private TableTariffset tariffset;

	public TableTariff() {
	}

	public TableTariff(Integer tariffsetId, Integer order, String name, TariffType1 tariffType1, TariffType2 tariffType2, Integer value) {
		this.tariffset = new TableTariffset(tariffsetId);
		this.order = order;
		this.name = name;
		this.type1 = tariffType1.getId();
		this.type2 = tariffType2.getId();
		this.value = value;
	}

	public TableTariff(TableTariffset tariffset, Integer order, String name, TariffType1 tariffType1, TariffType2 tariffType2, Integer value) {
		this.tariffset = tariffset;
		this.order = order;
		this.name = name;
		this.type1 = tariffType1.getId();
		this.type2 = tariffType2.getId();
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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

	public TableTariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(TableTariffset tariffset) {
		this.tariffset = tariffset;
	}

}