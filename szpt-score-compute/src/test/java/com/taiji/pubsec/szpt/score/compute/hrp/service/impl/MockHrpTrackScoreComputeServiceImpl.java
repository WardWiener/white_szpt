package com.taiji.pubsec.szpt.score.compute.hrp.service.impl;

import java.util.Date;

import com.taiji.pubsec.szpt.score.util.service.HrpTrackScoreComputeService;

public class MockHrpTrackScoreComputeServiceImpl implements HrpTrackScoreComputeService{

	@Override
	public Integer countGyFlyAndTrainTravelTimes(String identy, Date startDay, Date endDay) {
		return 6;
	}

	@Override
	public Long countInternetBarStayTime(String identy, Date startDay, Date endDay) {
		return 432000000L;
	}

	@Override
	public Long countHotelStayTime(String identy, Date startDay, Date endDay) {
		return 1814400000L;
	}

}
