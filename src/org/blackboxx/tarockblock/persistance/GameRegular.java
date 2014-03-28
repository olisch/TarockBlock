package org.blackboxx.tarockblock.persistance;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "game_regular")
public class GameRegular {

	@DatabaseField(columnName = "gr_id", generatedId = true)
	private Integer id;

	@DatabaseField(canBeNull = false, foreign = true)
	private Game game;

	@DatabaseField(canBeNull = false, foreign = true)
	private Player partner;

	@DatabaseField(columnName = "gr_kontra", canBeNull = false, defaultValue = "1")
	private Integer kontra;

	@DatabaseField(columnName = "gr_won", canBeNull = false)
	private Boolean won;

	@ForeignCollectionField(eager = false)
	ForeignCollection<AssocGameRegularPremium> assocGameRegularPremiums;

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

	public Player getPartner() {
		return partner;
	}

	public void setPartner(Player partner) {
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

	public ForeignCollection<AssocGameRegularPremium> getAssocGameRegularPremiums() {
		return assocGameRegularPremiums;
	}

	public void setAssocGameRegularPremiums(
			ForeignCollection<AssocGameRegularPremium> assocGameRegularPremiums) {
		this.assocGameRegularPremiums = assocGameRegularPremiums;
	}

}
