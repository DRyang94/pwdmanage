package com.devin.pwdmanage.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ColumnGenerator
{
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static Date getTime() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return timestamp;
    }
}
