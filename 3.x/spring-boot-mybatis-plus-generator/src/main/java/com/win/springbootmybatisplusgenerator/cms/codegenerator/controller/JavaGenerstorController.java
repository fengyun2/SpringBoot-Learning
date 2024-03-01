package com.win.springbootmybatisplusgenerator.cms.codegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.springbootmybatisplusgenerator.cms.codegenerator.service.IJavaGeneratorService;

@RestController
@RequestMapping("/generator")
public class JavaGenerstorController {
  @Autowired
  private IJavaGeneratorService javaGeneratorService;
  /**
   * 代码生成
   * @param tableName
   * @return
   * @example http://localhost:8080/generator/java?tableName=user
   */
  @GetMapping("/java")
  public String generatorCode(String tableName) {
    if (tableName == null || tableName == "") {
      return "表名不能为空";
    }
    javaGeneratorService.generatorCode(tableName);
    return "代码生成成功";
  }
}
