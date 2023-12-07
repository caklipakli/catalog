package com.caklipakli.catalog;

import com.caklipakli.catalog.tasks.*;
import lombok.extern.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.sql.*;
import java.util.concurrent.*;

@Component
@Command(name = "process", description = "process the tasks")
@Log4j2
public class AppCommand implements Callable {

    @Autowired
    private SaveData saveProcess;
    @Autowired
    private GenerateReport generateReport;

    public static final String ERROR_LOG_FILE = "src/main/resources/importLog.csv";

    public static StringBuilder errors = new StringBuilder();

    static int exitCode;

    @Option(names = {"-t"}, description = "Task to be performed")
    private String option;

    @Override
    public Object call() throws Exception {

        log.info("Simulate a listing reporting system");

        if ((option != null) && option.equals("--save")) {
            saveProcess.saveData();
            System.exit(0);
        } else if ((option != null) && option.equals("--report")) {
            generateReport.generateReport();
            System.exit(0);
            }
        saveProcess.saveData();
        generateReport.generateReport();

        System.exit(0);
        return 0;
    }
}
