package com.win.springbootmybatisplusgenerator.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

@SpringBootApplication
public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);

    // init();
	}

  public static void init() {
    FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis-plus-generator", "root", "root123")
        .globalConfig(builder ->
                builder.author("author").outputDir(System.getProperty("user.dir") + "/src/main/java")
                .build())
        .packageConfig(
                builder -> builder.parent("com.win.springbootmybatisplusgenerator.cms").moduleName("user").build())
        .strategyConfig(
                builder -> builder.addInclude("user").entityBuilder().enableLombok()
                        .disableSerialVersionUID().build()
        )
        // .templateEngine(new FreemarkerTemplateEngine())
        .execute();

    // new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/mybatis-plus-generator","root","root123")
    // .build();

    // FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3366/mybatis-plus-generator?serverTimezone=Asia/Shanghai", "root", "root123")
    //             //全局配置
    //             .globalConfig(builder -> {
    //                builder.author("win") //设置作者
    //                        .commentDate("yyyy-MM-dd") //注释日期格式
    //                        .outputDir(System.getProperty("user.dir") + "/spring-boot-mybatis-plus-generator/src/main/java"); //指定输出目录
    //                       //  .enableFileOverride(); //覆盖文件
    //             })
    //             //包配置
    //             .packageConfig(builder -> {
    //                 builder.parent("com.win.springbootmybatisplusgenerator.cms") //包名的前缀
    //                         .entity("domain") //实体类包名
    //                         .mapper("mapper") //mapper接口包名
    //                         .service("service") //service包名
    //                         .controller("controller") //controller包名
    //                         .xml("mapper"); //映射文件包名
    //             })
    //             //策略配置
    //             .strategyConfig(builder -> {
    //                 builder.addInclude("User") //设置需要生成的表名或排除的表名，支持多张表
    //                         .addTablePrefix("tbl_") //设置表名前缀
    //                         .addTableSuffix("") //设置表名后缀
    //                         .entityBuilder() //开始实体类配置
    //                         .enableLombok() //开启lombok模型
    //                         .naming(NamingStrategy.underline_to_camel) //表名下划线转驼峰
    //                         .columnNaming(NamingStrategy.underline_to_camel); //列名下划线转驼峰
    //             })
    //             .execute();
  }

}
