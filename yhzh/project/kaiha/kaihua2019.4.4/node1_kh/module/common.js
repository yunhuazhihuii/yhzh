
var SIGN_REGEXP = /([yMdhsm])(\1*)/g;

function padding(s, len) {
    var len = len - (s + '').length;
    for (var i = 0; i < len; i++) { s = '0' + s; }
    return s;
};

module.exports ={
	/**
	 * @sha1加密模块 (加密固定,不可逆)
	 * @param str string 要加密的字符串
	 * @retrun string 加密后的字符串
	 * */
	getSha1 :function(str){
       var sha1 = crypto.createHash("sha1");//定义加密方式:md5不可逆,此处的md5可以换成任意hash加密的方法名称；
	    sha1.update(str);
	    var res = sha1.digest("hex");  //加密后的值d
	    return res;
	},

	/**
	 * 判断字符串是否为空  chenms 2018-9-15
	 * @param str string 传入的字符串
	 * @retrun Boolean 为空则true，否则false
	 * */
	isNull: function(str){
		var rst = false;
		if (str == null || str == undefined || str == '' || str.length <= 0){
			rst = true;
		}
		return rst;
	},
	/**获取格式化日期 chenms 2018-9-25
     * @param date 日期 需要传递，可以传new Date()
     * @param  pattern 若要获取yyyy-MM-dd格式的日期，可以不传； 若要获取日期时间，可传yyyyMMddhhmmss，返回20180925234353
     */
	formatDateTime: function (date, pattern) {
        pattern = pattern || DEFAULT_PATTERN;
        return pattern.replace(SIGN_REGEXP, function ($0) {
            switch ($0.charAt(0)) {
                case 'y': return padding(date.getFullYear(), $0.length);
                case 'M': return padding(date.getMonth() + 1, $0.length);
                case 'd': return padding(date.getDate(), $0.length);
                case 'w': return date.getDay() + 1;
                case 'h': return padding(date.getHours(), $0.length);
                case 'm': return padding(date.getMinutes(), $0.length);
                case 's': return padding(date.getSeconds(), $0.length);
            }
        });
    }
}