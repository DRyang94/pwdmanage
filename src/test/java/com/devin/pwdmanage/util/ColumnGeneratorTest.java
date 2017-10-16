package com.devin.pwdmanage.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;


/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class ColumnGeneratorTest {

    @Test
    public void testGetUUID() throws Exception {
        System.out.println(ColumnGenerator.getUUID());
    }

    @Test
    public void testGetTime() throws Exception {
        System.out.println(ColumnGenerator.getTime());
    }

}