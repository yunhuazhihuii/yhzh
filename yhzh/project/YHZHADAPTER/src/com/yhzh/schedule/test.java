package com.yhzh.schedule;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.yhzh.schedule.dao.ScheduleDao;
import com.yhzh.zhyq.dao.YhzhDao;


@Component
@Path("/holiday") 
public class test { 
	//interval
	@Resource 
    ScheduleDao scheduleDao;
	@Resource 
	YhzhDao yhzhDao;
	
	@GET  
    @Produces("text/plain")  
    public String helloWorld(){  
        return "hello world";  
        
    }  
    
 //  查询节假日
    @GET  
    @Path("holi")  
    @Produces("text/plain") 
    public String getHoli(@PathParam("name") String name){  
    	
    	List<Map<String, Object>> scList =null;
    	System.out.println("准备发送节假日列表");

    	 scList =scheduleDao.selectHoliday();
    
    	 ListToJson ltj=new ListToJson();
    	 
    	 System.out.println("发送节假日列表完成");
    	 return (ltj.change(scList)).toString();
    }  
      
    //  查询点
    @GET  
    @Path("getpoint")  
    @Produces("text/plain") 
    public String getPoint(@PathParam("name") String name){  
    	
    	List<Map<String, Object>> scList =null;
    	System.out.println("准备发送属性点");

    	 scList =yhzhDao.getPointInfo();
    	
    	 String allpoint="{\"data\":[";
    	 for(Map<String,Object> map : scList){
    		 String point=null;
    		
				String pointid = (String)map.get("pointid");
				String ioproperty = (String)map.get("ioproperty");
				String pointname = (String)map.get("pointname");
				String pointvalue = (String)map.get("pointvalue");
				if(pointname.equals("回路输入")&&ioproperty.equals("1")){
					String idname="A001"+"."+"000001"+"."+pointid;
					 point="{\"name\":\""+idname+"\",\"value\":\""+pointvalue+"\",\"ioproperty\":\"DI\"}";
					 if(allpoint.equals("{\"data\":[")){
							allpoint=allpoint+point;
						}else{
							allpoint=allpoint+","+point;
						}
				}else if(pointname.equals("空调")){

					String controllerid = (String)map.get("controllerid");
					String idname="A001"+"."+controllerid+"."+pointid;
					switch (ioproperty) {
					case "1":
						ioproperty="DI";
						break;
					case "2":
						ioproperty="DO";
						break;
					case "3":
						ioproperty="AV";
						break;
					case "4":
						ioproperty="AI";
						break;
					case "5":
						ioproperty="AO";
						break;
					case "6":
						ioproperty="BV";
						break;
					default:
						break;
					}
					 point="{\"name\":\""+idname+"\",\"value\":\""+pointvalue+"\",\"ioproperty\":\""+ioproperty+"\"}";
					 if(allpoint.equals("{\"data\":[")){
							allpoint=allpoint+point;
						}else{
							allpoint=allpoint+","+point;
						}
				}
				
    	 }
    	 
    	 String data= allpoint+"]}";
    	 return data;
    }  
      
  //删除节假日
    @GET  
    @Path("holidele/{name}")  
    @Produces("text/plain")  
    public String deleHoli(@PathParam("name") String name){ 

    	scheduleDao.deleteHoliday( name);
    	System.out.println("删除节假日成功"+name);
        return "删除节假日成功";  
    } 
 //查询日程表
    @GET  
    @Path("sche/{name}")  
      @Produces("text/plain") 
    public String getSche(@PathParam("name") String name){  
    	String[] s=name.split("\\.");
    	String sche=scheduleDao.selectSche( s[0],s[1]);
    	System.out.println("s[0]:"+s[0]+"s[1]:"+s[1]);
    	System.out.println(sche);
    	//调用查询日程表方法
    	System.out.println("准备发送日程表列表");
        return sche;  
    }  
    /**
   	 * 删除日程表
   	 * 
   	 * @return
   	 */  
    
    @GET  
    @Path("schedele/{name}")  
    @Produces("text/plain")  
    public String deleSche(@PathParam("name") String name){ 
    	//
    	scheduleDao.deletesche(name);
    	System.out.println("删除日程表成功"+name);
        return "删除日程表成功";  
    }

    /**
	 * 增加修改日程表
	 * 
	 * @return
     * @throws ParseException 
     * @throws JSONException 
	 */
    @POST
    @Path("/addsche")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addSche(String name) throws JSONException, ParseException {

    	
        System.out.println(name);
    	JSONObject obj = new JSONObject(name);
    	JSONObject form = (JSONObject) obj.get("form");
    	JSONObject sche = (JSONObject) form.get("schedule");
    	String scheduleid=(String)sche.get("scheduleid");
    	String schedulename=(String)sche.get("schedulename");
    	    	
    	
    	//String controlmode=(String)form.get("radio");
    	//String enabled=(String)form.get("checked");
    	String floor=(String)sche.get("floor");
    	String system=(String)sche.get("system");
    	String backtop="修改日程表成功！";
    	if (null==scheduleid||scheduleid.equals("")){
    		//调用增加
    	 Date date=new Date();
       	 SimpleDateFormat sdf2 = new SimpleDateFormat("HH-mm-ss");
       	 scheduleid  =	sdf2.format(date);
       	backtop=scheduleid;
    	}else {
    		//调用删除
    		scheduleDao.deletesche(scheduleid);
		}
    	
    	//保存日程主表
    	//可能是101，108
    	scheduleDao.addsche( scheduleid, schedulename,"1","0","0", "180",floor,system);
    	//保存时间安排表
    	JSONArray sureTime = (JSONArray) form.get("sureTime");
    	SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    	for (int i = 0; i < sureTime.length(); i++) {
    		JSONObject  object3 = (JSONObject) sureTime.get(i);
    		String fromtime=sdf1.format(sdf1.parse((String) object3.get("fromtime")));
    		String totime=sdf1.format(sdf1.parse((String) object3.get("totime")));
    		
    		
    		
    		JSONArray DateValue = (JSONArray) form.get("DateValue");
        	for (int j = 0; j < DateValue.length(); j++) {
        		JSONObject  object4 = (JSONObject) DateValue.get(j);
        		String weekid=(String) object4.get("weekid");
        		String scheduletimeid=scheduleid+weekid+j;
        		scheduleDao.addscheduletimes(scheduletimeid, scheduleid,  weekid, fromtime, totime);
        	}
    	}
    	//保存时间关联点表
    	JSONArray light = (JSONArray) form.get("light");
    	
if (system.equals("BA")){
	for (int i = 0; i < light.length(); i++) {
		JSONObject  object3 = (JSONObject) light.get(i);
		JSONObject  StartBut = (JSONObject) object3.get("startBut");
		String OutSport=(String) StartBut.get("ReflectVar");
		//解析点
		String[] s=OutSport.split("\\.");
		
		scheduleDao.addschedulerel	(scheduleid,s[2],"0","0","空");
	}
    	}else if (system.equals("ZM")){
    		for (int i = 0; i < light.length(); i++) {
        		JSONObject  object3 = (JSONObject) light.get(i);
        		String OutSport=(String) object3.get("InSport");
        		String potintname=(String) object3.get("equipment");
        		//解析点
        		String[] s=OutSport.split("\\.");
        		
        		scheduleDao.addschedulerel	(scheduleid,s[2],"0","0",potintname);
        	}
    	}else if (system.equals("fan")){
    		for (int i = 0; i < light.length(); i++) {
    			JSONObject  object3 = (JSONObject) light.get(i);
    			JSONObject  StartBut = (JSONObject) object3.get("startBut");
    			String OutSport=(String) StartBut.get("ReflectVar");
    			//解析点
    			String[] s=OutSport.split("\\.");
    			
    			scheduleDao.addschedulerel	(scheduleid,s[2],"0","0","空");
    		}
    	    	}


      return backtop;
    }
    /**
   	 * 增加修改节假日
   	 * 
   	 * @return
   	 */
    @POST
    @Path("/addHoli")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addHoli(  String name) {
//    	{"type": "updateHoliday",
//    			"data": [{
//    					"name": "日期",
//    					"value": "描述"
//    				},{
//    					"name": "日期",
//    					"value": "描述"}]}
    	JSONObject obj = new JSONObject(name);
    	String type = (String) obj.get("type");
    	JSONArray data = (JSONArray) obj.get("data");
    	for (int i = 0; i < data.length(); i++) {
			JSONObject object3 = (JSONObject) data.get(i);
			String holiname = (String) object3.get("name");
			String value = (String) object3.get("value");
			
			if(type.equals("updateHoliday")){
				//调用修改
				scheduleDao.updateHoliday(holiname,value);
	    	}else{
	    		//调用增加
	    		scheduleDao.saveHoliday(holiname,value);
	    	}
    	}
      return "增加/修改节假日成功";
    }
    
    
    
    
    
}
