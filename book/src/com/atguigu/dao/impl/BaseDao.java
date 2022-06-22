package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

//这个类声明为 abstract，因为这一般是给别人复用的
//这里需要注意，前面写的工具类JdbcUtils用到的Druid是用来获取数据库连接的
//这里的Dbutils是用来操纵数据库的
public abstract class BaseDao {
    //使用DbUtils操作数据库
    //要引入 commons-dbutils-1.3.jar包
    //QueryRunner类是DbUtils里的一个类
    private QueryRunner queryRunner = new QueryRunner();


    //update() 方法用来执行：Insert\Update\Delete语句
    //  如果返回-1，说明执行失败
    //  返回其他，表示影响的行数
    public int update(String sql, Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            //第三个参数表示sql语句里的参数值
            return queryRunner.update(connection, sql, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //查询 返回JavaBean对象

    //返回一个对象的情况
    /* 参数含义
    *    type   执行的对象类型
    *    sql    执行的sql语句
    *    args   sql对应的参数值
    *    <T>    返回的类型的泛型
    * */
    public <T> T queryForOne(Class<T> type, String sql, Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //返回多个对象的情况
    /* 参数含义
     *    type   执行的对象类型
     *    sql    执行的sql语句
     *    args   sql对应的参数值
     *    <T>    返回的类型的泛型
     * */
    public <T> List<T> queryForList(Class<T> type, String sql, Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //返回单个值的情况
    public Object queryForSingleValue(String sql, Object ... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
