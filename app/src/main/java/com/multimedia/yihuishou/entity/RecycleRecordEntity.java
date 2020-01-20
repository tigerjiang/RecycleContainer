package com.multimedia.yihuishou.entity;

public class RecycleRecordEntity {

    /**
     * account : string
     * cardNo : string
     * comment : string
     * createdTime : 2020-01-19T14:43:42.941Z
     * id : 0
     * inspectorAccount : string
     * integral : 0
     * rubbishType : string
     * weight : 0
     */

    private String account;
    private String cardNo;
    private String comment;
    private String createdTime;
    private int id;
    private String inspectorAccount;
    private int integral;
    private String rubbishType;
    private int weight;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInspectorAccount() {
        return inspectorAccount;
    }

    public void setInspectorAccount(String inspectorAccount) {
        this.inspectorAccount = inspectorAccount;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getRubbishType() {
        return rubbishType;
    }

    public void setRubbishType(String rubbishType) {
        this.rubbishType = rubbishType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
