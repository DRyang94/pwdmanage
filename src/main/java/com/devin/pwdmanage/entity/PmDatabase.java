package com.devin.pwdmanage.entity;

public class PmDatabase {
    private String dbID;

    private String dbName;

    private String dbInfo;

    private String dbVersion;

    private String ip;

    private int port;

    @Override
    public String toString() {
        return "PmDatabase{" +
                "dbID='" + dbID + '\'' +
                ", dbName='" + dbName + '\'' +
                ", dbInfo='" + dbInfo + '\'' +
                ", dbVersion='" + dbVersion + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
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


}
