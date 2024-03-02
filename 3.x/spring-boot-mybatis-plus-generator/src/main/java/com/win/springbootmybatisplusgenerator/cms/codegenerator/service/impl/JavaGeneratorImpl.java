package com.win.springbootmybatisplusgenerator.cms.codegenerator.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.win.springbootmybatisplusgenerator.cms.codegenerator.service.IJavaGeneratorService;

@Service
public class JavaGeneratorImpl  implements IJavaGeneratorService {
  @Override
  public void generatorCode(String tableName) {
    String url = "jdbc:mysql://localhost:3306/mybatis-plus-generator";
    String author = "win";
    String packageParent = "com.win.springbootmybatisplusgenerator.cms";
    String moduleName = "system";
    // String driveDriverName = "com.mysql.cj.jdbc.Driver";
    FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(url, "root", "root123");

    fastAutoGenerator.globalConfig(builder -> {
            builder.author(author) // 设置作者
                    // .enableSwagger() // 开启swagger 模式
                    .disableOpenDir() // 禁止打开输出目录
                    .dateType(DateType.TIME_PACK)
                    .commentDate("yyyy-MM-dd HH:mm:ss")
                    .outputDir(System.getProperty("user.dir") + "/src/main/java") // 指定输出目录
                    .build();
        })
        .packageConfig(builder -> {
            builder.parent(packageParent) // 设置父包名
                  .moduleName(moduleName) // 设置父包名模块名
                  .entity("entity") // 实体类包名，默认为 entity
                  .mapper("mapper") // mapper接口包名
                  .service("service") // service包名
                  .controller("controller") // controller包名
                  .build();
                  // .xml("mapper.xml"); // 映射文件包名
                  // .build();
        })
        .strategyConfig(builder -> {
            builder.addInclude(tableName); // 设置需要生成的表名
                  // .addTablePrefix(null) // 设置需要过滤表前缀

            builder.entityBuilder()
                  .enableFileOverride()
                  .enableLombok()
                  .enableChainModel()
                  .enableTableFieldAnnotation()
                  .versionColumnName("version")
                  .logicDeleteColumnName("deleted")
                  .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                  .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                  .addSuperEntityColumns("id", "create_by", "create_time", "updated_by", "updated_time") // 添加父类公共字段
                  // .addIgnoreColumns("age") // 添加忽略字段
                  .addTableFills(new Column("create_time", FieldFill.INSERT))
                  .addTableFills(new Column("updated_time", FieldFill.INSERT_UPDATE))
                  .idType(IdType.AUTO)
                  // .formatFileName("%sEntity")
                  .build();
            builder.controllerBuilder()
                  .enableFileOverride()
                  .enableHyphenStyle() // 开启驼峰转连字符
                  .enableRestStyle() // 开启生成 @RestController 控制器
                  .build();
            builder.serviceBuilder()
                  .enableFileOverride()
                  .build();
            builder.mapperBuilder()
                  .enableFileOverride()
                  .mapperAnnotation(Mapper.class)
                  .enableBaseResultMap()
                  .enableBaseColumnList()
                  .build();

                  // .disableSerialVersionUID();
                  // .build();
          }
        )
        // 配置代码生成模版
        .templateConfig(builder -> {
            builder.disable(TemplateType.ENTITY)
                    .entity("/templates/entity.java.vm")
                    .service("/templates/service.java.vm")
                    .serviceImpl("/templates/serviceImpl.java.vm")
                    .mapper("/templates/mapper.java.vm")
                    .xml("/templates/mapper.xml.vm")
                    .controller("/templates/controller.java.vm")
                    .build();
        })
        .templateEngine(new VelocityTemplateEngine())
        .execute();
  }
}
