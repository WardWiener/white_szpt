package com.taiji.pubsec.szpt.highriskpersonalert.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.taiji.pubsec.szpt.highriskpersonalert.service.aop.CreateHighriskPersonMarkHandler;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryType;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryTypeService;
import com.taiji.pubsec.common.tools.aophandler.annotation.AopAnno;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.attachment.model.Attachment;
import com.taiji.pubsec.szpt.attachment.service.IAttachmentCustomizedService;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskCriminalRecord;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPeopleType;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPersonHistoryInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.model.MobilePhoneInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.StatisticsInfo;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonHistoryInfoService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IHighriskPersonService;
import com.taiji.pubsec.szpt.highriskpersonalert.service.aop.HighRiskPersonSaveOrUpdateHandler;
import com.taiji.pubsec.szpt.highriskpersonalert.util.HighriskPersonConstant;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.service.IOperationRecordService;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("highriskPersonService")
public class HighriskPersonServiceImpl implements IHighriskPersonService {
	
	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	@Resource
	private IDictionaryTypeService dictionaryTypeService;
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@Resource
	private IHighriskPersonHistoryInfoService highriskPersonHistoryInfoService;
	
	@Resource
	private IOperationRecordService operationRecordService;
	
	@Resource
	private IAttachmentCustomizedService attachmentCustomizedService;

	@SuppressWarnings("unchecked")
	@Override
	public Pager<HighriskPerson> findByCondition(Map<String, Object> paramMap, int pageNo, int pageSize) {
		StringBuilder xqlSelect  = new StringBuilder("select distinct h from HighriskPerson as h");
		String xqlWhere = " where 1 = 1";
		StringBuilder xqlCondition = new StringBuilder();
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		if(ParamMapUtil.isNotBlank(paramMap.get("name"))) {
			xqlCondition.append(" and h.name like :name");
			SQLTool.SQLAddEscape(xqlCondition);
			xqlMap.put("name", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("name")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("idcode"))) {
			xqlCondition.append(" and h.idcode like :idcode");
			SQLTool.SQLAddEscape(xqlCondition);
			xqlMap.put("idcode", "%" + SQLTool.SQLSpecialChTranfer((String)paramMap.get("idcode")) + "%");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("warningTypeList"))) {
			xqlCondition.append(" and h.warnType in (:warningTypeList)");
			xqlMap.put("warningTypeList", paramMap.get("warningTypeList"));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("personTypeList"))) {
			xqlSelect.append(" left join h.highriskPeopleTypes as ht");
			List<String> personTypeList = (List<String>) paramMap.get("personTypeList");
			xqlCondition.append(" and (ht.peopleType like :personTypeList0 ");
			SQLTool.SQLAddEscape(xqlCondition);
			xqlMap.put("personTypeList0", SQLTool.SQLSpecialChTranfer(personTypeList.get(0)) + "%");
			for(int i = 1; i < personTypeList.size(); i++){
				xqlCondition.append(" or ht.peopleType like :personTypeList" + i);
				SQLTool.SQLAddEscape(xqlCondition);
				xqlMap.put("personTypeList" + i, SQLTool.SQLSpecialChTranfer(personTypeList.get(i)) + "%");
			}
			xqlCondition.append(" ) ");
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("createTime"))) {
			xqlCondition.append(" and h.createdTime >= :createTime");
			xqlMap.put("createTime", paramMap.get("createTime"));
		}
		if (ParamMapUtil.isNotBlank(paramMap.get("operateStatusList"))) {
			xqlCondition.append(" and h.operateStatus in (:operateStatusList)");
			xqlMap.put("operateStatusList", paramMap.get("operateStatusList"));
		}
		xqlCondition.append(" order by h.createdTime,h.accumulatePoints desc,h.warnType");
		Pager<HighriskPerson> pager = this.dao.findByPage(HighriskPerson.class, xqlSelect.append(xqlWhere).append(xqlCondition).toString(), xqlMap, pageNo, pageSize);
		
		return pager;
	}

	@SuppressWarnings("unchecked")
	@AopAnno(mark=HighRiskPersonSaveOrUpdateHandler.MARK)
	@Override
	public void createHighriskPerson(HighriskPerson highriskPerson) {
		HighriskPerson hp = this.findByIdCode(highriskPerson.getIdcode());
		if(null != hp) {
			hp.setDataNew(HighriskPerson.HIGHRISK_PERSON_DATA_OLD);
			dao.update(hp);
		}
		if(highriskPerson.getCreatedTime() == null){
			highriskPerson.setCreatedTime(new Date());
		}
		if(highriskPerson.getUpdateTime() == null){
			highriskPerson.setUpdateTime(new Date());
		}
		highriskPerson.setDataNew(HighriskPerson.HIGHRISK_PERSON_DATA_NEW);
		dao.save(highriskPerson);
		/*高危人员调整：新增*/
		HighriskPersonHistoryInfo highriskPersonHistoryInfo = new HighriskPersonHistoryInfo(highriskPerson);
		highriskPersonHistoryInfo.setOperateContent(highriskPerson.getWarnType());
		highriskPersonHistoryInfo.setOperateMethod(HighriskPersonConstant.HIGHRISK_PERSON_ADJUSTMENT_ADD);
		highriskPersonHistoryInfo.setOperateTime(new Date());
		highriskPersonHistoryInfo.setOperateType(HighriskPersonConstant.HIGHRISK_PERSON_ADJUSTMENT);
		highriskPersonHistoryInfoService.addHighriskPersonHistoryInfo(highriskPersonHistoryInfo);
		
	}
	
	private void deleteHighriskPerson(HighriskPerson highriskPerson) {
		dao.delete(highriskPerson);
		/*高危人员调整：删除*/
		HighriskPersonHistoryInfo highriskPersonHistoryInfo = new HighriskPersonHistoryInfo(highriskPerson);
		highriskPersonHistoryInfo.setOperateContent(highriskPerson.getWarnType());
		highriskPersonHistoryInfo.setOperateMethod(HighriskPersonConstant.HIGHRISK_PERSON_ADJUSTMENT_DELETE);
		highriskPersonHistoryInfo.setOperateTime(new Date());
		highriskPersonHistoryInfo.setOperateType(HighriskPersonConstant.HIGHRISK_PERSON_ADJUSTMENT);
		highriskPersonHistoryInfoService.addHighriskPersonHistoryInfo(highriskPersonHistoryInfo);
		
		for (MobilePhoneInfo mobilePhoneInfo : highriskPerson.getMobilePhoneInfos()) {
			dao.delete(mobilePhoneInfo);
			highriskPerson.getMobilePhoneInfos().remove(mobilePhoneInfo);
		}
		
		for(HighriskPeopleType highriskPeopleType : highriskPerson.getHighriskPeopleTypes()) {
			this.deleteHighriskPeopleType(highriskPeopleType);
			highriskPerson.getHighriskPeopleTypes().remove(highriskPeopleType);
		}
	}

	@SuppressWarnings("unchecked")
	@AopAnno(mark=HighRiskPersonSaveOrUpdateHandler.MARK)
	@Override
	public void updateHighriskPerson(HighriskPerson highriskPerson) {
		this.dao.update(highriskPerson);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticsInfo> sumupHighriskPersonByWarnType() {
		String xql = "select new " + StatisticsInfo.class.getName() + "(h.warnType, count(h.id)) from HighriskPerson as h group by h.warnType order by h.warnType";
		List<StatisticsInfo> list = this.dao.findAllByParams(HighriskPerson.class, xql, new HashMap<String, Object>());
		DictionaryType dicType = this.dictionaryTypeService.findDicTypeByCode(HighriskPersonConstant.HIGHRISK_PERSON_WARNTYPE_CODE);
			for(StatisticsInfo statisticsInfo :list) {
				if (null != dicType) {
					DictionaryItem dicItem = this.dictionaryItemService.findDictionaryItemByDicTypeAndItemCode(dicType.getId(), statisticsInfo.getName(), null);
					if (null != dicItem)
						statisticsInfo.setName(dicItem.getName());
				}
			}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HighriskPerson> findCreatedHighriskLately() {
		String xql = "select h from HighriskPerson as h order by h.createdTime desc";
		return this.dao.findByPage(HighriskPerson.class, xql, new HashMap<String, Object>(), 0, 5).getPageList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public HighriskPerson findByIdCode(String idCode) {
		//String xql = "select h from HighriskPerson as h where h.idcode = :idCode";
		String[] paramValues = {idCode};
		List<HighriskPerson> list = this.dao.findAllByParams(HighriskPerson.class, "select h from HighriskPerson as h where h.idcode = ? ", paramValues);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HighriskPerson findByMac(String mac) {
		String xql = "select distinct mp.highriskPerson from MobilePhoneInfo as mp where mp.mac = :mac";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("mac", mac);
		List<HighriskPerson> list = this.dao.findAllByParams(HighriskPerson.class, xql, xqlMap);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HighriskPerson findByMobile(String telephone) {
		String xql = "select mp.highriskPerson from MobilePhoneInfo as mp where mp.number = :telephone";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("telephone", telephone);
		List<HighriskPerson> list = this.dao.findAllByParams(HighriskPerson.class, xql, xqlMap);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findMacByMobile(String telephone) {
		String xql = "select distinct mp.mac from MobilePhoneInfo as mp where mp.number = :telephone";
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		xqlMap.put("telephone", telephone);
		return this.dao.findAllByParams(MobilePhoneInfo.class, xql, xqlMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findMacsByCondition(Map<String, String> paramMap) {
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		if(ParamMapUtil.isNotBlank(paramMap.get("idcode"))){
			String hql = "select distinct mp.mac from MobilePhoneInfo as mp where mp.highriskPerson.idcode = :idcode";
			Map<String, Object> xqlMap = new HashMap<String, Object>();
			xqlMap.put("idcode", paramMap.get("idcode"));
			List<String> macs = this.dao.findAllByParams(MobilePhoneInfo.class, hql, xqlMap);
			set.addAll(macs);
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("telephone"))) {
			set.addAll(this.findMacByMobile(paramMap.get("telephone")));
		}
		if(ParamMapUtil.isNotBlank(paramMap.get("mac"))) {
			set.add(paramMap.get("mac"));
		}
		list.addAll(set);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createHighriskPersonMark(Map<String, Object> paramMap) {
		HighriskPerson highriskPerson = (HighriskPerson) this.dao.findById(HighriskPerson.class, paramMap.get("highriskPersonId").toString());
		if(paramMap.get("highriskPersonNickname")!=null){
			highriskPerson.setNickname(paramMap.get("highriskPersonNickname").toString());
		}
		if(paramMap.get("personInControlType")!=null){
			highriskPerson.setPersonInControlType(paramMap.get("personInControlType").toString());
		}
		if(paramMap.get("liveAddress")!=null){
			highriskPerson.setLiveAddress(paramMap.get("liveAddress").toString());
		}
		if(paramMap.get("marriageStatus")!=null){
			highriskPerson.setMarriageStatus(paramMap.get("marriageStatus").toString());
		}
		if(paramMap.get("employmentStatus")!=null){
			highriskPerson.setEmploymentStatus(paramMap.get("employmentStatus").toString());
		}
		if(paramMap.get("workAddress")!=null){
			highriskPerson.setWorkAddress(paramMap.get("workAddress").toString());
		}
		if(paramMap.get("profession")!=null){
			highriskPerson.setProfession(paramMap.get("profession").toString());
		}
		if(paramMap.get("income")!=null && !(paramMap.get("income").toString()).isEmpty()){
			highriskPerson.setIncome(Integer.valueOf(paramMap.get("income").toString()));
		}
		if(paramMap.get("name")!=null){
			highriskPerson.setName(paramMap.get("name").toString());
		}
		if(paramMap.get("registerAddress")!=null){
			highriskPerson.setRegisterAddress(paramMap.get("registerAddress").toString());
		}
		if(paramMap.get("warnType")!=null){
			highriskPerson.setWarnType(paramMap.get("warnType").toString());
		}
		if(paramMap.get("usedName")!=null){
			highriskPerson.setUsedName(paramMap.get("usedName").toString());
		}
		if(paramMap.get("ethnicGroup")!=null){
			highriskPerson.setEthnicGroup(paramMap.get("ethnicGroup").toString());
		}
		if(paramMap.get("sex")!=null){
			highriskPerson.setSex(paramMap.get("sex").toString());
		}
		if(paramMap.get("education")!=null){
			highriskPerson.setEducation(paramMap.get("education").toString());
		}
		if(paramMap.get("birthday")!=null){
			highriskPerson.setBirthday((Date)paramMap.get("birthday"));
		}
		if(paramMap.get("operateStatus")!=null){
			highriskPerson.setOperateStatus(paramMap.get("operateStatus").toString());
		}
		if(paramMap.get("addReason")!=null){
			highriskPerson.setAddReason(paramMap.get("addReason").toString());
		}
		if(null!= (paramMap.get("mobilePhoneInfoList"))) {
			List<MobilePhoneInfo> oldMobilePhoneInfos = new ArrayList<>(highriskPerson.getMobilePhoneInfos());
			List<MobilePhoneInfo> newMobilePhoneInfos = (List<MobilePhoneInfo>) paramMap.get("mobilePhoneInfoList");
			
			//to delete 
			Collection<MobilePhoneInfo> substract = CollectionUtils.subtract(oldMobilePhoneInfos, newMobilePhoneInfos);
			for (MobilePhoneInfo mp : substract) {
				this.dao.delete(mp);
				highriskPerson.getMobilePhoneInfos().remove(mp);
			}
			//to update																newMobilePhoneInfos, oldMobilePhoneInfos
			Collection<MobilePhoneInfo> intersection = CollectionUtils.intersection(oldMobilePhoneInfos, newMobilePhoneInfos);
			for (MobilePhoneInfo mp : intersection) {
				this.dao.update(mp);
			}
			//to add
			Collection<MobilePhoneInfo> substractb = CollectionUtils.subtract(newMobilePhoneInfos, oldMobilePhoneInfos);
			for (MobilePhoneInfo mp : substractb) {
				mp.setId(null);
				mp.setHighriskPerson(highriskPerson);
				this.dao.save(mp);
			}
			
		}
		if (null != (paramMap.get("highriskPersonTypeList"))) {
			List<HighriskPeopleType> oldHighriskPeopleTypes = new ArrayList<>(highriskPerson.getHighriskPeopleTypes());
			List<HighriskPeopleType> newHighriskPeopleTypes = (List<HighriskPeopleType>) paramMap.get("highriskPersonTypeList");
			
			//to delete
			Collection<HighriskPeopleType> substract = CollectionUtils.subtract(oldHighriskPeopleTypes, newHighriskPeopleTypes);
			for (HighriskPeopleType hp : substract) {
				this.deleteHighriskPeopleType(hp);
				highriskPerson.getHighriskPeopleTypes().remove(hp);
			}
			 
			//to update                                                                newHighriskPeopleTypes, oldHighriskPeopleTypes
			Collection<HighriskPeopleType> intersection = CollectionUtils.intersection(oldHighriskPeopleTypes, newHighriskPeopleTypes);
			for (HighriskPeopleType hp : intersection) {
				for(HighriskCriminalRecord hcr : hp.getHighriskCriminalRecords()) {
					dao.delete(hcr);
				}
			}
			Collection<HighriskPeopleType> intersectionb = CollectionUtils.intersection(newHighriskPeopleTypes, oldHighriskPeopleTypes);
			for(HighriskPeopleType hp : intersectionb) {
				for (HighriskCriminalRecord hcr : hp.getHighriskCriminalRecords()) {
					for (HighriskPeopleType hpt : intersection) {
						if (hpt.getPeopleType().equals(hp.getPeopleType())) {
							hcr.setHighriskPeopleType(hpt);
						}
					}
					dao.save(hcr);
				}
			}
			
			//to add
			Collection<HighriskPeopleType> substractTypes = CollectionUtils.subtract(newHighriskPeopleTypes, oldHighriskPeopleTypes);
			for(HighriskPeopleType hc : substractTypes) {
				hc.setHighriskPerson(highriskPerson);
				this.saveHighriskPeopleType(hc);
				for(HighriskCriminalRecord hcr : hc.getHighriskCriminalRecords()) {
					hcr.setHighriskPeopleType(hc);
					this.dao.save(hcr);
				}
			}
		
		}
		
		this.dao.update(highriskPerson);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public HighriskPerson findHighriskPersonById(String id) {
		return (HighriskPerson) dao.findById(HighriskPerson.class, id);
	}

	@Override
	public void createHighriskPersonForMobile(Map<String, Object> paramMap) {
		HighriskPerson highriskPerson = (HighriskPerson) paramMap.get("highriskPerson");
		this.createHighriskPerson(highriskPerson);
		List<MobilePhoneInfo> mobilePhoneInfoList = (List<MobilePhoneInfo>) paramMap.get("mobilePhoneInfoList");
		if(ParamMapUtil.isNotBlank(mobilePhoneInfoList)) {
			for (MobilePhoneInfo mobilePhoneInfo : mobilePhoneInfoList) {
				mobilePhoneInfo.setHighriskPerson(highriskPerson);
				dao.save(mobilePhoneInfo);
			}
		}
		List<HighriskPeopleType>  highriskPeopleTypeList = (List<HighriskPeopleType>) paramMap.get("highriskPeopleTypeList");
		if(ParamMapUtil.isNotBlank(highriskPeopleTypeList)) {
			for (HighriskPeopleType highriskPeopleType : highriskPeopleTypeList) {
				highriskPeopleType.setHighriskPerson(highriskPerson);
				this.saveHighriskPeopleType(highriskPeopleType);
				for(HighriskCriminalRecord highriskCriminalRecord : highriskPeopleType.getHighriskCriminalRecords()) {
					highriskCriminalRecord.setHighriskPeopleType(highriskPeopleType);
					dao.save(highriskCriminalRecord);
				}
			}
		}
	}

	@Override
	public void updateHighriskPersonForMobile(Map<String, Object> paramMap) {
		HighriskPerson highriskPerson = (HighriskPerson) paramMap.get("highriskPerson");
		if(null!= (paramMap.get("mobilePhoneInfoList"))) {
			List<MobilePhoneInfo> oldMobilePhoneInfos = new ArrayList<>(highriskPerson.getMobilePhoneInfos());
			List<MobilePhoneInfo> newMobilePhoneInfos = (List<MobilePhoneInfo>) paramMap.get("mobilePhoneInfoList");
			
			//to delete 
			Collection<MobilePhoneInfo> substract = CollectionUtils.subtract(oldMobilePhoneInfos, newMobilePhoneInfos);
			for (MobilePhoneInfo mp : substract) {
				this.dao.delete(mp);
				highriskPerson.getMobilePhoneInfos().remove(mp);
			}
			//to update																newMobilePhoneInfos, oldMobilePhoneInfos
			Collection<MobilePhoneInfo> intersection = CollectionUtils.intersection(oldMobilePhoneInfos, newMobilePhoneInfos);
			for (MobilePhoneInfo mp : intersection) {
				this.dao.update(mp);
			}
			//to add
			Collection<MobilePhoneInfo> substractb = CollectionUtils.subtract(newMobilePhoneInfos, oldMobilePhoneInfos);
			for (MobilePhoneInfo mp : substractb) {
				mp.setId(null);
				mp.setHighriskPerson(highriskPerson);
				this.dao.save(mp);
			}
			
		}
		if (null != (paramMap.get("highriskPeopleTypeList"))) {
			List<HighriskPeopleType> oldHighriskPeopleTypes = new ArrayList<>(highriskPerson.getHighriskPeopleTypes());
			List<HighriskPeopleType> newHighriskPeopleTypes = (List<HighriskPeopleType>) paramMap.get("highriskPeopleTypeList");
			
			//to delete
			Collection<HighriskPeopleType> substract = CollectionUtils.subtract(oldHighriskPeopleTypes, newHighriskPeopleTypes);
			for (HighriskPeopleType hp : substract) {
				this.deleteHighriskPeopleType(hp);
				highriskPerson.getHighriskPeopleTypes().remove(hp);
			}
			 
			//to update                                                                newHighriskPeopleTypes, oldHighriskPeopleTypes
			Collection<HighriskPeopleType> intersection = CollectionUtils.intersection(oldHighriskPeopleTypes, newHighriskPeopleTypes);
			for (HighriskPeopleType hp : intersection) {
				for(HighriskCriminalRecord hcr : hp.getHighriskCriminalRecords()) {
					dao.delete(hcr);
				}
			}
			Collection<HighriskPeopleType> intersectionb = CollectionUtils.intersection(newHighriskPeopleTypes, oldHighriskPeopleTypes);
			for(HighriskPeopleType hp : intersectionb) {
				for (HighriskCriminalRecord hcr : hp.getHighriskCriminalRecords()) {
					for (HighriskPeopleType hpt : intersection) {
						if (hpt.getPeopleType().equals(hp.getPeopleType())) {
							hcr.setHighriskPeopleType(hpt);
						}
					}
					dao.save(hcr);
				}
			}
			
			//to add
			Collection<HighriskPeopleType> substractTypes = CollectionUtils.subtract(newHighriskPeopleTypes, oldHighriskPeopleTypes);
			for(HighriskPeopleType hc : substractTypes) {
				hc.setHighriskPerson(highriskPerson);
				this.saveHighriskPeopleType(hc);
				for(HighriskCriminalRecord hcr : hc.getHighriskCriminalRecords()) {
					hcr.setHighriskPeopleType(hc);
					this.dao.save(hcr);
				}
			}
		
		}
		
		if(ParamMapUtil.isNotBlank(paramMap.get("attachId"))){
			Attachment attachment = attachmentCustomizedService.findById((String) paramMap.get("attachId"));
			attachment.setTargetId(highriskPerson.getId());
			attachment.setType(HighriskPerson.class.getName());
			dao.update(attachment);
		}
		
		if(highriskPerson.getUpdateTime() == null){
			highriskPerson.setUpdateTime(new Date());
		}
		this.dao.update(highriskPerson);
		
	}
	
	private void saveHighriskPeopleType(HighriskPeopleType highriskPeopleType) {
		dao.save(highriskPeopleType);
		/*添加人员类型调整历史记录:新增*/
		HighriskPersonHistoryInfo highriskPersonHistoryInfo = new HighriskPersonHistoryInfo(highriskPeopleType);
		highriskPersonHistoryInfo.setOperateContent(highriskPeopleType.getPeopleType());
		highriskPersonHistoryInfo.setOperateMethod(HighriskPersonConstant.PERSON_TYPE_ADJUSTMENT_ADD);
		highriskPersonHistoryInfo.setOperateTime(new Date());
		highriskPersonHistoryInfo.setOperateType(HighriskPersonConstant.PERSON_TYPE_ADJUSTMENT);
		highriskPersonHistoryInfoService.addHighriskPersonHistoryInfo(highriskPersonHistoryInfo);
	}
	
	private void deleteHighriskPeopleType(HighriskPeopleType highriskPeopleType) {
		for (HighriskCriminalRecord highriskCriminalRecord : highriskPeopleType.getHighriskCriminalRecords()) {
			dao.delete(highriskCriminalRecord);
//			highriskPeopleType.getHighriskCriminalRecords().remove(highriskCriminalRecord);
			
		}
		dao.delete(highriskPeopleType);
		/*添加人员类型调整历史记录:删除*/
		HighriskPersonHistoryInfo highriskPersonHistoryInfo = new HighriskPersonHistoryInfo(highriskPeopleType);
		highriskPersonHistoryInfo.setOperateContent(highriskPeopleType.getPeopleType());
		highriskPersonHistoryInfo.setOperateMethod(HighriskPersonConstant.PERSON_TYPE_ADJUSTMENT_DELETE);
		highriskPersonHistoryInfo.setOperateTime(new Date());
		highriskPersonHistoryInfo.setOperateType(HighriskPersonConstant.PERSON_TYPE_ADJUSTMENT);
		highriskPersonHistoryInfoService.addHighriskPersonHistoryInfo(highriskPersonHistoryInfo);
	}
	
	@Override
	public String saveOperationRecord(OperationRecord operationRecord) {
		return operationRecordService.saveOperationRecord(operationRecord);
	}
	
	@Override
	public List<OperationRecord> findOperationRecordByHighrishPeople(String highriskPersonId) {
		return operationRecordService.findOperationRecordByTarget(highriskPersonId, HighriskPerson.class.getName(), null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HighriskPerson findHighriskPersonByIdCardNo(String idCardNo) {
		String xql = "select p from HighriskPerson as p where p.idcode = ?";
		return (HighriskPerson) dao.findByParams(HighriskPerson.class, xql, new Object[]{idCardNo});
	}

}
