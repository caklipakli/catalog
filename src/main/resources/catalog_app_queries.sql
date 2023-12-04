TRUNCATE listing;

<-- Total listings -->

SELECT COUNT (id) AS total_listings
FROM listing

<-- Total active listings per marketplace per currency -->

SELECT m.marketplace_name, COUNT (id) AS total_active_listings, 
SUM(listing_price) AS "total active listing price", 
AVG(listing_price) AS "average active listing price", currency
FROM listing
INNER JOIN marketplace m USING (marketplace_id)
WHERE (listing_status = 1)
GROUP BY m.marketplace_name, currency
ORDER BY m.marketplace_name;

<-- best lister -->

SELECT owner_email_address AS best_lister, listing_price
FROM listing
WHERE listing_price = (SELECT MAX(listing_price) FROM listing);

<-- Monthly report -- >

SELECT m.marketplace_name, date_part('year', upload_time) AS year, date_part('month', upload_time) AS month, COUNT (id),
SUM(listing_price) AS total_price, AVG(listing_price) AS average_price, currency
FROM listing
INNER JOIN marketplace m USING (marketplace_id)
WHERE (listing_status = 1) 
GROUP BY m.marketplace_name, date_part('year', upload_time), date_part('month', upload_time), currency
ORDER BY m.marketplace_name, date_part('year', upload_time), date_part('month', upload_time);








