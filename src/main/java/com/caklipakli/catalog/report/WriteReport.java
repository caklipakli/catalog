package com.caklipakli.catalog.report;

import com.caklipakli.catalog.tasks.Process;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
public class WriteReport {

    public static final String ERROR_LOG_FILE_NAME = "src/main/resources/importLog.csv";

    public WriteReport() {
    }

    public static void writeImportLogReport(String fileName) {

        Path path = Paths.get(fileName);

        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Writer writer = new BufferedWriter(new FileWriter(path.toFile()))) {

            if (Process.errorsSb.length() > 0) {
                writer.write(Process.errorsSb.toString());
            } else {
                writer.write("no errors at the moment");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
            log.info("Number of lines in importLog.csv: " + lines.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
