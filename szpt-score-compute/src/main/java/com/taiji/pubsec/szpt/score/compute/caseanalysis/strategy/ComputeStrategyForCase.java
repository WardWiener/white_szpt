package com.taiji.pubsec.szpt.score.compute.caseanalysis.strategy;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.scoreframework.ComputeStrategy;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeObject;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeResultImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointConfigImpl;

public class ComputeStrategyForCase implements ComputeStrategy{

		private static final Logger LOGGER = LoggerFactory.getLogger(ComputeStrategyForCase.class);

		@SuppressWarnings("unchecked")
		@Override
		public ScoreComputeResult<Double> compute(Map<ScoreComputeObject, ScoreComputeResult<?>> map, Parameter...params) {
			LOGGER.debug("开始计算任务分数...");
			double score = 0;
			double weightTotal = 0;
			for(ScoreComputeObject obj : map.keySet()){
				if(obj instanceof ScoreComputePointConfigImpl){
					ScoreComputePointConfigImpl config = ((ScoreComputePointConfigImpl)obj) ;
					weightTotal += config.getWeight();
				}
			}
			LOGGER.debug("权重总值: {}", weightTotal);
			for(Map.Entry<ScoreComputeObject, ScoreComputeResult<?>> entry : map.entrySet()){
				ScoreComputeObject obj = entry.getKey();
				ScoreComputeResult<Double> r = (ScoreComputeResult<Double>)entry.getValue();
				
				if(obj instanceof ScoreComputePointConfigImpl){
					ScoreComputePointConfigImpl config = ((ScoreComputePointConfigImpl)obj) ;
					double ratio = config.getWeight() / weightTotal;
					double ts = ratio * r.getValue().doubleValue();
					if(null != config.getListener()){
						config.getListener().afterCompute(config.getScoreTask(), config, r, new ScoreComputeResultImpl(ts), params);
					}
					score += ts;
					LOGGER.debug("分数 : " + r.getValue().doubleValue() + ", 系数 : " + ratio + ", 得分 : " + ts);
				}
			}
			LOGGER.debug("策略计算结果 : " + score);
			return new ScoreComputeResultImpl(score);
		}


}
