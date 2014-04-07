package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assoc_game_session")
public class TableAssocGameSession {
	@DatabaseField(columnName = "gase_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableGame game;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableSession session;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TableGame getGame() {
		return game;
	}

	public void setGame(TableGame game) {
		this.game = game;
	}

	public TableSession getSession() {
		return session;
	}

	public void setSession(TableSession session) {
		this.session = session;
	}
}
