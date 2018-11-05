package com.taiji.pubsec.szpt.score.util.hrp.busdata.executor;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taiji.pubsec.common.tools.spring.SpringContextUtil;
import com.taiji.pubsec.scoreframework.DefaultParameter;
import com.taiji.pubsec.scoreframework.Parameter;
import com.taiji.pubsec.szpt.score.util.hrp.pojo.RuleDetail;
import com.taiji.pubsec.szpt.score.util.hrp.service.HrpScoreUtilService;
import com.taiji.pubsec.szpt.score.util.service.HrpTrackScoreComputeService;

@Service
public class HrpTripRecordScoreExecutor extends AbstractHrpScoreExecutor{
	
	@Resource
	private HrpTrackScoreComputeService hrpTrackScoreComputeService ;
	
	@Resource
	private HrpScoreUtilService hrpScoreUtilService;

	public static HrpTripRecordScoreExecutor getScoreExecutor(String id){
		HrpTripRecordScoreExecutor executor = SpringContextUtil.getApplicationContext().getBean(HrpTripRecordScoreExecutor.class) ;
		return executor;
	}

	@Override
	public Object[] excute(Parameter... params) {
		Date[] dates = getLatestMonth();
		Integer hotel = hrpTrackScoreComputeService.countGyFlyAndTrainTravelTimes(hrpScoreUtilService.findHrpIndentyById(getHrpId(params)), dates[0], dates[1]) ;
		
		return new Parameter[]{
			new DefaultParameter(hotel, RuleDetail.SCORE_POINT_TRIP_RECORD)
		};
	}
	
	private Date[] getLatestMonth(){
		
		Date today = new Date() ;
		
		Calendar cal = Calendar.getInstance() ;
		cal.setTime(today);
		
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
		
		return new Date[]{cal.getTime(), today} ;
	}
}
