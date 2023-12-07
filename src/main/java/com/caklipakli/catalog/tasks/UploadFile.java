package com.caklipakli.catalog.tasks;

import lombok.extern.log4j.*;
import org.apache.commons.net.ftp.*;
import org.springframework.stereotype.*;

import java.io.*;

@Log4j2
@Component
public class UploadFile {

    //@Value("${ftp.host}")
    private static final String FTP_HOST = "192.168.1.12";
    private static final String FTP_USER = "user";
    private static final String FTP_PASSWORD = "admin";
    public static final String REPORT_JSON_FILE_NAME = "src/main/resources/report.json";

    public static void uploadReportJSONFile() {

        log.info("File upload process started ");

        FTPClient client = new FTPClient();
        FileInputStream fis = null;

        try {
            client.connect(FTP_HOST);
            client.login(FTP_USER, FTP_PASSWORD);

            fis = new FileInputStream(REPORT_JSON_FILE_NAME);

            client.storeFile(REPORT_JSON_FILE_NAME, fis);
            log.info("File uploaded process ended ");
            client.logout();
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
