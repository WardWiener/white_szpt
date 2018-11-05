/**
 * 
 */
package com.taiji.pubsec.szpt.solr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.junit.Test;

import com.taiji.pubsec.szpt.solr.util.SolrConstant;

/**
 * @author cly
 *
 */
public class SolrHelperAddTest {
	//添加航班信息
	@Test
	public void add() throws DateParseException{
		String idcard = "130425199001263419";
		String id = "001";
		String idOut = "out001";
		String mac = "AA-BB-CC-DD";
		String startTime = "2017-03-12 11:20:14";
		String endTime = "2017-03-12 15:20:14";
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		//添加飞机入港
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", SolrHelperTestModel.createArrivalFlight(id, idcard, startTime, endTime, null));
		//飞机出港
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", SolrHelperTestModel.createOutFlight(idOut, idcard, startTime, endTime, null));
		//火车进站
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", SolrHelperTestModel.createInTrainticket(id, idcard, startTime, null));
		//火车出站
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", SolrHelperTestModel.createInTrainticket(idOut, idcard, startTime, null));
		//wifi轨迹1
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", SolrHelperTestModel.createWifiTrack(id, mac, startTime, endTime, null, "26.5102,106.68344", null));
		//wifi轨迹2
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", SolrHelperTestModel.createWifiTrack(idOut, mac, startTime, endTime, null, "26.607146,106.629461", null));
		//网吧
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_CYBERCAFE, "id", SolrHelperTestModel.createCybercafe(id, idcard, startTime, endTime, null, null));
		//旅馆
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_ACCOMMODATION, "id", SolrHelperTestModel.createAccommodation(id, idcard, startTime, endTime, null, null));
	}
	
	@Test
	public void add1() throws DateParseException{
		String idcard = "130425199101263418";
		String id = "0002";
		String idOut = "out001";
		String mac = "11-22-33-44-55-66";
		String startTime = "2017-05-01 11:20:14";
		String endTime = "2017-05-01 15:20:14";
		
		System.setProperty("solr.zkhost", "zookeeper01.szpt.jk:2181,zookeeper02.szpt.jk:2181,zookeeper03.szpt.jk:2181/solr");

		String startTime1 = "2017-05-02 11:20:14";
		String endTime1 = "2017-05-02 15:20:14";
		
		String startTime2 = "2017-05-03 11:20:14";
		String endTime2= "2017-05-03 15:20:14";
		
		String startTime3 = "2017-05-04 11:20:14";
		String endTime3 = "2017-05-04 15:20:14";
		
		String startTime4 = "2017-05-05 11:20:14";
		String endTime4 = "2017-05-05 15:20:14";
		
		String startTime5 = "2017-05-06 11:20:14";
		String endTime5 = "2017-05-06 15:20:14";
		
		String startTime6 = "2017-05-07 11:20:14";
		String endTime6 = "2017-05-07 15:20:14";
		
		String startTime7 = "2017-05-08 11:20:14";
		String endTime7 = "2017-05-08 15:20:14";
		
		String startTime8 = "2017-05-09 11:20:14";
		String endTime8 = "2017-05-09 15:20:14";
		
		String startTime9 = "2017-05-10 11:20:14";
		String endTime9 = "2017-05-10 15:20:14";
		
		String startTime10 = "2017-05-11 11:20:14";
		String endTime10 = "2017-05-11 15:20:14";
		
		//添加飞机入港
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", SolrHelperTestModel.createArrivalFlight(id, idcard, startTime, endTime, null));
		//飞机出港
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", SolrHelperTestModel.createOutFlight(idOut, idcard, startTime1, endTime1, null));
		//火车进站
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", SolrHelperTestModel.createInTrainticket(id, idcard, startTime3, null));
		//火车出站
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", SolrHelperTestModel.createInTrainticket(idOut, idcard, startTime4, null));
		//wifi轨迹1
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", SolrHelperTestModel.createWifiTrack(id, mac, startTime5, endTime5, null, "26.5102,106.68344", null));
		//wifi轨迹2
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", SolrHelperTestModel.createWifiTrack(idOut, mac, startTime6, endTime6, null, "26.607146,106.629461", null));
		//wifi轨迹3
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", SolrHelperTestModel.createWifiTrack(id+"ceshi01", mac, startTime10, endTime10, null, "26.637146,106.619461", null));
		//wifi轨迹4
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", SolrHelperTestModel.createWifiTrack(idOut+ "ceshi02", mac, startTime9, endTime9, null, "26.627146,106.649461", null));

		//网吧
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_CYBERCAFE, "id", SolrHelperTestModel.createCybercafe(id, idcard, startTime7, endTime7, null, null));
		//旅馆
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_ACCOMMODATION, "id", SolrHelperTestModel.createAccommodation(id, idcard, startTime8, endTime8, null, null));
	}
	
	@Test
	public void addWifiTrack() throws DateParseException{
		String[] id = {"001","002","003","004","005","006"};
		String[] mac = {"48-4D-7E-BA-6D-AC","48-4D-7E-BA-6D-AC","48-4D-7E-BA-6D-AC","41-5F-C2-CE-CC-F3","E2-31-3E-BB-C1-E3","C1-3D-2F-F2-EE-5C"};
		//开始时间，结束时间在案发时间的基础上加8小时
		String[] startTime = {"2016-09-01 24:40:00","2016-03-21 18:40:00","2016-09-17 10:40:00","2016-03-21 18:40:00","2016-03-21 18:40:00","2016-03-23 12:40:00"};
		String[] endTime = {"2016-09-02 01:40:00","2016-03-21 20:40:00","2016-09-17 12:40:00","2016-03-21 20:40:00","2016-03-21 20:40:00","2016-03-23 15:40:00"};
		String[] latlon = {"26.512097,106.693631","26.519466,106.697184","26.526974,106.698009","26.520119,106.701794","26.516044,106.695222","26.521443,106.699535"};
		String[] placename = {"泛亚华为双语幼儿园","泛亚华为美容养生会所门口","泛亚华为龙腾国际门口","金嘉丽钻石","泛亚华为电信营业厅门口","六合香"};
		String[] placecode = {"5201042C002227","5201042D002249","52010429002275","5201042A004389","5201042B002244","52010427000980"};
		
		System.setProperty("solr.zkhost", "zookeeper01.szpt.jk:2181,zookeeper02.szpt.jk:2181,zookeeper03.szpt.jk:2181/solr");
		
		for(int i=0;i<6;i++){
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", SolrHelperTestModel.createWifiTrack(id[i], mac[i], startTime[i], endTime[i], placename[i], latlon[i], placecode[i]));
		}
		
//		String id = "0014";
//		String mac = "CC-CC-CC-CC-CC-CC";
//		String startTime = "2016-03-23 12:40:00";
//		String endTime = "2016-03-23 15:40:00";
//		String latlon = "26.50719747877184,106.67592610008185";
//		String placename = "测试wifi监控点1";
//		String placecode = "00003";
//		String address = "贵阳市小河区长江路17号";
	
//		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_POINT, "id", SolrHelperTestModel.createWifiPoint(placecode, address, placename, latlon));
	}
}
