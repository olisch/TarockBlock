package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assoc_tariff_tariffset")
public class AssocTariffTariffset {

	@DatabaseField(columnName = "tats_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private Tariff tariff;

	@DatabaseField(canBeNull = false, foreign = true)
	private Tariffset tariffset;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}

	public Tariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(Tariffset tariffset) {
		this.tariffset = tariffset;
	}

}
