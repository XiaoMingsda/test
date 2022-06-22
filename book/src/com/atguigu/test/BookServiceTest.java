package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "book1", 12.50, "作者", 100, 1, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23, "book12", 12.50, "作者", 100, 1, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(23));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void page() {
        Page<Book> page = bookService.page(2, 5);
        System.out.println(page.getPageNo());
        System.out.println(page.getPageSize());
        System.out.println(page.getPageTotal());
        System.out.println(page.getPageTotalCount());
        List<Book> items = page.getItems();
        for (Book item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void pageByPrice() {
        Page<Book> page = bookService.pageByPrice(2, 5, 10, 2000);
        System.out.println(page.getPageNo());
        System.out.println(page.getPageSize());
        System.out.println(page.getPageTotal());
        System.out.println(page.getPageTotalCount());
        List<Book> items = page.getItems();
        for (Book item : items) {
            System.out.println(item);
        }
    }
}