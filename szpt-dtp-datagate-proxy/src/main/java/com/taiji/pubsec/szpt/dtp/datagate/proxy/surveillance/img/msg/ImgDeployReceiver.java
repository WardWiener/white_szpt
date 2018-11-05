package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.msg;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.service.ImgClueService;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgClueDeployMsg;

public class ImgDeployReceiver extends KafkaReceiver{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ImgDeployReceiver.class) ;
	
	@Resource
	private ImgClueService imgClueService;

	@Override
	public void processData(byte[] message) {
	
		Object obj = SerializeUtils.unserialize(message);
		
		ImgClueDeployMsg msg = (ImgClueDeployMsg)obj ;
		
		LOGGER.debug("接收监控端图片监控线索：操作{}", msg.getOperate());
		
		if(msg.getOperate().equals(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_CANCEL)){
			imgClueService.cancelSurveilList(msg.getSurveilListInfo().getNum());
			return ;
		}
		
		imgClueService.deploySurveilList(msg);
	}
}
