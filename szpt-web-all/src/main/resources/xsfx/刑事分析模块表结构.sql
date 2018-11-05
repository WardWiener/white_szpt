/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : szpt

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2016-10-21 10:38:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_xsfx_aj_db
-- ----------------------------
DROP TABLE IF EXISTS `t_xsfx_aj_db`;
CREATE TABLE `t_xsfx_aj_db` (
  `id` varchar(255) NOT NULL,
  `ajxz` varchar(255) DEFAULT NULL,
  `fact_aj_id` varchar(255) DEFAULT NULL,
  `ajlb` varchar(255) DEFAULT NULL,
  `cqbm` varchar(255) DEFAULT NULL,
  `zajk` varchar(255) DEFAULT NULL,
  `wd` longtext,
  `jd` varchar(255) DEFAULT NULL,
  `faddmc` varchar(255) DEFAULT NULL,
  `afddlx` varchar(255) DEFAULT NULL,
  `zzsd` varchar(255) DEFAULT NULL,
  `zack` varchar(255) DEFAULT NULL,
  `zzrs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xsfx_aj_db
-- ----------------------------

-- ----------------------------
-- Table structure for t_xsfx_ajkz
-- ----------------------------
DROP TABLE IF EXISTS `t_xsfx_ajkz`;
CREATE TABLE `t_xsfx_ajkz` (
  `id` varchar(255) NOT NULL,
  `fact_aj_id` varchar(255) DEFAULT NULL,
  `bzbj` double DEFAULT NULL,
  `kznr` varchar(255) DEFAULT NULL,
  `jssj` datetime DEFAULT NULL,
  `wd` longtext,
  `jd` varchar(255) DEFAULT NULL,
  `kssj` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xsfx_ajkz
-- ----------------------------

-- ----------------------------
-- Table structure for t_xsfx_zatd
-- ----------------------------
DROP TABLE IF EXISTS `t_xsfx_zatd`;
CREATE TABLE `t_xsfx_zatd` (
  `id` varchar(255) NOT NULL,
  `fact_aj_id` varchar(255) DEFAULT NULL,
  `bm` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_xsfx_zatd
-- ----------------------------
