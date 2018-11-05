package com.taiji.pubsec.szpt.kafka;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class KafkaTestCase {

	@Resource
	private KafkaProducer caseComputeProducer;
	
	
	@Test
	public void testSendMessage() {
		//caseComputeProducer.sendData("topic-wifihptrack", "1020002	B0-E2-35-43-05-9F	test1	5	102	B0-E2-35-43-05-9F		B0-E2-35-43-05-9F	72300510494885E0A00C1		1476084014	1476084125	167772224	30206725		16320	16575	1	65535	AA-AA-AA-AA-AA-AA	72300510494885E0A00C1	94-88-5E-0A-00-C1	26.688139	106.642342	52011325000410B0E23543059F1482226983	-63									1476084014".getBytes());
		caseComputeProducer.sendData("topic-wifihptrack", "{\"id\":\"1020002\",\"mac\":\"AA-AA-AA-AA-AA-AA\",\"enterTime\":1476084014,\"leaveTime\":1476084125,\"gatherTime\":167772224,\"createTime\":30206725,\"placeId\":\"52011325000410\",\"placeName\":\"同心路47号，51栋对面3单元\",\"latitude\":26.688139,\"longitude\":106.642342,\"idcode\":\"520102197006265029\",\"fiveColorPersonName\":\"孙黔飞\",\"peopleType\":[],\"warnType\":\"0\",\"noupdateCount\":0,\"residentialArea\":true,\"theft\":true}".getBytes());
	}
	
	@Test
	public void testFindTemplateById() {
		
	}
	
	
	
}
