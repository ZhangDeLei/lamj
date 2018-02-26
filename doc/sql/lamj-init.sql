/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : lamj

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 02/26/2018 14:26:32 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `MT_Dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `MT_Dictionary`;
CREATE TABLE `MT_Dictionary` (
  `Id` int(11) NOT NULL COMMENT '唯一编码',
  `Name` varchar(255) NOT NULL COMMENT '字典名称',
  `Code` varchar(255) NOT NULL COMMENT '字典编码',
  `Value` int(11) NOT NULL COMMENT '字典值',
  `Description` varchar(255) DEFAULT NULL COMMENT '字典描述',
  `Type` varchar(255) DEFAULT NULL COMMENT '字典类型',
  `Status` varchar(255) NOT NULL COMMENT '字典状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `MT_Group`
-- ----------------------------
DROP TABLE IF EXISTS `MT_Group`;
CREATE TABLE `MT_Group` (
  `Id` int(11) NOT NULL COMMENT '唯一编码',
  `Name` varchar(255) NOT NULL COMMENT '分组名称',
  `Code` varchar(255) NOT NULL COMMENT '分组编码',
  `Status` int(11) NOT NULL COMMENT '分组状态',
  `Description` varchar(255) DEFAULT NULL COMMENT '分组描述',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `MT_Group`
-- ----------------------------
BEGIN;
INSERT INTO `MT_Group` VALUES ('1', '系统管理员', '0000', '1', '用于系统管理操作');
COMMIT;

-- ----------------------------
--  Table structure for `MT_GroupPermission`
-- ----------------------------
DROP TABLE IF EXISTS `MT_GroupPermission`;
CREATE TABLE `MT_GroupPermission` (
  `GroupId` int(11) DEFAULT NULL COMMENT '分组id',
  `PermissionId` int(11) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `MT_GroupPermission`
-- ----------------------------
BEGIN;
INSERT INTO `MT_GroupPermission` VALUES ('1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `MT_Menu`
-- ----------------------------
DROP TABLE IF EXISTS `MT_Menu`;
CREATE TABLE `MT_Menu` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一编码',
  `Name` varchar(255) NOT NULL COMMENT '菜单名称',
  `Code` varchar(255) NOT NULL COMMENT '菜单编码',
  `Icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `Level` int(11) NOT NULL COMMENT '菜单级别',
  `Path` varchar(255) DEFAULT NULL COMMENT '菜单跳转地址',
  `ParentId` int(11) DEFAULT NULL COMMENT '父级菜单id',
  `Status` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `MT_Menu`
-- ----------------------------
BEGIN;
INSERT INTO `MT_Menu` VALUES ('1', '菜单管理', '0001', '', '1', null, '0', '1'), ('2', '菜单列表', '000101', 'glyphicon glyphicon-indent-right', '2', '/menu', '1', '1'), ('3', '菜单新增', '000102', 'glyphicon glyphicon-plus', '2', '/menu-add', '1', '1'), ('4', '账号管理', '0002', null, '1', null, '0', '1');
COMMIT;

-- ----------------------------
--  Table structure for `MT_Permission`
-- ----------------------------
DROP TABLE IF EXISTS `MT_Permission`;
CREATE TABLE `MT_Permission` (
  `Id` int(11) NOT NULL COMMENT '唯一编码',
  `Name` varchar(255) NOT NULL COMMENT '权限名称',
  `Code` varchar(255) NOT NULL COMMENT '权限编码',
  `Status` int(11) DEFAULT NULL COMMENT '权限状态',
  `Description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `MT_Permission`
-- ----------------------------
BEGIN;
INSERT INTO `MT_Permission` VALUES ('1', '超级管理员', '0000', '1', '超级管理员权限，可参与所有权限管理');
COMMIT;

-- ----------------------------
--  Table structure for `MT_PermissionMenu`
-- ----------------------------
DROP TABLE IF EXISTS `MT_PermissionMenu`;
CREATE TABLE `MT_PermissionMenu` (
  `PermissionId` int(11) DEFAULT NULL COMMENT '权限id',
  `MenuId` int(11) DEFAULT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `MT_PermissionMenu`
-- ----------------------------
BEGIN;
INSERT INTO `MT_PermissionMenu` VALUES ('1', '1'), ('1', '2'), ('1', '3');
COMMIT;

-- ----------------------------
--  Table structure for `MT_User`
-- ----------------------------
DROP TABLE IF EXISTS `MT_User`;
CREATE TABLE `MT_User` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `UserAccount` varchar(255) NOT NULL COMMENT '用户账号',
  `Password` varchar(255) NOT NULL COMMENT '登陆密码',
  `NickName` varchar(255) NOT NULL COMMENT '昵称',
  `Tel` varchar(11) NOT NULL COMMENT '手机号码',
  `Sex` bit(1) DEFAULT NULL COMMENT '性别',
  `PhotoUrl` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `Status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `MT_User`
-- ----------------------------
BEGIN;
INSERT INTO `MT_User` VALUES ('5', 'admin', '96e79218965eb72c92a549dd5a330112', '管理员', '15156888276', b'1', null, null), ('6', '123123', '4297f44b13955235245b2497399d7a93', '123123', '123123', b'1', null, null), ('7', '123123123', '4297f44b13955235245b2497399d7a93', '123123', '123123', b'1', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `MT_UserGroup`
-- ----------------------------
DROP TABLE IF EXISTS `MT_UserGroup`;
CREATE TABLE `MT_UserGroup` (
  `UserId` int(11) DEFAULT NULL COMMENT '用户id',
  `GroupId` int(11) DEFAULT NULL COMMENT '分组id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `MT_UserGroup`
-- ----------------------------
BEGIN;
INSERT INTO `MT_UserGroup` VALUES ('5', '1');
COMMIT;

-- ----------------------------
--  Table structure for `MT_UserPermission`
-- ----------------------------
DROP TABLE IF EXISTS `MT_UserPermission`;
CREATE TABLE `MT_UserPermission` (
  `UserId` int(11) DEFAULT NULL COMMENT '用户id',
  `PermissionId` int(11) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `MT_UserPermission`
-- ----------------------------
BEGIN;
INSERT INTO `MT_UserPermission` VALUES ('5', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
