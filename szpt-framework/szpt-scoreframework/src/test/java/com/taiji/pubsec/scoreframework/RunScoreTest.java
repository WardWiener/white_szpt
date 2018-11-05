package com.taiji.pubsec.scoreframework;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.taiji.pubsec.scoreframework.ScoreComputeObject;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointConfigImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;
import com.taiji.pubsec.scoreframework.Parameter;

public class RunScoreTest {
	private static Logger log = LogManager.getLogger(RunScoreTest.class);

	@Test
	public void testRun(){
		//初始化积分点配置
		ScoreComputePointImpl cp1 = new ScoreComputePointImpl();
		cp1.setScoreExecutorType("com.taiji.pubsec.scoreframework.MokeExecutor");
		cp1.setScoreExecutorId("1");
		Set<ScoreComputeRule> rules1 = new HashSet<ScoreComputeRule>();
		ScoreComputeRuleImpl r1 = new ScoreComputeRuleImpl();
		r1.setType("com.taiji.pubsec.scoreframework.MokeRuleA");
		r1.setId("1");
		rules1.add(r1);
		ScoreComputeRuleImpl r2 = new ScoreComputeRuleImpl();
		r2.setType("com.taiji.pubsec.scoreframework.MokeRuleB");
		r2.setId("1");
		rules1.add(r2);
		cp1.setScoreRules(rules1);

		ScoreComputePointConfigImpl cpCfg1 = new ScoreComputePointConfigImpl();
		cpCfg1.setScorePoint(cp1);
		cpCfg1.setWeight(0.3);

		//初始化检查点配置
		ScoreComputePointImpl cp2 = new ScoreComputePointImpl();
		cp2.setScoreExecutorType("com.taiji.pubsec.scoreframework.MokeExecutor");
		cp2.setScoreExecutorId("2");
		Set<ScoreComputeRule> rules2 = new HashSet<ScoreComputeRule>();
		rules2.add(r1);
		cp1.setScoreRules(rules2);
		
		ScoreComputePointConfigImpl cpCfg2 = new ScoreComputePointConfigImpl();
		cpCfg2.setScorePoint(cp1);
		cpCfg2.setWeight(0.7);
		
		//初始化检查任务
		ScoreComputeTaskImpl task = new ScoreComputeTaskImpl();
		Set<ScoreComputePointConfig> scorePointConfigs = task.getScorePointConfigs();
		scorePointConfigs.add(cpCfg1);
		scorePointConfigs.add(cpCfg2);
		task.setScorePointConfigs(scorePointConfigs);
		//taskService.save(task);
		
		log.info(task.run(new ForMokeExcetorParam(7, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(1000, MokeExecutor.TAG_MOKEEXECUTOR_Y), new Parameter() {
			
			@Override
			public boolean support(ScoreComputeObject scoreObject) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getTag() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getParameter() {
				// TODO Auto-generated method stub
				return null;
			}
		}).getValue());
		log.info(task.run(new ForMokeExcetorParam(17, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(100, MokeExecutor.TAG_MOKEEXECUTOR_Y)).getValue());
		log.info(task.run(new ForMokeExcetorParam(27, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(10000, MokeExecutor.TAG_MOKEEXECUTOR_Y)).getValue());
		log.info(task.run(new ForMokeExcetorParam(37, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(2000, MokeExecutor.TAG_MOKEEXECUTOR_Y)).getValue());
		log.info(task.run(new ForMokeExcetorParam(47, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(500, MokeExecutor.TAG_MOKEEXECUTOR_Y)).getValue());
		log.info(task.run(new ForMokeExcetorParam(57, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(700, MokeExecutor.TAG_MOKEEXECUTOR_Y)).getValue());
		log.info(task.run(new ForMokeExcetorParam(67, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(7000, MokeExecutor.TAG_MOKEEXECUTOR_Y)).getValue());
	}
	
	class ForMokeExcetorParam implements Parameter{
		Integer value;
		String tag;
		
		public ForMokeExcetorParam(Integer value, String tag) {
			this.value = value;
			this.tag = tag;
		}

		@Override
		public boolean support(ScoreComputeObject scoreObject) {
			if(scoreObject instanceof MokeExecutor)
				return true;
			return false;
		}

		@Override
		public String getTag() {
			return this.tag;
		}

		@Override
		public Object getParameter() {
			return value;
		}
		
	}
}
