package com.taiji.pubsec.szpt.dpp.surveillance.clue.deploy.service;

import com.taiji.pubsec.szpt.surveillance.util.message.surveillist.SurveilListInfo;

public interface ClueDeployService {

	/**
	 * 部署布控单
	 * @param surveilListInfo 布控单
	 * @param operate 操作:add_or_update；cancel
	 */
	public void deploy(SurveilListInfo surveilListInfo, String operate) ;
}
