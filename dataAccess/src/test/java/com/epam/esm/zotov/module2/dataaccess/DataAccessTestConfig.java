package com.epam.esm.zotov.module2.dataaccess;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

public class DataAccessTestConfig {
    @Profile("test")
    @Bean
    DataSource testDataSource() throws IOException {
        EmbeddedPostgres embeddedPg = EmbeddedPostgres.start();
        return embeddedPg.getPostgresDatabase();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        PlatformTransactionManager manager = new DataSourceTransactionManager(dataSource);
        return manager;
    }
}