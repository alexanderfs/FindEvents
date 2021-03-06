package com.alexan.findevents.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.alexan.findevents.dao.DBImage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DBIMAGE.
*/
public class DBImageDao extends AbstractDao<DBImage, Long> {

    public static final String TABLENAME = "DBIMAGE";

    /**
     * Properties of entity DBImage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ImageUrl = new Property(1, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property CacheLevel = new Property(2, Integer.class, "cacheLevel", false, "CACHE_LEVEL");
        public final static Property ReferBigImageID = new Property(3, Long.class, "referBigImageID", false, "REFER_BIG_IMAGE_ID");
        public final static Property Timestamp = new Property(4, Long.class, "timestamp", false, "TIMESTAMP");
        public final static Property EventID = new Property(5, Long.class, "eventID", false, "EVENT_ID");
        public final static Property PersonID = new Property(6, Long.class, "personID", false, "PERSON_ID");
    };


    public DBImageDao(DaoConfig config) {
        super(config);
    }
    
    public DBImageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DBIMAGE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'IMAGE_URL' TEXT," + // 1: imageUrl
                "'CACHE_LEVEL' INTEGER," + // 2: cacheLevel
                "'REFER_BIG_IMAGE_ID' INTEGER," + // 3: referBigImageID
                "'TIMESTAMP' INTEGER," + // 4: timestamp
                "'EVENT_ID' INTEGER," + // 5: eventID
                "'PERSON_ID' INTEGER);"); // 6: personID
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DBIMAGE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBImage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(2, imageUrl);
        }
 
        Integer cacheLevel = entity.getCacheLevel();
        if (cacheLevel != null) {
            stmt.bindLong(3, cacheLevel);
        }
 
        Long referBigImageID = entity.getReferBigImageID();
        if (referBigImageID != null) {
            stmt.bindLong(4, referBigImageID);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(5, timestamp);
        }
 
        Long eventID = entity.getEventID();
        if (eventID != null) {
            stmt.bindLong(6, eventID);
        }
 
        Long personID = entity.getPersonID();
        if (personID != null) {
            stmt.bindLong(7, personID);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DBImage readEntity(Cursor cursor, int offset) {
        DBImage entity = new DBImage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // imageUrl
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // cacheLevel
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // referBigImageID
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // timestamp
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // eventID
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // personID
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBImage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImageUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCacheLevel(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setReferBigImageID(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setTimestamp(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setEventID(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setPersonID(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DBImage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DBImage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
