package com.devin.pwdmanage.entity;

public class SysURRelation {
    private String urID;

    private String userID;

    private String roleID;

    @Override
    public String toString() {
        return "SysURRrelation{" +
                "urID='" + urID + '\'' +
                ", userID='" + userID + '\'' +
                ", roleID='" + roleID + '\'' +
                '}';
    }



    public String getUrID() {
        return urID;
    }

    public void setUrID(String urID) {
        this.urID = urID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

}
