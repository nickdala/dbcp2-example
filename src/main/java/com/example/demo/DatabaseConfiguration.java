package com.example.demo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import liquibase.integration.spring.SpringLiquibase;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    
    @Bean
    public DataSource embedDataSource(@Value("${DatabaseConfigEmbedDriver}") String driver,
                                           @Value("${DatabaseConfigEmbedUrl}") String url,
                                           @Value("${DatabaseConfigEmbedUsername}") String username,
                                           @Value("${DatabaseConfigEmbedPassword}") String password) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:liquibase/db-changelog.xml");
        return springLiquibase;
    }
}
