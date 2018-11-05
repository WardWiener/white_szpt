package com.taiji.pubsec.szpt.score.compute.caseanalysis.aop;

import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.aophandler.handler.AbstractAopAnnoHandler;
import com.taiji.pubsec.scoreframework.monitor.model.ComputeProcess;
import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;

@Service
public class CaseScoreProcessStepMonitorHandler extends AbstractAopAnnoHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseScoreProcessStepMonitorHandler.class) ;

	public static final String MARK = "MARK_com.taiji.pubsec.szpt.score.compute.caseanalysis.aop.CaseScoreProcessStepMonitorHandler";
	
	@Resource
	private ComputeProcessService computeProcessService; 
	
	@Override
	public void afterReturningHandle(Object aopAnnoObj, Object returnVal, JoinPoint jp) {
		completeComputeProcess() ;
	}
	
	private void completeComputeProcess(){

		String cpId = CaseScoreProcessMonitorHandler.computeProcessId.get() ;
		LOGGER.debug("开始处理id为{}的ComputeProcess", cpId);
		ComputeProcess process = this.computeProcessService.getComputeProcess(cpId) ;
		process.setCompletedNum(process.getCompletedNum() + 1L);
		process.setEndTime(new Date());
		
		this.computeProcessService.updateComputeProcess(process);
		
	}
	
	@Override
	public String getMark() {
		return MARK;
	}

}
