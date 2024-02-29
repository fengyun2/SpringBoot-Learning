# 使用注意点

### 依赖包：
1. mybatis-plus-spring-boot3-starter：一定要安装对应版本springboot对应版本的插件
2. druid-spring-boot-starter：需要安装数据源(druid或h2或其他)

### 需要在 application.yml 配置数据库连接，否则连接不上数据库，进而无法启动

### 数据表


### 生成后的代码如（src/main/java/com/win/springbootmybatisplusgenerator/cms/user/mapper/UserMapper.java）需要添加`@Mapper`注解，否则无法启动
```java
package com.win.springbootmybatisplusgenerator.cms.user.mapper;

import com.win.springbootmybatisplusgenerator.cms.user.entity.User;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-02-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

```

