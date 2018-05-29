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
import com.yhzh.pojo.S1110000;

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
			String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','rightClick':'"
					+ rightClick + "'}";
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
	public int insertS0000002(String serviceCode,String serviceUnitCode) {
		int insertS0000002 = 0;
		List<S0000002> S2list = new ArrayList<S0000002>();
		for (int i = 0; i < 6; i++) {
			S0000002 s2 = new S0000002();
			// 根据服务编码设置其他参数
			if (i == 0) {
				s2.setF1(serviceCode);// 服务编码
				s2.setF2(serviceCode);// 节点编码
				s2.setF3("模型服务");// 节点名称
				s2.setF4("1000000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6("000000000000");// 父节点
				S2list.add(s2);
			}
			if (i == 1) {
				s2.setF1(serviceCode);// 服务单元编码
				s2.setF2(serviceUnitCode);// 节点编码
				s2.setF3("服务单元");// 节点名称
				s2.setF4("1100000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);
			}
			if (i == 2) {
				String F2 = serverDao.AutoGetCode("04");
				s2.setF1(serviceCode);// 单元外小程序编码
				s2.setF2(F2);// 节点编码
				s2.setF3("单元外小程序");// 节点名称
				s2.setF4("1200000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);

			}
			if (i == 3) {
				String F2 = serverDao.AutoGetCode("05");
				s2.setF1(serviceCode);// 数据库设置编码
				s2.setF2(F2);// 节点编码
				s2.setF3("数据库设置");// 节点名称
				s2.setF4("1300000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);
			}
			if (i == 4) {
				String F2 = serverDao.AutoGetCode("06");
				s2.setF1(serviceCode);// 集群设置编码
				s2.setF2(F2);// 节点编码
				s2.setF3("集群设置");// 节点名称
				s2.setF4("1400000");// 对象类别
				s2.setF5("1");// 对象状态
				s2.setF6(serviceCode);// 父节点
				S2list.add(s2);
			}
			if (i == 5) {
				String F2 = serverDao.AutoGetCode("07");
				s2.setF1(serviceCode);// 模板编码
				s2.setF2(F2);// 节点编码
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());
		S1000000 s1000000 = new S1000000();
		s1000000.setF10(date);// 创建时间
		s1000000.setF1(serviceCode);// 服务编码
		s1000000.setF2(serviceCode + "服务");// 服务名称
		s1000000.setF4(serviceCode);// 集群名称
		s1000000.setF11("1");// 是否有效
		// 插入数据到s1000000表中
		int insertS1000000 = serverDao.insertS1000000(s1000000);
		return insertS1000000;
	}

	@Override
	public int insertS1100000(String serviceCode, String serviceUnitCode) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());
		S1100000 s1100000 = new S1100000();
		s1100000.setF1(serviceCode);// 服务编码
		s1100000.setF2(serviceUnitCode);// 服务单元编码
		s1100000.setF3(serviceUnitCode + "服务单元");// 服务单元名称
		s1100000.setF9(date);// 创建时间
		s1100000.setF10("1");// 是否有效
		// 插入数据到s1100000表中
		int insertS1100000 = serverDao.insertS1100000(s1100000);
		return insertS1100000;
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

	@Override
	public List<Object> findS1000000ByServerCode(String ServerCode) {
		List<Map<String, Object>> findS1000000BySC = serverDao.findS1000000ByServerCode(ServerCode);
		List<Object> list = new ArrayList<Object>();
		for (Map<String, Object> map : findS1000000BySC) {
			String F1 = (String) map.get("F1");
			String F2 = (String) map.get("F2");
			String F3 = (String) map.get("F3");
			String F4 = (String) map.get("F4");
			String F5 = (String) map.get("F5");
			String F6 = (String) map.get("F6");
			String F7 = (String) map.get("F7");
			String F8 = (String) map.get("F8");
			Object F9 = (Object) map.get("F9");
			Date F10 = (Date) map.get("F10");
			String F11 = (String) map.get("F11");

			String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
					+ "','F6':'" + F6 + "','F7':'" + F7 + "','F8':'" + F8 + "','F9':'" + F9 + "','F10':'" + F10
					+ "','F11':'" + F11 + "'}";
			JSONObject jsonObject = new JSONObject().fromObject(string);
			list.add(jsonObject);
		}
		return list;
	}

	@Override
	public int updateS1000000(String serverString) {
		// 将字符串转换成json对象
		JSONObject jsonObject = new JSONObject().fromObject(serverString);
		System.out.println("转换成的对象：" + jsonObject);
		// 将数据更新到S1000000表中
		S1000000 s1000000 = new S1000000();
		s1000000.setF1((String) jsonObject.get("F1"));
		s1000000.setF2((String) jsonObject.get("F2"));
		s1000000.setF3((String) jsonObject.get("F3"));
		s1000000.setF4((String) jsonObject.get("F4"));
		s1000000.setF5((String) jsonObject.get("F5"));
		s1000000.setF6((String) jsonObject.get("F6"));
		s1000000.setF7((String) jsonObject.get("F7"));
		s1000000.setF8((String) jsonObject.get("F8"));
		s1000000.setF9((String) jsonObject.get("F9"));
		s1000000.setF10((String) jsonObject.get("F10"));
		s1000000.setF11((String) jsonObject.get("F11"));
		int updateS1000000 = serverDao.updateS1000000(s1000000);
		return updateS1000000;
	}

	@Override
	public int updateServerS0000002(String serverString) {
		// 将字符串转换成json对象
		JSONObject jsonObject = new JSONObject().fromObject(serverString);
		// 将数据更新到S0000002表中
		S0000002 s0000002 = new S0000002();
		s0000002.setF2((String) jsonObject.get("F1"));// 设置编码
		s0000002.setF3((String) jsonObject.get("F2"));// 设置名称
		int updateS0000002 = serverDao.updateS0000002(s0000002);
		return updateS0000002;
	}

	@Override
	public List<Object> findS1100000ByServerUnitCode(String ServerUnitCode) {
		List<Map<String, Object>> findS1100000BySUC = serverDao.findS1100000ByServerUnitCode(ServerUnitCode);
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
					+ "','F6':'" + F6 + "','F7':'" + F7 + "','F8':'" + F8 + "','F9':'" + F9 + "','F10':'" + F10 + "'}";
			JSONObject jsonObject = new JSONObject().fromObject(string);
			list.add(jsonObject);
		}
		return list;
	}

	@Override
	public int updateS1100000(String ServerUnitCodeString) {
		// 将字符串转换成json对象
		JSONObject jsonObject = new JSONObject().fromObject(ServerUnitCodeString);
		System.out.println("转换成的对象：" + jsonObject);
		// 将数据更新到S1000000表中
		S1100000 s1100000 = new S1100000();
		s1100000.setF1((String) jsonObject.get("F1"));
		s1100000.setF2((String) jsonObject.get("F2"));
		s1100000.setF3((String) jsonObject.get("F3"));
		s1100000.setF4((String) jsonObject.get("F4"));
		s1100000.setF5((String) jsonObject.get("F5"));
		s1100000.setF6((String) jsonObject.get("F6"));
		s1100000.setF7((String) jsonObject.get("F7"));
		s1100000.setF8((String) jsonObject.get("F8"));
		s1100000.setF9((String) jsonObject.get("F9"));
		s1100000.setF10((String) jsonObject.get("F10"));
		int updateS1100000 = serverDao.updateS1100000(s1100000);
		return updateS1100000;
	}

	@Override
	public int updateServerUnitS0000002(String serverUnitString) {
		// 将字符串转换成json对象
		JSONObject jsonObject = new JSONObject().fromObject(serverUnitString);
		// 将数据更新到S0000002表中
		S0000002 s0000002 = new S0000002();
		s0000002.setF2((String) jsonObject.get("F2"));// 设置编码
		s0000002.setF3((String) jsonObject.get("F3"));// 设置名称
		int updateS0000002 = serverDao.updateS0000002(s0000002);
		return updateS0000002;
	}

	@Override
	public int CreateRTObject(String createRtObjectString) {
		// 得到服务编码和服务单元编码
		// 将字符串转换成json对象
		JSONObject jsonObject = new JSONObject().fromObject(createRtObjectString);
		String serverCode = (String) jsonObject.get("ServerCode");
		String serverUnitCode = (String) jsonObject.get("ServerUnitCode");
		//向S1110000表中插入一条数据
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());
		S1110000 s1110000 = new S1110000();
		s1110000.setF1(serverCode);//服务编码
		s1110000.setF2(serverUnitCode);//服务单元编码
		String F3 =  serverDao.AutoGetCode("03");//查询实时对象编码
		s1110000.setF3(F3);//实时对象编码
		s1110000.setF4("实时对象"+F3);//实时对象名称
		s1110000.setF5(serverUnitCode+"单元下，实时对象编码为"+F3);//实时对象说明
		s1110000.setF6(date);//创建时间
		s1110000.setF7("1");//是否有效
		int createRTObject = serverDao.insertS1110000(s1110000);
		System.out.println("插入S1110000数据库表成功："+createRTObject);
		
		//向S0000002中插入一条数据
		S0000002 s0000002 = new S0000002();
		s0000002.setF1(serverCode);//服务编码
		s0000002.setF2(F3);//节点编码
		s0000002.setF3("实时对象");//节点名称
		s0000002.setF4("1110000");//对象类别
		s0000002.setF5("1");//对象状态
		s0000002.setF6(serverUnitCode);//父节点
		int insertS0000002 = serverDao.insertS0000002(s0000002);
		System.out.println("插入S0000002数据库表成功："+insertS0000002);
		
		return createRTObject;
	}

	@Override
	public String AutoGetCode(String DicCode) {
		String autoGetCode = serverDao.AutoGetCode(DicCode);
		System.out.println("得到自动编码1：" + autoGetCode);
		return autoGetCode;
	}

}
