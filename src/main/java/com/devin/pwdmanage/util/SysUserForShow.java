package com.devin.pwdmanage.util;

import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysUser;

import java.util.Date;

public class SysUserForShow {
    private String userID;

    private String roleID;
    private String userName;

    private String pwd;

    private Date createTime;

    private String remark;

    private String state;

    private String roleName;


    public SysUserForShow() {

    }

    public SysUserForShow(String userID, String roleID, String userName, String pwd, Date createTime, String remark, String state, String roleName) {
        this.userID = userID;
        this.roleID = roleID;
        this.userName = userName;
        this.pwd = pwd;
        this.createTime = createTime;
        this.remark = remark;
        this.state = state;
        this.roleName = roleName;
    }


    public SysUserForShow(SysUser user, SysRole role) {
        this.userID = user.getUserID();
        this.roleID = user.getRoleID();
        this.userName = user.getUserName();
        this.pwd = user.getPwd();
        this.createTime = user.getCreateTime();
        this.remark = user.getRemark();
        this.state = user.getState();
        this.roleName = role.getRoleName();
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


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "SysUserForShow{" +
                "userID='" + userID + '\'' +
                ", roleID='" + roleID + '\'' +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
