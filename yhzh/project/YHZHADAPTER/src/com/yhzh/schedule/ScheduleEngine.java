package com.yhzh.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yhzh.pub.business.PointControl;
import com.yhzh.schedule.bean.SchPointRelBean;
import com.yhzh.schedule.dao.ScheduleDao;
import com.yhzh.util.YhzhUtil;
import com.yhzh.zhyq.dao.YhzhDao;

/**日程表控制引擎
 * @author samsun 2018-1-27
 *
 */
public class ScheduleEngine implements Runnable {

	public Log LOG = LogFactory.getLog(ScheduleEngine.class); 
	private YhzhUtil yhzhUtil = new YhzhUtil();
	@Resource 
	ScheduleDao scheduleDao;
	@Resource 
	YhzhDao yhzhDao;
	@Resource 
	private PointControl pointControl;

	private int interval = 60; //日程控制引擎轮询间隔秒数
	
	private int cnt = 1;// 循环计数器
	
	private Boolean running = true;
	
	//日程线程列表
	private List<ScheduleThread> scThList;
	
	/**
	 * 日程控制引擎初始化
	 */
	public void initStart(){
		
		scThList = new ArrayList<ScheduleThread>();
		new Thread(this).start();
	}
	
	/* 日程表引擎执行方法
	 * 
	 */
	public void run() {
		try {
			while(running){
				if(this.cnt % interval == 0){
					try {
						//读取所有日程列表
						List<Map<String,Object>> scList = scheduleDao.getScheduleList();
						for(Map<String,Object> map : scList){
							String scheduleid = (String)map.get("scheduleid");
							String enabled = (String)map.get("enabled");
							ScheduleThread scThThread = this.getScThThreadById(scheduleid);
			
							if(null == scThThread){
							
								//不存在则创建日程线程
								if("1".equals(enabled)){ //日程有效
									ScheduleThread scThThread1 = new ScheduleThread(scheduleDao,yhzhDao,pointControl);
									scThThread1.setRunning(true);
									scThThread1.setScheduleid(scheduleid);
									scThThread1.setSchedulename((String)map.get("schedulename"));
									scThThread1.setEnabled((String)map.get("enabled"));
									scThThread1.setReverse((String)map.get("reverse"));
									scThThread1.setControlmode((String)map.get("controlmode"));
									//scThThread1.setInterval((Integer)map.get("intervals")); //待检查是否写法正确
									scThThread1.setInterval((Integer)map.get("intervals"));
									List<Map<String,Object>> schPointRelList = scheduleDao.getpointbyscheduleid(scheduleid);
									List<SchPointRelBean> pointList = new ArrayList<SchPointRelBean>();
									for(Map<String,Object> mapPointRel : schPointRelList){
										SchPointRelBean schPointRelBean = new SchPointRelBean();
										schPointRelBean.setScheduleid((String)mapPointRel.get("scheduleid"));
										String outpointid = (String)mapPointRel.get("pointid");
										schPointRelBean.setOutpointid(outpointid);
										schPointRelBean.setInpointid(scheduleDao.getpointInbypointOut(outpointid));
										schPointRelBean.setControlmode((String)mapPointRel.get("controlmode"));
										schPointRelBean.setDelaytimes((String)mapPointRel.get("delaytimes"));
										pointList.add(schPointRelBean);
									}
									scThThread1.setPointList(pointList);
									scThList.add(scThThread1);
									scThThread1.start();
								}
							}else{
								//存在则更新属性
								scThThread.setSchedulename((String)map.get("schedulename"));
								scThThread.setEnabled((String)map.get("enabled"));
								scThThread.setReverse((String)map.get("reverse"));
								scThThread.setControlmode((String)map.get("controlmode"));
								scThThread.setInterval((Integer)map.get("intervals"));
								//检查日程是否有效
								/*
								if(!"1".equals(enabled)){ //日程无效
									if(scThThread.isInterrupted()){
										
									}
									//暂停线程
									scThThread.wait(); //待测试
								}*/
							}
						}
						//对于数据库中删除的日程，但日程还在跑的话要停掉并释放资源
						this.delScThThread(scList);
					} catch (Exception e) {
						LOG.error("日程控制引擎报错：" + e.getMessage() + "，请检查。");
						e.printStackTrace();
					}
				}
				this.cnt++;
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			LOG.error("日程控制引擎出错并停止：" + e.getMessage() + "，请联系管理员或重启程序。");
			e.printStackTrace();
		}
	}
	
	/**根据scheduleid 获取日程线程对象
	 * @param scheduleid
	 * @return
	 */
	public ScheduleThread getScThThreadById(String scheduleid){
		ScheduleThread scThThread = null;
		for(ScheduleThread scThThread1 :scThList){
			if(scheduleid.equals(scThThread1.getScheduleid())){
				scThThread = scThThread1;
				break;
			}
		}
		return scThThread;
	}
	
	/**对于数据库中删除的日程，但日程还在跑的话要停掉并释放资源
	 * @param db_scList 数据库中存在的日程列表
	 */
	private void delScThThread(List<Map<String,Object>> db_scList){
		for(ScheduleThread scThread: scThList){
			boolean bExist = yhzhUtil.isInList(db_scList, "scheduleid", scThread.getScheduleid());
			if(!bExist){
				//数据库中已删除该日程表，则优雅地结束该日程线程
				scThread.setRunning(false);
			}
		}
	}
	
}
