package com.taiji.pubsec.szpt.zhzats.update;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.pubsec.persistence.dao.SqlDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(false)
public class UpdateDateTest {

	@Resource
	private SqlDao sqlDao ;
	
	/**
	 * 修改警情信息表发生时间（用作综合治安态势警情分布）
	 */
	@Test
	public void testUpdateJwzhJqFssj(){
		String sql = "select id,fssj from t_zhzats_jwzh_jq";
		Map<String, Object> mapParam = new HashMap<String, Object>();
		List<Object[]> jqList = sqlDao.find(sql, mapParam);
		
		for(Object[] jqObj : jqList){
			String id = (String)jqObj[0];
			Date date = (Date)jqObj[1];
			
			Long dateTime = date.getTime() + 1000*60*60*24*2;
			Date newDate = new Date(dateTime);
			
			String sqlUpdate = "update t_zhzats_jwzh_jq set fssj = :date where id = :id";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("date", newDate);
			map.put("id", id);
			sqlDao.executeUpdate(sqlUpdate, map);
		}
	}
	
	/**
	 * 修改位置信息表更新时间（用作综合治安态势警情分布）
	 */
	@Test
	public void testUpdateJwzhWzxxGxsj(){
		String sql = "select id,gxsj from t_zhzats_jwzh_wzxx";
		Map<String, Object> mapParam = new HashMap<String, Object>();
		List<Object[]> jqList = sqlDao.find(sql, mapParam);
		
		for(Object[] jqObj : jqList){
			String id = (String)jqObj[0];
			Date date = (Date)jqObj[1];
			
			Long dateTime = date.getTime() + 1000*60*60*24*18;
			Date newDate = new Date(dateTime);
			
			String sqlUpdate = "update t_zhzats_jwzh_wzxx set gxsj = :date where id = :id";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("date", newDate);
			map.put("id", id);
			sqlDao.executeUpdate(sqlUpdate, map);
		}
	}
}
