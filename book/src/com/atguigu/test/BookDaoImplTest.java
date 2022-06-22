package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {
    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "book1", 22.30, "book1Author", 1000, 10000, ""));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "book12", 22.30, "book1Author", 1000, 10000, ""));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /*
    * 注意测试方法不需要返回值和参数
    * */
    @Test
    public void queryForPageTotalCount() {
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        System.out.println(pageTotalCount);
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(0, 10);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(10, 2000);
        System.out.println(pageTotalCount);
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(0, 198, 10, 2000);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}