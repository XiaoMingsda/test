package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    //第三步
    private static DruidDataSource dataSource;

    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            //读取 jdbc.properties 属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //用main主方法测试数据连接池是否连接成功
    public static void main(String[] args) {

    }

    //第一步
    //获取数据库连接池中的连接
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                conn = dataSource.getConnection();//从数据库连接池中获取连接
                //保存到ThreadLocal对象中，供后面的jdbc操作使用
                conns.set(conn);
                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //提交事务，并关闭释放连接
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) {//如果不等于null, 说明之前使用过连接，操作过数据库
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                connection.close();//不管前面提交事务执行结果如何，都要关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    //回滚事务，并关闭释放连接
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) {//如果不等于null, 说明之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();//不管前面提交事务执行结果如何，都要关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    //关闭连接，放回数据库连接池
    public static void close(Connection conn) {
        //第五步
        //不空才关闭，不然会有空指针异常
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //第二步
    //添加数据库驱动
    // mysql-connector-java-5.1.7-bin.jar包
    // druid-1.1.9.jar包

    //要使用 druid-1.1.9.jar 德鲁伊，必须要有一个配置文件，且位置必须要在src下
    //jdbc.properties
    //initialSize=5 初始化数量
    //maxActive=10  最大连接数


    //第六步 在test下进行测试
}
