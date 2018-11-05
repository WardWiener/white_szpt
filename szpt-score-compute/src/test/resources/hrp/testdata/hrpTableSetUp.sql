
drop table if exists t_gwry_gwryqklx;

DROP TABLE IF EXISTS `t_gwry_gwrylx`;

DROP TABLE IF EXISTS `t_gwry_ryxx`;

CREATE TABLE `t_gwry_ryxx` (
  `id` varchar(36) NOT NULL,
  `jf` int(11) DEFAULT NULL,
  `csrq` datetime DEFAULT NULL,
  `cjsj` datetime DEFAULT NULL,
  `cjr` varchar(255) DEFAULT NULL,
  `cjrdw_id` varchar(255) DEFAULT NULL,
  `sjzt` int(11) DEFAULT NULL,
  `sjly` varchar(255) DEFAULT NULL,
  `mz` varchar(255) DEFAULT NULL,
  `fksj` datetime DEFAULT NULL,
  `czqk` varchar(255) DEFAULT NULL,
  `sfzh` varchar(255) DEFAULT NULL,
  `zkdjsj` datetime DEFAULT NULL,
  `sr` int(11) DEFAULT NULL,
  `jzdz` varchar(255) DEFAULT NULL,
  `xzdxz` varchar(255) DEFAULT NULL,
  `xm` varchar(255) DEFAULT NULL,
  `gj` varchar(255) DEFAULT NULL,
  `wh` varchar(255) DEFAULT NULL,
  `jg` varchar(255) DEFAULT NULL,
  `zklx` varchar(255) DEFAULT NULL,
  `zy` varchar(255) DEFAULT NULL,
  `hjdz` varchar(255) DEFAULT NULL,
  `hjdxz` varchar(255) DEFAULT NULL,
  `hjdpcs` varchar(255) DEFAULT NULL,
  `xb` varchar(255) DEFAULT NULL,
  `rczk` varchar(255) DEFAULT NULL,
  `njjg` varchar(255) DEFAULT NULL,
  `yjlx` varchar(255) DEFAULT NULL,
  `gzdz` varchar(255) DEFAULT NULL,
  `xzyy` varchar(255) default NULL,
  `whcd` varchar(255) default NULL,
  `jyqk` varchar(255) default NULL,
  `hyqk` varchar(255) default NULL,
  `czzt` varchar(255) default NULL,
  `cym`  varchar(255) default NULL, 

  PRIMARY KEY (`id`)
) ;

CREATE TABLE `t_gwry_gwrylx` (
  `id` varchar(36) NOT NULL,
  `rylx` varchar(255) DEFAULT NULL,
  `gxsj` datetime DEFAULT NULL,
  `gwry_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_t_gwry_gwrylx_gwry_id` (`gwry_id`)
);



create table t_gwry_gwryqklx
(
   id    varchar(36) not null,
   qklx  varchar(255) default NULL,
   gwrylx_id  varchar(255) default NULL,
   primary key (id),
   key `index_t_gwry_gwryqklx_gwrylx_id` (gwrylx_id)
          
);

INSERT INTO `t_gwry_ryxx` (`id`, `sfzh`, `zklx`, `jyqk`, `hyqk`, `sr`) VALUES ('testHrpId1', '365404865805147868', '0000000032001', '0000000031001', '0000000030003', '6700') ;
INSERT INTO `t_gwry_gwrylx` (`id`, `rylx`, `gwry_id`) VALUES ('testHrpId1-personType1', '017000002', 'testHrpId1') ;
INSERT INTO `t_gwry_gwrylx` (`id`, `rylx`, `gwry_id`) VALUES ('testHrpId1-personType2', '017001', 'testHrpId1') ;
INSERT INTO `t_gwry_gwrylx` (`id`, `rylx`, `gwry_id`) VALUES ('testHrpId1-personType3', '017000', 'testHrpId1') ;
INSERT INTO `t_gwry_gwryqklx` (`id`, `qklx`, `gwrylx_id`) VALUES ('testHrpId1-criminalRecord1', '017004000', 'testHrpId1-personType1') ;