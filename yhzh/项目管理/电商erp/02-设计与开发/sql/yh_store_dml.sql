
--公司表
delete from yh_pa_comp;
INSERT INTO yh_pa_comp(compid,compname,mnguserid,create_userid,createtime,mod_userid,modtime,remark) 
	VALUES ('comp001', '泰和顺电子商务1', '陈家福', 'Admin', '2019/2/1', 'admin', '2019/2/1', '永锋是傻屌');
INSERT INTO yh_pa_comp(compid,compname,mnguserid,create_userid,createtime,mod_userid,modtime,remark) 
 VALUES ('comp002', '泰和顺电子商务2', '陈家福', 'Admin', '2019/2/1', 'admin', '2019/2/1', '永锋是傻屌');
INSERT INTO yh_pa_comp(compid,compname,mnguserid,create_userid,createtime,mod_userid,modtime,remark) 
 VALUES ('comp003', '泰和顺电子商务3', '陈家福', 'Admin', '2019/2/1', 'admin', '2019/2/1', '永锋是傻屌');


-- 字典表
delete from yh_pa_dict;
-- 订单状态
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','UNPAID','待付款','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','READY_TO_SHIP','待发货','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','RETRY_SHIP','重新发货?','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','SHIPPED','已发货(运输中)','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','TO_CONFIRM_RECEIVE','待签收?','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','IN_CANCEL','申请取消','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','CANCELLED','已取消','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','TO_RETURN','申请退款？','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('OrderStatus','订单状态','COMPLETED','已完成','');
-- 付款方式
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Cybersource','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Nicepay Credit Card','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','IPay88 Credit Card','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Airpay Credit Card[PH]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Stripe CC','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Airpay Credit Card[ID, VN, TW, TH]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Transfer[ID]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank BCA (Manual Transfer)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Mandiri (Manual Transfer)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank BNI (Manual Transfer)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank BRI (Manual Transfer)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank CIMB Niaga (Manual Transfer)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Transfer[VN]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Fubon Bank Transfer','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Esun Bank Transfer','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Transfer[TW]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Esun CB Bank Transfer','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Transfer[SG]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Transfer[MY]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','ATM Payment','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','ATM Payment (BBL)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Transfer[TH]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','ATM Payment (KBANK)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','ATM Payment (KTB)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','ATM Payment (SCB)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','ATM Payment (BAY)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Online Payment (KBANK)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Online Payment (BAY)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Transfer[PH]','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Cash on Delivery','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','現付','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Shopee Seller Wallet','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Shopee Wallet','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Indomaret','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank BRI(Virtual Account)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank BCA (Virtual Account)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank Mandiri (Virtual Account)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Bank BNI (Virtual Account)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Virtual Account Parent','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Android Pay','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','MOLPay','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','iPay 88','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','iBanking Payment','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','iBanking Payment(BBL)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','iBanking Payment (KTB)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','iBanking Payment (SCB)','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Dragonpay - Remittance Center','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Dragonpay - OTC','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Dragonpay - Online Payment','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Buyer-Seller Self Arrange','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Kredivo','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Kredivo - BNPL','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Kredivo - 3 Months Installment','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Kredivo - 6 Months Installment','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Kredivo - 12 Months Installment','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Nicepay Credit Card Installment','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','BCA One Klik','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Akulaku','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','Free','已完成','');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) values ('PaymentMethod','付款方式','iPay88 CC Installment','已完成','');
-- 国家
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'JP', '日本', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'JO', '约旦', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'KH', '柬埔寨', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'KZ', '哈萨克斯坦', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'KE', '肯尼亚', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'KR', '韩国', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'KW', '科威特', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'KG', '吉尔吉斯坦', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LA', '老挝', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LV', '拉脱维亚', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LB', '黎巴嫩', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LS', '莱索托', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LR', '利比里亚', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LY', '利比亚', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LI', '列支敦士登', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LT', '立陶宛', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'LU', '卢森堡', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'MO', '澳门', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'MG', '马达加斯加', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'MW', '马拉维', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'MY', '马来西亚', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'MV', '马尔代夫', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'ML', '马里', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'MT', '马耳他', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', '', '马里亚那群岛', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'US', '美国', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'UY', '乌拉圭', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'UZ', '乌兹别克斯坦', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'VE', '委内瑞拉', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'VN', '越南', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'YE', '也门', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'YU', '南斯拉夫', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'ZW', '津巴布韦', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'ZR', '扎伊尔', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'ZM', '赞比亚', '');
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('country', '国家', 'TW', '台湾', null);
-- 物流公司
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('shipping_carrier', '物流公司', '13131413qee', '韵达快递', null);
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('shipping_carrier', '物流公司', 's', '中通快递', null);
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('shipping_carrier', '物流公司', 'w', '圆通快递', null);
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('shipping_carrier', '物流公司', 'sa', '顺丰快递', null);
INSERT INTO yh_pa_dict(dictname,dictdesc,mykey,val,myOrder) VALUES ('shipping_carrier', '物流公司', 'q', '申通快递', null);

-- 采购账号
delete from yh_pa_purinfo;
INSERT INTO yh_pa_purinfo(tb_code) VALUES ('1');
INSERT INTO yh_pa_purinfo(tb_code) VALUES ('3');
INSERT INTO yh_pa_purinfo(tb_code) VALUES ('4');
INSERT INTO yh_pa_purinfo(tb_code) VALUES ('5');
INSERT INTO yh_pa_purinfo(tb_code) VALUES ('6');
INSERT INTO yh_pa_purinfo(tb_code) VALUES ('7');
INSERT INTO yh_pa_purinfo(tb_code) VALUES ('8');

-- 角色表
delete from yh_pa_role;
INSERT INTO yh_pa_role(roleid,rolename,roleauth,roledesc,create_userid,createtime,mod_userid,modtime) 
	VALUES ('1001', '客服', '订单模块', '处理订单', '陈家福', '2019/2/1', '陈家福', '2019/2/1');
INSERT INTO yh_pa_role(roleid,rolename,roleauth,roledesc,create_userid,createtime,mod_userid,modtime) 
	VALUES ('1002', '拣货员', '仓库模块', '仓库对货，打包', 'Admin', '2019/2/1', 'Admin', '2019/2/1');

