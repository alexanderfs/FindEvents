package com.alexan.findevents.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.alexan.findevents.dao.DBUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DBUSER.
*/
public class DBUserDao extends AbstractDao<DBUser, Long> {

    public static final String TABLENAME = "DBUSER";

    /**
     * Properties of entity DBUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserID = new Property(1, String.class, "userID", false, "USER_ID");
        public final static Property PhoneNumber = new Property(2, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property EmailAddr = new Property(3, String.class, "emailAddr", false, "EMAIL_ADDR");
        public final static Property Nickname = new Property(4, String.class, "nickname", false, "NICKNAME");
        public final static Property Gender = new Property(5, Boolean.class, "gender", false, "GENDER");
        public final static Property Signature = new Property(6, String.class, "signature", false, "SIGNATURE");
        public final static Property Timestamp = new Property(7, Long.class, "timestamp", false, "TIMESTAMP");
    };


    public DBUserDao(DaoConfig config) {
        super(config);
    }
    
    public DBUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DBUSER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'USER_ID' TEXT," + // 1: userID
                "'PHONE_NUMBER' TEXT," + // 2: phoneNumber
                "'EMAIL_ADDR' TEXT," + // 3: emailAddr
                "'NICKNAME' TEXT," + // 4: nickname
                "'GENDER' INTEGER," + // 5: gender
                "'SIGNATURE' TEXT," + // 6: signature
                "'TIMESTAMP' INTEGER);"); // 7: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DBUSER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(2, userID);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(3, phoneNumber);
        }
 
        String emailAddr = entity.getEmailAddr();
        if (emailAddr != null) {
            stmt.bindString(4, emailAddr);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
 
        Boolean gender = entity.getGender();
        if (gender != null) {
            stmt.bindLong(6, gender ? 1l: 0l);
        }
 
        String signature = entity.getSignature();
        if (signature != null) {
            stmt.bindString(7, signature);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(8, timestamp);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DBUser readEntity(Cursor cursor, int offset) {
        DBUser entity = new DBUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // phoneNumber
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // emailAddr
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // nickname
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // gender
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // signature
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhoneNumber(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmailAddr(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNickname(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setGender(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setSignature(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTimestamp(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DBUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DBUser entity) {
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