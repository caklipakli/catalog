package com.caklipakli.catalog.tasks;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

@Log4j2
public class TestDatabaseConnection {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {

        try {
            dataSource.getConnection();
            log.info("Connection successful");
        } catch (Exception e) {
            log.error("Connection failed");
        }
    }
}
