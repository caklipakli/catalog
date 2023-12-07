package com.caklipakli.catalog;

import lombok.extern.log4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
@Log4j2
public class CatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
        log.info("Starting app....");
    }

}
