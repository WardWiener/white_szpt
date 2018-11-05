/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_tlga

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:52:37
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_tlga`;
CREATE Database `source_tlga`;
use source_tlga;
-- ----------------------------
-- Table structure for t_bz_hcsk_gpxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_hcsk_gpxx`;
CREATE TABLE `t_bz_hcsk_gpxx` (
  `SYSTEMID` varchar(100) NOT NULL COMMENT '系统主键',
  `XM` varchar(200) DEFAULT NULL COMMENT '姓名',
  `ZJHM` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `SFZLX` varchar(100) DEFAULT NULL COMMENT '证件类型',
  `GPSJ` datetime DEFAULT NULL COMMENT '发车时间',
  `CC` varchar(100) DEFAULT NULL COMMENT '车次',
  `SFZ` varchar(100) DEFAULT NULL COMMENT '始发站',
  `ZDZ` varchar(100) DEFAULT NULL COMMENT '终点站',
  `SPFS` varchar(100) DEFAULT NULL COMMENT '售票方式',
  `FCSJ` datetime DEFAULT NULL COMMENT '购票时间',
  `BZK_RKSJ` datetime DEFAULT NULL COMMENT '入库时间',
  `BZK_GXSJ` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`SYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
