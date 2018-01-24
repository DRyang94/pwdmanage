package com.devin.pwdmanage.entity;

public class SysUserAccess {
    private String userID;

    private String dbID;

    private String mainframeID;

    private String accessUserID;

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "SysUserAccess{" +
                "userID='" + userID + '\'' +
                ", dbID='" + dbID + '\'' +
                ", mainframeID='" + mainframeID + '\'' +
                ", accessUserID='" + accessUserID + '\'' +
                '}';
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }

    public String getMainframeID() {
        return mainframeID;
    }

    public void setMainframeID(String mainframeID) {
        this.mainframeID = mainframeID;
    }

    public String getAccessUserID() {
        return accessUserID;
    }

    public void setAccessUserID(String accessUserID) {
        this.accessUserID = accessUserID;
    }


}
