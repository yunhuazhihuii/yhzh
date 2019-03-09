
const BASEURL = "http://192.168.0.122:8888/api/"
// const BASEURL = "http://116.62.112.118:8888/api/"
// const BASEURL = "http://192.168.1.187:8888/api/"

const URL = {
	getShopList:BASEURL+'getShopList',
	requestUrl:BASEURL+'requestUrl',
	addShop:BASEURL+'addShop',
	delShop:BASEURL+'delShop',
	associateShop:BASEURL+'associateShop',
	getPurinfo:BASEURL+'getPurinfo',
	addPurinfo:BASEURL+'addPurinfo',
	delPurinfo:BASEURL+'delPurinfo',
	getCompList:BASEURL+'getCompList',
	getRoleList:BASEURL+'getRoleList',
	getStaffList:BASEURL+'getStaffList',
}


module.exports = URL