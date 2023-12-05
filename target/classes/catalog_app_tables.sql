TRUNCATE listing;

CREATE TABLE IF NOT EXISTS location (
	id VARCHAR(36) NOT NULL,
	manager_name VARCHAR(50),
	phone VARCHAR (50),
	address_primary VARCHAR (50),
	address_secondary VARCHAR (50),
	country VARCHAR (50),
	town VARCHAR (50),
	postal_code VARCHAR (20),
	PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS status (
	status_id SMALLINT PRIMARY KEY,
	status_name VARCHAR (10)
);

CREATE TABLE IF NOT EXISTS marketplace (
	marketplace_id SMALLINT PRIMARY KEY,
	marketplace_name VARCHAR (10)
);

CREATE TABLE IF NOT EXISTS listing (
	id VARCHAR(36) NOT NULL,
	title VARCHAR (50) NOT NULL,
	description VARCHAR (300) NOT NULL,
	location_id VARCHAR(36) NOT NULL,
	listing_price NUMERIC (5,2) CHECK (listing_price > 0),
	currency VARCHAR(3) NOT NULL,
	quantity SMALLINT NOT NULL CHECK (quantity > 0),
	listing_status SMALLINT NOT NULL,
	marketplace_id SMALLINT NOT NULL,
	upload_time DATE,
	owner_email_address VARCHAR(30),
	PRIMARY KEY (id),
	FOREIGN KEY (location_id) REFERENCES location (id),
	FOREIGN KEY (listing_status) REFERENCES status (status_id),
	FOREIGN KEY (marketplace_id) REFERENCES marketplace (marketplace_id)
);



