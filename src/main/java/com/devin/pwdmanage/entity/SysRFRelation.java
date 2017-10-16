package com.devin.pwdmanage.entity;

public class SysRFRelation {

    private String rfID;

    private String roleID;

    private String funID;

    @Override
    public String toString() {
        return "SysUFRelation{" +
                "ufID='" + rfID + '\'' +
                ", roleID='" + roleID + '\'' +
                ", funID='" + funID + '\'' +
                '}';
    }

    public String getUfID() {
        return rfID;
    }

    public void setUfID(String ufID) {
        this.rfID = ufID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getFunID() {
        return funID;
    }

    public void setFunID(String funID) {
        this.funID = funID;
    }



}
