package com.yhzh.hxpt.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**读取properties配置文件配置
 * @author chenms 2018-6-22
 *
 */
@Component
public class PropertiesUtil {
	
	@Value("${websocket.clientName}")
	public String websocket_clientName;
	
}
