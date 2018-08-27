package com.yhzh.biz;

public interface RTObjectAttrBiz {

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
