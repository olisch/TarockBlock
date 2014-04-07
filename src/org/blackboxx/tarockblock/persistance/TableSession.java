package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "session")
public class TableSession {

	@DatabaseField(columnName = "se_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "se_name", canBeNull = false)
	private String name;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableTariffset tariffset;

	@ForeignCollectionField(eager = false)
	ForeignCollection<TableAssocPlayerSession> playerSessions;

	@ForeignCollectionField(eager = false)
	ForeignCollection<TableAssocGameSession> gameSessions;

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

	public TableTariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(TableTariffset tariffset) {
		this.tariffset = tariffset;
	}

	public ForeignCollection<TableAssocPlayerSession> getPlayerSessions() {
		return playerSessions;
	}

	public void setPlayerSessions(
			ForeignCollection<TableAssocPlayerSession> playerSessions) {
		this.playerSessions = playerSessions;
	}

	public ForeignCollection<TableAssocGameSession> getGameSessions() {
		return gameSessions;
	}

	public void setGameSessions(ForeignCollection<TableAssocGameSession> gameSessions) {
		this.gameSessions = gameSessions;
	}

}
