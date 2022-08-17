package one.digitalinnovation.parking.controller;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerBase {

    static final PostgreSQLContainer POSTEGRE_SQL_CONTAINER;

    static {
        POSTEGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:10-alpine");
        POSTEGRE_SQL_CONTAINER.start();
        System.setProperty("spring.datasource.url", POSTEGRE_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTEGRE_SQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTEGRE_SQL_CONTAINER.getPassword());
    }
}
