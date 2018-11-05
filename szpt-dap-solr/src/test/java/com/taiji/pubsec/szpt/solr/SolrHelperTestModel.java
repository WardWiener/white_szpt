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
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.junit.Test;

import com.taiji.pubsec.szpt.solr.util.SolrConstant;

/**
 * @author cly
 *
 */
public class SolrHelperTestModel {
	
	/**
	 * 向solr中添加航班进港信息
	 * @param id solr中的唯一表示
	 * @param idcard 身份证号
	 * @param startTime 起飞时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param endTime 到达时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param takeoffairport 起飞机场
	 * @return 拼装好的map航班信息
	 */
	public static Map<String,Object> createArrivalFlight(String id,String idcard , String startTime , String endTime, String takeoffairport) throws DateParseException{
		
		Random rd = new Random ();
		int flightnumber = rd.nextInt(9) + 1;
		int seatnum = rd.nextInt(50) + 1;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null == startTime){
			Date date = new Date();
			date.setHours(date.getHours() - 3);
			startTime = sdf.format(date);
		}
		if(null == endTime){
			endTime = sdf.format(new Date());
		}
		if(null == takeoffairport){
			takeoffairport = "首都国际机场";
		}
		Date takeofftime = DateUtils.parseDate(startTime, new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date arriveattime = DateUtils.parseDate(endTime, new String[]{"yyyy-MM-dd HH:mm:ss"});;
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), id);
		map.put(SolrConstant.Flight.idcard.getValue(), idcard);
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight000" + flightnumber);
		map.put(SolrConstant.Flight.takeoffairport.getValue(), takeoffairport);
		map.put(SolrConstant.Flight.arriveatairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), seatnum + "");
		return map;
	}
	
	/**
	 * 向solr中添加航班出港信息
	 * @param id solr中的唯一表示
	 * @param idcard 身份证号
	 * @param startTime 起飞时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param endTime 到达时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param takeoffairport 到达机场
	 * @return 拼装好的map航班信息
	 */
	public static Map<String,Object> createOutFlight(String id,String idcard , String startTime , String endTime, String arriveatairport) throws DateParseException{
		Date takeofftime = null;
		Date arriveattime = null;
		Random rd = new Random ();
		int flightnumber = rd.nextInt(9) + 1;
		int seatnum = rd.nextInt(50) + 1;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null== id){
			id = UUID.randomUUID().toString();
		}
		
		if(null == startTime){
			Date date = new Date();
			date.setHours(date.getHours() - 3);
			startTime = sdf.format(date);
		}
		if(null == endTime){
			endTime = sdf.format(new Date());
		}
		if(null == arriveatairport){
			arriveatairport = "首都国际机场";
		}
		takeofftime = DateUtils.parseDate(startTime, new String[]{"yyyy-MM-dd HH:mm:ss"});
		arriveattime = DateUtils.parseDate(endTime, new String[]{"yyyy-MM-dd HH:mm:ss"});;
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Flight.id.getValue(), id);
		map.put(SolrConstant.Flight.idcard.getValue(), idcard);
		map.put(SolrConstant.Flight.flightnumber.getValue(), "flight000" + flightnumber);
		map.put(SolrConstant.Flight.takeoffairport.getValue(), "贵阳国际机场");
		map.put(SolrConstant.Flight.arriveatairport.getValue(), arriveatairport);
		map.put(SolrConstant.Flight.takeofftime.getValue(), takeofftime);
		map.put(SolrConstant.Flight.arriveattime.getValue(), arriveattime);
		map.put(SolrConstant.Flight.seatnum.getValue(), seatnum + "");
		return map;
	}
		
	/**
	 * 向solr中添加火车票信息
	 * @param id solr中的唯一表示
	 * @param idcard 身份证号
	 * @param startTime 发车时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param arriveatstation 出发车站
	 * @return 拼装好的map火车票信息
	 */
	public static Map<String,Object> createOutTrainticket(String id,String idcard , String startTime , String arriveatstation) throws DateParseException{
		Random rd = new Random ();
		int trainnumber = rd.nextInt(99) + 1;
		if(null== id){
			id = UUID.randomUUID().toString();
		}
		if(null == startTime){
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startTime = sdf.format(new Date());
		}
		if(null == arriveatstation){
			arriveatstation = "北京西站";
		}
		Date takeofftime = DateUtils.parseDate(startTime, new String[]{"yyyy-MM-dd HH:mm:ss"});	
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.TrainTicket.id.getValue(), id);
		map.put(SolrConstant.TrainTicket.idcard.getValue(), idcard);
		map.put(SolrConstant.TrainTicket.trainnumber.getValue(), "T" + trainnumber);
		map.put(SolrConstant.TrainTicket.startstation.getValue(), "贵阳火车站");
		map.put(SolrConstant.TrainTicket.arriveatstation.getValue(), arriveatstation);
		map.put(SolrConstant.TrainTicket.starttime.getValue(), takeofftime);
		return map;
	}
	
	/**
	 * 向solr中添加火车票信息
	 * @param id solr中的唯一表示
	 * @param idcard 身份证号
	 * @param startTime 发车时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param arriveatstation 出发车站
	 * @return 拼装好的map火车票信息
	 */
	public static Map<String,Object> createInTrainticket(String id,String idcard , String startTime , String startstation) throws DateParseException{
		Random rd = new Random ();
		int trainnumber = rd.nextInt(99) + 1;
		if(null== id){
			id = UUID.randomUUID().toString();
		}
		if(null == startTime){
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startTime = sdf.format(new Date());
		}
		if(null == startstation){
			startstation = "北京西站";
		}
		Date takeofftime = DateUtils.parseDate(startTime, new String[]{"yyyy-MM-dd HH:mm:ss"});	
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.TrainTicket.id.getValue(), id);
		map.put(SolrConstant.TrainTicket.idcard.getValue(), idcard);
		map.put(SolrConstant.TrainTicket.trainnumber.getValue(), "T" + trainnumber);
		map.put(SolrConstant.TrainTicket.startstation.getValue(), startstation);
		map.put(SolrConstant.TrainTicket.arriveatstation.getValue(), "贵阳火车站");
		map.put(SolrConstant.TrainTicket.starttime.getValue(), takeofftime);
		return map;
	}
			
	/**
	 * 向solr中添加wifi轨迹信息
	 * @param id solr中的唯一表示
	 * @param mac mac地址
	 * @param startTime 开始时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param endTime 结束时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param placename 场所名称
	 * @param placeposition 经纬度  格式"纬度,经度" 例"26.5102,106.6834"
	 * @return 拼装好的wifi轨迹信息map
	 */
	public static Map<String,Object> createWifiTrack(String id,String mac , String startTime , String endTime, String placename,String placeposition,String placecode) throws DateParseException{
		Random rd = new Random ();
		int placecodeInt = rd.nextInt(9) + 1;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null == startTime){
			Date date = new Date();
			date.setHours(date.getHours() - 3);
			startTime = sdf.format(date);
		}
		if(null == endTime){
			endTime = sdf.format(new Date());
		}
		if(null == placename){
			placename = "花溪网吧";
		}
		Date entertime = DateUtils.parseDate(startTime, new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate(endTime, new String[]{"yyyy-MM-dd HH:mm:ss"});;
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiTrack.id.getValue(), id);
		map.put(SolrConstant.WifiTrack.mac.getValue(), mac);
		map.put(SolrConstant.WifiTrack.placecode.getValue(), (placecode == null || "".equals(placecode)) ? "0000" + placecodeInt : placecode);
		map.put(SolrConstant.WifiTrack.placename.getValue(), placename);
		map.put(SolrConstant.WifiTrack.entertime.getValue(), entertime);
		map.put(SolrConstant.WifiTrack.leavetime.getValue(), leavetime);
		Long period = leavetime.getTime() - entertime.getTime();
		map.put(SolrConstant.WifiTrack.period.getValue(), period);
		map.put(SolrConstant.WifiTrack.phonenumber.getValue(), "13112341234");
		map.put(SolrConstant.WifiTrack.placeposition.getValue(), placeposition);
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_TRACK, "id", map);
		return map;
	}
	
	/**
	 * 向solr中添加wifi监控点信息
	 * @param id solr中的唯一表示
	 * @param mac mac地址
	 * @param startTime 开始时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param endTime 结束时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param placename 场所名称
	 * @param placeposition 经纬度  格式"纬度,经度" 例"26.5102,106.6834"
	 * @return 拼装好的wifi轨迹信息map
	 */
	public static Map<String,Object> createWifiPoint(String id, String address, String placename,String placeposition) throws DateParseException{
		if(null == placename){
			placename = "花溪网吧";
		}
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.WifiPoint.id.getValue(), id);
		map.put(SolrConstant.WifiPoint.name.getValue(), placename);
		map.put(SolrConstant.WifiPoint.address.getValue(), address);
		map.put(SolrConstant.WifiPoint.position.getValue(), placeposition);
		SolrHelper.getInstance().addIndex(SolrConstant.COLLECTION_WIFI_POINT, "id", map);
		return map;
	}		
	
	/**
	 * 向solr中添加网吧信息
	 * @param id solr中的唯一表示
	 * @param idcard 身份证号
	 * @param startTime 开始时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param endTime 结束时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param cybercafename 网吧名称
	 * @param cybercafeaddress 网吧地址
	 * @return 拼装好的网吧map
	 */
	public static Map<String,Object> createCybercafe(String id,String idcard , String startTime , String endTime, String cybercafename,String cybercafeaddress) throws DateParseException{
		Random rd = new Random ();
		int cybercafecode = rd.nextInt(9) + 1;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null == startTime){
			Date date = new Date();
			date.setHours(date.getHours() - 3);
			startTime = sdf.format(date);
		}
		if(null == endTime){
			endTime = sdf.format(new Date());
		}
		if(null == cybercafename){
			cybercafename = "小河网吧";
		}
		if(null == cybercafeaddress){
			cybercafeaddress = "花溪大道和中漕路交叉路口";
		}
		Date entertime = DateUtils.parseDate(startTime, new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate(endTime, new String[]{"yyyy-MM-dd HH:mm:ss"});;
		
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Cybercafe.id.getValue(), id);
		map.put(SolrConstant.Cybercafe.idcard.getValue(), idcard);
		map.put(SolrConstant.Cybercafe.cybercafecode.getValue(), "000" + cybercafecode);
		map.put(SolrConstant.Cybercafe.cybercafename.getValue(), cybercafename);
		map.put(SolrConstant.Cybercafe.cybercafeaddress.getValue(), cybercafeaddress);
		map.put(SolrConstant.Cybercafe.entertime.getValue(), entertime);
		map.put(SolrConstant.Cybercafe.leavetime.getValue(), leavetime);
		map.put(SolrConstant.Cybercafe.terminalnum.getValue(), "AA-BB-CC-DD");
		return map;
	}

	/**
	 * 向solr中添加住宿信息
	 * @param id solr中的唯一表示
	 * @param idcard 身份证号
	 * @param startTime 开始时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param endTime 结束时间  格式"yyyy-MM-dd HH:mm:ss"
	 * @param hotelname 旅馆名称
	 * @param cybercafeaddress 旅馆地址
	 * @return 拼装好的住宿信息map
	 */
	public static Map<String, Object> createAccommodation(String id,String idcard , String startTime , String endTime, String hotelname,String hoteladdress) throws DateParseException{
		Random rd = new Random ();
		int hotelcode = rd.nextInt(9) + 1;
		int roomnum = rd.nextInt(99) + 1;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null == startTime){
			Date date = new Date();
			date.setHours(date.getHours() - 3);
			startTime = sdf.format(date);
		}
		if(null == endTime){
			endTime = sdf.format(new Date());
		}
		if(null == hotelname){
			hotelname = "小河旅馆";
		}
		if(null == hoteladdress){
			hoteladdress = "花溪大道和中漕路交叉路口";
		}
		Date entertime = DateUtils.parseDate(startTime, new String[]{"yyyy-MM-dd HH:mm:ss"});
		Date leavetime  = DateUtils.parseDate(endTime, new String[]{"yyyy-MM-dd HH:mm:ss"});;
		Map<String, Object> map = new HashMap<>();
		map.put(SolrConstant.Accommodation.id.getValue(),id);
		map.put(SolrConstant.Accommodation.idcard.getValue(), idcard);
		map.put(SolrConstant.Accommodation.hotelcode.getValue(), "000" + hotelcode);
		map.put(SolrConstant.Accommodation.hotelname.getValue(), hotelname);
		map.put(SolrConstant.Accommodation.hoteladdress.getValue(), hoteladdress);
		map.put(SolrConstant.Accommodation.entertime.getValue(), entertime);
		map.put(SolrConstant.Accommodation.leavetime.getValue(), leavetime);
		map.put(SolrConstant.Accommodation.roomnum.getValue(), roomnum + "");
		return map;
	}
}
