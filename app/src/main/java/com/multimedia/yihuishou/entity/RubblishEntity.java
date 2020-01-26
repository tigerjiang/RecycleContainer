package com.multimedia.yihuishou.entity;

import com.multimedia.yihuishou.utils.Constant;

public class RubblishEntity extends BaseEntity{
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

    @Override
    public String toString() {
        return "RubblishEntity{" +
                "comment='" + comment + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", deleted='" + deleted + '\'' +
                ", id=" + id +
                ", integralType='" + integralType + '\'' +
                ", integralWeight=" + integralWeight +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getSubtitle() {
        return comment;
    }

    @Override
    public String getDesc() {
        //按次计算
        if (integralType.equals(Constant.CZD_KEY)) {

           return  integralWeight+ "积分" + " / 次";
           //重量自动计算
        } else if (integralType.equals(Constant.WZD_KEY)) {
            return  integralWeight+ "积分" + " / 千克";
            //重量手动计算
        } else if (integralType.equals(Constant.WSD_KEY)) {
            return  integralWeight+ "积分" + " ";
            //重量手动计算
        }
        return integralWeight + "";
    }
    @Override
    public boolean isChecked() {
        return false;
    }
}
