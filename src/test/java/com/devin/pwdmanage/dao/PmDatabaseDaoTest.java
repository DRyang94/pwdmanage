package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmUser;
import com.devin.pwdmanage.util.ColumnGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;

public class PmDatabaseDaoTest extends BaseTest{

    @Autowired
    private PmDatabaseDao pmDatabaseDao;

    @Test
    public void testFindDB() throws Exception {
        HashMap<String, Object> test = new HashMap<String, Object>();
        test.put("dbID", "56db722f960711e7a230c45444fb4cc1");
        ArrayList<PmDatabase> dbs = (ArrayList<PmDatabase>)pmDatabaseDao.findDB(test);
        System.out.println(dbs.get(0));
    }

    @Test
    public void getTotalDBTest() {
        HashMap<String, Object> test = new HashMap<String, Object>();
        System.out.println(pmDatabaseDao.getTotalDB(test));
    }

    @Test
    public void updateDBTest() {
        PmDatabase test = new PmDatabase();
        test.setDbID("test");
        test.setDbName("test2");
        test.setDbInfo("test2");
        //大于0的意思是成功修改了一条记录,即修改成功,如果updateUser()方法返回值等于0,即修改失败
        pmDatabaseDao.updateDB(test);
    }

    @Test
    public void addDBTest() {
        PmDatabase test = new PmDatabase();
        test.setDbID("test");
        test.setDbName("test");
        test.setDbInfo("test");
        test.setIp("test");
        test.setPort(22);
        test.setRootName("root");
        test.setRootPwd("123456");
        pmDatabaseDao.addDB(test);
    }

    @Test
    public void deleteDBTest() {
        pmDatabaseDao.deleteDB("test");
    }

}
