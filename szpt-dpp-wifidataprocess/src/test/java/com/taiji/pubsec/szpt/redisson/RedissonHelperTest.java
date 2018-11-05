/**
 * Copyright 2017 Taiji
 * All right reserved.
 * Created on 2017年1月19日 下午2:35:25
 */
package com.taiji.pubsec.szpt.redisson;

import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;
import org.redisson.api.RMap;

/**
 * @author yucy
 *
 */
public class RedissonHelperTest {

	@Test
	public void testAddData(){
		RMap<String, SomeObject> map = RedissonHelper.getClient().getMap("testmap");
		map.clear();
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
		for(Entry<String, SomeObject> entry : map.getAll(set).entrySet()){
			System.out.println(entry.getValue());
		}
	}
}
