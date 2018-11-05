package com.taiji.pubsec.szpt.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.complex.tools.web.action.BaseAction;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.bean.OptionItemBean;

import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
@Controller("dictionaryItemAction")
@Scope("prototype")
public class DictionaryItemAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private String dictionaryType;// 字典类型code
	
	private String dictionaryItem;// 字典项code
	
	private List<String> dictionaryItemCodeList;// 字典项code集合list
	
	private String parentItemId;// 父级字典项id

	private List<OptionItemBean> dictionaryItemLst = new ArrayList<OptionItemBean>();// 字典项 查询结果列表
	
	private Map<String,List<OptionItemBean>> dicItemCodeMap = new HashMap<String,List<OptionItemBean>>();// 级联字典项查询结果
	
	@Resource
	private IDictionaryItemService dictionaryItemService;// 字典项接口
	
	/**
	 * 根据字典类型Code获取字典项
	 * @param dictionaryType 字典类型code
	 * @return "success",成功返回dictionaryItemLst:字典项Bean集合
	 */
	public String findDictionaryItemsByType(){
		List<DictionaryItem> rlt = dictionaryItemService.findDicItemsByTypeCode(dictionaryType, DICT.DICT_ENABLED.getValue());
		for (DictionaryItem tmp : rlt){
			this.dictionaryItemLst.add(dictionaryItemToOptionItemBean(tmp));
		}
		return SUCCESS;
	}
	
	/**
	 * 根据字典类型Code获取字典项（包括子类）
	 *@param dictionaryType 字典类型code
	 * @return "success",成功返回dictionaryItemLst:字典项Bean集合
	 */
	public String findAllSubDictionaryItemsByTypeCode(){
		Map<String, Object> hqlMap = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("SELECT zd FROM  DictionaryItem as zd, DictionaryType as lx WHERE lx.id=zd.dicType AND lx.code= :code "); 
		hqlMap.put("code", dictionaryType);
		List<DictionaryItem> rlt = dictionaryItemService.findAllByParams(hql.toString(), hqlMap);
		for (DictionaryItem tmp : rlt){
			this.dictionaryItemLst.add(dictionaryItemToOptionItemBean(tmp));
		}
		return SUCCESS;
	}
	
	/**
	 * 根据父级字典项Code查询所有下级字典项
	 * @param dictionaryItem 父级字典项Code
	 * @return "success",成功返回dictionaryItemLst:字典项Bean集合
	 */
	public String findAllSubDictionaryItemsByParentItemCode(){
		DictionaryItem di = dictionaryItemService.findDicItemByCode(dictionaryItem);
		if(di == null){
			return SUCCESS;
		}
		List<DictionaryItem> rlt = dictionaryItemService.findDicItemsByParent(di.getId(), DICT.DICT_ENABLED.getValue());
		for (DictionaryItem tmp : rlt){
			this.dictionaryItemLst.add(dictionaryItemToOptionItemBean(tmp));
		}
		return SUCCESS;
	}
	
	
	/**
	 * 根据父级字典项id查询所有下级字典项
	 * @param parentItemId 父级字典项id
	 * @return "success",成功返回dictionaryItemLst:字典项Bean集合
	 */
	public String findAllSubDictionaryItemsByParentItemId(){
		List<DictionaryItem> rlt = dictionaryItemService.findDicItemsByParent(parentItemId, DICT.DICT_ENABLED.getValue());
		for (DictionaryItem tmp : rlt){
			this.dictionaryItemLst.add(dictionaryItemToOptionItemBean(tmp));
		}
		return SUCCESS;
	}
	
	/**
	 * 根据父级字典项code和字典类型code查询所有下级字典项
	 * 
	 * @param dictionaryType 字典类型code, dictionaryItem 父级字典项code
	 * @return "success",成功返回dictionaryItemLst:字典项Bean集合
	 */
	public String findAllSubDictionaryItemsByParentItemCodeAndTypeCode(){
		DictionaryItem dic = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(dictionaryType, dictionaryItem, null);
		if(dic != null){
			parentItemId = dic.getId();
			this.findAllSubDictionaryItemsByParentItemId();
		}
		return SUCCESS;
	}
		
	/**
	 * 根据父级字典项code和字典类型code查询所有下级字典项
	 * 
	 * @param dictionaryType 字典类型code, dictionaryItem 父级字典项code集合List
	 * @return "success",成功返回dictionaryItemLst:字典项Bean集合
	 */
	public String findAllSubDictionaryItemsByParentItemCodeAndTypeCodeList(){
		List<String> list=dictionaryItemCodeList;
		DictionaryItem dic=null;
		for (String dictionaryItem : list) {
			 dic = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(dictionaryType, dictionaryItem, null);
			
			if(dic != null){
				parentItemId = dic.getId();
				List<DictionaryItem> rlt = dictionaryItemService.findDicItemsByParent(parentItemId, DICT.DICT_ENABLED.getValue());
				for (DictionaryItem tmp : rlt){
					this.dictionaryItemLst.add(dictionaryItemToOptionItemBean(tmp));
				}
				//this.findAllSubDictionaryItemsByParentItemId();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 查询多级字典项
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findCascadedDicItem(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		
		for (String k : rqst.keySet()) {
			Map<String, Object> codeObj = JSONObject.fromObject(rqst.get(k));
			String code = JSONNull.getInstance().equals(codeObj.get("selectedCode"))?"":(String)codeObj.get("selectedCode");
			String dicTypeCode = JSONNull.getInstance().equals(codeObj.get("dicTypeCode"))?"":(String)codeObj.get("dicTypeCode");
			List<OptionItemBean> oibList = new ArrayList<OptionItemBean>();
			if(StringUtils.isBlank(code)){
				dicItemCodeMap.put(k, oibList);
				continue;
			}                   
			DictionaryItem di = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(dicTypeCode, code, null);
			List<DictionaryItem> diList = null;
			if(di == null){
				diList = dictionaryItemService.findDicItemsByTypeCode(code, DICT.DICT_ENABLED.getValue());
			}else if(diList == null){
				diList = dictionaryItemService.findDicItemsByParent(di.getId(), DICT.DICT_ENABLED.getValue());
			}
			if(diList == null){
				dicItemCodeMap.put(k, oibList);
				continue;
			}
			for(DictionaryItem dictionaryItem : diList){
				OptionItemBean oib = dictionaryItemToOptionItemBean(dictionaryItem);
				if(oib == null){
					continue;
				}
				oibList.add(oib);
			}
			dicItemCodeMap.put(k, oibList);
		}
		return SUCCESS;
	}
	
	/**
	 * 字典项model转bean
	 * 
	 * @param di 字典项model
	 * @return bean 字典项bean
	 */
	private static OptionItemBean dictionaryItemToOptionItemBean(DictionaryItem di){
		if(di == null){
			return null;
		}
		OptionItemBean bean = new OptionItemBean();
		bean.setId(di.getId());
		bean.setCode(di.getCode());
		bean.setName(di.getName());
		bean.setParentTypeId(di.getDicType() == null?null:di.getDicType().getId());
		bean.setParentItemId(di.getParentItem() == null?null:di.getParentItem().getId());
		return bean;
	}

	public List<OptionItemBean> getDictionaryItemLst() {
		return dictionaryItemLst;
	}

	public void setDictionaryItemLst(List<OptionItemBean> dictionaryItemLst) {
		this.dictionaryItemLst = dictionaryItemLst;
	}

	public List<String> getDictionaryItemCodeList() {
		return dictionaryItemCodeList;
	}

	public void setDictionaryItemCodeList(List<String> dictionaryItemCodeList) {
		this.dictionaryItemCodeList = dictionaryItemCodeList;
	}

	public String getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(String dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	public String getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getDictionaryItem() {
		return dictionaryItem;
	}

	public void setDictionaryItem(String dictionaryItem) {
		this.dictionaryItem = dictionaryItem;
	}

	public Map<String, List<OptionItemBean>> getDicItemCodeMap() {
		return dicItemCodeMap;
	}

	public void setDicItemCodeMap(Map<String, List<OptionItemBean>> dicItemCodeMap) {
		this.dicItemCodeMap = dicItemCodeMap;
	}
	
}
