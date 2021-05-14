/*
MySQL Backup
Database: miaosha
Backup Time: 2021-05-14 17:35:10
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `miaosha`.`goods`;
DROP TABLE IF EXISTS `miaosha`.`miaosha_goods`;
DROP TABLE IF EXISTS `miaosha`.`miaosha_order`;
DROP TABLE IF EXISTS `miaosha`.`miaosha_user`;
DROP TABLE IF EXISTS `miaosha`.`order_info`;
DROP TABLE IF EXISTS `miaosha`.`user`;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品图片',
  `goods_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品详情',
  `goods_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `goods_stock` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='商品表';
CREATE TABLE `miaosha_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  `stock_count` bigint DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `miaosha_price` decimal(10,2) DEFAULT NULL COMMENT '秒杀价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='秒杀商品';
CREATE TABLE `miaosha_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='秒杀订单';
CREATE TABLE `miaosha_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称/登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `head` varchar(255) DEFAULT NULL COMMENT '盐',
  `salt` varchar(255) DEFAULT NULL COMMENT '头像',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
  `login_count` int DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18225529116 DEFAULT CHARSET=utf8mb3;
CREATE TABLE `order_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` int DEFAULT NULL COMMENT '收货地址',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `goods_count` int DEFAULT NULL COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `order_channel` int DEFAULT NULL COMMENT '订单栏目',
  `status` int DEFAULT NULL COMMENT '状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COMMENT='订单信息';
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
BEGIN;
LOCK TABLES `miaosha`.`goods` WRITE;
DELETE FROM `miaosha`.`goods`;
INSERT INTO `miaosha`.`goods` (`id`,`goods_name`,`goods_title`,`goods_img`,`goods_detail`,`goods_price`,`goods_stock`) VALUES (1, '苹果11', '苹果11', NULL, '苹果11', 8000.00, '10');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `miaosha`.`miaosha_goods` WRITE;
DELETE FROM `miaosha`.`miaosha_goods`;
INSERT INTO `miaosha`.`miaosha_goods` (`id`,`goods_id`,`stock_count`,`start_date`,`end_date`,`miaosha_price`) VALUES (1, 1, 9, '2021-05-14 16:15:18', '2021-05-15 16:15:23', 7000.00);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `miaosha`.`miaosha_order` WRITE;
DELETE FROM `miaosha`.`miaosha_order`;
INSERT INTO `miaosha`.`miaosha_order` (`id`,`user_id`,`order_id`,`goods_id`) VALUES (1, 18225529115, 1, 1);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `miaosha`.`miaosha_user` WRITE;
DELETE FROM `miaosha`.`miaosha_user`;
INSERT INTO `miaosha`.`miaosha_user` (`id`,`nickname`,`password`,`head`,`salt`,`register_date`,`last_login_date`,`login_count`) VALUES (18225529115, '18225529115', 'd283243b7a1db25c03c1e0e6c2c8d8c9', NULL, '123456', '2021-05-14 16:09:02', '2021-05-14 16:09:05', 1);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `miaosha`.`order_info` WRITE;
DELETE FROM `miaosha`.`order_info`;
INSERT INTO `miaosha`.`order_info` (`id`,`user_id`,`goods_id`,`delivery_addr_id`,`goods_name`,`goods_count`,`goods_price`,`order_channel`,`status`,`create_date`,`pay_date`) VALUES (1, 18225529115, 1, NULL, '苹果11', 1, 7000.00, 1, 0, '2021-05-14 17:22:34', NULL);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `miaosha`.`user` WRITE;
DELETE FROM `miaosha`.`user`;
UNLOCK TABLES;
COMMIT;
