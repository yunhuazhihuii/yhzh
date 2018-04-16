package com.yhzh.util;

import javax.annotation.Resource;


/**用来获取spring单例bean的工具类
 * @author chenms
 *
 */
public class BeanUtil {

	@Resource 
    private SpringContextHelper springContextHelper;

	public SpringContextHelper getSpringContextHelper() {
		return springContextHelper;
	}
	
}
