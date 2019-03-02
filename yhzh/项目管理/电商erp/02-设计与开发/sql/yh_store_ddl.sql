-- ----------------------------
-- 1.订单主表(yh_order)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_order`;
CREATE TABLE `yh_order` (
  `ordersn` varchar(30) NOT NULL COMMENT '订单号',
  `shop_id` varchar(10) DEFAULT NULL COMMENT '店铺id',
  `country` varchar(10) DEFAULT NULL COMMENT '国家',
  `currency` varchar(3) DEFAULT NULL COMMENT '币种',
  `cod` varchar(5) DEFAULT NULL COMMENT '是否到付',
  `tracking_no` varchar(30) DEFAULT NULL COMMENT '运单号',
  `days_to_ship` int DEFAULT NULL COMMENT '剩余发货天数',
  `estimated_shipping_fee` decimal DEFAULT NULL COMMENT '预估运费',
  `actual_shipping_cost` varchar(10) DEFAULT NULL COMMENT '实际运输成本',
  `total_amount` varchar(10) DEFAULT NULL COMMENT '订单总金额',
  `escrow_amount` varchar(10) DEFAULT NULL COMMENT '归属店铺收入总金额',
  `order_status` varchar(20) DEFAULT NULL COMMENT '订单状态',
  `shipping_carrier` varchar(20) DEFAULT NULL COMMENT '物流商',
  `payment_method` varchar(50) DEFAULT NULL COMMENT '付款方式',
  `update_time` varchar(30) DEFAULT NULL COMMENT '更新时间',
  `goods_to_declare` varchar(5) DEFAULT NULL COMMENT '是否需要申报海关(对进出口货品)',
  `message_to_seller` varchar(255) DEFAULT NULL COMMENT '买家备注',
  `note` varchar(255) DEFAULT NULL COMMENT '卖家备注',
  `note_update_time` varchar(30) DEFAULT NULL COMMENT '卖家备注更新时间',
  `create_time` varchar(30) DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` int DEFAULT NULL COMMENT '付款时间',
  `dropshipper` varchar(30) DEFAULT NULL COMMENT '承运批发商(仅印度尼西亚订单用到)',
  `credit_card_number` varchar(10) DEFAULT NULL COMMENT '信用卡号(末四位)',
  `buyer_username` varchar(100) DEFAULT NULL COMMENT '买家名称',
  `dropshipper_phone` varchar(30) DEFAULT NULL COMMENT '承运批发商电话',
  `request_id` varchar(30) DEFAULT NULL COMMENT '错误请求id(用于与服务端跟踪错误)',
  `local_currency` varchar(3) DEFAULT NULL COMMENT '收入详情-本地币种',
  `coin` varchar(10) DEFAULT NULL COMMENT '收入详情-所用平台购物币(GetEscrowDetails接口)',
  `voucher` varchar(10) DEFAULT NULL COMMENT '收入详情-所用平台购物券(GetEscrowDetails接口)',
  `voucher_seller` varchar(10) DEFAULT NULL COMMENT '收入详情-所用店铺购物券(GetEscrowDetails接口)',
  `seller_rebate` varchar(10) DEFAULT NULL COMMENT '收入详情-卖家回扣(平台补贴)(GetEscrowDetails接口)',
  `shipping_fee_rebate` varchar(10) DEFAULT NULL COMMENT '收入详情-平台运费补贴(GetEscrowDetails接口)',
  `commission_fee` varchar(10) DEFAULT NULL COMMENT '收入详情-手续费(平台收取)(GetEscrowDetails接口)',
  `voucher_code` varchar(30) DEFAULT NULL COMMENT '收入详情-购物券代码(GetEscrowDetails接口)',
  `voucher_name` varchar(30) DEFAULT NULL COMMENT '收入详情-购物券名称(GetEscrowDetails接口)',
  `cross_border_tax` decimal DEFAULT NULL COMMENT '收入详情-跨境税(GetEscrowDetails接口)',
  `credit_card_promotion` decimal DEFAULT NULL COMMENT '收入详情-信用卡促销抵扣费用?(GetEscrowDetails接口)',
  `credit_card_transaction_fee` decimal DEFAULT NULL COMMENT '收入详情-信用卡交易手续费?(GetEscrowDetails接口)',
  `escrow_currency` varchar(3) DEFAULT NULL COMMENT '卖家收款币种(3位数字)(GetEscrowDetails接口)',
  `exchange_rate` varchar(10) DEFAULT NULL COMMENT '汇率(local_currency与escrow_currency之间)(GetEscrowDetails接口)',
  `escrow_channel` varchar(16) DEFAULT NULL COMMENT '卖家收款渠道(GetEscrowDetails接口)',
  `payee_id` varchar(30) DEFAULT NULL COMMENT '收款人id?(GetEscrowDetails接口)',
  `bank_name` varchar(30) DEFAULT NULL COMMENT '收款银行信息-银行名称(GetEscrowDetails接口)',
  `bank_account_number` varchar(30) DEFAULT NULL COMMENT '收款银行信息-银行卡号(GetEscrowDetails接口)',
  `bank_account_country` varchar(3) DEFAULT NULL COMMENT '收款银行信息-卡号所属国家(2位数)(GetEscrowDetails接口)',
  `airway_bill` varchar(250) DEFAULT NULL COMMENT '面单pdf文件url',
  `md_print_time` varchar(30) DEFAULT NULL COMMENT '面单打印时间',
  `userid` varchar(30) DEFAULT NULL COMMENT '面单打印人',
  PRIMARY KEY (`ordersn`)
) COMMENT='订单主表';

-- ----------------------------
-- 2.订单收货地址表(与订单一对一)(yh_order_recaddr)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_order_recaddr`;
CREATE TABLE `yh_order_recaddr` (
  `ordersn` varchar(30) NOT NULL COMMENT '订单号',
  `name` varchar(100) DEFAULT NULL COMMENT '收件人姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '收件人电话',
  `town` varchar(30) DEFAULT NULL COMMENT '镇',
  `district` varchar(30) DEFAULT NULL COMMENT '区',
  `city` varchar(30) DEFAULT NULL COMMENT '市',
  `state` varchar(30) DEFAULT NULL COMMENT '省',
  `country` varchar(30) DEFAULT NULL COMMENT '国家',
  `zipcode` varchar(12) DEFAULT NULL COMMENT '邮编',
  `full_address` varchar(255) DEFAULT NULL COMMENT '完整地址',
  PRIMARY KEY (`ordersn`)
) COMMENT='订单收货地址表';

-- ----------------------------
-- 3.1.订单商品表(订单子表)(yh_order_item)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_order_item`;
CREATE TABLE `yh_order_item` (
  `ordersn` varchar(30) NOT NULL COMMENT '订单号',
  `item_id` varchar(64) NOT NULL COMMENT '商品id',
  `item_name` varchar(150) DEFAULT NULL COMMENT '商品名称',
  `item_sku` varchar(30) DEFAULT NULL COMMENT '商品SKU',
  `variation_id` varchar(64) DEFAULT NULL COMMENT '商品类别id',
  `variation_name` varchar(30) DEFAULT NULL COMMENT '商品类别名称(比如商品是红色、M码的，是一种组合)',
  `variation_sku` varchar(30) DEFAULT NULL COMMENT '商品类别SKU',
  `variation_quantity_purchased` varchar(20) DEFAULT NULL COMMENT '购买数量',
  `variation_original_price` varchar(10) DEFAULT NULL COMMENT '该类别商品原始价格',
  `variation_discounted_price` varchar(10) DEFAULT NULL COMMENT '该类别商品折后价',
  `is_wholesale` varchar(5) DEFAULT NULL COMMENT '是否按批发价购买',
  `weight` varchar(18) DEFAULT NULL COMMENT '商品重量',
  `discount_from_coin` varchar(10) DEFAULT NULL COMMENT '购物币抵扣金额(GetEscrowDetails接口)',
  `discount_from_voucher` decimal DEFAULT NULL COMMENT '购物券抵扣金额(GetEscrowDetails接口)',
  `discount_from_voucher_seller` decimal DEFAULT NULL COMMENT '店铺购物券抵扣金额 (GetEscrowDetails接口)',
  `seller_rebate` varchar(10) DEFAULT NULL COMMENT '卖家回扣(平台补贴)(GetEscrowDetails接口)',
  `deal_price` varchar(10) DEFAULT NULL COMMENT '买家支付实际金额(GetEscrowDetails接口)',	
  `credit_card_promotion` varchar(10) DEFAULT NULL COMMENT '信用卡促销抵扣费用(GetEscrowDetails接口)',
  `images` varchar(1000) DEFAULT NULL COMMENT '商品图片(GetItemDetail接口)',
  PRIMARY KEY (`ordersn`,`item_id`)
) COMMENT='订单商品表(订单子表)';

-- ----------------------------
-- 3.2.订单活动表(GetEscrowDetails接口返回)(yh_order_activity)【搁置】
-- ----------------------------

-- ----------------------------
-- 3.3.订单活动商品表(GetEscrowDetails接口返回)(yh_order_activity_item)【搁置】
-- ----------------------------

-- ----------------------------
-- 4.订单标签表(yh_order_label) 
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_order_label`;
CREATE TABLE `yh_order_label` (
  `ordersn` varchar(30) NOT NULL COMMENT '订单号',
  `labelid` varchar(20) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`ordersn`,`labelid`)
) COMMENT='订单标签表';	

-- ----------------------------
-- 5.采购信息表(yh_order_purchase)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_order_purchase`;
CREATE TABLE `yh_order_purchase` (
  `ordersn` varchar(30) NOT NULL COMMENT '订单号',
  `item_id` varchar(64) NOT NULL COMMENT '商品id',
  `userid` varchar(64) NOT NULL COMMENT '采购信息填写人(客服)',
  `tb_code` varchar(64) DEFAULT NULL COMMENT '采购账号(就是淘宝账号)',
  `tb_order_no` varchar(64) DEFAULT NULL COMMENT '淘宝订单号',
  `purchase_time` varchar(30) DEFAULT NULL COMMENT '采购时间',
  `purchase_price` decimal(10,0) DEFAULT NULL COMMENT '采购价格',
  `purchase_note` varchar(800) DEFAULT NULL COMMENT '采购备注',
  `tbk_url` varchar(800) DEFAULT NULL COMMENT '淘宝客链接',
  `tbk_bl` varchar(10) DEFAULT NULL COMMENT '淘宝客比例',
  `bgname` varchar(32) DEFAULT NULL COMMENT '报关名',
  `shipping_carrier` varchar(10) DEFAULT NULL COMMENT '物流公司',
  `shippingno` varchar(64) DEFAULT NULL COMMENT '物流单号',
  `bgzzkh` varchar(10) DEFAULT NULL COMMENT '包裹周转筐号',
  `reciorder` int DEFAULT NULL COMMENT '包裹到达顺序',
  PRIMARY KEY (`ordersn`,`item_id`)
) COMMENT='采购信息表';

-- ----------------------------
-- 6.采购退货订单表(yh_order_pur_return)【搁置】
-- ----------------------------


-- ----------------------------
-- 7.店铺表(yh_pa_shop)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_pa_shop`;
CREATE TABLE `yh_pa_shop` (
  `shop_id` varchar(64) NOT NULL COMMENT '店铺id(不能变化)',
  `partner_id` varchar(64) NOT NULL COMMENT 'partnerid',
  `shop_name` varchar(100) DEFAULT NULL COMMENT '店铺名称',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `country` varchar(10) DEFAULT NULL COMMENT '国家',
  `shop_description` varchar(1000) DEFAULT NULL COMMENT '店铺描述',
  `videos` varchar(1000) DEFAULT NULL COMMENT '店铺视频URL',
  `images` varchar(1000) DEFAULT NULL COMMENT '店铺介绍图片URL',
  `disable_make_offer` varchar(1) DEFAULT NULL COMMENT '是否可提建议？',
  `enable_display_unitno` varchar(5) DEFAULT NULL COMMENT '是否显示取货地址',
  `item_limit` varchar(16) DEFAULT NULL COMMENT '最大上新数',
  `account` varchar(20) DEFAULT NULL COMMENT '用户账号',
  `item` varchar(16) DEFAULT NULL COMMENT '产品数',
  `status` varchar(10) DEFAULT NULL COMMENT '店铺状态(BANNED,FROZEN,NORMAL)',
  `compid` varchar(20) DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`shop_id`)
) COMMENT='店铺表';


-- ----------------------------
-- 8.店铺修改记录表(yh_pa_shop_mod)【搁置】
-- ----------------------------

-- ----------------------------
-- 9.公司表(yh_pa_comp)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_pa_comp`;
CREATE TABLE `yh_pa_comp` (
  `compid` varchar(20) NOT NULL COMMENT '公司id',
  `compname` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `mnguserid` varchar(20) DEFAULT NULL COMMENT '管理员',
  `create_userid` varchar(20) DEFAULT NULL COMMENT '创建人',
  `createtime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `mod_userid` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modtime` varchar(30) DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`compid`)
) COMMENT='公司表';

-- ----------------------------
-- 10.员工表(yh_pa_staff)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_pa_staff`;
CREATE TABLE `yh_pa_staff` (
  `userid` varchar(20) NOT NULL COMMENT '员工号',
  `userenname` varchar(100) DEFAULT NULL COMMENT '员工英文名',
  `usercnname` varchar(100) DEFAULT NULL COMMENT '员工中文名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `shop_id` varchar(64) DEFAULT NULL COMMENT '店铺id',
  `roleid` varchar(20) DEFAULT NULL COMMENT '角色id',
  `create_userid` varchar(20) DEFAULT NULL COMMENT '创建人',
  `createtime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `mod_userid` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modtime` varchar(30) DEFAULT NULL COMMENT '修改时间',
  `headurl` varchar(100) DEFAULT NULL COMMENT '头像',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(3) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`userid`)
) COMMENT='员工表';

-- ----------------------------
-- 11.角色表(yh_pa_role)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_pa_role`;
CREATE TABLE `yh_pa_role` (
  `roleid` varchar(20) NOT NULL COMMENT '角色id',
  `rolename` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `roleauth` varchar(100) DEFAULT NULL COMMENT '角色权限',
  `roledesc` varchar(30) DEFAULT NULL COMMENT '角色描述',
  `create_userid` varchar(20) DEFAULT NULL COMMENT '创建人',
  `createtime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `mod_userid` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modtime` varchar(30) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`roleid`)
) COMMENT='角色表';

-- ----------------------------
-- 12.采购账号表(yh_pa_purinfo)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_pa_purinfo`;
CREATE TABLE `yh_pa_purinfo` (
  `tb_code` varchar(64) NOT NULL COMMENT '采购账号(就是淘宝账号)',
  PRIMARY KEY (`tb_code`)
) COMMENT='采购账号表';

-- ----------------------------
-- 13.标签标志维护表(yh_pa_label)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_pa_label`;
CREATE TABLE `yh_pa_label` (
  `labelid` varchar(20) NOT NULL COMMENT '标签id',
  `labeltype` varchar(2) DEFAULT NULL COMMENT '标签类型(1普通标签,2异常标签)',
  `color` varchar(20) DEFAULT NULL COMMENT '颜色',
  `lblorder` int DEFAULT NULL COMMENT '标签排序',
  PRIMARY KEY (`labelid`)
) COMMENT='标签标志维护表';

-- ----------------------------
-- 14.字典表(yh_pa_dict)
-- ----------------------------
set character set utf8;
DROP TABLE IF EXISTS `yh_pa_dict`;
CREATE TABLE `yh_pa_dict` (
  `dictname` varchar(60) NOT NULL COMMENT '字典名',
  `dictdesc` varchar(100) DEFAULT NULL COMMENT '字典描述',
  `mykey` varchar(50) DEFAULT NULL COMMENT '字典key',
  `val` varchar(255) DEFAULT NULL COMMENT '字典value',
  `myOrder` varchar(255) DEFAULT NULL COMMENT '顺序',
  UNIQUE INDEX IN_YH_DICT_1 (`dictname`,`mykey`)
) COMMENT='字典表';
