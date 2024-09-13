DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS outbox_message;

CREATE TABLE orders
(
    id          INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id INT            NOT NULL,
    amount      DECIMAL(11, 2) NOT NULL,
    address     VARCHAR(500),
    status      VARCHAR(20)    NOT NULL,
    created_at  DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB;

CREATE TABLE order_item
(
    id            INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id      INT           NOT NULL,
    product_id    INT           NOT NULL,
    product_price DECIMAL(9, 2) NOT NULL,
    quantity      INT           NOT NULL
) ENGINE=INNODB;

CREATE TABLE outbox_message
(
    id         BIGINT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event      VARCHAR(1000) NOT NULL,
    created_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB;
