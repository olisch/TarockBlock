package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "game_negative")
public class GameNegative {

	@DatabaseField(columnName = "gn_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private Game game;

	@DatabaseField(canBeNull = false, foreign = true)
	private Player player;

	@DatabaseField(canBeNull = false, foreign = true)
	private Tariff tariff;

	@DatabaseField(columnName = "gn_won", canBeNull = false)
	private Boolean won;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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

	public Boolean getWon() {
		return won;
	}

	public void setWon(Boolean won) {
		this.won = won;
	}

}
