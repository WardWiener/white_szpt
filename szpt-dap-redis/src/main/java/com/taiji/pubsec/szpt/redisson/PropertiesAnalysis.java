package com.taiji.pubsec.szpt.redisson;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class PropertiesAnalysis implements Serializable{
	private static final long serialVersionUID = 7713704696617854996L;
	static String CONFIG_FILENAME = "redis.properties";

	
	public String analyze(String fileSuffix) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(CONFIG_FILENAME);

		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (Exception e) {
			return null;
		}
		return pro.getProperty(fileSuffix);

	}
}
