package com.alexan.findevents.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import com.alexan.findevents.dao.DBImageDao;
import com.alexan.findevents.dao.DBUserDao;
import com.alexan.findevents.dao.DBPersonDao;
import com.alexan.findevents.dao.DBCategoryDao;
import com.alexan.findevents.dao.DBFavCategoryDao;
import com.alexan.findevents.dao.DBFriendDao;
import com.alexan.findevents.dao.DBEventDao;
import com.alexan.findevents.dao.DBEventCategoryDao;
import com.alexan.findevents.dao.DBEventImageDao;
import com.alexan.findevents.dao.DBLocationDao;
import com.alexan.findevents.dao.DBProvinceDao;
import com.alexan.findevents.dao.DBCityDao;
import com.alexan.findevents.dao.DBDistrictDao;
import com.alexan.findevents.dao.DBCommentDao;
import com.alexan.findevents.dao.DBHotSpotDao;
import com.alexan.findevents.dao.DBHotEventDao;
import com.alexan.findevents.dao.DBRTEventDao;
import com.alexan.findevents.dao.DBCategoryEventDao;
import com.alexan.findevents.dao.DBSearchResultEventDao;
import com.alexan.findevents.dao.DBFriendEventDao;
import com.alexan.findevents.dao.DBEventMessageDao;
import com.alexan.findevents.dao.DBFriendMessageDao;
import com.alexan.findevents.dao.DBTimeRecorderDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1000): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1000;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        DBImageDao.createTable(db, ifNotExists);
        DBUserDao.createTable(db, ifNotExists);
        DBPersonDao.createTable(db, ifNotExists);
        DBCategoryDao.createTable(db, ifNotExists);
        DBFavCategoryDao.createTable(db, ifNotExists);
        DBFriendDao.createTable(db, ifNotExists);
        DBEventDao.createTable(db, ifNotExists);
        DBEventCategoryDao.createTable(db, ifNotExists);
        DBEventImageDao.createTable(db, ifNotExists);
        DBLocationDao.createTable(db, ifNotExists);
        DBProvinceDao.createTable(db, ifNotExists);
        DBCityDao.createTable(db, ifNotExists);
        DBDistrictDao.createTable(db, ifNotExists);
        DBCommentDao.createTable(db, ifNotExists);
        DBHotSpotDao.createTable(db, ifNotExists);
        DBHotEventDao.createTable(db, ifNotExists);
        DBRTEventDao.createTable(db, ifNotExists);
        DBCategoryEventDao.createTable(db, ifNotExists);
        DBSearchResultEventDao.createTable(db, ifNotExists);
        DBFriendEventDao.createTable(db, ifNotExists);
        DBEventMessageDao.createTable(db, ifNotExists);
        DBFriendMessageDao.createTable(db, ifNotExists);
        DBTimeRecorderDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        DBImageDao.dropTable(db, ifExists);
        DBUserDao.dropTable(db, ifExists);
        DBPersonDao.dropTable(db, ifExists);
        DBCategoryDao.dropTable(db, ifExists);
        DBFavCategoryDao.dropTable(db, ifExists);
        DBFriendDao.dropTable(db, ifExists);
        DBEventDao.dropTable(db, ifExists);
        DBEventCategoryDao.dropTable(db, ifExists);
        DBEventImageDao.dropTable(db, ifExists);
        DBLocationDao.dropTable(db, ifExists);
        DBProvinceDao.dropTable(db, ifExists);
        DBCityDao.dropTable(db, ifExists);
        DBDistrictDao.dropTable(db, ifExists);
        DBCommentDao.dropTable(db, ifExists);
        DBHotSpotDao.dropTable(db, ifExists);
        DBHotEventDao.dropTable(db, ifExists);
        DBRTEventDao.dropTable(db, ifExists);
        DBCategoryEventDao.dropTable(db, ifExists);
        DBSearchResultEventDao.dropTable(db, ifExists);
        DBFriendEventDao.dropTable(db, ifExists);
        DBEventMessageDao.dropTable(db, ifExists);
        DBFriendMessageDao.dropTable(db, ifExists);
        DBTimeRecorderDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(DBImageDao.class);
        registerDaoClass(DBUserDao.class);
        registerDaoClass(DBPersonDao.class);
        registerDaoClass(DBCategoryDao.class);
        registerDaoClass(DBFavCategoryDao.class);
        registerDaoClass(DBFriendDao.class);
        registerDaoClass(DBEventDao.class);
        registerDaoClass(DBEventCategoryDao.class);
        registerDaoClass(DBEventImageDao.class);
        registerDaoClass(DBLocationDao.class);
        registerDaoClass(DBProvinceDao.class);
        registerDaoClass(DBCityDao.class);
        registerDaoClass(DBDistrictDao.class);
        registerDaoClass(DBCommentDao.class);
        registerDaoClass(DBHotSpotDao.class);
        registerDaoClass(DBHotEventDao.class);
        registerDaoClass(DBRTEventDao.class);
        registerDaoClass(DBCategoryEventDao.class);
        registerDaoClass(DBSearchResultEventDao.class);
        registerDaoClass(DBFriendEventDao.class);
        registerDaoClass(DBEventMessageDao.class);
        registerDaoClass(DBFriendMessageDao.class);
        registerDaoClass(DBTimeRecorderDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}