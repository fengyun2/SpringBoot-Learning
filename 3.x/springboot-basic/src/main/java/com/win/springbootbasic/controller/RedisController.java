package com.win.springbootbasic.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {
  // 注意：这里@Autowired是报错的，因为@Autowired按照类名注入的
  @Resource
  private RedisTemplate<String, User> redisTemplate;

  @GetMapping("get/{userName}")
  public User getUser(@PathVariable String userName) {
    return redisTemplate.opsForValue().get(userName);
  }

  @PostMapping("add")
  public User addUser(@RequestBody User user) {
    User newUser = new User();
    newUser.setName(user.getName());
    newUser.setAge(user.getAge());
    newUser.setBirth(user.getBirth());
    redisTemplate.opsForValue().set(user.getName(), user);
    return user;
  }

  @DeleteMapping("delete/{userName}")
  public Boolean deleteUser(@PathVariable String userName) {
    return redisTemplate.delete(userName);
  }

  @PutMapping("update")
  public User updateUser(@RequestBody User user) {
    User newUser = new User();
    newUser.setName(user.getName());
    newUser.setAge(user.getAge());
    newUser.setBirth(user.getBirth());
    redisTemplate.opsForValue().set(user.getName(), user);
    return user;
  }
}