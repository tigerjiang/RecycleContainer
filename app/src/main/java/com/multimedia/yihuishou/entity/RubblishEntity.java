package com.multimedia.yihuishou.entity;

public class RubblishEntity {
    /**
     * comment : 玩具、亚克力
     * createdTime : 2020-01-17 23:18:35
     * deleted :
     * id : 3
     * integralType : WZD
     * integralWeight : 5
     * name : 塑料
     * status : 0
     */

    private String comment;
    private String createdTime;
    private String deleted;
    private int id;
    private String integralType;
    private int integralWeight;
    private String name;
    private String status;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntegralType() {
        return integralType;
    }

    public void setIntegralType(String integralType) {
        this.integralType = integralType;
    }

    public int getIntegralWeight() {
        return integralWeight;
    }

    public void setIntegralWeight(int integralWeight) {
        this.integralWeight = integralWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
