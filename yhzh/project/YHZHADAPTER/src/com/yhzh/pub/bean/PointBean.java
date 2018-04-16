package com.yhzh.pub.bean;

import com.serotonin.bacnet4j.type.enumerated.ObjectType;

/**点表的bean
 * @author samsun 2018-1-6
 *  
 */
public class PointBean {
	
	//点编号
	private String pointId;
	//点物理编号
	private String phisId;
	//点名称
	private String pointName;
	//点输入输出属性 （1数据量输入2数据量输出3通用模拟量4模拟量输入5模拟量输出6通用数据量）
	private String ioProperty;
	//点类型(1回路2组3区域4空调)
	private String pointType;
	//点值
	private String pointValue;
	//点参数类型（对应字典表中point.paratype）
	private String paraType;
	//控制器编号
	private String controllerid;
	//点实例号
	private int instanceNumber;
	//是否订阅
	private String issubs;
	//订阅变化范围(对模拟量点)，不采用BigDecimal类型因为后面不好处理
	private String increment;
	//点对象类型（对ba才用到,与ioProperty对应）
	private ObjectType objectType;
	
	public String getPointId() {
		return pointId;
	}
	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	public String getPhisId() {
		return phisId;
	}
	public void setPhisId(String phisId) {
		this.phisId = phisId;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getIoProperty() {
		return ioProperty;
	}
	public void setIoProperty(String ioProperty) {
		this.ioProperty = ioProperty;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public String getPointValue() {
		return pointValue;
	}
	public void setPointValue(String pointValue) {
		this.pointValue = pointValue;
	}
	public String getParaType() {
		return paraType;
	}
	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	public String getControllerid() {
		return controllerid;
	}
	public void setControllerid(String controllerid) {
		this.controllerid = controllerid;
	}
	public int getInstanceNumber() {
		return instanceNumber;
	}
	public void setInstanceNumber(int instanceNumber) {
		this.instanceNumber = instanceNumber;
	}
	public String getIssubs() {
		return issubs;
	}
	public void setIssubs(String issubs) {
		this.issubs = issubs;
	}
	public String getIncrement() {
		return increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	public ObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType() {
		//1数据量输入2数据量输出3通用模拟量4模拟量输入5模拟量输出6通用数据量
		if("1".equals(ioProperty)){
			this.objectType = ObjectType.binaryInput;
		}else if("2".equals(ioProperty)){
			this.objectType = ObjectType.binaryOutput;
		}else if("3".equals(ioProperty)){
			this.objectType = ObjectType.analogValue;
		}else if("4".equals(ioProperty)){
			this.objectType = ObjectType.analogInput;
		}else if("5".equals(ioProperty)){
			this.objectType = ObjectType.analogOutput;
		}else if("6".equals(ioProperty)){
			this.objectType = ObjectType.binaryValue;
		}
		
	}
	
}
