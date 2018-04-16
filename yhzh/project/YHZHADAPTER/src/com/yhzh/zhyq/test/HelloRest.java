package com.yhzh.zhyq.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**spring下restful服务接口 例子
 * @author samsun
 *
 */
//@RestController
@Controller
@RequestMapping("/hello_rest")
public class HelloRest {
	/**
     * 1.路径的变化：/hello_rest/{name}/{password}其中{}相当于可以的参数
     * 2.参数的写法：需要利用@PathVariable("name")来匹配上面的参数
     * 3.至于@PathVariable后面跟的形参你就可以随便命名了
     * @param username
     * @param password
     * @return
     */
//	@RequestMapping(value = "/hello_rest/{name}/{password}")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HelloBean hello_rest
//    (@PathVariable("name") String username,
//            @PathVariable("password") String password) 
    (@PathVariable long id)
	{
        /*if("admin".equals(username)&"123".equals(password)){
            System.out.println("登录成功");
            return "hello";
        }else{
        	System.out.println("登录失败");
            return "fail";
        }*/
		System.out.println("id:" +id);
		return new HelloBean(id,"HELLOhaha");
    }
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public HelloBean update(@PathVariable long id, @RequestModel HelloBean updated) {
		//... load user, update values, etc
		return new HelloBean(id,"HELLOhaha");
	 }
*/
}
