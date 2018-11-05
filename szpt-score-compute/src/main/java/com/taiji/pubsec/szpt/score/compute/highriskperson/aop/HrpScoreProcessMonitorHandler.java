package com.taiji.pubsec.szpt.score.compute.highriskperson.aop;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.aophandler.handler.AbstractAopAnnoHandler;
import com.taiji.pubsec.scoreframework.monitor.model.ComputeProcess;
import com.taiji.pubsec.scoreframework.monitor.model.FailItem;
import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;
import com.taiji.pubsec.szpt.score.util.ScoreComputeMsg;

@Service
public class HrpScoreProcessMonitorHandler extends AbstractAopAnnoHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HrpScoreProcessMonitorHandler.class) ;

	public static final String MARK = "MARK_com.taiji.pubsec.szpt.score.compute.highriskperson.aop.HrpScoreProcessMonitorHandler";
	
	private static ThreadLocal<String> computeProcessId = new ThreadLocal<String>() ;
	
	@Resource
	private ComputeProcessService computeProcessService;
	
	@Override
	public void beforeHandle(Object aopAnnoObj, JoinPoint jp) {
		
		LOGGER.debug("开始创建ComputeProcess");
		
		Object[] obj = (Object[])aopAnnoObj ;
		LOGGER.debug("获得aop参数:{}", obj[0]);
		
		ScoreComputeMsg msg = (ScoreComputeMsg)obj[0] ;
		
		createComputeProcess(msg) ;
		
	}
	
	@Override
	public void afterReturningHandle(Object aopAnnoObj, Object returnVal, JoinPoint jp) {
		
		Object[] obj = (Object[])aopAnnoObj ;
		ScoreComputeMsg msg = (ScoreComputeMsg)obj[0] ;
	
		completeComputeProcess(msg) ;
		
	}
	
	@Override
	public void afterThrowingHandle(Object aopAnnoObj, JoinPoint jp, Throwable e) {
		
		LOGGER.error(e.getLocalizedMessage());
		
		Object[] obj = (Object[])aopAnnoObj ;
		ScoreComputeMsg msg = (ScoreComputeMsg)obj[0] ;
		
		createFailItem(msg, e) ;
		
	}
	
	private void createComputeProcess(ScoreComputeMsg msg){
		
		ComputeProcess process = new ComputeProcess(ComputeProcess.COMPUTE_PROCESS_STATUS_START, new Date(), 1L, ComputeProcess.COMPUTE_PROCESS_TYPE_HRP, msg.getScoreComputeObj().toString(), ComputeProcess.COMPUTE_PROCESS_TYPE_HRP) ;
		String cpId = computeProcessService.createComputeProcess(process) ;
		computeProcessId.set(cpId);
		
		LOGGER.debug("创建ComputeProcess:id为{}", cpId);
	}
	
	private void completeComputeProcess(ScoreComputeMsg msg){

		String cpId = computeProcessId.get() ;
		
		LOGGER.debug("开始完成id为{}的ComputeProcess", cpId);
		
		ComputeProcess process = this.computeProcessService.getComputeProcess(cpId) ;
		
		process.setCompletedNum(process.getCompletedNum() + 1L);
		process.setEndTime(new Date());
		process.setStatus(ComputeProcess.COMPUTE_PROCESS_STATUS_FINISH);
		
		this.computeProcessService.updateComputeProcess(process);
		
	}
	
	private void createFailItem(ScoreComputeMsg msg, Throwable e){
		
		String cpId = computeProcessId.get() ;
		
		LOGGER.debug("id为{}的ComputeProcess产生错误", cpId);
		
		ComputeProcess process = this.computeProcessService.getComputeProcess(cpId) ;
		process.setStatus(ComputeProcess.COMPUTE_PROCESS_STATUS_ERROR);
		this.computeProcessService.updateComputeProcess(process);
		
		FailItem ft = new FailItem() ;
		ft.setBusinessKey(msg.getScoreComputeObj().toString());
		ft.setBusinessType(ComputeProcess.COMPUTE_PROCESS_TYPE_HRP);
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
