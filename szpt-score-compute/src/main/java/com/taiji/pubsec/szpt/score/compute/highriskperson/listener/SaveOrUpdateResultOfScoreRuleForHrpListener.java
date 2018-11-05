package com.taiji.pubsec.szpt.score.compute.highriskperson.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.Constant;

import net.sf.json.JSONArray;

import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;

public class SaveOrUpdateResultOfScoreRuleForHrpListener implements ScoreComputeRuleImpl.Listener{

	private static Logger LOGGER = LogManager.getLogger(SaveOrUpdateResultOfScoreRuleForHrpListener.class);
	
	private HrpScoreService hrpScoreService;
	
	public SaveOrUpdateResultOfScoreRuleForHrpListener(HrpScoreService hrpScoreService){
		super();
		this.hrpScoreService = hrpScoreService;
	}

	@Override
	public void afterProcess(ScoreComputeRuleImpl rule, ScoreComputeResult<Double> result, Object... params) {
		LOGGER.debug("积分任务结果监听器保存积规则运算结果："+result.getValue());
		JSONArray obj = JSONArray.fromObject(params);
		hrpScoreService.saveOrUpdateResultOfScoreRuleForHrp(getHrpId(params), rule.getScorePoint().getId(), rule.getId(), obj.toString(), result.getOtherResults(), rule.getDescription(), result.getValue());
	}
	
	private String getHrpId(Object... params){
		for(Object param:params){
			if(param instanceof Parameter){
				Parameter p = (Parameter)param;
				if(p.getTag().equals(Constant.PARAMETER_TAG_HRP_ID)){
					return p.getParameter().toString();
				}
			}
		}
		return null ;
	}
}
