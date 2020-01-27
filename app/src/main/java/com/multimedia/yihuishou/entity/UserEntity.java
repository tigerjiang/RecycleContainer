package com.multimedia.yihuishou.entity;

public class UserEntity extends BaseEntity {

    /**
     * account : string
     * avatar : string
     * birthday : 2020-01-19T14:45:52.434Z
     * createTime : 2020-01-19T14:45:52.434Z
     * createUser : 0
     * deptId : 0
     * email : string
     * exchangeAuth : string
     * latitude : 0
     * longitude : 0
     * name : string
     * password : string
     * phone : string
     * roleId : string
     * salt : string
     * sex : string
     * status : string
     * type : string
     * updateTime : 2020-01-19T14:45:52.434Z
     * updateUser : 0
     * userId : 0
     * version : 0
     */

    private String account;
    private String avatar;
    private String birthday;
    private String createTime;
    private String createUser;
    private String deptId;
    private String email;
    private String exchangeAuth;
    private String latitude;
    private String longitude;
    private String name;
    private String password;
    private String phone;
    private String roleId;
    private String salt;
    private String sex;
    private String status;
    private String type;
    private String updateTime;
    private String updateUser;
    private String userId;
    private String version;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExchangeAuth() {
        return exchangeAuth;
    }

    public void setExchangeAuth(String exchangeAuth) {
        this.exchangeAuth = exchangeAuth;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "account='" + account + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday='" + birthday + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
                ", deptId='" + deptId + '\'' +
                ", email='" + email + '\'' +
                ", exchangeAuth='" + exchangeAuth + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roleId='" + roleId + '\'' +
                ", salt='" + salt + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", userId='" + userId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserEntity)) {
            return false;
        }
        UserEntity entity = (UserEntity) obj;
        if (entity.name == this.name && entity.account == this.account) {
            return true;
        }
        return false;
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
