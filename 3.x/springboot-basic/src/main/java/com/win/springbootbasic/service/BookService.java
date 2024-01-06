package com.win.springbootbasic.service;

import java.util.List;

import com.win.springbootbasic.bean.Book;

public interface BookService {
  Boolean save(Book book);
  Boolean update(Book book);
  Boolean delete(Integer id);
  Book getById(Integer id);
  List<Book> getAll();
}
