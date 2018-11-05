package com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.SurveilListService;
import com.taiji.pubsec.szpt.surveillance.util.SurveillanceUtilConstant;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ClueInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.HotelInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;
import com.taiji.pubsec.szpt.surveillance.result.service.SurveilListResultService;

@Service("hotelClueSubjectProcessService")
public class HotelClueSubjectProcessServiceImpl extends AbstractClueSubjectProcessServiceImpl{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelClueSubjectProcessServiceImpl.class);

	@Override
	public boolean support(ClueInfo clueInfo) {
		if(clueInfo instanceof HotelInfo){
			return true ;
		}
		return false;
	}
	
	@Override
	public List<SurveilListResult> process(ClueInfo clueInfo) {

		if(!support(clueInfo)){
			return null ;
		}

		List<SurveilListResult> list = new ArrayList<>() ;
		
		LOGGER.debug("处理酒店线索开始");
		
		HotelInfo clue =(HotelInfo)clueInfo ;
		
		for(SurveilListInfo info:getSubjectRequiredSurveilListInfos(clueInfo)){
			list.add(processSurveilList(clue, info));
		}
		
		LOGGER.debug("处理酒店线索结束");

		return list ;
	}
	
	private SurveilListResult processSurveilList(HotelInfo clue, SurveilListInfo info){
		DefaultSurveilListResult result = new DefaultSurveilListResult() ;
		result.setClueId(info.getId());
		result.setClueType(DefaultSurveilListResult.CLUE_TYPE.HOTEL.getValue());
		result.setCatchContent(clue.sketch());
		result.setResultStatus(SurveillanceUtilConstant.DICT_XTZT_INIT);
		result.setCatchDetail(clue.detailDescription());
		result.setCatchObject(clue.getIdCard());
		result.setSurveilListNum(info.getNum());
		result.setCatchTime(new Date());

		return saveSurveilListResultInfo(result) ;
	}

	@Override
	public List<SurveilListInfo> getSubjectRequiredSurveilListInfos(ClueInfo clueInfo) {
		HotelInfo clue = (HotelInfo)clueInfo ;
		return surveilListService.getSurveilListInfoByNotOutOfDateByIdCardNo(clue.getIdCard()) ;
	}
	
	@Resource
	@Override
	public void setSurveilListService(SurveilListService surveilListService) {
		this.surveilListService = surveilListService;
	}
	
	@Resource
	@Override
	public void setSurveilListResultService(SurveilListResultService surveilListResultService){
		this.surveilListResultService = surveilListResultService ;
	}
}
