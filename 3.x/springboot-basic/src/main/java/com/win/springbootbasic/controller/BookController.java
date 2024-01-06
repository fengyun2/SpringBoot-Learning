package com.win.springbootbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.springbootbasic.bean.Book;
import com.win.springbootbasic.dao.BookDao;

@RestController
@RequestMapping("/books")
public class BookController {
  @Autowired
  private BookDao bookDao;
  @GetMapping("getOne")
  public Book getOne() {
    Book book = bookDao.getById(1);
    return book;
  }
}
