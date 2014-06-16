package com.alexan.findevents.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBCATEGORY.
 */
public class DBCategory {

    private Long id;
    private String name;
    private Long imageUrl;
    private Long timestamp;

    public DBCategory() {
    }

    public DBCategory(Long id) {
        this.id = id;
    }

    public DBCategory(Long id, String name, Long imageUrl, Long timestamp) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Long imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}