package com.win.springbootbasic.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.springbootbasic.config.response.ResponseResult;

/**
 * 统一返回接口封装案例
 */
@RestController
@RequestMapping("/restful")
public class RestResponeResultDemo {
  /**
   * http://localhost:8080/restful/getUserName1
   * @return
   * {"timestamp":1704613116064,"status":"200","message":"success","data":"Hello"}
   */
  @GetMapping("getUserName1")
  public ResponseResult<String> getUserName1() {
    return ResponseResult.success("Hello");
  }

  /**
   * http://localhost:8080/restful/getUserName2
   * @return
   * {"timestamp":"2024-01-07T07:39:14.146+00:00","status":500,"error":"Internal Server Error","message":"Cannot invoke \"Object.toString()\" because the return value of \"java.util.HashMap.get(Object)\" is null","path":"/restful/getUserName2"}
   */
  @GetMapping("getUserName2")
  public ResponseResult<String> getUserName2() {
    HashMap hashMap = new HashMap<>();
    return ResponseResult.success(hashMap.get(0).toString()); // 模拟一个空指针异常
  }

  /**
   * http://localhost:8080/restful/getUserNameError
   * @return
   * {"timestamp":1704613419959,"status":"500","message":"获取用户信息异常","data":null}
   */
  @GetMapping("getUserNameError")
  public ResponseResult<String> getUserNameError() {
    return ResponseResult.fail("获取用户信息异常");
  }
}
