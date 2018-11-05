package com.taiji.pubsec.szpt.score.compute.highriskperson.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.Constant;

public class SaveOrUpdateResultOfScorePointConfigForHrpListener implements ScoreComputePointConfig.Listener{

	private static Logger LOGGER = LogManager.getLogger(SaveOrUpdateResultOfScorePointConfigForHrpListener.class);
	
	private HrpScoreService hrpScoreService;
	
	public SaveOrUpdateResultOfScorePointConfigForHrpListener(HrpScoreService hrpScoreService){
		super();
		this.hrpScoreService = hrpScoreService;
	}

	@Override
	public void afterCompute(ScoreComputeTask task, ScoreComputePointConfig scorePointConfig,
			ScoreComputeResult<Double> resultBeforeWeight, ScoreComputeResult<Double> resultAfterWeight,
			Parameter... params) {
		
		LOGGER.debug("积分任务结果监听器保存积分点配置结果："+resultBeforeWeight.getValue() + "(乘以权重之前)、" + +resultAfterWeight.getValue() + "(乘以权重之后)");
		this.hrpScoreService.saveOrUpdateResultOfScorePointConfigForHrp(getHrpId(params), scorePointConfig.getId(), task.getId(), scorePointConfig.getScorePoint().getId(), scorePointConfig.getDescription(), scorePointConfig.getWeight(), resultBeforeWeight.getValue(), resultAfterWeight.getValue());	
	}

	private String getHrpId(Parameter... params){
		for(Parameter param:params){
			if(param.getTag().equals(Constant.PARAMETER_TAG_HRP_ID)){
				return param.getParameter().toString();
			}
		}
		return null ;
	}
}
