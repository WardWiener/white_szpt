package com.taiji.pubsec.szpt.ptjc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.common.model.Country;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.common.model.SzptUnit;
import com.taiji.pubsec.szpt.common.model.SzptUnit.UNIT_TYPE;
import com.taiji.pubsec.szpt.ptjc.action.bean.DistributionRuleBean;
import com.taiji.pubsec.szpt.ptjc.model.DistributionRule;
import com.taiji.pubsec.szpt.ptjc.service.DistributionRuleService;
import com.taiji.pubsec.szpt.service.SzptCommonService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.Constant.STATISTICS_COLOR;
import com.taiji.pubsec.szpt.util.Constant.STATISTICS_TOTAL;
import com.taiji.pubsec.szpt.util.Constant.STATISTICS_TYPE;
import com.taiji.pubsec.szpt.util.ReturnMessageAction;

@Controller("distributionRuleAction")
@Scope("prototype")
public class DistributionRuleAction extends ReturnMessageAction {

	private static final long serialVersionUID = 1L;

	private String queryStr;

	private Map<String, Object> resultMap = new HashMap<String, Object>();

	@Resource
	DistributionRuleService distributionRuleService;
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;

	@Resource
	private SzptCommonService szptCommonService;
	
	public String findAllDistributionRule() {
		@SuppressWarnings("unchecked")
		Map<String, String> data = JSONObject.fromObject(queryStr);
		if(data.get("tjdx").equals(STATISTICS_TYPE.XLZG.getKey())){
			resultMap.put("dataList", findAllDistributionRuleByXLZG(data));
		}else if(data.get("tjdx").equals(STATISTICS_TYPE.PCSXQ.getKey())){
			resultMap.put("dataList", findAllDistributionRuleByPCSXQ(data));
		}else if(data.get("tjdx").equals(STATISTICS_TYPE.SQ.getKey())){
			resultMap.put("dataList", findAllDistributionRuleBySQ(data));
		}
		return SUCCESS;
	}
	
	public String saveAllDistributionRule() {
		JSONArray json = JSONArray.fromObject(read());
		@SuppressWarnings("unchecked")
		List<DistributionRuleBean> drBeanLst = (List<DistributionRuleBean>)JSONArray.toCollection(json, DistributionRuleBean.class);
		List<String> targetIdLst = new ArrayList<String>();
		String targetType = drBeanLst.get(0).getTargetType();
		String alarmTypeCode = drBeanLst.get(0).getAlarmTypeCode();
		List<DistributionRule> entityLst = new ArrayList<DistributionRule>();
		for(DistributionRuleBean bean : drBeanLst){
			if(!targetIdLst.contains(bean.getTargetId())){
				targetIdLst.add(bean.getTargetId());
			}
			entityLst.addAll(toDistributionRuleLst(bean));
		}
		distributionRuleService.deleteDistributionRuleByTargetAndAlarmTypeCode(targetIdLst, targetType, alarmTypeCode);
		for(DistributionRule entity : entityLst){
			distributionRuleService.saveDistributionRule(entity);
		}
		return SUCCESS;
	}

	private List<DistributionRuleBean> findAllDistributionRuleByXLZG(Map<String, String> data){
		List<DistributionRuleBean> beanLst = new ArrayList<DistributionRuleBean>();
		String targetType = "";
		List<String> targetIdLst = new ArrayList<String>();
		List<OrderCell> oclst = new ArrayList<OrderCell>();
		targetType = OrderCell.class.getName();
		oclst = this.szptUnitCommonService.findOrderCellByType(OrderCell.ORDERCELL_TYPE.TYPE_ZHUGE.getValue());;
		for(OrderCell su : oclst){
			targetIdLst.add(su.getId());
		}
		targetIdLst.add(STATISTICS_TOTAL.XLZG_TOTAL.getKey());
		List<DistributionRule> drLst = distributionRuleService.findDistributionRuleByTargetAndAlarmTypeCode(targetIdLst, targetType, data.get("jqlx"));
		for(OrderCell oc : oclst){
			beanLst.add(toDistributionRuleBean(data.get("jqlx"), oc.getId(), OrderCell.class.getName(), oc.getCode(), oc.getName(), drLst));
		}
		beanLst.add(toDistributionRuleBean(data.get("jqlx"), STATISTICS_TOTAL.XLZG_TOTAL.getKey(), OrderCell.class.getName(), STATISTICS_TOTAL.XLZG_TOTAL.getKey(), "合计", drLst));
		return beanLst;
	}
	
	private List<DistributionRuleBean> findAllDistributionRuleByPCSXQ(Map<String, String> data){
		List<DistributionRuleBean> beanLst = new ArrayList<DistributionRuleBean>();
		String targetType = "";
		List<String> targetIdLst = new ArrayList<String>();
		List<OrderCell> oclst = new ArrayList<OrderCell>();
		targetType = OrderCell.class.getName();
		oclst = this.szptUnitCommonService.findOrderCellByType(OrderCell.ORDERCELL_TYPE.TYPE_DW.getValue());
		for(OrderCell oc : oclst){
			if(oc.getName().indexOf("派出所") != -1){
				targetIdLst.add(oc.getId());
			}
		}
		targetIdLst.add(STATISTICS_TOTAL.PCSXQ_TOTAL.getKey());
		List<DistributionRule> drLst = distributionRuleService.findDistributionRuleByTargetAndAlarmTypeCode(targetIdLst, targetType, data.get("jqlx"));
		for(OrderCell oc : oclst){
			if(oc.getName().indexOf("派出所") != -1){
				beanLst.add(toDistributionRuleBean(data.get("jqlx"), oc.getId(), OrderCell.class.getName(), oc.getCode(), oc.getName(), drLst));
			}
		}
		beanLst.add(toDistributionRuleBean(data.get("jqlx"), STATISTICS_TOTAL.PCSXQ_TOTAL.getKey(), OrderCell.class.getName(), STATISTICS_TOTAL.PCSXQ_TOTAL.getKey(), "合计", drLst));
		return beanLst;
	}
	
	private List<DistributionRuleBean> findAllDistributionRuleBySQ(Map<String, String> data){
		List<DistributionRuleBean> beanLst = new ArrayList<DistributionRuleBean>();
		String targetType = "";
		List<String> targetIdLst = new ArrayList<String>();
		List<Community> clst = new ArrayList<Community>();
		targetType = Community.class.getName();
		clst = this.szptCommonService.findAllCommunity();
		for(Community c : clst){
			targetIdLst.add(c.getId());
		}
		targetIdLst.add(STATISTICS_TOTAL.SQ_TOTAL.getKey());
		List<DistributionRule> drLst = distributionRuleService.findDistributionRuleByTargetAndAlarmTypeCode(targetIdLst, targetType, data.get("jqlx"));
		for(Community c : clst){
			beanLst.add(toDistributionRuleBean(data.get("jqlx"), c.getId(), Country.class.getName(), c.getCode(), c.getName(), drLst));
		}
		beanLst.add(toDistributionRuleBean(data.get("jqlx"), STATISTICS_TOTAL.SQ_TOTAL.getKey(), Country.class.getName(), STATISTICS_TOTAL.SQ_TOTAL.getKey(), "合计", drLst));
		return beanLst;
	}
	
	private DistributionRuleBean toDistributionRuleBean(String jqlx, String targetId, String targetType, String targetCode, String targetName, List<DistributionRule> drLst){
		DistributionRuleBean bean = new DistributionRuleBean();
		bean.setAlarmTypeCode(jqlx);
		bean.setTargetCode(targetCode);
		bean.setTargetName(targetName);
		bean.setTargetId(targetId);
		bean.setTargetType(targetType);
		boolean nullFlag = true;
		for(DistributionRule dr : drLst){
			if(dr.getTargetId().equals(targetId) && dr.getTargetType().equals(targetType)){
				nullFlag = false;
				if(dr.getColor().equals(STATISTICS_COLOR.BLUE.getKey())){
					bean.setBlue(dr.getRange().split("<")[0]);
				}
				if(dr.getColor().equals(STATISTICS_COLOR.YELLOW.getKey())){
					bean.setYellow(dr.getRange().split("<")[0]);
				}
				if(dr.getColor().equals(STATISTICS_COLOR.ORANGE.getKey())){
					bean.setOrange(dr.getRange().split("<")[0]);
				}
				if(dr.getColor().equals(STATISTICS_COLOR.RED.getKey())){
					bean.setRed(dr.getRange().split("<")[0]);
				}
				if(dr.getColor().equals(STATISTICS_COLOR.ADVICE.getKey())){
					bean.setAdvice(dr.getRange());
				}
			}
		}
		if(nullFlag){
			bean.setBlue("");
			bean.setYellow("");
			bean.setOrange("");
			bean.setRed("");
			bean.setAdvice("");
		}
		return bean;
	}
	
	private List<DistributionRule> toDistributionRuleLst(DistributionRuleBean bean){
		List<DistributionRule> list = new ArrayList<DistributionRule>();
		list.add(dataToDistributionRule(bean, STATISTICS_COLOR.BLUE.getKey(), bean.getBlue() + "<=x<" + bean.getYellow()));
		list.add(dataToDistributionRule(bean, STATISTICS_COLOR.YELLOW.getKey(), bean.getYellow() + "<=x<" + bean.getOrange()));
		list.add(dataToDistributionRule(bean, STATISTICS_COLOR.ORANGE.getKey(), bean.getOrange() + "<=x<" + bean.getRed()));
		list.add(dataToDistributionRule(bean, STATISTICS_COLOR.RED.getKey(), bean.getRed() + "<=x"));
		list.add(dataToDistributionRule(bean, STATISTICS_COLOR.ADVICE.getKey(), bean.getAdvice()));
		return list;
	}
	
	private DistributionRule dataToDistributionRule(DistributionRuleBean bean, String color, String range){
		DistributionRule entity = new DistributionRule();
		entity.setAlarmTypeCode(bean.getAlarmTypeCode());
		entity.setTargetCode(bean.getTargetCode());
		entity.setTargetName(bean.getTargetName());
		entity.setTargetId(bean.getTargetId());
		entity.setTargetType(bean.getTargetType());
		entity.setColor(color);
		entity.setRange(range);
		return entity;
	}
	
	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	

}
