package com.yhzh.biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yhzh.dao.ServerDao;
import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;

import net.sf.json.JSONObject;

public class ServerBizImpl implements ServerBiz {
	@Resource
	ServerDao serverDao;
	
	@Override
	public List<Object> getS0000001() {
		List<Map<String, Object>> scList = serverDao.getS0000001();
		List<Object> list = new ArrayList<Object>();
		for (Map<String, Object> map : scList) {
			String F1 = (String) map.get("F1");
			String F2 = (String) map.get("F2");
			String F3 = (String) map.get("F3");
			String F4 = (String) map.get("F4");
			String rightClick = "False";
			String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','rightClick':'" + rightClick + "'}";
			JSONObject jsonObject = new JSONObject().fromObject(string);
			list.add(jsonObject);
		}
		return list;
	}

	@Override
	public List<Object> getS0000002() {
		List<Map<String, Object>> scList = serverDao.getDatas();
		List<Object> list = new ArrayList<Object>();
		for (Map<String, Object> map : scList) {
			String F1 = (String) map.get("F1");
			String F2 = (String) map.get("F2");
			String F3 = (String) map.get("F3");
			String F4 = (String) map.get("F4");
			String F5 = (String) map.get("F5");
			String F6 = (String) map.get("F6");
			String rightClick = "False";
			String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
					+ "','F6':'" + F6 + "','rightClick':'" + rightClick + "'}";
			JSONObject jsonObject = new JSONObject().fromObject(string);
			list.add(jsonObject);
		}
		return list;
	}

	@Override
	public Map<String, String> getServiceCodeAndUnitCode() {
		List<Map<String, Object>> Diclist = serverDao.getServiceCode();
		Map<String, String> mapString = new HashMap<String,String>();
		String serviceCode = null;
		String serviceUnitCode = null;
		for (Map<String, Object> map : Diclist) {
			if (map.get("F5").equals("服务编码")) {
				String serviceCode1 = (String) map.get("F4");
				String str1 = serviceCode1.substring(0, 11);
				String str2 = serviceCode1.substring(11);
				int i = (Integer) Integer.parseInt(str2) + 1;
				System.out.println("服务编码为：" + serviceCode1);
				serviceCode = str1 + i;
				mapString.put("serviceCode", serviceCode);
				System.out.println("服务编码+1为：" + serviceCode);
			}
			if (map.get("F5").equals("服务单元编码")) {
				String serviceUnitCode1 = (String) map.get("F4");
				String str1 = serviceUnitCode1.substring(0, 11);
				String str2 = serviceUnitCode1.substring(11);
				int i = (Integer) Integer.parseInt(str2) + 1;
				System.out.println("服务单元编码为：" + serviceUnitCode1);
				serviceUnitCode = str1 + i;
				mapString.put("serviceUnitCode", serviceUnitCode);
				System.out.println("服务单元编码+1为：" + serviceUnitCode);
			}
		}
		return mapString;
	}

	@Override
	public int updateServiceCode(String serviceCode) {
		int updateServiceCode = serverDao.updateServiceCode(serviceCode);
		return updateServiceCode;
	}

	@Override
	public int updateServiceUnitCode(String serviceUnitCode) {
		int updateServiceUnitCode = serverDao.updateServiceUnitCode(serviceUnitCode);
		return updateServiceUnitCode;
	}

	@Override
	public int insertS0000002(String serviceCode) {
		int insertS0000002 = 0;
		List<S0000002> S2list = new ArrayList<S0000002>();
		System.out.println("服务编码:" + serviceCode);
		// 截取服务编码变化的末位
		String str1 = serviceCode.substring(0, 1);
		String str2 = serviceCode.substring(1, 2);
		String str3 = serviceCode.substring(2);
		System.out.println("截取" + str1);
		System.out.println("截取" + str2);
		System.out.println("截取" + str3);
		// 变化的数据
		for (int i = 0; i < 6; i++) {
			S0000002 s2 = new S0000002();
			// 根据服务编码设置其他参数
			if (i == 0) {
				s2.setF1(serviceCode);// 服务编码
				s2.setF2(str1 + i + str3);// 节点编码
				s2.setF3("模型服务");// 节点名称
				s2.setF4("1000000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6("000000000000");// 父节点
				S2list.add(s2);
			}
			if (i == 1) {
				s2.setF1(serviceCode);// 服务编码
				s2.setF2(str1 + i + str3);// 节点编码
				s2.setF3("服务单元");// 节点名称
				s2.setF4("1100000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);
			}
			if (i == 2) {
				s2.setF1(serviceCode);// 服务编码
				s2.setF2(str1 + i + str3);// 节点编码
				s2.setF3("单元外小程序");// 节点名称
				s2.setF4("1200000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);

			}
			if (i == 3) {
				s2.setF1(serviceCode);// 服务编码
				s2.setF2(str1 + i + str3);// 节点编码
				s2.setF3("数据库设置");// 节点名称
				s2.setF4("1300000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);
			}
			if (i == 4) {
				s2.setF1(serviceCode);// 服务编码
				s2.setF2(str1 + i + str3);// 节点编码
				s2.setF3("集群设置");// 节点名称
				s2.setF4("1400000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);
			}
			if (i == 5) {
				s2.setF1(serviceCode);// 服务编码
				s2.setF2(str1 + i + str3);// 节点编码
				s2.setF3("模板");// 节点名称
				s2.setF4("1500000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);
			}
		}
		// 遍历集合
		for (S0000002 s0000002 : S2list) {
			System.out.println("输出集合数据" + s0000002);
			insertS0000002 = serverDao.insertS0000002(s0000002) + insertS0000002;
			System.out.println("插入数据成功返回的数据：" + insertS0000002);
		}
		return insertS0000002;
	}

	@Override
	public int insertS1000000(String serviceCode) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		S1000000 s1000000 = new S1000000();
		s1000000.setF10(date);//创建时间
		s1000000.setF1(serviceCode);//服务编码
		s1000000.setF2(serviceCode+"服务");//服务名称
		s1000000.setF4(serviceCode);//集群名称
		s1000000.setF11("1");//是否有效
		//插入数据到s1000000表中
		int insertS1000000 = serverDao.insertS1000000(s1000000);
		return insertS1000000;
	}

	@Override
	public int insertS1100000(String serviceCode,String serviceUnitCode) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		S1100000 s1100000 = new S1100000();
		s1100000.setF1(serviceCode);//服务编码
		s1100000.setF2(serviceUnitCode);//服务单元编码
		s1100000.setF3(serviceUnitCode+"服务单元");//服务单元名称
		s1100000.setF9(date);//创建时间
		s1100000.setF10("1");//是否有效
		//插入数据到s1100000表中
		int insertS1100000 = serverDao.insertS1100000(s1100000);
		return 0;
	}

	@Override
	public List<Object> getDatasByServiceCode(String ServerCode) {
		List<Map<String, Object>> serviceDatas = serverDao.getDatasByServiceCode(ServerCode);
		List<Object> list = new ArrayList<Object>();
		for (Map<String, Object> map : serviceDatas) {
			String F1 = (String) map.get("F1");
			String F2 = (String) map.get("F2");
			String F3 = (String) map.get("F3");
			String F4 = (String) map.get("F4");
			String F5 = (String) map.get("F5");
			String F6 = (String) map.get("F6");
			String rightClick = "False";
			String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
					+ "','F6':'" + F6 + "','rightClick':'" + rightClick + "'}";
			JSONObject jsonObject = new JSONObject().fromObject(string);
			list.add(jsonObject);
		}
		return list;
	}
}
