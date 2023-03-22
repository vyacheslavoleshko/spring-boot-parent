package org.example;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(DataSource.class)
public class TestContainersAutoConfiguration {

    /**
     * Autoconfigures TestContainers datasource.
     * All the projects that have this Autoconfiguration as a dependency
     * can start TestContainers datasource if specify the respective
     * property in application.properties (see conditions below)
     *
     * @return DataSource object configured to use TestContainers
     */
    @Bean
    @ConditionalOnProperty(
            name = "use-test-containers",
            havingValue = "true")
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(
                "org.testcontainers.jdbc.ContainerDatabaseDriver"
        );
        dataSource.setUrl("jdbc:tc:postgresql:10.9://localhost/test");
        dataSource.setUsername("test");
        dataSource.setPassword("test");

        return dataSource;
    }
}
