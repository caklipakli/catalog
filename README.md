CATALOG APP
===========
Command-line based Java application that simulates a listing reporting system.

The app performs the following tasks:
1. Reads location and listings data from an external API (https://my.api.mockaroo.com)
2. validates the data
3. saves the data to a local database
4. writes errors to a log file 'importLog.csv'
5. generates a report of the listings in the database
- total listings
- total EBAY (ACTIVE) listings, total price, average price
- total Amazon (ACTIVE) listings, total price, average price
- best lister email
- Monthly report:
- total EBAY (ACTIVE) listings, total price, average price per month
- total Amazon (ACTIVE) listings, total price, average price per month
- upcoming: best lister email of month
6. writes the report to a file 'report.json'
7. uploads file to an FTP server


## Specifications
- Java 21.0.1
- Maven 3.9.4
- Spring Boot 3.1.6
- picocli 4.6.1

## How to run
The app can be run from the command line using the following commands:

0. mvn clean install 
1. java -jar .\target\catalog-0.0.1-SNAPSHOT.jar --> performs tasks 1-7
2. java -jar .\target\catalog-0.0.1-SNAPSHOT.jar -t --save --> performs tasks 1-4
3. java -jar .\target\catalog-0.0.1-SNAPSHOT.jar -t --report --> performs tasks 5-7

{Disclaimer: 
The app is not fully functional yet. It is still a work in progress.
It receives only listings data currently.}


