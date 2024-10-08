CREATE DATABASE IF NOT EXISTS db_product;

USE db_product;

CREATE TABLE IF NOT EXISTS product
(
    id         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255)  NOT NULL,
    price      DECIMAL(9, 2) NOT NULL,
    quantity   INT           NOT NULL,
    created_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = INNODB;

INSERT INTO product (name, price, quantity, created_at)
VALUES ('Smartphone', 599.99, 100, '2023-01-15 00:00:00'),
       ('Jeans', 49.99, 100, '2023-02-20 01:00:00'),
       ('Dark Chocolate', 1.99, 100, '2023-03-01 02:05:00'),
       ('Action Figure', 15.99, 0, '2023-04-10 03:10:00'),
       ('Laptop', 1200.00, 100, '2023-05-25 04:15:00'),
       ('T-Shirt', 20.99, 100, '2023-06-15 05:20:00'),
       ('Milk Chocolate', 3.49, 100, '2023-07-05 06:25:00'),
       ('Board Game', 29.99, 100, '2023-08-20 07:30:00'),
       ('Electric Kettle', 35.99, 0, '2023-09-10 08:35:00'),
       ('Running Shoes', 89.99, 100, '2023-10-05 09:40:00');

CREATE TABLE IF NOT EXISTS outbox_message
(
    id         BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event      VARCHAR(1000) NOT NULL,
    created_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = INNODB;
