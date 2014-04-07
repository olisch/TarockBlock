package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "game_regular")
public class TableGameRegular {

	@DatabaseField(columnName = "gr_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private TableGame game;

	@DatabaseField(canBeNull = false, foreign = true)
	private TablePlayer partner;

	@DatabaseField(columnName = "gr_kontra", canBeNull = false, defaultValue = "1")
	private Integer kontra;

	@DatabaseField(columnName = "gr_won", canBeNull = false)
	private Boolean won;

	@ForeignCollectionField(eager = false)
	ForeignCollection<TableAssocGameRegularPremium> assocGameRegularPremiums;

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

	public TablePlayer getPartner() {
		return partner;
	}

	public void setPartner(TablePlayer partner) {
		this.partner = partner;
	}

	public Integer getKontra() {
		return kontra;
	}

	public void setKontra(Integer kontra) {
		this.kontra = kontra;
	}

	public Boolean getWon() {
		return won;
	}

	public void setWon(Boolean won) {
		this.won = won;
	}

	public ForeignCollection<TableAssocGameRegularPremium> getAssocGameRegularPremiums() {
		return assocGameRegularPremiums;
	}

	public void setAssocGameRegularPremiums(
			ForeignCollection<TableAssocGameRegularPremium> assocGameRegularPremiums) {
		this.assocGameRegularPremiums = assocGameRegularPremiums;
	}

}
