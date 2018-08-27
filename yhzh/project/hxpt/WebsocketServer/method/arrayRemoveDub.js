/**
 * Created by Administrator on 2018/3/13.
 */
 function arrayRemoveDup(array){
    var result = [];
    var obj = {};
    for(var i = 0; i < array.length; i++){
        if(!obj[array[i]]){
            result.push(array[i]);
            obj[array[i]] = 1;
        }
    }
    return result;
}
module.exports = arrayRemoveDup;
