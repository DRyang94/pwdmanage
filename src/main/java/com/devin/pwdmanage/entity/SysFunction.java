package com.devin.pwdmanage.entity;

public class SysFunction {
    private String funID;

    private String funName;

    private String funUrl;

    private String funState;

    @Override
    public String toString() {
        return "SysFunction{" +
                "funID='" + funID + '\'' +
                ", funName='" + funName + '\'' +
                ", funUrl='" + funUrl + '\'' +
                ", funState='" + funState + '\'' +
                '}';
    }

    public String getFunID() {
        return funID;
    }

    public void setFunID(String funID) {
        this.funID = funID;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getFunUrl() {
        return funUrl;
    }

    public void setFunUrl(String funUrl) {
        this.funUrl = funUrl;
    }

    public String getFunState() {
        return funState;
    }

    public void setFunState(String funState) {
        this.funState = funState;
    }


}
