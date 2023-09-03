# SpringBoot Start

```sql
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别',
  `color` int NOT NULL DEFAULT '0' COMMENT '颜色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT '用户表';
```

需求：

- 数据库存储：0、1、2
