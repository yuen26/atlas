CREATE DATABASE IF NOT EXISTS db_auth;

USE db_auth;

CREATE TABLE IF NOT EXISTS users
(
    id         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE INDEX idx_username (username)
) ENGINE = INNODB;

-- Plain password: 123456
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$12$.sw34Jx7f/I9aln/s9b2.eMcs6Ji9HpcudPAl2BOkLPmupGhsU8yO', 'ADMIN'),
       ('customer', '$2a$12$.sw34Jx7f/I9aln/s9b2.eMcs6Ji9HpcudPAl2BOkLPmupGhsU8yO', 'CUSTOMER');
