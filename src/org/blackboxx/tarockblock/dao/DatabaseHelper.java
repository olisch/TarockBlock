package org.blackboxx.tarockblock.dao;

import java.sql.SQLException;

import org.blackboxx.tarockblock.enums.Bei;
import org.blackboxx.tarockblock.enums.KontraMax;
import org.blackboxx.tarockblock.enums.PremiumType1;
import org.blackboxx.tarockblock.enums.PremiumType2;
import org.blackboxx.tarockblock.enums.TariffType1;
import org.blackboxx.tarockblock.enums.TariffType2;
import org.blackboxx.tarockblock.enums.TrischakenQid;
import org.blackboxx.tarockblock.persistance.TableAssocGameRegularPremium;
import org.blackboxx.tarockblock.persistance.TableAssocGameSession;
import org.blackboxx.tarockblock.persistance.TableAssocPlayerSession;
import org.blackboxx.tarockblock.persistance.TableGame;
import org.blackboxx.tarockblock.persistance.TableGameNegative;
import org.blackboxx.tarockblock.persistance.TableGameNegativeKontra;
import org.blackboxx.tarockblock.persistance.TableGameRegular;
import org.blackboxx.tarockblock.persistance.TableGameTrischaken;
import org.blackboxx.tarockblock.persistance.TablePlayer;
import org.blackboxx.tarockblock.persistance.TablePremium;
import org.blackboxx.tarockblock.persistance.TableSession;
import org.blackboxx.tarockblock.persistance.TableTariff;
import org.blackboxx.tarockblock.persistance.TableTariffset;
import org.blackboxx.tarockblock.persistance.TableTrischaken;

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

	private Dao<TableAssocGameRegularPremium, Integer> assocGameRegularPremiumDao;
	private Dao<TableAssocGameSession, Integer> assocGameSessionDao;
	private Dao<TableAssocPlayerSession, Integer> assocPlayerSessionDao;
	private Dao<TableGame, Integer> gameDao;
	private Dao<TableGameNegative, Integer> gameNegativeDao;
	private Dao<TableGameNegativeKontra, Integer> gameNegativeKontraDao;
	private Dao<TableGameRegular, Integer> gameRegularDao;
	private Dao<TableGameTrischaken, Integer> gameTrischakenDao;
	private Dao<TablePlayer, Integer> playerDao;
	private Dao<TablePremium, Integer> premiumDao;
	private Dao<TableSession, Integer> sessionDao;
	private Dao<TableTariff, Integer> tariffDao;
	private Dao<TableTariffset, Integer> tariffsetDao;
	private Dao<TableTrischaken, Integer> trischakenDao;

	public DatabaseHelper(Context context, String databaseName, CursorFactory factory, int databaseVersion, int configFileId) {
		super(context, databaseName, factory, databaseVersion, configFileId);
	}

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource, TablePlayer.class);
			TableUtils.createTable(connectionSource, TableTariffset.class);
			TableUtils.createTable(connectionSource, TableTariff.class);
			TableUtils.createTable(connectionSource, TableTrischaken.class);
			TableUtils.createTable(connectionSource, TablePremium.class);
			TableUtils.createTable(connectionSource, TableAssocGameRegularPremium.class);
			TableUtils.createTable(connectionSource, TableAssocGameSession.class);
			TableUtils.createTable(connectionSource, TableAssocPlayerSession.class);
			TableUtils.createTable(connectionSource, TableGame.class);
			TableUtils.createTable(connectionSource, TableGameNegative.class);
			TableUtils.createTable(connectionSource, TableGameNegativeKontra.class);
			TableUtils.createTable(connectionSource, TableGameRegular.class);
			TableUtils.createTable(connectionSource, TableGameTrischaken.class);
			TableUtils.createTable(connectionSource, TableSession.class);

			initDBWithDefaultValues();

		} catch (SQLException e) {
			Log.e(LOG_NAME, "Could not create tables!", e);
		}

	}

	private void initDBWithDefaultValues() throws SQLException {

		// this.connectionSource.getReadWriteConnection().setAutoCommit(true);

		getPlayerDao().create(new TablePlayer("Oliver"));
		getPlayerDao().create(new TablePlayer("Dani"));
		getPlayerDao().create(new TablePlayer("Jürgen"));

		TableTariffset tariffset = new TableTariffset("TarockBlock", Bei.NurPiccolo, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		// getTariffsetDao().refresh(tariffset);
		getTariffDao().create(new TableTariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 2, "Königrufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 30));
		getTariffDao().create(new TableTariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 10));
		getTariffDao().create(new TableTariff(tariffset, 5, "Zwiccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 10));
		getTariffDao().create(new TableTariff(tariffset, 6, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new TableTariff(tariffset, 7, "Besserrufer (+Vogel)", TariffType1.Rufer, TariffType2.Besserrufer, 10));
		getTariffDao().create(new TableTariff(tariffset, 8, "Bettler", TariffType1.Negativ, TariffType2.Bettler, 30));
		getTariffDao().create(new TableTariff(tariffset, 9, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 40));
		getTariffDao().create(new TableTariff(tariffset, 10, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 40));
		getTariffDao().create(new TableTariff(tariffset, 11, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 50));
		getTariffDao().create(new TableTariff(tariffset, 12, "Zwiccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 50));
		getTariffDao().create(new TableTariff(tariffset, 13, "Bettler ouvert", TariffType1.Negativ, TariffType2.Bettler, 70));
		getTariffDao().create(new TableTariff(tariffset, 14, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 80));
		getTariffDao().create(new TableTariff(tariffset, 15, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 80));
		getPremiumDao().create(new TablePremium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new TablePremium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new TablePremium(tariffset, 4, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 5, "Mondfang", PremiumType1.Tarock, PremiumType2.Mondfang, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 8, "1. Sack (≥45/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 9, "2. Sack (≥55/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new TablePremium(tariffset, 10, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new TableTariffset("Wiener Zeitung Cup", Bei.Keine, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new TableTariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 2, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 40));
		getTariffDao().create(new TableTariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new TableTariff(tariffset, 5, "Zwiccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new TableTariff(tariffset, 6, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new TableTariff(tariffset, 7, "Pagatrufer", TariffType1.Rufer, TariffType2.Besserrufer, 30));
		getTariffDao().create(new TableTariff(tariffset, 8, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 40));
		getTariffDao().create(new TableTariff(tariffset, 9, "Uhurufer", TariffType1.Rufer, TariffType2.Besserrufer, 50));
		getTariffDao().create(new TableTariff(tariffset, 10, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 50));
		getTariffDao().create(new TableTariff(tariffset, 11, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 50));
		getTariffDao().create(new TableTariff(tariffset, 12, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 60));
		getTariffDao().create(new TableTariff(tariffset, 13, "Zwiccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 60));
		getTariffDao().create(new TableTariff(tariffset, 14, "Kakadurufer", TariffType1.Rufer, TariffType2.Besserrufer, 70));
		getTariffDao().create(new TableTariff(tariffset, 15, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 80));
		getTariffDao().create(new TableTariff(tariffset, 16, "Quapilrufer", TariffType1.Rufer, TariffType2.Besserrufer, 90));
		getTariffDao().create(new TableTariff(tariffset, 17, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 100));
		getTariffDao().create(new TableTariff(tariffset, 18, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 100));
		getPremiumDao().create(new TablePremium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new TablePremium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new TablePremium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new TablePremium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 8, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new TableTariffset("Raiffeisencup/Hausruckcup", Bei.Keine, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new TableTariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 2, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 40));
		getTariffDao().create(new TableTariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new TableTariff(tariffset, 5, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 20));
		getTariffDao().create(new TableTariff(tariffset, 6, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new TableTariff(tariffset, 7, "A-Rufer (+Vogel)", TariffType1.Rufer, TariffType2.Besserrufer, 10));
		getTariffDao().create(new TableTariff(tariffset, 8, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 40));
		getTariffDao().create(new TableTariff(tariffset, 9, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 50));
		getTariffDao().create(new TableTariff(tariffset, 10, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 60));
		getTariffDao().create(new TableTariff(tariffset, 11, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 70));
		getTariffDao().create(new TableTariff(tariffset, 12, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 80));
		getPremiumDao().create(new TablePremium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new TablePremium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new TablePremium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new TablePremium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 8, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new TableTariffset("Tiroler Tarockcup", Bei.NurGleichwertige, KontraMax.Subkontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new TableTariff(tariffset, 1, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 2, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 20));
		getTariffDao().create(new TableTariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 40));
		getTariffDao().create(new TableTariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new TableTariff(tariffset, 5, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new TableTariff(tariffset, 6, "Besserrufer (+Vogel)", TariffType1.Rufer, TariffType2.Besserrufer, 20));
		getTariffDao().create(new TableTariff(tariffset, 7, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 40));
		getTariffDao().create(new TableTariff(tariffset, 8, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 50));
		getTariffDao().create(new TableTariff(tariffset, 9, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 50));
		getTariffDao().create(new TableTariff(tariffset, 10, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 70));
		getTariffDao().create(new TableTariff(tariffset, 11, "Zwiccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 70));
		getTariffDao().create(new TableTariff(tariffset, 12, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 80));
		getTariffDao().create(new TableTariff(tariffset, 13, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 100));
		getTariffDao().create(new TableTariff(tariffset, 14, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 100));
		getPremiumDao().create(new TablePremium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new TablePremium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new TablePremium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new TablePremium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 6, "Mondfang", PremiumType1.Tarock, PremiumType2.Mondfang, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 7, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 8, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 9, "1. Sack (≥45/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "0", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 10, "2. Sack (≥55/2)", PremiumType1.PunkteStiche, PremiumType2.Nothing, "0", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 11, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "150", "300"));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Vorhand, 2));

		tariffset = new TableTariffset("Steirischer Tarockcup", Bei.NurPiccolo, KontraMax.Rekontra);
		getTariffsetDao().create(tariffset);
		getTariffDao().create(new TableTariff(tariffset, 1, "Trischaken", TariffType1.Negativ, TariffType2.Vorhand, 50));
		getTariffDao().create(new TableTariff(tariffset, 2, "Rufer", TariffType1.Rufer, TariffType2.Vorhand, 10));
		getTariffDao().create(new TableTariff(tariffset, 3, "Sechserdreier", TariffType1.Dreier, TariffType2.Vorhand, 50));
		getTariffDao().create(new TableTariff(tariffset, 4, "Piccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new TableTariff(tariffset, 5, "Zwiccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new TableTariff(tariffset, 6, "Triccolo", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 20));
		getTariffDao().create(new TableTariff(tariffset, 7, "Solorufer", TariffType1.Rufer, TariffType2.Solo, 20));
		getTariffDao().create(new TableTariff(tariffset, 8, "Pagatrufer", TariffType1.Rufer, TariffType2.Besserrufer, 30));
		getTariffDao().create(new TableTariff(tariffset, 9, "Bettel", TariffType1.Negativ, TariffType2.Bettler, 40));
		getTariffDao().create(new TableTariff(tariffset, 10, "Uhurufer", TariffType1.Rufer, TariffType2.Besserrufer, 50));
		getTariffDao().create(new TableTariff(tariffset, 11, "Farbendreier", TariffType1.Farben, TariffType2.Nothing, 40));
		getTariffDao().create(new TableTariff(tariffset, 12, "Dreier", TariffType1.Dreier, TariffType2.Nothing, 50));
		getTariffDao().create(new TableTariff(tariffset, 13, "Piccolo ouvert", TariffType1.Negativ, TariffType2.PiccoloZwiccolo, 40));
		getTariffDao().create(new TableTariff(tariffset, 14, "Kakadurufer", TariffType1.Rufer, TariffType2.Besserrufer, 70));
		getTariffDao().create(new TableTariff(tariffset, 15, "Bettel ouvert", TariffType1.Negativ, TariffType2.Bettler, 80));
		getTariffDao().create(new TableTariff(tariffset, 16, "Quapilrufer", TariffType1.Rufer, TariffType2.Besserrufer, 90));
		getTariffDao().create(new TableTariff(tariffset, 17, "Farbensolo", TariffType1.Farben, TariffType2.Solo, 80));
		getTariffDao().create(new TableTariff(tariffset, 18, "Solodreier", TariffType1.Dreier, TariffType2.Solo, 100));
		getPremiumDao().create(new TablePremium(tariffset, 1, "Pagat", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 2, "Uhu", PremiumType1.Tarock, PremiumType2.Nothing, "20", "40"));
		getPremiumDao().create(new TablePremium(tariffset, 3, "Kakadu", PremiumType1.Tarock, PremiumType2.Nothing, "30", "60"));
		getPremiumDao().create(new TablePremium(tariffset, 4, "Quapil", PremiumType1.Tarock, PremiumType2.Nothing, "40", "80"));
		getPremiumDao().create(new TablePremium(tariffset, 5, "König ultimo", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 6, "Trull", PremiumType1.Tarock, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 7, "4 Könige", PremiumType1.Farben, PremiumType2.Nothing, "10", "20"));
		getPremiumDao().create(new TablePremium(tariffset, 8, "Valat", PremiumType1.PunkteStiche, PremiumType2.Nothing, "x4", "x8"));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Punktesieger, 1));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Bürgermeister, 2));
		getTrischakenDao().create(new TableTrischaken(tariffset, TrischakenQid.Vorhand, 2));

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {

	}

	public Dao<TableAssocGameRegularPremium, Integer> getAssocGameRegularPremiumDao() throws SQLException {
		if (assocGameRegularPremiumDao == null) {
			assocGameRegularPremiumDao = getDao(TableAssocGameRegularPremium.class);
		}
		return assocGameRegularPremiumDao;
	}

	public Dao<TableAssocGameSession, Integer> getAssocGameSessionDao() throws SQLException {
		if (assocGameSessionDao == null) {
			assocGameSessionDao = getDao(TableAssocGameSession.class);
		}
		return assocGameSessionDao;
	}

	public Dao<TableAssocPlayerSession, Integer> getAssocPlayerSessionDao() throws SQLException {
		if (assocPlayerSessionDao == null) {
			assocPlayerSessionDao = getDao(TableAssocPlayerSession.class);
		}
		return assocPlayerSessionDao;
	}

	public Dao<TableGame, Integer> getGameDao() throws SQLException {
		if (gameDao == null) {
			gameDao = getDao(TableGame.class);
		}
		return gameDao;
	}

	public Dao<TableGameNegative, Integer> getGameNegativeDao() throws SQLException {
		if (gameNegativeDao == null) {
			gameNegativeDao = getDao(TableGameNegative.class);
		}
		return gameNegativeDao;
	}

	public Dao<TableGameNegativeKontra, Integer> getGameNegativeKontraDao() throws SQLException {
		if (gameNegativeKontraDao == null) {
			gameNegativeKontraDao = getDao(TableGameNegativeKontra.class);
		}
		return gameNegativeKontraDao;
	}

	public Dao<TableGameRegular, Integer> getGameRegularDao() throws SQLException {
		if (gameRegularDao == null) {
			gameRegularDao = getDao(TableGameRegular.class);
		}
		return gameRegularDao;
	}

	public Dao<TableGameTrischaken, Integer> getGameTrischakenDao() throws SQLException {
		if (gameTrischakenDao == null) {
			gameTrischakenDao = getDao(TableGameTrischaken.class);
		}
		return gameTrischakenDao;
	}

	public Dao<TablePlayer, Integer> getPlayerDao() throws SQLException {
		if (playerDao == null) {
			playerDao = getDao(TablePlayer.class);
		}
		return playerDao;
	}

	public Dao<TablePremium, Integer> getPremiumDao() throws SQLException {
		if (premiumDao == null) {
			premiumDao = getDao(TablePremium.class);
		}
		return premiumDao;
	}

	public Dao<TableSession, Integer> getSessionDao() throws SQLException {
		if (sessionDao == null) {
			sessionDao = getDao(TableSession.class);
		}
		return sessionDao;
	}

	public Dao<TableTariff, Integer> getTariffDao() throws SQLException {
		if (tariffDao == null) {
			tariffDao = getDao(TableTariff.class);
		}
		return tariffDao;
	}

	public Dao<TableTariffset, Integer> getTariffsetDao() throws SQLException {
		if (tariffsetDao == null) {
			tariffsetDao = getDao(TableTariffset.class);
		}
		return tariffsetDao;
	}

	public Dao<TableTrischaken, Integer> getTrischakenDao() throws SQLException {
		if (trischakenDao == null) {
			trischakenDao = getDao(TableTrischaken.class);
		}
		return trischakenDao;
	}

}
