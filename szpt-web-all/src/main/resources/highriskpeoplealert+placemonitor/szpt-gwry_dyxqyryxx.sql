/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.26 : Database - szpt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`szpt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `szpt`;

/*Table structure for table `t_gwry_dyxqy` */

DROP TABLE IF EXISTS `t_gwry_dyxqy`;

CREATE TABLE `t_gwry_dyxqy` (
  `id` varchar(255) NOT NULL,
  `cjzalx` varchar(255) DEFAULT NULL,
  `gxsj` datetime DEFAULT NULL,
  `qy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_gwry_dyxqy` */

/*Table structure for table `t_gwry_dyxqyryxx` */

DROP TABLE IF EXISTS `t_gwry_dyxqyryxx`;

CREATE TABLE `t_gwry_dyxqyryxx` (
  `id` varchar(255) NOT NULL,
  `xzd` varchar(255) DEFAULT NULL,
  `cksj` datetime DEFAULT NULL,
  `cjzalx` varchar(255) DEFAULT NULL,
  `dyxdq` varchar(255) DEFAULT NULL,
  `sfzh` varchar(255) DEFAULT NULL,
  `sfck` int(11) DEFAULT NULL,
  `zhjrsj` datetime DEFAULT NULL,
  `hjd` varchar(255) DEFAULT NULL,
  `xm` varchar(255) DEFAULT NULL,
  `sjh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_gwry_dyxqyryxx` */

insert  into `t_gwry_dyxqyryxx`(`id`,`xzd`,`cksj`,`cjzalx`,`dyxdq`,`sfzh`,`sfck`,`zhjrsj`,`hjd`,`xm`,`sjh`) values ('1','贵州省贵阳市小河区',NULL,'一般盗窃','贵州省贵阳市小河区','520102197006265029',0,'2016-09-13 09:01:02','贵州省贵阳市小河区','孙黔飞',NULL),('2','贵州省贵阳市小河区',NULL,'异物塞车抢夺','新疆','520102197006265030',0,'2016-09-11 09:01:02','新疆','刘帥希',NULL),('3','贵州省贵阳市小河区',NULL,'盗剪、抢夺小孩颈上金项链','江西瑞金','520102197006265031',1,'2016-09-10 09:01:02','西藏','宋丽丽',NULL),('4','贵州省贵阳市小河区',NULL,'撬防盗门入室盗窃案件','江西赣州宜春','520102197006265032',0,'2016-09-13 09:01:02','江西赣州宜春','宋建池',NULL),('5','贵州省贵阳市小河区',NULL,'盗剪、抢夺小孩颈上金项链','江西瑞金','520102197006265033',1,'2016-09-12 09:01:02','西藏','刘骏熙',NULL),('6','贵州省贵阳市小河区',NULL,'异物塞车抢夺','新疆','520102197006265034',0,'2016-09-13 09:01:02','新疆','李天一',NULL),('7','贵州省贵阳市小河区',NULL,'异物塞车抢夺','新疆','520102197006265035',0,'2016-09-10 09:01:02','新疆','李铁刚',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
