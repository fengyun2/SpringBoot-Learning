package com.win.springbootbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping
  public String home() {
    return "Hello, Spring Boot 3";
  }
  @GetMapping("/hello")
  public String hello() {
    return "Hello World!";
  }
}
