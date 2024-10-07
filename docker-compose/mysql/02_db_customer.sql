CREATE DATABASE IF NOT EXISTS db_customer;

USE db_customer;

CREATE TABLE IF NOT EXISTS customer
(
    user_id           INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(255)   NOT NULL,
    last_name    VARCHAR(255)   NOT NULL,
    email        VARCHAR(255)   NOT NULL,
    phone_number VARCHAR(20)    NOT NULL,
    credit       DECIMAL(11, 2) NOT NULL DEFAULT 0,
    created_at   DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE INDEX idx_email (email)
) ENGINE = INNODB;

INSERT INTO customer (user_id, first_name, last_name, email, phone_number, credit)
VALUES (1, 'John', 'Doe', 'customer@atlas.org', '0987654321', 100000);

CREATE TABLE IF NOT EXISTS outbox_message
(
    id         BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event      VARCHAR(1000) NOT NULL,
    created_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = INNODB;
