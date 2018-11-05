

-- ----------------------------
-- Table structure for t_attachmentmeta
-- ----------------------------

DROP TABLE IF EXISTS `t_surveillance_default_result` ;

DROP TABLE IF EXISTS `t_gwry_rybk_bkzp`;
DROP TABLE IF EXISTS `t_gwry_rybkyddxx`;
DROP TABLE IF EXISTS `t_gwry_rybk`;

DROP TABLE IF EXISTS `t_attachmentcopy`;
DROP TABLE IF EXISTS `t_attachmentmeta`;
DROP TABLE IF EXISTS `t_dbattachmentimpl`;

CREATE TABLE `t_attachmentmeta` (
  `id` varchar(255) NOT NULL,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `t_attachmentcopy` (
  `id` varchar(255) NOT NULL,
  `createTime` date NOT NULL,
  `type` longtext NOT NULL,
  `attachmentMetaId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_eulsyk7g17lj67my7p671s6x5` (`attachmentMetaId`),
  CONSTRAINT `FK_eulsyk7g17lj67my7p671s6x5` FOREIGN KEY (`attachmentMetaId`) REFERENCES `t_attachmentmeta` (`id`)
) ;

-- ----------------------------
-- Table structure for t_dbattachmentimpl
-- ----------------------------
CREATE TABLE `t_dbattachmentimpl` (
  `id` varchar(255) NOT NULL,
  `attachment` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ;


-- ----------------------------
-- Table structure for t_gwry_rybk
-- ----------------------------


CREATE TABLE `t_gwry_rybk` (
  `id` varchar(36) NOT NULL,
  `bkjssj` datetime DEFAULT NULL,
  `sfzh` varchar(255) DEFAULT NULL,
  `zxxgrxm` varchar(255) DEFAULT NULL,
  `zxxgsj` datetime DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `bh` varchar(255) DEFAULT NULL,
  `czzt` varchar(255) DEFAULT NULL,
  `xm` varchar(255) DEFAULT NULL,
  `hjdz` varchar(255) DEFAULT NULL,
  `xb` varchar(255) DEFAULT NULL,
  `bkkssj` datetime DEFAULT NULL,
  `sjzt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
); 

-- ----------------------------
-- Table structure for t_gwry_rybk_bkzp
-- ----------------------------

CREATE TABLE `t_gwry_rybk_bkzp` (
  `id` varchar(255) NOT NULL,
  `ywfj_id` varchar(255) DEFAULT NULL,
  `fjy_id` varchar(255) DEFAULT NULL,
  `rybk_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fjy_id` (`fjy_id`),
  KEY `FKDE27FB71A04C425C` (`rybk_id`),
  KEY `FKDE27FB714C20AAE` (`fjy_id`),
  CONSTRAINT `FKDE27FB714C20AAE` FOREIGN KEY (`fjy_id`) REFERENCES `t_attachmentmeta` (`id`),
  CONSTRAINT `FKDE27FB71A04C425C` FOREIGN KEY (`rybk_id`) REFERENCES `t_gwry_rybk` (`id`)
); 

-- ----------------------------
-- Records of t_gwry_rybk_bkzp
-- ----------------------------

-- ----------------------------
-- Table structure for t_gwry_rybkyddxx
-- ----------------------------

CREATE TABLE `t_gwry_rybkyddxx` (
  `id` varchar(36) NOT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `sjhm` varchar(255) DEFAULT NULL,
  `rybk_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDF972CCCA04C425C` (`rybk_id`)
); 

CREATE TABLE `t_surveillance_default_result` (
  `id` varchar(255) NOT NULL,
  `catchContent` varchar(255) DEFAULT NULL ,
  `catchDetail` longtext ,
  `catchLatitude` double DEFAULT NULL ,
  `catchLongitude` double DEFAULT NULL ,
  `catchObject` varchar(255) DEFAULT NULL ,
  `catchTime` datetime DEFAULT NULL ,
  `clueId` varchar(255) DEFAULT NULL ,
  `clueType` varchar(255) DEFAULT NULL ,
  `resultStatus` varchar(255) DEFAULT NULL ,
  `surveilListNum` varchar(255)  DEFAULT NULL ,
  `resultType` varchar(255) DEFAULT NULL ,
  `fjy_catchImgResult_id` varchar(255) DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fjy_catchImgResult_id` (`fjy_catchImgResult_id`),
  KEY `FK99D78CF66FFC3A14` (`fjy_catchImgResult_id`),
  KEY `surveillance_default_result_surveilListNum` (`surveilListNum`),
  CONSTRAINT `FK99D78CF66FFC3A14` FOREIGN KEY (`fjy_catchImgResult_id`) REFERENCES `t_attachmentmeta` (`id`)
) ; 
