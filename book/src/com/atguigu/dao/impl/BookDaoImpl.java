package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {

    String sql = "";

    @Override
    public int addBook(Book book) {
        sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values (?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        sql = "update t_book set `name` = ?,`author` = ?,`price` = ?,`sales` = ?,`stock` = ?,`img_path` = ? where `id` = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    //查询时的sql语句里的img_path 要加上别名，这样才能和JavaBean兼容起来
    @Override
    public Book queryBookById(Integer id) {
        sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        sql = "select count(*) from t_book";
        //这个方法在BaseDao里其实没有声明，但直接调用即可
        //返回值是一个Number类型
        Number count = (Number) queryForSingleValue(sql);
        //使用intValue转换为int类型
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        sql = "select count(*) from t_book where price between ? and ?";
        //这个方法在BaseDao里其实没有声明，但直接调用即可
        //返回值是一个Number类型
        Number count = (Number) queryForSingleValue(sql, min, max);
        //使用intValue转换为int类型
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        sql = "select id, name, price, author, sales, stock, img_path as imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
