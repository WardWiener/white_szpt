/*
Navicat MySQL Data Transfer

Source Server         : jwzh
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : source_th

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2017-03-24 16:39:08
*/

SET FOREIGN_KEY_CHECKS=0;
DROP Database IF EXISTS `source_th`;
CREATE Database `source_th`;
use source_th;
-- ----------------------------
-- Table structure for t_bz_ly_lvguan
-- ----------------------------
DROP TABLE IF EXISTS `t_bz_ly_lvguan`;
CREATE TABLE `t_bz_ly_lvguan` (
  `SYSTEMID` varchar(50) NOT NULL COMMENT '系统主键',
  `BAOANBUDH` varchar(30) DEFAULT NULL COMMENT '保安部电话(de00216)(c..18)',
  `BOHAOBZ` int(11) DEFAULT NULL COMMENT '拨号标识',
  `BOHAODHLB` varchar(300) DEFAULT NULL COMMENT '拨号id列表',
  `BOHAOLEIXING` varchar(3) DEFAULT NULL COMMENT '拨号类型',
  `BOHAOSJLB` varchar(383) DEFAULT NULL COMMENT '拨号时间列表',
  `CHUANGWEISHU` int(11) DEFAULT NULL COMMENT '床位数',
  `CONGYERENSHU` int(11) DEFAULT NULL COMMENT '从业人数(de00829)',
  `CRJGXDW` varchar(120) DEFAULT NULL,
  `CRJGXDWBM` varchar(60) DEFAULT NULL,
  `DANWEILX` varchar(3) DEFAULT NULL COMMENT '单位类型,海门需求',
  `DCBS` varchar(2) DEFAULT NULL COMMENT '导出标识',
  `DCSJ` datetime DEFAULT NULL COMMENT '导出时间(de00102)(t6（hhmmss）)',
  `DENGJIRIQI` datetime DEFAULT NULL COMMENT '登记日期(de00524)(d14(yyyymmddhhmmss))',
  `DUKAQI` varchar(2) DEFAULT NULL COMMENT '读卡器,海门需求',
  `FANGJIANSHU` int(11) DEFAULT NULL COMMENT '房间数(de00532)(n..4)',
  `FARENMC` varchar(45) DEFAULT NULL COMMENT '法人',
  `FAZHENGJIGUAN` varchar(150) DEFAULT NULL COMMENT '发证机关',
  `FAZHENGSHIJIAN` datetime DEFAULT NULL COMMENT '发证时间(de00283)',
  `FILECREATETIME` datetime DEFAULT NULL COMMENT '文件创建时间(de00102)(t6（hhmmss）)',
  `FIRSTINDBTIME` datetime DEFAULT NULL,
  `GXDWBM` varchar(18) DEFAULT NULL COMMENT '管辖单位编码',
  `GXDWDH` varchar(30) DEFAULT NULL COMMENT '管辖单位电话(de00216)(c..18)',
  `GXDWLXR` varchar(45) DEFAULT NULL COMMENT '管线单位联系人',
  `GXDWMC` varchar(105) DEFAULT NULL COMMENT '管辖单位名称',
  `HANGYELX` varchar(2) DEFAULT NULL COMMENT '行业类型，默认a，代表旅业',
  `HEFAREN` varchar(45) DEFAULT NULL COMMENT '核发人(de00002)(c..50)',
  `JIANZHUZONGMJ` decimal(10,2) DEFAULT NULL COMMENT '建筑总面积(de00534)(n..8,2)',
  `JIFANGSB` varchar(15) DEFAULT NULL COMMENT '技防设备,海门需求',
  `JINGPEIXUNRS` int(11) DEFAULT NULL COMMENT '经培训人数',
  `JINGYINGXM` varchar(15) DEFAULT NULL COMMENT '经营项目,海门需求(de00640)(c..200)',
  `JIQIJIAMIBH` text COMMENT '机器加密编号',
  `KAITONGBZ` int(11) DEFAULT NULL COMMENT '开通标识(0为未开通,1为已开通)',
  `KAITONGSJ` datetime DEFAULT NULL COMMENT '开通时间(de00102)(t6（hhmmss）)',
  `KYSJ` datetime DEFAULT NULL COMMENT '开业时间',
  `LASTEDITTIME` datetime DEFAULT NULL COMMENT '最后修改时间(de00629)(d14(yyyymmddhhmmss))',
  `LGFL` varchar(1) DEFAULT NULL COMMENT '旅馆分类（贵州个性需求2012-1-12）',
  `LIANXIDH` varchar(60) DEFAULT NULL COMMENT '联系电话(de00216)(c..18)',
  `LINGZHENGREN` varchar(45) DEFAULT NULL COMMENT '领证人(de00002)(c..50)',
  `LPCS` varchar(20) DEFAULT NULL,
  `NEIWANGJSSJ` datetime DEFAULT NULL COMMENT '内网接收时间(de00102)(t6（hhmmss）)',
  `NEIWANGTBBS` varchar(2) DEFAULT NULL COMMENT '公安内网同步标识，从地市到省厅或从省厅到地市',
  `QIANTAISBPP` varchar(15) DEFAULT NULL COMMENT '前台设备品牌，bs代码',
  `QITAISBLX` varchar(15) DEFAULT NULL COMMENT '前台设备类型，bs代码',
  `QIYEBIANMA` varchar(15) DEFAULT NULL COMMENT '企业编码',
  `QIYEDENGJI` varchar(2) DEFAULT NULL COMMENT '旅馆等级代码',
  `YXTBH` decimal(30,0) DEFAULT NULL COMMENT '企业id(de00627)(d14(yyyymmddhhmmss))',
  `QIYEMC` varchar(105) DEFAULT NULL COMMENT '企业名称(de00065)(c..100)',
  `QIYEXINGJI` varchar(2) DEFAULT NULL COMMENT '旅馆星级代码',
  `QIYEXINGZHI` varchar(45) DEFAULT NULL COMMENT '企业性质',
  `QIYEXINGZHI_DM` varchar(3) DEFAULT NULL COMMENT '企业性质代码',
  `RUZHULV` int(11) DEFAULT NULL COMMENT '入住率',
  `SFSW` varchar(1) DEFAULT NULL COMMENT '是否上网(de00742)',
  `SFYZ` varchar(3) DEFAULT NULL,
  `SHOUJI` varchar(30) DEFAULT NULL COMMENT '手机(de00215)(c..18)',
  `SPELL` varchar(120) DEFAULT NULL COMMENT '旅馆名称的汉字首字母，存大写后的',
  `WAIWANGTBBZ` varchar(2) DEFAULT NULL COMMENT '从公安内网向外网同步旅馆信息',
  `WANGLUOZK` varchar(3) DEFAULT NULL COMMENT '网络状况',
  `XIANGXIDIZHI` varchar(120) DEFAULT NULL COMMENT '详细地址(de00076)(c..100)',
  `XUKEZHENGHAO` varchar(75) DEFAULT NULL COMMENT '特行许可证号',
  `YEWULB` varchar(15) DEFAULT NULL COMMENT '业务类别',
  `YEWULBBM` varchar(6) DEFAULT NULL COMMENT '业务类别编码',
  `YOUBIAN` varchar(39) DEFAULT NULL COMMENT '邮编(de00077)(c6)',
  `ZERENMINJING` varchar(45) DEFAULT NULL COMMENT '责任民警',
  `ZERENMINJINGSFZH` varchar(27) DEFAULT NULL COMMENT '责任民警身份证号',
  `ZHIANZRR` varchar(45) DEFAULT NULL COMMENT '治安责任人(de00002)(c..50)',
  `ZHIBAORENSHU` int(11) DEFAULT NULL COMMENT '治保人数(de00829)',
  `ZHUANGJIFS` varchar(2) DEFAULT NULL COMMENT '装机方式,海门需求',
  `ZHUANGTAI` varchar(2) DEFAULT NULL COMMENT '状态代码',
  `ZHUANGTAIGBRQ` datetime DEFAULT NULL COMMENT '状态改变日期(de00101)(d8（yyyymmdd）)',
  `ZHUXIAOBZ` varchar(2) DEFAULT NULL COMMENT '注销标志（0未注销，1注销。默认为0）',
  `ZONGJINGLI` varchar(45) DEFAULT NULL COMMENT '总经理(de00002)(c..50)',
  `ZUIHOUSCSJ` datetime DEFAULT NULL COMMENT '最后上传时间(de00102)(t6（hhmmss）)',
  `BZK_RKSJ` datetime DEFAULT NULL COMMENT '资源库入库时间',
  `BZK_GXSJ` datetime DEFAULT NULL COMMENT '资源库更新时间',
  `ZYK_SSXTMC` varchar(100) DEFAULT NULL COMMENT '所属系统名称',
  `BZK_SCBZ` varchar(1) DEFAULT NULL COMMENT '标准库删除标识',
  `BZK_ZZJG` varchar(18) DEFAULT NULL,
  `GXDWBM_GABZ` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`SYSTEMID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_gnlk
-- ----------------------------
DROP TABLE IF EXISTS `t_gnlk`;
CREATE TABLE `t_gnlk` (
  `GNLKID` int(11) NOT NULL COMMENT '旅客ID',
  `QIYEBIANMA` varchar(15) NOT NULL COMMENT '企业编码',
  `LVKEBIANMA` varchar(33) DEFAULT NULL COMMENT '旅客编码',
  `XINGMING` varchar(45) DEFAULT NULL COMMENT '姓名',
  `FAYIN` varchar(75) DEFAULT NULL COMMENT '发音',
  `XINGBIE` varchar(2) DEFAULT NULL COMMENT '性别代码',
  `MINZU` varchar(3) DEFAULT NULL COMMENT '民族代码',
  `CHUSHENGRIQI` datetime DEFAULT NULL COMMENT '出生日期',
  `ZHENGJIANLX` varchar(3) DEFAULT NULL COMMENT '证件类型',
  `ZHENGJIANHM` varchar(30) DEFAULT NULL COMMENT '证件号码',
  `SHENGSHIXIAN` varchar(9) DEFAULT NULL COMMENT '省市县代码',
  `XIANGXIDIZHI` varchar(150) DEFAULT NULL COMMENT '详细地址',
  `RUZHUSJ` datetime DEFAULT NULL COMMENT '入住时间',
  `FANGJIANHAO` varchar(15) DEFAULT NULL COMMENT '房间号',
  `TUIFANGSJ` datetime DEFAULT NULL COMMENT '退房时间',
  `QIYEMC` varchar(105) DEFAULT NULL COMMENT '企业名称',
  `XINYONGKALX` varchar(15) DEFAULT NULL COMMENT '信用卡类型代码',
  `XINYONGKAHAO` varchar(150) DEFAULT NULL COMMENT '信用卡号',
  `GXDWBM` varchar(18) DEFAULT NULL COMMENT '管辖单位编码',
  `GXDWMC` varchar(105) DEFAULT NULL COMMENT '管辖单位名称',
  `TUOJISHEBEIBZ` varchar(2) DEFAULT NULL COMMENT '脱机设备标志',
  `QIANTAIJLZJ` varchar(45) DEFAULT NULL COMMENT '前台记录主键',
  `QIANTAICPSJ` datetime DEFAULT NULL COMMENT '前台存盘时间',
  `QIANTAIDBSJ` datetime DEFAULT NULL COMMENT '前台打包时间',
  `QIANTAISCSJ` datetime DEFAULT NULL COMMENT '前台上传时间',
  `WAIWANGJSSJ` datetime DEFAULT NULL COMMENT '外网接收时间',
  `NEIWANGJSSJ` datetime DEFAULT NULL COMMENT '内网接收时间',
  `CHUANSONGBZ` varchar(2) DEFAULT NULL COMMENT '传送标志',
  `HANGYELX` varchar(2) DEFAULT NULL COMMENT '行业类型代码',
  `YEWULB` varchar(15) DEFAULT NULL COMMENT '业务类别',
  `SHIFOUHC` varchar(2) DEFAULT NULL COMMENT '是否核查',
  `BIDUIFLAG` varchar(2) DEFAULT NULL COMMENT '报警比对标志',
  `GZDX_BIDUIFLAG` varchar(2) DEFAULT NULL COMMENT '关注对象比对标志',
  `LASTEDITTIME` datetime DEFAULT NULL COMMENT '最后编辑时间',
  `YEWULBBM` varchar(6) DEFAULT NULL COMMENT '业务类别编码',
  `HECHAFLAG` varchar(3) DEFAULT NULL,
  `HECHATIME` datetime DEFAULT NULL,
  `ZPID` int(11) DEFAULT NULL,
  `MZ_ZH` varchar(90) DEFAULT NULL,
  `SHENGSHIXIAN_ZH` varchar(90) DEFAULT NULL,
  `ZJLX_ZH` varchar(90) DEFAULT NULL,
  `FILECREATETIME` datetime DEFAULT NULL,
  `XB_ZH` varchar(9) DEFAULT NULL,
  `FIRSTINDBTIME` datetime DEFAULT NULL,
  `HCK_GXSJ` datetime DEFAULT NULL COMMENT '更新时间',
  `HCK_RKSJ` datetime DEFAULT NULL COMMENT '入库时间',
  `SYSTEMID` varchar(100) DEFAULT NULL COMMENT '主键',
  PRIMARY KEY (`GNLKID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
