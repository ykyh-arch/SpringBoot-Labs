/*创建数据库lab-12-spring-mybatis*/
DROP DATABASE IF EXISTS `lab-12-spring-mybatis`;
CREATE DATABASE `lab-12-spring-mybatis`;

USE `lab-12-spring-mybatis`;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
                       id int AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
                       name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名'
) COMMENT '用户表';
INSERT INTO t_user VALUES (1,'张学友'),(2,'路人甲Java');

DROP TABLE IF EXISTS t_goods;
CREATE TABLE t_goods(
                        id int AUTO_INCREMENT PRIMARY KEY COMMENT '商品id',
                        name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '商品名称',
                        price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '商品价格'
) COMMENT '商品信息表';
INSERT INTO t_goods VALUES (1,'Mybatis系列',8.88),(2,'maven高手系列',16.66);

DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order(
                        id int AUTO_INCREMENT PRIMARY KEY COMMENT '订单id',
                        user_id INT NOT NULL DEFAULT 0 COMMENT '用户id，来源于t_user.id',
                        create_time BIGINT NOT NULL DEFAULT 0 COMMENT '订单创建时间(时间戳，秒)',
                        up_time BIGINT NOT NULL DEFAULT 0 COMMENT '订单最后修改时间(时间戳，秒)'
) COMMENT '订单表';
INSERT INTO t_order VALUES (1,2,unix_timestamp(now()),unix_timestamp(now())),(2,1,unix_timestamp(now()),unix_timestamp(now()));

DROP TABLE IF EXISTS t_order_detail;
CREATE TABLE t_order_detail(
                               id int AUTO_INCREMENT PRIMARY KEY COMMENT '订单明细id',
                               order_id INT NOT NULL DEFAULT 0 COMMENT '订单id，来源于t_order.id',
                               goods_id INT NOT NULL DEFAULT 0 COMMENT '商品id，来源于t_goods.id',
                               num INT NOT NULL DEFAULT 0 COMMENT '商品数量',
                               total_price DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '商品总金额'
) COMMENT '订单表';
INSERT INTO t_order_detail VALUES (1,1,1,2,17.76),(2,1,1,1,16.66),(3,2,1,1,8.88);

select * from t_user;
select * from t_goods;
select * from t_order;
select * from t_order_detail;