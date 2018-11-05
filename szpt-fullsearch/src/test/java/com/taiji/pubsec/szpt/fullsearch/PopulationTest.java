package com.taiji.pubsec.szpt.fullsearch;

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
public class PopulationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(PopulationTest.class) ;
	
	@Resource
	private FullTextSearchService fullTextSearchService;
	
	@Test
	public void testSearchPersons(){
		
		Map<String,Object> conditions = new HashMap<String,Object>();
		conditions.put(SolrConstant.Population.text.toString(), "测试") ;
		
		conditions.put(SolrConstant.Population.alertlevel.toString(), new String[]{"黄", "绿"}) ;
		
		conditions.put(SolrConstant.Population.type.toString(), "是");
		
		conditions.put(SolrConstant.Population.persontypecode.toString(), new String[]{"123"});
		
		Pager<Map<String,Object>> persons = fullTextSearchService.queryPerson(conditions, 0, 10) ;
		
		LOGGER.debug("查询结果总数：{}", persons.getTotalNumber());
		LOGGER.debug("当前页内容数量：{}", persons.getPageList().size());
		for(Map<String,Object> person : persons.getPageList()){
			LOGGER.debug("当前页查询结果：姓名({}), {}", person.get("name"), person.toString());
		}
	}
	
	@Test
	public void addIndex(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcard", "34040404040404");
		map.put("name", "测试人员11");
		map.put("gender", "2");
		
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("nation","01");
		map.put("birthday",  sdfdst.format(new Date()));
		map.put("address", "测试地址：" + UUID.randomUUID().toString());
		map.put("id",  UUID.randomUUID().toString());
		//map.put("id",  "74f38582-6524-4d71-ae30-4b1aec5c456f");
		
		map.put("type","是");
		
		map.put("alertlevel", "黄");
		
		map.put(SolrConstant.Population.persontypecode.toString(), new String[]{"123", "456"});
		
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex("population", "id", map);
	}
	
	@Test
	public void addHightPersonIndex(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcard", "130412199001233418");
		map.put("name", "张三丰");
		map.put("gender", "1");
		
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("nation","汉族");
		map.put("birthday",  sdfdst.format(new Date()));
		map.put("address", "河北省石家庄市赵县");
		map.put("oldname", "张君宝");
		map.put("type", "是");
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("alertlevel", "红");
		map.put("birthaddress", "河北石家庄");
		map.put("culture", "高中");
		map.put("marry", "未婚");
		map.put("occupation", "工人");
		map.put("phone", "13112341234");
		map.put("householder", "张三");
		map.put("relation", "父子");
		map.put("persontypecode", "017004000,017001001006,017004002,003001");
		map.put("criminaltypecode", "017004001004010,017004001005000");
		map.put("id",  "d66e4d38-8051-4e12-af96-c69c560f068f");
		map.put(SolrConstant.Population.persontypecode.toString(), new String[]{"123", "456"});
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex("population", "id", map);
	}
	
	
	@Test
	public void addPersonIndex(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcard", "130412199001233418");
		map.put("name", "李四");
		map.put("gender", "1");
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("nation","汉族");
		map.put("birthday",  sdfdst.format(new Date()));
		map.put("address", "河北省石家庄市赵县");
		map.put("oldname", "小李");
		map.put("type", "否");
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("alertlevel", "红");
		map.put("birthaddress", "河北石家庄");
		map.put("culture", "高中");
		map.put("marry", "未婚");
		map.put("occupation", "工人");
		map.put("phone", "13112341234");
		map.put("relation", "父子");
		map.put("householder", "李小二");
		map.put("persontypecode", "017004000,017001001006,017004002,003001");
		map.put("criminaltypecode", "017004001004010,017004001005000");
		map.put("id",  "d66e4d38-8051-4e12");
		map.put(SolrConstant.Population.persontypecode.toString(), new String[]{"123", "456"});
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex("population", "id", map);
	}
	
	@Test
	public void addPersonIndex1(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcard", "130414199001233419");
		map.put("name", "赵六");
		map.put("gender", "1");
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("nation","汉族");
		map.put("birthday",  sdfdst.format(new Date()));
		map.put("address", "河北省石家庄市赵县");
		map.put("oldname", "小赵");
		map.put("type", "否");
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("alertlevel", "红");
		map.put("birthaddress", "河北石家庄");
		map.put("culture", "高中");
		map.put("marry", "未婚");
		map.put("occupation", "工人");
		map.put("phone", "13312341234");
		map.put("relation", "父子");
		map.put("householder", "赵小二");
		map.put("persontypecode", "017004000,017001001006,017004002,003001");
		map.put("criminaltypecode", "017004001004010,017004001005000");
		map.put("id",  "130425199101263418");
		map.put(SolrConstant.Population.persontypecode.toString(), new String[]{"123", "456"});
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex("population", "id", map);
	}
	
	
	@Test
	public void addPersonIndex2(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcard", "130425199101263418");
		map.put("name", "小成");
		map.put("gender", "1");
		map.put("id",  "d66e4d38-8051-1111");
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("nation","汉族");
		map.put("birthday",  sdfdst.format(new Date()));
		map.put("address", "河北邯郸");
		map.put("oldname", "小连");
		map.put("type", "是");
		map.put("collecttime", sdfdst.format(new Date()));
		map.put("alertlevel", "2");
		map.put("birthaddress", "北京");
		map.put("culture", "大学本科");
		map.put("marry", "未婚");
		map.put("occupation", "工人");
		map.put("phone", "15232886372");
		map.put("relation", "父子");
		map.put("householder", "老成");
		map.put("persontypecode", "017004000,017001001006,017004002,003001");
		map.put("criminaltypecode", "017004001004010,017004001005000");
		map.put(SolrConstant.Population.persontypecode.toString(), new String[]{"123", "456"});
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex("population", "id", map);
	}
	
}
