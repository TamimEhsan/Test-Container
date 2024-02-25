package com.example.demo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(VaultDBConfig.class)
public class DatasourceConfig {

    private final VaultDBConfig vaultDBConfig;

    /**
     * Instantiates a new Datasource config.
     *
     * @param vaultDBConfig the vault db config
     */
    public DatasourceConfig(VaultDBConfig vaultDBConfig) {
        this.vaultDBConfig = vaultDBConfig;
    }

    /**
     * Gets data source.
     *
     * @return the data source
     */
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        // System.out.println("vaultDBConfig.getUrl() = " + vaultDBConfig.getUrl());
        dataSourceBuilder.url(vaultDBConfig.getUrl());
        dataSourceBuilder.username(vaultDBConfig.getUser());
        dataSourceBuilder.password(vaultDBConfig.getPassword());

        return dataSourceBuilder.build();
    }
}
