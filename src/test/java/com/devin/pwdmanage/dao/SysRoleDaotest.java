package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.SysRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysRoleDaotest extends BaseTest{

    @Autowired
    private SysRoleDao sysRoleDao;

    @Test
    public void testGetRoleByUser() throws Exception {
        String test = new String("admin");
        SysRole role = sysRoleDao.getRole(test);
        System.out.println(role);
    }
}
