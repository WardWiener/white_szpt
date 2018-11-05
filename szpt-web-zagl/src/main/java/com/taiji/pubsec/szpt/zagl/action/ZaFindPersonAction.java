package com.taiji.pubsec.szpt.zagl.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IDepartmentService;
import com.taiji.pubsec.businesscomponents.organization.service.IOrganizationService;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zagl.action.bean.ZaDwBean;
import com.taiji.pubsec.szpt.zagl.action.bean.PersonBean;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialService;

@Controller("zaFindPersonAction")
@Scope("prototype")
public class ZaFindPersonAction extends PageCommonAction {
	
	private static final long serialVersionUID = 1L;

	private String queryStr;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	@Resource
	private IOrganizationService organizationService;
	
	@Resource
	private SpecialCaseMaterialService specialCaseMaterialService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;
	
	@Resource
	private IDepartmentService departmentService;
	@Resource
	 private IPersonService personService;
	
	@Resource
	private IUnitService unitService;
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	
	/**
	 * 查询所有的单位及部门
	 * @return
	 */
	public String findAllUnitAndDepartment(){
		List<Unit> allUnit = unitService.findAll();
		ZaDwBean  allDw = assembleDwBean(allUnit,null,null);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-专案维护",null);
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_XB.getValue());
		List<DictionaryItem> sexList= dictionaryItemService.findDicItemsByType(type.getId(), null);
		resultMap.put("sexList", sexList);
		resultMap.put("result", allDw);
		return SUCCESS;
	}
	
	private ZaDwBean assembleDwBean(List<Unit> allUnit,ZaDwBean dw, String code){
		
		if(null  == dw){
			dw = new ZaDwBean();
		}
		if(null != code){
			for (Unit unit : allUnit) {
				if(null != unit.getSuperOrg() && unit.getSuperOrg().getId().equals(code)){
					ZaDwBean sunDw  = new ZaDwBean();
					sunDw.setId(unit.getId());
					sunDw.setCode(unit.getCode());
					sunDw.setName(unit.getShortName());
					sunDw.setCode(unit.getCode());
					dw.getDwLst().add(sunDw);
					assembleDwBean(allUnit,sunDw,unit.getId());
				}
			}
		}else{
			for (Unit unit : allUnit) {
				if (null == unit.getSuperOrg()) {
					ZaDwBean sunDw = new ZaDwBean();
					sunDw.setId(unit.getId());
					sunDw.setCode(unit.getCode());
					sunDw.setName(unit.getShortName());
					dw.getDwLst().add(sunDw);
					assembleDwBean(allUnit,sunDw,unit.getId());
				}
			}
		}
		return dw;
	}

	
	/**
	 * 查询所有的符合要求的人员
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findPersonLstByCondition(){
		Map<String, Object> data = JSONObject.fromObject(queryStr);
		Integer start=(Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("length")));
		data.put("start",start);
		
		StringBuilder hql = new StringBuilder("select ps from "
			+ Person.class.getName() +" as ps where 1 = 1 ");
		Map<String,Object> hqlMap = new HashMap<String, Object>();
		
		if(ParamMapUtil.isNotBlank(data.get("name"))){
			hql.append(" and ps.name like :name ");
			SQLTool.SQLAddEscape(hql);
			hqlMap.put("name",  "%" + SQLTool.SQLSpecialChTranfer((String)data.get("name")) + "%"  );  
		};
		if(ParamMapUtil.isNotBlank(data.get("sex"))){
			hql.append(" and ps.sex = :sex ");
			hqlMap.put("sex", (String)data.get("sex"));  
		}
		if(ParamMapUtil.isNotBlank(data.get("unitId"))){
			if("0".equals(data.get("state").toString())){
				hql.append(" and ps.organization.id = :unitId ");
				hqlMap.put("unitId", (String)data.get("unitId"));  
			}else{
				 List<String> dwLst = new ArrayList<String>();
				 dwLst.add((String)data.get("unitId"));
				 
				 List<Unit> subUnit = unitService.findSubUnitsByUnitId(data.get("unitId").toString());
				int size = 0;
				if (null != subUnit && subUnit.size() > 0) {
					size = subUnit.size();
				}
				String[] units = new String[size + 1];
				for (int i = 0; i < size; i++) {
					units[i] = subUnit.get(i).getId();
				}
				units[size] = (String) data.get("unitId");
				if (null != dwLst && dwLst.size() > 0) {
					hql.append(" and ps.organization.id in ("
							+ inParamFormatForQuery(units) + ") ");
				}
			}
		}
		Pager<Person> page = personService.findByPage(hql.toString(), hqlMap, start, (Integer)data.get("length"));
		Pager<PersonBean> personPage = new Pager<PersonBean>();
		personPage.setPageList(changPersonBean(page.getPageList()));
		personPage.setTotalNum(page.getTotalNum());
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-专案维护",
				"caseId='"+data.get("caseId")+"',name like '"+String.valueOf(data.get("name"))+"',sex='"+String.valueOf(data.get("sex"))+"'");
		resultMap.put("result", personPage);
		return SUCCESS;
	}
	
	/**
	 * 人员可分配的bean
	 * @param pageList
	 * @return
	 */
	private List<PersonBean> changPersonBean(List<Person> list) {
		List<PersonBean> person=new ArrayList<PersonBean>();
		for(int i=0;i<list.size();i++){
			PersonBean per=new PersonBean();
			per.setId(list.get(i).getId());
			per.setName(list.get(i).getName());
			String sex=this.findDictNameByDictTypeCodeAndDictItemCode(Constant.DICT.DICT_TYPE_XB.getValue(), list.get(i).getSex());
			per.setSex(sex);
			per.setState(list.get(i).getStatus());
			per.setUnit(list.get(i).getOrganization().getShortName());
			person.add(per);
		}
		return person;
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
