package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.PmDatabase;
import com.devin.pwdmanage.entity.PmMainframe;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;

public class PmMainframeDaoTest extends BaseTest{

    @Autowired
    private PmMainframeDao pmMainframeDao;

    @Test
    public void testFindMainframe() throws Exception {
        HashMap<String, Object> test = new HashMap<String, Object>();
        test.put("mainframeID", "99883198960711e7a230c45444fb4cc1");
        ArrayList<PmMainframe> dbs = (ArrayList<PmMainframe>)pmMainframeDao.findMainframe(test);
        System.out.println(dbs.get(0));
    }

    @Test
    public void getTotalMainframeTest() {
        HashMap<String, Object> test = new HashMap<String, Object>();
        System.out.println(pmMainframeDao.getTotalMainframe(test));
    }

    @Test
    public void updateMainframeTest() {
        PmMainframe test = new PmMainframe();
        test.setMainframeID("test");
        test.setMainframeName("test2");
        test.setSystemInfo("test2");
        //大于0的意思是成功修改了一条记录,即修改成功,如果updateUser()方法返回值等于0,即修改失败
        pmMainframeDao.updateMainframe(test);
    }

    @Test
    public void addMainframeTest() {
        PmMainframe test = new PmMainframe();
        test.setMainframeID("test");
        test.setMainframeName("test");
        test.setSystemInfo("test");
        test.setIp("test");
        test.setPort(22);
        test.setRootName("root");
        test.setRootPwd("123456");
        pmMainframeDao.addMainframe(test);
    }

    @Test
    public void deleteMainframeTest() {
        pmMainframeDao.deleteMainframe("test");
    }

}
