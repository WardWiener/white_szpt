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

/*Table structure for table `t_gwry_hcrytjxx` */

DROP TABLE IF EXISTS `t_gwry_hcrytjxx`;

CREATE TABLE `t_gwry_hcrytjxx` (
  `id` varchar(255) NOT NULL,
  `hcsj` datetime DEFAULT NULL,
  `hcdwid` varchar(255) DEFAULT NULL,
  `xsqkrs` int(11) DEFAULT NULL,
  `sdry` int(11) DEFAULT NULL,
  `csryrs` int(11) DEFAULT NULL,
  `bsryrs` int(11) DEFAULT NULL,
  `hcrs` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_gwry_hcrytjxx` */

insert  into `t_gwry_hcrytjxx`(`id`,`hcsj`,`hcdwid`,`xsqkrs`,`sdry`,`csryrs`,`bsryrs`,`hcrs`) values ('1','2016-09-13 23:59:11','90b2efdf-3e66-4c8d-92ba-33fe5320bdb5',3,8,2,5,10),('2','2016-09-12 23:59:11','b4f8b72d-1940-43a4-9518-d6606b8b768a',4,6,3,6,11),('3','2016-09-11 23:59:11','e1048cb4-01fb-4dd6-a668-048cdf3a4567',5,7,4,4,12),('4','2016-09-10 23:59:11','e5be6a62-4cbe-468e-9cf4-205b3abaea23',6,5,5,3,11),('5','2016-09-09 23:59:11','069c4e0f-a006-43d6-a9d7-36f488514c7d',7,3,7,2,14),('6','2016-09-14 23:59:11','7b8cac4c-2e89-499c-b83e-63defc1da3dd',5,7,4,6,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
