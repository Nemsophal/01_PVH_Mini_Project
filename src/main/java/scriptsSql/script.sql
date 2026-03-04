-- Run in PostgreSQL
CREATE
DATABASE stock_db;

CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    unit_price  DECIMAL(10, 2) NOT NULL,
    qty         INT            NOT NULL,
    import_date DATE DEFAULT CURRENT_DATE
)

-- Insert data
INSERT INTO products (name, unit_price, qty)
VALUES ('Sting', 2, 3),
       ('Dell Alienware', 1120, 3),
       ('Toyota Prius', 19900, 10),
       ('Khmer Noddle', 12, 3),
       ('Coca Cola', 3, 4),
       ('Pepsi', 3, 7),
       ('Works', 3, 2),
       ('Champion', 3, 7),
       ('Vital Pure', 3, 9)
       ('Dasani', 3, 1);