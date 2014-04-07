package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "player")
public class TablePlayer {

	@DatabaseField(columnName = "pl_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "pl_name", unique = true, canBeNull = false)
	private String name;

	@ForeignCollectionField(eager = false)
	ForeignCollection<TableAssocPlayerSession> playerSessions;

	@ForeignCollectionField(eager = false)
	ForeignCollection<TableAssocGameRegularPremium> assocGameRegularPremiums;

	public TablePlayer() {
	}

	public TablePlayer(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
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

	public ForeignCollection<TableAssocPlayerSession> getPlayerSessions() {
		return playerSessions;
	}

	public void setPlayerSessions(ForeignCollection<TableAssocPlayerSession> playerSessions) {
		this.playerSessions = playerSessions;
	}

	public ForeignCollection<TableAssocGameRegularPremium> getAssocGameRegularPremiums() {
		return assocGameRegularPremiums;
	}

	public void setAssocGameRegularPremiums(ForeignCollection<TableAssocGameRegularPremium> assocGameRegularPremiums) {
		this.assocGameRegularPremiums = assocGameRegularPremiums;
	}

}
