/*
指令管理模块脚本
*/

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `t_zlgl_zl`;
CREATE TABLE `t_zlgl_zl` (
  `id` varchar(255) NOT NULL,
  `zlnr` longtext COMMENT '指令内容',
  `cjrbmid` varchar(255) DEFAULT NULL COMMENT '创建人单位id',
  `cjrid` varchar(255) DEFAULT NULL COMMENT '创建人id',
  `cjsj` datetime DEFAULT NULL COMMENT '创建时间',
  `sftzbdwfzr` int(11) DEFAULT NULL COMMENT '是否通知本单位负责人',
  `glztnr` varchar(255) DEFAULT NULL COMMENT '关联主题内容',
  `glztid` varchar(255) DEFAULT NULL COMMENT '关联主题id',
  `glztlx` varchar(255) DEFAULT NULL COMMENT '关联主题类型',
  `yqfksj` datetime DEFAULT NULL COMMENT '要求反馈时间',
  `zllx` varchar(255) DEFAULT NULL COMMENT '指令类型',
  `zllxxgnr` longtext COMMENT '指令类型相关的内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指令';


DROP TABLE IF EXISTS `t_zlgl_zljszt`;
CREATE TABLE `t_zlgl_zljszt` (
  `id` varchar(255) NOT NULL,
  `jsztid` varchar(255) DEFAULT NULL COMMENT '接收主体id',
  `jsztlx` varchar(255) DEFAULT NULL COMMENT '接收主体类型',
  `jssj` datetime DEFAULT NULL COMMENT '接收时间',
  `zt` varchar(255) DEFAULT NULL COMMENT '状态',
  `zl_id` varchar(255) DEFAULT NULL COMMENT '指令',
  PRIMARY KEY (`id`),
  KEY `FKE48EE2D2BB7BBA48` (`zl_id`),
  CONSTRAINT `FKE48EE2D2BB7BBA48` FOREIGN KEY (`zl_id`) REFERENCES `t_zlgl_zl` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指令接收主体';



DROP TABLE IF EXISTS `t_zlgl_zljsztfk`;
CREATE TABLE `t_zlgl_zljsztfk` (
  `id` varchar(255) NOT NULL,
  `fknr` longtext COMMENT '反馈内容',
  `fkrid` varchar(255) DEFAULT NULL COMMENT '反馈人id',
  `fkrxm` varchar(255) DEFAULT NULL COMMENT '反馈人姓名',
  `fksj` datetime DEFAULT NULL COMMENT '反馈时间',
  `ewfknr` varchar(255) DEFAULT NULL COMMENT '额外的反馈内容',
  `czdxid` varchar(255) DEFAULT NULL COMMENT '操作对象ID',
  `czdxlx` varchar(255) DEFAULT NULL COMMENT '操作对象类型',
  `zljszt_id` varchar(255) DEFAULT NULL COMMENT '指令接收主体',
  PRIMARY KEY (`id`),
  KEY `FKFC6183178CFC1B0E` (`zljszt_id`),
  CONSTRAINT `FKFC6183178CFC1B0E` FOREIGN KEY (`zljszt_id`) REFERENCES `t_zlgl_zljszt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指令接收主体反馈';



DROP TABLE IF EXISTS `t_zlgl_zljsztqs`;
CREATE TABLE `t_zlgl_zljsztqs` (
  `id` varchar(255) NOT NULL,
  `qsrid` varchar(255) DEFAULT NULL COMMENT '签收人id',
  `qsrxm` varchar(255) DEFAULT NULL COMMENT '签收人姓名',
  `qssj` datetime DEFAULT NULL COMMENT '签收时间',
  `zljszt_id` varchar(255) DEFAULT NULL COMMENT '指令接收主题',
  PRIMARY KEY (`id`),
  KEY `FKFC6184748CFC1B0E` (`zljszt_id`),
  CONSTRAINT `FKFC6184748CFC1B0E` FOREIGN KEY (`zljszt_id`) REFERENCES `t_zlgl_zljszt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指令接收主体签收';

-- 指令类型
INSERT INTO `t_zdlx` (`id`, `fl`, `bm`, `bz`, `mc`, `zt`, `sjc`) VALUES ('0000000011', NULL, 'zllx', NULL, '指令类型', '1', '2016-06-26 21:23:01');

-- 指令状态
INSERT INTO `t_zdlx` (`id`, `fl`, `bm`, `bz`, `mc`, `zt`, `sjc`) VALUES ('0000000012', null, 'zlzt', null, '指令状态', '1', '2016-06-26 21:23:01');

-- 指令类型字典项
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES
	('0000000011001', '0000000011001', '', '打击指令', 1, '1', '2016-06-26 21:23:01', '0000000011', NULL),
	('0000000011002', '0000000011002', '', '防控指令', 2, '1', '2016-06-26 21:23:01', '0000000011', NULL),
	('0000000011002001', '0000000011002001', '', '场所防控', 1, '1', '2016-06-26 21:23:01', '0000000011','0000000011002'),
	('0000000011002002', '0000000011002002', '', '处所防控', 2, '1', '2016-06-26 21:23:01', '0000000011','0000000011002'),
	('0000000011002003', '0000000011002003', '', '人员防控', 3, '1', '2016-06-26 21:23:01', '0000000011','0000000011002'),
	('0000000011002004', '0000000011002004', '', '物品防控', 4, '1', '2016-06-26 21:23:01', '0000000011','0000000011002'),
	('0000000011003', '0000000011003', '', '管控指令', 3, '1', '2016-06-26 21:23:01', '0000000011', NULL),
	('0000000011003001', '0000000011003001', '', '场所管控', 1, '1', '2016-06-26 21:23:01', '0000000011', '0000000011003'),
	('0000000011003002', '0000000011003002', '', '人员管控', 2, '1', '2016-06-26 21:23:01', '0000000011', '0000000011003'),
	('0000000011004', '0000000011004', '', '研判指令', 4, '1', '2016-06-26 21:23:01', '0000000011', NULL),
	('0000000011004001', '0000000011004001', '', '人员研判指令', '1', '1', '2016-06-26 21:23:01', '0000000011', '0000000011004'),
	('0000000011004002', '0000000011004002', '', '案件研判指令', '2', '1', '2016-06-26 21:23:01', '0000000011', '0000000011004'),
	('0000000011004003', '0000000011004003', '', '其他研判指令', '3', '1', '2016-06-26 21:23:01', '0000000011', '0000000011004'),
	('0000000011005', '0000000011005', '', '落地指令', 5, '1', '2016-06-26 21:23:01', '0000000011', NULL),
	('0000000011005001', '0000000011005001', '', '人员盘查', 1, '1', '2016-06-26 21:23:01', '0000000011', '0000000011005'),
	('0000000011005002', '0000000011005002', '', '车辆盘查', 2, '1', '2016-06-26 21:23:01', '0000000011', '0000000011005'),
	('0000000011006', '0000000011006', '', '情报核实指令', 5, '1', '2016-06-26 21:23:01', '0000000011', NULL),
	('0000000011007', '0000000011007', '', '研判结果推送指令', 5, '1', '2016-06-26 21:23:01', '0000000011', NULL);
	
-- 指令状态字典项
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES
	('0000000012001', '0000000012001', '', '待签收', '1', '1', '2016-06-26 21:23:01', '0000000012', null),
    ('0000000012002', '0000000012002', '', '已签收', '2', '1', '2016-06-26 21:23:01', '0000000012', null),
    ('0000000012003', '0000000012003', '', '已反馈', '3', '1', '2016-06-26 21:23:01', '0000000012', null);
	
