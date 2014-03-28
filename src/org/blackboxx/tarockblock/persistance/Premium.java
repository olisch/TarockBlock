package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "premium")
public class Premium {
	@DatabaseField(columnName = "pr_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "pr_name", unique = true, canBeNull = false)
	private String name;

	@DatabaseField(columnName = "pr_type1", canBeNull = false)
	private Integer type1;

	@DatabaseField(columnName = "pr_type2", canBeNull = false)
	private Integer type2;

	@DatabaseField(columnName = "pr_value_silent", canBeNull = false)
	private Integer valueSilent;

	@DatabaseField(columnName = "pr_value_called", canBeNull = false)
	private Integer valueCalled;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocPremiumTariffset> assocPremiumTariffsets;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocGameRegularPremium> assocGameRegularPremiums;

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

	public Integer getValueSilent() {
		return valueSilent;
	}

	public void setValueSilent(Integer valueSilent) {
		this.valueSilent = valueSilent;
	}

	public Integer getValueCalled() {
		return valueCalled;
	}

	public void setValueCalled(Integer valueCalled) {
		this.valueCalled = valueCalled;
	}

	public ForeignCollection<AssocPremiumTariffset> getAssocPremiumTariffsets() {
		return assocPremiumTariffsets;
	}

	public void setAssocPremiumTariffsets(
			ForeignCollection<AssocPremiumTariffset> assocPremiumTariffsets) {
		this.assocPremiumTariffsets = assocPremiumTariffsets;
	}

	public ForeignCollection<AssocGameRegularPremium> getAssocGameRegularPremiums() {
		return assocGameRegularPremiums;
	}

	public void setAssocGameRegularPremiums(
			ForeignCollection<AssocGameRegularPremium> assocGameRegularPremiums) {
		this.assocGameRegularPremiums = assocGameRegularPremiums;
	}
}