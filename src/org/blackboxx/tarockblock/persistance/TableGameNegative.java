package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "game_negative")
public class TableGameNegative {

	@DatabaseField(columnName = "gn_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableGame game;

	@DatabaseField(canBeNull = false, foreign = true)
	private TablePlayer player;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableTariff tariff;

	@DatabaseField(columnName = "gn_won", canBeNull = false)
	private Boolean won;

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

	public Boolean getWon() {
		return won;
	}

	public void setWon(Boolean won) {
		this.won = won;
	}

}
