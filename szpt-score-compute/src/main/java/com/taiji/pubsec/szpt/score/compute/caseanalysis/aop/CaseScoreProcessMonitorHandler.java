package com.taiji.pubsec.szpt.score.compute.caseanalysis.aop;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.aophandler.handler.AbstractAopAnnoHandler;
import com.taiji.pubsec.scoreframework.monitor.model.ComputeProcess;
import com.taiji.pubsec.scoreframework.monitor.model.FailItem;
import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;
import com.taiji.pubsec.szpt.caseanalysis.tag.model.CriminalBasicCase;

@Service
public class CaseScoreProcessMonitorHandler extends AbstractAopAnnoHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(CaseScoreProcessMonitorHandler.class) ;

	public static final String MARK = "MARK_com.taiji.pubsec.szpt.score.compute.caseanalysis.aop.CaseScoreProcessMonitorHandler";
	public static ThreadLocal<String> computeProcessId = new ThreadLocal<String>() ;
	
	@Resource
	private ComputeProcessService computeProcessService; 
	
	@Override
	public void beforeHandle(Object aopAnnoObj, JoinPoint jp) {
		
		LOGGER.debug("开始创建ComputeProcess");
		
		Object[] obj = (Object[])aopAnnoObj ;
		LOGGER.debug("获得aop参数:{},{}", obj[0], obj[1]);
		
		createComputeProcess(obj) ;
		
	}
	
	@Override
	public void afterReturningHandle(Object aopAnnoObj, Object returnVal, JoinPoint jp) {
		completeComputeProcess() ;
	}
	
	@Override
	public void afterThrowingHandle(Object aopAnnoObj, JoinPoint jp, Throwable e) {
		
		LOGGER.error(e.getLocalizedMessage());
		
		Object[] obj = (Object[])aopAnnoObj ;
		createFailItem(obj, e) ;
		
	}
	
	private void createComputeProcess(Object[] obj){
		String mainCaseCode = (String)(obj[0]);
		@SuppressWarnings("unchecked")
		List<CriminalBasicCase> comparedCases = (List<CriminalBasicCase>)(obj[1]);
		
		ComputeProcess process = new ComputeProcess(ComputeProcess.COMPUTE_PROCESS_STATUS_START, new Date(), comparedCases.size() + 0L, ComputeProcess.COMPUTE_PROCESS_TYPE_CASE, mainCaseCode, ComputeProcess.COMPUTE_PROCESS_TYPE_CASE) ;
		String cpId = computeProcessService.createComputeProcess(process) ;
		computeProcessId.set(cpId);
		
		LOGGER.debug("创建ComputeProcess:id为{}", cpId);
	}
	
	private void completeComputeProcess(){

		String cpId = computeProcessId.get() ;
		
		LOGGER.debug("开始完成id为{}的ComputeProcess", cpId);
		
		ComputeProcess process = this.computeProcessService.getComputeProcess(cpId) ;
		
		process.setEndTime(new Date());
		process.setStatus(ComputeProcess.COMPUTE_PROCESS_STATUS_FINISH);
		
		this.computeProcessService.updateComputeProcess(process);
		
	}
	
	private void createFailItem(Object[] obj, Throwable e){
		
		String cpId = computeProcessId.get() ;
		
		LOGGER.debug("id为{}的ComputeProcess产生错误", cpId);
		
		ComputeProcess process = this.computeProcessService.getComputeProcess(cpId) ;
		process.setStatus(ComputeProcess.COMPUTE_PROCESS_STATUS_ERROR);
		this.computeProcessService.updateComputeProcess(process);
		
		FailItem ft = new FailItem() ;
		ft.setBusinessKey((String)(obj[0]));
		ft.setBusinessType(ComputeProcess.COMPUTE_PROCESS_TYPE_CASE);
		ft.setComputeProcess(process);
		ft.setRecordTime(new Date());
		ft.setErrorMessage(e.getMessage());
		
		String failId = this.computeProcessService.createFailItem(ft);
		
		LOGGER.debug("生成id为{}的FailItem的错误记录", failId);
	}
	@Override
	public String getMark() {
		return MARK;
	}

}
