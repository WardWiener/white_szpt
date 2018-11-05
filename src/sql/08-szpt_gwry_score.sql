/*
高危人员积分功能脚本
*/

SET FOREIGN_KEY_CHECKS=0;


drop table if exists t_gwry_ryjfmx;
create table t_gwry_ryjfmx
(
   id                   varchar(36) not null comment 'id',
   zdyjfsz              int(11) default NULL comment '最低预警分数值',
   xgr                  varchar(255) default NULL comment '修改人',
   xgsj                 datetime default NULL comment '修改时间',
   mc                   varchar(255) default NULL comment '模型名称',
   bz                   varchar(255) default NULL comment '备注',
   bh                   varchar(255) default NULL comment '编号',
   zt                   varchar(255) default NULL comment '状态',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分模型';

-- ----------------------------
-- Records of t_gwry_ryjfmx
-- ----------------------------
INSERT INTO `t_gwry_ryjfmx` VALUES ('2cf05691-b4f2-4897-819f-f4b0371a459c', '70', 'xnry02', '2017-04-13 11:35:27', '系统初始化高危人积分模型', '系统初始化高危人积分模型', '201704080001', '1');



drop table if exists t_gwry_ryjfmxgz;
create table t_gwry_ryjfmxgz
(
   id                   varchar(255) not null comment 'id',
   gjz                  varchar(255) default NULL comment '关键字  已在jsp中写死  下面是所有的key\r\n	  在控类型：\r\n	 	在控类型-评分项权重\r\n	 	在控类型-权重封顶\r\n	 	在控类型-高危\r\n		在控类型-关注\r\n	 	在控类型-一般\r\n	 	在控类型-无\r\n	 人员类别：	\r\n	 	人员类别-评分项权重\r\n	 	人员类别-权重封顶\r\n	 	人员类别-在逃\r\n	 	人员类别-涉稳\r\n	 	人员类别-涉恐\r\n	 	人员类别-肇事肇祸精神病\r\n	 	人员类别-重点上访\r\n	 	人员类别-违法犯罪青少年\r\n	 	人员类别-艾滋病人\r\n		人员类别-涉毒@吸毒人员\r\n	 	人员类别-涉毒@制贩毒人员\r\n	 	人员类别-刑事前科@危害国家安全案\r\n	 	人员类别-刑事前科@危害公共安全案\r\n	 	人员类别-刑事前科@破坏社会主义市场经济秩序案\r\n	 	人员类别-刑事前科@侵犯公民人身权利、民主权利案\r\n	 	人员类别-刑事前科@侵犯财产案\r\n	 	人员类别-刑事前科@妨害社会管理案\r\n	 	人员类别-刑事前科@危害国防利益案\r\n	 	人员类别-刑事前科@渎职案\r\n	  就业情况:\r\n	 	就业情况-评分项权重\r\n	 	就业情况-权重封顶\r\n	 	就业情况-无业\r\n	   就业情况-待业	\r\n	  	就业情况-失业\r\n	  	就业情况-就业\r\n	  婚姻情况:\r\n	  	就业情况-评分项权重\r\n		就业情况-权重封顶\r\n	  	婚姻情况-已婚\r\n	  	婚姻情况-再婚\r\n		婚姻情况-丧偶\r\n	  	婚姻情况-未婚\r\n	 	婚姻情况-离婚\r\n	  	婚姻情况-未知\r\n	  经济收入（月）:\r\n	  	经济收入（月）-评分项权重\r\n	 	就业情况-权重封顶\r\n	 	经济收入（月）-少于1000元\r\n	 	经济收入（月）-1000~2000\r\n	  	经济收入（月）-2000~5000\r\n	 	经济收入（月）-5000以上\r\n	 	经济收入（月）-空\r\n	 近一月出行次数:\r\n	 	近一月出行次数-评分项权重\r\n	 	近一月出行次数-权重封顶\r\n	  	近一月出行次数-=6次\r\n	  	近一月出行次数-=5次\r\n	 	近一月出行次数-=4次\r\n	 	近一月出行次数-=3次\r\n	 	近一月出行次数-=2次\r\n	  	近一月出行次数-<=1次\r\n	  场所属性:\r\n	 	场所属性-评分项权重\r\n	 	场所属性-权重封顶\r\n		场所属性-娱乐场所权重（近一年）@>=20次',
   sz                   varchar(255) default NULL comment '                数值\r\n	  数值为两位数字\r\n	  例： 20',
   ryjfmx_id            varchar(255) default NULL comment '人员积分模型_id',
   primary key (id),
   key FKDAC31F9E9910E4A3 (ryjfmx_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分模型规则';

-- ----------------------------
-- Records of t_gwry_ryjfmxgz
-- ----------------------------
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('0641547a-4036-4d50-a230-ae038d8accc1', '人员类别-渎职案', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('0cd69ae6-7049-4c85-b9b9-8e78156f738e', '经济收入（月）-评分项权重', '5', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('0ceb1c65-2d55-4080-be19-c29966c06734', '人员类别-肇事肇祸精神病人', '40', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('100eb012-33d5-4390-b15d-686c27af6fc8', '场所属性-娱乐场所权重（近一年）@<=2次', '0', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('12b95d05-89d0-4eac-a402-c519147e3650', '场所属性-娱乐场所权重（近一年）@>2次，且<20次', '60', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('13e84496-425b-4f14-875d-297477b6021c', '人员类别-涉恐人员', '90', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('151d3643-a3cc-4d33-a25a-33b2815ab9a2', '经济收入（月）-1000~2000', '90', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('19b17e2a-d082-4558-8488-088e170c089a', '人员类别-权重封顶', '40', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('1f0fe87a-98f5-4880-8b72-1c588b094b78', '场所属性-酒店宾馆权重（近一年）@>2天，且<20天', '50', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('267627bd-c49e-43b8-bd94-e40d1ec4e7f0', '婚姻情况-再婚', '60', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('2724045b-2cd7-45e2-b740-6689941930be', '经济收入（月）-空', '0', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('290ba58d-8622-453a-bccb-80dcaff24338', '场所属性-酒店宾馆权重（近一年）@>=20天', '90', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('394b7101-0799-43ab-82dc-360b92cd40ea', '近一月出行次数-=2次', '50', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('399914b0-8b50-4fbe-8014-f8f42cac4b56', '人员类别-刑事前科@妨害社会管理案', '60', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('4028f081-5a12-4e76-bbd7-6f179de1a5cc', '人员类别-涉毒人员@制贩毒人员', '60', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('40eabbec-ff76-4889-b7d4-4d375fd9e7f0', '近一月出行次数-权重封顶', '15', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('41b5871f-239e-4e34-a1e7-113f2ede9129', '婚姻情况-离婚', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('42dc09b5-6ecc-49ad-984a-cfc7508d5ec2', '经济收入（月）-少于1000元', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('43db5e62-05a2-4f81-80d9-2cb3d0ab3441', '婚姻情况-未婚', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('4993c6dc-8379-4eba-94af-6f1ad631d880', '婚姻情况-权重封顶', '5', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('4b525a9a-bfbb-4766-9bab-97f3df4656a8', '近一月出行次数-=4次', '70', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('4dc512f9-ae97-47e3-83c6-7b6cacad9f2d', '人员类别-重点上访人员', '50', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('4ef62b22-56fd-4c94-aef6-a334f5713bd2', '在控类型-权重封顶', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('51452559-1559-47b6-b3a5-cae278d589ec', '在控类型-高危', '110', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('57527f94-0971-41ca-a405-5796698b8747', '就业情况-就业', '40', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('57dd49b1-b6d0-41e8-bd9b-2d395c4992c2', '婚姻情况-评分项权重', '5', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('5cba9b8a-c3a1-4e2c-b4e6-80d6e23f9fff', '人员类别-涉毒人员@吸毒人员', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('622a56aa-e635-48ab-b0e8-3deb69a8b14e', '经济收入（月）-权重封顶', '5', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('69456c2d-15ad-44a5-81a1-ebf585b4d6f0', '人员类别-艾滋病人', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('746b2b31-cb1d-4c78-a5ab-82716d517f24', '近一月出行次数-=5次', '90', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('79a70a54-c559-4034-999c-0e6cc7613c2d', '在控类型-评分项权重', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('814ff9a4-f73f-4806-9142-11e77dcd40d6', '近一月出行次数-=3次', '60', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('83de72bc-1969-4cbe-8a16-411e2c6ad073', '场所属性-网吧权重（近一年）@@>=240小时', '90', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('877d7866-5caa-4070-8d08-458616cf7a60', '人员类别-刑事前科@危害公共安全案', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('87e109d6-79ee-4be9-a50c-837021a0096d', '在控类型-无', '0', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('88c768bc-818e-4a35-9f73-1edcc48ef76c', '婚姻情况-已婚', '50', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('8b71d02f-409e-4e11-9e49-ad2479d7e9b5', '场所属性-网吧权重（近一年）@<=56小时', '0', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('8bdbb388-f058-424e-beb6-543c678eb3ea', '人员类别-刑事前科@破坏社会主义市场经济秩序案', '60', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('8f04840f-1618-4a99-add6-4fa4f8e358f5', '场所属性-权重封顶', '40', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('90df2603-7688-4ede-98da-2a5c077e4b31', '就业情况-无业', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('91cf6e05-17f4-4afa-87c1-5f40ab0a7d05', '就业情况-评分项权重', '15', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('93c3b507-2d6d-4865-bfbb-fd6bf34fda54', '就业情况-权重封顶', '15', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('93f0c92c-0ae6-43d9-ac81-c23600337f30', '人员类别-刑事前科@危害国防利益案', '80', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('9501a8e0-8025-4a83-955d-a2a0cdae066a', '在控类型-一般', '60', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('96e0837b-029b-4583-9df9-19dd66860e5a', '经济收入（月）-5000以上', '50', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('999dcd45-dda3-49e7-aa73-5926a6e4fe10', '近一月出行次数-<=1次', '0', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('9aa71df5-4fce-4974-baeb-3a572bf7e390', '人员类别-涉稳人员', '80', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('9e9550e5-d41b-4b4c-bbf6-054bee6e3a0b', '近一月出行次数-=6次', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('a04e92b8-abc6-4ba3-87e0-3f61070f77e2', '人员类别-在逃人员', '120', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('a4498aac-e4eb-45b3-a1c7-f7011be3af09', '场所属性-酒店宾馆权重（近一年）@<=2天', '0', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('a699bd8a-a249-4cae-8a67-14b0f62ada1a', '婚姻情况-未知', '0', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('a6bcbecb-1059-4ed2-8732-d0b8e612fc73', '人员类别-违法犯罪青少年', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('a7e87596-5e6d-4985-ba64-7322ee291415', '场所属性-娱乐场所权重（近一年）@>=20次', '120', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('a9fd8a86-f4b3-48d4-9b42-61c37380df2e', '近一月出行次数-评分项权重', '15', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('abcaf1ef-c1eb-43a7-bf84-a08e716cdd08', '人员类别-刑事前科@危害国家安全案', '120', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('b7bc7e70-6eee-4862-bd3b-1189e9309797', '就业情况-待业', '80', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('bc4b2301-15d4-4e6d-b6a0-ef0cf5a1819f', '人员类别-刑事前科@侵犯公民人身权利、民主权利案', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('c1dd2c4d-36d3-48ed-b0fb-75dc57b153eb', '在控类型-关注', '80', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('d1ebdcfa-4bcd-4dd7-a32e-fb8b6751bb6d', '经济收入（月）-2000~5000', '70', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('dab0a5f4-d107-4953-aff4-56a5a2d1afd3', '场所属性-网吧权重（近一年）@>56小时，且<240小', '50', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('e4253e66-8f3f-415e-af8a-a0744f6a9f45', '人员类别-评分项权重', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('eb5ffede-8afb-4d0f-863f-a01d3a824b16', '婚姻情况-丧偶', '100', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('edb24bae-c2d8-4248-b764-3f9b3d001570', '场所属性-评分项权重', '20', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('f3a61811-2568-485b-8f80-159b1b9ffc42', '人员类别-刑事前科@侵犯财产案', '80', '2cf05691-b4f2-4897-819f-f4b0371a459c');
INSERT INTO `t_gwry_ryjfmxgz` VALUES ('f59853ab-b404-43f1-a52f-aafefa95ffcb', '就业情况-失业', '80', '2cf05691-b4f2-4897-819f-f4b0371a459c');



DROP TABLE IF EXISTS `t_scorecompute_hrpscore_result`;
CREATE TABLE `t_scorecompute_hrpscore_result` (
  `id` varchar(255) NOT NULL,
  `hrpId` varchar(64) NOT NULL,
  `score` double NOT NULL,
  `scoreTaskId` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分计算结果';



DROP TABLE IF EXISTS `t_scorecompute_hrpscore_rule_result`;
CREATE TABLE `t_scorecompute_hrpscore_rule_result` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hrpid` varchar(64) NOT NULL,
  `input` longtext,
  `otherResults` longtext,
  `score` double NOT NULL,
  `scorepointid` varchar(64) NOT NULL,
  `scoreruleid` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分规则计算结果';



DROP TABLE IF EXISTS `t_scorecompute_hrpscore_scorepoint_result`;
CREATE TABLE `t_scorecompute_hrpscore_scorepoint_result` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hrpId` varchar(64) NOT NULL,
  `score` double NOT NULL,
  `scorePointId` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分积分点计算结果';



DROP TABLE IF EXISTS `t_scorecompute_hrpscore_scorepointconfig_result`;
CREATE TABLE `t_scorecompute_hrpscore_scorepointconfig_result` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hrpId` varchar(64) NOT NULL,
  `scoreAfterWeight` double NOT NULL,
  `scoreBeforeWeight` double NOT NULL,
  `scorePointConfigId` varchar(64) NOT NULL,
  `scorePointId` varchar(64) NOT NULL,
  `scoreTaskId` varchar(64) NOT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分积分点配置计算结果';

DROP TABLE IF EXISTS `t_score_monitor_process`;
CREATE TABLE `t_score_monitor_process` (
  `id` varchar(255) NOT NULL,
  `completedNum` bigint(20) NOT NULL,
  `computeType` varchar(255) DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `startTime` datetime NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `targetId` varchar(255) DEFAULT NULL,
  `targetType` varchar(255) DEFAULT NULL,
  `totalNum` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分过程记录';

DROP TABLE IF EXISTS `t_score_monitor_fail`;
CREATE TABLE `t_score_monitor_fail` (
  `id` varchar(255) NOT NULL,
  `businessKey` varchar(64) DEFAULT NULL,
  `businessType` varchar(255) DEFAULT NULL,
  `errorMessage` longtext,
  `recordTime` datetime DEFAULT NULL,
  `cpid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfvory1eoyxtokx39jcb85gkvp` (`cpid`),
  CONSTRAINT `FKfvory1eoyxtokx39jcb85gkvp` FOREIGN KEY (`cpid`) REFERENCES `t_score_monitor_process` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员积分异常记录';

