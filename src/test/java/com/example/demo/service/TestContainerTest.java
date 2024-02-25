package com.example.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;




@Testcontainers
@SpringBootTest
// @TestPropertySource(locations = "application.yml")
// @AutoConfigureMockMvc(addFilters = false)
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = ClassUsingProperty.class)
// @TestPropertySource(locations = "classpath:application.yml")
public class TestContainerTest {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.2-alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("init-ddl.sql");

    

    @DynamicPropertySource
    public static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("db.url", postgreSQLContainer::getJdbcUrl);
        registry.add("db.user", postgreSQLContainer::getUsername);
        registry.add("db.password", postgreSQLContainer::getPassword);
        // registry.add("spring.jpa.hibernate.ddl-auto", "never");
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_containers() {
        int a = 1;
        int b = 2;
        
        Assertions.assertThat(a+b).isEqualTo(3);
        
    }

}