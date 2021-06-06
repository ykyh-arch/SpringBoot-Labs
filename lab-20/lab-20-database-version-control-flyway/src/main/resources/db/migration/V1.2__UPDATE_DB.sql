-- 插入一条数据
INSERT INTO `users`(username, password, create_time) VALUES('google', '123456', now());
-- 更新一条数据
UPDATE users SET username = 'java' WHERE id = 7