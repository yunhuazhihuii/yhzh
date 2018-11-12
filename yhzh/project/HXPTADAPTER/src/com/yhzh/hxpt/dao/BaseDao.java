package com.yhzh.hxpt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**数据库操作
 * @author chenmingshuai
 * 2017-9-16
 */
@Repository("baseDao")  //这个这个类不用在spring的xml配置文件中写bean了
//@Scope(scopeName="singleton") //等同于配置文件的scope属性
public class BaseDao{
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource  
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; // 支持具名参数，提高代码的可读性。
	
//	private static BaseDao baseDao = null;
	
	@Resource //事务管理器
	private DataSourceTransactionManager txManager;
	
	//日志
	public Log log = LogFactory.getLog(BaseDao.class); 
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	
	/**不能用 private，spring实例工厂会报错：No visible constructors in class com.cms.dao.BaseDao
	 * 
	 */
	/*public BaseDao(){ 
		System.out.println("已经初始化过BaseDao了");
		baseDao = this;
	}*/
	
	/*public static BaseDao getInstance(){
		return baseDao;
	}*/
	
	/**查询
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,Object>> queryForList(String sql,Map<String,Object> paramMap){
		List<Map<String,Object>> listRst = null;
		log.debug(sql + paramMap);
		listRst = namedParameterJdbcTemplate.queryForList(sql, paramMap);
		return listRst;
	}
	
	/**增、删、改
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public int execSql(String sql,Map<String,Object> paramMap){
		int affectRow = -1;
		log.debug(sql + paramMap);
		affectRow = namedParameterJdbcTemplate.update(sql, paramMap);
		return affectRow;
	}
	
	@Transactional
    public void initTest(){
    	String sql = "insert into yhzh_user(id,name) values (:id,:name)";
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("id", "1000000004");
    	paramMap.put("name", "chenmingshuai4");
    	this.execSql(sql, paramMap);

    	String sql1 = "insert into yhzh_user1(id,name) values (:id,:name)";
    	Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("id", "1000000005");
    	paramMap1.put("name", "chenmingshuai5");
    	this.execSql(sql1, paramMap1);
    }
	
	/**
	 * 事务测试 2018-6-9
	 *  @Transactional 注解只能应用到 public 可见度的方法上,其它的不起作用；
	 *  默认情况下，spring会对unchecked异常进行事务回滚；如果是checked异常则不回滚
	 *  （java里面将派生于Error或者RuntimeException（比如空指针，1/0）的异常称为unchecked异常，
		 其他继承自java.lang.Exception得异常统称为Checked Exception，如IOException、TimeoutException等）
		 再通俗一点：你写代码出现的空指针等异常，会被回滚，文件读写，网络出问题，spring就没法回滚了。
		 如果我想check异常也想回滚怎么办，注解上面写明异常类型即可：@Transactional(rollbackFor=Exception.class)
		 数据库引擎要支持事务，如果是mysql，注意表要使用支持事务的引擎，比如innodb，如果是myisam，事务是不起作用的。
	 */
	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
//	@Transactional
//	@Transactional(rollbackFor=BadSqlGrammarException.class)
	//以上四种写法都生效
    public void testTrans() /*throws Exception*/{
    	String sql = "insert into s0000004(F1,F2,F3,F4) values (:f1,:f2,:f3,:f4)";
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("f1", "f1");
    	paramMap.put("f2", "11");
    	paramMap.put("f3", "f3");
    	paramMap.put("f4", "4");
    	this.execSql(sql, paramMap);
    	System.out.println("插入s0000004成功");
    	String sql1 = "insert into yhzh_user1(id,name) values (:id,:name)";
    	Map<String,Object> paramMap1 = new HashMap<String,Object>();
    	paramMap1.put("id", "1000000005");
    	paramMap1.put("name", "chenmingshuai5");
//    	try {
			this.execSql(sql1, paramMap1);
		/*} catch (Exception e) {
			System.out.println("异常的类名为：" + e.getClass().getName());
			throw e;
		}*/
    }
	
	/**
	 * 直接人工写事务控制
	 */
	public void testTrans2(){
		/*事物的传播行为：
		TransactionDefinition.PROPAGATION_REQUIRED: 当前方法必须运行在事物中，如果当前事物存在，方法将会在事务中运行，否则会启动一个新事务
		TransactionDefinition.PROPAGATION_SUPPORTS：当前方法不需要事务上下文，但是如果存在当前事务的话，那么这个方法会在这个事务中运行
		TransactionDefinition.PROPAGATION_MANDATORY：该方法必须在事务中运行，如果当前事务不存在则会抛出一个异常
		TransactionDefinition.PROPAGATION_REQUIRES_NEW：表示当前方法必须运行在它自己的事务中。一个新的事务将被启动。如果存在当前事务，在该方法
			执行期间，当前事务会被挂起。如果使用JTATransactionManager的话，则需要访问TransactionManager。
		TransactionDefinition.PROPAGATION_NOT_SUPPORTED：该方法不应运行在事务中。如果存在 当前事务，在该方法运行期间，当前事务被挂起，
			如果使用JTATransactionManager的话，则需要访问TransactionManager。
		TransactionDefinition.PROPAGATION_NEVER：当前方法不应该运行在事务上下文中，如果当前正有一个事务在运行，则会抛出异常。
		TransactionDefinition.PROPAGATION_NESTED：如果当前已经存在一个事务，那么该方法将会在嵌套事务中运行。嵌套的事务可以独立于当前事务
			进行单独地提交或回滚。如果当前事务不存在，那么其行为与PROPAGATION_REQUIRED一样。注意各厂商对这种传播行为的支持是有所差异的。可以
			参考资源管理器文档来确认他们是否支持嵌套事务。
		*/
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();//事物定义类
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);//传播行为：需要事务，无则创建
		TransactionStatus status = txManager.getTransaction(def);//返回事务 【这里面就会拿到(无则创建)事务并开始事务】
		
		try {
			String sql = "insert into s0000004(F1,F2,F3,F4) values (:f1,:f2,:f3,:f4)";
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("f1", "f1");
			paramMap.put("f2", "12");
			paramMap.put("f3", "f3");
			paramMap.put("f4", "5");
			this.execSql(sql, paramMap);
			System.out.println("插入s0000004成功");
			
			/*String sql1 = "insert into yhzh_user1(id,name) values (:id,:name)";
			Map<String,Object> paramMap1 = new HashMap<String,Object>();
			paramMap1.put("id", "1000000005");
			paramMap1.put("name", "chenmingshuai5");
			this.execSql(sql1, paramMap1);*/
			
			//提交事务
			txManager.commit(status);
			System.out.println("事务提交成功！！！");
		} catch (Exception e) {
			//回滚事务
			txManager.rollback(status);
			System.out.println("出现异常，回滚事务完成！！！");
			e.printStackTrace();
		}
		
	}

	/**获取事务管理器
	 * @return
	 */
	public DataSourceTransactionManager getTxManager() {
		return txManager;
	}
	
	
	
}

