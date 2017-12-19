package com.devin.pwdmanage.util;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url;
    private static String username;
    private static String pwd;

    private static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static boolean verifyUser(PmUsersForShow user){
        url = "jdbc:mysql://" + user.getIp() + ":" + user.getPort()
                + "/" + user.getDbName();
        username = user.getUserName();
        pwd = user.getPwd();
        Connection conn = getConn();
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public static int addUser(PmUsersForShow user) {
        url = "jdbc:mysql://" + user.getIp() + ":" + user.getPort()
                + "/" + user.getDbName();
        username = user.getRootName();
        pwd = user.getRootPwd();
        Connection conn = getConn();
        int i = 0;
//        String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
        String sql1 = "CREATE USER ?@'%' IDENTIFIED BY ?;";
        String sql2 = "GRANT ALL PRIVILEGES ON *.* TO ?@'%' WITH GRANT OPTION;";
        PreparedStatement pstmt1, pstmt2;
        try {
            pstmt1 = (PreparedStatement) conn.prepareStatement(sql1);
            pstmt1.setString(1, user.getUserName());
            pstmt1.setString(2, user.getPwd());
            pstmt2 = (PreparedStatement) conn.prepareStatement(sql2);
            pstmt2.setString(1, user.getUserName());
            i = pstmt1.executeUpdate() + pstmt2.executeUpdate();
            //即使执行成功了也是1
            pstmt1.close();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            i = -1;
            e.printStackTrace();
        }
        return i;
    }

    public static int deleteUser(PmUsersForShow user) {
        url = "jdbc:mysql://" + user.getIp() + ":" + user.getPort()
                + "/" + user.getDbName();
        username = user.getRootName();
        pwd = user.getRootPwd();
        Connection conn = getConn();
        int i = 0;
//        String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
        String sql = "DROP USER ?@'%';";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserName());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            i = -1;
            e.printStackTrace();
        }
        return i;
    }

    //因为要分修改用户名跟修改密码的情况，所以需要oldName来做判断
    public static int updateUser(PmUsersForShow user, String oldName) {
        url = "jdbc:mysql://" + user.getIp() + ":" + user.getPort()
                + "/" + user.getDbName();
        username = user.getRootName();
        pwd = user.getRootPwd();
        Connection conn = getConn();
        int i = 0;
        if(oldName == null) {
            String sql = "ALTER USER ?@'%' IDENTIFIED BY ?;";
            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement) conn.prepareStatement(sql);
                pstmt.setString(1, user.getUserName());
                pstmt.setString(2, user.getPwd());
                i = pstmt.executeUpdate();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                i = -1;
                e.printStackTrace();
            }
        } else {
            addUser(user);
            user.setUserName(oldName);
            deleteUser(user);
        }

        return i;
    }




    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


}
