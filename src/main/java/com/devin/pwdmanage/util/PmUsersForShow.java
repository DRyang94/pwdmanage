package com.devin.pwdmanage.util;

import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmMainframe;
import com.devin.pwdmanage.entity.PmUser;

public class PmUsersForShow {

    public PmUsersForShow() {
    }

    public PmUsersForShow(String userID, String userName,
                          String pwd, String remark, String state,
                          String mainframeID, String mainframeName,
                          String systemInfo, String dbID, String dbName,
                          String dbInfo, String ip, int port,
                          String rootName, String rootPwd) {
        this.userID = userID;
        this.userName = userName;
        this.pwd = pwd;
        this.remark = remark;
        this.state = state;
        this.mainframeID = mainframeID;
        this.mainframeName = mainframeName;
        this.systemInfo = systemInfo;
        this.dbID = dbID;
        this.dbName = dbName;
        this.dbInfo = dbInfo;
        this.ip = ip;
        this.port = port;
        this.rootName = rootName;
        this.rootPwd = rootPwd;
    }

    public PmUsersForShow(PmUser user, PmDatabase db, PmMainframe mf) {
        setUserID(user.getUserID());
        setUserName(user.getUserName());
        setPwd(user.getPwd());
        setRemark(user.getRemark());
        setState(user.getState());
        if(db != null) {
            setDbID(db.getDbID());
            setDbInfo(db.getDbInfo());
            setDbName(db.getDbName());
            setIp(db.getIp());
            setPort(db.getPort());
            setRootName(db.getRootName());
            setRootPwd(db.getRootPwd());
        } else {
            setMainframeID(mf.getMainframeID());
            setMainframeName(mf.getMainframeName());
            setSystemInfo(mf.getSystemInfo());
            setIp(mf.getIp());
            setPort(mf.getPort());
            setRootPwd(mf.getRootPwd());
            setRootName(mf.getRootName());
        }
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

    public String getMainframeID() {
        return mainframeID;
    }

    public void setMainframeID(String mainframeID) {
        this.mainframeID = mainframeID;
    }

    public String getMainframeName() {
        return mainframeName;
    }

    public void setMainframeName(String mainframeName) {
        this.mainframeName = mainframeName;
    }

    public String getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo) {
        this.systemInfo = systemInfo;
    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbInfo() {
        return dbInfo;
    }

    public void setDbInfo(String dbInfo) {
        this.dbInfo = dbInfo;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String userID;

    private String userName;

    private String pwd;

    private String remark;

    private String state;

    private String mainframeID;

    private String mainframeName;

    private String systemInfo;

    private String dbID;

    private String dbName;

    private String dbInfo;

    private String ip;

    private int port;

    public String getRootPwd() {
        return rootPwd;
    }

    public void setRootPwd(String rootPwd) {
        this.rootPwd = rootPwd;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    private String rootPwd;

    private String rootName;


}
