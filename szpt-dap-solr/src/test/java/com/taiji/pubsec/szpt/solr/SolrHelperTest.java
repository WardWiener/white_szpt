/**
 * 
 */
package com.taiji.pubsec.szpt.solr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.junit.Test;

import com.taiji.pubsec.szpt.solr.util.SolrConstant;

/**
 * @author sunjd
 *
 */
public class SolrHelperTest {

	@Test
	public void createIndex(){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put("id", "code1");
		map.put("name", "监控点");
		map.put("address", "test-address123");
		map.put("position", "26.607146,106.629461");
		SolrHelper.getInstance().addIndex("wifipoint", "id", map);
	}
	
//	public enum Flight {
//		id,// 值是idcard + flightnumber + takeofftime
//		idcard, //身份证号
//		flightnumber, //航班号
//		takeoffairport, //起飞机场
//		arriveatairport, //到达机场
//		takeofftime, //起飞时间
//		arriveattime, //到达时间
//		persontypecode, //人员类别编码
//		criminaltypecode, //前科类型编码
//		seatnum, //座位号
//		text; //输入查询内容
//		
//		public String getKey(){
//			return this.name() ;
//		}
	@Test
	public void createFlight() throws DateParseException{
		Date takeofftime = null;
		Date arriveattime = null;
		takeofftime = DateUtils.parseDate("2016-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2016-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), "flight0001");
		map.put(SolrConstant.Flight.idcard.getValue(), "130412199001233418");
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight0001");
		map.put(SolrConstant.Flight.takeoffairport.getValue(), "北京国际机场");
		map.put(SolrConstant.Flight.arriveatairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), "33C");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", map);
	}
	
	@Test
	public void createFlight2() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;
		takeofftime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), "flight0002");
		map.put(SolrConstant.Flight.idcard.getValue(), "130412199001233418");
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight0001");
		map.put(SolrConstant.Flight.takeoffairport.getValue(), "北京国际机场");
		map.put(SolrConstant.Flight.arriveatairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), "33C");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", map);
	}
	
	@Test
	public void createFlight3() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;
		takeofftime = DateUtils.parseDate("2017-03-11 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2017-03-11 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), "flight0004");
		map.put(SolrConstant.Flight.idcard.getValue(), "130412199001233418");
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight0001");
		map.put(SolrConstant.Flight.takeoffairport.getValue(), "北京国际机场");
		map.put(SolrConstant.Flight.arriveatairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), "33C");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", map);
	}
	
	@Test
	public void createFlightp3() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;
		takeofftime = DateUtils.parseDate("2017-03-11 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2017-03-11 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), "pflight0004");
		map.put(SolrConstant.Flight.idcard.getValue(), "130412199001233419");
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight0001");
		map.put(SolrConstant.Flight.takeoffairport.getValue(), "北京国际机场");
		map.put(SolrConstant.Flight.arriveatairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), "33C");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", map);
	}
	
	@Test
	public void createFlight4() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;
		takeofftime = DateUtils.parseDate("2017-03-13 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2017-03-13 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), "flight0005");
		map.put(SolrConstant.Flight.idcard.getValue(), "130412199001233418");
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight0003");
		map.put(SolrConstant.Flight.takeoffairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.arriveatairport.getValue(), "北京国际机场");
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), "36C");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", map);
	}
	
	@Test
	public void createFlightp4() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;
		takeofftime = DateUtils.parseDate("2017-03-13 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2017-03-13 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), "pflight0005");
		map.put(SolrConstant.Flight.idcard.getValue(), "130412199001233419");
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight0003");
		map.put(SolrConstant.Flight.takeoffairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.arriveatairport.getValue(), "北京国际机场");
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), "36C");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_FLIGHT, "id", map);
	}
	
	
//	//火车票
//	public enum TrainTicket {
//		id, // 值为 idcard + starttime+ trainnumber
//		idcard, //身份证号
//		trainnumber, //车次
//		startstation, //出发车站
//		arriveatstation, //目的车站
//		starttime, //发车时间
//		persontypecode, //人员类别编码
//		criminaltypecode, //前科类型编码
//		text; //输入查询内容
//		
	
	@Test
	public void createTrainticket() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;

		takeofftime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.TrainTicket.id.getValue(), "trainticket0002");
		map.put(SolrConstant.TrainTicket.idcard.getValue(), "130412199001233418");
		map.put(SolrConstant.TrainTicket.trainnumber.getValue(), "T89");
		map.put(SolrConstant.TrainTicket.startstation.getValue(), "贵阳火车站");
		map.put(SolrConstant.TrainTicket.arriveatstation.getValue(), "上海火车站");
		map.put(SolrConstant.TrainTicket.starttime.getValue(), takeofftime);
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", map);
	}
	
	@Test
	public void createTrainticket1() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;

		takeofftime = DateUtils.parseDate("2017-03-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2017-03-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.TrainTicket.id.getValue(), "trainticket0003");
		map.put(SolrConstant.TrainTicket.idcard.getValue(), "130412199001233418");
		map.put(SolrConstant.TrainTicket.trainnumber.getValue(), "T89");
		map.put(SolrConstant.TrainTicket.startstation.getValue(), "贵阳火车站");
		map.put(SolrConstant.TrainTicket.arriveatstation.getValue(), "上海火车站");
		map.put(SolrConstant.TrainTicket.starttime.getValue(), takeofftime);
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", map);
	}
	
	
	@Test
	public void createTrainticketp1() throws DateParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;

		takeofftime = DateUtils.parseDate("2017-03-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate("2017-03-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.TrainTicket.id.getValue(), "ptrainticket0003");
		map.put(SolrConstant.TrainTicket.idcard.getValue(), "130412199001233419");
		map.put(SolrConstant.TrainTicket.trainnumber.getValue(), "T89");
		map.put(SolrConstant.TrainTicket.startstation.getValue(), "贵阳火车站");
		map.put(SolrConstant.TrainTicket.arriveatstation.getValue(), "上海火车站");
		map.put(SolrConstant.TrainTicket.starttime.getValue(), takeofftime);
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", map);
	}
	
	//
	@Test
	public void createTrainticket2(){
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date takeofftime = null;
		Date arriveattime = null;
		try {
			takeofftime = sdf.parse("2016-10-10 15:20:14");
			arriveattime = sdf.parse("2015-10-10 18:20:14");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.TrainTicket.id.getValue(), "trainticket0001");
		map.put(SolrConstant.TrainTicket.idcard.getValue(), "130412199001233418");
		map.put(SolrConstant.TrainTicket.trainnumber.getValue(), "T89");
		map.put(SolrConstant.TrainTicket.startstation.getValue(), "贵阳火车站");
		map.put(SolrConstant.TrainTicket.arriveatstation.getValue(), "上海火车站");
		map.put(SolrConstant.TrainTicket.starttime.getValue(), takeofftime);
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_TRAIN_TICKET, "id", map);
	}
	
	
	
	@Test
	public void create(){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date d1 = null;
		try {
			d1 = sdf.parse("2016-10-10 15:20:14");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				//("2016-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put("id", "code1");
		map.put("name", "监控点");
		map.put("address", "test-address123");
		map.put("position", "26.607146,106.629461");
		
		SolrHelper.getInstance().addIndex("wifipoint", "id", map);
	}
	
	@Test
	public void deleteIndex(){
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
//		SolrHelper.getInstance().deleteAll("alertsituation");
//		SolrHelper.getInstance().deleteAll("accommodation");
//		SolrHelper.getInstance().deleteAll("trainticket");
//		SolrHelper.getInstance().deleteAll("cybercafe");
//		SolrHelper.getInstance().deleteAll("flight");
		SolrHelper.getInstance().deleteAll("population");
	}
	
	@Test
	public void TESTTIME() throws DateParseException{//2016-10-10T15:20:14Z
		Date d = new Date(1476084014000L);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(sdf.format(d));
		
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		System.out.println(sdf.format(d));
		
		Date d1 = DateUtils.parseDate("2016-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		System.out.println(sdf.format(d1));
		System.out.println(d1.getTime());
	}
	
//      wifi轨迹
//		public enum WifiTrack {
//			id, // 值为 mac+placecode+entertime+leavetime
//			mac, //mac地址
//			placecode,//场所编码
//			placename,//场所名称
//			entertime,//进入时间
//			leavetime,//离开时间
//			period,//停留时间
//			phonenumber,//手机号
//			placeposition,//场所经纬度
//			persontypecode,//人员类别编码
//			criminaltypecode,//前科类型编码
//			tag; //是否有五色预警类型值(fivecolor)
			
	//
	@Test
	public void createWifiTrack() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2015-10-10 19:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0001");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-BB-CC-DD");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00001");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "花溪网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		
		Long period = leavetime.getTime() - entertime.getTime();
		map.put(SolrConstant.WifiTrack.period.getValue(), period);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.5102,106.6834");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrack1() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0002");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-BB-CC-DD");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00002");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "三江网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		
		Long period = leavetime.getTime() - entertime.getTime();
		map.put(SolrConstant.WifiTrack.period.getValue(), period);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.5102,106.6834");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrack2() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0003");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-BB-CC-DD");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00003");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "小河网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		Long period = leavetime.getTime() - entertime.getTime();
		map.put(SolrConstant.WifiTrack.period.getValue(), period);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.2815,106.5703");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrack4() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0004");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-BB-CC-DD");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00004");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "经开网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		
		Long period = leavetime.getTime() - entertime.getTime();
		map.put(SolrConstant.WifiTrack.period.getValue(), period);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.9359,106.4991");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrack5() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2015-10-12 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2015-10-12 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0006");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-BB-CC-DD");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00004");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "经开网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		
		Long period = leavetime.getTime() - entertime.getTime();
		map.put(SolrConstant.WifiTrack.period.getValue(), period);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.9359,106.4991");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrack6() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0005");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-AA-AA-AA");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00004");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "经开网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.9359,106.4991");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrackp6() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "pwifiTrack0005");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-AA-AA-AA");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00004");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "经开网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.9359,106.4991");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrack7() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2017-03-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2017-03-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0007");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-AA-AA-AA");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00004");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "经开网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.5102,106.68344");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
	
	@Test
	public void createWifiTrack8() throws DateParseException{
		DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
		Date entertime = DateUtils.parseDate("2017-03-11 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate("2017-03-11 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
	
		System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), "wifiTrack0008");
		map.put(SolrConstant.WifiTrack.mac.getValue(), "AA-AA-AA-AA");
		map.put(SolrConstant.WifiTrack.placecode.getValue(), "00004");
		map.put(SolrConstant.WifiTrack.placename.getValue(), "经开网吧");
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), "26.508,106.6855");
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
	}
//	public enum Cybercafe {
//		id, //值为 idcard + cybercafename +entertime+leavetime
//		idcard,//身份证号
//		cybercafecode, //网吧编码
//		cybercafename, //网吧名称
//		cybercafeaddress, //网吧地址
//		entertime, //进入时间
//		leavetime, //离开时间
//		persontypecode, //人员类别编码
//		criminaltypecode, //前科类型编码
//		terminalnum, //终端号
//		text; //输入查询内容
		
	
		@Test
		public void createCybercafe() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2017-03-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2015-03-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
		
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Cybercafe.id.getValue(), "cybercafe0001");
			map.put(SolrConstant.Cybercafe.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Cybercafe.cybercafecode.getValue(), "0002");
			map.put(SolrConstant.Cybercafe.cybercafename.getValue(), "大华网吧");
			map.put(SolrConstant.Cybercafe.cybercafeaddress.getValue(), "花溪大道和中漕路交叉路口");
			map.put(SolrConstant.Cybercafe.entertime.getValue(), entertime);
			map.put(SolrConstant.Cybercafe.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Cybercafe.terminalnum.getValue(), "AA-BB-CC-DD");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_CYBERCAFE, "id", map);
		}
		
		@Test
		public void createCybercafe1() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2017-03-11 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2017-03-11 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
		
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Cybercafe.id.getValue(), "cybercafe0002");
			map.put(SolrConstant.Cybercafe.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Cybercafe.cybercafecode.getValue(), "0003");
			map.put(SolrConstant.Cybercafe.cybercafename.getValue(), "阿萨网吧");
			map.put(SolrConstant.Cybercafe.cybercafeaddress.getValue(), "花溪大道和中漕路交叉路口");
			map.put(SolrConstant.Cybercafe.entertime.getValue(), entertime);
			map.put(SolrConstant.Cybercafe.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Cybercafe.terminalnum.getValue(), "AA-BB-CC-DD");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_CYBERCAFE, "id", map);
		}
		
		@Test
		public void createCybercafe3() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2017-03-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2017-03-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
		
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Cybercafe.id.getValue(), "cybercafe0003");
			map.put(SolrConstant.Cybercafe.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Cybercafe.cybercafecode.getValue(), "0003");
			map.put(SolrConstant.Cybercafe.cybercafename.getValue(), "阿萨网吧");
			map.put(SolrConstant.Cybercafe.cybercafeaddress.getValue(), "花溪大道和中漕路交叉路口");
			map.put(SolrConstant.Cybercafe.entertime.getValue(), entertime);
			map.put(SolrConstant.Cybercafe.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Cybercafe.terminalnum.getValue(), "AA-BB-CC-DD");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_CYBERCAFE, "id", map);
		}
		
		@Test
		public void createCybercafe4() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2015-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2015-10-10 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
		
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Cybercafe.id.getValue(), "cybercafe0004");
			map.put(SolrConstant.Cybercafe.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Cybercafe.cybercafecode.getValue(), "0004");
			map.put(SolrConstant.Cybercafe.cybercafename.getValue(), "古风歌网吧");
			map.put(SolrConstant.Cybercafe.cybercafeaddress.getValue(), "花溪大道和中漕路交叉路口");
			map.put(SolrConstant.Cybercafe.entertime.getValue(), entertime);
			map.put(SolrConstant.Cybercafe.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Cybercafe.terminalnum.getValue(), "AA-BB-CC-DD");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_CYBERCAFE, "id", map);
		}
		
//		//住宿
//		public enum Accommodation {
//			id, //值为 idcard+entertime+leavetime+hotelcode
//			idcard, //身份证号
//			hotelcode,//旅馆编码
//			hotelname, //旅馆名称
//			hoteladdress, //旅馆地址
//			entertime, // 进入时间
//			leavetime, //离开时间
//			persontypecode, //人员类别编码
//			criminaltypecode, //前科类型编码
//			roomnum, //房间号
//			text; // 输入的查询条件

		@Test
		public void createAccommodation() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2016-10-10 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2016-10-13 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
			
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Accommodation.id.getValue(), "accommodation0001");
			map.put(SolrConstant.Accommodation.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Accommodation.hotelcode.getValue(), "001");
			map.put(SolrConstant.Accommodation.hotelname.getValue(), "小河旅馆");
			map.put(SolrConstant.Accommodation.hoteladdress.getValue(), "花溪大道和中漕路交叉路口");
			map.put(SolrConstant.Accommodation.entertime.getValue(), entertime);
			map.put(SolrConstant.Accommodation.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Accommodation.roomnum.getValue(), "201");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_ACCOMMODATION, "id", map);
		}
		
		@Test
		public void createAccommodation1() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2016-10-16 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2016-10-21 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
			
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Accommodation.id.getValue(), "accommodation0002");
			map.put(SolrConstant.Accommodation.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Accommodation.hotelcode.getValue(), "002");
			map.put(SolrConstant.Accommodation.hotelname.getValue(), "小河旅馆");
			map.put(SolrConstant.Accommodation.hoteladdress.getValue(), "花溪大道和中漕路交叉路口");
			map.put(SolrConstant.Accommodation.entertime.getValue(), entertime);
			map.put(SolrConstant.Accommodation.leavetime.getValue(), leavetime);
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_ACCOMMODATION, "id", map);
		}
		
		@Test
		public void createAccommodation2() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2017-03-16 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2017-03-16 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
			
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Accommodation.id.getValue(), "accommodation0003");
			map.put(SolrConstant.Accommodation.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Accommodation.hotelcode.getValue(), "002");
			map.put(SolrConstant.Accommodation.hotelname.getValue(), "小河旅馆");
			map.put(SolrConstant.Accommodation.hoteladdress.getValue(), "花溪大道和中漕路交叉路口");
			map.put(SolrConstant.Accommodation.entertime.getValue(), entertime);
			map.put(SolrConstant.Accommodation.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Accommodation.roomnum.getValue(), "202");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_ACCOMMODATION, "id", map);
		}
		
		@Test
		public void createAccommodation3() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2017-03-12 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2017-03-14 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
			
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Accommodation.id.getValue(), "accommodation0004");
			map.put(SolrConstant.Accommodation.idcard.getValue(), "130412199001233418");
			map.put(SolrConstant.Accommodation.hotelcode.getValue(), "003");
			map.put(SolrConstant.Accommodation.hotelname.getValue(), "速八酒店");
			map.put(SolrConstant.Accommodation.hoteladdress.getValue(), "青龙街与颐和园路交叉路口向北1000米");
			map.put(SolrConstant.Accommodation.entertime.getValue(), entertime);
			map.put(SolrConstant.Accommodation.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Accommodation.roomnum.getValue(), "203");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_ACCOMMODATION, "id", map);
		}
		
		@Test
		public void createAccommodationp3() throws DateParseException{
			DateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Chongqing"));
			Date entertime = DateUtils.parseDate("2017-03-12 15:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});
			Date leavetime  = DateUtils.parseDate("2017-03-14 18:20:14", new String[]{"yyyy-MM-dd HH:mm:ss"});;
			
			System.setProperty("solr.zkhost", "bigdata1:2181,bigdata2:2181,bigdata3:2181/solr");
			Map<String, Object> map = new HashMap<>();
			map.put(SolrConstant.Accommodation.id.getValue(), "paccommodation0004");
			map.put(SolrConstant.Accommodation.idcard.getValue(), "130412199001233419");
			map.put(SolrConstant.Accommodation.hotelcode.getValue(), "003");
			map.put(SolrConstant.Accommodation.hotelname.getValue(), "速八酒店");
			map.put(SolrConstant.Accommodation.hoteladdress.getValue(), "青龙街与颐和园路交叉路口向北1000米");
			map.put(SolrConstant.Accommodation.entertime.getValue(), entertime);
			map.put(SolrConstant.Accommodation.leavetime.getValue(), leavetime);
			map.put(SolrConstant.Accommodation.roomnum.getValue(), "203");
			SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_ACCOMMODATION, "id", map);
		}
		
}
