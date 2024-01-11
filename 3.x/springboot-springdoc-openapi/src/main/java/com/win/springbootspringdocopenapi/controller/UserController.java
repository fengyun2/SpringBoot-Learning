package com.win.springbootspringdocopenapi.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.springbootspringdocopenapi.entity.param.UserParam;
import com.win.springbootspringdocopenapi.entity.vo.AddressVo;
import com.win.springbootspringdocopenapi.entity.vo.UserVo;

@RestController
@RequestMapping("/user")
public class UserController {
  /**
   * http://localhost:3003/user/add
   * @param userParam
   * @return user
   */
  @PostMapping("add")
  public ResponseEntity<String> add(@RequestBody UserParam userParam) {
    return ResponseEntity.ok("添加用户成功");
  }

  /**
   * http://localhost:3003/user/list
   * @return user list
   */
  @GetMapping("list")
  public ResponseEntity<List<UserVo>> list() {
    List<UserVo> userVoList = Collections.singletonList(UserVo.builder().name("win").age(16).address(AddressVo.builder().city("SZ").zipcode("10001").build()).build());
    return ResponseEntity.ok(userVoList);
  }
}
