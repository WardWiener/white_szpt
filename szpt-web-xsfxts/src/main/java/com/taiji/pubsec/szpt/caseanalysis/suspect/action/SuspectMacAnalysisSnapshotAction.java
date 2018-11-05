package com.taiji.pubsec.szpt.caseanalysis.suspect.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.instruction.action.util.Constant;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;
import com.taiji.pubsec.szpt.util.Constant.INFO_SNAPSHOT_MODULE;
import com.taiji.pubsec.szpt.util.PageCommonAction;

import net.sf.json.JSONObject;

/**
 * 嫌疑人MAC地址分析Action
 * 
 * @author WangLei
 *
 */
@Controller("suspectMacAnalysisSnapshotAction")
@Scope("prototype")
public class SuspectMacAnalysisSnapshotAction extends PageCommonAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private InfoSnapshotService infoSnapshotService;// 快照接口
	
	@Resource
	private IInstructionService instructionService;// 指令接口
	
	@Resource
	private IPersonService personService;// 人员接口
	
	@Resource
	private CaseService caseService;// 案件接口

	private Map<String, Object> resultMap = new HashMap<String, Object>();// 返回前台用
	
	/**
	 * 添加快照
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String createSnapshot(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		
		InfoSnapshot is = new InfoSnapshot();
		is.setTargetId((String)rqst.get("mainCaseCode"));
		is.setType(CriminalBasicCase.class.getName());
		is.setCreatedDate(new Date());
		is.setCreatePerson(this.findCurrentPerson().getName());
		is.setSnapshot(rqst.get("caseSnapshootObject").toString());
		is.setCode(INFO_SNAPSHOT_MODULE.XSAJFX_XYRMACFX.getValue());
		is.setIntro((String)rqst.get("snapshotInfo"));
		resultMap.put("flag", infoSnapshotService.saveInfoSnapshot(is));
		
		return SUCCESS;
	}
	
	/**
	 * 根据指令主体id和指令类型查询指令列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findInstructionListByRelatedObjectIdAndType(){
		Map<String, Object> rqst = JSONObject.fromObject(read());
		List<Instruction> instructions = instructionService.findInstructionByRelatedObjectIdAndType((String)rqst.get("relatedObjectId"), (String)rqst.get("type"));
		
		resultMap.put("instructions", instructions);
		return SUCCESS;
	}
	
	/**
	 * 反馈指令
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String feedbackInstruction() {
		Map<String, Object> rqst = JSONObject.fromObject(read());
		
		String mainCaseCode = (String)rqst.get("mainCaseCode");
		CriminalBasicCase cbc = caseService.findCaseByCode(mainCaseCode);
		if(cbc == null){
			resultMap.put("flag", false);
			return SUCCESS;
		}
		InstructionReceiveSubjectFeedback feedback = new InstructionReceiveSubjectFeedback();
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String feedbackContent = "针对" + mainCaseCode + "，案件名称：" + cbc.getCaseName() + "，生成的快照：" + (String)rqst.get("snapshotName") + sdf.format(nowDate);
		feedback.setFeedbackContent(feedbackContent);
		feedback.setFeedbackPeopleId(this.findCurrentPerson().getId());
		Person person = personService.findById(this.findCurrentPerson().getId());
		if (person != null) {
			feedback.setFeedbackPeopleName(person.getName());
		}
		feedback.setFeedbackTime(new Date());
		String receiveSubjectId = (String)rqst.get("receiveSubjectId");
		instructionService.feedbackInstruction(feedback, receiveSubjectId);
		resultMap.put("flag", true);
		return SUCCESS;
	}
	

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	
}
