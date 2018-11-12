package com.yhzh.hxpt.spring;

import org.springframework.context.ApplicationContext;

import com.yhzh.hxpt.util.PropertiesUtil;

public class SpringBeanManager {

	//饿汉式单例，无线程安全问题
	private static final SpringBeanManager springBeanManager = new SpringBeanManager();
	private ApplicationContext ac = null;
	
	private SpringBeanManager(){
		
	}
	
	public static SpringBeanManager getInstance(){
		return springBeanManager;
	}
	
	/**根据beanid获取bean实例对象
	 * @param beanID
	 * @return
	 */
	public Object getSpringBean(String beanID){
		Object o = null;
		//通过spring提供的contextLoader获取bean实例，该方式不依赖于servlet，也不要求本类要注入
		//该方法不可行，因为web.xml中没有配置ContextLoaderListener
		/*WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		o = wac.getBean(beanID);*/
		o = ac.getBean(beanID);
		return o;		
	}
	
	/**获取Properties配置文件工具类对象
	 * @return
	 */
	public PropertiesUtil getPropertiesUtil(){
		return (PropertiesUtil)this.getSpringBean("propertiesUtil");
	}

	public ApplicationContext getAc() {
		return ac;
	}

	public void setAc(ApplicationContext ac) {
		this.ac = ac;
	}
	
	
}
