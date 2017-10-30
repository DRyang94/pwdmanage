package com.devin.pwdmanage.util;

import com.devin.pwdmanage.BaseTest;
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
public class ColumnGeneratorTest extends BaseTest{

    @Test
    public void testGetUUID() throws Exception {
        System.out.println(ColumnGenerator.getUUID());
    }

    @Test
    public void testGetTime() throws Exception {
        System.out.println(ColumnGenerator.getTime());
    }

    @Test
    public void testMd5() throws Exception {
        System.out.println(MD5Util.MD5Encode("askfhkasjfhkjfhakjewjhfjkahfrkaehr","UTF-8"));
    }


}