package com.yhzh.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yhzh.biz.RTObjectAttrBiz;
import com.yhzh.biz.RTObjectBiz;
import com.yhzh.dao.RTObjectAttrDao;
import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1110000;
import com.yhzh.pojo.S1111000;

import net.sf.json.JSONObject;

public class RTObjectAttrBizImpl implements RTObjectAttrBiz {
	@Resource
	RTObjectAttrDao rTObjectAttrDao;

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
			rTObjectAttrDao.insertS0000002(s0000002);
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
			String F3 = rTObjectAttrDao.AutoGetCode(DicCode + "");
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
			rTObjectAttrDao.insertS1111000(s1111000);

			// 向S0000002中插入一条数据
			S0000002 s0000002 = new S0000002();
			s0000002.setF1(jsonObject1.getString("ServerCode"));// 服务编码
			s0000002.setF2(F3);// 节点编码
			s0000002.setF3(jsonObject1.getString("AttrName"));// 节点名称
			s0000002.setF4("1111100");// 对象类别
			s0000002.setF5("1");// 对象状态
			s0000002.setF6(jsonObject1.getString("ParentId"));// 父节点

			rTObjectAttrDao.insertS0000002(s0000002);
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
			rTObjectAttrDao.deleteS0000002ByCode(RTOAttrCatalogCode);
			rTObjectAttrDao.DeleteRTOAttr(RtObjectCode, DotCategory);
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
			List<Map<String, Object>> findS1111000ByRTOAC = rTObjectAttrDao.findS1111000ByRTOAttrCode(RTOAttrCode);
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
			rTObjectAttrDao.updateS1111000(s1111000);
			rTObjectAttrDao.updateS0000002(s0000002);
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
			rTObjectAttrDao.deleteS0000002ByCode(RTOAttrCode);
			rTObjectAttrDao.DeleteRTOAttr(RTOAttrCode);
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
