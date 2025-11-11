CREATE TABLE deals (
                       id BIGSERIAL PRIMARY KEY,
                       ordering_currency_iso_code VARCHAR(255) NOT NULL,
                       to_currency_iso_code VARCHAR(255) NOT NULL,
                       deal_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       deal_amount DOUBLE PRECISION NOT NULL,
                       CONSTRAINT chk_deal_amount CHECK (deal_amount >= 0)
);