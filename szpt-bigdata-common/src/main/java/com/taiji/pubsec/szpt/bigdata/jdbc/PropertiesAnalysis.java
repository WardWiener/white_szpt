package com.taiji.pubsec.szpt.bigdata.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class PropertiesAnalysis implements Serializable{
	private static final long serialVersionUID = 7713704696617854996L;

	public String analyze(String fileSuffix) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream("bigdata.properties");

		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro.getProperty(fileSuffix);

	}
}
