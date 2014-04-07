package org.blackboxx.tarockblock.persistance;

import org.blackboxx.tarockblock.enums.PremiumType1;
import org.blackboxx.tarockblock.enums.PremiumType2;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "premium")
public class TablePremium {
	@DatabaseField(columnName = "pr_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "pr_order", canBeNull = false)
	private Integer order;

	@DatabaseField(columnName = "pr_name", canBeNull = false)
	private String name;

	@DatabaseField(columnName = "pr_type1", canBeNull = false)
	private Integer type1;

	@DatabaseField(columnName = "pr_type2", canBeNull = false)
	private Integer type2;

	@DatabaseField(columnName = "pr_value_silent", canBeNull = false)
	private String valueSilent;

	@DatabaseField(columnName = "pr_value_called", canBeNull = false)
	private String valueCalled;

	@DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private TableTariffset tariffset;

	@ForeignCollectionField(eager = false)
	ForeignCollection<TableAssocGameRegularPremium> assocGameRegularPremiums;

	public TablePremium() {
	}

	public TablePremium(Integer tariffsetId, Integer order, String name, PremiumType1 premiumType1, PremiumType2 premiumType2, String valueSilent, String valueCalled) {
		this.tariffset = new TableTariffset(tariffsetId);
		this.order = order;
		this.name = name;
		this.type1 = premiumType1.getId();
		this.type2 = premiumType2.getId();
		this.valueSilent = valueSilent;
		this.valueCalled = valueCalled;
	}

	public TablePremium(TableTariffset tariffset, Integer order, String name, PremiumType1 premiumType1, PremiumType2 premiumType2, String valueSilent, String valueCalled) {
		this.tariffset = tariffset;
		this.order = order;
		this.name = name;
		this.type1 = premiumType1.getId();
		this.type2 = premiumType2.getId();
		this.valueSilent = valueSilent;
		this.valueCalled = valueCalled;
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

	public String getValueSilent() {
		return valueSilent;
	}

	public void setValueSilent(String valueSilent) {
		this.valueSilent = valueSilent;
	}

	public String getValueCalled() {
		return valueCalled;
	}

	public void setValueCalled(String valueCalled) {
		this.valueCalled = valueCalled;
	}

	public ForeignCollection<TableAssocGameRegularPremium> getAssocGameRegularPremiums() {
		return assocGameRegularPremiums;
	}

	public void setAssocGameRegularPremiums(ForeignCollection<TableAssocGameRegularPremium> assocGameRegularPremiums) {
		this.assocGameRegularPremiums = assocGameRegularPremiums;
	}

	public TableTariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(TableTariffset tariffset) {
		this.tariffset = tariffset;
	}
}
