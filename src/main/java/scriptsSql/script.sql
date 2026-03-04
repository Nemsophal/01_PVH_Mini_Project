-- Run in PostgreSQL
CREATE DATABASE stock_db;
\c stock_db;
CREATE TABLE products (
id SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
unit_price DECIMAL(10,2) NOT NULL,
qty INT NOT NULL,
import_date DATE DEFAULT CURRENT_DATE
)