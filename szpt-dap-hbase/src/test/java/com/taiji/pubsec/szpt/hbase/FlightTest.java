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
public class FlightTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightTest.class);
	
	@Test
	public void testAddHSKP(){

		String idcard = "130412199001233418" ;
		String flightnumber = "1462" ;
		String takeoffairport = "天津机场" ;
		String arriveatairport = "上海机场" ;
		Calendar arriveattimeCal = Calendar.getInstance() ;
		arriveattimeCal.set(2018, 6, 7);
		String takeofftime = String.valueOf(new Date().getTime()) ;
		String arriveattime = String.valueOf(arriveattimeCal.getTime().getTime()) ;
		
		String seatNum = "36D" ;
		
		String rowKey = HbaseConstant.getRowKeyOfFlight(idcard, takeofftime, arriveattime, flightnumber) ;
		
		List<Put> puts = new ArrayList<Put>() ;
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Flight.COLUMN_FAMILY.getValue(),
				HbaseConstant.Flight.idcard.getValue(),
				idcard));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Flight.COLUMN_FAMILY.getValue(),
				HbaseConstant.Flight.flightnumber.getValue(),
				flightnumber));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Flight.COLUMN_FAMILY.getValue(),
				HbaseConstant.Flight.takeoffairport.getValue(),
				takeoffairport));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Flight.COLUMN_FAMILY.getValue(),
				HbaseConstant.Flight.arriveatairport.getValue(),
				arriveatairport));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Flight.COLUMN_FAMILY.getValue(),
				HbaseConstant.Flight.takeofftime.getValue(),
				takeofftime));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Flight.COLUMN_FAMILY.getValue(),
				HbaseConstant.Flight.arriveattime.getValue(),
				arriveattime));
		
		puts.add(TestHelper.makePutByString(rowKey,
				HbaseConstant.Flight.COLUMN_FAMILY.getValue(),
				HbaseConstant.Flight.seatnum.getValue(),
				seatNum));
		
		HBaseHelper.addDatas(HbaseConstant.Flight.TABLE_NAME.getValue(), puts) ;
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
			
			Table table = HBaseHelper.getConn().getTable(TableName.valueOf(HbaseConstant.Flight.TABLE_NAME.getValue()));
			rs = table.getScanner(scan);
			
			LOGGER.debug("********************************************************************");
			
			for (Result r : rs) {
				String rowKey = Bytes.toString(r.getRow()) ;
				String idcard = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Flight.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Flight.idcard.getValue()))) ;
				String flightnumber = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Flight.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Flight.flightnumber.getValue()))) ;
				String takeoffairport = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Flight.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Flight.takeoffairport.getValue()))) ;
				String arriveatairport = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Flight.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Flight.arriveatairport.getValue()))) ;
				String takeofftime = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Flight.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Flight.takeofftime.getValue()))) ;
				String arriveattime = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Flight.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Flight.arriveattime.getValue()))) ;
				String seatnum = Bytes.toString(r.getValue(Bytes.toBytes(HbaseConstant.Flight.COLUMN_FAMILY.getValue()), Bytes.toBytes(HbaseConstant.Flight.seatnum.getValue()))) ;
						
				LOGGER.debug("列：{}，列值：{}", "rowKey", rowKey);
				
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Flight.idcard.getValue(), idcard);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Flight.flightnumber.getValue(), flightnumber);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Flight.takeoffairport.getValue(), takeoffairport);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Flight.arriveatairport.getValue(), arriveatairport);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Flight.takeofftime.getValue(), takeofftime);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Flight.arriveattime.getValue(), arriveattime);
				LOGGER.debug("列：{}，列值：{}", HbaseConstant.Flight.seatnum.getValue(), seatnum);
			
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
