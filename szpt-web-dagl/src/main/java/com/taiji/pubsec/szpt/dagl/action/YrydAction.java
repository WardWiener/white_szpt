package com.taiji.pubsec.szpt.dagl.action;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.persistence.dao.JpaSqlDaoImpl;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CollectInfoSituation;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CollectInfoSituationService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CriminalPersonService;
import com.taiji.pubsec.szpt.dagl.action.bean.InstructionAndFeedbackBean;
import com.taiji.pubsec.szpt.dagl.bean.PersonBriefInfo;
import com.taiji.pubsec.szpt.dagl.bean.YrydCybercafeBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydHotelBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydPlaneGoOutBean;
import com.taiji.pubsec.szpt.dagl.bean.YrydTrainGoOutBean;
import com.taiji.pubsec.szpt.dagl.service.PersonInterestedService;
import com.taiji.pubsec.szpt.dagl.service.PersonSearchService;
import com.taiji.pubsec.szpt.dagl.service.YrydService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPersonAvatar;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.PersonTrackInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.WifiTrackBean;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.PersonnelTrackService;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.service.SzptUnitCommonService;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.LoginInfoAction;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

import net.sf.json.JSONObject;

@Controller("yrydAction")
@Scope("prototype")
public class YrydAction extends LoginInfoAction{

	private static final long serialVersionUID = 1L;

	private String queryStr;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	
	@Resource
	private YrydService yrydService;
	
	@Resource(name="personnelTrackWifiService")
	private PersonnelTrackService personnelTrackWifiService;
	
	@Resource
	private PersonInterestedService personInterestedService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource
	private JpaSqlDaoImpl sqlDao;
	
	@Resource
	private IUnitService unitService ;
	
	@Resource
	private SzptUnitCommonService szptUnitCommonService;
	
	@Resource
	private PersonSearchService personSearchService;
	
	@Resource
	private IInstructionService instructionService;
	
	@Resource
	private IPersonService personService; 
	
	@Resource(name="criminalSzptPersonService")
	private CriminalPersonService criminalPersonService;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;
	
	@Resource
	private CaseService caseService;// 案件接口
	
	@Resource
	private CollectInfoSituationService collectInfoSituationService;// 采集信息接口
	
	public String findHiPersonAvatarById(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String idcard = String.valueOf(data.get("idcard"));
		List<Attachment> attLst = attachmentCustomizedService.findByTargetIdAndType(idcard, HighriskPersonAvatar.class.getName());
		List<String> imgLst = new ArrayList<>();
		for (Attachment att : attLst) {
			String base64Str = null;
			try {
				base64Str = Base64CryptFmtUtil.encode(FileFmtUtils.readByByteBuffer(
						att.getAttachmentMeta().getAttachmentCopies().get(0).getInputStream()), false);
			} catch (UnsupportedEncodingException e) {
				System.out.println("图片转字符串错误");
			}
			imgLst.add(base64Str);
		}
		resultMap.put("result", imgLst);
		return SUCCESS;
	}
	
	/**
	 * 查询一人一档信息
	 * @return
	 */
	public String findPersonInfo(){
		Map<String, Object> data = JSONObject.fromObject(queryStr);
		if("".equals(data.get(SolrConstant.Population.text.getValue()).toString())){
			data.put(SolrConstant.Population.text.getValue(), null);
		}
		Integer pageNo=(Integer.parseInt(String.valueOf( data.get("start"))))/Integer.parseInt(String.valueOf( data.get("pageSize")));
		Pager<Map<String, Object>> pager = personSearchService.searchPersonGetPager(data, pageNo, Integer.valueOf(data.get("pageSize").toString()));
		Pager<PersonBriefInfo> page = new Pager<>();
		if(pager.getPageList().size() >0){
			List<Map<String, Object>> list = pager.getPageList();
			List<PersonBriefInfo> personBriefInfoLst = new  ArrayList<>();
			for(Map<String, Object> map : list){
				personBriefInfoLst.add(mapTrunPersonBean(map));
			}
			page.setTotalNum(pager.getTotalNum());
			page.setPageList(personBriefInfoLst);
			page.setTotalNumber(pager.getTotalNumber());
		}else{
			List<PersonBriefInfo> personBriefInfoLst = new  ArrayList<>();
			page.setPageList(personBriefInfoLst);
			page.setTotalNumber(0l);
		}
		resultMap.put("result", page);
		return SUCCESS;
	}	
	
	public String findOnePersonInfo(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String idcard = String.valueOf(data.get("idcard"));
		Date startDate = DateFmtUtil.longToDate(Long.valueOf(data.get("startTime").toString()));
		Date endDate = DateFmtUtil.longToDate(Long.valueOf(data.get("endTime").toString()));
		Map<String, Object> personMap = personSearchService.searchPerson(idcard );
		List<YrydTrainGoOutBean> yrydTrainGoOutBeanLst = yrydService.findTrainGoOutInfoByIdcard(idcard, startDate, endDate);
		List<YrydPlaneGoOutBean> planeGoOutBeanLst = yrydService.findPlaneGoOutInfoByIdcard(idcard, startDate, endDate);
		List<YrydCybercafeBean> cybercafeLst = yrydService.findCybercafeInfoByIdcard(idcard, startDate, endDate);
		List<YrydHotelBean> hotelLst = yrydService.findHotelInfoByIdcard(idcard, startDate, endDate);
//		resultMap.put("instruction", findInstructionAndfeedbackBeanLst(idcard));
		PersonBriefInfo personBriefInfo = mapTrunPersonBean(personMap);
		if(null != criminalPersonService.findCriminalPersonByIdcard(personBriefInfo.getIdcard())){
			personBriefInfo.setSfsXyy("1");
		}else{
			personBriefInfo.setSfsXyy("0");
		}
		
		resultMap.put("result", personBriefInfo);
		resultMap.put("train", yrydTrainGoOutBeanLst);
		resultMap.put("plane", planeGoOutBeanLst);
		resultMap.put("cybercafe", cybercafeLst);
		resultMap.put("hotel", hotelLst);
		
		resultMap.put("recordInfo", findCriminalBasicCaseByIdcard(idcard));
		return SUCCESS;
	}
	
	/**
	 * 根据身份证号查询采集信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findCollectInfoSituationByIdcard(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String idcard = String.valueOf(data.get("idcard"));
		CollectInfoSituation cis = collectInfoSituationService.findCollectInfoSituationByIdcard(idcard);
		resultMap.put("result", cis);
		return SUCCESS;
	}
	/**
	 * 根据人员身份证号码查询关联案件集合
	 * @return
	 */
	private List<Map<String, String>> findCriminalBasicCaseByIdcard(String idcard){
		List<CriminalBasicCase> cbcs = caseService.findCaseBySuspectIdcard(idcard);
		if(cbcs == null){
			return null;
		}
		List<Map<String, String>> cbcMap = new ArrayList<Map<String, String>>();
		for(CriminalBasicCase cbc : cbcs){
			Map<String, String> map = new HashMap<String, String>();
			map.put("caseCode", cbc.getCaseCode());
			map.put("caseName", cbc.getCaseName());
			map.put("caseTypeName", cbc.getCaseTypeName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("caseTimeStart", sdf.format(cbc.getCaseTimeStart()));
			cbcMap.add(map);
		}
		return cbcMap;
	}
	
	public String findCommonPersonInfo(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String idcard = String.valueOf(data.get("idcard"));
		Date startDate = DateFmtUtil.longToDate(Long.valueOf(data.get("startTime").toString()));
		Date endDate = DateFmtUtil.longToDate(Long.valueOf(data.get("endTime").toString()));
		Map<String, Object> personMap = personSearchService.searchPerson(idcard );
		PersonBriefInfo personBriefInfo = mapTrunPersonBean(personMap);;
		List<YrydTrainGoOutBean> yrydTrainGoOutBeanLst = yrydService.findTrainGoOutInfoByIdcard(idcard, startDate, endDate);
		List<YrydPlaneGoOutBean> planeGoOutBeanLst = yrydService.findPlaneGoOutInfoByIdcard(idcard, startDate, endDate);
		List<YrydCybercafeBean> cybercafeLst = yrydService.findCybercafeInfoByIdcard(idcard, startDate, endDate);
		List<YrydHotelBean> hotelLst = yrydService.findHotelInfoByIdcard(idcard, startDate, endDate);
		resultMap.put("result", personBriefInfo);
		resultMap.put("train", yrydTrainGoOutBeanLst);
		resultMap.put("plane", planeGoOutBeanLst);
		resultMap.put("cybercafe", cybercafeLst);
		resultMap.put("hotel", hotelLst);
		return SUCCESS;
	}
	
	public String findOnePersonTrankInfo(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String idcard = String.valueOf(data.get("idcard"));
		Date startDate = DateFmtUtil.longToDate(Long.valueOf(data.get("startTime").toString()));
		Date endDate = DateFmtUtil.longToDate(Long.valueOf(data.get("endTime").toString()));	
		HighriskPerson person = highriskPersonService.findByIdCode(idcard);
		List<PersonTrackInfo> personTrackLst = new ArrayList();
		WifiTrackBean wifiTrackBean = new WifiTrackBean();
		if(null != person && person.getMobilePhoneInfos().size() > 0){
			Set<MobilePhoneInfo> mobilePhoneInfos = person.getMobilePhoneInfos();
			for(MobilePhoneInfo mobilePhoneInfo : mobilePhoneInfos){
				List<PersonTrackInfo> personTracks = personnelTrackWifiService.getPersonTracks(startDate, endDate, mobilePhoneInfo.getMac());
				personTrackLst.addAll(personTracks);
			}
		}
		resultMap.put("result", personTrackLst);
		return SUCCESS;
	}
	
	//加载指令和反馈
	public  String  findInstructionAndfeedbackBeanLst(){
		Map<String, Object> data = JSONObject.fromObject(read());
		String idcard = String.valueOf(data.get("idcard"));
		List<Instruction> instructionLst = instructionService.findInstructionByRelatedObjectId(idcard);
		List<InstructionReceiveSubjectFeedback> instructionReceiveSubjectFeedbackLst = instructionService.findFeedBacksByInstructionRelatedObjectId(idcard);
		List<InstructionAndFeedbackBean> instructionBeanLst = instructionChangeInstructionAndFeedbackBean(instructionLst);
		List<InstructionAndFeedbackBean> InstructionFeedbackBeanLst = instructionReceiveSubjectFeedbackChangeInstructionAndFeedbackBean(instructionReceiveSubjectFeedbackLst);
		List<InstructionAndFeedbackBean> instructionAndFeedbackBeanLst = new ArrayList();
		if(null != instructionBeanLst){
			instructionAndFeedbackBeanLst = instructionBeanLst;
		}
		if(null != InstructionFeedbackBeanLst){
			instructionAndFeedbackBeanLst.addAll(InstructionFeedbackBeanLst);
		}
		resultMap.put("result", instructionAndFeedbackBeanLst);
		return SUCCESS;
	}
	
	//添加指令
	private List<InstructionAndFeedbackBean> instructionChangeInstructionAndFeedbackBean(List<Instruction> instructionLst){
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
		DateFormat sj = new SimpleDateFormat("HH:mm:ss"); 
		if(null != instructionLst && instructionLst.size() > 0){
			List<InstructionAndFeedbackBean>  instructionAndFeedbackBeanLst = new ArrayList<InstructionAndFeedbackBean>();
			for(Instruction  instruction : instructionLst){
				InstructionAndFeedbackBean instructionAndFeedbackBean = new InstructionAndFeedbackBean();
				instructionAndFeedbackBean.setId(instruction.getId());
				instructionAndFeedbackBean.setCreateTimeLong(instruction.getCreateTime() == null ? 0 : instruction.getCreateTime().getTime());
				Unit createUnit = unitService.findUnitById(instruction.getCreatePeopleDepartmentId());
				instructionAndFeedbackBean.setCreateDepartment(null != createUnit ? createUnit.getShortName() : "");
				String str = "";
				for(InstructionReceiveSubject irs :  instruction.getInstructionReceiveSubjects()){
					if(Unit.class.getName().equals(irs.getReceiveSubjectType())){
						str = str + unitService.findUnitById(instruction.getCreatePeopleDepartmentId()).getShortName()+",";
					}
				}
				if(str.length() > 0){
					str = str.substring(0, str.length()-1);
				}
				instructionAndFeedbackBean.setAcceptDepartments(str);
				instructionAndFeedbackBean.setContent(instruction.getContent());
				instructionAndFeedbackBean.setCreateDate(dt.format(instruction.getCreateTime()));
				instructionAndFeedbackBean.setCreateTime(sj.format(instruction.getCreateTime()));
				instructionAndFeedbackBean.setIsFeedBack(false);
				instructionAndFeedbackBeanLst.add(instructionAndFeedbackBean);
			}
			return instructionAndFeedbackBeanLst;
		}
		return null;
	}
	
	//添加指令反馈
	private List<InstructionAndFeedbackBean> instructionReceiveSubjectFeedbackChangeInstructionAndFeedbackBean(List<InstructionReceiveSubjectFeedback> instructionReceiveSubjectFeedbackLst){
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
		DateFormat sj = new SimpleDateFormat("HH:mm:ss"); 
		if(null != instructionReceiveSubjectFeedbackLst && instructionReceiveSubjectFeedbackLst.size() > 0){
			List<InstructionAndFeedbackBean>  instructionAndFeedbackBeanLst = new ArrayList<InstructionAndFeedbackBean>();
			for(InstructionReceiveSubjectFeedback  instructionFeedback  : instructionReceiveSubjectFeedbackLst){
				InstructionAndFeedbackBean instructionAndFeedbackBean = new InstructionAndFeedbackBean();
				instructionAndFeedbackBean.setId(instructionFeedback.getId());
				instructionAndFeedbackBean.setCreateDate(dt.format(instructionFeedback.getFeedbackTime()));
				instructionAndFeedbackBean.setCreateTime(sj.format(instructionFeedback.getFeedbackTime()));
				instructionAndFeedbackBean.setCreateTimeLong(instructionFeedback.getFeedbackTime() == null ? 0 : instructionFeedback.getFeedbackTime().getTime());
				Unit createUnit = unitService.findUnitById(instructionFeedback.getInstructionReceiveSubject().getInstruction().getCreatePeopleDepartmentId());
				instructionAndFeedbackBean.setCreateDepartment(null != createUnit ? createUnit.getShortName() : "");
				instructionAndFeedbackBean.setFeedbackContent(instructionFeedback.getFeedbackContent());
				instructionAndFeedbackBean.setIsFeedBack(true);
				Person person = personService.findById(instructionFeedback.getFeedbackPeopleId());
				instructionAndFeedbackBean.setFeedbackDepartment(person.getOrganization().getShortName());
				instructionAndFeedbackBeanLst.add(instructionAndFeedbackBean);
			}
			return instructionAndFeedbackBeanLst;
		}
		return null;
	}
	
	//map转人员bean
	 private PersonBriefInfo mapTrunPersonBean(Map<String,Object> map){
		 DateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
		 PersonBriefInfo personBriefInfo = new PersonBriefInfo();
			personBriefInfo.setIdcard(null == map.get("idcard")? "" :  String.valueOf(map.get("idcard")));
			personBriefInfo.setName(null == map.get("name")? "" :  String.valueOf(map.get("name")));
			personBriefInfo.setOldname(null == map.get("oldname")? "" :  String.valueOf(map.get("oldname")));
			personBriefInfo.setAddress(null == map.get("address")? "" :  String.valueOf(map.get("address")));
			personBriefInfo.setType(null == map.get("type")? "" :  String.valueOf(map.get("type")));
			personBriefInfo.setNation(null == map.get("nation")? "" :  String.valueOf(map.get("nation")));
			personBriefInfo.setBirthaddress(null == map.get("birthaddress")? "" :  String.valueOf(map.get("birthaddress")));
			personBriefInfo.setCulture(null == map.get("culture")? "" :  String.valueOf(map.get("culture")));
			personBriefInfo.setMarry(null == map.get("marry")? "" :  String.valueOf(map.get("marry")));
			personBriefInfo.setOccupation(null == map.get("occupation")? "" :  String.valueOf(map.get("occupation")));
			personBriefInfo.setPhone(null == map.get("phone")? "" :  String.valueOf(map.get("phone")));
			personBriefInfo.setHouseholder(null == map.get("householder")? "" : String.valueOf(map.get("householder")));
			personBriefInfo.setRelation(null == map.get("relation")? "" : String.valueOf(map.get("relation")));
			personBriefInfo.setBirthday(null == map.get("birthday") ? "" : dt.format(map.get("birthday")));
			personBriefInfo.setAlertlevel(null == map.get("alertlevel") ? "" : String.valueOf(map.get("alertlevel")));
			if(map.get("type")!= null && ("是".equals(String.valueOf(map.get("type"))) || "1".equals(String.valueOf(map.get("type"))))){
				HighriskPerson highriskPerson = highriskPersonService.findByIdCode(personBriefInfo.getIdcard());
				if(null != highriskPerson && null != highriskPerson.getLocalAddressDetail()){
					personBriefInfo.setLocalAddress(highriskPerson.getLocalAddressDetail());
				}else{
					personBriefInfo.setLocalAddress("");
				}
			}
			if(null != criminalPersonService.findCriminalPersonByIdcard(personBriefInfo.getIdcard())){
				personBriefInfo.setSfsXyy("1");
			}else{
				personBriefInfo.setSfsXyy("0");
			}
			if(null !=map.get("gender") && ("1".equals(String.valueOf(map.get("gender"))) || "男".equals(String.valueOf(map.get("gender"))))){
				personBriefInfo.setSex("男");
			}else if(null !=map.get("gender") && ("2".equals(String.valueOf(map.get("gender"))) || "女".equals(String.valueOf(map.get("gender"))))){
				personBriefInfo.setSex("女");
			}else{
				personBriefInfo.setSex("性别:未知");
			}
			if(null != personInterestedService.personIfOrNotInterested(this.findCurrentPerson().getId(),personBriefInfo.getIdcard())){
				personBriefInfo.setSfbGz(true);
			}else{
				personBriefInfo.setSfbGz(false);
			}
			if(ParamMapUtil.isNotBlank(map.get("persontypecode"))){
				Object persontypecodes = map.get("persontypecode");
				String typeCode = com.taiji.pubsec.szpt.util.Constant.DICT.DICT_TYPE_RYLX.getValue();
				DictionaryItem dict = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode,persontypecodes.toString(), null);
				if(null !=  dict){
					personBriefInfo.setPersontypeName(dict.getName());
				}else{
					personBriefInfo.setPersontypeName("");
				}
			}else{
				personBriefInfo.setPersontypeName("");
			}
		 return personBriefInfo;
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
