package com.devin.pwdmanage.entity;

public class PmMainframe {
    private String mainframeID;
    private String mainframeName;

    private String systemInfo;

    private String systemVersion;

    private String ip;

    private int port;

    @Override
    public String toString() {
        return "PmMainframe{" +
                "mainframeID='" + mainframeID + '\'' +
                ", mainframeName='" + mainframeName + '\'' +
                ", systemInfo='" + systemInfo + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
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
