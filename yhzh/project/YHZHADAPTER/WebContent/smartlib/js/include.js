/**
 * 
 */
$.extend({
     includePath: '',
     include: function(file) {
        var files = typeof file == "string" ? [file]:file;
        for (var i = 0; i < files.length; i++) {
            var name = files[i].replace(/^\s|\s$/g, "");
            var att = name.split('.');
            var ext = att[att.length - 1].toLowerCase();
            var isCSS = ext == "css";
            var tag = isCSS ? "link" : "script";
            var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
            var link = (isCSS ? "href" : "src") + "='" + $.includePath + name + "'";
            if ($(tag + "[" + link + "]").length == 0) 
            	document.write("<" + tag + attr + link + "></" + tag + ">");
        }
      }
});
 
//获取项目绝对路径 默认是./相对路径
$.includePath = "./";
if(typeof rootPath !==  "undefined"){
	$.includePath =  rootPath;
}

//加载js文件
$.include([
//           'css/easyui-themes/icon.css',
//           'css/easyui-themes/' + xcp_sys_constant_usertheme + '/easyui.css',
//           'css/gjjs-themes/'   + xcp_sys_constant_usertheme + '/uploadify.css',
//           'css/gjjs-themes/'   + xcp_sys_constant_usertheme + '/commons.css',
//           'css/easyui-themes/jquery.autocomplete.css',
//           'js/jquery-core/jquery.easyui.min.js',
//           'js/jquery-core/jquery.json-2.3.js',
//           'js/jquery-core/jquery.easyui-datagrid-detailview.js',
//           'js/jquery-core/jquery.autocomplete.js',
           'mylib/js/share/common.js'
     ]);  
