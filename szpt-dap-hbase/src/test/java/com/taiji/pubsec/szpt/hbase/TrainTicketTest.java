package com.taiji.pubsec.szpt.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class TrainTicketTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrainTicketTest.class);

	@Test
	public void testAdd(){

		String idcard = "130425199001263418" ;
		String trainnumber = "G122" ;
		String startstation = "北京" ;
		String arriveatstation = "上海" ;
		String starttime = String.valueOf(new Date().getTime()) ;
		
		String rowKey = HbaseConstant.getRowKeyOfTrainTicket(idcard, starttime, trainnumber) ;
		
		List<Put> puts = new ArrayList<Put>() ;
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.idcard.getValue(),
				idcard));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.trainnumber.getValue(),
				trainnumber));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.startstation.getValue(),
				startstation));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.arriveatstation.getValue(),
				arriveatstation));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.starttime.getValue(),
				starttime));
		
		HBaseHelper.addDatas(HbaseConstant.TrainTicket.TABLE_NAME.getValue(), puts) ;
	}
	
	@Test
	public void testAddHSKP(){

		String idcard = "130412199001233418" ;
		String trainnumber = "G123" ;
		String startstation = "天津" ;
		String arriveatstation = "上海" ;
		String starttime = String.valueOf(new Date().getTime()) ;
		
		String rowKey = HbaseConstant.getRowKeyOfTrainTicket(idcard, starttime, trainnumber) ;
		
		List<Put> puts = new ArrayList<Put>() ;
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.idcard.getValue(),
				idcard));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.trainnumber.getValue(),
				trainnumber));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.startstation.getValue(),
				startstation));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.arriveatstation.getValue(),
				arriveatstation));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue(),
				HbaseConstant.TrainTicket.starttime.getValue(),
				starttime));
		
		HBaseHelper.addDatas(HbaseConstant.TrainTicket.TABLE_NAME.getValue(), puts) ;
	}
	
	
	@Test
	public void testSearch(){
		
		Calendar startCal = Calendar.getInstance() ;
		startCal.set(2016, 1, 1);
		
		Calendar endCal = Calendar.getInstance() ;
		endCal.set(2018, 1, 1);
		
		String start_rowkey = HbaseConstant.getRowKeyOfTrainTicket("130412199001233418", String.valueOf(startCal.getTime().getTime()), null) ;
		String end_rowkey = HbaseConstant.getRowKeyOfTrainTicket("130412199001233418", String.valueOf(endCal.getTime().getTime()), null) ;
		
		ResultScanner rs = null;
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(start_rowkey));
		scan.setStopRow(Bytes.toBytes(end_rowkey));
		
		try {
			
			Table table = HBaseHelper.getConn().getTable(TableName.valueOf(HbaseConstant.TrainTicket.TABLE_NAME.getValue()));
			rs = table.getScanner(scan);
			
			LOGGER.debug("********************************************************************");
			
			for (Result r : rs) {
				
				String rowKey = Bytes.toString(r.getRow()) ;
				
				String idcard = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.TrainTicket.idcard.getValue()))) ;
				String trainnumber = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.TrainTicket.trainnumber.getValue()))) ;
				String startstation = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.TrainTicket.startstation.getValue()))) ;
				String arriveatstation = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.TrainTicket.arriveatstation.getValue()))) ;
				String starttime = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.TrainTicket.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.TrainTicket.starttime.getValue()))) ;
				
				LOGGER.debug("列：{}，列值：{}", "rowKey", rowKey);
		
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.TrainTicket.idcard.getValue(), idcard);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.TrainTicket.trainnumber.getValue(), trainnumber);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.TrainTicket.startstation.getValue(), startstation);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.TrainTicket.arriveatstation.getValue(), arriveatstation);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.TrainTicket.starttime.getValue(), starttime);
			
				LOGGER.debug("********************************************************************");
			}
			
		} catch (IOException e) {
			LOGGER.error("IOException", e);
		} finally {
			if( null!=rs ){
				rs.close();
			}
		}
	}
}
