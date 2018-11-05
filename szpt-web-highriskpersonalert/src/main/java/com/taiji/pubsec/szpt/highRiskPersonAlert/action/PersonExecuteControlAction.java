package com.taiji.pubsec.szpt.highRiskPersonAlert.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.common.tools.util.FileFmtUtils;
import com.taiji.pubsec.complex.tools.attachment.AttachmentCopy;
import com.taiji.pubsec.complex.tools.attachment.AttachmentMeta;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.generatenum.service.IGenerateNumService;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonExecuteControlBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonExecuteControlResultBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.PersonMobileInfoBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.Constant;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.util.ModelBeanTransformUtilInHighRiskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonControlImg;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonMobileInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlResultService;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;
import com.taiji.pubsec.szpt.util.bean.FileObjectBean;

import groovy.ui.SystemOutputInterceptor;


/**
 * 人员布控
 * 
 */
@Controller("personExecuteControlAction")
@Scope("prototype")
public class PersonExecuteControlAction extends PageCommonAction {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PersonExecuteControlAction.class) ;

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private boolean isShowInvalid;
	
	private boolean isPass;
	
	private String snapshotInfo;
	
	private String idCode;
	
	private String bkNum;
//	private Integer start;
//	
//	private Integer length;
	
	private Pager<PersonExecuteControlBean> personExecuteControlBeanPager;
	
	private PersonExecuteControlBean personExecuteControlBean;
	
	private List<FileObjectBean> fileBeanList;
	
	private List<PersonMobileInfoBean> personMobileInfoBeanList;
	
	private List<OperationRecordBean> operationRecordBeanList;
	
	private String approveContent;
	
	private Pager<PersonExecuteControlResultBean> personExecuteControlResultBeanPager;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private InfoSnapshotService infoSnapshotService;
	
	@Resource
	private IHighriskPersonService highriskPersonService;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;
	
	@Resource
	private IPersonExecuteControlService personExecuteControlService;
	
	@Resource
	private IPersonExecuteControlResultService personExecuteControlResultService;
	
	@Resource
	private ModelBeanTransformUtilInHighRiskPerson modelBeanTransformUtil; //action model和bean对象转换工具
	
	@Resource
	private IOperationRecordService operationRecordService;
	
	@Resource
	private IGenerateNumService generateNumService;
	
	public String acquireNum(){
		bkNum = personExecuteControlService.acquireNum();
		return SUCCESS;
	}
	
	public String queryPersonExecuteControlList() {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("code", personExecuteControlBean.getNum());
		if(personExecuteControlBean.getStartTime()!=null){
			paramMap.put("timeStart", DateFmtUtil.longToDate(personExecuteControlBean.getStartTime()));
		}
		if(personExecuteControlBean.getEndTime()!=null){
			paramMap.put("timeEnd", DateFmtUtil.longToDate(personExecuteControlBean.getEndTime()));
		}
		paramMap.put("personName", personExecuteControlBean.getPersonName());
		paramMap.put("personIdcode", personExecuteControlBean.getIdCardNo());
		paramMap.put("note", personExecuteControlBean.getNote());
		paramMap.put("operateStatus", personExecuteControlBean.getOperateStatus());
		paramMap.put("isShowInvalid", isShowInvalid);
		paramMap.put("status", isShowInvalid?null:Constant.ENABLED);
		List<PersonExecuteControlBean> list = new ArrayList<PersonExecuteControlBean>();
		Pager<PersonExecuteControl> pager = personExecuteControlService.findPersonExecuteControlByCondition(paramMap, this.getStart() / this.getLength(), this.getLength());
		for (PersonExecuteControl control : pager.getPageList()) {
			list.add(transformPersonExecuteControlBean(control));
		}
		personExecuteControlBeanPager = new Pager<PersonExecuteControlBean>();
		personExecuteControlBeanPager.setPageList(list);
		personExecuteControlBeanPager.setTotalNum(pager.getTotalNum());
		return SUCCESS;
	}
	public String findPersonExecuteControlById(){ //修改内容展示
		PersonExecuteControl control= personExecuteControlService.findById(id);
		personExecuteControlBean = transformPersonExecuteControlBean(control);
//		List<Attachment> attList = attachmentCustomizedService
//				.findByTargetIdAndType(control.getId(), PersonExecuteControl.class.getName());
//    	fileBeanList = new ArrayList<FileObjectBean>(); //页面显示的图片信息
//		for (Attachment att : attList) {
//			FileObjectBean fBeam = new FileObjectBean();
//			fBeam.setId(att.getId());
//			fBeam.setName(att.getName());
//			fBeam.setSize(String.valueOf(att.getSize()));
//			fileBeanList.add(fBeam);
//		}
		Set<PersonControlImg> attSet=control.getPersonControlImgs();
		fileBeanList = new ArrayList<FileObjectBean>(); //页面显示的图片信息
		for (PersonControlImg att : attSet) {
			FileObjectBean fBeam = new FileObjectBean();
			fBeam.setId(att.getSzptAttachmentId());//对应的附件id
			fBeam.setName(att.getName());
			fBeam.setSize(String.valueOf(att.getSize()));
			fileBeanList.add(fBeam);
		}
		List<PersonMobileInfo> phones = new ArrayList<>(control.getPersonMobileInfos());
		personMobileInfoBeanList = new ArrayList<PersonMobileInfoBean>();
		for(PersonMobileInfo phone : phones){
			PersonMobileInfoBean phoneBean = new PersonMobileInfoBean();
			BeanUtils.copyProperties(phone, phoneBean);
			personMobileInfoBeanList.add(phoneBean);
		}
		
		HighriskPerson person = highriskPersonService.findByIdCode(control.getIdCardNo());
		if(person!=null){
			personExecuteControlBean.setLocalAddressDetail(person.getLocalAddressDetail());
			personExecuteControlBean.setEthnicGroup(person.getEthnicGroup());
			StringBuffer phone = new StringBuffer();
			for(MobilePhoneInfo phoneInfo : person.getMobilePhoneInfos()){
				phone.append(phoneInfo.getNumber().length()==0?"":phoneInfo.getNumber()+",");
			}
			personExecuteControlBean.setPhone(phone.length()==0?"":phone.substring(0, phone.length()-1));
		}
		if(control.getOperateStatus()!=null&&control.getOperateStatus().equals(Constant.CZZT_SPBH)){
			List<OperationRecord> records = personExecuteControlService.findOperationRecordByPersonExecuteControl(id);
			if(records.size()>0){
    			OperationRecordBean bean = modelBeanTransformUtil.transformOperationRecordBean(records.get(0));
    			personExecuteControlBean.setApproveContent(bean.getContent());
    			personExecuteControlBean.setApprovePeople(bean.getOperatorName());
    			personExecuteControlBean.setApproveTime(bean.getOperateTime());
    		}
		}
		return SUCCESS;
	}
	public String savePersonExecuteControl(){
		PersonExecuteControl control;
		if(StringUtils.isEmpty(personExecuteControlBean.getId())){
			control = new PersonExecuteControl();
			BeanUtils.copyProperties(personExecuteControlBean, control);
			control.setCreatePerson(this.findCurrentPerson());
			control.setStartTime(DateFmtUtil.longToDate(personExecuteControlBean.getStartTime()));
			control.setEndTime(DateFmtUtil.longToDate(personExecuteControlBean.getEndTime()));
			control.setLastModifyPerson(findCurrentPerson().getName());
			control.setLastModifyTime(new Date());
			control.setOperateStatus(Constant.CZZT_DSP);
			List<PersonMobileInfo> personMobileInfoList = new ArrayList<PersonMobileInfo>();
			if(personMobileInfoBeanList!=null){
				for(PersonMobileInfoBean phone : personMobileInfoBeanList){
					PersonMobileInfo personMobileInfo = new PersonMobileInfo();
					BeanUtils.copyProperties(phone, personMobileInfo);
					personMobileInfoList.add(personMobileInfo);
				}
			}
			personExecuteControlService.savePersonExecuteControl(control, personMobileInfoList);
			if(fileBeanList!=null){
				for(FileObjectBean file : fileBeanList){
					Attachment att = attachmentCustomizedService.findById(file.getId());
					AttachmentMeta meta = att.getAttachmentMeta();
					List<AttachmentCopy> copys = meta.getAttachmentCopies();
					InputStream in = copys.get(0).getInputStream();
//					Attachment newAtt = new Attachment();
//					attachmentCustomizedService.create(newAtt, att.getName(), in, control.getId(), control.getClass().getName());
					PersonControlImg.getAlreadyPersistedInstance(control.getId(), att.getId(), att.getName(), in);
				}
			}
		}else{  //修改保存
			control = personExecuteControlService.findById(personExecuteControlBean.getId());
			control.setNote(personExecuteControlBean.getNote());
			control.setOperateStatus(Constant.CZZT_DSP);
			control.setStartTime(DateFmtUtil.longToDate(personExecuteControlBean.getStartTime()));
			control.setEndTime(DateFmtUtil.longToDate(personExecuteControlBean.getEndTime()));
			control.setLastModifyPerson(findCurrentPerson().getName());
			control.setLastModifyTime(new Date());
			personExecuteControlService.update(control);
		}
		saveOperationRecord(control,"","提交审批");
		return SUCCESS;
	}
	public String findPersonExecuteControlResult(){
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("personExecuteControlId", id);
		Pager<DefaultSurveilListResult> pager = personExecuteControlResultService.findPersonExecuteControlResultByPage(id, this.getStart() / this.getLength(), this.getLength());
		List<PersonExecuteControlResultBean> personExecuteControlResultBeanList = new ArrayList<PersonExecuteControlResultBean>();
		for(DefaultSurveilListResult result : pager.getPageList()){
			PersonExecuteControlResultBean bean = new PersonExecuteControlResultBean();
			BeanUtils.copyProperties(result, bean);
			bean.setCatchTime(DateFmtUtil.dateToLong(result.getCatchTime())); //将data格式转换成long
//			if(result.getCatchObject().equals(DefaultSurveilListResult.CATCH_OBJECT_IMAGE)){
//				List<String> attIds = new ArrayList<String>();
//				List<Attachment> attList = attachmentCustomizedService.findByTargetIdAndType(result.getId(), result.getClass().getName());
//				for(Attachment att :attList){
//					attIds.add(att.getId());
//				}
//				bean.setAttachmentId(attIds);
//			}
//			List<String> attIds = new ArrayList<String>();
//			attIds.add(result.getCatchImgResult().getId());
			
			InputStream imgin = null;
			try{
				imgin = result.getCatchImgResult().getAttachmentCopies().get(0).getInputStream() ;
				bean.setImgBase64(FileFmtUtils.inputStreamToBase64String(imgin, false));
				//bean.setImgBase64("iVBORw0KGgoAAAANSUhEUgAAAAEAAAAkCAYAAABIdFAMAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAHhJREFUeNo8zjsOxCAMBFB/KEAUFFR0Cbng3nQPw68ArZdAlOZppPFIBhH5EAB8b+Tlt9MYQ6i1BuqFaq1CKSVcxZ2Acs6406KUgpt5/LCKuVgz5BDCSb13ZO99ZOdcZGvt4mJjzMVKqcha68iIePB86GAiOv8CDADlIUQBs7MD3wAAAABJRU5ErkJggg%3D%3D");
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}finally {
				try {
					if(imgin!=null){
						imgin.close();
					}
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
			
			personExecuteControlResultBeanList.add(bean);
		}
		personExecuteControlResultBeanPager = new Pager<PersonExecuteControlResultBean>();
		personExecuteControlResultBeanPager.setPageList(personExecuteControlResultBeanList);
		personExecuteControlResultBeanPager.setTotalNum(pager.getTotalNum());
		return SUCCESS;
	}
	public String stopPersonExecuteControl(){
		PersonExecuteControl control = personExecuteControlService.findById(id);
		control.setStatus(Constant.DISABLED);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		control.setNote(control.getNote()+" "+sdf.format(new Date())+" 停止布控");
		control.setLastModifyPerson(findCurrentPerson().getName());
		control.setLastModifyTime(new Date());
		personExecuteControlService.updatePersonExecuteControlStatus(control);
		return SUCCESS;
	}
	
	public String approvePersonExecuteControl(){
		PersonExecuteControl control = personExecuteControlService.findById(id);
		saveOperationRecord(control,approveContent,isPass?"审批通过":"审批驳回");
		control.setOperateStatus(isPass?Constant.CZZT_SPTG:Constant.CZZT_SPBH);
		personExecuteControlService.updatePersonExecuteControlStatus(control);
		return SUCCESS;
	}
	public String findOperationRecord(){
		List<OperationRecord> records = personExecuteControlService.findOperationRecordByPersonExecuteControl(id);
		operationRecordBeanList = new ArrayList<OperationRecordBean>();
		for(OperationRecord record : records){
			OperationRecordBean bean = modelBeanTransformUtil.transformOperationRecordBean(record);
			operationRecordBeanList.add(bean);
		}
		return SUCCESS;
	}
	
	public  String  saveIgnore(){
		isPass =false;
		isPass=personExecuteControlResultService.deleteExecuteControlResultStatus(id);
		return SUCCESS;
	}
	
	public  String  saveConfirm(){
		isPass =false;
		isPass=personExecuteControlResultService.saveExecuteControlResultStatus(id,"1");
		return SUCCESS;
	}
	
	
	public void saveOperationRecord(PersonExecuteControl control,String content,String result){
		OperationRecord record = new OperationRecord(control);
		record.setOperateTime(new Date());
		record.setContent(content);
		record.setResult(result);
		record.setOperateUnit(findCurrentOrganization().getId());
		record.setOperator(findCurrentPerson().getId());
		operationRecordService.saveOperationRecord(record);
	}
	public PersonExecuteControlBean transformPersonExecuteControlBean(PersonExecuteControl model){
		PersonExecuteControlBean bean = new PersonExecuteControlBean();
		BeanUtils.copyProperties(model, bean);
		bean.setStatusName(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.ZT, model.getStatus()));
		bean.setSexName(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.XB, model.getSex()));
		bean.setOperateStatusName(modelBeanTransformUtil.findDictionaryItemNameByCode(Constant.CZZT, model.getOperateStatus()));
		bean.setStartTime(DateFmtUtil.dateToLong(model.getStartTime()));
		bean.setEndTime(DateFmtUtil.dateToLong(model.getEndTime()));
		bean.setLastModifyTime(DateFmtUtil.dateToLong(model.getLastModifyTime()));
//		findOperationRecord();
//		OperationRecordBean record = operationRecordBeanList.get(0);
//		bean.setApproveContent(record.getContent());
//		bean.setApprovePeople(record.getOperatorName());
//		bean.setApproveTime(record.getOperateTime());
		return bean;
	}
	
	public String generateExecuteControlSnapshot(){
		
		System.out.println(snapshotInfo);
		System.out.println("1111111");
		
		InfoSnapshot infoSnapshot = new InfoSnapshot();
		infoSnapshot.setType(HighriskPerson.class.getName());
		infoSnapshot.setCode(idCode);
		infoSnapshot.setSnapshot(snapshotInfo);
		System.out.println(snapshotInfo.length());
		Date createdDate = new Date();
		infoSnapshot.setCreatedDate(createdDate);
		infoSnapshotService.saveInfoSnapshot(infoSnapshot);
		return SUCCESS;
	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean getIsShowInvalid() {
		return isShowInvalid;
	}
	public void setIsShowInvalid(boolean isShowInvalid) {
		this.isShowInvalid = isShowInvalid;
	}
	public Pager<PersonExecuteControlBean> getPersonExecuteControlBeanPager() {
		return personExecuteControlBeanPager;
	}
	public void setPersonExecuteControlBeanPager(
			Pager<PersonExecuteControlBean> personExecuteControlBeanPager) {
		this.personExecuteControlBeanPager = personExecuteControlBeanPager;
	}
	public PersonExecuteControlBean getPersonExecuteControlBean() {
		return personExecuteControlBean;
	}
	public void setPersonExecuteControlBean(
			PersonExecuteControlBean personExecuteControlBean) {
		this.personExecuteControlBean = personExecuteControlBean;
	}
	public List<FileObjectBean> getFileBeanList() {
		return fileBeanList;
	}
	public void setFileBeanList(List<FileObjectBean> fileBeanList) {
		this.fileBeanList = fileBeanList;
	}
	public List<PersonMobileInfoBean> getPersonMobileInfoBeanList() {
		return personMobileInfoBeanList;
	}
	public void setPersonMobileInfoBeanList(
			List<PersonMobileInfoBean> personMobileInfoBeanList) {
		this.personMobileInfoBeanList = personMobileInfoBeanList;
	}
	public boolean getIsPass() {
		return isPass;
	}
	public void setIsPass(boolean isPass) {
		this.isPass = isPass;
	}
	public String getApproveContent() {
		return approveContent;
	}
	public void setApproveContent(String approveContent) {
		this.approveContent = approveContent;
	}
	public List<OperationRecordBean> getOperationRecordBeanList() {
		return operationRecordBeanList;
	}
	public void setOperationRecordBeanList(
			List<OperationRecordBean> operationRecordBeanList) {
		this.operationRecordBeanList = operationRecordBeanList;
	}
	public Pager<PersonExecuteControlResultBean> getPersonExecuteControlResultBeanPager() {
		return personExecuteControlResultBeanPager;
	}
	public void setPersonExecuteControlResultBeanPager(
			Pager<PersonExecuteControlResultBean> personExecuteControlResultBeanPager) {
		this.personExecuteControlResultBeanPager = personExecuteControlResultBeanPager;
	}
	public String getSnapshotInfo() {
		return snapshotInfo;
	}
	public void setSnapshotInfo(String snapshotInfo) {
		this.snapshotInfo = snapshotInfo;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getBkNum() {
		return bkNum;
	}

	public void setBkNum(String bkNum) {
		this.bkNum = bkNum;
	}
	
}
