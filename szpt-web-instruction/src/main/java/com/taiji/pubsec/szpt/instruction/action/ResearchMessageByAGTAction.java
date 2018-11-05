package com.taiji.pubsec.szpt.instruction.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.taiji.pubsec.businesscomponents.dictionary.model.DictionaryItem;
import com.taiji.pubsec.businesscomponents.dictionary.service.IDictionaryItemService;
import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.model.Unit;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.MySecureUser;
import com.taiji.pubsec.businesscomponents.springsecurity.rbac.util.SessionUserDetailsUtil;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.comet.model.DefaultHintMsg;
import com.taiji.pubsec.complex.tools.comet.service.CometdPushService;
import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionBean;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionReceiveSubjectBean;
import com.taiji.pubsec.szpt.instruction.action.bean.InstructionReceiveSubjectFeedbackBean;
import com.taiji.pubsec.szpt.instruction.action.bean.OperationRecordBean;
import com.taiji.pubsec.szpt.instruction.action.bean.researchMessageBean;
import com.taiji.pubsec.szpt.instruction.action.util.Constant;
import com.taiji.pubsec.szpt.instruction.action.util.InsturctionCommonUtil;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectSign;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.operationrecord.model.OperationRecord;
import com.taiji.pubsec.szpt.operationrecord.model.StandardLogRecord;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.DateFmtUtil;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONObject;

/**
 * 案件管理研判页面
 * 
 */
@Controller("researchMessageByAGTAction")
@Scope("prototype")
public class ResearchMessageByAGTAction extends PageCommonAction {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ResearchMessageByAGTAction.class);
	
	@Resource
	private IInstructionService instructionService;
	
	@Resource
	private InfoSnapshotService infoSnapshotService ;

	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private String queryStr;

	/**
	 * 指令列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findResearchMessage() {
		Map<String,Object> map=JSONObject.fromObject(queryStr);
		int start=Integer.parseInt(String.valueOf(map.get("start")));
		int length =Integer.parseInt(String.valueOf(map.get("length")));
		Pager<InstructionReceiveSubjectFeedback> page= instructionService.findInstructionsByRelatedObjectIdByPage(String.valueOf(map.get("identy")), start/length, length);
		resultMap.put("totalNum", page.getTotalNum());
		List<researchMessageBean> listBean=changeResBean(page.getPageList());
		resultMap.put("list", listBean);
		return SUCCESS;
	}

	

	/**
	 * |转换bean
	 * @param pageList
	 * @return
	 */
	private List<researchMessageBean> changeResBean(List<InstructionReceiveSubjectFeedback> pageList) {
		List<researchMessageBean> list=new ArrayList<researchMessageBean>();
		for(int i=0;i<pageList.size();i++){
			researchMessageBean bean=new researchMessageBean();
			bean.setId(pageList.get(i).getId());
			bean.setInstructionContent(pageList.get(i).getFeedbackContent());
			SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			bean.setInstructionTime(sdfdst.format(pageList.get(i).getFeedbackTime()));
			bean.setInstructionType(pageList.get(i).getInstructionReceiveSubject().getInstruction().getRelatedObjectType());
			bean.setPersonNum(pageList.get(i).getInstructionReceiveSubject().getInstruction().getRelatedObjectId());
			
			String feedbackRelateObjectType = pageList.get(i).getRelateObjectType();
			if(StringUtils.isNotBlank(feedbackRelateObjectType) && feedbackRelateObjectType.equals(InfoSnapshot.class.getName())){
				InfoSnapshot ss = infoSnapshotService.findInfoSnapshotById(pageList.get(i).getRelateObjectId()) ;
				if(ss!=null){
					bean.setSnapshotCode(ss.getCode());
					bean.setSnapshotId(ss.getId());
				}
				
			}
			
			list.add(bean);
		}
		return list;
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
