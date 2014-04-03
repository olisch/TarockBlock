package org.blackboxx.tarockblock.dao;

import java.sql.SQLException;

import org.blackboxx.tarockblock.enums.Bei;
import org.blackboxx.tarockblock.enums.KontraMax;
import org.blackboxx.tarockblock.enums.PremiumType1;
import org.blackboxx.tarockblock.enums.PremiumType2;
import org.blackboxx.tarockblock.enums.TariffType1;
import org.blackboxx.tarockblock.enums.TariffType2;
import org.blackboxx.tarockblock.enums.TrischakenQid;
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

		// this.connectionSource.getReadWriteConnection().setAutoCommit(true);

		getPlayerDao().create(new Player("Oliver"));
		getPlayerDao().create(new Player("Dani"));
		getPlayerDao().create(new Player("Jürgen"));

		Tariffset tariffset = new Tariffset("TarockBlock", Bei.NurPiccolo, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		// getTariffsetDao().refresh(tariffset);
		getTariffDao().create(new Tariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 2, "Königrufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 30));
		getTariffDao().create(new Tariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 10));
		getTariffDao().create(new Tariff(tariffset, 5, "Zwiccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 10));
		getTariffDao().create(new Tariff(tariffset, 6, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new Tariff(tariffset, 7, "Besserrufer (+Vogel)", TariffType1.Rufer, TariffType2.Besserrufer, 10));
		getTariffDao().create(new Tariff(tariffset, 8, "Bettler", TariffType1.Negativ, TariffType2.Bettler, 30));
		getTariffDao().create(new Tariff(tariffset, 9, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 40));
		getTariffDao().create(new Tariff(tariffset, 10, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 40));
		getTariffDao().create(new Tariff(tariffset, 11, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 50));
		getTariffDao().create(new Tariff(tariffset, 12, "Zwiccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 50));
		getTariffDao().create(new Tariff(tariffset, 13, "Bettler ouvert", TariffType1.Negativ, TariffType2.Bettler, 70));
		getTariffDao().create(new Tariff(tariffset, 14, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 80));
		getTariffDao().create(new Tariff(tariffset, 15, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 80));
		getPremiumDao().create(new Premium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new Premium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new Premium(tariffset, 4, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 5, "Mondfang", PremiumType1.Tarock, PremiumType2.Mondfang, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 8, "1. Sack (≥45/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 9, "2. Sack (≥55/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new Premium(tariffset, 10, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new Tariffset("Wiener Zeitung Cup", Bei.Keine, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new Tariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 2, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 40));
		getTariffDao().create(new Tariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new Tariff(tariffset, 5, "Zwiccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new Tariff(tariffset, 6, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new Tariff(tariffset, 7, "Pagatrufer", TariffType1.Rufer, TariffType2.Besserrufer, 30));
		getTariffDao().create(new Tariff(tariffset, 8, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 40));
		getTariffDao().create(new Tariff(tariffset, 9, "Uhurufer", TariffType1.Rufer, TariffType2.Besserrufer, 50));
		getTariffDao().create(new Tariff(tariffset, 10, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 50));
		getTariffDao().create(new Tariff(tariffset, 11, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 50));
		getTariffDao().create(new Tariff(tariffset, 12, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 60));
		getTariffDao().create(new Tariff(tariffset, 13, "Zwiccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 60));
		getTariffDao().create(new Tariff(tariffset, 14, "Kakadurufer", TariffType1.Rufer, TariffType2.Besserrufer, 70));
		getTariffDao().create(new Tariff(tariffset, 15, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 80));
		getTariffDao().create(new Tariff(tariffset, 16, "Quapilrufer", TariffType1.Rufer, TariffType2.Besserrufer, 90));
		getTariffDao().create(new Tariff(tariffset, 17, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 100));
		getTariffDao().create(new Tariff(tariffset, 18, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 100));
		getPremiumDao().create(new Premium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new Premium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new Premium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new Premium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 8, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new Tariffset("Raiffeisencup/Hausruckcup", Bei.Keine, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new Tariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 2, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 40));
		getTariffDao().create(new Tariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new Tariff(tariffset, 5, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 20));
		getTariffDao().create(new Tariff(tariffset, 6, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new Tariff(tariffset, 7, "A-Rufer (+Vogel)", TariffType1.Rufer, TariffType2.Besserrufer, 10));
		getTariffDao().create(new Tariff(tariffset, 8, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 40));
		getTariffDao().create(new Tariff(tariffset, 9, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 50));
		getTariffDao().create(new Tariff(tariffset, 10, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 60));
		getTariffDao().create(new Tariff(tariffset, 11, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 70));
		getTariffDao().create(new Tariff(tariffset, 12, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 80));
		getPremiumDao().create(new Premium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new Premium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new Premium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new Premium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 8, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new Tariffset("Tiroler Tarockcup", Bei.NurGleichwertige, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new Tariff(tariffset, 1, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 2, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 20));
		getTariffDao().create(new Tariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 40));
		getTariffDao().create(new Tariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new Tariff(tariffset, 5, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new Tariff(tariffset, 6, "Besserrufer (+Vogel)", TariffType1.Rufer, TariffType2.Besserrufer, 20));
		getTariffDao().create(new Tariff(tariffset, 7, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 40));
		getTariffDao().create(new Tariff(tariffset, 8, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 50));
		getTariffDao().create(new Tariff(tariffset, 9, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 50));
		getTariffDao().create(new Tariff(tariffset, 10, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 70));
		getTariffDao().create(new Tariff(tariffset, 11, "Zwiccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 70));
		getTariffDao().create(new Tariff(tariffset, 12, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 80));
		getTariffDao().create(new Tariff(tariffset, 13, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 100));
		getTariffDao().create(new Tariff(tariffset, 14, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 100));
		getPremiumDao().create(new Premium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new Premium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new Premium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new Premium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 6, "Mondfang", PremiumType1.Tarock, PremiumType2.Mondfang, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 7, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 8, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 9, "1. Sack (≥45/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "0", "20"));
		getPremiumDao().create(new Premium(tariffset, 10, "2. Sack (≥55/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "0", "20"));
		getPremiumDao().create(new Premium(tariffset, 11, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "150", "300"));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new Tariffset("Steirischer Tarockcup", Bei.NurPiccolo, KontraMax.Rekontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new Tariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 50));
		getTariffDao().create(new Tariff(tariffset, 2, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new Tariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 50));
		getTariffDao().create(new Tariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new Tariff(tariffset, 5, "Zwiccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new Tariff(tariffset, 6, "Triccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new Tariff(tariffset, 7, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new Tariff(tariffset, 8, "Pagatrufer", TariffType1.Rufer, TariffType2.Besserrufer, 30));
		getTariffDao().create(new Tariff(tariffset, 9, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 40));
		getTariffDao().create(new Tariff(tariffset, 10, "Uhurufer", TariffType1.Rufer, TariffType2.Besserrufer, 50));
		getTariffDao().create(new Tariff(tariffset, 11, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 40));
		getTariffDao().create(new Tariff(tariffset, 12, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 50));
		getTariffDao().create(new Tariff(tariffset, 13, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 40));
		getTariffDao().create(new Tariff(tariffset, 14, "Kakadurufer", TariffType1.Rufer, TariffType2.Besserrufer, 70));
		getTariffDao().create(new Tariff(tariffset, 15, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 80));
		getTariffDao().create(new Tariff(tariffset, 16, "Quapilrufer", TariffType1.Rufer, TariffType2.Besserrufer, 90));
		getTariffDao().create(new Tariff(tariffset, 17, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 80));
		getTariffDao().create(new Tariff(tariffset, 18, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 100));
		getPremiumDao().create(new Premium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new Premium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new Premium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new Premium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new Premium(tariffset, 8, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new Trischaken(tariffset, TrischakenQid.Vorhand, 2));

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
