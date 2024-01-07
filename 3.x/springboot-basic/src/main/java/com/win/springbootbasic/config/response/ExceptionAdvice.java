package com.win.springbootbasic.config.response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
  @ExceptionHandler(Exception.class)
  public ResponseResult<String> Exception(Exception e) {
    log.error("未知异常！", e);
    e.printStackTrace();
    return ResponseResult.fail(ResponseStatus.valueOf("HTTP_STATUS_400").getDescription());
  }
}
