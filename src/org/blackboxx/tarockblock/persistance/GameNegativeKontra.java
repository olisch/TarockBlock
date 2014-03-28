package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "game_negative_kontra")
public class GameNegativeKontra {
	@DatabaseField(columnName = "gnk_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private GameNegative gameNegative;

	@DatabaseField(canBeNull = false, foreign = true)
	private Player player;

	@DatabaseField(columnName = "gr_kontra", canBeNull = false)
	private Integer kontra;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GameNegative getGameNegative() {
		return gameNegative;
	}

	public void setGameNegative(GameNegative gameNegative) {
		this.gameNegative = gameNegative;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getKontra() {
		return kontra;
	}

	public void setKontra(Integer kontra) {
		this.kontra = kontra;
	}

}
