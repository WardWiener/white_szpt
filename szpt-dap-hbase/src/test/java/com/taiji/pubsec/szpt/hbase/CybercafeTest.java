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
public class CybercafeTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CybercafeTest.class);
	
	@Test
	public void testAddHSKP(){

		String idcard = "130412199001233418" ;
		String cybercafename = "飞宇网吧" ;
		String cybercafecode = "feiyuwangba" ;
		String cybercafeaddress = "淮南市前锋路" ;
		Calendar arriveattimeCal = Calendar.getInstance() ;
		arriveattimeCal.set(2018, 6, 7);
		String entertime = String.valueOf(new Date().getTime()) ;
		String leavetime = String.valueOf(arriveattimeCal.getTime().getTime()) ;
		
		String terminalnum = "tno22" ;
		
		String rowKey = HbaseConstant.getRowKeyOfCybercafe(idcard, entertime, leavetime, cybercafecode) ;
		
		List<Put> puts = new ArrayList<Put>() ;
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue(),
				HbaseConstant.Cybercafe.idcard.getValue(),
				idcard));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue(),
				HbaseConstant.Cybercafe.cybercafename.getValue(),
				cybercafename));	
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue(),
				HbaseConstant.Cybercafe.cybercafecode.getValue(),
				cybercafecode));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue(),
				HbaseConstant.Cybercafe.cybercafeaddress.getValue(),
				cybercafeaddress));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue(),
				HbaseConstant.Cybercafe.entertime.getValue(),
				entertime));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue(),
				HbaseConstant.Cybercafe.leavetime.getValue(),
				leavetime));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue(),
				HbaseConstant.Cybercafe.terminalnum.getValue(),
				terminalnum));
		
		HBaseHelper.addDatas(HbaseConstant.Cybercafe.TABLE_NAME.getValue(), puts) ;
	}
	
	
	@Test
	public void testSearch(){
		
		Calendar startCal = Calendar.getInstance() ;
		startCal.set(2016, 1, 1);
		
		Calendar endCal = Calendar.getInstance() ;
		endCal.set(2019, 1, 1);
		
		String start_rowkey = HbaseConstant.getRowKeyOfTrainTicket("130412199001233418", String.valueOf(startCal.getTime().getTime()), null) ;
		String end_rowkey = HbaseConstant.getRowKeyOfTrainTicket("130412199001233418", String.valueOf(endCal.getTime().getTime()), null) ;
		
		ResultScanner rs = null;
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(start_rowkey));
		scan.setStopRow(Bytes.toBytes(end_rowkey));
		
		try {
			
			Table table = HBaseHelper.getConn().getTable(TableName.valueOf(HbaseConstant.Cybercafe.TABLE_NAME.getValue()));
			rs = table.getScanner(scan);
			
			LOGGER.debug("********************************************************************");
			
			for (Result r : rs) {
				String rowKey = Bytes.toString(r.getRow()) ;
				String idcard = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Cybercafe.idcard.getValue()))) ;
				String hotelname = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Cybercafe.cybercafename.getValue()))) ;
				String hotelcode = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Cybercafe.cybercafecode.getValue()))) ;
				String hoteladdress = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Cybercafe.cybercafeaddress.getValue()))) ;
				String entertime = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Cybercafe.entertime.getValue()))) ;
				String leavetime = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Cybercafe.leavetime.getValue()))) ;
				String roomnum = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Cybercafe.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Cybercafe.terminalnum.getValue()))) ;
						
				LOGGER.debug("列：{}，列值：{}", "rowKey", rowKey);
				
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Cybercafe.idcard.getValue(), idcard);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Cybercafe.cybercafename.getValue(), hotelname);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Cybercafe.cybercafecode.getValue(), hotelcode);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Cybercafe.cybercafeaddress.getValue(), hoteladdress);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Cybercafe.entertime.getValue(), entertime);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Cybercafe.leavetime.getValue(), leavetime);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Cybercafe.terminalnum.getValue(), roomnum);
			
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
