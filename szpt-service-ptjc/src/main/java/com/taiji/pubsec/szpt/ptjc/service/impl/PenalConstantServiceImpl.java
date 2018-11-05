package com.taiji.pubsec.szpt.ptjc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.ptjc.bean.AlertThresholdInfo;
import com.taiji.pubsec.szpt.ptjc.model.PenalConstant;
import com.taiji.pubsec.szpt.ptjc.service.PenalConstantService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("penalConstantService")
public class PenalConstantServiceImpl implements PenalConstantService{


	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;

	/**
	 * 通过单位code查询刑事常量
	 * @param unitCodeLst 单位编码
	 * @param type 年月周日
	 * @return
	 */
	@Override
	public List<PenalConstant> findPenalConstantByUnitCodeAndType(
			List<String> unitCodeLst, String type) {
		
		List<PenalConstant> list = new ArrayList<PenalConstant>() ;
		StringBuilder hql = new StringBuilder("select p from  " + PenalConstant.class.getName() +" as p where 1 = 1");
		Map<String, Object> hqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(unitCodeLst)) {
			hql.append(" and p.unitCode in ("+inParamFormatForQuery((String[])unitCodeLst.toArray(new String[unitCodeLst.size()]))+") ");
		}
		
		if(ParamMapUtil.isNotBlank(type)) {
			hql.append(" and p.type = :type");
			hqlMap.put("type", type);
		}
	
		 list = this.dao.findAllByParams(PenalConstant.class, hql.toString(), hqlMap);

		return list;
	}

	/**
	 * 通过单位code删除刑事常量
	 * @param unitCodeLst 单位编码
	 * @param type 年月周日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void deletePenalConstantByUnitCode(List<String> unitCodeLst,
			String type) {
		
		List<PenalConstant> list = new ArrayList<PenalConstant>() ;
		StringBuilder hql = new StringBuilder("select p from  " + PenalConstant.class.getName() +" as p where 1 = 1");
		Map<String, Object> hqlMap = new HashMap<String, Object>(0);
		if(ParamMapUtil.isNotBlank(unitCodeLst)) {
			hql.append(" and p.unitCode in ("+inParamFormatForQuery((String[])unitCodeLst.toArray(new String[unitCodeLst.size()]))+") ");
		}
		
		if(ParamMapUtil.isNotBlank(type)) {
			hql.append(" and p.type = :type");
			hqlMap.put("type", type);
		}
	
		 list = this.dao.findAllByParams(PenalConstant.class, hql.toString(), hqlMap);
		 if(null != list && list.size() > 0){
			 for (PenalConstant entity : list) {
				dao.delete(entity);
			}
		 }
	}

	/**
	 * 保存刑事常量
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void savePenalConstant(PenalConstant entity) {
		
//		if(null == entity.getId()){
//			entity.setId(UUID.randomUUID().toString());
//		}
		dao.save(entity);
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

	@Override
	public List<AlertThresholdInfo> findPcsAlertThresholdInfosByType(String type) {

		List<OrderCell> orderCellList = szptUnitCommonService.findOrderCellByType(OrderCell.ORDERCELL_TYPE.TYPE_DW.getValue()) ;
		
		List<AlertThresholdInfo> beanList = new ArrayList<AlertThresholdInfo>();
		if(null != orderCellList && orderCellList.size() > 0){
			List<String> unitCodeList = new ArrayList<String>();
			for (OrderCell orderCell : orderCellList) {
				if(orderCell.getName().indexOf("派出所") != -1){
					unitCodeList.add(orderCell.getCode());
				}
			
			}
			List<PenalConstant> pcList = this.findPenalConstantByUnitCodeAndType(unitCodeList,type);
			for (OrderCell oc : orderCellList) {
				if(oc.getName().indexOf("派出所") != -1){
					beanList.add(penalConstantChangePCB(oc,pcList));
				}
			}
		}	
		return beanList;
	}
	
	/**
	 * 工具方法
	 * PenalConstant集合转AlertThresholdInfo
	 * @param su 派出所信息
	 * @param pcList PenalConstant集合
	 * @return AlertThresholdInfo
	 */
	private AlertThresholdInfo penalConstantChangePCB(OrderCell oc,List<PenalConstant> pcList){

		AlertThresholdInfo ati = new AlertThresholdInfo();
		ati.setCode(oc.getCode());
		ati.setName(oc.getName());
		if (null != pcList && pcList.size() > 0) {
			for (PenalConstant p : pcList) {
				if (oc.getCode().equals(p.getUnitCode())) {
					if (Constant.STATISTICS_COLOR.RED.name().equals(p
							.getColor())) {
						ati.setRedHoldValue(Integer.valueOf(p.getRange().split("<")[0]));
					}
					if (Constant.STATISTICS_COLOR.YELLOW.name().equals( p
							.getColor())) {
						ati.setYellowHoldValue(Integer.valueOf(p.getRange().split("<")[0]));
					}
					if (Constant.STATISTICS_COLOR.BLUE.name().equals( p
							.getColor())) {
						ati.setBlueHoldValue(Integer.valueOf(p.getRange().split("<")[0]));
					}
					if (Constant.STATISTICS_COLOR.ORANGE.name().equals(p
							.getColor())) {
						ati.setOrangeHoldValue(Integer.valueOf(p.getRange().split("<")[0]));
					}
				}
			}
		}
		if(null == ati.getBlueHoldValue()){
			ati.setBlueHoldValue(0);
		}
		if(null == ati.getYellowHoldValue()){
			ati.setYellowHoldValue(0);
		}
		if(null == ati.getOrangeHoldValue()){
			ati.setOrangeHoldValue(0);
		}
		if(null == ati.getRedHoldValue()){
			ati.setRedHoldValue(0);
		}
		return ati;
	}

}
