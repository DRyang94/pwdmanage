package com.devin.pwdmanage.util;

import com.devin.pwdmanage.BaseTest;
import org.junit.Test;

public class DatabaseHelperTest extends BaseTest{


    @Test
    public void testVerify() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test", "password", null, null,
                null, null, null, null, "pwdmanage",
                "mysql", "localhost", 3306, "root", "d123456"
        );
        boolean result = DatabaseHelper.verifyUser(user);
        System.out.println(result);
    }

    @Test
    public void testAdd() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test", "123456", null, null,
                null, null, null, null, "pwdmanage",
                "mysql", "localhost", 3306, "root", "d123456"
        );
        int result = DatabaseHelper.addUser(user);
        System.out.println(result);
    }

    @Test
    public void testUpdate() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test", "123456", null, null,
                null, null, null, null, "pwdmanage",
                "mysql", "localhost", 3306, "root", "d123456"
        );
        int result = DatabaseHelper.updateUser(user, null);
        System.out.println(result);
    }

    @Test
    public void testDelete() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test", "123456", null, null,
                null, null, null, null, "pwdmanage",
                "mysql", "localhost", 3306, "root", "d123456"
        );
        int result = DatabaseHelper.deleteUser(user);
        System.out.println(result);
    }
}
