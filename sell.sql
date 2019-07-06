/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : sell

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 30/04/2019 10:58:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info`  (
  `car_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `product_quantity` int(11) NOT NULL DEFAULT 1 COMMENT '数量',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  PRIMARY KEY (`car_id`, `open_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (10);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `create_time` timestamp(6) NOT NULL COMMENT '创建时间',
  `update_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`detail_id`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('15565339618531888957', '15565339618181311700', '1223756', 1, '2019-04-29 18:32:41.803000', '2019-04-29 18:32:41.803000');
INSERT INTO `order_detail` VALUES ('15565406655681668077', '15565406655451443178', '1223756', 2, '2019-04-29 20:24:25.538000', '2019-04-29 20:24:25.538000');
INSERT INTO `order_detail` VALUES ('15565408895171226550', '15565408894931775170', '1228756', 2, '2019-04-29 20:28:09.485000', '2019-04-29 20:28:09.485000');
INSERT INTO `order_detail` VALUES ('15565408895331514470', '15565408894931775170', '123456', 2, '2019-04-29 20:28:09.485000', '2019-04-29 20:28:09.485000');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `open_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  `address_id` int(11) NOT NULL COMMENT '地址id',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付状态, 默认未支付',
  `create_time` timestamp(6) NOT NULL COMMENT '创建时间',
  `update_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  PRIMARY KEY (`order_id`, `open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('15565339618181311700', '33333333', 0, 3.50, 0, 0, '2019-04-29 18:32:41.803000', '2019-04-29 18:32:41.803000');
INSERT INTO `order_master` VALUES ('15565406655451443178', '324234234', 1, 7.00, 0, 0, '2019-04-29 20:24:25.531000', '2019-04-29 20:24:25.531000');
INSERT INTO `order_master` VALUES ('15565408894931775170', '324234234', 2, 18.00, 0, 0, '2019-04-29 20:28:09.480000', '2019-04-29 20:28:09.480000');
INSERT INTO `order_master` VALUES ('21667', '11111111', 0, 11.20, 0, 0, '2019-04-29 17:23:22.118000', '2019-04-29 17:23:22.118000');
INSERT INTO `order_master` VALUES ('23667', '11111111', 0, 11.20, 0, 0, '2019-04-29 10:23:16.078000', '2019-04-29 10:23:16.078000');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名字',
  `create_time` timestamp(6) NOT NULL COMMENT '创建时间',
  `update_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `idx_category_name`(`category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (3, '人气', '2019-04-26 17:15:12.814000', '2019-04-26 17:15:12.814000');
INSERT INTO `product_category` VALUES (4, '五一狂欢', '2019-04-26 17:49:15.190000', '2019-04-26 17:49:15.190000');
INSERT INTO `product_category` VALUES (7, '十一狂欢', '2019-04-26 17:59:37.615000', '2019-04-26 17:59:37.615000');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_id` int(11) NOT NULL COMMENT '类目id',
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品描述',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小图',
  `product_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '商品状态， 0正常，1下架',
  `create_time` timestamp(6) NOT NULL COMMENT '创建时间',
  `update_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1223756', 4, '辣条', 3.50, 1, '这是个好东西', 'http://xxxx.jpg', 1, '2019-04-28 09:40:49.696000', '2019-04-29 20:24:25.595083');
INSERT INTO `product_info` VALUES ('1228756', 4, '肥仔快乐水', 2.50, 7, '这是个好东西', 'http://xxxx.jpg', 0, '2019-04-28 09:44:12.037000', '2019-04-29 20:28:09.555428');
INSERT INTO `product_info` VALUES ('123456', 3, '泡面', 6.50, 2, '这是个好东西', 'http://xxxx.jpg', 0, '2019-04-28 09:39:08.190000', '2019-04-29 20:28:09.557717');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  `country` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市',
  `county` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '县/区',
  `address_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细地址',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_address
-- ----------------------------
INSERT INTO `user_address` VALUES (1, '15565408894931775170', '中国', '浙江', '', '杭州', '杭州电子科技大学');
INSERT INTO `user_address` VALUES (8, '15565898263241215956', 'china', '江西省', '上饶市', '上饶县', '蒋村');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信openid',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信登陆用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信登陆密码',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话',
  `create_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  `update_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('0', '15565408894931775170', 'xc', '123456', '1234567890', '2019-04-10 20:53:02.000000', '2019-04-29 20:53:06.000000');
INSERT INTO `user_info` VALUES ('1212', '15565898263241215956', 'zhangshan', '12345667', '12345678901', '2019-04-30 10:03:46.323000', '2019-04-30 10:03:46.323000');

SET FOREIGN_KEY_CHECKS = 1;
