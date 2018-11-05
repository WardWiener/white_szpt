/*
系统管理模块脚本
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_at_fwkzzt
-- ----------------------------
DROP TABLE IF EXISTS `t_at_fwkzzt`;
CREATE TABLE `t_at_fwkzzt` (
  `id` varchar(255) NOT NULL,
  `zt_id` varchar(36) DEFAULT NULL,
  `lx` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问控制主体';

-- ----------------------------
-- Records of t_at_fwkzzt
-- ----------------------------
INSERT INTO `t_at_fwkzzt` VALUES ('0b454292-da37-4437-901e-ec7634a98cfd', 'ebc5990a-0597-46f8-923e-71bb2baa8441', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('2c9b7279-8b6c-4783-b168-3a1e3144327d', '9f99b5ab-2f12-4658-ae77-0941a5f0eb29', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('326c5a95-4a12-47bf-9dbf-0deece299d38', 'ff1a01f9-cdbb-4a8f-ac01-7f2c87f71deb', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('4bf4dc59-469f-4edd-b53f-086421eee1c1', '40aacdf6-8ffd-4806-87d9-805fb5e174bf', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('592802bf-109c-4b60-a171-4e199678c2ec', '0f18aa04-f6b6-4c93-9ce1-780b3e2b37b3', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('65984ec1-81de-4b49-88ed-d10ccb6b4f1b', '4ea2e817-4b83-4de2-99db-4ed96e6f8469', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('77ae85b9-9a50-453e-92f1-03bacf73c185', 'adb7a6f4-94e8-4651-9b60-a62491cd106e', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('798a8ff9-63f0-44fe-9911-c3ec981200d6', '56f11613-e159-466a-ab86-4a64da4a363b', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('7ac4ac53-482f-4dca-b11e-16e34fa9b994', '9c6bbc9b-404d-41ba-aea8-96b02da1b992', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('7ac4ac53-482f-4dca-b11e-16e34fa9b995', '92488a83-acd6-415d-b50b-f44e369aa990', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('7ac4ac53-482f-4dca-b11e-16e34fa9b996', '9c6bbc9b-404d-41ba-aea8-96b02da1b993', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('81e14e9d-99d4-4dc8-801a-f5af514e3884', '8ca8c8c8-a112-4288-b30e-675988afba45', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('8d40122f-0ada-489d-b525-33f8797a6822', '2ca97719-7fa8-4b4d-b29b-87d824a026fb', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('bb764661-47f9-46a8-af2e-89f25a6cdee1', '29877e0f-9dc6-4fa9-b8d6-5a045a581bc1', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('da621e3a-cef8-4d2a-a127-18bbf80c88b7', '9c11a2d0-70ca-4650-b5a7-31e20b96a8a3', 'com.taiji.pubsec.businesscomponents.organization.model.Account');
INSERT INTO `t_at_fwkzzt` VALUES ('e028c193-4cd3-4b45-aa5a-7436f626984f', '75367542-db77-4626-b3b9-b72c0eef4667', 'com.taiji.pubsec.businesscomponents.organization.model.Account');

-- ----------------------------
-- Table structure for t_at_fwkzzt_js
-- ----------------------------
-- ----------------------------
-- Table structure for t_at_fwkzzt_js
-- ----------------------------
DROP TABLE IF EXISTS `t_at_fwkzzt_js`;
CREATE TABLE `t_at_fwkzzt_js` (
  `id` varchar(255) NOT NULL,
  `zt` varchar(6) DEFAULT NULL,
  `js_id` varchar(255) DEFAULT NULL,
  `fwkzzt_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3497DF4D4672D7E2` (`js_id`),
  KEY `FK3497DF4DED5E7A1B` (`fwkzzt_id`),
  CONSTRAINT `FK3497DF4D4672D7E2` FOREIGN KEY (`js_id`) REFERENCES `t_at_js` (`id`),
  CONSTRAINT `FK3497DF4DED5E7A1B` FOREIGN KEY (`fwkzzt_id`) REFERENCES `t_at_fwkzzt` (`id`),
  CONSTRAINT `FKj9755e494ddt4s7vfo4717i8q` FOREIGN KEY (`fwkzzt_id`) REFERENCES `t_at_fwkzzt` (`id`),
  CONSTRAINT `FKmyoucw0ysiky8ybpsnq7c11ms` FOREIGN KEY (`js_id`) REFERENCES `t_at_js` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问控制主体角色关系';

-- ----------------------------
-- Records of t_at_fwkzzt_js
-- ----------------------------
INSERT INTO `t_at_fwkzzt_js` VALUES ('095b3b81-7385-4dba-ac78-6d3767328b23', '1', '81e5759d-9db2-487b-9217-49375a6b2994', 'da621e3a-cef8-4d2a-a127-18bbf80c88b7');
INSERT INTO `t_at_fwkzzt_js` VALUES ('0a912610-2c70-48bf-8828-a9e9afc491d3', '1', 'a533c241-66d9-4c8b-9aac-c40efcaf8648', 'e028c193-4cd3-4b45-aa5a-7436f626984f');
INSERT INTO `t_at_fwkzzt_js` VALUES ('13df076f-7db4-42e0-9256-5d514dd0eea9', '1', '4dd27667-6e8a-46e7-ad7b-4d332066479c', 'da621e3a-cef8-4d2a-a127-18bbf80c88b7');
INSERT INTO `t_at_fwkzzt_js` VALUES ('2917a7b2-f5da-438e-8c08-d3f0882f1a11', '1', '91b18bd9-7b5f-4762-9a2c-a6b6e9537b00', 'bb764661-47f9-46a8-af2e-89f25a6cdee1');
INSERT INTO `t_at_fwkzzt_js` VALUES ('3680c11e-bf2b-47c4-b213-9c66d4845496', '1', '4dd27667-6e8a-46e7-ad7b-4d332066479c', '77ae85b9-9a50-453e-92f1-03bacf73c185');
INSERT INTO `t_at_fwkzzt_js` VALUES ('3802730d-cb74-4f6e-b584-cfb733913852', '1', '91b18bd9-7b5f-4762-9a2c-a6b6e9537b00', 'da621e3a-cef8-4d2a-a127-18bbf80c88b7');
INSERT INTO `t_at_fwkzzt_js` VALUES ('482627b9-19e8-450d-b681-eb3142e259bb', '1', 'a533c241-66d9-4c8b-9aac-c40efcaf8648', '65984ec1-81de-4b49-88ed-d10ccb6b4f1b');
INSERT INTO `t_at_fwkzzt_js` VALUES ('52f62c95-0892-49fe-9de4-3ba23f41c51f', '1', 'd618b269-b172-40c6-b452-c0c3cbcf4dfd', '81e14e9d-99d4-4dc8-801a-f5af514e3884');
INSERT INTO `t_at_fwkzzt_js` VALUES ('5cb4791d-5bc3-46a2-a2bb-01ace5329c03', '1', 'd618b269-b172-40c6-b452-c0c3cbcf4dfd', '326c5a95-4a12-47bf-9dbf-0deece299d38');
INSERT INTO `t_at_fwkzzt_js` VALUES ('64af02ac-46f0-4f59-ba10-60c8f015dac9', '1', '6c23e187-6022-4530-aa82-bb2ae820b60b', '8d40122f-0ada-489d-b525-33f8797a6822');
INSERT INTO `t_at_fwkzzt_js` VALUES ('6627c82f-8ba8-4baa-98f7-4a88025425cd', '1', '8d5efa8f-6f7c-48ad-8aaf-ac373408c412', '592802bf-109c-4b60-a171-4e199678c2ec');
INSERT INTO `t_at_fwkzzt_js` VALUES ('77d6a318-7fe5-4dcd-a17f-df6ac6318c6d', '1', '81e5759d-9db2-487b-9217-49375a6b2994', '2c9b7279-8b6c-4783-b168-3a1e3144327d');
INSERT INTO `t_at_fwkzzt_js` VALUES ('7ae4ccdf-7a08-48be-b54f-7c0d73c2a5d4', '1', '91b18bd9-7b5f-4762-9a2c-a6b6e9537b00', '0b454292-da37-4437-901e-ec7634a98cfd');
INSERT INTO `t_at_fwkzzt_js` VALUES ('8c9d0e19-22e8-406e-bef9-8f6b145f4bf3', '1', '4dd27667-6e8a-46e7-ad7b-4d332066479c', '4bf4dc59-469f-4edd-b53f-086421eee1c1');
INSERT INTO `t_at_fwkzzt_js` VALUES ('9aaede1a-06d9-4632-b02c-598a1f1c8865', '1', 'a533c241-66d9-4c8b-9aac-c40efcaf8648', '7ac4ac53-482f-4dca-b11e-16e34fa9b995');
INSERT INTO `t_at_fwkzzt_js` VALUES ('9aaede1a-06d9-4632-b02c-598a1f1c8866', '1', 'a533c241-66d9-4c8b-9aac-c40efcaf8648', '7ac4ac53-482f-4dca-b11e-16e34fa9b994');
INSERT INTO `t_at_fwkzzt_js` VALUES ('9aaede1a-06d9-4632-b02c-598a1f1c8876', '1', '81e5759d-9db2-487b-9217-49375a6b2994', '7ac4ac53-482f-4dca-b11e-16e34fa9b994');
INSERT INTO `t_at_fwkzzt_js` VALUES ('afe40413-e80f-4593-8f11-bde3f22e67ab', '1', 'd618b269-b172-40c6-b452-c0c3cbcf4dfd', 'e028c193-4cd3-4b45-aa5a-7436f626984f');
INSERT INTO `t_at_fwkzzt_js` VALUES ('c0af60ce-b076-4791-b8d9-38024a1a07e8', '1', '0d1d1697-18fa-4f3c-b08e-475ae0227781', '798a8ff9-63f0-44fe-9911-c3ec981200d6');
INSERT INTO `t_at_fwkzzt_js` VALUES ('c9ae00b7-c470-44ec-b868-952bbcf2e4f4', '1', 'a533c241-66d9-4c8b-9aac-c40efcaf8648', 'da621e3a-cef8-4d2a-a127-18bbf80c88b7');
INSERT INTO `t_at_fwkzzt_js` VALUES ('d0a42b69-99b7-4aad-90bc-b0c41aa1f97e', '1', 'a533c241-66d9-4c8b-9aac-c40efcaf8648', '326c5a95-4a12-47bf-9dbf-0deece299d38');
INSERT INTO `t_at_fwkzzt_js` VALUES ('ddce01ab-1a07-40d9-8122-8a5b0e207eae', '1', 'd618b269-b172-40c6-b452-c0c3cbcf4dfd', 'da621e3a-cef8-4d2a-a127-18bbf80c88b7');

-- ----------------------------
-- Table structure for t_at_js
-- ----------------------------
DROP TABLE IF EXISTS `t_at_js`;
CREATE TABLE `t_at_js` (
  `id` varchar(255) NOT NULL,
  `zzjg_id` varchar(50) DEFAULT NULL,
  `jsbm` varchar(50) DEFAULT NULL,
  `jsmc` varchar(30) DEFAULT NULL,
  `zt` varchar(30) DEFAULT NULL,
  `sjc` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_at_js
-- ----------------------------
INSERT INTO `t_at_js` VALUES ('0d1d1697-18fa-4f3c-b08e-475ae0227781', null, 'cegn', '测试功能', '1', '2017-04-26 10:08:27');
INSERT INTO `t_at_js` VALUES ('4dd27667-6e8a-46e7-ad7b-4d332066479c', null, 'qzzxld', '情指中心领导', '1', '2017-04-13 19:36:20');
INSERT INTO `t_at_js` VALUES ('6c23e187-6022-4530-aa82-bb2ae820b60b', null, 'pcs', '派出所', '1', '2017-04-24 14:56:47');
INSERT INTO `t_at_js` VALUES ('81e5759d-9db2-487b-9217-49375a6b2994', null, 'fjdd', '分局大队', '1', '2017-04-24 14:54:10');
INSERT INTO `t_at_js` VALUES ('8d5efa8f-6f7c-48ad-8aaf-ac373408c412', null, 'xzdd', '刑侦大队', '1', '2017-04-24 14:49:38');
INSERT INTO `t_at_js` VALUES ('91b18bd9-7b5f-4762-9a2c-a6b6e9537b00', null, 'qzzxjy', '情指中心警员', '1', '2017-04-13 19:36:50');
INSERT INTO `t_at_js` VALUES ('a533c241-66d9-4c8b-9aac-c40efcaf8648', null, '123123', '系统管理员', '1', '2017-04-24 15:02:16');
INSERT INTO `t_at_js` VALUES ('d618b269-b172-40c6-b452-c0c3cbcf4dfd', null, 'ywry', '运维人员', '1', '2017-04-24 14:57:41');

-- ----------------------------
-- Table structure for t_at_js_qx
-- ----------------------------
DROP TABLE IF EXISTS `t_at_js_qx`;
CREATE TABLE `t_at_js_qx` (
  `js_id` varchar(255) NOT NULL,
  `qx_id` varchar(255) NOT NULL,
  KEY `FKB379253CF9678ABB` (`qx_id`),
  KEY `FKB379253C4672D7E2` (`js_id`),
  CONSTRAINT `FK21p9a4mba38i3d9t586fp7dr5` FOREIGN KEY (`qx_id`) REFERENCES `t_at_qx` (`id`),
  CONSTRAINT `FKB379253C4672D7E2` FOREIGN KEY (`js_id`) REFERENCES `t_at_js` (`id`),
  CONSTRAINT `FKB379253CF9678ABB` FOREIGN KEY (`qx_id`) REFERENCES `t_at_qx` (`id`),
  CONSTRAINT `FKov6d1ye9r1oltby5w7ep56lpm` FOREIGN KEY (`js_id`) REFERENCES `t_at_js` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关系';

-- ----------------------------
-- Records of t_at_js_qx
-- ----------------------------
INSERT INTO `t_at_js_qx` VALUES ('4dd27667-6e8a-46e7-ad7b-4d332066479c', 'daafc369-d727-41fa-b640-6f30faece59f');
INSERT INTO `t_at_js_qx` VALUES ('91b18bd9-7b5f-4762-9a2c-a6b6e9537b00', 'ad9df135-7335-436e-a6d3-c5b8c274fb42');
INSERT INTO `t_at_js_qx` VALUES ('8d5efa8f-6f7c-48ad-8aaf-ac373408c412', '0cb64d40-2b55-471a-8a77-6a66e15986c3');
INSERT INTO `t_at_js_qx` VALUES ('8d5efa8f-6f7c-48ad-8aaf-ac373408c412', '14ac59ee-0b46-465b-823c-bdeaa465b6ef');
INSERT INTO `t_at_js_qx` VALUES ('81e5759d-9db2-487b-9217-49375a6b2994', '0cb64d40-2b55-471a-8a77-6a66e15986c3');
INSERT INTO `t_at_js_qx` VALUES ('6c23e187-6022-4530-aa82-bb2ae820b60b', '4c3684d7-8ec4-4325-a75b-ea00dde621f9');
INSERT INTO `t_at_js_qx` VALUES ('d618b269-b172-40c6-b452-c0c3cbcf4dfd', '8c5b9919-f598-4242-a178-2edb94843691');
INSERT INTO `t_at_js_qx` VALUES ('a533c241-66d9-4c8b-9aac-c40efcaf8648', 'cbbdd6e2-dc83-44b7-8e2a-83f21797e839');
INSERT INTO `t_at_js_qx` VALUES ('0d1d1697-18fa-4f3c-b08e-475ae0227781', 'f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1');

-- ----------------------------
-- Table structure for t_at_qx
-- ----------------------------
DROP TABLE IF EXISTS `t_at_qx`;
CREATE TABLE `t_at_qx` (
  `id` varchar(255) NOT NULL,
  `qxbm` varchar(36) DEFAULT NULL,
  `qxmc` varchar(36) DEFAULT NULL,
  `qxlx` varchar(36) DEFAULT NULL,
  `sjc` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Records of t_at_qx
-- ----------------------------
INSERT INTO `t_at_qx` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', 'fjddgt', '分局大队共通', null, '2017-04-24 15:02:34');
INSERT INTO `t_at_qx` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', 'fjxzdd', '分局刑侦大队', null, '2017-04-24 15:02:45');
INSERT INTO `t_at_qx` VALUES ('349c5ec7-508e-4b1f-9228-4bf8a7fab0f5', '123', '部门', null, '2017-01-20 11:47:33');
INSERT INTO `t_at_qx` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', 'pcs', '派出所', null, '2017-04-24 15:02:54');
INSERT INTO `t_at_qx` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', 'xtywry', '系统运维人员', null, '2017-04-24 15:03:24');
INSERT INTO `t_at_qx` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', 'qzhxjy', '情指中心警员', null, '2017-04-24 15:03:02');
INSERT INTO `t_at_qx` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'xtgly', '系统管理员', null, '2017-04-24 15:40:32');
INSERT INTO `t_at_qx` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', 'qzzxld', '情指中心领导', null, '2017-05-12 11:20:50');
INSERT INTO `t_at_qx` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', 'gncs', '功能测试', null, '2017-04-26 11:14:54');

-- ----------------------------
-- Table structure for t_at_qx_zy
-- ----------------------------
DROP TABLE IF EXISTS `t_at_qx_zy`;
CREATE TABLE `t_at_qx_zy` (
  `qx_id` varchar(255) NOT NULL,
  `zy_id` varchar(255) NOT NULL,
  KEY `FKB3DE10B6F9678ABB` (`qx_id`),
  KEY `FKB3DE10B689DBF2D1` (`zy_id`),
  CONSTRAINT `FK2tjsvefvgc2mtmo922uu0bkfq` FOREIGN KEY (`qx_id`) REFERENCES `t_at_qx` (`id`),
  CONSTRAINT `FKB3DE10B689DBF2D1` FOREIGN KEY (`zy_id`) REFERENCES `t_at_zy` (`id`),
  CONSTRAINT `FKB3DE10B6F9678ABB` FOREIGN KEY (`qx_id`) REFERENCES `t_at_qx` (`id`),
  CONSTRAINT `FKg3g74fusn34psdnr8hu6ttfti` FOREIGN KEY (`zy_id`) REFERENCES `t_at_zy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源关系';

-- ----------------------------
-- Records of t_at_qx_zy
-- ----------------------------
INSERT INTO `t_at_qx_zy` VALUES ('349c5ec7-508e-4b1f-9228-4bf8a7fab0f5', 'e25976c5-388f-4865-9beb-a190ee9e97a1');
INSERT INTO `t_at_qx_zy` VALUES ('349c5ec7-508e-4b1f-9228-4bf8a7fab0f5', 'fefd86d1-d5ed-4126-adab-8cce2b98e89c');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '4df1c4ba-6a80-4100-90ff-c8e579146015');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '54e12fb6-42a6-4c27-a15c-fe0bc836fe60');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '75b1d5dd-9791-4b02-a7d5-c42fdc93018b');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', 'ce9d1f92-c96a-428f-9d9b-9d139da579f9');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '50f3b8a3-f271-470b-939d-5af1e009a3a6');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', 'a3321349-3b33-4d44-99ce-c089550b1aa4');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', 'c6e85281-1012-41d3-97a8-cdcc8051fb05');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', 'e25976c5-388f-4865-9beb-a190ee9e97a1');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '6f5b191d-27c0-434b-bf95-f988e6c123f6');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '65858380-3797-4570-9f93-32b0cdacf818');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '8564c9a4-7cb2-4979-97a4-45ef685e5c6e');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', 'cd79828b-5e54-401c-a1a3-dfb8ac7e1da2');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '44c4fca6-34d7-4cd8-8d13-c936dbe14052');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '6f7db2d4-90cd-442a-b9a9-ffbd027b90ea');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '6b9ccc78-d9da-43a2-b733-f97826a29a88');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '7ae4bec6-29b9-46f4-bbc3-ef9cf7752df8');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '87519699-c28e-4383-baee-564d8e6bfa4d');
INSERT INTO `t_at_qx_zy` VALUES ('8c5b9919-f598-4242-a178-2edb94843691', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '86911003-9c31-41db-aa59-d56fc6f135b4');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', 'aad7f6ef-3435-4d26-9fdb-457835d4142b');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '5c94bb30-08d0-45d6-957a-4f84f3f9a5c4');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '63108100-9efc-4fbb-b6ca-c2d2cced9311');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '54e12fb6-42a6-4c27-a15c-fe0bc836fe60');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '8045a48e-1213-427a-b46d-cc7841cf9214');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '548b71e7-3f5f-4102-b06c-c90e228d288a');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '632be624-8e79-499c-9f1d-8699328da7e9');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', 'ff52747f-e326-4600-9519-c3430e64894b');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '21dc5a7d-f3eb-4fce-9a8e-0bcb24c8384b');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', 'ce9d1f92-c96a-428f-9d9b-9d139da579f9');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '639e2498-1516-4e4f-9ee4-57dc8167c293');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', 'd621e36e-0b81-40c8-8f20-f7f9d8310ada');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '75b1d5dd-9791-4b02-a7d5-c42fdc93018b');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '50f3b8a3-f271-470b-939d-5af1e009a3a6');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '07a90ae6-c8ea-4720-b475-d8ce3f8e8b3f');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '44333ad6-da63-45a1-a99c-22343ea76c74');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '852745b3-a278-4018-82a7-92141e5e6a65');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '60aca1aa-9965-40be-876f-bfb74b78dbef');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', 'e63d3c38-9506-4587-8e3b-1f8865d00c0a');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '37306b04-099e-435c-a435-c9bc7a38f26c');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', 'e8f0f537-b59d-485e-aef9-0a23d71fe63f');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '1a6977a8-2b38-4ecb-9497-187bfc66dd05');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('ad9df135-7335-436e-a6d3-c5b8c274fb42', '849906dd-641d-418a-9c49-07be813bd9ad');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '5c94bb30-08d0-45d6-957a-4f84f3f9a5c4');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '63108100-9efc-4fbb-b6ca-c2d2cced9311');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '54e12fb6-42a6-4c27-a15c-fe0bc836fe60');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '3cb94818-3f3f-4c8e-b868-bef7bb191731');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '548b71e7-3f5f-4102-b06c-c90e228d288a');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '632be624-8e79-499c-9f1d-8699328da7e9');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', 'ff52747f-e326-4600-9519-c3430e64894b');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '87239031-e9bd-481b-a4f4-dc400bc7864d');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', 'ce9d1f92-c96a-428f-9d9b-9d139da579f9');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', 'd621e36e-0b81-40c8-8f20-f7f9d8310ada');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '50f3b8a3-f271-470b-939d-5af1e009a3a6');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '75b1d5dd-9791-4b02-a7d5-c42fdc93018b');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '07a90ae6-c8ea-4720-b475-d8ce3f8e8b3f');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '852745b3-a278-4018-82a7-92141e5e6a65');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '91b00e62-8152-485d-9303-216c01c6e0fe');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '60aca1aa-9965-40be-876f-bfb74b78dbef');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', 'e63d3c38-9506-4587-8e3b-1f8865d00c0a');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', 'e8f0f537-b59d-485e-aef9-0a23d71fe63f');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '1a6977a8-2b38-4ecb-9497-187bfc66dd05');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '57a7352f-0538-4341-89e7-89f0f2da8a70');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '6ed6bc2e-7ae8-4d5e-b495-ee1d7e2f578e');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '849906dd-641d-418a-9c49-07be813bd9ad');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', 'c05f51a3-803a-400d-9e6f-f97bc715321d');
INSERT INTO `t_at_qx_zy` VALUES ('daafc369-d727-41fa-b640-6f30faece59f', '3cced668-10ad-4ac5-b61e-af10567ad894');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '01de7e0f-47cb-4fcd-bed7-043847a46f4a');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '07a90ae6-c8ea-4720-b475-d8ce3f8e8b3f');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '19dcd938-4df0-47bf-a4e9-00384bfa6242');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '1a6977a8-2b38-4ecb-9497-187bfc66dd05');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '21dc5a7d-f3eb-4fce-9a8e-0bcb24c8384b');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '27e335c6-85b9-4efa-8372-2ea956333a28');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '37306b04-099e-435c-a435-c9bc7a38f26c');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '3887114e-aa18-4a37-b1c0-e4877318a804');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '3cb57b05-39dd-4e65-9357-a0549f64dc43');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '3cb94818-3f3f-4c8e-b868-bef7bb191731');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '44333ad6-da63-45a1-a99c-22343ea76c74');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '44c4fca6-34d7-4cd8-8d13-c936dbe14052');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '4df1c4ba-6a80-4100-90ff-c8e579146015');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '50f3b8a3-f271-470b-939d-5af1e009a3a6');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '548b71e7-3f5f-4102-b06c-c90e228d288a');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '54e12fb6-42a6-4c27-a15c-fe0bc836fe60');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '5c94bb30-08d0-45d6-957a-4f84f3f9a5c4');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '60aca1aa-9965-40be-876f-bfb74b78dbef');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '63108100-9efc-4fbb-b6ca-c2d2cced9311');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '632be624-8e79-499c-9f1d-8699328da7e9');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '639e2498-1516-4e4f-9ee4-57dc8167c293');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '65858380-3797-4570-9f93-32b0cdacf818');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '6b9ccc78-d9da-43a2-b733-f97826a29a88');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '6eba5add-0982-48bf-b1b5-914e53ad08b5');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '6f5b191d-27c0-434b-bf95-f988e6c123f6');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '6f7db2d4-90cd-442a-b9a9-ffbd027b90ea');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '75b1d5dd-9791-4b02-a7d5-c42fdc93018b');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '7ae4bec6-29b9-46f4-bbc3-ef9cf7752df8');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '8045a48e-1213-427a-b46d-cc7841cf9214');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '849906dd-641d-418a-9c49-07be813bd9ad');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '852745b3-a278-4018-82a7-92141e5e6a65');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '8564c9a4-7cb2-4979-97a4-45ef685e5c6e');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '86911003-9c31-41db-aa59-d56fc6f135b4');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '87239031-e9bd-481b-a4f4-dc400bc7864d');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '87519699-c28e-4383-baee-564d8e6bfa4d');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '91b00e62-8152-485d-9303-216c01c6e0fe');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'a3321349-3b33-4d44-99ce-c089550b1aa4');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'aad7f6ef-3435-4d26-9fdb-457835d4142b');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'c6e85281-1012-41d3-97a8-cdcc8051fb05');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'cd79828b-5e54-401c-a1a3-dfb8ac7e1da2');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'ce9d1f92-c96a-428f-9d9b-9d139da579f9');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'd621e36e-0b81-40c8-8f20-f7f9d8310ada');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'e19df85c-a8a0-4ea8-a363-72a7b88fbc35');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'e25976c5-388f-4865-9beb-a190ee9e97a1');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'e63d3c38-9506-4587-8e3b-1f8865d00c0a');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'e8f0f537-b59d-485e-aef9-0a23d71fe63f');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'fc446838-a9fa-4dfe-b81c-690d3f6197bf');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'fefd86d1-d5ed-4126-adab-8cce2b98e89c');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', 'ff52747f-e326-4600-9519-c3430e64894b');
INSERT INTO `t_at_qx_zy` VALUES ('cbbdd6e2-dc83-44b7-8e2a-83f21797e839', '3cced668-10ad-4ac5-b61e-af10567ad894');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '54e12fb6-42a6-4c27-a15c-fe0bc836fe60');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '632be624-8e79-499c-9f1d-8699328da7e9');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', 'ff52747f-e326-4600-9519-c3430e64894b');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', 'ce9d1f92-c96a-428f-9d9b-9d139da579f9');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', 'd621e36e-0b81-40c8-8f20-f7f9d8310ada');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '07a90ae6-c8ea-4720-b475-d8ce3f8e8b3f');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '44333ad6-da63-45a1-a99c-22343ea76c74');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '91b00e62-8152-485d-9303-216c01c6e0fe');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '60aca1aa-9965-40be-876f-bfb74b78dbef');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', 'e63d3c38-9506-4587-8e3b-1f8865d00c0a');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '1a6977a8-2b38-4ecb-9497-187bfc66dd05');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '3887114e-aa18-4a37-b1c0-e4877318a804');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '57a7352f-0538-4341-89e7-89f0f2da8a70');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '6ed6bc2e-7ae8-4d5e-b495-ee1d7e2f578e');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '849906dd-641d-418a-9c49-07be813bd9ad');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', 'c05f51a3-803a-400d-9e6f-f97bc715321d');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '3cced668-10ad-4ac5-b61e-af10567ad894');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', '2c6bdeda-2e81-467f-800c-849eb3ff333d');
INSERT INTO `t_at_qx_zy` VALUES ('0cb64d40-2b55-471a-8a77-6a66e15986c3', 'cc45340f-2970-492f-9110-d0690ede141d');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '6eba5add-0982-48bf-b1b5-914e53ad08b5');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '3887114e-aa18-4a37-b1c0-e4877318a804');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '57a7352f-0538-4341-89e7-89f0f2da8a70');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '6ed6bc2e-7ae8-4d5e-b495-ee1d7e2f578e');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '849906dd-641d-418a-9c49-07be813bd9ad');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', 'c05f51a3-803a-400d-9e6f-f97bc715321d');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '3cced668-10ad-4ac5-b61e-af10567ad894');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', '2c6bdeda-2e81-467f-800c-849eb3ff333d');
INSERT INTO `t_at_qx_zy` VALUES ('14ac59ee-0b46-465b-823c-bdeaa465b6ef', 'cc45340f-2970-492f-9110-d0690ede141d');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '5c94bb30-08d0-45d6-957a-4f84f3f9a5c4');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '639e2498-1516-4e4f-9ee4-57dc8167c293');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', 'a3321349-3b33-4d44-99ce-c089550b1aa4');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '852745b3-a278-4018-82a7-92141e5e6a65');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', 'e63d3c38-9506-4587-8e3b-1f8865d00c0a');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '37306b04-099e-435c-a435-c9bc7a38f26c');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '87519699-c28e-4383-baee-564d8e6bfa4d');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', 'ce9d1f92-c96a-428f-9d9b-9d139da579f9');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', 'fefd86d1-d5ed-4126-adab-8cce2b98e89c');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '91b00e62-8152-485d-9303-216c01c6e0fe');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', 'aad7f6ef-3435-4d26-9fdb-457835d4142b');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '54e12fb6-42a6-4c27-a15c-fe0bc836fe60');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '7ae4bec6-29b9-46f4-bbc3-ef9cf7752df8');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '849906dd-641d-418a-9c49-07be813bd9ad');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '3cced668-10ad-4ac5-b61e-af10567ad894');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', '2c6bdeda-2e81-467f-800c-849eb3ff333d');
INSERT INTO `t_at_qx_zy` VALUES ('f7ea98b9-12ee-4e98-8e6e-34d2cc9b6fa1', 'cc45340f-2970-492f-9110-d0690ede141d');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '60aca1aa-9965-40be-876f-bfb74b78dbef');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', 'e63d3c38-9506-4587-8e3b-1f8865d00c0a');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '1a6977a8-2b38-4ecb-9497-187bfc66dd05');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '19dcd938-4df0-47bf-a4e9-00384bfa6242');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '0cec34f5-2f87-47a0-904b-6bbf0d9e728f');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '3887114e-aa18-4a37-b1c0-e4877318a804');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '57a7352f-0538-4341-89e7-89f0f2da8a70');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '6ed6bc2e-7ae8-4d5e-b495-ee1d7e2f578e');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '849906dd-641d-418a-9c49-07be813bd9ad');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', 'c05f51a3-803a-400d-9e6f-f97bc715321d');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '07a90ae6-c8ea-4720-b475-d8ce3f8e8b3f');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '548b71e7-3f5f-4102-b06c-c90e228d288a');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '54e12fb6-42a6-4c27-a15c-fe0bc836fe60');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '8045a48e-1213-427a-b46d-cc7841cf9214');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '3cced668-10ad-4ac5-b61e-af10567ad894');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', 'cc45340f-2970-492f-9110-d0690ede141d');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '2c6bdeda-2e81-467f-800c-849eb3ff333d');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '236eb773-b15c-44bd-8472-77a42993b331');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', 'fe3f3b74-fec9-4ad3-ad7e-781f59eb3637');
INSERT INTO `t_at_qx_zy` VALUES ('4c3684d7-8ec4-4325-a75b-ea00dde621f9', '8b06d96d-a664-4a1e-b3a8-e54a358a80aa');



DROP TABLE IF EXISTS `t_at_zy`;
CREATE TABLE `t_at_zy` (
  `id` varchar(255) NOT NULL,
  `zymc` varchar(36) DEFAULT NULL,
  `zylx` varchar(36) DEFAULT NULL,
  `zydz` varchar(255) DEFAULT NULL,
  `sjc` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of t_at_zy
-- ----------------------------
INSERT INTO `t_at_zy` VALUES ('01de7e0f-47cb-4fcd-bed7-043847a46f4a', '专案维护', '1', '/zawh/*', '2016-12-17 23:42:46');
INSERT INTO `t_at_zy` VALUES ('07a90ae6-c8ea-4720-b475-d8ce3f8e8b3f', '高危人WIFI监测', '1', '/wifiAnalyze/*', '2017-04-24 15:35:21');
INSERT INTO `t_at_zy` VALUES ('0cec34f5-2f87-47a0-904b-6bbf0d9e728f', '首页', '1', '/loginHomePage/showLoginPage.action', '2017-04-24 11:18:17');
INSERT INTO `t_at_zy` VALUES ('19dcd938-4df0-47bf-a4e9-00384bfa6242', '移动端app', '1', '/interface/*', '2017-04-24 14:24:09');
INSERT INTO `t_at_zy` VALUES ('1a6977a8-2b38-4ecb-9497-187bfc66dd05', '全文检索', '1', '/fullsearch/*', '2017-04-24 13:42:28');
INSERT INTO `t_at_zy` VALUES ('21dc5a7d-f3eb-4fce-9a8e-0bcb24c8384b', '数据布控', '1', '/personExecuteControl/showPersonExecuteControlList.action', '2017-04-24 13:31:50');
INSERT INTO `t_at_zy` VALUES ('236eb773-b15c-44bd-8472-77a42993b331', '高危人查看详情', '1', '/personDetail*', '2017-05-25 14:45:30');
INSERT INTO `t_at_zy` VALUES ('27e335c6-85b9-4efa-8372-2ea956333a28', '人员档案', '1', '/personDetail/*', '2017-04-24 15:38:12');
INSERT INTO `t_at_zy` VALUES ('2c6bdeda-2e81-467f-800c-849eb3ff333d', '指令操作记录', '1', '/instruction/findOperationRecordBeanListByReceiveSubjectId.action', '2017-05-22 16:53:24');
INSERT INTO `t_at_zy` VALUES ('37306b04-099e-435c-a435-c9bc7a38f26c', '指令管理', '1', '/instruction/showInstructionListPage.action', '2017-04-24 13:40:41');
INSERT INTO `t_at_zy` VALUES ('3887114e-aa18-4a37-b1c0-e4877318a804', '接收指令', '1', '/instruction/showInstructionReceiveListPage.action', '2017-04-24 13:41:58');
INSERT INTO `t_at_zy` VALUES ('3cb57b05-39dd-4e65-9357-a0549f64dc43', '我的专案', '1', '/wdza/*', '2016-12-17 23:43:02');
INSERT INTO `t_at_zy` VALUES ('3cb94818-3f3f-4c8e-b868-bef7bb191731', '高危人管理领导审批', '1', '/highriskPerson/showPersonApproveListPage.action', '2017-04-24 15:33:00');
INSERT INTO `t_at_zy` VALUES ('3cced668-10ad-4ac5-b61e-af10567ad894', '查看指令详情', '1', '/instruction/findInstructionReceiveSubject.action', '2017-05-22 16:18:57');
INSERT INTO `t_at_zy` VALUES ('44333ad6-da63-45a1-a99c-22343ea76c74', '人员WIFI轨迹分析', '1', '/personLocusAnalyze/*', '2017-04-24 15:35:08');
INSERT INTO `t_at_zy` VALUES ('44c4fca6-34d7-4cd8-8d13-c936dbe14052', '社区信息维护', '1', '/community/*', '2016-12-17 23:46:12');
INSERT INTO `t_at_zy` VALUES ('4df1c4ba-6a80-4100-90ff-c8e579146015', '人员积分模型设置', '1', '/integralModel/*', '2017-04-24 15:32:35');
INSERT INTO `t_at_zy` VALUES ('50f3b8a3-f271-470b-939d-5af1e009a3a6', '两抢一盗智能串并案分析结果查询', '1', '/caseAnalysis/*', '2017-04-24 15:33:39');
INSERT INTO `t_at_zy` VALUES ('548b71e7-3f5f-4102-b06c-c90e228d288a', '高危人预警', '1', '/personAttention/showPersonAttentionPage.action', '2017-04-24 11:26:52');
INSERT INTO `t_at_zy` VALUES ('54e12fb6-42a6-4c27-a15c-fe0bc836fe60', '高危人群分析', '1', '/highriskPerson/showHomePage.action', '2017-04-24 15:37:44');
INSERT INTO `t_at_zy` VALUES ('57a7352f-0538-4341-89e7-89f0f2da8a70', '指令反馈页面', '0', '/instruction/showFeedbackInstructionPage.action', '2017-05-22 16:47:54');
INSERT INTO `t_at_zy` VALUES ('5c94bb30-08d0-45d6-957a-4f84f3f9a5c4', '警情分布分析', '1', '/zhzats/*', '2017-04-24 15:37:13');
INSERT INTO `t_at_zy` VALUES ('60aca1aa-9965-40be-876f-bfb74b78dbef', '一人一档', '1', '/yryd/*', '2017-04-24 15:39:26');
INSERT INTO `t_at_zy` VALUES ('63108100-9efc-4fbb-b6ca-c2d2cced9311', '高发警情分析', '1', '/zhzats/showZhzatsGaofa.action', '2017-04-24 11:20:15');
INSERT INTO `t_at_zy` VALUES ('632be624-8e79-499c-9f1d-8699328da7e9', '人案时空分析', '1', '/heatAnalyze/*', '2017-04-24 15:36:35');
INSERT INTO `t_at_zy` VALUES ('639e2498-1516-4e4f-9ee4-57dc8167c293', '刑事案件一案一研判', '1', '/caseJudge/*', '2017-04-24 15:34:41');
INSERT INTO `t_at_zy` VALUES ('65858380-3797-4570-9f93-32b0cdacf818', '资源管理', '1', '/resource/*', '2016-12-17 23:28:43');
INSERT INTO `t_at_zy` VALUES ('6b9ccc78-d9da-43a2-b733-f97826a29a88', '统计分析类规则管理', '1', '/gzgl/*', '2017-04-24 13:43:44');
INSERT INTO `t_at_zy` VALUES ('6eba5add-0982-48bf-b1b5-914e53ad08b5', '刑事案件分析打标', '1', '/caseTag/*', '2017-04-24 15:34:26');
INSERT INTO `t_at_zy` VALUES ('6ed6bc2e-7ae8-4d5e-b495-ee1d7e2f578e', '指令签收', '1', '/instruction/signInstruction.action', '2017-05-18 17:04:05');
INSERT INTO `t_at_zy` VALUES ('6f5b191d-27c0-434b-bf95-f988e6c123f6', '单位管理', '1', '/unit/*', '2016-12-17 23:45:16');
INSERT INTO `t_at_zy` VALUES ('6f7db2d4-90cd-442a-b9a9-ffbd027b90ea', '数据字典', '1', '/dictionaryType/*', '2017-04-24 14:43:25');
INSERT INTO `t_at_zy` VALUES ('75b1d5dd-9791-4b02-a7d5-c42fdc93018b', '两抢一盗智能串并案评分模板', '1', '/scoreTemplate/*', '2017-04-24 15:33:47');
INSERT INTO `t_at_zy` VALUES ('7ae4bec6-29b9-46f4-bbc3-ef9cf7752df8', '警情分布规则设置', '1', '/gzgl/showDistributionRule.action', '2017-04-24 13:44:24');
INSERT INTO `t_at_zy` VALUES ('8045a48e-1213-427a-b46d-cc7841cf9214', '高危人管理', '1', '/highriskPerson/showPersonListPage.action', '2017-04-13 19:31:38');
INSERT INTO `t_at_zy` VALUES ('849906dd-641d-418a-9c49-07be813bd9ad', '首页指令查看更多', '1', '/instruct/showInstructLst.action', '2017-05-22 15:26:13');
INSERT INTO `t_at_zy` VALUES ('852745b3-a278-4018-82a7-92141e5e6a65', 'WIFI围栏监控点', '1', '/deployControl/*', '2017-04-24 15:35:01');
INSERT INTO `t_at_zy` VALUES ('8564c9a4-7cb2-4979-97a4-45ef685e5c6e', '人员管理', '1', '/personManage/*', '2016-12-17 23:45:51');
INSERT INTO `t_at_zy` VALUES ('86911003-9c31-41db-aa59-d56fc6f135b4', '治安态势', '1', '/zhzats/*', '2017-04-24 11:19:08');
INSERT INTO `t_at_zy` VALUES ('87239031-e9bd-481b-a4f4-dc400bc7864d', '数据布控-领导审批', '1', '/personExecuteControl/showPersonExecuteControlApproveListPage.action', '2017-05-18 14:56:09');
INSERT INTO `t_at_zy` VALUES ('87519699-c28e-4383-baee-564d8e6bfa4d', '刑事警情常量设置', '1', '/gzgl/showPenalConstant.action', '2017-04-24 15:36:11');
INSERT INTO `t_at_zy` VALUES ('8b06d96d-a664-4a1e-b3a8-e54a358a80aa', '高危人轨迹分析', '1', '/trackAnalyze*', '2017-05-25 14:41:40');
INSERT INTO `t_at_zy` VALUES ('91b00e62-8152-485d-9303-216c01c6e0fe', '实时WIFI设备监控', '1', '/realTimeWifi/*', '2017-04-24 15:35:15');
INSERT INTO `t_at_zy` VALUES ('a3321349-3b33-4d44-99ce-c089550b1aa4', '角色管理', '1', '/role/*', '2016-12-17 23:30:55');
INSERT INTO `t_at_zy` VALUES ('aad7f6ef-3435-4d26-9fdb-457835d4142b', '综合治安态势', '1', '/zhzats/showZhzatsWelcome.action', '2017-04-24 11:19:22');
INSERT INTO `t_at_zy` VALUES ('c05f51a3-803a-400d-9e6f-f97bc715321d', '接收指令列表查询', '1', '/instruction/queryInstructionReceiveList.action', '2017-05-18 17:01:08');
INSERT INTO `t_at_zy` VALUES ('c6e85281-1012-41d3-97a8-cdcc8051fb05', '权限管理', '1', '/authority/*', '2016-12-17 23:30:19');
INSERT INTO `t_at_zy` VALUES ('cc45340f-2970-492f-9110-d0690ede141d', '指令反馈', '1', '/instruction/feedbackInstruction.action', '2017-05-22 16:48:12');
INSERT INTO `t_at_zy` VALUES ('cd79828b-5e54-401c-a1a3-dfb8ac7e1da2', '用户管理', '1', '/userManage/*', '2016-12-17 23:31:21');
INSERT INTO `t_at_zy` VALUES ('ce9d1f92-c96a-428f-9d9b-9d139da579f9', '刑事案件分析', '1', '/xsfxts/*', '2017-04-24 15:34:11');
INSERT INTO `t_at_zy` VALUES ('d621e36e-0b81-40c8-8f20-f7f9d8310ada', '嫌疑人MAC地址分析', '1', '/suspectMacAnalysis/*', '2017-04-24 15:36:56');
INSERT INTO `t_at_zy` VALUES ('e19df85c-a8a0-4ea8-a363-72a7b88fbc35', '专案资料分类维护', '1', '/zllxgl/*', '2016-12-17 23:42:03');
INSERT INTO `t_at_zy` VALUES ('e25976c5-388f-4865-9beb-a190ee9e97a1', '部门管理', '1', '/department/*', '2016-12-17 23:45:33');
INSERT INTO `t_at_zy` VALUES ('e63d3c38-9506-4587-8e3b-1f8865d00c0a', '一案一档', '1', '/yayd/*', '2017-04-24 15:39:35');
INSERT INTO `t_at_zy` VALUES ('e8f0f537-b59d-485e-aef9-0a23d71fe63f', '指令管理(菜单)', '1', '/instruction/*', '2017-04-26 10:22:12');
INSERT INTO `t_at_zy` VALUES ('fc446838-a9fa-4dfe-b81c-690d3f6197bf', '刑事警情研判', '1', '/zhzats/*', '2017-04-24 15:34:18');
INSERT INTO `t_at_zy` VALUES ('fe3f3b74-fec9-4ad3-ad7e-781f59eb3637', '高危人人案时空分析', '1', '/heatAnalyze/jumpHeatAnalyzePage.action', '2017-05-25 14:34:42');
INSERT INTO `t_at_zy` VALUES ('fefd86d1-d5ed-4126-adab-8cce2b98e89c', '专案角色维护', '1', '/zagl/*', '2016-12-17 23:41:32');
INSERT INTO `t_at_zy` VALUES ('ff52747f-e326-4600-9519-c3430e64894b', '轨迹分析', '1', '/trackAnalyze/*', '2017-04-24 15:37:04');


DROP TABLE IF EXISTS `t_og_zzjg`;
CREATE TABLE `t_og_zzjg` (
  `id` varchar(255) NOT NULL,
  `bm` varchar(50) NOT NULL,
  `mc` varchar(200) NOT NULL,
  `bgdz` varchar(200) DEFAULT NULL,
  `cz` varchar(30) DEFAULT NULL,
  `bz` varchar(250) DEFAULT NULL,
  `jc` varchar(50) DEFAULT NULL,
  `zt` varchar(50) DEFAULT NULL,
  `lxdh` varchar(30) DEFAULT NULL,
  `sjc` datetime DEFAULT NULL,
  `sjzzjg_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK609CD1B9A950914E` (`sjzzjg_id`),
  CONSTRAINT `FK609CD1B9A950914E` FOREIGN KEY (`sjzzjg_id`) REFERENCES `t_og_zzjg` (`id`),
  CONSTRAINT `FKr6suj0f1gpufqmvp1ahvfm0p8` FOREIGN KEY (`sjzzjg_id`) REFERENCES `t_og_zzjg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of t_og_zzjg
-- ----------------------------
INSERT INTO `t_og_zzjg` VALUES ('01', '520199000000', '贵阳市公安局经济技术开发区分局', '贵州省贵阳市经济技术开发区桐荫路12号', null, null, '经开公安分局', '1', '0851-83847110', '2016-02-23 23:12:58', null);
INSERT INTO `t_og_zzjg` VALUES ('069c4e0f-a006-43d6-a9d7-36f488514c7d', '520199630000', '贵阳市公安局经济技术开发区分局三江派出所', '贵州省贵阳市经济技术开发区开发大道109号', '', '', '三江派出所', '1', '0851-83832110', '2016-04-21 16:42:27', '01');
INSERT INTO `t_og_zzjg` VALUES ('0b3d22e9-a71f-4216-b98b-39fd6ef6899d', '520199030000', '贵阳市公安局经济技术开发区分局治安管理大队', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '治安大队', '1', '0851-83801351', '2016-04-21 19:14:54', '01');
INSERT INTO `t_og_zzjg` VALUES ('150bf697-3fa7-41b4-a929-0dc933ba7e5d', '520199350000', '贵阳市公安局经济技术开发区分局政工室', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '政工室', '1', '0851-6798395', '2016-04-21 18:59:30', '01');
INSERT INTO `t_og_zzjg` VALUES ('27602d81-70db-4dba-bb89-28b1114adbe0', '发货', '公司的方式高发', '', '', '', '公司的方式高', '1', '', '2017-01-20 17:30:20', 'b01776e5-59a3-4892-ab1c-85e6a676388d');
INSERT INTO `t_og_zzjg` VALUES ('3ba1e1ab-e937-420c-88a5-cce1d2522cf4', '520199320000', '贵阳市公安局经济技术开发区分局纪检监察室', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '纪检监察室', '1', '0851-83839772', '2016-04-21 19:16:05', '01');
INSERT INTO `t_og_zzjg` VALUES ('3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5', '520199210000', '贵阳市公安局经济技术开发区分局禁毒大队', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '禁毒大队', '1', '0851-83807622', '2016-04-21 19:14:14', '01');
INSERT INTO `t_og_zzjg` VALUES ('3f025ed8-d417-45ac-96cd-af383b3fc431', '5201998B0000', '贵阳市公安局经济技术开发区分局强制隔离戒毒所', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '戒毒所', '1', '0851-83830867', '2016-04-21 19:03:14', '01');
INSERT INTO `t_og_zzjg` VALUES ('585fdd1b-4453-443d-8d28-305368c99728', '520199110000', '贵阳市公安局经济技术开发区分局网安大队', '贵州省贵阳市经济技术开发桐荫路12号', '', '', '网安大队', '1', '15722122069', '2017-01-22 11:37:07', '01');
INSERT INTO `t_og_zzjg` VALUES ('710ec0e8-fe8a-42da-b4e4-353a380be40d', '5201998A0000', '贵阳市公安局经济技术开发区分局行政拘留所', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '拘留所', '1', '0851-83830467', '2017-01-24 17:36:58', '01');
INSERT INTO `t_og_zzjg` VALUES ('76e82277-9ef2-4c01-b223-454048e3db9c', '520199180000', '贵阳市公安局经济技术开发区分局法制大队', '贵州省贵阳市经济技术开发区桐荫路12号', '', '联系电话：0851-86798411； 0851-86798413；0851-86798410； 0851-83830110', '法制大队', '1', '0851-80798411', '2016-04-21 19:10:50', '01');
INSERT INTO `t_og_zzjg` VALUES ('7b8cac4c-2e89-499c-b83e-63defc1da3dd', '520199610000', '贵阳市公安局经济技术开发区分局平桥派出所', '贵州省贵阳市经济技术开发区中曹路44号', '', '', '平桥派出所', '1', '0851-83832757', '2016-04-21 16:38:39', '01');
INSERT INTO `t_og_zzjg` VALUES ('90b2efdf-3e66-4c8d-92ba-33fe5320bdb5', '520199650000', '贵阳市公安局经济技术开发区分局长江路派出所', '贵州省贵阳市小河区珠江路66号', '', '', '长江派出所', '1', '0851-83818110', '2016-04-21 20:42:26', '01');
INSERT INTO `t_og_zzjg` VALUES ('a6c2bcaa-00e6-4d86-a323-9f930ac19103', '520199250000', '贵阳市公安局经济技术开发区分局警务保障室', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '警务保障室', '1', '', '2016-04-21 19:15:30', '01');
INSERT INTO `t_og_zzjg` VALUES ('acd071b4-e055-4688-bf69-132b8b24466d', '520199050100', '贵州省贵阳市公安局经济技术开发区分局刑事侦查大队技术科', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '技术科', '1', '', '2016-04-21 19:07:30', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_zzjg` VALUES ('b01776e5-59a3-4892-ab1c-85e6a676388d', '00007', '贵阳市公安局经济技术开发区分局反恐大队', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '反恐大队', '1', '', '2016-04-21 20:44:27', '01');
INSERT INTO `t_og_zzjg` VALUES ('b4f8b72d-1940-43a4-9518-d6606b8b768a', '520199620000', '贵阳市公安局经济技术开发区分局金竹派出所', '贵州省贵阳市经济技术开发区金竹镇金溪路', '', '', '金竹派出所', '1', '0851-83762560', '2016-04-21 16:40:41', '01');
INSERT INTO `t_og_zzjg` VALUES ('c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c', '520199310000', '贵阳市公安局经济技术开发区分局情指中心', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '情指中心', '1', '0851-3847110', '2016-04-21 18:58:15', '01');
INSERT INTO `t_og_zzjg` VALUES ('c1bc34ff-d881-4a5c-9fbe-e4e4048ae99e', '520199210991', '贵阳市公安局经济技术开发区分局办案区', '贵州省贵阳市经济技术开发桐荫路12号', '', '', '案件管理-办案区', '1', '15722122069', '2016-04-21 19:09:21', '01');
INSERT INTO `t_og_zzjg` VALUES ('c2bb4358-551f-4621-83e9-4aa64181370f', '00088', '贵阳市公安局经济技术开发区分局办公室', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '办公室', '1', '0851-83832121', '2016-04-21 20:46:55', '01');
INSERT INTO `t_og_zzjg` VALUES ('e1048cb4-01fb-4dd6-a668-048cdf3a4567', '520199600000', '贵阳市公安局经济技术开发区分局黄河派出所', '贵州省贵阳市经济开发区浦江路178号', '', '', '黄河派出所', '1', '0851-83848803', '2016-04-21 20:45:26', '01');
INSERT INTO `t_og_zzjg` VALUES ('e55be91b-f2cc-4de6-80cc-d460e3c92975', '5201995C0000', '贵阳市公安局经济技术开发区分局巡（特）警大队', '贵州省贵阳市经济技术开发区浦江路178号', '', '', '巡（特）大队', '1', '0851-83858110', '2016-04-21 19:17:27', '01');
INSERT INTO `t_og_zzjg` VALUES ('e5be6a62-4cbe-468e-9cf4-205b3abaea23', '520199640000', '贵阳市公安局经济技术开发区分局大兴派出所', '贵州省贵阳市经济技术开发区清水江路东段211号', '', '', '大兴派出所', '1', '0851-83481110', '2016-04-21 16:35:46', '01');
INSERT INTO `t_og_zzjg` VALUES ('e7c5276c-94e3-4290-be7f-9a91e84037e6', '520199020000', '贵州省贵阳市公安局经济技术开发区分局经济犯罪侦查大队', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '经侦大队', '1', '', '2016-04-21 19:07:30', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_zzjg` VALUES ('ec3a33b2-7ff2-4a53-b269-ac529187c517', '2311', '32160', '', '', '', '32160', '0', '', '2017-01-23 15:37:24', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_zzjg` VALUES ('f1218a34-d2aa-43da-a9cf-1475a15dc3ad', '520199050000', '贵阳市公安局经济技术开发区分局刑事侦查大队', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '刑侦大队', '1', '0851-86798492', '2016-04-21 19:13:19', '01');
INSERT INTO `t_og_zzjg` VALUES ('fc4376c0-ba88-4958-87da-f3498281839b', '520199010000', '贵阳市公安局经济技术开发区分局国保大队', '贵州省贵阳市经济技术开发区桐荫路12号', '', '', '国保大队', '1', '0851-83809651', '2016-04-21 19:07:30', '01');


DROP TABLE IF EXISTS `t_og_bm`;
CREATE TABLE `t_og_bm` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA0BBB087FF68B483` (`id`),
  CONSTRAINT `FK5695c5hton0y7fmpiw1j7r72a` FOREIGN KEY (`id`) REFERENCES `t_og_zzjg` (`id`),
  CONSTRAINT `FKA0BBB087FF68B483` FOREIGN KEY (`id`) REFERENCES `t_og_zzjg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of t_og_bm
-- ----------------------------
INSERT INTO `t_og_bm` VALUES ('150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_bm` VALUES ('27602d81-70db-4dba-bb89-28b1114adbe0');
INSERT INTO `t_og_bm` VALUES ('3ba1e1ab-e937-420c-88a5-cce1d2522cf4');
INSERT INTO `t_og_bm` VALUES ('a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_bm` VALUES ('acd071b4-e055-4688-bf69-132b8b24466d');
INSERT INTO `t_og_bm` VALUES ('c2bb4358-551f-4621-83e9-4aa64181370f');
INSERT INTO `t_og_bm` VALUES ('e7c5276c-94e3-4290-be7f-9a91e84037e6');
INSERT INTO `t_og_bm` VALUES ('ec3a33b2-7ff2-4a53-b269-ac529187c517');



-- ----------------------------
-- Table structure for t_og_dw
-- ----------------------------
DROP TABLE IF EXISTS `t_og_dw`;
CREATE TABLE `t_og_dw` (
  `fl` varchar(50) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA0BBB0CFFF68B483` (`id`),
  CONSTRAINT `FKA0BBB0CFFF68B483` FOREIGN KEY (`id`) REFERENCES `t_og_zzjg` (`id`),
  CONSTRAINT `FKguhdvpidaeyi85k6gsa1u2wax` FOREIGN KEY (`id`) REFERENCES `t_og_zzjg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位';

-- ----------------------------
-- Records of t_og_dw
-- ----------------------------
INSERT INTO `t_og_dw` VALUES ('0', '01');
INSERT INTO `t_og_dw` VALUES ('2', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_dw` VALUES ('1', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_dw` VALUES ('1', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_dw` VALUES ('3', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_dw` VALUES ('1', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_dw` VALUES ('3', '710ec0e8-fe8a-42da-b4e4-353a380be40d');
INSERT INTO `t_og_dw` VALUES ('1', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_dw` VALUES ('2', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_dw` VALUES ('2', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_dw` VALUES ('1', 'b01776e5-59a3-4892-ab1c-85e6a676388d');
INSERT INTO `t_og_dw` VALUES ('2', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_dw` VALUES ('3', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_dw` VALUES ('SJ0012002', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae99e');
INSERT INTO `t_og_dw` VALUES ('2', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_dw` VALUES ('1', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_dw` VALUES ('2', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_dw` VALUES ('1', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_dw` VALUES ('1', 'fc4376c0-ba88-4958-87da-f3498281839b');


DROP TABLE IF EXISTS `t_og_ry`;
CREATE TABLE `t_og_ry` (
  `id` varchar(255) NOT NULL,
  `jh` varchar(50) DEFAULT NULL,
  `xl` varchar(36) DEFAULT NULL,
  `sfzh` varchar(50) DEFAULT NULL,
  `xm` varchar(36) DEFAULT NULL,
  `mz` varchar(36) DEFAULT NULL,
  `bgdh` varchar(30) DEFAULT NULL,
  `qt` varchar(36) DEFAULT NULL,
  `zzmm` varchar(36) DEFAULT NULL,
  `zw` varchar(36) DEFAULT NULL,
  `xb` varchar(36) DEFAULT NULL,
  `pxh` int(11) DEFAULT NULL,
  `zt` varchar(30) NOT NULL,
  `yddh` varchar(30) DEFAULT NULL,
  `sjc` datetime NOT NULL,
  `org_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA0BBB283C3494FBE` (`org_id`),
  CONSTRAINT `FKA0BBB283C3494FBE` FOREIGN KEY (`org_id`) REFERENCES `t_og_zzjg` (`id`),
  CONSTRAINT `FKctbf68771nonxq1vlbavaubcj` FOREIGN KEY (`org_id`) REFERENCES `t_og_zzjg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员';

-- ----------------------------
-- Records of t_og_ry
-- ----------------------------
INSERT INTO `t_og_ry` VALUES ('00adabdd-3104-4349-8ff5-a1860c72d9eb', '042072', null, '52242719861224261X', '赵兴', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('01b3c18d-ffde-41d7-a75d-80ebe5bcc1ca', '042074', null, '522522198401124246', '蔡辉', null, null, null, null, null, '', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('023a6e91-73b9-4f39-b5a0-060f052d3e8d', '042094', null, '412824199010141039', '邵帅', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('030cc136-cab8-4d18-8b7d-45361e3ebcc2', '006406', null, '520102195804076645', '陆建萍', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('039be972-b615-4c66-8c92-8b64ab3e8c3d', '006132', null, '52010219691015201X', '沈涧', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('04eb62e2-f06f-4e8b-a57c-537bf6dc946c', '035256', null, '520102197603235813', '蔡剑', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('0699ad67-a4e2-4244-93fc-fa27babbaad8', '006419', null, '520111196703270618', '冯琪', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('07564d7c-6fb4-4bfc-96b0-21a7130c03ee', '031630', null, '522328198001144114', '冯川', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('077da105-07e3-4529-bf69-3295e729a403', '006478', null, '522425197508273216', '王渊', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('09eb2294-0b9d-4e6a-8e00-3ae9e15f67d5', '042053', null, '520111198301163317', '熊江丹', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('09fcb7e4-5e37-486d-8487-1c78c839ff41', '042088', null, '520122199002030024', '刘璐', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('0a98757b-dd3b-4f91-8617-980c102c60fc', '005802', null, '520102197102072411', '王刚', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('0ab91827-6a5b-4752-a600-6d81799db03c', '009910', null, '520102196204055829', '陈娟', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('0b2d503b-8758-4692-ab8f-ab33576308ec', '042077', null, '522321198712080837', '刘光平', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('0b6cb5b8-5cf0-4f58-948f-96dfa09c5e43', '042025', null, '522627198811282658', '刘兆源', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('0c226a1c-3d30-4332-8618-0c7708151cb0', '030486', null, '522731197710080198', '李鑫', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('0d30e466-8d58-460c-8c11-582adbf87674', '037085', null, '522524196502230551', '胡明发', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('0f4adf6f-f2e9-43c8-8a8a-fea548679777', '037156', null, '520102195808253039', '张沏', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('0f84e080-2ed1-4a33-aeb7-44cf00d33be7', '042024', null, '52212919870811001X', '戈兵', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('11103c9c-6861-4aca-af39-e39bbd95c6a1', '009257', null, '520114197901050417', '王可', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('12bfcf0c-78ae-4213-b15b-65e0685ff14f', '042036', null, '520123198702180084', '吴容欣', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('12d74348-533f-4509-b25a-beff8d93f2c4', '004954', null, '520111195911020010', '倪奎', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('1325b7e1-4476-4ead-af4a-9a11bca731cd', '037104', null, '520111197610230056', '罗吉松', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('16388d33-f505-4b15-a7d2-c1a291561fc5', '004968', null, '522525196306268831', '林金根', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('16fd5e84-1065-4cfd-8c36-aeacab679850', '004888', null, '520103197807121240', '涂晓梅', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_ry` VALUES ('176497fd-44f4-4cbb-b987-cd350ffaaa39', '006451', null, '520102197001025034', '刘乙', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('178936b8-bf51-4044-9d95-ab7003f2431c', '009912', null, '520111197411050618', '罗华林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('183623d0-686c-44c6-9fdf-9139a79ba553', '030497', null, '520111198008020632', '朱文珂', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('19148123-92bf-4e0b-b72b-3526384ea6a8', '042080', null, '520103198610281616', '邵崑', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('19801036-c99e-4026-b218-2d64fbbdae44', '035244', null, '520102197404262421', '石婷', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_ry` VALUES ('19a4ae31-9768-445d-a0ab-1c5df3e4aa69', '037081', null, '520114197801170438', '卜小强', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('1a46e05f-054f-4a17-85c1-ba5f7909e94c', '006431', null, '520114197607040410', '王晓俊', null, null, null, null, null, '1', null, '1', null, '2016-02-19 16:32:41', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('1a4ceb84-619e-459a-b6e6-fe125230c94a', '042079', null, '522626198604300116', '邓毓', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('1ab155c0-a4ff-4212-894d-ba2891e76e21', '035291', null, '520112197608152419', '付安政', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('1b67b86d-e137-47b5-b608-1c87914e91a1', '042020', null, '522729198801154212', '石燕飞', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('1b91a0d3-8bb2-4eea-b9bd-15b7171b4137', '006429', null, '52010319500406081X', '陈兴武', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('1c24944a-ba0c-41af-b443-265b2751173f', '006443', null, '520111195504080024', '杨娅菲', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('1e22b89d-fd44-4674-8fb9-9f1b28850ffc', '042033', null, '520114198509070012', '龙飞', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('1f0362d2-5d07-404c-b231-618e4d9268d4', '042065', null, '522222199103170015', '李知君', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('1f10437c-9d85-4fa9-b28e-61d45a729eed', '042014', null, '522701198001050726', '王飞', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('202c668d-6f3d-4cd7-9eb3-0db987af49af', '006426', null, '520102197608156639', '袁隆', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('20a1387e-d6e0-4d95-b904-4698f2f63e43', '004925', null, '520114198211020012', '王晨星', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('20c27876-2382-4381-97ce-fb25c5f05b65', '037579', null, '522501196607011625', '吴红', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '3ba1e1ab-e937-420c-88a5-cce1d2522cf4');
INSERT INTO `t_og_ry` VALUES ('21153951-d5b3-4a30-bfa6-472be252b6c9', '037091', null, '520102196604196612', '杨国军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('223d253f-8f2b-4776-90da-10471bbcf568', '006394', null, '52011119680510061X', '王亦林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('2271fe9e-6475-4df1-9943-97e0f634e5d1', '035260', null, '52010219800924202X', '张敏', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('22a56f6e-ff37-4206-9058-590d77c467be', '037079', null, '520102197605056632', '蒋辉', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('23eb605b-828a-4b63-8e21-8d62e8856c5d', '004966', null, '52252119710215417X', '张鲁', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('24536bb6-b255-4eb0-af9e-f1d57903f183', '035377', null, '520102197908035820', '范燕玲', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('24aef2fc-f671-4d55-858b-7cc3e11dd165', '004889', null, '520102196410011253', '邱兴明', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('250a9e70-f47e-4161-8ef4-3ead5bf4c52d', '042063', null, '420621198301066839', '刘明明', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('2543b564-b144-4e8b-8171-862e03b05117', '006007', null, '520102197003046613', '杨勇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('267df216-b120-412d-834a-909d338cc6ac', '006445', null, '520111195910210031', '陈阳福', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('2771f98b-2b30-4331-b7b3-d71af27d5d8f', '004951', null, '52213219880826361X', '龚天雨', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('293b3890-ebaa-445f-b30f-c1aabc761165', '037103', null, '522524195810101298', '段红光', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('2ab42fd4-85d1-48e2-96e9-8c9ceec434c3', '035295', null, '520102197606126612', '王斌', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('2b24eb02-2b5e-458f-b885-e9c80246b183', '036403', null, '522526198208250019', '孙林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('2bd48ce6-92e7-411b-acac-4170d1ee1015', '005922', null, '520102197012133014', '刘军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('2c59af07-6627-43d0-9eda-4cdcb9e03a92', '037102', null, '522524196812310565', '罗萍', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('2d6b269e-6926-4a55-87c8-a8808f307990', '999934', '0', '520181198706442621', '办案区民警1', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('2d6b269e-6926-4a55-87c8-a8808f307999', '049907', '0', '520181198706442630', '网安大队民警1', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('2d6b269e-6926-4a55-87c8-a8808f307a89', '042007', null, '520181198706052630', '范海', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('2d82a7af-7b33-4560-9789-4ec90bf6d9c8', '035241', null, '520102198110053418', '王勇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('2e415d87-e1e4-4fb4-adbe-fd94eac8bb1a', '006463', null, '520102196502216619', '刘大兴', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '710ec0e8-fe8a-42da-b4e4-353a380be40d');
INSERT INTO `t_og_ry` VALUES ('2e85b805-1837-4712-96ce-7fe52c1c9cc4', '004961', null, '520102196201293418', '张宇辉', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('3009e240-e910-4bd5-9aed-fde3fab67646', '037090', null, '520102196104256615', '李玉江', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('30cb8a19-8f50-4842-8872-a93b3c3bbaf5', '037601', null, '522528198109010047', '周静', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('31d284e0-71a0-4a2c-987e-81777b6cec6b', '035284', null, '522523196301270403', '陈莲珍', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_ry` VALUES ('325f215b-52d1-4e21-89c0-8ce8ddb0b205', '042096', null, '421123198601295314', '周坤鹏', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('32ca4b2f-9752-42c9-82de-ba01df66478b', '006417', null, '520103195909230414', '杨军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('3368213a-c9a1-47e9-bcd7-bb1ff0570291', '042058', null, '511023198412250666', '袁诗梅', null, null, null, null, null, '', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('33710c16-8ea5-4f1c-8eff-169486895f19', '042066', null, '520114199001190069', '彭樱姿', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('34109171-32c4-481c-a9a0-26fd701b3fe4', '006143', null, '520103197106220430', '丰伟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('34373c18-b945-4d21-8d45-83f50095be2a', '042046', null, '520103198704221622', '唐莉', null, null, null, null, null, '', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('3483bd5f-db91-41c0-9404-256ded560b14', '035242', null, '522523197203160117', '崔鹏', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('348d5a6c-44d6-4555-81ce-835f7286dbe4', '030487', null, '520111197506180044', '杨婷婷', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('3540b9fb-3680-41d8-8db4-8e88a677684d', '006470', null, '520102197605164617', '张勇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('3546a9ec-a5e9-4f32-9db5-55843e7e065c', '006474', null, '520103196403161612', '冷丛洪', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('3573939b-48fd-4593-a4d2-0f6d23732fa7', '042100', null, '522325199107030014', '王威', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('35913e01-ab19-47dc-8f6e-8370143322b9', '037087', null, '520114198211260411', '王伟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('3714f10f-d445-43a4-86cc-b42aec29e541', '042043', null, '520102198411061219', '张锃谅', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('3736ce29-2a67-41b6-82dd-de5ccf810824', '006392', null, '520114197912290416', '郑又源', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('38720f34-7db3-40b4-8975-78976c784b3a', '006472', null, '520102195809257015', '陈建国', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('38780d84-ff61-4f07-a70e-08ddef683c2b', '005299', null, '520103197710255226', '杨英识', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('39500dd1-8ed7-41d1-a8d7-b36398f75251', '037083', null, '522501197208010814', '杨涛', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('3c4cc83a-15a3-473a-9ba1-968f86752881', '006450', null, '520102197610266626', '聂祥敏', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('3e7565a8-8a1d-44a8-b632-1180b451c82b', '035251', null, '520111197505260034', '廖一龙', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('3e76bde9-1ad2-4b38-ad6d-b2105b28bcfa', '006423', null, '520111197411090644', '龙艺', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('3e86d685-5603-4738-9355-913add21d70a', '005582', null, '520103196105030016', '文发春', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('3ea02a0a-5232-4bc0-b9f5-133184de8af2', '004958', null, '522525195806238917', '王洪利', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('3f4c72dd-2fcf-4fdd-8649-1b56eaad9b17', '007555', null, '520102195710264215', '张友苏', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('3f5c1866-2da2-4c22-b9e5-4570b43d9d2f', '035363', null, '522521197003034180', '潘红', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_ry` VALUES ('4136e486-ada3-4fc6-8506-28bcb8879ce4', '035271', null, '520114197804100021', '刘晓亚', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('413a23ed-052d-4701-8ce0-c638dbe8fbff', '006441', null, '520102196612120416', '夏林刚', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('413ef7bb-67bb-4588-a292-908df64288c9', '004965', null, '520102195801246610', '孙海洲', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('426801ac-9c42-419f-981c-a2928871cae3', '035355', null, '52252919760426701X', '裴放', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('430899b9-21a9-4e74-837e-b87b4ea7b20b', '035276', null, '520102197702192011', '熊远明', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('4395fa0c-c5e8-403b-8d75-0918203bd764', '005923', null, '520111197412260617', '王辉', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('43d815df-7c0a-4c00-9777-5d9af8944b8d', '006388', null, '520102196101206612', '刘运书', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3ba1e1ab-e937-420c-88a5-cce1d2522cf4');
INSERT INTO `t_og_ry` VALUES ('4436ae6c-a2e1-4ac1-be7a-48529cbd902c', '006456', null, '522526198411042418', '谷彦君', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('458fca61-873f-48a5-ab9a-bb04892eae59', '009917', null, '520102196607074282', '李萍', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('47abceb6-4fd6-4325-bb58-77d116121d5a', '042003', null, '522228198701030016', '何杰', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('4817ffb0-e644-4817-8c32-36ae5f8f6224', '004957', null, '522423196309035218', '唐堂', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('481914b4-88a7-421d-81cc-7967d9a0413d', '042095', null, '370181198803112713', '刘金滨', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('4a624c60-54ba-4b51-8756-9e3f8a57aead', '009259', null, '520102197209105017', '任大东', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('4a8b7131-dd21-4eab-a7e7-2ba32c25c553', '035281', null, '520102197407114635', '张毅', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('4a956261-56bf-45d4-8d63-d07f664d1448', '006477', null, '520103197707151215', '杜军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('4ade3972-1f72-451e-a9ce-2b73929f6aaa', '042029', null, '520103198905192813', '武志强', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('4ba70e6a-2df0-4eec-a6ac-b93fa271160b', '042062', null, '530381198606041718', '刘箭', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('4bae5541-50d3-4eca-a555-c13210c77ced', '042034', null, '520111198703095417', '周松', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('4c593029-1480-4ab8-9598-442748162814', '006446', null, '520102198608140818', '蒋远海', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('4e1fa6f1-2b15-428b-a0e3-217812e6b74d', '042091', null, '520114199104080428', '陈翔燕', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('4e8423dc-eb9d-4e53-aa68-0a83dd37dad4', '005825', null, '520103197312225216', '刘军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('4e9e9f43-9f5a-4aff-a0e0-51bc01ac9e47', '006403', null, '520113197608020415', '王宇翔', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('4f0c3c29-c97b-4b4f-9820-82bac7fcc8e1', '037089', null, '520102195801056614', '胡继贺', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('4f4d90c5-4a74-49eb-a715-b8dfd37ca4be', '004964', null, '522501198009081219', '王伟伟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('4f799db2-bd25-4005-8cae-97b7e72acd02', '042092', null, '522428198904145017', '王磊', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('506be8c5-7429-44df-a751-d831bc7a718d', '042073', null, '520103198612264027', '张昕燕', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('5114147d-194a-4485-9dd0-0ae8972db32f', '042049', null, '522425198406110170', '杨万', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('512047d8-9f57-4a32-8e6f-747954826f19', '005852', null, '520102196601287017', '关露阳', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'fc4376c0-ba88-4958-87da-f3498281839b');
INSERT INTO `t_og_ry` VALUES ('5155e0e8-4d31-4e39-b1dc-9af7a3558c70', '042061', null, '522401198412152712', '简成润', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('51dd2f21-35f7-4c50-9aa6-505bbc0ef6ab', '037095', null, '522524195503080554', '张绍平', null, null, null, null, null, '1', null, '0', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('54761e5e-184c-430f-96a9-922c42357eef', '006453', null, '522524197612283552', '石华', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('55201503-0043-4036-ad16-3512cb1b8053', '017021', null, '520181198601270439', '区杰', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('55487903-1ff7-4e0f-85b6-e065f5a99fcd', '006409', null, '522525196812168921', '陈蓓', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('56eb0466-4267-43e0-a154-0068156c5f9c', '006401', null, '520113198705120412', '贺文君', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('58c75254-58e3-4ffe-b2ef-2246d841d28a', '042010', null, '522524198010081237', '刘德林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('591581de-558e-4078-8240-b958bac531ee', '006468', null, '520111197612240610', '孙煜', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('5a341f21-b6a8-4919-a90f-9b9f1a12c250', '006136', null, '520102196909022410', '申友胜', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('5b84d688-6e3f-4839-b5fd-47fde11c882e', '035277', null, '520102197301281638', '张力', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('5c806458-e61b-4ab3-80e4-0df0b5ea29c8', '030490', null, '510522196703201937', '任义前', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('5de8076c-425b-4ba4-bac7-e0bdaad32341', '042019', null, '520114198806280032', '罗文', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('5ef46c94-5043-4168-ac7b-bce70e18db73', '042021', null, '520102198508103411', '杨洋', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('5f4d6f9e-a3e7-4d9b-8291-ef16323c9b43', '006461', null, '520102196905205826', '姚贵芬', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('61bfda57-ff3c-4546-81c8-2038bbabbb8b', '035270', null, '520103196207164015', '刘龙', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('629f0acd-fdc2-4faf-853b-dcc564eb58bc', '005955', null, '520102197112045417', '成珍禹', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('63067ea1-617b-4379-998d-945a6d000557', '037082', null, '520102197001036614', '褚建刚', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('6327c862-4581-4fa7-9257-e64f302f1fdf', '037094', null, '522524196305241294', '罗福敏', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('657668bd-4b88-4410-9787-aba92a9f1239', '042032', null, '520202198612092017', '黄康龙', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('660fe75d-eca2-4dd9-88d6-124fbbb4903f', '004959', null, '52010219590822661X', '刘辉', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('664aab18-3daa-406e-b37c-b36a4b61461d', '042035', null, '522725198708150094', '何建刚', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('6687ba00-31df-4e72-a5cd-091d52a81bd7', '042002', null, '522327198509071618', '王应魁', null, null, null, null, null, '1', null, '1', null, '2016-09-19 11:28:56', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('67152e78-cdf3-4d30-a937-d479fef73f5b', '042022', null, '370421198204292214', '苑建波', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('675a0ead-3eeb-4faf-8b10-4eb9b1fe0de7', '042057', null, '522401199002016616', '彭彪', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('68fefc96-51ef-4742-acde-7b9a8263d417', '037105', null, '522525195812027729', '王鲁平', null, null, null, null, null, '2', null, '0', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('69ae27e8-1ec5-4750-96e0-4d2be603a857', '006464', null, '52011419730301001X', '孟征', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('6a1589df-94c1-4db8-b0ca-70729165b7d5', '042042', null, '52010219880813122X', '陈璐', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('6ba2d9fe-2b7b-44be-9f6b-042a694c5ce2', '042048', null, '522625198806270027', '罗瑞', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('6d3a231c-dbec-47dd-af94-8c53c7205bbf', '006449', null, '520111196611170097', '张劲勇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'fc4376c0-ba88-4958-87da-f3498281839b');
INSERT INTO `t_og_ry` VALUES ('6d8a5ac4-557e-4025-ac0f-74c673d93cf9', '777777', null, '520102196011036138', '张建黔', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('6dd5b3b3-9807-45b2-89b7-0c7153bf5bb7', '037077', null, '520102197204176617', '龙刚', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('6dd93e5f-7651-4d5d-b14b-f6a07f477736', '035264', null, '52011419770403002X', '陈佾红', null, null, null, null, null, '2', null, '1', null, '2016-08-25 17:16:23', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('6e114685-9f91-4097-beeb-76a82085fba8', '008457', null, '520111197310120648', '王小华', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('6eb497a2-1331-4894-bca3-1befa30ec924', '035246', null, '52011119740604001X', '唐家庚', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('6f4d7079-e7b5-4924-8b93-eac6ce8973be', '035280', null, '520102197905022418', '张辛', null, null, null, null, null, '1', null, '1', null, '2016-08-25 17:16:23', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('6fcc9f7f-2a30-4819-a86f-a06742ab88eb', '006418', null, '520102197112121221', '刘芳', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '3ba1e1ab-e937-420c-88a5-cce1d2522cf4');
INSERT INTO `t_og_ry` VALUES ('731b0c13-8919-4381-8023-989b03fe64e2', '035272', null, '520113197712040811', '马骏', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('747886bc-5dfc-4eb4-8393-25dcf75d6677', '042026', null, '522729198705070019', '王俊', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('74c2d307-8fa1-4727-8832-0fe1f0ee52cd', '006047', null, '520111197407300610', '赵文学', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('75020212-2fc4-4bbf-bde5-af437fa3c6be', '006397', null, '520114197405190015', '聂祥运', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('752f9703-4ba1-4d91-96f2-cbff90a6cc0c', '042031', null, '520103198812206411', '周航丞', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('7795f9f6-05ec-4eae-a1e3-3bbe44c70d26', '035249', null, '52011419790718001X', '殷赵军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('78b34105-76e7-474a-89be-36f501cf1cb9', '035258', null, '520103197610170815', '黄彬', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('794b1f80-0af9-41f4-82de-a3787ba77f74', '035173', null, '520102198204242420', '糜盈', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('7a6c416f-cb8e-4882-8487-e1e9c1e65678', '042056', null, '520202198510105913', '刘波', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('7bc08dbc-0e73-44e3-b027-2817fcb0bf32', '037074', null, '522501197007170811', '廖全勇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'fc4376c0-ba88-4958-87da-f3498281839b');
INSERT INTO `t_og_ry` VALUES ('7cdcb99e-3f40-4d9c-946f-b7a393f3f5f4', '042097', null, '530321199107280050', '梁应泽', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('7ce2c18a-cb88-49e8-8070-3b90960cdc17', '004755', null, '520114198312040418', '项伟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('7edeface-0d48-4495-bcfc-9f33e4180ee1', '006436', null, '520121198802204212', '李宇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('7f2d0c14-264c-47f9-9d1b-c1d2bc1a8c4e', '006422', null, '520112197003100037', '周晓勇', null, null, null, null, null, '1', null, '1', null, '2016-03-01 14:35:38', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('7fa902ad-eb73-4928-b266-be5efeffc60d', '006432', null, '220102196810153356', '姜晓亭', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '710ec0e8-fe8a-42da-b4e4-353a380be40d');
INSERT INTO `t_og_ry` VALUES ('7fcd875f-d753-43f3-bab7-ad6294bb8a62', '042071', null, '522125198506193739', '舒瑛', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('7fd2417a-95e0-47be-8606-3696f24027a9', '004753', null, '522731198202230027', '刘鑫镁', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('80dc5b44-1578-4a7f-91b8-645c4aaf9dcd', '006410', null, '520111196704270652', '李克峰', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('81c1ebb7-d589-4aca-9785-5eaa661b9993', '009941', '0', '', '情指中心民警1', '0', '', null, '0', '2', '1', null, '1', '', '2017-03-17 16:34:18', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('8217c793-3742-42e3-bb3d-5c1d9b878e60', '042075', null, '522401198607128229', '唐敬靖', null, null, null, null, null, '', null, '0', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('82b7062e-d535-433a-a19f-d863524cb068', '004795', null, '522423196212135212', '唐义', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('82d6ee5a-0c4c-429d-8722-6aae2505e0ff', '035248', null, '520113196606202413', '汪少平', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_ry` VALUES ('830fff8a-db0a-4041-a50a-ee83907f7e05', '035279', null, '520102197407011230', '张浩', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('831feab5-2635-4d3f-9f7c-2f99a4e95812', '037098', null, '52252419591028129X', '龚建国', null, null, null, null, null, '1', null, '0', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('862050b9-2e65-427a-8258-e553d9ae3a7f', '004751', null, '520114198111130011', '马宗祥', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('86d3dbd7-8cc3-45a1-b102-f72819001808', '042013', null, '522501198604230864', '安智', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('880fc689-5d0f-42d6-9e72-a744a889f074', '004917', null, '520111197305220011', '秦毅', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3ba1e1ab-e937-420c-88a5-cce1d2522cf4');
INSERT INTO `t_og_ry` VALUES ('882ac1fe-50b9-4d85-a59f-edfbaab9839b', '035250', null, '520114197211150015', '张军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('882be32d-411e-419e-ba49-14d6896a28b6', '042083', null, '522426198402055932', '彭忠永', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('89c09d03-3266-40fc-86b7-4ed5322ed82a', '123456', '0', '', '潍坊市', '0', '', null, '0', '0', '1', null, '1', '', '2017-01-19 11:13:09', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('8b520f54-fccc-4a0b-ba7c-c690dc81893f', '009258', null, '520113197712040053', '王健', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('8bfd666a-4f32-4bb4-8bec-bff4817a2def', '035257', null, '52010319751204281X', '朱海涛', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('8cdac137-dc25-4db2-a27c-294268cf248e', '042023', null, '522227198806120011', '冯桂林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('8d83913b-a0a4-42d0-91cd-3f3e8cb9c2c2', '006400', null, '520102196112236614', '杨健', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('8ebd83b8-7162-42c9-ab2f-ab2d158f37fe', '004752', null, '520111198401160615', '郭军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('8f057bec-d583-428b-b9cb-e21f8e231909', '004798', null, '522228198902081717', '杜美', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('922efdb1-57a4-44c6-8d01-c4466176819c', '004955', null, '522524196405231296', '徐勇浩', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('92846ac2-b23f-49a6-8073-35bb1bfc9f58', '042068', null, '522130198702283222', '蔡珊', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('936262da-ca86-4bdd-9978-40955562279f', '042045', null, '431121198909177751', '李枫', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('95427b34-e105-4733-a623-7808712816fd', '042004', null, '211122198311053311', '任大河', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('95b6ed59-75e6-42ca-b906-70114b22aee9', '006149', null, '520112197105103212', '陈昆', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('9647a534-9965-4e02-b8d6-634c54ef0742', '035252', null, '520111197210170912', '阮期敏', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('96d95c0a-be1b-40c4-b4db-6ddf197bbc3c', '111111', '0', '', '测试', '0', '', null, '0', '0', '1', null, '1', '', '2017-04-14 13:56:20', '01');
INSERT INTO `t_og_ry` VALUES ('96efc037-f540-416a-a260-8f97fd7c9b25', '006430', null, '52011119540716121X', '汤永江', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('9742f572-6175-4658-9cf9-7c86acf8ca91', '037092', null, '520102196512096614', '刘长华', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('97b7ac0b-b11d-4b40-8ec3-079a9a687926', '042005', null, '341222198906280059', '付强', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('97cb518a-6ae0-44d1-b024-7397bdca7ff9', '006386', null, '520114198202280429', '张薇', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('9887dc2c-ec15-43a0-8f66-85f249ab6c4d', '042011', null, '522701198411032213', '陈宇耕', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('99aa3b04-10db-4a08-a058-a6feb1a5e0ce', '037078', null, '522521196812084175', '郑海', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'fc4376c0-ba88-4958-87da-f3498281839b');
INSERT INTO `t_og_ry` VALUES ('99cb8d0a-46fb-497a-9598-57b905f70452', '042040', null, '520121199003071286', '钟菲', null, null, null, null, null, '', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('9a0ce443-2758-4534-8ac5-35e2b98c178a', '035283', null, '520102197406304613', '班华', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('9b187e23-5af2-450f-b268-7f377a00a6a5', '008544', null, '520111197409190611', '王功贵', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('9b4becb2-733d-4dff-b6c9-bbead2a15b95', '042030', null, '52010319881105441X', '曾麒麟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('9c3380e6-dc54-465d-9401-7ca8db1a5ce1', '004956', null, '52242319620215521X', '罗新安', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('9f0b76c0-841e-433b-a852-dcc5743e9091', '042006', null, '520114198607230438', '洪一峰', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('9f9e692c-fbe0-46b7-aedd-d26e952a79c5', '042060', null, '522123198612153025', '杨晓雪', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('a22dadd0-c63f-465d-9a2e-2daf94266a18', '037602', null, '522426198512040117', '朱宏', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('a3703706-9be0-4657-83b0-c4c598e21d26', '006393', null, '522425195704178837', '李红林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('a42b4d1e-60ca-4d28-b702-6b0de8882fc2', '006092', null, '520102196809103432', '王永新', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('a597001f-6e7d-4ed3-b1a4-808bfb9e0e9e', '037099', null, '522423196911175221', '沈薇', null, null, null, null, null, '2', null, '0', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('a69fe84d-52e8-4ed3-9210-6867147dd336', '037097', null, '520114198411060414', '王祥', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('a74a463d-0cc4-4c5b-af88-2e4c543791fc', '037084', null, '522524196401280592', '刘训澜', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('a819d915-212a-4e0a-9ae4-e5d20576b95b', '009387', null, '52242119640910082X', '齐玉芳', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '710ec0e8-fe8a-42da-b4e4-353a380be40d');
INSERT INTO `t_og_ry` VALUES ('a85b4b02-9ba2-4476-8ee4-18c7353be50e', '035290', null, '520114197705200019', '陈鹍', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('a85b9449-7334-471b-9be3-dae09bf9c1bf', '006425', null, '520102197001052438', '姜勋强', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('a90ddee8-73ea-4fc7-b381-51d98a9274b5', '004963', null, '520114196309230414', '罗振兴', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('a93ba071-ef8e-46f0-959c-980957d7bc57', '004960', null, '520102196003256616', '鲁富铜', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('aa330964-c34c-4701-be0b-74fa0c561fb3', '006151', null, '520102197001164237', '涂利军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('aa39250d-07f6-42a2-bbd9-47f0adc1c9dd', '006111', null, '520102197310055018', '沈灵良', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('aac2064f-7fd7-454a-9af9-aba829909d4c', '006462', null, '520102197701023021', '孟庆玥', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('ab2bef60-7070-40ca-b40a-3d5ee6543d07', '042047', null, '520114198909190021', '兰岚', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('abf810a5-1147-4111-a9b9-0c62c5b99e44', '042085', null, '522526198405021813', '朱坤', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('abf904b1-49ab-4802-b638-b4107222f6e5', '009263', null, '520102195809180417', '黄承林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('ac0f65da-b636-4b09-a78a-369d3c71f1f3', '042086', null, '522124198502080810', '黄焱林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('ac29570d-e2d4-4486-909c-e10e90337049', '042067', null, '520103198910184041', '邓贤', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('ac4cb788-4de9-4f72-8110-0ef366929c15', '006405', null, '522525196012018917', '骆勋', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('ac5418de-1596-41cf-975b-f6c25c655a73', '005727', null, '520103196708116716', '丁辉', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('ad156d2a-3e0e-4fdb-8e88-e2d0d5720d86', '009911', null, '520103197910075211', '苟冰', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('ad692822-008c-4db3-aa1c-ebec3c035a5b', '004969', null, '522423195912175210', '邓如山', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_ry` VALUES ('ada779e1-1c0b-47e3-8bd5-d47ea05322e0', '006424', null, '520111197603050612', '徐庭龙', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('ae8dd1ab-a00c-497e-ad2b-f8f9c20bd7b8', '037093', null, '520102198209157014', '李伟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('afe9cc76-75db-4314-8a4c-f1312173edc1', '004793', null, '420621199005102772', '赵朋', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('b0a88506-81c4-408a-b097-d8109817b3c6', '030420', null, '520114196501010016', '陈新年', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('b0df0b2c-d33d-456c-b857-31d08af416c2', '006157', null, '52010319711116041X', '伍绍宇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('b11edd3c-e428-47b5-a300-a0b6568dc734', '009256', null, '52011119800424062X', '吴燕', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('b170fc04-93ba-489b-9547-62cce2f045ac', '006396', null, '520111197708180624', '张晖', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('b1fac620-9b4d-4bbd-8abd-413db5810df9', '037088', null, '522524196712031307', '孙大东', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'fc4376c0-ba88-4958-87da-f3498281839b');
INSERT INTO `t_og_ry` VALUES ('b3115f32-4d78-4e88-b39d-32720c7006d8', '006404', null, '522501196506140823', '郑海燕', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('b3732dc9-6b70-47a3-ac0c-81691a740fd8', '035243', null, '520102197403015015', '李超', null, null, null, null, null, '1', null, '1', null, '2016-09-27 15:46:04', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('b42ef242-e636-4012-aecf-41da720450d8', '042093', null, '350423199102116512', '魏正威', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('b6303357-d6fa-4c00-9867-34e5a24cd60d', '004794', null, '522625197909240045', '杨婵娟', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('b63db6ea-a629-4ba4-b681-8a0c305a2203', '042038', null, '522227198708200069', '顾培玲', null, null, null, null, null, '', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('b66d6b4c-d095-4c0b-82e9-c6c39d77f3af', '035294', null, '520114197810020044', '王俊芳', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('b66e8578-8af6-43b1-be14-ad1b122f9665', '042051', null, '522629198501210010', '罗仁君', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('b7b20653-f44d-46e5-8402-b30938d7026e', '042069', null, '522630198303140336', '吴昌才', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('b838e734-4a03-4b0e-9db0-77cfd5970582', '042084', null, '500224198712317396', '张磊', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('b882395c-cd6f-4cb5-b811-d1d9a89ac6a9', '005973', null, '52010219701206301X', '蒋勇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('b889bcab-bf9b-4bae-a3d9-ca9655b25089', '042054', null, '420106198212044047', '李苑', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('b9d35066-ce0c-46eb-a23b-a47426c00fe8', '039449', null, '520101198803044032', '陈劲鹏', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('bab46961-dfd9-40b1-81bf-454051c21eef', '035263', null, '520102197808261214', '陈胜宇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('badbec5a-8168-45ea-99e5-7c40adc9b326', '042001', null, '520102198902023428', '石玮', null, null, null, null, null, '2', null, '0', null, '2016-08-25 17:16:23', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('bb579a26-49fa-45c9-8606-dde23e6fa474', '006413', null, '520102197212306610', '甘林', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('bb8a89e4-8882-4c20-af14-183946ea5fc9', '042076', null, '520114198208200012', '魏峰', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('bbc189ea-5446-4a72-ad29-ea7435b2278d', '042055', null, '520122198904260027', '蒋宇丹', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('bc06dce2-f25c-4848-b745-e75cb74c3d8d', '042099', null, '513822198912263198', '廖忠', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('bc5593a1-e8ec-4405-af77-2879483c4fe6', '009260', null, '52011119611108001X', '邓广祥', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('bd264a0b-9405-4f82-84d3-637d9987662f', '042090', null, '522426198509175619', '吴易学', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('beb03093-f1f0-4633-b3f3-6cf8df4dca5d', '006075', null, '52011119750109063X', '葛伟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('bf4b3128-b4cb-4ce8-a7ce-3b61d39c9539', '004967', null, '522525195611129155', '余建明', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('bffe5c8e-9068-4d8a-82ef-248390722752', '042082', null, '522125198605093119', '刘凡', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('c04be87a-8cb0-45b9-a6fa-b101175dea78', '035286', null, '520103197610261645', '雷莉涓', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('c08b580c-914c-4c6c-b360-abfb85d7e669', '042070', null, '522426198611251657', '陈东吴', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('c14f66e6-48c0-40ad-b566-4b46d503650b', '006448', null, '520111197601020636', '王晓乐', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('c1e3345e-65bb-45c3-9f0c-a179edfe9257', '006469', null, '520111196001020032', '徐广生', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('c32a36bb-f637-4634-8534-d89334176af9', '006452', null, '520111197801220619', '万钧', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('c4cf444c-b756-47a8-84a0-3f6e18c8cf91', '037076', null, '522524196807270554', '王万军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('c63002ef-acb7-4503-9e1a-db05d6b6ec0f', '006467', null, '520111197504070618', '林健', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'fc4376c0-ba88-4958-87da-f3498281839b');
INSERT INTO `t_og_ry` VALUES ('c674ec89-434a-4b32-9734-464ba9dbf93c', '035296', null, '520114197401111228', '张淑玉', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('c6b8ad1d-77f0-40ed-a6d1-ca0c4656abc8', '006460', null, '520102196303106636', '李敬华', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('ca0c0ff5-9328-4de4-9d2f-cc24ee09fa08', '035253', null, '52010319770905403X', '石林', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('cc26834d-7509-441e-b5da-af1c0a97c1f1', '035269', null, '520103197404203639', '刘好', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('ccc3194f-78ea-4085-b3d6-9866eb029217', '042027', null, '520102198608215824', '刘丹', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('cd0945e2-4af4-4436-80bd-37d17fe3d132', '006408', null, '520111196808250664', '刘献红', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('cd0bcf0f-935b-43e1-a922-0a3a5e2f7b57', '006141', null, '52010319711209563X', '蒋波', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('ce172f03-3c6d-49d1-ae28-d3b1fd8ac4fe', '006032', null, '52273119711025019X', '曹刚', null, null, null, null, null, '1', null, '1', null, '2016-07-27 12:40:44', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('ce233d79-155f-4b6c-a000-b847dfcc620d', '035297', null, '520102197706135022', '赵华', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('ce46023f-8004-4016-bfa6-d93e66c46b87', '035273', null, '520102197708252433', '莫利达', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('cee5a0ef-cb66-491f-bf49-ecdd57f5ca13', '042037', null, '520103198812100019', '龙宇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('cefd82b9-90c1-48bb-9773-7f8271b32e65', '009255', null, '520102196903106648', '余平苹', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '150bf697-3fa7-41b4-a929-0dc933ba7e5d');
INSERT INTO `t_og_ry` VALUES ('cf538f1f-cfc5-41e0-86ee-13f789574c89', '035289', null, '520102197307036617', '王顺', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('d00eb9c6-cde4-499c-bcdd-628bf11754ba', '042087', null, '522502198711262622', '唐晓娇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('d1217935-f6dd-4f87-add1-2388f56e8adc', '035285', null, '520102198205280015', '丁亮', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('d16ef995-6d4b-4408-be32-f666163a09e4', '037096', null, '522524196104013551', '黄永泽', null, null, null, null, null, '1', null, '0', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('d195b38d-2da0-4e1b-98ab-8c0b6e6d46ce', '037080', null, '522524196906110556', '冯建军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('d2250584-16de-48b8-8f89-03f4862fc11b', '037075', null, '520102197406286611', '徐涛', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('d2871636-6fa7-41a5-8f86-838042608a1b', '042028', null, '520201198607180016', '王斐', null, null, null, null, null, '1', null, '1', null, '2016-03-01 14:35:38', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('d37c3413-9e2e-48d1-8fcf-858002275101', '006399', null, '520102195611146651', '陈登亮', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'fc4376c0-ba88-4958-87da-f3498281839b');
INSERT INTO `t_og_ry` VALUES ('d40b5081-a9c5-46c2-aa2a-7a469b4fff16', '006439', null, '520102197112052457', '何意', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('d41362fd-9ba4-45c5-ae0f-12dce0776f81', '005845', null, '520102197111192511', '潘和峻', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('d4175733-2582-4263-afbd-b6241a504846', '035278', null, '52010219730629661X', '张斌', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('d4a9af7f-6e24-4307-99ec-4f209c2fa4b4', '006414', null, '520111197707120638', '龙爱山', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('d534826e-1aef-40f4-b7b4-926832b015ce', '035287', null, '520113197808260421', '潘莉', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('d569b101-7486-494a-a0e5-92a47138ab07', '037101', null, '520114198605220041', '张伶丽', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('d714a43a-57c1-4f05-9294-7e6e4fb66b54', '004926', null, '500231196803108519', '杨万权', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('d73e44bf-323f-4e1f-96e8-dada207976c5', '042041', null, '532128198606243343', '余菊', null, null, null, null, null, '', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('d7513faa-8dc5-4a16-9b8e-ad3116e4b6df', '042044', null, '522529198910053831', '吴吉友', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('d7f992f9-bc57-422a-b553-50dd10d60cd0', '006402', null, '520111197411110633', '王钟', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('d8149ff2-5882-4310-b13e-063149cac11a', '035255', null, '520114197706200053', '易景翔', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('d93519c1-2728-42ce-9f9f-6da98873f979', '007294', null, '520103196210031213', '余海', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('d96b145b-99ab-40dd-8333-55d256a16b98', '035265', null, '520103197610196716', '江克非', null, null, null, null, null, '1', null, '1', null, '2016-02-17 15:59:11', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('da3e5395-5b20-414e-9cd2-d8a8e59f0f73', '006475', null, '520103196002216715', '杨发坤', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('da6c9926-54c0-432d-9c3a-2f1d4284f729', '009262', null, '520103196312280085', '徐兰珍', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('da7bda5f-b44d-4696-8e80-4593eef18dd5', '042050', null, '41021119820502101X', '朱岱', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('dae1da2c-1e1e-44c7-8a4a-2c54bf85585b', '035261', null, '52010219750328241X', '薄军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('db06e5e1-f3f8-4dcf-b410-4542cd67ea5f', '030453', null, '520102197711173410', '钟华', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('db893dc8-fe5d-4589-9792-70578ec90038', '006084', null, '520102197212106213', '万晓东', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('dbde3969-5b84-4c4a-83a9-eb62c2ea1b58', '030452', null, '520111198010100623', '吴隽', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('dcd943bc-e9ad-4818-8db4-9d37488d161c', '035225', null, '522701198402050022', '李晓琴', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('ddf32e2c-5e99-43fc-8e42-87cd4b486dd7', '042039', null, '522425198803039048', '何青青', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('de53b7ca-fc60-499e-85ae-1d72e5d31aef', '037100', null, '520102196712191253', '李健', null, null, null, null, null, '1', null, '0', null, '2016-08-25 17:16:23', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('df380319-85a5-4d35-af3a-1db80d3a65a0', '006428', null, '520111195611130031', '焦治州', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('e02ef080-edb9-4e4f-a4b9-7748cc1dccfd', '006471', null, '520102196708146628', '龙新', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('e17b50e3-5393-4fe8-92e0-5e1182ca894a', '035245', null, '520114197905260016', '孙可珂', null, null, null, null, null, '1', null, '1', null, '2016-03-01 14:35:38', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('e266f65f-1e76-472b-9686-b537224f7944', '004962', null, '520102196202066612', '于林根', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('e29b83bf-d04f-4663-a543-540c64da488b', '004953', null, '522525196811018833', '杨松', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('e3f4efe7-a6cd-4758-bcd8-bbbeda536f3b', '006455', null, '522525196702198919', '张稀国', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('e4726d28-ef9a-49ae-9df4-b63ed21cf7bb', '030454', null, '520102196407230412', '刘贤猛', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('e4995957-e9ad-4f8b-b4ee-72740add4276', '037086', null, '522731199112142896', '李灵江', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('e53d581d-c592-44f5-8c7e-3ec41e74d15d', '030451', null, '520103197801136425', '王妮丽', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('e5bea852-4ff6-433b-ab63-4cf6fa66f3b7', '006390', null, '520111196011160610', '张书军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '710ec0e8-fe8a-42da-b4e4-353a380be40d');
INSERT INTO `t_og_ry` VALUES ('e6a30801-b799-43d6-8376-3db3c6784f79', '042089', null, '522122198611101630', '潘俊', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('e7dd1eae-c897-4df8-9b35-2d8a810dbab6', '006113', null, '520102196901040833', '姚勤志', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'a6c2bcaa-00e6-4d86-a323-9f930ac19103');
INSERT INTO `t_og_ry` VALUES ('e80a4a1b-b264-4dbf-bae7-62b700cd1528', '035275', null, '520102197403246622', '钱奕霖', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('e839cb13-a20d-4f8c-bb4d-11b51350daab', '035282', null, '520102197201236610', '赵光宇', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('e9408764-dd58-4357-9a46-4263ce0b3eea', '006416', null, '522525196808158835', '李毅', null, null, null, null, null, '1', null, '0', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('eb453b98-eb95-45fc-9708-54d3c8eb91a1', '035262', null, '520111197511090019', '蔡春山', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('eb62f5a2-52d9-4452-b034-87933544d3eb', '035259', null, '520102197408096619', '李在峰', null, null, null, null, null, '1', null, '1', null, '2016-07-18 08:57:38', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('eba2ad7e-bacd-41c7-bdf9-5f5ed5540649', '006433', null, '520102196710215039', '周卫', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('ed1cd989-1f65-4d26-ae52-10872b1e0f57', '006458', null, '522225198612082018', '陈波', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('ee432d5b-1e21-45b1-92f5-02708ab7a868', '006398', null, '520102196811214625', '周娟', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('f0fa4e9e-d656-4ea4-be95-25ea4db00459', '007267', null, '520102196707024215', '谢军', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('f181f33e-a65b-499c-b5a4-fa51cc1c9979', '006140', null, '520112197205130015', '高超', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '585fdd1b-4453-443d-8d28-305368c99728');
INSERT INTO `t_og_ry` VALUES ('f24b8d61-8c97-4dde-a59e-96fcf56023b2', '004970', null, '522423197008155210', '潘伟俊', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('f3de5fe5-f0e0-45ec-83ea-afb0294abb00', '008301', null, '520111196203250039', '李建辉', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('f423d20f-421f-4b14-a543-77365bf0303f', '006137', null, '520103197002232832', '杨荣', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('f49baf6c-0393-4339-b701-b7685d9beea1', '006466', null, '520111197709290614', '向顺华', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('f4d29dae-c266-4541-a395-9ea06fef8841', '042081', null, '500233198805256410', '陈毅', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('f54d478d-085f-42bb-a643-52de8d74acd6', '035254', null, '520102197304026616', '王黔', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('f5b44e09-f240-49a6-808f-3fa3a054d7bc', '042098', null, '522128199111157016', '吴华东', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '069c4e0f-a006-43d6-a9d7-36f488514c7d');
INSERT INTO `t_og_ry` VALUES ('f617fae3-d8ef-4eb7-a938-39d200ca9de1', '042064', null, '522225198710039013', '罗臣', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('f628109f-fb03-4afd-b784-064b1ed5d501', '042018', null, '522526198210230025', '刘彦君', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '76e82277-9ef2-4c01-b223-454048e3db9c');
INSERT INTO `t_og_ry` VALUES ('f75089ed-3922-4482-9d54-e76ac97a93d0', '035268', null, '52010219790613813X', '刘翀', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('f759486b-3079-4122-8611-2e72472930b4', '006457', null, '520111196704110632', '杨黔筑', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '01');
INSERT INTO `t_og_ry` VALUES ('f77dcc74-447a-4f5a-ac2a-3504bb397c28', '006421', null, '522524197305201297', '武长文', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('f8c73fa3-a37a-4138-9ff6-c2807edd4112', '042059', null, '430524199006221760', '袁楠', null, null, null, null, null, '', null, '1', null, '2016-02-03 09:55:32', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5');
INSERT INTO `t_og_ry` VALUES ('f9555a76-1c35-4600-ab42-71d135774c90', '009915', null, '520111196008270050', '张应笠', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3f025ed8-d417-45ac-96cd-af383b3fc431');
INSERT INTO `t_og_ry` VALUES ('f9bc0db9-64fe-4286-ba70-bc965afe54e5', '035274', null, '520114197612020422', '彭莉', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('fa043a61-31b9-4ef3-b69b-db353902199b', '006459', null, '520103195910292428', '张萍', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '710ec0e8-fe8a-42da-b4e4-353a380be40d');
INSERT INTO `t_og_ry` VALUES ('faeea14a-822b-41f0-81ea-0fc105c87c45', '042078', null, '522121198603297411', '骆小峰', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23');
INSERT INTO `t_og_ry` VALUES ('fc0e27c7-7a7f-4f7f-84d9-dc33704af2a2', '042052', null, '522424198808074831', '陈进', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '7b8cac4c-2e89-499c-b83e-63defc1da3dd');
INSERT INTO `t_og_ry` VALUES ('fc30f919-20dc-49ef-bb98-2631893ca78f', '006395', null, '522501196501220832', '郭冰', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'f1218a34-d2aa-43da-a9cf-1475a15dc3ad');
INSERT INTO `t_og_ry` VALUES ('fc44edc0-d277-4d29-8aed-7e8af06d6474', '004792', null, '520102196401146615', '蔡勇', null, null, null, null, null, '1', null, '1', null, '2016-09-05 10:20:25', 'b4f8b72d-1940-43a4-9518-d6606b8b768a');
INSERT INTO `t_og_ry` VALUES ('fcc73596-b609-400d-8372-a7782af4cb34', '006447', null, '520111196912110039', '盖雷', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('fe09a377-0754-476b-9912-221d3be0c8e6', '006411', null, '522525196812148832', '关恒青', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567');
INSERT INTO `t_og_ry` VALUES ('fefc87da-f926-494b-a182-f8c2813591c3', '037580', null, '522328198307020819', '杨值玉', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'e55be91b-f2cc-4de6-80cc-d460e3c92975');
INSERT INTO `t_og_ry` VALUES ('ff0c854b-ddff-4319-a523-3afd716deb2c', '038996', null, '520201198607050035', '雷坤', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '3cdc025c-dcaf-47f8-a2fd-6c9f6e1b18d5');
INSERT INTO `t_og_ry` VALUES ('ff916388-7779-4c38-8bfa-6df715cb1a6b', '006389', null, '520114198102240032', '蔡鹏', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', '0b3d22e9-a71f-4216-b98b-39fd6ef6899d');
INSERT INTO `t_og_ry` VALUES ('ffdcb500-e335-46d6-8eb8-e083b0548763', '006476', null, '520114197612100027', '赵明', null, null, null, null, null, '2', null, '1', null, '2016-02-03 09:55:32', '3ba1e1ab-e937-420c-88a5-cce1d2522cf4');
INSERT INTO `t_og_ry` VALUES ('fff04f78-1b86-4b97-98f3-c7e44c1860c4', '005862', null, '520102197007271615', '柯研', null, null, null, null, null, '1', null, '1', null, '2016-02-03 09:55:32', 'c1bc34ff-d881-4a5c-9fbe-e4e4048ae82c');
INSERT INTO `t_og_ry` VALUES ('xnry02', 'admin', null, null, '超级管理员', null, '', null, null, null, '0', null, '1', null, '2016-02-24 12:00:00', '01');


-- ----------------------------
-- Table structure for t_og_zh
-- ----------------------------
DROP TABLE IF EXISTS `t_og_zh`;
CREATE TABLE `t_og_zh` (
  `id` varchar(255) NOT NULL,
  `zhm` varchar(50) DEFAULT NULL,
  `sxsj` datetime DEFAULT NULL,
  `bz` varchar(50) DEFAULT NULL,
  `mm` varchar(50) DEFAULT NULL,
  `yxsj` datetime DEFAULT NULL,
  `zt` varchar(10) DEFAULT NULL,
  `sjc` datetime DEFAULT NULL,
  `ry_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ry_id` (`ry_id`),
  KEY `FKA0BBB36AAF974B5D` (`ry_id`),
  CONSTRAINT `FKA0BBB36AAF974B5D` FOREIGN KEY (`ry_id`) REFERENCES `t_og_ry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户';

-- ----------------------------
-- Records of t_og_zh
-- ----------------------------
INSERT INTO `t_og_zh` VALUES ('00061260-efa1-470d-9650-ede2d19436d1', '035297', '9999-01-01 00:00:00', null, '035297', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ce233d79-155f-4b6c-a000-b847dfcc620d');
INSERT INTO `t_og_zh` VALUES ('0127ad62-3171-4475-a12d-4c52a923127b', 'JB02', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:19:01', '1', '2016-09-01 15:23:37', 'ac0f65da-b636-4b09-a78a-369d3c71f1f3');
INSERT INTO `t_og_zh` VALUES ('02091e0a-e79a-4265-9b40-3b54cf90ab0a', '042005', '9999-01-01 00:00:00', null, '042005', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '97b7ac0b-b11d-4b40-8ec3-079a9a687926');
INSERT INTO `t_og_zh` VALUES ('02f248e9-f2dc-4372-8232-589944209795', '009255', '9999-01-01 00:00:00', null, '009255', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'cefd82b9-90c1-48bb-9773-7f8271b32e65');
INSERT INTO `t_og_zh` VALUES ('03030996-ec22-47aa-a5a3-9e93205241ad', '009915', '9999-01-01 00:00:00', null, '009915', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f9555a76-1c35-4600-ab42-71d135774c90');
INSERT INTO `t_og_zh` VALUES ('03d3d98d-1bb8-4648-857e-0020425a71e6', '006402', '9999-01-01 00:00:00', null, '006402', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd7f992f9-bc57-422a-b553-50dd10d60cd0');
INSERT INTO `t_og_zh` VALUES ('040067c4-879f-4c3b-9af4-67aa11f6914e', '042082', '9999-01-01 00:00:00', null, '042082', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bffe5c8e-9068-4d8a-82ef-248390722752');
INSERT INTO `t_og_zh` VALUES ('0590c6f2-a239-497e-a556-e9b22cdc52ec', '042031', '9999-01-01 00:00:00', null, '042031', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '752f9703-4ba1-4d91-96f2-cbff90a6cc0c');
INSERT INTO `t_og_zh` VALUES ('0731776b-f438-4a52-b1bf-cbe56cd294b3', '035251', '9999-01-01 00:00:00', null, '035251', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3e7565a8-8a1d-44a8-b632-1180b451c82b');
INSERT INTO `t_og_zh` VALUES ('074cbb7d-fefa-4ff8-addb-513686658298', '042079', '9999-01-01 00:00:00', null, '042079', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '1a4ceb84-619e-459a-b6e6-fe125230c94a');
INSERT INTO `t_og_zh` VALUES ('07ac0765-10b9-49b2-8f24-acd1f1e289fa', '037097', '9999-01-01 00:00:00', null, '037097', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a69fe84d-52e8-4ed3-9210-6867147dd336');
INSERT INTO `t_og_zh` VALUES ('09013da6-0ae1-437f-945a-2f1b0d74b534', '005825', '9999-01-01 00:00:00', null, '005825', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4e8423dc-eb9d-4e53-aa68-0a83dd37dad4');
INSERT INTO `t_og_zh` VALUES ('0985e221-4f4b-4d4b-992b-d7cde9695656', '037091', '9999-01-01 00:00:00', null, '037091', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '21153951-d5b3-4a30-bfa6-472be252b6c9');
INSERT INTO `t_og_zh` VALUES ('0b3df739-fed4-490a-87f1-2cfec71e3b86', 'JB11', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:29:46', '1', '2016-09-01 15:30:15', 'd4175733-2582-4263-afbd-b6241a504846');
INSERT INTO `t_og_zh` VALUES ('0c18fe97-feb4-45a3-93cc-23a28015a35f', 'JB06', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:23:55', '1', '2016-09-01 15:27:47', 'cf538f1f-cfc5-41e0-86ee-13f789574c89');
INSERT INTO `t_og_zh` VALUES ('0c6e1ac2-cde4-41e6-a5fa-c5cf6100a561', '042088', '9999-01-01 00:00:00', null, '042088', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '09fcb7e4-5e37-486d-8487-1c78c839ff41');
INSERT INTO `t_og_zh` VALUES ('0d37fee5-88de-4da4-80d7-ac95143f0fa9', '005727', '9999-01-01 00:00:00', null, '005727', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ac5418de-1596-41cf-975b-f6c25c655a73');
INSERT INTO `t_og_zh` VALUES ('0db42a2f-6195-4ad2-8990-521100448f74', '035253', '9999-01-01 00:00:00', null, '035253', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', 'ca0c0ff5-9328-4de4-9d2f-cc24ee09fa08');
INSERT INTO `t_og_zh` VALUES ('0e52eaac-2d7e-4141-a296-ebd541cd3a4c', '006396', '9999-01-01 00:00:00', null, '006396', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b170fc04-93ba-489b-9547-62cce2f045ac');
INSERT INTO `t_og_zh` VALUES ('0e7b9c65-fef8-4322-a019-1b9cc796d27f', '005923', '9999-01-01 00:00:00', null, '005923', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4395fa0c-c5e8-403b-8d75-0918203bd764');
INSERT INTO `t_og_zh` VALUES ('0f18aa04-f6b6-4c93-9ce1-780b3e2b37b3', '006449', '9999-01-01 00:00:00', '', '006449', '2016-12-06 13:31:00', '1', '2017-04-24 14:59:51', '6d3a231c-dbec-47dd-af94-8c53c7205bbf');
INSERT INTO `t_og_zh` VALUES ('0f4624f5-ef64-4ed6-9f4c-16c4dd100e2e', '006447', '9999-01-01 00:00:00', null, '006447', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'fcc73596-b609-400d-8372-a7782af4cb34');
INSERT INTO `t_og_zh` VALUES ('11524403-3550-4701-99cd-42853f3e85a9', '037601', '9999-01-01 00:00:00', null, '037601', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '30cb8a19-8f50-4842-8872-a93b3c3bbaf5');
INSERT INTO `t_og_zh` VALUES ('12481e12-b03d-4060-a199-edc28b9194b9', '042048', '9999-01-01 00:00:00', null, '042048', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6ba2d9fe-2b7b-44be-9f6b-042a694c5ce2');
INSERT INTO `t_og_zh` VALUES ('1253b888-80e9-4837-92b0-18c1ebc0599e', '006136', '9999-01-01 00:00:00', null, '006136', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '5a341f21-b6a8-4919-a90f-9b9f1a12c250');
INSERT INTO `t_og_zh` VALUES ('1287b17c-8c3f-4871-9ae7-d1158776b9d0', '030453', '9999-01-01 00:00:00', null, '030453', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'db06e5e1-f3f8-4dcf-b410-4542cd67ea5f');
INSERT INTO `t_og_zh` VALUES ('13e051e3-d3a8-455b-9d9c-8d68bc54a170', '042097', '9999-01-01 00:00:00', null, '042097', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7cdcb99e-3f40-4d9c-946f-b7a393f3f5f4');
INSERT INTO `t_og_zh` VALUES ('1430b6c6-0d62-4fe3-9baa-9908f57e2bce', '042076', '9999-01-01 00:00:00', null, '042076', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bb8a89e4-8882-4c20-af14-183946ea5fc9');
INSERT INTO `t_og_zh` VALUES ('148b62f4-c403-4982-b836-e5d5ed288341', '006421', '9999-01-01 00:00:00', null, '006421', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f77dcc74-447a-4f5a-ac2a-3504bb397c28');
INSERT INTO `t_og_zh` VALUES ('15f95976-b73c-4257-ae91-1c81c5810980', '035258', '9999-01-01 00:00:00', null, '035258', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '78b34105-76e7-474a-89be-36f501cf1cb9');
INSERT INTO `t_og_zh` VALUES ('16207f0e-4685-4d02-b089-795d4ac42100', '006466', '9999-01-01 00:00:00', null, '006466', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f49baf6c-0393-4339-b701-b7685d9beea1');
INSERT INTO `t_og_zh` VALUES ('1688dd51-6ff3-481c-837a-441254ed528d', '035282', '9999-01-01 00:00:00', null, '035282', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e839cb13-a20d-4f8c-bb4d-11b51350daab');
INSERT INTO `t_og_zh` VALUES ('16a4dfb4-88b5-4ac5-8327-3bc864f405b5', '037081', '9999-01-01 00:00:00', null, '037081', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '19a4ae31-9768-445d-a0ab-1c5df3e4aa69');
INSERT INTO `t_og_zh` VALUES ('172749ff-97d0-4b90-8088-4ec0616f1bb1', '035377', '9999-01-01 00:00:00', null, '035377', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '24536bb6-b255-4eb0-af9e-f1d57903f183');
INSERT INTO `t_og_zh` VALUES ('17e32d27-27a5-4fb5-b36d-6e91e86c413e', '042077', '9999-01-01 00:00:00', null, '042077', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '0b2d503b-8758-4692-ab8f-ab33576308ec');
INSERT INTO `t_og_zh` VALUES ('17fb6d3e-1ad1-426c-8ecc-e6472df49a57', '006459', '9999-01-01 00:00:00', null, '006459', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'fa043a61-31b9-4ef3-b69b-db353902199b');
INSERT INTO `t_og_zh` VALUES ('18014fcf-870a-4052-bc74-1ff0d0fb213d', '035249', '9999-01-01 00:00:00', null, '035249', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7795f9f6-05ec-4eae-a1e3-3bbe44c70d26');
INSERT INTO `t_og_zh` VALUES ('1914fecd-bc2c-42c0-9cb7-96a9f6a82094', '042021', '9999-01-01 00:00:00', null, '042021', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '5ef46c94-5043-4168-ac7b-bce70e18db73');
INSERT INTO `t_og_zh` VALUES ('1943778a-ab4e-4386-86d3-2a8cb518abb2', '037092', '9999-01-01 00:00:00', null, '037092', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9742f572-6175-4658-9cf9-7c86acf8ca91');
INSERT INTO `t_og_zh` VALUES ('196ee703-71f7-4868-ab62-4e1eebd1b316', '009262', '9999-01-01 00:00:00', null, '009262', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'da6c9926-54c0-432d-9c3a-2f1d4284f729');
INSERT INTO `t_og_zh` VALUES ('197730bc-a662-4ad6-8c4e-ca6a26ee760d', '006453', '9999-01-01 00:00:00', null, '006453', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', '54761e5e-184c-430f-96a9-922c42357eef');
INSERT INTO `t_og_zh` VALUES ('19844a1b-b4bf-4c4e-8f2d-4b3018c06b2b', '035286', '9999-01-01 00:00:00', null, '035286', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'c04be87a-8cb0-45b9-a6fa-b101175dea78');
INSERT INTO `t_og_zh` VALUES ('1bf02bde-b918-42c6-94e4-e3e6a9d0f1d3', '009257', '9999-01-01 00:00:00', null, '009257', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', '11103c9c-6861-4aca-af39-e39bbd95c6a1');
INSERT INTO `t_og_zh` VALUES ('1cb41487-242e-4cc7-9b7e-81aa4299c0aa', '042025', '9999-01-01 00:00:00', null, '042025', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '0b6cb5b8-5cf0-4f58-948f-96dfa09c5e43');
INSERT INTO `t_og_zh` VALUES ('1d47a7df-930e-44c7-ba6c-67f1efa9d886', '006392', '9999-01-01 00:00:00', null, '006392', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3736ce29-2a67-41b6-82dd-de5ccf810824');
INSERT INTO `t_og_zh` VALUES ('1dc8eaef-0495-4af5-8174-0f556f246838', '004888', '9999-01-01 00:00:00', null, '004888', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '16fd5e84-1065-4cfd-8c36-aeacab679850');
INSERT INTO `t_og_zh` VALUES ('20fa7b05-2b24-44e6-8e1f-aafcfa93ad75', '006143', '9999-01-01 00:00:00', null, '006143', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '34109171-32c4-481c-a9a0-26fd701b3fe4');
INSERT INTO `t_og_zh` VALUES ('2134aacb-55d4-47e3-983e-7b73e12de4be', '042069', '9999-01-01 00:00:00', null, '042069', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b7b20653-f44d-46e5-8402-b30938d7026e');
INSERT INTO `t_og_zh` VALUES ('22166239-bd0d-4cae-8f57-452395c3855f', '009260', '9999-01-01 00:00:00', null, '009260', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bc5593a1-e8ec-4405-af77-2879483c4fe6');
INSERT INTO `t_og_zh` VALUES ('225f4211-0441-4638-a53a-f5221f903565', '006432', '9999-01-01 00:00:00', null, '006432', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7fa902ad-eb73-4928-b266-be5efeffc60d');
INSERT INTO `t_og_zh` VALUES ('22b17dda-6a03-44f3-b577-3083b3097a79', '042047', '9999-01-01 00:00:00', null, '042047', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ab2bef60-7070-40ca-b40a-3d5ee6543d07');
INSERT INTO `t_og_zh` VALUES ('22f6edcf-7c4e-43f5-a6dd-fae31096804b', '037087', '9999-01-01 00:00:00', null, '037087', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '35913e01-ab19-47dc-8f6e-8370143322b9');
INSERT INTO `t_og_zh` VALUES ('23e55d17-2215-4f28-abd1-3d76279bb848', '035271', '9999-01-01 00:00:00', null, '035271', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4136e486-ada3-4fc6-8506-28bcb8879ce4');
INSERT INTO `t_og_zh` VALUES ('23ea1ae2-83d3-405a-8aca-3aa24ec0efad', '006393', '9999-01-01 00:00:00', null, '006393', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a3703706-9be0-4657-83b0-c4c598e21d26');
INSERT INTO `t_og_zh` VALUES ('2576af84-71a8-4ceb-af0a-624fc1f04486', '037098注销', '9999-01-01 00:00:00', null, '037098注销', '2016-12-06 13:31:00', '0', '2016-02-03 09:55:32', '831feab5-2635-4d3f-9f7c-2f99a4e95812');
INSERT INTO `t_og_zh` VALUES ('259f1a4f-3e38-436d-9d25-9fc57745f746', '042072', '9999-01-01 00:00:00', null, '042072', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '00adabdd-3104-4349-8ff5-a1860c72d9eb');
INSERT INTO `t_og_zh` VALUES ('2648097f-1963-4beb-8f20-54e0d236a80b', '006388', '9999-01-01 00:00:00', null, '006388', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '43d815df-7c0a-4c00-9777-5d9af8944b8d');
INSERT INTO `t_og_zh` VALUES ('26a3de31-8d16-4c91-b516-068ae6ef958c', '042089', '9999-01-01 00:00:00', null, '042089', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e6a30801-b799-43d6-8376-3db3c6784f79');
INSERT INTO `t_og_zh` VALUES ('27baffd4-6f19-4614-bdca-33562595a74b', '006462', '9999-01-01 00:00:00', null, '006462', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'aac2064f-7fd7-454a-9af9-aba829909d4c');
INSERT INTO `t_og_zh` VALUES ('282485c8-29e2-49de-b26c-de2969de7126', '035285', '9999-01-01 00:00:00', null, '035285', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd1217935-f6dd-4f87-add1-2388f56e8adc');
INSERT INTO `t_og_zh` VALUES ('2902aec8-60e9-40d4-b18d-b41ae403c206', '009912', '9999-01-01 00:00:00', null, '009912', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '178936b8-bf51-4044-9d95-ab7003f2431c');
INSERT INTO `t_og_zh` VALUES ('29877e0f-9dc6-4fa9-b8d6-5a045a581bc1', '004966', '9999-01-01 00:00:00', '', '004966', '2016-12-06 13:31:00', '1', '2017-04-13 19:37:41', '23eb605b-828a-4b63-8e21-8d62e8856c5d');
INSERT INTO `t_og_zh` VALUES ('2a7f45d3-48ba-497d-ab84-c1e4bd37a966', '042058', '9999-01-01 00:00:00', null, '042058', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3368213a-c9a1-47e9-bcd7-bb1ff0570291');
INSERT INTO `t_og_zh` VALUES ('2b6f9666-e026-4729-aef8-cb4b2cb87f7c', '009911', '9999-01-01 00:00:00', null, '009911', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', 'ad156d2a-3e0e-4fdb-8e88-e2d0d5720d86');
INSERT INTO `t_og_zh` VALUES ('2b8cd272-df3e-403f-a315-c89d3cea2e06', '006140', '9999-01-01 00:00:00', null, '006140', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f181f33e-a65b-499c-b5a4-fa51cc1c9979');
INSERT INTO `t_og_zh` VALUES ('2ba7cbca-d03b-4d82-955d-9f749e42091e', '042067', '9999-01-01 00:00:00', null, '042067', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ac29570d-e2d4-4486-909c-e10e90337049');
INSERT INTO `t_og_zh` VALUES ('2c30bc06-66ec-46a0-88a6-9efbb686f8dc', '042045', '9999-01-01 00:00:00', null, '042045', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '936262da-ca86-4bdd-9978-40955562279f');
INSERT INTO `t_og_zh` VALUES ('2ca97719-7fa8-4b4d-b29b-87d824a026fb', '037074', '9999-01-01 00:00:00', '', '037074', '2016-12-06 13:31:00', '1', '2017-04-24 15:01:01', '7bc08dbc-0e73-44e3-b027-2817fcb0bf32');
INSERT INTO `t_og_zh` VALUES ('2d00ef54-f7f8-417a-bf2a-cb0107f485d1', '004958', '9999-01-01 00:00:00', null, '004958', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3ea02a0a-5232-4bc0-b9f5-133184de8af2');
INSERT INTO `t_og_zh` VALUES ('2d21cb03-5405-4147-8475-81b510d5aa6e', '006417', '9999-01-01 00:00:00', null, '006417', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '32ca4b2f-9752-42c9-82de-ba01df66478b');
INSERT INTO `t_og_zh` VALUES ('2e2e2612-b42b-49a9-af1f-e49f8c6bafb3', '006424', '9999-01-01 00:00:00', null, '006424', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ada779e1-1c0b-47e3-8bd5-d47ea05322e0');
INSERT INTO `t_og_zh` VALUES ('2e727998-72fc-4844-ac90-e1221b65ffef', '042065', '9999-01-01 00:00:00', null, '042065', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', '1f0362d2-5d07-404c-b231-618e4d9268d4');
INSERT INTO `t_og_zh` VALUES ('2e876b6a-89d6-4b60-9c80-09ff27f145f7', '042027', '9999-01-01 00:00:00', null, '042027', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ccc3194f-78ea-4085-b3d6-9866eb029217');
INSERT INTO `t_og_zh` VALUES ('2ea174eb-b9cd-4137-af6b-abe4b3fc6dff', '006445', '9999-01-01 00:00:00', null, '006445', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '267df216-b120-412d-834a-909d338cc6ac');
INSERT INTO `t_og_zh` VALUES ('2eb7190b-15ff-45c7-832f-b5a107e82ccb', '042034', '9999-01-01 00:00:00', null, '042034', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4bae5541-50d3-4eca-a555-c13210c77ced');
INSERT INTO `t_og_zh` VALUES ('2f4a3aab-759b-4454-8185-9dcf8cc9b46a', '042036', '9999-01-01 00:00:00', null, '042036', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '12bfcf0c-78ae-4213-b15b-65e0685ff14f');
INSERT INTO `t_og_zh` VALUES ('3093b82b-7972-4fff-84c2-195ec5cbf8ed', '042038', '9999-01-01 00:00:00', null, '042038', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b63db6ea-a629-4ba4-b681-8a0c305a2203');
INSERT INTO `t_og_zh` VALUES ('31477cf9-94e3-46b9-b300-7eb7f0ad3b7c', '006418', '9999-01-01 00:00:00', null, '006418', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6fcc9f7f-2a30-4819-a86f-a06742ab88eb');
INSERT INTO `t_og_zh` VALUES ('328363f8-6bff-4365-9baf-c5bb490440b6', '037079', '9999-01-01 00:00:00', null, '037079', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '22a56f6e-ff37-4206-9058-590d77c467be');
INSERT INTO `t_og_zh` VALUES ('344e89ef-1ea3-49c4-98d6-bd106897ea53', '037077', '9999-01-01 00:00:00', null, '037077', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6dd5b3b3-9807-45b2-89b7-0c7153bf5bb7');
INSERT INTO `t_og_zh` VALUES ('3500a0bc-e76e-4284-9e95-0e8bba517df3', 'JB12', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:30:24', '1', '2016-09-01 15:30:44', '4ade3972-1f72-451e-a9ce-2b73929f6aaa');
INSERT INTO `t_og_zh` VALUES ('355e91e6-dae5-4ddb-b887-2cd561227451', '006461', '9999-01-01 00:00:00', null, '006461', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '5f4d6f9e-a3e7-4d9b-8291-ef16323c9b43');
INSERT INTO `t_og_zh` VALUES ('36eae02d-ca6e-4781-93dd-43041fe3f874', 'XZ02', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:38:32', '1', '2016-09-01 15:38:48', 'e4995957-e9ad-4f8b-b4ee-72740add4276');
INSERT INTO `t_og_zh` VALUES ('370ecb30-c64c-483f-9643-62f4079fa901', '037085', '9999-01-01 00:00:00', null, '037085', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', '0d30e466-8d58-460c-8c11-582adbf87674');
INSERT INTO `t_og_zh` VALUES ('37232761-e1cb-471e-bf4c-c47669d68784', '042020', '9999-01-01 00:00:00', null, '042020', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '1b67b86d-e137-47b5-b608-1c87914e91a1');
INSERT INTO `t_og_zh` VALUES ('3732b1f8-84cf-41a1-8d44-5f4b8a48a560', '037579', '9999-01-01 00:00:00', null, '037579', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '20c27876-2382-4381-97ce-fb25c5f05b65');
INSERT INTO `t_og_zh` VALUES ('37865b45-39ee-4640-a4f6-adce38f1a2c9', '006464', '9999-01-01 00:00:00', null, '006464', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '69ae27e8-1ec5-4750-96e0-4d2be603a857');
INSERT INTO `t_og_zh` VALUES ('37a2add1-4118-4c23-88ec-21824f7ad2b6', '004968', '9999-01-01 00:00:00', null, '004968', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '16388d33-f505-4b15-a7d2-c1a291561fc5');
INSERT INTO `t_og_zh` VALUES ('384bbea5-1b51-4d18-8744-9238a864673e', '042041', '9999-01-01 00:00:00', null, '042041', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd73e44bf-323f-4e1f-96e8-dada207976c5');
INSERT INTO `t_og_zh` VALUES ('38e348ce-e2be-4cbd-8293-98822d948cf0', '035275', '9999-01-01 00:00:00', null, '035275', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e80a4a1b-b264-4dbf-bae7-62b700cd1528');
INSERT INTO `t_og_zh` VALUES ('390c3361-d397-4ef6-ac81-0e2ffdcad611', '006423', '9999-01-01 00:00:00', null, '006423', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3e76bde9-1ad2-4b38-ad6d-b2105b28bcfa');
INSERT INTO `t_og_zh` VALUES ('39df5fe6-b002-4747-a587-f0ca89358f4a', '042074', '9999-01-01 00:00:00', null, '042074', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '01b3c18d-ffde-41d7-a75d-80ebe5bcc1ca');
INSERT INTO `t_og_zh` VALUES ('3ade63bc-1c82-4b3d-9981-d605103e0847', '006390', '9999-01-01 00:00:00', null, '006390', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e5bea852-4ff6-433b-ab63-4cf6fa66f3b7');
INSERT INTO `t_og_zh` VALUES ('3b73858a-b25d-4c18-bf15-584be01c6c62', '035294', '9999-01-01 00:00:00', null, '035294', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b66d6b4c-d095-4c0b-82e9-c6c39d77f3af');
INSERT INTO `t_og_zh` VALUES ('3c2a17c4-a9e7-459d-9647-0330446d3cd3', '006141', '9999-01-01 00:00:00', null, '006141', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'cd0bcf0f-935b-43e1-a922-0a3a5e2f7b57');
INSERT INTO `t_og_zh` VALUES ('3d0944bc-d292-4d0a-ae02-bb221cd202bd', '042094', '9999-01-01 00:00:00', null, '042094', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '023a6e91-73b9-4f39-b5a0-060f052d3e8d');
INSERT INTO `t_og_zh` VALUES ('3dd1d079-3167-4704-8aed-98e34da6060e', '035264', '9999-01-01 00:00:00', null, '035264', '2016-12-06 13:31:00', '1', '2016-08-25 17:16:23', '6dd93e5f-7651-4d5d-b14b-f6a07f477736');
INSERT INTO `t_og_zh` VALUES ('3ec616b1-5b40-40e7-a87e-fc371c3d5794', '006397', '9999-01-01 00:00:00', null, '006397', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '75020212-2fc4-4bbf-bde5-af437fa3c6be');
INSERT INTO `t_og_zh` VALUES ('3f84cd3c-a4e8-499e-a2d3-462c44d1eb7f', '042075注销', '9999-01-01 00:00:00', null, '042075注销', '2016-12-06 13:31:00', '0', '2016-02-03 09:55:32', '8217c793-3742-42e3-bb3d-5c1d9b878e60');
INSERT INTO `t_og_zh` VALUES ('4074df62-c6b9-403d-ae8c-4c3081eb2dc5', '004794', '9999-01-01 00:00:00', null, '004794', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b6303357-d6fa-4c00-9867-34e5a24cd60d');
INSERT INTO `t_og_zh` VALUES ('40aacdf6-8ffd-4806-87d9-805fb5e174bf', '030487', '9999-01-01 00:00:00', '', '030487', '2016-12-06 13:31:00', '1', '2017-04-13 19:38:39', '348d5a6c-44d6-4555-81ce-835f7286dbe4');
INSERT INTO `t_og_zh` VALUES ('40f7fbce-29df-40ce-b04c-6fe50b3e3ae9', '009910', '9999-01-01 00:00:00', null, '009910', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '0ab91827-6a5b-4752-a600-6d81799db03c');
INSERT INTO `t_og_zh` VALUES ('422a02d8-25a9-4b98-b0aa-4a76ce4115aa', '030486', '9999-01-01 00:00:00', null, '030486', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '0c226a1c-3d30-4332-8618-0c7708151cb0');
INSERT INTO `t_og_zh` VALUES ('42362175-2bbb-40a6-a3e4-9caf54051621', '004917', '9999-01-01 00:00:00', null, '004917', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '880fc689-5d0f-42d6-9e72-a744a889f074');
INSERT INTO `t_og_zh` VALUES ('42735c9a-15d7-403e-afad-955add70c313', '006436', '9999-01-01 00:00:00', null, '006436', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7edeface-0d48-4495-bcfc-9f33e4180ee1');
INSERT INTO `t_og_zh` VALUES ('43741fb2-85ad-44d3-b5ec-19f2b1038b5f', '037094', '9999-01-01 00:00:00', null, '037094', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6327c862-4581-4fa7-9257-e64f302f1fdf');
INSERT INTO `t_og_zh` VALUES ('43adb100-fef5-4060-a383-93d5010eac97', '004969', '9999-01-01 00:00:00', null, '004969', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ad692822-008c-4db3-aa1c-ebec3c035a5b');
INSERT INTO `t_og_zh` VALUES ('46abc3de-3dcd-4ffc-9093-93dbac6849cd', '035283', '9999-01-01 00:00:00', null, '035283', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9a0ce443-2758-4534-8ac5-35e2b98c178a');
INSERT INTO `t_og_zh` VALUES ('46d37ee3-0720-4263-a753-997beca40d4c', 'JB10', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:28:58', '1', '2016-09-01 15:29:38', 'fefc87da-f926-494b-a182-f8c2813591c3');
INSERT INTO `t_og_zh` VALUES ('4712f44a-4330-45cb-a650-e82542ba6d5d', 'SJ02', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:36:11', '1', '2016-09-02 13:30:39', 'dae1da2c-1e1e-44c7-8a4a-2c54bf85585b');
INSERT INTO `t_og_zh` VALUES ('472cb3cf-48f0-4a09-838c-7e8409963505', '031630', '9999-01-01 00:00:00', null, '031630', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '07564d7c-6fb4-4bfc-96b0-21a7130c03ee');
INSERT INTO `t_og_zh` VALUES ('475d66e0-2b6c-4373-9948-876b140283ea', 'JB09', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:28:26', '1', '2016-09-01 15:28:53', '3483bd5f-db91-41c0-9404-256ded560b14');
INSERT INTO `t_og_zh` VALUES ('479f968c-f007-459d-b857-c64012d588fb', '004955', '9999-01-01 00:00:00', null, '004955', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '922efdb1-57a4-44c6-8d01-c4466176819c');
INSERT INTO `t_og_zh` VALUES ('47cf1f0e-e39f-4816-b25a-8ae5b8e96285', '035363', '9999-01-01 00:00:00', null, '035363', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3f5c1866-2da2-4c22-b9e5-4570b43d9d2f');
INSERT INTO `t_og_zh` VALUES ('47fe8247-4607-476e-8074-edb52d9ffa92', '006400', '9999-01-01 00:00:00', null, '006400', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '8d83913b-a0a4-42d0-91cd-3f3e8cb9c2c2');
INSERT INTO `t_og_zh` VALUES ('483bec54-c6ae-4b43-b989-90e261c0bd08', '037101', '9999-01-01 00:00:00', null, '037101', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd569b101-7486-494a-a0e5-92a47138ab07');
INSERT INTO `t_og_zh` VALUES ('494014c8-f261-4ee7-91f7-ca6f9b18604b', '006401', '9999-01-01 00:00:00', null, '006401', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '56eb0466-4267-43e0-a154-0068156c5f9c');
INSERT INTO `t_og_zh` VALUES ('4950fc2e-35cb-4670-ac90-409b1bab3111', '006456', '9999-01-01 00:00:00', null, '006456', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4436ae6c-a2e1-4ac1-be7a-48529cbd902c');
INSERT INTO `t_og_zh` VALUES ('49e4b8c4-6dab-4ad0-a882-bdffb08b09cd', '009259', '9999-01-01 00:00:00', null, '009259', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4a624c60-54ba-4b51-8756-9e3f8a57aead');
INSERT INTO `t_og_zh` VALUES ('4b1fb168-136a-47dd-b2f2-6b474ecc8d89', '042037', '9999-01-01 00:00:00', null, '042037', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'cee5a0ef-cb66-491f-bf49-ecdd57f5ca13');
INSERT INTO `t_og_zh` VALUES ('4b67ac5a-41bb-4b32-a84e-c51ed211ea94', '042098', '9999-01-01 00:00:00', null, '042098', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f5b44e09-f240-49a6-808f-3fa3a054d7bc');
INSERT INTO `t_og_zh` VALUES ('4b8a9508-daeb-4b97-87dc-6a7de1c26636', '004752', '9999-01-01 00:00:00', null, '004752', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '8ebd83b8-7162-42c9-ab2f-ab2d158f37fe');
INSERT INTO `t_og_zh` VALUES ('4b981622-1bbd-4393-ab0a-69c0abfc4af3', '035279', '9999-01-01 00:00:00', null, '035279', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '830fff8a-db0a-4041-a50a-ee83907f7e05');
INSERT INTO `t_og_zh` VALUES ('4cdb0f93-159d-427e-96c1-82e4a08abc30', '042043', '9999-01-01 00:00:00', null, '042043', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3714f10f-d445-43a4-86cc-b42aec29e541');
INSERT INTO `t_og_zh` VALUES ('4cf51f68-8f5f-487b-84bd-098b9d34af17', '037100', '9999-01-01 00:00:00', null, '037100', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', 'de53b7ca-fc60-499e-85ae-1d72e5d31aef');
INSERT INTO `t_og_zh` VALUES ('4d15e059-c090-480e-95b9-1611febc3b86', '007555', '9999-01-01 00:00:00', null, '007555', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3f4c72dd-2fcf-4fdd-8649-1b56eaad9b17');
INSERT INTO `t_og_zh` VALUES ('4d1769e2-8404-4eab-a46b-7575e60d756a', '006007', '9999-01-01 00:00:00', null, '006007', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2543b564-b144-4e8b-8171-862e03b05117');
INSERT INTO `t_og_zh` VALUES ('4d3b6314-86fd-40c4-bd99-fdb31f6efe2c', '006075', '9999-01-01 00:00:00', null, '006075', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'beb03093-f1f0-4633-b3f3-6cf8df4dca5d');
INSERT INTO `t_og_zh` VALUES ('4e93747b-33ce-4512-a1fe-3d49ad2eef12', '042028', '9999-01-01 00:00:00', null, '042028', '2016-12-06 13:31:00', '1', '2016-03-01 14:35:38', 'd2871636-6fa7-41a5-8f86-838042608a1b');
INSERT INTO `t_og_zh` VALUES ('4ea2e817-4b83-4de2-99db-4ed96e6f8469', '006467', '9999-01-01 00:00:00', '', '006467', '2016-12-06 13:31:00', '1', '2017-04-24 15:01:40', 'c63002ef-acb7-4503-9e1a-db05d6b6ec0f');
INSERT INTO `t_og_zh` VALUES ('4eb122b3-5d07-451f-b6b0-34879d219d58', '006458', '9999-01-01 00:00:00', null, '006458', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ed1cd989-1f65-4d26-ae52-10872b1e0f57');
INSERT INTO `t_og_zh` VALUES ('4ef69f2a-8735-42f6-8698-f517b0872257', '035256', '9999-01-01 00:00:00', null, '035256', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '04eb62e2-f06f-4e8b-a57c-537bf6dc946c');
INSERT INTO `t_og_zh` VALUES ('4f60306b-9ef1-415d-b966-d88da1ac3560', '006478', '9999-01-01 00:00:00', null, '006478', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '077da105-07e3-4529-bf69-3295e729a403');
INSERT INTO `t_og_zh` VALUES ('4f8140d1-9139-40a3-9f95-a37baf5a503e', '006439', '9999-01-01 00:00:00', null, '006439', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd40b5081-a9c5-46c2-aa2a-7a469b4fff16');
INSERT INTO `t_og_zh` VALUES ('4fb6c4fb-df1e-4d63-b270-2a2f92156ef3', '035257', '9999-01-01 00:00:00', null, '035257', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '8bfd666a-4f32-4bb4-8bec-bff4817a2def');
INSERT INTO `t_og_zh` VALUES ('4fe6a42a-d471-4d96-a507-ea341f3b05d4', '037089', '9999-01-01 00:00:00', null, '037089', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4f0c3c29-c97b-4b4f-9820-82bac7fcc8e1');
INSERT INTO `t_og_zh` VALUES ('4ff06de7-a52c-44da-afae-a4cb371ac79f', 'administrator', '9999-01-01 00:00:00', null, '123', '2016-12-06 13:31:00', '1', null, 'xnry02');
INSERT INTO `t_og_zh` VALUES ('51cee281-c23b-4aab-aa6c-95c1862cc6b9', 'JA04', '9999-01-01 00:00:00', '', '123456', '2016-03-17 20:01:34', '1', '2016-09-01 15:14:07', '63067ea1-617b-4379-998d-945a6d000557');
INSERT INTO `t_og_zh` VALUES ('5254451d-6b10-4188-981b-bfba2d2b32b9', '030452', '9999-01-01 00:00:00', null, '030452', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'dbde3969-5b84-4c4a-83a9-eb62c2ea1b58');
INSERT INTO `t_og_zh` VALUES ('5471957f-8a4d-43dc-ba92-dabad0344b86', '008301', '9999-01-01 00:00:00', null, '008301', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f3de5fe5-f0e0-45ec-83ea-afb0294abb00');
INSERT INTO `t_og_zh` VALUES ('54e560e9-2dfd-4f28-893e-eac8af988e0c', '037090', '9999-01-01 00:00:00', null, '037090', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3009e240-e910-4bd5-9aed-fde3fab67646');
INSERT INTO `t_og_zh` VALUES ('55d9baf7-dd5f-44ad-a832-09e66d184f1e', '035246', '9999-01-01 00:00:00', null, '035246', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6eb497a2-1331-4894-bca3-1befa30ec924');
INSERT INTO `t_og_zh` VALUES ('56812a0b-bf9f-4f5e-9434-8f34d9e0b6b5', '042013', '9999-01-01 00:00:00', null, '042013', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '86d3dbd7-8cc3-45a1-b102-f72819001808');
INSERT INTO `t_og_zh` VALUES ('56f11613-e159-466a-ab86-4a64da4a363b', '006406', '9999-01-01 00:00:00', '', '006406', '2016-12-06 13:31:00', '1', '2017-04-26 10:09:30', '030cc136-cab8-4d18-8b7d-45361e3ebcc2');
INSERT INTO `t_og_zh` VALUES ('58322738-7749-4f9b-9295-4d7f35fadca1', '005845', '9999-01-01 00:00:00', null, '005845', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd41362fd-9ba4-45c5-ae0f-12dce0776f81');
INSERT INTO `t_og_zh` VALUES ('58eede38-25d4-4412-bef2-d47460fdbb2c', '004961', '9999-01-01 00:00:00', null, '004961', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', '2e85b805-1837-4712-96ce-7fe52c1c9cc4');
INSERT INTO `t_og_zh` VALUES ('592eb54e-72fa-4597-a33b-f6139e2042b4', '042066', '9999-01-01 00:00:00', null, '042066', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '33710c16-8ea5-4f1c-8eff-169486895f19');
INSERT INTO `t_og_zh` VALUES ('5971a6dd-ec2c-4606-a06e-da4e89762efd', 'JA01', '9999-01-01 00:00:00', '', '123456', '2016-03-17 19:47:17', '1', null, 'b9d35066-ce0c-46eb-a23b-a47426c00fe8');
INSERT INTO `t_og_zh` VALUES ('5b565589-85ad-4c6a-a75a-7b200ec83e00', '038996', '9999-01-01 00:00:00', null, '038996', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ff0c854b-ddff-4319-a523-3afd716deb2c');
INSERT INTO `t_og_zh` VALUES ('5bd5ba6d-3dda-46f3-a5af-60c7a645fece', '004953', '9999-01-01 00:00:00', null, '004953', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e29b83bf-d04f-4663-a543-540c64da488b');
INSERT INTO `t_og_zh` VALUES ('5c58aec3-aad9-4095-8f26-03b5a9dee430', 'JB05', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:21:02', '1', '2016-09-01 15:21:57', '9b4becb2-733d-4dff-b6c9-bbead2a15b95');
INSERT INTO `t_og_zh` VALUES ('5c8aceec-2812-4e31-a958-8445ac1216c9', '037103', '9999-01-01 00:00:00', null, '037103', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '293b3890-ebaa-445f-b30f-c1aabc761165');
INSERT INTO `t_og_zh` VALUES ('5d9a1643-6453-434b-97e9-84a788e45988', '006460', '9999-01-01 00:00:00', null, '006460', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', 'c6b8ad1d-77f0-40ed-a6d1-ca0c4656abc8');
INSERT INTO `t_og_zh` VALUES ('5f221b0b-65c7-4e02-bbaa-77ae28f790c4', '006425', '9999-01-01 00:00:00', null, '006425', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a85b9449-7334-471b-9be3-dae09bf9c1bf');
INSERT INTO `t_og_zh` VALUES ('60af0f7b-588f-4341-8b24-1ec461d28b3b', '042068', '9999-01-01 00:00:00', null, '042068', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '92846ac2-b23f-49a6-8073-35bb1bfc9f58');
INSERT INTO `t_og_zh` VALUES ('61fbad4e-af70-4e97-9285-f8cd5f2265e5', 'JA02', '9999-01-01 00:00:00', '', '123456', '2016-03-17 19:59:51', '1', null, '2b24eb02-2b5e-458f-b885-e9c80246b183');
INSERT INTO `t_og_zh` VALUES ('6209bbba-8024-4099-833c-9e97361958f1', '042090', '9999-01-01 00:00:00', null, '042090', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bd264a0b-9405-4f82-84d3-637d9987662f');
INSERT INTO `t_og_zh` VALUES ('620c85c2-6c85-411d-ac49-e1a99193ba24', '042039', '9999-01-01 00:00:00', null, '042039', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ddf32e2c-5e99-43fc-8e42-87cd4b486dd7');
INSERT INTO `t_og_zh` VALUES ('621dc254-3854-4436-8e13-975ba775447e', '004926', '9999-01-01 00:00:00', null, '004926', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd714a43a-57c1-4f05-9294-7e6e4fb66b54');
INSERT INTO `t_og_zh` VALUES ('66787152-c4c5-4308-811a-359872fc2e61', '042083', '9999-01-01 00:00:00', null, '042083', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '882be32d-411e-419e-ba49-14d6896a28b6');
INSERT INTO `t_og_zh` VALUES ('66b1257c-cf6d-4ebf-bea4-dd903ca50a3d', '004959', '9999-01-01 00:00:00', null, '004959', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '660fe75d-eca2-4dd9-88d6-124fbbb4903f');
INSERT INTO `t_og_zh` VALUES ('66b620f9-6e73-40e2-a4cf-25d1da07ed9b', '006416注销', '9999-01-01 00:00:00', null, '006416注销', '2016-12-06 13:31:00', '0', '2016-02-03 09:55:32', 'e9408764-dd58-4357-9a46-4263ce0b3eea');
INSERT INTO `t_og_zh` VALUES ('67310690-1cc6-460b-ad07-bf47750cbb6d', 'JB08', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:27:58', '1', '2016-09-01 15:28:21', '2d82a7af-7b33-4560-9789-4ec90bf6d9c8');
INSERT INTO `t_og_zh` VALUES ('69d7bc1d-e98f-40ce-adb6-2fa4157f29cb', '035270', '9999-01-01 00:00:00', null, '035270', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', '61bfda57-ff3c-4546-81c8-2038bbabbb8b');
INSERT INTO `t_og_zh` VALUES ('6b08164d-2179-4d3f-b28a-997e4dfefa31', 'JB04', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:20:19', '1', '2016-09-01 15:20:55', 'b882395c-cd6f-4cb5-b811-d1d9a89ac6a9');
INSERT INTO `t_og_zh` VALUES ('6b5f2251-b451-4c1b-a8a8-c5e81a641a95', 'JB01', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:15:01', '1', '2016-09-01 15:18:58', '1f10437c-9d85-4fa9-b28e-61d45a729eed');
INSERT INTO `t_og_zh` VALUES ('6be0ff5c-c9f2-4775-8c2b-2889b81b59ab', '006132', '9999-01-01 00:00:00', null, '006132', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '039be972-b615-4c66-8c92-8b64ab3e8c3d');
INSERT INTO `t_og_zh` VALUES ('6e9f0534-3bbf-40db-bb7c-1ccc5de7fddf', 'JZ02', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:32:29', '1', '2016-09-01 15:33:15', '2271fe9e-6475-4df1-9943-97e0f634e5d1');
INSERT INTO `t_og_zh` VALUES ('70decacf-76b7-4b0a-9f8c-fbb61cf5d7ed', '777777', '9999-01-01 00:00:00', null, '777777', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6d8a5ac4-557e-4025-ac0f-74c673d93cf9');
INSERT INTO `t_og_zh` VALUES ('712d4765-af00-4dd7-b2e4-64b40c03a75e', '006092', '9999-01-01 00:00:00', null, '006092', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a42b4d1e-60ca-4d28-b702-6b0de8882fc2');
INSERT INTO `t_og_zh` VALUES ('71cc882d-d7d1-4a01-9603-b7d2ae2b7629', '004753', '9999-01-01 00:00:00', null, '004753', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7fd2417a-95e0-47be-8606-3696f24027a9');
INSERT INTO `t_og_zh` VALUES ('726a6ec3-2198-4acb-9abc-bdffe4072205', '037102', '9999-01-01 00:00:00', null, '037102', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2c59af07-6627-43d0-9eda-4cdcb9e03a92');
INSERT INTO `t_og_zh` VALUES ('7512a048-be24-446b-b9f2-c54e9c0c97a1', '042093', '9999-01-01 00:00:00', null, '042093', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b42ef242-e636-4012-aecf-41da720450d8');
INSERT INTO `t_og_zh` VALUES ('75367542-db77-4626-b3b9-b72c0eef4667', '037156', '9999-01-01 00:00:00', '', '037156', '2016-12-06 13:31:00', '1', '2017-01-20 13:53:57', '0f4adf6f-f2e9-43c8-8a8a-fea548679777');
INSERT INTO `t_og_zh` VALUES ('75705b06-22d8-4a93-b331-3cb2b5171e20', '042085', '9999-01-01 00:00:00', null, '042085', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'abf810a5-1147-4111-a9b9-0c62c5b99e44');
INSERT INTO `t_og_zh` VALUES ('75890510-d729-418f-8654-6cb0da984031', '042073', '9999-01-01 00:00:00', null, '042073', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '506be8c5-7429-44df-a751-d831bc7a718d');
INSERT INTO `t_og_zh` VALUES ('75c8ae98-e338-4d03-b1d1-fc8f08bc4e04', '042060', '9999-01-01 00:00:00', null, '042060', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9f9e692c-fbe0-46b7-aedd-d26e952a79c5');
INSERT INTO `t_og_zh` VALUES ('765c684e-2092-4d40-982e-0f076a735aed', '042053', '9999-01-01 00:00:00', null, '042053', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '09eb2294-0b9d-4e6a-8e00-3ae9e15f67d5');
INSERT INTO `t_og_zh` VALUES ('76670bd5-0884-484c-b8f2-260edd71a8cf', '037104', '9999-01-01 00:00:00', null, '037104', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '1325b7e1-4476-4ead-af4a-9a11bca731cd');
INSERT INTO `t_og_zh` VALUES ('76b45980-1e6c-4105-9afd-c8750d244a6f', '042011', '9999-01-01 00:00:00', null, '042011', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9887dc2c-ec15-43a0-8f66-85f249ab6c4d');
INSERT INTO `t_og_zh` VALUES ('776cbfa6-6ed6-4bef-b434-ab771b7efb1a', '017021', '9999-01-01 00:00:00', null, '017021', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '55201503-0043-4036-ad16-3512cb1b8053');
INSERT INTO `t_og_zh` VALUES ('78484167-8bcc-4c49-a08d-4023b98e630c', '008457', '9999-01-01 00:00:00', null, '008457', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6e114685-9f91-4097-beeb-76a82085fba8');
INSERT INTO `t_og_zh` VALUES ('785d88a1-e5d7-4818-b777-84006dfb48cf', '006474', '9999-01-01 00:00:00', null, '006474', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3546a9ec-a5e9-4f32-9db5-55843e7e065c');
INSERT INTO `t_og_zh` VALUES ('78b83fbe-172e-4856-88d3-117ea9d60b83', '006047', '9999-01-01 00:00:00', null, '006047', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '74c2d307-8fa1-4727-8832-0fe1f0ee52cd');
INSERT INTO `t_og_zh` VALUES ('791ab056-f2e9-469d-b3c7-23e8618b6681', '037602', '9999-01-01 00:00:00', null, '037602', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', 'a22dadd0-c63f-465d-9a2e-2daf94266a18');
INSERT INTO `t_og_zh` VALUES ('796c3a5b-046c-48c1-8fa7-ba64591d5d90', '004793', '9999-01-01 00:00:00', null, '004793', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'afe9cc76-75db-4314-8a4c-f1312173edc1');
INSERT INTO `t_og_zh` VALUES ('7a0395c9-e465-4abe-9ae4-c9afcc0b2fdb', '007294', '9999-01-01 00:00:00', null, '007294', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd93519c1-2728-42ce-9f9f-6da98873f979');
INSERT INTO `t_og_zh` VALUES ('7a3437a8-6650-4ff2-83ae-5fbc8e6fbb64', '042051', '9999-01-01 00:00:00', null, '042051', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b66e8578-8af6-43b1-be14-ad1b122f9665');
INSERT INTO `t_og_zh` VALUES ('7b205bac-7b9d-4150-8f0a-f0b0cc8b46ce', '042050', '9999-01-01 00:00:00', null, '042050', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'da7bda5f-b44d-4696-8e80-4593eef18dd5');
INSERT INTO `t_og_zh` VALUES ('7bcb8067-8e39-44b7-956b-45696827da86', '006113', '9999-01-01 00:00:00', null, '006113', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e7dd1eae-c897-4df8-9b35-2d8a810dbab6');
INSERT INTO `t_og_zh` VALUES ('7c919bcc-fc09-4b30-9a50-1269731d1b7d', '042062', '9999-01-01 00:00:00', null, '042062', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4ba70e6a-2df0-4eec-a6ac-b93fa271160b');
INSERT INTO `t_og_zh` VALUES ('7ca298be-d011-4792-9e10-d1ff9fea29b0', '006443', '9999-01-01 00:00:00', null, '006443', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '1c24944a-ba0c-41af-b443-265b2751173f');
INSERT INTO `t_og_zh` VALUES ('7d802f44-c8f0-43fd-9dfe-b29eb49fd717', '035269', '9999-01-01 00:00:00', null, '035269', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'cc26834d-7509-441e-b5da-af1c0a97c1f1');
INSERT INTO `t_og_zh` VALUES ('7e74ff83-9175-4c2e-a761-1013586f81b7', '035355', '9999-01-01 00:00:00', null, '035355', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '426801ac-9c42-419f-981c-a2928871cae3');
INSERT INTO `t_og_zh` VALUES ('805968cd-23bd-46eb-9638-41fa4bd33113', '005922', '9999-01-01 00:00:00', null, '005922', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2bd48ce6-92e7-411b-acac-4170d1ee1015');
INSERT INTO `t_og_zh` VALUES ('810b5239-f1d1-4b95-bb6f-dfd6ab35379e', '042035', '9999-01-01 00:00:00', null, '042035', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '664aab18-3daa-406e-b37c-b36a4b61461d');
INSERT INTO `t_og_zh` VALUES ('81120460-f8e3-4573-8cd7-0b064c63b26a', '042061', '9999-01-01 00:00:00', null, '042061', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '5155e0e8-4d31-4e39-b1dc-9af7a3558c70');
INSERT INTO `t_og_zh` VALUES ('8212deda-88fa-4a95-a157-33144df6c846', '042095', '9999-01-01 00:00:00', null, '042095', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '481914b4-88a7-421d-81cc-7967d9a0413d');
INSERT INTO `t_og_zh` VALUES ('82862401-c781-45d8-b4b5-08bf37e84046', '035265', '9999-01-01 00:00:00', null, '035265', '2016-12-06 13:31:00', '1', '2016-02-17 15:59:11', 'd96b145b-99ab-40dd-8333-55d256a16b98');
INSERT INTO `t_og_zh` VALUES ('833af88e-fb97-4930-b60f-2acef85d65b2', '035284', '9999-01-01 00:00:00', null, '035284', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '31d284e0-71a0-4a2c-987e-81777b6cec6b');
INSERT INTO `t_og_zh` VALUES ('84420a98-3c96-4ab3-b645-7ca9e79d53dc', '004956', '9999-01-01 00:00:00', null, '004956', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9c3380e6-dc54-465d-9401-7ca8db1a5ce1');
INSERT INTO `t_og_zh` VALUES ('84c5f007-e79f-4d79-97c5-c664d908bfc8', '004755', '9999-01-01 00:00:00', null, '004755', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7ce2c18a-cb88-49e8-8070-3b90960cdc17');
INSERT INTO `t_og_zh` VALUES ('84e1906b-027c-4321-9bee-37e82dcc854d', '006455', '9999-01-01 00:00:00', null, '006455', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e3f4efe7-a6cd-4758-bcd8-bbbeda536f3b');
INSERT INTO `t_og_zh` VALUES ('8607b14d-61b8-40b3-9c01-4ea978e03b0b', '042042', '9999-01-01 00:00:00', null, '042042', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '6a1589df-94c1-4db8-b0ca-70729165b7d5');
INSERT INTO `t_og_zh` VALUES ('86936a36-33c8-4737-840e-40247e190666', '042003', '9999-01-01 00:00:00', null, '042003', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '47abceb6-4fd6-4325-bb58-77d116121d5a');
INSERT INTO `t_og_zh` VALUES ('872bee1e-c5b3-4ddc-b133-b83478fe0a71', '035277', '9999-01-01 00:00:00', null, '035277', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', '5b84d688-6e3f-4839-b5fd-47fde11c882e');
INSERT INTO `t_og_zh` VALUES ('878436ae-5ced-4a76-bd3c-a312364c34ec', '037080', '9999-01-01 00:00:00', null, '037080', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd195b38d-2da0-4e1b-98ab-8c0b6e6d46ce');
INSERT INTO `t_og_zh` VALUES ('8af581c9-5638-434f-b33f-3836f90325ff', '004957', '9999-01-01 00:00:00', null, '004957', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', '4817ffb0-e644-4817-8c32-36ae5f8f6224');
INSERT INTO `t_og_zh` VALUES ('8b6317b6-5340-4cc6-b73d-8341ef1464e2', '005862', '9999-01-01 00:00:00', null, '005862', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'fff04f78-1b86-4b97-98f3-c7e44c1860c4');
INSERT INTO `t_og_zh` VALUES ('8ca8c8c8-a112-4288-b30e-675988afba45', '006399', '9999-01-01 00:00:00', '', '006399', '2016-12-06 13:31:00', '1', '2017-04-24 15:01:56', 'd37c3413-9e2e-48d1-8fcf-858002275101');
INSERT INTO `t_og_zh` VALUES ('8cc55908-79da-40f9-9a0d-7d6afb714ba4', '006403', '9999-01-01 00:00:00', null, '006403', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4e9e9f43-9f5a-4aff-a0e0-51bc01ac9e47');
INSERT INTO `t_og_zh` VALUES ('8d6d6366-570e-4a08-a8af-b19b1ed1f2f7', '037075', '9999-01-01 00:00:00', null, '037075', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd2250584-16de-48b8-8f89-03f4862fc11b');
INSERT INTO `t_og_zh` VALUES ('8dfeffab-2993-46b2-8e7c-0384982f69ba', 'JZ01', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:31:53', '1', '2016-09-01 15:32:26', '675a0ead-3eeb-4faf-8b10-4eb9b1fe0de7');
INSERT INTO `t_og_zh` VALUES ('8f712046-5b0e-4f94-872e-eb406b372e98', '006446', '9999-01-01 00:00:00', null, '006446', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4c593029-1480-4ab8-9598-442748162814');
INSERT INTO `t_og_zh` VALUES ('90b9102d-fa82-45df-b9ac-553cac1b3d4b', '042018', '9999-01-01 00:00:00', null, '042018', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f628109f-fb03-4afd-b784-064b1ed5d501');
INSERT INTO `t_og_zh` VALUES ('919dbd1f-505f-4f1e-b126-58c9a7c184e4', '005299', '9999-01-01 00:00:00', null, '005299', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '38780d84-ff61-4f07-a70e-08ddef683c2b');
INSERT INTO `t_og_zh` VALUES ('91f95d0e-4731-4698-b96f-6c54944c9f0f', '042056', '9999-01-01 00:00:00', null, '042056', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7a6c416f-cb8e-4882-8487-e1e9c1e65678');
INSERT INTO `t_og_zh` VALUES ('92488a83-acd6-415d-b50b-f44e369aa990', 'qzzxmj1', '2029-01-01 00:00:00', '', '123456', '2017-03-17 16:34:20', '1', '2017-03-17 16:34:26', '81c1ebb7-d589-4aca-9785-5eaa661b9993');
INSERT INTO `t_og_zh` VALUES ('93630b04-1750-42af-9e30-af5629568979', '006476', '9999-01-01 00:00:00', null, '006476', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ffdcb500-e335-46d6-8eb8-e083b0548763');
INSERT INTO `t_og_zh` VALUES ('943e490a-a3ab-4abc-a688-bac32adc351e', '006411', '9999-01-01 00:00:00', null, '006411', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'fe09a377-0754-476b-9912-221d3be0c8e6');
INSERT INTO `t_og_zh` VALUES ('94549977-0fbb-4bd3-841d-5366ef93f824', '035263', '9999-01-01 00:00:00', null, '035263', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bab46961-dfd9-40b1-81bf-454051c21eef');
INSERT INTO `t_og_zh` VALUES ('94c3802b-1ee9-46cc-9343-70709926a803', '007267', '9999-01-01 00:00:00', null, '007267', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f0fa4e9e-d656-4ea4-be95-25ea4db00459');
INSERT INTO `t_og_zh` VALUES ('94da0654-f185-479f-845f-d71e3c4433e5', '006471', '9999-01-01 00:00:00', null, '006471', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e02ef080-edb9-4e4f-a4b9-7748cc1dccfd');
INSERT INTO `t_og_zh` VALUES ('94ebf2a4-abd7-46b2-8ed4-bcfd9b4d058e', '006032', '9999-01-01 00:00:00', null, '006032', '2016-12-06 13:31:00', '1', '2016-07-27 12:40:44', 'ce172f03-3c6d-49d1-ae28-d3b1fd8ac4fe');
INSERT INTO `t_og_zh` VALUES ('955a56b0-95f1-4d63-9a47-4f83a60391b8', '006084', '9999-01-01 00:00:00', null, '006084', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'db893dc8-fe5d-4589-9792-70578ec90038');
INSERT INTO `t_og_zh` VALUES ('95f0ccf2-51c4-42b4-a441-72ed9f1bf1fd', '006413', '9999-01-01 00:00:00', null, '006413', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bb579a26-49fa-45c9-8606-dde23e6fa474');
INSERT INTO `t_og_zh` VALUES ('9712d7f2-ff8e-4f75-9f52-1743c88cc667', '037095注销', '9999-01-01 00:00:00', null, '037095注销', '2016-12-06 13:31:00', '0', '2016-02-03 09:55:32', '51dd2f21-35f7-4c50-9aa6-505bbc0ef6ab');
INSERT INTO `t_og_zh` VALUES ('97822aeb-3b7f-4487-8c24-9c06999c7b45', '042006', '9999-01-01 00:00:00', null, '042006', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9f0b76c0-841e-433b-a852-dcc5743e9091');
INSERT INTO `t_og_zh` VALUES ('98192cfb-bdc8-4d30-a7c9-8760c5217a3f', '037099注销', '9999-01-01 00:00:00', null, '037099注销', '2016-12-06 13:31:00', '0', '2016-02-03 09:55:32', 'a597001f-6e7d-4ed3-b1a4-808bfb9e0e9e');
INSERT INTO `t_og_zh` VALUES ('983c9247-37a9-4bd2-9134-503e3216bf62', '006137', '9999-01-01 00:00:00', null, '006137', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f423d20f-421f-4b14-a543-77365bf0303f');
INSERT INTO `t_og_zh` VALUES ('984a81bc-8c7a-4b36-b7aa-2e70f8ae3e3c', '042049', '9999-01-01 00:00:00', null, '042049', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '5114147d-194a-4485-9dd0-0ae8972db32f');
INSERT INTO `t_og_zh` VALUES ('9aac3b08-134e-47e7-a2ba-af74afd3f6b8', '035290', '9999-01-01 00:00:00', null, '035290', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a85b4b02-9ba2-4476-8ee4-18c7353be50e');
INSERT INTO `t_og_zh` VALUES ('9c11a2d0-70ca-4650-b5a7-31e20b96a8a3', '111111', '2029-01-01 00:00:00', '', '111111', '2017-04-14 13:55:22', '1', '2017-04-14 13:56:35', '96d95c0a-be1b-40c4-b4db-6ddf197bbc3c');
INSERT INTO `t_og_zh` VALUES ('9c3a3dcb-7f9d-49f4-ae49-435ce4c696dc', '035244', '9999-01-01 00:00:00', null, '035244', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '19801036-c99e-4026-b218-2d64fbbdae44');
INSERT INTO `t_og_zh` VALUES ('9c6bbc9b-404d-41ba-aea8-96b02da1b1f2', '042007', '9999-01-01 00:00:00', null, '042007', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2d6b269e-6926-4a55-87c8-a8808f307a89');
INSERT INTO `t_og_zh` VALUES ('9c6bbc9b-404d-41ba-aea8-96b02da1b992', 'waddmj1', '9999-01-01 00:00:00', null, '123456', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2d6b269e-6926-4a55-87c8-a8808f307999');
INSERT INTO `t_og_zh` VALUES ('9c6bbc9b-404d-41ba-aea8-96b02da1b993', 'baqmj1', '9999-01-01 00:00:00', null, '123456', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2d6b269e-6926-4a55-87c8-a8808f307990');
INSERT INTO `t_og_zh` VALUES ('9d7929c3-c74f-43a8-a920-9508d00bc931', 'JB03', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:19:44', '1', '2016-09-01 15:23:49', '1e22b89d-fd44-4674-8fb9-9f1b28850ffc');
INSERT INTO `t_og_zh` VALUES ('9f99b5ab-2f12-4658-ae77-0941a5f0eb29', '005852', '9999-01-01 00:00:00', '', '005852', '2016-12-06 13:31:00', '1', '2017-04-24 14:59:22', '512047d8-9f57-4a32-8e6f-747954826f19');
INSERT INTO `t_og_zh` VALUES ('a07c109e-2bb0-4620-b563-e8a10abcfb14', '009917', '9999-01-01 00:00:00', null, '009917', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '458fca61-873f-48a5-ab9a-bb04892eae59');
INSERT INTO `t_og_zh` VALUES ('a0c655a1-6138-4c02-9322-0360c4ee8f7c', '035243', '9999-01-01 00:00:00', null, '035243', '2016-12-06 13:31:00', '1', '2016-09-27 15:46:04', 'b3732dc9-6b70-47a3-ac0c-81691a740fd8');
INSERT INTO `t_og_zh` VALUES ('a0d29d33-db7f-4dd3-b523-dddf76a2e797', '009258', '9999-01-01 00:00:00', null, '009258', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '8b520f54-fccc-4a0b-ba7c-c690dc81893f');
INSERT INTO `t_og_zh` VALUES ('a1f97abf-cd56-4fef-9c20-4fb310076075', '005802', '9999-01-01 00:00:00', null, '005802', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', '0a98757b-dd3b-4f91-8617-980c102c60fc');
INSERT INTO `t_og_zh` VALUES ('a43b7cb8-a522-4746-9d7c-3239b6ad246c', '035248', '9999-01-01 00:00:00', null, '035248', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '82d6ee5a-0c4c-429d-8722-6aae2505e0ff');
INSERT INTO `t_og_zh` VALUES ('a4da05be-dfe6-4361-b013-a7a81023152d', '042078', '9999-01-01 00:00:00', null, '042078', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'faeea14a-822b-41f0-81ea-0fc105c87c45');
INSERT INTO `t_og_zh` VALUES ('a4f89edf-db35-4a59-b5d6-beb3268967f7', '042100', '9999-01-01 00:00:00', null, '042100', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3573939b-48fd-4593-a4d2-0f6d23732fa7');
INSERT INTO `t_og_zh` VALUES ('a71089c0-f51d-4553-90c8-24372754fa84', '004951', '9999-01-01 00:00:00', null, '004951', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2771f98b-2b30-4331-b7b3-d71af27d5d8f');
INSERT INTO `t_og_zh` VALUES ('a748a4e1-3608-4b89-9929-a52106ba617e', '035274', '9999-01-01 00:00:00', null, '035274', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f9bc0db9-64fe-4286-ba70-bc965afe54e5');
INSERT INTO `t_og_zh` VALUES ('a7628c91-2a74-410e-b6db-5d7d23dcde01', '006409', '9999-01-01 00:00:00', null, '006409', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '55487903-1ff7-4e0f-85b6-e065f5a99fcd');
INSERT INTO `t_og_zh` VALUES ('a9af32e2-957b-4136-a663-7a4498e6950d', '030451', '9999-01-01 00:00:00', null, '030451', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e53d581d-c592-44f5-8c7e-3ec41e74d15d');
INSERT INTO `t_og_zh` VALUES ('aa646b53-5352-4bbe-9b13-03d4454ea0b5', 'SJ01', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:35:19', '1', '2016-09-01 15:36:02', 'c08b580c-914c-4c6c-b360-abfb85d7e669');
INSERT INTO `t_og_zh` VALUES ('ab920723-d6f8-48c6-9955-4036a89598ab', '035291', '9999-01-01 00:00:00', null, '035291', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '1ab155c0-a4ff-4212-894d-ba2891e76e21');
INSERT INTO `t_og_zh` VALUES ('ac2021c2-5e80-492d-8119-88c9b1f07507', 'ZA01', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:36:48', '1', '2016-09-01 15:37:31', '0f84e080-2ed1-4a33-aeb7-44cf00d33be7');
INSERT INTO `t_og_zh` VALUES ('ad1239a3-4bff-489a-b3d3-5dfbf7b3647b', '008544', '9999-01-01 00:00:00', null, '008544', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9b187e23-5af2-450f-b268-7f377a00a6a5');
INSERT INTO `t_og_zh` VALUES ('ad6617c2-2c20-4844-ab96-7db92f9dd05b', '004964', '9999-01-01 00:00:00', null, '004964', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4f4d90c5-4a74-49eb-a715-b8dfd37ca4be');
INSERT INTO `t_og_zh` VALUES ('adb7a6f4-94e8-4651-9b60-a62491cd106e', '037078', '9999-01-01 00:00:00', '', '037078', '2016-12-06 13:31:00', '1', '2017-04-24 15:00:47', '99aa3b04-10db-4a08-a058-a6feb1a5e0ce');
INSERT INTO `t_og_zh` VALUES ('ae33d45d-b9bf-4eef-93c5-2ec86ec2d89a', '006404', '9999-01-01 00:00:00', null, '006404', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b3115f32-4d78-4e88-b39d-32720c7006d8');
INSERT INTO `t_og_zh` VALUES ('aeffede5-eeb7-43dc-b2df-105efb83863a', '006386', '9999-01-01 00:00:00', null, '006386', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '97cb518a-6ae0-44d1-b024-7397bdca7ff9');
INSERT INTO `t_og_zh` VALUES ('afcb1376-b201-4f26-87c8-39f2ca7c121d', '030490', '9999-01-01 00:00:00', null, '030490', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '5c806458-e61b-4ab3-80e4-0df0b5ea29c8');
INSERT INTO `t_og_zh` VALUES ('b0250034-058e-4ecc-9edb-cb05488d3af6', '004970', '9999-01-01 00:00:00', null, '004970', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f24b8d61-8c97-4dde-a59e-96fcf56023b2');
INSERT INTO `t_og_zh` VALUES ('b03aabd9-ecf5-45d3-bf63-40551d9add09', '037084', '9999-01-01 00:00:00', null, '037084', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a74a463d-0cc4-4c5b-af88-2e4c543791fc');
INSERT INTO `t_og_zh` VALUES ('b165ac13-6628-435a-a093-202acf0e18d5', '037096注销', '9999-01-01 00:00:00', null, '037096注销', '2016-12-06 13:31:00', '0', '2016-02-03 09:55:32', 'd16ef995-6d4b-4408-be32-f666163a09e4');
INSERT INTO `t_og_zh` VALUES ('b542e5cd-90cf-4527-b18c-2c89f050d4b5', '035173', '9999-01-01 00:00:00', null, '035173', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '794b1f80-0af9-41f4-82de-a3787ba77f74');
INSERT INTO `t_og_zh` VALUES ('b656aaba-7be5-41f1-88db-8af76ce4c9be', '004925', '9999-01-01 00:00:00', null, '004925', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '20a1387e-d6e0-4d95-b904-4698f2f63e43');
INSERT INTO `t_og_zh` VALUES ('b6815e19-3504-4c16-b2a7-cdf682292519', '006408', '9999-01-01 00:00:00', null, '006408', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'cd0945e2-4af4-4436-80bd-37d17fe3d132');
INSERT INTO `t_og_zh` VALUES ('b7a03b1a-9f6c-480e-8bc5-fd5189927669', '042026', '9999-01-01 00:00:00', null, '042026', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '747886bc-5dfc-4eb4-8393-25dcf75d6677');
INSERT INTO `t_og_zh` VALUES ('b86dd8da-1de2-4476-a22d-046bfa88cc74', '005582', '9999-01-01 00:00:00', null, '005582', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3e86d685-5603-4738-9355-913add21d70a');
INSERT INTO `t_og_zh` VALUES ('b8d7ddba-2e26-427d-a561-efef9bb6dab5', '006149', '9999-01-01 00:00:00', null, '006149', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '95b6ed59-75e6-42ca-b906-70114b22aee9');
INSERT INTO `t_og_zh` VALUES ('b926a4a1-b6b4-43e7-8b27-0fe5f0e0fb9d', '042064', '9999-01-01 00:00:00', null, '042064', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f617fae3-d8ef-4eb7-a938-39d200ca9de1');
INSERT INTO `t_og_zh` VALUES ('ba2627c1-ff11-4279-8d09-43583561e5f0', '030497', '9999-01-01 00:00:00', null, '030497', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '183623d0-686c-44c6-9fdf-9139a79ba553');
INSERT INTO `t_og_zh` VALUES ('baa8679d-a6fc-492d-b274-c513ce5e9c67', 'JA03', '9999-01-01 00:00:00', '', '123456', '2016-03-17 20:00:37', '1', '2016-09-01 15:14:27', '657668bd-4b88-4410-9787-aba92a9f1239');
INSERT INTO `t_og_zh` VALUES ('bb8590f5-72cf-482a-9caa-91f781ca367c', '006428', '9999-01-01 00:00:00', null, '006428', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'df380319-85a5-4d35-af3a-1db80d3a65a0');
INSERT INTO `t_og_zh` VALUES ('bc061595-f738-4984-bb4f-2c83eeac0121', '035273', '9999-01-01 00:00:00', null, '035273', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ce46023f-8004-4016-bfa6-d93e66c46b87');
INSERT INTO `t_og_zh` VALUES ('bc40f8d5-9482-4d6a-810e-a0deed24acd5', '042099', '9999-01-01 00:00:00', null, '042099', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bc06dce2-f25c-4848-b745-e75cb74c3d8d');
INSERT INTO `t_og_zh` VALUES ('bd954a01-da01-4380-b3ab-e288e1869bba', 'ZL', '9999-01-01 00:00:00', '', '123456', '2016-03-18 20:57:17', '1', '2016-03-18 20:57:36', 'de88436f-21b3-4cb5-ade7-dea5cfaea4d6');
INSERT INTO `t_og_zh` VALUES ('bdc1ec3e-a65a-4841-9b94-0912b2c216e6', '004967', '9999-01-01 00:00:00', null, '004967', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bf4b3128-b4cb-4ce8-a7ce-3b61d39c9539');
INSERT INTO `t_og_zh` VALUES ('be018d7d-014b-4e9a-865f-6a9157883748', '006395', '9999-01-01 00:00:00', null, '006395', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'fc30f919-20dc-49ef-bb98-2631893ca78f');
INSERT INTO `t_og_zh` VALUES ('bf3e167d-6e05-4d92-a16b-3b15ea28bb67', '037105注销', '9999-01-01 00:00:00', null, '037105注销', '2016-12-06 13:31:00', '0', '2016-02-03 09:55:32', '68fefc96-51ef-4742-acde-7b9a8263d417');
INSERT INTO `t_og_zh` VALUES ('bf5512dc-bd77-49a8-b0ab-ca0a93fd16f9', '006451', '9999-01-01 00:00:00', null, '006451', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '176497fd-44f4-4cbb-b987-cd350ffaaa39');
INSERT INTO `t_og_zh` VALUES ('bfa0df0a-9820-418a-934d-f9a9ae00bcb3', '035252', '9999-01-01 00:00:00', null, '035252', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '9647a534-9965-4e02-b8d6-634c54ef0742');
INSERT INTO `t_og_zh` VALUES ('c01dac19-27e4-4af4-b805-c8517a1f01e2', '037093', '9999-01-01 00:00:00', null, '037093', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ae8dd1ab-a00c-497e-ad2b-f8f9c20bd7b8');
INSERT INTO `t_og_zh` VALUES ('c028d3ff-3ffa-49af-b3f5-75da0b561a1f', '004960', '9999-01-01 00:00:00', null, '004960', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a93ba071-ef8e-46f0-959c-980957d7bc57');
INSERT INTO `t_og_zh` VALUES ('c1b62819-0483-49d5-a677-82a82b672297', '009387', '9999-01-01 00:00:00', null, '009387', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'a819d915-212a-4e0a-9ae4-e5d20576b95b');
INSERT INTO `t_og_zh` VALUES ('c1f7c68d-1996-4963-aeae-63e445d87837', '042046', '9999-01-01 00:00:00', null, '042046', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '34373c18-b945-4d21-8d45-83f50095be2a');
INSERT INTO `t_og_zh` VALUES ('c29625a6-7a01-4072-b13b-ed54462948ce', '006433', '9999-01-01 00:00:00', null, '006433', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'eba2ad7e-bacd-41c7-bdf9-5f5ed5540649');
INSERT INTO `t_og_zh` VALUES ('c417b0a2-139d-48ac-8060-a6300e28cd39', '006394', '9999-01-01 00:00:00', null, '006394', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '223d253f-8f2b-4776-90da-10471bbcf568');
INSERT INTO `t_og_zh` VALUES ('c4d1565d-5454-4dd1-aaf1-a20fd1ea2ffe', 'XZ03', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:38:51', '1', '2016-09-01 15:39:08', '5de8076c-425b-4ba4-bac7-e0bdaad32341');
INSERT INTO `t_og_zh` VALUES ('c61e0181-a684-4403-9813-1fa52deb99ee', '042052', '9999-01-01 00:00:00', null, '042052', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'fc0e27c7-7a7f-4f7f-84d9-dc33704af2a2');
INSERT INTO `t_og_zh` VALUES ('c6d51a76-b156-4bee-ae84-7f979792f423', '009263', '9999-01-01 00:00:00', null, '009263', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'abf904b1-49ab-4802-b638-b4107222f6e5');
INSERT INTO `t_og_zh` VALUES ('c71886bf-218b-442e-8dcd-c7a67b6432cf', '035287', '9999-01-01 00:00:00', null, '035287', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd534826e-1aef-40f4-b7b4-926832b015ce');
INSERT INTO `t_og_zh` VALUES ('c71c54cd-b5dd-44e8-a5b4-696521dcf6d1', '035280', '9999-01-01 00:00:00', null, '035280', '2016-12-06 13:31:00', '1', '2016-08-25 17:16:23', '6f4d7079-e7b5-4924-8b93-eac6ce8973be');
INSERT INTO `t_og_zh` VALUES ('c75b2db3-2679-4b51-9742-b9a0c8a8beee', '004792', '9999-01-01 00:00:00', null, '004792', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', 'fc44edc0-d277-4d29-8aed-7e8af06d6474');
INSERT INTO `t_og_zh` VALUES ('c810a303-5ebf-4a1b-851c-cda9965cf2c1', '004889', '9999-01-01 00:00:00', null, '004889', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '24aef2fc-f671-4d55-858b-7cc3e11dd165');
INSERT INTO `t_og_zh` VALUES ('c88d43b9-f59c-4a36-9288-65762f781fb7', '035262', '9999-01-01 00:00:00', null, '035262', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'eb453b98-eb95-45fc-9708-54d3c8eb91a1');
INSERT INTO `t_og_zh` VALUES ('c8c32bbc-c21f-4388-9b01-d5eb798d5915', 'PQ02', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:34:08', '1', '2016-09-02 13:29:45', 'eb62f5a2-52d9-4452-b034-87933544d3eb');
INSERT INTO `t_og_zh` VALUES ('c97efe5f-cd02-4659-bdb9-7fe71fa45b1e', '042001', '9999-01-01 00:00:00', null, '042001', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', 'badbec5a-8168-45ea-99e5-7c40adc9b326');
INSERT INTO `t_og_zh` VALUES ('ca183f8f-5db0-49ae-b526-4190acd44a09', '006470', '9999-01-01 00:00:00', null, '006470', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3540b9fb-3680-41d8-8db4-8e88a677684d');
INSERT INTO `t_og_zh` VALUES ('cb3187f3-bc38-4c42-8a97-2ef299bbbd05', '042040', '9999-01-01 00:00:00', null, '042040', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '99cb8d0a-46fb-497a-9598-57b905f70452');
INSERT INTO `t_og_zh` VALUES ('cb6c1de1-b69a-4ef9-abee-80af34fa48f3', '006431', '9999-01-01 00:00:00', null, '006431', '2016-12-06 13:31:00', '1', '2016-02-19 16:32:41', '1a46e05f-054f-4a17-85c1-ba5f7909e94c');
INSERT INTO `t_og_zh` VALUES ('cc16f748-f87d-417d-9780-eff9baaaedfe', '042071', '9999-01-01 00:00:00', null, '042071', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '7fcd875f-d753-43f3-bab7-ad6294bb8a62');
INSERT INTO `t_og_zh` VALUES ('ccbb75aa-91ca-440e-a4d5-23a9ab1ce271', '006389', '9999-01-01 00:00:00', null, '006389', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ff916388-7779-4c38-8bfa-6df715cb1a6b');
INSERT INTO `t_og_zh` VALUES ('cdbbf8cf-a6f4-4806-ae05-5d3f7e32a5d2', '037083', '9999-01-01 00:00:00', null, '037083', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '39500dd1-8ed7-41d1-a8d7-b36398f75251');
INSERT INTO `t_og_zh` VALUES ('ce6f715a-339b-40b0-8e52-5b3667d51db6', '042044', '9999-01-01 00:00:00', null, '042044', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd7513faa-8dc5-4a16-9b8e-ad3116e4b6df');
INSERT INTO `t_og_zh` VALUES ('ced26656-fbee-4075-aff7-ff8e3f44a9eb', '035295', '9999-01-01 00:00:00', null, '035295', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2ab42fd4-85d1-48e2-96e9-8c9ceec434c3');
INSERT INTO `t_og_zh` VALUES ('d09a65c3-d7e9-417d-ab6e-8bda1c4f866c', '030420', '9999-01-01 00:00:00', null, '030420', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b0a88506-81c4-408a-b097-d8109817b3c6');
INSERT INTO `t_og_zh` VALUES ('d0d57208-e36a-42a4-9bb8-8b732886ef96', '004962', '9999-01-01 00:00:00', null, '004962', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e266f65f-1e76-472b-9686-b537224f7944');
INSERT INTO `t_og_zh` VALUES ('d2e7b47f-d16d-4e52-9a9d-8a3a4a9add87', '006430', '9999-01-01 00:00:00', null, '006430', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '96efc037-f540-416a-a260-8f97fd7c9b25');
INSERT INTO `t_og_zh` VALUES ('d3ece890-6c9f-4bd4-9e2d-281e1b7fa80a', '009256', '9999-01-01 00:00:00', null, '009256', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b11edd3c-e428-47b5-a300-a0b6568dc734');
INSERT INTO `t_og_zh` VALUES ('d646287e-05e8-42a1-94dc-a6a635de6d19', '042055', '9999-01-01 00:00:00', null, '042055', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'bbc189ea-5446-4a72-ad29-ea7435b2278d');
INSERT INTO `t_og_zh` VALUES ('d7786436-090e-46fb-b4a8-4375f74b9473', '042004', '9999-01-01 00:00:00', null, '042004', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '95427b34-e105-4733-a623-7808712816fd');
INSERT INTO `t_og_zh` VALUES ('d79a6a68-1b28-4c70-af31-1ac0e7c95ec4', '035250', '9999-01-01 00:00:00', null, '035250', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '882ac1fe-50b9-4d85-a59f-edfbaab9839b');
INSERT INTO `t_og_zh` VALUES ('d819ebca-eb9a-4c76-802e-fc905a576860', '004963', '9999-01-01 00:00:00', null, '004963', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', 'a90ddee8-73ea-4fc7-b381-51d98a9274b5');
INSERT INTO `t_og_zh` VALUES ('d9374ce6-2ad5-4a1c-a9ac-f96eae88f84a', 'JB07', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:24:26', '1', '2016-09-01 15:25:04', '82b7062e-d535-433a-a19f-d863524cb068');
INSERT INTO `t_og_zh` VALUES ('d99f07ef-48d5-424b-9e6b-3cde35d82eef', '035245', '9999-01-01 00:00:00', null, '035245', '2016-12-06 13:31:00', '1', '2016-03-01 14:35:38', 'e17b50e3-5393-4fe8-92e0-5e1182ca894a');
INSERT INTO `t_og_zh` VALUES ('d9e24888-9ac1-41e8-876c-0c00a08c3598', '006441', '9999-01-01 00:00:00', null, '006441', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '413a23ed-052d-4701-8ce0-c638dbe8fbff');
INSERT INTO `t_og_zh` VALUES ('db20247e-29ca-4f84-a748-fd959d4c7c04', '006477', '9999-01-01 00:00:00', null, '006477', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4a956261-56bf-45d4-8d63-d07f664d1448');
INSERT INTO `t_og_zh` VALUES ('db5f87a5-b06b-4cb5-ba35-3df47ca1c996', '042022', '9999-01-01 00:00:00', null, '042022', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '67152e78-cdf3-4d30-a937-d479fef73f5b');
INSERT INTO `t_og_zh` VALUES ('db99a054-bf7e-4c94-a9fd-1e4fc9373cee', '004965', '9999-01-01 00:00:00', null, '004965', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '413ef7bb-67bb-4588-a292-908df64288c9');
INSERT INTO `t_og_zh` VALUES ('dc44b071-ed57-4014-b009-36ca781daa5a', '005955', '9999-01-01 00:00:00', null, '005955', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '629f0acd-fdc2-4faf-853b-dcc564eb58bc');
INSERT INTO `t_og_zh` VALUES ('dca0dd71-a99c-447c-b8d2-df431cac979b', '042080', '9999-01-01 00:00:00', null, '042080', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '19148123-92bf-4e0b-b72b-3526384ea6a8');
INSERT INTO `t_og_zh` VALUES ('dd64b7c7-9a2d-49a2-8af5-196470971b93', '004751', '9999-01-01 00:00:00', null, '004751', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', '862050b9-2e65-427a-8258-e553d9ae3a7f');
INSERT INTO `t_og_zh` VALUES ('dd8f866d-55b0-47fd-a88f-40f717521fb0', '006429', '9999-01-01 00:00:00', null, '006429', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', '1b91a0d3-8bb2-4eea-b9bd-15b7171b4137');
INSERT INTO `t_og_zh` VALUES ('dd931462-88e3-4eb9-8307-4063c89b1768', '006405', '9999-01-01 00:00:00', null, '006405', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ac4cb788-4de9-4f72-8110-0ef366929c15');
INSERT INTO `t_og_zh` VALUES ('de1017d5-409e-4e63-838f-8373b97fde23', '042092', '9999-01-01 00:00:00', null, '042092', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4f799db2-bd25-4005-8cae-97b7e72acd02');
INSERT INTO `t_og_zh` VALUES ('de477254-d455-493d-aad6-47af91d483ec', '006151', '9999-01-01 00:00:00', null, '006151', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'aa330964-c34c-4701-be0b-74fa0c561fb3');
INSERT INTO `t_og_zh` VALUES ('de93903e-d530-452b-81f8-86bc9ffc114f', '006450', '9999-01-01 00:00:00', null, '006450', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '3c4cc83a-15a3-473a-9ba1-968f86752881');
INSERT INTO `t_og_zh` VALUES ('deec7d7e-5c45-4832-8fbe-51e8b2b420b8', '042096', '9999-01-01 00:00:00', null, '042096', '2016-12-06 13:31:00', '0', '2016-08-25 17:16:23', '325f215b-52d1-4e21-89c0-8ce8ddb0b205');
INSERT INTO `t_og_zh` VALUES ('e05ab25c-7b10-4706-a155-8b507eb87024', '042087', '9999-01-01 00:00:00', null, '042087', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd00eb9c6-cde4-499c-bcdd-628bf11754ba');
INSERT INTO `t_og_zh` VALUES ('e15f2a77-0f0f-483c-858a-90a08bb89f8b', 'XZ01', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:37:53', '1', '2016-09-01 15:38:29', 'b838e734-4a03-4b0e-9db0-77cfd5970582');
INSERT INTO `t_og_zh` VALUES ('e2fecd24-edd5-44a5-8eda-5c87740a484d', '035254', '9999-01-01 00:00:00', null, '035254', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f54d478d-085f-42bb-a643-52de8d74acd6');
INSERT INTO `t_og_zh` VALUES ('e4c4d1f1-d28f-460a-ba96-d0c480c0acc9', '006426', '9999-01-01 00:00:00', null, '006426', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '202c668d-6f3d-4cd7-9eb3-0db987af49af');
INSERT INTO `t_og_zh` VALUES ('e55c4448-906e-4c2f-80f6-5b82dec6d249', '006422', '9999-01-01 00:00:00', null, '006422', '2016-12-06 13:31:00', '1', '2016-03-01 14:35:38', '7f2d0c14-264c-47f9-9d1b-c1d2bc1a8c4e');
INSERT INTO `t_og_zh` VALUES ('e73605da-f4cf-404a-a6b5-7f9c94919492', '006410', '9999-01-01 00:00:00', null, '006410', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '80dc5b44-1578-4a7f-91b8-645c4aaf9dcd');
INSERT INTO `t_og_zh` VALUES ('e79aed21-9599-4d14-b9f1-c3c19fe305d1', '035268', '9999-01-01 00:00:00', null, '035268', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f75089ed-3922-4482-9d54-e76ac97a93d0');
INSERT INTO `t_og_zh` VALUES ('e8e8dbfc-5aa1-41fc-a911-f26085c77a90', '035276', '9999-01-01 00:00:00', null, '035276', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '430899b9-21a9-4e74-837e-b87b4ea7b20b');
INSERT INTO `t_og_zh` VALUES ('e9be9f85-5ee3-437a-a045-e28d214ea73c', '006469', '9999-01-01 00:00:00', null, '006469', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'c1e3345e-65bb-45c3-9f0c-a179edfe9257');
INSERT INTO `t_og_zh` VALUES ('ea8a1627-75c5-40ab-8752-723b7a9246f8', '006448', '9999-01-01 00:00:00', null, '006448', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'c14f66e6-48c0-40ad-b566-4b46d503650b');
INSERT INTO `t_og_zh` VALUES ('eb5a613d-e74b-4fd5-94dc-5d475023d350', '042063', '9999-01-01 00:00:00', null, '042063', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '250a9e70-f47e-4161-8ef4-3ead5bf4c52d');
INSERT INTO `t_og_zh` VALUES ('eb671e84-329f-4733-9f9d-417704112add', '042054', '9999-01-01 00:00:00', null, '042054', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b889bcab-bf9b-4bae-a3d9-ca9655b25089');
INSERT INTO `t_og_zh` VALUES ('ebc5990a-0597-46f8-923e-71bb2baa8441', '037088', '9999-01-01 00:00:00', '', '037088', '2016-12-06 13:31:00', '1', '2017-04-24 15:01:22', 'b1fac620-9b4d-4bbd-8abd-413db5810df9');
INSERT INTO `t_og_zh` VALUES ('ec2d1a93-3735-4e9b-9c75-3ac9a4a79252', '006111', '9999-01-01 00:00:00', null, '006111', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'aa39250d-07f6-42a2-bbd9-47f0adc1c9dd');
INSERT INTO `t_og_zh` VALUES ('ecb4fe86-c051-479c-8c01-d4cc82e07c1f', '037076', '9999-01-01 00:00:00', null, '037076', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'c4cf444c-b756-47a8-84a0-3f6e18c8cf91');
INSERT INTO `t_og_zh` VALUES ('edebcc27-cf3d-4291-a222-c0b055a9f49d', '042002', '9999-01-01 00:00:00', null, '042002', '2016-12-06 13:31:00', '1', '2016-09-19 11:28:56', '6687ba00-31df-4e72-a5cd-091d52a81bd7');
INSERT INTO `t_og_zh` VALUES ('edf1672e-9285-4b84-9c00-37a8687e381c', 'JB13', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:30:52', '1', '2016-09-01 15:31:18', 'f4d29dae-c266-4541-a395-9ea06fef8841');
INSERT INTO `t_og_zh` VALUES ('ef2be832-a5d6-4986-90dd-2848ac59ce0e', '006472', '9999-01-01 00:00:00', null, '006472', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '38720f34-7db3-40b4-8975-78976c784b3a');
INSERT INTO `t_og_zh` VALUES ('efafb282-2cd9-4564-b483-4bb22846fb92', '030454', '9999-01-01 00:00:00', null, '030454', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'e4726d28-ef9a-49ae-9df4-b63ed21cf7bb');
INSERT INTO `t_og_zh` VALUES ('efe82da1-5853-48b0-92ef-5558210f0df7', '042023', '9999-01-01 00:00:00', null, '042023', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '8cdac137-dc25-4db2-a27c-294268cf248e');
INSERT INTO `t_og_zh` VALUES ('effa5ce0-d4ff-45aa-acce-721374dceb5d', '006463', '9999-01-01 00:00:00', null, '006463', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '2e415d87-e1e4-4fb4-adbe-fd94eac8bb1a');
INSERT INTO `t_og_zh` VALUES ('f00cb154-3d04-4055-866f-841820434db0', '006414', '9999-01-01 00:00:00', null, '006414', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd4a9af7f-6e24-4307-99ec-4f209c2fa4b4');
INSERT INTO `t_og_zh` VALUES ('f028be17-d17a-47a8-8100-1eee959f2058', '004954', '9999-01-01 00:00:00', null, '004954', '2016-12-06 13:31:00', '1', '2016-09-05 10:20:25', '12d74348-533f-4509-b25a-beff8d93f2c4');
INSERT INTO `t_og_zh` VALUES ('f0f8d373-982c-4cd0-b094-f827ac95ada1', '042091', '9999-01-01 00:00:00', null, '042091', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4e1fa6f1-2b15-428b-a0e3-217812e6b74d');
INSERT INTO `t_og_zh` VALUES ('f27773f4-cbc5-4a89-82f1-2c1fe25e29b9', '006157', '9999-01-01 00:00:00', null, '006157', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'b0df0b2c-d33d-456c-b857-31d08af416c2');
INSERT INTO `t_og_zh` VALUES ('f2b39721-c08e-4ce8-a80e-73d342e99974', '006452', '9999-01-01 00:00:00', null, '006452', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'c32a36bb-f637-4634-8534-d89334176af9');
INSERT INTO `t_og_zh` VALUES ('f2fcfca5-ecd6-4306-a095-8237d3e29178', '004798', '9999-01-01 00:00:00', null, '004798', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '8f057bec-d583-428b-b9cb-e21f8e231909');
INSERT INTO `t_og_zh` VALUES ('f4f68520-ffa2-485a-bef4-98fab0b7224d', '035225', '9999-01-01 00:00:00', null, '035225', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'dcd943bc-e9ad-4818-8db4-9d37488d161c');
INSERT INTO `t_og_zh` VALUES ('f6574f60-e97c-4d15-987b-d0d17f131974', '035281', '9999-01-01 00:00:00', null, '035281', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '4a8b7131-dd21-4eab-a7e7-2ba32c25c553');
INSERT INTO `t_og_zh` VALUES ('f77415e8-54aa-452c-b487-da2f23f47fe7', '042059', '9999-01-01 00:00:00', null, '042059', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f8c73fa3-a37a-4138-9ff6-c2807edd4112');
INSERT INTO `t_og_zh` VALUES ('f84e9997-03b9-45f9-8aa4-887cfadedfbb', '035255', '9999-01-01 00:00:00', null, '035255', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'd8149ff2-5882-4310-b13e-063149cac11a');
INSERT INTO `t_og_zh` VALUES ('f8546374-d947-4156-9241-cc28cab5ca00', '006398', '9999-01-01 00:00:00', null, '006398', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'ee432d5b-1e21-45b1-92f5-02708ab7a868');
INSERT INTO `t_og_zh` VALUES ('fa2469c5-9a17-49ba-86fb-8482b7df8e61', 'PQ01', '9999-01-01 00:00:00', '', '123456', '2016-09-01 15:33:27', '1', '2016-09-02 13:29:28', '731b0c13-8919-4381-8023-989b03fe64e2');
INSERT INTO `t_og_zh` VALUES ('fa41a2ed-cdbc-4585-ae0b-9ec23cc2f5d1', '006457', '9999-01-01 00:00:00', null, '006457', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'f759486b-3079-4122-8611-2e72472930b4');
INSERT INTO `t_og_zh` VALUES ('fb65b81e-a614-4214-9d88-3c3461db707a', '006468', '9999-01-01 00:00:00', null, '006468', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '591581de-558e-4078-8240-b958bac531ee');
INSERT INTO `t_og_zh` VALUES ('fc945060-28d7-4a2c-9eb7-1e147252b6cb', '006475', '9999-01-01 00:00:00', null, '006475', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'da3e5395-5b20-414e-9cd2-d8a8e59f0f73');
INSERT INTO `t_og_zh` VALUES ('fe19b366-9d20-4566-a31b-2ea5ff9ab1da', '042010', '9999-01-01 00:00:00', null, '042010', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', '58c75254-58e3-4ffe-b2ef-2246d841d28a');
INSERT INTO `t_og_zh` VALUES ('ff1a01f9-cdbb-4a8f-ac01-7f2c87f71deb', '006419', '9999-01-01 00:00:00', '', '006419', '2016-12-06 13:31:00', '1', '2017-01-20 17:24:29', '0699ad67-a4e2-4244-93fc-fa27babbaad8');
INSERT INTO `t_og_zh` VALUES ('ffb01d72-f8b1-4626-aa32-1cdb897847c1', '035296', '9999-01-01 00:00:00', null, '035296', '2016-12-06 13:31:00', '1', '2016-02-03 09:55:32', 'c674ec89-434a-4b32-9734-464ba9dbf93c');


DROP TABLE IF EXISTS `t_og_zzjg_t_og_ry`;
CREATE TABLE `t_og_zzjg_t_og_ry` (
  `t_og_zzjg_id` varchar(255) NOT NULL,
  `persons_id` varchar(255) NOT NULL,
  UNIQUE KEY `persons_id` (`persons_id`),
  KEY `FK35FB61FDE8A3C0C9` (`t_og_zzjg_id`),
  KEY `FK35FB61FDD19A0286` (`persons_id`),
  CONSTRAINT `FK35FB61FDD19A0286` FOREIGN KEY (`persons_id`) REFERENCES `t_og_ry` (`id`),
  CONSTRAINT `FK35FB61FDE8A3C0C9` FOREIGN KEY (`t_og_zzjg_id`) REFERENCES `t_og_zzjg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员单位关系表';



DROP TABLE IF EXISTS `t_og_cj`;
CREATE TABLE `t_og_cj` (
  `id` varchar(255) NOT NULL,
  `bm` varchar(255) NOT NULL COMMENT '编码',
  `mc` varchar(255) NOT NULL COMMENT '名称',
  `gxsj` datetime DEFAULT NULL COMMENT '更新时间',
  `dwid` varchar(255) NOT NULL COMMENT '所属单位id',
  `dsq_id` varchar(255) NOT NULL COMMENT '大社区id',
  KEY `index_t_og_cj_dwid` (`dwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='村居';

-- ----------------------------
-- Records of t_og_cj
-- ----------------------------
INSERT INTO `t_og_cj` VALUES ('c8299809-5c2e-4d76-a29c-5a354ee0afd7', '52019960000001', '兴隆居委会', '2017-01-23 15:38:55', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567', '9905e194-6faf-4edc-aced-5a3f7b46b4dc');
INSERT INTO `t_og_cj` VALUES ('c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1', '52019960000002', '香江居委会', '2016-08-18 13:24:29', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567', '9905e194-6faf-4edc-aced-5a3f7b46b4dc');
INSERT INTO `t_og_cj` VALUES ('3285bb5a-2336-4712-a0eb-cfc3c2dac77a', '52019960000003', '淮河居委会', '2016-08-18 13:24:53', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567', '9905e194-6faf-4edc-aced-5a3f7b46b4dc');
INSERT INTO `t_og_cj` VALUES ('6498f050-4581-4f5e-bc2a-f2babf9d9122', '52019960000004', '锦江居委会', '2016-08-18 13:25:26', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567', '9905e194-6faf-4edc-aced-5a3f7b46b4dc');
INSERT INTO `t_og_cj` VALUES ('3408ff75-3e04-4115-acf4-bb71fe7a86b3', '52019960000005', '浦江居委会', '2016-08-18 13:25:43', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567', '66d6a7e3-9578-4b9a-8940-8bf924aa66df');
INSERT INTO `t_og_cj` VALUES ('77034de2-0b62-4a45-851a-c39af465d70b', '52019964000001', '清水江居委会', '2016-08-18 13:26:20', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', '66d6a7e3-9578-4b9a-8940-8bf924aa66df');
INSERT INTO `t_og_cj` VALUES ('49755b49-44b9-48a1-9665-d1d8010d125c', '52019964000002', '盘江居委会', '2016-08-18 13:27:02', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', '66d6a7e3-9578-4b9a-8940-8bf924aa66df');
INSERT INTO `t_og_cj` VALUES ('90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc', '52019964000003', '华阳居委会', '2016-08-18 13:27:40', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', 'a159c084-2c0c-4c13-9233-a7a2eb32a70f');
INSERT INTO `t_og_cj` VALUES ('da6465ab-a9f5-4eba-8281-6508bc66d872', '52019964000004', '大兴居委会', '2016-08-18 13:28:13', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', 'a159c084-2c0c-4c13-9233-a7a2eb32a70f');
INSERT INTO `t_og_cj` VALUES ('60344d37-c313-44bd-9f1a-b2b307559223', '52019965000001', '长江居委会', '2016-08-18 13:28:41', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5', '108666b0-df3c-416a-beed-12ffeb29cc7c');
INSERT INTO `t_og_cj` VALUES ('dcb1ec3e-4353-43e3-834b-dc6786046ff6', '52019965000002', '黄河居委会', '2016-08-18 13:29:15', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5', '108666b0-df3c-416a-beed-12ffeb29cc7c');
INSERT INTO `t_og_cj` VALUES ('d7e0a9fd-df91-4d2a-9b76-414715bb3675', '52019965000003', '珠江居委会', '2016-08-18 13:30:15', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5', '108666b0-df3c-416a-beed-12ffeb29cc7c');
INSERT INTO `t_og_cj` VALUES ('b044d73d-3ce0-4e2f-b30e-b41c5caa9210', '52019961000001', '中曹居委会', '2016-08-18 13:30:43', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', '4e831d07-e1cd-4a99-9b25-5af42981c5fe');
INSERT INTO `t_og_cj` VALUES ('1d4b5a92-88da-4eb0-9e01-838f5433d974', '52019961000002', '五山居委会', '2016-08-18 13:31:34', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', '4e831d07-e1cd-4a99-9b25-5af42981c5fe');
INSERT INTO `t_og_cj` VALUES ('a601ee3b-83d0-481b-93b4-023b6dc0862a', '52019961000003', '北门居委会', '2016-08-18 13:31:53', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', '4e831d07-e1cd-4a99-9b25-5af42981c5fe');
INSERT INTO `t_og_cj` VALUES ('a5f855ce-ba77-4b07-89a6-8000567f9949', '52019961000004', '滨河居委会', '2016-08-18 13:32:16', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', '71246220-dcb6-45d5-a2b1-3e549006fd43');
INSERT INTO `t_og_cj` VALUES ('deedc0b2-8bd1-4d2c-a55c-ea4579796851', '52019961000005', '漓江居委会', '2016-08-18 13:32:36', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', '71246220-dcb6-45d5-a2b1-3e549006fd43');
INSERT INTO `t_og_cj` VALUES ('a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c', '52019963000001', '航天园居委会', '2016-08-18 13:33:43', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '71246220-dcb6-45d5-a2b1-3e549006fd43');
INSERT INTO `t_og_cj` VALUES ('fe939b6e-8825-40d8-a81b-1788420e8f34', '52019963000002', '红林居委会', '2016-08-18 13:34:08', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '25f5dfce-8631-40a9-ba73-74d05cc38943');
INSERT INTO `t_og_cj` VALUES ('e5421be0-f93d-44b3-8aa2-3c8449f351f4', '52019964000005', '枫阳居委会', '2016-08-18 13:35:00', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', '25f5dfce-8631-40a9-ba73-74d05cc38943');
INSERT INTO `t_og_cj` VALUES ('9c24f13f-6c2b-4b2c-af1e-39bb0f80bab5', '52019964000006', '松花江居委会', '2016-08-18 13:35:27', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', '25f5dfce-8631-40a9-ba73-74d05cc38943');
INSERT INTO `t_og_cj` VALUES ('e986e150-aa3f-4d0c-89b3-4654dc8a00e3', '52019962000001', '金溪居委会', '2016-08-18 13:35:52', 'b4f8b72d-1940-43a4-9518-d6606b8b768a', 'e9183276-2ff1-4115-a310-46e368c9cf40');
INSERT INTO `t_og_cj` VALUES ('ecac66d0-f5eb-4eae-9746-f3d8250342fe', '52019962000002', '金农居委会', '2016-08-18 13:36:17', 'b4f8b72d-1940-43a4-9518-d6606b8b768a', 'e9183276-2ff1-4115-a310-46e368c9cf40');
INSERT INTO `t_og_cj` VALUES ('2b169128-7a4b-4d07-bcba-3bb30af2b874', '52019962000003', '烂泥村', '2016-08-18 13:37:56', 'b4f8b72d-1940-43a4-9518-d6606b8b768a', '23cb732a-5536-431b-957c-0266cb9db9ec');
INSERT INTO `t_og_cj` VALUES ('db325b2c-5a49-4938-a3b1-9edba3374a05', '52019962000004', '金山村', '2016-08-18 13:37:42', 'b4f8b72d-1940-43a4-9518-d6606b8b768a', '23cb732a-5536-431b-957c-0266cb9db9ec');
INSERT INTO `t_og_cj` VALUES ('cb8111de-8c9b-42fa-af16-53002ef77f2a', '52019962000005', '竹林村', '2016-08-18 13:37:27', 'b4f8b72d-1940-43a4-9518-d6606b8b768a', '23cb732a-5536-431b-957c-0266cb9db9ec');
INSERT INTO `t_og_cj` VALUES ('b80382e9-cf11-4c4c-85b1-90e388b3ff7b', '52019963000003', '红艳村', '2016-08-18 13:38:29', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '6635a935-79cd-4fb9-a52e-9e95923630fe');
INSERT INTO `t_og_cj` VALUES ('ea50460a-0b4d-40a0-a297-e42924544d92', '52019963000004', '王宽村', '2016-08-18 13:38:48', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '6635a935-79cd-4fb9-a52e-9e95923630fe');
INSERT INTO `t_og_cj` VALUES ('2f116199-8ab4-450b-a0b1-38b591efec86', '52019963000005', '丰报云村', '2016-08-18 13:39:20', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '6635a935-79cd-4fb9-a52e-9e95923630fe');
INSERT INTO `t_og_cj` VALUES ('26a1e349-0a2e-439a-978e-d993fa3d529c', '52019963000006', '王武村', '2016-08-18 13:39:55', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '6635a935-79cd-4fb9-a52e-9e95923630fe');
INSERT INTO `t_og_cj` VALUES ('e87830cd-27ad-40b0-8145-172d358509a9', '52019963000007', '翁岩村', '2016-08-18 13:40:12', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '6635a935-79cd-4fb9-a52e-9e95923630fe');
INSERT INTO `t_og_cj` VALUES ('4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5', '52019963000008', '付官村', '2016-08-18 13:40:40', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '6635a935-79cd-4fb9-a52e-9e95923630fe');
INSERT INTO `t_og_cj` VALUES ('7b559751-37c3-4050-8861-20b8e7344273', '52019963000009', '陈亮村', '2016-08-18 13:41:04', '069c4e0f-a006-43d6-a9d7-36f488514c7d', '6635a935-79cd-4fb9-a52e-9e95923630fe');
INSERT INTO `t_og_cj` VALUES ('ce2152d9-4dce-4324-a99e-7fa7a657a735', '52019963000010', '扬中村', '2016-08-18 13:41:24', '069c4e0f-a006-43d6-a9d7-36f488514c7d', 'ddcc1a17-cf8c-49b0-80b6-92af8db8e1f6');
INSERT INTO `t_og_cj` VALUES ('ff86c3b0-b0b7-49cc-8300-ee7449c311f8', '52019963000011', '把伙村', '2016-08-18 13:41:53', '069c4e0f-a006-43d6-a9d7-36f488514c7d', 'ddcc1a17-cf8c-49b0-80b6-92af8db8e1f6');
INSERT INTO `t_og_cj` VALUES ('08343de7-fb05-4aaf-8151-5b81d802e5b0', '52019963000012', '麦乃村', '2016-08-18 13:42:27', '069c4e0f-a006-43d6-a9d7-36f488514c7d', 'ddcc1a17-cf8c-49b0-80b6-92af8db8e1f6');
INSERT INTO `t_og_cj` VALUES ('312426d3-5d43-4c31-a38a-c781fd42f1c2', '52019963000013', '周家寨村', '2016-08-18 13:42:48', '069c4e0f-a006-43d6-a9d7-36f488514c7d', 'aad45ba0-7baf-4f6c-ba19-c06cb5b84103');
INSERT INTO `t_og_cj` VALUES ('5858eb5b-7d00-46a5-ba8f-ecd1398a010f', '52019963000014', '场坝村', '2016-08-18 13:43:21', '069c4e0f-a006-43d6-a9d7-36f488514c7d', 'aad45ba0-7baf-4f6c-ba19-c06cb5b84103');
INSERT INTO `t_og_cj` VALUES ('d6644c6e-1b84-4817-b6e5-3a3740563ce1', '52019961000006', '大寨村', '2016-08-18 13:43:42', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', 'aad45ba0-7baf-4f6c-ba19-c06cb5b84103');
INSERT INTO `t_og_cj` VALUES ('224cd19e-1858-48d2-ae0a-efa5d18554fc', '52019961000007', '尖山村', '2016-08-18 13:44:11', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', 'aad45ba0-7baf-4f6c-ba19-c06cb5b84103');
INSERT INTO `t_og_cj` VALUES ('176e67f8-34fe-4f76-817d-cfc2b4d795a1', '52019961000008', '龙王村', '2016-08-18 13:44:31', '7b8cac4c-2e89-499c-b83e-63defc1da3dd', 'aad45ba0-7baf-4f6c-ba19-c06cb5b84103');
INSERT INTO `t_og_cj` VALUES ('1e022e30-62bc-41a3-b6d7-5ceb13c26566', '52019964000007', '毛寨村', '2016-08-18 13:45:46', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', 'aad45ba0-7baf-4f6c-ba19-c06cb5b84103');
INSERT INTO `t_og_cj` VALUES ('5461daea-f0ba-48c5-9801-c33bb1a8d052', '52019964000008', '珠显村', '2016-08-18 13:46:15', 'e5be6a62-4cbe-468e-9cf4-205b3abaea23', '6bc2dd84-3641-451a-b949-9173864e7f25');
INSERT INTO `t_og_cj` VALUES ('5af7b79b-6e8b-4493-b710-c7c506c667a1', '52019960000006', '中院村', '2016-08-18 13:46:43', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567', '6bc2dd84-3641-451a-b949-9173864e7f25');
INSERT INTO `t_og_cj` VALUES ('6880fd8b-ef84-4c41-a3ff-f8450f9f52b1', '52019960000007', '大坡村', '2016-08-18 13:47:02', 'e1048cb4-01fb-4dd6-a668-048cdf3a4567', '6bc2dd84-3641-451a-b949-9173864e7f25');
INSERT INTO `t_og_cj` VALUES ('2ed5b15f-4ff5-4465-8109-f487054934ff', '52019965000004', '洛解村', '2016-08-18 13:47:30', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5', '6bc2dd84-3641-451a-b949-9173864e7f25');
INSERT INTO `t_og_cj` VALUES ('2d4c8d58-2265-4a93-837d-ee64978ed3d5', '52019965000005', '瑞和居委会', '2016-08-18 13:48:41', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5', 'a159c084-2c0c-4c13-9233-a7a2eb32a70f');
INSERT INTO `t_og_cj` VALUES ('8dcc8bda-befb-4bc0-b98e-0c796cac3ef3', '52019965000006', '云凯居委会', '2016-08-18 13:49:15', '90b2efdf-3e66-4c8d-92ba-33fe5320bdb5', '108666b0-df3c-416a-beed-12ffeb29cc7c');

	

DROP TABLE IF EXISTS `t_og_cjxlgx`;
CREATE TABLE `t_og_cjxlgx` (
  `id` varchar(255) NOT NULL,
  `zsq_id` varchar(255) NOT NULL,
  `xlsq_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='村居相邻关系';

-- ----------------------------
-- Records of t_og_cjxlgx
-- ----------------------------
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000001', 'ce2152d9-4dce-4324-a99e-7fa7a657a735', 'ff86c3b0-b0b7-49cc-8300-ee7449c311f8');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000002', 'ce2152d9-4dce-4324-a99e-7fa7a657a735', '7b559751-37c3-4050-8861-20b8e7344273');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000003', 'ce2152d9-4dce-4324-a99e-7fa7a657a735', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000004', '8dcc8bda-befb-4bc0-b98e-0c796cac3ef3', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000005', '8dcc8bda-befb-4bc0-b98e-0c796cac3ef3', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000006', '8dcc8bda-befb-4bc0-b98e-0c796cac3ef3', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000007', '2d4c8d58-2265-4a93-837d-ee64978ed3d5', '2ed5b15f-4ff5-4465-8109-f487054934ff');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000008', '2d4c8d58-2265-4a93-837d-ee64978ed3d5', '60344d37-c313-44bd-9f1a-b2b307559223');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000009', '2d4c8d58-2265-4a93-837d-ee64978ed3d5', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000010', '2d4c8d58-2265-4a93-837d-ee64978ed3d5', '49755b49-44b9-48a1-9665-d1d8010d125c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000011', '2d4c8d58-2265-4a93-837d-ee64978ed3d5', '5461daea-f0ba-48c5-9801-c33bb1a8d052');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000012', '2ed5b15f-4ff5-4465-8109-f487054934ff', 'd7e0a9fd-df91-4d2a-9b76-414715bb3675');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000013', '2ed5b15f-4ff5-4465-8109-f487054934ff', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000014', '2ed5b15f-4ff5-4465-8109-f487054934ff', '60344d37-c313-44bd-9f1a-b2b307559223');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000015', '2ed5b15f-4ff5-4465-8109-f487054934ff', '5461daea-f0ba-48c5-9801-c33bb1a8d052');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000016', '2ed5b15f-4ff5-4465-8109-f487054934ff', '2d4c8d58-2265-4a93-837d-ee64978ed3d5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000017', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1', '2d4c8d58-2265-4a93-837d-ee64978ed3d5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000018', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1', '60344d37-c313-44bd-9f1a-b2b307559223');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000019', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000020', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000021', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1', '49755b49-44b9-48a1-9665-d1d8010d125c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000022', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000023', '5af7b79b-6e8b-4493-b710-c7c506c667a1', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000024', '5af7b79b-6e8b-4493-b710-c7c506c667a1', 'a601ee3b-83d0-481b-93b4-023b6dc0862a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000025', '5af7b79b-6e8b-4493-b710-c7c506c667a1', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000026', '5af7b79b-6e8b-4493-b710-c7c506c667a1', '6498f050-4581-4f5e-bc2a-f2babf9d9122');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000027', '5af7b79b-6e8b-4493-b710-c7c506c667a1', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000028', '5461daea-f0ba-48c5-9801-c33bb1a8d052', '2ed5b15f-4ff5-4465-8109-f487054934ff');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000029', '5461daea-f0ba-48c5-9801-c33bb1a8d052', '2d4c8d58-2265-4a93-837d-ee64978ed3d5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000030', '5461daea-f0ba-48c5-9801-c33bb1a8d052', '49755b49-44b9-48a1-9665-d1d8010d125c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000031', '5461daea-f0ba-48c5-9801-c33bb1a8d052', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000032', '5461daea-f0ba-48c5-9801-c33bb1a8d052', 'da6465ab-a9f5-4eba-8281-6508bc66d872');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000033', '5461daea-f0ba-48c5-9801-c33bb1a8d052', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000034', '5461daea-f0ba-48c5-9801-c33bb1a8d052', '1e022e30-62bc-41a3-b6d7-5ceb13c26566');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000035', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', '5461daea-f0ba-48c5-9801-c33bb1a8d052');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000036', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', 'da6465ab-a9f5-4eba-8281-6508bc66d872');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000037', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000038', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000039', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000040', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000041', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', '9c24f13f-6c2b-4b2c-af1e-39bb0f80bab5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000042', '1e022e30-62bc-41a3-b6d7-5ceb13c26566', 'e5421be0-f93d-44b3-8aa2-3c8449f351f4');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000043', '176e67f8-34fe-4f76-817d-cfc2b4d795a1', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000044', '176e67f8-34fe-4f76-817d-cfc2b4d795a1', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000045', '224cd19e-1858-48d2-ae0a-efa5d18554fc', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000046', '224cd19e-1858-48d2-ae0a-efa5d18554fc', 'a601ee3b-83d0-481b-93b4-023b6dc0862a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000047', '224cd19e-1858-48d2-ae0a-efa5d18554fc', 'a5f855ce-ba77-4b07-89a6-8000567f9949');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000048', '224cd19e-1858-48d2-ae0a-efa5d18554fc', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000049', '224cd19e-1858-48d2-ae0a-efa5d18554fc', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000050', '224cd19e-1858-48d2-ae0a-efa5d18554fc', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000051', '224cd19e-1858-48d2-ae0a-efa5d18554fc', '1d4b5a92-88da-4eb0-9e01-838f5433d974');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000052', '224cd19e-1858-48d2-ae0a-efa5d18554fc', '176e67f8-34fe-4f76-817d-cfc2b4d795a1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000053', '224cd19e-1858-48d2-ae0a-efa5d18554fc', 'b044d73d-3ce0-4e2f-b30e-b41c5caa9210');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000054', '224cd19e-1858-48d2-ae0a-efa5d18554fc', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000055', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000056', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000057', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000058', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1', 'a5f855ce-ba77-4b07-89a6-8000567f9949');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000059', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000060', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000061', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1', '1e022e30-62bc-41a3-b6d7-5ceb13c26566');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000062', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000063', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f', 'a5f855ce-ba77-4b07-89a6-8000567f9949');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000064', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000065', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000066', '312426d3-5d43-4c31-a38a-c781fd42f1c2', '9c24f13f-6c2b-4b2c-af1e-39bb0f80bab5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000067', '312426d3-5d43-4c31-a38a-c781fd42f1c2', '1e022e30-62bc-41a3-b6d7-5ceb13c26566');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000068', '312426d3-5d43-4c31-a38a-c781fd42f1c2', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000069', '312426d3-5d43-4c31-a38a-c781fd42f1c2', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000070', '312426d3-5d43-4c31-a38a-c781fd42f1c2', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000071', '312426d3-5d43-4c31-a38a-c781fd42f1c2', 'ea50460a-0b4d-40a0-a297-e42924544d92');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000072', '312426d3-5d43-4c31-a38a-c781fd42f1c2', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000073', '312426d3-5d43-4c31-a38a-c781fd42f1c2', '26a1e349-0a2e-439a-978e-d993fa3d529c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000074', '08343de7-fb05-4aaf-8151-5b81d802e5b0', 'e87830cd-27ad-40b0-8145-172d358509a9');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000075', '08343de7-fb05-4aaf-8151-5b81d802e5b0', '7b559751-37c3-4050-8861-20b8e7344273');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000076', '08343de7-fb05-4aaf-8151-5b81d802e5b0', 'ff86c3b0-b0b7-49cc-8300-ee7449c311f8');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000077', 'ff86c3b0-b0b7-49cc-8300-ee7449c311f8', '08343de7-fb05-4aaf-8151-5b81d802e5b0');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000078', 'ff86c3b0-b0b7-49cc-8300-ee7449c311f8', '7b559751-37c3-4050-8861-20b8e7344273');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000079', 'ff86c3b0-b0b7-49cc-8300-ee7449c311f8', 'ce2152d9-4dce-4324-a99e-7fa7a657a735');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000083', '7b559751-37c3-4050-8861-20b8e7344273', 'e87830cd-27ad-40b0-8145-172d358509a9');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000084', '7b559751-37c3-4050-8861-20b8e7344273', '08343de7-fb05-4aaf-8151-5b81d802e5b0');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000085', '7b559751-37c3-4050-8861-20b8e7344273', 'ff86c3b0-b0b7-49cc-8300-ee7449c311f8');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000086', '7b559751-37c3-4050-8861-20b8e7344273', 'ce2152d9-4dce-4324-a99e-7fa7a657a735');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000087', '7b559751-37c3-4050-8861-20b8e7344273', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000088', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5', '2f116199-8ab4-450b-a0b1-38b591efec86');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000089', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5', 'e87830cd-27ad-40b0-8145-172d358509a9');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000090', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5', '7b559751-37c3-4050-8861-20b8e7344273');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000091', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5', 'ce2152d9-4dce-4324-a99e-7fa7a657a735');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000092', 'e87830cd-27ad-40b0-8145-172d358509a9', '2f116199-8ab4-450b-a0b1-38b591efec86');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000093', 'e87830cd-27ad-40b0-8145-172d358509a9', '26a1e349-0a2e-439a-978e-d993fa3d529c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000094', 'e87830cd-27ad-40b0-8145-172d358509a9', '08343de7-fb05-4aaf-8151-5b81d802e5b0');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000095', 'e87830cd-27ad-40b0-8145-172d358509a9', '7b559751-37c3-4050-8861-20b8e7344273');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000096', 'e87830cd-27ad-40b0-8145-172d358509a9', 'ea50460a-0b4d-40a0-a297-e42924544d92');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000097', 'e87830cd-27ad-40b0-8145-172d358509a9', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000098', '26a1e349-0a2e-439a-978e-d993fa3d529c', '9c24f13f-6c2b-4b2c-af1e-39bb0f80bab5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000099', '26a1e349-0a2e-439a-978e-d993fa3d529c', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000100', '26a1e349-0a2e-439a-978e-d993fa3d529c', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000101', '26a1e349-0a2e-439a-978e-d993fa3d529c', 'fe939b6e-8825-40d8-a81b-1788420e8f34');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000102', '26a1e349-0a2e-439a-978e-d993fa3d529c', 'ea50460a-0b4d-40a0-a297-e42924544d92');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000103', '26a1e349-0a2e-439a-978e-d993fa3d529c', 'e87830cd-27ad-40b0-8145-172d358509a9');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000104', '26a1e349-0a2e-439a-978e-d993fa3d529c', '2f116199-8ab4-450b-a0b1-38b591efec86');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000105', '2f116199-8ab4-450b-a0b1-38b591efec86', '26a1e349-0a2e-439a-978e-d993fa3d529c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000106', '2f116199-8ab4-450b-a0b1-38b591efec86', 'e87830cd-27ad-40b0-8145-172d358509a9');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000107', '2f116199-8ab4-450b-a0b1-38b591efec86', '4f0fb26d-636d-4d8e-990b-f8ca21f4bdd5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000108', 'ea50460a-0b4d-40a0-a297-e42924544d92', '26a1e349-0a2e-439a-978e-d993fa3d529c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000109', 'ea50460a-0b4d-40a0-a297-e42924544d92', 'fe939b6e-8825-40d8-a81b-1788420e8f34');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000110', 'ea50460a-0b4d-40a0-a297-e42924544d92', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000111', 'ea50460a-0b4d-40a0-a297-e42924544d92', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000112', 'ea50460a-0b4d-40a0-a297-e42924544d92', 'e87830cd-27ad-40b0-8145-172d358509a9');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000113', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b', 'fe939b6e-8825-40d8-a81b-1788420e8f34');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000114', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b', '26a1e349-0a2e-439a-978e-d993fa3d529c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000115', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000116', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b', 'ea50460a-0b4d-40a0-a297-e42924544d92');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000117', '2b169128-7a4b-4d07-bcba-3bb30af2b874', 'db325b2c-5a49-4938-a3b1-9edba3374a05');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000118', '2b169128-7a4b-4d07-bcba-3bb30af2b874', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000119', '2b169128-7a4b-4d07-bcba-3bb30af2b874', 'e986e150-aa3f-4d0c-89b3-4654dc8a00e3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000120', 'db325b2c-5a49-4938-a3b1-9edba3374a05', 'cb8111de-8c9b-42fa-af16-53002ef77f2a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000121', 'db325b2c-5a49-4938-a3b1-9edba3374a05', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000122', 'db325b2c-5a49-4938-a3b1-9edba3374a05', '2b169128-7a4b-4d07-bcba-3bb30af2b874');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000123', 'cb8111de-8c9b-42fa-af16-53002ef77f2a', 'db325b2c-5a49-4938-a3b1-9edba3374a05');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000124', 'cb8111de-8c9b-42fa-af16-53002ef77f2a', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000125', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe', 'cb8111de-8c9b-42fa-af16-53002ef77f2a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000126', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe', 'db325b2c-5a49-4938-a3b1-9edba3374a05');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000127', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe', '2b169128-7a4b-4d07-bcba-3bb30af2b874');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000128', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe', '176e67f8-34fe-4f76-817d-cfc2b4d795a1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000129', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000130', 'e986e150-aa3f-4d0c-89b3-4654dc8a00e3', '2b169128-7a4b-4d07-bcba-3bb30af2b874');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000131', 'e986e150-aa3f-4d0c-89b3-4654dc8a00e3', 'ecac66d0-f5eb-4eae-9746-f3d8250342fe');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000132', '9c24f13f-6c2b-4b2c-af1e-39bb0f80bab5', '1e022e30-62bc-41a3-b6d7-5ceb13c26566');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000133', '9c24f13f-6c2b-4b2c-af1e-39bb0f80bab5', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000134', '9c24f13f-6c2b-4b2c-af1e-39bb0f80bab5', '26a1e349-0a2e-439a-978e-d993fa3d529c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000135', 'e5421be0-f93d-44b3-8aa2-3c8449f351f4', '1e022e30-62bc-41a3-b6d7-5ceb13c26566');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000136', 'fe939b6e-8825-40d8-a81b-1788420e8f34', '26a1e349-0a2e-439a-978e-d993fa3d529c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000137', 'fe939b6e-8825-40d8-a81b-1788420e8f34', 'b80382e9-cf11-4c4c-85b1-90e388b3ff7b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000138', 'fe939b6e-8825-40d8-a81b-1788420e8f34', 'ea50460a-0b4d-40a0-a297-e42924544d92');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000139', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000140', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c', 'a5f855ce-ba77-4b07-89a6-8000567f9949');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000141', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000142', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000143', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c', '312426d3-5d43-4c31-a38a-c781fd42f1c2');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000144', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000145', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851', '6498f050-4581-4f5e-bc2a-f2babf9d9122');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000146', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000147', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851', '5af7b79b-6e8b-4493-b710-c7c506c667a1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000148', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851', 'a601ee3b-83d0-481b-93b4-023b6dc0862a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000149', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851', 'a5f855ce-ba77-4b07-89a6-8000567f9949');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000150', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000151', 'a5f855ce-ba77-4b07-89a6-8000567f9949', '5858eb5b-7d00-46a5-ba8f-ecd1398a010f');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000152', 'a5f855ce-ba77-4b07-89a6-8000567f9949', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000153', 'a5f855ce-ba77-4b07-89a6-8000567f9949', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000154', 'a5f855ce-ba77-4b07-89a6-8000567f9949', 'a601ee3b-83d0-481b-93b4-023b6dc0862a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000155', 'a5f855ce-ba77-4b07-89a6-8000567f9949', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000156', 'a5f855ce-ba77-4b07-89a6-8000567f9949', 'a3dbfdf8-c221-437a-bfa0-337e9e9a6f2c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000157', 'a601ee3b-83d0-481b-93b4-023b6dc0862a', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000158', 'a601ee3b-83d0-481b-93b4-023b6dc0862a', '5af7b79b-6e8b-4493-b710-c7c506c667a1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000159', 'a601ee3b-83d0-481b-93b4-023b6dc0862a', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000160', 'a601ee3b-83d0-481b-93b4-023b6dc0862a', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000161', 'a601ee3b-83d0-481b-93b4-023b6dc0862a', 'a5f855ce-ba77-4b07-89a6-8000567f9949');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000162', '1d4b5a92-88da-4eb0-9e01-838f5433d974', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000163', 'b044d73d-3ce0-4e2f-b30e-b41c5caa9210', '224cd19e-1858-48d2-ae0a-efa5d18554fc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000164', 'd7e0a9fd-df91-4d2a-9b76-414715bb3675', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000165', 'd7e0a9fd-df91-4d2a-9b76-414715bb3675', '2ed5b15f-4ff5-4465-8109-f487054934ff');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000166', 'd7e0a9fd-df91-4d2a-9b76-414715bb3675', '60344d37-c313-44bd-9f1a-b2b307559223');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000167', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6', 'd7e0a9fd-df91-4d2a-9b76-414715bb3675');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000168', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6', '2ed5b15f-4ff5-4465-8109-f487054934ff');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000169', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6', '60344d37-c313-44bd-9f1a-b2b307559223');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000170', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6', '8dcc8bda-befb-4bc0-b98e-0c796cac3ef3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000171', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000172', '60344d37-c313-44bd-9f1a-b2b307559223', '2ed5b15f-4ff5-4465-8109-f487054934ff');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000173', '60344d37-c313-44bd-9f1a-b2b307559223', 'd7e0a9fd-df91-4d2a-9b76-414715bb3675');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000174', '60344d37-c313-44bd-9f1a-b2b307559223', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000175', '60344d37-c313-44bd-9f1a-b2b307559223', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000176', '60344d37-c313-44bd-9f1a-b2b307559223', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000177', '60344d37-c313-44bd-9f1a-b2b307559223', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000178', '60344d37-c313-44bd-9f1a-b2b307559223', '2d4c8d58-2265-4a93-837d-ee64978ed3d5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000179', 'da6465ab-a9f5-4eba-8281-6508bc66d872', 'da6465ab-a9f5-4eba-8281-6508bc66d872');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000180', 'da6465ab-a9f5-4eba-8281-6508bc66d872', '1e022e30-62bc-41a3-b6d7-5ceb13c26566');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000181', 'da6465ab-a9f5-4eba-8281-6508bc66d872', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000182', 'da6465ab-a9f5-4eba-8281-6508bc66d872', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000183', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc', '5461daea-f0ba-48c5-9801-c33bb1a8d052');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000184', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc', '49755b49-44b9-48a1-9665-d1d8010d125c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000185', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000186', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000187', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc', 'da6465ab-a9f5-4eba-8281-6508bc66d872');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000188', '49755b49-44b9-48a1-9665-d1d8010d125c', '5461daea-f0ba-48c5-9801-c33bb1a8d052');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000189', '49755b49-44b9-48a1-9665-d1d8010d125c', '2d4c8d58-2265-4a93-837d-ee64978ed3d5');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000190', '49755b49-44b9-48a1-9665-d1d8010d125c', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000191', '49755b49-44b9-48a1-9665-d1d8010d125c', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000192', '49755b49-44b9-48a1-9665-d1d8010d125c', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000193', '77034de2-0b62-4a45-851a-c39af465d70b', 'da6465ab-a9f5-4eba-8281-6508bc66d872');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000194', '77034de2-0b62-4a45-851a-c39af465d70b', '5461daea-f0ba-48c5-9801-c33bb1a8d052');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000195', '77034de2-0b62-4a45-851a-c39af465d70b', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000196', '77034de2-0b62-4a45-851a-c39af465d70b', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000197', '77034de2-0b62-4a45-851a-c39af465d70b', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000198', '77034de2-0b62-4a45-851a-c39af465d70b', '6498f050-4581-4f5e-bc2a-f2babf9d9122');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000199', '77034de2-0b62-4a45-851a-c39af465d70b', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000200', '77034de2-0b62-4a45-851a-c39af465d70b', 'd6644c6e-1b84-4817-b6e5-3a3740563ce1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000201', '77034de2-0b62-4a45-851a-c39af465d70b', '1e022e30-62bc-41a3-b6d7-5ceb13c26566');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000202', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', '49755b49-44b9-48a1-9665-d1d8010d125c');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000203', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000204', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', '60344d37-c313-44bd-9f1a-b2b307559223');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000205', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', 'dcb1ec3e-4353-43e3-834b-dc6786046ff6');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000206', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', '8dcc8bda-befb-4bc0-b98e-0c796cac3ef3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000207', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000208', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000209', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000210', '3408ff75-3e04-4115-acf4-bb71fe7a86b3', '90f5aebc-ecb6-40c7-a5e2-1e6c0d50dccc');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000211', '6498f050-4581-4f5e-bc2a-f2babf9d9122', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000212', '6498f050-4581-4f5e-bc2a-f2babf9d9122', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000213', '6498f050-4581-4f5e-bc2a-f2babf9d9122', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000214', '6498f050-4581-4f5e-bc2a-f2babf9d9122', '5af7b79b-6e8b-4493-b710-c7c506c667a1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000215', '6498f050-4581-4f5e-bc2a-f2babf9d9122', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000216', '6498f050-4581-4f5e-bc2a-f2babf9d9122', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000217', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a', '77034de2-0b62-4a45-851a-c39af465d70b');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000218', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000219', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000220', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000221', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a', '5af7b79b-6e8b-4493-b710-c7c506c667a1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000222', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000223', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a', '6498f050-4581-4f5e-bc2a-f2babf9d9122');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000224', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1', '60344d37-c313-44bd-9f1a-b2b307559223');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000225', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1', '3408ff75-3e04-4115-acf4-bb71fe7a86b3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000226', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1', '8dcc8bda-befb-4bc0-b98e-0c796cac3ef3');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000227', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1', '6880fd8b-ef84-4c41-a3ff-f8450f9f52b1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000228', 'c9ff27fb-33bb-4343-9cbe-9ec8ddca2ee1', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000229', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7', '3285bb5a-2336-4712-a0eb-cfc3c2dac77a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000230', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7', '6498f050-4581-4f5e-bc2a-f2babf9d9122');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000231', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7', '5af7b79b-6e8b-4493-b710-c7c506c667a1');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000232', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7', 'deedc0b2-8bd1-4d2c-a55c-ea4579796851');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000233', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7', 'a601ee3b-83d0-481b-93b4-023b6dc0862a');
INSERT INTO `t_og_cjxlgx` VALUES ('gx0000234', 'c8299809-5c2e-4d76-a29c-5a354ee0afd7', '224cd19e-1858-48d2-ae0a-efa5d18554fc');


DROP TABLE IF EXISTS `t_zdlx`;
CREATE TABLE `t_zdlx` (
  `id` varchar(255) NOT NULL,
  `fl` varchar(24) DEFAULT NULL COMMENT '分类',
  `bm` varchar(50) NOT NULL COMMENT '编码',
  `bz` varchar(150) DEFAULT NULL COMMENT '备注',
  `mc` varchar(36) NOT NULL COMMENT '名称',
  `zt` varchar(36) NOT NULL COMMENT '状态',
  `sjc` datetime NOT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '字典类型';

INSERT INTO `t_zdlx` VALUES ('0000000001', null, 'xb', null, '性别', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('0000000002', null, 'zt', null, '状态', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('0000000003', null, 'sf', null, '是否', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('0000000004', null, 'yw', null, '有无', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('0000000017', '系统管理', 'zylx', '系统管理', '资源类型', '1', '2016-07-28 23:33:49');
INSERT INTO `t_zdlx` VALUES ('0000000018', '系统管理', 'xl', '系统管理', '学历', '1', '2016-07-28 23:59:38');
INSERT INTO `t_zdlx` VALUES ('0000000019', '系统管理', 'mz', '系统管理', '民族', '1', '2016-07-29 00:04:55');
INSERT INTO `t_zdlx` VALUES ('0000000020', '系统管理', 'zzmm', '系统管理', '政治面貌', '1', '2016-07-29 00:09:49');
INSERT INTO `t_zdlx` VALUES ('0000000021', '系统管理', 'zw', '系统管理', '职务', '1', '2016-07-29 00:17:20');
INSERT INTO `t_zdlx` VALUES ('0000000022', '系统管理', 'zzzt', '系统管理', '在职状态', '1', '2016-07-29 07:42:35');
INSERT INTO `t_zdlx` VALUES ('0000000023', '系统管理', 'dwsx', '系统管理', '单位属性', '1', '2016-07-29 08:01:30');
INSERT INTO `t_zdlx` VALUES ('dca71f42-ecff-482a-ac83-46b4278672ad', '系统管理', 'sq', '', '社区', '1', '2016-08-20 20:43:00');

INSERT INTO `t_zdlx` VALUES ('0000000007', null, 'njjg', null, '尿检结果', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('0000000008', null, 'czqk', null, '处置情况', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('0000000009', null, 'rylbbq', null, '人员类别标签', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('0000000010', null, 'yjjlzt', null, '预警记录状态', '1', '2016-06-26 21:23:01');

INSERT INTO `t_zdlx` VALUES ('0000000036', null, 'tjys', null, '统计颜色', '1', '2016-11-16 17:08:44');
INSERT INTO `t_zdlx` VALUES ('0000000037', null, 'xtzt', null, '系统状态', '1', '2016-06-26 21:23:01');
INSERT INTO `t_zdlx` VALUES ('zy2d4ed3b8e', '案件监控管理', 'zy', NULL, '职业', '1', '2017-04-11 15:42:10');



DROP TABLE IF EXISTS `t_zdx`;
CREATE TABLE `t_zdx` (
  `id` varchar(255) NOT NULL,
  `bm` varchar(36) NOT NULL COMMENT '编码',
  `bz` varchar(150) DEFAULT NULL COMMENT '备注',
  `mc` varchar(50) NOT NULL COMMENT '名称',
  `sx` int(11) DEFAULT NULL COMMENT '顺序',
  `zt` varchar(36) NOT NULL COMMENT '状态',
  `sjc` datetime NOT NULL COMMENT '时间戳',
  `zdlx_id` varchar(255) NOT NULL COMMENT '字典类型id',
  `sjzdx_id` varchar(255) DEFAULT NULL COMMENT '上级字典项id',
  PRIMARY KEY (`id`),
  KEY `FK68FAC4350BBE766` (`zdlx_id`),
  KEY `FK68FAC43D19428FE` (`sjzdx_id`),
  CONSTRAINT `FK68FAC43D19428FE` FOREIGN KEY (`sjzdx_id`) REFERENCES `t_zdx` (`id`),
  CONSTRAINT `FK68FAC4350BBE766` FOREIGN KEY (`zdlx_id`) REFERENCES `t_zdlx` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '字典项';

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000001001', '1', '', '男', '1', '1', '2016-06-26 21:23:01', '0000000001', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000001002', '2', '', '女', '2', '1', '2016-06-26 21:23:01', '0000000001', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000001003', '0', '', '未知', '3', '1', '2016-06-26 21:23:01', '0000000001', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000002001', '0', '', '停用', '2', '1', '2016-08-24 11:22:39', '0000000002', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000002002', '1', '', '启用', '1', '1', '2016-08-24 11:23:14', '0000000002', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000037001', 'init', '', '初始化', '1', '1', '2016-08-24 11:23:14', '0000000037', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000003001', '0', '', '否', '1', '1', '2016-06-26 21:23:01', '0000000003', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000003002', '1', '', '是', '2', '1', '2016-06-26 21:23:01', '0000000003', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000004001', '0', '', '无', '1', '1', '2016-06-26 21:23:01', '0000000004', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000004002', '1', '', '有', '2', '1', '2016-06-26 21:23:01', '0000000004', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000017001', '0', '', '按钮', '1', '1', '2016-07-28 23:34:25', '0000000017', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000017002', '1', '', '链接', '2', '1', '2016-07-28 23:35:19', '0000000017', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000018001', '0', '', '大学本科', '1', '1', '2016-07-29 00:00:50', '0000000018', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000018002', '1', '', '专科', '2', '1', '2016-07-29 00:01:48', '0000000018', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000018003', '2', '', '硕士', '3', '1', '2016-07-29 00:02:29', '0000000018', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019001', '0', '', '汉族', '1', '1', '2016-07-29 00:05:28', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019002', '1', '', '壮族', '2', '1', '2016-07-29 00:06:10', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019003', '2', '', '满族', '3', '1', '2016-07-29 00:06:37', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019004', '3', '', '回族', '4', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019005', '4', '', '苗族', '5', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019006', '5', '', '维吾尔族', '6', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019007', '6', '', '土家族', '7', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019008', '7', '', '彝族', '8', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019009', '8', '', '蒙古族', '9', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019010', '9', '', '藏族', '10', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019011', '10', '', '布依族', '11', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019012', '11', '', '侗族', '12', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019013', '12', '', '瑶族', '13', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019014', '13', '', '朝鲜族', '14', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019015', '14', '', '白族', '15', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019016', '15', '', '哈尼族', '16', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019017', '16', '', '哈萨克族', '17', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019018', '17', '', '黎族', '18', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019019', '18', '', '傣族', '19', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019020', '19', '', '畲族', '20', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019021', '20', '', '傈僳族', '21', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019022', '21', '', '仡佬族', '22', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019023', '22', '', '东乡族', '23', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019024', '23', '', '高山族', '24', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019025', '24', '', '拉祜族', '25', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019026', '25', '', '水族', '26', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019027', '26', '', '佤族', '27', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019028', '27', '', '纳西族', '28', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019029', '28', '', '羌族', '29', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019030', '29', '', '土族', '30', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019031', '30', '', '仫佬族', '31', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019032', '31', '', '锡伯族', '32', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019033', '32', '', '柯尔克孜族', '33', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019034', '33', '', '达斡尔族', '34', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019035', '34', '', '景颇族', '35', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019036', '35', '', '毛南族', '36', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019037', '36', '', '撒拉族', '37', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019038', '37', '', '塔吉克族', '38', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019039', '38', '', '阿昌族', '39', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019040', '39', '', '普米族', '40', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019041', '40', '', '鄂温克族', '41', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019042', '41', '', '怒族', '42', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019043', '42', '', '京族', '43', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019044', '43', '', '基诺族', '44', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019045', '44', '', '德昂族', '45', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019046', '45', '', '保安族', '46', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019047', '46', '', '俄罗斯族', '47', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019048', '47', '', '裕固族', '48', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019049', '48', '', '乌兹别克族', '49', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019050', '49', '', '门巴族', '50', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019051', '50', '', '鄂伦春族', '51', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019052', '51', '', '独龙族', '52', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019053', '52', '', '塔塔尔族', '53', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019054', '53', '', '赫哲族', '54', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019055', '54', '', '珞巴族', '55', '1', '2016-07-29 00:07:42', '0000000019', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000019056', '55', '', '布朗族', '56', '1', '2016-07-29 00:07:42', '0000000019', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000020001', '0', '', '群众', '1', '1', '2016-07-29 00:10:36', '0000000020', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000020002', '1', '', '团员', '2', '1', '2016-07-29 00:11:52', '0000000020', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000020003', '2', '', '预备党员', '3', '1', '2016-07-29 00:14:40', '0000000020', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000020004', '3', '', '党员', '4', '1', '2016-07-29 00:15:15', '0000000020', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000021001', '0', '', '局长', '1', '1', '2016-07-29 00:18:07', '0000000021', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000021002', '1', '', '副局长', '2', '1', '2016-07-29 00:18:33', '0000000021', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000021003', '2', '', '科员', '3', '1', '2016-07-29 00:19:08', '0000000021', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000022001', '1', '', '在职', '1', '1', '2016-07-29 07:44:26', '0000000022', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000022002', '0', '', '离职', '2', '1', '2016-07-29 07:44:55', '0000000022', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000023001', '0', '', '分局', '1', '1', '2016-07-29 08:02:10', '0000000023', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000023002', '1', '', '警种大队', '2', '1', '2016-07-29 08:02:48', '0000000023', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000023003', '2', '', '派出所', '2', '1', '2016-07-29 08:02:48', '0000000023', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000023004', '3', '', '其他', '2', '1', '2016-07-29 08:02:48', '0000000023', NULL);


INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000007001', '0', '', '阴性', '1', '1', '2016-06-26 21:23:01', '0000000007', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000007002', '1', '', '阳性', '2', '1', '2016-06-26 21:23:01', '0000000007', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000008001', '0', '', '现场盘查', '1', '1', '2016-06-26 21:23:01', '0000000008', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000008002', '1', '', '带回单位深度盘查', '2', '1', '2016-06-26 21:23:01', '0000000008', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009001', '0', '10', '涉黄', '1', '1', '2016-06-26 21:23:01', '0000000009', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009002', '1', '20', '涉毒', '2', '1', '2016-06-26 21:23:01', '0000000009', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009003', '2', '30', '涉赌', '3', '1', '2016-06-26 21:23:01', '0000000009', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009004', '3', '40', '涉军', '4', '1', '2016-06-26 21:23:01', '0000000009', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009005', '4', '50', '涉外', '5', '1', '2016-06-26 21:23:01', '0000000009', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009006', '5', '60', '涉稳', '6', '1', '2016-06-26 21:23:01', '0000000009', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009007', '6', '70', '涉恐', '7', '1', '2016-06-26 21:23:01', '0000000009', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000009008', '7', '80', '涉案', '8', '1', '2016-06-26 21:23:01', '0000000009', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000010001', '0', '', '未处理', '1', '1', '2016-06-26 21:23:01', '0000000010', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000010002', '1', '', '已处理', '2', '1', '2016-06-26 21:23:01', '0000000010', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000010003', '2', '', '忽略', '3', '1', '2016-06-26 21:23:01', '0000000010', NULL);





INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000036001', '0000000036001', NULL, '蓝', '1', '1', '2016-11-16 17:09:47', '0000000036', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000036002', '0000000036002', NULL, '黄', '2', '1', '2016-11-16 17:09:47', '0000000036', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000036003', '0000000036003', NULL, '橙', '3', '1', '2016-11-16 17:09:47', '0000000036', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0000000036004', '0000000036004', NULL, '红', '4', '1', '2016-11-16 17:09:47', '0000000036', NULL);

INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('108666b0-df3c-416a-beed-12ffeb29cc7c', 'qjsq', '', '黔江社区', '5', '1', '2016-08-20 20:45:46', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('23cb732a-5536-431b-957c-0266cb9db9ec', 'jzsq', '', '金竹社区', '10', '1', '2016-08-20 20:49:03', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('25f5dfce-8631-40a9-ba73-74d05cc38943', 'hksq', '', '航空社区', '8', '1', '2016-08-20 20:47:55', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('4e831d07-e1cd-4a99-9b25-5af42981c5fe', 'pqsq', '', '平桥社区', '6', '1', '2016-08-20 20:46:13', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('6635a935-79cd-4fb9-a52e-9e95923630fe', 'xmsq', '', '小孟社区', '11', '1', '2016-08-20 20:49:31', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('66d6a7e3-9578-4b9a-8940-8bf924aa66df', 'qpsq', '', '清浦社区', '3', '1', '2016-08-20 20:44:48', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('6bc2dd84-3641-451a-b949-9173864e7f25', 'hhsq', '', '黄河社区', '14', '1', '2016-08-20 20:50:28', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('71246220-dcb6-45d5-a2b1-3e549006fd43', 'htsq', '', '航天社区', '7', '1', '2016-08-20 20:47:24', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('9905e194-6faf-4edc-aced-5a3f7b46b4dc', 'xlsq', '', '兴隆社区', '2', '1', '2016-08-20 20:44:23', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('a159c084-2c0c-4c13-9233-a7a2eb32a70f', 'rhsq', '', '瑞华社区', '4', '1', '2016-08-20 20:45:13', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('aad45ba0-7baf-4f6c-ba19-c06cb5b84103', 'sjsq', '', '三江社区', '13', '1', '2016-08-20 20:50:11', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('ddcc1a17-cf8c-49b0-80b6-92af8db8e1f6', 'hmsq', '', '花孟社区', '12', '1', '2016-08-20 20:49:53', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('e9183276-2ff1-4115-a310-46e368c9cf40', 'jxsq', '', '金欣社区', '9', '1', '2016-08-20 20:48:25', 'dca71f42-ecff-482a-ac83-46b4278672ad', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0007', '301', '', '国家公务员', '7', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0008', '302', '', '公安民警', '8', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0009', '303', '', '企事业管理人员', '9', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('464e7211-bfb9-4e44-ab0d-fd43eea9d578', '304', NULL, '工人', '45', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0011', '305', '', '务农农民', '11', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0012', '306', '', '务工农民', '12', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0013', '307', '', '经商农民', '13', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('74e9902f-405f-4f90-a13c-b4b7cef951ec', '308', NULL, '服务业农民', '44', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0015', '309', '', '其他农民', '15', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0016', '310', '', '在校小学生', '16', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0017', '311', '', '在校初中生', '17', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('71a11f37-4c33-4e34-a884-b73c0ae299c6', '312', NULL, '在校高中生', '57', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0019', '313', '', '在校大学生', '19', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0020', '314', '', '个体工商业者', '20', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0021', '315', '', '离退休人员', '21', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('04cf16f9-9747-4b61-820c-d047eca97dfa', '316', NULL, '无业人员', '56', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0023', '317', '', '现役军人', '23', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('bjlx6083-9017-4256-8ccf-fbe2d4ed3b8e0024', '318', '', '内部保卫人员', '24', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);
INSERT INTO `t_zdx` (`id`, `bm`, `bz`, `mc`, `sx`, `zt`, `sjc`, `zdlx_id`, `sjzdx_id`) VALUES ('0afda987-fc29-41b6-a829-f747fd4fa58d', '990', NULL, '其他', '2', '1', '2015-11-02 10:50:05', 'zy2d4ed3b8e', NULL);

DROP TABLE IF EXISTS `t_ajgl_sq_cj_szpt`;
CREATE TABLE `t_ajgl_sq_cj_szpt` (
  `ajgl_sq_bm` varchar(255) NOT NULL COMMENT '案管系统社区编码',
  `szpt_cj_bm` varchar(255) NOT NULL COMMENT '实战平台村居编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案管系统社区编码与实战平台村居编码对应关系表';

-- ----------------------------
-- Records of t_ajgl_sq_cj_szpt
-- ----------------------------
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114510000003', '52019960000001');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114510000004', '52019960000002');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114510000005', '52019960000003');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114510000006', '52019960000004');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114510000007', '52019960000005');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000002', '52019964000001');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000003', '52019964000002');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000004', '52019964000003');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000005', '52019964000004');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114230000003', '52019965000001');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114230000008', '52019965000002');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114230000002', '52019965000003');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000028', '52019961000001');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000025', '52019961000002');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000024', '52019961000003');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000029', '52019961000004');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000023', '52019961000005');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000046', '52019963000001');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000041', '52019963000002');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000007', '52019964000005');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000006', '52019964000006');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114530000030', '52019962000001');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114530000031', '52019962000002');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114530000034', '52019962000003');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114530000032', '52019962000004');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114530000033', '52019962000005');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000035', '52019963000003');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000036', '52019963000004');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000042', '52019963000005');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000040', '52019963000006');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000044', '52019963000007');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000045', '52019963000008');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000043', '52019963000009');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000049', '52019963000010');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000051', '52019963000012');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000048', '52019963000013');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000047', '52019963000014');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000022', '52019961000006');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000027', '52019961000007');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114520000026', '52019961000008');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000008', '52019964000007');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114550000001', '52019964000008');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114510000002', '52019960000006');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114510000001', '52019960000007');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114230000004', '52019965000004');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114230000005', '52019965000005');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114230000001', '52019965000006');
INSERT INTO `t_ajgl_sq_cj_szpt` VALUES ('520114540000050', '52019963000011');

