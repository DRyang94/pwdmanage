package com.devin.pwdmanage.entity;

public class PmDatabase {
    public PmDatabase() {
    }

    private String dbID;

    private String dbName;


    private String dbInfo;

    private String ip;

    private int port;

    private String rootName;

    private String rootPwd;

    @Override
    public String toString() {
        return "PmDatabase{" +
                "dbID='" + dbID + '\'' +
                ", dbName='" + dbName + '\'' +
                ", dbInfo='" + dbInfo + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", rootName='" + rootName + '\'' +
                ", rootPwd='" + rootPwd + '\'' +
                '}';
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getRootPwd() {
        return rootPwd;
    }

    public void setRootPwd(String rootPwd) {
        this.rootPwd = rootPwd;
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

    public PmDatabase(String dbID, String dbName, String dbInfo, String ip, int port, String rootName, String rootPwd) {
        this.dbID = dbID;
        this.dbName = dbName;
        this.dbInfo = dbInfo;
        this.ip = ip;
        this.port = port;
        this.rootName = rootName;
        this.rootPwd = rootPwd;
    }
}