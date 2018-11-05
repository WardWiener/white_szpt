package com.taiji.pubsec.szpt.placemonitor.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.placemonitor.model.PeopleType;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.placemonitor.util.PlaceMonitorConstant;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("wifiPlaceInAndOutInfoQueryService")
public class WifiPlaceInAndOutInfoQueryServiceImpl implements WifiPlaceInAndOutInfoQueryService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;

	@SuppressWarnings("unchecked")
	@Override
	public List<WifiPlaceInAndOutInfo> findAllByMacs(Map<String, Object> paramMap) {
		StringBuilder xqlSelect = new StringBuilder("select w from WifiPlaceInAndOutInfo as w ");
		String xqlWhere = " where 1 = 1";
		StringBuilder xqlCondition = new StringBuilder();
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);	
		if(ParamMapUtil.isNotBlank(paramMap.get("macList"))) {
			xqlCondition.append(" and w.mac in (:macList)");
			xqlMap.put("macList", paramMap.get("macList"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xqlCondition.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xqlCondition.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("placeName"))) {
			xqlCondition.append(" and w.place = :placeName");
			xqlMap.put("placeName", paramMap.get("placeName"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("peopleTypeList"))) {
			xqlSelect.append(" left join w.peopleTypes as pt");
			xqlCondition.append(" and pt.peopleType in (:peopleTypeList)");
			xqlMap.put("peopleTypeList", paramMap.get("peopleTypeList"));
		}
		xqlCondition.append(" order by w.enterTime desc");
		return dao.findAllByParams(WifiPlaceInAndOutInfo.class, xqlSelect.append(xqlWhere).append(xqlCondition).toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<WifiPlaceInAndOutInfo> findByMacs(Map<String, Object> paramMap, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select w from WifiPlaceInAndOutInfo as w where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("macList"))) {
			xql.append(" and w.mac in (:macList)");
			xqlMap.put("macList", paramMap.get("macList"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("placeName"))) {
			xql.append(" and w.place = :placeName");
			xqlMap.put("placeName", paramMap.get("placeName"));
		}
		xql.append(" order by w.enterTime desc");
		return dao.findByPage(WifiPlaceInAndOutInfo.class, xql.toString(), xqlMap, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	/*直接从人员类型种分组统计   1人 2类型   统计两次        类型返回按一级类型分组*/
	/*peopleType 编码每三位表示一层，001001*/
	public List<PlaceSumInfo> sumupHitCountByPeopleType(Map<String, Object> paramMap) {
		StringBuilder xql = new StringBuilder("select new " + PlaceSumInfo.class.getName() + "(SUBSTRING(pt.peopleType,1,3), count(pt.peopleType), sum(pt.wifiPlaceInfo.stayInterval)) "
				+ "from PeopleType as pt where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and pt.wifiPlaceInfo.leaveTime > :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and pt.wifiPlaceInfo.enterTime < :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("macList"))) {
			xql.append(" and pt.wifiPlaceInfo.mac in (:macList)");
			xqlMap.put("macList", paramMap.get("macList"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("placeName"))) {
			xql.append(" and pt.wifiPlaceInfo.place = :placeName");
			xqlMap.put("placeName", paramMap.get("placeName"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("peopleTypeList"))) {
			xql.append(" and pt.peopleType in (:peopleTypeList)");
			xqlMap.put("peopleTypeList", paramMap.get("peopleTypeList"));
		}
		xql.append(" group by SUBSTRING(pt.peopleType,1,3) order by count(pt) desc");
		List<PlaceSumInfo> list = this.dao.findByXql(xql.toString(), xqlMap);
		if(!list.isEmpty()) {
			
			/*高危人员类型数据字典由系统管理维护*/
			DictionaryType dicType = this.dictionaryTypeService.findDicTypeByCode(PlaceMonitorConstant.HIGHRISK_PERSON_TYPE_DIC_TYPE_CODE);	
			for(PlaceSumInfo si : list) {
				/*高危人员类型数据字典项由系统管理维护*/
				String name = this.dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(dicType.getId(), si.getGroupName(), null).getName();
				si.setGroupName(name); 
			}
			
			
//			for (PlaceSumInfo ps : list){
//				String peopleType = this.dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(PeopleType.PEOPLETYPE_DICTYPE_ID,
//						ps.getGroupName(), null).getName();
//				ps.setGroupName(peopleType);
//			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceSumInfo> sumupHitCountByPlace(Map<String, Object> paramMap) {
		StringBuilder xql = new StringBuilder("select new com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo(w.place, count(w)) from WifiPlaceInAndOutInfo as w where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("placeName"))) {
			xql.append(" and w.mac = :placeName");
			xqlMap.put("placeName", paramMap.get("placeName"));
		}
		xql.append(" group by w.place order by count(w)");
		return this.dao.findAllByParams(WifiPlaceInAndOutInfo.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceSumInfo> sumupStayCountByMacs(Map<String, Object> paramMap) {
		StringBuilder xql = new StringBuilder("select new com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo(w.place, count(w), 0) from WifiPlaceInAndOutInfo as w where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("macList"))) {
			xql.append(" and w.mac in (:macList)");
			xqlMap.put("macList", paramMap.get("macList"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		xql.append(" group by w.place order by count(w) desc ");
		return this.dao.findAllByParams(WifiPlaceInAndOutInfo.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceSumInfo> sumupStayIntervalByMacs(Map<String, Object> paramMap) {
		StringBuilder xql = new StringBuilder("select new com.taiji.pubsec.szpt.placemonitor.pojo.PlaceSumInfo(w.place, 0, sum(w.stayInterval)) from WifiPlaceInAndOutInfo as w where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("macList"))) {
			xql.append(" and w.mac in (:macList)");
			xqlMap.put("macList", paramMap.get("macList"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		xql.append(" group by w.place order by sum(w.stayInterval) desc");
		return this.dao.findAllByParams(WifiPlaceInAndOutInfo.class, xql.toString(), xqlMap);
	}

	//TODO    次数与停留时长     因为左连接  累加了
	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceSumInfo> sumupHitCountAndStayByCondition(Map<String, Object> paramMap) {
		
		StringBuilder hql = new StringBuilder("select distinct w.id from WifiPlaceInAndOutInfo as w left join w.peopleTypes as pt where 1 = 1");
		Map<String, Object> hqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(paramMap.get("peopleTypeList"))) {
			hql.append(" and pt.peopleType in (:peopleTypeList)");
			hqlMap.put("peopleTypeList", paramMap.get("peopleTypeList"));
		}
		List<String> idList = dao.findAllByParams(PeopleType.class, hql.toString(), hqlMap);

		StringBuilder xql = new StringBuilder("select new " + PlaceSumInfo.class.getName() + "(w.place, count(w.id), sum(w.stayInterval)) from WifiPlaceInAndOutInfo as w where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
			xql.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", paramMap.get("timeStart"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
			xql.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
		}
		if(!idList.isEmpty()) {
			xql.append(" and w.id in (:idList)");
			xqlMap.put("idList", idList);
		}
		xql.append(" group by w.place order by count(w) desc");
		List<PlaceSumInfo> list = this.dao.findByXql(xql.toString(), xqlMap);
		return list;
		
		
		
		
		
		
		
//		StringBuilder hql = new StringBuilder("select new " + PlaceSumInfo.class.getName() + "(a.place, count(a.id), sum(a.stayInterval)) from (select distinct id, place, stayInterval, leaveTime, enterTime from WifiPlaceInAndOutInfo as w left join w.peopleTypes as pt where 1 = 1");
//		Map<String, Object> xqlMap = new HashMap<String, Object>(0);
//		if(ParamMapUtil.isNotBlank(paramMap.get("peopleTypeList"))) {
//			hql.append(" and pt.peopleType in (:peopleTypeList)");
//			xqlMap.put("peopleTypeList", paramMap.get("peopleTypeList"));
//		}
//		hql.append(" ) a where 1 = 1");
////		if(ParamMapUtil.isNotBlank(paramMap.get("timeStart"))) {
////			hql.append(" and a.leaveTime > :timeStart");
////			xqlMap.put("timeStart", paramMap.get("timeStart"));
////		}
////		if(ParamMapUtil.isNotBlank(paramMap.get("timeEnd"))) {
////			hql.append(" and a.enterTime < :timeEnd");
////			xqlMap.put("timeEnd", paramMap.get("timeEnd"));
////		}
//		hql.append(" group by a.place order by count(a.id) desc");
//		List<PlaceSumInfo> list = this.dao.findByXql(hql.toString(), xqlMap);
//		return list;
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public WifiPlaceInAndOutInfo findWifiPlaceInAndOutInfoById(String wifiPlaceInAndOutInfoId) {
		return (WifiPlaceInAndOutInfo) this.dao.findById(WifiPlaceInAndOutInfo.class, wifiPlaceInAndOutInfoId);
	}
	
	@Override
	public List<WifiPlaceInAndOutInfo> queryWifiPlaceInAndOutInfo(WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo) {
		//比进入时间大离开时间为空 或者 比 进入时间大同时比离开时间小
		String hql = "from WifiPlaceInAndOutInfo where longitude =? and latitude=? and mac=? and place=? "
				+ " and personName=? and idCode=? and ((enterTime <= ? and leaveTime is null) or (enterTime <= ? and leaveTime >= ?))";
		@SuppressWarnings("unchecked")
		List<WifiPlaceInAndOutInfo> wifiPlaceInAndOutInfos = dao.findAllByParams(WifiPlaceInAndOutInfo.class, hql, new Object[]{
			wifiPlaceInAndOutInfo.getLongitude(), wifiPlaceInAndOutInfo.getLatitude(), wifiPlaceInAndOutInfo.getMac(),wifiPlaceInAndOutInfo.getPlace(),
			wifiPlaceInAndOutInfo.getPersonName(), wifiPlaceInAndOutInfo.getIdCode(), wifiPlaceInAndOutInfo.getEnterTime(), wifiPlaceInAndOutInfo.getEnterTime(), wifiPlaceInAndOutInfo.getEnterTime()});
		
		return wifiPlaceInAndOutInfos;
	}
	
	@Override
	public void saveWifiPlaceInAndOutInfo(WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo) {
		if (wifiPlaceInAndOutInfo.getLeaveTime() != null) {
			wifiPlaceInAndOutInfo.setStayInterval((int) ((wifiPlaceInAndOutInfo.getLeaveTime().getTime() - wifiPlaceInAndOutInfo.getEnterTime().getTime()) / 1000));
		}
		
		dao.save(wifiPlaceInAndOutInfo);
		for (PeopleType peopleType : wifiPlaceInAndOutInfo.getPeopleTypes()) {
			peopleType.setWifiPlaceInfo(wifiPlaceInAndOutInfo);
			dao.save(peopleType);
		}
	}

	@Override
	public void updateWifiPlaceInAndOutInfo(WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo) {
		WifiPlaceInAndOutInfo info = (WifiPlaceInAndOutInfo) dao.findById(WifiPlaceInAndOutInfo.class, wifiPlaceInAndOutInfo.getId());
		
		info.setStayInterval((int) ((wifiPlaceInAndOutInfo.getEnterTime().getTime() - info.getEnterTime().getTime()) / 1000));
		if (wifiPlaceInAndOutInfo.getLeaveTime() != null) {
			info.setLeaveTime(wifiPlaceInAndOutInfo.getLeaveTime());
			info.setStayInterval((int) ((wifiPlaceInAndOutInfo.getLeaveTime().getTime() - info.getEnterTime().getTime()) / 1000));
		}
		dao.update(info);
		
	}
	
	@Override
	public WifiPlaceInAndOutInfo findById(String id) {
		WifiPlaceInAndOutInfo wifiPlaceInAndOutInfo = (WifiPlaceInAndOutInfo) dao.findById(WifiPlaceInAndOutInfo.class, id);
		return wifiPlaceInAndOutInfo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PlaceSumInfo> summpHitCountByPersonIdCode(String idCode, Date timeStart, Date timeEnd) {
		StringBuilder xql = new StringBuilder("select new " + PlaceSumInfo.class.getName() + "(w.place, count(w.id)) from WifiPlaceInAndOutInfo as w where 1 = 1");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(idCode)) {
			xql.append(" and w.idCode = :idCode");
			xqlMap.put("idCode", idCode);
		}
		if(ParamMapUtil.isNotBlank(timeStart)) {
			xql.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if(ParamMapUtil.isNotBlank(timeEnd)) {
			xql.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		xql.append(" group by w.place order by count(w.id) desc");
		return this.dao.findAllByParams(WifiPlaceInAndOutInfo.class, xql.toString(), xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WifiPlaceInAndOutInfo> findWifiPlaceInAndOutInfoByIdCode(String idCode, Date timeStart, Date timeEnd) {
		StringBuilder hql = new StringBuilder("select w from WifiPlaceInAndOutInfo as w where w.idCode = :idCode");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("idCode", idCode);
		if (null != timeStart) {
			hql.append(" and w.leaveTime > :timeStart");
			xqlMap.put("timeStart", timeStart);
		}
		if (null != timeEnd) {
			hql.append(" and w.enterTime < :timeEnd");
			xqlMap.put("timeEnd", timeEnd);
		}
		hql.append(" order by w.enterTime desc");
		return dao.findAllByParams(WifiPlaceInAndOutInfo.class, hql.toString(), xqlMap);
	}

}
