package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "assoc_game_regular_premium")
public class TableAssocGameRegularPremium {

	@DatabaseField(columnName = "grpr_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableGameRegular gameRegular;

	@DatabaseField(canBeNull = false, foreign = true)
	private TablePlayer player;

	@DatabaseField(canBeNull = false, foreign = true)
	private TablePremium premium;

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

	public TableGameRegular getGameRegular() {
		return gameRegular;
	}

	public void setGameRegular(TableGameRegular gameRegular) {
		this.gameRegular = gameRegular;
	}

	public TablePlayer getPlayer() {
		return player;
	}

	public void setPlayer(TablePlayer player) {
		this.player = player;
	}

	public TablePremium getPremium() {
		return premium;
	}

	public void setPremium(TablePremium premium) {
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
