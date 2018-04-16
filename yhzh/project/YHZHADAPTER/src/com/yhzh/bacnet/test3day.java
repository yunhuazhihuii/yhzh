package com.yhzh.bacnet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test3day {
	/**
     * 判断当前日期是星期几
     * 
     * @param date 修要判断的时间
     * @return dayForWeek 判断结果 
     */
 public static int dayForWeek(Date date)  {

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
 



   public static void test() throws ParseException{
      String string = "21:59:06";
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
     System.out.println(sdf.parse(string));
   }
 
 public static void main(String[] args) throws Exception {
	Date date=new Date();
	
/*	 SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
	 System.out.println("现在时间："+sdf1.format(date));*/
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	int week=dayForWeek(date);
	System.out.println("今天是星期几："+week);
}
}
