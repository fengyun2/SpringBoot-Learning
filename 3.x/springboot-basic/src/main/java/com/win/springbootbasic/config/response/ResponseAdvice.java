package com.win.springbootbasic.config.response;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.MethodParameter;

/**
 * restful统一接口返回(统一封装Controller接口的返回结果)
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

  // 排除swagger相关类
  private static final String SPRING_FOX_STR = "springfox";

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * 是否开启功能 true: 开启
   */
  @Override
  public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    return true;
    // 排除拦截swagger相关
    // return methodParameter.getDeclaringClass().getName().contains(SPRING_FOX_STR);
  }

  /**
   * 处理返回结果
   */
  @Override
  public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
    // 处理字符串类型数据
    // 避免使用StringHttpMessageConvert处理使用Resp类型封装字符串后的数据，导致数据处理异常问题
    if (o instanceof String) {
      try {
        // 将数据包装在 ResponseResult 里后，再转换为json字符串响应给前端
        return objectMapper.writeValueAsString(ResponseResult.success(o));
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    }
    // 返回类型是否已经封装(防止重复包裹的问题出现)，解决与统一异常处理产生的冲突问题
    if (o instanceof ResponseResult) {
      return o;
    } else {
      return ResponseResult.success(o);
    }
  }
}
