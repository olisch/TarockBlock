package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assoc_trischaken_tariffset")
public class AssocTrischakenTariffset {

	@DatabaseField(columnName = "trts_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private Trischaken trischaken;

	@DatabaseField(canBeNull = false, foreign = true)
	private Tariffset tariffset;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Trischaken getTrischaken() {
		return trischaken;
	}

	public void setTrischaken(Trischaken trischaken) {
		this.trischaken = trischaken;
	}

	public Tariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(Tariffset tariffset) {
		this.tariffset = tariffset;
	}
}
