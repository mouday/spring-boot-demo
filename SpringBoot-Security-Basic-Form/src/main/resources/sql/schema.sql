-- 创建用户表
CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '昵称',
  `enabled` tinyint NOT NULL DEFAULT '1' COMMENT '账号可用标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- 添加初始数据
insert into `tb_user` values (1, "zhangsan", "zhangsan", "张三", 1);
insert into `tb_user` values (2, "lisi", "lisi", "李四", 1);
insert into `tb_user` values (3, "wangwu", "wangwu", "王五", 1);