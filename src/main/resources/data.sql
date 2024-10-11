-- Insertar datos en la tabla
INSERT INTO currency_conversion_rate (from_currency, to_currency, rate)
VALUES ('USD', 'EUR', 0.85);

INSERT INTO currency_conversion_rate (from_currency, to_currency, rate)
VALUES ('EUR', 'GBP', 0.90);

INSERT INTO currency_conversion_rate (from_currency, to_currency, rate)
VALUES ('USD', 'JPY', 110.25);

INSERT INTO currency_conversion_rate (from_currency, to_currency, rate)
VALUES ('GBP', 'INR', 101.15);

-- Conversión al Sol peruano (PEN)
INSERT INTO currency_conversion_rate (from_currency, to_currency, rate)
VALUES ('USD', 'PEN', 3.85);

INSERT INTO currency_conversion_rate (from_currency, to_currency, rate)
VALUES ('EUR', 'PEN', 4.50);