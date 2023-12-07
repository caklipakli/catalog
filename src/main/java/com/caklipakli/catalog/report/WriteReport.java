package com.caklipakli.catalog.report;

import lombok.extern.log4j.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

@Log4j2
public class WriteReport {

    public WriteReport() {
    }

    public static void writeImportLogReport(String fileName, StringBuilder errors) {

        Path path = Paths.get(fileName);

        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Writer writer = new BufferedWriter(new FileWriter(path.toFile()))) {

            if (errors.length() > 0) {
                writer.write(errors.toString());
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
