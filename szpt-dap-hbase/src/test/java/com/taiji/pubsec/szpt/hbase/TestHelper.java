package com.taiji.pubsec.szpt.hbase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class TestHelper {

	public static Put makePutByString(String rowKey, String columnFamily, String columnName, String columnValue){
		
		Put put = new Put(Bytes.toBytes(rowKey));
		put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columnName), Bytes.toBytes(columnValue));
		
		return put ;
	}
	
}
