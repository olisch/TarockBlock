package org.blackboxx.tarockblock.persistance;

import org.blackboxx.tarockblock.enums.TrischakenQid;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "trischaken")
public class TableTrischaken {

	@DatabaseField(columnName = "tr_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "tr_qid", canBeNull = false)
	private Integer qid;

	@DatabaseField(columnName = "tr_value", canBeNull = false)
	private Integer value;

	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private TableTariffset tariffset;

	public TableTrischaken() {
	}

	public TableTrischaken(Integer tariffsetId, TrischakenQid trischakenQid, Integer value) {
		this.tariffset = new TableTariffset(tariffsetId);
		this.qid = trischakenQid.getId();
		this.value = value;
	}

	public TableTrischaken(TableTariffset tariffset, TrischakenQid trischakenQid, Integer value) {
		this.tariffset = tariffset;
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

	public TableTariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(TableTariffset tariffset) {
		this.tariffset = tariffset;
	}

}
