package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.aop;

import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListMsg;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by huangcheng on 2017/4/12.
 */
public class SavePersonExecuteControlStatusHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(SavePersonExecuteControlStatusHandler.class) ;

    @Resource
    private KafkaProducer kafkaProducer ;

    public void doAfterReturning(JoinPoint jp, Object retVal){
        Object[] aobj =  jp.getArgs() ;
        PersonExecuteControl pec = (PersonExecuteControl) aobj[0];

        if(pec.getId()==null || pec.getId().length()==0){
            LOGGER.error("布控单id为空");
            return;
        }

        SurveilListMsg msg = new SurveilListMsg() ;

        if(pec.getStatus().equals(SurveillanceUtilConstant.DICT_ON) && pec.getOperateStatus().equals(SurveillanceUtilConstant.CZZT_SPTG)){
            msg.setOperationType(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_ADD_OR_UPDATE);
        }

        if(pec.getStatus().equals(SurveillanceUtilConstant.DICT_OFF)){
            msg.setOperationType(SurveillanceUtilConstant.SURVEILLIST_MSG_OPERATION_TYPE_CANCEL);
        }

        if(StringUtils.isBlank(msg.getOperationType())){
            LOGGER.debug("OperationType，不通知布控端");
            return ;
        }

        msg.setSurveilListCode(pec.getNum());

        kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_BUSINESS_TO_SURVEIL(), SerializeUtils.serialize(msg));
    }
}
