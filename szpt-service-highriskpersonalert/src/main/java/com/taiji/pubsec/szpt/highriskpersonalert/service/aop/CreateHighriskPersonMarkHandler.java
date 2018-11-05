package com.taiji.pubsec.szpt.highriskpersonalert.service.aop;

import com.taiji.pubsec.common.tools.aophandler.handler.AbstractAopAnnoHandler;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.score.util.Constant;
import com.taiji.pubsec.szpt.score.util.ScoreComputeMsg;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by huangcheng on 2017/4/11.
 */
public class CreateHighriskPersonMarkHandler{
	
    @Resource
    private KafkaProducer kafkaProducer ;

    public void doAfterReturning(JoinPoint jp, Object retVal){
       Object[] aobj =  jp.getArgs() ;
        Map<String, Object> paramMap = (Map<String, Object>)aobj[0] ;
       String hrpId = (String)paramMap.get("highriskPersonId") ;

        ScoreComputeMsg msg = new ScoreComputeMsg() ;

        msg.setComputeProcessId(Constant.PARAMETER_TAG_COMPUTEPROCESS_ID);
        msg.setScoreComputeObj(hrpId);

        kafkaProducer.sendData("topic-scorecompute-hp", SerializeUtils.serialize(msg));

    }

}
