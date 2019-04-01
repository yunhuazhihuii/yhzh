/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1
 Source Server Version : 50621
 Source Host           : localhost
 Source Database       : RUNOOB

 Target Server Version : 50621
 File Encoding         : utf-8

 Date: 05/18/2016 11:44:07 AM
*/
CREATE DATABASE IF NOT EXISTS test CHARACTER SET UTF8;

USE test;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `PageReflect`;
CREATE TABLE `floorInfo` (
  `PageCode` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `floor` int(11) NOT NULL ,
  `equipmentId` varchar(64) NOT NULL ,
  `code` INT NOT NULL ,
  `equipment` varchar(64) NOT NULL ,
  `equipment` varchar(64) NOT NULL ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


    insert into viewreflect  values ('HBA0400001','startBut','',1),
      ('HBA0400001','FanAlarm','',1),
      ('HBA0400001','FanState','',1),
      ('HBA0400001','temperature','',1),
      ('HBA0400001','timeTotal','',1),
      ('HBA0400001','WaterAdjust','',1),
      ('HBA0400001','WaterFeedback','',1);

[{"equipmentId":"2GAL1-1-1","equipment":"一楼一号灯","code":1},
{"equipmentId":"2GAL1-1-2","equipment":"照明回路L2","code":1},
{"equipmentId":"2GAL1-1-3","equipment":"照明回路L3","code":1},
{"equipmentId":"2GAL1-1-4","equipment":"照明回路L4","code":1},
{"equipmentId":"2GAL1-2-5","equipment":"照明回路L5","code":1},
{"equipmentId":"2GAL1-2-6","equipment":"照明回路L6","code":1},
{"equipmentId":"2GAL1-2-7","equipment":"照明回路L7","code":1},
{"equipmentId":"2GAL1-2-8","equipment":"照明回路L8","code":1},
{"equipmentId":"2GAL1-3-9","equipment":"照明回路L9","code":1},
{"equipmentId":"2GAL1-3-10","equipment":"照明回路L10","code":1},
{"equipmentId":"2GAL1-3-11","equipment":"照明回路L11","code":1},
{"equipmentId":"2GAL1-3-12","equipment":"照明回路L12","code":1},
{"equipmentId":"2GAL1-4-13","equipment":"照明回路L13","code":1},
{"equipmentId":"2GAL1-4-14","equipment":"照明回路L14","code":1},
{"equipmentId":"2GAL1-4-15","equipment":"照明回路L15","code":1},
{"equipmentId":"2GAL1-4-16","equipment":"照明回路L16","code":1},
{"equipmentId":"2GAL1-5-17","equipment":"照明回路L34","code":1},
{"equipmentId":"2GAL1-5-18","equipment":"照明回路L35","code":1},
{"equipmentId":"2GAL1-5-19","equipment":"照明回路L36","code":1},
{"equipmentId":"2GAL1-5-20","equipment":"照明回路L37","code":1},
{"equipmentId":"2GAL1-6-21","equipment":"照明回路L38","code":1},
{"equipmentId":"2GAL1-6-22","equipment":"照明回路L39","code":1},
{"equipmentId":"2GAL1-6-23","equipment":"照明回路L40","code":1},{
"equipmentId":"2GAL1-6-24","equipment":"照明回路L41","code":1},
{"equipmentId":"2GAL2-1-1","equipment":"照明回路L17","code":1},
{"equipmentId":"2GAL2-1-2","equipment":"照明回路L18","code":1},
{"equipmentId":"2GAL2-1-3","equipment":"照明回路L19","code":1},
{"equipmentId":"2GAL2-1-4","equipment":"照明回路L20","code":1},
{"equipmentId":"2GAL2-2-5","equipment":"照明回路L21","code":1},
{"equipmentId":"2GAL2-2-6","equipment":"照明回路L22","code":1},
{"equipmentId":"2GAL2-2-7","equipment":"照明回路L23","code":1},
{"equipmentId":"2GAL2-2-8","equipment":"照明回路L24","code":1},
{"equipmentId":"2GAL2-3-9","equipment":"照明回路L25","code":1},
{"equipmentId":"2GAL2-3-10","equipment":"照明回路L26","code":1},
{"equipmentId":"2GAL2-3-11","equipment":"照明回路L27","code":1},
{"equipmentId":"2GAL2-3-12","equipment":"照明回路L28","code":1},
{"equipmentId":"2GAL2-4-13","equipment":"照明回路L29","code":1},
{"equipmentId":"2GAL2-4-14","equipment":"照明回路L30","code":1},
{"equipmentId":"2GAL2-4-15","equipment":"照明回路L31","code":1},
{"equipmentId":"2GAL2-4-16","equipment":"照明回路L32","code":1},
{"equipmentId":"7S2GAL1-1-1","equipment":"照明回路L41","code":1},
{"equipmentId":"7S2GAL1-1-2","equipment":"照明回路L17","code":1},
{"equipmentId":"7S2GAL1-1-3","equipment":"照明回路L18","code":1},
{"equipmentId":"7S2GAL1-1-4","equipment":"照明回路L19","code":1},
{"equipmentId":"7S2GAL1-2-5","equipment":"照明回路L20","code":1},
{"equipmentId":"7S2GAL1-2-6","equipment":"照明回路L21","code":1},
{"equipmentId":"7S2GAL1-2-7","equipment":"照明回路L22","code":1},
{"equipmentId":"7S2GAL1-2-8","equipment":"照明回路L23","code":1},
{"equipmentId":"7S2GAL1-3-9","equipment":"照明回路L24","code":1},
{"equipmentId":"7S2GAL1-3-10","equipment":"照明回路L25","code":1},
{"equipmentId":"7S2GAL1-3-11","equipment":"照明回路L26","code":1},
{"equipmentId":"7S2GAL1-3-12","equipment":"照明回路L27","code":1},
{"equipmentId":"7S2GAL1-4-13","equipment":"照明回路L28","code":1},
{"equipmentId":"7S2GAL1-4-14","equipment":"照明回路L29","code":1},
{"equipmentId":"7S2GAL1-4-15","equipment":"照明回路L30","code":1},
{"equipmentId":"7S2GAL1-4-16","equipment":"照明回路L31","code":1},
{"equipmentId":"7S2GAL1-5-17","equipment":"照明回路L32","code":1},
{"equipmentId":"7S2GAL1-5-18","equipment":"照明回路L32","code":1},
{"equipmentId":"7S2GAL1-5-19","equipment":"照明回路L32","code":1},
{"equipmentId":"7S2GAL1-5-20","equipment":"照明回路L32","code":1}]
     
        




	UPDATE floorInfo SET equipmentId=''