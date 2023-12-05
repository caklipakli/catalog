package com.caklipakli.catalog.utils;

public class QueryApp {

    public static final String TOTAL_LISTINGS = "SELECT COUNT(*) FROM listing";

    public static final String TOTAL_MARKETPLACES = "SELECT m.marketplace_name, COUNT (id) AS total_active_listings, \n" +
            "SUM(listing_price) AS \"total active listing price\", \n" +
            "AVG(listing_price) AS \"average active listing price\", currency\n" +
            "FROM listing\n" +
            "INNER JOIN marketplace m USING (marketplace_id)\n" +
            "WHERE (listing_status = 1)\n" +
            "GROUP BY m.marketplace_name, currency\n" +
            "ORDER BY m.marketplace_name;";

    public static final String BEST_LISTER = "SELECT owner_email_address AS best_lister, listing_price\n" +
            "FROM listing\n" +
            "WHERE listing_price = (SELECT MAX(listing_price) FROM listing);";

    public static final String MONTHLY_REPORT = "SELECT m.marketplace_name, date_part('year', upload_time) AS year, date_part('month', upload_time) AS month, COUNT (id),\n" +
            "SUM(listing_price) AS total_price, AVG(listing_price) AS average_price, currency\n" +
            "FROM listing\n" +
            "INNER JOIN marketplace m USING (marketplace_id)\n" +
            "WHERE (listing_status = 1) \n" +
            "GROUP BY m.marketplace_name, date_part('year', upload_time), date_part('month', upload_time), currency\n" +
            "ORDER BY m.marketplace_name, date_part('year', upload_time), date_part('month', upload_time);";

}
