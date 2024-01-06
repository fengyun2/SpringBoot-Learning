package com.win.springbootbasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
  // 读取全部数据到 Environment 对象中
  @Autowired
  private Environment env;

  @Autowired
  private Enterprise enterprise;

  @Value("${lesson}")
  private String lessonName;

  @Value("${server.port}")
  private int port;

  @Value("${enterprise.subject[1]}")
  private String[] subject_01;

  @GetMapping("list")
  public String list() {
    return "lessonName:" + lessonName + "\n" + "port:" + port + "\n" + "subject_01:" + subject_01[0];
  }

  @GetMapping("{id}")
  public String getById(@PathVariable Integer id) {
    System.out.println(env.getProperty("lesson"));
    System.out.println(env.getProperty("enterprise.name"));
    System.out.println(env.getProperty("enterprise.subject[0]"));
    System.out.println(enterprise.getName());
    System.out.println(enterprise.getTel());
    System.out.println(enterprise.getSubject()[0]);
    System.out.println(enterprise);
    return "Hello, Spring Boot";
  }
}
