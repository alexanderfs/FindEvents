package com.alexan.findevents.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBPERSON.
 */
public class DBPerson {

    private Long id;
    private String userID;
    private String phoneNumber;
    private String emailAddr;
    private String nickname;
    private Long locationID;
    private Boolean gender;
    private String signature;
    private Long icon;
    private Long timestamp;

    public DBPerson() {
    }

    public DBPerson(Long id) {
        this.id = id;
    }

    public DBPerson(Long id, String userID, String phoneNumber, String emailAddr, String nickname, Long locationID, Boolean gender, String signature, Long icon, Long timestamp) {
        this.id = id;
        this.userID = userID;
        this.phoneNumber = phoneNumber;
        this.emailAddr = emailAddr;
        this.nickname = nickname;
        this.locationID = locationID;
        this.gender = gender;
        this.signature = signature;
        this.icon = icon;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getIcon() {
        return icon;
    }

    public void setIcon(Long icon) {
        this.icon = icon;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
