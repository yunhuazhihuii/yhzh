/**
 * Created by Administrator on 2018/3/5.
 */
//获得主机的ip
function getIPAdress(){
    var interfaces = require('os').networkInterfaces();
    //console.log("interfaces" +interfaces);
    //console.log(interfaces);
    for(var devName in interfaces){
        //console.log("devName" +devName);
        //console.log(devName);
        var iface = interfaces[devName];
        //console.log("iface" +iface);
        //console.log(iface);
        for(var i=0;i<iface.length;i++){
            var alias = iface[i];
            //console.log("alias" + alias);
            //console.log(alias);
            if(alias.family === 'IPv4' && alias.address !== '127.0.0.1' && !alias.internal){
                return alias.address;
            }
        }
    }
}
module.exports = getIPAdress;