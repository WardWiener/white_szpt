/*
平台基础模块脚本
*/

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `t_ptjc_fbgz`;
CREATE TABLE `t_ptjc_fbgz` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `jqlb` varchar(255) DEFAULT NULL COMMENT '警情类别(字典项)',
  `ys` varchar(255) DEFAULT NULL COMMENT '颜色(字典项 蓝/黄/橙/红)',
  `fw` varchar(255) DEFAULT NULL COMMENT '范围',
  `mbbm` varchar(255) DEFAULT NULL COMMENT '目标编码',
  `mbid` varchar(255) DEFAULT NULL COMMENT '目标id',
  `mbmc` varchar(255) DEFAULT NULL COMMENT '目标名称',
  `mblx` varchar(255) DEFAULT NULL COMMENT '目标类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分布规则';


DROP TABLE IF EXISTS `t_ptjc_xscl`;
CREATE TABLE `t_ptjc_xscl` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `ys` varchar(255) DEFAULT NULL COMMENT '颜色(字典项 蓝/黄/橙/红)',
  `fw` varchar(255) DEFAULT NULL COMMENT '范围',
  `sjlx` varchar(255) DEFAULT NULL COMMENT '时间类型(字典项  日/周/月/年)',
  `dwbm` varchar(255) DEFAULT NULL COMMENT '单位编码',
  `dwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刑事常量';
