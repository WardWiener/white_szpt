/*
Navicat MySQL Data Transfer

Source Server         : szpt
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : szpt

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-05-11 11:58:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_customizedmenu_xtcd
-- ----------------------------
DROP TABLE IF EXISTS `t_customizedmenu_xtcd`;
CREATE TABLE `t_customizedmenu_xtcd` (
  `id` varchar(255) NOT NULL,
  `ms` varchar(255) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `sfzd` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `zy_id` varchar(255) DEFAULT NULL,
  `f_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKns9jithdy6j11hu5awwi1jm46` (`zy_id`),
  KEY `FKh094teu03r67mkwfo5kon3ixv` (`f_id`),
  CONSTRAINT `FKh094teu03r67mkwfo5kon3ixv` FOREIGN KEY (`f_id`) REFERENCES `t_customizedmenu_xtcd` (`id`),
  CONSTRAINT `FKns9jithdy6j11hu5awwi1jm46` FOREIGN KEY (`zy_id`) REFERENCES `t_at_zy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customizedmenu_xtcd
-- ----------------------------
INSERT INTO `t_customizedmenu_xtcd` VALUES ('000', '功能菜单', '功能菜单', '0', '', null, null);
INSERT INTO `t_customizedmenu_xtcd` VALUES ('0001', '首页', '首页', '1', '/loginHomePage/showLoginPage.action', null, '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('001', '综合治安态势', '综合治安态势', '0', '/zhzats/showZhzatsWelcome.action', '65858380-3797-4570-9f93-32b0cdacf818', '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00102', '综合治安态势', '警情分布分析', '1', '/zhzats/showZhzatsFenbu.action?clickOrder=1&&clickListOrder=0', null, '001');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00103', '综合治安态势', '高发警情分析', '1', '/zhzats/showZhzatsGaofa.action?clickOrder=2&&clickListOrder=0', null, '001');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00104', '综合治安态势', '刑事警情研判', '1', '/zhzats/showZhzatsYanpan.action?clickOrder=3&&clickListOrder=0', null, '001');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('002', '形式案件分析', '形式案件分析', '0', '/xsfxts/showXsfxtsWelcome.action', '65858380-3797-4570-9f93-32b0cdacf818', '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00202', '刑事案件分析', '刑事案件一案一研判', '1', '/caseJudge/showCaseJudgePage.action?clickOrder=1&&clickListOrder=0', null, '002');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00204', '刑事案件分析', '嫌疑人MAC地址分析', '1', '/suspectMacAnalysis/showSuspectMacAnalysisPage.action?clickOrder=3&&clickListOrder=0', null, '002');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00206', '刑事案件分析', '专案资料管理', '1', '/zagl/showProjectManagement.action', null, '002');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('003', '高危人群分析', '高危人', '0', '/highriskPerson/showHomePage.action', '65858380-3797-4570-9f93-32b0cdacf818', '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00301', '高危人', '高危人管理', '0', '/highriskPerson/showHomePage.action', '65858380-3797-4570-9f93-32b0cdacf818', '003');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00303', '高危人', '高危人群预警', '1', '/personAttention/showPersonAttentionPage.action?clickOrder=4&&clickListOrder=0', null, '003');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00304', '高危人', '人案时空分析', '1', '/heatAnalyze/showHeatAnalyzePage.action?clickOrder=5&&clickListOrder=0', null, '003');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00306', '高危人', '人员积分模型设置', '1', '/integralModel/showIntegralModelPage.action?clickOrder=9&&clickListOrder=0', null, '003');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00307', '高危人', '数据布控', '1', '/personExecuteControl/showPersonExecuteControlListPage.action?clickOrder=7&&clickListOrder=0', null, '003');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('004', '场所分析', '场所分析', '0', '/realTimeWifi/showLookRealTimeWifiPage.action', null, '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00401', '场所监控分析', '实时WIFI设备监控', '1', '/realTimeWifi/showLookRealTimeWifiPage.action?clickOrder=0&&clickListOrder=0', null, '004');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00402', '场所监控分析', '人员WIFI轨迹分析', '1', '/personLocusAnalyze/showPersonLocusAnalyzePage.action?clickOrder=1&&clickListOrder=0', null, '004');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('005', '档案管理', '档案管理', '1', '/yayd/showYaydListPage.action', '65858380-3797-4570-9f93-32b0cdacf818', '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00501', '一人一档', '一人一档', '0', '/yryd/showYrydLstPage.action', '65858380-3797-4570-9f93-32b0cdacf818', '005');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00502', '一案一档', '一案一档', '0', '/yayd/showYaydListPage.action', '65858380-3797-4570-9f93-32b0cdacf818', '005');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('007', '全文检索', '全文检索', '1', '/fullsearch/showFullSearchIndexPage.action', null, '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('008', '指令管理', '指令管理', '0', '/instruction/showInstructionListPage.action', '65858380-3797-4570-9f93-32b0cdacf818', '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('009', '基础设施', '基础设施', '1', '/gzgl/showPenalConstant.action', null, '000');
INSERT INTO `t_customizedmenu_xtcd` VALUES ('00901', '基础设施', '统计分析类规则管理', '1', '/gzgl/showPenalConstant.action', null, '009');

-- ----------------------------
-- Table structure for t_customizedmenu_zdycd
-- ----------------------------
DROP TABLE IF EXISTS `t_customizedmenu_zdycd`;
CREATE TABLE `t_customizedmenu_zdycd` (
  `id` varchar(255) NOT NULL,
  `mb_id` varchar(255) DEFAULT NULL,
  `lx` varchar(255) DEFAULT NULL,
  `yj_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7qd277nwc8j7c6i8csj7y58hd` (`yj_id`),
  CONSTRAINT `FK7qd277nwc8j7c6i8csj7y58hd` FOREIGN KEY (`yj_id`) REFERENCES `t_customizedmenu_xtcd` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customizedmenu_zdycd
-- ----------------------------
