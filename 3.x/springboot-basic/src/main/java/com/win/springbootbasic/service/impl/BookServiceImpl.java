package com.win.springbootbasic.service.impl;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.win.springbootbasic.bean.Book;
import com.win.springbootbasic.dao.BookDao;
import com.win.springbootbasic.service.BookService;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookDao bookDao;

  @Override
  public Boolean save(Book book) {
    return bookDao.insert(book) > 0;
  }

  @Override
  public Boolean update(Book book) {
    return bookDao.updateById(book) > 0;
  }

  @Override
  public Boolean delete(Integer id) {
    return bookDao.deleteById(id) > 0;
  }

  @Override
  public Book getById(Integer id) {
    return bookDao.selectById(id);
  }

  @Override
  public List<Book> getAll() {
    return bookDao.selectList(null);
  }

  @Override
  public IPage<Book> getPage(int currentPage, int pageSize, Book book) {
    IPage<Book> page = new Page<Book>(currentPage, pageSize);
    LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
    lqw.like(Strings.isNotEmpty(book.getName()), Book::getName, book.getName());
    lqw.like(Strings.isNotEmpty(book.getType()), Book::getType, book.getType());
    lqw.like(Strings.isNotEmpty(book.getDescription()), Book::getDescription, book.getDescription());
    return bookDao.selectPage(page, lqw);
  }
}
