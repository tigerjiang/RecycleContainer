package com.multimedia.yihuishou.entity;

public class UserEntity extends BaseEntity{

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
    private int createUser;
    private int deptId;
    private String email;
    private String exchangeAuth;
    private int latitude;
    private int longitude;
    private String name;
    private String password;
    private String phone;
    private String roleId;
    private String salt;
    private String sex;
    private String status;
    private String type;
    private String updateTime;
    private int updateUser;
    private int userId;
    private int version;

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

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
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

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
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

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
