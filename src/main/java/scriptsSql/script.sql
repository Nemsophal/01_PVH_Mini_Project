
CREATE DATABASE stock_db;


CREATE TABLE products (
id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name VARCHAR(100) NOT NULL,
unit_price DECIMAL(10,2) NOT NULL,
qty INT NOT NULL,
import_date DATE DEFAULT CURRENT_DATE
)


INSERT INTO products (name, unit_price, qty, import_date) VALUES
('Laptop', 1200.00, 10, '2026-03-01'),
('Mouse', 25.50, 50, '2026-03-02'),
('Keyboard', 45.99, 30, '2026-03-02'),
('Monitor', 250.75, 15, '2026-03-03'),
('Printer', 180.40, 8, '2026-03-01'),
('USB Cable', 8.99, 100, '2026-03-03'),
('External HDD', 95.00, 20, '2026-03-02'),
('Webcam', 60.00, 25, '2026-03-01'),
('Headphones', 75.25, 18, '2026-03-03'),
('Speaker', 110.00, 12, NULL);