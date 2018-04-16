package com.yhzh.schedule.bean;

/**日程和点关联 bean
 * @author samsun
 *
 */
public class SchPointRelBean {
	//日程编号
	private String scheduleid;
	//输出点编号
	private String outpointid;
	//输入点编号
	private String inpointid;
	
	//手动/日程控制（全程控制模式启用）0日程1手动
	private String controlmode;
	//启动延时时长（秒）：是怕同时启动电压变化过大会烧掉一些仪器
	private String delaytimes;
	//状态点值
	private String value;
	
	
	public String getScheduleid() {
		return scheduleid;
	}
	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}
	public String getOutpointid() {
		return outpointid;
	}
	public void setOutpointid(String outpointid) {
		this.outpointid = outpointid;
	}
	public String getInpointid() {
		return inpointid;
	}
	public void setInpointid(String inpointid) {
		this.inpointid = inpointid;
	}
	public String getControlmode() {
		return controlmode;
	}
	public void setControlmode(String controlmode) {
		this.controlmode = controlmode;
	}
	public String getDelaytimes() {
		return delaytimes;
	}
	public void setDelaytimes(String delaytimes) {
		this.delaytimes = delaytimes;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
