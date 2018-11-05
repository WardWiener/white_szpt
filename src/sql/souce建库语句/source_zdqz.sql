/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_zdqz

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:53:27
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_zdqz`;
CREATE Database `source_zdqz`;
use source_zdqz;
-- ----------------------------
-- Table structure for t_bz_xzqc_zcrjbxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_xzqc_zcrjbxx`;
CREATE TABLE `t_bz_xzqc_zcrjbxx` (
  `BZK_GXSJ` datetime DEFAULT NULL,
  `ZYK_SSXTMC` varchar(255) DEFAULT NULL,
  `SYSTEMID` varchar(255) DEFAULT NULL,
  `BH` varchar(255) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `CJSJ` datetime DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT NULL,
  `ETLDATE` datetime DEFAULT NULL,
  `HCK_GXSJ` datetime DEFAULT NULL,
  `HCK_RKSJ` datetime DEFAULT NULL,
  `HJDZ` varchar(255) DEFAULT NULL,
  `JJLXDH` varchar(255) DEFAULT NULL,
  `JJLXR` varchar(255) DEFAULT NULL,
  `JSZH` varchar(255) DEFAULT NULL,
  `LXDH` varchar(255) DEFAULT NULL,
  `OBJECTID` varchar(255) DEFAULT NULL,
  `SFZH` varchar(255) DEFAULT NULL,
  `SJHM` varchar(255) DEFAULT NULL,
  `YXTBH_SSXT` varchar(255) DEFAULT NULL,
  `XB` varchar(255) DEFAULT NULL,
  `XJZDZ` varchar(255) DEFAULT NULL,
  `XM` varchar(255) DEFAULT NULL,
  `YXTBH_ZCRID` varchar(255) DEFAULT NULL,
  `ZHXGSJ` varchar(255) DEFAULT NULL,
  `ZJCX` varchar(255) DEFAULT NULL,
  `ZLQYID` varchar(255) DEFAULT NULL,
  `BZK_ZZJG` varchar(255) DEFAULT NULL,
  `BZK_RKSJ` datetime DEFAULT NULL,
  `BZK_SCBZ` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_bz_xzqc_zlcljbxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_xzqc_zlcljbxx`;
CREATE TABLE `t_bz_xzqc_zlcljbxx` (
  `SYSTEMID` varchar(255) DEFAULT NULL,
  `BXDH` varchar(255) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `CJH` varchar(255) DEFAULT NULL,
  `CJSJ` datetime DEFAULT NULL,
  `CLBH` varchar(255) DEFAULT NULL,
  `CLPP` varchar(255) DEFAULT NULL,
  `CLXH` varchar(255) DEFAULT NULL,
  `CLZT` varchar(255) DEFAULT NULL,
  `CPHM` varchar(255) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT NULL,
  `CSFS` varchar(255) DEFAULT NULL,
  `CSYS` varchar(255) DEFAULT NULL,
  `CZLXDH` varchar(255) DEFAULT NULL,
  `CZSFZH` varchar(255) DEFAULT NULL,
  `CZSJH` varchar(255) DEFAULT NULL,
  `CZXB` varchar(255) DEFAULT NULL,
  `ETLDATE` datetime DEFAULT NULL,
  `FDJH` varchar(255) DEFAULT NULL,
  `GPS1` varchar(255) DEFAULT NULL,
  `GPS2` varchar(255) DEFAULT NULL,
  `GPSBH1` varchar(255) DEFAULT NULL,
  `GPSBH2` varchar(255) DEFAULT NULL,
  `HCK_GXSJ` datetime DEFAULT NULL,
  `HCK_RKSJ` datetime DEFAULT NULL,
  `OBJECTID` varchar(255) DEFAULT NULL,
  `RZJ` varchar(255) DEFAULT NULL,
  `SFZT` varchar(255) DEFAULT NULL,
  `YXTBH_SSXT` varchar(255) DEFAULT NULL,
  `ZHXGSJ` datetime DEFAULT NULL,
  `YXTBH_ZLCLID` varchar(255) DEFAULT NULL,
  `BZK_ZZJG` varchar(255) DEFAULT NULL,
  `BZK_RKSJ` datetime DEFAULT NULL,
  `BZK_GXSJ` datetime DEFAULT NULL,
  `ZYK_SSXTMC` varchar(255) DEFAULT NULL,
  `BZK_SCBZ` varchar(255) DEFAULT NULL,
  `CLLX` varchar(255) DEFAULT NULL,
  `CZXM` varchar(255) DEFAULT NULL,
  `ZLQYID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_bz_xzqc_zlxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_xzqc_zlxx`;
CREATE TABLE `t_bz_xzqc_zlxx` (
  `SYSTEMID` varchar(255) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `CJSJ` datetime DEFAULT NULL,
  `CLZLZT` varchar(255) DEFAULT NULL,
  `CPHM` varchar(255) DEFAULT NULL,
  `CREATEDATE` datetime DEFAULT NULL,
  `CZGLS` varchar(255) DEFAULT NULL,
  `ETLDATE` datetime DEFAULT NULL,
  `HCGLS` varchar(255) DEFAULT NULL,
  `HCK_GXSJ` datetime DEFAULT NULL,
  `HCK_RKSJ` datetime DEFAULT NULL,
  `HCSJ` datetime DEFAULT NULL,
  `HTBH` varchar(255) DEFAULT NULL,
  `JHHCSJ` varchar(255) DEFAULT NULL,
  `JHQX` varchar(255) DEFAULT NULL,
  `OBJECTID` varchar(255) DEFAULT NULL,
  `RZJ` varchar(255) DEFAULT NULL,
  `SJQX` varchar(255) DEFAULT NULL,
  `SLRXM` varchar(255) DEFAULT NULL,
  `SLSJ` datetime DEFAULT NULL,
  `YXTBH_SSXT` varchar(255) DEFAULT NULL,
  `ZCRID` varchar(255) DEFAULT NULL,
  `ZCRSFZH` varchar(255) DEFAULT NULL,
  `ZCSJ` varchar(255) DEFAULT NULL,
  `ZCYJ` varchar(255) DEFAULT NULL,
  `ZHXGSJ` varchar(255) DEFAULT NULL,
  `ZLCLID` varchar(255) DEFAULT NULL,
  `YXTBH_ZLID` varchar(255) DEFAULT NULL,
  `ZLQYID` varchar(255) DEFAULT NULL,
  `ZLQYMC` varchar(255) DEFAULT NULL,
  `BZK_ZZJG` varchar(255) DEFAULT NULL,
  `BZK_RKSJ` datetime DEFAULT NULL,
  `BZK_GXSJ` datetime DEFAULT NULL,
  `ZYK_SSXTMC` varchar(255) DEFAULT NULL,
  `BZK_SCBZ` varchar(255) DEFAULT NULL,
  `ZCRXM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
