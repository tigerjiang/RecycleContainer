package com.multimedia.yihuishou.entity;
//社区
public class CommunityEntity {

    /**
     * code : string
     * createTime : 2020-01-19T14:41:43.935Z
     * createUser : 0
     * description : string
     * dictId : 0
     * dictTypeId : 0
     * name : string
     * parentId : 0
     * parentIds : string
     * sort : 0
     * status : string
     * updateTime : 2020-01-19T14:41:43.935Z
     * updateUser : 0
     */

    private String code;
    private String createTime;
    private int createUser;
    private String description;
    private int dictId;
    private int dictTypeId;
    private String name;
    private int parentId;
    private String parentIds;
    private int sort;
    private String status;
    private String updateTime;
    private int updateUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDictId() {
        return dictId;
    }

    public void setDictId(int dictId) {
        this.dictId = dictId;
    }

    public int getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(int dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "CommunityEntity{" +
                "code='" + code + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser=" + createUser +
                ", description='" + description + '\'' +
                ", dictId=" + dictId +
                ", dictTypeId=" + dictTypeId +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", sort=" + sort +
                ", status='" + status + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser=" + updateUser +
                '}';
    }
}
