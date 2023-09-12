DROP DATABASE IF EXISTS dbdemo;
CREATE DATABASE if NOT EXISTS dbdemo;

USE dbdemo;
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(256) NOT NULL DEFAULT '' COMMENT '姓名'
);

DROP TABLE IF EXISTS t_msg;
CREATE TABLE t_msg(
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      msg VARCHAR(256) NOT NULL DEFAULT '' COMMENT '消息内容，可以json格式的数据',
                      msg_order_id BIGINT NOT NULL DEFAULT 0 COMMENT '消息订单id',
                      status SMALLINT NOT NULL DEFAULT 0 COMMENT '消息状态,0:待投递，1：已发送，2：取消发送'
) COMMENT '消息表';

DROP TABLE IF EXISTS t_msg_order;
CREATE TABLE t_msg_order(
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            ref_type BIGINT NOT NULL DEFAULT 0 COMMENT '关联业务类型',
                            ref_id VARCHAR(256) NOT NULL DEFAULT '' COMMENT '关联业务id（ref_type & ref_id 唯一）'
) COMMENT '消息订单表,放在业务库中';

alter table t_msg_order add UNIQUE INDEX idx1 (ref_type,ref_id);
