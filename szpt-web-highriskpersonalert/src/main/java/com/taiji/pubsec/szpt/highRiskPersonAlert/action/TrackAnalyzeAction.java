package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HitSumBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonTrackInfoBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.WifiPlaceInAndOutInfoBean;
import com.taiji.pubsec.szpt.highriskpersonalert.model.AirPlaneTrack;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HotelTrack;
import com.taiji.pubsec.szpt.highriskpersonalert.model.InternetBarTrack;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MonitoringGuardInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.TrainTrack;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IMonitoringGuardInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONObject;

/**
 * 轨迹分析Action
 * 
 * @author WangLei
 *
 */
@Controller("trackAnalyzeAction")
@Scope("prototype")
public class TrackAnalyzeAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;

	@Resource(name="defaultPersonnelTrackService")
	private PersonnelTrackService personTrackService;// 人员轨迹分析接口
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 高危人员查询接口
	
	@Resource
	private IMonitoringGuardInfoService monitoringGuardInfoService;
	
	@Resource
	private WifiPlaceInAndOutInfoQueryService wifiPlaceInAndOutInfoQueryService;
	
	@Resource
	private InfoSnapshotService infoSnapshotService;
	
	@Resource
	private IInstructionService instructionService;// 指令接口
	
	private Long startTime;// 开始时间
	
	private Long endTime;// 结束时间
	
	private String idCode;// 身份证号
	
	private String snapshotInfo;
	
	private List<PersonTrackInfoBean> personTrackInfoList;// 人员轨迹集合
	
	private List<HitSumBean> hotelList;
	
	private List<HitSumBean> internetBarList;
	
	private List<HitSumBean> trainInList;
	
	private List<HitSumBean> trainOutList;
	
	private List<HitSumBean> airPlaneInList;
	
	private List<HitSumBean> airPlaneOutList;
	
	private List<HitSumBean> monitoringGuardList;
	
	private List<HitSumBean> wifiList;
	
	private List<PersonTrackInfoBean> trainAndAirPlaneList;
	
	private List<PersonTrackInfoBean> hotelTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> internetBarTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> trainInTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> trainOutTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> airPlaneInTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<PersonTrackInfoBean> airPlaneOutTrackList  = new ArrayList<PersonTrackInfoBean>();
	
	private List<WifiPlaceInAndOutInfoBean> wifiTrackList = new ArrayList<WifiPlaceInAndOutInfoBean>();
	
	private List<MonitoringGuardInfo> monitoringGuardTrackList;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	/**
	 * 根据身份证号和时间段查询人员轨迹
	 * 
	 * @return
	 */
	public String findPersonTrackByIdCodeAndTime() {
		Date startDate = startTime == null ? null : DateFmtUtil.longToDate(startTime);
		Date endDate = endTime == null ? null :DateFmtUtil.longToDate(endTime);
		List<PersonTrackInfo> trackList = personTrackService.getPersonTracks(startDate, endDate, idCode);
		Map<String , Integer> hotelMap = new HashMap<String, Integer>();
		Map<String , Integer> internetBarMap = new HashMap<String, Integer>();
		Map<String , Integer> trainInMap = new HashMap<String, Integer>();
		Map<String , Integer> trainOutMap = new HashMap<String, Integer>();
		Map<String , Integer> airPlaneInMap = new HashMap<String, Integer>();
		Map<String , Integer> airPlaneOutMap = new HashMap<String, Integer>();

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
				HotelTrack hotelTrack = (HotelTrack) track.toObject();
				String name = hotelTrack.getHotelName();
				if(hotelMap.get(name)==null){
					hotelMap.put(name, 1);
				}else{
					hotelMap.put(name, hotelMap.get(name)+1);
				}
				bean.setLatitude(hotelTrack.getLatitude());
				bean.setLongitude(hotelTrack.getLongitude());
				bean.setPlaceName(hotelTrack.getHotelName());
				bean.setLeaveTime(DateFmtUtil.dateToLong(hotelTrack.getLeaveTime()));
				hotelTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_INTERNETBAR)){
				InternetBarTrack internetBarTrack = (InternetBarTrack) track.toObject();
				String name = internetBarTrack.getBarName();
				if(internetBarMap.get(name)==null){
					internetBarMap.put(name, 1);
				}else{
					internetBarMap.put(name, internetBarMap.get(name)+1);
				}
				bean.setLatitude(internetBarTrack.getLatitude());
				bean.setLongitude(internetBarTrack.getLongitude());
				bean.setPlaceName(internetBarTrack.getBarName());
				bean.setLeaveTime(DateFmtUtil.dateToLong(internetBarTrack.getOffineTime()));
				internetBarTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_TRAIN_ENTER)){
				TrainTrack trainTrack = (TrainTrack) track.toObject();
				String name = trainTrack.getAddress();
				if(trainInMap.get(name)==null){
					trainInMap.put(name, 1);
				}else{
					trainInMap.put(name, trainInMap.get(name)+1);
				}
				bean.setLatitude(trainTrack.getLatitude());
				bean.setLongitude(trainTrack.getLongitude());
				bean.setPlaceName(trainTrack.getOriginLocation());
				bean.setDestinationLocation(trainTrack.getDestinationLocation());
				bean.setOriginLocation(trainTrack.getOriginLocation());
				trainAndAirPlaneList.add(bean);
				trainInTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_TRAIN_OUT)){
				TrainTrack trainTrack = (TrainTrack) track.toObject();
				String name = trainTrack.getAddress();
				if(trainOutMap.get(name)==null){
					trainOutMap.put(name, 1);
				}else{
					trainOutMap.put(name, trainOutMap.get(name)+1);
				}
				bean.setLatitude(trainTrack.getLatitude());
				bean.setLongitude(trainTrack.getLongitude());
				bean.setPlaceName(trainTrack.getDestinationLocation());
				bean.setDestinationLocation(trainTrack.getDestinationLocation());
				bean.setOriginLocation(trainTrack.getOriginLocation());
				trainAndAirPlaneList.add(bean);
				trainOutTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_AIRPLANE_ENTER)){
				AirPlaneTrack airPlaneTrack = (AirPlaneTrack) track.toObject();
				String name = airPlaneTrack.getAddress();
				if(airPlaneInMap.get(name)==null){
					airPlaneInMap.put(name, 1);
				}else{
					airPlaneInMap.put(name, airPlaneInMap.get(name)+1);
				}
				bean.setLatitude(airPlaneTrack.getLatitude());
				bean.setLongitude(airPlaneTrack.getLongitude());
				bean.setPlaceName(airPlaneTrack.getOriginLocation());
				bean.setDestinationLocation(airPlaneTrack.getDestinationLocation());
				bean.setOriginLocation(airPlaneTrack.getOriginLocation());
				trainAndAirPlaneList.add(bean);
				airPlaneInTrackList.add(bean);
			}else if(track.trackTypeDescription().equals(HighriskPersonConstant.PERSON_TRACK_DESCRIPTION_AIRPLANE_OUT)){
				AirPlaneTrack airPlaneTrack = (AirPlaneTrack) track.toObject();
				String name = airPlaneTrack.getAddress();
				if(airPlaneOutMap.get(name)==null){
					airPlaneOutMap.put(name, 1);
				}else{
					airPlaneOutMap.put(name, airPlaneOutMap.get(name)+1);
				}
				bean.setLatitude(airPlaneTrack.getLatitude());
				bean.setLongitude(airPlaneTrack.getLongitude());
				bean.setPlaceName(airPlaneTrack.getDestinationLocation());
				bean.setDestinationLocation(airPlaneTrack.getDestinationLocation());
				bean.setOriginLocation(airPlaneTrack.getOriginLocation());
				trainAndAirPlaneList.add(bean);
				airPlaneOutTrackList.add(bean);
			}
			personTrackInfoList.add(bean);
		}
		List<Map.Entry<String, Integer>> hotelSortList = new ArrayList<Map.Entry<String, Integer>>(hotelMap.entrySet());
		Collections.sort(hotelSortList, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getKey()).toString().compareTo(o1.getKey());
		    }
		}); 
		List<Map.Entry<String, Integer>> internetBarSortList = new ArrayList<Map.Entry<String, Integer>>(internetBarMap.entrySet());
		Collections.sort(internetBarSortList, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getKey()).toString().compareTo(o1.getKey());
		    }
		}); 
		List<Map.Entry<String, Integer>> trainInSortList = new ArrayList<Map.Entry<String, Integer>>(trainInMap.entrySet());
		Collections.sort(trainInSortList, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getKey()).toString().compareTo(o1.getKey());
		    }
		}); 
		List<Map.Entry<String, Integer>> trainOutSortList = new ArrayList<Map.Entry<String, Integer>>(trainOutMap.entrySet());
		Collections.sort(trainOutSortList, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getKey()).toString().compareTo(o1.getKey());
		    }
		}); 
		List<Map.Entry<String, Integer>> airPlaneInSortList = new ArrayList<Map.Entry<String, Integer>>(airPlaneInMap.entrySet());
		Collections.sort(airPlaneInSortList, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getKey()).toString().compareTo(o1.getKey());
		    }
		}); 
		List<Map.Entry<String, Integer>> airPlaneOutSortList = new ArrayList<Map.Entry<String, Integer>>(airPlaneOutMap.entrySet());
		Collections.sort(airPlaneOutSortList, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
		        return (o2.getKey()).toString().compareTo(o1.getKey());
		    }
		}); 
		hotelList = new ArrayList<HitSumBean>();
		for (int i = 0; i < hotelSortList.size(); i++) {
			HitSumBean bean = new HitSumBean();
			bean.setGroupName(hotelSortList.get(i).getKey());
			bean.setTotalInterval(hotelSortList.get(i).getValue());
			hotelList.add(bean);
		}
		internetBarList = new ArrayList<HitSumBean>();
		for (int i = 0; i < internetBarSortList.size(); i++) {
			HitSumBean bean = new HitSumBean();
			bean.setGroupName(internetBarSortList.get(i).getKey());
			bean.setTotalInterval(internetBarSortList.get(i).getValue());
			internetBarList.add(bean);
		}
		trainInList = new ArrayList<HitSumBean>();
		for (int i = 0; i < trainInSortList.size(); i++) {
			HitSumBean bean = new HitSumBean();
			bean.setGroupName(trainInSortList.get(i).getKey());
			bean.setTotalInterval(trainInSortList.get(i).getValue());
			trainInList.add(bean);
		}
		trainOutList = new ArrayList<HitSumBean>();
		for (int i = 0; i < trainOutSortList.size(); i++) {
			HitSumBean bean = new HitSumBean();
			bean.setGroupName(trainOutSortList.get(i).getKey());
			bean.setTotalInterval(trainOutSortList.get(i).getValue());
			trainOutList.add(bean);
		}
		airPlaneInList = new ArrayList<HitSumBean>();
		for (int i = 0; i < airPlaneInSortList.size(); i++) {
			HitSumBean bean = new HitSumBean();
			bean.setGroupName(airPlaneInSortList.get(i).getKey());
			bean.setTotalInterval(airPlaneInSortList.get(i).getValue());
			airPlaneInList.add(bean);
		}
		airPlaneOutList = new ArrayList<HitSumBean>();
		for (int i = 0; i < airPlaneOutSortList.size(); i++) {
			HitSumBean bean = new HitSumBean();
			bean.setGroupName(airPlaneOutSortList.get(i).getKey());
			bean.setTotalInterval(airPlaneOutSortList.get(i).getValue());
			airPlaneOutList.add(bean);
		}
		monitoringGuardList = new ArrayList<HitSumBean>();
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
		
		wifiList = new ArrayList<HitSumBean>();
		List<PlaceSumInfo> wifis = wifiPlaceInAndOutInfoQueryService.summpHitCountByPersonIdCode(idCode, startDate, endDate);
		for(PlaceSumInfo info : wifis){
			if(info.getGroupName()!=null){
				HitSumBean bean = new HitSumBean();
				bean.setGroupName(info.getGroupName());
				bean.setTotalInterval(info.getCount());
				wifiList.add(bean);
			}
		}
		List<WifiPlaceInAndOutInfo> wifiPlaceList = wifiPlaceInAndOutInfoQueryService.findWifiPlaceInAndOutInfoByIdCode(idCode, startDate, endDate);
		for(WifiPlaceInAndOutInfo wifi : wifiPlaceList){
			PersonTrackInfoBean bean = new PersonTrackInfoBean();
			bean.setAppearTime(DateFmtUtil.dateToLong(wifi.getEnterTime()));
			bean.setLeaveTime(DateFmtUtil.dateToLong(wifi.getLeaveTime()));
			bean.setPlaceName(wifi.getPlace());
			bean.setLatitude(wifi.getLatitude());
			bean.setLongitude(wifi.getLongitude());
			bean.setIsWifi(true);
			boolean flag = false;
			for(int i = 0;i<personTrackInfoList.size()-1;i++){
				if(bean.getAppearTime()>personTrackInfoList.get(i).getAppearTime()){
					personTrackInfoList.add(i, bean);
					flag = true;
					break;
				}
			}
			if(!flag){
				personTrackInfoList.add(bean);
			}
			WifiPlaceInAndOutInfoBean wifiBean = new WifiPlaceInAndOutInfoBean();
			BeanUtils.copyProperties(wifi, wifiBean);
			wifiBean.setLeaveTime(DateFmtUtil.dateToLong(wifi.getLeaveTime()));
			wifiBean.setEnterTime(DateFmtUtil.dateToLong(wifi.getEnterTime()));
			wifiTrackList.add(wifiBean);
		}
		return SUCCESS;
	}
	
	//轨迹分析快照
	@SuppressWarnings("unchecked")
	public String generateSnapshot(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String idCode = (String)rqst.get("idCode");
		
		InfoSnapshot infoSnapshot = new InfoSnapshot();
		infoSnapshot.setType(HighriskPerson.class.getName());
		infoSnapshot.setCode(INFO_SNAPSHOT_MODULE.GWR_GJFX.getValue());
		infoSnapshot.setTargetId(idCode);
		infoSnapshot.setCreatePerson(this.findCurrentPerson().getName());
		infoSnapshot.setIntro((String)rqst.get("snapshotInfo"));
		infoSnapshot.setSnapshot(rqst.get("snapshotObject").toString());
		infoSnapshot.setCreatedDate(new Date());
		resultMap.put("flag", infoSnapshotService.saveInfoSnapshot(infoSnapshot));
		
		//保存反馈
		String id = (String)rqst.get("id");
		if("".equals(id) || null == id){
			return SUCCESS;
		}
		InstructionReceiveSubjectFeedback irsf = new InstructionReceiveSubjectFeedback();
		irsf.setFeedbackContent(infoSnapshot.getIntro());
		irsf.setFeedbackTime(new Date());
		irsf.setFeedbackPeopleName(this.findCurrentPerson().getName());
		irsf.setFeedbackPeopleId(this.findCurrentPerson().getId());
		irsf.setRelateObjectType(InfoSnapshot.class.getName());
		irsf.setRelateObjectId(infoSnapshot.getId());
		instructionService.feedbackInstruction(irsf, id);
		
		return SUCCESS;
	}
	
	/**
	 * 根据类型和id查询发给当前单位的指令
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findInstructionByUnitId(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", (String)rqst.get("type"));
		map.put("loginUnitId", this.findCurrentOrganization().getId());
		Pager<InstructionReceiveSubject> pager = instructionService.findInstructionsByPageOfReceiveDepartment(null, map, 0, Integer.MAX_VALUE);
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		if(pager == null || pager.getPageList().isEmpty()){
			resultMap.put("result", maps);
			return SUCCESS;
		}
		for(InstructionReceiveSubject irs : pager.getPageList()){
			Map<String, String> bean = new HashMap<String, String>();
			bean.put("id", irs.getId());
			bean.put("content", irs.getInstruction().getContent());
			maps.add(bean);
		}
		resultMap.put("result", maps);
		return SUCCESS;
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

	public List<WifiPlaceInAndOutInfoBean> getWifiTrackList() {
		return wifiTrackList;
	}

	public void setWifiTrackList(List<WifiPlaceInAndOutInfoBean> wifiTrackList) {
		this.wifiTrackList = wifiTrackList;
	}

	public List<MonitoringGuardInfo> getMonitoringGuardTrackList() {
		return monitoringGuardTrackList;
	}

	public void setMonitoringGuardTrackList(
			List<MonitoringGuardInfo> monitoringGuardTrackList) {
		this.monitoringGuardTrackList = monitoringGuardTrackList;
	}


	public String getSnapshotInfo() {
		return snapshotInfo;
	}

	public void setSnapshotInfo(String snapshotInfo) {
		this.snapshotInfo = snapshotInfo;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	
}
