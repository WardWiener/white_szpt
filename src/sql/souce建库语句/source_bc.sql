/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_bc

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:30:16
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_bc`;
CREATE Database `source_bc`;
use source_bc;
-- ----------------------------
-- Table structure for t_bz_bc_thjlxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_bc_thjlxx`;
CREATE TABLE `t_bz_bc_thjlxx` (
  `SYSTEMID` varchar(50) NOT NULL COMMENT '系统主键',
  `CALL_STATUS` varchar(1) DEFAULT NULL COMMENT '通话状态:0未接 1接通 9其他',
  `COLLECT_TARGET_ID` varchar(26) DEFAULT NULL COMMENT '手机取证采集目标编号',
  `DELETE_STATUS` varchar(1) DEFAULT NULL COMMENT '删除状态:是否已删除(0未删除，1已删除)',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  `DUAL_TIME` int(11) DEFAULT NULL COMMENT '通话时长:单位为秒（未接通时为响铃时长）',
  `END_TIME` datetime DEFAULT NULL COMMENT '通话结束时间',
  `HCK_GXSJ` datetime DEFAULT NULL COMMENT '更新时间',
  `HCK_RKSJ` datetime DEFAULT NULL COMMENT '入库时间',
  `LOCAL_ACTION` varchar(2) DEFAULT NULL COMMENT '本地动作:标示本机是收方还是发方.01接收方 02发送方 99其他',
  `MSISDN` varchar(128) DEFAULT NULL COMMENT '本机号码',
  `PERSONID` varchar(23) DEFAULT NULL COMMENT '人员编号',
  `PRIVACYCONFIG` varchar(1) DEFAULT NULL COMMENT '加密状态',
  `RELATIONSHIP_ACCOUNT` varchar(128) DEFAULT NULL COMMENT '对方号码',
  `RELATIONSHIP_NAME` varchar(64) DEFAULT NULL COMMENT '联系人姓名',
  `START_TIME` datetime DEFAULT NULL COMMENT '通话开始时间',
  `YXTBH` varchar(32) DEFAULT NULL COMMENT '该条记录唯一编号',
  `BZK_ZZJG` varchar(12) DEFAULT NULL COMMENT '标准库组织机构',
  `BZK_RKSJ` datetime DEFAULT NULL COMMENT '标准库入库时间',
  `BZK_GXSJ` datetime DEFAULT NULL COMMENT '标准库更新时间',
  `ZYK_SSXTMC` varchar(100) DEFAULT NULL COMMENT '所属系统名称',
  `BZK_SCBZ` varchar(1) DEFAULT NULL COMMENT '标准库删除标识',
  PRIMARY KEY (`SYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_bz_bc_txlxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_bc_txlxx`;
CREATE TABLE `t_bz_bc_txlxx` (
  `SYSTEMID` varchar(50) NOT NULL COMMENT '系统主键',
  `COLLECT_TARGET_ID` varchar(26) DEFAULT NULL COMMENT '手机取证采集目标编号',
  `DELETE_STATUS` varchar(1) DEFAULT NULL COMMENT '删除状态:(0未删除，1已删除)',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  `HCK_GXSJ` datetime DEFAULT NULL COMMENT '更新时间',
  `HCK_RKSJ` datetime DEFAULT NULL COMMENT '入库时间',
  `PERSONID` varchar(23) DEFAULT NULL COMMENT '人员编号',
  `PRIVACYCONFIG` varchar(1) DEFAULT NULL COMMENT '加密状态',
  `RELATIONSHIP_NAME` varchar(64) DEFAULT NULL COMMENT '联系人姓名',
  `SEQUENCE_NAME` varchar(32) DEFAULT NULL COMMENT '通讯录ID',
  `YXTBH` varchar(32) DEFAULT NULL COMMENT '该条记录唯一编号',
  `BZK_ZZJG` varchar(12) DEFAULT NULL COMMENT '标准库组织机构',
  `BZK_RKSJ` datetime DEFAULT NULL COMMENT '标准库入库时间',
  `BZK_GXSJ` datetime DEFAULT NULL COMMENT '标准库更新时间',
  `ZYK_SSXTMC` varchar(100) DEFAULT NULL COMMENT '所属系统名称',
  `BZK_SCBZ` varchar(1) DEFAULT NULL COMMENT '标准库删除标识',
  PRIMARY KEY (`SYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_bz_bc_txlxxxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_bc_txlxxxx`;
CREATE TABLE `t_bz_bc_txlxxxx` (
  `SYSTEMID` varchar(50) NOT NULL COMMENT '系统主键',
  `COLLECT_TARGET_ID` varchar(26) DEFAULT NULL COMMENT '手机取证采集目标编号',
  `DELETE_STATUS` varchar(1) DEFAULT NULL COMMENT '删除状态:(0未删除，1已删除)',
  `DELETE_TIME` datetime DEFAULT NULL COMMENT '删除时间',
  `HCK_GXSJ` datetime DEFAULT NULL COMMENT '更新时间',
  `HCK_RKSJ` datetime DEFAULT NULL COMMENT '入库时间',
  `PERSONID` varchar(23) DEFAULT NULL COMMENT '人员编号',
  `PHONE_NUMBER_TYPE` varchar(256) DEFAULT NULL COMMENT '字段标签',
  `PHONE_VALUE_TYPE` varchar(2) DEFAULT NULL COMMENT '通讯录字段类型(01电话号码，02EMAIL，03地址，04即时通讯，05网站，06纪念日，07备注，08群组，99其他)',
  `PUUID` varchar(32) DEFAULT NULL COMMENT '父节点记录唯一编号',
  `RELATIONSHIP_ACCOUNT` varchar(128) DEFAULT NULL COMMENT '字段值',
  `YXTBH` varchar(32) DEFAULT NULL COMMENT '该条记录唯一编号',
  `BZK_ZZJG` varchar(12) DEFAULT NULL COMMENT '标准库组织机构',
  `BZK_RKSJ` datetime DEFAULT NULL COMMENT '标准库入库时间',
  `BZK_GXSJ` datetime DEFAULT NULL COMMENT '标准库更新时间',
  `ZYK_SSXTMC` varchar(100) DEFAULT NULL COMMENT '所属系统名称',
  `BZK_SCBZ` varchar(1) DEFAULT NULL COMMENT '标准库删除标识',
  PRIMARY KEY (`SYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
