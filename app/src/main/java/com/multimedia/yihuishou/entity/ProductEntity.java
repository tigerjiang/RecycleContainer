package com.multimedia.yihuishou.entity;

public class ProductEntity {
    /**
     * comment : 500ML洗手液
     * count : 100
     * createdTime : 2020-01-13 13:51:29
     * id : 2
     * modifiedTime : 2020-01-13 13:51:29
     * name : 500ML洗手液
     * price : 79
     * status : 使用中
     * unit : 件
     */

    private String comment;
    private int count;
    private String createdTime;
    private int id;
    private String modifiedTime;
    private String name;
    private int price;
    private String status;
    private String unit;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
