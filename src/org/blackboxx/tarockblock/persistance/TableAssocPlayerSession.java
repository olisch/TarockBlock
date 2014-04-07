package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assoc_player_session")
public class TableAssocPlayerSession {

	@DatabaseField(columnName = "plse_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private TablePlayer player;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableSession session;

	@DatabaseField(columnName = "plse_points", canBeNull = false, defaultValue = "0")
	private Integer points;

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

	public TableSession getSession() {
		return session;
	}

	public void setSession(TableSession session) {
		this.session = session;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
