package com.taiji.pubsec.szpt.placemonitor.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.text.DateFormat; 
import java.text.NumberFormat; 
import java.text.ParseException; 
import java.util.Date; 
import java.util.Random; 

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.bean.TimeIntervalBean;
import com.taiji.pubsec.szpt.bean.TopTimeBean;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.placemonitor.action.bean.BaseBean;
import com.taiji.pubsec.szpt.placemonitor.action.bean.HitSumBean;
import com.taiji.pubsec.szpt.placemonitor.action.util.ModelBeanTransformUtilInPlaceMonitor;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiPlaceInAndOutInfoBean;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceCountBean;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStateRecord;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceBasicInfoService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;

import net.sf.json.JSONObject;

/**
 * wifi围栏命中信息Action
 * 
 * @author WL-PC
 *
 */
@Controller("wifiFenceHitAction")
@Scope("prototype")
public class WifiFenceHitAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;
	
	private String snapshotInfo;// 快照信息
	
	private List<String> macList = new ArrayList<String>(); // mac地址集合

	private Map<String,Object> paramMap = new HashMap<String,Object>(); // 查询参数
	
	private List<HitSumBean> hitSumBeanList = new ArrayList<HitSumBean>(); // 分组统计结果Bean集合
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	private Long startTime;// 开始时间
	
	private Long endTime;// 结束时间
	
	private String idCode;
	
	private String search;
	
	private boolean flag;
	
	@Resource
	private ModelBeanTransformUtilInPlaceMonitor modelBeanTransformUtil; //action model和bean对象转换工具
	
	@Resource
	private WifiPlaceInAndOutInfoQueryService wifiMacHitQueryService; // wifi围栏命中信息接口
	
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource
	private WifiStateRecordService wifiStateRecordService;
	
	@Resource
	private PlaceBasicInfoService placeBasicInfoService;
	
	@Resource
	private InfoSnapshotService infoSnapshotService; //快照service
	
	@Resource
	private IInstructionService instructionService;// 指令接口
	
	/**
	 * 根据身份证号查mac地址
	 * @return
	 */
	public String findMacsByIdCode() {
		HighriskPerson person = highriskPersonService.findByIdCode(idCode);
		if(person==null){
			flag=false;
			return SUCCESS;
		}
		flag=true;
		for(MobilePhoneInfo mobile : person.getMobilePhoneInfos()){
			if(!StringUtils.isEmpty(mobile.getMac())){
				macList.add(mobile.getMac());
			}
		}
		return SUCCESS;
	}
	/**
	 * 根据手机号查mac地址
	 * @return
	 */
	public String findMacsByPhoneNumber() {
		HighriskPerson person = highriskPersonService.findByMobile(idCode);
		if(person==null){
			flag=false;
			return SUCCESS;
		}
		flag=true;
		for(MobilePhoneInfo mobile : person.getMobilePhoneInfos()){
			if(!StringUtils.isEmpty(mobile.getMac())){
				macList.add(mobile.getMac());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据mac地址查询轨迹列表（分页查询）
	 * 
	 * @param macList mac地址集合
	 * @return "success"，成功返回wifiFenceHitBeanList:wifi围栏命中信息Bean集合
	 */
	public String findLocusByMacs() {
		if(macList.isEmpty()){
			resultMap.put("totalNum", 0);
			resultMap.put("list", new ArrayList<WifiStateRecord>());
			return SUCCESS;
		}
		Date startDay = DateFmtUtil.longToDate(startTime) ;
		Date endDay = DateFmtUtil.longToDate(endTime) ;
		if(this.getStart()!=null){
			Pager<WifiStateRecord> pager = wifiStateRecordService.findByMacs(macList, startDay, endDay,this.getStart() / this.getLength(),this.getLength());
			resultMap.put("list", pager.getPageList());
			resultMap.put("totalNum", pager.getTotalNumber());
		}else{
			List<WifiStateRecord> list = wifiStateRecordService.findByMacs(macList, startDay, endDay);
			resultMap.put("list", list);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询某人员某时间段经过次数最多的场所
	 * 
	 * @param paramMap 查询条件</br>条件包括mac地址列表、起始时间、结束时间。
	 * macList mac地址列表
	 * startTime 开始时间
	 * endTime 结束时间
	 * @return "success"，成功返回hitSumBeanList:分组统计结果bean集合
	 */
	public String findPlaceSumByMacAndTime(){
		Date startDay = DateFmtUtil.longToDate(startTime);
		Date endDay = DateFmtUtil.longToDate(endTime);
	
		Integer descNum=3;
		List<PlaceCountBean> placeCountBean = wifiStateRecordService.findByMacsByTopPlaceAndTimes(macList, startDay, endDay, descNum);
		List<TopTimeBean> list= new ArrayList<TopTimeBean>();
		if(ParamMapUtil.isNotBlank(placeCountBean)){
			List<String> placeCodes = new ArrayList<String>();
			for (PlaceCountBean bean : placeCountBean) {
				PlaceBasicInfo p = placeBasicInfoService.findByCode(bean.getPlaceCode());
				if(p != null){
					bean.setPlaceName(p.getInternetServicePlaceName());
				}
				placeCodes.add(bean.getPlaceCode());
				list.add(new TopTimeBean(bean.getPlaceName(),bean.getPlaceCode(),bean.getCount(),bean.getStayTime()));
			}
			List<WifiStateRecord> wifiStateRecord=wifiStateRecordService.findDetailByPlaceByMacs(macList, placeCodes, startDay, endDay);
			for (TopTimeBean topTimeBean : list) {
				if(null == wifiStateRecord || wifiStateRecord.size() <=0){
					list = new ArrayList();
				}else{
					for (WifiStateRecord wifiStateRecordBean : wifiStateRecord) {
						if(topTimeBean.getPlaceCode().equals(wifiStateRecordBean.getPlaceCode())){
							TimeIntervalBean bean = new TimeIntervalBean();
							bean.setEnterTime(wifiStateRecordBean.getEnterTime());
							bean.setLeaveTime(wifiStateRecordBean.getLeaveTime());
							bean.setMacAddress(wifiStateRecordBean.getMac());
							topTimeBean.getTimeIntervalList().add(bean);
							topTimeBean.setPlaceName(wifiStateRecordBean.getPlaceName());
						}
					}	
				}
			}
		}
		resultMap.put("list", list) ;
		return SUCCESS;
	}
	
	/**
	 * 查询某人员某时间段驻留时间最长的场所
	 * 
	 * @param paramMap 查询条件</br>条件包括mac地址列表、起始时间、结束时间。
	 * macList mac地址列表
	 * startTime 开始时间
	 * endTime 结束时间
	 * @return "success"，成功返回wifiFenceHitBeanList:wifi围栏命中信息Bean集合
	 */
	public String findMaxTimePlaceByMacAndTime(){
		Date startDay = DateFmtUtil.longToDate(startTime);
		Date endDay = DateFmtUtil.longToDate(endTime);
	
		Integer descNum=3;										 
		List<PlaceCountBean> placeCountBean = wifiStateRecordService.findByMacsByTopAndStayeTime(macList, startDay, endDay, descNum );
		List<TopTimeBean> list= new ArrayList<TopTimeBean>();
		if(ParamMapUtil.isNotBlank(placeCountBean)){
			List<String> placeCodes = new ArrayList<String>();
			for (PlaceCountBean bean : placeCountBean) {
				PlaceBasicInfo p = placeBasicInfoService.findByCode(bean.getPlaceCode());
				if(p != null){
					bean.setPlaceName(p.getInternetServicePlaceName());
				}
				placeCodes.add(bean.getPlaceCode());
				list.add(new TopTimeBean(bean.getPlaceName(),bean.getPlaceCode(),bean.getCount(),bean.getStayTime()));
			}
			
			List<WifiStateRecord> WifiStateRecord=wifiStateRecordService.findDetailByPlaceByMacs(macList, placeCodes, startDay, endDay);
			for (TopTimeBean topTimeBean : list) {
				for (WifiStateRecord wifiStateRecordBean : WifiStateRecord) {
					if(topTimeBean.getPlaceCode().equals(wifiStateRecordBean.getPlaceCode())){
						TimeIntervalBean bean = new TimeIntervalBean();
						bean.setEnterTime(wifiStateRecordBean.getEnterTime());
						bean.setLeaveTime(wifiStateRecordBean.getLeaveTime());
						bean.setMacAddress(wifiStateRecordBean.getMac());
						topTimeBean.getTimeIntervalList().add(bean);
						topTimeBean.setPlaceName(wifiStateRecordBean.getPlaceName());
					}
				}	
			}
		}
		resultMap.put("list", list) ;
		return SUCCESS;
	}
	
	/**
	 * 分组统计结果model转bean
	 * 
	 * @param hs 分组统计结果model
	 * @param paramMap 查询条件</br>条件包括mac地址列表、起始时间、结束时间。
	 * macList mac地址列表
	 * startTime 开始时间
	 * endTime 结束时间
	 * placeName 场所名称
	 * @return hsb 分组统计结果bean
	 */
	public HitSumBean hitSumToHitSumBean(PlaceSumInfo hs, Map<String, Object> paramMap){
		if(hs == null){
			return null;
		}
		HitSumBean hsb = new HitSumBean();
		hsb.setCount(hs.getCount());
		hsb.setGroupName(hs.getGroupName());
		hsb.setTotalInterval(hs.getTotalInterval());
		//查询时间列表
		paramMap.put("placeName", hs.getGroupName());
		List<WifiPlaceInAndOutInfo> wfhList = wifiMacHitQueryService.findAllByMacs(paramMap);
		List<BaseBean> timeIntervalList = new ArrayList<BaseBean>();
		for(WifiPlaceInAndOutInfo wfh : wfhList){
			if(wfh.getEnterTime() == null || wfh.getLeaveTime() == null){
				continue;
			}
			BaseBean bb = new BaseBean();
			bb.setStartTime(DateFmtUtil.dateToLong(wfh.getEnterTime()));
			bb.setEndTime(DateFmtUtil.dateToLong(wfh.getLeaveTime()));
			timeIntervalList.add(bb);
		}
		hsb.setTimeIntervalList(timeIntervalList);
		return hsb;
	}
	
	@SuppressWarnings("unchecked")
	public String generateWifiLocusSnapshot(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String search = (String)rqst.get("search");
		
		
		if(search.length() == 15 || search.length() == 18){
			idCode = search;	
		}else if(search.length() == 11){
			HighriskPerson person = highriskPersonService.findByMobile(search);
			idCode = person.getIdcode();
		}else{
			HighriskPerson person = highriskPersonService.findByMac(search);
			idCode = person.getIdcode();
		}

		
		InfoSnapshot infoSnapshot = new InfoSnapshot();
		infoSnapshot.setType(HighriskPerson.class.getName());
		infoSnapshot.setCode(INFO_SNAPSHOT_MODULE.CSJKFX_WIFIGJ.getValue());
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

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public List<String> getMacList() {
		return macList;
	}

	public void setMacList(List<String> macList) {
		this.macList = macList;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public List<HitSumBean> getHitSumBeanList() {
		return hitSumBeanList;
	}

	public void setHitSumBeanList(List<HitSumBean> hitSumBeanList) {
		this.hitSumBeanList = hitSumBeanList;
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

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getSnapshotInfo() {
		return snapshotInfo;
	}
	public void setSnapshotInfo(String snapshotInfo) {
		this.snapshotInfo = snapshotInfo;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
}
