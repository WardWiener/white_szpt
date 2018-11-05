package com.taiji.pubsec.szpt.highriskpersonalert.service;

import java.util.Map;

import com.taiji.pubsec.persistence.dao.Pager;
import com.taiji.pubsec.szpt.highriskpersonalert.model.HighriskPerson;
import com.taiji.pubsec.szpt.highriskpersonalert.pojo.HrpScoreResult;


public interface HighriskPersonScoreAnalyzeService {

	/**
	 * 积分预警人员查询
	 * @param pageNo  页号 
	 * @param pageSize  每页条数
	 */
	public Pager<Map<HighriskPerson, Double>> findHPersonScoreByPage(int pageNo, Integer pageSize, Double alertScore);
	
	/**
	 * 查询人员积分详细信息
	 * @param hrpId 高危人id
	 * @return 
	 */
	public HrpScoreResult findHPersonScoreDetail(String hrpId) ;

}
