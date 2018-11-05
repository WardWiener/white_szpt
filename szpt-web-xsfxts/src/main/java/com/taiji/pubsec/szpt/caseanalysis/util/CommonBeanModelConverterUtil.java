package com.taiji.pubsec.szpt.caseanalysis.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import com.taiji.pubsec.szpt.caseanalysis.score.model.RobberyTheftCaseScoreTemplate;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreTemplateService;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.ArchivedFileBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CaseTagBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CrimialCaseNoteBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalBasicCaseBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.action.bean.CriminalObjectBean;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ArchivedFile;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseFeatureTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CrimialCaseNote;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalPerson;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.ImpoundedObject;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseTagService;
import com.taiji.pubsec.szpt.common.model.Community;
import com.taiji.pubsec.szpt.operationrecord.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.service.ICommunityService;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

/**
 * 公用的Bean和Model互转工具类
 * 
 * @author WangLei
 *
 */
@Component
public class CommonBeanModelConverterUtil extends PageCommonAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ICommunityService communityService;// og村居接口
	
	@Resource
	private CaseService caseService;// 案件接口
	
	@Resource
	private CaseTagService caseTagService;// 案件打标接口
	
	@Resource
	private ScoreTemplateService scoreTemplateService;// 打分模版接口

	/**
	 * 案件基本信息Model 转 Bean
	 * 
	 * @param cbc
	 * @return
	 */
	public CriminalBasicCaseBean criminalBasicCaseToCriminalBasicCaseBean(CriminalBasicCase cbc){
		if(cbc == null){
			return null;
		}
		CriminalBasicCaseBean cbcb = new CriminalBasicCaseBean();
		//设置相同类型且相同名称的字段
		BeanCopier copier = BeanCopier.create(cbc.getClass(),cbcb.getClass(), false);
		copier.copy(cbc, cbcb, null);
		//设置不同类型或不同名称的字段
		cbcb.setLossValue(cbc.getLossValue() == null ? null : String.valueOf(cbc.getLossValue()));
		cbcb.setInjuredCount(cbc.getInjuredCount() == null ? null : String.valueOf(cbc.getInjuredCount()));
		cbcb.setDeadCount(cbc.getDeadCount() == null ? null : String.valueOf(cbc.getDeadCount()));
		cbcb.setDiscoverTimeStart(DateFmtUtil.dateToLong(cbc.getDiscoverTimeStart()));
		cbcb.setDiscoverTimeEnd(DateFmtUtil.dateToLong(cbc.getDiscoverTimeEnd()));
		cbcb.setCaseTimeStart(DateFmtUtil.dateToLong(cbc.getCaseTimeStart()));
		cbcb.setCaseTimeEnd(DateFmtUtil.dateToLong(cbc.getCaseTimeEnd()));
		cbcb.setInputTime(DateFmtUtil.dateToLong(cbc.getInputTime()));
		cbcb.setModifiedTime(DateFmtUtil.dateToLong(cbc.getModifiedTime()));
		cbcb.setConfirmDate(DateFmtUtil.dateToLong(cbc.getConfirmDate()));
		cbcb.setCaseTagId(null);
		if(cbc.getCaseTag() != null){
			cbcb.setCaseTagId(cbc.getCaseTag().getId());
		}
		cbcb.setScoreTemplateName("");
		RobberyTheftCaseScoreTemplate rtcst = scoreTemplateService.findMatchedTemplate(cbc.getCaseCode());
		if(rtcst != null){
			cbcb.setScoreTemplateName(rtcst.getName());
		}
		return cbcb;
	}
	
	/**
	 * 案件打标Bean 转 Model
	 * 
	 * @param mark
	 * @return
	 */
	public CaseTag caseTagBeanToCaseTag(CaseTagBean ctb){
		if(ctb == null){
			return null;
		}
		CaseTag ct = null;
		if(StringUtils.isBlank(ctb.getId()) || "null".equals(ctb.getId())){
			ct = new CaseTag();
			//设置相同类型且相同名称的字段
			BeanCopier copier = BeanCopier.create(ctb.getClass(),ct.getClass(), false);
			copier.copy(ctb, ct, null);
			ct.setId(null);
			ct.setCreateTime(new Date());
			ct.setCreatePersonId(this.findCurrentPerson().getId());
		}else{
			ct = caseTagService.findCaseTag(ctb.getCaseCode());
			ct.setId(ctb.getId());
			ct.setLevel(ctb.getLevel());
			ct.setType(ctb.getType());
			ct.setFirstSort(ctb.getFirstSort());
			ct.setSecondSort(ctb.getSecondSort());
			ct.setLongitude(ctb.getLongitude());
			ct.setLatitude(ctb.getLatitude());
			ct.setAddress(ctb.getAddress());
			ct.setCommunity(ctb.getCommunity());
			ct.setPlaceType(ctb.getPlaceType());
			ct.setPlaceName(ctb.getPlaceName());
			ct.setOccurPlace(ctb.getOccurPlace());
			ct.setEntrance(ctb.getEntrance());
			ct.setExit(ctb.getExit());
			ct.setPeopleNum(ctb.getPeopleNum());
			ct.setPeriod(ctb.getPeriod());
		}
		ct.setUpdatePersonId(this.findCurrentPerson().getId());
		ct.setUpdateTime(new Date());
		return ct;
	}
	
	/**
	 * 案件打标Model 转　Bean
	 * 
	 * @param ct
	 * @return
	 */
	public CaseTagBean caseTagToCaseTagBean(CaseTag ct){
		if(ct == null){
			return null;
		}
		CaseTagBean ctb = new CaseTagBean();
		BeanCopier copier = BeanCopier.create(ct.getClass(), ctb.getClass(), false);
		copier.copy(ct, ctb, null);
		
		//字典项名称查询
		ctb.setLevelName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_AJJB.getValue(), ctb.getLevel()));
		ctb.setTypeName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_AJXZ.getValue(), ctb.getType()));;
		ctb.setFirstSortName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_AJXZ.getValue(), ctb.getFirstSort()));
		ctb.setSecondSortName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_AJXZ.getValue(), ctb.getSecondSort()));
		ctb.setPlaceTypeName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_SACS.getValue(), ctb.getPlaceType()));
		ctb.setOccurPlaceName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_FACS.getValue(), ctb.getOccurPlace()));
		ctb.setEntranceName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_ZACRK.getValue(), ctb.getEntrance()));
		ctb.setExitName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_ZACRK.getValue(), ctb.getExit()));
		ctb.setPeopleNumName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_ZARS.getValue(), ctb.getPeopleNum()));
		ctb.setPeriodName(this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_ZATIME.getValue(), ctb.getPeriod()));
		//村居
		Community community = communityService.findCommunityByCode(ctb.getCommunity());
		ctb.setCommunityName("");
		if(community != null){
			ctb.setCommunityName(community.getName());
		}
		//作案特点
		String featureNames = "";
		List<String> featureCodes = new ArrayList<String>();
		List<CaseFeatureTag> cfts = new ArrayList(ct.getFeatures());
		Collections.sort(cfts);
		for(int i=0;i<cfts.size();i++){
			featureCodes.add(cfts.get(i).getFeature());
			featureNames += this.findDictNameByDictTypeCodeAndDictItemCode(DICT.DICT_TYPE_ZATD.getValue(), cfts.get(i).getFeature());
			if(i < cfts.size() - 1){
				featureNames += "、";
			}
		}
		ctb.setFeatureCodes(featureCodes);
		ctb.setFeatureNames(featureNames);
		ctb.setCreateTime(DateFmtUtil.dateToLong(ct.getCreateTime()));
		ctb.setUpdateTime(DateFmtUtil.dateToLong(ct.getUpdateTime()));
		
		//设置关联案件信息
		CriminalBasicCase cbc = ct.getBasicCase();
		if(cbc != null){
			ctb.setCaseCode(cbc.getCaseCode());
			ctb.setCaseName(cbc.getCaseName());
			ctb.setCaseTimeEnd(cbc.getCaseTimeEnd() == null ? null : cbc.getCaseTimeEnd().getTime());
			ctb.setCaseTimeStart(cbc.getCaseTimeStart() == null ? null : cbc.getCaseTimeStart().getTime());
		}
		return ctb;
	}
	
	/**
	 * 涉案物品Model 转 Bean
	 * 
	 * @param co
	 * @return
	 */
	public CriminalObjectBean criminalObjectToCriminalObjectBean(CriminalObject co){
		if(co == null){
			return null;
		}
		CriminalObjectBean cob = new CriminalObjectBean();
		BeanCopier copier = BeanCopier.create(co.getClass(), cob.getClass(), false);
		copier.copy(co, cob, null);
		cob.setPurchaseDate(DateFmtUtil.dateToLong(co.getPurchaseDate()));
		cob.setInputTime(DateFmtUtil.dateToLong(co.getInputTime()));
		cob.setModifiedTime(DateFmtUtil.dateToLong(co.getModifiedTime()));
		cob.setCaseCode(co.getBasicCase().getCaseCode());
		return cob;
	}
	
	/**
	 * 案件管理涉案物品Model 转 Bean
	 */
	public CriminalObjectBean impoundedObjectToCriminalObjectBean(ImpoundedObject io){
		if(io == null){
			return null;
		}
		CriminalObjectBean cob = new CriminalObjectBean();
		BeanCopier copier = BeanCopier.create(io.getClass(), cob.getClass(), false);
		copier.copy(io, cob, null);
		cob.setCaseCode(io.getBasicCase().getCaseCode());
		return cob;
	}
	
	/**
	 * 全局操作记录Model 转 Bean
	 * 
	 * @param or
	 * @return
	 */
	public OperationRecordBean operationRecordToOperationRecordBean(OperationRecord or){
		if(or == null){
			return null;
		}
		OperationRecordBean orb = new OperationRecordBean();
		BeanCopier copier = BeanCopier.create(or.getClass(), orb.getClass(), false);
		copier.copy(or, orb, null);
		orb.setOperateTime(DateFmtUtil.dateToLong(or.getOperateTime()));
		return orb;
	}
	
	/**
	 * 根据案件编号查询嫌疑人名称
	 * 
	 * @param caseCode
	 * @return suspectNames 嫌疑人名称
	 */
	public String findSuspectNameByCaseCode(String caseCode){
		String suspectNames = "";
		List<CriminalPerson> cps = caseService.findSuspectsByCase(caseCode);
		if(cps == null || cps.isEmpty()){
			return suspectNames;
		}
		for(int i=0;i<cps.size();i++){
			String name = cps.get(i).getName();
			if(StringUtils.isBlank(name) || "null".equals(name)){
				continue;
			}
			suspectNames += name;
			if(i < cps.size() - 1){
				suspectNames += "、";
			}
		}
		return suspectNames;
	}
	
	/**
	 * 宗卷文书Model 转 Bean
	 * 
	 * @param af
	 * @return
	 */
	public ArchivedFileBean archivedFileToArchivedFileBean(ArchivedFile af){
		if(af == null){
			return null;
		}
		ArchivedFileBean afb = new ArchivedFileBean();
		BeanCopier copier = BeanCopier.create(af.getClass(), afb.getClass(), false);
		copier.copy(af, afb, null);
		afb.setExcuteTime(DateFmtUtil.dateToLong(af.getExcuteTime()));
		afb.setCaseCode(af.getBasicCase().getCaseCode());
		return afb;
	}
	
	/**
	 * 问询笔录Model 转 Bean
	 * 
	 * @param ccn
	 * @return
	 */
	public CrimialCaseNoteBean crimialCaseNoteToCrimialCaseNoteBean(CrimialCaseNote ccn){
		if(ccn == null){
			return null;
		}
		CrimialCaseNoteBean ccnb = new CrimialCaseNoteBean();
		BeanCopier copier = BeanCopier.create(ccn.getClass(), ccnb.getClass(), false);
		copier.copy(ccn, ccnb, null);
		ccnb.setSort(ccn.getSort() == null ? null : String.valueOf(ccn.getSort()));
		ccnb.setInputTime(DateFmtUtil.dateToLong(ccn.getInputTime()));
		ccnb.setModifiedTime(DateFmtUtil.dateToLong(ccn.getModifiedTime()));
		ccnb.setCaseCode(ccn.getBasicCase().getCaseCode());
		return ccnb;
	}
	
}
