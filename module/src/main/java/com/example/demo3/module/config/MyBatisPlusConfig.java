package com.example.demo3.module.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;


@Configuration
public class MyBatisPlusConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // 设置别名包
        sessionFactory.setTypeAliasesPackage("com.example.demo3.common.entity");
        // 设置Mapper XML文件位置
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath*:mapper/**/*.xml")
        );

        return sessionFactory.getObject();
    }
}