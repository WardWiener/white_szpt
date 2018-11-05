package com.taiji.pubsec.szpt.dagl.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.dagl.bean.YrydCybercafeBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydHotelBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydLocusBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydPlaneGoOutBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydTrainGoOutBean;
import com.taiji.pubsec.szpt.dagl.service.YrydService;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.AirPlaneTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HotelTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.InternetBarTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.TrainTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.WifiTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;

@Service
public class YrydServiceImpl implements YrydService{
	
	@SuppressWarnings("rawtypes")
	@Resource(name="jpaDao")
	private Dao dao ;
	
	//火车出行记录
	@Resource(name="personnelTrackTrainService")
	private PersonnelTrackService personnelTrackTrainService;
	
	//飞机出行记录
	@Resource(name="personnelTrackAirPlaneService")
	private PersonnelTrackService personnelTrackAirPlaneService;
	
	//网吧记录
	@Resource(name="personnelTrackInternetBarService")
	private PersonnelTrackService personnelTrackInternetBarService;
	
	//旅馆记录
	@Resource(name="personnelTrackHotelService")
	private PersonnelTrackService personnelTrackHotelService;
	
	//wifi记录
	@Resource(name="personnelTrackWifiService")
	private PersonnelTrackService personnelTrackWifiService;	

	@Override
	public List<YrydTrainGoOutBean> findTrainGoOutInfoByIdcard( String idcard,Date startDate,Date endDate) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		List<PersonTrackInfo> personTrackInfos = personnelTrackTrainService.getPersonTracks(startDate, endDate, idcard);
		if(null != personTrackInfos && personTrackInfos.size() > 0){
			int size = personTrackInfos.size();
			List<YrydTrainGoOutBean> trinBeanLst = new ArrayList<>();
			for(int i= 0;i < size;i++){
				TrainTrackBean trin = (TrainTrackBean)personTrackInfos.get(i);
				YrydTrainGoOutBean trinBean = new YrydTrainGoOutBean();
				trinBean.setId(trin.getId());
				trinBean.setTrainNumber(null == trin.getTrainNo() ? "" : trin.getTrainNo());
				trinBean.setIdcard(null == trin.getPersonIdcode() ? "" : trin.getPersonIdcode());
				trinBean.setStartPlace(null == trin.getOriginLocation() ? "" : trin.getOriginLocation());
				trinBean.setEndPlace(null == trin.getDestinationLocation() ? "" : trin.getDestinationLocation());
				trinBean.setDepartTime(null ==trin.getSetoutTime() ? "" : df.format(trin.getSetoutTime()));
				trinBeanLst.add(trinBean);
			}
			return trinBeanLst;
		}
		return null;
	}


	@Override
	public List<YrydPlaneGoOutBean> findPlaneGoOutInfoByIdcard(String idcard,Date startDate,Date endDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat sj = new SimpleDateFormat("HH:mm:ss");
		List<PersonTrackInfo> personTrackInfos = personnelTrackAirPlaneService.getPersonTracks(startDate, endDate, idcard);
		
		if(null != personTrackInfos && personTrackInfos.size() > 0){
			int size = personTrackInfos.size();
			List<YrydPlaneGoOutBean> planeLst = new ArrayList<>();
			for(int i= 0;i < size;i++){
				AirPlaneTrackBean airPlane = (AirPlaneTrackBean)personTrackInfos.get(i);
				YrydPlaneGoOutBean plane = new YrydPlaneGoOutBean();
				plane.setFlyNum(null == airPlane.getFlightNo() ? "" :airPlane.getFlightNo() );
				plane.setStratTime(null == airPlane.getSetoutTime()? "" : df.format(airPlane.getSetoutTime()));
				plane.setGoAboardTime(null == airPlane.getSetoutTime()? "" : sj.format(airPlane.getSetoutTime()));
				plane.setStartPlace(null == airPlane.getOriginLocation() ? "" : airPlane.getOriginLocation());
				plane.setEndPlace(null == airPlane.getDestinationLocation() ? "" : airPlane.getDestinationLocation());
				plane.setSeatNum(null == airPlane.getSeatnum() ? "" : airPlane.getSeatnum());
				planeLst.add(plane);
			}
			return planeLst;
		}
		return null;
	}

	@Override
	public List<YrydCybercafeBean> findCybercafeInfoByIdcard(String idcard,Date startDate,Date endDate) {
	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<PersonTrackInfo> personTrackInfos = personnelTrackInternetBarService.getPersonTracks(startDate, endDate, idcard);
		if(null != personTrackInfos && personTrackInfos.size() > 0){
			int size = personTrackInfos.size();
			List<YrydCybercafeBean>  cybercafeLst = new ArrayList<>();
			for(int i= 0;i < size;i++){
				InternetBarTrackBean internetBar = (InternetBarTrackBean)personTrackInfos.get(i);
				YrydCybercafeBean  cybercafe = new YrydCybercafeBean();
				cybercafe.setId(null == internetBar.getId() ? "" : internetBar.getId());
				cybercafe.setAddress(null == internetBar.getBarAddress() ? "" : internetBar.getBarAddress());
				cybercafe.setStartTime(null == internetBar.getOnlineTime() ? "" : df.format(internetBar.getOnlineTime()));
				cybercafe.setEndTime(null == internetBar.getOfflineTime() ? "" : df.format(internetBar.getOfflineTime()));
				cybercafe.setTerminalNum(null ==  internetBar.getTerminalnum() ? "" : internetBar.getTerminalnum());
				cybercafe.setName(null == internetBar.getBarName() ? "" : internetBar.getBarName());
				cybercafeLst.add(cybercafe);
			}
			return cybercafeLst;
		}
		return null;
	}
	
	@Override
	public List<YrydHotelBean> findHotelInfoByIdcard(String idcard,Date startDate,Date endDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		List<PersonTrackInfo> personTrackInfos = personnelTrackHotelService.getPersonTracks(startDate, endDate, idcard);
		if(null != personTrackInfos && personTrackInfos.size() > 0){
			int size = personTrackInfos.size();
			List<YrydHotelBean> hotelLst =  new ArrayList<>();
			for(int i= 0;i < size;i++){
				HotelTrackBean hotelBean = (HotelTrackBean)personTrackInfos.get(i);
				YrydHotelBean  hotel = new YrydHotelBean();
				hotel.setAddress(null == hotelBean.getHotelAddr() ? "" : hotelBean.getHotelAddr());
				hotel.setId(null == hotelBean.getId() ? "" : hotelBean.getId());
				hotel.setName(null == hotelBean.getHotelName() ? "" : hotelBean.getHotelName());
				hotel.setRoomNum(null == hotelBean.getRoomnum() ? "" : hotelBean.getRoomnum());
				hotel.setStratTime(null == hotelBean.getEnterTime() ? "" : df.format(hotelBean.getEnterTime()));
				hotel.setEndTime(null == hotelBean.getLeaveTime() ? "" : df.format(hotelBean.getLeaveTime()));
				hotelLst.add(hotel);
			}
			return hotelLst;
		}
		return null;
	}
	
	@Override
	public List<YrydLocusBean> findWifiInfoByIdcard(String idcard,Date startDate,Date endDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<PersonTrackInfo> personTrackInfos = personnelTrackWifiService.getPersonTracks(startDate, endDate, idcard);
		if(null != personTrackInfos && personTrackInfos.size() > 0){
			int size = personTrackInfos.size();
			List<YrydLocusBean> LocusLst =  new ArrayList<>();
			for(int i= 0;i < size;i++){
				WifiTrackBean wifiTrackBean = (WifiTrackBean)personTrackInfos.get(i);
				YrydLocusBean  locus = new YrydLocusBean();
				locus.setId(null == wifiTrackBean.getId() ? "" : wifiTrackBean.getId());
				locus.setEnterTime(null == wifiTrackBean.getEnterTime()  ? "" : df.format( wifiTrackBean.getEnterTime()));
				locus.setLeaveTime(null == wifiTrackBean.getLeaveTime()  ? "" : df.format( wifiTrackBean.getLeaveTime()));
				locus.setPlaceCode(null == wifiTrackBean.getPlaceCode() ? "" : wifiTrackBean.getPlaceCode());
				locus.setPlaceName(null == wifiTrackBean.getPlaceName() ? "" : wifiTrackBean.getPlaceName());
				locus.setMac(null == wifiTrackBean.getMac() ? "" : wifiTrackBean.getMac());
				locus.setPhone(null == wifiTrackBean.getPhone() ? "" : wifiTrackBean.getPhone());
				locus.setLongitude(null == wifiTrackBean.getLongitude() ? "" : wifiTrackBean.getLongitude().toString());
				locus.setLatitude(null == wifiTrackBean.getLatitude() ? "" : wifiTrackBean.getLatitude().toString());
				LocusLst.add(locus);
			}
			return LocusLst;
		}
		return null;
	}

}
