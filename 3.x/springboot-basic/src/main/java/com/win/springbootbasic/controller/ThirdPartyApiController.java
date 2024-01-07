package com.win.springbootbasic.controller;

// import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
// import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.win.springbootbasic.config.response.ResponseResult;


/**
 * 如何访问外部接口
 * 1. 采用原生的Http请求
 * 2. 采用Feign进行消费
 * 3. 采用RestTemplate方法（推荐）
 */
@RestController
@RequestMapping("/thirdPartyApi")
public class ThirdPartyApiController {
  // 1. 采用原生的Http请求
  public static JSONObject doGet() {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    // 定义请求的参数
    URI uri = null;
    try {
      uri = new URIBuilder("https://cnodejs.org/api/v1/topics").setParameter("page", "0").setParameter("limt", "1").build();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    // 创建http GET请求
    HttpGet httpGet = new HttpGet(uri);
    httpGet.setHeader("Content-Type", "application/json;charset=utf8");
    // response对象
    CloseableHttpResponse response = null;
    JSONObject jsonObject = null;
    try {
      // 执行 http GET请求
      response = httpClient.execute(httpGet);
      // 判断返回状态是否为200
      if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String result = EntityUtils.toString(response.getEntity());
        // FileUtils.writeStringToFile(new File("./topics.json"), result, "UTF-8");
        jsonObject = JSON.parseObject(result, JSONObject.class);
      }
    } catch (Exception e) {
      // TODO: handle exception
      throw new RuntimeException(e);
    }
    return jsonObject;
  }

  @GetMapping("httpGet")
  public ResponseResult<String> httpGet() throws ParseException {
    JSONObject jsonObject = ThirdPartyApiController.doGet();
    // String text = "{\"id\": 2,\"name\": \"fastjson2\"}";
    // JSONObject obj = JSON.parseObject(text);
    // System.out.println(obj.getString("name"));
    return ResponseResult.success(jsonObject.toString());
  }
  @GetMapping("getForEntity")
  public ResponseResult<String> getForEntity() throws ParseException {
    RestTemplate restTemplate = new RestTemplate();
    Map<String, String> params = new HashMap<>();
    params.put("page", "0");
    params.put("limit", "1");
    ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://cnodejs.org/api/v1/topics", String.class, params);
    return ResponseResult.success(responseEntity.getBody());
  }
}
