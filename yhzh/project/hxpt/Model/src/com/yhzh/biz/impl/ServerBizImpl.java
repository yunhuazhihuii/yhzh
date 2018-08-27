package com.yhzh.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.yhzh.biz.ServerBiz;
import com.yhzh.dao.ServerDao;
import com.yhzh.pojo.S0000001;
import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S0000003;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;
import com.yhzh.pojo.S1110000;
import com.yhzh.pojo.S1111000;
import com.yhzh.pojo.S1120000;

import net.sf.json.JSONObject;

public class ServerBizImpl implements ServerBiz {
	@Resource
	ServerDao serverDao;

	@Override
	public String InitPage() {
		StringBuffer sb = new StringBuffer();
		try {
			// 表s0000002中的1、2级数据
			List<Map<String, Object>> scList1 = serverDao.getF1InS0000002();
			List<Object> list1 = new ArrayList<Object>();
			// 遍历服务编码
			for (Map<String, Object> map : scList1) {
				String serverCode = (String) map.get("F1");
				List<Map<String, Object>> scList2 = serverDao.getDatas(serverCode);
				List<Object> list2 = new ArrayList<Object>();
				StringBuffer sb1 = new StringBuffer();
				if (null != scList2 && scList2.size() > 0) {
					// 将对象装换成json字符串格式
					List<Object> list3 = transferJsonString(scList2);
					String string1 = list3.toString().substring(1, list3.toString().length() - 2);
					sb1.append(string1).append(",");
					Map<String, Object> map1 = scList2.get(0);
					String F2 = (String) map1.get("F2");
					List<Map<String, Object>> childrenList = serverDao.getTreeChildren(F2);
					List<Object> list4 = transferJsonString(childrenList);
					String string2 = list4.toString();
					sb1.append("'children':").append(string2).append("}");
					list2.add(sb1.toString());
				}
				list1.add(list2.toString().substring(1, list2.toString().length() - 1));
			}

			// 表s0000001的数据
			 /*List<Map<String, Object>> scList = serverDao.getS0000001(); 
			 List<Object> list = new ArrayList<Object>(); 
			 for (Map<String, Object> map : scList) { 
				 String F1= (String) map.get("F1"); 
				 String F2 = (String) map.get("F2"); 
				 String F3 =(String) map.get("F3"); 
				 String F4 = (String) map.get("F4"); 
				 String rightClick = "False"; 
				 String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','rightClick':'" + rightClick + "'}"; 
				 JSONObject jsonObject = new JSONObject().fromObject(string); 
				 list.add(jsonObject); 
				 }*/
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list1).append("}");
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
	public String ChildrenNode(String parentCode) {
		StringBuffer sb = new StringBuffer();
		try {
			List<Object> list = new ArrayList<Object>();
			// 查找parentCode的第一个0，然后用%号代替
			String findTheFirst0 = FindTheFirst0(parentCode);
			if("1110000".equals(parentCode)) {
				List<Map<String, Object>> findChildNode = serverDao.FindChildNode(parentCode,findTheFirst0);
				for (Map<String, Object> map : findChildNode) {
					String F1 = (String) map.get("F1");
					String F2 = (String) map.get("F2");
					String F3 = (String) map.get("F3");
					String F4 = (String) map.get("F4");
					String F5 = (String) map.get("F5");
					String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5+ "'}";
					JSONObject jsonObject = new JSONObject().fromObject(string);
					list.add(jsonObject);
				}
			}else {
				List<Map<String, Object>> findChildNodeFromS0000003 = serverDao.FindChildNodeFromS0000003(parentCode,findTheFirst0);
				for (Map<String, Object> map : findChildNodeFromS0000003) {
					String F1 = (String) map.get("F1");
					String F2 = (String) map.get("F2");
					String F3 = (String) map.get("F3");
					String F4 = (String) map.get("F4");
					String F5 = (String) map.get("F5");
					String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5+ "'}";
					JSONObject jsonObject = new JSONObject().fromObject(string);
					list.add(jsonObject);	
				}
			}
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append(list).append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'initPage error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	public String FindTheFirst0(String parentCode) {
		char[] arr = parentCode.toCharArray();
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '0') {
				count++;
			}
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i <= 7 - count; i++) {
			sb.append("1");
		}
		sb.append("%");
		for (int i = 1; i < count; i++) {
			sb.append("0");
		}
		return sb.toString();
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
			StringBuffer sb1 = new StringBuffer();
			if (null != scList && scList.size() > 0) {
				// 将对象装换成json字符串格式
				List<Object> list1 = transferJsonString(scList);
				String string1 = list1.toString().substring(1, list1.toString().length() - 2);
				sb1.append(string1).append(",");
				Map<String, Object> map = scList.get(0);
				String F2 = (String) map.get("F2");
				List<Map<String, Object>> childrenList = serverDao.getTreeChildren(F2);
				List<Object> list2 = transferJsonString(childrenList);
				String string2 = list2.toString();
				sb1.append("'children':").append(string2).append("}");
				list.add(sb1.toString());
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

	/**
	 * 将List<Map<String, Object>>对象转成json格式字符串对象
	 * @param List<Map<String, Object>> scList
	 * @return
	 */
	private List<Object> transferJsonString(List<Map<String, Object>> scList) {
		List<Object> list = new ArrayList<>();
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
	
	/**
	 * 将map格式转成字符串
	 * @param Map<String, Object> scList
	 * @return
	 */
	private String transferJsonString(Map<String, Object> scList) {
			String F1 = (String) scList.get("F1");
			String F2 = (String) scList.get("F2");
			String F3 = (String) scList.get("F3");
			String F4 = (String) scList.get("F4");
			String F5 = (String) scList.get("F5");
			String F6 = (String) scList.get("F6");
			String rightClick = "False";
			String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
					+ "','F6':'" + F6 + "','rightClick':'" + rightClick + "'}";
			JSONObject jsonObject = new JSONObject().fromObject(string);
			return jsonObject.toString();
	}

	@Override
	public String OpenServer(String ServerCode) {
		StringBuffer sb1 = new StringBuffer();
		List<Object> listone = new ArrayList<>();
	
		try {
			//获得该服务的json字符串
			//获得一级子节点
			List<Map<String, Object>> parent = serverDao.getDatas(ServerCode);
			List<Object> parentJsonString = transferJsonString(parent);
			String parentString = parentJsonString.toString().substring(1, parentJsonString.toString().length()-2);
			System.out.println("输出答案");
			System.out.println(parentString);//[{"F1":"100000000001","F2":"100000000001","F3":"100000000001服务","F4":"1000000","F5":"1","F6":"000000000000","rightClick":"False"}]
			sb1.append(parentString);
			//获得该服务下的所有子节点
//			List<Map<String, Object>> treeChildren = serverDao.getNextChildNodes(ServerCode);
			List<Map<String, Object>> treeChildren = serverDao.getTreeChildren(ServerCode);
			System.out.println("获得该服务二级子节点");
			System.out.println(treeChildren.toString());
			//第二级
			StringBuffer sb2 = new StringBuffer();
			List<Object> listtwo = new ArrayList<>();
			if(treeChildren != null && treeChildren.size() >0) {
				sb1.append(",").append("children:");
				for (Map<String, Object> map : treeChildren) {
					System.out.println("test");
					System.out.println(map.toString());
					String transferJsonString = transferJsonString(map);
					String parentString2 = transferJsonString.substring(1, transferJsonString.length()-2);
					System.out.println("parentString2");
					System.out.println(parentString2);
					sb2.append(parentString2);
					String secondId = (String) map.get("F2");
					List<Map<String, Object>> treeChildren2 = serverDao.getTreeChildren(secondId);
					//第三级
					StringBuffer sb3 = new StringBuffer();
					List<Object> listthree = new ArrayList<>();
					if(treeChildren2 != null && treeChildren2.size() >0) {
						sb2.append(",").append("children:");
						for (Map<String, Object> map2 : treeChildren2) {
							
						}
					}	
				}
			}
			sb1.append("{isSuccess:1,");
			sb1.append("resultMessage:").append("OK").append("}");
		} catch (Exception e) {
			sb1.append("{isSuccess:0,");
			sb1.append("resultMessage:").append("'getDatasByServiceCode error!'}").append(",");
			sb1.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb1.toString());
		return jsonObject.toString();
	}

	@Override
	public String EditServer(String ServerCode) {
		StringBuffer sb = new StringBuffer();
		try {
			List<Map<String, Object>> findS1000000BySC = serverDao.EditServer(ServerCode);
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
	public String UpdateServer(String serverString) {
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

}
