package com.epam.esm.zotov.module2.dataaccess;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.epam.esm.zotov.module2.dataacces")
@PropertySource("classpath:db.properties")
public class DataAccessConfig {
    @Value("${driver}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${user}")
    private String user;
    @Value("${password}")
    private String password;
    @Value("${test.url}")
    private String testUrl;

    @Profile("prod")
    @Bean
    public DataSource dataSource() {
        return dataSourceBuilder(driver, url, user, password);
    }

    @Profile("dev")
    @Bean
    public DataSource devDataSource() {
        return dataSourceBuilder(driver, testUrl, user, password);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    private DataSource dataSourceBuilder(String driver, String url, String user, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}