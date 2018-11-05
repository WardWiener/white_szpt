package com.taiji.pubsec.szpt.caseanalysis.score.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.common.tools.sql.SQLTool;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.caseanalysis.score.bean.MessageResultBean;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResultMessage;
import com.taiji.pubsec.szpt.caseanalysis.score.service.CaseScoreResultMessageService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CaseTag;
import com.taiji.pubsec.szpt.util.Constant.DICT;
import com.taiji.pubsec.szpt.util.ParamMapUtil;

@Service("caseScoreResultMessageService")
public class CaseScoreResultMessageServiceImpl implements CaseScoreResultMessageService {

	@SuppressWarnings("rawtypes")
	@Resource
	private Dao dao;
	
	private static Logger LOGGER = LoggerFactory
			.getLogger(CaseScoreResultMessageServiceImpl.class);
	
	@Resource
	private IDictionaryItemService dictionaryItemService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<MessageResultBean> findMessageByConditions(Map<String, Object> conditions, int pageNo, int pageSize) {
		StringBuilder xql = new StringBuilder("select t from CaseTag as t, CaseScoreResultMessage as m where t.basicCase.caseCode = m.caseCode ");
		Map<String, Object> xqlMap = new HashMap<String, Object>();
		
		if(ParamMapUtil.isNotBlank(conditions.get("caseCodeLists"))){
			xql.append(" and t.basicCase.caseCode in (:caseCodeLists) ");
			xqlMap.put("caseCodeLists", conditions.get("caseCodeLists"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("type"))){
			xql.append(" and t.type = :type ");
			xqlMap.put("type", conditions.get("type"));
		}
		//TODO 案件状态在案件基本信息里面存的不是字典项，是名称
		if(ParamMapUtil.isNotBlank(conditions.get("caseState"))){
//			xql.append(" and t.basicCase.caseState = caseState ");
//			xqlMap.put("caseState", conditions.get("caseState"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("handlePolice"))){
			xql.append(" and t.basicCase.handlePolice like :handlePolice ");
			SQLTool.SQLAddEscape(xql);
			xqlMap.put("handlePolice", "%" + SQLTool.SQLSpecialChTranfer((String) conditions.get("handlePolice")) + "%");
		}
		if(ParamMapUtil.isNotBlank(conditions.get("firstSort"))){
			xql.append(" and t.firstSort = :firstSort ");
			xqlMap.put("firstSort", conditions.get("firstSort"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("secondSort"))){
			xql.append(" and t.secondSort = :secondSort ");
			xqlMap.put("secondSort", conditions.get("secondSort"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("caseTimeStartFrom"))){
			xql.append(" and t.basicCase.caseTimeStart >= :caseTimeStartFrom ");
			xqlMap.put("caseTimeStartFrom", conditions.get("caseTimeStartFrom"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("caseTimeStartTo"))){
			xql.append(" and t.basicCase.caseTimeStart < :caseTimeStartTo ");
			xqlMap.put("caseTimeStartTo", conditions.get("caseTimeStartTo"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("systemAutoPushTimeFrom"))){
			xql.append(" and m.createdTime >= :systemAutoPushTimeFrom ");
			xqlMap.put("systemAutoPushTimeFrom", conditions.get("systemAutoPushTimeFrom"));
		}
		if(ParamMapUtil.isNotBlank(conditions.get("systemAutoPushTimeTo"))){
			xql.append(" and m.createdTime < :systemAutoPushTimeTo ");
			xqlMap.put("systemAutoPushTimeTo", conditions.get("systemAutoPushTimeTo"));
		}
		xql.append(" order by m.createdTime desc");
		Pager<CaseTag> tagPager = dao.findByPage(CaseTag.class, xql.toString(), xqlMap, pageNo, pageSize);
		Pager<MessageResultBean> resultBeanPager = new Pager<MessageResultBean>();
		for(CaseTag tag: tagPager.getPageList()){
			MessageResultBean bean = new MessageResultBean();
			bean.setCaseCode(tag.getBasicCase().getCaseCode());
			bean.setCaseName(tag.getBasicCase().getCaseName());
			bean.setCaseState(tag.getBasicCase().getCaseStateName());
			bean.setCaseTimeStart(tag.getBasicCase().getCaseTimeStart().getTime());
			bean.setFirstSort(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), tag.getFirstSort(), null).getName());
			bean.setHandlePolice(tag.getBasicCase().getHandlePolice());
			bean.setSecondSort(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), tag.getSecondSort(), null).getName());
			bean.setType(dictionaryItemService.findDictionaryItemByDicTypeCodeAndItemCode(DICT.DICT_TYPE_AJXZ.getValue(), tag.getType(), null).getName());
			
			CaseScoreResultMessage message = (CaseScoreResultMessage) dao.findByParams(CaseScoreResultMessage.class, "select m from CaseScoreResultMessage as m where m.caseCode = ?", new Object[]{tag.getBasicCase().getCaseCode()});
			if(message != null){
				bean.setSystemAutoPushTime(message.getCreatedTime().getTime());
			}
			
			resultBeanPager.getPageList().add(bean);
		}
		
		resultBeanPager.setTotalNum(tagPager.getTotalNum());
		return resultBeanPager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean receiveMessage(String messageId) {
		try {
			CaseScoreResultMessage message = (CaseScoreResultMessage) dao.findById(CaseScoreResultMessage.class, messageId);
			message.setRead(true);
			dao.update(message);
		} catch (Exception e) {
			LOGGER.debug("更新消息状态失败！", e);
			return false;
		}
		return true;
	}

}
