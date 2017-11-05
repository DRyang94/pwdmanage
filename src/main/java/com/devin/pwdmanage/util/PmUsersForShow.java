package com.devin.pwdmanage.util;

import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmMainframe;
import com.devin.pwdmanage.entity.PmUser;

public class PmUsersForShow {

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
            setDbVersion(db.getDbVersion());
            setIp(db.getIp());
            setPort(db.getPort());
        } else {
            setMainframeID(mf.getMainframeID());
            setMainframeName(mf.getMainframeName());
            setSystemInfo(mf.getSystemInfo());
            setSystemVersion(mf.getSystemVersion());
            setIp(mf.getIp());
            setPort(mf.getPort());
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

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
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

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
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

    private String systemVersion;

    private String dbID;

    private String dbName;

    private String dbInfo;

    private String dbVersion;

    private String ip;

    private int port;


}
