package com.yhzh.biz;

public interface DataObjectBiz {
	/**
	 * 创建数据对象
	 * @param DataObjectString
	 * @return
	 */
	public String CreateDataObject(String DataObjectString);
	/**
	 * 修改数据对象
	 * @param DataObjectString
	 * @return
	 */
	public String findS1120000ByDataObjectCode(String DataObjectCode);
	/**
	 * 更新数据对象
	 * @param DataObjectString
	 * @return
	 */
	public String UpdateDataObject(String DataObjectString);
	/**
	 *删除数据对象
	 * @param DataObjectString
	 * @return
	 */
	public String DeleteDataObject(String DataObjectCode);
	
	
}
