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

/*Table structure for table `t_gwry_gwrylsxx` */

DROP TABLE IF EXISTS `t_gwry_gwrylsxx`;

CREATE TABLE `t_gwry_gwrylsxx` (
  `id` varchar(255) NOT NULL,
  `cznr` varchar(255) DEFAULT NULL,
  `czfs` varchar(255) DEFAULT NULL,
  `czsj` datetime DEFAULT NULL,
  `czlx` varchar(255) DEFAULT NULL,
  `czdxId` varchar(255) DEFAULT NULL,
  `czdxlx` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_gwry_gwrylsxx` */

insert  into `t_gwry_gwrylsxx`(`id`,`cznr`,`czfs`,`czsj`,`czlx`,`czdxId`,`czdxlx`) values ('240270394453344','0','新增','2016-09-14 16:33:03','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453346','1','新增','2016-09-13 17:59:30','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453347','1','新增','2016-09-14 17:59:34','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453348','2','新增','2016-09-14 17:59:45','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453349','2','删除','2016-09-14 18:00:26','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453350','2','删除','2016-09-13 18:00:30','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453351','3','删除','2016-09-14 18:00:40','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453352','3','删除','2016-09-14 18:00:42','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453353','3','删除','2016-09-14 18:00:44','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453354','4','删除','2016-09-14 18:00:53','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453355','4','新增','2016-09-12 18:01:01','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453356','4','新增','2016-09-14 18:01:03','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453357','5','新增','2016-09-14 18:01:11','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453358','5','新增','2016-09-14 18:01:23','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453359','5','删除','2016-09-10 18:01:25','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453360','5','删除','2016-09-14 18:01:56','高危人员调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453361','000','新增','2016-09-14 18:03:18','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453362','000','新增','2016-09-14 18:03:33','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453363','001','新增','2016-09-10 18:04:40','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453364','001','删除','2016-09-14 18:04:48','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453365','001','删除','2016-09-14 18:04:51','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453366','001','删除','2016-09-10 18:04:52','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453367','002','删除','2016-09-14 18:05:05','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453368','002','删除','2016-09-14 18:05:07','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453369','003','删除','2016-09-14 18:05:19','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453370','003','删除','2016-09-14 18:05:23','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453371','003','新增','2016-09-14 18:05:29','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453372','003','新增','2016-09-10 18:05:31','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453373','004','新增','2016-09-14 18:06:18','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453374','004','新增','2016-09-14 18:06:21','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453375','004','新增','2016-09-14 18:06:22','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453376','004','新增','2016-09-14 18:06:24','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453377','004','新增','2016-09-14 18:06:27','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453378','005','新增','2016-09-14 18:06:42','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453379','005','新增','2016-09-14 18:06:46','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453380','005','新增','2016-09-14 18:06:51','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453381','005','删除','2016-09-14 18:07:11','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453382','005','删除','2016-09-14 18:07:14','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453383','005','删除','2016-09-14 18:07:17','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL),('240270394453384','005','删除','2016-09-14 18:07:20','人员类型调整','00013b7f-341c-4d5b-9d57-3469e0d2f92f',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
