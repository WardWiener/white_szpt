package com.taiji.pubsec.szpt.redisson;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import com.taiji.pubsec.szpt.redisson.util.RedisConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RSetCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
/**
 * @author yucy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class RedissonHelperTest {

	private static Logger LOGGER = LoggerFactory.getLogger(RedissonHelperTest.class) ;

	@Test
	public void testWifiGetAllData(){
		RMapCache<String, String> maptrack = RedissonHelper.getClient().getMapCache(RedisConstant.CURRENT_MAC_WIFITRACK.RMAP_KEY.getValue());
		for(Entry<String, String> entry : maptrack.readAllEntrySet()){
			LOGGER.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&") ;
			LOGGER.debug("key:{}", entry.getKey());
			LOGGER.debug("value:{}", entry.getValue());
			LOGGER.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&") ;
		}
	}

	@Test
	public void testAddData(){
		RMap<String, SomeObject> map = RedissonHelper.getClient().getMap("testmap");
		long start = System.currentTimeMillis();
		for(int i=0; i<100; i++){
			String k = "str第" + i + "个";
			map.fastPut(k, new SomeObject(k, (long)i));
		}
		long end = System.currentTimeMillis();
		System.out.println("用时：" + (end - start) );
	}
	
	@Test
	public void testGetData(){
		Set<String> set = new HashSet<>();
		set.add("str第7个");
		set.add("str第17个");
		set.add("str第27个");
		set.add("str第37个");
		set.add("str第47个");
		set.add("str第57个");

		RMap<String, SomeObject> map = RedissonHelper.getClient().getMap("testmap");
		System.out.println("get : " + map.get("str第99个"));
		for(Entry<String, SomeObject> entry : map.getAll(set).entrySet()){
			System.out.println(entry.getValue());
		}
	}
	
	@Test
	public void testGetPointMacData(){

		RSetCache<String> macs  = RedissonHelper.getClient().getSetCache("current_point_52011325000410");
		System.out.println("get readall size: " + macs.readAll().size()); //按过期计算数量
		System.out.println("get size: " + macs.size());//redis实际存储数量
		Iterator<String> it = macs.iterator();
		while(it.hasNext()){
			System.out.println("mac : " + it.next());
			
		}
		
//		getMapCache<String,> macs  = RedissonHelper.getClient().getMapCache("current_mac_wifitrack");
////		System.out.println("get : " + map.get("str第99个"));
//		Iterator<String> it = macs.iterator();
//		while(it.hasNext()){
//			System.out.println("mac : " + it.next());
//			
//		}
		
//		RSet<String> tests = RedissonHelper.getClient().getSet("test");
//		System.out.println("get test data : " + tests);

	}
	
	@Test
	public void testClearAll(){

		RMap<String, Set<String>> mappoint = RedissonHelper.getClient().getMap("current_point_macs");
		mappoint.clear();
		RMap<String, Set<String>> wifitrack = RedissonHelper.getClient().getMap("current_mac_wifitrack");
		wifitrack.clear();
		RMap<String, Set<String>> m1 = RedissonHelper.getClient().getMap("hello");
		m1.clear();
		RMap<String, Set<String>> m2 = RedissonHelper.getClient().getMap("test");
		m2.clear();
		RMap<String, Set<String>> m3 = RedissonHelper.getClient().getMap("anymap");
		m3.clear();
		RMap<String, Set<String>> m4 = RedissonHelper.getClient().getMap("testmap");
		m4.clear();


	}
}
