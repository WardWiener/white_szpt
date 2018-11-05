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
import com.taiji.pubsec.szpt.surveillance.util.message.clue.WifiInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListMobileInfo;
import com.taiji.pubsec.szpt.surveillance.result.model.DefaultSurveilListResult;
import com.taiji.pubsec.szpt.surveillance.result.service.SurveilListResultService;

@Service("wifiClueSubjectProcessService")
public class WifiClueSubjectProcessServiceImpl extends AbstractClueSubjectProcessServiceImpl{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WifiClueSubjectProcessServiceImpl.class);

	@Override
	public boolean support(ClueInfo clueInfo) {
		if(clueInfo instanceof WifiInfo){
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
		
		LOGGER.debug("处理wifi线索开始");
		
		WifiInfo clue = (WifiInfo)clueInfo ;
		
		for(SurveilListInfo info:getSubjectRequiredSurveilListInfos(clueInfo)){
			list.addAll(processSurveilList(clue, info));
		}
		
		LOGGER.debug("处理wifi线索结束");
		
		return list ;
	}
	
	private List<SurveilListResult> processSurveilList(WifiInfo clue, SurveilListInfo info){
		List<SurveilListResult> list = new ArrayList<>() ;
		for(SurveilListMobileInfo minfo:info.getSurveilListMobileInfos()){
			list.add(processSurveilList(clue, minfo, info)) ;
		}
		return list;
	}
	
	private SurveilListResult processSurveilList(WifiInfo clue, SurveilListMobileInfo surveilListMobileInfo, SurveilListInfo listInfo){
		if(!clue.getMac().equals(surveilListMobileInfo.getMac())){
			return null ;
		}
		
		DefaultSurveilListResult result = new DefaultSurveilListResult() ;
		result.setClueId(surveilListMobileInfo.getWifiClueId());
		result.setClueType(DefaultSurveilListResult.CLUE_TYPE.WIFI.getValue());
		result.setCatchContent(clue.sketch());
		result.setResultStatus(SurveillanceUtilConstant.DICT_XTZT_INIT);
		result.setCatchDetail(clue.detailDescription());
		result.setCatchObject(clue.getMac());
		result.setSurveilListNum(listInfo.getNum());
		result.setCatchTime(new Date());
		
		return saveSurveilListResultInfo(result) ;
	}

	@Override
	public List<SurveilListInfo> getSubjectRequiredSurveilListInfos(ClueInfo clueInfo) {
		WifiInfo clue = (WifiInfo)clueInfo ;
		return surveilListService.getSurveilListInfoByNotOutOfDateByMac(clue.getMac()) ;
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
