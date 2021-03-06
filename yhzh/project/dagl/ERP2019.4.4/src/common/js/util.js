

// vue 时间戳转换为时间
var SIGN_REGEXP = /([yMdhsm])(\1*)/g;  //正则表达式格式化？
var DEFAULT_PATTERN = 'yyyy-MM-dd';    //时间格式化？
function padding(s, len) {
    var len = len - (s + '').length;
    for (var i = 0; i < len; i++) { s = '0' + s; }
    return s;
};

// JS中写Date日期类型的数据,要求具体格式化.
export default {

    /**获取格式化日期 chenms 2018-9-25
     * @param date 日期 需要传递，可以传new Date()
     * @param  pattern 若要获取yyyy-MM-dd格式的日期，可以不传；
      若要获取日期时间，可传yyyyMMddhhmmss，返回20180925234353
     */

    //在formatDate对象，创建两个方法：format、parse
    formatDate: {

        // format：格式化
        //date：数据从哪里来？
        format: function (date, pattern) {

            // pattern
            pattern = pattern || DEFAULT_PATTERN;

            //返回pattern
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
        },

        //parse接收一个表示日期的字符串参数。
        parse: function (dateString, pattern) {

            var matchs1 = pattern.match(SIGN_REGEXP);
            var matchs2 = dateString.match(/(\d)+/g);

            if (matchs1.length == matchs2.length) {
                var _date = new Date(1970, 0, 1);
                for (var i = 0; i < matchs1.length; i++) {
                    var _int = parseInt(matchs2[i]);
                    var sign = matchs1[i];
                    switch (sign.charAt(0)) {
                        case 'y': _date.setFullYear(_int); break;
                        case 'M': _date.setMonth(_int - 1); break;
                        case 'd': _date.setDate(_int); break;
                        case 'h': _date.setHours(_int); break;
                        case 'm': _date.setMinutes(_int); break;
                        case 's': _date.setSeconds(_int); break;
                    }
                }
                return _date;
            }
            return null;
        }

    },

    /**
     * 去掉字符串前后空格 chenms 2018-9-19
     */
    trim: function(str){ 
      return str.replace(/(^\s*)|(\s*$)/g, ""); 
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

};
/**
 * 存储localStorage
 */
export const setStore = (name, content) => {
    if (!name) return;
    if (typeof content !== 'string') {
        content = JSON.stringify(content);
    }
    window.localStorage.setItem(name, content);
}

/**
 * 获取localStorage
 */
export const getStore = name => {
    if (!name) return;
    return window.localStorage.getItem(name);
}

/**
 * 删除localStorage
 */
export const removeStore = name => {
    if (!name) return;
    window.localStorage.removeItem(name);
}

/**
  存储sessionStorage
 */
export const setSession = (name, content) => {
    if (!name) return;
    if (typeof content !== 'string') {
        content = JSON.stringify(content);
    }
    window.sessionStorage.setItem(name, content);
}
/**
 * 获取sessionStorage
 */
export const getSession = name => {
    if (!name) return;
    return window.sessionStorage.getItem(name);
}
