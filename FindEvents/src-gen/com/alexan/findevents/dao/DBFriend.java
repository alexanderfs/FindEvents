package com.alexan.findevents.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBFRIEND.
 */
public class DBFriend {

    private Long id;
    private Long mainID;
    private Long friendID;
    private Integer friendType;
    private Long timestamp;

    public DBFriend() {
    }

    public DBFriend(Long id) {
        this.id = id;
    }

    public DBFriend(Long id, Long mainID, Long friendID, Integer friendType, Long timestamp) {
        this.id = id;
        this.mainID = mainID;
        this.friendID = friendID;
        this.friendType = friendType;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainID() {
        return mainID;
    }

    public void setMainID(Long mainID) {
        this.mainID = mainID;
    }

    public Long getFriendID() {
        return friendID;
    }

    public void setFriendID(Long friendID) {
        this.friendID = friendID;
    }

    public Integer getFriendType() {
        return friendType;
    }

    public void setFriendType(Integer friendType) {
        this.friendType = friendType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
