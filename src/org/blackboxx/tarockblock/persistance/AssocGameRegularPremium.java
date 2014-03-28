package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assoc_game_regular_premium")
public class AssocGameRegularPremium {

	@DatabaseField(columnName = "grpr_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private GameRegular gameRegular;

	@DatabaseField(canBeNull = false, foreign = true)
	private Player player;

	@DatabaseField(canBeNull = false, foreign = true)
	private Premium premium;

	@DatabaseField(columnName = "grpr_called", canBeNull = false)
	private Boolean called;

	@DatabaseField(columnName = "grpr_won", canBeNull = false)
	private Boolean won;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GameRegular getGameRegular() {
		return gameRegular;
	}

	public void setGameRegular(GameRegular gameRegular) {
		this.gameRegular = gameRegular;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Premium getPremium() {
		return premium;
	}

	public void setPremium(Premium premium) {
		this.premium = premium;
	}

	public Boolean getCalled() {
		return called;
	}

	public void setCalled(Boolean called) {
		this.called = called;
	}

	public Boolean getWon() {
		return won;
	}

	public void setWon(Boolean won) {
		this.won = won;
	}

}
