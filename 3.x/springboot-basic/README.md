# 技术栈

- Lombok 快速制作实体类
- Redis 内存数据库
- Mybatis 通用数据库操作
- Mybatis-plus 简化Mybatis操作
- SpringMVC
- SpringSecurity
- SpringBoot

### 数据表

- Book 测试表

```sql
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_book
-- ----------------------------
DROP TABLE IF EXISTS `tbl_book`;
CREATE TABLE `tbl_book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_book
-- ----------------------------
INSERT INTO `tbl_book` VALUES (1, '三体', '科幻', '大刘的巅峰之作，将中国科幻推向世界舞台。总共分为三部曲：《地球往事》、《黑暗森林》、《死神永生》。');
INSERT INTO `tbl_book` VALUES (2, '格林童话', '童话', '睡前故事。');
INSERT INTO `tbl_book` VALUES (3, 'Spring 5设计模式', '计算机理论', '深入Spring源码剖析Spring源码中蕴含的10大设计模式');
INSERT INTO `tbl_book` VALUES (4, 'Spring MVC+ MyBatis开发从入门到项目实战', '计算机理论', '全方位解析面向Web应用的轻量级框架,带你成为Spring MVC开发高手');
INSERT INTO `tbl_book` VALUES (5, '轻量级Java Web企业应用实战', '计算机理论', '源码级剖析Spring框架,适合已掌握Java基础的读者');
INSERT INTO `tbl_book` VALUES (6, 'Java核心技术卷|基础知识(原书第11版)', '计算机理论', 'Core Java第11版，Jolt大奖获奖作品，针对Java SE9、10、 11全面更新');
INSERT INTO `tbl_book` VALUES (7, '深入理解Java虚拟机', '计算机理论', '5个维度全面剖析JVM,面试知识点全覆盖');
INSERT INTO `tbl_book` VALUES (8, 'Java编程思想(第4版)', '计算机理论', 'Java学习必读经典殿堂级著作!赢得了全球程序员的广泛赞誉');
INSERT INTO `tbl_book` VALUES (9, '零基础学Java (全彩版)', '计算机理论', '零基础自学编程的入门]图书，由浅入深，详解Java语言的编程思想和核心技术');
INSERT INTO `tbl_book` VALUES (10, '直播就该这么做:主播高效沟通实战指南', '市场营销', '李子柒、李佳琦、薇娅成长为网红的秘密都在书中');
INSERT INTO `tbl_book` VALUES (11, '直播销讲实战一本通', '市场营销', '和秋叶一起学系列网络营销书籍');
INSERT INTO `tbl_book` VALUES (12, '直播带货:淘宝、天猫直播从新手到高手', '市场营销', '一本教你如何玩转直播的书， 10堂课轻松实现带货月入3W+');
INSERT INTO `tbl_book` VALUES (13, 'Spring实战第5版', '计算机理论', 'Spring入门经典教程,深入理解Spring原理技术内幕');
INSERT INTO `tbl_book` VALUES (14, 'Spring 5核心原理与30个类手写实战', '计算机理论', '十年沉淀之作，写Spring精华思想');

SET FOREIGN_KEY_CHECKS = 1;
```

### Mac安装Redis

- 使用 Homebrew 安装 Redis

```bash
brew install redis
```

- 启动 Redis

```bash
redis-server

# 或者使用 brew services 启动
brew services start redis

# 手动启动命令：
redis-server /usr/local/etc/redis.conf
```

- 停止 Redis

```bash
brew services stop redis
```

- 检查 Redis 状态

```bash
redis-cli ping
```

如果返回 PONG，说明 Redis 已经启动成功。

- 另外，你可以让Redis随着机器启动而自动启动

```bash
ln -sfv /usr/local/opt/redis/*.plist ~/Library/LaunchAgents
```

### Lombok

- 实例类

```java
// 简化JavaBean开发
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
}
```

实体类的开发可以手工生成get/set方法，然后覆盖toString()方法。不过这一套操作书写很繁琐，可以使用 `Lombok` 来简化JavaBean开发。

引入 Lombok,用注解代替构造器、getter/setter、toString()等代码。

- @Data ： 注在类上，提供类的get、set、equals、hashCode、toString等方法

- @AllArgsConstructor ：注在类上，提供类的全参构造

- @NoArgsConstructor ：注在类上，提供类的无参构造

- @Setter ：注在属性上，提供 set 方法

- @Getter ：注在属性上，提供 get 方法

- @EqualsAndHashCode ：注在类上，提供对应的 equals 和 hashCode 方法

- @Log4j/@Slf4j ：注在类上，提供对应的 Logger 对象，变量名为 log

Lombok 还可以简化日志开发，例如下面代码：

```java
@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String home(@RequestParam("name") String name){
        log.info("请求进来了....");
        return "Hello, Spring Boot 3!"+"你好："+name;
    }
}
```

## 如何统一接口封装（消息一致性开发）

如果 SpringBoot 不使用统一返回格式，默认会有如下三种返回情况。

- 返回字符串
```java
@GetMapping("/getUserName")
public String getUserName(){
    return "Hello";
}

// 返回结果：Hello
```
- 返回实体类对象
```java
@GetMapping("/getUserName")
public User getUserName(){
    return new User("win", 16);
}

// 返回结果：{ "name": "win", "age": 16 }
```
- 返回异常
```java
@GetMapping("/getUserName")
publics static String getUserName(){
    HashMap hashMap = new HashMap();
    return hashMap.get(0).toString(); // 模拟一个空指针异常
}

// 返回结果：{ "timestamp": 1704611303413, "status": 500, "error": "Internal Server Error", "path": "/getUserName" }
```

对于上面这几种情况，如果整个项目没有定义统一的返回格式，不同开发人员可能会定义不同的返回格式，这样会使前后端对接出现一些问题。

### a. 定义返回标准

一个标准的返回格式至少包含 3 部分：

```txt
code: 状态码
message: 接口调用的提示信息
data: 返回数据
```

例如：

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "name": "win",
    "age": 16
  }
}
```

- 步骤 1：定义数据返回格式

```java
// ResponseResult.java

package com.win.springbootbasic.config.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseResult<T> {

    /**
     * response timestamp.
     */
    private long timestamp;

    /**
     * response code, 200 -> OK.
     */
    private String status;

    /**
     * response message.
     */
    private String message;

    /**
     * response data.
     */
    private T data;

    /**
     * response success result wrapper.
     *
     * @param <T> type of data class
     * @return response result
     */
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    /**
     * response success result wrapper.
     *
     * @param data response data
     * @param <T>  type of data class
     * @return response result
     */
    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder().data(data)
                .message(ResponseStatus.SUCCESS.getDescription())
                .status(ResponseStatus.SUCCESS.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * response error result wrapper.
     *
     * @param message error message
     * @param <T>     type of data class
     * @return response result
     */
    public static <T extends Serializable> ResponseResult<T> fail(String message) {
        return fail(null, message);
    }

    /**
     * response error result wrapper.
     *
     * @param data    response data
     * @param message error message
     * @param <T>     type of data class
     * @return response result
     */
    public static <T> ResponseResult<T> fail(T data, String message) {
        return ResponseResult.<T>builder().data(data)
                .message(message)
                .status(ResponseStatus.FAIL.getResponseCode())
                .timestamp(System.currentTimeMillis())
                .build();
    }


}
```

- 步骤 2：定义状态码

```java
// ResponseStatus.java

package com.win.springbootbasic.config.response;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS("200", "success"),
    FAIL("500", "failed"),

    HTTP_STATUS_200("200", "ok"),
    HTTP_STATUS_400("400", "request error"),
    HTTP_STATUS_401("401", "no authentication"),
    HTTP_STATUS_403("403", "no authorities"),
    HTTP_STATUS_500("500", "server error");

    public static final List<ResponseStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500
            ));

    /**
     * response code
     */
    private final String responseCode;

    /**
     * description.
     */
    private final String description;

}

```

- 步骤 3：使用

```java
//示例1
@GetMapping("/getUserName")
public Result getUserName(){
    return Result.success("Hello");
}

//示例2
@GetMapping("/getUserName")
public static Result getUserName(){
  HashMap hashMap = new HashMap();
  return Result.success(hashMap.get(0).toString()); // 模拟一个空指针异常
}
```

返回结果如下：

```txt
# 示例1结果
{
    "status": 0,
    "message": "成功",
    "data": "Hello"
}

# 示例2结果
{
    "timestamp": 1704611303413,
    "status": 500,
    "error": "Internal Server Error",
    "path": "/getUserName"
}
```

### b. 统一接口返回

前面步骤不够优雅，可以继续改进，用 `@RestControllerAdvice` 注解，拦截后端返回的数据，实现 `ResponseBodyAdvice` 接口对数据做一层包装再返回给前端。

> `ResponseBodyAdvice`: 该接口是 SpringMVC 4.1 提供的，它允许在 执行`@ResponseBody` 后自定义返回数据，用来封装统一数据格式返回；拦截 Controller 方法的返回值，统一处理返回值/响应体，一般用来统一返回格式，加解密，签名等。
`@RestControllerAdvice`: 该注解是 Controller 的增强版，可以全局捕获抛出的异常，全局数据绑定，全局数据预处理。

- 步骤 1：新建 ResponseAdvice 类，该类用于统一封装 Controller 中接口的返回结果。实现 ResponseBodyAdvice 接口，实现 supports、beforeBodyWrite 方法。

```java
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 是否开启功能 true:开启
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 处理返回结果
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //处理字符串类型数据
        if(o instanceof String){
            try {
                return objectMapper.writeValueAsString(Result.success(o));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        //返回类型是否已经封装
        if(o instanceof Result){
            return o;
        }
        return Result.success(o);
    }
}
```


### c. 全局异常处理



## 参考文档

1. [SpringBoot-基础篇](https://jwt1399.top/posts/33757.html)
2. [SpringBoot-整合篇](https://jwt1399.top/posts/58591.html)
3. [Java 全栈知识体系](https://pdai.tech) , 推荐，比较全，有案例