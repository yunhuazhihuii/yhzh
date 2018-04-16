package com.yhzh.znzm.socket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.annotation.Resource;

import org.json.JSONObject;

import com.yhzh.bacnet.dao.AirCondiDao;
import com.yhzh.util.JsonUtil;
import com.yhzh.zhyq.dao.BaseDao;

public class TestOut { 

public static void main(String[] args) throws ParseException {
	Date date=new Date();
    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
   String scheduleid  =	sdf1.format(date);
   String da="9:12:0";
   sdf1.parse(da);
   System.out.println(scheduleid+"sssssssssssssssss"+ sdf1.format(sdf1.parse(da)));
}
}
