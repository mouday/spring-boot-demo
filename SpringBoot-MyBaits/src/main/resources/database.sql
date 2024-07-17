-- 创建数据库
CREATE DATABASE IF NOT EXISTS db_data character SET utf8mb4;

-- 选择使用数据库
USE db_data;

-- 用户表
CREATE TABLE IF NOT EXISTS `tb_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '用户名称',
  `age` tinyint unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT current_timestamp ,
  `update_time` datetime DEFAULT current_timestamp on update current_timestamp,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO tb_user (id, name, age) VALUES (1, '曹操', 40);
INSERT INTO tb_user (id, name, age) VALUES (2, '刘备', 30);
INSERT INTO tb_user (id, name, age) VALUES (3, '孙权', 20);

-- 创建文章表t_article并插入相关数据
CREATE TABLE IF NOT EXISTS tb_article (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  user_id int(11) NOT NULL COMMENT '用户id',
  title varchar(50) NOT NULL COMMENT '文章标题',
  content longtext COMMENT '文章具体内容',
  `create_time` datetime DEFAULT current_timestamp ,
  `update_time` datetime DEFAULT current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO tb_article (id, user_id, title, content) VALUES (1, 1, 'MySQL基础教程', 'MySQL从入门到精通');
INSERT INTO tb_article (id, user_id, title, content) VALUES (2, 2, 'SpringBoot框架基础教程', 'SpringBoot从入门到精通');
INSERT INTO tb_article (id, user_id, title, content) VALUES (3, 1, 'MyBaits框架基础教程', 'MyBaits从入门到精通');


-- 创建评论表t_comment并插入相关数据
CREATE TABLE IF NOT EXISTS tb_comment (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  user_id int(11) NOT NULL COMMENT '用户id',
  article_id int(11) NOT NULL COMMENT '关联的文章id',
  content longtext COMMENT '评论内容',
  `create_time` datetime DEFAULT current_timestamp ,
  `update_time` datetime DEFAULT current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO tb_comment (id, user_id, article_id, content) VALUES ('1', 1, '1', '内容很详细');
INSERT INTO tb_comment (id, user_id, article_id, content) VALUES ('2', 2, '1', '已三连');
INSERT INTO tb_comment (id, user_id, article_id, content) VALUES ('3', 1, '3', '很不错');
INSERT INTO tb_comment (id, user_id, article_id, content) VALUES ('4', 2, '1', '赞一个');
INSERT INTO tb_comment (id, user_id, article_id, content) VALUES ('5', 1, '2', '内容全面');
