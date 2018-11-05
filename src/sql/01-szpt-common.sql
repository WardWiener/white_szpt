/*
公共组件模块脚本
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_ywfj`;
CREATE TABLE `t_ywfj` (
  `id` varchar(255) NOT NULL,
  `mbid` varchar(255) DEFAULT NULL,
  `lx` varchar(255) DEFAULT NULL,
  `fjy_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fjy_id` (`fjy_id`),
  KEY `FKCB65AD4D4C20AAE` (`fjy_id`),
  CONSTRAINT `FKCB65AD4D4C20AAE` FOREIGN KEY (`fjy_id`) REFERENCES `t_attachmentmeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_attachmentcopy`;
CREATE TABLE IF NOT EXISTS `t_attachmentcopy` (
  `id` varchar(255) NOT NULL,
  `createTime` date NOT NULL,
  `type` longtext NOT NULL,
  `attachmentMetaId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAE43B6438C35F60C` (`attachmentMetaId`),
  CONSTRAINT `FKAE43B6438C35F60C` FOREIGN KEY (`attachmentMetaId`) REFERENCES `t_attachmentmeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件代理表';


DROP TABLE IF EXISTS `t_attachmentmeta`;
CREATE TABLE IF NOT EXISTS `t_attachmentmeta` (
  `id` varchar(255) NOT NULL,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件元数据表';


DROP TABLE IF EXISTS `t_dbattachmentimpl`;
CREATE TABLE IF NOT EXISTS `t_dbattachmentimpl` (
  `id` varchar(255) NOT NULL,
  `attachment` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件数据库实现表';


DROP TABLE IF EXISTS `t_czjl`;
CREATE TABLE `t_czjl` (
  `id` varchar(255) NOT NULL,
  `cznr` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `czsj` datetime DEFAULT NULL COMMENT '操作时间',
  `czdw` varchar(255) DEFAULT NULL COMMENT '操作单位名称',
  `czr` varchar(255) DEFAULT NULL COMMENT '操作人姓名',
  `czjg` varchar(255) DEFAULT NULL COMMENT '操作结果',
  `ywdxid` varchar(255) DEFAULT NULL COMMENT '业务对象id',
  `ywdxlx` varchar(255) DEFAULT NULL COMMENT '业务对象类型，全类名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录';


DROP TABLE IF EXISTS `t_bh`;
CREATE TABLE `t_bh` (
  `id` varchar(255) NOT NULL,
  `currentYear` varchar(50) DEFAULT NULL,
  `formatStr` longtext,
  `isYear` int(11) DEFAULT NULL,
  `name` varchar(36) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `prefixStr` longtext,
  `type` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水号';


DROP TABLE IF EXISTS `t_jzrzjl`;
CREATE TABLE `t_jzrzjl` (
  `Num_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录标识',
  `Operate_Condition` longtext COMMENT '查询条件',
  `Error_Code` varchar(4) DEFAULT NULL COMMENT '错误代码',
  `Operate_Name` varchar(30) DEFAULT NULL COMMENT '功能模块名称',
  `Operate_Time` varchar(30) DEFAULT NULL COMMENT '操作时间',
  `Operate_Type` int(11) DEFAULT NULL COMMENT '操作类型',
  `User_Name` varchar(30) DEFAULT NULL COMMENT '操作人姓名',
  `Operate_Result` varchar(1) DEFAULT NULL COMMENT '操作结果',
  `Terminal_ID` varchar(40) DEFAULT NULL COMMENT '终端标识',
  `Organization_ID` varchar(12) DEFAULT NULL COMMENT '单位编码',
  `Organization` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `User_ID` varchar(18) DEFAULT NULL COMMENT '用户标识',
  PRIMARY KEY (`Num_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='警综日志记录';


DROP TABLE IF EXISTS `t_scoreframework_groovyrule`;
CREATE TABLE `t_scoreframework_groovyrule` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `script` longtext,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分框架groovy规则表';


DROP TABLE IF EXISTS `t_scoreframework_scorepoint`;
CREATE TABLE `t_scoreframework_scorepoint` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ceid` varchar(255) DEFAULT NULL,
  `cetype` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分框架积分点表';


DROP TABLE IF EXISTS `t_scoreframework_scorepointcfg`;
CREATE TABLE `t_scoreframework_scorepointcfg` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `weight` double NOT NULL,
  `scoreframework_scorepoint_id` varchar(255) DEFAULT NULL,
  `scoreframework_scoretask_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK518889FEAE869647` (`scoreframework_scorepoint_id`),
  KEY `FK518889FE9E26556D` (`scoreframework_scoretask_id`),
  CONSTRAINT `FK518889FE9E26556D` FOREIGN KEY (`scoreframework_scoretask_id`) REFERENCES `t_scoreframework_scoretask` (`id`),
  CONSTRAINT `FK518889FEAE869647` FOREIGN KEY (`scoreframework_scorepoint_id`) REFERENCES `t_scoreframework_scorepoint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分框架积分点配置表';


DROP TABLE IF EXISTS `t_scoreframework_scorerule`;
CREATE TABLE `t_scoreframework_scorerule` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ruleid` varchar(64) NOT NULL,
  `type` varchar(200) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `scoreframework_scorepoint_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDEECC526AE869647` (`scoreframework_scorepoint_id`),
  CONSTRAINT `FKDEECC526AE869647` FOREIGN KEY (`scoreframework_scorepoint_id`) REFERENCES `t_scoreframework_scorepoint` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分框架积分规则表';


DROP TABLE IF EXISTS `t_scoreframework_scoretask`;
CREATE TABLE `t_scoreframework_scoretask` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `warnscore` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDEED63AFE40D9147` (`parent_id`),
  CONSTRAINT `FKDEED63AFE40D9147` FOREIGN KEY (`parent_id`) REFERENCES `t_scoreframework_scoretask` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分任务表';


DROP TABLE IF EXISTS `t_scoreframework_szpt_groovyrule`;
CREATE TABLE `t_scoreframework_szpt_groovyrule` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `script` longtext,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分框架szpt专用groovy规则表';


DROP TABLE IF EXISTS `t_scoreprocess_fail_item`;
CREATE TABLE `t_scoreprocess_fail_item` (
  `id` varchar(255) NOT NULL,
  `exception` longtext,
  `recordtime` datetime NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `scoreprocess_process_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9D564D9D65C04F66` (`scoreprocess_process_id`),
  CONSTRAINT `FK9D564D9D65C04F66` FOREIGN KEY (`scoreprocess_process_id`) REFERENCES `t_scoreprocess_process` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分计算失败项';


DROP TABLE IF EXISTS `t_scoreprocess_process`;
CREATE TABLE `t_scoreprocess_process` (
  `id` varchar(255) NOT NULL,
  `endtime` datetime DEFAULT NULL,
  `finishednum` int(11) DEFAULT NULL,
  `starttime` datetime NOT NULL,
  `state` varchar(10) NOT NULL,
  `totalnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分计算过程';



DROP TABLE IF EXISTS `t_surveillance_default_result`;
CREATE TABLE `t_surveillance_default_result` (
  `id` varchar(255) NOT NULL,
  `catchContent` varchar(255) DEFAULT NULL COMMENT '捕获内容',
  `catchDetail` longtext COMMENT '捕获详情',
  `catchLatitude` double DEFAULT NULL COMMENT '纬度',
  `catchLongitude` double DEFAULT NULL COMMENT '经度',
  `catchObject` varchar(255) DEFAULT NULL COMMENT '捕获对象',
  `catchTime` datetime DEFAULT NULL COMMENT '捕获时间',
  `clueId` varchar(255) DEFAULT NULL COMMENT '线索id：如果是图片类型就是t_gwry_rybk_bkzp的id；如果是wif就是t_gwry_rybkyddxx的id；其他则是t_gwry_rybk的id',
  `clueType` varchar(255) DEFAULT NULL COMMENT '布控类型',
  `resultStatus` varchar(255) DEFAULT NULL COMMENT '状态：默认值为字典项初始化:init（字典类型系统状态:xtzt）',
  `surveilListNum` varchar(255)  DEFAULT NULL COMMENT '布控单编码',
  `resultType` varchar(255) DEFAULT NULL COMMENT '布控结果类型',
  `fjy_catchImgResult_id` varchar(255) DEFAULT NULL COMMENT '布控图片对象的结果',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fjy_catchImgResult_id` (`fjy_catchImgResult_id`),
  KEY `FK99D78CF66FFC3A14` (`fjy_catchImgResult_id`),
  KEY `surveillance_default_result_surveilListNum` (`surveilListNum`),
  CONSTRAINT `FK99D78CF66FFC3A14` FOREIGN KEY (`fjy_catchImgResult_id`) REFERENCES `t_attachmentmeta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='布控结果基础表'; 