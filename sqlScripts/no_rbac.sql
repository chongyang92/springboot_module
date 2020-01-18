/*
Navicat MySQL Data Transfer

Source Server         : springboot
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : no_rbac

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-01-19 00:45:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
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
-- Records of menu
-- ----------------------------

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

-- ----------------------------
-- Table structure for `path`
-- ----------------------------
DROP TABLE IF EXISTS `path`;
CREATE TABLE `path` (
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
-- Records of path
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` varchar(50) NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `ORG_ID` varchar(50) DEFAULT NULL,
  `LOGIN_TIME` datetime DEFAULT NULL,
  `LOGOUT_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `LOGIN_FAIL_TIMES` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_menu`
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `USER_ID` varchar(50) NOT NULL,
  `MENU_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`USER_ID`,`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `user_path`
-- ----------------------------
DROP TABLE IF EXISTS `user_path`;
CREATE TABLE `user_path` (
  `USER_ID` varchar(50) NOT NULL,
  `PATH_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`USER_ID`,`PATH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_path
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='DB WorkerID Assigner for UID Generator';

-- ----------------------------
-- Records of worker_node
-- ----------------------------
INSERT INTO `worker_node` VALUES ('1', '192.168.43.138', '1579223947010-30900', '2', '2020-01-17', '2020-01-17 09:19:07', '2020-01-17 09:19:07');
INSERT INTO `worker_node` VALUES ('2', '192.168.0.106', '1579340983822-56476', '2', '2020-01-18', '2020-01-18 17:49:43', '2020-01-18 17:49:43');
INSERT INTO `worker_node` VALUES ('3', '192.168.0.106', '1579341265156-65022', '2', '2020-01-18', '2020-01-18 17:54:25', '2020-01-18 17:54:25');
INSERT INTO `worker_node` VALUES ('4', '192.168.0.106', '1579341332135-19716', '2', '2020-01-18', '2020-01-18 17:55:32', '2020-01-18 17:55:32');
INSERT INTO `worker_node` VALUES ('5', '192.168.0.106', '1579342075903-59300', '2', '2020-01-18', '2020-01-18 18:07:55', '2020-01-18 18:07:55');
INSERT INTO `worker_node` VALUES ('6', '192.168.0.106', '1579342934113-41934', '2', '2020-01-18', '2020-01-18 18:22:14', '2020-01-18 18:22:14');
INSERT INTO `worker_node` VALUES ('7', '192.168.0.106', '1579343052884-52941', '2', '2020-01-18', '2020-01-18 18:24:12', '2020-01-18 18:24:12');
INSERT INTO `worker_node` VALUES ('8', '192.168.0.106', '1579343661894-98120', '2', '2020-01-18', '2020-01-18 18:34:21', '2020-01-18 18:34:21');
INSERT INTO `worker_node` VALUES ('9', '192.168.0.106', '1579343802772-9605', '2', '2020-01-18', '2020-01-18 18:36:42', '2020-01-18 18:36:42');
INSERT INTO `worker_node` VALUES ('10', '192.168.0.106', '1579344566239-90836', '2', '2020-01-18', '2020-01-18 18:49:26', '2020-01-18 18:49:26');
INSERT INTO `worker_node` VALUES ('11', '192.168.0.106', '1579345986421-7308', '2', '2020-01-18', '2020-01-18 19:13:06', '2020-01-18 19:13:06');
INSERT INTO `worker_node` VALUES ('12', '192.168.0.106', '1579346614611-17916', '2', '2020-01-18', '2020-01-18 19:23:34', '2020-01-18 19:23:34');
INSERT INTO `worker_node` VALUES ('13', '192.168.0.106', '1579346836365-38849', '2', '2020-01-18', '2020-01-18 19:27:16', '2020-01-18 19:27:16');
INSERT INTO `worker_node` VALUES ('14', '192.168.0.106', '1579347350648-71091', '2', '2020-01-18', '2020-01-18 19:35:50', '2020-01-18 19:35:50');
INSERT INTO `worker_node` VALUES ('15', '192.168.0.106', '1579347445055-86823', '2', '2020-01-18', '2020-01-18 19:37:25', '2020-01-18 19:37:25');
INSERT INTO `worker_node` VALUES ('16', '192.168.0.106', '1579348149795-66515', '2', '2020-01-18', '2020-01-18 19:49:09', '2020-01-18 19:49:09');
INSERT INTO `worker_node` VALUES ('17', '192.168.0.106', '1579349214455-6607', '2', '2020-01-18', '2020-01-18 20:06:54', '2020-01-18 20:06:54');
INSERT INTO `worker_node` VALUES ('18', '192.168.0.106', '1579350109046-49438', '2', '2020-01-18', '2020-01-18 20:21:49', '2020-01-18 20:21:49');
INSERT INTO `worker_node` VALUES ('19', '192.168.0.106', '1579350381325-65363', '2', '2020-01-18', '2020-01-18 20:26:21', '2020-01-18 20:26:21');
INSERT INTO `worker_node` VALUES ('20', '192.168.0.106', '1579351078904-67189', '2', '2020-01-18', '2020-01-18 20:37:58', '2020-01-18 20:37:58');
INSERT INTO `worker_node` VALUES ('21', '192.168.0.106', '1579351382681-95213', '2', '2020-01-18', '2020-01-18 20:43:02', '2020-01-18 20:43:02');
INSERT INTO `worker_node` VALUES ('22', '192.168.0.106', '1579351784160-22945', '2', '2020-01-18', '2020-01-18 20:49:44', '2020-01-18 20:49:44');
INSERT INTO `worker_node` VALUES ('23', '192.168.0.106', '1579352147015-65654', '2', '2020-01-18', '2020-01-18 20:55:47', '2020-01-18 20:55:47');
INSERT INTO `worker_node` VALUES ('24', '192.168.0.106', '1579352217314-81917', '2', '2020-01-18', '2020-01-18 20:56:57', '2020-01-18 20:56:57');
INSERT INTO `worker_node` VALUES ('25', '192.168.0.106', '1579352373017-37292', '2', '2020-01-18', '2020-01-18 20:59:33', '2020-01-18 20:59:33');
INSERT INTO `worker_node` VALUES ('26', '192.168.0.106', '1579353246652-61554', '2', '2020-01-18', '2020-01-18 21:14:06', '2020-01-18 21:14:06');
INSERT INTO `worker_node` VALUES ('27', '192.168.0.106', '1579353677891-42041', '2', '2020-01-18', '2020-01-18 21:21:17', '2020-01-18 21:21:17');
INSERT INTO `worker_node` VALUES ('28', '192.168.0.106', '1579354208815-14283', '2', '2020-01-18', '2020-01-18 21:30:08', '2020-01-18 21:30:08');
INSERT INTO `worker_node` VALUES ('29', '192.168.0.106', '1579354363662-37467', '2', '2020-01-18', '2020-01-18 21:32:43', '2020-01-18 21:32:43');
INSERT INTO `worker_node` VALUES ('30', '192.168.0.106', '1579354467509-67581', '2', '2020-01-18', '2020-01-18 21:34:27', '2020-01-18 21:34:27');
INSERT INTO `worker_node` VALUES ('31', '192.168.0.106', '1579354513633-15378', '2', '2020-01-18', '2020-01-18 21:35:13', '2020-01-18 21:35:13');
INSERT INTO `worker_node` VALUES ('32', '192.168.0.106', '1579355819259-48923', '2', '2020-01-18', '2020-01-18 21:56:59', '2020-01-18 21:56:59');
INSERT INTO `worker_node` VALUES ('33', '192.168.0.106', '1579355960332-74687', '2', '2020-01-18', '2020-01-18 21:59:20', '2020-01-18 21:59:20');
