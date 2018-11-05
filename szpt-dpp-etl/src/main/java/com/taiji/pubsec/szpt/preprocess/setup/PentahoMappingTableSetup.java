/**
 * Copyright 2017 Taiji
 * All right reserved.
 * Created on 2017年1月21日 下午8:11:22
 */
package com.taiji.pubsec.szpt.preprocess.setup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.taiji.pubsec.szpt.hbase.HBaseHelper;

import net.sf.json.JSONObject;

/**
 * @author yucy
 *
 */
public class PentahoMappingTableSetup {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if( null == args || args.length < 1){
			printhelp();
			return;
		}
		String command = args[0];
		
		if( "backup".equals(command) ){
			if( args.length < 3 ){
				System.out.println("backup pentaho_mappings table : XXX backup [tablename] [filepath]");
				return;
			}
			String tablename = args[1];
			String filepath = args[2];
			backupEtlConfig(tablename, filepath);
			return;
		}
		
		if( "create".equals(command) ){
			if( args.length < 2 ){
				System.out.println("create pentaho_mappings table : XXX create [tablename]");
				return;
			}
			String tablename = args[1];
			createPentahoTable(tablename);
			return;
		}
		
		if( "setup".equals(command) ){
			if( args.length < 3 ){
				System.out.println("setup pentaho_mappings table : XXX setup [tablename] [filepath]");
				return;
			}
			String tablename = args[1];
			String filepath = args[2];
			setupPentahoMappings(tablename, filepath);
			return;
		}
		
		printhelp();
	}

	private static void printhelp() {
		System.out.println("backup pentaho_mappings table : XXX backup [tablename] [filepath]");
		System.out.println("create pentaho_mappings table : XXX create [tablename]");
		System.out.println("setup pentaho_mappings table : XXX setup [tablename] [filepath]");
	}

	private static void backupEtlConfig(String tablename, String filepath) throws IOException {
		Scan scan = new Scan();

		ResultScanner rs = null;
		try {
			Map<String, Map<String, Map<String, String>>> datas = new HashMap<>();
			Table table = HBaseHelper.getConn().getTable(TableName.valueOf(tablename));
			rs = table.getScanner(scan);
			for (Result r : rs) {
				String rowkey = Bytes.toString(r.getRow());
				Map<String, Map<String, String>> row = datas.get(rowkey);
				if (null == row) {
					row = new HashMap<>();
					datas.put(rowkey, row);
				}

				List<Cell> cells = r.listCells();
				for (Cell cell : cells) {
					String famliy = Bytes.toString(cell.getFamily());
					String colum = Bytes.toString(cell.getQualifier());
					String value = Bytes.toString(cell.getValue());

					Map<String, String> cellmap = row.get(famliy);
					if (null == cellmap) {
						cellmap = new HashMap<>();
						row.put(famliy, cellmap);
					}
					cellmap.put(colum, value);
				}
			}

			File backfile = new File(filepath);
			if(backfile.exists()){
				backfile.delete();
			}
			backfile.createNewFile();
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(backfile));
			String json = JSONObject.fromObject(datas).toString(2);
			System.out.println(json);
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != rs) {
				rs.close();
			}
		}
	}

	private static void setupPentahoMappings(String table, String filepath) {
		File backfile = new File(filepath);

		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
            reader = new BufferedReader(new FileReader(backfile));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
               sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		
		JSONObject jsonObj = JSONObject.fromObject(sb.toString());
		List<Put> puts = new ArrayList<Put>();

		for(Object objrow : jsonObj.entrySet()){
			Entry row = (Entry)objrow;
			String rowkey = (String) row.getKey();
			JSONObject familyjo = (JSONObject) row.getValue();
			for(Object objcolom : familyjo.entrySet()){
				Entry family = (Entry)objcolom;
				String famname = (String) family.getKey();
				JSONObject coljo = (JSONObject) family.getValue();
				for(Object objcol : coljo.entrySet()){
					Entry col = (Entry)objcol;
					String colname = (String) col.getKey();
					String value = (String) col.getValue();
					
					Put put = new Put( Bytes.toBytes(rowkey) );
					put.add(Bytes.toBytes(famname), Bytes.toBytes(colname), Bytes.toBytes(value));
					puts.add(put);
//					System.out.println("rk : " + rowkey + " | fam : " + famname + " | " + "column : " + colname + " | value : " + value);
				}
			}
		}
		try {
			HBaseHelper.batchAddDatas(table, puts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void createPentahoTable(String tablename){
		Admin admin;
		try {
			admin = HBaseHelper.getConn().getAdmin();
			TableName tn = TableName.valueOf(tablename);
			System.out.println(tn.toString());
			HTableDescriptor desc = new HTableDescriptor(tn);
			System.out.println(desc.toStringTableAttributes());

			desc.addFamily(new HColumnDescriptor("columns"));
			desc.addFamily(new HColumnDescriptor("key"));
			if (admin.tableExists(tn)) {
				System.out.println("Hbase表：{}已经存在");
			} else {
				admin.createTable(desc);
				System.out.println("Hbase表：{}成功");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
