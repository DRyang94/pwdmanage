package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.util.ColumnGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;

public class SysUserDaoTest extends BaseTest{

    @Autowired
    private SysUserDao sysUserDao;

    @Test
    public void testLogin() throws Exception {
        SysUser test = new SysUser();
        test.setUserName("admin");
        test.setPwd("123456");
        SysUser user = sysUserDao.login(test);
        System.out.println(user);
    }

    @Test
    public void testFindUsers() throws Exception {
        HashMap<String, Object> test = new HashMap<String, Object>();
        test.put("userName", "admin");
        ArrayList<SysUser> users = (ArrayList<SysUser>)sysUserDao.findUsers(test);
        System.out.println(users.get(0));
    }

    @Test
    public void getTotalUserTest() {
        System.out.println(sysUserDao.getTotalUser(null));
    }

    @Test
    public void updateUserTest() {
        SysUser test = new SysUser();
        test.setUserName("admin");
        test.setPwd("123456");
        //大于0的意思是成功修改了一条记录,即修改成功,如果updateUser()方法返回值等于0,即修改失败
        sysUserDao.updateUser(test);
    }

    @Test
    public void addUserTest() {
        SysUser test = new SysUser();
        test.setUserName("test");
        test.setPwd("123456");
        test.setUserID(ColumnGenerator.getUUID());
        test.setCreateTime(ColumnGenerator.getTime());
        sysUserDao.addUser(test);
    }

    @Test
    public void deleteUserTest() {
        //Assert.assertTrue(userDao.deleteUser(51) > 0);
        sysUserDao.deleteUser("4addd0f70538456db98495b96b75325b");
    }

}
