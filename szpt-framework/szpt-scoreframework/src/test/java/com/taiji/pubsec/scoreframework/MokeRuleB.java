package com.taiji.pubsec.scoreframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeResultImpl;

public class MokeRuleB implements ScoreComputeRule {
	private static Logger log = LogManager.getLogger(MokeRuleB.class);

	private String id;
	
	public static MokeRuleB getScoreRule(String id){
		MokeRuleB r = new MokeRuleB();
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
		if(businessData <= 0) 
			return (ScoreComputeResult<Double>) new ScoreComputeResultImpl(Double.valueOf(-20));
		return new ScoreComputeResultImpl(Double.valueOf(0));
	}

	@Override
	public String getDescription() {
		return null;
	}

}
