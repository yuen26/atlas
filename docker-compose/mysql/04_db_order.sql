CREATE DATABASE IF NOT EXISTS db_order;

USE db_order;

CREATE TABLE IF NOT EXISTS orders
(
    id         INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    INT            NOT NULL,
    amount     DECIMAL(11, 2) NOT NULL,
    address    VARCHAR(500),
    status     VARCHAR(20)    NOT NULL,
    created_at DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS order_item
(
    id            INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id      INT           NOT NULL,
    product_id    INT           NOT NULL,
    product_price DECIMAL(9, 2) NOT NULL,
    quantity      INT           NOT NULL
) ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS outbox_message
(
    id         BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event      VARCHAR(1000) NOT NULL,
    created_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = INNODB;
