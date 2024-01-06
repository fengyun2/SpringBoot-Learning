package com.win.springbootbasic.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// 配置提示效果案例

// 在自定义的类上添加配置注解和添加pom.xml插件-spring-boot-configuration-processor

@ConfigurationProperties(prefix = "user")
@Component // 标注类为 Spring 容器的Bean
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 有参构造
@Data // 所有属性都有get/set方法
@EqualsAndHashCode
public class User {
  private String name;
  private Integer age;
  private String birth;
}
