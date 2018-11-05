package com.taiji.pubsec.szpt.placemonitor.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.placemonitor.action.bean.BaseBean;
import com.taiji.pubsec.szpt.placemonitor.action.bean.PlaceBasicInfoBean;
import com.taiji.pubsec.szpt.placemonitor.action.util.ModelBeanTransformUtilInPlaceMonitor;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiPlaceInAndOutInfoBean;
import com.taiji.pubsec.szpt.placemonitor.pojo.WifiStateRecord;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceMonitorService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiStateRecordService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.LoginInfoAction;

import net.sf.json.JSONObject;

/**
 * 实时WIFI Action
 * @author WL-PC
 *
 */
@Controller("realTimeWifiAction")
@Scope("prototype")
public class RealTimeWifiAction extends LoginInfoAction{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RealTimeWifiAction.class);
	
	@Resource
	private PlaceMonitorService placeMonitoringService;// wifi热点数量和设备数量查询接口
	
	@Resource
	private InfoSnapshotService infoSnapshotService;// 快照接口
	
	@Resource
	private IInstructionService instructionService;// 指令接口
	
	@Resource
	private IHighriskPersonService highriskPersonService;// 高危人接口
	
	@Resource
	private WifiStateRecordService wifiStateRecordService;
	
	@Resource
	private WifiPlaceInAndOutInfoQueryService wifiMacHitQueryService; // wifi围栏命中信息接口

	@Resource
	private ModelBeanTransformUtilInPlaceMonitor modelBeanTransformUtil; //action model和bean对象转换工具
	
	private List<BaseBean> baseBeanList = new ArrayList<BaseBean>();//  监控点列表
	
	private List<String> placeNameList = new ArrayList<String>();// 场所名称集合
	
	private List<PlaceBasicInfoBean> placeBasicInfoBeanList = new ArrayList<PlaceBasicInfoBean>();// 场所对象Bean集合
	
	private String placeSum;//  监控点总数
	
	private String deviceaSum;//  被监控对象总数
	
	private List<WifiPlaceInAndOutInfoBean> wifiPlaceInAndOutInfoBeanList = new ArrayList<WifiPlaceInAndOutInfoBean>(); // wifi围栏命中信息Bean集合

	private List<String> macList = new ArrayList<String>(); // mac地址集合
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;
	
	private Long startTime;// 开始时间
	
	private Long endTime;// 结束时间
	
	private String searchText;//输入信息
	
	private String snapshotInfo;//快照信息
	
	
	
	/**
	 * 根据mac地址查询轨迹列表
	 * 
	 * @param macList mac地址集合
	 * @return "success"，成功返回wifiFenceHitBeanList:wifi围栏命中信息Bean集合
	 */
	//查询按钮
	public String findAllLocusByMacs() {
		List<WifiPlaceInAndOutInfoBean> list = placeMonitoringService.findAllByMacs(macList);
		if(list == null){
			return SUCCESS;
		}
		wifiPlaceInAndOutInfoBeanList = list ;
		return SUCCESS;
	}
	
	/**
	 * 根据场所名称查询场所详情*****
	 * @param paramMap 新增的时间判断
	 * 
	 * @param placeNameList 场所名称集合
	 * @return "success",成功返回值 WifiStateRecord:场所对象Bean集合
	 */
	public String findAllPlaceByPlaceName(Map<String, Object> paramMap){
		Date startDay = DateFmtUtil.longToDate(startTime) ;
		Date endDay = DateFmtUtil.longToDate(endTime) ;
		String placeCode=placeNameList.get(0);
		
		List<WifiStateRecord> list = wifiStateRecordService.findByPlaces(placeCode, startDay, endDay );
		
		resultMap.put("list", list);
		return SUCCESS;
	}
	
	/**
	 * 查询所有WIFI监控点列表
	 * 
	 * @return "success",成功返回值 baseBeanList:监控点列表
	 */
	//实施wifi热点方法action方法
	public String findAllPlaceList(){
		Map<String,Object> paramMap = new HashMap<String,Object>(); 
//		paramMap.put("timeStart", DateFmtUtil.longToDate(startTime));//
//		paramMap.put("timeEnd", DateFmtUtil.longToDate(endTime));//
		List<PlaceSumInfo> objList = placeMonitoringService.sumupDeviceaByPlace();
		for(PlaceSumInfo obj : objList){
			BaseBean bb = new BaseBean();
			PlaceBasicInfoBean pb = new PlaceBasicInfoBean();
			bb.setPlaceName(obj.getGroupName());
			bb.setDeviceSum(String.valueOf(obj.getCount()));
			baseBeanList.add(bb);
			pb.setInternetServicePlaceName(obj.getGroupName());
			pb.setLatitude(obj.getLatitude());
			pb.setLongitude(obj.getLongitude());
			pb.setPlaceMonitorNum(obj.getCount());
			placeBasicInfoBeanList.add(pb);
			placeNameList.add(obj.getGroupName());
		}
		//findAllPlaceByPlaceName(paramMap);
		return SUCCESS;
	}
	
	/**
	 * 查询wifi监控点总数
	 * 
	 * @return "success",成功返回值 placeSum:监控点总数
	 */
	//wifi围栏监测点总数action
	public String findPlaceSum(){
		Integer ps = placeMonitoringService.sumupPlaceNum();
		placeSum = String.valueOf(ps);
		return SUCCESS;
	}
	
	/**
	 * 查询终端设备
	 * 
	 * @return "success",成功返回值 deviceaSum:被监控对象总数
	 */
	public String findDeviceaSum(){
		Number ds = placeMonitoringService.sumupMonitorDeviceCount();
		deviceaSum = String.valueOf(ds);
		return SUCCESS;
	}
	
	/**
	 * 场所对象model转bean
	 * 
	 * @param wifiPlaceInAndOutInfoBeanList 场所设备table
	 * @param pbi 场所对象model
	 * @return pbib 场所对象bean
	 * 
	 */
//	private static PlaceBasicInfoBean placeBasicInfoToPlaceBasicInfoBean(PlaceBasicInfo pbi,List<WifiPlaceInAndOutInfoBean> wifiPlaceInAndOutInfoBeanList){
//		if(pbi == null){
//			return null;
//		}
//		PlaceBasicInfoBean pbib = new PlaceBasicInfoBean();
//		BeanUtils.copyProperties(pbi, pbib);
//		pbib.setBusinessStartTime(DateFmtUtil.dateToLong(pbi.getBusinessStartTime()));
//		pbib.setBusinessEndTime(DateFmtUtil.dateToLong(pbi.getBusinessEndTime()));
//		pbib.setPlaceMonitorNum(pbi.getPlaceMonitor() != null?pbi.getPlaceMonitor().getNum():0);
//		pbib.setWifiPlaceInAndOutInfoBeanList(wifiPlaceInAndOutInfoBeanList);
//		return pbib;
//	}
	
	/**
	 * 生成快照
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String generateRealTimeSnapshot(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String mac = (String)rqst.get("mac");
		HighriskPerson hp = highriskPersonService.findByMac(mac);
		if(hp == null){
			resultMap.put("flag", false);
			resultMap.put("msg", "mac未关联高危人信息，无法保存快照。");
		}else{
			InfoSnapshot is = new InfoSnapshot();
			
			is.setTargetId(hp.getIdcode());
			is.setType(HighriskPerson.class.getName());
			is.setCreatedDate(new Date());
			is.setCreatePerson(this.findCurrentPerson().getName());
			is.setSnapshot(rqst.get("wifiSnapshootObject").toString());
			is.setCode(INFO_SNAPSHOT_MODULE.CSJKFX_SSWIFI.getValue());
			is.setIntro((String)rqst.get("snapshotInfo"));
			resultMap.put("flag", infoSnapshotService.saveInfoSnapshot(is));
			
			//保存反馈
			String id = (String)rqst.get("id");
			if("".equals(id) || null == id){
				return SUCCESS;
			}
			InstructionReceiveSubjectFeedback irsf = new InstructionReceiveSubjectFeedback();
			irsf.setFeedbackContent(is.getIntro());
			irsf.setFeedbackTime(new Date());
			irsf.setFeedbackPeopleName(this.findCurrentPerson().getName());
			irsf.setFeedbackPeopleId(this.findCurrentPerson().getId());
			irsf.setRelateObjectType(InfoSnapshot.class.getName());
			irsf.setRelateObjectId(is.getId());
			try {
				instructionService.feedbackInstruction(irsf, id);
			} catch (Exception e) {
				resultMap.put("flag", false);
				resultMap.put("msg", "快照反馈失败。");
				LOGGER.debug("场所监控分析-实时wifi生成快照功能，指令反馈快照失败。",e);
			}
		}
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

	public List<BaseBean> getBaseBeanList() {
		return baseBeanList;
	}

	public void setBaseBeanList(List<BaseBean> baseBeanList) {
		this.baseBeanList = baseBeanList;
	}

	public String getPlaceSum() {
		return placeSum;
	}

	public void setPlaceSum(String placeSum) {
		this.placeSum = placeSum;
	}

	public String getDeviceaSum() {
		return deviceaSum;
	}

	public void setDeviceaSum(String deviceaSum) {
		this.deviceaSum = deviceaSum;
	}

	public List<String> getPlaceNameList() {
		return placeNameList;
	}

	public void setPlaceNameList(List<String> placeNameList) {
		this.placeNameList = placeNameList;
	}

	public List<PlaceBasicInfoBean> getPlaceBasicInfoBeanList() {
		return placeBasicInfoBeanList;
	}

	public void setPlaceBasicInfoBeanList(List<PlaceBasicInfoBean> placeBasicInfoBeanList) {
		this.placeBasicInfoBeanList = placeBasicInfoBeanList;
	}

	public List<String> getMacList() {
		return macList;
	}

	public void setMacList(List<String> macList) {
		this.macList = macList;
	}

	public List<WifiPlaceInAndOutInfoBean> getWifiPlaceInAndOutInfoBeanList() {
		return wifiPlaceInAndOutInfoBeanList;
	}

	public void setWifiPlaceInAndOutInfoBeanList(
			List<WifiPlaceInAndOutInfoBean> wifiPlaceInAndOutInfoBeanList) {
		this.wifiPlaceInAndOutInfoBeanList = wifiPlaceInAndOutInfoBeanList;
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSnapshotInfo() {
		return snapshotInfo;
	}

	public void setSnapshotInfo(String snapshotInfo) {
		this.snapshotInfo = snapshotInfo;
	}
	
	
	
}
