package com.caklipakli.catalog.tasks;

import com.caklipakli.catalog.report.ResultSetConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j2;
import picocli.CommandLine;

import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@Log4j2
@CommandLine.Command(name = "report", description = "generate report")
public class GenerateReport implements Runnable {

    private static final String HEADERS = "Aggregated and monthly report for marketplaces";

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {

        CommandLine commandLine = new CommandLine(new GenerateReport());
        commandLine.parseWithHandler(new CommandLine.RunAll(), args);
        commandLine.getCommandSpec().parser().collectErrors(true);

    }

    public void queryData() throws SQLException, JSONException, IOException {

        log.info(HEADERS);

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

        ResultSet total = stmt.executeQuery("SELECT COUNT(id) AS total_listings FROM listing;");

        while (total.next()) {
            String temp = total.getString(1);
            o.put("total_listings", temp);
            sum.put(o);
        }

        allResults.put(sum);

        //Marketplace results
        ResultSet marketRs = stmt.executeQuery("SELECT m.marketplace_name, COUNT (id) AS total_active_listings, \n" +
                "SUM(listing_price) AS \"total active listing price\", \n" +
                "AVG(listing_price) AS \"average active listing price\", currency\n" +
                "FROM listing\n" +
                "INNER JOIN marketplace m USING (marketplace_id)\n" +
                "WHERE (listing_status = 1)\n" +
                "GROUP BY m.marketplace_name, currency\n" +
                "ORDER BY m.marketplace_name;");

        market = rsc.convert(marketRs);
        allResults.put(market);

     //Best Lister

        ResultSet bestLister = stmt.executeQuery("SELECT owner_email_address AS best_lister, listing_price \n" +
                "FROM listing\n" +
                "WHERE listing_price = (SELECT MAX(listing_price) FROM listing);");

        while (bestLister.next()) {
            String temp1 = bestLister.getString(1);
            email.put("best_lister", temp1);
            emails.put(email);
        }
        allResults.put(emails);

// Monthly reports

        log.info("Monthly reports");

        ResultSet monthlyRs = stmt.executeQuery("SELECT m.marketplace_name, date_part('year', upload_time) AS year, date_part('month', upload_time) AS month, COUNT (id),\n" +
                "SUM(listing_price) AS total_price, AVG(listing_price) AS average_price, currency\n" +
                "FROM listing\n" +
                "INNER JOIN marketplace m USING (marketplace_id)\n" +
                "WHERE (listing_status = 1) \n" +
                "GROUP BY m.marketplace_name, date_part('year', upload_time), date_part('month', upload_time), currency\n" +
                "ORDER BY m.marketplace_name, date_part('year', upload_time), date_part('month', upload_time);");

        monthly = rsc.convert(monthlyRs);
        allResults.put(monthly);

        writeJsonArrayToFile(prettyPrintJson(allResults.toString()), UploadFile.REPORT_JSON_FILE_NAME);

        UploadFile.uploadReportJSONFile();

    }

    private static String prettyPrintJson (String uglyJsonString) throws JsonProcessingException {
        log.info("Pretty print JSON");
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(uglyJsonString, Object.class);
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        return prettyJson;
    }

    private static void writeJsonArrayToFile(String jsonString, String fileName) throws IOException {
        log.info("Write JSON to file");
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();
    }

    @Override
    public void run() {
        try {
            queryData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
