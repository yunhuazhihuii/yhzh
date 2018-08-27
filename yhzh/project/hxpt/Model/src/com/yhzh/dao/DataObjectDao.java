package com.yhzh.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.yhzh.pojo.S0000002;
import com.yhzh.pojo.S1000000;
import com.yhzh.pojo.S1100000;
import com.yhzh.pojo.S1110000;
import com.yhzh.pojo.S1111000;
import com.yhzh.pojo.S1120000;

public class DataObjectDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource  
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Resource
	BaseDao baseDao;
	
	/**
	 * 插入数据到S0000002数据库表中
	 * @param s2
	 * @return
	 */
	public int insertS0000002(S0000002 s2) {
		System.out.println("获得传输的数据："+ s2.getF1()+ s2.getF2()+ s2.getF3()+ s2.getF4()+ s2.getF5()+ s2.getF6());
		String sql = "insert into S0000002(F1,F2,F3,F4,F5,F6) values('"+s2.getF1()+"','"+s2.getF2()+"','"+s2.getF3()+"','"+s2.getF4()+"','"+s2.getF5()+"','"+s2.getF6()+"')";
		int insertS0000002 = baseDao.execSql(sql, null);
		return insertS0000002;
	}
	/**
	 * 根据创建的编码从S0000002表中实时对象及目录的记录
	 * @param code
	 * @return
	 */
	public int deleteS0000002ByCode(String RtObjectCode) {
		String sql = "DELETE FROM S0000002 WHERE F2='"+RtObjectCode+"' OR F6 = '"+RtObjectCode+"'";
		int deleteS0000002ByCode = baseDao.execSql(sql, null);
		return deleteS0000002ByCode;
	}
	/**
	 * 删除实时对象目录下的属性
	 * @param RtObjectCode
	 * @return
	 */
	public int deleteS0000002ByCodeSub(String RtObjectCode) {
		String sql = "DELETE FROM S0000002 WHERE F6 IN (SELECT A.F2 FROM (SELECT F2 FROM S0000002 WHERE F6='"+RtObjectCode+"') A);";
		int deleteS0000002ByCodeSub = baseDao.execSql(sql, null);
		return deleteS0000002ByCodeSub;
	}
	
	/**
	 * 将数据更新到S0000002表中
	 * @param s0000002
	 * @return
	 */
	public int updateS0000002(S0000002 s0000002) {
		System.out.println("将数据更新到S0000002表中的格式："+s0000002.getF3()+ s0000002.getF2());
		String sql = "update S0000002 set F3='"+s0000002.getF3()+"' where F2='"+s0000002.getF2()+"'";
		int updateS0000002 = baseDao.execSql(sql, null);
		return updateS0000002;
	}
	/**
	 * 自动获得编码
	 * @param DicCode
	 * @return
	 */
	public String AutoGetCode(String DicCode) {
		String sql = "select GetAutoCode('"+DicCode+"')";
		String getCode = null;
		List<Map<String, Object>> autoGetCode = baseDao.queryForList(sql, null);
		for (Map<String, Object> map : autoGetCode) {
			getCode = (String) map.get("GetAutoCode('"+DicCode+"')");
			System.out.println("得到自动编码2：" + getCode);
		}
		return getCode;
	}
	/**
	 * 插入记录到表S1111000
	 * @param s1111000
	 * @return
	 */
	public int insertS1111000(S1111000 s1111000) {
		String sql = "insert into S1111000(F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13) values('"+s1111000.getF1()+"','"+s1111000.getF2()+"','"+s1111000.getF3()+"','"+s1111000.getF4()+"','"+s1111000.getF5()+"','"+s1111000.getF6()+"','"+s1111000.getF7()+"','"+s1111000.getF8()+"','"+s1111000.getF9()+"','"+s1111000.getF10()+"','"+s1111000.getF11()+"','"+s1111000.getF12()+"','"+s1111000.getF13()+"') ";
		int insertS1111000 = baseDao.execSql(sql, null);
		return insertS1111000;
	}
	/**
	 * 根据实时对象编码和点分类编码删除实时对象属性
	 * @param RTOAttrCatalogCode
	 * @return
	 */
	public int  DeleteRTOAttr(String RTOAttrCode,String DotCategory) {
		String sql = "DELETE FROM S1111000 WHERE F2='"+RTOAttrCode+"' and F6 = '"+DotCategory+"'";
		int DeleteRTOAttr = baseDao.execSql(sql, null);
		return DeleteRTOAttr;
	}
	
	/**
	 * 根据实时对象属性编码在S1111000表中查询实时对象属性
	 * @param ServerUnitCode
	 * @return
	 */
	public List<Map<String, Object>> findS1111000ByRTOAttrCode(String RTOAttrCode) {
		String sql = "select F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13 from S1111000 where F3 = '"+ RTOAttrCode+"'";
		List<Map<String, Object>> list = baseDao.queryForList(sql, null);
		return list;
	} 
	/**
	 * 根据实时对象编码删除实时对象属性
	 * @param s1110000
	 * @return
	 */
	public int DeleteRTOAttr(String RTOAttrCode) {
		String sql = "DELETE FROM S1111000 WHERE F3='"+RTOAttrCode+"'";
		int DeleteRTOAttr = baseDao.execSql(sql, null);
		return DeleteRTOAttr;
	}
	/**
	 * 更新实时对象属性
	 * @param s1110000
	 * @return
	 */
	public int updateS1111000(S1111000 s1111000) {
		String sql = "update S1111000 set F4='"+s1111000.getF4()+"',F5='"+s1111000.getF5()+"',F6='"+s1111000.getF6()+"',F7='"+s1111000.getF7()+"',F8='"+s1111000.getF8()+"',F9='"+s1111000.getF9()+"',F10='"+s1111000.getF10()+"',F11='"+s1111000.getF11()+"',F12='"+s1111000.getF12()+"',F13='"+s1111000.getF7()+"' where F3='"+s1111000.getF3()+"'";
		int updateS1111000 = baseDao.execSql(sql, null);
		return updateS1111000;
	}
	/**
	 * 创建数据对象
	 * @param s1120000
	 * @return
	 */
	public int insertS1120000(S1120000 s1120000) {
		String sql = "insert into S1120000(F1,F2,F3,F4,F5,F6) values('"+s1120000.getF1()+"','"+s1120000.getF2()+"','"+s1120000.getF3()+"','"+s1120000.getF4()+"','"+s1120000.getF5()+"','"+s1120000.getF6()+"') ";
		int insertS1111000 = baseDao.execSql(sql, null);
		return insertS1111000; 
	}
	/**
	 * 查询数据对象
	 * @param s1120000
	 * @return
	 */
	public List<Map<String, Object>> findS1120000ByDataObjectCode(String DataObjectCode) {
		String sql = "select F1,F2,F3,F4,F5,F6 from S1120000 where F2 = '"+DataObjectCode+"'";
		List<Map<String, Object>> list = baseDao.queryForList(sql, null);
		return list; 
	}
	/**
	 * 修改数据对象
	 * @param s1120000
	 * @return
	 */
	public int UpdateDataObject(S1120000 s1120000) {
		String sql = "update S1120000 set F3='"+s1120000.getF3()+"',F4='"+s1120000.getF4()+"',F5='"+s1120000.getF5()+"',F6='"+s1120000.getF6()+"' where F2='"+s1120000.getF2()+"'";
		int insertS1111000 = baseDao.execSql(sql, null);
		return insertS1111000; 
	}
	/**
	 * 删除数据对象
	 * @param s1120000
	 * @return
	 */
	public int DeleteDataObject(String DataObjectCode) {
		String sql = "DELETE FROM S1120000 WHERE F2='"+DataObjectCode+"'";
		int insertS1111000 = baseDao.execSql(sql, null);
		return insertS1111000; 
	}
	
}
