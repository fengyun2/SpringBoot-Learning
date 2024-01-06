package com.win.springbootbasic.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.win.springbootbasic.bean.Book;

// BookDao-映射接口
// 数据库 SQL 映射需要添加 @Mapper 被容器映射到
@Mapper
public interface BookDao {
  @Select("select * from tbl_book where id = #{id}")
  public Book getById(Integer id);
}
