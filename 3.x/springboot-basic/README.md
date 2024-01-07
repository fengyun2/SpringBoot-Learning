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


## 参考文档

1. [SpringBoot-基础篇](https://jwt1399.top/posts/33757.html)
2. [SpringBoot-整合篇](https://jwt1399.top/posts/58591.html)
3. [Java 全栈知识体系](https://pdai.tech) , 推荐，比较全，有案例