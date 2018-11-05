/*
综合治安模块脚本
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_zhzats_jwzh_jqsp`;
CREATE TABLE `t_zhzats_jwzh_jqsp` (
  `id` varchar(255) NOT NULL,
  `jqbm` varchar(255) DEFAULT NULL COMMENT '警情编码',
  `wjid` varchar(50) DEFAULT NULL COMMENT '文件id',
  `wjmc` varchar(500) DEFAULT NULL COMMENT '文件名称',
  `wjlx` int DEFAULT NULL COMMENT '文件类型，1-图片 2-视频 3-音频',
  `bfdz` varchar(850) DEFAULT NULL COMMENT '播放地址',
  `yldz` varchar(850) DEFAULT NULL COMMENT '预览地址',
  `lzrjh` varchar(50) DEFAULT NULL COMMENT '录制人警号',
  `lzrxm` varchar(100) DEFAULT NULL COMMENT '录制人姓名',
  `lzrdw` varchar(50) DEFAULT NULL COMMENT '录制人单位编码',
  `kssj` datetime DEFAULT NULL COMMENT '开始时间',
  `jssj` datetime DEFAULT NULL COMMENT '结束时间',
  `lzsc` varchar(255) DEFAULT NULL COMMENT '录制时长',
  `bcsc` varchar(255) DEFAULT NULL COMMENT '保存时长',
  `wjdx` int DEFAULT NULL COMMENT '文件大小,单位为字节',
  `zt` char(1) DEFAULT NULL COMMENT '文件是否有效,0表示有效,1表示无效',
  `ly` varchar(10) DEFAULT NULL COMMENT '文件来源，jwzh指挥系统，zfjly执法记录仪系统',
  `gxsj` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_jqsp_jqbm` (`jqbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='警情视频';


DROP TABLE IF EXISTS `t_zhzats_hlck_pcqk`;
CREATE TABLE `t_zhzats_hlck_pcqk` (
  `id` varchar(255) NOT NULL,
  `pcsj` datetime DEFAULT NULL COMMENT '盘查时间',
  `fkycl` int(11) DEFAULT 0 COMMENT '盘查非客运车辆数量',
  `kycl` int(11) DEFAULT 0 COMMENT '盘查客运车辆数量',
  `ry` int(11) DEFAULT 0 COMMENT '盘查人员数量',
  `dwbm` varchar(255) DEFAULT NULL COMMENT '单位编码',
  `dwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_hlck_pcqk_dwbm` (`dwbm`),
  KEY `index_t_zhzats_hlck_pcqk_pcsj` (`pcsj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盘查情况';


DROP TABLE IF EXISTS `t_zhzats_jwzh_bbjl`;
CREATE TABLE `t_zhzats_jwzh_bbjl` (
  `id` varchar(255) NOT NULL,
  `ty_zhdy_bm` varchar(255) DEFAULT NULL COMMENT '指挥单元编码',
  `ty_zhdy_id` varchar(255) DEFAULT NULL COMMENT '指挥单元id',
  `ty_zhdy_mc` varchar(255) DEFAULT NULL COMMENT '指挥单元名称',
  `ryjh` varchar(255) DEFAULT NULL COMMENT ' 警号',
  `xm` varchar(255) DEFAULT NULL COMMENT '姓名',
  `bbrq` datetime DEFAULT NULL COMMENT '报备日期',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_bbjl_bbrq` (`bbrq`),
  KEY `index_t_zhzats_jwzh_bbjl_ty_zhdy_id` (`ty_zhdy_id`),
  KEY `index_t_zhzats_jwzh_bbjl_ty_zhdy_bm` (`ty_zhdy_bm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报备警力';


DROP TABLE IF EXISTS `t_zhzats_jwzh_cj`;
CREATE TABLE `t_zhzats_jwzh_cj` (
  `id` varchar(255) NOT NULL,
  `ty_zhdy_bm` varchar(255) DEFAULT NULL COMMENT '处警指挥单元编码',
  `ty_zhdy_id` varchar(255) DEFAULT NULL COMMENT ' 处警指挥单元id',
  `cjsj` datetime DEFAULT NULL COMMENT '处警时间',
  `fact_jq_id` varchar(255) DEFAULT NULL COMMENT '警情Id',
  `ty_zhdy_mc` varchar(255) DEFAULT NULL COMMENT '处警指挥单元名称',
  `qssj` datetime DEFAULT NULL COMMENT ' 签收时间',
  `qsr` varchar(255) DEFAULT NULL COMMENT '签收人',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_cj_cjsj` (`cjsj`),
  KEY `index_t_zhzats_jwzh_cj_fact_jq_id` (`fact_jq_id`),
  KEY `index_t_zhzats_jwzh_cj_ty_zhdy_bm` (`ty_zhdy_bm`),
  KEY `index_t_zhzats_jwzh_cj_ty_zhdy_id` (`ty_zhdy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='警务指挥处警';


DROP TABLE IF EXISTS `t_zhzats_jwzh_fk`;
CREATE TABLE `t_zhzats_jwzh_fk` (
  `id` varchar(255) NOT NULL,
  `fknr` varchar(255) DEFAULT NULL COMMENT '反馈内容',
  `fklx` varchar(255) DEFAULT NULL COMMENT '反馈类型',
  `fact_cj_id` varchar(255) DEFAULT NULL COMMENT '处警信息ID',
  `fksj` datetime DEFAULT NULL COMMENT '反馈时间',
  `ty_zhdy_bm` varchar(255) DEFAULT NULL COMMENT '反馈的指挥单元编码',
  `ty_zhdy_id` varchar(255) DEFAULT NULL COMMENT '反馈的指挥单元id',
  `ty_zhdy_mc` varchar(255) DEFAULT NULL COMMENT '反馈的指挥单元名称',
  `fkrxm` varchar(255) DEFAULT NULL COMMENT '反馈人姓名',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_cj_id` (`fact_cj_id`),
  KEY `index_t_zhzats_jwzh_fk_ty_zhdy_bm` (`ty_zhdy_bm`),
  KEY `index_t_zhzats_jwzh_fk_ty_zhdy_id` (`ty_zhdy_id`),
  KEY `index_t_zhzats_jwzh_fk_fksj` (`fksj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='警务指挥处警反馈';


DROP TABLE IF EXISTS `t_zhzats_jwzh_jq`;
CREATE TABLE `t_zhzats_jwzh_jq` (
  `id` varchar(255) NOT NULL,
  `jqdz` varchar(255) DEFAULT NULL COMMENT '警情地址',
  `jjsj` datetime DEFAULT NULL COMMENT '接警时间',
  `bjsj` datetime DEFAULT NULL COMMENT '报警时间',
  `bjr` varchar(255) DEFAULT NULL COMMENT '报警人',
  `jjr` varchar(255) DEFAULT NULL COMMENT '接警人',
  `bjrdh` varchar(255) DEFAULT NULL COMMENT ' 报警人电话',
  `bm` varchar(255) DEFAULT NULL COMMENT '警情编码',
  `ty_cq_bm` varchar(255) DEFAULT NULL COMMENT '村区编码',
  `ty_cq_mc` varchar(255) DEFAULT NULL COMMENT '村区名称',
  `jqly` varchar(255) DEFAULT NULL COMMENT '警情来源',
  `jqgy` varchar(255) DEFAULT NULL COMMENT '警情概要',
  `ty_jqlx_bm` varchar(255) DEFAULT NULL COMMENT '警情类型编码',
  `ty_jqlx_mc` varchar(255) DEFAULT NULL COMMENT '警情类型名称',
  `wd` double DEFAULT NULL COMMENT ' 纬度',
  `jd` double DEFAULT NULL COMMENT '经度',
  `jqmc` varchar(255) DEFAULT NULL COMMENT '警情名称',
  `fssj` datetime DEFAULT NULL COMMENT '发生时间',
  `ty_dw_pcs_bm` varchar(255) DEFAULT NULL COMMENT '派出所编码',
  `ty_dw_pcs_mc` varchar(255) DEFAULT NULL COMMENT '派出所名称',
  `jjcd` varchar(255) DEFAULT NULL COMMENT '紧急程度',
  `gxsjc` datetime DEFAULT NULL COMMENT '更新时间戳',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_jq_ty_dw_pcs_bm` (`ty_dw_pcs_bm`),
  KEY `index_t_zhzats_jwzh_jq_fssj` (`fssj`),
  KEY `index_t_zhzats_jwzh_jq_jjsj` (`jjsj`),
  KEY `index_t_zhzats_jwzh_jq_bm` (`bm`),
  KEY `index_t_zhzats_jwzh_jq_ty_jqlx_bm` (`ty_jqlx_bm`),
  KEY `index_t_zhzats_jwzh_jq_ty_cq_bm` (`ty_cq_bm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='警务指挥警情';


DROP TABLE IF EXISTS `t_zhzats_jwzh_jqfxyp`;
CREATE TABLE `t_zhzats_jwzh_jqfxyp` (
  `id` varchar(255) NOT NULL,
  `zhzats_fact_jq_id` varchar(255) DEFAULT NULL COMMENT '警情id',
  `xyrzbsxt` varchar(255) DEFAULT NULL COMMENT '周边摄像头',
  `shrxm` varchar(50) DEFAULT NULL COMMENT '受害人姓名',
  `shrsfzh` varchar(50) DEFAULT NULL COMMENT '受害人身份证号',
  `sqhfs` varchar(255) DEFAULT NULL COMMENT '受侵害方式',
  `shwptz` varchar(255) DEFAULT NULL COMMENT '受害物品特征',
  `sfjccl` varchar(255) DEFAULT NULL COMMENT '是否驾乘车辆',
  `tcfx` varchar(255) DEFAULT NULL COMMENT '逃穿方向',
  `tcfs` varchar(255) DEFAULT NULL COMMENT '逃穿方式',
  `xyrnl` varchar(255) DEFAULT NULL COMMENT ' 嫌疑人年龄',
  `xyrtx` varchar(255) DEFAULT NULL COMMENT '嫌疑人体型',
  `xyrsswptz` varchar(255) DEFAULT NULL COMMENT '嫌疑人随身物品特征',
  `xyrzagj` varchar(255) DEFAULT NULL COMMENT '嫌疑人作案工具',
  `xyrzafs` varchar(255) DEFAULT NULL COMMENT '嫌疑人作案方式',
  `xyrfx` varchar(255) DEFAULT NULL COMMENT '嫌疑人发型',
  `xyrsg` varchar(255) DEFAULT NULL COMMENT ' 嫌疑人身高',
  `xyrqttz` varchar(255) DEFAULT NULL COMMENT '嫌疑人其他特征',
  `xyrxb` varchar(255) DEFAULT NULL COMMENT '嫌疑人性别',
  `xyrfs` varchar(255) DEFAULT NULL COMMENT '嫌疑人肤色',
  `xyrxztz` varchar(255) DEFAULT NULL COMMENT '嫌疑人衣着特征',
  `sjc` datetime DEFAULT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_jqfxyp_zhzats_fact_jq_id` (`zhzats_fact_jq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='警务指挥警情分析研判';



DROP TABLE IF EXISTS `t_zhzats_sqjw_ldrk`;
CREATE TABLE `t_zhzats_sqjw_ldrk` (
  `id` varchar(255) NOT NULL,
  `sl` int(11) DEFAULT 0 COMMENT '流动人口数量',
  `ldsj` datetime DEFAULT NULL COMMENT '流动时间',
  `dwbm` varchar(255) DEFAULT NULL COMMENT '单位编码',
  `dwmc` varchar(255) DEFAULT NULL COMMENT '单位名称',
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_sqjw_ldrk_dwbm` (`dwbm`),
  KEY `index_t_zhzats_sqjw_ldrk_ldsj` (`ldsj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流动人口';



-- 待删除
DROP TABLE IF EXISTS `t_ty_dw`;
CREATE TABLE IF NOT EXISTS `t_ty_dw` (
  `id` varchar(255) NOT NULL,
  `bm` varchar(255) DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `ty_dw_id` varchar(255) DEFAULT NULL,
  `lx` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_t_ty_dw_ty_dw_id` (`ty_dw_id`),
  KEY `index_t_ty_dw_bm` (`bm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 待删除
DROP TABLE IF EXISTS `t_ty_jwzh_cq`;
CREATE TABLE IF NOT EXISTS `t_ty_jwzh_cq` (
  `id` varchar(255) NOT NULL,
  `bm` varchar(255) DEFAULT NULL,
  `sqbm` varchar(255) DEFAULT NULL,
  `sqmc` varchar(255) DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `pcsbm` varchar(255) DEFAULT NULL,
  `pcsmc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_t_ty_jwzh_cq_sqbm` (`sqbm`),
  KEY `index_t_ty_jwzh_cq_pcsbm` (`pcsbm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `t_ty_jwzh_jqlx`;
CREATE TABLE IF NOT EXISTS `t_ty_jwzh_jqlx` (
  `id` varchar(255) NOT NULL,
  `bm` varchar(255) DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `fid` varchar(255) DEFAULT NULL,
  `sjc` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_t_ty_jwzh_jqlx_fid` (`fid`),
  KEY `index_t_ty_jwzh_jqlx_bm` (`bm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `t_ty_jwzh_zhdy`;
CREATE TABLE IF NOT EXISTS `t_ty_jwzh_zhdy` (
  `id` varchar(255) NOT NULL,
  `bm` varchar(255) DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `mc` varchar(255) DEFAULT NULL,
  `pId` varchar(255) DEFAULT NULL,
  `lx` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_t_ty_jwzh_zhdy_bm` (`bm`),
  KEY `index_t_ty_jwzh_zhdy_pId` (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `t_zhzats_jwzh_gjxx`;
CREATE TABLE IF NOT EXISTS `t_zhzats_jwzh_gjxx` (
  `id` varchar(255) NOT NULL,
  `dwsbbs` varchar(255) DEFAULT NULL,
  `wd` double DEFAULT NULL,
  `jd` double DEFAULT NULL,
  `lx` varchar(255) DEFAULT NULL,
  `zhdybm` varchar(255) DEFAULT NULL,
  `gxsj` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_gjxx_zhdybm` (`zhdybm`),
  KEY `index_t_zhzats_jwzh_gjxx_gxsj` (`gxsj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `t_zhzats_jwzh_wzxx`;
CREATE TABLE IF NOT EXISTS `t_zhzats_jwzh_wzxx` (
  `id` varchar(255) NOT NULL,
  `dwsbbs` varchar(255) DEFAULT NULL,
  `wd` double DEFAULT NULL,
  `jd` double DEFAULT NULL,
  `zhdybm` varchar(255) DEFAULT NULL,
  `zhdymc` varchar(255) DEFAULT NULL,
  `ryjh` varchar(255) DEFAULT NULL,
  `xm` varchar(255) DEFAULT NULL,
  `zt` varchar(255) DEFAULT NULL,
  `lx` varchar(255) DEFAULT NULL,
  `gxsj` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_t_zhzats_jwzh_wzxx_dwsbbs` (`dwsbbs`),
  KEY `index_t_zhzats_jwzh_wzxx_lx` (`lx`),
  KEY `index_t_zhzats_jwzh_wzxx_zhdybm` (`zhdybm`),
  KEY `index_t_zhzats_jwzh_wzxx_gxsj` (`gxsj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;