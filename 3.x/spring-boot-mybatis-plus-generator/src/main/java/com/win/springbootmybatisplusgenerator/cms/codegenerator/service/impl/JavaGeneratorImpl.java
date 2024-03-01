package com.win.springbootmybatisplusgenerator.cms.codegenerator.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
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
                  .enableTableFieldAnnotation()
                  .addTableFills()
                  .idType(IdType.AUTO)
                  .build();
            builder.controllerBuilder()
                  .enableFileOverride()
                  .enableRestStyle()
                  .build();
            builder.serviceBuilder()
                  .enableFileOverride()
                  .build();
            builder.mapperBuilder()
                  .enableFileOverride()
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
