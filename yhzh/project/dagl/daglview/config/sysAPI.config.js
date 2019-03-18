
// const BASEURL = "http://192.168.0.102:8888/api/"
const BASEURL = "http://116.62.112.118:8888/api/"
// const BASEURL = "http://192.168.1.178:8888/api/"

const URL = {

	//订单部分的API
	getUserShop:BASEURL+'getUserShop',  //获取店铺列表pull到多条件查询的下拉框
	SynchronizeOrder:BASEURL+'SynchronizeOrder', //同步订单接口

	// 导出功能
	ExportOrder:BASEURL+'ExportOrder', //同步订单接口

	// 标签管理
	getLabelList:BASEURL+'getLabelList',
	addLabel:BASEURL+'addLabel',
	delLabel:BASEURL+'delLabel',

	// 订单关联标签
	LabelRef:BASEURL+'LabelRef',
	delLabelRef:BASEURL+'delLabelRef',

	// 店铺列表管理API
	getShopList:BASEURL+'getShopList',
	requestUrl:BASEURL+'requestUrl',
	addShop:BASEURL+'addShop',
	delShop:BASEURL+'delShop',
	associateShop:BASEURL+'associateShop',
	// 采购账号管理API
	getPurinfo:BASEURL+'getPurinfo',
	addPurinfo:BASEURL+'addPurinfo',
	delPurinfo:BASEURL+'delPurinfo',
	// 公司信息管理API
	getCompList:BASEURL+'getCompList',
	// 角色管理API
	getRoleList:BASEURL+'getRoleList',
	// 员工管理API
	getStaffList:BASEURL+'getStaffList',
	// 公告管理API
	getBulletinList:BASEURL+'getBulletinList',
	addBulletin:BASEURL+'addBulletin',
	updateBulletin:BASEURL+'updateBulletin',
	delBulletin:BASEURL+'delBulletin',
	// 常用工具管理API
	addTool:BASEURL+'addTool',
	getToolList:BASEURL+'getToolList',
	updateTool:BASEURL+'updateTool',
	delTool:BASEURL+'delTool',
	// 代办事项
	todoList:BASEURL+'todoList',
}


module.exports = URL