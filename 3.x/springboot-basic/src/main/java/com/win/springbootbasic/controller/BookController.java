package com.win.springbootbasic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.win.springbootbasic.bean.Book;
import com.win.springbootbasic.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  /**
   * http://localhost:8080/books
   * @return
   */
  @GetMapping
  public List<Book> getAll() {
    return bookService.getAll();
  }

  /**
   * http://localhost:8080/books
   * @param book
   * @return
   */
  @PostMapping
  public Boolean save(@RequestBody Book book) {
    return bookService.save(book);
  }

  /**
   * http://localhost:8080/books
   * @param book
   * @return
   */
  @PutMapping
  public Boolean update(@RequestBody Book book) {
    return bookService.update(book);
  }

  /**
   * http://localhost:8080/books/{id}
   * @param id
   * @return
   */
  @GetMapping("{id}")
  public Book getById(@PathVariable Integer id) {
    return bookService.getById(id);
  }

  /**
   * http://localhost:8080/books/{currentPage}/{pageSize}
   * @param currentPage
   * @param pageSize
   * @param book
   * @return
   */
  @GetMapping("{currentPage}/{pageSize}")
  public IPage<Book> getPage(@PathVariable int currentPage, @PathVariable int pageSize, Book book) {
    System.out.println("参数 =====>"+ book);
    IPage<Book> page = bookService.getPage(currentPage, pageSize, book);
    // 如果当前页码值大于总页码值。那么重新执行查询操作，使用最大页码值作为当前页码值
    if (currentPage > page.getPages()) {
      page = bookService.getPage((int)page.getPages(), pageSize, book);
    }
    return page;
  }
}
