package com.devin.pwdmanage.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

public class SysUser {
    private String userID;

    private String userName;

    private String pwd;

    private Date createTime;

    private String remark;

    private String state;

    @Override
    public String toString() {
        return "SysUser{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}
