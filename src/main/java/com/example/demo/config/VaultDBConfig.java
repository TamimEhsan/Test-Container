package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties("db")
public class VaultDBConfig {
    private String user;
    private String password;
    private String url;
}
