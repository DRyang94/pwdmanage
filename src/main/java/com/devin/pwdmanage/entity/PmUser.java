package com.devin.pwdmanage.entity;

public class PmUser {
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMainframeID() {
        return mainframeID;
    }

    public void setMainframeID(String mainframeID) {
        this.mainframeID = mainframeID;
    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PmUser{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", mainframeID='" + mainframeID + '\'' +
                ", dbID='" + dbID + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    private String userID;

    private String userName;

    private String pwd;

    private String mainframeID;

    private String dbID;

    private String remark;

    private String state;
}
