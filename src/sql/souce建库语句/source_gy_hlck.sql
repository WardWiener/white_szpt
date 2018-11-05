/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_gy_hlck

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:34:52
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_gy_hlck`;
CREATE Database `source_gy_hlck`;
use source_gy_hlck;
-- ----------------------------
-- Table structure for t_fkycl
-- ----------------------------
DROP TABLE IF EXISTS `t_fkycl`;
CREATE TABLE `t_fkycl` (
  `FKYCLBH` varchar(14) DEFAULT NULL COMMENT '非客运车辆编号',
  `HLDZBH` varchar(14) DEFAULT NULL COMMENT '核录地址编号',
  `HLSJ` datetime DEFAULT NULL COMMENT '核录时间',
  `HPHM` varchar(30) DEFAULT NULL COMMENT '号牌号码',
  `CLLX` varchar(20) DEFAULT NULL COMMENT '车辆类型',
  `CLSBDH` varchar(18) DEFAULT NULL COMMENT '车辆识别代号',
  `JSYZJLX` varchar(10) DEFAULT NULL COMMENT '驾驶员证件类型',
  `JSYZJHM` varchar(18) DEFAULT NULL COMMENT '驾驶员证件号码',
  `JSYXM` varchar(30) DEFAULT NULL COMMENT '驾驶员姓名',
  `CLXCZP` varchar(14) DEFAULT NULL COMMENT '车辆现场照片索引',
  `BZ` varchar(800) DEFAULT NULL COMMENT '备注',
  `CLUEID` varchar(60) DEFAULT NULL COMMENT '采集ID',
  `HLDZ` varchar(200) DEFAULT NULL COMMENT '核录地址',
  `USERID` varchar(30) DEFAULT NULL COMMENT '警员Id',
  `LONGITUDE` decimal(10,0) DEFAULT NULL COMMENT '经度',
  `LATITUDE` decimal(10,0) DEFAULT NULL COMMENT '纬度',
  `T_TYPE` int(11) DEFAULT NULL COMMENT '1-终端，2-PC端录入，3-PC端刷二代证录入，4-PC端批量导入',
  `IMEI` varchar(80) DEFAULT NULL COMMENT '终端设备ID号',
  `ALERT` int(11) DEFAULT NULL COMMENT '预警人员：1前科，2涉毒，3在逃，4涉医。没有预警为0',
  `POLICENAME` varchar(40) DEFAULT NULL COMMENT '警员姓名',
  `UNIT` varchar(60) DEFAULT NULL COMMENT '警员单位ID',
  `UNITNAME` varchar(80) DEFAULT NULL COMMENT '警员单位名称',
  `UPUNIT` varchar(60) DEFAULT NULL,
  `UPUNITNAME` varchar(80) DEFAULT NULL COMMENT '警员上级单位名称',
  `HCYY` varchar(200) DEFAULT NULL,
  `XCCLJG` varchar(40) DEFAULT NULL,
  `SFCL` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ryxx
-- ----------------------------
DROP TABLE IF EXISTS `t_ryxx`;
CREATE TABLE `t_ryxx` (
  `RYBH` varchar(14) NOT NULL COMMENT '人员编号',
  `HLDZBH` varchar(14) DEFAULT NULL COMMENT '核录地址编号',
  `HLSJ` datetime DEFAULT NULL COMMENT '核录时间',
  `HLGZLX` varchar(40) DEFAULT NULL COMMENT '核录工作类型',
  `ZJLX` varchar(20) DEFAULT NULL COMMENT '证件类型',
  `ZJHM` varchar(40) DEFAULT NULL COMMENT '证件号码',
  `XM` varchar(30) DEFAULT NULL COMMENT '姓名',
  `XB` varchar(20) DEFAULT NULL COMMENT '性别',
  `DHHM` varchar(60) DEFAULT NULL COMMENT '电话号码',
  `GZDW` varchar(100) DEFAULT NULL COMMENT '工作单位',
  `ZZ` varchar(410) DEFAULT NULL COMMENT '住址',
  `XCZP` varchar(200) DEFAULT NULL COMMENT '现场照片',
  `BZ` varchar(800) DEFAULT NULL COMMENT '备注',
  `CLUEID` varchar(60) DEFAULT NULL COMMENT '采集ID',
  `HLDZ` varchar(200) DEFAULT NULL COMMENT '核录地址',
  `USERID` varchar(30) DEFAULT NULL COMMENT '警员Id',
  `ZZDZ` varchar(410) DEFAULT NULL COMMENT '暂住地址',
  `JSCL` varchar(200) DEFAULT NULL COMMENT '驾驶车辆',
  `SSWP` varchar(200) DEFAULT NULL COMMENT '随身物品',
  `HCJG` varchar(20) DEFAULT NULL COMMENT '核查结果',
  `LONGITUDE` decimal(10,0) DEFAULT NULL COMMENT '经度',
  `LATITUDE` decimal(10,0) DEFAULT NULL COMMENT '纬度',
  `T_TYPE` int(11) DEFAULT NULL COMMENT '1-终端，2-PC端录入，3-PC端刷二代证录入，4-PC端批量导入',
  `IMEI` varchar(80) DEFAULT NULL COMMENT '终端设备ID号',
  `ALERT` int(11) DEFAULT NULL COMMENT '预警人员：1前科，2涉毒，3在逃，4涉医。没有预警为0',
  `POLICENAME` varchar(40) DEFAULT NULL COMMENT '警员姓名',
  `UNIT` varchar(60) DEFAULT NULL COMMENT '警员单位ID',
  `UNITNAME` varchar(80) DEFAULT NULL COMMENT '警员单位名称',
  `UPUNIT` varchar(60) DEFAULT NULL,
  `UPUNITNAME` varchar(80) DEFAULT NULL COMMENT '警员上级单位名称',
  `HCYY` varchar(200) DEFAULT NULL,
  `XCCLJG` varchar(40) DEFAULT NULL,
  `SFCL` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`RYBH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
