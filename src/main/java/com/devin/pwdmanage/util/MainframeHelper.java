package com.devin.pwdmanage.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class MainframeHelper {
    public static String user;
    public static String pwd;
    public static String ip;
    public static int port = 22;

    public static int testLogin(PmUsersForShow user) {
        Loadjsch.loadjsch();

        return 0;
    }

}

class Loadjsch {
    private static  String USER="m";
    private static  String PASSWORD="1234";
    private static  String HOST="39.108.152.133";
    private static  int DEFAULT_SSH_PORT=22;
    public Loadjsch() {

    };
    public Loadjsch(String user,String password) {
        USER=user;
        PASSWORD=password;
    };
    public Loadjsch(String user,String password,String host) {
        USER=user;
        PASSWORD=password;
        HOST=host;
    }
    public static void loadjsch(){

        try{
            JSch jsch=new JSch();

            Session session = jsch.getSession(USER,HOST,DEFAULT_SSH_PORT);
            session.setPassword(PASSWORD);

            UserInfo userInfo = new UserInfo() {

                public String getPassphrase() {
                    System.out.println("getPassphrase");
                    return null;
                }

                public String getPassword() {
                    System.out.println("getPassword");
                    return null;
                }

                public boolean promptPassword(String s) {
                    System.out.println("promptPassword:"+s);
                    return false;
                }

                public boolean promptPassphrase(String s) {
                    System.out.println("promptPassphrase:"+s);
                    return false;
                }

                public boolean promptYesNo(String s) {
                    System.out.println("promptYesNo:"+s);
                    return true;
                }

                public void showMessage(String s) {
                    System.out.println("showMessage:"+s);
                }
            };

            session.setUserInfo(userInfo);
            session.connect(30000);

            Channel channel=session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect(3*1000);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}

