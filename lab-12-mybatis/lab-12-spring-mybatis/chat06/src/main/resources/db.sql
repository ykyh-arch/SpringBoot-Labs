/*创建数据库lab-12-spring-mybatis*/
DROP DATABASE IF EXISTS `lab-12-spring-mybatis`;
CREATE DATABASE `lab-12-spring-mybatis`;

USE `lab-12-spring-mybatis`;

DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
                       id int AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
                       name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
                       age SMALLINT NOT NULL DEFAULT 1 COMMENT '年龄'
) COMMENT '用户表';
INSERT INTO t_user VALUES (1,'路人甲Java',30),(2,'张学友',50),(3,'刘德华',50);