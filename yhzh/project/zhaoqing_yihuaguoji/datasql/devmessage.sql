/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-01 11:38:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `devmessage`
-- ----------------------------
DROP TABLE IF EXISTS `devmessage`;
CREATE TABLE `devmessage` (
  `devcode` varchar(255) NOT NULL,
  `floorNum` varchar(255) DEFAULT NULL,
  `subsystem` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`devcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of devmessage
-- ----------------------------
INSERT INTO `devmessage` VALUES ('FAN0100001', '1', 'fan', '一楼一号风机');
INSERT INTO `devmessage` VALUES ('FAN0100002', '1', 'fan', '一楼二号风机');
INSERT INTO `devmessage` VALUES ('FAN0100003', '1', 'fan', '一楼三号风机');
INSERT INTO `devmessage` VALUES ('FAN0100004', '1', 'fan', '一楼四号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B1', '-1', 'fan', '负一楼一号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B10', '-1', 'fan', '负一楼十号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B11', '-1', 'fan', '负一楼11号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B12', '-1', 'fan', '负一楼12号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B2', '-1', 'fan', '负一楼二号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B3', '-1', 'fan', '负一楼三号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B4', '-1', 'fan', '负一楼四号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B5', '-1', 'fan', '负一楼五号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B6', '-1', 'fan', '负一楼六号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B7', '-1', 'fan', '负一楼七号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B8', '-1', 'fan', '负一楼八号风机');
INSERT INTO `devmessage` VALUES ('FAN01000B9', '-1', 'fan', '负一楼九号风机');
INSERT INTO `devmessage` VALUES ('FAN0200001', '2', 'fan', '二楼一号风机');
INSERT INTO `devmessage` VALUES ('FAN0200002', '2', 'fan', '二楼二号风机');
INSERT INTO `devmessage` VALUES ('FAN0200003', '2', 'fan', '二号三号风机');
INSERT INTO `devmessage` VALUES ('FAN0200004', '2', 'fan', '二号四号风机');
INSERT INTO `devmessage` VALUES ('FAN0200005', '2', 'fan', '二楼五号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B01', '-2', 'fan', '负二楼一号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B02', '-2', 'fan', '负二楼二号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B03', '-2', 'fan', '负二楼三号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B04', '-2', 'fan', '负二楼四号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B05', '-2', 'fan', '负二楼五号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B06', '-2', 'fan', '负二楼六号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B07', '-2', 'fan', '负二楼七号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B08', '-2', 'fan', '负二楼八号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B09', '-2', 'fan', '负二楼九号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B10', '-2', 'fan', '负二楼十号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B11', '-2', 'fan', '负二楼十一号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B12', '-2', 'fan', '负二楼十二号风机');
INSERT INTO `devmessage` VALUES ('FAN02000B13', '-2', 'fan', '负二楼十三号风机');
INSERT INTO `devmessage` VALUES ('FAN0300001', '3', 'fan', '三楼一层风机');
INSERT INTO `devmessage` VALUES ('FAN0300002', '3', 'fan', '三楼二号风机');
INSERT INTO `devmessage` VALUES ('FAN0300003', '3', 'fan', '三楼三号风机');
INSERT INTO `devmessage` VALUES ('HBA0100001', '1', 'BA', '一楼一号空调');
INSERT INTO `devmessage` VALUES ('HBA0100002', '1', 'BA', '一楼二号空调');
INSERT INTO `devmessage` VALUES ('HBA0100003', '1', 'BA', '一楼三号空调');
INSERT INTO `devmessage` VALUES ('HBA0100004', '1', 'BA', '一楼四号空调');
INSERT INTO `devmessage` VALUES ('HBA0100005', '1', 'BA', '一楼五号空调');
INSERT INTO `devmessage` VALUES ('HBA0100006', '1', 'BA', '一楼六号空调');
INSERT INTO `devmessage` VALUES ('HBA01000B1', '-1', 'BA', '负一楼1号新风机');
INSERT INTO `devmessage` VALUES ('HBA01000B2', '-1', 'BA', '负一楼2号新风机');
INSERT INTO `devmessage` VALUES ('HBA01000B3', '-1', 'BA', '负一楼3号新风机');
INSERT INTO `devmessage` VALUES ('HBA01000B4', '-1', 'BA', '负一楼4号新风机');
INSERT INTO `devmessage` VALUES ('HBA01000B5', '-1', 'BA', '负一楼1号空调');
INSERT INTO `devmessage` VALUES ('HBA01000B6', '-1', 'BA', '负一楼2号空调');
INSERT INTO `devmessage` VALUES ('HBA01000B7', '-1', 'BA', '负一楼3号空调');
INSERT INTO `devmessage` VALUES ('HBA0200001', '2', 'BA', '二楼一号空调');
INSERT INTO `devmessage` VALUES ('HBA0200002', '2', 'BA', '二楼二号空调');
INSERT INTO `devmessage` VALUES ('HBA0200003', '2', 'BA', '二楼三号空调');
INSERT INTO `devmessage` VALUES ('HBA0200004', '2', 'BA', '二楼四号空调');
INSERT INTO `devmessage` VALUES ('HBA0200005', '2', 'BA', '二楼五号空调');
INSERT INTO `devmessage` VALUES ('HBA0200006', '2', 'BA', '二楼六号空调');
INSERT INTO `devmessage` VALUES ('HBA0200007', '2', 'BA', '二楼七号空调');
INSERT INTO `devmessage` VALUES ('HBA0200008', '2', 'BA', '二楼八号空调');
INSERT INTO `devmessage` VALUES ('HBA0300001', '3', 'BA', '三楼一号空调');
INSERT INTO `devmessage` VALUES ('HBA0300002', '3', 'BA', '三楼二号空调');
INSERT INTO `devmessage` VALUES ('HBA0300003', '3', 'BA', '三楼三号空调');
INSERT INTO `devmessage` VALUES ('HBA0300004', '3', 'BA', '三楼四号空调');
INSERT INTO `devmessage` VALUES ('HBA0400001', '4', 'BA', '四楼一号空调');
INSERT INTO `devmessage` VALUES ('SLU01000B01-1', '-2', 'slu', '负二楼01-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B01-2', '-2', 'slu', '负二楼01-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B02-1', '-2', 'slu', '负二楼02-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B02-2', '-2', 'slu', '负二楼02-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B03-1', '-2', 'slu', '负二楼03-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B04-1', '-2', 'slu', '负二楼04-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B05-1', '-2', 'slu', '负二楼05-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B05-2', '-2', 'slu', '负二楼05-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B06-1', '-2', 'slu', '负二楼06-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B06-2', '-2', 'slu', '负二楼06-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B07-1', '-2', 'slu', '负二楼07-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B07-2', '-2', 'slu', '负二楼07-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B08-1', '-2', 'slu', '负二楼08-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B08-2', '-2', 'slu', '负二楼08-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B09-1', '-2', 'slu', '负二楼09-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B09-2', '-2', 'slu', '负二楼09-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B10-1', '-2', 'slu', '负二楼10-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B10-2', '-2', 'slu', '负二楼10-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B11-1', '-2', 'slu', '负二楼11-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B11-2', '-2', 'slu', '负二楼11-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B12-1', '-2', 'slu', '负二楼12-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B12-2', '-2', 'slu', '负二楼12-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B13-1', '-2', 'slu', '负二楼13-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B13-2', '-2', 'slu', '负二楼13-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B14-1', '-2', 'slu', '负二楼14-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B14-2', '-2', 'slu', '负二楼14-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B15-1', '-2', 'slu', '负二楼15-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B16-1', '-2', 'slu', '负二楼16-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B16-2', '-2', 'slu', '负二楼16-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B17-1', '-2', 'slu', '负二楼17-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B18-1', '-2', 'slu', '负二楼18-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B18-2', '-2', 'slu', '负二楼18-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B19-1', '-2', 'slu', '负二楼19-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B19-2', '-2', 'slu', '负二楼19-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B20-1', '-2', 'slu', '负二楼20-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B21-1', '-2', 'slu', '负二楼21-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B22-1', '-2', 'slu', '负二楼22-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B23-1', '-2', 'slu', '负二楼23-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B23-2', '-2', 'slu', '负二楼23-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B24-1', '-2', 'slu', '负二楼24-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B25-1', '-2', 'slu', '负二楼25-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B26-1', '-2', 'slu', '负二楼26-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B26-2', '-2', 'slu', '负二楼26-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B27-1', '-2', 'slu', '负二楼27-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B27-2', '-2', 'slu', '负二楼27-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B28-1', '-2', 'slu', '负二楼28-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B29-1', '-2', 'slu', '负二楼29-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B30-1', '-2', 'slu', '负二楼30-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B30-2', '-2', 'slu', '负二楼30-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B31-1', '-2', 'slu', '负二楼31-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B31-2', '-2', 'slu', '负二楼31-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B32-1', '-2', 'slu', '负二楼32-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B32-2', '-2', 'slu', '负二楼32-2号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B33-1', '-2', 'slu', '负二楼33-1号污水泵');
INSERT INTO `devmessage` VALUES ('SLU01000B33-2', '-2', 'slu', '负二楼33-2号污水泵');
