package com.caklipakli.catalog;

import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;
import picocli.CommandLine.IFactory;
import picocli.CommandLine;

@Component
@Log4j2
public class Runner implements CommandLineRunner, ExitCodeGenerator {

    private final IFactory factory;

    private final AppCommand myCommand;

    private int exitCode;

    @Autowired
    public Runner(IFactory factory, AppCommand myCommand) {
        this.factory = factory;
        this.myCommand = myCommand;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Inside Runner");

        exitCode = new CommandLine(myCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
