/*
Navicat MySQL Data Transfer

Source Server         : springboot
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-02-09 18:35:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `org`
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `ORG_ID` varchar(50) NOT NULL DEFAULT '' COMMENT '机构ID',
  `PARENT_ORG_ID` varchar(50) NOT NULL COMMENT '父机构ID',
  `ORG_NAME` varchar(200) NOT NULL COMMENT '机构名称',
  `REAL_ORG_ID` varchar(20) NOT NULL COMMENT '机构号',
  `STATUS` varchar(10) NOT NULL COMMENT '状态',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ORG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org
-- ----------------------------
INSERT INTO `org` VALUES ('4036162977743790080', 'Root', '中信银行总行', '71000', 'NORMAL', '2020-02-08 14:00:04', '2020-02-08 14:00:04');
INSERT INTO `org` VALUES ('4036162977743790081', '4036162977743790080', '中信银行北京分行', '71100', 'NORMAL', '2020-02-08 14:00:18', '2020-02-08 14:00:18');
INSERT INTO `org` VALUES ('4036162977743790082', '4036162977743790080', '中信银行天津分行', '71200', 'NORMAL', '2020-02-08 14:00:32', '2020-02-08 14:00:32');
INSERT INTO `org` VALUES ('4036162977743790083', '4036162977743790080', '中信银行上海分行', '71300', 'NORMAL', '2020-02-08 14:00:42', '2020-02-08 14:00:42');

-- ----------------------------
-- Table structure for `perm`
-- ----------------------------
DROP TABLE IF EXISTS `perm`;
CREATE TABLE `perm` (
  `PERM_ID` varchar(50) NOT NULL,
  `PERM_NAME` varchar(100) NOT NULL,
  `PARENT_PERM_ID` varchar(50) NOT NULL,
  `TYPE` varchar(2) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(300) NOT NULL,
  PRIMARY KEY (`PERM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of perm
-- ----------------------------
INSERT INTO `perm` VALUES ('4039390800285614089', '系统管理权限', 'Root', '1', '2020-02-09 16:09:36', '2020-02-09 16:09:36', '所有系统管理的权限');
INSERT INTO `perm` VALUES ('4039415951614115840', '机构管理权限', '4039390800285614089', '1', '2020-02-09 16:14:45', '2020-02-09 16:14:45', '机构管理的权限');
INSERT INTO `perm` VALUES ('4039415951614115841', '用户管理权限', '4039390800285614089', '1', '2020-02-09 16:16:45', '2020-02-09 16:16:45', '用户管理的权限');
INSERT INTO `perm` VALUES ('4039415951614115842', '角色管理权限', '4039390800285614089', '1', '2020-02-09 16:17:46', '2020-02-09 16:17:46', '角色管理的权限');
INSERT INTO `perm` VALUES ('4039415951614115843', '权限管理权限', '4039390800285614089', '1', '2020-02-09 16:18:45', '2020-02-09 16:18:45', '权限管理的权限');
INSERT INTO `perm` VALUES ('4039415951614115844', '菜单管理权限', '4039390800285614089', '1', '2020-02-09 16:46:52', '2020-02-09 16:46:52', '菜单管理的权限');
INSERT INTO `perm` VALUES ('4039415951614115845', '路径管理权限', '4039390800285614089', '1', '2020-02-09 16:47:58', '2020-02-09 16:47:58', '路径管理的权限');
INSERT INTO `perm` VALUES ('4039415951614115846', '业务管理权限', '4039390800285614089', '1', '2020-02-09 16:49:17', '2020-02-09 16:49:17', '业务管理的权限');

-- ----------------------------
-- Table structure for `perm_menu`
-- ----------------------------
DROP TABLE IF EXISTS `perm_menu`;
CREATE TABLE `perm_menu` (
  `PERM_ID` varchar(50) NOT NULL,
  `MENU_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`PERM_ID`,`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of perm_menu
-- ----------------------------
INSERT INTO `perm_menu` VALUES ('4039415951614115840', '4036162977743790084');
INSERT INTO `perm_menu` VALUES ('4039415951614115841', '4039390800285614083');
INSERT INTO `perm_menu` VALUES ('4039415951614115842', '4039390800285614084');
INSERT INTO `perm_menu` VALUES ('4039415951614115843', '4039390800285614085');
INSERT INTO `perm_menu` VALUES ('4039415951614115844', '4039390800285614086');
INSERT INTO `perm_menu` VALUES ('4039415951614115845', '4039390800285614087');
INSERT INTO `perm_menu` VALUES ('4039415951614115846', '4039390800285614088');

-- ----------------------------
-- Table structure for `perm_path`
-- ----------------------------
DROP TABLE IF EXISTS `perm_path`;
CREATE TABLE `perm_path` (
  `PERM_ID` varchar(50) NOT NULL,
  `PATH_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`PERM_ID`,`PATH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of perm_path
-- ----------------------------
INSERT INTO `perm_path` VALUES ('4039415951614115840', '4036162977743790091');
INSERT INTO `perm_path` VALUES ('4039415951614115841', '4036162977743790092');
INSERT INTO `perm_path` VALUES ('4039415951614115842', '4036162977743790093');
INSERT INTO `perm_path` VALUES ('4039415951614115843', '4036162977743790094');
INSERT INTO `perm_path` VALUES ('4039415951614115844', '4036162977743790095');
INSERT INTO `perm_path` VALUES ('4039415951614115845', '4036162977743790096');
INSERT INTO `perm_path` VALUES ('4039415951614115846', '4036162977743790097');

-- ----------------------------
-- Table structure for `res_menu`
-- ----------------------------
DROP TABLE IF EXISTS `res_menu`;
CREATE TABLE `res_menu` (
  `MENU_ID` varchar(50) NOT NULL,
  `MENU_NAME` varchar(100) NOT NULL,
  `PARENT_MENU_ID` varchar(50) NOT NULL,
  `TYPE` varchar(2) NOT NULL,
  `ACTION` varchar(50) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_menu
-- ----------------------------
INSERT INTO `res_menu` VALUES ('4039390800285614080', '系统管理', 'Root', '1', 'SystemManager', '2020-02-09 16:02:31', '2020-02-09 16:02:31');
INSERT INTO `res_menu` VALUES ('4039390800285614081', '业务管理', 'Root', '2', 'BusinessManager', '2020-02-09 16:02:39', '2020-02-09 16:02:39');
INSERT INTO `res_menu` VALUES ('4039390800285614082', '机构管理', '4039390800285614080', '1', 'SystemManager.OrgMannger', '2020-02-09 16:03:04', '2020-02-09 16:04:51');
INSERT INTO `res_menu` VALUES ('4039390800285614083', '用户管理', '4039390800285614080', '1', 'SystemManager.UserManager', '2020-02-09 16:03:33', '2020-02-09 16:03:33');
INSERT INTO `res_menu` VALUES ('4039390800285614084', '角色管理', '4039390800285614080', '1', 'SystemManager.RoleManager', '2020-02-09 16:03:52', '2020-02-09 16:03:52');
INSERT INTO `res_menu` VALUES ('4039390800285614085', '权限管理', '4039390800285614080', '1', 'SystemManager.PermManager', '2020-02-09 16:04:07', '2020-02-09 16:04:07');
INSERT INTO `res_menu` VALUES ('4039390800285614086', '菜单管理', '4039390800285614080', '1', 'SystemManager.MenuManager', '2020-02-09 16:04:31', '2020-02-09 16:04:31');
INSERT INTO `res_menu` VALUES ('4039390800285614087', '路径管理', '4039390800285614080', '1', 'SystemManager.PathManager', '2020-02-09 16:05:35', '2020-02-09 16:05:35');
INSERT INTO `res_menu` VALUES ('4039390800285614088', '业务管理', '4039390800285614081', '1', 'BusinessManager.Business1', '2020-02-09 16:05:59', '2020-02-09 16:48:51');

-- ----------------------------
-- Table structure for `res_path`
-- ----------------------------
DROP TABLE IF EXISTS `res_path`;
CREATE TABLE `res_path` (
  `PATH_ID` varchar(50) NOT NULL,
  `HTTP_PATH` varchar(300) NOT NULL,
  `HTTP_METHOD_TYPE` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(400) NOT NULL,
  `TYPE` varchar(2) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PATH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_path
-- ----------------------------
INSERT INTO `res_path` VALUES ('4036162977743790091', '/org/create', 'POST', '创建机构', '1', '2020-02-08 14:36:18', '2020-02-08 14:36:18');
INSERT INTO `res_path` VALUES ('4036162977743790092', '/user/create', 'POST', '创建用户', '1', '2020-02-08 14:36:22', '2020-02-08 14:36:22');
INSERT INTO `res_path` VALUES ('4036162977743790093', '/role/create', 'POST', '创建角色', '1', '2020-02-08 14:36:26', '2020-02-08 14:36:26');
INSERT INTO `res_path` VALUES ('4036162977743790094', '/perm/create', 'POST', '创建权限', '1', '2020-02-08 14:36:30', '2020-02-08 14:36:30');
INSERT INTO `res_path` VALUES ('4036162977743790095', '/menu/create', 'POST', '创建菜单', '1', '2020-02-08 14:36:33', '2020-02-08 14:36:33');
INSERT INTO `res_path` VALUES ('4036162977743790096', '/path/create', 'POST', '创建路径', '1', '2020-02-08 14:42:32', '2020-02-08 14:42:32');
INSERT INTO `res_path` VALUES ('4036162977743790097', '/business/create', 'POST', '业务访问路径', '2', '2020-02-08 14:42:35', '2020-02-08 14:42:47');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ROLE_ID` varchar(50) NOT NULL,
  `ROLE_NAME` varchar(100) NOT NULL,
  `TYPE` varchar(2) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DESCRIPTION` varchar(300) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('4039510990650458112', 'root角色', '1', '2020-02-09 17:03:43', '2020-02-09 17:03:43', '拥有所有系统权限');
INSERT INTO `role` VALUES ('4039510990650458113', '管理员角色', '1', '2020-02-09 17:03:51', '2020-02-09 17:03:51', '拥有用户权限');
INSERT INTO `role` VALUES ('4039510990650458114', '业务管理员角色', '2', '2020-02-09 17:03:55', '2020-02-09 17:15:43', '拥有所有业务权限');
INSERT INTO `role` VALUES ('4039510990650458115', '分行业务角色', '2', '2020-02-09 17:03:58', '2020-02-09 17:15:40', '拥有分行业务权限');

-- ----------------------------
-- Table structure for `role_perm`
-- ----------------------------
DROP TABLE IF EXISTS `role_perm`;
CREATE TABLE `role_perm` (
  `ROLE_ID` varchar(50) NOT NULL,
  `PERM_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`PERM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_perm
-- ----------------------------
INSERT INTO `role_perm` VALUES ('4039510990650458112', '4039390800285614089');
INSERT INTO `role_perm` VALUES ('4039510990650458112', '4039415951614115840');
INSERT INTO `role_perm` VALUES ('4039510990650458112', '4039415951614115841');
INSERT INTO `role_perm` VALUES ('4039510990650458112', '4039415951614115842');
INSERT INTO `role_perm` VALUES ('4039510990650458112', '4039415951614115843');
INSERT INTO `role_perm` VALUES ('4039510990650458112', '4039415951614115844');
INSERT INTO `role_perm` VALUES ('4039510990650458112', '4039415951614115845');
INSERT INTO `role_perm` VALUES ('4039510990650458113', '4039390800285614089');
INSERT INTO `role_perm` VALUES ('4039510990650458113', '4039415951614115840');
INSERT INTO `role_perm` VALUES ('4039510990650458113', '4039415951614115842');
INSERT INTO `role_perm` VALUES ('4039510990650458113', '4039415951614115843');
INSERT INTO `role_perm` VALUES ('4039510990650458113', '4039415951614115844');
INSERT INTO `role_perm` VALUES ('4039510990650458113', '4039415951614115845');
INSERT INTO `role_perm` VALUES ('4039510990650458114', '4039415951614115840');
INSERT INTO `role_perm` VALUES ('4039510990650458114', '4039415951614115846');
INSERT INTO `role_perm` VALUES ('4039510990650458115', '4039415951614115846');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` varchar(50) NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `ORG_ID` varchar(50) NOT NULL,
  `LOGIN_TIME` datetime DEFAULT NULL,
  `LOGOUT_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `LOGIN_FAIL_TIMES` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('4039537516368486400', 'root', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '4036162977743790080', null, null, '2020-02-09 17:16:18', '2020-02-09 17:16:18', null, '0');
INSERT INTO `user` VALUES ('4039537516368486401', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '4036162977743790080', null, null, '2020-02-09 17:17:44', '2020-02-09 17:17:44', null, '0');
INSERT INTO `user` VALUES ('4039537516368486402', 'zhadmin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '4036162977743790080', null, null, '2020-02-09 17:18:29', '2020-02-09 17:18:29', null, '0');
INSERT INTO `user` VALUES ('4039537516368486403', 'zy-jb', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '4036162977743790081', null, null, '2020-02-09 17:19:14', '2020-02-09 17:19:14', null, '0');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `USER_ID` varchar(50) NOT NULL,
  `ROLE_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4039537516368486400', '4039510990650458112');
INSERT INTO `user_role` VALUES ('4039537516368486401', '4039510990650458113');
INSERT INTO `user_role` VALUES ('4039537516368486402', '4039510990650458114');
INSERT INTO `user_role` VALUES ('4039537516368486403', '4039510990650458115');

-- ----------------------------
-- Table structure for `worker_node`
-- ----------------------------
DROP TABLE IF EXISTS `worker_node`;
CREATE TABLE `worker_node` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
  `HOST_NAME` varchar(64) NOT NULL COMMENT 'host name',
  `PORT` varchar(64) NOT NULL COMMENT 'port',
  `TYPE` int(11) NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
  `LAUNCH_DATE` date NOT NULL COMMENT 'launch date',
  `MODIFIED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'modified time',
  `CREATED` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'created time',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='DB WorkerID Assigner for UID Generator';

-- ----------------------------
-- Records of worker_node
-- ----------------------------
INSERT INTO `worker_node` VALUES ('1', '192.168.0.13', '1581140231493-83836', '2', '2020-02-08', '2020-02-08 13:37:11', '2020-02-08 13:37:11');
INSERT INTO `worker_node` VALUES ('2', '192.168.0.13', '1581141202588-5461', '2', '2020-02-08', '2020-02-08 13:53:22', '2020-02-08 13:53:22');
INSERT INTO `worker_node` VALUES ('3', '192.168.0.13', '1581141402868-17605', '2', '2020-02-08', '2020-02-08 13:56:42', '2020-02-08 13:56:42');
INSERT INTO `worker_node` VALUES ('4', '192.168.0.13', '1581146891730-17213', '2', '2020-02-08', '2020-02-08 15:28:11', '2020-02-08 15:28:11');
INSERT INTO `worker_node` VALUES ('5', '192.168.0.13', '1581150859040-86775', '2', '2020-02-08', '2020-02-08 16:34:19', '2020-02-08 16:34:19');
INSERT INTO `worker_node` VALUES ('6', '192.168.0.13', '1581150918291-28647', '2', '2020-02-08', '2020-02-08 16:35:18', '2020-02-08 16:35:18');
INSERT INTO `worker_node` VALUES ('7', '192.168.0.13', '1581151310795-82787', '2', '2020-02-08', '2020-02-08 16:41:50', '2020-02-08 16:41:50');
INSERT INTO `worker_node` VALUES ('8', '192.168.0.13', '1581152525177-80472', '2', '2020-02-08', '2020-02-08 17:02:05', '2020-02-08 17:02:05');
INSERT INTO `worker_node` VALUES ('9', '192.168.0.13', '1581153236132-90995', '2', '2020-02-08', '2020-02-08 17:13:56', '2020-02-08 17:13:56');
INSERT INTO `worker_node` VALUES ('10', '192.168.0.13', '1581235344854-90378', '2', '2020-02-09', '2020-02-09 16:02:24', '2020-02-09 16:02:24');
INSERT INTO `worker_node` VALUES ('11', '192.168.0.13', '1581236000179-41097', '2', '2020-02-09', '2020-02-09 16:13:20', '2020-02-09 16:13:20');
INSERT INTO `worker_node` VALUES ('12', '192.168.0.13', '1581236076873-78189', '2', '2020-02-09', '2020-02-09 16:14:36', '2020-02-09 16:14:36');
INSERT INTO `worker_node` VALUES ('13', '192.168.0.13', '1581238660552-39880', '2', '2020-02-09', '2020-02-09 16:57:40', '2020-02-09 16:57:40');
INSERT INTO `worker_node` VALUES ('14', '192.168.0.13', '1581238842126-58642', '2', '2020-02-09', '2020-02-09 17:00:42', '2020-02-09 17:00:42');
INSERT INTO `worker_node` VALUES ('15', '192.168.0.13', '1581239614798-12225', '2', '2020-02-09', '2020-02-09 17:13:34', '2020-02-09 17:13:34');
INSERT INTO `worker_node` VALUES ('16', '192.168.0.13', '1581242566133-57765', '2', '2020-02-09', '2020-02-09 18:02:46', '2020-02-09 18:02:46');
INSERT INTO `worker_node` VALUES ('17', '192.168.0.13', '1581242763888-26454', '2', '2020-02-09', '2020-02-09 18:06:03', '2020-02-09 18:06:03');
INSERT INTO `worker_node` VALUES ('18', '192.168.0.13', '1581243863137-67496', '2', '2020-02-09', '2020-02-09 18:24:23', '2020-02-09 18:24:23');
INSERT INTO `worker_node` VALUES ('19', '192.168.0.13', '1581243964823-17942', '2', '2020-02-09', '2020-02-09 18:26:04', '2020-02-09 18:26:04');
INSERT INTO `worker_node` VALUES ('20', '192.168.0.13', '1581244063901-39447', '2', '2020-02-09', '2020-02-09 18:27:43', '2020-02-09 18:27:43');
INSERT INTO `worker_node` VALUES ('21', '192.168.0.13', '1581244270361-75182', '2', '2020-02-09', '2020-02-09 18:31:10', '2020-02-09 18:31:10');
