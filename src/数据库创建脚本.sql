-- 删除数据库
DROP DATABASE IF EXISTS crmdb ;
-- 创建数据库
CREATE DATABASE crmdb CHARACTER SET UTF8 ;
-- 使用数据库
USE crmdb ;
-- 创建数据表
-- 1、创建权限组表
CREATE TABLE groups (
   gid                  INT	AUTO_INCREMENT,
   title                VARCHAR(50),
   img                  VARCHAR(50) ,
   type                  VARCHAR(50) ,
   CONSTRAINT pk_gid PRIMARY KEY (gid)
);
-- 2、创建权限表
CREATE TABLE action(
   actid                INT	AUTO_INCREMENT,
   title                VARCHAR(50),
   menu                 INT,
   url                  VARCHAR(200),
   CONSTRAINT pk_actid PRIMARY KEY (actid) 
);

-- 3、权限组-权限
CREATE TABLE groups_action (
   gid                  INT ,
   actid                INT ,
   CONSTRAINT fk_gid11 FOREIGN KEY(gid) REFERENCES groups(gid) ON DELETE CASCADE ,                
   CONSTRAINT fk_actid11 FOREIGN KEY(actid) REFERENCES action(actid) ON DELETE CASCADE 
) ;
-- 4、创建角色表
CREATE TABLE role (
   rid                  INT 	AUTO_INCREMENT ,
   title                VARCHAR(50),
   CONSTRAINT pk_rid PRIMARY KEY (rid) 
);

-- 5、创建角色-权限组
CREATE TABLE role_groups (
   rid                  INT,
   gid                  INT ,
   CONSTRAINT fk_rid4 FOREIGN KEY(rid) REFERENCES role(rid) ON DELETE CASCADE ,
   CONSTRAINT fk_gid4 FOREIGN KEY(gid) REFERENCES groups(gid) ON DELETE CASCADE
);

-- 6、创建用户表
CREATE TABLE member (
   mid                  VARCHAR(50) NOT NULL,
   rid                  INT,
   password             VARCHAR(32),
   tel                  VARCHAR(50),
   lastdate             DATETIME,
   photo                VARCHAR(200),
   flag                 INT,
   locked               INT,
   CONSTRAINT pk_mid PRIMARY KEY (MID) ,
   CONSTRAINT fk_rid FOREIGN KEY(rid) REFERENCES role(rid) ON DELETE CASCADE 
);
-- 7、创建登录日志表
CREATE TABLE logs (
   logid                INT	AUTO_INCREMENT ,
   mid                  VARCHAR(50),
   indate               DATETIME,
   CONSTRAINT pk_logid PRIMARY KEY (logid)  ,
   CONSTRAINT fk_mid FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE 
);

-- 8、公告信息表
CREATE TABLE news (
   nid                  INT	AUTO_INCREMENT ,
   mid                  VARCHAR(50),
   title                VARCHAR(50),
   type                 INT,
   pubdate              DATETIME,
   note                 TEXT,
   CONSTRAINT pk_nid PRIMARY KEY (nid) ,
   CONSTRAINT fk_mid7 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE SET NULL 
);

-- 9、用户公告阅读
CREATE TABLE member_news (
   mid                  VARCHAR(50),
   nid                  INT,
   rdate                DATETIME ,
   CONSTRAINT fk_mid8 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE CASCADE ,
   CONSTRAINT fk_nid8 FOREIGN KEY(nid) REFERENCES news(nid) ON DELETE CASCADE
);

-- 10、客户信息登记表
CREATE TABLE client (
   cid                  INT 	AUTO_INCREMENT ,
   mid                  VARCHAR(50),
   name                 VARCHAR(50),
   sex                  VARCHAR(10),
   email                VARCHAR(200),
   tel                  VARCHAR(200),
   qq                   VARCHAR(200),
   type                 INT,
   reg                  DATETIME,
   note                 TEXT,
   CONSTRAINT pk_cid PRIMARY KEY (cid) ,
   CONSTRAINT fk_mid9 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE SET NULL 
);

-- 11、客户回访记录表
CREATE TABLE task (
   tid                  INT 	AUTO_INCREMENT ,
   mid                  VARCHAR(50),
   cid                  INT,
   title                VARCHAR(50),
   tdate                DATE,
   visit                INT,
   type                 INT,
   note                 TEXT,
   status               INT,
   level                INT,
   CONSTRAINT pk_tid PRIMARY KEY (tid)  ,
   CONSTRAINT fk_mid10 FOREIGN KEY(mid) REFERENCES member(mid) ON DELETE SET NULL ,
   CONSTRAINT fk_cid10 FOREIGN KEY(cid) REFERENCES client(cid) ON DELETE CASCADE
);




-- 测试数据 
INSERT INTO role(title) VALUES ('系统管理员') ;
INSERT INTO role(title) VALUES ('信息管理员') ;
INSERT INTO role(title) VALUES ('客服人员') ;
-- 权限组信息
INSERT INTO groups(title,img,type) VALUES ('业务信息','leftico01.png' ,'客服人员') ;
INSERT INTO groups(title,img,type) VALUES ('任务管理','leftico02.png' ,'客服人员') ;
INSERT INTO groups(title,img,type) VALUES ('公告管理','leftico03.png' ,'客服人员') ;

INSERT INTO groups(title,img,type) VALUES ('公告管理','leftico03.png' ,'信息管理员') ;
INSERT INTO groups(title,img,type) VALUES ('客户管理','leftico05.png' ,'信息管理员') ;

INSERT INTO groups(title,img,type) VALUES ('公告管理','leftico03.png' ,'系统管理员') ;
INSERT INTO groups(title,img,type) VALUES ('权限管理','leftico04.png' ,'系统管理员') ;
INSERT INTO groups(title,img,type) VALUES ('客户管理','leftico05.png' ,'系统管理员') ;

-- 权限信息
INSERT INTO action(title,menu,url) VALUES ('添加客户',1,'/pages/back/client/ClientServletBack/addPre') ;
INSERT INTO action(title,menu,url) VALUES ('客户列表',1,'/pages/back/client/ClientServletBack/listSplit') ;
INSERT INTO action(title,menu,url) VALUES ('客户修改',0,'/pages/back/client/ClientServletBack/editPre') ;
INSERT INTO action(title,menu,url) VALUES ('客户删除',0,'/pages/back/client/ClientServletBack/rm') ;

INSERT INTO action(title,menu,url) VALUES ('添加任务',1,'/pages/back/task/TaskServletBack/addPre') ;
INSERT INTO action(title,menu,url) VALUES ('任务列表',1,'/pages/back/task/TaskServletBack/listSplit') ;
INSERT INTO action(title,menu,url) VALUES ('任务修改',0,'/pages/back/task/TaskServletBack/editPre') ;
INSERT INTO action(title,menu,url) VALUES ('任务完成',0,'/pages/back/task/TaskServletBack/finish') ;
INSERT INTO action(title,menu,url) VALUES ('任务关闭',0,'/pages/back/task/TaskServletBack/over') ;
INSERT INTO action(title,menu,url) VALUES ('任务删除',0,'/pages/back/task/TaskServletBack/rm') ;


INSERT INTO action(title,menu,url) VALUES ('公告列表',1,'/pages/back/news/NewsServletBack/list') ;
INSERT INTO action(title,menu,url) VALUES ('发布公告',1,'/pages/back/news/NewsServletBack/addPre') ;
INSERT INTO action(title,menu,url) VALUES ('公告修改',0,'/pages/back/news/NewsServletBack/editPre') ;
INSERT INTO action(title,menu,url) VALUES ('公告删除',0,'/pages/back/news/NewsServletBack/rm') ;

INSERT INTO action(title,menu,url) VALUES ('创建用户',1,'/pages/back/member/MemberServletBack/addPre') ;
INSERT INTO action(title,menu,url) VALUES ('用户列表',1,'/pages/back/member/MemberServletBack/listSplit') ;
INSERT INTO action(title,menu,url) VALUES ('用户修改',0,'/pages/back/member/MemberServletBack/editPre') ;
INSERT INTO action(title,menu,url) VALUES ('用户删除',0,'/pages/back/member/MemberServletBack/rm') ;
INSERT INTO action(title,menu,url) VALUES ('角色增加',1,'/pages/back/role/RoleServletBack/addPre') ;
INSERT INTO action(title,menu,url) VALUES ('角色列表',1,'/pages/back/role/RoleServletBack/list') ;
INSERT INTO action(title,menu,url) VALUES ('角色修改',0,'/pages/back/role/RoleServletBack/editPre') ;
INSERT INTO action(title,menu,url) VALUES ('角色删除',0,'/pages/back/role/RoleServletBack/rm') ;
INSERT INTO action(title,menu,url) VALUES ('权限组列表',1,'/pages/back/groups/GroupsServletBack/list') ;
INSERT INTO action(title,menu,url) VALUES ('权限组详情',0,'/pages/back/groups/GroupsServletBack/show') ;
INSERT INTO action(title,menu,url) VALUES ('权限列表',1,'/pages/back/action/ActionServletBack/list') ;

INSERT INTO action(title,menu,url) VALUES ('客户列表',1,'/pages/back/mclient/ManagerClientServletBack/listSplit') ;
INSERT INTO action(title,menu,url) VALUES ('客户详情',0,'/pages/back/mclient/ManagerClientServletBack/show') ;
INSERT INTO action(title,menu,url) VALUES ('用户任务列表',1,'/pages/back/mtask/ManagerTaskServletBack/listSplit') ;
INSERT INTO action(title,menu,url) VALUES ('查看客户任务',0,'/pages/back/mtask/ManagerTaskServletBack/listByClient') ;

INSERT INTO action(title,menu,url) VALUES ('客户任务列表',0,'/pages/back/task/TaskServletBack/listByClient') ;
INSERT INTO action(title,menu,url) VALUES ('任务详情',0,'/pages/back/task/TaskServletBack/show') ;
INSERT INTO action(title,menu,url) VALUES ('用户任务详情',0,'/pages/back/mtask/ManagerTaskServletBack/show') ;
INSERT INTO action(title,menu,url) VALUES ('公告详情',0,'/pages/back/news/NewsServletBack/show') ;
INSERT INTO action(title,menu,url) VALUES ('角色详情',0,'/pages/back/role/RoleServletBack/show') ;

-- 客服人员权限组
INSERT INTO groups_action(gid,actid) VALUES(1,1) ;
INSERT INTO groups_action(gid,actid) VALUES(1,2) ;
INSERT INTO groups_action(gid,actid) VALUES(1,3) ;
-- INSERT INTO groups_action(gid,actid) VALUES(1,4) ;
INSERT INTO groups_action(gid,actid) VALUES(2,5) ;
INSERT INTO groups_action(gid,actid) VALUES(2,6) ;
INSERT INTO groups_action(gid,actid) VALUES(2,7) ;
INSERT INTO groups_action(gid,actid) VALUES(2,8) ;
INSERT INTO groups_action(gid,actid) VALUES(2,9) ;
INSERT INTO groups_action(gid,actid) VALUES(2,10) ;
INSERT INTO groups_action(gid,actid) VALUES(2,30) ;
INSERT INTO groups_action(gid,actid) VALUES(2,31) ;
INSERT INTO groups_action(gid,actid) VALUES(3,11) ;
INSERT INTO groups_action(gid,actid) VALUES(3,33) ;

-- 公告管理员
INSERT INTO groups_action(gid,actid) VALUES(4,11) ;
INSERT INTO groups_action(gid,actid) VALUES(4,12) ;
INSERT INTO groups_action(gid,actid) VALUES(4,13) ;
INSERT INTO groups_action(gid,actid) VALUES(4,14) ;
INSERT INTO groups_action(gid,actid) VALUES(5,26) ;
INSERT INTO groups_action(gid,actid) VALUES(5,27) ;
INSERT INTO groups_action(gid,actid) VALUES(5,28) ;
INSERT INTO groups_action(gid,actid) VALUES(5,29) ;
INSERT INTO groups_action(gid,actid) VALUES(5,32) ;
INSERT INTO groups_action(gid,actid) VALUES(5,33) ;

-- 系统管理员
INSERT INTO groups_action(gid,actid) VALUES(6,11) ;
INSERT INTO groups_action(gid,actid) VALUES(6,12) ;
INSERT INTO groups_action(gid,actid) VALUES(6,13) ;
INSERT INTO groups_action(gid,actid) VALUES(6,14) ;
INSERT INTO groups_action(gid,actid) VALUES(7,15) ;
INSERT INTO groups_action(gid,actid) VALUES(7,16) ;
INSERT INTO groups_action(gid,actid) VALUES(7,17) ;
INSERT INTO groups_action(gid,actid) VALUES(7,18) ;
INSERT INTO groups_action(gid,actid) VALUES(7,19) ;
INSERT INTO groups_action(gid,actid) VALUES(7,20) ;
INSERT INTO groups_action(gid,actid) VALUES(7,21) ;
INSERT INTO groups_action(gid,actid) VALUES(7,22) ;
INSERT INTO groups_action(gid,actid) VALUES(7,23) ;
INSERT INTO groups_action(gid,actid) VALUES(7,24) ;
INSERT INTO groups_action(gid,actid) VALUES(7,25) ;
INSERT INTO groups_action(gid,actid) VALUES(7,34) ;
INSERT INTO groups_action(gid,actid) VALUES(8,26) ;
INSERT INTO groups_action(gid,actid) VALUES(8,27) ;
INSERT INTO groups_action(gid,actid) VALUES(8,28) ;
INSERT INTO groups_action(gid,actid) VALUES(8,29) ;
INSERT INTO groups_action(gid,actid) VALUES(8,32) ;
INSERT INTO groups_action(gid,actid) VALUES(8,33) ;

-- 管理员角色
INSERT INTO role_groups(rid,gid) VALUES (1,6) ;
INSERT INTO role_groups(rid,gid) VALUES (1,7) ;
INSERT INTO role_groups(rid,gid) VALUES (1,8) ;
-- 信息管理员角色
INSERT INTO role_groups(rid,gid) VALUES (2,4) ;
INSERT INTO role_groups(rid,gid) VALUES (2,5) ;
-- 客户人员角色
INSERT INTO role_groups(rid,gid) VALUES (3,1) ;
INSERT INTO role_groups(rid,gid) VALUES (3,2) ;
INSERT INTO role_groups(rid,gid) VALUES (3,3) ;

-- 管理员数据
-- 超级管理员：admin / hello
INSERT INTO member(mid,rid,password,tel,lastdate,photo,flag,locked) VALUES ('admin',1,'5D41402ABC4B2A76B9719D911017C592','110','2016-10-19','nophoto.jpg',1,0) ;
-- 信息管理员：mldn / java
INSERT INTO member(mid,rid,password,tel,lastdate,photo,flag,locked) VALUES ('mldn',2,'93F725A07423FE1C889F448B33D21F46','110','2016-10-19','nophoto.jpg',0,0) ;
-- 客户人员：mermaid / hello
INSERT INTO member(mid,rid,password,tel,lastdate,photo,flag,locked) VALUES ('mermaid',3,'5D41402ABC4B2A76B9719D911017C592','110','2016-10-19','nophoto.jpg',0,0) ;
