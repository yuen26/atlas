INSERT INTO category (name)
VALUES ('ELECTRONICS'), ('CLOTHING'), ('FOOD'), ('TOYS');

INSERT INTO product (name, category_id, price, quantity, status, featured, created_at) VALUES
('Smartphone', 1, 599.99, 100, 'IN_STOCK', TRUE, '2023-01-15 00:00:00'),
('Jeans', 2, 49.99, 100, 'IN_STOCK', TRUE, '2023-02-20 01:00:00'),
('Dark Chocolate', 3, 1.99, 100, 'IN_STOCK', TRUE, '2023-03-01 02:05:00'),
('Action Figure', 4, 15.99, 0, 'PREORDER', FALSE, '2023-04-10 03:10:00'),
('Laptop', 1, 1200.00, 100, 'IN_STOCK', TRUE, '2023-05-25 04:15:00'),
('T-Shirt', 2, 20.99, 100, 'IN_STOCK', TRUE, '2023-06-15 05:20:00'),
('Milk Chocolate', 3, 3.49, 100, 'IN_STOCK', TRUE, '2023-07-05 06:25:00'),
('Board Game', 4, 29.99, 100, 'IN_STOCK', TRUE, '2023-08-20 07:30:00'),
('Electric Kettle', 1, 35.99, 0, 'OUT_OF_STOCK', FALSE, '2023-09-10 08:35:00'),
('Running Shoes', 2, 89.99, 100, 'IN_STOCK', TRUE, '2023-10-05 09:40:00');
