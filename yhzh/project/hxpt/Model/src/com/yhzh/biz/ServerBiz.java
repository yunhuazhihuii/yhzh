package com.yhzh.biz;

import java.util.List;
import java.util.Map;

import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;

public interface ServerBiz {
	/**
	 * 初始化页面
	 * 查询S0000001表格中的所有数据
	 */
	public String InitPage();
	/**
	 * 创建服务
	 * @return
	 */
	public String CreateServer();

	/**
	 * 根据服务编码，从数据库中查询 出数据给页面
	 * @param ServerCode
	 * @return
	 */
	public String getDatasByServiceCode(String ServerCode);
	/**
	 * 根据服务编码从S1000000表中查询数据
	 * @param ServerCode
	 * @return
	 */
	public String findS1000000ByServerCode(String ServerCode);
	
	/**
	 * 更新数据到s1000000表中
	 * @param serverString
	 * @return
	 */
	public String updateS1000000(String serverString);

	/**
	 * 根据服务单元编码向S1100000数据库中查询数据
	 * @param ServerUnitCodeString
	 * @return
	 */
	public String findS1100000ByServerUnitCode(String ServerUnitCode);
	/**
	 * 更新数据到S1100000表中
	 * @param ServerUnitCodeString
	 * @return
	 */
	public String updateS1100000(String ServerUnitCodeString);

	/**
	 * 创建实时对象
	 * @param createRtObjectString
	 * @return
	 */
	public String CreateRTObject(String createRtObjectString);
	
	/**
	 * 根据实时对象编码查询实时对象
	 * @param RtObjectCode
	 * @return
	 */
	public String findS1110000ByRtObjectCode(String RtObjectCode);
	
	/**
	 * 更新实时对象
	 * @param RtObjectString
	 * @return
	 */
	public String UpdateRtObject(String RtObjectString);
	/**
	 * 删除实时对象
	 * @param RtObjectCode
	 * @return
	 */
	public String DeleteRtObject(String RtObjectCode);
	/**
	 * 创建实时对象属性目录
	 * @param RTOAttrString
	 * @return
	 */
	public String RTOAttrCatalog(String RTOAttrCatalogString);
	/**
	 * 创建实施对象属性
	 * @param RTOAttrString
	 * @return
	 */
	public String CreateRTOAttr(String RTOAttrString);
	/**
	 * 删除实时对象目录
	 * @param RTOAttrCatalogCode
	 * @return
	 */
	public String DeleteRTOAttrCatalog(String RTOAttrCatalogCode);
	/**
	 * 编辑实时对象属性
	 * @param RTOAttrCode
	 * @return
	 */
	public String findS1111000ByRTOAttrCode(String RTOAttrCode);
	/**
	 * 更改实时对象属性
	 * @param RTOAttrString
	 * @return
	 */
	public String UpdateRTOAttr(String RTOAttrString);
	/**
	 * 删除实时对象属性
	 * @param RTOAttrCode
	 * @return
	 */
	public String DeleteRTOAttr(String RTOAttrCode);
}
