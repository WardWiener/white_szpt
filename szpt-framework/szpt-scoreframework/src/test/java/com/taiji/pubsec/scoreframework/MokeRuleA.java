package com.taiji.pubsec.scoreframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeResultImpl;

public class MokeRuleA implements ScoreComputeRule {
	private static Logger log = LogManager.getLogger(MokeRuleA.class);

	private String id;
	
	public static MokeRuleA getScoreRule(String id){
		MokeRuleA r = new MokeRuleA();
		r.setId(id);
		return r;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ScoreComputeResult<?> process(Object... params) {
		Integer businessData = (Integer)params[0];
		log.debug("对" + businessData + "应用规制.");
		if(businessData < 10000 && businessData > 0) return (ScoreComputeResult<Double>) new ScoreComputeResultImpl(Double.valueOf(20));
		if(businessData < 20000 && businessData > 10000) return (ScoreComputeResult<Double>) new ScoreComputeResultImpl(Double.valueOf(40));
		if(businessData < 30000 && businessData > 20000) return (ScoreComputeResult<Double>) new ScoreComputeResultImpl(Double.valueOf(60));
		if(businessData < 40000 && businessData > 30000) return (ScoreComputeResult<Double>) new ScoreComputeResultImpl(Double.valueOf(80));
		return new ScoreComputeResultImpl(Double.valueOf(0));
	}

	@Override
	public String getDescription() {
		return null;
	}

}
