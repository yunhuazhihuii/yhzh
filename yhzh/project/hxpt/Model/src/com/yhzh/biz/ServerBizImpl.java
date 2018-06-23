package com.yhzh.biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.yhzh.dao.ServerDao;
import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;
import com.yhzh.pojo.S1110000;
import com.yhzh.pojo.S1111000;

import net.sf.json.JSONObject;

public class ServerBizImpl implements ServerBiz {
	@Resource
	ServerDao serverDao;

	@Override
	public String InitPage() {
		StringBuffer sb = new StringBuffer();
		try {
			List<Map<String, Object>> scList = serverDao.getS0000001();
			List<Object> list = new ArrayList<Object>();
			for (Map<String, Object> map : scList) {
				String F1 = (String) map.get("F1");
				String F2 = (String) map.get("F2");
				String F3 = (String) map.get("F3");
				String F4 = (String) map.get("F4");
				String rightClick = "False";
				String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4
						+ "','rightClick':'" + rightClick + "'}";
				JSONObject jsonObject = new JSONObject().fromObject(string);
				list.add(jsonObject);
			}
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'initPage error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String CreateServer() {
		StringBuffer sb = new StringBuffer();
		try {
			String serviceCode = serverDao.AutoGetCode("01");
			String serviceUnitCode = serverDao.AutoGetCode("02");

			// 插入1级和2级菜单到S0000002表中
			List<S0000002> S2list = new ArrayList<S0000002>();
			for (int i = 0; i < 6; i++) {
				S0000002 s2 = new S0000002();
				// 根据服务编码设置其他参数
				if (i == 0) {
					s2.setF1(serviceCode);// 服务编码
					s2.setF2(serviceCode);// 节点编码
					s2.setF3(serviceCode + "服务");// 节点名称
					s2.setF4("1000000");// 对象类别
					s2.setF5("1");// 对象状态
					s2.setF6("000000000000");// 父节点
					S2list.add(s2);
				}
				if (i == 1) {
					s2.setF1(serviceCode);// 服务单元编码
					s2.setF2(serviceUnitCode);// 节点编码
					s2.setF3(serviceUnitCode + "服务单元");// 节点名称
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
				int insertS0000002 = serverDao.insertS0000002(s0000002);
			}

			// S1000000和S1100000各插入一条记录
			// 向S1000000表中 插入一条记录
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

			// 向S1100000表中 插入一条记录
			S1100000 s1100000 = new S1100000();
			s1100000.setF1(serviceCode);// 服务编码
			s1100000.setF2(serviceUnitCode);// 服务单元编码
			s1100000.setF3(serviceUnitCode + "服务单元");// 服务单元名称
			s1100000.setF9(date);// 创建时间
			s1100000.setF10("1");// 是否有效
			// 插入数据到s1100000表中
			int insertS1100000 = serverDao.insertS1100000(s1100000);

			// 将S0000002表中的数据发送给前端
			List<Map<String, Object>> scList = serverDao.getDatas(serviceCode);
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
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'CreateServer error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String getDatasByServiceCode(String ServerCode) {
		StringBuffer sb = new StringBuffer();
		try {
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
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'getDatasByServiceCode error!'}").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String findS1000000ByServerCode(String ServerCode) {
		StringBuffer sb = new StringBuffer();
		try {
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
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");

		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'findS1000000ByServerCode error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String updateS1000000(String serverString) {
		// 将字符串转换成json对象
		JSONObject jsonObject1 = new JSONObject().fromObject(serverString);
		StringBuffer sb = new StringBuffer();
		try {
			// 将数据更新到S1000000表中
			S1000000 s1000000 = new S1000000();
			s1000000.setF1((String) jsonObject1.get("F1"));
			s1000000.setF2((String) jsonObject1.get("F2"));
			s1000000.setF3((String) jsonObject1.get("F3"));
			s1000000.setF4((String) jsonObject1.get("F4"));
			s1000000.setF5((String) jsonObject1.get("F5"));
			s1000000.setF6((String) jsonObject1.get("F6"));
			s1000000.setF7((String) jsonObject1.get("F7"));
			s1000000.setF8((String) jsonObject1.get("F8"));
			s1000000.setF9((String) jsonObject1.get("F9"));
			s1000000.setF10((String) jsonObject1.get("F10"));
			s1000000.setF11((String) jsonObject1.get("F11"));
			// 将数据更新到S0000002表中
			S0000002 s0000002 = new S0000002();
			s0000002.setF2((String) jsonObject1.get("F1"));// 设置编码
			s0000002.setF3((String) jsonObject1.get("F2"));// 设置名称
			System.out.println("222222222");
			serverDao.updateS1000000(s1000000);
			serverDao.updateS0000002(s0000002);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'updateServer success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'updateServer error!'").append("}");
			// sb.append("resultMessage:").append("'updateServer error!'").append(",");
			// sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String findS1100000ByServerUnitCode(String ServerUnitCode) {
		StringBuffer sb = new StringBuffer();
		try {
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
			int updateS1100000 = serverDao.updateS1100000(s1100000);
			int updateS0000002 = serverDao.updateS0000002(s0000002);
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
	public String CreateRTObject(String createRtObjectString) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		try {
			// 得到服务编码和服务单元编码
			// 将字符串转换成json对象
			JSONObject jsonObject = new JSONObject().fromObject(createRtObjectString);
			String serverCode = (String) jsonObject.get("ServerCode");
			String serverUnitCode = (String) jsonObject.get("ServerUnitCode");
			// 向S1110000表中插入一条数据
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String date = df.format(new Date());
			S1110000 s1110000 = new S1110000();
			s1110000.setF1(serverCode);// 服务编码
			s1110000.setF2(serverUnitCode);// 服务单元编码
			String F3 = serverDao.AutoGetCode("03");// 查询实时对象编码
			s1110000.setF3(F3);// 实时对象编码
			s1110000.setF4("实时对象" + F3);// 实时对象名称
			s1110000.setF5(serverUnitCode + "单元下，实时对象编码为" + F3);// 实时对象说明
			s1110000.setF6(date);// 创建时间
			s1110000.setF7("1");// 是否有效

			// 将创建实时对象数据发送给页面
			String string = "{'F1':'" + s1110000.getF1() + "','F2':'" + s1110000.getF2() + "','F3':'" + s1110000.getF3()
					+ "','F4':'" + s1110000.getF4() + "','F5':'" + s1110000.getF5() + "','F6':'" + s1110000.getF6()
					+ "','F7':'" + s1110000.getF7() + "'}";
			JSONObject jsonObject1 = new JSONObject().fromObject(string);
			list.add(jsonObject1);
			int createRTObject = serverDao.insertS1110000(s1110000);
			System.out.println("插入S1110000数据库表成功：" + createRTObject);

			// 向S0000002中插入一条数据
			S0000002 s0000002 = new S0000002();
			s0000002.setF1(serverCode);// 服务编码
			s0000002.setF2(F3);// 节点编码
			s0000002.setF3("实时对象");// 节点名称
			s0000002.setF4("1110000");// 对象类别
			s0000002.setF5("1");// 对象状态
			s0000002.setF6(serverUnitCode);// 父节点
			int insertS0000002 = serverDao.insertS0000002(s0000002);
			System.out.println("插入S0000002数据库表成功：" + insertS0000002);
			sb.append("{isSuccess:1,");
			// sb.append("resultMessage:").append("'createRTObject success!'").append("}");
			sb.append("resultMessage:").append(list).append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'createRTObject error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String findS1110000ByRtObjectCode(String RtObjectCode) {
		StringBuffer sb = new StringBuffer();
		try {
			List<Map<String, Object>> findS1110000ByRtOC = serverDao.findS1110000ByRtObjectCode(RtObjectCode);
			List<Object> list = new ArrayList<Object>();
			for (Map<String, Object> map : findS1110000ByRtOC) {
				String F1 = (String) map.get("F1");
				String F2 = (String) map.get("F2");
				String F3 = (String) map.get("F3");
				String F4 = (String) map.get("F4");
				String F5 = (String) map.get("F5");
				Date F6 = (Date) map.get("F6");
				String F7 = (String) map.get("F7");
				String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
						+ "','F6':'" + F6 + "','F7':'" + F7 + "'}";
				JSONObject jsonObject = new JSONObject().fromObject(string);
				list.add(jsonObject);
			}
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'findS1110000ByRtObjectCode error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String UpdateRtObject(String RtObjectString) {
		// 将字符串转换成json对象
		JSONObject jsonObject1 = new JSONObject().fromObject(RtObjectString);
		// 将数据更新到S1110000表中
		S1110000 s1110000 = new S1110000();
		s1110000.setF1((String) jsonObject1.get("F1"));
		s1110000.setF2((String) jsonObject1.get("F2"));
		s1110000.setF3((String) jsonObject1.get("F3"));
		s1110000.setF4((String) jsonObject1.get("F4"));
		s1110000.setF5((String) jsonObject1.get("F5"));
		s1110000.setF6((String) jsonObject1.get("F6"));
		s1110000.setF7((String) jsonObject1.get("F7"));

		// 将修改的实时对象
		S0000002 s0000002 = new S0000002();
		s0000002.setF2((String) jsonObject1.get("F3"));// 设置编码
		s0000002.setF3((String) jsonObject1.get("F4"));// 设置名称
		StringBuffer sb = new StringBuffer();
		try {
			serverDao.updateS1110000(s1110000);
			serverDao.updateS0000002(s0000002);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'UpdateRtObject success!'").append("}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			sb.append("{isSuccess:0,");
			// sb.append("failMessage:").append("'UpdateRtObject error!'").append("}");
			sb.append("resultMessage:").append("'UpdateRtObject error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String DeleteRtObject(String RtObjectCode) {
		StringBuffer sb = new StringBuffer();
		try {
			// 删除目录下的实时对象属性
			int deleteS0000002ByCodeSub = serverDao.deleteS0000002ByCodeSub(RtObjectCode);
			// 删除表S0000002中的记录，包括实时对象及目录
			int deleteS0000002ByCode = serverDao.deleteS0000002ByCode(RtObjectCode);

			System.out.println("S0000002表中删除实时对象目录属性记录成功！" + deleteS0000002ByCodeSub);
			System.out.println("S0000002表中删除实时对象记录成功！" + deleteS0000002ByCode);

			// 删除表S1110000中的记录
			int deleteS1110000ByRtCode = serverDao.deleteS1110000ByRtCode(RtObjectCode);
			System.out.println("S1110000表中删除实时对象记录成功！" + deleteS1110000ByRtCode);

			// 删除表S1111000中的记录
			int deleteS1111000ByRtCode = serverDao.deleteS1111000ByRtCode(RtObjectCode);
			System.out.println("S1111000表中删除实时对象记录成功！" + deleteS1111000ByRtCode);

			// 删除表S1111110中的记录

			// 删除表S1111120中的记录

			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'DeleteRtObject success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'DeleteRtObject error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String RTOAttrCatalog(String RTOAttrCatalogString) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		// 将传送过来的数据插入S0000002表中
		// 将字符串转换成json对象
		JSONObject jsonObject1 = new JSONObject().fromObject(RTOAttrCatalogString);
		S0000002 s0000002 = new S0000002();
		s0000002.setF1(jsonObject1.getString("ServerCode"));
		s0000002.setF2(jsonObject1.getString("NodeCode"));
		s0000002.setF3(jsonObject1.getString("NodeName"));
		s0000002.setF4(jsonObject1.getString("CategoryCode"));
		s0000002.setF5(jsonObject1.getString("ObjectStatus"));
		s0000002.setF6(jsonObject1.getString("ParentNode"));
		String string = "{'F1':'" + s0000002.getF1() + "','F2':'" + s0000002.getF2() + "','F3':'" + s0000002.getF3()
				+ "','F4':'" + s0000002.getF4() + "','F5':'" + s0000002.getF5() + "','F6':'" + s0000002.getF6() + "'}";
		JSONObject jsonObject2 = new JSONObject().fromObject(string);
		list.add(jsonObject2);
		try {
			serverDao.insertS0000002(s0000002);
			sb.append("{isSuccess:1,");
			// sb.append("resultMessage:").append("'CreateRTOAttrCatalog
			// success!'").append("}");
			sb.append("resultMessage:").append(list).append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'CreateRTOAttrCatalog error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String CreateRTOAttr(String RTOAttrString) {
		StringBuffer sb = new StringBuffer();
		try {
			JSONObject jsonObject1 = new JSONObject().fromObject(RTOAttrString);
			// 向S1110000表中插入一条数据
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String date = df.format(new Date());
			S1111000 s1111000 = new S1111000();
			s1111000.setF1(jsonObject1.getString("ServerCode"));// 服务编码
			s1111000.setF2(jsonObject1.getString("RTOCode"));// 实时对象编码
			// 得到分类编码
			String dotCategory = jsonObject1.getString("DotCategory");
			int DicCode = Integer.parseInt(dotCategory) + 12;
			// 根据点分类查询
			String F3 = serverDao.AutoGetCode(DicCode + "");
			s1111000.setF3(F3);// 实时对象属性编码
			s1111000.setF4(jsonObject1.getString("AttrName"));// 属性名称
			s1111000.setF5(jsonObject1.getString("AttrDesc"));// 属性描述
			s1111000.setF6(jsonObject1.getString("DotCategory"));// 点分类
			s1111000.setF7("1");// 数据变化有效定义类别
			s1111000.setF8("");// 数据变化有效定义
			s1111000.setF9("");// 接口映射点编码
			s1111000.setF10("");// 接口映射点名称
			s1111000.setF11(date);// 创建时间
			s1111000.setF12(date);// 值改变时间
			s1111000.setF13("1");// 是否有效
			serverDao.insertS1111000(s1111000);

			// 向S0000002中插入一条数据
			S0000002 s0000002 = new S0000002();
			s0000002.setF1(jsonObject1.getString("ServerCode"));// 服务编码
			s0000002.setF2(F3);// 节点编码
			s0000002.setF3(jsonObject1.getString("AttrName"));// 节点名称
			s0000002.setF4("1111100");// 对象类别
			s0000002.setF5("1");// 对象状态
			s0000002.setF6(jsonObject1.getString("ParentId"));// 父节点

			serverDao.insertS0000002(s0000002);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'CreateRTOAttr success!'").append("}");
			// sb.append("resultMessage:").append(list).append("}");

		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'CreateRTOAttr error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String DeleteRTOAttrCatalog(String RTOAttrCatalogCode) {
		StringBuffer sb = new StringBuffer();
		try {
			String RtObjectCode = RTOAttrCatalogCode.substring(0, 15);
			String DotCategory = RTOAttrCatalogCode.substring(15);
			serverDao.deleteS0000002ByCode(RTOAttrCatalogCode);
			serverDao.DeleteRTOAttr(RtObjectCode, DotCategory);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'DeleteRTOAttrCatalog success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'DeleteRTOAttrCatalog error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String findS1111000ByRTOAttrCode(String RTOAttrCode) {
		StringBuffer sb = new StringBuffer();
		try {
			List<Map<String, Object>> findS1111000ByRTOAC = serverDao.findS1111000ByRTOAttrCode(RTOAttrCode);
			List<Object> list = new ArrayList<Object>();
			for (Map<String, Object> map : findS1111000ByRTOAC) {
				String F1 = (String) map.get("F1");
				String F2 = (String) map.get("F2");
				String F3 = (String) map.get("F3");
				String F4 = (String) map.get("F4");
				String F5 = (String) map.get("F5");
				String F6 = (String) map.get("F6");
				String F7 = (String) map.get("F7");
				String F8 = (String) map.get("F8");
				String F9 = (String) map.get("F9");
				String F10 = (String) map.get("F10");
				Date F11 = (Date) map.get("F11");
				Date F12 = (Date) map.get("F12");
				String F13 = (String) map.get("F13");

				String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
						+ "','F6':'" + F6 + "','F7':'" + F7 + "','F8':'" + F8 + "','F9':'" + F9 + "','F10':'" + F10
						+ "','F11':'" + F11 + "','F12':'" + F12 + "','F13':'" + F13 + "'}";
				JSONObject jsonObject = new JSONObject().fromObject(string);
				list.add(jsonObject);
			}
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");

		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'findS1000000ByServerCode error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String UpdateRTOAttr(String RTOAttrString) {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());
		// 将字符串转换成json对象
		JSONObject jsonObject1 = new JSONObject().fromObject(RTOAttrString);
		// 将数据更新到S1111000表中
		S1111000 s1111000 = new S1111000();
		s1111000.setF1((String) jsonObject1.get("F1"));
		s1111000.setF2((String) jsonObject1.get("F2"));
		s1111000.setF3((String) jsonObject1.get("F3"));
		s1111000.setF4((String) jsonObject1.get("F4"));
		s1111000.setF5((String) jsonObject1.get("F5"));
		s1111000.setF6((String) jsonObject1.get("F6"));
		s1111000.setF7((String) jsonObject1.get("F7"));
		s1111000.setF8((String) jsonObject1.get("F8"));
		s1111000.setF9((String) jsonObject1.get("F9"));
		s1111000.setF10((String) jsonObject1.get("F10"));
		s1111000.setF11((String) jsonObject1.get("F11"));
		s1111000.setF12((String) jsonObject1.get("F12"));
		s1111000.setF13((String) jsonObject1.get("F13"));

		// 将修改的实时对象
		S0000002 s0000002 = new S0000002();
		s0000002.setF2((String) jsonObject1.get("F3"));// 设置编码
		s0000002.setF3((String) jsonObject1.get("F4"));// 设置名称
	
		try {
			serverDao.updateS1111000(s1111000);
			serverDao.updateS0000002(s0000002);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'UpdateRTOAttr success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			// sb.append("failMessage:").append("'UpdateRTOAttr error!'").append("}");
			sb.append("resultMessage:").append("'UpdateRTOAttr error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String DeleteRTOAttr(String RTOAttrCode) {
		StringBuffer sb = new StringBuffer();
		try {
			serverDao.deleteS0000002ByCode(RTOAttrCode);
			serverDao.DeleteRTOAttr(RTOAttrCode);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'DeleteRTOAttr success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'DeleteRTOAttr error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

}
