package com.win.springbootspringdocopenapi.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.springbootspringdocopenapi.entity.param.UserParam;
import com.win.springbootspringdocopenapi.entity.vo.AddressVo;
import com.win.springbootspringdocopenapi.entity.vo.UserVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
  /**
   * http://localhost:3003/user/add
   * http://localhost:3003/swagger-ui/index.html
   * @param userParam
   * @return user
   */
  @Operation(summary = "添加用户", description = "根据用户参数添加用户")
  @Parameter()
  @PostMapping("add")
  public ResponseEntity<String> add(@RequestBody UserParam userParam) {
    return ResponseEntity.ok("添加用户成功");
  }

  /**
   * http://localhost:3003/user/list
   * http://localhost:3003/swagger-ui/index.html
   * @return user list
   */
  @Operation(summary = "查询用户列表", description = "查询用户列表")
  @GetMapping("list")
  public ResponseEntity<List<UserVo>> list() {
    List<UserVo> userVoList = Collections.singletonList(UserVo.builder().name("win").age(16).address(AddressVo.builder().city("SZ").zipcode("10001").build()).build());
    return ResponseEntity.ok(userVoList);
  }

  /**
   * http://localhost:3003/user/find/win
   * @param name
   * @return user
   */
  @Operation(summary = "根据用户名查询用户", description = "根据用户名查询用户")
  @Parameter(name = "name",description = "用户名", in = ParameterIn.PATH)
  @GetMapping("find/{name}")
  public ResponseEntity<UserVo> find(@Parameter(description = "用户名") @PathVariable String name) {
    UserVo userVo = UserVo.builder().name(name).age(16).address(AddressVo.builder().city("SZ").zipcode("10001").build()).build();
    return ResponseEntity.ok(userVo);
  }
}
