/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-06-27 17:58:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT,
  `logname` varchar(255) DEFAULT NULL,
  `userid` int(65) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `succeed` varchar(255) DEFAULT NULL,
  `message` text,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT,
  `logtype` varchar(255) DEFAULT NULL,
  `logname` varchar(255) DEFAULT NULL,
  `userid` int(65) DEFAULT NULL,
  `classname` varchar(255) DEFAULT NULL,
  `method` text,
  `createtime` datetime DEFAULT NULL,
  `succeed` varchar(255) DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=494 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for rc_dept
-- ----------------------------
DROP TABLE IF EXISTS `rc_dept`;
CREATE TABLE `rc_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `simplename` varchar(45) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_dept
-- ----------------------------
INSERT INTO `rc_dept` VALUES ('34', null, '0', '总公司', '总公司', null, null);
INSERT INTO `rc_dept` VALUES ('35', null, '34', '技术部', '技术部', null, null);
INSERT INTO `rc_dept` VALUES ('36', null, '34', '运营部', '运营部', null, null);
INSERT INTO `rc_dept` VALUES ('37', null, '34', '招商部', '招商部', null, null);

-- ----------------------------
-- Table structure for rc_dict
-- ----------------------------
DROP TABLE IF EXISTS `rc_dict`;
CREATE TABLE `rc_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_dict
-- ----------------------------
INSERT INTO `rc_dict` VALUES ('16', '0', '0', '状态', null);
INSERT INTO `rc_dict` VALUES ('17', '1', '16', '启用', null);
INSERT INTO `rc_dict` VALUES ('18', '2', '16', '禁用', null);
INSERT INTO `rc_dict` VALUES ('29', '0', '0', '性别', null);
INSERT INTO `rc_dict` VALUES ('30', '1', '29', '男', null);
INSERT INTO `rc_dict` VALUES ('31', '2', '29', '女', null);
INSERT INTO `rc_dict` VALUES ('35', '0', '0', '账号状态', null);
INSERT INTO `rc_dict` VALUES ('36', '1', '35', '启用', null);
INSERT INTO `rc_dict` VALUES ('37', '2', '35', '冻结', null);
INSERT INTO `rc_dict` VALUES ('38', '3', '35', '已删除', null);

-- ----------------------------
-- Table structure for rc_permission
-- ----------------------------
DROP TABLE IF EXISTS `rc_permission`;
CREATE TABLE `rc_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status_id` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `permissions_name` varchar(20) NOT NULL COMMENT '权限名',
  `permissions_value` varchar(20) NOT NULL COMMENT '权限值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_permission_name` (`permissions_name`),
  UNIQUE KEY `unique_permission_value` (`permissions_value`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_permission
-- ----------------------------
INSERT INTO `rc_permission` VALUES ('1', '1', '2017-06-22 16:34:28', '2017-06-26 12:39:46', '管理员查看', 'admin:read');
INSERT INTO `rc_permission` VALUES ('2', '1', '2017-06-22 16:35:47', null, '管理员添加', 'admin:insert');
INSERT INTO `rc_permission` VALUES ('3', '1', '2017-06-22 16:36:23', null, '管理员删除', 'admin:delete');
INSERT INTO `rc_permission` VALUES ('4', '1', '2017-06-22 16:36:55', null, '管理员修改', 'admin:update');
INSERT INTO `rc_permission` VALUES ('5', '1', '2017-06-22 16:37:54', null, '用户修改', 'user:update');
INSERT INTO `rc_permission` VALUES ('6', '1', '2017-06-22 16:38:22', null, '用户查看', 'user:read');
INSERT INTO `rc_permission` VALUES ('7', '1', '2017-06-22 16:38:45', null, '用户添加', 'user:insert');
INSERT INTO `rc_permission` VALUES ('8', '1', '2017-06-22 16:39:25', null, '用户删除', 'user:delete');
INSERT INTO `rc_permission` VALUES ('10', '1', '2017-06-22 16:07:53', null, '超级管理员删除', 'super:delete');
INSERT INTO `rc_permission` VALUES ('11', '1', '2017-06-22 16:08:16', null, '超级管理员查看', 'super:read');
INSERT INTO `rc_permission` VALUES ('12', '1', '2017-06-22 16:08:47', null, '超级管理员修改', 'super:update');
INSERT INTO `rc_permission` VALUES ('13', '1', '2017-06-22 16:09:14', null, '超级管理员添加', 'super:insert');

-- ----------------------------
-- Table structure for rc_relation
-- ----------------------------
DROP TABLE IF EXISTS `rc_relation`;
CREATE TABLE `rc_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3713 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_relation
-- ----------------------------
INSERT INTO `rc_relation` VALUES ('3679', '1', '8', '2017-06-22 10:40:19', null);
INSERT INTO `rc_relation` VALUES ('3680', '2', '8', '2017-06-22 10:40:30', null);
INSERT INTO `rc_relation` VALUES ('3681', '3', '8', '2017-06-22 10:40:38', null);
INSERT INTO `rc_relation` VALUES ('3682', '4', '8', '2017-06-22 10:40:48', null);
INSERT INTO `rc_relation` VALUES ('3683', '5', '8', '2017-06-22 10:40:55', null);
INSERT INTO `rc_relation` VALUES ('3684', '6', '8', '2017-06-22 10:41:03', null);
INSERT INTO `rc_relation` VALUES ('3685', '7', '8', '2017-06-22 10:41:11', null);
INSERT INTO `rc_relation` VALUES ('3686', '8', '8', '2017-06-22 10:41:18', null);
INSERT INTO `rc_relation` VALUES ('3687', '10', '8', '2017-06-22 10:41:40', null);
INSERT INTO `rc_relation` VALUES ('3688', '11', '8', '2017-06-22 10:41:50', null);
INSERT INTO `rc_relation` VALUES ('3689', '12', '8', '2017-06-22 10:41:58', null);
INSERT INTO `rc_relation` VALUES ('3690', '13', '8', '2017-06-22 10:42:06', null);
INSERT INTO `rc_relation` VALUES ('3691', '6', '15', '2017-06-23 10:03:57', null);
INSERT INTO `rc_relation` VALUES ('3704', '1', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3705', '2', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3706', '3', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3707', '4', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3708', '5', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3709', '6', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3710', '7', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3711', '8', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3712', '81', '6', '2017-06-26 12:46:09', null);

-- ----------------------------
-- Table structure for rc_role
-- ----------------------------
DROP TABLE IF EXISTS `rc_role`;
CREATE TABLE `rc_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`name`),
  UNIQUE KEY `unique_role_value` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_role
-- ----------------------------
INSERT INTO `rc_role` VALUES ('6', '管理员', 'admin', null, '2017-06-20 15:07:13', '2017-06-26 12:46:09', '1');
INSERT INTO `rc_role` VALUES ('8', '超级管理员', 'super', null, '2017-06-20 15:08:45', null, '1');
INSERT INTO `rc_role` VALUES ('15', '用户', 'user', null, '2017-06-23 10:03:57', null, '1');

-- ----------------------------
-- Table structure for rc_user
-- ----------------------------
DROP TABLE IF EXISTS `rc_user`;
CREATE TABLE `rc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_user
-- ----------------------------
INSERT INTO `rc_user` VALUES ('46', null, 'super', '5844591dff62349ce65f98c60baa669e', 'e8z0i', '超级管理员', '2017-06-22 14:26:09', '1', null, null, '8', '34', '1', '2017-06-20 15:12:16', null);
INSERT INTO `rc_user` VALUES ('47', null, 'test', 'a387925ac992274f2099389df1d68b16', 'o2p9n', '', null, '1', null, null, '15', null, '1', '2017-06-26 17:23:22', null);
INSERT INTO `rc_user` VALUES ('48', null, 'admin', '439b9b33eb18d644f3b57e182f45b86a', 'bycca', '管理员', null, '1', null, null, '6', null, '1', '2017-06-26 17:31:41', null);
INSERT INTO `rc_user` VALUES ('49', null, 'test2', '8e16dd198cee5158b05301081bea900e', 'f15nh', '王五', null, '1', null, null, null, null, '1', '2017-06-26 17:43:19', null);
