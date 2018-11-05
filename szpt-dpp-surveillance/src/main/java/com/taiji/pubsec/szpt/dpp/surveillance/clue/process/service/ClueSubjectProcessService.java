package com.taiji.pubsec.szpt.dpp.surveillance.clue.process.service;

import java.util.List;
import com.taiji.pubsec.szpt.dpp.surveillance.surveillist.service.SurveilListService;
import com.taiji.pubsec.szpt.kafka.KafkaProducer;
import com.taiji.pubsec.szpt.surveillance.util.message.clue.ClueInfo;
import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;
import com.taiji.pubsec.szpt.surveillance.result.SurveilListResult;
import com.taiji.pubsec.szpt.surveillance.result.service.SurveilListResultService;

public interface ClueSubjectProcessService {

	/**
	 * 处理线索信息
	 * @param clueInfo 线索信息
	 * @return 返回true表示执行完成ClueProcessService的循环终止，返回false标识ClueProcessService的循环继续
	 */
	public List<SurveilListResult> process(ClueInfo clueInfo) ;

	public boolean support(ClueInfo clueInfo) ;
	
	public void setSurveilListService(SurveilListService surveilListService) ;
	
	public List<SurveilListInfo> getSubjectRequiredSurveilListInfos(ClueInfo clueInfo) ;
	
	public SurveilListResult saveSurveilListResultInfo(SurveilListResult result) ;
	
	public void setSurveilListResultService(SurveilListResultService surveilListResultService) ;
}
