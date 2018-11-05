package com.taiji.pubsec.szpt.score.compute.highriskperson.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.Constant;

public class SaveOrUpdateResultOfScorePointForHrpListener implements ScoreComputePoint.Listener{

	private static Logger LOGGER = LogManager.getLogger(SaveOrUpdateResultOfScorePointForHrpListener.class);
	
	private HrpScoreService hrpScoreService;
	
	public SaveOrUpdateResultOfScorePointForHrpListener(HrpScoreService hrpScoreService){
		super();
		this.hrpScoreService = hrpScoreService;
	}

	@Override
	public void afterCompute(ScoreComputeTask task, ScoreComputePoint scorePoint, ScoreComputeResult<Double> result,
			Parameter... params) {
		LOGGER.debug("积分任务结果监听器保存积分点结果："+result.getValue());
		hrpScoreService.saveOrUpdateResultOfScorePointForHrp(getHrpId(params), scorePoint.getId(), scorePoint.getDescription(), result.getValue());
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
