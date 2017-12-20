package com.devin.pwdmanage.util;
import com.jcraft.jsch.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
public class MainframeHelper {
    private static Session session = null;
    private static Channel channel = null;
    //root的相关信息
    private static String ip;
    private static int  port;
    private static String userName;
    private static String pwd;
    private static final int timeout = 30000;

    private static ChannelExec getChannelExec() {
        try {
            if (channel != null && channel.isConnected()) {
                return (ChannelExec) channel;
            }
            JSch jSch = new JSch();
            if (session == null || !session.isConnected()) {
                session = jSch.getSession(userName, ip, port);
                session.setPassword(pwd);
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
                session.setTimeout(timeout);
                session.connect();
            }
            channel = session.openChannel("exec");
        } catch (Exception e) {
            if (session != null) {
                session.disconnect();
                session = null;
            }
            channel = null;
        }
        return channel == null ? null : (ChannelExec) channel;
    }

    //使用目标用户信息登陆主机
    public static boolean verifyUser(PmUsersForShow user) {
        ip = user.getIp();
        port = user.getPort();
        userName = user.getUserName();
        pwd = user.getPwd();
        getChannelExec();
        if(channel != null) {
            try {
                channel.disconnect();
                channel = null;
                session.disconnect();
                session = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }


    public static boolean addUser(PmUsersForShow user)
    {
        ip = user.getIp();
        port = user.getPort();
        userName = user.getRootName();
        pwd = user.getRootPwd();
        if(user.getUserName()==null) return false;
        if(user.getPwd()==null) return false;
        if(executeCommand("useradd " + user.getUserName()))
        {
            if(executeCommand("echo " + user.getUserName() + ":" + user.getPwd() + "|chpasswd"))
                return true;
            else return false;
        }
        else return false;

    }

    public static boolean deleteUser(PmUsersForShow user)
    {
        ip = user.getIp();
        port = user.getPort();
        userName = user.getRootName();
        pwd = user.getRootPwd();
        if(user.getUserName()==null) return false;
        if(executeCommand("userdel " + user.getUserName()))
        {
            return true;
        }
        else return false;

    }

    public static boolean updateUser(PmUsersForShow user, String oldName)
    {
        ip = user.getIp();
        port = user.getPort();
        userName = user.getRootName();
        pwd = user.getRootPwd();
        if(user.getUserName()==null) return false;
        if(user.getPwd()==null) return false;
        if(oldName == null) {
            if(executeCommand("echo " + user.getUserName() + ":" + user.getPwd() + "|chpasswd"))
            {
                return true;
            }
        }else {
            addUser(user);
            user.setUserName(oldName);
            deleteUser(user);
            return true;
        }
        return false;

    }
//
//    public boolean ifexit()
//    {
//        if(user==null) return false;
//        ExecCommand jschCommand = new ExecCommand();
//        if(jschCommand.executeCommand("useradd"+user)==true)
//        {
//            return true;
//        }
//        else return false;
//
//    }


    public static boolean executeCommand(String command) {
        boolean flag = false;
        ChannelExec channelExec = getChannelExec();
        if (channelExec == null) {
            return false;
        }
        try {
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);
            channelExec.setCommand(command);
            InputStream in = channelExec.getInputStream();
            channelExec.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line = null;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
                return false;
            }
            closeChannel();
            flag = true;
        } catch (Exception e) {
            System.out.println(e);
            flag = false;
        }
        return flag;
    }

    //关闭连接
    private static void closeChannel() {
        try {
            if (channel != null) {
                channel.disconnect();
                channel = null;
            }
            if (session != null) {
                session.disconnect();
                session = null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
