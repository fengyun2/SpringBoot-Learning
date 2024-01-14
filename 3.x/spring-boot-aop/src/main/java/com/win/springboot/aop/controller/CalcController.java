package com.win.springboot.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.win.springboot.aop.service.CalcService;

@RestController
@RequestMapping("/calc")
public class CalcController {
  @Autowired
  private CalcService calcService;

  @GetMapping("divide")
  public int divide(@RequestParam("param1") int param1, @RequestParam("param2") int param2) {
    return calcService.divide(param1, param2);
  }
}
