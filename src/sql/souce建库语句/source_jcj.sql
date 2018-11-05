/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_jcj

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:35:08
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_jcj`;
CREATE Database `source_jcj`;
use source_jcj;
-- ----------------------------
-- Table structure for t_cjd
-- ----------------------------
DROP TABLE IF EXISTS `t_cjd`;
CREATE TABLE `t_cjd` (
  `CJDBH` varchar(255) DEFAULT NULL COMMENT '处警单编号',
  `XZQH` varchar(100) DEFAULT NULL,
  `SJDBH` varchar(255) DEFAULT NULL COMMENT '受警单编号',
  `CJDW` varchar(255) DEFAULT NULL COMMENT '处警单位',
  `CJYGH` varchar(255) DEFAULT NULL COMMENT '处警人工号',
  `CJYXM` varchar(255) DEFAULT NULL COMMENT '处警人姓名',
  `CJCS` varchar(255) DEFAULT NULL,
  `CJJSSJ` datetime DEFAULT NULL COMMENT '处警结束时间',
  `DDCJSC` int(100) DEFAULT NULL,
  `CJSC` int(255) DEFAULT NULL,
  `CJDZT` varchar(100) DEFAULT NULL COMMENT '处警单状态',
  `CJTH` varchar(255) DEFAULT NULL,
  `SJC` datetime DEFAULT NULL,
  `CJKSSJ` datetime DEFAULT NULL,
  `CJLX` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_cjzld
-- ----------------------------
DROP TABLE IF EXISTS `t_cjzld`;
CREATE TABLE `t_cjzld` (
  `ZLDBH` varchar(255) DEFAULT NULL COMMENT '指令单编号',
  `XZQH` varchar(255) DEFAULT NULL,
  `SJDBH` varchar(255) DEFAULT NULL COMMENT '受警单编号',
  `CJDBH` varchar(255) DEFAULT NULL COMMENT '处警单编号',
  `CJDW` varchar(255) DEFAULT NULL COMMENT '处警单位',
  `ZLXDSJ` datetime DEFAULT NULL COMMENT '指令下达时间',
  `ZLJSSJ` datetime DEFAULT NULL COMMENT '指令接收时间',
  `ZLJSDW` varchar(255) DEFAULT NULL COMMENT '指令接收单位',
  `ZLJSRXM` varchar(255) DEFAULT NULL COMMENT '指令接收人姓名',
  `CDSJ` datetime DEFAULT NULL COMMENT '出动时间',
  `DCSJ` datetime DEFAULT NULL COMMENT '到场时间',
  `ZLZT` varchar(255) DEFAULT NULL COMMENT '指令状态',
  `FKCSBJ` varchar(255) DEFAULT NULL,
  `SJC` datetime DEFAULT NULL COMMENT '时间戳',
  `CDWG` varchar(255) DEFAULT NULL,
  `ZLDDSJ` datetime DEFAULT NULL COMMENT '指令到达时间',
  `ZLJSRGH` varchar(255) DEFAULT NULL COMMENT '指令接收人工号',
  `WXFKBJ` varchar(255) DEFAULT NULL,
  `IMEI` varchar(255) DEFAULT NULL COMMENT '手机IMEI号',
  `ZDFKBJ` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_fkd
-- ----------------------------
DROP TABLE IF EXISTS `t_fkd`;
CREATE TABLE `t_fkd` (
  `FKDBH` varchar(255) DEFAULT NULL COMMENT '反馈单编号',
  `XZQH` varchar(255) DEFAULT NULL,
  `SJDBH` varchar(255) DEFAULT NULL COMMENT '受警单编号',
  `CJDBH` varchar(255) DEFAULT NULL COMMENT '处警单编号',
  `ZLDBH` varchar(255) DEFAULT NULL COMMENT '指令单编号',
  `ZLJSSJ` datetime DEFAULT NULL COMMENT '接收指令时间',
  `FKLX` varchar(255) DEFAULT NULL COMMENT '反馈类型',
  `FKKSSJ` datetime DEFAULT NULL COMMENT '反馈开始时间',
  `FKDW` varchar(255) DEFAULT NULL COMMENT '反馈单位',
  `FKTH` varchar(255) DEFAULT NULL,
  `FKRGH` varchar(255) DEFAULT NULL COMMENT '反馈人工号',
  `FKRXM` varchar(255) DEFAULT NULL COMMENT '反馈人姓名',
  `FKTXDW` varchar(255) DEFAULT NULL,
  `FKBCSJ` varchar(255) DEFAULT NULL COMMENT '反馈保存时间',
  `FKSC` varchar(255) DEFAULT NULL,
  `FKJSSJ` datetime DEFAULT NULL COMMENT '反馈结束时间',
  `FKJSDW` varchar(255) DEFAULT NULL COMMENT '反馈接收单位',
  `FKJSRGH` varchar(255) DEFAULT NULL COMMENT '反馈接收人工号',
  `FKJSRXM` varchar(255) DEFAULT NULL COMMENT '反馈接收人姓名',
  `FKJSTH` varchar(255) DEFAULT NULL,
  `FKJSZT` varchar(255) DEFAULT NULL COMMENT '反馈接收状态',
  `ZJBZ` varchar(255) DEFAULT NULL,
  `SHBZ` varchar(255) DEFAULT NULL,
  `CLLX` varchar(255) DEFAULT NULL COMMENT '处理类型',
  `GLSJDBH` varchar(255) DEFAULT NULL COMMENT '关联受警单编号',
  `AY` varchar(255) DEFAULT NULL COMMENT '案由',
  `JQJB` varchar(255) DEFAULT NULL COMMENT '警情级别',
  `CLJG` varchar(255) DEFAULT NULL COMMENT '处理结果',
  `CJQK` text,
  `YYWJ` varchar(255) DEFAULT NULL,
  `XZB` varchar(255) DEFAULT NULL COMMENT '经度',
  `YZB` varchar(255) DEFAULT NULL COMMENT '纬度',
  `SJC` datetime DEFAULT NULL,
  `FKWG` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sjd
-- ----------------------------
DROP TABLE IF EXISTS `t_sjd`;
CREATE TABLE `t_sjd` (
  `SJDBH` varchar(255) DEFAULT NULL COMMENT '受警单号 ',
  `XZQH` varchar(255) DEFAULT NULL,
  `JJDWBH` varchar(255) DEFAULT NULL,
  `SFDZ` varchar(255) DEFAULT NULL,
  `DWBZW` varchar(255) DEFAULT NULL,
  `XZB` varchar(255) DEFAULT NULL,
  `YZB` varchar(255) DEFAULT NULL,
  `SCBJSJ` datetime DEFAULT NULL,
  `BJR` varchar(255) DEFAULT NULL,
  `LXDH` varchar(255) DEFAULT NULL,
  `LXDZ` varchar(255) DEFAULT NULL,
  `XQDW` varchar(255) DEFAULT NULL,
  `SSFJ` varchar(255) DEFAULT NULL,
  `JZ` varchar(255) DEFAULT NULL,
  `CLLX` varchar(255) DEFAULT NULL,
  `JQJB` varchar(255) DEFAULT NULL,
  `AY` varchar(255) DEFAULT NULL,
  `GLSJDBH` varchar(255) DEFAULT NULL,
  `SJXQ` varchar(500) DEFAULT NULL,
  `CJDW` varchar(255) DEFAULT NULL,
  `CLJG` varchar(255) DEFAULT NULL,
  `SJCLQK` text,
  `SJZT` varchar(255) DEFAULT NULL,
  `SCSLJSSJ` datetime DEFAULT NULL,
  `BJRXB` varchar(255) DEFAULT NULL,
  `SFDZFL` varchar(255) DEFAULT NULL,
  `SCSLDBH` varchar(255) DEFAULT NULL,
  `SFNM` varchar(255) DEFAULT NULL,
  `JJYXM` varchar(255) DEFAULT NULL,
  `YYWJ` varchar(255) DEFAULT NULL,
  `SJC` datetime DEFAULT NULL,
  `SFGJZ` varchar(255) DEFAULT NULL,
  `SFFKGJZ` varchar(255) DEFAULT NULL,
  `LZBH` varchar(255) DEFAULT NULL,
  `JJYGH` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
