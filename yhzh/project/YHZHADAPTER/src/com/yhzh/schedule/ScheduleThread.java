package com.yhzh.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.pub.business.PointControl;
import com.yhzh.schedule.bean.SchPointRelBean;
import com.yhzh.schedule.dao.ScheduleDao;
import com.yhzh.zhyq.dao.YhzhDao;

/**日程线程
 * @author samsun 2018-1-27
 *
 */
public class ScheduleThread extends Thread{
	
	public Log LOG = LogFactory.getLog(ScheduleThread.class); 
	
	@Resource 
	private ScheduleDao scheduleDao;
	@Resource 
	private YhzhDao yhzhDao;
	@Resource 
	private PointControl pointControl;
	
	private Boolean running = true;
	
	//日程编号
	private String scheduleid;
	//日程名称
	private String schedulename;
	//是否有效 0否 1是
	private String enabled;
	//是否反向
	private String reverse;	
	//触发/全程控制 0一次，1全程控制
	private String controlmode;
	//轮询间隔(单位为妙)
	private int interval;
	//该日程对应的输出点列表
	private List<SchPointRelBean> pointList;
	
	private int cnt = 1;// 循环计数器
	//触发控制 控制轮询次数设置：对于触发控制 才用到，即controlmode=0
	private int C_conTimes = 3;
	//触发控制 控制开的已轮询次数，关的时候要重置为0
	private int v_openTimes = 0;
	//触发控制 控制关的已轮询次数，开的时候要重置为0
	private int v_closeTimes = 0;
	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	public  ScheduleThread(ScheduleDao scheduleDao,YhzhDao yhzhDao,PointControl pointControl){
		this.scheduleDao=scheduleDao;
		this.yhzhDao =yhzhDao;
		this.pointControl=pointControl;
	}
	
	/**
	 * 重写run()方法
	 */
	public void run(){
		try {
			while(running){
				if(this.cnt % interval == 0){ //轮询
					try {
						if("1".equals(enabled)){ //日程有效
							//判断当前时间是否在开的时间范围内
							Date now = new Date();
						LOG.debug("scheduleDao.isopen(now, scheduleid)"+scheduleDao.isopen(now, scheduleid)+"scheduleid"+scheduleid);
							if(scheduleDao.isopen(now, scheduleid).equals("1")){
							if("0".equals(controlmode)){ //单次控制
									v_closeTimes = 0; //关次数复位
								}
								if("1".equals(controlmode) || v_openTimes < C_conTimes){ //全程日程控制，或者单次控制但开轮询次数小于设定次数
									//当前时间在开的时间范围,轮询所有点
									for(SchPointRelBean schPoint: pointList){
										if("0".equals(schPoint.getControlmode())){//0为日程控制
											//获取状态点值
											
											String inPointValue = yhzhDao.getpointValuebypointid(schPoint.getOutpointid());
											LOG.debug("查看是否开启schPoint.getOutpointid()"+schPoint.getOutpointid()+"inPointValue"+inPointValue);
											if("0".equals(inPointValue)){
												//状态点为0，则写入1
												pointControl.writePoint(schPoint.getOutpointid(), "1");
//保存日程表日志，暂时注释掉。待完善 
												
											scheduleDao.insYhzhScheduleLog(sdf.format(new Date()) , scheduleid, schPoint.getScheduleid(),
														schPoint.getOutpointid(), "1", schPoint.getInpointid(), schPoint.getValue(), "null");
											}
										}
										Thread.sleep(100);
									}
									if("0".equals(controlmode)){ //单次控制
										v_openTimes++;
									}
								}
							}else if(scheduleDao.isopen(now, scheduleid).equals("0")){
								//当前时间在关的时间范围
								if("0".equals(controlmode)){ //单次控制
									v_openTimes = 0;//开次数复位
								}
								if("1".equals(controlmode) || v_closeTimes < C_conTimes){ //全程日程控制，或者单次控制但关轮询次数小于设定次数
									//当前时间在关的时间范围,轮询所有点
									for(SchPointRelBean schPoint: pointList){
										//获取状态点值
										String inPointValue = yhzhDao.getpointValuebypointid(schPoint.getOutpointid());
										LOG.debug("查看是否关闭schPoint.getOutpointid()"+schPoint.getOutpointid()+"inPointValue"+inPointValue);
										if("1".equals(inPointValue)){
											//状态点为1，则写入0
											
											pointControl.writePoint(schPoint.getOutpointid(), "0");
											scheduleDao.insYhzhScheduleLog(sdf.format(new Date()) , scheduleid, schPoint.getScheduleid(),
													schPoint.getOutpointid(), "0", schPoint.getInpointid(), schPoint.getValue(), "null");
										}
										Thread.sleep(100);
									}
									if("0".equals(controlmode)){ //单次控制
										v_closeTimes++;
									}
								}
							}
						}
					} catch (Exception e) {
						LOG.error("日程线程[scheduleid=" + scheduleid + "]报错：" + e.getMessage() + "，请检查。");
						e.printStackTrace();
					}
				}
				this.cnt++;
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			LOG.error("日程线程[scheduleid=" + scheduleid + "]报错：" + e.getMessage() + "，该日程控制停止，请联系管理员。");
			e.printStackTrace();
		}
	}

	/**根据输出点编号返回日程和点关联 bean
	 * @param pointId
	 * @return
	 */
	public SchPointRelBean getSchPointRelBean(String pointId){
		SchPointRelBean schPointRelBean = null;
		for(SchPointRelBean schPoint: pointList){
			if(pointId.equals(schPoint.getOutpointid())){
				schPointRelBean = schPoint;
				break;
			}
		}
		return schPointRelBean;
	}

	public String getScheduleid() {
		return scheduleid;
	}


	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}


	public String getSchedulename() {
		return schedulename;
	}


	public void setSchedulename(String schedulename) {
		this.schedulename = schedulename;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public String getReverse() {
		return reverse;
	}


	public void setReverse(String reverse) {
		this.reverse = reverse;
	}


	public String getControlmode() {
		return controlmode;
	}


	public void setControlmode(String controlmode) {
		this.controlmode = controlmode;
	}


	public int getInterval() {
		return interval;
	}


	public void setInterval(int interval) {
		this.interval = interval;
	}


	public Boolean getRunning() {
		return running;
	}


	public void setRunning(Boolean running) {
		this.running = running;
	}


	public List<SchPointRelBean> getPointList() {
		return pointList;
	}


	public void setPointList(List<SchPointRelBean> pointList) {
		this.pointList = pointList;
	}
	
}
