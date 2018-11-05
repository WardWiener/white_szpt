package com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import com.taiji.pubsec.businesscomponents.organization.model.Person;
import com.taiji.pubsec.businesscomponents.organization.service.IPersonService;
import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.complex.tools.comet.model.DefaultHintMsg;
import com.taiji.pubsec.complex.tools.comet.service.CometdPushService;
import com.taiji.pubsec.persistence.dao.SqlDao;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.model.PersonExecuteControl;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlResultService;
import com.taiji.pubsec.szpt.highriskpersonalert.personexecutecontrol.service.IPersonExecuteControlService;
import com.taiji.pubsec.szpt.kafka.KafkaReceiver;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteControlReceiver extends KafkaReceiver{

	@Resource
	private IPersonExecuteControlResultService personExecuteControlResultService;

	@Resource
	private IPersonExecuteControlService personExecuteControlService;

	@Resource
	private IPersonService personService;

	@Resource
	private SqlDao sqlDao ;

	private static Logger LOGGER = LoggerFactory.getLogger(ExecuteControlReceiver.class);

	@Override
	public void processData(byte[] message) {

		Object obj = SerializeUtils.unserialize(message);
		String resultId = (String)obj ;

		//查询创建布控单的人，并推送消息给该人员
		DefaultSurveilListResult result = personExecuteControlResultService.findById(resultId);
		LOGGER.debug("result的值:,DefaultSurveilListResult:{}",result);
		if(result != null && result.getClueId() != null){
			LOGGER.debug("result的ClueId值:",result.getClueId());
			Map<String, Object> xqlMap = new HashMap<String, Object>();
			xqlMap.put("id", result.getClueId());
			List<Object[]> controlList = sqlDao.find("select id,bh,cjr_id,sfzh,xm from t_gwry_rybk where id = :id", xqlMap);
			Object[] controlArr = controlList.get(0);
			LOGGER.debug("controlArr的值:id：",controlArr[0]);
			LOGGER.debug("controlArr的值:编号：",controlArr[1]);
			LOGGER.debug("controlArr的值:创建人id：",controlArr[2]);
			PersonExecuteControl control = new PersonExecuteControl();
			control.setId(controlArr[0] == null ? null : (String)controlArr[0]);
			control.setNum(controlArr[1] == null ? null : (String)controlArr[1]);
			Person person = null;
			if(controlArr[2] != null){
				person = personService.findById((String)controlArr[2]);
			}
			control.setCreatePerson(person);
			control.setIdCardNo(controlArr[3] == null ? null : (String)controlArr[3]);
			control.setPersonName(controlArr[4] == null ? null : (String)controlArr[4]);
			LOGGER.debug("control的值：PersonExecuteControl:{}",control);
			if(person != null){
				LOGGER.debug("person的值：Person:{}",person);
				CometdPushService cometdPushService = (CometdPushService)SpringContextUtil.getBean("defaultCometdPushService");
				DefaultHintMsg msg = new DefaultHintMsg("{businessType:\"SzptBkjg\", businessData:\"\", existingTime:\"\", "
						+ "bkdId:\"" + control.getId()+ "\", bkdh:\"" + control.getNum()+ "\"}");
				cometdPushService.pushHint(control.getCreatePerson().getCode() + "PC", "/service/receive/hint", msg);
			}
		}

	}

}
