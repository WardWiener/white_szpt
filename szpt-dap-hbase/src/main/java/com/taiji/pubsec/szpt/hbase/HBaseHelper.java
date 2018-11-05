/**
 * Copyright 2016 Taiji
 * All right reserved.
 * Created on 2016年11月28日 上午11:59:25
 */
package com.taiji.pubsec.szpt.hbase;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yucy
 *
 */
public class HBaseHelper implements Serializable{
	private static final long serialVersionUID = -4434842893700993876L;

	private static final Logger logger = LoggerFactory.getLogger(HBaseHelper.class);
	
	// 声明静态配置
	static Configuration conf = null;
	static Connection conn = null;
	
//	static String zkURL ="172.19.0.214,172.19.0.215,172.19.0.216,172.19.0.217,172.19.0.218";
	static {
		conf = HBaseConfiguration.create();
		logger.info("hbase.zookeeper.quorum : {}", conf.get("hbase.zookeeper.quorum"));
		logger.info("hbase.zookeeper.property.clientPort : {}", conf.get("hbase.zookeeper.property.clientPort"));
		try {
			conn = ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			logger.error("初始化Hbase连接池失败", e);
		}
	}
	
	public static Connection getConn(){
		return conn;
	}

	/*
	 * CREATE TABLE
	 * 
	 * @param tableName
	 * 
	 * @param family
	 */
	public static void createTable(String tableName, String family) {
		Admin admin;
		try {
			admin = conn.getAdmin();
			TableName tn = TableName.valueOf(tableName);
			System.out.println(tn.toString());
			HTableDescriptor desc = new HTableDescriptor(tn);
			System.out.println(desc.toStringTableAttributes());

			desc.addFamily(new HColumnDescriptor(family));
			if (admin.tableExists(tn)) {
				logger.debug("Hbase表：{}已经存在", tableName);
			} else {
				admin.createTable(desc);
				logger.debug("创建Hbase表：{},成功", tableName);
			}
		} catch (IOException e) {
			logger.error("创建Hbase表" + tableName + "失败", e);
		}

	}

	/*
	 * add one data
	 * 
	 * @param rowKey
	 * 
	 * @param tablename
	 * 
	 * @param family
	 * 
	 * @param column
	 * 
	 * @param value
	 * 
	 */
	public static void addData(String rowKey, String tableName, String family, String column, String value)
			throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
		
		Put put = new Put(Bytes.toBytes(rowKey));
		put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
		
		table.put(put);
	}

	public static void addDatas(String tableName, List<Put> puts) {
		try {
			Table table = conn.getTable(TableName.valueOf(tableName));
			table.put(puts);
		} catch (IOException e) {
			logger.error("数据写入Hbase表:" + tableName + "失败", e);
			createTable(tableName, "info");
		} catch (NullPointerException e2) {
			try {
				Table table = conn.getTable(TableName.valueOf(tableName));
				table.put(puts);
			} catch (IOException e1) {
				logger.error("数据再次写入Hbase表:" + tableName + "失败", e1);
			}

		}

	}
	
	public static void batchAddDatas(String tableName, List<Put> puts) throws IOException{
		Table table = conn.getTable(TableName.valueOf(tableName));
		BufferedMutator mutator = conn.getBufferedMutator(TableName.valueOf(tableName));
		try{
			mutator.mutate(puts);
			mutator.flush();
		}finally{
			if(null != mutator) mutator.close();
			if(null != table) table.close();
		}
	}

	public static void addDatas(Map<String, List<Map<String, Object>>> data) {
		for (Entry<String, List<Map<String, Object>>> entry : data.entrySet()) {
			List<Put> puts = new ArrayList<Put>();
			for (Map<String, Object> map : entry.getValue()) {
				String rowKey = (String) map.get("id");
				if (rowKey ==null) {
					logger.error("插入的数据没有指定行健：{}", map);
					continue;
				}
				Put put = new Put(Bytes.toBytes(rowKey));
				for (Entry<String, Object> e : map.entrySet()) {
					if (!"id".equals(e.getKey())) {
						String value = (String) e.getValue();
						if (value != null) {
							put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(e.getKey()), Bytes.toBytes(value));
						}
						
					}
				}
				puts.add(put);
			}
			addDatas(entry.getKey(), puts);
		}
	}

	/*
	 * query data by rwokey
	 * 
	 * @param rowKey
	 * 
	 * @param tablename
	 */
	public static Result getResult(String tableName, String rowKey) throws IOException {
		Get get = new Get(Bytes.toBytes(rowKey));
		Table table = conn.getTable(TableName.valueOf(tableName));
		Result result = table.get(get);
		return result;
	}

	/*
	 * Traversal query hbase table
	 * 
	 * @param tablename
	 */
	public static ResultScanner getResultScann(String tableName) throws IOException {
		Scan scan = new Scan();
		ResultScanner rs = null;
		Table table = conn.getTable(TableName.valueOf(tableName));
		try {
			rs = table.getScanner(scan);
		} finally {
			rs.close();
		}
		return rs;
	}

	/*
	 * Traversal query hbase table range
	 * 
	 * @param tablename
	 * 
	 * @param start_rowkey
	 * 
	 * @param stop_rewkey
	 */
	public static ResultScanner getResultScann(String tableName, String start_rowkey, String stop_rowkey, Filter filter)
			throws IOException {
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(start_rowkey));
		scan.setStopRow(Bytes.toBytes(stop_rowkey));
		if( null != filter){
			scan.setFilter(filter);
		}
		ResultScanner rs = null;
		Table table = conn.getTable(TableName.valueOf(tableName));
		try {
			rs = table.getScanner(scan);
		} finally {
			rs.close();
		}
		return rs;
	}

	/*
	 * get one column
	 * 
	 * @param tablename
	 * 
	 * @param rowKey
	 * 
	 * @param familyName
	 * 
	 * @param columnName
	 */
	public static Result getResultByColumn(String tableName, String rowKey, String familyName, String columnName)
			throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)); //
		Result result = table.get(get);
		return result;
	}

	/*
	 * update one column
	 * 
	 * @param tablename
	 * 
	 * @param rowKey
	 * 
	 * @param familyName
	 * 
	 * @param columnName
	 * 
	 * @param value
	 */
	public static void updateTable(String tableName, String rowKey, String familyName, String columnName, String value)
			throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
		put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));
		table.put(put);
		logger.debug("更新表{}数据{}成功!", tableName, rowKey + ", " + columnName + ", " + value);
	}

	/*
	 * get data by version
	 * 
	 * @param tablename
	 * 
	 * @param rowKey
	 * 
	 * @param familyName
	 * 
	 * @param columnName
	 */
	public static Result getResultByVersion(String tableName, String rowKey, String familyName, String columnName)
			throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
		get.setMaxVersions(5);
		Result result = table.get(get);
		return result;
	}

	/*
	 * delete data by column
	 * 
	 * @param tablename
	 * 
	 * @param rowKey
	 * 
	 * @param familyName
	 * 
	 * @param columnName
	 */
	public static void deleteColumn(String tableName, String rowKey, String falilyName, String columnName)
			throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
		Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
		deleteColumn.deleteColumns(Bytes.toBytes(falilyName), Bytes.toBytes(columnName));
		table.delete(deleteColumn);
		logger.debug("列族{}的列{}被删除了!", falilyName, columnName );
	}

	/*
	 * delete one column by rowkey
	 * 
	 * @param tablename
	 * 
	 * @param rowKey
	 */
	public static void deleteAllColumn(String tableName, String rowKey) throws IOException {
		Table table = conn.getTable(TableName.valueOf(tableName));
		Delete deleteAll = new Delete(Bytes.toBytes(rowKey));
		table.delete(deleteAll);
	}

	/*
	 * delete table
	 * 
	 * @param tablename
	 */
	public static void deleteTable(String tableName) throws IOException {
		Admin admin = conn.getAdmin();
		admin.disableTable(TableName.valueOf(tableName));
		admin.deleteTable(TableName.valueOf(tableName));
		logger.debug("表{}被删除了!", tableName );
	}

}