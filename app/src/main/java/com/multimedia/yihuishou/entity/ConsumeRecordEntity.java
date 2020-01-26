package com.multimedia.yihuishou.entity;

public class ConsumeRecordEntity extends BaseEntity{

    /**
     * cardNo : string
     * comment : string
     * createdTime : 2020-01-19T14:40:10.713Z
     * id : 0
     * inspectorAccount : string
     * integral : 0
     * productID : 0
     * residentAccount : string
     */

    private String cardNo;
    private String comment;
    private String createdTime;
    private int id;
    private String inspectorAccount;
    private int integral;
    private int productID;
    private String residentAccount;

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

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getResidentAccount() {
        return residentAccount;
    }

    public void setResidentAccount(String residentAccount) {
        this.residentAccount = residentAccount;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getSubtitle() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
    @Override
    public boolean isChecked() {
        return false;
    }
}
