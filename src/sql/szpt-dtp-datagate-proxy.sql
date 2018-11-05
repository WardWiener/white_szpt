/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : datagate

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-08 10:37:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dtp_datagate_img_clue
-- ----------------------------
DROP TABLE IF EXISTS `t_dtp_datagate_img_clue`;
CREATE TABLE `t_dtp_datagate_img_clue` (
  `id` varchar(255) NOT NULL,
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `img` longblob COMMENT '图片二进制',
  `imgClueId` varchar(255) DEFAULT NULL,
  `surveilList_id` varchar(255) DEFAULT NULL COMMENT '布控单id',
  PRIMARY KEY (`id`),
  KEY `FKgf9bago2qaat3tjh31pew1i77` (`surveilList_id`),
  CONSTRAINT `FKgf9bago2qaat3tjh31pew1i77` FOREIGN KEY (`surveilList_id`) REFERENCES `t_dtp_datagate_surveil_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片线索';

-- ----------------------------
-- Records of t_dtp_datagate_img_clue
-- ----------------------------

-- ----------------------------
-- Table structure for t_dtp_datagate_img_clue_result
-- ----------------------------
DROP TABLE IF EXISTS `t_dtp_datagate_img_clue_result`;
CREATE TABLE `t_dtp_datagate_img_clue_result` (
  `id` varchar(255) NOT NULL,
  `cameraAddr` varchar(255) DEFAULT NULL COMMENT '摄像头地址',
  `cameraCode` varchar(255) DEFAULT NULL COMMENT '摄像头编码',
  `catchDate` datetime DEFAULT NULL COMMENT '捕获时间',
  `confidenceLevel` double DEFAULT NULL COMMENT '置信度',
  `img` longblob COMMENT '捕获图片二进制',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `status` varchar(255) NOT NULL COMMENT '状态:未读unread, 已读read',
  `surveilList_id` varchar(255) DEFAULT NULL COMMENT '布控单id',
  PRIMARY KEY (`id`),
  KEY `FKtpjguxadt6py1sp5huhd36eft` (`surveilList_id`),
  CONSTRAINT `FKtpjguxadt6py1sp5huhd36eft` FOREIGN KEY (`surveilList_id`) REFERENCES `t_dtp_datagate_surveil_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='布控结果';

-- ----------------------------
-- Records of t_dtp_datagate_img_clue_result
-- ----------------------------

-- ----------------------------
-- Table structure for t_dtp_datagate_surveil_list
-- ----------------------------
DROP TABLE IF EXISTS `t_dtp_datagate_surveil_list`;
CREATE TABLE `t_dtp_datagate_surveil_list` (
  `id` varchar(255) NOT NULL,
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `endDate` datetime DEFAULT NULL COMMENT '布控结束时间',
  `identy` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `startDate` datetime DEFAULT NULL COMMENT '布控开始时间',
  `status` varchar(255) DEFAULT NULL COMMENT '状态：1启用；0停用',
  `surveilListCode` varchar(255) DEFAULT NULL COMMENT '布控单编码',
  PRIMARY KEY (`id`),
  KEY `dtp_datagate_surveil_list_surveilListCode` (`surveilListCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='布控单';

-- ----------------------------
-- Records of t_dtp_datagate_surveil_list
-- ----------------------------
