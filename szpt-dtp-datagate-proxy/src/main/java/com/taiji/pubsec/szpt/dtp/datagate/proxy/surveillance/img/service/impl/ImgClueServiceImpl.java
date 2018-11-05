package com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.base64.Base64CryptFmtUtil;
import com.taiji.pubsec.persistence.dao.Dao;
import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model.ImgClue;
import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.model.SurveilList;
import com.taiji.pubsec.szpt.dtp.datagate.proxy.surveillance.img.service.ImgClueService;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ImgClueDeployMsg;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListImgInfo;

@Service
public class ImgClueServiceImpl implements ImgClueService{
	
	private static Logger LOGGER = LoggerFactory.getLogger(ImgClueServiceImpl.class) ;

	@SuppressWarnings("rawtypes")
	@Autowired
	private Dao dao ;

	@SuppressWarnings("unchecked")
	@Override
	public void cancelSurveilList(String surveilListCode) {
		List<SurveilList> list = this.findSurveilListStatusOn(surveilListCode) ;
		for(SurveilList entity:list){
			LOGGER.debug("解除监控线索：id:{}", entity.getId());
			entity.setStatus(SurveillanceUtilConstant.DICT_OFF);
			this.dao.update(entity);
		}
	}
	
	protected List<SurveilList> findSurveilListStatusOn(String surveilListCode){
		String xql = "select s from " + SurveilList.class.getName() + " as s where"
				+ " s.surveilListCode=:surveilListCode"
				+ " and s.status=:status" ;
		Map<String, Object> xqlMap = new  HashMap<String, Object>() ;
		xqlMap.put("surveilListCode", surveilListCode) ;
		xqlMap.put("status", SurveillanceUtilConstant.DICT_ON) ;
		@SuppressWarnings("unchecked")
		List<SurveilList> list = this.dao.findAllByParams(SurveilList.class, xql, xqlMap) ;
		return list ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deploySurveilList(ImgClueDeployMsg imgClueDeployMsg) {
		
		LOGGER.debug("部署监控线索开始");
		
		cancelSurveilList(imgClueDeployMsg.getSurveilListInfo().getNum()) ;
		
		SurveilList surveilList = new SurveilList() ;
		surveilList.setCreateDate(new Date());
		
		Calendar startCal = Calendar.getInstance() ;
		startCal.setTimeInMillis(imgClueDeployMsg.getSurveilListInfo().getStartTime());
		
		Calendar endCal = Calendar.getInstance() ;
		endCal.setTimeInMillis(imgClueDeployMsg.getSurveilListInfo().getEndTime());
		
		surveilList.setStartDate(startCal.getTime());
		surveilList.setEndDate(endCal.getTime());
		
		surveilList.setSex(imgClueDeployMsg.getSurveilListInfo().getSex());
		surveilList.setName(imgClueDeployMsg.getSurveilListInfo().getPersonName());
		surveilList.setIdenty(imgClueDeployMsg.getSurveilListInfo().getIdCardNo());
		
		surveilList.setStatus(SurveillanceUtilConstant.DICT_ON);
		surveilList.setSurveilListCode(imgClueDeployMsg.getSurveilListInfo().getNum());
		
		this.dao.save(surveilList);
		
		LOGGER.debug("生成布控单：id:{}", surveilList.getId());
		
		for(SurveilListImgInfo imgInfo:imgClueDeployMsg.getSurveilListImgInfos()){
			deployImgClue(imgInfo, surveilList) ;
		}
		
		LOGGER.debug("部署监控线索结束");
	}
	
	@SuppressWarnings("unchecked")
	protected void deployImgClue(SurveilListImgInfo imgInfo, SurveilList surveilList){
		
		ImgClue entity = new ImgClue() ;
		entity.setCreateDate(new Date());
		entity.setImg(Base64CryptFmtUtil.decodeToByte(imgInfo.getImgBase64().getBytes())) ;
		entity.setImgClueId(imgInfo.getImgClueId());
		entity.setSurveilList(surveilList);
		
		this.dao.save(entity);
		
		LOGGER.debug("生成布控img：id:{}" + entity.getId());
	}
}
