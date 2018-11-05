package com.taiji.pubsec.szpt.zagl.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.zagl.action.bean.CaseRelationBean;
import com.taiji.pubsec.szpt.zagl.action.bean.CriminalBasicCaseBean;
import com.taiji.pubsec.szpt.zagl.action.bean.PersonBean;
import com.taiji.pubsec.szpt.zagl.action.bean.ProjectsMaintenanceBean;
import com.taiji.pubsec.szpt.zagl.action.bean.SpecialCaseAndSonBean;
import com.taiji.pubsec.szpt.zagl.action.bean.ZDXBean;
import com.taiji.pubsec.szpt.zagl.model.CaseRelation;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPerson;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRoleAssignment;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleAssignmentService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 专案维护Action
 * 
 * @author 
 *
 */
@Controller("projectsMaintenanceAction")
@Scope("prototype")
public class ProjectsMaintenanceAction extends PageCommonAction  {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectsMaintenanceAction.class);
	                   
	@Resource(name = "specialCaseService")
	private SpecialCaseService specialCaseService;
	
	private Map<String,Object> resultMap=new HashMap<String,Object>();
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	@Resource(name = "specialCaseRoleService")
	private SpecialCaseRoleService specialCaseRoleService;
	
	@Resource
	private SpecialCaseRoleAssignmentService specialCaseRoleAssignmentService;
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	@Resource
	private CaseService caseService;
	
	private String queryStr;
	
	/**
	 * 页面载入表格
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findZAData(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		String start=String.valueOf((Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("length"))));
		data.put("start",start);		
		Pager<SpecialCase> dataList = specialCaseService
				.findSpecialCaseByConditions(data, "",Integer.parseInt(String.valueOf( data.get("start"))),Integer.parseInt(String.valueOf( data.get("length"))));
		List<ProjectsMaintenanceBean> resultListBean=changeBean(dataList.getPageList());
		resultMap.put("resultMap", resultListBean);
		resultMap.put("totalNum",dataList.getTotalNum());
		String s="code="+String.valueOf(data.get("zabh"))+",name="+String.valueOf(data.get("zamc"))+",content="+String.valueOf(data.get("jyaq"))+",nature="+String.valueOf(data.get("ajxz"))+",sonCaseCode="+String.valueOf(data.get("sjzaj"))+",zazcyId="+String.valueOf(data.get("zazcy"))+",pageNo="+String.valueOf(Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))))+",pageSize="+String.valueOf(data.get("length"));
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",s);
		return SUCCESS;
	}
	
	/**
	 * 专案维护页面载入搜索加载项
	 * @return
	 */
	public String findCaseProject(){
		if (specialCaseService == null)
			return ERROR;
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_ZAXZ.getValue());
		List<DictionaryItem> lxList=dictionaryItemService.findDicItemsByType(type.getId(), null);
		resultMap.put("ajxz",lxList);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",null);
		return SUCCESS;
	}
	
	/**
	 * 专案维护页面载入搜索加载项--新增加载项
	 * @return
	 */
	public String findAddProject(){
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_ZAXZ.getValue());
		List<DictionaryItem> lxList=dictionaryItemService.findDicItemsByType(type.getId(), null);
		String code=specialCaseService.acquireNum();
		resultMap.put("ajxz",lxList);
		resultMap.put("code", code);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",null);
		return SUCCESS;
	}
	
	/**
	 * 修改页面 载入时加载的数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findUpdataProject(){
		Map<String,Object> data = JSONObject.fromObject(read());
		queryStr= String.valueOf(data.get("id"));
		SpecialCase  cas=specialCaseService.findSpecialCaseById(queryStr);
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_ZAXZ.getValue());
		List<DictionaryItem> lxList=dictionaryItemService.findDicItemsByType(type.getId(), null);
		SpecialCaseAndSonBean caseBean=changeSpecialCaseAndSonBean(cas);
		resultMap.put("ajxz",lxList);
		resultMap.put("specialCase",caseBean);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护--修改加载项",null);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public  String saveSpecialCase(){
		Map<String,Object> data = JSONObject.fromObject(read());
		SpecialCase  spe= new SpecialCase();
		spe.setCode(String.valueOf(data.get("zabhName")));//案件编号
		spe.setName(String.valueOf(data.get("zamcName"))); //案件名称
		spe.setNature(String.valueOf(data.get("ajxzName")));//案件性质
		spe.setContent(String.valueOf(data.get("jyaqName")));//简要案情
		spe.setProgress(String.valueOf(data.get("gzjzName")));//专案进展
	    Date date=new Date();
	    specialCaseService.createSpecialCase(spe);
	    spe.setCreatedTime(date);//案件新建日期
	    JSONArray kcaJson = JSONArray.fromObject(data.get("sonCodeArr"));
	    CaseRelation[]  arr=(CaseRelation[])JSONArray.toArray(kcaJson, CaseRelation.class);
		for(int i=0;i<arr.length;i++){
			arr[i].setSpecialCase(spe);
			specialCaseService.addSonCase(arr[i]);
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",null);
		return SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String updataSpecialCase(){
		Map<String,Object> data = JSONObject.fromObject(read());
		SpecialCase  spe= new SpecialCase();
		spe.setId(String.valueOf(data.get("zaid")));
		spe.setCode(String.valueOf(data.get("zabhName")));//案件编号
		spe.setName(String.valueOf(data.get("zamcName"))); //案件名称
		spe.setContent(String.valueOf(data.get("jyaqName")));//简要案情
		spe.setNature(String.valueOf(data.get("ajxzName")));//案件性质
		spe.setProgress(String.valueOf(data.get("gzjzName")));//工作进展
	    Date date=new Date();
	    spe.setUpdatedTime(date);//案件新建日期
		specialCaseService.updateSpecialCase(spe);
		//新增子案件
		JSONArray kcaJson = JSONArray.fromObject(data.get("addArr"));
	    CaseRelation[]  arr=(CaseRelation[])JSONArray.toArray(kcaJson, CaseRelation.class);
		for(int i=0;i<arr.length;i++){
			arr[i].setSpecialCase(spe);
			specialCaseService.addSonCase(arr[i]);
		}
		JSONArray list = JSONArray.fromObject(data.get("delArr"));
		for(int i=0;i<list.size();i++){
			specialCaseService.deleteSonCase(String.valueOf(data.get("zaid")), String.valueOf(list.get(i)));
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",null);
		return SUCCESS;
	}
	
	/**
	 * 删除数据
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteCase(){
		Map<String,Object> data = JSONObject.fromObject(read());
		String id="";
		id=String.valueOf(data.get("id"));
		specialCaseService.delete(id);	
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护","specialCaseId="+id+"");
		return SUCCESS;
		
		
	}
	
	
	
	/**
	 * 角色分配页面载入加载数据
	 * @return
	 */
	public  String findRole(){
		List<SpecialCaseRole> specialCaseRole= specialCaseRoleService.findRolesByState(Constant.ROLE_TYPE.ROLE_TYPE_ZDY.getValue());		
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_XB.getValue());
		List<DictionaryItem> sexList= dictionaryItemService.findDicItemsByType(type.getId(), null);
		resultMap.put("sexList", sexList);
		resultMap.put("specialCaseRole", specialCaseRole);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",null);
		return SUCCESS;
		
	}
	
	
	
	/**
	 * 该专案可分配人员
	 */
	@SuppressWarnings("unchecked")
	public String findAssignableRole(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		Pager<Person> pesonPager = specialCaseRoleAssignmentService.findPersonsByConditions(data, Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))), Integer.parseInt(String.valueOf(data.get("length"))));
		List<PersonBean> person=new ArrayList<PersonBean>();
		resultMap.put("totalNum",pesonPager.getTotalNum());
		person=changPersonBean1(pesonPager.getPageList());
		resultMap.put("result", person);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护","roleId="+String.valueOf(data.get("roleId"))+",unitId="+String.valueOf(data.get("unitId"))+",sex="+String.valueOf(data.get("sex"))+"ifSub="+String.valueOf(data.get("ifSub"))+",pageNo="+String.valueOf(Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))))+",pageSize="+String.valueOf(data.get("length"))+"");
		return SUCCESS;
	}
	
	

	/**
	 * 该专案已分配人员
	 */
	@SuppressWarnings("unchecked")
	public String findAlreadyAssignableRole(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		List<PersonBean> person=new ArrayList<PersonBean>();
		Pager<SpecialCaseRoleAssignment> pesonPager = specialCaseRoleAssignmentService.findAssignedPersonsByConditions(data, Integer.parseInt(String.valueOf(data.get("start"))), Integer.parseInt(String.valueOf(data.get("length"))));
		resultMap.put("totalNum",pesonPager.getTotalNum());
		person=changPersonBean(pesonPager.getPageList());
		resultMap.put("result", person);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护","organization="+String.valueOf(data.get("organization"))+",caseId="+String.valueOf(data.get("caseId"))+",roleId="+String.valueOf(data.get("roleId"))+"");
		return SUCCESS;
	}
	
	

	/**
	 * 专案添加角色
	 */
	@SuppressWarnings("unchecked")
	public  String saveRole(){
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		List<String> list=(List<String>) data.get("personId");
		String str="personId in (";
		for(int i=0;i<list.size();i++){
			str+=list.get(i)+",";
			bn=specialCaseRoleAssignmentService.createSpecialCaseRole(String.valueOf(data.get("roleId")),list.get(i), String.valueOf(data.get("caseId")));
		}
		str+=")";
		resultMap.put("result", bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护","personId="+str+",caseId="+String.valueOf(data.get("caseId"))+",roleId="+String.valueOf(data.get("roleId"))+"");
		return SUCCESS;
	}
	
	/**
	 * 移除已分配人员
	 */
	@SuppressWarnings("unchecked")
	public  String removeRole(){
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		List<String> list=(List<String>) data.get("personId");
		List<String> list2=(List<String>) data.get("roleId");
		String str="personId in (";
		for(int i=0;i<list.size();i++){
			str+=list.get(i)+",";
		  bn=specialCaseRoleAssignmentService.removeAssignedSpeccialCaseRole(list2.get(i), list.get(i), String.valueOf(data.get("caseId")));
		}
		str+=")";
		resultMap.put("result", bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护","personId="+str+",caseId="+String.valueOf(data.get("caseId"))+",roleId="+String.valueOf(data.get("roleId"))+"");
		return SUCCESS;
	}
	/**
	 * 加载案件类型
	 * @return
	 */
	public String findAJLX(){
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_V3AJLB.getValue());
		List<DictionaryItem> lxList=dictionaryItemService.findDicItemsByType(type.getId(), null);
		List<ZDXBean> list=changeZDXBean(lxList);
		resultMap.put("result",list);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",null);
		return SUCCESS;
	}
	/**
	 * 查询子案件
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public String findSonCase() throws ParseException{
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(!"".equals(String.valueOf(data.get("discoverTimeStart")))){
			data.put("discoverTimeStart",sdf.parse(String.valueOf(data.get("discoverTimeStart"))));
		}
		if(!"".equals(String.valueOf(data.get("discoverTimeEnd")))){
			data.put("discoverTimeEnd",sdf.parse(String.valueOf(data.get("discoverTimeEnd"))));
		}
		Pager<com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase> sonCase= caseService.findCaseByConditions(data, Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))), Integer.parseInt(String.valueOf(data.get("length"))));
		List<CriminalBasicCaseBean> criBean=changeCriminalBasicCaseBean(sonCase.getPageList());
		resultMap.put("result",criBean);
		resultMap.put("totalNum",sonCase.getTotalNum());
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--专案维护",null);
		return SUCCESS;
		
	}


	/**
	 * 根据专案查询涉案人员
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findPersonConcern(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		String start=String.valueOf((Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("length"))));
		data.put("start",start);
		Pager<SpecialCaseInvolvedPerson> per=specialCaseService.findInvolvedPersonByCase(String.valueOf( data.get("caseId")),Integer.parseInt(String.valueOf( data.get("start"))),Integer.parseInt(String.valueOf( data.get("length"))));
		resultMap.put("result",per.getPageList());
		return SUCCESS;
	}

	/*********************************************************************************************/	
	
	/**
	 * 子案件转换Bean	
	 * @param list2
	 * @return
	 */
	private List<CriminalBasicCaseBean> changeCriminalBasicCaseBean(
			List<com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase> list2) {
		List<CriminalBasicCaseBean>list=new ArrayList<CriminalBasicCaseBean>();
		for(int i=0;i<list2.size();i++){
			CriminalBasicCaseBean  cri=new CriminalBasicCaseBean();
			cri.setCaseCode(list2.get(i).getCaseCode());
			cri.setCaseName(list2.get(i).getCaseName());
			cri.setCaseSort(list2.get(i).getCaseSort());
			cri.setCaseTimeStart(list2.get(i).getCaseTimeStart().getTime());
			cri.setDqbldw(list2.get(i).getHandleUnit());
			cri.setHandlingPeople(list2.get(i).getHandlePolice());
			list.add(cri);
		}
		return list;
	}
	
	/**
	 * 专案维护数据转换Bean
	 * @param list
	 * @return
	 */
	private List<ProjectsMaintenanceBean> changeBean(List<SpecialCase> list) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ProjectsMaintenanceBean> pageList=new ArrayList<ProjectsMaintenanceBean>();
		for(int i=0;i<list.size();i++){
				ProjectsMaintenanceBean  project=new ProjectsMaintenanceBean();
				project.setId(list.get(i).getId());  //案件id  
				project.setCode(list.get(i).getCode()); //案件编码
				project.setNature(list.get(i).getNature()); //案件编码
				project.setContent(list.get(i).getContent()); //案情简介
				project.setCreatedTime(format.format(list.get(i).getCreatedTime()));//创建时间
				project.setName(list.get(i).getName()); //专案名称
				project.setUpdatedTime(format.format(list.get(i).getUpdatedTime()));//最后更新时间
				//project.setSjzajList(list.get(i).getCaseRelation());  //涉及子案件
				List<String> sonIDList=new ArrayList<String>();
				List<String> sonCaseCodeList=new ArrayList<String>();
				for (CaseRelation str : list.get(i).getCaseRelation()) {  
				     sonIDList.add(str.getId());
				     sonCaseCodeList.add(str.getCaseCode());
				} 
				project.setSonIDList(sonIDList);
				project.setSonCaseCodeList(sonCaseCodeList);
			//	project.setQbSize(list.get(i).getSpecialCaseMaterial().size());//情报条数
				Set<SpecialCaseRoleAssignment> jsset= list.get(i).getSpecialCaseRoleAssignment();  //办案人员set集合
				Set<String> setr=new HashSet<String>(); 
				List<String> codeList=new ArrayList<String>();
				for(SpecialCaseRoleAssignment person : jsset){					
					if(setr.add(person.getRole().getCode())){  //获取共有多少个code
						String code=person.getRole().getCode(); //角色的编码
						codeList.add(code);
					}
				}
				List<String> ryList= new ArrayList<String>();  //人员list
				List<String> roleList= new ArrayList<String>();  //角色list
				//排序
				Collections.sort(codeList);

				
				Set<String> set1=new HashSet<String>(); 
				for(int j=0;j<codeList.size();j++){
					 String  str="";
					 String role="";
					for(SpecialCaseRoleAssignment person : jsset){	
						//等于循环的code
						if(codeList.get(j).equals(person.getRole().getCode())){
							//为第一次出现
							if(set1.add(person.getRole().getCode())){  
							   	//str=person.getRole().getName()+":"+person.getPerson().getName();
								str=person.getPerson().getName()+"("+person.getPerson().getOrganization().getShortName()+")";  //人员名单
								role=person.getRole().getName();  //角色名称
							}else{
								str+=","+person.getPerson().getName()+"("+person.getPerson().getOrganization().getShortName()+")";
							}							
						}
					}
					ryList.add(str);
					roleList.add(role);
				}
				project.setZazcyList(ryList);
				project.setRoleList(roleList);
				pageList.add(project);
			  } 
	    	return pageList;
		}
	
	/**
	 * 人员可分配的bean
	 * @param pageList
	 * @return
	 */
	private List<PersonBean> changPersonBean1(List<Person> list) {
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
	 * 人员Bean 
	 * @param pageList
	 * @return
	 */
	private List<PersonBean> changPersonBean(
			List<SpecialCaseRoleAssignment> list) {
		List<PersonBean> person=new ArrayList<PersonBean>();
		for(int i=0;i<list.size();i++){
			PersonBean per=new PersonBean();
			per.setId(list.get(i).getPerson().getId());
			per.setName(list.get(i).getPerson().getName());
			String sex=this.findDictNameByDictTypeCodeAndDictItemCode(Constant.DICT.DICT_TYPE_XB.getValue(), list.get(i).getPerson().getSex());
			per.setSex(sex);
			per.setState(list.get(i).getPerson().getStatus());
			per.setUnit(list.get(i).getPerson().getOrganization().getShortName());
			per.setRoleId(list.get(i).getRole().getId());
			per.setRoleName(list.get(i).getRole().getName());
			person.add(per);
		}
		return person;
	}
	/**
	 * 维护案件基本信息转换Bean
	 * @param cas
	 * @return
	 */
	private SpecialCaseAndSonBean changeSpecialCaseAndSonBean(SpecialCase cas) {
		SpecialCaseAndSonBean  caseBean=new SpecialCaseAndSonBean();
		caseBean.setCode(cas.getCode());
		caseBean.setContent(cas.getContent());
		caseBean.setId(cas.getId());
		caseBean.setName(cas.getName());
		caseBean.setNature(cas.getNature());
		caseBean.setPlan(cas.getPlan());
		caseBean.setProblem(cas.getProblem());
		caseBean.setProgress(cas.getProgress());
		List<CaseRelationBean> list=new ArrayList<CaseRelationBean>();
		for (CaseRelation str : cas.getCaseRelation()) {  
			CaseRelationBean ca=new CaseRelationBean();
			ca.setCaseCode(str.getCaseCode());
			ca.setCaseName(str.getCaseName());
			ca.setId(str.getId());
			ca.setWorkers(str.getWorkers());
			list.add(ca);
		} 
		caseBean.setSonList(list);
		return caseBean;
	}
	/**
	 * 字典项转换Bean	
	 * @param lxList
	 * @return
	 */
	private List<ZDXBean> changeZDXBean(List<DictionaryItem> lxList) {
		List<ZDXBean>  listBean=new ArrayList<ZDXBean>();
			for(int i=0;i<lxList.size();i++){
				ZDXBean zdx=new ZDXBean();
				zdx.setId(lxList.get(i).getId());
				zdx.setCode(lxList.get(i).getCode());
				zdx.setName(lxList.get(i).getName());
				listBean.add(zdx);
			}
			return listBean;
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
