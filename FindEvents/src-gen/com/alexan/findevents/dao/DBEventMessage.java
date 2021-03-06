package com.alexan.findevents.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBEVENT_MESSAGE.
 */
public class DBEventMessage {

    private Long id;
    private Long commentID;
    private Long timestamp;

    public DBEventMessage() {
    }

    public DBEventMessage(Long id) {
        this.id = id;
    }

    public DBEventMessage(Long id, Long commentID, Long timestamp) {
        this.id = id;
        this.commentID = commentID;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
