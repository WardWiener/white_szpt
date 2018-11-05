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
public class HrpPlaceRecordScoreExecutor extends AbstractHrpScoreExecutor{
	
	@Resource
	private HrpScoreUtilService hrpScoreUtilService;
	
	@Resource
	private HrpTrackScoreComputeService hrpTrackScoreComputeService ;

	public static HrpPlaceRecordScoreExecutor getScoreExecutor(String id){
		HrpPlaceRecordScoreExecutor executor = SpringContextUtil.getApplicationContext().getBean(HrpPlaceRecordScoreExecutor.class) ;
		return executor;
	}

	@Override
	public Object[] excute(Parameter... params) {
		
		Date[] dates = getLatestYear();
		Long hotel = hrpTrackScoreComputeService.countHotelStayTime(hrpScoreUtilService.findHrpIndentyById(getHrpId(params)), dates[0], dates[1]) ;
		Long internetBar = hrpTrackScoreComputeService.countInternetBarStayTime(hrpScoreUtilService.findHrpIndentyById(getHrpId(params)), dates[0], dates[1]) ;
		
		return new Parameter[]{
			new DefaultParameter(hotel, RuleDetail.SCORE_DETAIL_TYPE_HOTEL_TIME),	
			new DefaultParameter(internetBar, RuleDetail.SCORE_DETAIL_TYPE_INTERNET_BAR_TIME)
		};
	}
	
	private Date[] getLatestYear(){
		
		Date today = new Date() ;
		
		Calendar cal = Calendar.getInstance() ;
		cal.setTime(today);
		
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
		
		return new Date[]{cal.getTime(), today} ;
	}
}
