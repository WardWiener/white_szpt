package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.comet.model.DefaultHintMsg;
import com.taiji.pubsec.complex.tools.comet.service.CometdPushService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.AlertBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.BaseBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.ModelBeanTransformUtilInHighRiskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.Alert;
import com.taiji.pubsec.szpt.highriskpersonalert.model.AlertInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.AlertService;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionBean;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionReceiveSubjectBean;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.placemonitor.model.PlaceBasicInfo;
import com.taiji.pubsec.szpt.placemonitor.model.WifiPlaceInAndOutInfo;
import com.taiji.pubsec.szpt.placemonitor.service.PlaceMonitorService;
import com.taiji.pubsec.szpt.placemonitor.service.WifiPlaceInAndOutInfoQueryService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.bean.FileObjectBean;

/**
 * 重点人关注Action
 *
 */
@Controller("personAttentionAction")
@Scope("prototype")
public class PersonAttentionAction extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonAttentionAction.class);
	
	@Resource(name="alertService")
	private AlertService alertService;
	
	@Resource
	private ModelBeanTransformUtilInHighRiskPerson modelBeanTransformUtil; //action model和bean对象转换工具
	
	private String alertId;// 预警记录id
	
	private String status;// 状态字典项id
	
	private int num;
	
	private String id;
	
	private InstructionBean instructionBean;
	
	private List<FileObjectBean> fileObjectBeans = new ArrayList<FileObjectBean>();// 附件Bean
	
	private Map<String,Object> paramMap = new HashMap<String,Object>();// 参数集合
	
	private List<AlertBean> alertBeanList = new ArrayList<AlertBean>();// 预警记录bean集合
	
	@Resource
	private WifiPlaceInAndOutInfoQueryService wifiPlaceInAndOutInfoQueryService;
	
	@Resource
	private PlaceMonitorService placeMonitorService;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;// 附件接口
	
	@Resource
	private IUnitService unitService;
	
	@Resource
	private IInstructionService instructionService;
	
	/**
	 * 保存指令
	 * 
	 * @return
	 */
	public String saveInstruction(){
		Instruction in = new Instruction();
		in.setRelatedObjectId(instructionBean.getRelatedObjectId());
		in.setRelatedObjectType(instructionBean.getRelatedObjectType());
		in.setRelateObjectContent(instructionBean.getRelateObjectContent());
		in.setContent(instructionBean.getContent());
		in.setType(instructionBean.getType());
		in.setRequireFeedbackTime(new Date(instructionBean.getRequireFeedbackTime()));
		in.setIsNofityDepartmentLeader(instructionBean.getIsNofityDepartmentLeader());
		in.setTypeContent(instructionBean.getTypeContent());
		if(instructionBean.getSubs() != null && !instructionBean.getSubs().isEmpty()){
			for(InstructionReceiveSubjectBean irsb : instructionBean.getSubs()){
				InstructionReceiveSubject irs = transformInstructionReceiveSubject(irsb, in);
				if(irs == null){
					continue;
				}
				in.getInstructionReceiveSubjects().add(irs);
				
				CometdPushService cometdPushService = (CometdPushService)SpringContextUtil.getBean("defaultCometdPushService");
				Unit unit = unitService.findById(irsb.getReceiveSubjectId());
				LOGGER.debug("实战指令推送任务ForPDA执行");
				DefaultHintMsg msg = new DefaultHintMsg("{businessType:\"SzptInstruction\", businessData:\"\", existingTime:\"\"}") ;
				cometdPushService.pushHint(unit.getCode(), "/service/receive/hint", msg);
			}
		}
		if (StringUtils.isEmpty(instructionBean.getId())) {
			in.setCreateTime(new Date());
			in.setCreatePeopleId(findCurrentPersonId());
			in.setCreatePeopleDepartmentId(findCurrentOrganizationId());
			
			id = instructionService.createInstruction(in);
		}else{
			for (InstructionReceiveSubject sub : in.getInstructionReceiveSubjects()) {
				instructionService.addInstructionReceiveSubject(sub);
			}
			id = instructionBean.getId();
		}
		
		for(FileObjectBean fob : fileObjectBeans){
			Attachment att = attachmentCustomizedService.findById(fob.getId());
			att.setTargetId(in.getId());
			att.setType(Instruction.class.getName());
			attachmentCustomizedService.save(att);
		}
		return SUCCESS;
	}
	
	/**
	 * 指令接收主体Bean转Model
	 * 
	 * @param irsb 指令接收主体Bean
	 * @param in 指令Model
	 * @return irs 指令接收主体Model
	 */
	public InstructionReceiveSubject transformInstructionReceiveSubject(InstructionReceiveSubjectBean irsb, Instruction in){
		if(irsb == null){
			return null;
		}
		InstructionReceiveSubject irs = new InstructionReceiveSubject();
		irs.setId(StringUtil.isBlank(irsb.getId()) || "null".equals(irsb.getId())?null:irsb.getId());
		irs.setReceiveSubjectType(irsb.getReceiveSubjectType());
		irs.setReceiveSubjectId(irsb.getReceiveSubjectId());
		irs.setReceiveTime(new Date());
		irs.setStatus(DICT.DICT_ZLZT_DQS.getValue());
		irs.setInstruction(in);
		return irs;
	}

	/**
	 * 查询所有未处理预警信息（分页查询）
	 * 
	 * @param paramMap 查询参数集合
	 * @param status 状态字典项id
	 * @param this.getStart()/this.getLength() 页数
	 * @param this.getLength() 步长
	 * @return "success"，成功返回alertBeanList：预警记录bean集合
	 */
	public String findAllUntreatedAlert(){
		Pager<Alert> alertPager = alertService.findAllAlert(Constant.YJJLZT_WCL,this.getStart()/this.getLength(),this.getLength());
		
		this.setTotalNum(alertPager.getTotalNum());
		for(Alert a : alertPager.getPageList()){
			alertBeanList.add(alertToAlertBean(a));
		}
		return SUCCESS;
	}
	
	/**
	 * 更新预警信息状态
	 * 
	 * @param alertId 预警记录id
	 * @param status 状态字典项id
	 * @return "success",成功无返回值
	 */
	public String updateAlertStatus(){
		Alert alert = alertService.findAlertById(alertId);
		alert.setState(status);
		alertService.updateAlert(alert);
		return SUCCESS;
	}
	
	
	/**
	 * 查询未处理预警条数
	 * 
	 * @return "success",成功无返回值
	 */
	public String findUntreatedAlertNum(){
		num = alertService.findAlertNumByState(Constant.YJJLZT_WCL);
		return SUCCESS;
	}
	
	
	/**
	 * 预警记录model转bean
	 * 
	 * @param alert 预警记录model
	 * @return alertBean 预警记录bean
	 */
	private AlertBean alertToAlertBean(Alert alert){
		AlertBean alertBean = new AlertBean();
		BeanUtils.copyProperties(alert, alertBean);
		//设置聚集重点人位置信息集合（经纬度）
		List<BaseBean> personLocationList = new ArrayList<BaseBean>();
		List<BaseBean> placeLocationList = new ArrayList<BaseBean>();
		if(!alert.getAlertInfos().isEmpty()){
			String[] placeNames = alert.getPlace().split(",");
			for(int i=0;i<placeNames.length;i++){
				PlaceBasicInfo place = placeMonitorService.findPlaceBasicInfoByPlaceName(placeNames[i]);
				if(place!=null){
					BaseBean bb = new BaseBean();
					bb.setLatitude(place.getLatitude());
					bb.setLongitude(place.getLongitude());
					bb.setIdentifying(place.getId());
					placeLocationList.add(bb);
				}
			}
			alertBean.setPlaceLocationList(placeLocationList);
			for(AlertInfo info : alert.getAlertInfos()){
				if(info.getTargetType().equals(WifiPlaceInAndOutInfo.class.getName())){
					WifiPlaceInAndOutInfo wifi = wifiPlaceInAndOutInfoQueryService.findWifiPlaceInAndOutInfoById(info.getTargetId());
					if(wifi==null){
						continue;
					}
					BaseBean bb = new BaseBean();
					bb.setLatitude(wifi.getLatitude());
					bb.setLongitude(wifi.getLongitude());
					bb.setIdCode(wifi.getIdCode());
					bb.setPersonName(wifi.getPersonName());
					personLocationList.add(bb);
				}
			}
			alertBean.setPersonLocationList(personLocationList);
		}
		alertBean.setStateName(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.YJJLZT,alert.getState()));
		return alertBean;
	}
	
	/**
	 * 查看当前登录人id
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String findCurrentPersonId() {
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
		Map<String, Object> userMap = user.getUserMap();
		Map<String, String> mPerson = new HashMap<String, String>(0);
		if (userMap.get("person") != null) {
			mPerson = (Map<String, String>) userMap.get("person");
		}
		return mPerson.get("id");
	}

	/**
	 * 查看当前登录单位id
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String findCurrentOrganizationId() {
		MySecureUser user = SessionUserDetailsUtil.getMySecureUser();
		Map<String, Object> userMap = user.getUserMap();
		Map<String, String> mOrg = new HashMap<String, String>(0);
		if (userMap.get("org") != null) {
			mOrg = (Map<String, String>) userMap.get("org");
		}
		return mOrg.get("id");
	}
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public List<AlertBean> getAlertBeanList() {
		return alertBeanList;
	}

	public void setAlertBeanList(List<AlertBean> alertBeanList) {
		this.alertBeanList = alertBeanList;
	}

	public String getAlertId() {
		return alertId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public InstructionBean getInstructionBean() {
		return instructionBean;
	}

	public void setInstructionBean(InstructionBean instructionBean) {
		this.instructionBean = instructionBean;
	}

	public List<FileObjectBean> getFileObjectBeans() {
		return fileObjectBeans;
	}

	public void setFileObjectBeans(List<FileObjectBean> fileObjectBeans) {
		this.fileObjectBeans = fileObjectBeans;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
