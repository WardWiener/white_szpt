package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.scan;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model.ImgClueResult;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.kafka.SerializeUtils;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgInfo;

@Service
public class ImgResultScanServiceImpl implements ImgResultScanService{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ImgResultScanServiceImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;
	
	@Resource
	protected KafkaProducer kafkaProducer ;

	@Override
	public void scan() {
		LOGGER.debug("datagate:扫描数据库开始");
		List<ImgClueResult> unreadList = this.findAllByUnreadByStatusOnByDateRange() ;
		sendResults(unreadList);
		saveUnreadToRead(unreadList) ;
		LOGGER.debug("datagate:扫描数据库结束，未读结果{}条", unreadList.size());
	}
	
	@Override
	public void sendResults(List<ImgClueResult> list) {
		ImgInfo[] infos = makeImgInfos(list) ;
		if(infos.length>0){
			LOGGER.debug("datagate:图片代理发送向监控端发送结果");
			kafkaProducer.sendData(SurveillanceUtilConstant.TOPIC_SURVEILLANCE_IMG_RESULT_DATAGATE_TO_SURVEIL(), SerializeUtils.serialize(infos));
		}
	}
	
	private ImgInfo[] makeImgInfos(List<ImgClueResult> list){
		ImgInfo[] infos = new ImgInfo[list.size()] ;
		for(int i=0; i<list.size(); i++){
			infos[i] = makeImgInfo(list.get(i)) ;
		}
		return infos ;
	}
	
	private ImgInfo makeImgInfo(ImgClueResult result){
		ImgInfo msg = new ImgInfo() ;
		
		msg.setCameraCode(result.getCameraCode());
		msg.setCameraAddr(result.getCameraAddr());
		msg.setCatchDate(result.getCatchDate().getTime());
		msg.setConfidenceLevel(result.getConfidenceLevel());

		msg.setLatitude(result.getLatitude());
		msg.setLongitude(result.getLongitude());
		
		msg.setSurveilListCode(result.getSurveilList().getSurveilListCode());
		
		try {
			msg.setImgBase64(Base64CryptFmtUtil.encode(result.getImg(), false));
			return msg ;
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e) ;
		}
	}

	@SuppressWarnings("unchecked")
	private List<ImgClueResult> findAllByUnreadByStatusOnByDateRange(){
		
		String xql = "select r from " + ImgClueResult.class.getName() + " as r where"
				+ " r.status=:status"
				+ " and r.surveilList.status=:imgClueStatus"
				+ " and r.surveilList.startDate<=:nowDate"
				+ " and r.surveilList.endDate>=:nowDate" ;
		
		Map<String, Object> xqlMap = new HashMap<String, Object>() ;
		xqlMap.put("status", ImgClueResult.DTP_DG_IMG_CLUE_RESULT_UNREAD) ;
		xqlMap.put("imgClueStatus", SurveillanceUtilConstant.DICT_ON) ;
		xqlMap.put("nowDate", new Date()) ;
		
		List<ImgClueResult> list = this.dao.findAllByParams(ImgClueResult.class, xql, xqlMap) ;
		
		return list ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveUnreadToRead(List<ImgClueResult> list) {
		for(ImgClueResult entity:list){
			entity.setStatus(ImgClueResult.DTP_DG_IMG_CLUE_RESULT_READ);
			this.dao.update(entity);
		}
	}
}
