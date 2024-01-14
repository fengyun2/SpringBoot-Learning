package com.win.springboot.aop.service;

import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {
  @Override
  public int divide(int x, int y) {
    System.out.println("=========== CalcService 被调用了");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (y == 0) {
      throw new RuntimeException("除数不能为0");
    } else {
      System.out.println("=========== CalcService 调用成功");
      return x / y;
    }
  }
}
