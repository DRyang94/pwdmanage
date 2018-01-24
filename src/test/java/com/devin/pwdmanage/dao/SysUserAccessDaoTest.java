package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.entity.SysUserAccess;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SysUserAccessDaoTest extends BaseTest {

    @Autowired
    SysUserAccessDao sysUserAccessDao;

    @Test
    public void testGetAccessByUser() {
        SysUser user = new SysUser();
        user.setUserID("d3fed3b4962611e7a230c45444fb4cc1");
        List<SysUserAccess> result = sysUserAccessDao.getAccessByUser(user);
        for (SysUserAccess item:result
             ) {
            System.out.println(result);

        }
    }
}
