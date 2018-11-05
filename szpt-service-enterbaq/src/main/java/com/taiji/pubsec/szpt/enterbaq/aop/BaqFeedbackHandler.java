package com.taiji.pubsec.szpt.enterbaq.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.complex.tools.comet.model.DefaultHintMsg;
import com.taiji.pubsec.complex.tools.comet.service.CometdBroadcastService;
import com.taiji.pubsec.szpt.instruction.model.Instruction;
import com.taiji.pubsec.szpt.instruction.model.InstructionReceiveSubjectFeedback;
import com.taiji.pubsec.szpt.instruction.service.IInstructionService;
import com.taiji.pubsec.szpt.snapshot.model.InfoSnapshot;
import com.taiji.pubsec.szpt.snapshot.service.InfoSnapshotService;

import net.sf.json.JSONObject;

public class BaqFeedbackHandler {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(BaqFeedbackHandler.class) ;
	
	@Resource(name="defaultCometdBroadcastService")
	private CometdBroadcastService cometdBroadcastService;
	
	@Resource
	private IInstructionService instructionService ;
	
	@Resource
	private InfoSnapshotService infoSnapshotService ;

	public void doAfterReturning(JoinPoint jp, Object retVal){
		String feedbackId = (String)retVal ;
		
		if(StringUtils.isBlank(feedbackId)){
			return ;
		}
		InstructionReceiveSubjectFeedback feedback = instructionService.findFeedbackById(feedbackId) ;

		Instruction instruction =  feedback.getInstructionReceiveSubject().getInstruction();
		
		SimpleDateFormat sdfdst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String otherFeedbackContent = feedback.getOtherFeedbackContent();
		String identy = instruction.getRelatedObjectId() ;
		String instructionType = instruction.getRelatedObjectType() ;
		String instructionContent = feedback.getFeedbackContent();
		String instructionTime = sdfdst.format(new Date());
		String id = feedback.getId() ;
		
		JSONObject obj = new JSONObject() ;
		obj.put("instructionType", instructionType) ;
		obj.put("instructionContent", instructionContent) ;
		obj.put("instructionTime", instructionTime) ;
		obj.put("id", id) ;
		obj.put("identy", identy) ;
		obj.put("otherFeedbackContent", otherFeedbackContent) ;
		
		String feedbackRelateObjectType = feedback.getRelateObjectType();
		if(StringUtils.isNotBlank(feedbackRelateObjectType) && feedbackRelateObjectType.equals(InfoSnapshot.class.getName())){
			InfoSnapshot ss = infoSnapshotService.findInfoSnapshotById(feedback.getRelateObjectId()) ;
			obj.put("snapshotId", ss.getId()) ;
			obj.put("snapshotCode", ss.getCode()) ;
			obj.put("identy", ss.getTargetId()) ;
		}
		
		DefaultHintMsg msg = new DefaultHintMsg(obj.toString()) ;
		
		cometdBroadcastService.pushBroadcast("/comet/broadcast/enterbaq", msg);
	}
	
	public void doAfterThrowing(JoinPoint jp, Throwable e){
		
	}
}
