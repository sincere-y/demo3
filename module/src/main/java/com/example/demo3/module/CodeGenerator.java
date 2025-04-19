package com.example.demo3.module;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {

    public static void main(String[] args) {

        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/1234?allowPublicKeyRetrieval=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true&useAffectedRows=true", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Yang") // 设置作者
                            .outputDir("module/src/main/java" ); // 输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.demo3.module") // 设置父包名
                            .entity("entity") // 设置实体类包名
                            .mapper("mapper") // 设置 Mapper 接口包名
                            .service("service") // 设置 Service 实现类包名
                            .xml("mapper") // 设置 Mapper XML 文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "module/src/main/resources/mapper")); // 设置mapperXml生成路径

                })
                .strategyConfig(builder -> {
                    builder.addInclude("gun_tag_relation","tag") // 设置需要生成的表名

                            .entityBuilder()
                            .javaTemplate("templateconfig/entity.java")
                            .naming(NamingStrategy.underline_to_camel)
                            .enableLombok()// 启用 Lombok
                            .logicDeleteColumnName("is_deleted")
                            .logicDeletePropertyName("isDeleted")

                            .serviceBuilder().disableServiceImpl()

                            .serviceTemplate("templateconfig/service.java")
                            .formatServiceFileName("%sService")

                            .mapperBuilder()
                            .mapperTemplate("templateconfig/mapper.java")
                            .mapperXmlTemplate("templateconfig/mapper.xml")
                            .enableMapperAnnotation()
                            .controllerBuilder().disable()
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成

    }
}
