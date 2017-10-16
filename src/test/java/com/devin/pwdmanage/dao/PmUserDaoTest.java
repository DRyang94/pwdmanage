package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.entity.SysUser;
import com.devin.pwdmanage.util.ColumnGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;

public class PmUserDaoTest extends BaseTest{

    @Autowired
    private PmUserDao pmUserDao;

    @Test
    public void testFindUsers() throws Exception {
        HashMap<String, Object> test = new HashMap<String, Object>();
//        test.put("userName", "test1");
        test.put("mainframeID", "9c1099d7960711e7a230c45444fb4cc1");
        ArrayList<PmUser> users = (ArrayList<PmUser>)pmUserDao.findUsers(test);
        System.out.println(users.get(0));
    }

    @Test
    public void getTotalUserTest() {
        HashMap<String, Object> test = new HashMap<String, Object>();
        test.put("dbID", "56db722f960711e7a230c45444fb4cc1");
        System.out.println(pmUserDao.getTotalUser(test));
    }

    @Test
    public void updateUserTest() {
        PmUser test = new PmUser();
        test.setUserName("test1");
        test.setPwd("123456");
        //大于0的意思是成功修改了一条记录,即修改成功,如果updateUser()方法返回值等于0,即修改失败
        pmUserDao.updateUser(test);
    }

    @Test
    public void addUserTest() {
        PmUser test = new PmUser();
        test.setUserName("test5");
        test.setPwd("123456");
        test.setUserID(ColumnGenerator.getUUID());
        pmUserDao.addUser(test);
    }

    @Test
    public void deleteUserTest() {
        //Assert.assertTrue(userDao.deleteUser(51) > 0);
        pmUserDao.deleteUser("5c6a0a4cf61046a1b7b102516ef016a8");
    }

}
