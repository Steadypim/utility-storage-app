package dev.steadypim.thewhitehw.homework1;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@TestConfiguration
public abstract class AbstractTestcontainers {

    @Container
    protected static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest");
    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> registerDriverProperties(DynamicPropertyRegistry registry) {
        registry.add("postgresql.driver", postgreSQLContainer::getDriverClassName);
        return postgreSQLContainer;
    }
}
