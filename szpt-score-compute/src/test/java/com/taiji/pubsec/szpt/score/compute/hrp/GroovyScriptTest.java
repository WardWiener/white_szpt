package com.taiji.pubsec.szpt.score.compute.hrp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.scoreframework.DefaultParameter;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import net.sf.json.JSONObject;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hrp.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
public class GroovyScriptTest {

	private static Logger LOGGER = LoggerFactory.getLogger(GroovyScriptTest.class);
	
	@Test
	public void groovyBasicTest(){
		
		Map<String, DefaultParameter> map = new HashMap<String, DefaultParameter>() ;
		DefaultParameter p1 = new DefaultParameter("测试值1", "标签（测试）tag1") ;
		DefaultParameter p2 = new DefaultParameter("测试值2", "标签（测试）tag2") ;
		
		map.put("标签（测试）tag1", p1) ;
		map.put("标签（测试）tag2", p2) ;
		
		Binding bind = new Binding();
		bind.setVariable("paramMap", map);
		
		bind.setVariable("paramMapJsonStr", JSONObject.fromObject(map).toString());
		
		GroovyShell shell = new GroovyShell(bind);
		
		Object result = shell.evaluate(getGroovyBasicTestScript());
		
		LOGGER.debug("输出结果:" + result);
		
	}
	
	private String getGroovyBasicTestScript(){
		InputStream in = GroovyScriptTest.class.getClassLoader().getResourceAsStream("groovyrule/groovyBasicTest.groovy");
		try{
			String script = FileFmtUtils.readByBufferedLinesToStr(in); 
			LOGGER.debug(script);
			return script ;
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
