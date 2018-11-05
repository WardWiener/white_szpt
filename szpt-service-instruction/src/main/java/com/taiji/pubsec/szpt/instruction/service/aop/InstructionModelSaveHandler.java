package com.taiji.pubsec.szpt.instruction.service.aop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.businesscomponents.organization.service.IUnitService;
import com.taiji.pubsec.common.tools.aophandler.handler.AbstractAopAnnoHandler;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubject;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.solr.SolrHelper;
import com.taiji.pubsec.szpt.solr.util.SolrConstant;

@Service
public class InstructionModelSaveHandler extends AbstractAopAnnoHandler{
	
	public static final String MARK = "InstructionModelSaveHandler" ;
	
	@Resource
	private IUnitService unitService;
	@Resource
	private IInstructionService instructionService;
	
	/**
	 * 指令管理-指令新增增加solr索引
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void afterReturningHandle(Object aopAnnoObj, Object returnVal, JoinPoint jp){
		
		String id=(String) returnVal;
		Instruction in = instructionService.findInstructionById(id) ;
		
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		String content = in.getContent();
		String type = in.getType();
		String reletedcontent = in.getRelateObjectContent();
		
		Set<InstructionReceiveSubject> set = in.getInstructionReceiveSubjects();
		List<String> receiveunit=null;
		if(null != set){
			receiveunit = new ArrayList<String>() ;
			for (InstructionReceiveSubject instructionReceiveSubject : set) {
				if(null == instructionReceiveSubject.getReceiveSubjectId()){
					continue;
				}else{
					receiveunit.add(this.unitService.findById(instructionReceiveSubject.getReceiveSubjectId()).getName());
				}
			}
		}
		String createPeopleDepartmentId = in.getCreatePeopleDepartmentId();
		String sendunit = null;
		if(null != createPeopleDepartmentId){
			sendunit=this.unitService.findById(createPeopleDepartmentId).getName();
		}
		
		Date feedbacktime =  in.getRequireFeedbackTime();
		String askfeedbacktime=null;
		if(feedbacktime != null){
				askfeedbacktime = sdfdst.format(feedbacktime);
		}
		Date receivetime = in.getCreateTime();
		String createtime=null;
		if(receivetime != null){
				createtime = sdfdst.format(receivetime);
		}
		
		Map map = new HashMap();
		map.put(SolrConstant.Instruction.id.getValue(),in.getId());
		map.put(SolrConstant.Instruction.content.getValue(),content);
		map.put(SolrConstant.Instruction.type.getValue(),type);
		map.put(SolrConstant.Instruction.receiveunit.getValue(),receiveunit);
		map.put(SolrConstant.Instruction.sendunit.getValue(),sendunit);
		map.put(SolrConstant.Instruction.reletedcontent.getValue(),reletedcontent);
		map.put(SolrConstant.Instruction.askfeedbacktime.getValue(),askfeedbacktime);
		map.put(SolrConstant.Instruction.createtime.getValue(),createtime);
		
		SolrHelper solr = SolrHelper.getInstance();
		solr.addIndex(SolrConstant.COLLECTION_INSTRUCTION, SolrConstant.Instruction.id.getValue(), map);
	}
	
	@Override
	public String getMark() {
		return MARK;
	}

}
