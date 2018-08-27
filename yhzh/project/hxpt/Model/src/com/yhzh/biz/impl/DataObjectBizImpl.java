package com.yhzh.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.yhzh.biz.DataObjectBiz;
import com.yhzh.dao.DataObjectDao;
import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1120000;

import net.sf.json.JSONObject;

public class DataObjectBizImpl implements DataObjectBiz {
	@Resource
	DataObjectDao dataObjectDao;

	@Override
	public String CreateDataObject(String DataObjectString) {
		StringBuffer sb = new StringBuffer();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String date = df.format(new Date());
			// 将字符串转换成json对象
			JSONObject jsonObject1 = new JSONObject().fromObject(DataObjectString);
			S1120000 s1120000 = new S1120000();
			s1120000.setF1(jsonObject1.getString("ServerCode"));
			String F2 = dataObjectDao.AutoGetCode("08");// 查询数据对象编码
			s1120000.setF2(F2);
			s1120000.setF3(jsonObject1.getString("DataObjectName"));
			s1120000.setF4(jsonObject1.getString("DataObjectDesc"));
			s1120000.setF5(date);
			s1120000.setF6("1");
			// 更新到s1120000表中
			dataObjectDao.insertS1120000(s1120000);

			// 更新到s0000002表中
			S0000002 s0000002 = new S0000002();
			s0000002.setF1(jsonObject1.getString("ServerCode"));// 服务编码
			s0000002.setF2(F2);// 节点编码
			s0000002.setF3(jsonObject1.getString("DataObjectName"));// 节点名称
			s0000002.setF4("1120000");// 对象类别
			s0000002.setF5("1");// 对象状态
			s0000002.setF6(jsonObject1.getString("ParentId"));// 父节点
			dataObjectDao.insertS0000002(s0000002);

			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'CreateDataObject success!'").append("}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'CreateDataObject error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}

		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String findS1120000ByDataObjectCode(String DataObjectCode) {
		StringBuffer sb = new StringBuffer();
		try {
			List<Map<String, Object>> findS1120000ByDataObjectCode = dataObjectDao
					.findS1120000ByDataObjectCode(DataObjectCode);
			List<Object> list = new ArrayList<Object>();
			for (Map<String, Object> map : findS1120000ByDataObjectCode) {
				String F1 = (String) map.get("F1");
				String F2 = (String) map.get("F2");
				String F3 = (String) map.get("F3");
				String F4 = (String) map.get("F4");
				Date F5 = (Date) map.get("F5");
				String F6 = (String) map.get("F6");

				String string = "{'F1':'" + F1 + "','F2':'" + F2 + "','F3':'" + F3 + "','F4':'" + F4 + "','F5':'" + F5
						+ "','F6':'" + F6 + "'}";
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
	public String UpdateDataObject(String DataObjectString) {
		StringBuffer sb = new StringBuffer();
		JSONObject jsonObject1 = new JSONObject().fromObject(DataObjectString);
		try {
			// 更新S1120000表
			S1120000 s1120000 = new S1120000();
			s1120000.setF1(jsonObject1.getString("F1"));
			s1120000.setF2(jsonObject1.getString("F2"));
			s1120000.setF3(jsonObject1.getString("F3"));
			s1120000.setF4(jsonObject1.getString("F4"));
			s1120000.setF5(jsonObject1.getString("F5"));
			s1120000.setF6(jsonObject1.getString("F6"));
			dataObjectDao.UpdateDataObject(s1120000);
			// 更新S0000002表
			S0000002 s0000002 = new S0000002();
			s0000002.setF2((String) jsonObject1.get("F2"));// 设置编码
			s0000002.setF3((String) jsonObject1.get("F3"));// 设置名称
			dataObjectDao.updateS0000002(s0000002);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'UpdateDataObject success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'UpdateDataObject error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

	@Override
	public String DeleteDataObject(String DataObjectCode) {
		StringBuffer sb = new StringBuffer();
		try {
			// 先删除子节点
			dataObjectDao.deleteS0000002ByCodeSub(DataObjectCode);
			dataObjectDao.DeleteDataObject(DataObjectCode);
			dataObjectDao.deleteS0000002ByCode(DataObjectCode);
			sb.append("{isSuccess:1,");
			sb.append("resultMessage:").append("'DeleteDataObject success!'").append("}");
		} catch (Exception e) {
			sb.append("{isSuccess:0,");
			sb.append("resultMessage:").append("'DeleteDataObject error!'").append(",");
			sb.append("failMessage:").append('"' + e.toString() + '"').append("}");
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject().fromObject(sb.toString());
		return jsonObject.toString();
	}

}
