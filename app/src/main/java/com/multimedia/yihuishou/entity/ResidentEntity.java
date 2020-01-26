package com.multimedia.yihuishou.entity;

//居民信息
public class ResidentEntity extends BaseEntity {

    /**
     * account : string
     * address : string
     * cardNo : string
     * communityID : 0
     * deleted : 0
     * id : 0
     * integral : 0
     * name : string
     * registTime : 2020-01-19T14:44:36.129Z
     * sex : string
     * tel : string
     */

    private String account;
    private String address;
    private String cardNo;
    private String communityID;
    private String deleted;
    private String id;
    private String integral;
    private String name;
    private String registTime;
    private String sex;
    private String tel;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCommunityID() {
        return communityID;
    }

    public void setCommunityID(String communityID) {
        this.communityID = communityID;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ResidentEntity)) {
            return false;
        }
        ResidentEntity entity = (ResidentEntity) obj;
        if (entity.cardNo == this.cardNo && entity.integral == this.integral) {
            return true;
        }
        return false;
    }
}
