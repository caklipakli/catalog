package com.caklipakli.catalog;

import com.caklipakli.catalog.tasks.GenerateReport;
import com.caklipakli.catalog.tasks.Process;
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

	private Process process;
	private GenerateReport generateReport;

	private ApplicationContext context;
	@Autowired
	public CatalogApplication(Process process, GenerateReport generateReport) {
		this.process = process;
		this.generateReport = generateReport;
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("Starting....");

		CommandLine commandLine = new CommandLine(process);
		commandLine.addSubcommand("report", generateReport);

		commandLine.parseWithHandler(new CommandLine.RunAll(), args);

		log.info("Closing app as everything finished....");

		System.exit(0);
	}

}
