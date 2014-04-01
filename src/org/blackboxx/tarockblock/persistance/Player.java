package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "player")
public class Player {

	@DatabaseField(columnName = "pl_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "pl_name", unique = true, canBeNull = false)
	private String name;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocPlayerSession> playerSessions;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocGameRegularPremium> assocGameRegularPremiums;

	public Player() {
	}

	public Player(String name) {
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

	public ForeignCollection<AssocPlayerSession> getPlayerSessions() {
		return playerSessions;
	}

	public void setPlayerSessions(ForeignCollection<AssocPlayerSession> playerSessions) {
		this.playerSessions = playerSessions;
	}

	public ForeignCollection<AssocGameRegularPremium> getAssocGameRegularPremiums() {
		return assocGameRegularPremiums;
	}

	public void setAssocGameRegularPremiums(ForeignCollection<AssocGameRegularPremium> assocGameRegularPremiums) {
		this.assocGameRegularPremiums = assocGameRegularPremiums;
	}

}
