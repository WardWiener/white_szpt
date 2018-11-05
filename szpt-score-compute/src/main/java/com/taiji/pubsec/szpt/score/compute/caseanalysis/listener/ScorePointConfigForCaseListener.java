package com.taiji.pubsec.szpt.score.compute.caseanalysis.listener;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResult;
import com.taiji.pubsec.szpt.caseanalysis.score.model.CaseScoreResultDetail;
import com.taiji.pubsec.szpt.caseanalysis.score.service.ScoreResultService;
import com.taiji.pubsec.szpt.caseanalysis.tag.service.CaseService;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.Constant;

public class ScorePointConfigForCaseListener implements ScoreComputePointConfig.Listener{

	private static Logger LOGGER = LogManager.getLogger(ScorePointConfigForCaseListener.class);
	
	private ScoreResultService scoreResultService;
//	private CaseService caseService;
 	
	public ScorePointConfigForCaseListener(ScoreResultService scoreResultService){
		this.scoreResultService = scoreResultService;
	}


	@Override
	public void afterCompute(ScoreComputeTask task,
			ScoreComputePointConfig scorePointConfig,
			ScoreComputeResult<Double> resultBeforeWeight,
			ScoreComputeResult<Double> resultAfterWeight, Parameter... params) {
		LOGGER.debug("积分任务检查点配置监听器保存积分结果：加权前{}-加权后{}", resultBeforeWeight.getValue(), resultAfterWeight.getValue() );
		// arg0：主案件code
		// arg1: 主案件打标值集合
		// arg2：比对案件code
		// arg3: 模板id
		String mainCaseCode = params[0].getParameter().toString();
		String subCaseCode = params[2].getParameter().toString();
//		String templateId = params[3].getParameter().toString();
		CaseScoreResult csResult = scoreResultService.findScoreResult(mainCaseCode, subCaseCode);
		if (csResult == null) {
			csResult = new CaseScoreResult();
			csResult.setMainCase(mainCaseCode);
			csResult.setSubCase(subCaseCode);
//			csResult.setSubCase(templateId);
			scoreResultService.createOrUpdateScoreResult(csResult);
			scoreResultService.refresh(csResult);
		}
		CaseScoreResultDetail csDetail = scoreResultService.findDetailByResultIdAndRuleName(csResult.getId(), scorePointConfig.getDescription());
		if(csDetail == null ) {
			csDetail = new CaseScoreResultDetail();
			csDetail.setResult(csResult);
			csDetail.setScoreRuleName(scorePointConfig.getDescription());
			
		}
		csDetail.setScore(resultAfterWeight.getValue());
		scoreResultService.createOrUpdateCaseScoreResultDetail(csDetail);
	}
	


}
