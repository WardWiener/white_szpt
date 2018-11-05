package com.taiji.pubsec.szpt.dpp.surveillance.surveillist.message;

import javax.annotation.Resource;

import com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.SurveilListService;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListMsg;

public class SurveilListReceiver extends KafkaReceiver{
	
	@Resource
	private SurveilListService surveilListService ;

	@Override
	public void processData(byte[] message) {
		
		Object obj = SerializeUtils.unserialize(message);
		SurveilListMsg msg = (SurveilListMsg)obj ;
		
		if(msg.getOperationType().equals(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_ADD_OR_UPDATE)){
			surveilListService.unTransRenewSurveilListBySurveilListCode(msg.getSurveilListCode());
			return ;
		}
		
		if(msg.getOperationType().equals(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_CANCEL)){
			surveilListService.unTransCancelSurveilListBySurveilListCode(msg.getSurveilListCode());
			return ;
		}
	}

}
