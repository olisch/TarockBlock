package org.blackboxx.tarockblock.persistance;

import org.blackboxx.tarockblock.enums.TrischakenQid;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "trischaken")
public class Trischaken {

	@DatabaseField(columnName = "tr_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private Tariffset tariffset;

	@DatabaseField(columnName = "tr_qid", canBeNull = false)
	private Integer qid;

	@DatabaseField(columnName = "tr_value", canBeNull = false)
	private Integer value;

	public Trischaken() {
	}

	public Trischaken(Integer tariffsetId, TrischakenQid trischakenQid, Integer value) {
		this.tariffset = new Tariffset(tariffsetId);
		this.qid = trischakenQid.getId();
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Tariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(Tariffset tariffset) {
		this.tariffset = tariffset;
	}

}
