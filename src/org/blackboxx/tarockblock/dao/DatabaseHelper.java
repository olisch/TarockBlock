package org.blackboxx.tarockblock.dao;

import java.sql.SQLException;

import org.blackboxx.tarockblock.enums.Bei;
import org.blackboxx.tarockblock.enums.Kontra;
import org.blackboxx.tarockblock.enums.TariffType1;
import org.blackboxx.tarockblock.enums.TariffType2;
import org.blackboxx.tarockblock.persistance.AssocGameRegularPremium;
import org.blackboxx.tarockblock.persistance.AssocGameSession;
import org.blackboxx.tarockblock.persistance.AssocPlayerSession;
import org.blackboxx.tarockblock.persistance.Game;
import org.blackboxx.tarockblock.persistance.GameNegative;
import org.blackboxx.tarockblock.persistance.GameNegativeKontra;
import org.blackboxx.tarockblock.persistance.GameRegular;
import org.blackboxx.tarockblock.persistance.GameTrischaken;
import org.blackboxx.tarockblock.persistance.Player;
import org.blackboxx.tarockblock.persistance.Premium;
import org.blackboxx.tarockblock.persistance.Session;
import org.blackboxx.tarockblock.persistance.Tariff;
import org.blackboxx.tarockblock.persistance.Tariffset;
import org.blackboxx.tarockblock.persistance.Trischaken;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "TarokBlock.db";
	private static final int DATABASE_VERSION = 1;
	private final String LOG_NAME = getClass().getName();

	private Dao<AssocGameRegularPremium, Integer> assocGameRegularPremiumDao;
	private Dao<AssocGameSession, Integer> assocGameSessionDao;
	private Dao<AssocPlayerSession, Integer> assocPlayerSessionDao;
	private Dao<Game, Integer> gameDao;
	private Dao<GameNegative, Integer> gameNegativeDao;
	private Dao<GameNegativeKontra, Integer> gameNegativeKontraDao;
	private Dao<GameRegular, Integer> gameRegularDao;
	private Dao<GameTrischaken, Integer> gameTrischakenDao;
	private Dao<Player, Integer> playerDao;
	private Dao<Premium, Integer> premiumDao;
	private Dao<Session, Integer> sessionDao;
	private Dao<Tariff, Integer> tariffDao;
	private Dao<Tariffset, Integer> tariffsetDao;
	private Dao<Trischaken, Integer> trischakenDao;

	public DatabaseHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion, int configFileId) {
		super(context, databaseName, factory, databaseVersion, configFileId);
	}

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource, Player.class);
			TableUtils.createTable(connectionSource, Tariffset.class);
			TableUtils.createTable(connectionSource, Tariff.class);
			TableUtils.createTable(connectionSource, Trischaken.class);
			TableUtils.createTable(connectionSource, Premium.class);
			TableUtils.createTable(connectionSource, AssocGameRegularPremium.class);
			TableUtils.createTable(connectionSource, AssocGameSession.class);
			TableUtils.createTable(connectionSource, AssocPlayerSession.class);
			TableUtils.createTable(connectionSource, Game.class);
			TableUtils.createTable(connectionSource, GameNegative.class);
			TableUtils.createTable(connectionSource, GameNegativeKontra.class);
			TableUtils.createTable(connectionSource, GameRegular.class);
			TableUtils.createTable(connectionSource, GameTrischaken.class);
			TableUtils.createTable(connectionSource, Session.class);

			initDBWithDefaultValues();

		} catch (SQLException e) {
			Log.e(LOG_NAME, "Could not create tables!", e);
		}

	}

	private void initDBWithDefaultValues() throws SQLException {
		// String player_insert =
		// "INSERT INTO player VALUES (1,'Oliver'),(2,'Dani'),(3,'Jürgen')";
		//
		// playerDao.updateRaw(player_insert);

		getPlayerDao().create(new Player("Oliver"));
		getPlayerDao().create(new Player("Dani"));
		getPlayerDao().create(new Player("Jürgen"));

		int tariffsetTarockBlockId = getTariffsetDao().create(new Tariffset("TarockBlock", Bei.NurPiccolo, Kontra.Subkontra8));
		getTariffDao().create(new Tariff("Trischaken", TariffType1.Negativ, TariffType2.Kontra, 10, tariffsetTarockBlockId));
		// String tariffset_insert =
		// "INSERT INTO tariffset VALUES (1,'TarockBlock',2,8),(2,'Wiener Zeitung Cup',1,8),(3,'Raiffeisencup/Hausruckcup',1,8),(4,'Tiroler Tarockcup',4,8),(5,'Steirischer Tarockcup',2,4)";
		// String tariff_insert =
		// "INSERT INTO tariff VALUES (1,1,'Trischaken',4,1,10),(2,1,'Königrufer',1,1,10),(3,1,'Sechserdreier',2,1,30),(4,1,'Piccolo',4,2,10),(5,1,'Zwiccolo',4,2,10),(6,1,'Solorufer',1,4,20),(7,1,'Besserrufer(+Vogel)',1,5,10),(8,1,'Bettler',4,3,30),(9,1,'Farbendreier',3,0,40),(10,1,'Dreier',2,0,40),(11,1,'Piccolo ouvert',4,2,50),(12,1,'Zwiccolo ouvert',4,2,50),(13,1,'Bettler ouvert',4,3,70),(14,1,'Farbensolo',3,4,80),(15,1,'Solodreier',2,4,80),(16,2,'Trischaken',4,1,10),(17,2,'Rufer',1,1,10),(18,2,'Sechserdreier',2,1,40),(19,2,'Piccolo',4,2,20),(20,2,'Zwiccolo',4,2,20),(21,2,'Solorufer',1,4,20),(22,2,'Pagatrufer',1,5,30),(23,2,'Bettel',4,3,40),(24,2,'Uhurufer',1,5,50),(25,2,'Farbendreier',3,0,50),(26,2,'Dreier',2,0,50),(27,2,'Piccolo ouvert',4,2,60),(28,2,'Zwiccolo ouvert',4,2,60),(29,2,'Kakadurufer',1,5,70),(30,2,'Bettel ouvert',4,3,80),(31,2,'Quapilrufer',1,5,90),(32,2,'Farbensolo',3,4,100),(33,2,'Solodreier',2,4,100),(34,3,'Trischaken',4,1,10),(35,3,'Rufer',1,1,10),(36,3,'Sechserdreier',2,1,40),(37,3,'Piccolo',4,2,20),(38,3,'Bettel',4,3,20),(39,3,'Solorufer',1,4,20),(40,3,'A-Rufer (+Vogel)',1,5,10),(41,3,'Dreier',2,0,40),(42,3,'Farbensolo',3,4,50),(43,3,'Piccolo ouvert',4,2,60),(44,3,'Bettel ouvert',4,3,70),(45,3,'Solodreier',2,4,80),(46,4,'Rufer',1,1,10),(47,4,'Trischaken',4,1,20),(48,4,'Sechserdreier',2,1,40),(49,4,'Piccolo',4,2,20),(50,4,'Solorufer',1,4,20),(51,4,'Besserrufer (+Vogel)',1,5,20),(52,4,'Bettel',4,3,40),(53,4,'Farbendreier',3,0,50),(54,4,'Dreier',2,0,50),(55,4,'Piccolo ouvert',4,2,70),(56,4,'Zwiccolo ouvert',4,2,70),(57,4,'Bettel ouvert',4,3,80),(58,4,'Farbensolo',3,4,100),(59,4,'Solodreier',2,4,100),(60,5,'Trischaken',4,1,50),(61,5,'Rufer',1,1,10),(62,5,'Sechserdreier',2,1,50),(63,5,'Piccolo',4,2,20),(64,5,'Zwiccolo',4,2,20),(65,5,'Triccolo',4,2,20),(66,5,'Solorufer',1,4,20),(67,5,'Pagatrufer',1,5,30),(68,5,'Bettel',4,3,40),(69,5,'Uhurufer',1,5,50),(70,5,'Farbendreier',3,0,40),(71,5,'Dreier',2,0,50),(72,5,'Piccolo ouvert',4,2,40),(73,5,'Kakadurufer',1,5,70),(74,5,'Bettel ouvert',4,3,80),(75,5,'Quapilrufer',1,5,90),(76,5,'Farbensolo',3,4,80),(77,5,'Solodreier',2,4,100)";
		// String premium_insert =
		// "INSERT INTO premium VALUES (1,1,'Pagat',1,0,'10','20'),(2,1,'Uhu',1,0,'20','40'),(3,1,'Kakadu',1,0,'30','60'),(4,1,'König ultimo',2,0,'10','20'),(5,1,'Mondfang',1,1,'10','20'),(6,1,'Trull',1,0,'10','20'),(7,1,'4 Könige',2,0,'10','20'),(8,1,'1. Sack (=45/2)',3,0,'10','20'),(9,1,'2. Sack (=55/2)',3,0,'20','40'),(10,1,'Valat',3,0,'x4','x8'),(11,2,'Pagat',1,0,'10','20'),(12,2,'Uhu',1,0,'20','40'),(13,2,'Kakadu',1,0,'30','60'),(14,2,'Quapil',1,0,'40','80'),(15,2,'König ultimo',2,0,'10','20'),(16,2,'Trull',1,0,'10','20'),(17,2,'4 Könige',2,0,'10','20'),(18,2,'Valat',3,0,'x4','x8'),(19,3,'Pagat',1,0,'10','20'),(20,3,'Uhu',1,0,'20','40'),(21,3,'Kakadu',1,0,'30','60'),(22,3,'Quapil',1,0,'40','80'),(23,3,'König ultimo',2,0,'10','20'),(24,3,'Trull',1,0,'10','20'),(25,3,'4 Könige',2,0,'10','20'),(26,3,'Valat',3,0,'x4','x8'),(27,4,'Pagat',1,0,'10','20'),(28,4,'Uhu',1,0,'20','40'),(29,4,'Kakadu',1,0,'30','60'),(30,4,'Quapil',1,0,'40','80'),(31,4,'König ultimo',2,0,'10','20'),(32,4,'Mondfang',1,1,'10','20'),(33,4,'Trull',1,0,'10','20'),(34,4,'4 Könige',2,0,'10','20'),(35,4,'1. Sack (=45/2)',3,0,'0','20'),(36,4,'2. Sack (=55/2)',3,0,'0','20'),(37,4,'Valat',3,0,'150','300'),(38,5,'Pagat',1,0,'10','20'),(39,5,'Uhu',1,0,'20','40'),(40,5,'Kakadu',1,0,'30','60'),(41,5,'Quapil',1,0,'40','80'),(42,5,'König ultimo',2,0,'10','20'),(43,5,'Trull',1,0,'10','20'),(44,5,'4 Könige',2,0,'10','20'),(45,5,'Valat',3,0,'x4','x8')";
		// String trischaken_insert =
		// "INSERT INTO trischaken VALUES (1,1,1,1),(2,1,2,2),(3,1,3,2),(4,2,1,1),(5,2,2,2),(6,2,3,2),(7,3,1,1),(8,3,2,2),(9,3,3,2),(10,4,1,1),(11,4,2,2),(12,4,3,2),(13,5,1,1),(14,5,2,2),(15,5,3,2)";

		// String tariffset_insert =
		// "INSERT INTO tariffset VALUES (1,'TarockBlock',2,8),(2,'Wiener Zeitung Cup',1,8),(3,'Raiffeisencup/Hausruckcup',1,8),(4,'Tiroler Tarockcup',4,8),(5,'Steirischer Tarockcup',2,4)";
		// String tariff_insert =
		// "INSERT INTO tariff VALUES (1,1,'Trischaken',4,1,10),(2,1,'Königrufer',1,1,10),(3,1,'Sechserdreier',2,1,30),(4,1,'Piccolo',4,2,10),(5,1,'Zwiccolo',4,2,10),(6,1,'Solorufer',1,4,20),(7,1,'Besserrufer(+Vogel)',1,5,10),(8,1,'Bettler',4,3,30),(9,1,'Farbendreier',3,0,40),(10,1,'Dreier',2,0,40),(11,1,'Piccolo ouvert',4,2,50),(12,1,'Zwiccolo ouvert',4,2,50),(13,1,'Bettler ouvert',4,3,70),(14,1,'Farbensolo',3,4,80),(15,1,'Solodreier',2,4,80),(16,2,'Trischaken',4,1,10),(17,2,'Rufer',1,1,10),(18,2,'Sechserdreier',2,1,40),(19,2,'Piccolo',4,2,20),(20,2,'Zwiccolo',4,2,20),(21,2,'Solorufer',1,4,20),(22,2,'Pagatrufer',1,5,30),(23,2,'Bettel',4,3,40),(24,2,'Uhurufer',1,5,50),(25,2,'Farbendreier',3,0,50),(26,2,'Dreier',2,0,50),(27,2,'Piccolo ouvert',4,2,60),(28,2,'Zwiccolo ouvert',4,2,60),(29,2,'Kakadurufer',1,5,70),(30,2,'Bettel ouvert',4,3,80),(31,2,'Quapilrufer',1,5,90),(32,2,'Farbensolo',3,4,100),(33,2,'Solodreier',2,4,100),(34,3,'Trischaken',4,1,10),(35,3,'Rufer',1,1,10),(36,3,'Sechserdreier',2,1,40),(37,3,'Piccolo',4,2,20),(38,3,'Bettel',4,3,20),(39,3,'Solorufer',1,4,20),(40,3,'A-Rufer (+Vogel)',1,5,10),(41,3,'Dreier',2,0,40),(42,3,'Farbensolo',3,4,50),(43,3,'Piccolo ouvert',4,2,60),(44,3,'Bettel ouvert',4,3,70),(45,3,'Solodreier',2,4,80),(46,4,'Rufer',1,1,10),(47,4,'Trischaken',4,1,20),(48,4,'Sechserdreier',2,1,40),(49,4,'Piccolo',4,2,20),(50,4,'Solorufer',1,4,20),(51,4,'Besserrufer (+Vogel)',1,5,20),(52,4,'Bettel',4,3,40),(53,4,'Farbendreier',3,0,50),(54,4,'Dreier',2,0,50),(55,4,'Piccolo ouvert',4,2,70),(56,4,'Zwiccolo ouvert',4,2,70),(57,4,'Bettel ouvert',4,3,80),(58,4,'Farbensolo',3,4,100),(59,4,'Solodreier',2,4,100),(60,5,'Trischaken',4,1,50),(61,5,'Rufer',1,1,10),(62,5,'Sechserdreier',2,1,50),(63,5,'Piccolo',4,2,20),(64,5,'Zwiccolo',4,2,20),(65,5,'Triccolo',4,2,20),(66,5,'Solorufer',1,4,20),(67,5,'Pagatrufer',1,5,30),(68,5,'Bettel',4,3,40),(69,5,'Uhurufer',1,5,50),(70,5,'Farbendreier',3,0,40),(71,5,'Dreier',2,0,50),(72,5,'Piccolo ouvert',4,2,40),(73,5,'Kakadurufer',1,5,70),(74,5,'Bettel ouvert',4,3,80),(75,5,'Quapilrufer',1,5,90),(76,5,'Farbensolo',3,4,80),(77,5,'Solodreier',2,4,100)";
		// String premium_insert =
		// "INSERT INTO premium VALUES (1,1,'Pagat',1,0,10,20),(2,1,'Uhu',1,0,20,40),(3,1,'Kakadu',1,0,30,60),(4,1,'König ultimo',2,0,10,20),(5,1,'Mondfang',1,1,10,20),(6,1,'Trull',1,0,10,20),(7,1,'4 Könige',2,0,10,20),(8,1,'1. Sack (=45/2)',3,0,10,20),(9,1,'2. Sack (=55/2)',3,0,20,40),(10,1,'Valat',3,0,'x4','x8'),(11,2,'Pagat',1,0,10,20),(12,2,'Uhu',1,0,20,40),(13,2,'Kakadu',1,0,30,60),(14,2,'Quapil',1,0,40,80),(15,2,'König ultimo',2,0,10,20),(16,2,'Trull',1,0,10,20),(17,2,'4 Könige',2,0,10,20),(18,2,'Valat',3,0,'x4','x8'),(19,3,'Pagat',1,0,10,20),(20,3,'Uhu',1,0,20,40),(21,3,'Kakadu',1,0,30,60),(22,3,'Quapil',1,0,40,80),(23,3,'König ultimo',2,0,10,20),(24,3,'Trull',1,0,10,20),(25,3,'4 Könige',2,0,10,20),(26,3,'Valat',3,0,'x4','x8'),(27,4,'Pagat',1,0,10,20),(28,4,'Uhu',1,0,20,40),(29,4,'Kakadu',1,0,30,60),(30,4,'Quapil',1,0,40,80),(31,4,'König ultimo',2,0,10,20),(32,4,'Mondfang',1,1,10,20),(33,4,'Trull',1,0,10,20),(34,4,'4 Könige',2,0,10,20),(35,4,'1. Sack (=45/2)',3,0,0,20),(36,4,'2. Sack (=55/2)',3,0,0,20),(37,4,'Valat',3,0,150,300),(38,5,'Pagat',1,0,10,20),(39,5,'Uhu',1,0,20,40),(40,5,'Kakadu',1,0,30,60),(41,5,'Quapil',1,0,40,80),(42,5,'König ultimo',2,0,10,20),(43,5,'Trull',1,0,10,20),(44,5,'4 Könige',2,0,10,20),(45,5,'Valat',3,0,'x4','x8')";
		// String trischaken_insert =
		// "INSERT INTO trischaken VALUES (1,1,1,1),(2,1,2,2),(3,1,3,2),(4,2,1,1),(5,2,2,2),(6,2,3,2),(7,3,1,1),(8,3,2,2),(9,3,3,2),(10,4,1,1),(11,4,2,2),(12,4,3,2),(13,5,1,1),(14,5,2,2),(15,5,3,2)";

		// tariffsetDao.updateRaw(tariffset_insert);
		// tariffDao.updateRaw(tariff_insert);
		// premiumDao.updateRaw(premium_insert);
		// trischakenDao.updateRaw(trischaken_insert);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {

	}

	public Dao<AssocGameRegularPremium, Integer> getAssocGameRegularPremiumDao() throws SQLException {
		if (assocGameRegularPremiumDao == null) {
			assocGameRegularPremiumDao = getDao(AssocGameRegularPremium.class);
		}
		return assocGameRegularPremiumDao;
	}

	public Dao<AssocGameSession, Integer> getAssocGameSessionDao() throws SQLException {
		if (assocGameSessionDao == null) {
			assocGameSessionDao = getDao(AssocGameSession.class);
		}
		return assocGameSessionDao;
	}

	public Dao<AssocPlayerSession, Integer> getAssocPlayerSessionDao() throws SQLException {
		if (assocPlayerSessionDao == null) {
			assocPlayerSessionDao = getDao(AssocPlayerSession.class);
		}
		return assocPlayerSessionDao;
	}

	public Dao<Game, Integer> getGameDao() throws SQLException {
		if (gameDao == null) {
			gameDao = getDao(Game.class);
		}
		return gameDao;
	}

	public Dao<GameNegative, Integer> getGameNegativeDao() throws SQLException {
		if (gameNegativeDao == null) {
			gameNegativeDao = getDao(GameNegative.class);
		}
		return gameNegativeDao;
	}

	public Dao<GameNegativeKontra, Integer> getGameNegativeKontraDao() throws SQLException {
		if (gameNegativeKontraDao == null) {
			gameNegativeKontraDao = getDao(GameNegativeKontra.class);
		}
		return gameNegativeKontraDao;
	}

	public Dao<GameRegular, Integer> getGameRegularDao() throws SQLException {
		if (gameRegularDao == null) {
			gameRegularDao = getDao(GameRegular.class);
		}
		return gameRegularDao;
	}

	public Dao<GameTrischaken, Integer> getGameTrischakenDao() throws SQLException {
		if (gameTrischakenDao == null) {
			gameTrischakenDao = getDao(GameTrischaken.class);
		}
		return gameTrischakenDao;
	}

	public Dao<Player, Integer> getPlayerDao() throws SQLException {
		if (playerDao == null) {
			playerDao = getDao(Player.class);
		}
		return playerDao;
	}

	public Dao<Premium, Integer> getPremiumDao() throws SQLException {
		if (premiumDao == null) {
			premiumDao = getDao(Premium.class);
		}
		return premiumDao;
	}

	public Dao<Session, Integer> getSessionDao() throws SQLException {
		if (sessionDao == null) {
			sessionDao = getDao(Session.class);
		}
		return sessionDao;
	}

	public Dao<Tariff, Integer> getTariffDao() throws SQLException {
		if (tariffDao == null) {
			tariffDao = getDao(Tariff.class);
		}
		return tariffDao;
	}

	public Dao<Tariffset, Integer> getTariffsetDao() throws SQLException {
		if (tariffsetDao == null) {
			tariffsetDao = getDao(Tariffset.class);
		}
		return tariffsetDao;
	}

	public Dao<Trischaken, Integer> getTrischakenDao() throws SQLException {
		if (trischakenDao == null) {
			trischakenDao = getDao(Trischaken.class);
		}
		return trischakenDao;
	}

}
