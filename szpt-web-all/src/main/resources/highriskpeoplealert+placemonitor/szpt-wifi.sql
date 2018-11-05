/*
Navicat MySQL Data Transfer

Source Server         : connect1
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : unit_test

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2016-11-30 11:10:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_csjk_csjcxx
-- ----------------------------
DROP TABLE IF EXISTS `t_csjk_wificsjcxx`;
CREATE TABLE `t_csjk_csjcxx` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `yyjssj` datetime DEFAULT NULL COMMENT '营业结束时间',
  `csjyfr` varchar(255) DEFAULT NULL COMMENT '场所经营法人',
  `yykssj` datetime DEFAULT NULL COMMENT '营业开始时间',
  `lxfs` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `csxxdz` varchar(255) DEFAULT NULL COMMENT '场所详细地址',
  `swfwcsbm` varchar(255) DEFAULT NULL COMMENT '上网服务场所编码',
  `swfwcsmc` varchar(255) DEFAULT NULL COMMENT '上网服务场所名称',
  `cswd` varchar(255) DEFAULT NULL COMMENT '场所纬度',
  `csjd` varchar(255) DEFAULT NULL COMMENT '场所经度',
  `csjyxz` varchar(255) DEFAULT NULL COMMENT '场所经营性质',
  `csfwlx` varchar(255) DEFAULT NULL COMMENT '场所服务类型',
  `csjyfryxzjhm` varchar(255) DEFAULT NULL COMMENT '场所经营法人有效证件号码 ',
  `csjyfryxzjlx` varchar(255) DEFAULT NULL COMMENT '场所经营法人有效证件类型',
  `cszzjgdm` varchar(255) DEFAULT NULL COMMENT '厂商组织机构代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wifi场所基础消息';

-- ----------------------------
-- Table structure for t_csjk_csjkrs
-- ----------------------------
DROP TABLE IF EXISTS `t_csjk_csjkrs`;
CREATE TABLE `t_csjk_csjkrs` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `qypcs` varchar(255) DEFAULT NULL COMMENT '场所所属区域派出所',
  `sfjzxq` int(11) DEFAULT NULL COMMENT '是否居住小区,sfjzxq 0表示不是1表示是',
  `csrs` int(11) DEFAULT NULL COMMENT '当前监控场所人数',
  `ljcsrs` int(11) DEFAULT NULL COMMENT '场所累计监控人数',
  `cs_id` varchar(255) DEFAULT NULL COMMENT '监控场所',
  PRIMARY KEY (`id`),
  KEY `FK60141AB58BE4EBD6` (`cs_id`),
  CONSTRAINT `FK60141AB58BE4EBD6` FOREIGN KEY (`cs_id`) REFERENCES `t_csjk_csjcxx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场所监控人数';

-- ----------------------------
-- Table structure for t_csjk_wifi_cscrxx
-- ----------------------------
DROP TABLE IF EXISTS `t_csjk_wifi_cscrxx`;
CREATE TABLE `t_csjk_wifi_cscrxx` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `jrsj` datetime DEFAULT NULL COMMENT '进入时间',
  `sfzh` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `cswd` varchar(255) DEFAULT NULL COMMENT '纬度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示北纬（正符号省略），负符号表示南纬。例如123.23000 表示北纬123.2300度；-133.00000表示南纬133.00000度',
  `lksj` datetime DEFAULT NULL COMMENT '离开时间',
  `csjd` varchar(255) DEFAULT NULL COMMENT '经度，采用“正负+十进制度数”的格式表示。度数采用3位整数5位小数形式,小数位数不足补零，方位采用正负符号形式，使用正符号表示东经（正符号省略），负符号表示西经。例如123.23000 表示东经123.23000度；-133.00000表示西经133.00000度',
  `mac` varchar(17) DEFAULT NULL COMMENT 'mac地址',
  `rymc` varchar(50) DEFAULT NULL COMMENT '单个人员名称',
  `csmc` varchar(255) DEFAULT NULL COMMENT '场所名称',
  `zlsc` int(11) DEFAULT NULL COMMENT '驻留时间，秒',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='wifi场所出入信息';

-- ----------------------------
-- Table structure for t_csjk_wifi_mac_gj_rylx
-- ----------------------------
DROP TABLE IF EXISTS `t_csjk_wifi_mac_gj_rylx`;
CREATE TABLE `t_csjk_wifi_mac_gj_rylx` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `bq` varchar(255) DEFAULT NULL COMMENT '标签',
  `gxsj` datetime DEFAULT NULL COMMENT '更新时间',
  `wfcscrxx_id` varchar(255) DEFAULT NULL COMMENT 'WiFi命中信息',
  PRIMARY KEY (`id`),
  KEY `FKF586DA185ECDF1F1` (`wfcscrxx_id`),
  CONSTRAINT `FKF586DA185ECDF1F1` FOREIGN KEY (`wfcscrxx_id`) REFERENCES `t_csjk_wifi_cscrxx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='重点人标签';

-- ----------------------------
-- Table structure for t_csjk_wsyjryqklx
-- ----------------------------
DROP TABLE IF EXISTS `t_csjk_wsyjryqklx`;
CREATE TABLE `t_csjk_wsyjryqklx` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `qklx` varchar(255) DEFAULT NULL COMMENT '前科类型',
  `gxsj` datetime DEFAULT NULL COMMENT '更新时间',
  `rylx_id` varchar(255) DEFAULT NULL COMMENT '人员类型',
  PRIMARY KEY (`id`),
  KEY `FKA5FF7E9D3B834087` (`rylx_id`),
  CONSTRAINT `FKA5FF7E9D3B834087` FOREIGN KEY (`rylx_id`) REFERENCES `t_csjk_wifi_mac_gj_rylx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='重点人前科类型';

-- ----------------------------
-- Table structure for t_csjk_zdtzcjsbjcxx
-- ----------------------------
DROP TABLE IF EXISTS `t_csjk_zdtzcjsbjcxx`;
CREATE TABLE `t_csjk_zdtzcjsbjcxx` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `cjsbwd` varchar(255) DEFAULT NULL COMMENT '采集设备纬度',
  `cjsbjd` varchar(255) DEFAULT NULL COMMENT '采集设备经度',
  `cjsbmc` varchar(255) DEFAULT NULL COMMENT '采集设备名称',
  `cjsbbh` varchar(255) DEFAULT NULL COMMENT '采集设备编号',
  `cjsblx` varchar(255) DEFAULT NULL COMMENT '采集设备类型',
  `cjbj` varchar(255) DEFAULT NULL COMMENT '采集半径',
  `sbdz` varchar(255) DEFAULT NULL COMMENT '设备地址',
  `cphm` varchar(255) DEFAULT NULL COMMENT '车牌号码',
  `dtclxx` varchar(255) DEFAULT NULL COMMENT '地铁车辆信息',
  `csbh` varchar(255) DEFAULT NULL COMMENT '场所编号',
  `aqcszzjgdm` varchar(255) DEFAULT NULL COMMENT '安全厂商组织机构代码',
  `dtcxbh` varchar(255) DEFAULT NULL COMMENT '地铁车厢编号',
  `dtxlxx` varchar(255) DEFAULT NULL COMMENT '地铁线路信息',
  `scsjjgsj` varchar(255) DEFAULT NULL COMMENT '上传数据间隔时间',
  `cs_id` varchar(255) DEFAULT NULL COMMENT '监控场所',
  PRIMARY KEY (`id`),
  KEY `FKD3DB17E28BE4EBD6` (`cs_id`),
  CONSTRAINT `FKD3DB17E28BE4EBD6` FOREIGN KEY (`cs_id`) REFERENCES `t_csjk_csjcxx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='终端特征采集设备基础信息';
