package com.taiji.pubsec.szpt.score.compute.caseanalysis.listener;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResult;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreResultService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.score.compute.common.ComputeConstant;

public class ScoreComputeTaskForCaseListener implements ScoreComputeTask.Listener{

	private static Logger LOGGER = LogManager.getLogger(ScoreComputeTaskForCaseListener.class);
	
	private ScoreResultService scoreResultService;
	private CaseService caseService;
	
	public ScoreComputeTaskForCaseListener(ScoreResultService scoreResultService, CaseService caseService){
		this.scoreResultService = scoreResultService;
		this.caseService = caseService;
	}

	@Override
	public void afterRun(ScoreComputeTask task, ScoreComputeResult<Double> result, Parameter... params) {
		LOGGER.debug("积分任务结果监听器保存积分结果："+result.getValue());
		// arg0：主案件code
		// arg1: 主案件打标值集合
		// arg2：比对案件code
		// arg3: 模板id
		String mainCaseCode = params[0].getParameter().toString();
		String subCaseCode = params[2].getParameter().toString();
		CaseScoreResult csResult = scoreResultService.findScoreResult(mainCaseCode, subCaseCode);
		if (csResult == null) {
			csResult = new CaseScoreResult();
			csResult.setMainCase(mainCaseCode);
			csResult.setSubCase(subCaseCode);
		}
		csResult.setScore(result.getValue());
		csResult.setScoreTime(new Date());
		csResult.setTemplateId(params[3].getParameter().toString());
		if(caseService.findCaseByCode(mainCaseCode).getCaseStateName().equals("刑事案件破案")) {
			csResult.setFinished(true);
		}
		scoreResultService.createOrUpdateScoreResult(csResult);
	}
	
}
