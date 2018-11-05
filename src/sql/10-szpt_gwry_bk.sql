/*
高危人布控功能脚本
*/

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `t_gwry_rybk`;
CREATE TABLE `t_gwry_rybk` (
  `id` varchar(36) NOT NULL,
  `bkjssj` datetime DEFAULT NULL COMMENT '布控结束时间',
  `sfzh` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `zxxgrxm` varchar(255) DEFAULT NULL COMMENT '最新修改人姓名',
  `zxxgsj` datetime DEFAULT NULL COMMENT '最新修改时间',
  `bz` varchar(255) DEFAULT NULL COMMENT '备注',
  `bh` varchar(255) DEFAULT NULL COMMENT '编号',
  `czzt` varchar(255) DEFAULT NULL COMMENT '操作状态：0新增1待审批2审批通过3审批驳回',
  `xm` varchar(255) DEFAULT NULL COMMENT '姓名',
  `hjdz` varchar(255) DEFAULT NULL COMMENT '户籍地址',
  `xb` varchar(255) DEFAULT NULL COMMENT '性别',
  `bkkssj` datetime DEFAULT NULL COMMENT '布控开始时间',
  `sjzt` varchar(255) DEFAULT NULL COMMENT '数据字典：启用；停用',
  `cjr_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc1r2r06nds854yi5n42c7c8kq` (`cjr_id`),
  CONSTRAINT `FKc1r2r06nds854yi5n42c7c8kq` FOREIGN KEY (`cjr_id`) REFERENCES `t_og_ry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员布控记录';



DROP TABLE IF EXISTS `t_gwry_rybk_bkzp`;
CREATE TABLE `t_gwry_rybk_bkzp` (
  `id` varchar(255) NOT NULL,
  `ywfj_id` varchar(255) DEFAULT NULL COMMENT '关联的实战平台通用附件的id',
  `fjy_id` varchar(255) DEFAULT NULL COMMENT 't_attachmentmeta的id',
  `rybk_id` varchar(255) DEFAULT NULL COMMENT '人员布控id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fjy_id` (`fjy_id`),
  KEY `FKDE27FB71A04C425C` (`rybk_id`),
  KEY `FKDE27FB714C20AAE` (`fjy_id`),
  CONSTRAINT `FKDE27FB714C20AAE` FOREIGN KEY (`fjy_id`) REFERENCES `t_attachmentmeta` (`id`),
  CONSTRAINT `FKDE27FB71A04C425C` FOREIGN KEY (`rybk_id`) REFERENCES `t_gwry_rybk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员布控照片'; 



DROP TABLE IF EXISTS `t_gwry_rybkyddxx`;
CREATE TABLE `t_gwry_rybkyddxx` (
  `id` varchar(36) NOT NULL,
  `mac` varchar(255) DEFAULT NULL COMMENT 'MAC地址',
  `sjhm` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `rybk_id` varchar(255) DEFAULT NULL COMMENT '人员布控id',
  PRIMARY KEY (`id`),
  KEY `FKDF972CCCA04C425C` (`rybk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员布控移动端信息'; 
