package com.taiji.pubsec.szpt.score.compute.highriskperson.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.scoreframework.ScoreComputeResult;
import com.taiji.pubsec.scoreframework.ScoreComputeTask;
import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.score.compute.common.ComputeConstant;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;
import com.taiji.pubsec.szpt.score.util.Constant;

public class SaveOrUpdateResultOfScoreForHrpListener implements ScoreComputeTask.Listener{

	private static Logger LOGGER = LogManager.getLogger(SaveOrUpdateResultOfScoreForHrpListener.class);
	
	protected HrpScoreService hrpScoreService;
	protected ComputeProcessService computeProcessService;
	
	public SaveOrUpdateResultOfScoreForHrpListener(HrpScoreService hrpScoreService, ComputeProcessService computeProcessService){
		super();
		this.hrpScoreService = hrpScoreService;
		this.computeProcessService = computeProcessService;
	}

	@Override
	public void afterRun(ScoreComputeTask task, ScoreComputeResult<Double> result, Parameter... params) {
		LOGGER.debug("积分任务结果监听器保存积分结果："+result.getValue());
		String idOfResultOfScoreForHrp = hrpScoreService.saveOrUpdateResultOfScoreForHrp(getHrpId(params), task.getId(), result.getValue());
		
		sendResult(idOfResultOfScoreForHrp) ;
	}
	
	protected void sendResult(String idOfResultOfScoreForHrp){
		KafkaProducer kafkaProducer = (KafkaProducer)SpringContextUtil.getBean("kafkaProducer") ;
		kafkaProducer.sendData(ComputeConstant.KAFKA_TOPIC_SCORE_RESULT_HP(), idOfResultOfScoreForHrp.getBytes());
	}
	
	protected String getHrpId(Parameter... params){
		for(Parameter param:params){
			if(param.getTag().equals(Constant.PARAMETER_TAG_HRP_ID)){
				return param.getParameter().toString();
			}
		}
		return null ;
	}
}
