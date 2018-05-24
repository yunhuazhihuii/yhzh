package com.yhzh.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//获得当前系统时间，转化成mysql的date,存入数据库中
				String Nowtime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				Date date = Date.valueOf(Nowtime);
				System.out.println(Nowtime);
				System.out.println(date);
	}

}
