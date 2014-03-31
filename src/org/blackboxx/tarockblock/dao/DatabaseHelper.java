package org.blackboxx.tarockblock.dao;

import java.sql.SQLException;

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

	private Dao<Player, Integer> playerDao;

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

		} catch (SQLException e) {
			Log.e(LOG_NAME, "Could not create tables!", e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {

	}

	public Dao<Player, Integer> getPlayerDao() throws SQLException {
		if (playerDao == null) {

			playerDao = getDao(Player.class);
		}
		return playerDao;
	}

}
