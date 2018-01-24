package com.devin.pwdmanage.entity;

import java.util.Date;

public class LogOp {

    @Override
    public String toString() {
        return "LogOp{" +
                "logID=" + logID +
                ", userID='" + userID + '\'' +
                ", opType='" + opType + '\'' +
                ", operation='" + operation + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    private String logID;



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
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

    private String userID;

    private String opType;

    private String operation;

    private Date createTime;

    private String remark;

}
