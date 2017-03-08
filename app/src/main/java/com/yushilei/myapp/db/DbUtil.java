package com.yushilei.myapp.db;

import android.util.Log;

import com.yushilei.myapp.BuildConfig;
import com.yushilei.myapp.entitys.Bean;

import org.xutils.DbManager;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.ex.DbException;
import org.xutils.x;

/**
 * @auther by yushilei.
 * @time 2017/3/8-16:38
 * @desc
 */

public class DbUtil {
    private static DbUtil dbUtil;

    public final DbManager dataDb;

    private final String DB_DATA_NAME = "test.db";

    private final int DB_DATA_VERSION = 2;

    private static final String TAG = "DbUtil";

    public static DbUtil instance() {
        if (dbUtil == null) {
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }

    public DbUtil() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(DB_DATA_NAME)
                .setDbVersion(DB_DATA_VERSION)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                }).setDbUpgradeListener(new DBListener());
        dataDb = x.getDb(daoConfig);
    }

    private static final class DBListener implements DbManager.DbUpgradeListener {
        @Override
        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
            Log.i(TAG, "DataDb :newVersion=" + newVersion + " ;oldVersion=" + oldVersion);
            if (newVersion > oldVersion) {
                try {
                    db.addColumn(Bean.class,"Age");
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
