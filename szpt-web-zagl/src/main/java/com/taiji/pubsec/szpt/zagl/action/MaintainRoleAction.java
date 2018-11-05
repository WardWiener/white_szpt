package com.taiji.pubsec.szpt.zagl.action;

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

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.zagl.action.bean.PersonBean;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleAssignmentService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleService;
import com.taiji.pubsec.szpt.zagl.util.ZAUtil;

/**
 * 专案角色维护Action
 * 
 * @author 
 *
 */
@Controller("maintainRoleAction")
@Scope("prototype")
public class MaintainRoleAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MaintainRoleAction.class);
	
	@Resource(name = "specialCaseRoleService")
	private SpecialCaseRoleService specialCaseRoleService;
	
	@Resource
	private SpecialCaseRoleAssignmentService specialCaseRoleAssignmentService;
	
	private Map<String,Object> resultMap=new HashMap<String,Object>(); 
	
	private String queryStr;
	/**
	 * 页面载入
	 * @return
	 */
	public String findOnloadData(){
		List<SpecialCaseRole> dataList = specialCaseRoleService
				.findOnloadData();
		resultMap.put("dataList", dataList);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护",null);
		return SUCCESS;
		
	}
	
	/**
	 * 添加
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveSpecialCaseRole(){
		Map<String,Object> data = JSONObject.fromObject(read());
		String name="";
		name=String.valueOf(data.get("name"));
		specialCaseRoleService.saveSpecialCaseRole(name);		
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护","name="+name+"");
		return SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateSpecialCaseRole(){
		Map<String,Object> data = JSONObject.fromObject(read());
		String name="";
		String code="";
		code=String.valueOf(data.get("code"));
		name=String.valueOf(data.get("name"));
		specialCaseRoleService.updateSpecialCaseRole(name,code);		
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护","name="+name+",code="+code+"");
		return SUCCESS;
	}
	
	/**
	 * 删除前判断该角色分配人员
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findSpecialCaseRolePerson(){
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		String code="";
		code=String.valueOf(data.get("code"));
		bn=specialCaseRoleService.isRoleUsed(code);		
		resultMap.put("result", bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null,"专案管理模块--专案角色维护",null);
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteSpecialCaseRole(){
		Map<String,Object> data = JSONObject.fromObject(read());
		String code="";
		code=String.valueOf(data.get("code"));
		specialCaseRoleService.deleteSpecialCaseRole(code);		
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护","code="+code+"");
		return SUCCESS;
	}
	
	/**
	 * 启用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateStartSpecialCaseRole(){
		Map<String,List<String>> data = JSONObject.fromObject(read());
		List<String> codeArr=data.get("code");
		String s=ZAUtil.changeListToArray(codeArr);		
		specialCaseRoleService.startSpecialCaseRole(codeArr);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护",s);
		return SUCCESS;
	}
	
	/**
	 * 停用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String updateStopSpecialCaseRole(){
		Map<String,List<String>> data = JSONObject.fromObject(read());
		List<String> codeArr=data.get("code");
		specialCaseRoleService.stopSpecialCaseRole(codeArr);	
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护",ZAUtil.changeListToArray(codeArr));
		return SUCCESS;
	}
	
	
	/**
	 *已分配领导角色人员
	 */
	@SuppressWarnings("unchecked")
	public String findAlreadyAssignableLeaderRole(){		
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		Pager<Person> personPager = specialCaseRoleAssignmentService.findLeaderRolePersons(String.valueOf(data.get("roleId")),String.valueOf(data.get("organization")), Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))), Integer.parseInt(String.valueOf(data.get("length"))));
		List<PersonBean> personList=changPersonBean(personPager.getPageList());
		resultMap.put("result", personList);
		resultMap.put("totalNum", personPager.getTotalNum());
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护","roleId="+String.valueOf(data.get("roleId"))+",unitId="+String.valueOf(data.get("unitId"))+",pageNo="+String.valueOf(Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))))+",pageSize="+String.valueOf(data.get("length"))+"");
		return SUCCESS;
	}
	
	/**
	 * 可分配领导角色人员
	 */
	@SuppressWarnings("unchecked")
	public String findAssignableLeaderRole(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		Pager<Person> personPager = specialCaseRoleAssignmentService.findLeaderPersonsByConditions(data, Integer.parseInt(String.valueOf(data.get("start"))), Integer.parseInt(String.valueOf(data.get("length"))));
		List<PersonBean> personList=changPersonBean(personPager.getPageList());
		resultMap.put("result", personList);
		resultMap.put("totalNum", personPager.getTotalNum());
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护","roleId="+String.valueOf(data.get("roleId"))+",unitId="+String.valueOf(data.get("unitId"))+",sex="+String.valueOf(data.get("sex"))+"ifSub="+String.valueOf(data.get("ifSub"))+",pageNo="+String.valueOf(Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))))+",pageSize="+String.valueOf(data.get("length"))+"");
		return SUCCESS;
	}
	
	/**
	 * 添加领导角色人员
	 */
	@SuppressWarnings("unchecked")
	public  String saveLeaderRole(){
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		List<String> list=(List<String>) data.get("personId");
		String personId="personId in(";
		for(int i=0;i<list.size();i++){
            personId+=list.get(i)+",";		
		    bn= specialCaseRoleAssignmentService.createAssignLeaderRole(String.valueOf(data.get("roleId")), list.get(i));
		}
		personId=")";
		resultMap.put("result", bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护","roleId="+String.valueOf(data.get("roleId"))+","+personId+"");
		return SUCCESS;
	}
	
	/**
	 * 移除已分配领导角色人员
	 */
	@SuppressWarnings("unchecked")
	public  String delectableLeaderRole(){
		
		Map<String,Object> data = JSONObject.fromObject(read());
		List<String> list=(List<String>) data.get("personId");
		String personId="personId in(";
		boolean bn=false;
		for(int i=0;i<list.size();i++){
			 personId+=list.get(i)+",";
		      bn= specialCaseRoleAssignmentService.removeAssignedLeaderRole(String.valueOf(data.get("roleId")),list.get(i));
		}
		personId=")";
		resultMap.put("result", bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案角色维护","roleId="+String.valueOf(data.get("roleId"))+","+personId+"");
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
	

	public Map<String,Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String,Object> resultMap) {
		this.resultMap = resultMap;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}



}
