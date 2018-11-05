package com.taiji.pubsec.szpt.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.complex.tools.web.bean.ZTreeBean;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.bean.OptionItemBean;


@Controller("unitUtilAction")
@Scope("prototype")
public class UnitUtilAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	private String queryStr ;
	
	private String ztreeNodeId;
	
	private List<ZTreeBean> ztreeList = new ArrayList<ZTreeBean>() ;
	
	@Resource
	private IUnitService unitService;
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	/**
	 * 根据单位code查询下级
	 * @return
	 */
	public String findOrderCellByCode(){
		if(ParamMapUtil.isNotBlank(queryStr) && queryStr.length()>0){
			String code = queryStr ;
			List<OrderCell> list =this.szptUnitCommonService.findOrderCellByCode(code);
			resultMap.put("list", list);
		}
		return SUCCESS;
	}
	
	public String findOrderCellByType(){
		if(ParamMapUtil.isNotBlank(queryStr) && queryStr.length()>0){
			String type = queryStr ;
			List<OrderCell> list =this.szptUnitCommonService.findOrderCellByType(type);
			resultMap.put("list", list);
		}
		return SUCCESS;
	}
	
	/**
	 *  根据类型查询单位集合，select用t_og
	 * @return
	 */
	public String queryUnitAllByType(){
		if(ParamMapUtil.isNotBlank(queryStr) && queryStr.length()>0){
			String type = queryStr ;
			List<OptionItemBean> list = new ArrayList<OptionItemBean>();
			//查询所有单位
			List<Unit> units = unitService.findAll();
			for(Unit unit : units){
				if(type.equals(unit.getType())){
					OptionItemBean oib = new OptionItemBean();
					oib.setId(unit.getId());
					oib.setCode(unit.getCode());
					oib.setName(unit.getShortName());
					list.add(oib);
				}
			}
			resultMap.put("list", list);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有的单位
	 * @return
	 */
	public String queryUnit(){
		List<Unit> allUnit = null;
		if(StringUtils.isBlank(ztreeNodeId)){
			allUnit=unitService.findSubUnitsByUnitId("01");
		}else{
			allUnit = (List<Unit>) unitService.findSubUnitsByUnitId(ztreeNodeId);
		}
		ztreeList = new ArrayList<ZTreeBean>();
		if(allUnit!=null){
			for(Unit unit : allUnit){
				ZTreeBean bean = new ZTreeBean();
				bean.setId(unit.getId());
				bean.setName(unit.getShortName());
				Map<Object,Object> diyMap = new HashMap<Object,Object>();
				diyMap.put("id", unit.getId());
				diyMap.put("code", unit.getCode());
				bean.setDiyMap(diyMap);
				if(unit.getSuperOrg() != null){
					if(unit.getSuperOrg().getId() != null){
						bean.setpId(unit.getSuperOrg().getId());
					}
				}else{
					bean.setpId("");
				}
				
				if(unit.getSuperOrg() == null ){
					bean.setIsParent("true");
				}else{
					bean.setIsParent("false");
				}
				bean.setIcon("../images/xtgl_icon/ztree-icon_dw.png");
				ztreeList.add(bean);
			}
		}
		
		return SUCCESS;
	}

	public String getZtreeNodeId() {
		return ztreeNodeId;
	}


	public void setZtreeNodeId(String ztreeNodeId) {
		this.ztreeNodeId = ztreeNodeId;
	}


	public List<ZTreeBean> getZtreeList() {
		return ztreeList;
	}


	public void setZtreeList(List<ZTreeBean> ztreeList) {
		this.ztreeList = ztreeList;
	}


	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
}
