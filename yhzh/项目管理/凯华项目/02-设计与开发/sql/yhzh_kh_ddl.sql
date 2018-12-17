-- ----------------------------
-- Table structure for `yh_user`用户表
-- ----------------------------
DROP TABLE IF EXISTS `yh_user`;
CREATE TABLE `yh_user` (
  `userid` varchar(12) NOT NULL COMMENT '用户id',
  `userenname` varchar(16) NOT NULL COMMENT '用户英文名',
  `usercnname` varchar(100) NOT NULL COMMENT '用户中文名',
  `roleid` varchar(12) DEFAULT NULL COMMENT '角色id',
  `password` varchar(200) NOT NULL COMMENT '密码',
  PRIMARY KEY (`userid`)
) COMMENT='用户表';

-- ----------------------------
-- 设备类型表(YH_DEV_TYPE)
-- ----------------------------
DROP TABLE IF EXISTS `YH_DEV_TYPE`;
CREATE TABLE `YH_DEV_TYPE` (
  `devTypeId` varchar(3) NOT NULL COMMENT '设备类型编号',
  `pareDevTypeId` varchar(3) DEFAULT NULL COMMENT '父设备编号',
  `devTypeName` varchar(100) NOT NULL COMMENT '设备类型名称',
  `isStoreHis` varchar(1) NOT NULL COMMENT '是否存储历史数据',
  `storeRate` int DEFAULT NULL COMMENT '数据存储频率(分钟)',
  `storeDays` int DEFAULT NULL COMMENT '数据存储时效(天数)',
  PRIMARY KEY (`devTypeId`)
) COMMENT='设备类型表';


-- ----------------------------
-- 设备类型属性表(YH_DEV_ATTR)
-- ----------------------------
DROP TABLE IF EXISTS `YH_DEV_ATTR`;
CREATE TABLE `YH_DEV_ATTR` (
  `devAtrrId` varchar(16) NOT NULL COMMENT '属性编号(设备类型编号+3位编码)',
  `devtypeid` varchar(3) NOT NULL COMMENT '设备类型编号',
  `attrClass` varchar(60) DEFAULT NULL COMMENT '属性分类',
  `attrEnName` varchar(50) DEFAULT NULL COMMENT '属性英文名称',
  `attrCnName` varchar(50) NOT NULL COMMENT '属性中文名称',
  `attrUnit` varchar(20) DEFAULT NULL COMMENT '属性单位',
  `attrDict` varchar(60) DEFAULT NULL COMMENT '属性数据字典',
  `attrWidth` varchar(10) DEFAULT NULL COMMENT '列表宽度',
  `tabOrder` Int DEFAULT NULL COMMENT '列表展示顺序',
  PRIMARY KEY (`devAtrrId`),
  INDEX(devtypeid)
) COMMENT='设备类型属性表';


-- ----------------------------
-- 设备信息表(YH_DEV_INFO)
-- ----------------------------
DROP TABLE IF EXISTS `YH_DEV_INFO`;
CREATE TABLE `YH_DEV_INFO` (
  `devId` varchar(16) NOT NULL COMMENT '设备编号',
  `pareDevId` varchar(16) DEFAULT NULL COMMENT '父设备编号',
  `devtypeid` varchar(3) NOT NULL COMMENT '设备类型编号',
  `devEnName` varchar(50) DEFAULT NULL COMMENT '设备英文名称',
  `devCnName` varchar(255) DEFAULT NULL COMMENT '设备中文名称',
  `devModel` varchar(255) DEFAULT NULL COMMENT '设备型号',
  `floor` varchar(16)  NOT NULL COMMENT '设备所在楼层',
  `location` varchar(100) DEFAULT NULL COMMENT '设备位置',
  PRIMARY KEY (`devId`),
  INDEX(devtypeid)
) COMMENT='设备信息表';

-- ----------------------------
-- 设备属性点映射表(YH_DEV_POINT_REF)
-- ----------------------------
DROP TABLE IF EXISTS `YH_DEV_POINT_REF`;
CREATE TABLE `YH_DEV_POINT_REF` (
  `adapterId` varchar(12) NOT NULL COMMENT '适配器编号',
  `controllerId` varchar(16) NOT NULL COMMENT '控制器编号',
  `pointId` varchar(20) NOT NULL COMMENT '点编号',
  `devId` varchar(16) NOT NULL COMMENT '设备编号',
  `devAtrrId` varchar(16)  NOT NULL COMMENT '属性编号',
  PRIMARY KEY (`pointId`),
  INDEX(devId),
  INDEX(devAtrrId)
) COMMENT='设备属性点映射表';


-- ----------------------------
-- 序列号表(YH_SERIAL)
-- ----------------------------
DROP TABLE IF EXISTS `YH_SERIAL`;
CREATE TABLE `YH_SERIAL` (
  `tablekey` varchar(50) NOT NULL COMMENT '序列名',
  `currentNo` varchar(20) DEFAULT NULL COMMENT '当前序列号',
  `format` varchar(30) DEFAULT NULL COMMENT '格式，如c{yyyyMMddhhmmss}ddd',
  `currdate` varchar(20) DEFAULT NULL COMMENT '当前编号所在日期（格式为yyyymmdd）',
  PRIMARY KEY (`tablekey`)
) COMMENT='序列号表';


-- ----------------------------
-- 字典表(YH_DICT)
-- ----------------------------
DROP TABLE IF EXISTS `YH_DICT`;
CREATE TABLE `YH_DICT` (
  `dictname` varchar(60) NOT NULL COMMENT '字典名',
  `dictdesc` varchar(100) DEFAULT NULL COMMENT '字典描述',
  `mykey` varchar(50) DEFAULT NULL COMMENT '字典key',
  `val` varchar(255) DEFAULT NULL COMMENT '字典value',
  `myOrder` varchar(255) DEFAULT NULL COMMENT '顺序',
  UNIQUE INDEX IN_YH_DICT_1 (`dictname`,`mykey`)
) COMMENT='字典表';

-- *******************************以下是适配器相关表****************************************
-- ----------------------------
-- 适配器配置表(yhzh_adapter)
-- ----------------------------
DROP TABLE IF EXISTS `yhzh_adapter`;
CREATE TABLE `yhzh_adapter` (
  `adapterId` varchar(12) NOT NULL COMMENT '适配器编号',
  `adapterName` varchar(50) NOT NULL COMMENT '适配器名称',
  `server` varchar(50) DEFAULT NULL COMMENT '适配器所在服务器',
  `adapterIP` varchar(64) DEFAULT NULL COMMENT '适配器IP',
  PRIMARY KEY (`adapterId`)
) COMMENT='适配器配置表';

-- ----------------------------
-- 控制器配置表(yhzh_controller)
-- ----------------------------
DROP TABLE IF EXISTS `yhzh_controller`;
CREATE TABLE `yhzh_controller` (
  `controllerid` varchar(16) NOT NULL COMMENT '控制器编号',
  `adapterId` varchar(12) NOT NULL COMMENT '适配器编号',
  `controllername` varchar(60) DEFAULT NULL COMMENT '控制器名称',
  `deviceid` varchar(16) DEFAULT NULL COMMENT '控制器物理编号',
  `IP` varchar(64) DEFAULT NULL COMMENT '控制器IP',
  `mac` varchar(64) DEFAULT NULL COMMENT '控制器物理地址',
  `contype` varchar(3) DEFAULT NULL COMMENT '控制器类型（1智能照明2bacnet）',
  PRIMARY KEY (`controllerid`)
) COMMENT='控制器配置表';

-- ----------------------------
-- 点配置表(yhzh_point)
-- ----------------------------
DROP TABLE IF EXISTS `yhzh_point`;
CREATE TABLE `yhzh_point` (
  `pointid` varchar(20) NOT NULL COMMENT '点编号',
  `phisid` varchar(20) DEFAULT NULL COMMENT '点物理编号',
  `controllerid` varchar(16) NOT NULL COMMENT '控制器编号',
  `pointname` varchar(60) DEFAULT NULL COMMENT '点名称',
  `objectName` varchar(30) DEFAULT NULL COMMENT '点物理名称(物理)',
  `describe` varchar(100) DEFAULT NULL COMMENT '点属性描述(如滤网报警)',
  `ioproperty` varchar(2) DEFAULT NULL COMMENT '点输入输出属性（1数据量输入2数据量输出3通用模拟量4模拟量输入5模拟量输出6通用数据量）',
  `pointtype` varchar(8) DEFAULT NULL COMMENT '点类型(1回路2组3区域4空调)',
  `instanceNumber` varchar(16) DEFAULT NULL COMMENT '点实例号(物理)',
  `issubs` varchar(3) DEFAULT NULL COMMENT '是否订阅(1是0否)',
  `increment` decimal DEFAULT NULL COMMENT '订阅变化范围(对模拟量点)',
  `saveWhenChg` varchar(3) DEFAULT NULL COMMENT '值变化是否登记历史数据(1是0否)',
  `pointvalue` varchar(20) DEFAULT NULL COMMENT '点值',
  PRIMARY KEY (`pointid`),
  INDEX(controllerid)
) COMMENT='点配置表';

-- ----------------------------
-- 历史数据表1(7-53楼空调柜机)(yh_point_val_his1)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his1`;
CREATE TABLE `yh_point_val_his1` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表1(7-53楼空调柜机)';

-- ----------------------------
-- 历史数据表2((新风机)(yh_point_val_his2)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his2`;
CREATE TABLE `yh_point_val_his2` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表2((新风机)';

-- ----------------------------
-- 历史数据表3(33楼大堂)(yh_point_val_his3)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his3`;
CREATE TABLE `yh_point_val_his3` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表3(33楼大堂)';

-- ----------------------------
-- 历史数据表4(南北大堂)(yh_point_val_his4)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his4`;
CREATE TABLE `yh_point_val_his4` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表4(南北大堂)';

-- ----------------------------
-- 历史数据表5(送排风)(yh_point_val_his5)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his5`;
CREATE TABLE `yh_point_val_his5` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表5(送排风)';

-- ----------------------------
-- 历史数据表6(照明)(yh_point_val_his6)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his6`;
CREATE TABLE `yh_point_val_his6` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表6(照明)';

-- ----------------------------
-- 历史数据表7(负一楼供冷)(yh_point_val_his7)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his7`;
CREATE TABLE `yh_point_val_his7` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表7(负一楼供冷)';

-- ----------------------------
-- 历史数据表8(37层板换)(yh_point_val_his8)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his8`;
CREATE TABLE `yh_point_val_his8` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表8(37层板换)';

-- ----------------------------
-- 历史数据表9(热水、盘管、电加热、插座)(yh_point_val_his9)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his9`;
CREATE TABLE `yh_point_val_his9` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表9(热水、盘管、电加热、插座)';

-- ----------------------------
-- 历史数据表18(漏水报警)(yh_point_val_his18)
-- ----------------------------
DROP TABLE IF EXISTS `yh_point_val_his18`;
CREATE TABLE `yh_point_val_his18` (
  `pointid` varchar(16) NOT NULL COMMENT '点编号',
  `recDate` varchar(16) DEFAULT NULL COMMENT '日期(yyyy-mm-dd格式)',
  `recTime` varchar(16) DEFAULT NULL COMMENT '时间(hh24:min格式)',
  `recReason` varchar(3) DEFAULT NULL COMMENT '登记缘由(1正常间隔;2状态变化)',
  `RecRealTime` varchar(30) DEFAULT NULL COMMENT '登记时间(yyyy-mm-dd HH24:mi:ss)',
  `pointvalue` varchar(20) NOT NULL COMMENT '点值',
  INDEX(pointid),
  INDEX(recDate,recTime)
) COMMENT='历史数据表18(漏水报警)';