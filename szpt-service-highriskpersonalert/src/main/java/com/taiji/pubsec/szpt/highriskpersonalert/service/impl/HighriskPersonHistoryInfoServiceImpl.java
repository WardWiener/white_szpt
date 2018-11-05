package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPersonHistoryInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonHistoryInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;

@Service("highriskPersonHistoryInfoService")
public class HighriskPersonHistoryInfoServiceImpl implements IHighriskPersonHistoryInfoService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> statisticsHighPersonHistoryInfo(Date timeStart, Date timeEnd) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("timeStart", timeStart); 
		xqlMap.put("timeEnd", timeEnd);
		Map<String, Object> highriskPersonAdjustmentMap = new HashMap<String, Object>();
		String hql = "select hpi.operateContent, hpi.operateMethod, count(hpi) from HighriskPersonHistoryInfo as hpi where (hpi.operateTime between :timeStart and :timeEnd) and hpi.operateContent is not null and hpi.operateMethod is not null and hpi.operateType = '" + HighriskPersonConstant.HIGHRISK_PERSON_ADJUSTMENT
				+ "' group by hpi.operateContent, hpi.operateMethod order by hpi.operateContent, hpi.operateMethod";
		List<Object> highriskPersonAdjustmentList = this.dao.findAllByParams(HighriskPersonHistoryInfo.class, hql, xqlMap);
		int totalNuma = 0;
		if (!highriskPersonAdjustmentList.isEmpty()) {
			String operateContent = "";
			for(Object obj : highriskPersonAdjustmentList){
				Object[] o = (Object[]) obj;
				if(!operateContent.equals(o[0])){
					operateContent = o[0].toString();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put(o[1].toString(), o[2]); 
					highriskPersonAdjustmentMap.put(o[0].toString(), map);
					totalNuma +=  Integer.valueOf(o[2].toString());
				}
				else{
					Map<String, Object> map = (Map<String, Object>) highriskPersonAdjustmentMap.get(o[0].toString());
					map.put(o[1].toString(), o[2]);
					totalNuma +=  Integer.valueOf(o[2].toString());
				}
			}
		}
		highriskPersonAdjustmentMap.put("totalNum", totalNuma);
		resultMap.put("highriskPersonAdjustment", highriskPersonAdjustmentMap);
		
		Map<String, Object> personTypeAdjustmentMap = new HashMap<String, Object>();
		String xql = "select substring(hpi.operateContent, 1, 3), hpi.operateMethod, count(hpi) from HighriskPersonHistoryInfo as hpi where (hpi.operateTime between :timeStart and :timeEnd) and hpi.operateContent is not null and hpi.operateMethod is not null and hpi.operateType = '" + HighriskPersonConstant.PERSON_TYPE_ADJUSTMENT
				+ "' group by substring(hpi.operateContent, 1, 3), hpi.operateMethod order by substring(hpi.operateContent, 1, 3), hpi.operateMethod";
		List<Object> personTypeAdjustmentList = this.dao.findAllByParams(HighriskPersonHistoryInfo.class, xql, xqlMap);
		int totalNumb = 0;
		
		if (!personTypeAdjustmentList.isEmpty()) {
			String operateContent = "";
			for(Object obj : personTypeAdjustmentList){
				Object[] o = (Object[]) obj;
				if(!operateContent.equals(o[0])){
					operateContent = o[0].toString();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put(o[1].toString(), o[2]);
					personTypeAdjustmentMap.put(o[0].toString(), map);
					totalNumb +=  Integer.valueOf(o[2].toString());
				}
				else{
					Map<String, Object> map = (Map<String, Object>) personTypeAdjustmentMap.get(o[0].toString());
					map.put(o[1].toString(), o[2]);
					totalNumb +=  Integer.valueOf(o[2].toString());
				}
			}
		}
		personTypeAdjustmentMap.put("totalNum", totalNumb);
		resultMap.put("personTypeAdjustment", personTypeAdjustmentMap);
		Map<String, Integer> newHightPersonsHqlMap = new HashMap<String, Integer>(); 
		String newHightPersonsHql = "select hp from HighriskPerson as hp where (hp.createdTime between :timeStart and :timeEnd) and hp.operateStatus = '0' ";
		List<Object> newAddHighriskPerson = this.dao.findAllByParams(HighriskPerson.class, newHightPersonsHql, xqlMap);
		newHightPersonsHqlMap.put("totalNum", newAddHighriskPerson.size());
		resultMap.put("newAddHighriskPerson", newHightPersonsHqlMap);
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addHighriskPersonHistoryInfo(HighriskPersonHistoryInfo highriskPersonHistoryInfo) {
		dao.save(highriskPersonHistoryInfo);
	}

}
