package com.caklipakli.catalog;

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
public class CatalogApplication implements CommandLineRunner {

	private ProcessTasks tasks;


	private ApplicationContext context;
	@Autowired
	public CatalogApplication(ProcessTasks process) {
		this.tasks = process;
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("Starting....");

		new CommandLine(tasks).execute(args);


	}

}
