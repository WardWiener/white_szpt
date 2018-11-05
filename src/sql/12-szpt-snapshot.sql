/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50538
Source Host           : 127.0.0.1:3306
Source Database       : szpt

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-14 10:34:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `t_snapshot`;
CREATE TABLE `t_snapshot` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `cjr` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `snapshot` longtext,
  `targetId` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
