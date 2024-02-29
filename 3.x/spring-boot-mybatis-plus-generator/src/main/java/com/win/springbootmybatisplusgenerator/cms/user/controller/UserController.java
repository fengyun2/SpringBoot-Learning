package com.win.springbootmybatisplusgenerator.cms.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.win.springbootmybatisplusgenerator.cms.user.entity.User;
import com.win.springbootmybatisplusgenerator.cms.user.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-02-29
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserServiceImpl userService;

  @GetMapping
  public List<User> getAll() {
    return userService.list();
  }

  @PostMapping("/insert")
  public User insert(@RequestBody User user) {
    userService.save(user);
    return user;
  }

  /**
   * http://localhost:8080/user/selectById
   * @param userId
   * @return
   */
  @GetMapping("/selectById")
  public User selectById(Long userId) {
    return userService.getById(userId);
  }
}
