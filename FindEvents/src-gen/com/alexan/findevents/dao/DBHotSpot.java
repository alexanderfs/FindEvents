package com.alexan.findevents.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBHOT_SPOT.
 */
public class DBHotSpot {

    private Long id;
    /** Not-null value. */
    private String name;
    private Long timestamp;

    public DBHotSpot() {
    }

    public DBHotSpot(Long id) {
        this.id = id;
    }

    public DBHotSpot(Long id, String name, Long timestamp) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
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

}
