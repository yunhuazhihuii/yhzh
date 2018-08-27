package com.yhzh.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.yhzh.biz.RTObjectBiz;
import com.yhzh.dao.RTObjectDao;
import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1110000;
import com.yhzh.pojo.S1111000;

import net.sf.json.JSONObject;

public class RTObjectBizImpl implements RTObjectBiz {
	@Resource
	RTObjectDao rTObjectDao;

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
			String F3 = rTObjectDao.AutoGetCode("03");// 查询实时对象编码
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
			int createRTObject = rTObjectDao.insertS1110000(s1110000);
			System.out.println("插入S1110000数据库表成功：" + createRTObject);

			// 向S0000002中插入一条数据
			S0000002 s0000002 = new S0000002();
			s0000002.setF1(serverCode);// 服务编码
			s0000002.setF2(F3);// 节点编码
			s0000002.setF3("实时对象");// 节点名称
			s0000002.setF4("1110000");// 对象类别
			s0000002.setF5("1");// 对象状态
			s0000002.setF6(serverUnitCode);// 父节点
			int insertS0000002 = rTObjectDao.insertS0000002(s0000002);
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
			List<Map<String, Object>> findS1110000ByRtOC = rTObjectDao.findS1110000ByRtObjectCode(RtObjectCode);
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
			rTObjectDao.updateS1110000(s1110000);
			rTObjectDao.updateS0000002(s0000002);
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
			int deleteS0000002ByCodeSub = rTObjectDao.deleteS0000002ByCodeSub(RtObjectCode);
			// 删除表S0000002中的记录，包括实时对象及目录
			int deleteS0000002ByCode = rTObjectDao.deleteS0000002ByCode(RtObjectCode);

			System.out.println("S0000002表中删除实时对象目录属性记录成功！" + deleteS0000002ByCodeSub);
			System.out.println("S0000002表中删除实时对象记录成功！" + deleteS0000002ByCode);

			// 删除表S1110000中的记录
			int deleteS1110000ByRtCode = rTObjectDao.deleteS1110000ByRtCode(RtObjectCode);
			System.out.println("S1110000表中删除实时对象记录成功！" + deleteS1110000ByRtCode);

			// 删除表S1111000中的记录
			int deleteS1111000ByRtCode = rTObjectDao.deleteS1111000ByRtCode(RtObjectCode);
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
}
