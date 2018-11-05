/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50538
Source Host           : 127.0.0.1:3306
Source Database       : szpt

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-25 16:24:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dagl_gzr
-- ----------------------------
DROP TABLE IF EXISTS `t_dagl_gzr`;
CREATE TABLE `t_dagl_gzr` (
  `id` varchar(255) NOT NULL,
  `gzsj` datetime DEFAULT NULL,
  `sfzh` varchar(255) DEFAULT NULL,
  `sfzd` bit(1) DEFAULT NULL,
  `xm` varchar(255) DEFAULT NULL,
  `gzr_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
