package com.yhzh.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.yhzh.biz.ServerUnitBiz;
import com.yhzh.dao.ServerUnitDao;
import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1100000;

import net.sf.json.JSONObject;

public class ServerUnitBizImpl implements ServerUnitBiz {
	@Resource
	ServerUnitDao serverUnitDao;

	@Override
	public String findS1100000ByServerUnitCode(String ServerUnitCode) {
		StringBuffer sb = new StringBuffer();
		try {
			List<Map<String, Object>> findS1100000BySUC = serverUnitDao.findS1100000ByServerUnitCode(ServerUnitCode);
			List<Object> list = new ArrayList<Object>();
			for (Map<String, Object> map : findS1100000BySUC) {
				String F1 = (String) map.get("F1");
				String F2 = (String) map.get("F2");
				String F3 = (String) map.get("F3");
				String F4 = (String) map.get("F4");
				String F5 = (String) map.get("F5");
				String F6 = (String) map.get("F6");
				String F7 = (String) map.get("F7");
				String F8 = (String) map.get("F8");
				Date F9 = (Date) map.get("F9");
				String F10 = (String) map.get("F10");
				String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
						+ "','F6':'" + F6 + "','F7':'" + F7 + "','F8':'" + F8 + "','F9':'" + F9 + "','F10':'" + F10
						+ "'}";
				JSONObject jsonObject1 = new JSONObject().fromObject(string);
				list.add(jsonObject1);
			}
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");

		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'findS1100000ByServerUnitCode error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String updateS1100000(String ServerUnitCodeString) {
		// 将字符串转换成json对象
		JSONObject jsonObject1 = new JSONObject().fromObject(ServerUnitCodeString);
		StringBuffer sb = new StringBuffer();
		try {
			// 将数据更新到S1100000表中
			S1100000 s1100000 = new S1100000();
			s1100000.setF1((String) jsonObject1.get("F1"));
			s1100000.setF2((String) jsonObject1.get("F2"));
			s1100000.setF3((String) jsonObject1.get("F3"));
			s1100000.setF4((String) jsonObject1.get("F4"));
			s1100000.setF5((String) jsonObject1.get("F5"));
			s1100000.setF6((String) jsonObject1.get("F6"));
			s1100000.setF7((String) jsonObject1.get("F7"));
			s1100000.setF8((String) jsonObject1.get("F8"));
			s1100000.setF9((String) jsonObject1.get("F9"));
			s1100000.setF10((String) jsonObject1.get("F10"));
			// 将数据更新到S0000002表中
			S0000002 s0000002 = new S0000002();
			s0000002.setF2((String) jsonObject1.get("F2"));// 设置编码
			s0000002.setF3((String) jsonObject1.get("F3"));// 设置名称
			serverUnitDao.updateS1100000(s1100000);
			serverUnitDao.updateS0000002(s0000002);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'updateServerUnit success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'updateServerUnit error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String CreateServerUnit(String CreateServerUnitString) {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());
		try {
			// 将字符串转换成json对象
			JSONObject jsonObject1 = new JSONObject().fromObject(CreateServerUnitString);
			//获得服务编码和所创建的服务单元的名称
			String serverCode = jsonObject1.getString("ServerCode");
			String serverUnitName = jsonObject1.getString("ServerUnitName");
			//获得服务单元编码
			String serverUnitCode = serverUnitDao.AutoGetCode("02");
			//将数据插入到s1100000表中
			S1100000 s1100000 = new S1100000();
			s1100000.setF1(serverCode);//服务编码
			s1100000.setF2(serverUnitCode);//服务单元编码
			s1100000.setF3(serverUnitName);//服务单元名称
			s1100000.setF9(date);//创建时间
			s1100000.setF10("1");//是否有效
			serverUnitDao.insertS1100000(s1100000);
			
			//将数据插入到s0000002表中
			S0000002 s0000002 = new S0000002();
			s0000002.setF1(serverCode);//服务编号
			s0000002.setF1(serverUnitCode);//节点编码
			s0000002.setF1(serverUnitName);//节点名称
			s0000002.setF1("1100000");//对象类别
			s0000002.setF1("1");//对象状态
			s0000002.setF1(serverCode);//父节点
			serverUnitDao.insertS0000002(s0000002);
			
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'CreateServerUnit success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'CreateServerUnit error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}
}
