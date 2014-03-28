package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "session")
public class Session {

	@DatabaseField(columnName = "se_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "se_name", canBeNull = false)
	private String name;

	@DatabaseField(canBeNull = false, foreign = true)
	private Tariffset tariffset;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocPlayerSession> playerSessions;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocGameSession> gameSessions;

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

	public Tariffset getTariffset() {
		return tariffset;
	}

	public void setTariffset(Tariffset tariffset) {
		this.tariffset = tariffset;
	}

	public ForeignCollection<AssocPlayerSession> getPlayerSessions() {
		return playerSessions;
	}

	public void setPlayerSessions(
			ForeignCollection<AssocPlayerSession> playerSessions) {
		this.playerSessions = playerSessions;
	}

	public ForeignCollection<AssocGameSession> getGameSessions() {
		return gameSessions;
	}

	public void setGameSessions(ForeignCollection<AssocGameSession> gameSessions) {
		this.gameSessions = gameSessions;
	}

}
