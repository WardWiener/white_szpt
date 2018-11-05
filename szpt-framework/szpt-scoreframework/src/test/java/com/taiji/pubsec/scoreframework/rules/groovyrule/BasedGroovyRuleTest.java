package com.taiji.pubsec.scoreframework.rules.groovyrule;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.taiji.pubsec.scoreframework.DbunitTestCase;
import com.taiji.pubsec.scoreframework.MokeExecutor;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeObject;
import com.taiji.pubsec.scoreframework.ScoreComputePoint;
import com.taiji.pubsec.scoreframework.ScoreComputePointConfig;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeRule;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.scoreframework.javassist.JavassistUtils;
import com.taiji.pubsec.scoreframework.model.ScoreComputePointImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeRuleImpl;
import com.taiji.pubsec.scoreframework.model.ScoreComputeTaskImpl;
import com.taiji.pubsec.scoreframework.service.ScoreComputeTaskService;

public class BasedGroovyRuleTest extends DbunitTestCase {

	private static Logger log = LoggerFactory.getLogger(BasedGroovyRuleTest.class);
			
	@Resource(name="scoreTaskService")
	private ScoreComputeTaskService taskService;
	
	@SuppressWarnings("unchecked")
	@Test
	@DatabaseSetup("classpath:com/taiji/pubsec/scoreframework/rules/groovyrule/groovyrule-setup.xml")
	public void testGroovyRule(){
		ScoreComputePointImpl.Listener cplistener = new ScoreComputePointImpl.Listener() {
			
			public void afterCompute(ScoreComputeTask task, ScoreComputePoint scorePoint, ScoreComputeResult<Double> result, Parameter...params) {
				log.info("task(id:" + task.getId() + ", desc:" + task.getDescription() + ")");
				log.info("将积分点结果持久化到数据库 : [(id:" + scorePoint.getId() + ", desc:" + scorePoint.getDescription() + "), result:" + result + ", params:" + print(params) + "]");
			}
		};
		
		ScoreComputeRuleImpl.Listener rulelistener = new ScoreComputeRuleImpl.Listener() {
			
			public void afterProcess(ScoreComputeRuleImpl rule, ScoreComputeResult<Double> result, Object...params) {
				log.info("将积分规则结果持久化到数据库 : [(id:" + rule.getId() + ", desc:" + rule.getDescription() + "), result:" + result + ", params:" + print(params) + "]");
			}
		};
		
		ScoreComputeTask task = taskService.findById("ct1");
		for(ScoreComputePointConfig cpc : ((ScoreComputeTaskImpl)task).getScorePointConfigs()){
			ScoreComputePointImpl cp = (ScoreComputePointImpl)cpc.getScorePoint();
			cp.setListener(cplistener);
			Set<ScoreComputeRule> rules = cp.getScoreRules();
			for(ScoreComputeRule rule : rules){
				((ScoreComputeRuleImpl)rule).setListener(rulelistener);
			}
		}
		
		ScoreComputeResult<Double> r = (ScoreComputeResult<Double>) task.run(new ForMokeExcetorParam(17, MokeExecutor.TAG_MOKEEXECUTOR_X), new ForMokeExcetorParam(100, MokeExecutor.TAG_MOKEEXECUTOR_Y));
		log.info(r.getValue().toString());
	}
	
	private String print(Object[] objs){
		StringBuffer sb = new StringBuffer();
		for(Object obj : objs){
			sb.append(obj.toString());
		}
		return sb.toString();
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
		
		public String toString(){
			StringBuffer sb = new StringBuffer();
			sb.append("[").append(this.getClass().getName()).append(", ");
			sb.append("value:").append(value).append(", ");
			sb.append("tag:").append(tag).append("]");
			return sb.toString();
		}
	}
}
