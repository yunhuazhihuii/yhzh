/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.27 : Database - model
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`model` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `model`;

/*Table structure for table `s0000001` */

DROP TABLE IF EXISTS `s0000001`;

CREATE TABLE `s0000001` (
  `F1` varchar(7) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F4` varchar(8) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`F1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s0000001` */

insert  into `s0000001`(`F1`,`F2`,`F3`,`F4`) values ('1000000','模型服务','1',''),('1100000','服务单元','1',''),('1110000','实时对象','1',''),('1111000','DI','1',''),('1111100','数字输入点','1',''),('1111110','报警定义','1',''),('1111120','事件编程','1',''),('1112000','DO','1',''),('1112100','数字输出点','1',''),('1112110','报警定义','1',''),('1112120','事件编程','1',''),('1113000','AI','1',''),('1113100','模拟输入点','1',''),('1113110','报警定义','1',''),('1113120','事件编程','1',''),('1114000','AO','1',''),('1114100','模拟输出点','1',''),('1114110','报警定义','1',''),('1114120','事件编程','1',''),('1115000','DV','1',''),('1115100','数字虚拟点','1',''),('1115110','报警定义','1',''),('1115120','事件编程','1',''),('1116000','AV','1',''),('1116100','模拟虚拟点','1',''),('1116110','报警定义','1',''),('1116120','事件编程','1',''),('1117000','SV','1',''),('1117100','字符虚拟点','1',''),('1120000','数据对象','1',''),('1121000','数据库表类','1',''),('1121100','数据表','1',''),('1122000','数据库视图类','1',''),('1122100','视图','1',''),('1123000','Json格式类','1',''),('1123100','Json格式','1',''),('1130000','日程表','1',''),('1131000','日程表','1',''),('1140000','Restful API','1',''),('1141000','Restful','1',''),('1150000','Websocket Unit','1',''),('1151000','Websocket','1',''),('1160000','单元内小程序','1',''),('1161000','小程序','1',''),('1200000','单元外小程序','0',''),('1210000','小程序','1',''),('1300000','数据库设置','0',''),('1310000','数据库设置','1',''),('1400000','集群设置','0',''),('1410000','集群设置','1',''),('1500000','模板','0',''),('1510000','模板','1','');

/*Table structure for table `s0000002` */

DROP TABLE IF EXISTS `s0000002`;

CREATE TABLE `s0000002` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(7) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F6` varchar(18) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`F1`,`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s0000002` */

insert  into `s0000002`(`F1`,`F2`,`F3`,`F4`,`F5`,`F6`) values ('100000000001','100000000001','模型服务','1000000','1','000000000000'),('100000000001','110000000001','服务单元1','1100000','1','100000000001'),('100000000001','110000000002','服务单元2','1100000','1','100000000001'),('100000000001','111000001001','实时对象1','1110000','1','110000000001'),('100000000001','111000001002','实时对象2','1110000','1','110000000001'),('100000000001','111000002001','实时对象1','1110000','1','110000000002'),('100000000001','111000002002','实时对象2','1110000','1','110000000002'),('100000000001','111100000001','DI','1111000','2','111000001001'),('100000000001','111100000002','DI','1111000','2','111000002001'),('100000000001','111200000001','DO','1112000','2','111000001001'),('100000000001','111200000002','DO','1112000','2','111000002001'),('100000000001','111300000001','AI','1113000','2','111000001001'),('100000000001','111300000002','AI','1113000','2','111000002001'),('100000000001','111400000001','AO','1114000','2','111000001001'),('100000000001','111400000002','AO','1114000','2','111000002001'),('100000000001','111500000001','DV','1115000','2','111000001001'),('100000000001','111500000002','DV','1115000','2','111000002001'),('100000000001','111600000001','AV','1116000','2','111000001001'),('100000000001','111600000002','AV','1116000','2','111000002001'),('100000000001','111700000001','SV','1117000','2','111000001001'),('100000000001','111700000002','SV','1117000','2','111000002001'),('100000000001','120000000001','小程序','1200000','2','100000000001'),('100000000001','130000000001','数据库设置','1300000','2','100000000001'),('100000000001','140000000001','集群设置','1400000','2','100000000001'),('100000000001','150000000001','模板','1500000','2','100000000001');

/*Table structure for table `s0000003` */

DROP TABLE IF EXISTS `s0000003`;

CREATE TABLE `s0000003` (
  `F1` varchar(2) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(6) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(30) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`F1`,`F3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s0000003` */

insert  into `s0000003`(`F1`,`F2`,`F3`,`F4`,`F5`) values ('BM','编码序列号管理','01','100000000001','服务编码'),('BM','编码序列号管理','02','110000000001','服务单元编码'),('BM','编码序列号管理','03','111000000000001','实时对象编码'),('BM','编码序列号管理','04','120000000001','单元外小程序编码'),('BM','编码序列号管理','05','130000000001','数据库设置编码'),('BM','编码序列号管理','06','140000000001','集群设置编码'),('BM','编码序列号管理','07','150000000001','模板编码'),('BM','编码序列号管理','08','112000000000001','数据对象编码'),('BM','编码序列号管理','09','113000000001','日程表编码'),('BM','编码序列号管理','10','114000000001','Restful API编码'),('BM','编码序列号管理','11','115000000001','Websocket Unit编码'),('BM','编码序列号管理','12','116000000001','单元内小程序编码'),('BM','编码序列号管理','13','111100000000001','DI编码'),('BM','编码序列号管理','14','111200000000001','DO编码'),('BM','编码序列号管理','15','111300000000001','AI编码'),('BM','编码序列号管理','16','111400000000001','AO编码'),('BM','编码序列号管理','17','111500000000001','DV编码'),('BM','编码序列号管理','18','111600000000001','AV编码'),('BM','编码序列号管理','19','111700000000001','SV编码');

/*Table structure for table `s0000004` */

DROP TABLE IF EXISTS `s0000004`;

CREATE TABLE `s0000004` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(3) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F1`,`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s0000004` */

/*Table structure for table `s0000005` */

DROP TABLE IF EXISTS `s0000005`;

CREATE TABLE `s0000005` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(1) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(3) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F6` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`,`F3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s0000005` */

/*Table structure for table `s1000000` */

DROP TABLE IF EXISTS `s1000000`;

CREATE TABLE `s1000000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F4` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(39) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(4) CHARACTER SET utf8 DEFAULT NULL,
  `F8` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `F9` tinyblob,
  `F10` datetime NOT NULL,
  `F11` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F1`),
  UNIQUE KEY `F2` (`F2`),
  UNIQUE KEY `IndexF1F4` (`F1`,`F4`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1000000` */

/*Table structure for table `s1100000` */

DROP TABLE IF EXISTS `s1100000`;

CREATE TABLE `s1100000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F5` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F8` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `F9` datetime NOT NULL,
  `F10` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1100000` */

/*Table structure for table `s1110000` */

DROP TABLE IF EXISTS `s1110000`;

CREATE TABLE `s1110000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(15) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F6` datetime NOT NULL,
  `F7` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F3`),
  UNIQUE KEY `IndexF1F4` (`F1`,`F4`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1110000` */

/*Table structure for table `s1111100` */

DROP TABLE IF EXISTS `s1111100`;

CREATE TABLE `s1111100` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(15) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(1) CHARACTER SET utf8 NOT NULL,
  `F7` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `F8` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `F9` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `F10` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F11` datetime NOT NULL,
  `F12` datetime NOT NULL,
  `F13` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F3`),
  UNIQUE KEY `IndexF2F4` (`F2`,`F4`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1111100` */

/*Table structure for table `s1111110` */

DROP TABLE IF EXISTS `s1111110`;

CREATE TABLE `s1111110` (
  `F1` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(1) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(40) CHARACTER SET utf8 NOT NULL,
  `F8` text CHARACTER SET utf8 NOT NULL,
  `F9` datetime NOT NULL,
  `F10` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1111110` */

/*Table structure for table `s1111120` */

DROP TABLE IF EXISTS `s1111120`;

CREATE TABLE `s1111120` (
  `F1` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F3` text CHARACTER SET utf8 NOT NULL,
  `F4` datetime NOT NULL,
  `F5` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1111120` */

/*Table structure for table `s1120000` */

DROP TABLE IF EXISTS `s1120000`;

CREATE TABLE `s1120000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(15) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F5` datetime NOT NULL,
  `F6` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1120000` */

/*Table structure for table `s1121100` */

DROP TABLE IF EXISTS `s1121100`;

CREATE TABLE `s1121100` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(20) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(50) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(8) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(60) CHARACTER SET utf8 DEFAULT NULL,
  `F8` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `F9` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F10` datetime NOT NULL,
  PRIMARY KEY (`F3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1121100` */

/*Table structure for table `s1121110` */

DROP TABLE IF EXISTS `s1121110`;

CREATE TABLE `s1121110` (
  `F1` varchar(20) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(20) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(3) CHARACTER SET utf8 DEFAULT NULL,
  `F5` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1121110` */

/*Table structure for table `s1121200` */

DROP TABLE IF EXISTS `s1121200`;

CREATE TABLE `s1121200` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(120) CHARACTER SET utf8 NOT NULL,
  `F5` text CHARACTER SET utf8 NOT NULL,
  `F6` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F8` datetime NOT NULL,
  PRIMARY KEY (`F3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1121200` */

/*Table structure for table `s1121300` */

DROP TABLE IF EXISTS `s1121300`;

CREATE TABLE `s1121300` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(2) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F3` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`F1`,`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1121300` */

/*Table structure for table `s1130000` */

DROP TABLE IF EXISTS `s1130000`;

CREATE TABLE `s1130000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `F3` varchar(15) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `F7` datetime NOT NULL,
  `F8` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F3`),
  UNIQUE KEY `F4` (`F4`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1130000` */

/*Table structure for table `s1131000` */

DROP TABLE IF EXISTS `s1131000`;

CREATE TABLE `s1131000` (
  `F1` varchar(15) CHARACTER SET utf8 NOT NULL,
  `F2` int(11) NOT NULL,
  `F3` varchar(1) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(8) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(8) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`F1`,`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1131000` */

/*Table structure for table `s1140000` */

DROP TABLE IF EXISTS `s1140000`;

CREATE TABLE `s1140000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(120) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(50) CHARACTER SET utf8 NOT NULL,
  `F6` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `F8` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `F9` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `F10` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1140000` */

/*Table structure for table `s1150000` */

DROP TABLE IF EXISTS `s1150000`;

CREATE TABLE `s1150000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F5` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1150000` */

/*Table structure for table `s1160000` */

DROP TABLE IF EXISTS `s1160000`;

CREATE TABLE `s1160000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(15) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F5` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F6` text CHARACTER SET utf8,
  `F7` varchar(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`F3`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1160000` */

/*Table structure for table `s1200000` */

DROP TABLE IF EXISTS `s1200000`;

CREATE TABLE `s1200000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `F5` text CHARACTER SET utf8,
  `F6` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1200000` */

/*Table structure for table `s1300000` */

DROP TABLE IF EXISTS `s1300000`;

CREATE TABLE `s1300000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(2) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F5` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F6` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F8` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F9` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F10` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F11` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F12` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1300000` */

/*Table structure for table `s1400000` */

DROP TABLE IF EXISTS `s1400000`;

CREATE TABLE `s1400000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F4` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F1`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1400000` */

/*Table structure for table `s1410000` */

DROP TABLE IF EXISTS `s1410000`;

CREATE TABLE `s1410000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  `F5` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '0',
  PRIMARY KEY (`F1`,`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1410000` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
