package com.devin.pwdmanage.entity;

public class SysRole {
    private String roleID;

    private String roleName;

    private int count;

    @Override
    public String toString() {
        return "SysRole{" +
                "roleID='" + roleID + '\'' +
                ", roleName='" + roleName + '\'' +
                ", count=" + count +
                '}';
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
