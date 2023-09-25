/*创建数据库lab-12-spring-mybatis*/
DROP DATABASE IF EXISTS `lab-12-spring-mybatis`;
CREATE DATABASE `lab-12-spring-mybatis`;

USE `lab-12-spring-mybatis`;

CREATE TABLE t_user(
                       id int AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
                       name VARCHAR(32) NOT NULL DEFAULT '' COMMENT '用户名',
                       age SMALLINT NOT NULL DEFAULT 1 COMMENT '年龄',
                       sex SMALLINT DEFAULT 0 COMMENT '性别，0：未知，1：男，2：女'
) COMMENT '用户表';
INSERT INTO t_user VALUES (1,'路人甲Java',30,1),(2,'林志玲',45,2);