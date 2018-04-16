package com.yhzh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**工具类方法
 * @author samsun 2018-01-14
 *
 */
public class YhzhUtil {
	
	/**判断集合中是否存在某key-value的map
	 * @param list
	 * @param name
	 * @param value
	 * @return
	 */
	public boolean isInList(List<Map<String,Object>> list,String key,String value){
		boolean bRst = false;
		for(Map<String,Object> map: list){
			if(value.equals((String)map.get(key))){
				bRst = true;
				break;
			}
		}
		return bRst;
	}
	
	/**
     * 判断当前日期是星期几
     * 
     * @param date 修要判断的时间
     * @return dayForWeek 判断结果 
     */
 public  int dayForWeek(Date date)  {

  Calendar c = Calendar.getInstance();
  c.setTime(date);
  int dayForWeek = 0;
  if(c.get(Calendar.DAY_OF_WEEK) == 1){
   dayForWeek = 7;
  }else{
   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
  }
  return dayForWeek;
 }
}
