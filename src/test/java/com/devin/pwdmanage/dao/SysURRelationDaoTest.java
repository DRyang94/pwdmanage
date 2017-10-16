package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.SysRole;
import com.devin.pwdmanage.entity.SysURRelation;
import com.devin.pwdmanage.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */

public class SysURRelationDaoTest extends BaseTest {
    @Autowired
    private SysURRelationDao sysURRelationDao;

    @Test
    public void testGetRoleByUser() throws Exception {
        SysUser test = new SysUser();
        test.setUserID("882b82d38a3111e7a501c45444fb4cc1");
        test.setUserName("admin");
        test.setPwd("123456");
        SysRole role = sysURRelationDao.getRoleByUser(test);
        System.out.println(role);
    }

    @Test
    public void testGetUserByRole() throws Exception {
        SysRole test = new SysRole();
        test.setRoleID("a55bfa218a3011e7a501c45444fb4cc1");
        List<SysUser> user = sysURRelationDao.getUsersByRole(test);
        System.out.println(user.get(0));
    }

    @Test
    public void testAddRelation() throws Exception {
        SysURRelation relation = new SysURRelation();
        relation.setRoleID("24efdefb8a3111e7a501c45444fb4cc1");
        relation.setUrID("faf");
        relation.setUserID("882b82d38a3111e7a501c45444fb4cc1");
        sysURRelationDao.addRelation(relation);
    }

    @Test
    public void testUpdateRelation() throws Exception {
        SysURRelation relation = new SysURRelation();
        relation.setRoleID("24efdefb8a3111e7a501c45444fb4cc1");
        relation.setUrID("faf");
        relation.setUserID("882b82d38a3111e7a501c45444fb4cc1");
        sysURRelationDao.updateRelation(relation);
    }


    @Test
    public void testDeleteRelation() throws Exception {
        SysUser test = new SysUser();
        test.setUserID("882b82d38a3111e7a501c45444fb4cc1");
        test.setUserName("admin");
        test.setPwd("123456");
        sysURRelationDao.deleteRelation(test);

    }
}

