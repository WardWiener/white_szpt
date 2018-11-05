package com.taiji.pubsec.szpt.zagl.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialTypeService;
import com.taiji.pubsec.szpt.zagl.util.ZAUtil;

@Controller("zaDataMaintenanceAction")
@Scope("prototype")
public class ZaDataMaintenanceAction extends PageCommonAction {
	
	private static final long serialVersionUID = 1L;

	private String queryStr;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Resource
	private SpecialCaseMaterialService specialCaseMaterialService;
	
	@Resource
	private SpecialCaseMaterialTypeService  specialCaseMaterialTypeService;
	/**
	 * 加载所有专案资料
	 * @return
	 */
	public String findAllZaData(){
		
		 List<DictionaryItem> list = specialCaseMaterialTypeService.findAllTypes();
		
		 	int size = list.size();
		 	//排序
		 	DictionaryItem dictionaryItem = null;
		 	for(int i = 0 ;i < size-1 ;i++){
		 		for(int j = size-1;j > i ;j--){
		 			if(Integer.parseInt(list.get(i).getCode()) < Integer.parseInt(list.get(j).getCode())){
		 				dictionaryItem = list.get(i);
		 				list.set(i,list.get(j));
		 				list.set(j,dictionaryItem);
		 			};
		 		}
		 	}
		this.createStandardLog(1, "1", null, "专案管理-专案资料分类维护", null);
		 resultMap.put("result", list);
		 return SUCCESS;
	}
	
	/**
	 * 新增专案资料
	 * @return
	 */
	public String addZaData(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String name  = rqst.get("name").toString();
		boolean result = specialCaseMaterialTypeService.addMaterialType(name);
		if(result){
			this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-专案资料分类维护", "nane="+"'"+name+"'");
		}else{
			this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_FAIL , "1001", "专案管理-专案资料分类维护", "nane="+"'"+name+"'");
		}
		resultMap.put("result", result);
		return SUCCESS;
	}
	
	/**
	 * 删除专案资料
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String removeZaData(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> ids = JSONArray.fromObject(rqst.get("ids"));
		List<String> UsedTypes = specialCaseMaterialTypeService.findUsedTypes();
		String result = "";
		for(String id : ids){
			DictionaryItem item = specialCaseMaterialTypeService.findByItemId(id);
			boolean flag = true;
			if(null != UsedTypes && UsedTypes.size() >0){
				for (String strId : UsedTypes) {
					if (item.getCode().equals(strId)) {
						result += "有" + item.getName() + "的资料,不能删除此类型<br>";
						flag = false;
					}
				}
			}
			if(flag){
				specialCaseMaterialTypeService.deleteMaterialType(id);
			}
		}
		if("".equals(result)){
			this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS ,null, "专案管理-专案资料分类维护", "id in"+"'"+ZAUtil.changeListToArray(ids)+"'");
			resultMap.put("result", "删除成功");
		}else{
			this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_FAIL , "1001", "专案管理-专案资料分类维护", "ids="+"'"+ZAUtil.changeListToArray(ids)+"'");
			resultMap.put("result", result);
		}
		return SUCCESS;
	}
	
	/**
	 * 修改专案资料
	 * @return
	 */
	public String updateZaData(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String name  = rqst.get("name").toString();
		String id  = rqst.get("id").toString();
		specialCaseMaterialTypeService.updateMaterialType(id, name);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS ,null, "专案管理-专案资料分类维护", "id ="+"'"+id+"'nane="+"'"+name+"'");
		return SUCCESS;
	}
	
	/**
	 * 启用专案资料
	 * @return
	 */
	public String startUseZaData(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> ids = JSONArray.fromObject(rqst.get("ids"));
		for(String id : ids){
			specialCaseMaterialTypeService.updateMaterialTypeState(id, Constant.DICT.DICT_YES.getValue());
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS ,null, "专案管理-专案资料分类维护", "ids="+"'"+ZAUtil.changeListToArray(ids)+"'");
		return SUCCESS;
	}
	
	/**
	 * 停用专案资料
	 * @return
	 */
	public String stopUseZaData(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> ids = JSONArray.fromObject(rqst.get("ids"));
		for(String id : ids){
			specialCaseMaterialTypeService.updateMaterialTypeState(id, Constant.DICT.DICT_NO.getValue());
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS ,null, "专案管理-专案资料分类维护", "ids="+"'"+ZAUtil.changeListToArray(ids)+"'");
		return SUCCESS;
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
