/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.27 : Database - yhzh
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yhzh` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `yhzh`;

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

insert  into `s0000001`(`F1`,`F2`,`F3`,`F4`) values ('1000000','模型服务','1',''),('1100000','服务单元','1',''),('1110000','实时对象','1',''),('1111000','DI','0',''),('1111100','数字输入点','1',''),('1111110','报警定义','1',''),('1111120','事件编程','1',''),('1112000','DO','0',''),('1112100','数字输出点','1',''),('1112110','报警定义','1',''),('1112120','事件编程','1',''),('1113000','AI','0',''),('1113100','模拟输入点','1',''),('1113110','报警定义','1',''),('1113120','事件编程','1',''),('1114000','AO','0',''),('1114100','模拟输出点','1',''),('1114110','报警定义','1',''),('1114120','事件编程','1',''),('1115000','DV','0',''),('1115100','数字虚拟点','1',''),('1115110','报警定义','1',''),('1115120','事件编程','1',''),('1116000','AV','0',''),('1116100','模拟虚拟点','1',''),('1116110','报警定义','1',''),('1116120','事件编程','1',''),('1117000','SV','0',''),('1117100','字符虚拟点','1',''),('1120000','数据对象','1',''),('1121000','数据库表类','1',''),('1121100','数据表','1',''),('1122000','数据库视图类','1',''),('1122100','视图','1',''),('1123000','Json格式类','1',''),('1123100','Json格式','1',''),('1130000','日程表','1',''),('1131000','日程表','1',''),('1140000','Restful API','1',''),('1141000','Restful','1',''),('1150000','Websocket Unit','1',''),('1151000','Websocket','1',''),('1160000','单元内小程序','1',''),('1161000','小程序','1',''),('1200000','单元外小程序','0',''),('1210000','小程序','1',''),('1300000','数据库设置','0',''),('1310000','数据库设置','1',''),('1400000','集群设置','0',''),('1410000','集群设置','1',''),('1500000','模板','0',''),('1510000','模板','1','');

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

insert  into `s0000002`(`F1`,`F2`,`F3`,`F4`,`F5`,`F6`) values ('100000000001','100000000001','100000000001服务','1000000','1','000000000000'),('100000000001','110000000001','服务单元1','1100000','1','100000000001'),('100000000001','110000000002','服务单元2','1100000','1','100000000001'),('100000000001','111000001001','实时对象1','1110000','1','110000000001'),('100000000001','111100000001','DI','1111000','2','111000001001'),('100000000001','111200000001','DO','1112000','2','111000001001'),('100000000001','111300000001','AI','1113000','2','111000001001'),('100000000001','111400000001','AO','1114000','2','111000001001'),('100000000001','111500000001','DV','1115000','2','111000001001'),('100000000001','111600000001','AV','1116000','2','111000001001'),('100000000001','111700000001','SV','1117000','2','111000001001'),('100000000001','120000000001','单元外小程序','1200000','2','100000000001'),('100000000001','130000000001','数据库设置','1300000','2','100000000001'),('100000000001','140000000001','集群设置','1400000','2','100000000001'),('100000000001','150000000001','模板','1500000','2','100000000001'),('100000000002','100000000002','100000000002服务','1000000','1','000000000000'),('100000000002','110000000002','服务单元','1100000','1','100000000002'),('100000000002','120000000002','单元外小程序','1200000','1','100000000002'),('100000000002','130000000002','数据库设置','1300000','1','100000000002'),('100000000002','140000000002','集群设置','1400000','1','100000000002'),('100000000002','150000000002','模板','1500000','1','100000000002'),('100000000003','100000000003','100000000003服务','1000000','1','000000000000'),('100000000003','110000000003','110000000003服务单元对象修改','1100000','1','100000000003'),('100000000003','120000000003','单元外小程序','1200000','1','100000000003'),('100000000003','130000000003','数据库设置','1300000','1','100000000003'),('100000000003','140000000003','集群设置','1400000','1','100000000003'),('100000000003','150000000003','模板','1500000','1','100000000003'),('100000000012','100000000012','100000000012服务','1000000','1','000000000000'),('100000000012','110000000012','服务单元','1100000','1','100000000012'),('100000000012','120000000002','单元外小程序','1200000','1','100000000012'),('100000000012','130000000002','数据库设置','1300000','1','100000000012'),('100000000012','140000000002','集群设置','1400000','1','100000000012'),('100000000012','150000000002','模板','1500000','1','100000000012'),('100000000013','100000000013','100000000013服务','1000000','1','000000000000'),('100000000013','110000000030','110000000030服务单元','1100000','1','100000000013'),('100000000013','120000000142','单元外小程序','1200000','1','100000000013'),('100000000013','130000000142','数据库设置','1300000','1','100000000013'),('100000000013','140000000142','集群设置','1400000','1','100000000013'),('100000000013','150000000142','模板','1500000','1','100000000013'),('100000000014','100000000014','100000000014服务','1000000','1','000000000000'),('100000000014','110000000031','110000000031服务单元','1100000','1','100000000014'),('100000000014','120000000143','单元外小程序','1200000','1','100000000014'),('100000000014','130000000143','数据库设置','1300000','1','100000000014'),('100000000014','140000000143','集群设置','1400000','1','100000000014'),('100000000014','150000000143','模板','1500000','1','100000000014'),('100000000015','100000000015','100000000015服务','1000000','1','000000000000'),('100000000015','110000000032','110000000032服务单元','1100000','1','100000000015'),('100000000015','120000000144','单元外小程序','1200000','1','100000000015'),('100000000015','130000000144','数据库设置','1300000','1','100000000015'),('100000000015','140000000144','集群设置','1400000','1','100000000015'),('100000000015','150000000144','模板','1500000','1','100000000015'),('100000000016','100000000016','100000000016服务','1000000','1','000000000000'),('100000000016','110000000033','110000000033服务单元','1100000','1','100000000016'),('100000000016','120000000145','单元外小程序','1200000','1','100000000016'),('100000000016','130000000145','数据库设置','1300000','1','100000000016'),('100000000016','140000000145','集群设置','1400000','1','100000000016'),('100000000016','150000000145','模板','1500000','1','100000000016'),('100000000017','100000000017','100000000017服务','1000000','1','000000000000'),('100000000017','110000000034','110000000034服务单元','1100000','1','100000000017'),('100000000017','120000000146','单元外小程序','1200000','1','100000000017'),('100000000017','130000000146','数据库设置','1300000','1','100000000017'),('100000000017','140000000146','集群设置','1400000','1','100000000017'),('100000000017','150000000146','模板','1500000','1','100000000017'),('100000000018','100000000018','100000000018服务','1000000','1','000000000000'),('100000000018','110000000035','110000000035服务单元','1100000','1','100000000018'),('100000000018','120000000147','单元外小程序','1200000','1','100000000018'),('100000000018','130000000147','数据库设置','1300000','1','100000000018'),('100000000018','140000000147','集群设置','1400000','1','100000000018'),('100000000018','150000000147','模板','1500000','1','100000000018'),('100000000019','100000000019','100000000019服务','1000000','1','000000000000'),('100000000019','110000000036','110000000036服务单元','1100000','1','100000000019'),('100000000019','120000000148','单元外小程序','1200000','1','100000000019'),('100000000019','130000000148','数据库设置','1300000','1','100000000019'),('100000000019','140000000148','集群设置','1400000','1','100000000019'),('100000000019','150000000148','模板','1500000','1','100000000019'),('100000000021','100000000021','100000000021服务','1000000','1','000000000000'),('100000000021','110000000037','110000000037服务单元','1100000','1','100000000021'),('100000000021','120000000149','单元外小程序','1200000','1','100000000021'),('100000000021','130000000149','数据库设置','1300000','1','100000000021'),('100000000021','140000000149','集群设置','1400000','1','100000000021'),('100000000021','150000000149','模板','1500000','1','100000000021'),('100000000022','100000000022','100000000022服务','1000000','1','000000000000'),('100000000022','110000000038','110000000038服务单元','1100000','1','100000000022'),('100000000022','120000000150','单元外小程序','1200000','1','100000000022'),('100000000022','130000000150','数据库设置','1300000','1','100000000022'),('100000000022','140000000150','集群设置','1400000','1','100000000022'),('100000000022','150000000150','模板','1500000','1','100000000022'),('100000000023','100000000023','100000000023服务','1000000','1','000000000000'),('100000000023','110000000039','110000000039服务单元','1100000','1','100000000023'),('100000000023','120000000151','单元外小程序','1200000','1','100000000023'),('100000000023','130000000151','数据库设置','1300000','1','100000000023'),('100000000023','140000000151','集群设置','1400000','1','100000000023'),('100000000023','150000000151','模板','1500000','1','100000000023'),('100000000024','100000000024','100000000024服务','1000000','1','000000000000'),('100000000024','110000000040','110000000040服务单元','1100000','1','100000000024'),('100000000024','120000000152','单元外小程序','1200000','1','100000000024'),('100000000024','130000000152','数据库设置','1300000','1','100000000024'),('100000000024','140000000152','集群设置','1400000','1','100000000024'),('100000000024','150000000152','模板','1500000','1','100000000024'),('100000000025','100000000025','100000000025服务','1000000','1','000000000000'),('100000000025','110000000041','110000000041服务单元','1100000','1','100000000025'),('100000000025','120000000153','单元外小程序','1200000','1','100000000025'),('100000000025','130000000153','数据库设置','1300000','1','100000000025'),('100000000025','140000000153','集群设置','1400000','1','100000000025'),('100000000025','150000000153','模板','1500000','1','100000000025'),('100000000026','100000000026','100000000026服务','1000000','1','000000000000'),('100000000026','110000000042','110000000042服务单元','1100000','1','100000000026'),('100000000026','120000000154','单元外小程序','1200000','1','100000000026'),('100000000026','130000000154','数据库设置','1300000','1','100000000026'),('100000000026','140000000154','集群设置','1400000','1','100000000026'),('100000000026','150000000154','模板','1500000','1','100000000026'),('100000000027','100000000027','100000000027服务','1000000','1','000000000000'),('100000000027','110000000043','110000000043服务单元','1100000','1','100000000027'),('100000000027','120000000155','单元外小程序','1200000','1','100000000027'),('100000000027','130000000155','数据库设置','1300000','1','100000000027'),('100000000027','140000000155','集群设置','1400000','1','100000000027'),('100000000027','150000000155','模板','1500000','1','100000000027'),('100000000028','100000000028','100000000028服务','1000000','1','000000000000'),('100000000028','110000000044','110000000044服务单元修改测试','1100000','1','100000000028'),('100000000028','120000000156','单元外小程序','1200000','1','100000000028'),('100000000028','130000000156','数据库设置','1300000','1','100000000028'),('100000000028','140000000156','集群设置','1400000','1','100000000028'),('100000000028','150000000156','模板','1500000','1','100000000028'),('100000000029','100000000029','100000000029服务','1000000','1','000000000000'),('100000000029','110000000045','110000000045服务单元修改测试','1100000','1','100000000029'),('100000000029','120000000157','单元外小程序','1200000','1','100000000029'),('100000000029','130000000157','数据库设置','1300000','1','100000000029'),('100000000029','140000000157','集群设置','1400000','1','100000000029'),('100000000029','150000000157','模板','1500000','1','100000000029'),('100000000030','100000000030','100000000030服务','1000000','1','000000000000'),('100000000030','110000000047','110000000047服务单元','1100000','1','100000000030'),('100000000030','120000000158','单元外小程序','1200000','1','100000000030'),('100000000030','130000000158','数据库设置','1300000','1','100000000030'),('100000000030','140000000158','集群设置','1400000','1','100000000030'),('100000000030','150000000158','模板','1500000','1','100000000030'),('100000000031','100000000031','100000000031服务','1000000','1','000000000000'),('100000000031','110000000048','110000000048服务单元','1100000','1','100000000031'),('100000000031','120000000159','单元外小程序','1200000','1','100000000031'),('100000000031','130000000159','数据库设置','1300000','1','100000000031'),('100000000031','140000000159','集群设置','1400000','1','100000000031'),('100000000031','150000000159','模板','1500000','1','100000000031');

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

insert  into `s0000003`(`F1`,`F2`,`F3`,`F4`,`F5`) values ('BM','编码序列号管理','01','100000000031','服务编码'),('BM','编码序列号管理','02','110000000048','服务单元编码'),('BM','编码序列号管理','03','111000000000008','实时对象编码'),('BM','编码序列号管理','04','120000000159','单元外小程序编码'),('BM','编码序列号管理','05','130000000159','数据库设置编码'),('BM','编码序列号管理','06','140000000159','集群设置编码'),('BM','编码序列号管理','07','150000000159','模板编码'),('BM','编码序列号管理','08','112000000000002','数据对象编码'),('BM','编码序列号管理','09','113000000001','日程表编码'),('BM','编码序列号管理','10','114000000001','Restful API编码'),('BM','编码序列号管理','11','115000000001','Websocket Unit编码'),('BM','编码序列号管理','12','116000000001','单元内小程序编码'),('BM','编码序列号管理','13','111100000000009','DI编码'),('BM','编码序列号管理','14','111200000000009','DO编码'),('BM','编码序列号管理','15','111300000000003','AI编码'),('BM','编码序列号管理','16','111400000000001','AO编码'),('BM','编码序列号管理','17','111500000000001','DV编码'),('BM','编码序列号管理','18','111600000000001','AV编码'),('BM','编码序列号管理','19','111700000000001','SV编码');

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
  `F7` varchar(4) CHARACTER SET utf8 DEFAULT '8080',
  `F8` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `F9` tinyblob,
  `F10` datetime NOT NULL,
  `F11` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F1`),
  UNIQUE KEY `F2` (`F2`),
  UNIQUE KEY `IndexF1F4` (`F1`,`F4`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1000000` */

insert  into `s1000000`(`F1`,`F2`,`F3`,`F4`,`F5`,`F6`,`F7`,`F8`,`F9`,`F10`,`F11`) values ('100000000003','100000000003服务修改gggggg','test','100000000003','test','test','test','test','\"null\"','2018-05-24 11:46:39','2'),('100000000022','100000000022服务',NULL,'100000000022',NULL,NULL,'8080',NULL,NULL,'2018-06-30 12:16:13','1'),('100000000023','100000000023服务',NULL,'100000000023',NULL,NULL,'8080',NULL,NULL,'2018-06-30 12:16:56','1'),('100000000024','100000000024服务',NULL,'100000000024',NULL,NULL,'8080',NULL,NULL,'2018-06-30 12:19:52','1'),('100000000025','100000000025服务',NULL,'100000000025',NULL,NULL,'8080',NULL,NULL,'2018-06-30 12:20:32','1'),('100000000026','100000000026服务',NULL,'100000000026',NULL,NULL,'8080',NULL,NULL,'2018-06-30 12:21:20','1'),('100000000027','100000000027服务',NULL,'100000000027',NULL,NULL,'8080',NULL,NULL,'2018-06-30 12:22:18','1'),('100000000028','100000000028服务',NULL,'100000000028',NULL,NULL,'8080',NULL,NULL,'2018-07-02 11:30:04','1'),('100000000029','100000000029服务',NULL,'100000000029',NULL,NULL,'8080',NULL,NULL,'2018-07-02 11:39:21','1'),('100000000030','100000000030服务',NULL,'100000000030',NULL,NULL,'8080',NULL,NULL,'2018-07-12 17:37:06','1'),('100000000031','100000000031服务',NULL,'100000000031',NULL,NULL,'8080',NULL,NULL,'2018-07-12 18:51:19','1');

/*Table structure for table `s1100000` */

DROP TABLE IF EXISTS `s1100000`;

CREATE TABLE `s1100000` (
  `F1` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(12) CHARACTER SET utf8 NOT NULL,
  `F3` varchar(30) CHARACTER SET utf8 NOT NULL,
  `F4` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `F5` varchar(3) CHARACTER SET utf8 DEFAULT '000',
  `F6` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F7` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `F8` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `F9` datetime NOT NULL,
  `F10` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1100000` */

insert  into `s1100000`(`F1`,`F2`,`F3`,`F4`,`F5`,`F6`,`F7`,`F8`,`F9`,`F10`) values ('100000000003','110000000003','110000000003服务单元对象修改','test','11','test','test','test','2018-05-26 15:18:55','2'),('100000000013','110000000013','110000000013服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-27 11:57:50','1'),('100000000014','110000000014','110000000014服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-27 15:23:59','1'),('100000000015','110000000015','110000000015服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-27 15:24:59','1'),('100000000016','110000000016','110000000016服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:15:23','1'),('100000000017','110000000017','110000000017服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:15:34','1'),('100000000018','110000000018','110000000018服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:17:14','1'),('100000000019','110000000019','110000000019服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:17:19','1'),('100000000020','110000000020','110000000020服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:30:12','1'),('100000000021','110000000021','110000000021服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:30:23','1'),('100000000022','110000000022','110000000022服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:30:46','1'),('100000000023','110000000023','110000000023服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:31:14','1'),('100000000024','110000000024','110000000024服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:31:24','1'),('100000000025','110000000025','110000000025服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:32:48','1'),('100000000026','110000000026','110000000026服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:35:53','1'),('100000000027','110000000027','110000000027服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 11:36:53','1'),('100000000028','110000000028','110000000044服务单元修改测试','\"null\"','000','\"null\"','\"null\"','\"null\"','2018-07-02 11:30:04','1'),('100000000029','110000000029','110000000045服务单元修改测试','\"null\"','000','\"null\"','\"null\"','\"null\"','2018-07-02 11:39:21','1'),('100000000022','110000000038','110000000038服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 12:16:13','1'),('100000000023','110000000039','110000000039服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 12:16:56','1'),('100000000024','110000000040','110000000040服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 12:19:52','1'),('100000000025','110000000041','110000000041服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 12:20:32','1'),('100000000026','110000000042','110000000042服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 12:21:20','1'),('100000000027','110000000043','110000000043服务单元',NULL,'000',NULL,NULL,NULL,'2018-06-30 12:22:18','1'),('100000000028','110000000044','110000000044服务单元修改测试','\"null\"','000','\"null\"','\"null\"','\"null\"','2018-07-02 11:30:04','1'),('100000000029','110000000045','110000000045服务单元修改测试','\"null\"','000','\"null\"','\"null\"','\"null\"','2018-07-02 11:39:21','1'),('100000000026','110000000046','自定义服务单元名称',NULL,'000',NULL,NULL,NULL,'2018-07-02 14:54:20','1'),('100000000030','110000000047','110000000047服务单元',NULL,'000',NULL,NULL,NULL,'2018-07-12 17:37:06','1'),('100000000031','110000000048','110000000048服务单元',NULL,'000',NULL,NULL,NULL,'2018-07-12 18:51:19','1');

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

insert  into `s1110000`(`F1`,`F2`,`F3`,`F4`,`F5`,`F6`,`F7`) values ('100000000003','110000000003','111000000000005','实时对象','110000000003单元下，实时对象编码为111000000000005','2018-05-29 16:33:18','1'),('100000000004','110000000004','111000000000009','实时对象111000000000009','110000000004单元下，实时对象编码为111000000000009','2018-05-29 18:33:12','1'),('100000000004','110000000004','111000000000012','实时对象111000000000012','110000000004单元下，实时对象编码为111000000000012','2018-06-04 10:50:19','1'),('100000000004','110000000004','111000000000013','实时对象111000000000013','110000000004单元下，实时对象编码为111000000000013','2018-06-04 11:00:44','1'),('100000000004','110000000004','111000000000014','实时对象111000000000014','110000000004单元下，实时对象编码为111000000000014','2018-06-06 10:13:42','1');

/*Table structure for table `s1111000` */

DROP TABLE IF EXISTS `s1111000`;

CREATE TABLE `s1111000` (
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

/*Data for the table `s1111000` */

insert  into `s1111000`(`F1`,`F2`,`F3`,`F4`,`F5`,`F6`,`F7`,`F8`,`F9`,`F10`,`F11`,`F12`,`F13`) values ('100000000004','111000000000012','111100000000008','DI点','111000000000009下的属性点','1','1','\"null\"','\"null\"','\"null\"','2018-06-07 15:41:32','2018-06-07 17:00:00','1'),('100000000004','111000000000013','111100000000009','DI点点','111000000000009下的属性点','1','1','\"null\"','\"null\"','\"null\"','2018-06-07 15:41:32','2018-06-07 17:00:00','1'),('100000000004','111000000000009','111200000000007','DO','111000000000009下的属性点','2','1','','','','2018-06-07 15:42:24','2018-06-07 15:42:24','1'),('100000000004','111000000000013','111200000000009','DO','111000000000013下的属性点','2','1','','','','2018-06-07 15:43:44','2018-06-07 15:43:44','1');

/*Table structure for table `s1111100` */

DROP TABLE IF EXISTS `s1111100`;

CREATE TABLE `s1111100` (
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

/*Data for the table `s1111100` */

/*Table structure for table `s1111200` */

DROP TABLE IF EXISTS `s1111200`;

CREATE TABLE `s1111200` (
  `F1` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F2` varchar(18) CHARACTER SET utf8 NOT NULL,
  `F3` text CHARACTER SET utf8 NOT NULL,
  `F4` datetime NOT NULL,
  `F5` varchar(1) CHARACTER SET utf8 NOT NULL DEFAULT '1',
  PRIMARY KEY (`F2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `s1111200` */

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

/* Function  structure for function  `GetAutoCode` */

/*!50003 DROP FUNCTION IF EXISTS `GetAutoCode` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `GetAutoCode`(DicCode varchar(2)) RETURNS varchar(15) CHARSET utf8
BEGIN
        DECLARE AutoCode VARCHAR(30);
        UPDATE S0000003 SET F4 = F4 + 1 WHERE F1 = 'BM' AND F3 = DicCode;
	SELECT F4 INTO AutoCode FROM s0000003 WHERE F1 = 'BM' AND F3 = DicCode; 
	IF AutoCode IS null THEN
	RETURN 'null';
	else
	RETURN AutoCode;
	end if;
    END */$$
DELIMITER ;

/* Function  structure for function  `getNextChildNodes` */

/*!50003 DROP FUNCTION IF EXISTS `getNextChildNodes` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getNextChildNodes`(rootId VARCHAR(12)) RETURNS varchar(1000) CHARSET utf8
BEGIN
	  DECLARE sTemp VARCHAR(1000); 
       DECLARE sTempChd VARCHAR(1000); 
       SET sTemp = '$'; 
       SET sTempChd = rootId;
       WHILE sTempChd IS NOT NULL DO 
        SET sTemp = CONCAT(sTemp,',',sTempChd); 
         SELECT GROUP_CONCAT(F2) INTO sTempChd FROM S0000002 WHERE FIND_IN_SET(F6,sTempChd)>0; 
      END WHILE; 
      RETURN SUBSTRING(sTemp,16); 
    END */$$
DELIMITER ;

/* Function  structure for function  `getNextNodes` */

/*!50003 DROP FUNCTION IF EXISTS `getNextNodes` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `getNextNodes`(parentId varchar(12)) RETURNS varchar(1000) CHARSET utf8
BEGIN
	declare result varchar(1000);
	select F2 into result from S0000002 where F6 = parentId;
	IF result IS NULL THEN
	RETURN 'null';
	ELSE
	RETURN result;
	END IF;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
