package com.liqy.douyin.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by liqy on 2018/2/1.
 */

public class DBHelper {

    private static DaoSession daoSession;



    public static void initDB(Context context) {
        //TODO 当你需要数据库更新操作的时候需要实现自己的OpenHelper,不能直接调用DaoMaster.DevOpenHelper
        //TODO 否则会产生数据丢失。

        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context, "test-db");


        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
