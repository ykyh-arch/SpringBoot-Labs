CREATE TABLE `sys_task` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
                            `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
                            `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
                            `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
                            `job_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务状态（0=停止；1=运行）',
                            `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
                            `create_user` varchar(64) DEFAULT NULL COMMENT '创建者',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `update_user` varchar(64) DEFAULT NULL COMMENT '更新者',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3 COMMENT='系统-定时任务表';