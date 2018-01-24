package com.devin.pwdmanage.entity;

public class PmMainframe {
    public PmMainframe() {
    }

    private String mainframeID;
    private String mainframeName;

    public PmMainframe(String mainframeID, String mainframeName, String systemInfo, String ip, int port, String rootName, String rootPwd) {
        this.mainframeID = mainframeID;
        this.mainframeName = mainframeName;
        this.systemInfo = systemInfo;
        this.ip = ip;
        this.port = port;
        this.rootName = rootName;
        this.rootPwd = rootPwd;
    }

    private String systemInfo;

    private String ip;

    private int port;

    private String rootName;

    private String rootPwd;


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


    @Override
    public String toString() {
        return "PmMainframe{" +
                "mainframeID='" + mainframeID + '\'' +
                ", mainframeName='" + mainframeName + '\'' +
                ", systemInfo='" + systemInfo + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", rootName='" + rootName + '\'' +
                ", rootPwd='" + rootPwd + '\'' +
                '}';
    }
}
