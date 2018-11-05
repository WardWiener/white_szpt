package com.taiji.pubsec.szpt.zagl.action;

import java.io.InputStream;
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
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.util.Constant;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.zagl.action.bean.CaseRelationBean;
import com.taiji.pubsec.szpt.zagl.action.bean.FileBean;
import com.taiji.pubsec.szpt.zagl.action.bean.InvolvedPersonBean;
import com.taiji.pubsec.szpt.zagl.action.bean.SpecialCaseAndSonBean;
import com.taiji.pubsec.szpt.zagl.action.bean.SpecialCaseInvolvedPersonBean;
import com.taiji.pubsec.szpt.zagl.action.bean.SpecialCaseReportBean;
import com.taiji.pubsec.szpt.zagl.action.bean.ZDXBean;
import com.taiji.pubsec.szpt.zagl.action.bean.PersonBean;
import com.taiji.pubsec.szpt.zagl.action.bean.ProjectsMaintenanceBean;
import com.taiji.pubsec.szpt.zagl.model.CaseRelation;
import com.taiji.pubsec.szpt.zagl.model.SpecialCase;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseInvolvedPerson;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseMaterial;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseReport;
import com.taiji.pubsec.szpt.zagl.model.SpecialCaseRoleAssignment;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseMaterialService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseReportService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleAssignmentService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseRoleService;
import com.taiji.pubsec.szpt.zagl.service.SpecialCaseService;
import com.taiji.pubsec.weboffice.iweboffice2000.service.IWebOffice2000Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 我的专案Action
 * 
 * @author 
 *
 */
@Controller("myProjectsAction")
@Scope("prototype")
public class MyProjectsAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyProjectsAction.class);
	                   
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
	private IPersonService personService;
	@Resource
	private SpecialCaseReportService specialCaseReportService;
	@Resource
	private SpecialCaseMaterialService specialCaseMaterialService;
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	@Resource
	private IWebOffice2000Service iWebOffice2000Service;
	
	private String queryStr;
	
	/**
	 * 我的专案页面载入表格
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findMyProjectTable(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		String personID=this.findCurrentPerson().getId();
		Pager<SpecialCase> dataList = specialCaseService
				.findSpecialCaseByConditions(data, personID,Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))), Integer.parseInt(String.valueOf(data.get("length"))));
		List<ProjectsMaintenanceBean> resultListBean=changeBean(dataList.getPageList());
		resultMap.put("resultMap", resultListBean);
		resultMap.put("totalNum",dataList.getTotalNum());
		String s="code="+String.valueOf(data.get("zabh"))+",name="+String.valueOf(data.get("zamc"))+",content="+String.valueOf(data.get("jyaq"))+",nature="+String.valueOf(data.get("ajxz"))+",sonCaseCode="+String.valueOf(data.get("sjzaj"))+",zazcyId="+String.valueOf(data.get("zazcy"))+",pageNo="+String.valueOf(Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))))+",pageSize="+String.valueOf(data.get("length"));
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案",s);
		return SUCCESS;
	}
	
	/**
	 * 我的专案 专案页面载入搜索加载项
	 * @return
	 */
	public String findMyProject(){
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_ZAXZ.getValue());
		List<DictionaryItem> lxList=dictionaryItemService.findDicItemsByType(type.getId(), null);
		resultMap.put("ajxz",lxList);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案",null);
		return SUCCESS;
	}
	
	/**
	 * 我的专案 置顶
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveZD(){
		Map<String,Object> data = JSONObject.fromObject(read());
		boolean bn=false;
		bn=specialCaseService.stickSpecialCase(String.valueOf(data.get("caseId")),this.findCurrentPerson().getId(),new Date());
		resultMap.put("result",bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+String.valueOf(data.get("caseId"))+",stickPersonId="+this.findCurrentPerson().getId()+",stickTime="+new Date()+"");
		return SUCCESS;
	}
	
	
	/**
	 * 我的专案 取消置顶
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String saveQXZD(){
		Map<String,Object> data = JSONObject.fromObject(read());
		boolean bn=false;
		bn=specialCaseService.unstickSpecialCase(String.valueOf(data.get("caseId")),this.findCurrentPerson().getId());
		resultMap.put("result",bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+String.valueOf(data.get("caseId"))+",personId="+this.findCurrentPerson().getId()+"");
		return SUCCESS;
	}
	
	/**
	 * 维护专案基本信息页面 载入时加载的数据--修改
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findMyUpdataProject(){
		Map<String,Object> data = JSONObject.fromObject(read());
		queryStr= String.valueOf(data.get("caseId"));
		SpecialCase  cas=specialCaseService.findSpecialCaseById(queryStr);
		SpecialCaseAndSonBean caseBean=changeSpecialCaseAndSonBean(cas);
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_ZAXZ.getValue());
		List<DictionaryItem> lxList=dictionaryItemService.findDicItemsByType(type.getId(), null);
		resultMap.put("ajxz",lxList);
		resultMap.put("specialCase",caseBean);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+queryStr+"");
		return SUCCESS;
	}

	/**
	 * 维护专案基本信息页面 载入时加载的数据--加载子案件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findBasicSonProjectTable(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		SpecialCase  cas=specialCaseService.findSpecialCaseById(String.valueOf(data.get("caseId")));
		SpecialCaseAndSonBean caseBean=changeSpecialCaseAndSonBean(cas);
		resultMap.put("sonCase",caseBean.getSonList());
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+queryStr+"");
		return SUCCESS;
	}

	/**
	 * 删除子案件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteSonCase(){
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		bn=specialCaseService.deleteSonCase(String.valueOf(data.get("caseID")), String.valueOf(data.get("caseCode")));
		resultMap.put("result",bn);	
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+String.valueOf(data.get("caseID"))+"sonCaseCode="+String.valueOf(data.get("caseCode"))+"");
		return SUCCESS;
		
		
	}
	
	/**
	 * 修改我的专案 --专案信息
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public  String updataSpecialCase() throws ParseException{
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		SpecialCase  spe= new SpecialCase();
		spe.setId(String.valueOf(data.get("caseId")));   //专案id
		spe.setCode(String.valueOf(data.get("zabh")));   //专案编号
		spe.setName(String.valueOf(data.get("zamcName"))); //案件名称
		spe.setContent(String.valueOf(data.get("jyaqName")));//简要案情
		spe.setProgress(String.valueOf(data.get("mqjzjName")));//目前进展
		spe.setNature(String.valueOf(data.get("ajxzName")));//案件性质
		spe.setProblem(String.valueOf(data.get("zczywtName")));//侦查工作中的主要问题
		spe.setPlan(String.valueOf(data.get("xybjhName")));//下一步工作计划
	    spe.setUpdatedTime(new Date());//案件更改日期
	    spe.setUpdatePerson(this.findCurrentPerson());
		bn=specialCaseService.updateSpecialCase(spe);
		//新增子案件
		JSONArray kcaJson = JSONArray.fromObject(data.get("addArr"));
	    CaseRelation[]  arr=(CaseRelation[])JSONArray.toArray(kcaJson, CaseRelation.class);
		for(int i=0;i<arr.length;i++){
			arr[i].setSpecialCase(spe);
			specialCaseService.addSonCase(arr[i]);
		}
		JSONArray list = JSONArray.fromObject(data.get("delArr"));
		for(int i=0;i<list.size();i++){ //删除子案件
			bn=specialCaseService.deleteSonCase(String.valueOf(data.get("caseId")), String.valueOf(list.get(i)));
		}
		resultMap.put("result",bn);	
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案",null);
		return SUCCESS;
	}
	
	/**
	 * 涉案人员表格加载
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String findInvolvedPersonTable(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
		Pager<SpecialCaseInvolvedPerson> personPager=specialCaseService.findInvolvedPersonByCase(String.valueOf(data.get("caseId")), Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))), Integer.parseInt(String.valueOf(data.get("length"))));
		List<SpecialCaseInvolvedPerson> list=  personPager.getPageList();
		List<SpecialCaseInvolvedPersonBean> listBean=changeSpecialCaseInvolvedPersonBean(list);
		resultMap.put("totalNum",personPager.getTotalNum());
		resultMap.put("pageList",listBean);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+String.valueOf(data.get("caseId"))+"");
		return SUCCESS;
	}
	
	
	/**
	 * 添加或修改涉案人员
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public  String saveInvolvedPerson() throws ParseException{
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		String caseId=String.valueOf(data.get("caseId"));
		//JSONObject  list=JSONObject.fromObject(data.get("listTable"));
		JSONArray jsonRtcstbIds = JSONArray.fromObject(data.get("listTable"));
		InvolvedPersonBean[] arr = (InvolvedPersonBean[])JSONArray.toArray(jsonRtcstbIds, InvolvedPersonBean.class);
		List<SpecialCaseInvolvedPerson> list=new ArrayList<SpecialCaseInvolvedPerson>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		for(int i=0;i<arr.length;i++){
			SpecialCaseInvolvedPerson person=new SpecialCaseInvolvedPerson();
			person.setCreatedTime(sdf.parse(arr[i].getCreatedTime()));  //创建日期
			Person per=personService.findById(arr[i].getCreatePersonId());
			person.setCreatePerson(per);   //创建人
			person.setHouseholdAddress(arr[i].getHouseholdAddress()); //地址
			person.setHouseholdRegister(arr[i].getHouseholdRegister()); //户籍
			person.setIdcard(arr[i].getIdcard());  //身份证
			person.setName(arr[i].getName());// 涉案人名称
			person.setNick(arr[i].getNick());//涉案人绰号
			person.setPhone(arr[i].getPhone());//电话
			person.setId(arr[i].getId());
			list.add(person);
		}
		bn=specialCaseService.updateInvolvedPersons(caseId, list,new ArrayList<String>());
		resultMap.put("result",bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_UPDATE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+String.valueOf(data.get("caseId"))+"");
		return SUCCESS;
	}
	
	/**
	 * 删除涉案人员
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public  String deleteInvolvedPerson() {
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		String caseId=String.valueOf(data.get("caseID"));		
		List<String> jsonRtcstbIds = JSONArray.fromObject(data.get("InvolvedPersonID"));
		bn=specialCaseService.updateInvolvedPersons(caseId, new ArrayList<SpecialCaseInvolvedPerson>(),jsonRtcstbIds);
		resultMap.put("result",bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+caseId+"toDelPersons="+jsonRtcstbIds+"");
		return SUCCESS;
	}
	
	/**
	 * 加载报告表格
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String findReportTable(){
		Map<String,Object> data = JSONObject.fromObject(queryStr);
	//	String personID=this.findCurrentPerson().getId();
		Pager<SpecialCaseReport> pagerReport= specialCaseReportService.findReportByCase(String.valueOf(data.get("caseId")), Integer.parseInt(String.valueOf(data.get("start")))/Integer.parseInt(String.valueOf(data.get("length"))), Integer.parseInt(String.valueOf(data.get("length"))));
		List<SpecialCaseReportBean> listBean=changeSpecialCaseReportBean(pagerReport.getPageList());
		resultMap.put("totalNum",pagerReport.getTotalNum());
		resultMap.put("pageList",listBean);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+queryStr+"");
		return SUCCESS;
	}
	/**
	 * 删除报告表格
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String deleteReport(){
		boolean  bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		bn= specialCaseReportService.deleteReport(String.valueOf(data.get("id")));
		attachmentCustomizedService.delete(String.valueOf(data.get("fjId")));
		resultMap.put("result",bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","reportId="+String.valueOf(data.get("id"))+",fjId="+String.valueOf(data.get("fjId"))+"");
		return SUCCESS;
	}
	/**
	 * 新增报告表格一级加载项
	 * @return
	 */
	public  String onLoadAddReport(){
		DictionaryType type = dictionaryTypeService.findDicTypeByCode(Constant.DICT.DICT_TYPE_BGLX.getValue());
		Pager<DictionaryItem> pagerOne=dictionaryItemService.findAllDicItemsByType(type.getId(), 0, 50);
		List<ZDXBean> listBeanOne=changeZDXBean(pagerOne.getPageList());
		resultMap.put("resultOne",listBeanOne);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块-我的专案",null);
		return SUCCESS;
	}
     
	/**
	 * 新增报告表格二级加载项
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String onloadSecondLevel(){
		Map<String,Object> data = JSONObject.fromObject(read());
		Pager<DictionaryItem> pagerTwo=dictionaryItemService.findAllDicItemsByParent(String.valueOf(data.get("bgid")), 0, 50);
		List<ZDXBean> listBeanTwo=changeZDXBean(pagerTwo.getPageList());
		resultMap.put("resultTwo",listBeanTwo);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案",null);
		return SUCCESS;
	}
	
	/**
	 * 保存报告前的验证
	 */
	@SuppressWarnings("unchecked")
	public String existsYZ(){
		boolean bn=false;
		Map<String, Object> rqst = JSONObject.fromObject(read());
		String zaId = rqst.get("zaId").toString(); 
		Pager<SpecialCaseReport> reportList= specialCaseReportService.findReportByCase(zaId, 0, 1000);
		for(int i=0;i<reportList.getPageList().size();i++){
			if(reportList.getPageList().get(i).getName().equals(String.valueOf(rqst.get("bgmc")))&&reportList.getPageList().get(i).getType().equals(String.valueOf(rqst.get("bglx")))){
				bn=true;
				resultMap.put("resultBG", bn);  //判断报告是否已存在. 通过报告名称和类型
				return SUCCESS;
			}
		}
		resultMap.put("resultBG", bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","caseId="+zaId+"");
		return SUCCESS;
	}
	
	/**
	 * 新建报告及附件
	 */
    @SuppressWarnings("unchecked")
	public  String  saveSpecialCaseReport (){
        boolean bn=false;
		Map<String, Object> rqst = JSONObject.fromObject(read());
//		String zllx = rqst.get("zllx").toString();  //报告附件类型
		String zaId = rqst.get("zaId").toString();   //专案id
		List<JSONObject> fileBeanLst = (List<JSONObject>) rqst.get("fileBeanLst");
		if( null != fileBeanLst){
			for (int i = 0 ;i<fileBeanLst.size();i++ ) {
				FileBean file = (FileBean) JSONObject.toBean(fileBeanLst.get(i), FileBean.class);
				SpecialCaseReport scm = new SpecialCaseReport(); //新建报告
				scm.setCreatePerson(this.findCurrentPerson()); 
				SpecialCase za = specialCaseService.findSpecialCaseById(zaId);
				//SpecialCase za = specialCaseService.findSpecialCaseById(zaId);
				scm.setSpecialCase(za);
				scm.setType(String.valueOf(rqst.get("bglx")));
				scm.setCreatedTime(new Date());
				scm.setCreatePerson(this.findCurrentPerson());
				scm.setName(String.valueOf(rqst.get("bgmc")));
				//scm.set
				//scm.setCreatedName(file.getName());
				specialCaseReportService.addReport(scm);//资料
				//保存附件
				Attachment att = attachmentCustomizedService.findById(file.getId());
				att.setTargetId(scm.getId());//id是业务对象的id
				att.setType(SpecialCaseReport.class.getName());//业务对象类型
				attachmentCustomizedService.save(att);
				scm.setAttachmentId(att.getId());  //将附件id付给报告
				bn = specialCaseReportService.updateReport(scm);
			}
		}
		resultMap.put("result", bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案",null);
		return SUCCESS;
	
    }  
	
    /**
	 * 删除附件
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String deleteSpecialCaseMaterial(){
		boolean bn=false;
		Map<String,Object> data = JSONObject.fromObject(read());
		bn = specialCaseMaterialService.deleteMaterial(String.valueOf(data.get("fjId")));
		resultMap.put("result",bn);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_DELETE,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","materialId="+String.valueOf(data.get("fjId"))+"");
		return SUCCESS;
	}
	/**
	 * 查询附件
	 * @return
	 */
    @SuppressWarnings("unchecked")
	public String findReport(){
    	Map<String,Object> data = JSONObject.fromObject(read());
    	SpecialCaseReport spe=specialCaseReportService.findReportId(String.valueOf(data.get("reportId")));
    	SpecialCaseReportBean report=changeReportBean(spe);
    	resultMap.put("result", report);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_QUERY,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","reportId="+String.valueOf(data.get("reportId"))+"");
    	return SUCCESS;
    }
    
    /**
     * 添加子案件
     * @return
     */
    @SuppressWarnings("unchecked")
	public String saveSonProject(){
    	@SuppressWarnings("unused")
		Map<String,Object> data = JSONObject.fromObject(read());
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案",null);
    	return SUCCESS;
    }
    
    /**
	 * 根据id查询报告附件---报告附件展示
	 * @param id 报告id
	 * @return "success",
	 */
	@SuppressWarnings("unchecked")
	public String findAccessoryById() { //查询附件
		String wordName = null;
		FileBean fileBean=new FileBean();
		Map<String,Object> data = JSONObject.fromObject(read());
    	SpecialCaseReport spe=specialCaseReportService.findReportId(String.valueOf(data.get("reportId")));
		if (spe != null) {
			Attachment am = attachmentCustomizedService.findById(String.valueOf(data.get("fjid")));
			fileBean.setId(am.getId()); 
			fileBean.setName(am.getName());
			fileBean.setSize(am.getSize().toString());
				InputStream fromDoc = am.getAttachmentMeta()  //对应的数据库的 fij_id
						.getAttachmentCopies().get(0).getInputStream();
				String docType = am.getName().substring(
						am.getName().lastIndexOf("."), am.getName().length());
				try {
					 wordName = iWebOffice2000Service.copyDoc(fromDoc, docType);
				} catch (Exception e) {
					wordName = "";
					LOGGER.debug("产生异常：", e);
				}
		}
		resultMap.put("wordName", wordName);
		resultMap.put("fileBean", fileBean);
		this.createStandardLog(StandardLogRecord.OPERATETYPE_ADD,StandardLogRecord.OPERATERESULT_SUCCESS, null, "专案管理模块--我的专案","reportId="+String.valueOf(data.get("reportId"))+"fjid="+String.valueOf(data.get("fjid"))+"");
		return SUCCESS;
	}    
    
	

/********************************************************************************************/	
	/**
	 * 涉案人员changeBean
	 */
	private List<SpecialCaseInvolvedPersonBean> changeSpecialCaseInvolvedPersonBean(List<SpecialCaseInvolvedPerson> list){
		List<SpecialCaseInvolvedPersonBean> listBean=new ArrayList<SpecialCaseInvolvedPersonBean>();
		for(int i=0;i<list.size();i++){
			SpecialCaseInvolvedPersonBean personBean=new SpecialCaseInvolvedPersonBean();
			personBean.setCreatedTime(list.get(i).getCreatedTime().getTime());
			personBean.setCreatePerson(list.get(i).getCreatePerson());
			personBean.setHouseholdAddress(list.get(i).getHouseholdAddress());
			personBean.setHouseholdRegister(list.get(i).getHouseholdRegister());
			personBean.setId(list.get(i).getId());
			personBean.setIdcard(list.get(i).getIdcard());
			personBean.setName(list.get(i).getName());
			personBean.setNick(list.get(i).getNick());
			personBean.setPhone(list.get(i).getPhone());
			personBean.setSpecialCase(list.get(i).getSpecialCase());
			listBean.add(personBean);
		}
		return listBean;
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
				List<String> sonIDList=new ArrayList<String>();  //涉及子案件
				List<String> sonCaseCodeList=new ArrayList<String>();
				for (CaseRelation str : list.get(i).getCaseRelation()) {  
				     sonIDList.add(str.getId());
				     sonCaseCodeList.add(str.getCaseCode());
				} 
				project.setSonIDList(sonIDList);
				project.setSonCaseCodeList(sonCaseCodeList);
				project.setQbSize(list.get(i).getSpecialCaseMaterial().size());//情报条数
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
				for(SpecialCaseRoleAssignment spe: list.get(i).getSpecialCaseRoleAssignment()){
					if(spe.getPerson().getId()==this.findCurrentPerson().getId()){
						project.setIsStick(spe.getIsStick());	
					}
				}
				//project.setIsStick(list.get(i).getSpecialCaseRoleAssignment().iterator().next().getIsStick());
				pageList.add(project);
			  } 
	    	return pageList;
		}
	
	/**
	 * 报告的转换bean
	 * @param pageList
	 * @return
	 */
	private List<SpecialCaseReportBean> changeSpecialCaseReportBean(
			List<SpecialCaseReport> list) {
		 List<SpecialCaseReportBean> listBean=new ArrayList<SpecialCaseReportBean>();
		for(int i=0;i<list.size();i++){
			SpecialCaseReportBean  report=new SpecialCaseReportBean();
			report.setAttachmentId(list.get(i).getAttachmentId());
			report.setCaseID(list.get(i).getSpecialCase().getId());
			report.setCreatedTime(list.get(i).getCreatedTime().getTime());
			report.setCreatePerson(list.get(i).getCreatePerson().getName());
			report.setCreatePersonID(list.get(i).getCreatePerson().getId());
			report.setId(list.get(i).getId());
			report.setName(list.get(i).getName());
			report.setType(list.get(i).getType());
			report.setUnitName(list.get(i).getCreatePerson().getOrganization().getShortName());
			listBean.add(report);
		}
		return listBean;
	}
	
	/**
	 * 人员可分配的bean
	 * @param pageList
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<PersonBean> changPersonBean1(List<Person> list) {
		List<PersonBean> person=new ArrayList<PersonBean>();
		for(int i=0;i<list.size();i++){
			PersonBean per=new PersonBean();
			per.setId(list.get(i).getId());
			per.setName(list.get(i).getName());
			per.setSex(list.get(i).getSex());
			per.setState(list.get(i).getStatus());
			per.setUnit(list.get(i).getOrganization().getName());
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
	 * 人员Bean 
	 * @param pageList
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<PersonBean> changPersonBean(
			List<SpecialCaseRoleAssignment> list) {
		List<PersonBean> person=new ArrayList<PersonBean>();
		for(int i=0;i<list.size();i++){
			PersonBean per=new PersonBean();
			per.setId(list.get(i).getPerson().getId());
			per.setName(list.get(i).getPerson().getName());
			per.setSex(list.get(i).getPerson().getSex());
			per.setState(list.get(i).getPerson().getStatus());
			per.setUnit(list.get(i).getPerson().getOrganization().getName());
			per.setRoleId(list.get(i).getRole().getId());
			per.setRoleName(list.get(i).getRole().getName());
			person.add(per);
		}
		return person;
	}
	
	/**
     * ReportBean
     * @param spe
     * @return
     */
	private SpecialCaseReportBean changeReportBean(SpecialCaseReport spe) {
		SpecialCaseReportBean  report =new SpecialCaseReportBean();
		report.setAttachmentId(spe.getAttachmentId());
		report.setCaseID(spe.getSpecialCase().getId());
		report.setCreatedTime(spe.getCreatedTime().getTime());
		report.setCreatePerson(spe.getCreatePerson().getName());
		report.setCreatePersonID(spe.getCreatePerson().getId());
		report.setId(spe.getId());
		report.setName(spe.getName());
		report.setType(spe.getType());
		report.setUnitName(spe.getCreatePerson().getOrganization().getName());
		return report;
	}
	
	/**
	 * 字典项的Bean
	 * @param pageList
	 * @return
	 */
	private List<ZDXBean> changeZDXBean(List<DictionaryItem> pageList) {
		List<ZDXBean> list=new ArrayList<ZDXBean>();
		for(int i=0;i<pageList.size();i++){
			ZDXBean  bean=new ZDXBean();
			bean.setId(pageList.get(i).getId());
			bean.setName(pageList.get(i).getName());
			bean.setCode(pageList.get(i).getCode());
			list.add(bean);
		}
		return list;
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
