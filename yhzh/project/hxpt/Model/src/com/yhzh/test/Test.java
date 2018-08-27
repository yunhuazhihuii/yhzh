package com.yhzh.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import com.yhzh.dao.ServerDao;

public class Test {

	public static void main(String[] args) {
//		String RTOAttrCatalogCode = "[{\"F1\":\"100000000151\",\"F2\":\"100000000151\",\"F3\":\"100000000151服务\",\"F4\":\"1000000\",\"F5\":\"1\",\"F6\":\"000000000000\",\"rightClick\":\"False\"}]";
//		String RTOAttrCode = RTOAttrCatalogCode.substring(0, RTOAttrCatalogCode.length()-2);
//		String DotCategory = RTOAttrCatalogCode.substring(15);
//		System.out.println(RTOAttrCode);
//		System.out.println(DotCategory);
		
	/*	String parentCode = "1100000";
		char[] arr = parentCode.toCharArray();
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == '0') {
				count ++;
			}
		}
		StringBuffer sb  = new StringBuffer();
		for (int i = 1; i <= 7-count; i++) {
			sb.append("1");
		}
		sb.append("%");
		for (int i = 1; i <count; i++) {
			sb.append("0");
		}
		System.out.println(sb.toString());*/
		String sb = "{F1=100000000001, F2=120000000001, F3=单元外小程序, F4=1200000, F5=2, F6=100000000001}";
		String sb2 = sb.substring(0,sb.length()-1);
		System.out.println(sb2);
	}

}
