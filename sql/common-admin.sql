/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : common-admin

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2017-08-21 11:50:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rc_dept
-- ----------------------------
DROP TABLE IF EXISTS `rc_dept`;
CREATE TABLE `rc_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `simple_name` varchar(45) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
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
-- Table structure for rc_login_log
-- ----------------------------
DROP TABLE IF EXISTS `rc_login_log`;
CREATE TABLE `rc_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_name` varchar(255) DEFAULT NULL,
  `user_id` int(65) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `succeed` varchar(255) DEFAULT NULL,
  `message` text,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for rc_menu
-- ----------------------------
DROP TABLE IF EXISTS `rc_menu`;
CREATE TABLE `rc_menu` (
  `id` varchar(64) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `p_code` varchar(255) DEFAULT NULL COMMENT '菜单父编码',
  `p_id` varchar(255) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `is_menu` int(11) DEFAULT NULL COMMENT '是否是菜单',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `status` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_CODE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_menu
-- ----------------------------
INSERT INTO `rc_menu` VALUES ('000000000000000000', 'root', '0', '0', '系统根目录', '', '1', '0', '1', '1', null, '2017-08-03 18:31:54', null);
INSERT INTO `rc_menu` VALUES ('893287144657780736', 'system', 'root', '000000000000000000', '系统设置', 'system', '1', '1', '10', '1', '', '2017-08-04 09:47:06', null);
INSERT INTO `rc_menu` VALUES ('893288715881807872', 'userList', 'system', '893287144657780736', '用户管理', 'user/list', '1', '2', '1', '1', '', '2017-08-04 09:53:21', '2017-08-07 18:18:39');
INSERT INTO `rc_menu` VALUES ('893304960282787840', 'user/add', 'userList', '893288715881807872', '用户添加', 'user/add', '0', '3', '1', '1', '', '2017-08-04 10:57:54', '2017-08-08 11:02:55');
INSERT INTO `rc_menu` VALUES ('894396523532517376', 'user/edit', 'userList', '893288715881807872', '用户修改', 'user/edit', '0', '3', '1', '1', '', '2017-08-07 11:15:23', '2017-08-07 16:57:52');
INSERT INTO `rc_menu` VALUES ('894473486712438784', 'user/view', 'userList', '893288715881807872', '用户查看', 'user/View', '0', '3', '2', '1', '', '2017-08-07 16:21:12', null);
INSERT INTO `rc_menu` VALUES ('894473651837992960', 'user/delete', 'userList', '893288715881807872', '用户删除', 'user/delete', '0', '3', '4', '1', '', '2017-08-07 16:21:52', null);
INSERT INTO `rc_menu` VALUES ('894475142061621248', 'roleList', 'system', '893287144657780736', '角色管理', 'role/list', '1', '2', '2', '1', '', '2017-08-07 16:27:47', '2017-08-08 10:34:56');
INSERT INTO `rc_menu` VALUES ('894475827880656896', 'role/add', 'roleList', '894475142061621248', '角色添加', 'role/add', '0', '3', '1', '1', '', '2017-08-07 16:30:31', null);
INSERT INTO `rc_menu` VALUES ('894475985452269568', 'role/edit', 'roleList', '894475142061621248', '角色编辑', 'role/edit', '0', '3', '2', '1', '', '2017-08-07 16:31:08', null);
INSERT INTO `rc_menu` VALUES ('894476118730473472', 'role/delete', 'roleList', '894475142061621248', '角色删除', 'role/delete', '0', '3', '2', '1', '', '2017-08-07 16:31:40', '2017-08-07 16:37:24');
INSERT INTO `rc_menu` VALUES ('894476276402749440', 'role/permission', 'roleList', '894475142061621248', '角色配权', 'role/permission', '0', '3', '3', '1', '', '2017-08-07 16:32:18', null);
INSERT INTO `rc_menu` VALUES ('894476950951690240', 'menu/list', 'system', '893287144657780736', '菜单管理', 'menu/list', '1', '2', '2', '1', '', '2017-08-07 16:34:58', null);
INSERT INTO `rc_menu` VALUES ('894477107919323136', 'menu/add', 'menu/list', '894476950951690240', '菜单添加', 'menu/add', '0', '3', '1', '1', '', '2017-08-07 16:35:36', null);
INSERT INTO `rc_menu` VALUES ('894477244926263296', 'menu/edit', 'menu/list', '894476950951690240', '菜单编辑', 'menu/edit', '0', '3', '2', '1', '', '2017-08-07 16:36:08', null);
INSERT INTO `rc_menu` VALUES ('894477420512411648', 'menu/delete', 'menu/list', '894476950951690240', '菜单删除', 'menu/delete', '0', '3', '2', '1', '', '2017-08-07 16:36:50', null);
INSERT INTO `rc_menu` VALUES ('894477851082883072', 'apidoc', 'system', '893287144657780736', 'Api文档', 'swagger-ui.html', '1', '2', '9', '1', '', '2017-08-07 16:38:33', '2017-08-08 09:56:57');
INSERT INTO `rc_menu` VALUES ('894477995903811584', 'database/log', 'system', '893287144657780736', '数据库日志', 'druid', '1', '2', '10', '1', '', '2017-08-07 16:39:07', '2017-08-08 09:56:29');
INSERT INTO `rc_menu` VALUES ('894752734459199488', 'companyList', 'root', '000000000000000000', '公司管理', 'companyList', '1', '1', '1', '1', '', '2017-08-08 10:50:50', null);
INSERT INTO `rc_menu` VALUES ('894769217763540992', 'department/list', 'root', '000000000000000000', '部门管理', 'department/list', '1', '1', '3', '1', '', '2017-08-08 11:56:20', null);

-- ----------------------------
-- Table structure for rc_notice
-- ----------------------------
DROP TABLE IF EXISTS `rc_notice`;
CREATE TABLE `rc_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_notice
-- ----------------------------

-- ----------------------------
-- Table structure for rc_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `rc_operation_log`;
CREATE TABLE `rc_operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT,
  `log_type` varchar(255) DEFAULT NULL,
  `log_name` varchar(255) DEFAULT NULL,
  `user_id` int(65) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `method` text,
  `create_time` datetime DEFAULT NULL,
  `succeed` varchar(255) DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for rc_privilege
-- ----------------------------
DROP TABLE IF EXISTS `rc_privilege`;
CREATE TABLE `rc_privilege` (
  `role_id` int(11) DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_privilege
-- ----------------------------
INSERT INTO `rc_privilege` VALUES ('17', '894752734459199488', '2017-08-08 11:31:08');
INSERT INTO `rc_privilege` VALUES ('6', '893287144657780736', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893288715881807872', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894396523532517376', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477851082883072', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893287144657780736', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893288715881807872', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894396523532517376', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477851082883072', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477995903811584', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893287144657780736', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893288715881807872', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894396523532517376', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894473486712438784', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477851082883072', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477995903811584', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893287144657780736', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893288715881807872', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894396523532517376', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894473486712438784', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894473651837992960', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477851082883072', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477995903811584', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893287144657780736', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893288715881807872', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '893304960282787840', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894396523532517376', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894473486712438784', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894473651837992960', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477851082883072', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('6', '894477995903811584', '2017-08-08 11:31:39');
INSERT INTO `rc_privilege` VALUES ('8', '893287144657780736', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '893288715881807872', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '893304960282787840', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894396523532517376', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894473486712438784', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894473651837992960', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894475142061621248', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894475827880656896', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894475985452269568', '2017-08-08 11:56:44');
INSERT INTO `rc_privilege` VALUES ('8', '894476118730473472', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894476276402749440', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894476950951690240', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477107919323136', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477244926263296', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477420512411648', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477851082883072', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894477995903811584', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894752734459199488', '2017-08-08 11:56:45');
INSERT INTO `rc_privilege` VALUES ('8', '894769217763540992', '2017-08-08 11:56:45');

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
) ENGINE=InnoDB AUTO_INCREMENT=3723 DEFAULT CHARSET=utf8;

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
INSERT INTO `rc_relation` VALUES ('3704', '1', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3705', '2', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3706', '3', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3707', '4', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3708', '5', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3709', '6', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3710', '7', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3711', '8', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3712', '81', '6', '2017-06-26 12:46:09', null);
INSERT INTO `rc_relation` VALUES ('3719', '5', '17', '2017-07-21 09:41:28', null);
INSERT INTO `rc_relation` VALUES ('3720', '6', '17', '2017-07-21 09:41:28', null);
INSERT INTO `rc_relation` VALUES ('3721', '7', '17', '2017-07-21 09:41:28', null);
INSERT INTO `rc_relation` VALUES ('3722', '8', '17', '2017-07-21 09:41:28', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_role
-- ----------------------------
INSERT INTO `rc_role` VALUES ('6', '管理员', 'admin', null, '2017-06-20 15:07:13', '2017-06-26 12:46:09', '1');
INSERT INTO `rc_role` VALUES ('8', '超级管理员', 'super', null, '2017-06-20 15:08:45', null, '1');
INSERT INTO `rc_role` VALUES ('17', '用户', 'user', null, '2017-06-28 18:50:39', '2017-07-21 09:41:28', '1');

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
  `role_id` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rc_user
-- ----------------------------
INSERT INTO `rc_user` VALUES ('46', null, 'super', '5844591dff62349ce65f98c60baa669e', 'e8z0i', '超级管理员', '2017-06-22 14:26:09', '1', null, null, '8', '34', '1', '2017-06-20 15:12:16', null);
INSERT INTO `rc_user` VALUES ('48', null, 'admin', '439b9b33eb18d644f3b57e182f45b86a', 'bycca', '管理员', null, '1', null, null, '6', null, '1', '2017-06-26 17:31:41', null);
