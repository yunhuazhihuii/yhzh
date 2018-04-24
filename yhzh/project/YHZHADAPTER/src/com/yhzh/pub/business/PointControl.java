package com.yhzh.pub.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yhzh.bacnet.BacnetManager;
import com.yhzh.schedule.dao.ScheduleDao;
import com.yhzh.zhyq.dao.YhzhDao;
import com.yhzh.zhyq.socket.Nsocket;
//import com.yhzh.zhyq.socket.ServerSocketThread;
//import com.yhzh.zhyq.socket.SocketClient;

/**点控制公共类
 * @author samsun 2018-1-27
 *
 */
public class PointControl {
	@Resource 
	ScheduleDao scheduleDao;
//    @Resource //注解注入
//    private ServerSocketThread serverSocketThread;
    @Resource //注解注入
    private BacnetManager bacnetManager;
    @Resource //注解注入
    private YhzhDao yhzhDao;
	@Resource
	Nsocket nsocket;
    /**写点
     * @param pointId
     * @param value
     * @return
     */
    public boolean writePoint(String pointId,String value){
		boolean bRst = false;
		Map<String,Object> pointMap = yhzhDao.getPointInfoByid(pointId);
		String pointtype = (String)pointMap.get("pointtype");//点类型(1回路2组3区域4空调)
		String controllerid = (String)pointMap.get("controllerid");//控制器编号
		if("1".equals(pointtype) || "2".equals(pointtype) || "3".equals(pointtype)){ //智能照明
			//对于智能照明的点先认为是 第一个socketclient，后期再优化
//			List<SocketClient> sClientList  = serverSocketThread.getsClientList();
			//SocketClient socketClient = sClientList.get(0);
			nsocket.writePoint(pointId, value);
			//记录日志表 待补充
//			scheduleDao.insYhzhScheduleLog( new Date(), scheduleid, scheduletimeid,
//					 outpointid, outpointvalue, pointId, value, "");
			
		}else if("4".equals(pointtype)){ //空调ba
			bRst = bacnetManager.writePoint(controllerid, pointId, value);
		}
		return bRst;
    }
}
