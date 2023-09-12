DROP DATABASE IF EXISTS dbdemo1;
CREATE DATABASE IF NOT EXISTS dbdemo1;

USE dbdemo1;
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
                        id   INT PRIMARY KEY       AUTO_INCREMENT,
                        name VARCHAR(256) NOT NULL DEFAULT ''
                            COMMENT '姓名'
);

INSERT INTO t_user (name) VALUE ('master库');

DROP DATABASE IF EXISTS dbdemo2;
CREATE DATABASE IF NOT EXISTS dbdemo2;

USE dbdemo2;
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
                        id   INT PRIMARY KEY       AUTO_INCREMENT,
                        name VARCHAR(256) NOT NULL DEFAULT ''
                            COMMENT '姓名'
);
INSERT INTO t_user (name) VALUE ('slave库');