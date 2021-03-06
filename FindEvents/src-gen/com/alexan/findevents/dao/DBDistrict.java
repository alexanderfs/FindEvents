package com.alexan.findevents.dao;

import com.alexan.findevents.dao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBDISTRICT.
 */
public class DBDistrict {

    private Long id;
    /** Not-null value. */
    private String name;
    private Long timestamp;
    private long city_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient DBDistrictDao myDao;

    private DBCity dBCity;
    private Long dBCity__resolvedKey;


    public DBDistrict() {
    }

    public DBDistrict(Long id) {
        this.id = id;
    }

    public DBDistrict(Long id, String name, Long timestamp, long city_id) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.city_id = city_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDBDistrictDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    /** To-one relationship, resolved on first access. */
    public DBCity getDBCity() {
        long __key = this.city_id;
        if (dBCity__resolvedKey == null || !dBCity__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DBCityDao targetDao = daoSession.getDBCityDao();
            DBCity dBCityNew = targetDao.load(__key);
            synchronized (this) {
                dBCity = dBCityNew;
            	dBCity__resolvedKey = __key;
            }
        }
        return dBCity;
    }

    public void setDBCity(DBCity dBCity) {
        if (dBCity == null) {
            throw new DaoException("To-one property 'city_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.dBCity = dBCity;
            city_id = dBCity.getId();
            dBCity__resolvedKey = city_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
