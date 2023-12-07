package com.caklipakli.catalog;

import com.caklipakli.catalog.exception.*;
import com.caklipakli.catalog.tasks.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import picocli.CommandLine;

@SpringBootApplication
@Log4j2
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
		log.info("Starting app....");
	}

}
