package com.taiji.pubsec.szpt.ajcase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.fullsearch.service.FullTextSearchService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class CaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaseTest.class) ;
	
	@Resource
	private FullTextSearchService fullTextSearchService;
	
	@Test
	public void testQueryCase(){
		
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put(SolrConstant.Case.text.toString(), "测试") ;
		
		Pager<Map<String,Object>> cases = fullTextSearchService.queryCase(conditions, 0, 10) ;
		
		LOGGER.debug("查询结果总数：{}", cases.getTotalNumber());
		LOGGER.debug("当前页内容数量：{}", cases.getPageList().size());
		for(Map<String,Object> person : cases.getPageList()){
			LOGGER.debug("当前页查询结果：案件({}), {}", person.get("name"), person.toString());
		}
		
	}
	
	@Test
	public void addIndex(){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", "测试案件1");
		map.put("content", "xxx发生了xxx");
		
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		map.put("crimetime", sdfdst.format(new Date()));
		map.put("property","123");
		map.put("address", "xxx位置");
		map.put("handler", new String[]{"张三", "李四"});
		map.put("id",  UUID.randomUUID().toString());
		//map.put("id",  "74f38582-6524-4d71-ae30-4b1aec5c456f");
		
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex(SolrConstant.COLLECTION_CASE, "id", map);
		
	}
	
}
