package com.multimedia.yihuishou.entity;
//社区
public class CommunityEntity extends BaseEntity{

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
    private String createUser;
    private String description;
    private String dictId;
    private String dictTypeId;
    private String name;
    private String parentId;
    private String parentIds;
    private String sort;
    private String status;
    private String updateTime;
    private String updateUser;

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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "CommunityEntity{" +
                "code='" + code + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", description='" + description + '\'' +
                ", dictId='" + dictId + '\'' +
                ", dictTypeId='" + dictTypeId + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentIds='" + parentIds + '\'' +
                ", sort='" + sort + '\'' +
                ", status='" + status + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
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
        return code;
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
