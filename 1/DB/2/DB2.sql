/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : DB2

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 26/10/2018 16:34:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Bill
-- ----------------------------
DROP TABLE IF EXISTS `Bill`;
CREATE TABLE `Bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SMSFee` double NOT NULL,
  `SMSUsage` int(11) NOT NULL,
  `callFee` double NOT NULL,
  `callUsage` double NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `domesticDataFee` double NOT NULL,
  `domesticDataUsage` double NOT NULL,
  `localDataFee` double NOT NULL,
  `localDataUsage` double NOT NULL,
  `sumFee` double NOT NULL,
  `user_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsi0834o7yhnb6gulgc073hy8k` (`user_username`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Bill
-- ----------------------------
BEGIN;
INSERT INTO `Bill` VALUES (8, 10, 300, 0, 200, '2018-10', 0, 0, 0, 0, 10, '刘嘉');
INSERT INTO `Bill` VALUES (9, 0, 0, 0, 0, '2018-10', 3000, 1000, 0, 100, 3000, '陈振宇');
INSERT INTO `Bill` VALUES (10, 0, 0, 0, 0, '2018-10', 3000, 1000, 0, 100, 3000, '陈振宇');
INSERT INTO `Bill` VALUES (11, 0, 0, 0, 0, '2018-10', 3000, 1000, 0, 100, 3000, '陈振宇');
INSERT INTO `Bill` VALUES (12, 0, 0, 0, 0, '2018-10', 3000, 1000, 0, 100, 3000, '陈振宇');
INSERT INTO `Bill` VALUES (13, 0, 0, 0, 0, '2018-10', 3000, 1000, 0, 100, 3000, '陈振宇');
COMMIT;

-- ----------------------------
-- Table structure for Bundle
-- ----------------------------
DROP TABLE IF EXISTS `Bundle`;
CREATE TABLE `Bundle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `beginDate` date DEFAULT NULL,
  `bundleType` int(11) DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `period` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Bundle
-- ----------------------------
BEGIN;
INSERT INTO `Bundle` VALUES (2, '2018-11-01', 2, '2018-10-31', '2018-10-25', 2);
INSERT INTO `Bundle` VALUES (25, '2018-10-25', 1, '2018-11-25', '2018-10-25', 1);
INSERT INTO `Bundle` VALUES (29, '2018-10-25', 3, '2102-02-25', '2018-10-25', 1000);
INSERT INTO `Bundle` VALUES (30, '2018-11-01', 4, '2268-11-01', '2018-10-25', 3000);
INSERT INTO `Bundle` VALUES (26, '2018-10-25', 2, '2018-12-25', '2018-10-25', 2);
COMMIT;

-- ----------------------------
-- Table structure for Consumption
-- ----------------------------
DROP TABLE IF EXISTS `Consumption`;
CREATE TABLE `Consumption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SMSFee` double NOT NULL,
  `SMSUsage` double NOT NULL,
  `callFee` double NOT NULL,
  `callUsage` double NOT NULL,
  `domesticDataFee` double NOT NULL,
  `domesticDataUsage` double NOT NULL,
  `fee` double NOT NULL,
  `localDataFee` double NOT NULL,
  `localDataUsage` double NOT NULL,
  `localDate` date DEFAULT NULL,
  `user_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68qahrr3rqlwbwl5fbvbxr4sq` (`user_username`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Consumption
-- ----------------------------
BEGIN;
INSERT INTO `Consumption` VALUES (10, 0, 0, 0, 100, 0, 0, 0, 0, 0, '2018-10-25', '刘嘉');
INSERT INTO `Consumption` VALUES (14, 0, 0, 0, 100, 0, 0, 0, 0, 0, '2018-10-25', '刘嘉');
INSERT INTO `Consumption` VALUES (15, 10, 300, 0, 0, 0, 0, 10, 0, 0, '2018-10-25', '刘嘉');
INSERT INTO `Consumption` VALUES (18, 0, 0, 0, 0, 0, 0, 0, 0, 100, '2018-10-25', '陈振宇');
INSERT INTO `Consumption` VALUES (19, 0, 0, 0, 0, 3000, 1000, 3000, 0, 0, '2018-10-25', '陈振宇');
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `username` varchar(255) NOT NULL,
  `SMSRemain` int(11) NOT NULL,
  `balance` double NOT NULL,
  `callRemain` int(11) NOT NULL,
  `domesticDataRemain` double NOT NULL,
  `localDataRemain` double NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO `User` VALUES ('潘羽', 200, -20, 100, 0, 0, 'admin');
INSERT INTO `User` VALUES ('刘嘉', 0, 960, 0, 0, 0, '123456');
INSERT INTO `User` VALUES ('陈振宇', 0, -2020, 0, 0, 1900, '123456');
COMMIT;

-- ----------------------------
-- Table structure for User_Bundle
-- ----------------------------
DROP TABLE IF EXISTS `User_Bundle`;
CREATE TABLE `User_Bundle` (
  `User_username` varchar(255) NOT NULL,
  `bundleList_id` int(11) NOT NULL,
  UNIQUE KEY `UK_lt414968csig2736lrfbvru1v` (`bundleList_id`),
  KEY `FKg6oix0yadd5pc6pga1cloiuyw` (`User_username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of User_Bundle
-- ----------------------------
BEGIN;
INSERT INTO `User_Bundle` VALUES ('潘羽', 2);
INSERT INTO `User_Bundle` VALUES ('潘羽', 11);
INSERT INTO `User_Bundle` VALUES ('刘嘉', 26);
INSERT INTO `User_Bundle` VALUES ('刘嘉', 25);
INSERT INTO `User_Bundle` VALUES ('陈振宇', 30);
INSERT INTO `User_Bundle` VALUES ('陈振宇', 29);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
