package com.devin.pwdmanage.dao;

import com.devin.pwdmanage.BaseTest;
import com.devin.pwdmanage.entity.SysFunction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysFunctionDaoTest extends BaseTest {

    @Autowired
    SysFunctionDao sysFunctionDao;

    @Test
    public void testGetFun() {
        String test = new String("modify");
        SysFunction function = sysFunctionDao.getFun(test);
        System.out.println(function);
    }
}
