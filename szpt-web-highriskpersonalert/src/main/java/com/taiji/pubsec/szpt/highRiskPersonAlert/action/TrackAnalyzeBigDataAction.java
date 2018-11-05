package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HitSumBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonTrackInfoBean;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MonitoringGuardInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.AirPlaneTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HotelTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.InternetBarTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.TrainTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.WifiTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.DetentionInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.DrugRehabilitationInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IMonitoringGuardInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

/**
 * 轨迹分析Action
 * 
 * @author WangLei
 *
 */
@Controller("trackAnalyzeBigDataAction")
@Scope("prototype")
public class TrackAnalyzeBigDataAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;

	@Resource(name="defaultPersonnelTrackService")
	private PersonnelTrackService personTrackService;// 人员轨迹分析接口
	
	@Resource(name="personnelTrackWifiService")
	private PersonnelTrackService personnelTrackWifiService;
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 高危人员查询接口
	
	@Resource
	private IMonitoringGuardInfoService monitoringGuardInfoService;
	
	@Resource
	private WifiPlaceInAndOutInfoQueryService wifiPlaceInAndOutInfoQueryService;
	
	@Resource
	private DetentionInfoService detentionInfoService;// 拘留所接口
	
	@Resource
	private DrugRehabilitationInfoService drugRehabilitationInfoService;// 戒毒所接口
	
	
	
	private Long startTime;// 开始时间
	
	private Long endTime;// 结束时间
	
	private String idCode;// 身份证号
	
	private List<PersonTrackInfoBean> personTrackInfoList;// 人员轨迹集合(时间轴，轨迹图，热点图)
	
	private List<HitSumBean> hotelList = new ArrayList<HitSumBean>();//右侧列表数据
	
	private List<HitSumBean> internetBarList = new ArrayList<HitSumBean>();
	
	private List<HitSumBean> trainInList = new ArrayList<HitSumBean>();
	
	private List<HitSumBean> trainOutList = new ArrayList<HitSumBean>();
	
	private List<HitSumBean> airPlaneInList = new ArrayList<HitSumBean>();
	
	private List<HitSumBean> airPlaneOutList = new ArrayList<HitSumBean>();
	
	private List<HitSumBean> monitoringGuardList = new ArrayList<HitSumBean>();
	
	private List<HitSumBean> wifiList = new ArrayList<HitSumBean>();
	
	private List<PersonTrackInfoBean> trainAndAirPlaneList;//轨迹图table
	
	private List<PersonTrackInfoBean> hotelTrackList  = new ArrayList<PersonTrackInfoBean>();//点击“全部”显示的数据
	
	private List<PersonTrackInfoBean> internetBarTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> trainInTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> trainOutTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> airPlaneInTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> airPlaneOutTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> wifiTrackList = new ArrayList<PersonTrackInfoBean>();
	
	private List<MonitoringGuardInfo> monitoringGuardTrackList;
	
	/**
	 * 根据身份证号和时间段查询人员轨迹
	 * 
	 * @return
	 */
	public String findPersonTrackByIdCodeAndTime() {
		Date startDate = startTime == null ? null : DateFmtUtil.longToDate(startTime);
		Date endDate = endTime == null ? null :DateFmtUtil.longToDate(endTime);
		List<PersonTrackInfo> trackList = personTrackService.getPersonTracks(startDate, endDate, idCode);
		List<PersonTrackInfo> tracks = findWifiTrackBean(startDate, endDate, idCode);
		if(tracks.size() > 0){
			trackList.addAll(tracks);
		}
		Map<String , Integer> hotelMap = new HashMap<String, Integer>();
		Map<String , Integer> internetBarMap = new HashMap<String, Integer>();
		Map<String , Integer> trainInMap = new HashMap<String, Integer>();
		Map<String , Integer> trainOutMap = new HashMap<String, Integer>();
		Map<String , Integer> airPlaneInMap = new HashMap<String, Integer>();
		Map<String , Integer> airPlaneOutMap = new HashMap<String, Integer>();
		Map<String , Integer> wifiMap = new HashMap<String, Integer>();
		personTrackInfoList = new ArrayList<PersonTrackInfoBean>();
		trainAndAirPlaneList = new ArrayList<PersonTrackInfoBean>();
		for(PersonTrackInfo track : trackList){
			PersonTrackInfoBean bean = new PersonTrackInfoBean();
			bean.setTrackDescription(track.trackDescription());
			bean.setTrackType(track.trackType());
			bean.setTrackTypeDescription(track.trackTypeDescription());
			bean.setAppearTime(DateFmtUtil.dateToLong(track.getAppearTime()));
			bean.setIsWifi(false);
			if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_HOTEL)){
				HotelTrackBean hotelTrack = (HotelTrackBean) track.toObject();
				mapPutIn(hotelTrack.getHotelName(), hotelMap);
				bean.setPlaceName(hotelTrack.getHotelName());
				bean.setAppearTime(hotelTrack.getEnterTime().getTime());
				bean.setLeaveTime(DateFmtUtil.dateToLong(hotelTrack.getLeaveTime()));
				hotelTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_INTERNETBAR)){
				InternetBarTrackBean internetBarTrack = (InternetBarTrackBean) track.toObject();
				mapPutIn(internetBarTrack.getBarName(), internetBarMap);
				bean.setPlaceName(internetBarTrack.getBarName());
				bean.setAppearTime(internetBarTrack.getOnlineTime().getTime());
				bean.setLeaveTime(DateFmtUtil.dateToLong(internetBarTrack.getOfflineTime()));
				internetBarTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_TRAIN_ENTER)){
				TrainTrackBean trainTrack = (TrainTrackBean) track.toObject();
				mapPutIn(trainTrack.getOriginLocation(), trainInMap);
				bean.setPlaceName(trainTrack.getOriginLocation());
				bean.setDestinationLocation(trainTrack.getDestinationLocation());
				bean.setAppearTime(trainTrack.getSetoutTime().getTime());
				bean.setOriginLocation(trainTrack.getOriginLocation());
				trainAndAirPlaneList.add(bean);
				trainInTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_TRAIN_OUT)){
				TrainTrackBean trainTrack = (TrainTrackBean) track.toObject();
				mapPutIn(trainTrack.getDestinationLocation(), trainOutMap);
				bean.setPlaceName(trainTrack.getDestinationLocation());
				bean.setDestinationLocation(trainTrack.getDestinationLocation());
				bean.setAppearTime(trainTrack.getSetoutTime().getTime());
				bean.setOriginLocation(trainTrack.getOriginLocation());
				trainAndAirPlaneList.add(bean);
				trainOutTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_AIRPLANE_ENTER)){
				AirPlaneTrackBean airPlaneTrack = (AirPlaneTrackBean) track.toObject();
				mapPutIn(airPlaneTrack.getOriginLocation(), airPlaneInMap);
				bean.setPlaceName(airPlaneTrack.getOriginLocation());
				bean.setDestinationLocation(airPlaneTrack.getDestinationLocation());
				bean.setOriginLocation(airPlaneTrack.getOriginLocation());
				bean.setAppearTime(airPlaneTrack.getSetoutTime().getTime());
				trainAndAirPlaneList.add(bean);
				airPlaneInTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_AIRPLANE_OUT)){
				AirPlaneTrackBean airPlaneTrack = (AirPlaneTrackBean) track.toObject();
				mapPutIn(airPlaneTrack.getDestinationLocation(), airPlaneOutMap);
				bean.setPlaceName(airPlaneTrack.getDestinationLocation());
				bean.setAppearTime(airPlaneTrack.getSetoutTime().getTime());
				bean.setDestinationLocation(airPlaneTrack.getDestinationLocation());
				bean.setOriginLocation(airPlaneTrack.getOriginLocation());
				trainAndAirPlaneList.add(bean);
				airPlaneOutTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_WIFI)){
				WifiTrackBean wifiTrackBean = (WifiTrackBean) track.toObject();
				mapPutIn(wifiTrackBean.getPlaceName(), wifiMap);
				bean.setLatitude(wifiTrackBean.getLatitude().toString());
				bean.setLongitude(wifiTrackBean.getLongitude().toString());
				if(null == wifiTrackBean.getPlaceName() || wifiTrackBean.getPlaceName().equals("null")){
					bean.setPlaceName("");
				}else{
					bean.setPlaceName(wifiTrackBean.getPlaceName());
				}
				
				bean.setAppearTime(DateFmtUtil.dateToLong(wifiTrackBean.getEnterTime()));
				bean.setLeaveTime(DateFmtUtil.dateToLong(wifiTrackBean.getLeaveTime()));
				bean.setIsWifi(true);
				wifiTrackList.add(bean);
			}
			personTrackInfoList.add(bean);
		}
		sortAndAddToLst(hotelMap, hotelList);
		sortAndAddToLst(internetBarMap, internetBarList);
		sortAndAddToLst(trainInMap, trainInList);
		sortAndAddToLst(trainOutMap, trainOutList);
		sortAndAddToLst(airPlaneInMap, airPlaneInList);
		sortAndAddToLst(airPlaneOutMap, airPlaneOutList);
		sortAndAddToLst(wifiMap, wifiList);
		
		//查询拘留数量
		Integer detentionNum = detentionInfoService.findNumByIdenty(idCode);
		//查询戒毒数量
		Integer drugRehabilitationNum = drugRehabilitationInfoService.findNumByIdenty(idCode);
		if(detentionNum > 0){
			HitSumBean bean1 = new HitSumBean();
			bean1.setGroupName("拘留所");
			bean1.setTotalInterval(Integer.valueOf(detentionNum));
			monitoringGuardList.add(bean1);
		}
		
		if(drugRehabilitationNum > 0){
			HitSumBean bean2 = new HitSumBean();
			bean2.setGroupName("戒毒所");
			bean2.setTotalInterval(Integer.valueOf(drugRehabilitationNum));
			monitoringGuardList.add(bean2);
		}
		
		List<StatisticsInfo> monitoringGuards = monitoringGuardInfoService.findMonitoringGuardInfoByPerson(idCode, startDate, endDate);
		for(StatisticsInfo info : monitoringGuards){
			if(info.getName()!=null){
				HitSumBean bean = new HitSumBean();
				bean.setGroupName(info.getName());
				bean.setTotalInterval(Integer.valueOf(info.getValue()));
				monitoringGuardList.add(bean);
			}
		}
		monitoringGuardTrackList = monitoringGuardInfoService.findMonitoringGuardInfosByPerson(idCode, startDate, endDate);
		return SUCCESS;
	}
	
	//添加wifi信息
	private List<PersonTrackInfo> findWifiTrackBean(Date startDate,Date endDate,String idCode ){
		HighriskPerson person = highriskPersonService.findByIdCode(idCode);
		List<PersonTrackInfo> personTrackLst = new ArrayList();
		if(null != person && person.getMobilePhoneInfos().size() > 0){
			Set<MobilePhoneInfo> mobilePhoneInfos = person.getMobilePhoneInfos();
			for(MobilePhoneInfo mobilePhoneInfo : mobilePhoneInfos){
				List<PersonTrackInfo> personTracks = personnelTrackWifiService.getPersonTracks(startDate, endDate, mobilePhoneInfo.getMac());
				personTrackLst.addAll(personTracks);
			}
		}
		return personTrackLst;
	}

	// sortAndAddToLst(trainOutMap, trainOutList);
	private void sortAndAddToLst(Map<String, Integer> map, List<HitSumBean> resultLst){
		List<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) { 
		    	if(null == o2 || null == o1 || null == o2.getKey() || null == o1.getKey() ){
		    		return 0;
		    	}
		        return (o2.getKey()).toString().compareTo(o1.getKey());
		    }
		}); 
		for (int i = 0; i < sortList.size(); i++) {
			HitSumBean bean = new HitSumBean();
			if(null != sortList.get(i).getKey() &&  !sortList.get(i).getKey().equals("null")){
				bean.setGroupName(sortList.get(i).getKey());
			}else{
				continue;
			}
			bean.setTotalInterval(sortList.get(i).getValue());
			resultLst.add(bean);
		}
	}
	
	//mapPutIn(airPlaneTrack.getAddress(), airPlaneOutMap);
	private void mapPutIn(String name, Map<String, Integer> map){
		if(map.get(name)==null){
			map.put(name, 1);
		}else{
			map.put(name, map.get(name)+1);
		}
	}
	
	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public List<PersonTrackInfoBean> getPersonTrackInfoList() {
		return personTrackInfoList;
	}

	public void setPersonTrackInfoList(List<PersonTrackInfoBean> personTrackInfoList) {
		this.personTrackInfoList = personTrackInfoList;
	}

	public List<HitSumBean> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<HitSumBean> hotelList) {
		this.hotelList = hotelList;
	}

	public List<HitSumBean> getInternetBarList() {
		return internetBarList;
	}

	public void setInternetBarList(List<HitSumBean> internetBarList) {
		this.internetBarList = internetBarList;
	}

	public List<HitSumBean> getTrainInList() {
		return trainInList;
	}

	public void setTrainInList(List<HitSumBean> trainInList) {
		this.trainInList = trainInList;
	}

	public List<HitSumBean> getTrainOutList() {
		return trainOutList;
	}

	public void setTrainOutList(List<HitSumBean> trainOutList) {
		this.trainOutList = trainOutList;
	}

	public List<HitSumBean> getAirPlaneInList() {
		return airPlaneInList;
	}

	public void setAirPlaneInList(List<HitSumBean> airPlaneInList) {
		this.airPlaneInList = airPlaneInList;
	}

	public List<HitSumBean> getAirPlaneOutList() {
		return airPlaneOutList;
	}

	public void setAirPlaneOutList(List<HitSumBean> airPlaneOutList) {
		this.airPlaneOutList = airPlaneOutList;
	}

	public List<HitSumBean> getWifiList() {
		return wifiList;
	}

	public void setWifiList(List<HitSumBean> wifiList) {
		this.wifiList = wifiList;
	}

	public List<HitSumBean> getMonitoringGuardList() {
		return monitoringGuardList;
	}

	public void setMonitoringGuardList(List<HitSumBean> monitoringGuardList) {
		this.monitoringGuardList = monitoringGuardList;
	}

	public List<PersonTrackInfoBean> getTrainAndAirPlaneList() {
		return trainAndAirPlaneList;
	}

	public void setTrainAndAirPlaneList(
			List<PersonTrackInfoBean> trainAndAirPlaneList) {
		this.trainAndAirPlaneList = trainAndAirPlaneList;
	}

	public List<PersonTrackInfoBean> getHotelTrackList() {
		return hotelTrackList;
	}

	public void setHotelTrackList(List<PersonTrackInfoBean> hotelTrackList) {
		this.hotelTrackList = hotelTrackList;
	}

	public List<PersonTrackInfoBean> getInternetBarTrackList() {
		return internetBarTrackList;
	}

	public void setInternetBarTrackList(
			List<PersonTrackInfoBean> internetBarTrackList) {
		this.internetBarTrackList = internetBarTrackList;
	}


	public List<PersonTrackInfoBean> getTrainInTrackList() {
		return trainInTrackList;
	}

	public void setTrainInTrackList(List<PersonTrackInfoBean> trainInTrackList) {
		this.trainInTrackList = trainInTrackList;
	}

	public List<PersonTrackInfoBean> getTrainOutTrackList() {
		return trainOutTrackList;
	}

	public void setTrainOutTrackList(List<PersonTrackInfoBean> trainOutTrackList) {
		this.trainOutTrackList = trainOutTrackList;
	}

	public List<PersonTrackInfoBean> getAirPlaneInTrackList() {
		return airPlaneInTrackList;
	}

	public void setAirPlaneInTrackList(List<PersonTrackInfoBean> airPlaneInTrackList) {
		this.airPlaneInTrackList = airPlaneInTrackList;
	}

	public List<PersonTrackInfoBean> getAirPlaneOutTrackList() {
		return airPlaneOutTrackList;
	}

	public void setAirPlaneOutTrackList(
			List<PersonTrackInfoBean> airPlaneOutTrackList) {
		this.airPlaneOutTrackList = airPlaneOutTrackList;
	}

	public List<PersonTrackInfoBean> getWifiTrackList() {
		return wifiTrackList;
	}

	public void setWifiTrackList(List<PersonTrackInfoBean> wifiTrackList) {
		this.wifiTrackList = wifiTrackList;
	}

	public List<MonitoringGuardInfo> getMonitoringGuardTrackList() {
		return monitoringGuardTrackList;
	}

	public void setMonitoringGuardTrackList(
			List<MonitoringGuardInfo> monitoringGuardTrackList) {
		this.monitoringGuardTrackList = monitoringGuardTrackList;
	}
	
}
