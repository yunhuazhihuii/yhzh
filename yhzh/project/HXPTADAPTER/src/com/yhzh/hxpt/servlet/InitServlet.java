package com.yhzh.hxpt.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.yhzh.hxpt.imp.ModelImp;
import com.yhzh.hxpt.spring.SpringBeanManager;

/**自定义Servlet初始化类
 * @author chenms
 *
 */
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Log log = LogFactory.getLog(InitServlet.class); 

	public void init() throws ServletException {
//		System.out.println("InitServlet的init方法被触发了");
		try {
			log.debug("----------InitServlet开始启动初始化------------");
			//一.初始化SpringBeanManager，设置单例SpringBeanManager的ApplicationContext属性，之后就可以用SpringBeanManager去获取spring bean实例了
			ApplicationContext ac = (WebApplicationContext)this.getServletContext().getAttribute(
					"org.springframework.web.servlet.FrameworkServlet.CONTEXT.adapter");
			SpringBeanManager.getInstance().setAc(ac);
//		Object o1 = SpringBeanManager.getInstance().getSpringBean("jdbcTemplate");
//		System.out.println("SpringBeanManager获取到的spring对象为：" + o1);
			
			//在servlet中获取spring上下文获取bean实例。这种方式同样不行，也是因为web.xml中没有配置ContextLoaderListener
			/*ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			Object o1 = ac.getBean("jdbcTemplate");   
			System.out.println("获取到的spring对象为：" + o1);*/
			
			//若只通过DispatcherServlet来加载spring的配置文件，则可以用这种方式，其中org.springframework.web.servlet.FrameworkServlet.CONTEXT.rest
			//的rest是DispatcherServlet在web.xml中配置的名字
			/*ApplicationContext ac = (WebApplicationContext)this.getServletContext().getAttribute(
					"org.springframework.web.servlet.FrameworkServlet.CONTEXT.rest");
			Object o1 = ac.getBean("jdbcTemplate");   
			System.out.println("获取到的spring对象为：" + o1);*/
			
//		2.测试PropertiesUtil,测试通过
//		System.out.println("java读取properties配置：" + SpringBeanManager.getInstance().getPropertiesUtil().websocket_clientName);
			
			//二.初始化model，一个适配器工程只有一个model
			ModelImp model = new ModelImp();
			String rst = model.execute();
			if(!"1".equals(rst)){
				log.error("model初始化启动失败：" + rst);
			}
			log.debug("----------InitServlet初始化完成------------");
		} catch (Exception e) {
			log.error("InitServlet初始化启动失败：" + e);
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	public void destroy() {
		System.out.println("InitServlet的destroy方法被触发了");
	}
}
