CREATE DATABASE IF NOT EXISTS `hr-system` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `hr-system`;

DROP TABLE IF EXISTS directory_tree;

CREATE TABLE directory_tree (
								id INT PRIMARY KEY AUTO_INCREMENT,
								name VARCHAR(255) NOT NULL,
								parent_id INT,
								FOREIGN KEY (parent_id) REFERENCES directory_tree(id)
);


CREATE TABLE if not exists `t_user` (
						  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
						  `nick_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
						  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
						  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
						  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户描述',
						  `create_time` datetime DEFAULT NULL COMMENT '用户创建时间',
						  `update_time` datetime DEFAULT NULL COMMENT '用户修改时间',
						  `del_flag` tinyint DEFAULT NULL COMMENT '用户删除标识，0：正常，1：已删除',
						  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
