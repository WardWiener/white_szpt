

-- 专案管理模块	字典类型数据
INSERT INTO `t_zdlx` (`id`, `fl`, `bm`, `bz`, `mc`, `zt`, `sjc`) VALUES ('zagl0001', '专案管理', 'zaxz', '专案管理', '专案性质', '1', '2016-11-30 09:31:06');
INSERT INTO `t_zdlx` (`id`, `fl`, `bm`, `bz`, `mc`, `zt`, `sjc`) VALUES ('zagl0002', '专案管理', 'zazllx', '专案管理', '资料类型', '1', '2016-11-30 09:36:11');
INSERT INTO `t_zdlx` (`id`, `fl`, `bm`, `bz`, `mc`, `zt`, `sjc`) VALUES ('zagl0003', '专案管理', 'jszt', '专案管理', '角色状态', '1', '2016-11-30 09:36:52');
INSERT INTO `t_zdlx` (`id`, `fl`, `bm`, `bz`, `mc`, `zt`, `sjc`) VALUES ('zagl0004', '专案管理', 'jslx', '专家管理', '角色类型', '1', '2016-11-30 09:37:37');
INSERT INTO `t_zdlx` (`id`, `fl`, `bm`, `bz`, `mc`, `zt`, `sjc`) VALUES ('zagl0005', '专案管理', 'bglx', '专案管理', '报告类型', '1', '2016-11-30 09:38:09');

-- 专案管理模块	字典项数据
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000101', '01', '', '涉黑', '1', '1', '2016-11-30 09:57:31', 'zagl0001', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000102', '02', '', '涉黑涉恶', '2', '1', '2016-11-30 09:57:31', 'zagl0001', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000103', '03', '', '团伙案件', '3', '1', '2016-11-30 09:57:31', 'zagl0001', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000104', '04', '', '其他', '4', '1', '2016-11-30 09:57:31', 'zagl0001', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000201', '01', '', '案件基本资料', '1', '1', '2016-11-30 09:57:31', 'zagl0002', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000202', '02', '', '音频资料', '2', '1', '2016-11-30 09:57:31', 'zagl0002', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000203', '03', '', '视频资料', '3', '1', '2016-11-30 09:57:31', 'zagl0002', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000204', '04', '', '图片资料', '4', '1', '2016-11-30 09:57:31', 'zagl0002', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000205', '05', '', '其他资料', '5', '1', '2016-11-30 09:57:31', 'zagl0002', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000301', '01', '', '停用', '1', '1', '2016-11-30 09:57:31', 'zagl0003', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000302', '02', '', '启用', '2', '1', '2016-11-30 09:57:31', 'zagl0003', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000401', '01', '', '局领导', '1', '1', '2016-11-30 09:57:31', 'zagl0004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000402', '02', '', '组长', '2', '1', '2016-11-30 09:57:31', 'zagl0004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000403', '03', '', '副组长', '3', '1', '2016-11-30 09:57:31', 'zagl0004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000404', '04', '', '主要负责人', '4', '1', '2016-11-30 09:57:31', 'zagl0004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000405', '05', '', '专案内勤', '5', '1', '2016-11-30 09:57:31', 'zagl0004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000406', '06', '', '成员-抓捕组', '6', '1', '2016-11-30 09:57:31', 'zagl0004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000407', '07', '', '成员-情报组', '7', '1', '2016-11-30 09:57:31', 'zagl0004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000501', '01', '', '综合分析研判', '1', '1', '2016-11-30 09:57:31', 'zagl0005', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000502', '02', '', '案件分析研判', '2', '1', '2016-11-30 09:57:31', 'zagl0005', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000503', '03', '', '重点人分析研判', '3', '1', '2016-11-30 09:57:31', 'zagl0005', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000504', '04', '', '线索经营推送', '4', '1', '2016-11-30 09:57:31', 'zagl0005', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000505', '05', '', '专案管理', '5', '1', '2016-11-30 09:57:31', 'zagl0005', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000510', '10', '', '盗窃案发展情况通报', '10', '1', '2016-11-30 09:57:31', 'zagl0005', 'zagl000501');
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('zagl000511', '11', '', '专案进展报告', '11', '1', '2016-11-30 09:57:31', 'zagl0005', 'zagl000501');

-- ----------------------------
-- Table structure for t_zagl_za
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_za`;
CREATE TABLE `t_zagl_za` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `bm` varchar(255) DEFAULT NULL COMMENT '编码',
  `aqjj` varchar(255) DEFAULT NULL COMMENT '案情简介',
  `cjrq` datetime DEFAULT NULL COMMENT '创建日期',
  `mc` varchar(255) DEFAULT NULL COMMENT '名称',
  `xz` varchar(255) DEFAULT NULL COMMENT '性质',
  `xbjh` varchar(255) DEFAULT NULL COMMENT '下步计划',
  `zywt` varchar(255) DEFAULT NULL COMMENT '主要问题',
  `jz` varchar(255) DEFAULT NULL COMMENT '进展',
  `zhgxsj` datetime DEFAULT NULL COMMENT '最后更新时间',
  `ryid` varchar(255) DEFAULT NULL COMMENT '创建人',
  `gxry_id` varchar(255) DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`id`),
  KEY `FK9CAE452FC4FEAD0C` (`gxry_id`) USING BTREE COMMENT '最后更新人',
  KEY `FK9CAE452FA94E06EC` (`ryid`) USING BTREE COMMENT '创建人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案';

-- ----------------------------
-- Table structure for t_zagl_zabg
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zabg`;
CREATE TABLE `t_zagl_zabg` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `attachmentId` varchar(255) DEFAULT NULL COMMENT '附件id',
  `cjsj` datetime DEFAULT NULL COMMENT '创建时间',
  `mc` varchar(255) DEFAULT NULL COMMENT '名称',
  `lx` varchar(255) DEFAULT NULL COMMENT '类型',
  `cjr_id` varchar(255) DEFAULT NULL COMMENT '创建人',
  `zagl_za_id` varchar(255) DEFAULT NULL COMMENT '专案',
  PRIMARY KEY (`id`),
  KEY `FK2A31C1B46B9F452B` (`zagl_za_id`),
  KEY `FK2A31C1B458132639` (`cjr_id`),
  CONSTRAINT `FK2A31C1B458132639` FOREIGN KEY (`cjr_id`) REFERENCES `t_og_ry` (`id`),
  CONSTRAINT `FK2A31C1B46B9F452B` FOREIGN KEY (`zagl_za_id`) REFERENCES `t_zagl_za` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案报告';

-- ----------------------------
-- Table structure for t_zagl_zajs
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zajs`;
CREATE TABLE `t_zagl_zajs` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `bm` varchar(255) DEFAULT NULL COMMENT '编码',
  `mc` varchar(255) DEFAULT NULL COMMENT '名称',
  `zt` varchar(255) DEFAULT NULL COMMENT '状态：启用(1)、停用(0)',
  `lx` varchar(255) DEFAULT NULL COMMENT '类型：预置(0)、自定义(1)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案角色';

-- ----------------------------
-- Records of t_zagl_zajs
-- ----------------------------
INSERT INTO `t_zagl_zajs` VALUES ('1', '0001', '局领导', '1', '0');
INSERT INTO `t_zagl_zajs` VALUES ('2', '0002', '组长', '1', '0');
INSERT INTO `t_zagl_zajs` VALUES ('3', '0003', '副组长', '1', '0');
INSERT INTO `t_zagl_zajs` VALUES ('4', '0004', '主要负责人', '1', '0');

-- ----------------------------
-- Table structure for t_zagl_zaly
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zaly`;
CREATE TABLE `t_zagl_zaly` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `nr` varchar(255) DEFAULT NULL COMMENT '内容',
  `lysj` datetime DEFAULT NULL COMMENT '留言时间',
  `lyr_id` varchar(255) DEFAULT NULL COMMENT '留言人',
  `zagl_zabg_id` varchar(255) DEFAULT NULL COMMENT '专案',
  PRIMARY KEY (`id`),
  KEY `FK2A31C2FC66153D46` (`zagl_zabg_id`),
  KEY `FK2A31C2FC6842245F` (`lyr_id`),
  CONSTRAINT `FK2A31C2FC66153D46` FOREIGN KEY (`zagl_zabg_id`) REFERENCES `t_zagl_za` (`id`),
  CONSTRAINT `FK2A31C2FC6842245F` FOREIGN KEY (`lyr_id`) REFERENCES `t_og_ry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='留言';

-- ----------------------------
-- Table structure for t_zagl_lyzdjl
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_lyzdjl`;
CREATE TABLE `t_zagl_lyzdjl` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `zdsj` datetime DEFAULT NULL COMMENT '置顶时间',
  `zalyId` varchar(255) DEFAULT NULL COMMENT '留言',
  `zdr_id` varchar(255) DEFAULT NULL COMMENT '置顶人',
  PRIMARY KEY (`id`),
  KEY `FK4E416B017EFE0F7C` (`zdr_id`),
  KEY `FK4E416B01ABFEA0EB` (`zalyId`),
  CONSTRAINT `FK4E416B01ABFEA0EB` FOREIGN KEY (`zalyId`) REFERENCES `t_zagl_zaly` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='留言置顶记录';

-- ----------------------------
-- Table structure for t_zagl_zaryjsfp
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zaryjsfp`;
CREATE TABLE `t_zagl_zaryjsfp` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `sfzd` varchar(5) DEFAULT NULL COMMENT '是否置顶(1 置顶  0  不置顶)',
  `ryid` varchar(255) DEFAULT NULL COMMENT '人员',
  `zagl_zajs_id` varchar(255) DEFAULT NULL COMMENT '专案角色',
  `zagl_za_id` varchar(255) DEFAULT NULL COMMENT '专案',
  PRIMARY KEY (`id`),
  KEY `FK6FCEE4896B9F452B` (`zagl_za_id`),
  KEY `FK6FCEE489B9F494D8` (`zagl_zajs_id`),
  KEY `FK6FCEE489A94E06EC` (`ryid`),
  CONSTRAINT `FK6FCEE4896B9F452B` FOREIGN KEY (`zagl_za_id`) REFERENCES `t_zagl_za` (`id`),
  CONSTRAINT `FK6FCEE489A94E06EC` FOREIGN KEY (`ryid`) REFERENCES `t_og_ry` (`id`),
  CONSTRAINT `FK6FCEE489B9F494D8` FOREIGN KEY (`zagl_zajs_id`) REFERENCES `t_zagl_zajs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案人员角色分配';

-- ----------------------------
-- Table structure for t_zagl_zasary
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zasary`;
CREATE TABLE `t_zagl_zasary` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `cjsj` datetime DEFAULT NULL COMMENT '创建时间',
  `hjdz` varchar(255) DEFAULT NULL COMMENT '户籍地址',
  `hj` varchar(255) DEFAULT NULL COMMENT '户籍',
  `sfzh` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `xm` varchar(255) DEFAULT NULL COMMENT '姓名',
  `ch` varchar(255) DEFAULT NULL COMMENT '绰号',
  `sjh` varchar(255) DEFAULT NULL COMMENT '手机号',
  `ryid` varchar(255) DEFAULT NULL COMMENT '创建人',
  `zagl_za_id` varchar(255) DEFAULT NULL COMMENT '专案',
  PRIMARY KEY (`id`),
  KEY `FK64CFD6C46B9F452B` (`zagl_za_id`),
  KEY `FK64CFD6C4A94E06EC` (`ryid`),
  CONSTRAINT `FK64CFD6C46B9F452B` FOREIGN KEY (`zagl_za_id`) REFERENCES `t_zagl_za` (`id`),
  CONSTRAINT `FK64CFD6C4A94E06EC` FOREIGN KEY (`ryid`) REFERENCES `t_og_ry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案涉案人员';

-- ----------------------------
-- Table structure for t_zagl_zasarygx
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zasarygx`;
CREATE TABLE `t_zagl_zasarygx` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `gxlx` varchar(255) DEFAULT NULL COMMENT '关系类型',
  `gxsj` datetime DEFAULT NULL COMMENT '更新时间',
  `zagl_za_id` varchar(255) DEFAULT NULL COMMENT '专案',
  `agl_zasary_id` varchar(255) DEFAULT NULL COMMENT '关系线的起始人员',
  `agl_zasary_id2` varchar(255) DEFAULT NULL COMMENT '关系线的结束人员',
  `ryid` varchar(255) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `FK703542B56B9F452B` (`zagl_za_id`),
  KEY `FK703542B57B512B54` (`agl_zasary_id2`),
  KEY `FK703542B5BC4C098C` (`agl_zasary_id`),
  KEY `FK703542B5A94E06EC` (`ryid`),
  CONSTRAINT `FK703542B56B9F452B` FOREIGN KEY (`zagl_za_id`) REFERENCES `t_zagl_za` (`id`),
  CONSTRAINT `FK703542B57B512B54` FOREIGN KEY (`agl_zasary_id2`) REFERENCES `t_zagl_zasary` (`id`),
  CONSTRAINT `FK703542B5A94E06EC` FOREIGN KEY (`ryid`) REFERENCES `t_og_ry` (`id`),
  CONSTRAINT `FK703542B5BC4C098C` FOREIGN KEY (`agl_zasary_id`) REFERENCES `t_zagl_zasary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案涉案人员关系';

-- ----------------------------
-- Table structure for t_zagl_zazaj
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zazaj`;
CREATE TABLE `t_zagl_zazaj` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `zajbm` varchar(255) DEFAULT NULL COMMENT '子案件编码',
  `zajmc` varchar(255) DEFAULT NULL COMMENT '子案件名称',
  `bamj` varchar(255) DEFAULT NULL COMMENT '办案民警，多个用，隔开存储',
  `zagl_za_id` varchar(255) DEFAULT NULL COMMENT '专案',
  PRIMARY KEY (`id`),
  KEY `FK1C06CE946B9F452B` (`zagl_za_id`) USING BTREE COMMENT '专案',
  CONSTRAINT `FK1C06CE946B9F452B` FOREIGN KEY (`zagl_za_id`) REFERENCES `t_zagl_za` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='子案件表';

-- ----------------------------
-- Table structure for t_zagl_zazl
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zazl`;
CREATE TABLE `t_zagl_zazl` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `zazlmc` varchar(255) DEFAULT NULL COMMENT '专案资料名称',
  `cjsj` datetime DEFAULT NULL COMMENT '创建时间',
  `lx` varchar(255) DEFAULT NULL COMMENT '类型',
  `ryid` varchar(255) DEFAULT NULL COMMENT '创建人',
  `zagl_zabg_id` varchar(255) DEFAULT NULL COMMENT '专案',
  PRIMARY KEY (`id`),
  KEY `FK2A31C4A166153D46` (`zagl_zabg_id`),
  KEY `FK2A31C4A1A94E06EC` (`ryid`),
  CONSTRAINT `FK2A31C4A1A94E06EC` FOREIGN KEY (`ryid`) REFERENCES `t_og_ry` (`id`),
  CONSTRAINT `FK2A31C4A166153D46` FOREIGN KEY (`zagl_zabg_id`) REFERENCES `t_zagl_za` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案资料';

-- ----------------------------
-- Table structure for t_zagl_zxzdjl
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_zxzdjl`;
CREATE TABLE `t_zagl_zxzdjl` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `cjsj` datetime DEFAULT NULL COMMENT '置顶时间',
  `zagl_za_id` varchar(255) DEFAULT NULL COMMENT '专案',
  `zdr` varchar(255) DEFAULT NULL COMMENT '置顶人',
  PRIMARY KEY (`id`),
  KEY `FK66172C326B9F452B` (`zagl_za_id`),
  KEY `FK66172C32A91A37D2` (`zdr`),
  CONSTRAINT `FK66172C32A91A37D2` FOREIGN KEY (`zdr`) REFERENCES `t_og_ry` (`id`),
  CONSTRAINT `FK66172C326B9F452B` FOREIGN KEY (`zagl_za_id`) REFERENCES `t_zagl_za` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专案置顶记录';

-- ----------------------------
-- Table structure for t_zagl_ryjsfp
-- ----------------------------
DROP TABLE IF EXISTS `t_zagl_ryjsfp`;
CREATE TABLE `t_zagl_ryjsfp` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `ryid` varchar(255) DEFAULT NULL COMMENT '人员',
  `zagl_zajs_id` varchar(255) DEFAULT NULL COMMENT '专案角色',
  PRIMARY KEY (`id`),
  KEY `FK587770A2B9F494D8` (`zagl_zajs_id`),
  KEY `FK587770A2A94E06EC` (`ryid`),
  CONSTRAINT `FK587770A2A94E06EC` FOREIGN KEY (`ryid`) REFERENCES `t_og_ry` (`id`),
  CONSTRAINT `FK587770A2B9F494D8` FOREIGN KEY (`zagl_zajs_id`) REFERENCES `t_zagl_zajs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='局领导角色分配人员，该角色人员自动添加到每一个专案的角色人员分配记录中';

