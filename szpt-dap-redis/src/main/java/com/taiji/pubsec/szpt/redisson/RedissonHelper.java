/**
 * Copyright 2017 Taiji
 * All right reserved.
 * Created on 2017年1月19日 上午10:40:23
 */
package com.taiji.pubsec.szpt.redisson;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;


/**
 * @author yucy
 *
 */
public class RedissonHelper implements Serializable {

	private static final long serialVersionUID = 5714637377490686064L;
	
	static Config config = null;
	static RedissonClient redisson = null;
	static String CONFIG_FILENAME = "redisson-config.yaml";
	
	static{
		String clusters = new PropertiesAnalysis().analyze("redis.cluster");
		if (clusters == null) {
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			InputStream is = classLoader.getResourceAsStream(CONFIG_FILENAME);
			Config config;
			try {
				config = Config.fromYAML(is);
				redisson = Redisson.create(config);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			String[] custersArray = clusters.split(","); 
			Config config = new Config();
			config.useClusterServers()
			.setScanInterval(Integer.valueOf(new PropertiesAnalysis().analyze("scanInterval")))
			.addNodeAddress(custersArray);
			redisson = Redisson.create(config);
		}
	}

	public static RedissonClient getClient(){
		return redisson;
	}
}
