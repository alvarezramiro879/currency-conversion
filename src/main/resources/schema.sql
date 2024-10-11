DROP TABLE IF  EXISTS currency_conversion_rate;

CREATE TABLE IF NOT EXISTS
currency_conversion_rate (id INT NOT NULL AUTO_INCREMENT,
from_currency VARCHAR(255) NOT NULL, to_currency VARCHAR(255) NOT NULL, rate DECIMAL(19, 6) NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE currency_conversion_rate
ADD CONSTRAINT unique_currency_pair UNIQUE (from_currency, to_currency);

