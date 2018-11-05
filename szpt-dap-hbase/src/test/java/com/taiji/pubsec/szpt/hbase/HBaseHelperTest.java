/**
 * 
 */
package com.taiji.pubsec.szpt.hbase;

import org.junit.Test;

/**
 * @author sunjd
 *
 */
public class HBaseHelperTest {

	@Test
	public void createTables(){
		HBaseHelper.createTable("wifitrack", "stay");
		HBaseHelper.createTable("accommodation", "info");
		HBaseHelper.createTable("cybercafe", "info");
		HBaseHelper.createTable("flight", "info");
		HBaseHelper.createTable("trainticket", "info");
		HBaseHelper.createTable("tenement", "info");
	}
}
