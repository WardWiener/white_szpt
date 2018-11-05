package com.taiji.pubsec.szpt.zagl.action;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Organization;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.complex.tools.web.action.ExportInfoReq;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.ExcelUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;
import com.taiji.pubsec.szpt.zagl.action.bean.BackStorageFormExcelBean;
import com.taiji.pubsec.szpt.zagl.action.bean.DepartmentBean;
import com.taiji.pubsec.szpt.zagl.action.bean.LiuYanFanKuiBean;
import com.taiji.pubsec.szpt.zagl.action.bean.RoleAndPersonLstBean;
import com.taiji.pubsec.szpt.zagl.action.bean.SpecialCaseMessageBean;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessage;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMessageStickRecord;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRole;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMessageService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleAssignmentService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseService;
import com.taiji.pubsec.szpt.zagl.util.ZAUtil;

@Controller("zaFeedbackUserLayerAction")
@Scope("prototype")
public class ZaFeedbackUserLayerAction extends PageCommonAction {
	
	private static final long serialVersionUID = 1L;

	private String queryStr;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	//到出excel表格
	private ExportInfoReq exportInfoReq = new ExportInfoReq();
	
	@Resource
	private SpecialCaseMessageService specialCaseMessageService;
	
	@Resource
	private SpecialCaseService specialCaseService;
	
	@Resource
	private SpecialCaseRoleAssignmentService specialCaseRoleAssignmentService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService ;
	
	@Resource
	private IPersonService personService;
	
	@Resource
	private SpecialCaseRoleService  specialCaseRoleService;
	
	@Resource
	private IUnitService unitService;
	
	
	private List<BackStorageFormExcelBean> backStorageFormExcelBeanList = new ArrayList<BackStorageFormExcelBean>();// 导出ExcelBean集合
	
	/**
	 * 返还单列表导出EXCEL
	 * 
	 * @param
	 * @return
	 */
	public String exportExcel() throws Exception {

		Map<String, Object> data = JSONObject.fromObject(queryStr);
		Map<String, List<SpecialCaseMessage>> messagesMap = specialCaseMessageService.findMessageByCondition(data);

		if (null != messagesMap && messagesMap.size() > 0) {
			int sum = 0;
			for (String organizationNmae : messagesMap.keySet()) {
				List<SpecialCaseMessageBean> messageBs = new ArrayList<SpecialCaseMessageBean>();
				if (messagesMap.get(organizationNmae).size() > 0) {
					Integer serialNum = 1;
					for (SpecialCaseMessage scs : messagesMap.get(organizationNmae)) {
						backStorageFormExcelBeanList.add(messageChangeToExcelBean(scs,serialNum.toString()));
					}
					if(messagesMap.size() > 1){
						BackStorageFormExcelBean subtotal = new BackStorageFormExcelBean();
						subtotal.setSerialNum("小计");
						subtotal.setMessageContent(messagesMap.get(organizationNmae).size() + "");
						backStorageFormExcelBeanList.add(subtotal);
					}
					sum += messagesMap.get(organizationNmae).size();
				}
			}
			BackStorageFormExcelBean total = new BackStorageFormExcelBean();
			total.setSerialNum("总计");
			total.setMessageContent(sum + "");
			backStorageFormExcelBeanList.add(total);
		}
		
		ExcelUtil<BackStorageFormExcelBean> ex = new ExcelUtil<BackStorageFormExcelBean>();
		String[] headers = { "序号", "留言内容", "留言时间", "留言人", "所在部门" };
		SimpleDateFormat sdfExcel = new SimpleDateFormat("yyyyMMddHHmmss");
		String name = sdfExcel.format(new Date());
		name = name + ".xls";
		
		// 创建ByteArrayOutputStream字节流
		try {
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			ex.exportExcel(headers, backStorageFormExcelBeanList, bos);
			byte[] bytes = bos.toByteArray();
			bos.close();
			ByteArrayInputStream ins=new ByteArrayInputStream(bytes);
			exportInfoReq.setIn(ins);
			exportInfoReq.setName(name);
			exportInfoReq.setLength(Long.valueOf(bytes.length));
			ins.close();
		} catch (Exception e) {
			List<String> departments = JSONArray.fromObject(data.get("departments"));
			this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_FAIL , "2000", "专案管理-我的专案", 
					"caseId='"+data.get("caseId")+"',flag='"+data.get("falg")+"',"+String.valueOf(data.get("createTimeStart"))+"'<=createTimeEnd<='"+String.valueOf(data.get("createTimeEnd"))+"'");
			e.printStackTrace();
			return "done";
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案", 
				"caseId='"+data.get("caseId")+"',flag='"+data.get("falg")+"'"+String.valueOf(data.get("createTimeStart"))+"'<=createTimeEnd<='"+String.valueOf(data.get("createTimeEnd"))+"'");
		
		return "done";
	}
	
	/**
	 * 查找所有的留言部门
	 * @return
	 */
	public String findDepartmentLst(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String caseId = null;
		List<Organization> caseUnits = new ArrayList<Organization>();
		if(ParamMapUtil.isNotBlank(rqst.get("caseId"))){
			caseId = rqst.get("caseId").toString();
			caseUnits = specialCaseRoleAssignmentService.findCaseUnitByCaseId(caseId);
			if(caseUnits.size() > 0){
				List<DepartmentBean> departmentLst = new ArrayList<DepartmentBean>();
				for (Organization organization : caseUnits) {
					DepartmentBean db = new DepartmentBean();
					db.setId(organization.getId());
					db.setName(organization.getShortName());
					db.setCode(organization.getCode());
					departmentLst.add(db);
				}
				resultMap.put("result", departmentLst);
			}
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案", 
				"caseId='"+caseId+"'");
		return SUCCESS;
	}
	
	/**
	 * 查找所有的留言部门和相应职位的留言人员
	 * @return
	 */
	public String findDepartmentLstAndLiuYanPerson(){
		
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String caseId = null;
		List<Organization> caseUnits = new ArrayList<Organization>();
		SpecialCase specialCase  = null;
		LiuYanFanKuiBean lyfk = new LiuYanFanKuiBean();
		try {
			if(ParamMapUtil.isNotBlank(rqst.get("caseId"))){
				caseId = rqst.get("caseId").toString();
				specialCase = specialCaseService.findSpecialCaseById(caseId);
				
				caseUnits = specialCaseRoleAssignmentService.findCaseUnitByCaseId(caseId);
				Map<SpecialCaseRole, List<Person>> postAndPerson = specialCaseRoleAssignmentService.findRolesAndPersonsByCaseId(caseId);
				List<RoleAndPersonLstBean> roleAndPersonLstBeanLst = creatRoleAndPersonLstBeanLst(postAndPerson);
				lyfk.setRoleAndPersonsLst(roleAndPersonLstBeanLst);
			}
			if(caseUnits.size() > 0){
				for (Organization organization : caseUnits) {
					DepartmentBean db = new DepartmentBean();
					db.setId(organization.getId());
					db.setName(organization.getShortName());
					db.setCode(organization.getCode());
					lyfk.getDepartmentLst().add(db);
				}
			}
		} catch (Exception e) {
			this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_FAIL , "2000", "专案管理-我的专案", 
					"caseId='"+caseId+"'");
			e.printStackTrace();
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案", 
				"caseId='"+caseId+"'");
		resultMap.put("result", lyfk);
		resultMap.put("za", specialCase);
		return SUCCESS;
	}
	
	/**
	 * 查找符合条件的留言信息
	 * @return
	 */
	public String queryLiuYanByCondition(){
		
		Map<String, Object> data = JSONObject.fromObject(read());
		List<List<SpecialCaseMessageBean>> specialCaseLst = new ArrayList<List<SpecialCaseMessageBean>>();
		Map<String, List<SpecialCaseMessage>> messagesMap = null;
		try {
			messagesMap = specialCaseMessageService.findMessageByCondition(data);
			if(null != messagesMap && messagesMap.size() > 0){
				for (String organizationNmae : messagesMap.keySet()) {
					List<SpecialCaseMessageBean> messageBs = new ArrayList<SpecialCaseMessageBean>();
					if(messagesMap.get(organizationNmae).size() > 0){
						for (SpecialCaseMessage scs : messagesMap.get(organizationNmae)) {
							messageBs.add(liuYanChangeToLiuYanBean(scs,""));
						}
						specialCaseLst.add(messageBs);
					}
				}
			}
		} catch (Exception e) {
			this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_FAIL , "2000", "专案管理-我的专案", 
					"caseId='"+data.get("caseId")+"',flag='"+data.get("falg")+"'"+String.valueOf(data.get("createTimeStart"))+"'<=createTimeEnd<='"+String.valueOf(data.get("createTimeEnd"))+"'");
			e.printStackTrace();
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案", 
				"caseId='"+data.get("caseId")+"',flag='"+data.get("falg")+"'"+String.valueOf(data.get("createTimeStart"))+"'<=createTimeEnd<='"+String.valueOf(data.get("createTimeEnd"))+"'");
		resultMap.put("result", specialCaseLst);
		return SUCCESS;
	}
	/**
	 * 增加留言
	 * @return
	 */
	public String addLiuYan(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseId = null;
		SpecialCaseMessage liuYan = new SpecialCaseMessage();
		if(ParamMapUtil.isNotBlank(data.get("caseId"))){
			caseId = data.get("caseId").toString();
			liuYan.setSpecialCase(specialCaseService.findSpecialCaseById(caseId));
		}
		if(ParamMapUtil.isNotBlank(data.get("content"))){
			liuYan.setContent(data.get("content").toString());
		}
		liuYan.setCreatePerson(personService.findById(this.findCurrentPersonId()));
		liuYan.setCreatedTime(new Date());
		boolean result = specialCaseMessageService.addMessage(liuYan);
		if(result){
			this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_FAIL , "", "专案管理-我的专案",null);
		}
			this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",null);
		resultMap.put("result", result);
		return SUCCESS;
	}
	
	/**
	 * 根据条件查找相应的留言信息(不置顶)
	 * @return
	 */
	public String findLiuYanByCondition(){
		Map<String, Object> data = JSONObject.fromObject(queryStr);
		Integer pageNo=(Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("pageSize")));
		data.put("pageNo",pageNo);
		List<SpecialCaseMessageBean> list = new ArrayList<SpecialCaseMessageBean>();
		Pager<SpecialCaseMessage> messageByConditions = specialCaseMessageService.findMessageByConditions(data,pageNo, Integer.valueOf(data.get("pageSize").toString()));
		List<SpecialCaseMessage> pageList = messageByConditions.getPageList();
		if(null != pageList && pageList.size() > 0){
			for (SpecialCaseMessage scs : pageList) {
				list.add(liuYanChangeToLiuYanBean(scs,""));
			}
		}
		Pager<SpecialCaseMessageBean> pager =  new Pager<SpecialCaseMessageBean>();
		pager.setTotalNum(messageByConditions.getTotalNum());
		pager.setPageList(list);
		if(ParamMapUtil.isNotBlank(data.get("department"))){
			List<String> departments = JSONArray.fromObject(data.get("departments"));
			this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案", 
					"caseId='"+data.get("caseId")+"','"+data.get("createTimeStart")+"'<=createTimeEnd<='"+data.get("createTimeEnd")
					+"',createPerson.organization.id("+ZAUtil.changeListToArray(departments)+")");
		}else{
			this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案", 
					"caseId='"+data.get("caseId")+"','"+String.valueOf(data.get("createTimeStart"))+"'<=createTimeEnd<='"+String.valueOf(data.get("createTimeEnd"))+"'");
		}
		resultMap.put("result", pager);
		return SUCCESS;
	}
	
	
	/**
	 * 置顶或取消置顶
	 * @return
	 */
	public String topOrUntopBtn(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<String> liuYanIds = JSONArray.fromObject(rqst.get("liuYanIds"));
		boolean result = false;
		if(null != liuYanIds && liuYanIds.size() > 0){
			result = true;
			for (String id : liuYanIds) {
				boolean flag =  specialCaseMessageService.findStickRecordByPersonIdAndMessageId(this.findCurrentPersonId(),id);
				if(flag){
					result = specialCaseMessageService.unstickMessage(id,this.findCurrentPersonId());
					if(!result){
						this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_FAIL , "3001", "专案管理-我的专案",null);
						resultMap.put("result", "置顶或取消失败");
						return SUCCESS;
					}
				}else{
					Date date = new Date();
					result = specialCaseMessageService.stickMessage(id, this.findCurrentPersonId(), date);
					if(!result){
						this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_FAIL , "3001", "专案管理-我的专案",null);
						resultMap.put("result", "置顶或取消失败");
						return SUCCESS;
					}
				}
			}
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",null);
		resultMap.put("result", "置顶或取消成功");
		return SUCCESS;
	}
	
	/**
	 * 删除留言
	 * @return
	 */
	public String deleteLiuYan(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<Map<String,String>> liuYanIdAndPersonIdLst = JSONArray.fromObject(rqst.get("liuYanIdAndPersonIdLst"));
		String result = "删除成功!";
		if(null != liuYanIdAndPersonIdLst && liuYanIdAndPersonIdLst.size() > 0){	
			for (Map<String,String> liuYanIdAndPersonId : liuYanIdAndPersonIdLst) {
				String personId = liuYanIdAndPersonId.get("personId");
				if(findCurrentPersonId().equals(personId)){
					boolean flag =  specialCaseMessageService.findStickRecordByPersonIdAndMessageId(personId,liuYanIdAndPersonId.get("liuYanId"));
					if(flag){
						specialCaseMessageService.unstickMessage(liuYanIdAndPersonId.get("liuYanId"),personId);
					}
					specialCaseMessageService.deleteMessage(liuYanIdAndPersonId.get("liuYanId"));
				}else{
					this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_FAIL , "1001", "专案管理-我的专案",null);
					result = "您没有权限,只能删除自己的留言!";
				}
			}
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",null);
		resultMap.put("result", result);
		return SUCCESS;
	}
	
	/**
	 * 查看某角色的所有留言
	 * @return
	 */
	public String findLiuYanByRoleIdAndCaseId(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseId = null;
		String roleId = null;
		List<SpecialCaseMessageBean> list = new ArrayList<SpecialCaseMessageBean>();
		try {
			if(ParamMapUtil.isNotBlank(data.get("caseId")) && ParamMapUtil.isNotBlank(data.get("roleId"))){
				caseId = data.get("caseId").toString();
				roleId = data.get("roleId").toString();
				List<SpecialCaseMessage> messages = specialCaseMessageService.findMessagesByRoleIdAndCaseId(roleId, caseId);
				for (SpecialCaseMessage scs : messages) {
					//SpecialCaseRole role = specialCaseRoleService.findById(roleId);
					list.add(liuYanChangeToLiuYanBean(scs,""));
				}
			}
		} catch (Exception e) {
			this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_FAIL , "2000", "专案管理-我的专案",
					"caseId='"+data.get("caseId")+"',roleId='"+data.get("roleId")+"'");
			e.printStackTrace();
			return SUCCESS;
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"caseId='"+data.get("caseId")+"',roleId='"+data.get("roleId")+"'");
		resultMap.put("result", list);
		return SUCCESS;
	}
	/**
	 * 查看某人的所有留言
	 * @return
	 */
	public String findLiuYanByPersonIdAndCaseId(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseId = null;
		List<SpecialCaseMessageBean> list = new ArrayList<SpecialCaseMessageBean>();
		try {
			if(ParamMapUtil.isNotBlank(data.get("caseId"))){
				caseId = data.get("caseId").toString();
				String personId = this.findCurrentPersonId();
				if(ParamMapUtil.isNotBlank(data.get("personId"))){
					personId = data.get("personId").toString();
				}
				Pager<SpecialCaseMessage> pager = specialCaseMessageService.findMessgeByPerson(personId,caseId, 0, Integer.MAX_VALUE);
				for (SpecialCaseMessage scs : pager.getPageList()) {
					list.add(liuYanChangeToLiuYanBean(scs,""));
				}
			}
		} catch (Exception e) {
			this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_FAIL , "2000", "专案管理-我的专案",
					"caseId='"+data.get("caseId")+"',personId='"+data.get("personId")+"'");
			e.printStackTrace();
			return SUCCESS;
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"caseId='"+data.get("caseId")+"',personId='"+data.get("personId")+"'");
		resultMap.put("result", list);
		return SUCCESS;
	}
	
	/**
	 * 查看所有置顶(包括局领导.组长.副组长)的留言
	 * @return
	 */
	
	public String findAllTopMessages(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String caseId = null; 
		List<SpecialCaseMessageBean> list = new ArrayList<SpecialCaseMessageBean>();
		try {
			if(ParamMapUtil.isNotBlank(data.get("caseId"))){
				caseId = data.get("caseId").toString();
				List<SpecialCaseMessageStickRecord> messageRecords = specialCaseMessageService.findStickyMessage(this.findCurrentPersonId(), caseId);
				for (SpecialCaseMessageStickRecord scs : messageRecords) {
					if(null != scs.getId()){
						list.add(liuYanChangeToLiuYanBean(scs.getMessage(),"置顶"));
					}else{
						SpecialCaseRole role = specialCaseRoleService.findRoleByPersonIdAndCaseId(scs.getMessage().getCreatePerson().getId(), caseId);
						list.add(liuYanChangeToLiuYanBean(scs.getMessage(),role.getName()));
					}
				}
			}
		} catch (Exception e) {
			this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_FAIL , "2000", "专案管理-我的专案",
					"caseId='"+data.get("caseId")+"',personId='"+findCurrentPersonId()+"',role.id in ('1','2','3')");
			e.printStackTrace();
			return SUCCESS;
		}
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS , null, "专案管理-我的专案",
				"caseId='"+data.get("caseId")+"',personId='"+findCurrentPersonId()+"',role.id in ('1','2','3')");
		resultMap.put("result", list);
		return SUCCESS;
	}
	
	/**
	 * 获取当前登录人的id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String findCurrentPersonId(){
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser() ;
		Map<String, Object> userMap = user.getUserMap() ;
		Map<String, String> mPerson = new HashMap<String, String>(0) ;
		if(userMap.get("person")!=null){
			mPerson = (Map<String, String>)userMap.get("person");
		}
		return mPerson.get("id");
	}
	
	/**
	 * 由Map<SpecialCaseRole, List<Person>转换成排序的roleAndPersonLstBeanLst
	 * @return roleAndPersonLstBeanLst
	 */
	private List<RoleAndPersonLstBean> creatRoleAndPersonLstBeanLst(Map<SpecialCaseRole, List<Person>> roleAndPersonLst){
		List<RoleAndPersonLstBean> roleAndPersonLstBeanLst = new ArrayList<RoleAndPersonLstBean>();
		for ( SpecialCaseRole scr : roleAndPersonLst.keySet()) {
			RoleAndPersonLstBean roleAndPerson = new RoleAndPersonLstBean();
			roleAndPerson.setRole(scr);
			roleAndPerson.setPersonLst(roleAndPersonLst.get(scr));
			roleAndPersonLstBeanLst.add(roleAndPerson);
		}
		RoleAndPersonLstBean roleAndPerson = null;
		int size = roleAndPersonLstBeanLst.size();
		for(int i = 0 ;i < size-1 ;i++){
	 		for(int j = size-1;j > i ;j--){
	 			if(Integer.parseInt(roleAndPersonLstBeanLst.get(i).getRole().getCode()) > Integer.parseInt(roleAndPersonLstBeanLst.get(j).getRole().getCode())){
	 				roleAndPerson = roleAndPersonLstBeanLst.get(i);
	 				roleAndPersonLstBeanLst.set(i,roleAndPersonLstBeanLst.get(j));
	 				roleAndPersonLstBeanLst.set(j,roleAndPerson);
	 			};
	 		}
	 	}
		return roleAndPersonLstBeanLst;
	}
	
	/**
	 * 留言model转留言bean
	 * @return
	 */
	private SpecialCaseMessageBean liuYanChangeToLiuYanBean(SpecialCaseMessage scs,String top){
		SpecialCaseMessageBean liuYanBean = new SpecialCaseMessageBean();
		liuYanBean.setContext(scs.getContent());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
		liuYanBean.setCreatTime(df.format(scs.getCreatedTime()));
		DictionaryItem item = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(Constant.DICT.DICT_TYPE_ZW.getValue(), scs.getCreatePerson().getPosition(),null);
		if(null != item){
			liuYanBean.setDepartment(item.getName());
		}
		liuYanBean.setId(scs.getId());
		liuYanBean.setUnitName(unitService.findById(scs.getCreatePerson().getOrganization().getId()).getShortName());
		liuYanBean.setPersonId(scs.getCreatePerson().getId());
		liuYanBean.setPersonName(scs.getCreatePerson().getName());
		//SpecialCaseMessageStickRecord liuyanTop = specialCaseMessageService.findStickRecordByPersonIdAndMessageId(scs.getId(), findCurrentPersonId());
		liuYanBean.setTop(top);
		return liuYanBean;
	}
	/**
	 * 留言model转留言excelBean
	 * @return
	 */
	private BackStorageFormExcelBean messageChangeToExcelBean(SpecialCaseMessage scs , String serialNum){
		BackStorageFormExcelBean excelBean = new BackStorageFormExcelBean();
		excelBean.setMessageContent(scs.getContent());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
		excelBean.setCreatTime(df.format(scs.getCreatedTime()));
		excelBean.setMessageUnit(unitService.findById(scs.getCreatePerson().getOrganization().getId()).getShortName());
		excelBean.setMessagePerson(scs.getCreatePerson().getName());
		excelBean.setSerialNum(serialNum);
		return excelBean;
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
		if (null != formatInParmArr && formatInParmArr.length > 0) {
			for (int i = 0; i < formatInParmArr.length; i++) {
				if (null != formatInParmArr[i]
						&& !"".equals(formatInParmArr[i].toString().trim())) {
					if (i == 0) {
						formatInParm = "";
						formatInParm += "'" + formatInParmArr[i].toString()
								+ "'";
					} else {
						formatInParm += ",'" + formatInParmArr[i].toString()
								+ "'";
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
	
	public ExportInfoReq getExportInfoReq() {
		return exportInfoReq;
	}

	public void setExportInfoReq(ExportInfoReq exportInfoReq) {
		this.exportInfoReq = exportInfoReq;
	}

}
