package com.taiji.pubsec.szpt.ptjc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.common.model.SzptUnit;
import com.taiji.pubsec.szpt.common.model.SzptUnit.UNIT_TYPE;
import com.taiji.pubsec.szpt.ptjc.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.ptjc.model.DistributionRule;
import com.taiji.pubsec.szpt.ptjc.service.DistributionRuleService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.Constant.STATISTICS_COLOR;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("distributionRuleService")
public class DistributionRuleServiceImpl implements DistributionRuleService{

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;

	@SuppressWarnings("unchecked")
	@Override
	public List<DistributionRule> findDistributionRuleByTargetAndAlarmTypeCode(
			List<String> targetIdLst, String targetType, String alarmTypeCode) {
		
		StringBuilder hql = new StringBuilder("select d from  " + DistributionRule.class.getName() +" as d where 1 = 1");
		Map<String, Object> hqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(targetIdLst)) {
			hql.append(" and d.targetId in ("+inParamFormatForQuery((String[])targetIdLst.toArray(new String[targetIdLst.size()]))+") ");
		}
		if(ParamMapUtil.isNotBlank(targetType)) {
			hql.append(" and d.targetType = :targetType");
			hqlMap.put("targetType", targetType);
		}
		if(ParamMapUtil.isNotBlank(alarmTypeCode)) {
			hql.append(" and d.alarmTypeCode = :alarmTypeCode");
			hqlMap.put("alarmTypeCode", alarmTypeCode);
		}
		List<DistributionRule> list = this.dao.findAllByParams(DistributionRule.class, hql.toString(), hqlMap);
		if(null != list){
			return list;
		}else{
			return new ArrayList<DistributionRule>();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteDistributionRuleByTargetAndAlarmTypeCode(
			List<String> targetIdLst, String targetType, String alarmTypeCode) {
		List<DistributionRule> list = this.findDistributionRuleByTargetAndAlarmTypeCode(targetIdLst, targetType, alarmTypeCode);
		for (DistributionRule entity : list) {
			dao.delete(entity);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveDistributionRule(DistributionRule entity) {
		this.dao.save(entity);
	}
	
	/**
	 * 通过target和警情类型查询分布规则
	 * @param targetId 目标id
	 * @param targetType 目标类型
	 * @param alarmTypeCode 警情类型code
	 * @return
	 */
	@Override
	public List<AlertThresholdInfo> findPcsFenbuAlertThresholdInfosByType(
			String type) {
		StringBuilder hql = new StringBuilder("select d from  " + DistributionRule.class.getName() +" as d where 1 = 1");
		Map<String, Object> hqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(type)) {
			hql.append(" and d.alarmTypeCode = :alarmTypeCode");
			hqlMap.put("alarmTypeCode", type);
		}
		List<DistributionRule> list = this.dao.findAllByParams(DistributionRule.class, hql.toString(), hqlMap);

		List<OrderCell> oclst = new ArrayList<OrderCell>();
		List<OrderCell> xlzg = this.szptUnitCommonService.findOrderCellByType(OrderCell.ORDERCELL_TYPE.TYPE_ZHUGE.getValue());
		for(OrderCell oc : xlzg){
			oclst.add(oc);
		}
		List<OrderCell> pcs = this.szptUnitCommonService.findOrderCellByType(OrderCell.ORDERCELL_TYPE.TYPE_DW.getValue());
		for(OrderCell oc : pcs){
			if(oc.getName().indexOf("派出所") != -1){
				oclst.add(oc);
			}
		}
		List<AlertThresholdInfo> infoLst = new ArrayList<AlertThresholdInfo>();
		for(OrderCell su : oclst){
			infoLst.add(toDistributionRuleBean( su.getId(), OrderCell.class.getName(), su.getCode(), su.getName(), list));
		}
		return infoLst;
	}
	
	/**
	 * 组装查询语句中IN参数。
	 * 
	 * @param Object[] formatInParmArr  IN参数数组
	 * @param 
	 * @return 返回数据形式：'a1','a2','a3'
	 */
	private String inParamFormatForQuery(Object[] formatInParmArr) {
		String formatInParm = "' '";
		if(null != formatInParmArr && formatInParmArr.length > 0) {
			for(int i = 0; i < formatInParmArr.length; i++) {
				if(null != formatInParmArr[i] && !"".equals(formatInParmArr[i].toString().trim())) {
					if(i == 0) {
						formatInParm = "";
						formatInParm += "'" + formatInParmArr[i].toString() + "'";
					}else {
						formatInParm += ",'" + formatInParmArr[i].toString() + "'";
					}
				}
			}
		}
		return formatInParm;
	}
	
	private AlertThresholdInfo toDistributionRuleBean( String targetId, String targetType, String targetCode, String targetName, List<DistributionRule> drLst){
		AlertThresholdInfo bean = new AlertThresholdInfo();
		bean.setCode(targetCode);
		bean.setName(targetName);
		boolean nullFlag = true;
		for(DistributionRule dr : drLst){
			if(dr.getTargetId().equals(targetId) && dr.getTargetType().equals(targetType)){
				nullFlag = false;
				if(dr.getColor().equals(STATISTICS_COLOR.BLUE.getKey())){
					bean.setBlueHoldValue(Integer.valueOf(dr.getRange().split("<")[0]));
				}
				if(dr.getColor().equals(STATISTICS_COLOR.YELLOW.getKey())){
					bean.setYellowHoldValue(Integer.valueOf(dr.getRange().split("<")[0]));
				}
				if(dr.getColor().equals(STATISTICS_COLOR.ORANGE.getKey())){
					bean.setOrangeHoldValue(Integer.valueOf(dr.getRange().split("<")[0]));
				}
				if(dr.getColor().equals(STATISTICS_COLOR.RED.getKey())){
					bean.setRedHoldValue(Integer.valueOf(dr.getRange().split("<")[0]));
				}
			}
		}
		if(nullFlag){
			bean.setBlueHoldValue(0);
			bean.setYellowHoldValue(0);
			bean.setOrangeHoldValue(0);
			bean.setRedHoldValue(0);
		}
		return bean;
	}

}
