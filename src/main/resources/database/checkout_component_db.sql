CREATE DATABASE IF NOT EXISTS `checkout_component_db`;
USE `checkout_component_db`;

DROP TABLE IF EXISTS `basket`;
CREATE TABLE `basket` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `product` int(10) NOT NULL,
  `stock` BIGINT(20) NOT NULL ,
  `cost` DOUBLE(10,2) NOT NULL,
  `order_date`  TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `status` VARCHAR(20),
  constraint basket_product FOREIGN KEY (`product`) references products(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`(
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(20) NOT NULL ,
  `price` DOUBLE(10,2) NOT NULL,
  `unit` BIGINT(20),
  `special_price` DOUBLE(10,2),
  `special_price_condition` INT(10)
);