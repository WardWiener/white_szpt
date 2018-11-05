package com.taiji.pubsec.szpt.acion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.complex.tools.web.bean.ZTreeBean;
import com.taiji.pubsec.szpt.bean.SzptUnitBean;
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.common.model.SzptUnit;
import com.taiji.pubsec.szpt.ptjc.service.DistributionRuleService;
import com.taiji.pubsec.szpt.ptjc.service.PenalConstantService;
import com.taiji.pubsec.szpt.service.JqlxCommonService;
import com.taiji.pubsec.szpt.service.SzptCommonService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.bean.OptionItemBean;

@Controller("szptAnalyzeAction")
@Scope("prototype")
public class SzptAnalyzeAction extends BaseAction{
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService ;
	@Resource
	private SzptCommonService szptCommonService ;
	@Resource
	private IDictionaryItemService dictionaryItemService ;
	@Resource
	private PenalConstantService penalConstantService ;
	@Resource
	private DistributionRuleService distributionRuleService ;
	@Resource
	private JqlxCommonService jqlxCommonService ;
	@Resource
	private IUnitService unitService;// og单位接口
	
	private List<ZTreeBean> ztreeList = new ArrayList<ZTreeBean>() ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(SzptAnalyzeAction.class);
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;

	@SuppressWarnings("unchecked")
	public String analyzeInitInfo(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		
		String unitType = (String)rqst.get("unitType") ;
		
		List<OptionItemBean> unitCodes = this.queryUnitAllByType(unitType) ;
		//List<SzptUnitBean> unitCodes = this.unitListToSzptUnitBeanList(this.szptUnitCommonService.findUnitByType(unitType)) ;
		resultMap.put("pcs", unitCodes) ;
		
		resultMap.put(Constant.PCS_THRESHOLD_TYPE.DAY.getValue(), this.penalConstantService.findPcsAlertThresholdInfosByType(Constant.PCS_THRESHOLD_TYPE.DAY.getValue())) ;
		resultMap.put(Constant.PCS_THRESHOLD_TYPE.WEEK.getValue(), this.penalConstantService.findPcsAlertThresholdInfosByType(Constant.PCS_THRESHOLD_TYPE.WEEK.getValue())) ;
		resultMap.put(Constant.PCS_THRESHOLD_TYPE.MONTH.getValue(), this.penalConstantService.findPcsAlertThresholdInfosByType(Constant.PCS_THRESHOLD_TYPE.MONTH.getValue())) ;
		resultMap.put(Constant.PCS_THRESHOLD_TYPE.YEAR.getValue(), this.penalConstantService.findPcsAlertThresholdInfosByType(Constant.PCS_THRESHOLD_TYPE.YEAR.getValue())) ;	
		//resultMap.put(Constant.DICT.DICT_TYPE_AJXZ.getValue(), SzptFmtUtil.dictionaryItemToOptionItemBean(dictionaryItemService.findDicItemsByTypeCode(Constant.DICT.DICT_TYPE_AJXZ.getValue(), Constant.DICT.DICT_ENABLED.getValue()))) ;
		
		resultMap.put(Constant.PCS_FENBU_THRESHOLD_TYPE.FENBU_DAY.getValue(), this.distributionRuleService.findPcsFenbuAlertThresholdInfosByType(null)) ;
		resultMap.put(Constant.PCS_FENBU_THRESHOLD_TYPE.FENBU_WEEK.getValue(), this.distributionRuleService.findPcsFenbuAlertThresholdInfosByType(null)) ;
		resultMap.put(Constant.PCS_FENBU_THRESHOLD_TYPE.FENBU_MONTH.getValue(), this.distributionRuleService.findPcsFenbuAlertThresholdInfosByType(null)) ;
		resultMap.put(Constant.PCS_FENBU_THRESHOLD_TYPE.FENBU_YEAR.getValue(), this.distributionRuleService.findPcsFenbuAlertThresholdInfosByType(null)) ;	

		return SUCCESS ;
	}
	
	/**
	 * 根据类型查询派出所编码
	 * 
	 * @param type 单位类型编码
	 * @return oibs 通用Bean集合
	 */
	private List<OptionItemBean> queryUnitAllByType(String type){
		List<OptionItemBean> oibs = new ArrayList<OptionItemBean>();
		//查询所有单位
		List<Unit> units = unitService.findAll();
		for(Unit unit : units){
			if(type.equals(unit.getType())){
				OptionItemBean oib = new OptionItemBean();
				oib.setId(unit.getId());
				oib.setCode(unit.getCode());
				oib.setName(unit.getShortName());
				oibs.add(oib);
			}
		}
		return oibs;
	}
	
	private List<SzptUnitBean> unitListToSzptUnitBeanList(List<SzptUnit> unitList){
		List<SzptUnitBean> list = new ArrayList<SzptUnitBean>() ;
		for(SzptUnit unit:unitList){
			SzptUnitBean bean = new SzptUnitBean() ;
			bean.setCode(unit.getCode());
			bean.setId(unit.getId());
			bean.setName(unit.getName());
			bean.setParentId(unit.getParentId());
			bean.setType(unit.getType());
			list.add(bean) ;
		}
		return list ;
	}
	
	public String queryJqlxTree(){
		
		String parentId = request.getParameter("ztreeNodeId") ;
		List<Jqlx> dList = this.jqlxCommonService.findJqlxsByParentId(parentId) ;
		
		for(Jqlx entity:dList){
			ZTreeBean bean = new ZTreeBean();
			bean.setpId(entity.getParentId());
			bean.setId(entity.getId());
			
			if(jqlxCommonService.findJqlxsByParentId(entity.getId()).size()>0){
				bean.setIsParent("true");
				bean.setOpen("true");
			}else{
				bean.setIsParent("false");
			}
			
			bean.setName(entity.getName());
			
			Map<Object, Object> diyMap = new HashMap<Object, Object>(0) ;
			diyMap.put("code", entity.getCode());
			diyMap.put("level", entity.getLevel());
			diyMap.put("id", entity.getId());
			
			bean.setDiyMap(diyMap);
			
			ztreeList.add(bean) ;
		}
		
		return SUCCESS ;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public List<ZTreeBean> getZtreeList() {
		return ztreeList;
	}

	public void setZtreeList(List<ZTreeBean> ztreeList) {
		this.ztreeList = ztreeList;
	}
	
	
}
