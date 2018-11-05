package com.taiji.pubsec.szpt.highRiskPersonAlert.action.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.HighriskPersonBean;
import com.taiji.pubsec.szpt.highRiskPersonAlert.action.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskCriminalRecord;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HrpScoreResult;
import com.taiji.pubsec.szpt.highriskpersonalert.service.HighriskPersonScoreAnalyzeService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.util.DateFmtUtil;

/**
 * model和bean互转类
 *
 */
@Component
public class ModelBeanTransformUtilInHighRiskPerson{
	
	@Resource
	private IDictionaryItemService dictionaryItemService;// 字典项接口
	
	@Resource
	private IUnitService unitService;
	
	@Resource
	private HighriskPersonScoreAnalyzeService highriskPersonScoreAnalyzeService;	//人员积分查询
	
	@Resource
	private IPersonService personService;
	public String findDictionaryItemNameByCode(String typeCode , String code) {
		if (code != null && !code.isEmpty()) {
			DictionaryItem item = dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(typeCode, code, Constant.ENABLED);
			if (item != null) {
				return item.getName();
			}
		}
		return "";
	}
	public HighriskPersonBean transformHighriskPersonBean(HighriskPerson model) {
		if (model == null) {
			return null;
		}
		//查询高危人积分
		HrpScoreResult hrpScoreResult = highriskPersonScoreAnalyzeService.findHPersonScoreDetail(model.getId());
		HighriskPersonBean bean = new HighriskPersonBean();
		BeanUtils.copyProperties(model, bean);
		if(hrpScoreResult!=null){
			bean.setAccumulatePoints(hrpScoreResult.getTotalScore());
		}else{
			bean.setAccumulatePoints(0d);
		}
		bean.setCreatedTime(DateFmtUtil.dateToLong(model.getCreatedTime()));
		bean.setFeedbackTime(DateFmtUtil.dateToLong(model.getFeedbackTime()));
		bean.setSexName(findDictionaryItemNameByCode(Constant.XB,model.getSex()));
		bean.setWarnTypeName(findDictionaryItemNameByCode(Constant.YJLX,model.getWarnType()));
		bean.setOperateStatusName(findDictionaryItemNameByCode(Constant.CZZT,model.getOperateStatus()));
		
		
		StringBuffer typeNames = new StringBuffer();
		StringBuffer recordNames = new StringBuffer();
		for(HighriskPeopleType type : model.getHighriskPeopleTypes()){
			typeNames.append(findDictionaryItemNameByCode(Constant.RYLX, type.getPeopleType())).append("、");
			for(HighriskCriminalRecord record : type.getHighriskCriminalRecords()){
				recordNames.append(findDictionaryItemNameByCode(Constant.QKLX,record.getCriminalRecord())).append("、");
			}
		}
		bean.setPeopleTypeName(typeNames.length()>0?typeNames.substring(0,typeNames.length()-1).toString():"");
		bean.setPeopleType(recordNames.length()>0?recordNames.substring(0,recordNames.length()-1).toString():"");
		bean.setName(null == model.getName() ? "" : model.getName());
		bean.setUsedName(null == model.getUsedName() ? "" : model.getUsedName());
		bean.setNickname(null == model.getNickname() ? "" : model.getNickname());
		bean.setSexName(null == model.getSex() ? "未知" : findDictionaryItemNameByCode(Constant.XB,model.getSex()));
		bean.setEthnicGroup(null == model.getEthnicGroup() ? "" : findDictionaryItemNameByCode(Constant.MZ,model.getEthnicGroup()));
		bean.setBirthday(null == model.getBirthday() ? 0 : DateFmtUtil.dateToLong(model.getBirthday()));
		bean.setEducationName(null == model.getEducation() ? "" : findDictionaryItemNameByCode(Constant.XL,model.getEducation()));
		bean.setRegisterAddress(null == model.getRegisterAddress() ? "" : model.getRegisterAddress());
		bean.setLiveAddress(null == model.getLiveAddress() ? "" : model.getLiveAddress());
		bean.setMarriageStatusName(null == model.getMarriageStatus() ? "未知" : findDictionaryItemNameByCode(Constant.HYQK,model.getMarriageStatus()));
		bean.setEmploymentStatusName(null == model.getEmploymentStatus() ? "未知" : findDictionaryItemNameByCode(Constant.JYQK,model.getEmploymentStatus()));
		bean.setProfessionName(null == model.getProfession() ? "" : findDictionaryItemNameByCode(Constant.ZY,model.getProfession()));
		bean.setAddReason(null == model.getAddReason() ? "" : model.getAddReason());
		if(model.getIncome() != null && !(model.getIncome().toString()).isEmpty()){
			bean.setIncome(model.getIncome().toString());
		}else{
			bean.setIncome("");
		}
		bean.setWorkAddress(null == model.getWorkAddress() ? "" : model.getWorkAddress());
		List<String> phone = new ArrayList<String>();
		List<String> mac = new ArrayList<String>();
		for(MobilePhoneInfo mobile : model.getMobilePhoneInfos()){
			phone.add(StringUtils.isEmpty(mobile.getNumber())?"":mobile.getNumber());
			mac.add(StringUtils.isEmpty(mobile.getMac())?"":mobile.getMac());
		}
		bean.setStatusName("0".equals(model.getStatus())?"正常":"0".equals(model.getStatus())?"异常":"");
		bean.setMac(mac);
		bean.setPhone(phone);	
		bean.setAddReason("");
		bean.setPersonInControlTypeName(null == model.getPersonInControlType() ? "" : findDictionaryItemNameByCode(Constant.ZKLX,model.getPersonInControlType()));
		return bean;
	}
	/**
	 * 操作记录bean转操作记录model
	 * @param model 操作记录bean
	 * @return 操作记录model
	 */
	public OperationRecordBean transformOperationRecordBean(OperationRecord model){
		OperationRecordBean bean = new OperationRecordBean();
		BeanUtils.copyProperties(model, bean);
		bean.setOperateTime(DateFmtUtil.dateToLong(model.getOperateTime()));
		Unit unit = unitService.findById(model.getOperateUnit());
		if(unit!=null){
			bean.setOperateUnitName(unit.getName());
		}
		Person person = personService.findById(model.getOperator());
		if(person!=null){
			bean.setOperatorName(person.getName());
		}
		return bean;
	}
}
