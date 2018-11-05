/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_zdry

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:53:42
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_zdry`;
CREATE Database `source_zdry`;
use source_zdry;
-- ----------------------------
-- Table structure for t_bz_qb_zdryjcxx
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_qb_zdryjcxx`;
CREATE TABLE `t_bz_qb_zdryjcxx` (
  `systemid` varchar(50) NOT NULL COMMENT '系统主键',
  `csrq` datetime DEFAULT NULL COMMENT '出生日期',
  `gj` varchar(5) DEFAULT NULL COMMENT '国籍',
  `gj_gabz` varchar(3) DEFAULT NULL COMMENT '国籍_公安标准',
  `gxdw` varchar(105) DEFAULT NULL COMMENT '管辖单位',
  `gxdwjgdm` varchar(18) DEFAULT NULL COMMENT '管辖单位机构代码',
  `gxdwjgdm_gabz` varchar(12) DEFAULT NULL COMMENT '管辖单位机构代码_公安标准',
  `hck_gxsj` datetime DEFAULT NULL COMMENT '更新时间',
  `hck_rksj` datetime DEFAULT NULL COMMENT '入库时间',
  `hjdfjjgdm` varchar(18) DEFAULT NULL COMMENT '户籍地分县局机构代码',
  `hjdfjjgdm_gabz` varchar(12) DEFAULT NULL COMMENT '户籍地分县局机构代码_公安标准',
  `hjdpcs` varchar(105) DEFAULT NULL COMMENT '户籍地派出所',
  `hjdpcsdm` varchar(18) DEFAULT NULL COMMENT '户籍地派出所机构代码',
  `hjdpcsdm_gabz` varchar(12) DEFAULT NULL COMMENT '户籍地派出所机构代码_公安标准',
  `hjdqh` varchar(9) DEFAULT NULL COMMENT '户籍地省市县(区)',
  `hjdqh_gabz` varchar(6) DEFAULT NULL COMMENT '户籍地省市县(区)_公安标准',
  `hjdsjjgdm` varchar(18) DEFAULT NULL COMMENT '户籍地市局机构代码',
  `hjdsjjgdm_gabz` varchar(12) DEFAULT NULL COMMENT '户籍地市局机构代码_公安标准',
  `hjdstjgdm` varchar(18) DEFAULT NULL COMMENT '户籍地省厅机构代码',
  `hjdstjgdm_gabz` varchar(12) DEFAULT NULL COMMENT '户籍地省厅机构代码_公安标准',
  `hjdxz` varchar(150) DEFAULT NULL COMMENT '户籍地详址',
  `jg` varchar(9) DEFAULT NULL COMMENT '籍贯',
  `jlbgsj` datetime DEFAULT NULL COMMENT '记录变更时间',
  `jlcxsj` datetime DEFAULT NULL COMMENT '记录撤销时间',
  `jlxzsj` datetime DEFAULT NULL COMMENT '记录新增时间',
  `ladw` varchar(105) DEFAULT NULL COMMENT '立案单位',
  `ladwjgdm` varchar(18) DEFAULT NULL COMMENT '立案单位机构代码',
  `ladwjgdm_gabz` varchar(12) DEFAULT NULL COMMENT '立案单位机构代码_公安标准',
  `mz` varchar(3) DEFAULT NULL COMMENT '民族',
  `mz_gabz` varchar(10) DEFAULT NULL COMMENT '民族_公安标准',
  `nrbjzdryksj` datetime DEFAULT NULL COMMENT '纳入部级重点人员库时间',
  `qtzjhm` varchar(45) DEFAULT NULL COMMENT '其它证件号码',
  `sfzh` varchar(27) DEFAULT NULL COMMENT '公民身份号码',
  `wwxm` varchar(120) DEFAULT NULL COMMENT '外文姓名',
  `xb` varchar(2) DEFAULT NULL COMMENT '性别',
  `xb_gabz` varchar(1) DEFAULT NULL COMMENT '性别_公安标准',
  `xm` varchar(45) DEFAULT NULL COMMENT '姓名',
  `xmpy` varchar(135) DEFAULT NULL COMMENT '姓名拼音',
  `xzdpcs` varchar(105) DEFAULT NULL COMMENT '现住址派出所',
  `xzdpcsdm` varchar(18) DEFAULT NULL COMMENT '现住址派出所机构代码',
  `xzdpcsdm_gabz` varchar(12) DEFAULT NULL COMMENT '现住址派出所机构代码_公安标准',
  `xzdqh` varchar(9) DEFAULT NULL COMMENT '现住地省市县(区)',
  `xzdqh_gabz` varchar(6) DEFAULT NULL COMMENT '现住地省市县(区)_公安标准',
  `xzdxz` varchar(150) DEFAULT NULL COMMENT '现住地详址',
  `yxx` varchar(2) DEFAULT NULL COMMENT '有效性',
  `yxtbh` varchar(27) DEFAULT NULL COMMENT '部级重点人员编号',
  `zdryjklx` varchar(2) DEFAULT NULL COMMENT '重点人员监控类型。适应贵州省厅的要求添加。目前定义为0:动向未掌控人员;1',
  `zdrylbbj` varchar(48) DEFAULT NULL COMMENT '重点人员类别标记',
  `zdryxl` varchar(195) DEFAULT NULL,
  `zjlasj` datetime DEFAULT NULL COMMENT '最近立案时间',
  `bzk_zzjg` varchar(12) DEFAULT NULL COMMENT '标准库组织机构',
  `bzk_rksj` datetime DEFAULT NULL COMMENT '标准库入库时间',
  `bzk_gxsj` datetime DEFAULT NULL COMMENT '标准库更新时间',
  `zyk_ssxtmc` varchar(100) DEFAULT NULL COMMENT '所属系统名称',
  `bzk_scbz` varchar(1) DEFAULT NULL COMMENT '标准库删除标识',
  `yxx_gabz` varchar(2) DEFAULT NULL COMMENT '有效性_公安标准',
  PRIMARY KEY (`systemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
