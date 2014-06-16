package com.alexan.findevents.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.alexan.findevents.dao.DBEvent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DBEVENT.
*/
public class DBEventDao extends AbstractDao<DBEvent, Long> {

    public static final String TABLENAME = "DBEVENT";

    /**
     * Properties of entity DBEvent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserID = new Property(1, Long.class, "userID", false, "USER_ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Description = new Property(3, String.class, "description", false, "DESCRIPTION");
        public final static Property Address = new Property(4, String.class, "address", false, "ADDRESS");
        public final static Property Province = new Property(5, String.class, "province", false, "PROVINCE");
        public final static Property City = new Property(6, String.class, "city", false, "CITY");
        public final static Property District = new Property(7, String.class, "district", false, "DISTRICT");
        public final static Property Addressdetail = new Property(8, String.class, "addressdetail", false, "ADDRESSDETAIL");
        public final static Property Starttime = new Property(9, Long.class, "starttime", false, "STARTTIME");
        public final static Property Endtime = new Property(10, Long.class, "endtime", false, "ENDTIME");
        public final static Property CollectionNum = new Property(11, Integer.class, "collectionNum", false, "COLLECTION_NUM");
        public final static Property AttendNum = new Property(12, Integer.class, "attendNum", false, "ATTEND_NUM");
        public final static Property CommentNum = new Property(13, Integer.class, "commentNum", false, "COMMENT_NUM");
        public final static Property Timestamp = new Property(14, Long.class, "timestamp", false, "TIMESTAMP");
    };

    private DaoSession daoSession;


    public DBEventDao(DaoConfig config) {
        super(config);
    }
    
    public DBEventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DBEVENT' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'USER_ID' INTEGER," + // 1: userID
                "'TITLE' TEXT," + // 2: title
                "'DESCRIPTION' TEXT," + // 3: description
                "'ADDRESS' TEXT," + // 4: address
                "'PROVINCE' TEXT," + // 5: province
                "'CITY' TEXT," + // 6: city
                "'DISTRICT' TEXT," + // 7: district
                "'ADDRESSDETAIL' TEXT," + // 8: addressdetail
                "'STARTTIME' INTEGER," + // 9: starttime
                "'ENDTIME' INTEGER," + // 10: endtime
                "'COLLECTION_NUM' INTEGER," + // 11: collectionNum
                "'ATTEND_NUM' INTEGER," + // 12: attendNum
                "'COMMENT_NUM' INTEGER," + // 13: commentNum
                "'TIMESTAMP' INTEGER);"); // 14: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DBEVENT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBEvent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long userID = entity.getUserID();
        if (userID != null) {
            stmt.bindLong(2, userID);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(4, description);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(5, address);
        }
 
        String province = entity.getProvince();
        if (province != null) {
            stmt.bindString(6, province);
        }
 
        String city = entity.getCity();
        if (city != null) {
            stmt.bindString(7, city);
        }
 
        String district = entity.getDistrict();
        if (district != null) {
            stmt.bindString(8, district);
        }
 
        String addressdetail = entity.getAddressdetail();
        if (addressdetail != null) {
            stmt.bindString(9, addressdetail);
        }
 
        Long starttime = entity.getStarttime();
        if (starttime != null) {
            stmt.bindLong(10, starttime);
        }
 
        Long endtime = entity.getEndtime();
        if (endtime != null) {
            stmt.bindLong(11, endtime);
        }
 
        Integer collectionNum = entity.getCollectionNum();
        if (collectionNum != null) {
            stmt.bindLong(12, collectionNum);
        }
 
        Integer attendNum = entity.getAttendNum();
        if (attendNum != null) {
            stmt.bindLong(13, attendNum);
        }
 
        Integer commentNum = entity.getCommentNum();
        if (commentNum != null) {
            stmt.bindLong(14, commentNum);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(15, timestamp);
        }
    }

    @Override
    protected void attachEntity(DBEvent entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DBEvent readEntity(Cursor cursor, int offset) {
        DBEvent entity = new DBEvent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // description
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // address
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // province
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // city
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // district
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // addressdetail
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // starttime
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // endtime
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // collectionNum
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // attendNum
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // commentNum
            cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBEvent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDescription(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAddress(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProvince(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCity(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDistrict(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setAddressdetail(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setStarttime(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setEndtime(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setCollectionNum(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setAttendNum(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setCommentNum(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setTimestamp(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DBEvent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DBEvent entity) {
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