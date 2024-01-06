package com.win.springbootbasic.dao;

import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.win.springbootbasic.bean.Book;

// BookDao-映射接口
// 数据库 SQL 映射需要添加 @Mapper 被容器映射到
// 核心在于 Dao 接口继承了一个 BaseMapper 的接口，这个接口中帮助开发者预定了若干个常用的 API 接口，简化了通用 API 接口的开发工作。
@Mapper
public interface BookDao extends BaseMapper<Book>{

  // 新增自定义查询方法
  // @Select("select * from tbl_book where id = #{id}")
  // public Book getById(Integer id);
}
