/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_jds

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:37:42
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_jds`;
CREATE Database `source_jds`;
use source_jds;
-- ----------------------------
-- Table structure for jds_jbxx
-- ----------------------------
DROP TABLE IF EXISTS `jds_jbxx`;
CREATE TABLE `jds_jbxx` (
  `JDSBM` varchar(255) NOT NULL COMMENT '戒毒所编码',
  `JDSMC` varchar(255) DEFAULT NULL COMMENT '戒毒所名称',
  `DZ` varchar(255) DEFAULT NULL,
  `XXDZ` varchar(500) DEFAULT NULL COMMENT '详细地址',
  `DH` varchar(255) DEFAULT NULL COMMENT '电话',
  `CZ` varchar(255) DEFAULT NULL COMMENT '传真',
  `DZYX` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `WZ` varchar(255) DEFAULT NULL,
  `FZR` varchar(255) DEFAULT NULL COMMENT '负责人',
  `MJZS` varchar(255) DEFAULT NULL,
  `BZRS` varchar(255) DEFAULT NULL,
  `SJRL` varchar(255) DEFAULT NULL,
  `LRMJ_XM` varchar(255) DEFAULT NULL,
  `GZRY_BM` varchar(255) DEFAULT NULL,
  `HKURL` varchar(255) DEFAULT NULL,
  `HCK_RKSJ` datetime DEFAULT NULL,
  `HCK_GXSJ` datetime DEFAULT NULL,
  PRIMARY KEY (`JDSBM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_bz_jds_jdry
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_jds_jdry`;
CREATE TABLE `t_bz_jds_jdry` (
  `SYSTEMID` varchar(50) NOT NULL COMMENT '系统主键',
  `AY` varchar(3000) DEFAULT NULL COMMENT '案由',
  `AY_ZW` varchar(3000) DEFAULT NULL COMMENT '案由',
  `BADW` varchar(150) DEFAULT NULL COMMENT '办案单位',
  `BARY` varchar(75) DEFAULT NULL COMMENT '办案人员',
  `BM` varchar(300) DEFAULT NULL COMMENT '别名',
  `CCCHRQ` date DEFAULT NULL COMMENT '初次查获日期',
  `CCXDRQ` date DEFAULT NULL COMMENT '初次吸毒日期',
  `CHCS` int(11) DEFAULT NULL COMMENT '查获次数',
  `CSRQ` date DEFAULT NULL COMMENT '出生日期',
  `DABH` varchar(75) DEFAULT NULL COMMENT '档案编号',
  `DNABH` varchar(75) DEFAULT NULL COMMENT 'NDA编号',
  `DPLY` varchar(75) DEFAULT NULL COMMENT '毒品来源',
  `DPLY_ZW` varchar(255) DEFAULT NULL COMMENT '毒品来源',
  `DZLY` varchar(75) DEFAULT NULL COMMENT '毒资来源',
  `DZLY_ZW` varchar(255) DEFAULT NULL COMMENT '毒资来源',
  `GJ` varchar(75) DEFAULT NULL COMMENT '国籍',
  `GJ_ZW` varchar(255) DEFAULT NULL COMMENT '国籍',
  `GJ_GABZ` varchar(3) DEFAULT NULL COMMENT '国籍_公安标准',
  `GZDW` varchar(150) DEFAULT NULL COMMENT '工作单位',
  `GZRY_BM` varchar(75) DEFAULT NULL COMMENT '录入工作人员编码',
  `HCK_GXSJ` datetime DEFAULT NULL COMMENT '更新时间',
  `HCK_RKSJ` datetime DEFAULT NULL COMMENT '入库时间',
  `HJSZD` varchar(75) DEFAULT NULL COMMENT '户籍所在地',
  `HJSZD_ZW` varchar(255) DEFAULT NULL COMMENT '户籍所在地',
  `HJSZD_GABZ` varchar(6) DEFAULT NULL COMMENT '户籍所在地_公安标准',
  `HJSZDXXDZ` varchar(300) DEFAULT NULL COMMENT '户籍所在地详细地址',
  `HYZK` varchar(50) DEFAULT NULL COMMENT '婚姻状况',
  `HYZK_ZW` varchar(255) DEFAULT NULL COMMENT '婚姻状况',
  `HYZK_GABZ` varchar(2) DEFAULT NULL COMMENT '婚姻状况_公安标准',
  `YXTBH` int(11) DEFAULT NULL COMMENT 'ID',
  `ISFK` varchar(1) DEFAULT NULL COMMENT '0代表正常1代表是',
  `JDCS` int(11) DEFAULT NULL COMMENT '戒毒次数',
  `JDRY_BM` varchar(75) DEFAULT NULL COMMENT '强制戒毒人员编码',
  `JDSH` varchar(75) DEFAULT NULL COMMENT '戒毒室号',
  `JDS_BM` varchar(75) DEFAULT NULL COMMENT '戒毒所编码',
  `JDS_BM_ZW` varchar(255) DEFAULT NULL COMMENT '戒毒所编码',
  `JDS_BM_GABZ` varchar(12) DEFAULT NULL COMMENT '戒毒所编码_公安标准',
  `JG` varchar(75) DEFAULT NULL COMMENT '籍贯',
  `JG_ZW` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `JG_GABZ` varchar(6) DEFAULT NULL COMMENT '籍贯_公安标准',
  `JLJSRQ` date DEFAULT NULL COMMENT '拘留结束日期',
  `JLTS` int(11) DEFAULT NULL COMMENT '拘留天数',
  `KYTD` varchar(75) DEFAULT NULL COMMENT '口音特点',
  `LRMJ_XM` varchar(75) DEFAULT NULL COMMENT '经办民警',
  `LXDH` varchar(75) DEFAULT NULL COMMENT '联系电话',
  `MZ` varchar(75) DEFAULT NULL COMMENT '民族',
  `MZ_ZW` varchar(255) DEFAULT NULL COMMENT '民族',
  `MZ_GABZ` varchar(2) DEFAULT NULL COMMENT '民族_公安标准',
  `NL` int(11) DEFAULT NULL COMMENT '年龄',
  `QQH` varchar(150) DEFAULT NULL COMMENT 'QQ号',
  `QTDBXCL` varchar(150) DEFAULT NULL COMMENT '其他的并行处理',
  `QZGLJDJDSH` varchar(75) DEFAULT NULL COMMENT '强制隔离戒毒决定书号',
  `QZJDQX_JZ` date DEFAULT NULL COMMENT '强制隔离期限起止日期（止）',
  `QZJDQX_QS` date DEFAULT NULL COMMENT '强制隔离期限起止日期（起）',
  `RSLB` varchar(75) DEFAULT NULL COMMENT '入所类别',
  `RSLB_ZW` varchar(255) DEFAULT NULL COMMENT '入所类别',
  `RSLJJG` varchar(300) DEFAULT NULL COMMENT '入所尿检结果',
  `RSRQ` date DEFAULT NULL COMMENT '入所日期',
  `RSXM` varchar(75) DEFAULT NULL COMMENT '入所姓名',
  `RY_BJ` int(11) DEFAULT NULL COMMENT '人员标记',
  `RY_BJ_ZW` varchar(255) DEFAULT NULL COMMENT '人员标记',
  `SBFHM` varchar(150) DEFAULT NULL COMMENT '识别服号码',
  `SF` varchar(75) DEFAULT NULL COMMENT '身份',
  `SF_ZW` varchar(255) DEFAULT NULL COMMENT '身份',
  `SG` int(11) DEFAULT NULL COMMENT '身高（厘米）',
  `SHR` varchar(75) DEFAULT NULL COMMENT '审核人',
  `SJDW` varchar(150) DEFAULT NULL COMMENT '送戒单位',
  `SJDW_ZW` varchar(255) DEFAULT NULL COMMENT '送戒单位',
  `SJRLXDH` varchar(75) DEFAULT NULL COMMENT '送戒人联系电话',
  `SXPCS` varchar(300) DEFAULT NULL COMMENT '所辖派出所',
  `TBR` varchar(75) DEFAULT NULL COMMENT '填表人',
  `TBRQ` date DEFAULT NULL COMMENT '填表日期',
  `TSSF` varchar(75) DEFAULT NULL COMMENT '特殊身份',
  `TSSF_ZW` varchar(255) DEFAULT NULL COMMENT '特殊身份',
  `WHCD` varchar(75) DEFAULT NULL COMMENT '文化程度',
  `WHCD_ZW` varchar(255) DEFAULT NULL COMMENT '文化程度',
  `WHCD_GABZ` varchar(20) DEFAULT NULL COMMENT '文化程度_公安标准',
  `WSC_BZ` int(11) DEFAULT NULL COMMENT '删除标记',
  `XB` varchar(75) DEFAULT NULL COMMENT '性别',
  `XB_ZW` varchar(255) DEFAULT NULL COMMENT '性别',
  `XB_GABZ` varchar(1) DEFAULT NULL COMMENT '性别_公安标准',
  `XDFS` varchar(75) DEFAULT NULL COMMENT '吸毒方式',
  `XDFS_ZW` varchar(255) DEFAULT NULL COMMENT '吸毒方式',
  `XDRYLY` varchar(150) DEFAULT NULL COMMENT '吸毒人员来源',
  `XDYY` varchar(75) DEFAULT NULL COMMENT '吸毒原因',
  `XDYY_ZW` varchar(255) DEFAULT NULL COMMENT '吸毒原因',
  `XJZD` varchar(300) DEFAULT NULL COMMENT '现居住地',
  `XJZDXXDZ` varchar(300) DEFAULT NULL COMMENT '现居住地详细地址',
  `XSDPZL` varchar(75) DEFAULT NULL COMMENT '吸食毒品种类',
  `XSDPZL_ZW` varchar(255) DEFAULT NULL COMMENT '吸食毒品种类',
  `XXSFYHC` varchar(75) DEFAULT NULL COMMENT '信息是否已核查',
  `XZCFFLWSH` varchar(75) DEFAULT NULL COMMENT '行政处罚法律文书号',
  `XZCFQZRQ_KS` date DEFAULT NULL COMMENT '行政处罚起止日期_开始',
  `YJ` varchar(150) DEFAULT NULL COMMENT '邮件',
  `YXZH` varchar(150) DEFAULT NULL COMMENT '游戏帐号',
  `ZJHM` varchar(150) DEFAULT NULL COMMENT '证件号码',
  `ZJMC` varchar(50) DEFAULT NULL COMMENT '证件名称',
  `ZJMC_ZW` varchar(255) DEFAULT NULL COMMENT '证件名称',
  `ZRDW` varchar(300) DEFAULT NULL COMMENT '转入单位',
  `ZY` varchar(75) DEFAULT NULL COMMENT '职业',
  `ZY_ZW` varchar(255) DEFAULT NULL COMMENT '职业',
  `ZYJDSJR` varchar(75) DEFAULT NULL COMMENT '自愿戒毒送戒人',
  `ZZMM` varchar(75) DEFAULT NULL COMMENT '政治面貌',
  `ZZMM_ZW` varchar(255) DEFAULT NULL COMMENT '政治面貌',
  `ZZWBH` varchar(75) DEFAULT NULL COMMENT '指掌纹编号',
  `BZK_ZZJG` varchar(12) DEFAULT NULL COMMENT '标准库组织机构',
  `BZK_RKSJ` datetime DEFAULT NULL COMMENT '标准库入库时间',
  `BZK_GXSJ` datetime DEFAULT NULL COMMENT '标准库更新时间',
  `ZYK_SSXTMC` varchar(100) DEFAULT NULL COMMENT '所属系统名称',
  `BZK_SCBZ` varchar(1) DEFAULT NULL COMMENT '标准库删除标识',
  PRIMARY KEY (`SYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
