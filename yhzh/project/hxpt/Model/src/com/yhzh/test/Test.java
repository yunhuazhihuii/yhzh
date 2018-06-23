package com.yhzh.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import com.yhzh.dao.ServerDao;

public class Test {

	public static void main(String[] args) {
		String RTOAttrCatalogCode = "1110000000000081";
		String RTOAttrCode = RTOAttrCatalogCode.substring(0, 15);
		String DotCategory = RTOAttrCatalogCode.substring(15);
		System.out.println(RTOAttrCode);
		System.out.println(DotCategory);
	}

}
