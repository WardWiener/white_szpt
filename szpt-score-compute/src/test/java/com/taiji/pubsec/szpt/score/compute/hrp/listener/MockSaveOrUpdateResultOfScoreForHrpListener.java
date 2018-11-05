package com.taiji.pubsec.szpt.score.compute.hrp.listener;

import com.taiji.pubsec.scoreframework.monitor.service.ComputeProcessService;
import com.taiji.pubsec.szpt.score.compute.highriskperson.listener.SaveOrUpdateResultOfScoreForHrpListener;
import com.taiji.pubsec.szpt.score.compute.highriskperson.service.HrpScoreService;

public class MockSaveOrUpdateResultOfScoreForHrpListener extends SaveOrUpdateResultOfScoreForHrpListener{

	public MockSaveOrUpdateResultOfScoreForHrpListener(HrpScoreService hrpScoreService,
			ComputeProcessService computeProcessService) {
		super(hrpScoreService, computeProcessService);
	}

	@Override
	protected void sendResult(String idOfResultOfScoreForHrp){
		
	}
}
