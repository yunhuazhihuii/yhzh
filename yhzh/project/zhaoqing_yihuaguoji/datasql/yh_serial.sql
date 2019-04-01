/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-01 11:39:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `yh_serial`
-- ----------------------------
DROP TABLE IF EXISTS `yh_serial`;
CREATE TABLE `yh_serial` (
  `tablekey` varchar(50) NOT NULL DEFAULT '' COMMENT '序列名',
  `currentNo` varchar(20) DEFAULT NULL COMMENT '当前序列号',
  `format` varchar(30) DEFAULT NULL COMMENT '格式，如c{yyyyMMddhhmmss}ddd',
  `currdate` varchar(20) DEFAULT NULL COMMENT '当前编号所在日期（格式为yyyymmdd）',
  PRIMARY KEY (`tablekey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='序列号表';

-- ----------------------------
-- Records of yh_serial
-- ----------------------------
INSERT INTO `yh_serial` VALUES ('yh_alarm.alarmId', 'am20181027000826', 'am{yyyyMMdd}dddddd', '20181027');
