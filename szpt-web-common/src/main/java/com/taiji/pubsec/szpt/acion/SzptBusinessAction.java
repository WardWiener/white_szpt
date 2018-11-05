package com.taiji.pubsec.szpt.acion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.complex.tools.web.bean.ZTreeBean;
import com.taiji.pubsec.szpt.bean.CommunityBean;
import com.taiji.pubsec.szpt.bean.DictionaryItemBean;
import com.taiji.pubsec.szpt.bean.SzptUnitBean;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.common.model.Jqlx;
import com.taiji.pubsec.szpt.common.model.OrderCell;
import com.taiji.pubsec.szpt.common.model.SzptUnit;
import com.taiji.pubsec.szpt.service.ICommunityService;
import com.taiji.pubsec.szpt.service.JqlxCommonService;
import com.taiji.pubsec.szpt.service.SzptCommonService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.bean.OptionItemBean;

@Controller("szptBusinessAction")
@Scope("prototype")
public class SzptBusinessAction extends BaseAction{
	
	@Resource
	private IUnitService unitService;// og单位接口
	
	@Resource
	private ICommunityService communityService;// og村居接口
	
	//@Resource
	//private SzptUnitCommonService szptUnitCommonService ;
	@Resource
	private SzptCommonService szptCommonService ;
	@Resource
	private SzptUnitCommonService szptUnitCommonService ;
	@Resource
	private IDictionaryItemService dictionaryItemService ;
	@Resource
	private JqlxCommonService jqlxCommonService ;
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(SzptBusinessAction.class);
	
	private String topCode;
	
	private String ztreeNodeId;
	
	private String dicTypeCode;
	
	private Map<String, Object> resultMap = new HashMap<String, Object>() ;

	private List<ZTreeBean> ztreeList = new ArrayList<ZTreeBean>() ;
	
	@SuppressWarnings("unchecked")
	public String businessInitInfo(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		
		String unitType = (String)rqst.get("ogUnitType") ;
		resultMap.put("pcs", this.queryUnitAllByType(unitType)) ;
		//resultMap.put("pcs", this.unitListToSzptUnitBeanList(this.szptUnitCommonService.findUnitByType(unitType))) ;
		resultMap.put("country", this.countryListToCountryBeanList(this.szptCommonService.findAllCommunity())) ;
		//主格
		resultMap.put("zhuge", this.szptUnitCommonService.findOrderCellByType(OrderCell.ORDERCELL_TYPE.TYPE_ZHUGE.getValue())) ;
		
		//根据类型查询t_og_dw里的派出所
		String ogUnitType = (String)rqst.get("ogUnitType");
		resultMap.put("ogUnitPcs", this.queryUnitAllByType(ogUnitType));
		//查询t_og_cj表里的所有村居
		resultMap.put("ogAllCj", this.queryAllCommunity());
		//停起状态
		resultMap.put("zt", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZT.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		resultMap.put("yjlx", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_YJLX.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		resultMap.put("rylx", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_RYLX.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		resultMap.put("qklx", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_QKLX.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//案件类别
		resultMap.put("ajlb", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_AJXZ.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//案件级别
		resultMap.put("ajjb", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_AJJB.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//作案特点
		resultMap.put("zatd", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZATD.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//作案进出口
		resultMap.put("zajck", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZACRK.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//作案人数
		resultMap.put("zars", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZARS.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//作案时段
		resultMap.put("zasd", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_ZATIME.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//发案处所
		resultMap.put("facs", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_FACS.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		//打标状态
		resultMap.put("dbzt", this.dicItemListToDicItemBeanList(dictionaryItemService.findDicItemsByTypeCode(DICT.DICT_TYPE_AJDBZT.getValue(), DICT.DICT_ENABLED.getValue()))) ;
		return SUCCESS ;
	}
	
	/**
	 * 根据类型查询单位集合，select用t_og
	 * 
	 * @param type 单位类型编码
	 * @return oibs select通用Bean集合
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
	
	/**
	 * 查询所有t_og村居
	 * 
	 * @return oibs select通用Bean集合
	 */
	private List<OptionItemBean> queryAllCommunity(){
		List<OptionItemBean> oibs = new ArrayList<OptionItemBean>();
		
		List<Community> cs = communityService.findAllCommunityList();
		if(cs == null || cs.isEmpty()){
			return oibs;
		}
		for(Community c : cs){
			OptionItemBean oib = new OptionItemBean();
			oib.setId(c.getId());
			oib.setCode(c.getCode());
			oib.setName(c.getName());
			oibs.add(oib);
		}
		return oibs;
	}
	
	
	public String queryJqlx(){
		List<Jqlx> itemLst = null;
		if(StringUtils.isBlank(ztreeNodeId)){
			itemLst = jqlxCommonService.findJqlxsByLevel(0);
		}else{
			itemLst = jqlxCommonService.findJqlxsByParentId(ztreeNodeId);
		}
		ztreeList = new ArrayList<ZTreeBean>();
		if(itemLst != null){
			for(Jqlx item : itemLst){
				ZTreeBean bean = new ZTreeBean();
				bean.setId(item.getId());
				bean.setName(item.getName());
				Map<Object,Object> diyMap = new HashMap<Object,Object>();
				diyMap.put("code", item.getCode());
				bean.setDiyMap(diyMap);
				bean.setpId(item.getParentId());
				List<Jqlx> temp = jqlxCommonService.findJqlxsByParentId(item.getId());
				if(temp.size() > 0){
					bean.setIsParent("true");
				}else{
					bean.setIsParent("false");
				}
				bean.setIcon("../images/xtgl_icon/ztree-icon_bm.png");
				ztreeList.add(bean);
			}
		}
	
		return SUCCESS ;
	}
	
	@SuppressWarnings("null")
	public String queryXsjq(){
		List<Jqlx> itemLst = null;
		if(StringUtils.isBlank(ztreeNodeId)){
			itemLst.add(jqlxCommonService.findJqlxByCode(DICT.DICT_JQLX_CODE_XSJQ.getValue()));
		}else{
			itemLst = jqlxCommonService.findJqlxsByParentId(ztreeNodeId);
		}
		ztreeList = new ArrayList<ZTreeBean>();
		if(itemLst != null){
			for(Jqlx item : itemLst){
				ZTreeBean bean = new ZTreeBean();
				bean.setId(item.getId());
				bean.setName(item.getName());
				Map<Object,Object> diyMap = new HashMap<Object,Object>();
				diyMap.put("code", item.getCode());
				bean.setDiyMap(diyMap);
				bean.setpId(item.getParentId());
				List<Jqlx> temp = jqlxCommonService.findJqlxsByParentId(item.getId());
				if(temp.size() > 0){
					bean.setIsParent("true");
				}else{
					bean.setIsParent("false");
				}
				bean.setIcon("../images/xtgl_icon/ztree-icon_bm.png");
				ztreeList.add(bean);
			}
		}
		
		return SUCCESS ;
	}
	
	public String queryAjxz(){
		DictionaryItem item = null;
		if(StringUtils.isBlank(ztreeNodeId)){
			item = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), topCode, DICT.DICT_ENABLED.getValue());
		}else{
			item = dictionaryItemService.findById(ztreeNodeId);
		}
		ztreeList = new ArrayList<ZTreeBean>();
		if(item!=null){
			for(DictionaryItem dic : item.getSubItems()){
				ZTreeBean bean = new ZTreeBean();
				bean.setId(dic.getId());
				bean.setName(dic.getName());
				Map<Object,Object> diyMap = new HashMap<Object,Object>();
				diyMap.put("code", dic.getCode());
				bean.setDiyMap(diyMap);
				if(dic.getParentItem() != null){
					bean.setpId(dic.getParentItem().getId());
				}else{
					bean.setpId("");
				}
				if(dic.getSubItems() != null && dic.getSubItems().size() > 0){
					bean.setIsParent("true");
				}else{
					bean.setIsParent("false");
				}
				bean.setIcon("../images/xtgl_icon/ztree-icon_bm.png");
				ztreeList.add(bean);
			}
		}
		return SUCCESS ;
	}
	
	/**
	 * 查询村居
	 * 
	 * @return
	 */
	public String queryCunJu(){
		List<OptionItemBean> unitPcsList = null;
		List<Community> communityList = null;
		
		if(StringUtils.isBlank(ztreeNodeId)){
			unitPcsList = queryUnitAllByType(DICT.DICT_DWLX_PCS.getValue());
		}else{
			communityList = communityService.findCommunityByUnitId(ztreeNodeId);
		}
		ztreeList = new ArrayList<ZTreeBean>();
		if(unitPcsList != null){
			for(OptionItemBean oib : unitPcsList){
				ZTreeBean bean = new ZTreeBean();
				bean.setId(oib.getId());
				bean.setName(oib.getName());
				Map<Object,Object> diyMap = new HashMap<Object,Object>();
				diyMap.put("id", oib.getId());
				diyMap.put("code", oib.getCode());
				diyMap.put("type", DICT.DICT_DWLX_PCS.getValue());
				bean.setDiyMap(diyMap);
				List<Community> cList = communityService.findCommunityByUnitId(oib.getId());
				if(cList.size() > 0){
					bean.setIsParent("true");
				}else{
					bean.setIsParent("false");
				}
				bean.setIcon("../images/ztree/ztree-icon_dw.png");
				ztreeList.add(bean);
			}
		}else if(communityList != null){
			for(Community c : communityList){
				ZTreeBean bean = new ZTreeBean();
				bean.setId(c.getId());
				bean.setName(c.getName());
				Map<Object,Object> diyMap = new HashMap<Object,Object>();
				diyMap.put("code", c.getCode());
				diyMap.put("id", c.getId());
				bean.setDiyMap(diyMap);
				bean.setpId(c.getUnitId());
				bean.setIsParent("false");
				bean.setIcon("../images/ztree/ztree-icon_dot.png");
				ztreeList.add(bean);
			}
		}
		return SUCCESS;
	}
	
	public String queryDictionaryTree(){
		List<DictionaryItem> lst = null;
		if(StringUtils.isBlank(ztreeNodeId)){
			lst = dictionaryItemService.findDicItemsByTypeCode(dicTypeCode, DICT.DICT_ENABLED.getValue());
		}else{
			lst = dictionaryItemService.findDicItemsByParent(ztreeNodeId, DICT.DICT_ENABLED.getValue());
		}
		ztreeList = new ArrayList<ZTreeBean>();
		if(lst != null){
			for(DictionaryItem dic : lst){
				ZTreeBean bean = new ZTreeBean();
				bean.setId(dic.getId());
				bean.setName(dic.getName());
				Map<Object,Object> diyMap = new HashMap<Object,Object>();
				diyMap.put("code", dic.getCode());
				bean.setDiyMap(diyMap);
				if(dic.getParentItem() != null){
					bean.setpId(dic.getParentItem().getId());
				}else{
					bean.setpId("");
				}
				if(dic.getSubItems() != null && dic.getSubItems().size() > 0){
					bean.setIsParent("true");
				}else{
					bean.setIsParent("false");
				}
				bean.setIcon("../images/xtgl_icon/ztree-icon_bm.png");
				ztreeList.add(bean);
			}
		}
	
		return SUCCESS ;
	}
	
	private List<CommunityBean> countryListToCountryBeanList(List<Community> countryLst){
		List<CommunityBean> list = new ArrayList<CommunityBean>() ;
		for(Community coun:countryLst){
			CommunityBean bean = new CommunityBean() ;
			bean.setCode(coun.getCode());
			bean.setId(coun.getId());
			bean.setName(coun.getName());
			list.add(bean) ;
		}
		return list ;
	}
	
	private List<DictionaryItemBean> dicItemListToDicItemBeanList(List<DictionaryItem> dicItemLst){
		List<DictionaryItemBean> list = new ArrayList<DictionaryItemBean>() ;
		for(DictionaryItem dicItem:dicItemLst){
			DictionaryItemBean bean = new DictionaryItemBean() ;
			bean.setCode(dicItem.getCode());
			bean.setId(dicItem.getId());
			bean.setName(dicItem.getName());
			list.add(bean) ;
		}
		return list ;
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

	public String getTopCode() {
		return topCode;
	}

	public void setTopCode(String topCode) {
		this.topCode = topCode;
	}

	public String getZtreeNodeId() {
		return ztreeNodeId;
	}

	public void setZtreeNodeId(String ztreeNodeId) {
		this.ztreeNodeId = ztreeNodeId;
	}

	public String getDicTypeCode() {
		return dicTypeCode;
	}

	public void setDicTypeCode(String dicTypeCode) {
		this.dicTypeCode = dicTypeCode;
	}

}
