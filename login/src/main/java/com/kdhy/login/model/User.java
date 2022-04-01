package com.kdhy.login.model;

public class User {
    private String id;

    private String userName;

    private String passWord;

    private Integer status;

    private String realName;

    private String loginIp;

    private Integer permissionGrade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getPermissionGrade() {
        return permissionGrade;
    }

    public void setPermissionGrade(Integer permissionGrade) {
        this.permissionGrade = permissionGrade;
    }
}