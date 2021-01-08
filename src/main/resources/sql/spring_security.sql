/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : spring_security

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 08/01/2021 14:07:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (5, '/auth/*', '权限管理');
INSERT INTO `resource` VALUES (6, '/test/*', '测试');
INSERT INTO `resource` VALUES (11, '/admin/*', '操作界面管理');
INSERT INTO `resource` VALUES (12, '/order/*', '商品购买');
INSERT INTO `resource` VALUES (13, '/order/*', '商品购买');
INSERT INTO `resource` VALUES (14, '/home', '首页');

-- ----------------------------
-- Table structure for resource_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `resource_role_relation`;
CREATE TABLE `resource_role_relation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `resource_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_role_relation
-- ----------------------------
INSERT INTO `resource_role_relation` VALUES (1, 5, 1);
INSERT INTO `resource_role_relation` VALUES (2, 6, 1);
INSERT INTO `resource_role_relation` VALUES (3, 6, 2);
INSERT INTO `resource_role_relation` VALUES (4, 6, 3);
INSERT INTO `resource_role_relation` VALUES (5, 6, 4);
INSERT INTO `resource_role_relation` VALUES (6, 6, 5);
INSERT INTO `resource_role_relation` VALUES (7, 11, 2);
INSERT INTO `resource_role_relation` VALUES (8, 12, 3);
INSERT INTO `resource_role_relation` VALUES (9, 13, 4);
INSERT INTO `resource_role_relation` VALUES (15, 14, 1);
INSERT INTO `resource_role_relation` VALUES (16, 14, 2);
INSERT INTO `resource_role_relation` VALUES (17, 14, 3);
INSERT INTO `resource_role_relation` VALUES (18, 14, 4);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_name_cn` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_ROOT', '权限管理员', '管理权限的，无系统使用权限');
INSERT INTO `role` VALUES (2, 'ROLE_ADMIN', '系统管理员', '除了权限分配，其它都可以使用');
INSERT INTO `role` VALUES (3, 'ROLE_VIP', 'vip用户', '只能访问系统，购买商品有优惠');
INSERT INTO `role` VALUES (4, 'ROLE_USER', '普通用户', '只能访问系统，比如购买商品');
INSERT INTO `role` VALUES (5, 'ROLE_VISITOR', '访客', '只能访问系统，无法操作');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `user_pwd` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yhc', '$2a$10$oqK5kp.hOUPBiZyo6BGwq.2sZNQ7c59L5dr/hJIYa5zhFCCtI8/MW', '');
INSERT INTO `user` VALUES (2, 'root', '123456', NULL);
INSERT INTO `user` VALUES (3, 'admin', '123456', NULL);
INSERT INTO `user` VALUES (4, 'test', 'test', NULL);

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
