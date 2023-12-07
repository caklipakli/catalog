package com.caklipakli.catalog.tasks;

import com.caklipakli.catalog.report.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import lombok.extern.log4j.*;
import org.json.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import picocli.*;

import javax.sql.*;
import java.io.*;
import java.sql.*;

import static com.caklipakli.catalog.utils.QueryApp.BEST_LISTER;
import static com.caklipakli.catalog.utils.QueryApp.MONTHLY_REPORT;
import static com.caklipakli.catalog.utils.QueryApp.TOTAL_LISTINGS;
import static com.caklipakli.catalog.utils.QueryApp.TOTAL_MARKETPLACES;

@Component
@Log4j2
public class GenerateReport {
    @Autowired
    private DataSource dataSource;

    public void generateReport() {
        log.info("Generate report");
        try {
            queryData();
        } catch (SQLException | JSONException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    void queryData() throws SQLException, JSONException, IOException {

        log.info("Query database");

        JSONArray allResults = new JSONArray();
        JSONArray sum = new JSONArray();
        JSONObject o = new JSONObject();
        JSONArray market = new JSONArray();
        JSONArray emails = new JSONArray();
        JSONObject email = new JSONObject();
        JSONArray monthly = new JSONArray();

        ResultSetConverter rsc = new ResultSetConverter();

        Connection conn = dataSource.getConnection();

        Statement stmt = conn.createStatement();

        //Total Listings

        ResultSet total = stmt.executeQuery(TOTAL_LISTINGS);

        while (total.next()) {
            String temp = total.getString(1);
            o.put("total_listings", temp);
            sum.put(o);
        }

        allResults.put(sum);

        //Marketplace results
        ResultSet marketRs = stmt.executeQuery(TOTAL_MARKETPLACES);

        market = rsc.convert(marketRs);
        allResults.put(market);

        //Best Lister

        ResultSet bestLister = stmt.executeQuery(BEST_LISTER);

        while (bestLister.next()) {
            String temp1 = bestLister.getString(1);
            email.put("best_lister", temp1);
            emails.put(email);
        }
        allResults.put(emails);

// Monthly reports

        log.info("Monthly reports");

        ResultSet monthlyRs = stmt.executeQuery(MONTHLY_REPORT);

        monthly = rsc.convert(monthlyRs);
        allResults.put(monthly);

// Write JSON to file

        writeJsonArrayToFile(prettyPrintJson(allResults.toString()), UploadFile.REPORT_JSON_FILE_NAME);

        UploadFile.uploadReportJSONFile();

    }

    private String prettyPrintJson (String uglyJsonString) throws JsonProcessingException {
        log.info("Pretty print JSON");
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(uglyJsonString, Object.class);
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return prettyJson;
    }

    private void writeJsonArrayToFile(String jsonString, String fileName) throws IOException {
        log.info("Write JSON to file");
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();
    }
}
