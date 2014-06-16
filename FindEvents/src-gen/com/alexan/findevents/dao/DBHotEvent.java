package com.alexan.findevents.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBHOT_EVENT.
 */
public class DBHotEvent {

    private Long id;
    private Long eventID;
    private Long timestamp;

    public DBHotEvent() {
    }

    public DBHotEvent(Long id) {
        this.id = id;
    }

    public DBHotEvent(Long id, Long eventID, Long timestamp) {
        this.id = id;
        this.eventID = eventID;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
