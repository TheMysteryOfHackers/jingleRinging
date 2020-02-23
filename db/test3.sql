/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : test3

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 21/02/2020 16:14:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coderule
-- ----------------------------
DROP TABLE IF EXISTS `coderule`;
CREATE TABLE `coderule`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名字',
  `rule` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则表达式',
  `current` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '当前流水号',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流水号规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `level` decimal(2, 0) DEFAULT NULL COMMENT '等级',
  `sort` decimal(2, 0) DEFAULT NULL COMMENT '排序',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('4edb2041c439b516ca8acfd88fb8bacf', '5213e5e802e1fba39a97bdbd2862b10e', '人事部', 2, 1, NULL, '2019-12-05 23:13:27', NULL, '2019-12-05 23:13:27', NULL);
INSERT INTO `department` VALUES ('5213e5e802e1fba39a97bdbd2862b10e', '0', '总公司', 1, 1, NULL, '2019-12-05 23:13:12', NULL, '2019-12-09 11:11:29', NULL);
INSERT INTO `department` VALUES ('f9b75aed25e3a4f65f9404c82a1dc086', '5213e5e802e1fba39a97bdbd2862b10e', '技术部门', 2, 2, NULL, '2019-12-05 23:13:43', NULL, '2019-12-05 23:13:43', NULL);

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dtid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备类型id',
  `code` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备流水号',
  `residual` decimal(16, 2) DEFAULT NULL COMMENT '净值',
  `price` decimal(16, 2) DEFAULT NULL COMMENT '购买价钱',
  `status` decimal(2, 0) DEFAULT NULL COMMENT '状态 1-入库、2-出库中、3-出库、4-领用、5-报修',
  `proddate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '生产日期',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登记人',
  `createtime` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登记时间',
  `buyer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购买人',
  `buydate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购买日期',
  `sno` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '序列号',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('190b8a3ca990db15f6ab9eb107f81b65', 'c8069479365da48ee23055d3c67a12a9', 'S200045', NULL, 0.10, 4, NULL, 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', NULL, '2020-02-03 21:34:17', NULL, '2020-02-03 21:34:17', NULL);
INSERT INTO `device` VALUES ('19ded125002b3b7b017819d79d5a12a7', 'c8069479365da48ee23055d3c67a12a9', 'S200048', NULL, 0.10, 4, NULL, 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', NULL, '2020-02-03 21:34:17', NULL, '2020-02-03 21:34:17', NULL);
INSERT INTO `device` VALUES ('1c03e1badd779c47ff4abb574d3c5de2', 'c8069479365da48ee23055d3c67a12a9', 'S200043', 3242.00, 2131.00, 4, '2020-02-06', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 14:07:59', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-21', '312', '2020-02-03 14:08:00', NULL, '2020-02-03 14:08:00', NULL);
INSERT INTO `device` VALUES ('2f1a6d366116b29f08bdeff5cba39a76', 'b9b2a5ab21511ad62101d2fa364aec7a', 'S190002', 11114.00, 24.00, 2, '2019-06-05', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-15 17:06:51', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-18', '100002', '2019-12-15 17:06:52', NULL, '2020-02-02 11:34:34', 'admin1');
INSERT INTO `device` VALUES ('334ad0329a846bd829c19457c80a9b0d', 'b9b2a5ab21511ad62101d2fa364aec7a', 'S190004', 11116.00, 26.00, 2, '2019-06-07', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-15 17:06:51', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-18', '100004', '2019-12-15 17:06:53', NULL, '2020-02-02 10:10:34', 'admin1');
INSERT INTO `device` VALUES ('44066cb83f8bc0a04326fec2eb228fcb', 'c8069479365da48ee23055d3c67a12a9', 'S200047', NULL, 0.10, 1, NULL, 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', NULL, '2020-02-03 21:34:17', NULL, '2020-02-03 21:34:17', NULL);
INSERT INTO `device` VALUES ('440d7a80d87304da27a020264bbc0670', 'b9b2a5ab21511ad62101d2fa364aec7a', 'S190001', 11113.00, 23.00, 1, '2019-06-04', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-15 17:06:51', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-18', '100001', '2019-12-15 17:06:54', NULL, '2020-02-03 11:49:45', 'admin1');
INSERT INTO `device` VALUES ('b00d78c66e0ed133bd3c5f0522b83a02', 'c8069479365da48ee23055d3c67a12a9', 'S200049', NULL, 0.10, 1, NULL, 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', NULL, '2020-02-03 21:34:17', NULL, '2020-02-03 21:34:17', NULL);
INSERT INTO `device` VALUES ('cc102d716f64199b7a1e38b49788c574', 'b9b2a5ab21511ad62101d2fa364aec7a', 'S190003', 11115.00, 25.00, 1, '2019-06-06', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-15 17:06:51', 'ad7880c1d4fd2a57ab2517268ac1334t', '2019-12-18', '100003', '2019-12-15 17:06:55', NULL, '2020-02-03 11:49:52', 'admin1');
INSERT INTO `device` VALUES ('dafd272308977cc77edc4526272eeab3', 'c8069479365da48ee23055d3c67a12a9', 'S200044', NULL, 0.10, 1, NULL, 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', NULL, '2020-02-03 21:34:17', NULL, '2020-02-03 21:34:17', NULL);
INSERT INTO `device` VALUES ('ee10b9fee0180531943bb4a25c6dfe3f', 'c8069479365da48ee23055d3c67a12a9', 'S200046', NULL, 0.10, 1, NULL, 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:16', NULL, '2020-02-03 21:34:17', NULL, '2020-02-03 21:34:17', NULL);

-- ----------------------------
-- Table structure for devicepurchaserecords
-- ----------------------------
DROP TABLE IF EXISTS `devicepurchaserecords`;
CREATE TABLE `devicepurchaserecords`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dtid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '购买的商品类型id',
  `trade_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付宝交易号',
  `out_trade_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商户订单号',
  `buyer_pay_amount` decimal(9, 2) DEFAULT NULL COMMENT '付款金额',
  `subject` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单标题',
  `body` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品描述',
  `gmt_payment` datetime(0) DEFAULT NULL COMMENT '交易付款时间',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品购买记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devicepurchaserecords
-- ----------------------------
INSERT INTO `devicepurchaserecords` VALUES ('3890be36aff60e5eda045e2f33a9171e', 'c8069479365da48ee23055d3c67a12a9', '2020013022001439271000112362', '20200130222446447', 0.10, '买入设备', '购买最便宜的测试', '2020-01-30 22:25:04', '2020-01-30 22:25:10', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-01-30 22:25:10', NULL);
INSERT INTO `devicepurchaserecords` VALUES ('3b6df0a35d924c1036491088b7cfc3d5', 'c8069479365da48ee23055d3c67a12a9', '2020013022001439271000109434', '20200130202521369', 0.10, '买入设备', '购买最便宜的测试', '2020-01-30 20:25:42', '2020-01-30 20:25:47', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-01-30 20:25:47', NULL);
INSERT INTO `devicepurchaserecords` VALUES ('513c5696b1b7435e4dd3c7dad86329ee', 'c8069479365da48ee23055d3c67a12a9', '2020020322001439271000118186', '20200203213400470', 0.60, '买入设备', '购买最便宜的测试', '2020-02-03 21:34:13', '2020-02-03 21:34:17', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 21:34:17', NULL);
INSERT INTO `devicepurchaserecords` VALUES ('6a9fa540a43c1ce73d0a20369c4d40ad', 'c8069479365da48ee23055d3c67a12a9', '2020013022001439271000112363', '20200130224753060', 0.10, '买入设备', '购买最便宜的测试', '2020-01-30 22:48:07', '2020-01-30 22:48:11', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-01-30 22:48:11', NULL);
INSERT INTO `devicepurchaserecords` VALUES ('a0974bd78aa06f29b33528e9e078f5be', 'c8069479365da48ee23055d3c67a12a9', '2020013022001439271000109434', '20200130202521369', 0.10, '买入设备', '购买最便宜的测试', '2020-01-30 20:25:42', '2020-01-30 20:29:29', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-01-30 20:29:29', NULL);

-- ----------------------------
-- Table structure for devicereceive
-- ----------------------------
DROP TABLE IF EXISTS `devicereceive`;
CREATE TABLE `devicereceive`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `did` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备id',
  `receipt` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单据号',
  `recipients` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '领用人',
  `receivedate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '领取时间',
  `returndate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '归还日期',
  `status` decimal(1, 0) DEFAULT NULL COMMENT '状态 1-领用、2-归还',
  `receiveremarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '领用备注',
  `returnremarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '归还备注',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备领用表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devicereceive
-- ----------------------------
INSERT INTO `devicereceive` VALUES ('2583a5414062f726f9dcdfeee8a3d5e5', '440d7a80d87304da27a020264bbc0670', 'DJS200007', '陈小心', '2020-02-03 14:10:13', '2020-02-03 14:10:47', 2, NULL, NULL, '2020-02-03 14:10:14', NULL, '2020-02-03 14:10:48', NULL);
INSERT INTO `devicereceive` VALUES ('8acf9a20f5cb45cbd2687d38c5f8f19c', '1c03e1badd779c47ff4abb574d3c5de2', 'DJS200008', '陈大勇', '2020-02-04 20:32:27', NULL, 1, NULL, NULL, '2020-02-04 20:32:29', NULL, '2020-02-04 20:32:29', NULL);
INSERT INTO `devicereceive` VALUES ('f15bb1539798b03b836c0592c45aff29', '190b8a3ca990db15f6ab9eb107f81b65', 'DJS200001', '陈晓华', '2020-02-11 15:37:53', NULL, 1, NULL, NULL, '2020-02-11 15:37:55', NULL, '2020-02-11 15:37:55', NULL);

-- ----------------------------
-- Table structure for devicerepair
-- ----------------------------
DROP TABLE IF EXISTS `devicerepair`;
CREATE TABLE `devicerepair`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `did` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备id',
  `damager` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报修人',
  `damagedate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报修时间',
  `damageremarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报修备注',
  `repairdate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维修时间',
  `repairer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维修人',
  `repairremarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维修备注',
  `status` decimal(1, 0) DEFAULT NULL COMMENT '状态 1-报修、2-维修',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备维修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devicerepair
-- ----------------------------
INSERT INTO `devicerepair` VALUES ('c845084e06f6758a0cfe09a0cdba0520', 'cc102d716f64199b7a1e38b49788c574', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 18:51:05', NULL, '2020-02-03 18:51:20', 'ad7880c1d4fd2a57ab2517268ac1334t', '我会修好它的', 3, '2020-02-03 18:51:06', NULL, '2020-02-03 18:51:20', NULL);
INSERT INTO `devicerepair` VALUES ('f9df22cd87b111196fad16f84e4bd409', '440d7a80d87304da27a020264bbc0670', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 16:53:25', NULL, NULL, 'bff2b21bfe454242e9a4126a0e3e4d84', '的', 3, '2020-02-03 16:53:25', NULL, '2020-02-03 18:30:26', NULL);

-- ----------------------------
-- Table structure for devicescrap
-- ----------------------------
DROP TABLE IF EXISTS `devicescrap`;
CREATE TABLE `devicescrap`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `did` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备id',
  `scraper` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报废人',
  `scraperdate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报废日期',
  `scrapremarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报废备注',
  `status` decimal(1, 0) DEFAULT NULL COMMENT '状态 1-申请、2-准许、3-拒绝',
  `approver` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审批人',
  `approvalremarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审批意见',
  `approvaldate` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '审批日期',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备报废' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devicescrap
-- ----------------------------
INSERT INTO `devicescrap` VALUES ('95978ec6b0902ba2d4b9c53ba43133f5', '334ad0329a846bd829c19457c80a9b0d', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-02 11:43:32', NULL, 1, NULL, NULL, NULL, '2020-02-02 11:43:33', NULL, '2020-02-02 11:43:33', NULL);
INSERT INTO `devicescrap` VALUES ('fb24216b5732d674dce0b98b3bc04e60', '2f1a6d366116b29f08bdeff5cba39a76', 'ad7880c1d4fd2a57ab2517268ac1334t', '2020-02-03 10:12:02', NULL, 1, NULL, NULL, NULL, '2020-02-03 10:12:03', NULL, '2020-02-03 10:12:03', NULL);

-- ----------------------------
-- Table structure for devicetype
-- ----------------------------
DROP TABLE IF EXISTS `devicetype`;
CREATE TABLE `devicetype`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名字',
  `brand` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌',
  `useyear` decimal(2, 0) DEFAULT NULL COMMENT '使用年限',
  `intlcode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国际编号',
  `original` decimal(16, 2) DEFAULT NULL COMMENT '原值',
  `residualrate` decimal(3, 3) DEFAULT NULL COMMENT '净残值率',
  `model` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '型号',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of devicetype
-- ----------------------------
INSERT INTO `devicetype` VALUES ('9fbf87a9565b702a5bb8f1222b4a0689', '等待戈多', '很慌', 4, 'x455', 45454.00, 0.200, '4445', NULL, '2019-12-16 19:43:58', NULL, '2019-12-16 19:43:58', NULL);
INSERT INTO `devicetype` VALUES ('b9b2a5ab21511ad62101d2fa364aec7a', '华为交换机', '华为', 3, 'x44', 4000.00, 0.200, '11-6', '1231', '2019-12-11 11:16:57', NULL, '2019-12-11 11:38:20', NULL);
INSERT INTO `devicetype` VALUES ('c8069479365da48ee23055d3c67a12a9', '最便宜的测试', '金牌', 3, '233', 0.10, 0.100, '10086', NULL, '2020-01-21 15:15:39', NULL, '2020-01-21 15:15:39', NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `type` decimal(1, 0) DEFAULT NULL COMMENT '类型 1-显示菜单、2-跳转菜单、3-功能按钮',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名字',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限编码',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'url',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `expression` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限表达式（系统标识）',
  `sort` decimal(2, 0) DEFAULT NULL COMMENT '排序',
  `level` decimal(2, 0) DEFAULT NULL COMMENT '等级',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('0466e20444e84e9e8f0c02b66f3e150f', '89065718ad534b4f9c7cce690b7e8ccd', 3, '删除', '000000150010', '', NULL, NULL, 3, 3, NULL, '2019-02-01 11:24:56', NULL, '2019-02-01 11:24:56', NULL);
INSERT INTO `permission` VALUES ('051113cca1b3a56a3affce1c2659fbe5', '9c958f4f9267d7afc58a2688931a770f', 3, '删除', '000500150010', NULL, NULL, NULL, 3, 3, NULL, '2020-02-03 15:14:10', NULL, '2020-02-03 15:14:10', NULL);
INSERT INTO `permission` VALUES ('08a2d8d77669485ba18b1588a8668711', '72509285062f4e23865bbf069959a84a', 3, '添加', '000000000015', '', NULL, NULL, 4, 3, NULL, '2019-02-03 16:44:14', '718b3edade24475c8d704ba6065747aa', '2019-02-03 16:44:14', '718b3edade24475c8d704ba6065747aa');
INSERT INTO `permission` VALUES ('0b88b52892b1f3f18240c1188ad35646', '8e3c4ca3327c1ae5875081cee98b56c0', 3, '删除', '000500100010', NULL, NULL, NULL, 3, 3, NULL, '2020-02-02 12:13:20', NULL, '2020-02-02 12:13:20', NULL);
INSERT INTO `permission` VALUES ('12cecee01e06426596a36145f6248160', '7b53ebd909134f499db81956d2d6854f', 3, '添加', '000000050000', '', NULL, NULL, 1, 3, NULL, '2019-02-01 11:20:05', NULL, '2019-12-09 23:37:03', NULL);
INSERT INTO `permission` VALUES ('16b8b0412f5e41adb78f22aa4e0c0ae7', '89065718ad534b4f9c7cce690b7e8ccd', 3, '添加', '000000150000', '', NULL, NULL, 1, 3, NULL, '2019-02-01 11:24:28', NULL, '2019-12-09 23:40:36', NULL);
INSERT INTO `permission` VALUES ('29cf13f3704c48a8a98bf85856c64e57', 'd6e4d5040b4b4622aa68df5a568c4538', 3, '授权', '000000100010', '', NULL, NULL, 3, 3, NULL, '2019-02-01 11:26:38', NULL, '2019-02-01 11:26:38', NULL);
INSERT INTO `permission` VALUES ('3237bba5e8a7488b9c05f2e2445a8e09', '0', 1, '系统管理', '0000', NULL, NULL, NULL, 1, 1, 'layui-icon-set', '2019-01-26 14:17:28', NULL, '2019-12-09 11:10:59', NULL);
INSERT INTO `permission` VALUES ('35b181057d01499c9122c0aca7ebe149', '72509285062f4e23865bbf069959a84a', 3, '删除', '000000000010', '', NULL, NULL, 3, 3, NULL, '2019-01-29 17:04:28', NULL, '2019-01-29 17:04:28', NULL);
INSERT INTO `permission` VALUES ('3664c0fab1bc95fd94c3e8665b2eb500', '7fc8ecab25f9b32f2c99374f2f62a6db', 3, '编辑', '000500050005', NULL, NULL, NULL, 2, 3, NULL, '2020-01-30 20:07:59', NULL, '2020-01-30 20:07:59', NULL);
INSERT INTO `permission` VALUES ('3efcd2745c0006a764746fa0fd21791f', '7fc8ecab25f9b32f2c99374f2f62a6db', 3, '添加', '000500050000', NULL, NULL, NULL, 1, 3, NULL, '2020-01-30 20:06:33', NULL, '2020-01-30 20:06:33', NULL);
INSERT INTO `permission` VALUES ('3fea71bab5cc4142963e631e04b863d3', 'd6e4d5040b4b4622aa68df5a568c4538', 3, '删除', '000000100015', '', NULL, NULL, 4, 3, NULL, '2019-02-01 11:26:52', NULL, '2019-02-01 11:26:52', NULL);
INSERT INTO `permission` VALUES ('40a4661a2a313277a5e9f3da55dd118a', '9c958f4f9267d7afc58a2688931a770f', 3, '添加', '000500150000', NULL, NULL, NULL, 1, 3, NULL, '2020-02-03 14:57:50', NULL, '2020-02-03 14:57:50', NULL);
INSERT INTO `permission` VALUES ('42efcefbda234f11bdab6d74c44d7efd', 'd6e4d5040b4b4622aa68df5a568c4538', 3, '添加', '000000100000', '', NULL, NULL, 1, 3, NULL, '2019-02-01 11:25:59', NULL, '2019-12-09 23:38:56', NULL);
INSERT INTO `permission` VALUES ('47bfad7cec38548b0ccfb2b4f16ad9aa', '9c958f4f9267d7afc58a2688931a770f', 3, '归还', '000500150005', NULL, NULL, NULL, 2, 3, NULL, '2020-02-03 15:13:53', NULL, '2020-02-03 15:16:52', NULL);
INSERT INTO `permission` VALUES ('4bbe68e3b67b4b55813f338ff3c3c42c', '7b53ebd909134f499db81956d2d6854f', 3, '编辑', '000000050005', '', NULL, NULL, 2, 3, NULL, '2019-02-01 11:20:20', NULL, '2019-02-01 11:20:20', NULL);
INSERT INTO `permission` VALUES ('60ccd34bf713001e0b8192e81c6ac790', 'a646903554d9c3c96c08f8656d02d82a', 3, '删除', '000500000010', NULL, NULL, NULL, 3, 3, NULL, '2019-12-10 17:18:09', NULL, '2019-12-11 11:05:50', NULL);
INSERT INTO `permission` VALUES ('7117880013af919ba80cf240bfd7c519', '8e3c4ca3327c1ae5875081cee98b56c0', 3, '添加', '000500100000', NULL, NULL, NULL, 1, 3, NULL, '2020-02-02 12:12:23', NULL, '2020-02-02 12:12:23', NULL);
INSERT INTO `permission` VALUES ('717b5a6dd7864f3383ab57aef78a7964', '89065718ad534b4f9c7cce690b7e8ccd', 3, '编辑', '000000150005', '', NULL, NULL, 2, 3, NULL, '2019-02-01 11:24:44', NULL, '2019-02-01 11:24:44', NULL);
INSERT INTO `permission` VALUES ('72509285062f4e23865bbf069959a84a', '3237bba5e8a7488b9c05f2e2445a8e09', 2, '用户管理', '00000000', 'system/user/list.html', NULL, NULL, 1, 2, 'layui-icon-login-qq', '2019-01-26 14:18:11', NULL, '2019-12-09 23:26:18', NULL);
INSERT INTO `permission` VALUES ('7593ce1bfe114e128616c964daf8d172', '7b53ebd909134f499db81956d2d6854f', 3, '删除', '000000050010', '', NULL, NULL, 3, 3, NULL, '2019-02-01 11:20:33', NULL, '2019-02-01 11:20:33', NULL);
INSERT INTO `permission` VALUES ('776577ad21737424c2ed28c384a6c358', 'a646903554d9c3c96c08f8656d02d82a', 3, '添加', '000500000000', NULL, NULL, NULL, 1, 3, NULL, '2019-12-10 16:58:41', NULL, '2019-12-11 11:05:23', NULL);
INSERT INTO `permission` VALUES ('7a1b2b9512cd4c2522d7eeee3b0cb8be', 'a646903554d9c3c96c08f8656d02d82a', 3, '编辑', '000500000005', NULL, NULL, NULL, 2, 3, NULL, '2019-12-10 17:17:53', NULL, '2019-12-11 11:05:43', NULL);
INSERT INTO `permission` VALUES ('7b53ebd909134f499db81956d2d6854f', '3237bba5e8a7488b9c05f2e2445a8e09', 2, '部门管理', '00000005', 'system/department/list.html', NULL, NULL, 2, 2, 'layui-icon-user', '2019-01-26 14:18:38', NULL, '2019-12-09 03:09:44', NULL);
INSERT INTO `permission` VALUES ('7eceea6b4ca262f42a65364d5d04ca3a', '7fc8ecab25f9b32f2c99374f2f62a6db', 3, '删除', '000500050010', NULL, NULL, NULL, 3, 3, NULL, '2020-01-30 20:08:20', NULL, '2020-01-30 20:08:20', NULL);
INSERT INTO `permission` VALUES ('7fc8ecab25f9b32f2c99374f2f62a6db', '9d0d444e5c5f56d1ec716f75512162ae', 2, '设备明细', '00050005', 'device/device/list.html', NULL, NULL, 2, 2, 'layui-icon-cellphone', '2019-12-11 15:50:31', NULL, '2019-12-11 15:50:53', NULL);
INSERT INTO `permission` VALUES ('89065718ad534b4f9c7cce690b7e8ccd', '3237bba5e8a7488b9c05f2e2445a8e09', 2, '权限管理', '00000015', 'system/permission/list.html', NULL, NULL, 4, 2, 'layui-icon-password', '2019-01-26 14:19:53', NULL, '2019-12-09 10:57:25', NULL);
INSERT INTO `permission` VALUES ('8e3c4ca3327c1ae5875081cee98b56c0', '9d0d444e5c5f56d1ec716f75512162ae', 2, '设备报废', '00050010', 'device/devicescrap/list.html', '4', NULL, 4, 2, 'layui-icon-delete', '2020-02-01 20:20:26', NULL, '2020-02-01 20:20:51', NULL);
INSERT INTO `permission` VALUES ('8ebf37d6c2f0438c478df465200d956a', '8e3c4ca3327c1ae5875081cee98b56c0', 3, '审批', '000500100005', NULL, NULL, NULL, 2, 3, NULL, '2020-02-02 12:13:03', NULL, '2020-02-02 12:13:03', NULL);
INSERT INTO `permission` VALUES ('9c958f4f9267d7afc58a2688931a770f', '9d0d444e5c5f56d1ec716f75512162ae', 2, '设备领用', '00050015', 'device/devicereceive/list.html', '5', NULL, 5, 2, 'layui-icon-face-smile-b', '2020-02-03 10:01:16', NULL, '2020-02-03 10:01:33', NULL);
INSERT INTO `permission` VALUES ('9d0d444e5c5f56d1ec716f75512162ae', '0', 1, '设备管理', '0005', NULL, NULL, NULL, 2, 1, 'layui-icon-util', '2019-12-10 15:05:01', NULL, '2019-12-10 15:05:01', NULL);
INSERT INTO `permission` VALUES ('a646903554d9c3c96c08f8656d02d82a', '9d0d444e5c5f56d1ec716f75512162ae', 2, '设备类型', '00050000', 'device/devicetype/list.html', NULL, NULL, 1, 2, 'layui-icon-rate-solid', '2019-12-10 15:06:46', NULL, '2019-12-10 15:07:08', NULL);
INSERT INTO `permission` VALUES ('b135b11fa9120f6b9d7c71374a3d9ef8', 'dd86a691214366d2044b11b12471850b', 3, '维修', '000500200005', NULL, NULL, NULL, 2, 3, NULL, '2020-02-03 18:52:27', NULL, '2020-02-03 18:52:27', NULL);
INSERT INTO `permission` VALUES ('c3586d4b5d2b49c58e6aacd1a3a2bebc', '72509285062f4e23865bbf069959a84a', 3, '分配岗位', '000000000025', '', NULL, NULL, 6, 3, NULL, '2019-02-03 16:46:06', '718b3edade24475c8d704ba6065747aa', '2019-02-03 16:46:06', '718b3edade24475c8d704ba6065747aa');
INSERT INTO `permission` VALUES ('d6e4d5040b4b4622aa68df5a568c4538', '3237bba5e8a7488b9c05f2e2445a8e09', 2, '岗位管理', '00000010', 'system/role/list.html', NULL, NULL, 3, 2, 'layui-icon-friends', '2019-01-26 14:20:25', NULL, '2019-12-09 03:10:44', NULL);
INSERT INTO `permission` VALUES ('dd86a691214366d2044b11b12471850b', '9d0d444e5c5f56d1ec716f75512162ae', 2, '设备保修', '00050020', 'device/devicerepair/list.html', NULL, NULL, 6, 2, 'layui-icon-util', '2020-02-03 15:21:56', NULL, '2020-02-03 15:21:56', NULL);
INSERT INTO `permission` VALUES ('de75c26ee8594459a18bd27460ad569c', 'd6e4d5040b4b4622aa68df5a568c4538', 3, '编辑', '000000100005', '', NULL, NULL, 2, 3, NULL, '2019-02-01 11:26:18', NULL, '2019-02-01 11:26:18', NULL);
INSERT INTO `permission` VALUES ('ea0810e769ca453ba8695457f44aac3a', '72509285062f4e23865bbf069959a84a', 3, '编辑', '000000000005', '', NULL, NULL, 2, 3, NULL, '2019-01-29 17:04:47', NULL, '2019-12-09 23:30:36', NULL);
INSERT INTO `permission` VALUES ('f07c4feb1dcbf2ac6e34b39b4f85331a', 'dd86a691214366d2044b11b12471850b', 3, '添加', '000500200000', NULL, NULL, NULL, 1, 3, NULL, '2020-02-03 18:52:07', NULL, '2020-02-03 18:52:07', NULL);
INSERT INTO `permission` VALUES ('fc1523f6dcb6c0331787756c36511764', 'dd86a691214366d2044b11b12471850b', 3, '删除', '000500200010', NULL, NULL, NULL, 3, 3, NULL, '2020-02-03 18:52:39', NULL, '2020-02-03 18:52:39', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `did` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门id',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `level` decimal(2, 0) DEFAULT NULL COMMENT '等级',
  `sort` decimal(2, 0) DEFAULT NULL COMMENT '排序',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('882ea1f41c290764a9042f26c5010522', '5213e5e802e1fba39a97bdbd2862b10e', '0', '总经理', NULL, 1, 1, '2019-12-05 23:14:49', NULL, '2019-12-05 23:14:49', NULL);
INSERT INTO `role` VALUES ('8c004a6b546feff20a14c5a3de2e544e', '5213e5e802e1fba39a97bdbd2862b10e', '0', '超级管理员', NULL, 1, 1, '2019-12-05 23:14:18', NULL, '2019-12-05 23:14:18', NULL);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位id',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('01e6574f688b0891ae1404dcee71d854', '8c004a6b546feff20a14c5a3de2e544e', '35b181057d01499c9122c0aca7ebe149');
INSERT INTO `role_permission` VALUES ('0429cc520dede7306d382e5b90ea0474', '882ea1f41c290764a9042f26c5010522', '42efcefbda234f11bdab6d74c44d7efd');
INSERT INTO `role_permission` VALUES ('0673581bbea6f40eb9b129fe783cf850', '8c004a6b546feff20a14c5a3de2e544e', '0466e20444e84e9e8f0c02b66f3e150f');
INSERT INTO `role_permission` VALUES ('07fd549aea81c39a60a7616cb9265977', '8c004a6b546feff20a14c5a3de2e544e', 'a646903554d9c3c96c08f8656d02d82a');
INSERT INTO `role_permission` VALUES ('0fc468784fc246d14468fb2a776e03eb', '8c004a6b546feff20a14c5a3de2e544e', '3237bba5e8a7488b9c05f2e2445a8e09');
INSERT INTO `role_permission` VALUES ('274eabc2f0e5769fef62cf250eb5dbf3', '882ea1f41c290764a9042f26c5010522', 'd6e4d5040b4b4622aa68df5a568c4538');
INSERT INTO `role_permission` VALUES ('277a564c17194d3030a19d799ef111d0', '8c004a6b546feff20a14c5a3de2e544e', 'b135b11fa9120f6b9d7c71374a3d9ef8');
INSERT INTO `role_permission` VALUES ('2a742a856a1e82f3a28cd4be3cc93a4f', '8c004a6b546feff20a14c5a3de2e544e', '16b8b0412f5e41adb78f22aa4e0c0ae7');
INSERT INTO `role_permission` VALUES ('2a7f1f65b611c63cabd7afcba94cd74c', '8c004a6b546feff20a14c5a3de2e544e', '72509285062f4e23865bbf069959a84a');
INSERT INTO `role_permission` VALUES ('2b4c938bc0da82b3efea30ca8eb15c67', '8c004a6b546feff20a14c5a3de2e544e', 'de75c26ee8594459a18bd27460ad569c');
INSERT INTO `role_permission` VALUES ('2d34d2040c756129a4eb3e77889cb4dd', '882ea1f41c290764a9042f26c5010522', '7b53ebd909134f499db81956d2d6854f');
INSERT INTO `role_permission` VALUES ('3a8720aca0dc4f5ef647bcdd070cd55b', '882ea1f41c290764a9042f26c5010522', 'ea0810e769ca453ba8695457f44aac3a');
INSERT INTO `role_permission` VALUES ('46bb128f592dd60f65648a97b5855d14', '882ea1f41c290764a9042f26c5010522', '08a2d8d77669485ba18b1588a8668711');
INSERT INTO `role_permission` VALUES ('49e1276f2bef89744c9df34daab0fb78', '8c004a6b546feff20a14c5a3de2e544e', '7fc8ecab25f9b32f2c99374f2f62a6db');
INSERT INTO `role_permission` VALUES ('5108643a32c2b3af70097508da29bd4c', '8c004a6b546feff20a14c5a3de2e544e', '776577ad21737424c2ed28c384a6c358');
INSERT INTO `role_permission` VALUES ('52b864346d49d6a8ed996e3d5530a4ff', '882ea1f41c290764a9042f26c5010522', '7593ce1bfe114e128616c964daf8d172');
INSERT INTO `role_permission` VALUES ('5677fb969320ba081ac8aa62556de735', '8c004a6b546feff20a14c5a3de2e544e', 'c3586d4b5d2b49c58e6aacd1a3a2bebc');
INSERT INTO `role_permission` VALUES ('623e50d760053cd951d861158dfc0d15', '8c004a6b546feff20a14c5a3de2e544e', '8e3c4ca3327c1ae5875081cee98b56c0');
INSERT INTO `role_permission` VALUES ('6467de3d476fb9af6859cb3ec831e064', '8c004a6b546feff20a14c5a3de2e544e', '8ebf37d6c2f0438c478df465200d956a');
INSERT INTO `role_permission` VALUES ('660ddf7a211e40c7a2db88b3d92d646e', '8c004a6b546feff20a14c5a3de2e544e', '08a2d8d77669485ba18b1588a8668711');
INSERT INTO `role_permission` VALUES ('67a9fc5a1f0159d5d65525bdfd7a4313', '8c004a6b546feff20a14c5a3de2e544e', '4bbe68e3b67b4b55813f338ff3c3c42c');
INSERT INTO `role_permission` VALUES ('71a2bce0909db8ab4b02b7fef00f86fa', '8c004a6b546feff20a14c5a3de2e544e', 'fc1523f6dcb6c0331787756c36511764');
INSERT INTO `role_permission` VALUES ('75cd07953bce92c031e585f7c9e9d5b2', '8c004a6b546feff20a14c5a3de2e544e', '29cf13f3704c48a8a98bf85856c64e57');
INSERT INTO `role_permission` VALUES ('7cb64cdd964ea2c750f3bb239f229f62', '8c004a6b546feff20a14c5a3de2e544e', '40a4661a2a313277a5e9f3da55dd118a');
INSERT INTO `role_permission` VALUES ('8b5a9620f0e9d12fed769baeae25495d', '882ea1f41c290764a9042f26c5010522', 'c3586d4b5d2b49c58e6aacd1a3a2bebc');
INSERT INTO `role_permission` VALUES ('90b6ec6497fb4d30db96afdcca6d05fa', '8c004a6b546feff20a14c5a3de2e544e', '47bfad7cec38548b0ccfb2b4f16ad9aa');
INSERT INTO `role_permission` VALUES ('936f589c13b647b6a6e485be3e28e6f1', '8c004a6b546feff20a14c5a3de2e544e', 'f07c4feb1dcbf2ac6e34b39b4f85331a');
INSERT INTO `role_permission` VALUES ('978eda4173e05fb4402ed5391a681d80', '8c004a6b546feff20a14c5a3de2e544e', '9d0d444e5c5f56d1ec716f75512162ae');
INSERT INTO `role_permission` VALUES ('97ccba412b0bb21840d75c7047acbbc1', '8c004a6b546feff20a14c5a3de2e544e', '051113cca1b3a56a3affce1c2659fbe5');
INSERT INTO `role_permission` VALUES ('a3e9a503ab7a854d309e43316af3479f', '8c004a6b546feff20a14c5a3de2e544e', '7593ce1bfe114e128616c964daf8d172');
INSERT INTO `role_permission` VALUES ('a706aed51bcdf29c1e0fc32af946101d', '8c004a6b546feff20a14c5a3de2e544e', '60ccd34bf713001e0b8192e81c6ac790');
INSERT INTO `role_permission` VALUES ('a79a3456d7c8aa07040982c528e6b46c', '8c004a6b546feff20a14c5a3de2e544e', '7b53ebd909134f499db81956d2d6854f');
INSERT INTO `role_permission` VALUES ('b1d4ca17d1aa0db4d51cc3d4add0eaaf', '8c004a6b546feff20a14c5a3de2e544e', '7eceea6b4ca262f42a65364d5d04ca3a');
INSERT INTO `role_permission` VALUES ('b32f589f82efa7c67a19f93fbc933b33', '8c004a6b546feff20a14c5a3de2e544e', 'd6e4d5040b4b4622aa68df5a568c4538');
INSERT INTO `role_permission` VALUES ('b4bb89b2454895cc7d463b3574c364ee', '882ea1f41c290764a9042f26c5010522', '3237bba5e8a7488b9c05f2e2445a8e09');
INSERT INTO `role_permission` VALUES ('b5759f14e1145392133c39ce2bf455d6', '882ea1f41c290764a9042f26c5010522', '3fea71bab5cc4142963e631e04b863d3');
INSERT INTO `role_permission` VALUES ('bdee4f7fb67c728613b83e703c07dfd7', '8c004a6b546feff20a14c5a3de2e544e', '7a1b2b9512cd4c2522d7eeee3b0cb8be');
INSERT INTO `role_permission` VALUES ('c640b9f1844bbb36faec9837db2d3612', '8c004a6b546feff20a14c5a3de2e544e', '89065718ad534b4f9c7cce690b7e8ccd');
INSERT INTO `role_permission` VALUES ('c719246f0f1ed439b858ac9b623f0295', '882ea1f41c290764a9042f26c5010522', '29cf13f3704c48a8a98bf85856c64e57');
INSERT INTO `role_permission` VALUES ('d15d7cc52778818a62a4f823378baa4a', '882ea1f41c290764a9042f26c5010522', '72509285062f4e23865bbf069959a84a');
INSERT INTO `role_permission` VALUES ('d3d0f35d0189071d4d8ee404479d3f5d', '8c004a6b546feff20a14c5a3de2e544e', 'ea0810e769ca453ba8695457f44aac3a');
INSERT INTO `role_permission` VALUES ('d483da5367a4d26e17049432060744f1', '8c004a6b546feff20a14c5a3de2e544e', '3efcd2745c0006a764746fa0fd21791f');
INSERT INTO `role_permission` VALUES ('d4df1bf244dbf1b65bff9d999715dd0e', '8c004a6b546feff20a14c5a3de2e544e', '717b5a6dd7864f3383ab57aef78a7964');
INSERT INTO `role_permission` VALUES ('d7583c6ba6f7899cb18373137ad8aad1', '8c004a6b546feff20a14c5a3de2e544e', '12cecee01e06426596a36145f6248160');
INSERT INTO `role_permission` VALUES ('dd7f14d3445f2daa2f40519f9ea8758c', '8c004a6b546feff20a14c5a3de2e544e', '9c958f4f9267d7afc58a2688931a770f');
INSERT INTO `role_permission` VALUES ('e677e5d65a86d9580a939fed37505d83', '8c004a6b546feff20a14c5a3de2e544e', '3664c0fab1bc95fd94c3e8665b2eb500');
INSERT INTO `role_permission` VALUES ('e7c2854ef76ec62c1db10111efade8d3', '8c004a6b546feff20a14c5a3de2e544e', '42efcefbda234f11bdab6d74c44d7efd');
INSERT INTO `role_permission` VALUES ('ea2e55509131bd157ae9bf95a8ef0e7b', '882ea1f41c290764a9042f26c5010522', '35b181057d01499c9122c0aca7ebe149');
INSERT INTO `role_permission` VALUES ('ef0129a252b6ca9c36243bbee7032240', '8c004a6b546feff20a14c5a3de2e544e', 'dd86a691214366d2044b11b12471850b');
INSERT INTO `role_permission` VALUES ('f2c2de07cebf04ea18bc42562a7e4827', '8c004a6b546feff20a14c5a3de2e544e', '3fea71bab5cc4142963e631e04b863d3');
INSERT INTO `role_permission` VALUES ('f497c219a453a94c44ed4559e3fbcfc5', '8c004a6b546feff20a14c5a3de2e544e', '0b88b52892b1f3f18240c1188ad35646');
INSERT INTO `role_permission` VALUES ('f61fd2bdacc1a6311539744f6daad91e', '8c004a6b546feff20a14c5a3de2e544e', '7117880013af919ba80cf240bfd7c519');
INSERT INTO `role_permission` VALUES ('fd598ff7d7d6f239febdcd29738f0571', '882ea1f41c290764a9042f26c5010522', 'de75c26ee8594459a18bd27460ad569c');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `token` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'token',
  `expression` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES ('128cd073864b43088392f5caf6ee4577', '718b3edade24475c8d704ba6065747aa', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjZGEzMGVkMmU3ZGQ0ZDUzOWY2ZjIwOTQ1MjdlYzE1MyIsImlhdCI6MTU3MzYzODkxMCwic3ViIjoie1wiYWRkcmVzc1wiOlwiYXNkYXMzMzMzXCIsXCJhcmlkXCI6XCIzN1wiLFwiYmlydGhcIjpcIjIwMTktMDEtMDdcIixcImNydG1cIjoxNTQ4Njc5MzM4MDAwLFwiY3RpZFwiOlwiMzZcIixcImVkdWNhdGlvblwiOlwiZHNhXCIsXCJlbWFpbFwiOlwiMTIzOTA2MDM2QHFxLmNvbVwiLFwiZW50cnlcIjpcIjIwMTktMDEtMzBcIixcImlkXCI6XCI3MThiM2VkYWRlMjQ0NzVjOGQ3MDRiYTYwNjU3NDdhYVwiLFwiaXNkZWxldGVcIjoxLFwibWFqb3JcIjpcInNkYVwiLFwibWRlclwiOlwiNzE4YjNlZGFkZTI0NDc1YzhkNzA0YmE2MDY1NzQ3YWFcIixcIm1kdG1cIjoxNTUwMDQyMjU5MDAwLFwibW9iaWxlXCI6MTIzNDI0MzQsXCJuYW1lXCI6XCJhZG1pbjFcIixcIm5hdGlvbnNcIjpcImFzYVwiLFwibmF0aXZlc1wiOlwiYXNkXCIsXCJwcmlkXCI6XCIxXCIsXCJwd2RcIjpcImFkMjUxMTRjNDMyOWI1NjIxNzFkZTYxNjYwNWY3ZDcyXCIsXCJybmFtZVwiOlwi6ZmI5YGl55SfXCIsXCJzY2hvb2xcIjpcImFzZFwiLFwic2V4XCI6MSxcInN0YXRlXCI6MX0iLCJpZCI6IjcxOGIzZWRhZGUyNDQ3NWM4ZDcwNGJhNjA2NTc0N2FhIiwiZXhwIjoxNTczNjQ2MTEwfQ.ejPyTYzcjzPL4AncsuFQ8bzfJExD47D2Lon3H4_IOFc', '[\"0000\",\"0005\",\"0010\",\"0015\",\"00100000\",\"00150000\",\"00000000\",\"00050000\",\"00100005\",\"00000005\",\"00000010\",\"00150010\",\"00000015\",\"00150015\",\"00050020\",\"00000020\",\"00150020\",\"00050025\",\"00000025\",\"00150025\",\"00050030\",\"00050035\",\"000000100000\",\"000500350000\",\"001500200000\",\"000500300000\",\"000000150000\",\"001000000000\",\"000000000000\",\"001500050000\",\"000000200000\",\"000500000000\",\"000500200000\",\"001500100000\",\"001500000000\",\"001500150000\",\"000000250000\",\"000000050000\",\"000500250000\",\"001500250005\",\"001500050005\",\"000000150005\",\"000500300005\",\"000500000005\",\"000000050005\",\"001500200005\",\"000000100005\",\"000500250005\",\"001000000005\",\"000000200005\",\"001500250010\",\"000000000005\",\"001500000005\",\"001500100005\",\"000500200005\",\"001500150005\",\"001500050010\",\"000000100010\",\"000000050010\",\"001500150010\",\"000000200010\",\"000500300010\",\"001500200010\",\"001500250015\",\"000000000010\",\"000500000010\",\"000500200010\",\"001500100010\",\"000000150010\",\"001500000010\",\"000500250010\",\"001000000010\",\"000500200015\",\"001500050015\",\"001500200015\",\"000000150015\",\"000500250015\",\"001500000015\",\"000500300015\",\"001000000015\",\"000000100015\",\"000000200015\",\"000500000015\",\"000000050015\",\"001500150015\",\"001500100015\",\"000000000015\",\"000500200020\",\"001500000020\",\"001500150020\",\"000000100020\",\"000500000020\",\"001500050020\",\"000500250020\",\"001000000020\",\"000000000020\",\"001500200020\",\"001500100020\",\"000500300020\",\"001500100025\",\"001500000025\",\"000000000025\",\"000500000025\",\"001000000025\",\"001500200025\",\"001000000030\",\"001500100030\",\"001500200030\",\"000500000030\",\"000500000035\",\"001500100035\",\"001500200035\",\"001500200040\",\"000500000040\",\"001500200045\"]', '2019-11-13 17:55:11', NULL, '2019-11-13 17:55:11', NULL);
INSERT INTO `token` VALUES ('4a2ee15e7f8740bc92ae5d88a2368e7e', '718b3edade24475c8d704ba6065747aa', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkOTAwM2IyOGM3NjQ0NDA5ODFiY2Y5OTk5NTU5MTFkNiIsImlhdCI6MTU3MzYzOTAwNCwic3ViIjoie1wiYWRkcmVzc1wiOlwiYXNkYXMzMzMzXCIsXCJhcmlkXCI6XCIzN1wiLFwiYmlydGhcIjpcIjIwMTktMDEtMDdcIixcImNydG1cIjoxNTQ4Njc5MzM4MDAwLFwiY3RpZFwiOlwiMzZcIixcImVkdWNhdGlvblwiOlwiZHNhXCIsXCJlbWFpbFwiOlwiMTIzOTA2MDM2QHFxLmNvbVwiLFwiZW50cnlcIjpcIjIwMTktMDEtMzBcIixcImlkXCI6XCI3MThiM2VkYWRlMjQ0NzVjOGQ3MDRiYTYwNjU3NDdhYVwiLFwiaXNkZWxldGVcIjoxLFwibWFqb3JcIjpcInNkYVwiLFwibWRlclwiOlwiNzE4YjNlZGFkZTI0NDc1YzhkNzA0YmE2MDY1NzQ3YWFcIixcIm1kdG1cIjoxNTUwMDQyMjU5MDAwLFwibW9iaWxlXCI6MTIzNDI0MzQsXCJuYW1lXCI6XCJhZG1pbjFcIixcIm5hdGlvbnNcIjpcImFzYVwiLFwibmF0aXZlc1wiOlwiYXNkXCIsXCJwcmlkXCI6XCIxXCIsXCJwd2RcIjpcImFkMjUxMTRjNDMyOWI1NjIxNzFkZTYxNjYwNWY3ZDcyXCIsXCJybmFtZVwiOlwi6ZmI5YGl55SfXCIsXCJzY2hvb2xcIjpcImFzZFwiLFwic2V4XCI6MSxcInN0YXRlXCI6MX0iLCJpZCI6IjcxOGIzZWRhZGUyNDQ3NWM4ZDcwNGJhNjA2NTc0N2FhIiwiZXhwIjoxNTczNjQ2MjA0fQ.81KjZ9jG_JxYC5sqEOagbiEctGg0xqH3RA783OubVM4', '[\"0000\",\"0005\",\"0010\",\"0015\",\"00100000\",\"00150000\",\"00000000\",\"00050000\",\"00100005\",\"00000005\",\"00000010\",\"00150010\",\"00000015\",\"00150015\",\"00050020\",\"00000020\",\"00150020\",\"00050025\",\"00000025\",\"00150025\",\"00050030\",\"00050035\",\"000000100000\",\"000500350000\",\"001500200000\",\"000500300000\",\"000000150000\",\"001000000000\",\"000000000000\",\"001500050000\",\"000000200000\",\"000500000000\",\"000500200000\",\"001500100000\",\"001500000000\",\"001500150000\",\"000000250000\",\"000000050000\",\"000500250000\",\"001500250005\",\"001500050005\",\"000000150005\",\"000500300005\",\"000500000005\",\"000000050005\",\"001500200005\",\"000000100005\",\"000500250005\",\"001000000005\",\"000000200005\",\"001500250010\",\"000000000005\",\"001500000005\",\"001500100005\",\"000500200005\",\"001500150005\",\"001500050010\",\"000000100010\",\"000000050010\",\"001500150010\",\"000000200010\",\"000500300010\",\"001500200010\",\"001500250015\",\"000000000010\",\"000500000010\",\"000500200010\",\"001500100010\",\"000000150010\",\"001500000010\",\"000500250010\",\"001000000010\",\"000500200015\",\"001500050015\",\"001500200015\",\"000000150015\",\"000500250015\",\"001500000015\",\"000500300015\",\"001000000015\",\"000000100015\",\"000000200015\",\"000500000015\",\"000000050015\",\"001500150015\",\"001500100015\",\"000000000015\",\"000500200020\",\"001500000020\",\"001500150020\",\"000000100020\",\"000500000020\",\"001500050020\",\"000500250020\",\"001000000020\",\"000000000020\",\"001500200020\",\"001500100020\",\"000500300020\",\"001500100025\",\"001500000025\",\"000000000025\",\"000500000025\",\"001000000025\",\"001500200025\",\"001000000030\",\"001500100030\",\"001500200030\",\"000500000030\",\"000500000035\",\"001500100035\",\"001500200035\",\"001500200040\",\"000500000040\",\"001500200045\"]', '2019-11-13 17:56:45', NULL, '2019-11-13 17:56:45', NULL);
INSERT INTO `token` VALUES ('73438e4f09224a06a88e2c349e3589dc', '718b3edade24475c8d704ba6065747aa', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJlNTVlZjMyNGJjMTY0MTY1OGFhZjg5YWI3ODkxM2Y3MiIsImlhdCI6MTU2OTEzOTE1OCwic3ViIjoie1wiYWRkcmVzc1wiOlwiYXNkYXMzMzMzXCIsXCJhcmlkXCI6XCIzN1wiLFwiYmlydGhcIjpcIjIwMTktMDEtMDdcIixcImNydG1cIjoxNTQ4Njc5MzM4MDAwLFwiY3RpZFwiOlwiMzZcIixcImVkdWNhdGlvblwiOlwiZHNhXCIsXCJlbWFpbFwiOlwiMTIzOTA2MDM2QHFxLmNvbVwiLFwiZW50cnlcIjpcIjIwMTktMDEtMzBcIixcImlkXCI6XCI3MThiM2VkYWRlMjQ0NzVjOGQ3MDRiYTYwNjU3NDdhYVwiLFwiaXNkZWxldGVcIjoxLFwibWFqb3JcIjpcInNkYVwiLFwibWRlclwiOlwiNzE4YjNlZGFkZTI0NDc1YzhkNzA0YmE2MDY1NzQ3YWFcIixcIm1kdG1cIjoxNTUwMDQyMjU5MDAwLFwibW9iaWxlXCI6MTIzNDI0MzQsXCJuYW1lXCI6XCJhZG1pbjFcIixcIm5hdGlvbnNcIjpcImFzYVwiLFwibmF0aXZlc1wiOlwiYXNkXCIsXCJwcmlkXCI6XCIxXCIsXCJwd2RcIjpcImFkMjUxMTRjNDMyOWI1NjIxNzFkZTYxNjYwNWY3ZDcyXCIsXCJybmFtZVwiOlwi6ZmI5YGl55SfXCIsXCJzY2hvb2xcIjpcImFzZFwiLFwic2V4XCI6MSxcInN0YXRlXCI6MX0iLCJpZCI6IjcxOGIzZWRhZGUyNDQ3NWM4ZDcwNGJhNjA2NTc0N2FhIiwiZXhwIjoxNTY5MTQ2MzU4fQ.sZI7m9TORtUz7poduzhBSQ3fQHwgAJ2-_Vg7vbF4HgU', '[\"0000\",\"0005\",\"0010\",\"0015\",\"00100000\",\"00150000\",\"00000000\",\"00050000\",\"00100005\",\"00000005\",\"00150010\",\"00000010\",\"00000015\",\"00150015\",\"00000020\",\"00150020\",\"00050020\",\"00050025\",\"00000025\",\"00150025\",\"00050030\",\"00050035\",\"000000100000\",\"000500350000\",\"001500200000\",\"000500300000\",\"000000150000\",\"001000000000\",\"000000000000\",\"001500050000\",\"000000200000\",\"000500000000\",\"000500200000\",\"001500100000\",\"001500000000\",\"001500150000\",\"000000250000\",\"000000050000\",\"000500250000\",\"001500250005\",\"000500300005\",\"000500000005\",\"000000050005\",\"001500200005\",\"000000100005\",\"000500250005\",\"001000000005\",\"000000200005\",\"001500250010\",\"000000000005\",\"001500000005\",\"001500100005\",\"000500200005\",\"001500150005\",\"001500050010\",\"001500050005\",\"000000150005\",\"000000050010\",\"001500150010\",\"000000200010\",\"000500300010\",\"001500200010\",\"001500250015\",\"000000000010\",\"000500000010\",\"000500200010\",\"001500100010\",\"000000150010\",\"001500000010\",\"000500250010\",\"001000000010\",\"000000100010\",\"000500200015\",\"001500050015\",\"001500200015\",\"000000150015\",\"000500250015\",\"001500000015\",\"000500300015\",\"001000000015\",\"000000100015\",\"000000200015\",\"000500000015\",\"000000050015\",\"001500150015\",\"001500100015\",\"000000000015\",\"001500000020\",\"001500150020\",\"000000100020\",\"000500000020\",\"001500050020\",\"000500250020\",\"001000000020\",\"000000000020\",\"001500200020\",\"001500100020\",\"000500300020\",\"000500200020\",\"001500100025\",\"001500000025\",\"000000000025\",\"000500000025\",\"001000000025\",\"001500200025\",\"001000000030\",\"001500100030\",\"001500200030\",\"000500000030\",\"000500000035\",\"001500100035\",\"001500200035\",\"001500200040\",\"000500000040\",\"001500200045\"]', '2019-09-22 15:59:19', NULL, '2019-09-22 15:59:19', NULL);
INSERT INTO `token` VALUES ('dbf825d56e6a5fce130975040f3c0c56', 'ad7880c1d4fd2a57ab2517268ac1334t', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjOGI5OGNhYzhmOTQ0MmFkODdmYTlhYzU3ODY5YjgxMiIsImlhdCI6MTU4MTk0NTkzMywic3ViIjoie1wiYWRkcmVzc1wiOlwiYXNkYXMzMzMzXCIsXCJiaXJ0aFwiOjE1ODAyMjcyMDAwMDAsXCJjcmVyXCI6XCJcIixcImNydG1cIjoxNTQ4Njc5MzM4MDAwLFwiZW1haWxcIjpcIjEyMzkwNjAzNkBxcS5jb21cIixcImlkXCI6XCJhZDc4ODBjMWQ0ZmQyYTU3YWIyNTE3MjY4YWMxMzM0dFwiLFwibWR0bVwiOjE1ODAzMDcyNTkwMDAsXCJtb2JpbGVcIjoxMjM0MjQzNCxcIm5hbWVcIjpcImFkbWluMVwiLFwicHdkXCI6XCJhZDI1MTE0YzQzMjliNTYyMTcxZGU2MTY2MDVmN2Q3MlwiLFwicmVtYXJrc1wiOlwiNFwiLFwicm5hbWVcIjpcIumZiOWBpeeUnzHlj7dcIixcInNleFwiOjJ9IiwiaWQiOiJhZDc4ODBjMWQ0ZmQyYTU3YWIyNTE3MjY4YWMxMzM0dCIsImV4cCI6MTU4MTk1MzEzM30.9cFQ_ywchFzHC57rN_43u71JGnomiaWkee2-uEtCevg', '[\"0000\",\"0005\",\"00000000\",\"00050000\",\"00000005\",\"00050005\",\"00000010\",\"00000015\",\"00050010\",\"00050015\",\"00050020\",\"000500000000\",\"000500200000\",\"000500100000\",\"000500150000\",\"000000050000\",\"000000150000\",\"000500050000\",\"000000100000\",\"000500150005\",\"000000150005\",\"000500200005\",\"000000050005\",\"000000000005\",\"000500050005\",\"000000100005\",\"000500100005\",\"000500000005\",\"000500000010\",\"000000150010\",\"000500100010\",\"000000100010\",\"000500150010\",\"000000050010\",\"000000000010\",\"000500200010\",\"000500050010\",\"000000100015\",\"000000000015\",\"000000000025\"]', '2020-02-17 21:25:35', NULL, '2020-02-17 21:25:35', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职工名称',
  `pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `rname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `mobile` decimal(13, 0) DEFAULT NULL COMMENT '手机',
  `sex` decimal(1, 0) DEFAULT NULL COMMENT '性别 1-男、2-女',
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birth` date DEFAULT NULL COMMENT '生日',
  `remarks` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `crtm` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `crer` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `mdtm` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `mder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('ad7880c1d4fd2a57ab2517268ac1334t', 'admin1', 'ad25114c4329b562171de616605f7d72', '陈健生1号', 'asdas3333', 12342434, 2, '123906036@qq.com', '2020-01-29', '4', '2019-01-28 20:42:18', '', '2020-01-29 22:14:19', NULL);
INSERT INTO `user` VALUES ('bff2b21bfe454242e9a4126a0e3e4d84', 'tester', 'ad25114c4329b562171de616605f7d72', '测试爹', '广州', 15813342257, 1, '158@qq.com', '2019-12-09', '123', '2019-12-09 23:46:26', NULL, '2019-12-09 23:46:26', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '员工id',
  `rid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '岗位id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职工-部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('ac22fee31382cc40fb2b846f96258af0', 'bff2b21bfe454242e9a4126a0e3e4d84', '882ea1f41c290764a9042f26c5010522');
INSERT INTO `user_role` VALUES ('c04371c107b60e1437e44208e957f19b', 'ad7880c1d4fd2a57ab2517268ac1334t', '8c004a6b546feff20a14c5a3de2e544e');

SET FOREIGN_KEY_CHECKS = 1;
