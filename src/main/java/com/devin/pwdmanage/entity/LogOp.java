package com.devin.pwdmanage.entity;

import java.util.Date;

public class LogOp {
    @Override
    public String toString() {
        return "LogOp{" +
                "logID=" + logID +
                ", userID='" + userID + '\'' +
                ", loginIP='" + loginIP + '\'' +
                ", typeID=" + typeID +
                ", operation='" + operation + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID(int logID) {
        this.logID = logID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int logID;

    private String userID;

    private String loginIP;

    private int typeID;

    private String operation;

    private Date createTime;

    private String remark;


}
