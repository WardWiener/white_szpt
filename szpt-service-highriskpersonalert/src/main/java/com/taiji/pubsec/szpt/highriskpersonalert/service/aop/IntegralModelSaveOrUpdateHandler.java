package com.taiji.pubsec.szpt.highriskpersonalert.service.aop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.aophandler.handler.AbstractAopAnnoHandler;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModel;
import com.taiji.pubsec.szpt.highriskpersonalert.model.IntegralModelRule;
import com.taiji.pubsec.szpt.highriskpersonalert.service.IIntegralModelService;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.ScoreRule;
import com.taiji.pubsec.szpt.score.util.hrp.service.HrpScoreComputeTaskUtilService;

@Service
public class IntegralModelSaveOrUpdateHandler extends AbstractAopAnnoHandler{
	
	public static final String MARK = "IntegralModelSaveOrUpdateHandler" ;
	
	@Resource
	private HrpScoreComputeTaskUtilService hrpScoreComputeTaskUtilService ;
	
	@Resource
	private IIntegralModelService integralModelService ;

	@Override
	public void afterReturningHandle(Object aopAnnoObj, Object returnVal, JoinPoint jp){
		IntegralModel entity = (IntegralModel)returnVal ;
		ScoreRule scoreRule = new ScoreRule((double)entity.getAlertPoint());
		List<RuleDetail> ruleDetails = new ArrayList<RuleDetail>();
		for(IntegralModelRule imr :integralModelService.findIntegralModelRuleByIntegralModelId(entity.getId())){
			RuleDetail ruleDetail= new RuleDetail(imr.getKey(),imr.getValue());
			ruleDetails.add(ruleDetail);
		}
		scoreRule.setRuleDetails(ruleDetails);
		hrpScoreComputeTaskUtilService.renewHrpComputeTask(scoreRule);
	}

	@Override
	public String getMark() {
		return MARK;
	}

}
