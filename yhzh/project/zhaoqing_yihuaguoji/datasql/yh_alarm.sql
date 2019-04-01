/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-01 11:39:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `yh_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `yh_alarm`;
CREATE TABLE `yh_alarm` (
  `alarmId` varchar(20) NOT NULL COMMENT '报警id',
  `date` datetime NOT NULL COMMENT '报警时间',
  `reflectvar` varchar(50) DEFAULT NULL COMMENT '报警点编号',
  `system` varchar(50) DEFAULT NULL COMMENT '子系统',
  `AlarmLevel` varchar(3) DEFAULT NULL COMMENT '报警级别',
  `problem` varchar(100) DEFAULT NULL COMMENT '故障原因',
  `ACK` varchar(3) DEFAULT NULL COMMENT '确认标志(1已确认，0未确认)',
  `sureTime` datetime DEFAULT NULL COMMENT '确认时间',
  `sureUser` varchar(20) DEFAULT NULL COMMENT '确认人',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`alarmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警表';

-- ----------------------------
-- Records of yh_alarm
-- ----------------------------
INSERT INTO `yh_alarm` VALUES ('am20181027000687', '2018-10-27 15:55:16', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000688', '2018-10-27 15:55:16', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000689', '2018-10-27 15:55:16', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000690', '2018-10-27 15:55:16', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000691', '2018-10-27 15:55:16', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000692', '2018-10-27 15:55:16', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000693', '2018-10-27 15:55:16', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000694', '2018-10-27 15:55:16', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000695', '2018-10-27 15:55:16', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000696', '2018-10-27 15:55:16', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000697', '2018-10-27 15:55:16', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000698', '2018-10-27 15:55:16', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000699', '2018-10-27 15:55:16', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000700', '2018-10-27 15:55:16', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000701', '2018-10-27 15:55:16', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000702', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000703', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000704', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000705', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000706', '2018-10-27 15:55:16', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000707', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000708', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000709', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000710', '2018-10-27 15:55:17', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000711', '2018-10-27 15:55:17', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000712', '2018-10-27 15:55:17', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000713', '2018-10-27 15:55:17', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000714', '2018-10-27 15:55:17', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000715', '2018-10-27 15:56:17', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000716', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000717', '2018-10-27 15:56:17', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000718', '2018-10-27 15:56:17', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000719', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000720', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000721', '2018-10-27 15:56:17', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000722', '2018-10-27 15:56:17', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000723', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000724', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000725', '2018-10-27 15:56:17', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000726', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000727', '2018-10-27 15:56:17', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000728', '2018-10-27 15:56:17', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000729', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000730', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000731', '2018-10-27 15:56:17', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000732', '2018-10-27 15:56:17', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000733', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000734', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000735', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000736', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000737', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000738', '2018-10-27 15:56:17', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000739', '2018-10-27 15:56:17', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000740', '2018-10-27 15:56:17', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000741', '2018-10-27 15:56:17', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000742', '2018-10-27 15:56:17', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000743', '2018-10-27 15:57:17', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000744', '2018-10-27 15:57:17', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000745', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000746', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000747', '2018-10-27 15:57:17', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000748', '2018-10-27 15:57:17', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000749', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000750', '2018-10-27 15:57:17', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000751', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000752', '2018-10-27 15:57:17', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000753', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000754', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000755', '2018-10-27 15:57:17', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000756', '2018-10-27 15:57:17', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000757', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000758', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000759', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000760', '2018-10-27 15:57:17', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000761', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000762', '2018-10-27 15:57:17', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000763', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000764', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000765', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000766', '2018-10-27 15:57:17', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000767', '2018-10-27 15:57:17', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000768', '2018-10-27 15:57:17', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000769', '2018-10-27 15:57:17', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000770', '2018-10-27 15:57:17', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000771', '2018-10-27 15:58:17', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000772', '2018-10-27 15:58:17', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000773', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000774', '2018-10-27 15:58:18', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000775', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000776', '2018-10-27 15:58:18', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000777', '2018-10-27 15:58:18', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000778', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000779', '2018-10-27 15:58:18', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000780', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000781', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000782', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000783', '2018-10-27 15:58:18', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000784', '2018-10-27 15:58:18', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000785', '2018-10-27 15:58:18', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000786', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000787', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000788', '2018-10-27 15:58:18', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000789', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000790', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000791', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000792', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000793', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000794', '2018-10-27 15:58:18', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000795', '2018-10-27 15:58:18', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000796', '2018-10-27 15:58:18', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000797', '2018-10-27 15:58:18', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000798', '2018-10-27 15:58:18', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000799', '2018-10-27 15:59:18', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000800', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000801', '2018-10-27 15:59:18', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000802', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000803', '2018-10-27 15:59:18', 'A001.1100000000000018.1100000000001818', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000804', '2018-10-27 15:59:18', 'A001.1100000000000018.1100000000001816', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000805', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000806', '2018-10-27 15:59:18', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000807', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008103', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000808', '2018-10-27 15:59:18', 'A001.1721681031007.1721681031007114', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000809', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000810', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008104', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000811', '2018-10-27 15:59:18', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000812', '2018-10-27 15:59:18', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000813', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000814', '2018-10-27 15:59:18', 'A001.1721681031007.1721681031007116', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000815', '2018-10-27 15:59:18', 'A001.1721681031007.1721681031007115', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000816', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008106', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000817', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000818', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000819', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008101', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000820', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000821', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000822', '2018-10-27 15:59:18', 'A001.1721681031008.1721681031008201', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000823', '2018-10-27 15:59:18', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000824', '2018-10-27 15:59:18', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000825', '2018-10-27 15:59:18', 'A001.1200000000000001.1200000000000107', '空调', '1', 'testr', '0', null, null, null);
INSERT INTO `yh_alarm` VALUES ('am20181027000826', '2018-10-27 15:59:18', 'A001.1200000000000001.1200000000000102', '空调', '1', 'testr', '0', null, null, null);
