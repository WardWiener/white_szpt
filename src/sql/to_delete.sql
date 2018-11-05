/* 大数据替代                                           */
DROP TABLE IF EXISTS `t_gwry_zsjl`;
/*
CREATE TABLE `t_gwry_zsjl` (
  `id` varchar(36) NOT NULL,
  `rysfzh` varchar(255) DEFAULT NULL comment '人员身份证号',
  `rymc` varchar(255) DEFAULT NULL comment '人员名称',
  `hjdz` varchar(255) DEFAULT NULL comment '户籍地址',
  `rzsj` datetime DEFAULT NULL comment '入住时间',
  `lgjdbm` varchar(255) DEFAULT NULL comment '旅馆酒店编码',
  `lgjdmc` varchar(255) DEFAULT NULL comment '旅馆酒店名称',
  `tfsj` datetime DEFAULT NULL comment '退房时间',
  `fjh` varchar(255) DEFAULT NULL comment '房间号',
  `rzts` int(11) DEFAULT NULL comment '入住天数',
  `gxsj` datetime DEFAULT NULL comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='住宿记录';
 */


/* 大数据替代                                           */
DROP TABLE IF EXISTS `t_gwry_hc`;
/*
CREATE TABLE `t_gwry_hc` (
  `id` varchar(36) NOT NULL,
  `wd` varchar(255) DEFAULT NULL comment '纬度',
  `jd` varchar(255) DEFAULT NULL comment '经度',
  `rysfzh` varchar(255) DEFAULT NULL comment '人员身份证号',
  `rymc` varchar(255) DEFAULT NULL comment '人员名称',
  `hjdz` varchar(255) DEFAULT NULL comment '户籍地址',
  `mddwd` varchar(255) DEFAULT NULL comment '目的地纬度',
  `zdz` varchar(255) DEFAULT NULL comment '终点站',
  `mddjd` varchar(255) DEFAULT NULL comment '目的地经度',
  `sfz` varchar(255) DEFAULT NULL comment '始发站',
  `dzsj` datetime DEFAULT NULL comment '到站时间',
  `zwh` varchar(255) DEFAULT NULL comment '座位号',
  `fcsj` datetime DEFAULT NULL comment '发车时间',
  `cc` varchar(255) DEFAULT NULL comment '车次',
  `gxsj` datetime DEFAULT NULL comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='火车订票信息';
 */


DROP TABLE IF EXISTS `t_gwry_swjl`;
/*
CREATE TABLE `t_gwry_swjl` (
  `id` varchar(255) NOT NULL comment 'id',
  `rysfzh` varchar(255) DEFAULT NULL comment '人员身份证号',
  `rymc` varchar(255) DEFAULT NULL comment '人员名称',
  `hjdz` varchar(255) DEFAULT NULL comment '户籍地址',
  `wbbm` varchar(255) DEFAULT NULL comment '网吧编码',
  `wbmc` varchar(255) DEFAULT NULL comment '网吧名称',
  `xwsj` datetime DEFAULT NULL comment '下网时间',
  `swsj` datetime DEFAULT NULL comment '上网时间',
  `zdh` varchar(255) DEFAULT NULL comment '上网终端号',
  `gxsj` datetime DEFAULT NULL comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上网记录';
 */


DROP TABLE IF EXISTS `t_gwry_fj`;
/*
CREATE TABLE `t_gwry_fj` (
  `id` varchar(36) NOT NULL,
  `wd` varchar(255) DEFAULT NULL comment '纬度',
  `jd` varchar(255) DEFAULT NULL comment '经度',
  `rysfzh` varchar(255) DEFAULT NULL comment '人员身份证号',
  `rymc` varchar(255) DEFAULT NULL comment '人员名称',
  `hjdz` varchar(255) DEFAULT NULL comment '户籍地址',
  `mddwd` varchar(255) DEFAULT NULL comment '目的地纬度',
  `dgdd` varchar(255) DEFAULT NULL comment '到港地点',
  `mddjd` varchar(255) DEFAULT NULL comment '目的地经度',
  `hbh` varchar(255) DEFAULT NULL comment '航班号',
  `lgdd` varchar(255) DEFAULT NULL comment '离港地点',
  `dgsj` datetime DEFAULT NULL comment '到港时间',
  `zwh` varchar(255) DEFAULT NULL comment '座位号',
  `lgsj` datetime DEFAULT NULL comment '离港时间',
  `gxsj` datetime DEFAULT NULL comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='飞机出入港信息';
 */
