
create table if not exists `person` (
    `id` int,
    `name` varchar(255)
);
-- 注意需要有分号结尾

# 员工
create table if not exists employee(
  id int not null primary key auto_increment,
  name varchar(255),
  age int,
  sex int comment '0 表示女，1表示男',
  department_id int,
  birth date
);

# 部门
create table if not exists department(
    id int not null primary key auto_increment,
    name varchar(255)
);