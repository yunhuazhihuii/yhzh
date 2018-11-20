-- ----------------------------
-- 设备类型表(YH_DEV_TYPE)
-- ----------------------------
delete from yh_dev_type;
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('1', '7-53楼空调柜机', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('2', '新风机', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('3', '33楼大堂', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('4', '南北大堂', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('5', '送排风', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('6', '照明', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('7', '负一楼供冷', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('8', '37层板换', '1', 60, 60);
INSERT INTO `yh_dev_type`(devTypeId,devTypeName,isStoreHis,storeRate,storeDays) VALUES ('9', '热水、盘管、电加热、插座', '1', 60, 60);

-- ----------------------------
-- 字典表(YH_DICT)
-- ----------------------------
delete from yh_dict;
INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('running_state', '运行状态', '1', '运行');
INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('running_state', '运行状态', '0', '停止');

INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('hand_auto', '手/自动', '0', '手动');
INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('hand_auto', '手/自动', '1', '自动');

INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('start_stop', '启停控制', '1', '开启');
INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('start_stop', '启停控制', '0', '关闭');

INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('trouble_warn', '故障报警', '0', '正常');
INSERT INTO `yh_dict`(dictname,dictdesc,mykey,val) VALUES ('trouble_warn', '故障报警', '1', '报警');