package com.devin.pwdmanage.util;

import com.devin.pwdmanage.BaseTest;
import org.junit.Test;
import sun.applet.Main;

public class MainframeHelperTest extends BaseTest{


    @Test
    public void testVerify() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test", "password", null, null,
                null, null, null, null, null,
                null, "39.108.152.133", 22, "root", "Scut654123"
        );
        boolean result = MainframeHelper.verifyUser(user);
        System.out.println(result);
    }

    @Test
    public void testAdd() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test2", "password", null, null,
                null, null, null, null, null,
                null, "39.108.152.133", 22, "root", "Scut654123"
        );
        boolean result = MainframeHelper.addUser(user);
        System.out.println(result);
    }

    @Test
    public void testUpdate() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test2", "123456", null, null,
                null, null, null, null, null,
                null, "39.108.152.133", 22, "root", "Scut654123"
        );
        boolean result = MainframeHelper.updateUser(user, null);
        System.out.println(result);
    }

    @Test
    public void testDelete() throws Exception {
        PmUsersForShow user = new PmUsersForShow(
                null, "test2", "123456", null, null,
                null, null, null, null, "pwdmanage",
                "mysql", "localhost", 3306, "root", "d123456"
        );
        boolean result = MainframeHelper.deleteUser(user);
        System.out.println(result);
    }
}
