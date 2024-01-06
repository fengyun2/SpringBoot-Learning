package com.win.springbootbasic.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Component: 封装yaml对象格式数据必须先声明当前实体类受 Spring 管控
 * 使用 @ConfigurationProperties 注解定义当前实体类读取配置属性信息，通过prefix属性设置读取哪个数据。
 */
@Component
@ConfigurationProperties(prefix = "enterprise")
public class Enterprise {
  private String name;
  private Integer age;
  private String tel;
  private String[] subject;

  @Override
  public String toString() {
    return "Enterprise{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", tel='" + tel + '\'' +
        ", subject=" + subject +
        '}';
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String[] getSubject() {
    return subject;
  }

  public void setSubject(String[] subject) {
    this.subject = subject;
  }
}
