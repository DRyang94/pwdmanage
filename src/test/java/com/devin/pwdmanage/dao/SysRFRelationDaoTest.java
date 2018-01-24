package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.SysFunction;
import com.devin.pwdmanage.entity.SysRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysRFRelationDaoTest extends BaseTest{

    @Autowired
    SysRFRelationDao sysRFRelationDao;

    @Test
    public void testGetFunByRole() {
        SysRole test = new SysRole();
        test.setRoleID("a55bfa218a3011e7a501c45444fb4cc1");
        List<SysFunction> result = sysRFRelationDao.getFunByRole(test);
        if(!result.isEmpty()){
            for (SysFunction item:result
                 ) {
                System.out.println(item);

            }
        }

    }
}
