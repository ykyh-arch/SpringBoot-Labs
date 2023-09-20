/*创建数据库lab-12-spring-mybatis*/
DROP DATABASE IF EXISTS `lab-12-spring-mybatis`;
CREATE DATABASE `lab-12-spring-mybatis`;
USE `lab-12-spring-mybatis`;

/*创建表结构*/
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE t_user (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键，用户id，自动增长',
                        `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '姓名',
                        `age` SMALLINT NOT NULL DEFAULT 1 COMMENT '年龄',
                        `salary` DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '薪水',
                        `sex` TINYINT NOT NULL DEFAULT 0 COMMENT '性别,0:未知,1:男,2:女'
) COMMENT '用户表';

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE t_order (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键，订单id，自动增长',
                         `user_id` BIGINT NOT NULL DEFAULT 0 COMMENT '用户id',
                         `price` DECIMAL(12,2) NOT NULL DEFAULT 0 COMMENT '订单金额'
) COMMENT '订单表';

/*插入几条测试数据*/
INSERT INTO t_user (`name`,`age`,`salary`,`sex`)
VALUES
    ('路人甲Java',30,50000,1),
    ('javacode2018',30,50000,1),
    ('张学友',56,500000,1),
    ('林志玲',45,88888.88,2);


INSERT INTO t_order (`user_id`,`price`)
VALUES
    (1,88.88),
    (2,666.66);

SELECT * FROM t_user;
SELECT * FROM t_order;