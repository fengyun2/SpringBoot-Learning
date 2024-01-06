package com.win.springbootbasic.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  private ArrayList<User> users = new ArrayList<User>();

  @GetMapping("list")
  public ArrayList<User> list() {
    return users;
  }

  @PostMapping("add")
  public User add(User user) {
    users.add(user);
    return user;
  }

  @GetMapping("getByName")
  public User getUserByName(String name) {
    return users.stream().filter(user -> user.getName().equals(name)).findFirst().get();
  }
}
