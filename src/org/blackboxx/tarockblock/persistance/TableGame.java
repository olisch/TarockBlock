package org.blackboxx.tarockblock.persistance;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "game")
public class TableGame {

	@DatabaseField(columnName = "ga_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private TablePlayer player;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableTariff tariff;

	@DatabaseField(columnName = "ga_doublegames", canBeNull = false, defaultValue = "0")
	private Integer doublegames;

	@DatabaseField(columnName = "ga_creation", canBeNull = false)
	private Date creation;

	@ForeignCollectionField(eager = false)
	ForeignCollection<TableAssocGameSession> assocGameSessions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TablePlayer getPlayer() {
		return player;
	}

	public void setPlayer(TablePlayer player) {
		this.player = player;
	}

	public TableTariff getTariff() {
		return tariff;
	}

	public void setTariff(TableTariff tariff) {
		this.tariff = tariff;
	}

	public Integer getDoublegames() {
		return doublegames;
	}

	public void setDoublegames(Integer doublegames) {
		this.doublegames = doublegames;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public ForeignCollection<TableAssocGameSession> getAssocGameSessions() {
		return assocGameSessions;
	}

	public void setAssocGameSessions(
			ForeignCollection<TableAssocGameSession> assocGameSessions) {
		this.assocGameSessions = assocGameSessions;
	}

}
