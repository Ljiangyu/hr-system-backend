DELETE FROM directory_tree;

-- 插入根节点（总公司）
INSERT INTO directory_tree (name, parent_id) VALUES ('总公司', NULL);

-- 插入一级部门（研发部），关联到总公司（总公司id假设为1）
INSERT INTO directory_tree (name, parent_id) VALUES ('研发部', 1);

-- 插入一级部门（市场部），关联到总公司（总公司id假设为1）
INSERT INTO directory_tree (name, parent_id) VALUES ('市场部', 1);

-- 插入研发部的二级部门（后端开发组），关联到研发部（研发部id假设为2）
INSERT INTO directory_tree (name, parent_id) VALUES ('后端开发组', 2);

-- 插入研发部的二级部门（前端开发组），关联到研发部（研发部id假设为2）
INSERT INTO directory_tree (name, parent_id) VALUES ('前端开发组', 2);

-- 插入市场部的二级部门（市场推广组），关联到市场部（市场部id假设为3）
INSERT INTO directory_tree (name, parent_id) VALUES ('市场推广组', 3);

-- 插入市场部的二级部门（销售组），关联到市场部（市场部id假设为3）
INSERT INTO directory_tree (name, parent_id) VALUES ('销售组', 3);


-- user table -------------------------------------------------------
DELETE FROM t_user;
INSERT INTO `t_user` (`id`, `nick_name`, `username`, `password`, `description`, `create_time`, `update_time`, `del_flag`) VALUES (4, '吴立强', 'admin111', '123456', 'this is a good man', '2024-12-06 15:22:00', '2024-12-06 15:22:00', 0);
