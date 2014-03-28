package org.blackboxx.tarockblock.persistance;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "game")
public class Game {

	@DatabaseField(columnName = "ga_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private Player player;

	@DatabaseField(canBeNull = false, foreign = true)
	private Tariff tariff;

	@DatabaseField(columnName = "ga_doublegames", canBeNull = false, defaultValue = "0")
	private Integer doublegames;

	@DatabaseField(columnName = "ga_creation", canBeNull = false)
	private Date creation;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocGameSession> assocGameSessions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
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

	public ForeignCollection<AssocGameSession> getAssocGameSessions() {
		return assocGameSessions;
	}

	public void setAssocGameSessions(
			ForeignCollection<AssocGameSession> assocGameSessions) {
		this.assocGameSessions = assocGameSessions;
	}

}
