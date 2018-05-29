package com.yhzh.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//获得当前系统时间，转化成mysql的date,存入数据库中
			String str = "110000000002";
			String str1 = str.substring(11);
			System.out.println(str1);
	}

}
