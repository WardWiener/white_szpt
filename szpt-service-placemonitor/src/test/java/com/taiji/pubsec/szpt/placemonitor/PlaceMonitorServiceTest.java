package com.taiji.pubsec.szpt.placemonitor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMapCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.pubsec.dpp.wifi.bean.WifiData;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceMonitorService;
import com.taiji.pubsec.szpt.redisson.RedissonHelper;
import com.taiji.pubsec.szpt.test.TestBase;

import net.sf.json.JSONObject;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
//@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
//@Rollback(false)
public class PlaceMonitorServiceTest extends TestBase{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PlaceMonitorServiceTest.class);
	@Resource
	PlaceMonitorService placeMonitorService;
	
	@Test
	public void testFindWifiRecordInRealTimeByMac() {
		
		List<Map<String, Object>> result = placeMonitorService.findWifiRecordInRealTimeByMac("GG-GG-GG-GG-GG-GG");
		LOGGER.info("result size {}", result.size());
		Assert.assertEquals(1, result.size());
		for(Map.Entry<String, Object> entry : result.get(0).entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	@Test
	public void testFindWifiRecordInRealTimeByPlace() {
		List<Map<String, Object>> result = placeMonitorService.findWifiRecordInRealTimeByPlace("5201142B000011");
		System.out.println("result size "+ result.size());
	}

//	@Test
	public void testSendWifiData() {
		RMapCache<String, String> maptrack = RedissonHelper.getClient().getMapCache("current_mac_wifitrack");
		WifiData wifidata = new WifiData();
		wifidata.setMac("FF-FF-FF-FF-FF-FF");
		wifidata.setCreateTime(new Date());
		wifidata.setEnterTime("1482229103000");
		wifidata.setGatherTime("1482236103000");
		wifidata.setLeaveTime("1482229203000");
		wifidata.setLatitude("106.642342");
		wifidata.setLongitude("26.688139");
		wifidata.setNoupdateCount(0);
		wifidata.setPlaceId("52011325000410");
		
		
		//将wifi轨迹放入缓存
		maptrack.fastPut(wifidata.getMac(), JSONObject.fromObject(wifidata).toString(), 3600, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSumupMonitorDeviceCount() {
		int result = placeMonitorService.sumupMonitorDeviceCount();
		System.out.println("result size "+result);
	}
	
	@Test
	public void testSumupDeviceaByPlace() {
		List<PlaceSumInfo> result = placeMonitorService.sumupDeviceaByPlace();
		for(PlaceSumInfo ps : result) {
			System.out.println("placecode:"+ps.getGroupCode());
			System.out.println("placename:"+ps.getGroupName());
			System.out.println("longitude:"+ps.getLongitude());
			System.out.println("latitude:"+ps.getLongitude());
		}
		
	}
	
}
