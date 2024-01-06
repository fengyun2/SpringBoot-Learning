package com.win.springbootbasic.bean;

import lombok.Data;

// 使用 Lombok 快速制作实体类
// Book 实体类

@Data
public class Book {
  private Integer id;
  private String name;
  private String type;
  private String description;
}
