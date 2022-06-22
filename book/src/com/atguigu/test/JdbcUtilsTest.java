package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {
    /* 测试要引入两个jar包
    *   hamcrest-core-1.3.jar
    *   junit-4.12.jar
    *  */
    @Test
    public void testJdbcUtils() {
        for (int i = 0; i < 10; i++) {
            //这里需要注意，每使用一次 getConnection方法，就会占用一个数据库连接
            Connection conn = JdbcUtils.getConnection();
            System.out.println(conn);
            //用完之后及时释放
            JdbcUtils.close(conn);
        }
    }
}
