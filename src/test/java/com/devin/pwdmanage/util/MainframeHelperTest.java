package com.devin.pwdmanage.util;

import com.devin.pwdmanage.BaseTest;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.junit.Test;

public class MainframeHelperTest extends BaseTest{
    private static  String USER="root";
    private static  String PASSWORD="Scut654123";
    private static  String  HOST="39.108.152.133";
    private static  int  DEFAULT_SSH_PORT = 22;

    @Test
    public void test() throws Exception {
        MainframeHelper.testLogin(null);


    }
}
