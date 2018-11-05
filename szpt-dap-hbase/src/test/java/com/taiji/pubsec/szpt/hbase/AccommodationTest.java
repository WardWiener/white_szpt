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
public class AccommodationTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationTest.class);
	
	@Test
	public void testAddHSKP(){

		String idcard = "130412199001233418" ;
		String hotelname = "希尔顿" ;
		String hotelcode = "xierdun" ;
		String hoteladdress = "北京三里屯希尔顿大酒店" ;
		Calendar arriveattimeCal = Calendar.getInstance() ;
		arriveattimeCal.set(2018, 6, 7);
		String entertime = String.valueOf(new Date().getTime()) ;
		String leavetime = String.valueOf(arriveattimeCal.getTime().getTime()) ;
		
		String roomnum = "33333" ;
		
		String rowKey = HbaseConstant.getRowKeyOfAccommodation(idcard, entertime, leavetime, hotelcode) ;
		
		List<Put> puts = new ArrayList<Put>() ;
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Accommodation.COLUMN_FAMILY.getValue(),
				HbaseConstant.Accommodation.idcard.getValue(),
				idcard));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Accommodation.COLUMN_FAMILY.getValue(),
				HbaseConstant.Accommodation.hotelname.getValue(),
				hotelname));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Accommodation.COLUMN_FAMILY.getValue(),
				HbaseConstant.Accommodation.hotelcode.getValue(),
				hotelcode));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Accommodation.COLUMN_FAMILY.getValue(),
				HbaseConstant.Accommodation.hoteladdress.getValue(),
				hoteladdress));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Accommodation.COLUMN_FAMILY.getValue(),
				HbaseConstant.Accommodation.entertime.getValue(),
				entertime));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Accommodation.COLUMN_FAMILY.getValue(),
				HbaseConstant.Accommodation.leavetime.getValue(),
				leavetime));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Accommodation.COLUMN_FAMILY.getValue(),
				HbaseConstant.Accommodation.roomnum.getValue(),
				roomnum));
		
		HBaseHelper.addDatas(HbaseConstant.Accommodation.TABLE_NAME.getValue(), puts) ;
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
			
			Table table = HBaseHelper.getConn().getTable(TableName.valueOf(HbaseConstant.Accommodation.TABLE_NAME.getValue()));
			rs = table.getScanner(scan);
			
			LOGGER.debug("********************************************************************");
			
			for (Result r : rs) {
				String rowKey = Bytes.toString(r.getRow()) ;
				String idcard = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Accommodation.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Accommodation.idcard.getValue()))) ;
				String hotelname = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Accommodation.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Accommodation.hotelname.getValue()))) ;
				String hotelcode = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Accommodation.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Accommodation.hotelcode.getValue()))) ;
				String hoteladdress = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Accommodation.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Accommodation.hoteladdress.getValue()))) ;
				String entertime = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Accommodation.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Accommodation.entertime.getValue()))) ;
				String leavetime = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Accommodation.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Accommodation.leavetime.getValue()))) ;
				String roomnum = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Accommodation.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Accommodation.roomnum.getValue()))) ;
						
				LOGGER.debug("列：{}，列值：{}", "rowKey", rowKey);
				
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Accommodation.idcard.getValue(), idcard);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Accommodation.hotelname.getValue(), hotelname);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Accommodation.hotelcode.getValue(), hotelcode);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Accommodation.hoteladdress.getValue(), hoteladdress);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Accommodation.entertime.getValue(), entertime);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Accommodation.leavetime.getValue(), leavetime);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Accommodation.roomnum.getValue(), roomnum);
			
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
