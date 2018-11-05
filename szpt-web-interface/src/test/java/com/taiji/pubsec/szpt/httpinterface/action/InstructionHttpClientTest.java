package com.taiji.pubsec.szpt.httpinterface.action;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.szpt.httpinterface.action.bean.InstructionReceiveSubjectBean;
import com.taiji.pubsec.szpt.httpinterface.action.bean.InstructionWsBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class InstructionHttpClientTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InstructionHttpClientTest.class) ;
	
	@SuppressWarnings("unchecked")
	@Test
	public void saveInstructionTest(){
		
		String url = "http://localhost:8080/szpt-web-all/interface/saveInstruction.action" ;
		
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		
		CloseableHttpResponse resp = null ;
	
		InstructionWsBean bean = new InstructionWsBean() ;
		
		bean.setId("123456790000");
		bean.setContent("测试指令下发");
		bean.setCreatePeopleDepartmentCode("520199180000");
		bean.setCreatePeopleCode("006543");
		bean.setCreateTime(new Date().getTime());
		bean.setIsNofityDepartmentLeader(1);
		bean.setType("0000000011004");
		
		List<InstructionReceiveSubjectBean> sus = new ArrayList<InstructionReceiveSubjectBean>();
		
		InstructionReceiveSubjectBean feedbook =  new InstructionReceiveSubjectBean();
		feedbook.setReceiveSubjectId("76e82277-9ef2-4c01-b223-454048e3db9c");
		feedbook.setFeedbackTime(new Date().getTime()+12*60*60);
		feedbook.setReceiveSubjectType(Unit.class.getName());
		sus.add(feedbook);
		
		bean.setSubs(sus);

		try {
			String jsonStr = getMapper().writeValueAsString(bean);
			StringEntity entity = new StringEntity(jsonStr, "utf-8");//解决中文乱码问题    
			entity.setContentEncoding("UTF-8");  
			entity.setContentType("application/json");    
			httpPost.setEntity(entity);
			
			resp = client.execute(httpPost);
			
			HttpEntity he = null ;
			
			if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				he = resp.getEntity();	
				String respContent = EntityUtils.toString(he, Charset.forName("UTF-8"));
				
				Map<String, Object> respMap = getMapper().readValue(respContent, Map.class) ;
				
				LOGGER.debug("respContent String：{}", respContent);
				
				LOGGER.debug("respContent map：{}", respMap);
			}else{
				LOGGER.debug("HttpStatus code：{}", resp.getStatusLine().getStatusCode());
				LOGGER.debug("HttpStatus reason：{}", resp.getStatusLine().getReasonPhrase());
			}
		
			printResponse(resp) ;
			
			if(he!=null){
				EntityUtils.consume(he);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resp!=null){
					resp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void szptSaveInstructionTest(){
		
		String url = "http://localhost:8081/szpt-web-all/interface/szptSaveInstruction.action" ;
		
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient client = HttpClients.createDefault();
		
		CloseableHttpResponse resp = null ;
		
		InstructionWsBean bean = new InstructionWsBean() ;
		bean.setId("12345678");
		bean.setContent("adfadsf");
		//bean.setCreatePeopleDepartmentCode("520199630000");
		//bean.setCreatePeopleCode("042072");
		bean.setCreateTime(new Date().getTime());
		bean.setIsNofityDepartmentLeader(1);
		
		try {
			String jsonStr = getMapper().writeValueAsString(bean);
			StringEntity entity = new StringEntity(jsonStr, "utf-8");//解决中文乱码问题    
			entity.setContentEncoding("UTF-8");  
			entity.setContentType("application/json");    
			httpPost.setEntity(entity);
			
			resp = client.execute(httpPost);
			
			HttpEntity he = null ;
			
			if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				he = resp.getEntity();	
				String respContent = EntityUtils.toString(he, Charset.forName("UTF-8"));
				
				Map<String, Object> respMap = getMapper().readValue(respContent, Map.class) ;
				
				LOGGER.debug("respContent String：{}", respContent);
				
				LOGGER.debug("respContent map：{}", respMap);
			}else{
				LOGGER.debug("HttpStatus code：{}", resp.getStatusLine().getStatusCode());
				LOGGER.debug("HttpStatus reason：{}", resp.getStatusLine().getReasonPhrase());
			}
			
			printResponse(resp) ;
			
			if(he!=null){
				EntityUtils.consume(he);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(resp!=null){
					resp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private ObjectMapper getMapper(){
		return new ObjectMapper();
	}
	
	private void printResponse(HttpResponse response)throws IOException {
		for (Header header : response.getAllHeaders()) {
			LOGGER.debug("Header---> {}", header.getName() + ":" + header.getValue());
		}
		LOGGER.debug("StatusLine：{}", response.getStatusLine().toString());
		LOGGER.debug("StatusCode：{}", String.valueOf(response.getStatusLine().getStatusCode()));
		LOGGER.debug("ContentType：{}", String.valueOf(response.getEntity().getContentType().toString()));
		LOGGER.debug("ContentType Name：{}", String.valueOf(response.getEntity().getContentType().getName()));
		LOGGER.debug("ContentType Value：{}", String.valueOf(response.getEntity().getContentType().getValue()));
		LOGGER.debug("ContentLength：{}", response.getEntity().getContentLength());
	}
	
}
